package com.multibrand.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multibrand.dao.OfferCategoryDAOIF;
import com.multibrand.dao.OfferDAOIF;
import com.multibrand.domain.CampaignData;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.OfferInput;
import com.multibrand.domain.OfferOutPut;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PromoOfferOutData;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.domain.SSDomain;
import com.multibrand.domain.SSDomainPortBindingStub;
import com.multibrand.domain.SmallBusinessOfferRequest;
import com.multibrand.domain.SmallBusinessOfferResponse;
import com.multibrand.domain.SsBalanceAndUsageDataRequest;
import com.multibrand.domain.SsBalanceAndUsageDataResponse;
import com.multibrand.dto.OfferPlanSDLVO;
import com.multibrand.dto.SmallBusinessOfferDTO;
import com.multibrand.dto.request.ProductOfferRequest;
import com.multibrand.dto.response.ResidentialOfferPlanDTO;
import com.multibrand.helper.OfferHelper;
import com.multibrand.util.DBConstants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.POWOfferDO;
import com.multibrand.vo.response.TDSPChargeDO;

@Service("offerService")
public class OfferService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	@Qualifier("offerDAO")
	private OfferDAOIF offerDAO;

	@Autowired
	@Qualifier("offerCategoryDAO")
	private OfferCategoryDAOIF offerCategoryDAO;
	
	@Autowired
	private OfferHelper offerHelper;

	/**
	 * This will return OEDomain and set EndPoint URL
	 * 
	 * @return proxy The OEDomain Object
	 */
	protected OEDomain getOEServiceProxy() throws ServiceException {
		return (OEDomain) getServiceProxy(OEDomainPortBindingStub.class,
				OE_SERVICE_ENDPOINT_URL);
	}
	//Starts - POD POW Changes -Arumugam
	public List<POWOfferDO> getPOWOfferCodes(String strESID,
			String strTransactionType, String companyCode, String brandId) {
		logger.debug("Entering OfferService.getPOWOfferCodes() :strESID="
				+ strESID + " strTransactionType=" + strTransactionType);
		List<POWOfferDO> powOfferList = null;
		Map<String, Object> powOfferMap = offerDAO.getPOWOffer(strESID,
				strTransactionType,companyCode,brandId);
		//Ends - POD POW Changes -Arumugam
		if (null != powOfferMap
				&& StringUtils.isBlank((String) powOfferMap
						.get(DBConstants.OUT_ERROR_CODE_LOWER))) {
			powOfferList = (List<POWOfferDO>) powOfferMap
					.get(DBConstants.OUT_CUR_POW_OFFER);
		}

		if (null != powOfferList) {
			logger.debug("OfferService.getPOWOfferCodes() powOfferList.size(): "
					+ powOfferList.size());
			
			for(POWOfferDO offerDO:powOfferList) {
				logger.debug("POW Offer: "+ReflectionToStringBuilder.toString(offerDO,
	                    ToStringStyle.MULTI_LINE_STYLE));
			}
		}
		logger.debug("Exiting OfferService.getPOWOfferCodes()");

		return powOfferList;
	}

	public PromoOfferResponse getOfferPricingFromCCS(
			OfferPricingRequest offerPricingRequest) throws ServiceException {
		logger.debug("getOfferPricingFromCCS : Entering the method:");
		PromoOfferResponse promoOfferResponse = null;
		try {
			OEDomain proxyclient = getOEServiceProxy();
			promoOfferResponse = proxyclient
					.getOfferPricingFromCCS(offerPricingRequest);

			if (StringUtils.isNotBlank(promoOfferResponse.getStrErrMessage())) {
				logger.debug("OfferService.getOfferPricingFromCCS():"
						+ promoOfferResponse.getStrErrMessage());
			}
		} catch (Exception e) {
			logger.error("getOfferPricingFromCCS : Exception while fetching Offer pricing from ccs:"
					, e);
			throw new ServiceException("MSG_ERR_GET_OFFER_PRICING_FROM_CCS");
		}

		logger.debug("getOfferPricingFromCCS : Returning from method : promoOfferResponse:: "
				+ promoOfferResponse);
		return promoOfferResponse;
	}

	public PromoOfferResponse getOfferWithPricingFromCCS(
			PromoOfferRequest promoOfferRequest) throws ServiceException {
		PromoOfferResponse promoOfferResponse = this
				.getPromoOfferResponse(promoOfferRequest);
		return promoOfferResponse;
	}

	private PromoOfferResponse getPromoOfferResponse(
			PromoOfferRequest promoOfferRequest) throws ServiceException {
		logger.debug("getPromoOfferResponse : Entering the method:");
		PromoOfferResponse promoOfferResponse = null;
		try {
			OEDomain proxyclient = getOEServiceProxy();

			promoOfferResponse = proxyclient.getPromoOffers(promoOfferRequest);

			if (StringUtils.isNotBlank(promoOfferResponse.getStrErrMessage())) {
				logger.debug("OfferService.getPromoOfferResponse():"
						+ promoOfferResponse.getStrErrMessage());
			}
		} catch (Exception e) {
			String errorCode = "MSG_ERR_GET_OFFERS_WITH_PRICING_FROM_CCS";
			logger.error("getOfferWithPricingFromCCS : Exception while fetching Offers with pricing from ccs:"
					, e);
			throw new ServiceException(errorCode);

		}
		return promoOfferResponse;
	}

	public List<Map<String, Object>> getOfferCategories(String strOfferCodes) {
		List<Map<String, Object>> offerCategoryList = new ArrayList<Map<String, Object>>();
		try {
			offerCategoryList = offerCategoryDAO
					.getOfferCategoryLookupDetails(strOfferCodes);
		} catch (Exception e) {
			logger.error("Error in getOfferCategories()...", e);
		}
		return offerCategoryList;
	}

	/**
	 * Get Balance and Usage Data
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SsBalanceAndUsageDataResponse getBalanceAndUsageData(
			SsBalanceAndUsageDataRequest request) throws Exception {

		SsBalanceAndUsageDataResponse response = null;
		SSDomain ssdomainProxy = getSSServiceProxy();

		try {
			response = ssdomainProxy.getBalanceAndUsageData(request);
		} catch (RemoteException re) {
			logger.debug(XmlUtil.pojoToXML(request));
			logger.error(re);
			throw re;
		} catch (Exception e) {
			logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			throw e;
		}
		return response;
	}

	/**
	 * This will return SSDomain and set EndPoint URL
	 * 
	 * @return proxy The OEDomain Object
	 */
	protected SSDomain getSSServiceProxy() throws ServiceException {
		return (SSDomain) getServiceProxy(SSDomainPortBindingStub.class,
				SS_SERVICE_ENDPOINT_URL);
	}
	public PromoOfferResponse getOfferFromNRGWS(ProductOfferRequest productOfferRequest) {
		PromoOfferResponse promoOfferResponse = null;
		try {
			OEDomain oeDomain = getOEServiceProxy();
			promoOfferResponse = oeDomain.getPromoOffers(createPromoOfferRequest(productOfferRequest));
		} catch (Exception e) {
			logger.debug("Error in getOfferFromNRGWS : " + e.getMessage());
			promoOfferResponse = new PromoOfferResponse();
			promoOfferResponse.setStrErrCode(e.getMessage());
		}
		return promoOfferResponse;
	}
	
	private PromoOfferRequest createPromoOfferRequest(ProductOfferRequest productOfferRequest) {
		PromoOfferRequest promoOfferRequest = new PromoOfferRequest();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
		
		promoOfferRequest.setStrBrandName(getBrandNameByCompanyCode(productOfferRequest));
		promoOfferRequest.setStrChannelPartner(productOfferRequest.getChannelPartnerCode());
		promoOfferRequest.setStrCompanyCode(productOfferRequest.getCompanyCode());
		promoOfferRequest.setStrLanguage(getLangCode(productOfferRequest));
		promoOfferRequest.setStrTdspCode(productOfferRequest.getTdspCode());
		promoOfferRequest.setStrDate(sdfDate.format(cal.getTime()));
		promoOfferRequest.setStrTime(sdfTime.format(cal.getTime()));
		promoOfferRequest.setStrExcludeIncentive("X");
		if("X".equalsIgnoreCase(productOfferRequest.getAirlinePromo())) {
			promoOfferRequest.setStrExcludeIncentive("");
			promoOfferRequest.setStrMilesOnly("X");
			
		}
//		promoOfferRequest.setStrPromoCode(getPromoOfferCode(productOfferRequest));
		return promoOfferRequest;
	}
	private String getPromoOfferCode(ProductOfferRequest productOfferRequest) {
		return appConstMessageSource.getMessage(productOfferRequest.getChannelPartnerCode(), null, null);
}
private String getLangCode(ProductOfferRequest productOfferRequest) {
	return (productOfferRequest.getLangCode().equalsIgnoreCase("es")? "S" : "E");
}


private String getBrandNameByCompanyCode(ProductOfferRequest productOfferRequest) {
	return appConstMessageSource.getMessage(productOfferRequest.getCompanyCode(), null, null);
}

public SmallBusinessOfferResponse getSMBOfferFromNRGWS(ProductOfferRequest productOfferRequest) {
	SmallBusinessOfferResponse smallBusinessOfferResponse = null;
	try {
		OEDomain oeDomain = getOEServiceProxy();
		smallBusinessOfferResponse = oeDomain.getSMBOffer(createSMBOfferRequest(productOfferRequest));
	} catch (Exception e) {
		logger.error("Error: getSMBOfferFromNRGWS:" + e.getMessage());
		smallBusinessOfferResponse = new SmallBusinessOfferResponse();
		smallBusinessOfferResponse.setErrorCode(e.getMessage());
	}
	return smallBusinessOfferResponse;
}


private SmallBusinessOfferRequest createSMBOfferRequest(ProductOfferRequest productOfferRequest) {
	SmallBusinessOfferRequest smallBusinessOfferRequest = new SmallBusinessOfferRequest();
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	smallBusinessOfferRequest.setBrandID(getBrandNameByCompanyCode(productOfferRequest));
	smallBusinessOfferRequest.setChannelPartnerCode(productOfferRequest.getChannelPartnerCode());
	smallBusinessOfferRequest.setCompanyCode(productOfferRequest.getCompanyCode());
	smallBusinessOfferRequest.setLanCode(getLangCode(productOfferRequest));
	smallBusinessOfferRequest.setTdspCode(productOfferRequest.getTdspCode());
	smallBusinessOfferRequest.setDate(sdfDate.format(cal.getTime()));
	smallBusinessOfferRequest.setTime(sdfTime.format(cal.getTime()));
	
	return smallBusinessOfferRequest;
}


public Map<String, Object> getOfferInfoFromSDL(List<String> offerCodes,
		String langCode) {
	Map<String, Object> offerMap = new HashMap<>();
	String url = offerHelper.getContentServiceEncodedURL(offerCodes,  langCode);
	logger.info("Content Service API URL" + url);
	String jsonResponseFromContentServiceAPI = null;
	try {
		jsonResponseFromContentServiceAPI = getServiceCall(url);	
		
		logger.info("Finish get content from content service");
		offerMap = offerHelper.getOfferPlanMapFromJsonString(jsonResponseFromContentServiceAPI);
		logger.debug("Content Service API response {}", jsonResponseFromContentServiceAPI);
	} catch (Exception ex) {
		logger.error("Error in getOfferInfoFromSDL while call GET request to Content Service API" + ex.getMessage());
		offerMap.put(ERROR, ex.getMessage());
	}
	return offerMap;
}


private String getServiceCall(String url) throws ServiceException {
	try {
		
		HttpResponse response = getHttpClient().execute(new HttpGet(url));
		if (response.getStatusLine().getStatusCode() != 200 ) {
             throw new ServiceException(String.format("Failed to get Data from %s, content service API",url));
		}
		return EntityUtils.toString(response.getEntity());
	} catch (Exception ex) {
		throw new ServiceException("Faild to excute the get call from content service api" + ex.getMessage());
	} 

}


private HttpClient getHttpClient() {
	int timeout = 5;
	RequestConfig config = RequestConfig.custom()
	  .setConnectTimeout(timeout * 1000)
	  .setConnectionRequestTimeout(timeout * 1000)
	  .setSocketTimeout(timeout * 1000).build();
	return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
}


public List<SmallBusinessOfferDTO> constructSmallBusinessOffer(SmallBusinessOfferResponse smallBusinessOfferResponse,
		Map<String, Object> offerMap, ProductOfferRequest productOfferRequest) {
		List<SmallBusinessOfferDTO> smallBusinessOfferDOList = new ArrayList<>();
		SmallBusinessOfferDTO smallBusinessOfferDO = null;
		for (OfferOutPut offerDO : smallBusinessOfferResponse.getOfferOutPutLists()) {
			CampaignData campaignData = offerDO.getOfferData().getCampaignDatas(0);
			OfferInput offerKeyData = offerDO.getOfferInput();
			
			OfferPlanSDLVO offerPlanSDL = (OfferPlanSDLVO)offerMap.get(getOfferCode(offerKeyData.getOfferCode(), offerMap));
			if (offerPlanSDL != null) {
				smallBusinessOfferDO = new SmallBusinessOfferDTO();
				smallBusinessOfferDO.setLanguageCode(productOfferRequest.getLangCode());
				smallBusinessOfferDO.setZipcode(productOfferRequest.getZipCode());
				smallBusinessOfferDO.setChannelPartner(productOfferRequest.getChannelPartnerCode());
				
				smallBusinessOfferDO.setOfferName(offerPlanSDL.getOfferName());
				smallBusinessOfferDO.setOfferHighlights(offerPlanSDL.getHighlights());
				smallBusinessOfferDO.setOfferTagline(offerPlanSDL.getTagLine());
				smallBusinessOfferDO.setOfferOverview(offerPlanSDL.getOverViewText());
				smallBusinessOfferDO.setStrPromoText(offerPlanSDL.getPromotionText());
				smallBusinessOfferDO.setStrPromoTitle(offerPlanSDL.getPromotionTitle());
				smallBusinessOfferDO.setStrThingsToKnow(offerPlanSDL.getSummary());
				smallBusinessOfferDO.setIsRenewable(offerPlanSDL.getIsRenewable());
				smallBusinessOfferDO.setRenewablePercentage(offerPlanSDL.getRenewablePercentage());
				//key input value
				smallBusinessOfferDO.setStrCampaignCode(offerKeyData.getCampaignCode());
				smallBusinessOfferDO.setStrPromoCode(offerKeyData.getPromoCode());
				smallBusinessOfferDO.setStrOfferCode(offerKeyData.getOfferCode());
				smallBusinessOfferDO.setStrWebRank(offerKeyData.getWebRank());
				//campaign data

				smallBusinessOfferDO.setStrValidFromDate(campaignData.getStartdt());
				smallBusinessOfferDO.setStrValidToDate(campaignData.getExpdt());
				smallBusinessOfferDO.setStrDwellingType(campaignData.getDwellingType());
				smallBusinessOfferDO.setStrCustomerSegment(campaignData.getCustSegment());
				smallBusinessOfferDO.setStrMarketSegment(campaignData.getMarket());
				smallBusinessOfferDO.setStrProductCode(campaignData.getProdStrucid());
				smallBusinessOfferDO.setStrContractTerm(campaignData.getContractLength());
				smallBusinessOfferDO.setStrProductPriceCode(campaignData.getPricecd());
				smallBusinessOfferDO.setStrIncentiveCode(campaignData.getIncentivecd());
	
				smallBusinessOfferDO.setStrPlanType(campaignData.getPlanType());

//				smallBusinessOfferDO.setStrPenaltyValue(campaignData.getPenaltyValue());
				smallBusinessOfferDO.setStrPenaltyValue(campaignData.getPenaltyValue().setScale(0,BigDecimal.ROUND_FLOOR));
				smallBusinessOfferDO.setStrPaymentTerm(campaignData.getPaymentTerms());
				offerHelper.setSmallBusinessDocumentID(smallBusinessOfferDO, offerDO);
				smallBusinessOfferDO.setStrLateFee(campaignData.getLateFees());
				smallBusinessOfferDO.setStrInvoiceOptions(campaignData.getInvoiceOptions());
				smallBusinessOfferDO.setStrPaymentOption(campaignData.getPayOptions());
				smallBusinessOfferDO.setStrIncentiveValue(campaignData.getIncentiveValue());

				smallBusinessOfferDO.setStrCustClass(campaignData.getCustClass());
				offerHelper.setAvgpricesMap(smallBusinessOfferDO, campaignData.getAvgpriceTab());
				
				smallBusinessOfferDO.setOfferTDSPCharges(offerHelper.getTDSPCharges(offerDO));
				smallBusinessOfferDOList.add(smallBusinessOfferDO);
				
			}
		}
		logger.info("Finish construction of Business offer plan");
		return smallBusinessOfferDOList;
}


public List<ResidentialOfferPlanDTO> constructResidentalOfferPlan(PromoOfferResponse promoOfferResponse,
		Map<String, Object> offerMap, ProductOfferRequest productOfferRequest) {
	List<ResidentialOfferPlanDTO> offerPlanList = new ArrayList<>();
	ResidentialOfferPlanDTO offerPlan = null;
	for (PromoOfferOutData promoOfferOutData : promoOfferResponse.getOfferOuts()) {
		OfferPlanSDLVO offerPlanSDL = (OfferPlanSDLVO)offerMap.get(getOfferCode(promoOfferOutData.getStrOfferCode(), offerMap));
		if (offerPlanSDL != null) {
			
			
			offerPlan = new ResidentialOfferPlanDTO();
			
			offerPlan.setLanguageCode(productOfferRequest.getLangCode());
			offerPlan.setZipcode(productOfferRequest.getZipCode());
			offerPlan.setChannelPartner(productOfferRequest.getChannelPartnerCode());
			
		    offerPlan.setOfferName(offerPlanSDL.getOfferName());
		    offerPlan.setStrPromoText(offerPlanSDL.getPromotionText());
		    offerPlan.setStrPromoTitle(offerPlanSDL.getPromotionTitle());
		    offerPlan.setOfferHighlights(offerPlanSDL.getHighlights());
		    offerPlan.setOfferTagline(offerPlanSDL.getTagLine());
		    offerPlan.setOfferOverview(offerPlanSDL.getOverViewText());
		    offerPlan.setStrThingsToKnow(offerPlanSDL.getSummary());
		    offerPlan.setIsRenewable(StringUtils.isEmpty(offerPlanSDL.getIsRenewable())? "NO" : offerPlanSDL.getIsRenewable());
		    offerPlan.setRenewablePercentage(offerPlanSDL.getRenewablePercentage());
		    
//			offerPlan.setStrCancelFee(promoOfferOutData.getStrCancelFee());
		    offerPlan.setStrCancelFee(promoOfferOutData.getStrCancelFee().setScale(0,BigDecimal.ROUND_FLOOR));
			offerPlan.setStrWebRank(promoOfferOutData.getStrWebOfferRank());
			offerPlan.setStrContractTerm(promoOfferOutData.getStrContractTerm());
			offerPlan.setStrEFLDocID(promoOfferOutData.getStrEFLDocID());
			offerPlan.setStrYRAACDocID(promoOfferOutData.getStrYRAACDocID());
		    offerPlan.setStrTOSDocID(promoOfferOutData.getStrTOSDocID());
			offerPlan.setStrEFLDocLink(offerHelper.getEflURl(promoOfferOutData.getStrEflUrl()));
		    offerPlan.setStrYRAACDocLink(offerHelper.getDocURlwithID(promoOfferOutData.getStrYRAACDocID()));
		    offerPlan.setStrTOSDocLink(offerHelper.getDocURlwithID(promoOfferOutData.getStrTOSDocID()));
		    offerPlan.setStrCampaignCode(promoOfferOutData.getStrCampaignCode());
		    offerPlan.setStrOfferCode(promoOfferOutData.getStrOfferCode());
		    offerPlan.setStrValidToDate(promoOfferOutData.getStrValidToDate());
		    offerPlan.setStrValidFromDate(promoOfferOutData.getStrValidFromDate());
		    offerPlan.setStrCustomerSegment(promoOfferOutData.getStrCustomerSegment());
		    offerPlan.setStrMarketSegment(promoOfferOutData.getStrMarketSegment());
		    offerPlan.setStrPlanType(promoOfferOutData.getStrPlanType());
		    offerPlan.setStrCampaignDescription(promoOfferOutData.getStrCampaignDescription());
		    offerPlan.setStrCustClass(promoOfferOutData.getStrCustClass());
		    offerPlan.setStrEFLSmartCode(promoOfferOutData.getStrEFLSmartCode());
		    offerPlan.setStrYRAACSmartCode(promoOfferOutData.getStrYRAACSmartCode());
		    offerPlan.setStrPromoCode(promoOfferOutData.getStrPromoCode());
		    offerPlan.setStrProductCode(promoOfferOutData.getStrProductCode());
		    offerPlan.setStrProductPriceCode(promoOfferOutData.getStrProductPriceCode());
		    offerPlan.setStrIncentiveCode(promoOfferOutData.getStrIncentiveCode());
		    offerPlan.setStrOfferCellTrackCode(promoOfferOutData.getStrOfferCellTrackCode());
		    offerPlan.setStrInvoiceOptions(promoOfferOutData.getStrInvoiceOptions());
		    offerPlan.setStrPayOptions(promoOfferOutData.getStrPayOptions());
		    offerPlan.setStrIncentiveDescription(promoOfferOutData.getStrIncentiveDescription());
		    offerPlan.setStrOfferTeaser(promoOfferOutData.getStrOfferTeaser());
		    offerPlan.setStrIncentiveValue(promoOfferOutData.getStrIncentiveValue());
		    offerPlan.setStrPenaltyDesciption(promoOfferOutData.getStrPenaltyDesciption());
		    offerPlan.setStrOfferCodeTitle(promoOfferOutData.getStrOfferCodeTitle());
//		    offerPlan.setStrPenaltyValue(promoOfferOutData.getStrPenaltyValue());
		    offerPlan.setStrPenaltyValue(promoOfferOutData.getStrPenaltyValue().setScale(0,BigDecimal.ROUND_FLOOR));
		    offerPlan.setStrDwellingType(promoOfferOutData.getStrDwellingType());
		    offerPlan.setAvgPrices(promoOfferOutData.getAvgPriceMap());
		    offerPlan.setStrTOSSmartCode(promoOfferOutData.getStrTOSSmartCode());
		    offerPlan.setOfferTDSPCharges(getTDSPChargesDTO(promoOfferOutData.getOfferTDSPCharges()));
		    offerPlanList.add(offerPlan);
		}
	}
	logger.info("Finish construct the ResidentalOfferPlan");
	
	return offerPlanList;
}
private String getOfferCode(String strOfferCode, Map<String, Object> offerMap) {
	if(StringUtils.isEmpty(strOfferCode)) {
		return "-1";
	}
	  for (String key : offerMap.keySet()) { 
            if (key.contains(strOfferCode))
            	return key;
    } 
	return "-1";
}
private TDSPChargeDO getTDSPChargesDTO(PromoOfferTDSPCharge[] offerTDSPCharges) {
	TDSPChargeDO tdspChargeDTO = new TDSPChargeDO();		
	String strBundlingTag = "";
	String strBundlingGroup = "";
	if(null!=offerTDSPCharges && offerTDSPCharges.length>0) {
		for(PromoOfferTDSPCharge promoOfferTDSPCharge : offerTDSPCharges){
			PromoOfferTDSPCharge promoOfferTDSPChargeEntry = promoOfferTDSPCharge;

			strBundlingTag = promoOfferTDSPChargeEntry.getStrBundlingTag();
			strBundlingGroup = promoOfferTDSPChargeEntry.getStrBundlingGroup();
			
			if(StringUtils.equalsIgnoreCase(strBundlingTag, strBundlingGroup+"_MO")){
				tdspChargeDTO.setPerMonthValue(promoOfferTDSPChargeEntry.getStrValue());
			}
			if(StringUtils.equalsIgnoreCase(strBundlingTag, strBundlingGroup+"_KWH")){
				tdspChargeDTO.setPerKWValue(promoOfferTDSPChargeEntry.getStrValue());
			}
		}
		
		logger.info("TDSP perMonth Value"+tdspChargeDTO.getPerMonthValue());
		logger.info("TDSP perKWh Value"+tdspChargeDTO.getPerKWValue());
	}
	return tdspChargeDTO;
}



}
