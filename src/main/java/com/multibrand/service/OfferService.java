package com.multibrand.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multibrand.dao.OfferCategoryDAOIF;
import com.multibrand.dao.OfferDAOIF;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.domain.SSDomain;
import com.multibrand.domain.SSDomainPortBindingStub;
import com.multibrand.domain.SsBalanceAndUsageDataRequest;
import com.multibrand.domain.SsBalanceAndUsageDataResponse;
import com.multibrand.util.DBConstants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.POWOfferDO;

@Service("offerService")
public class OfferService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	@Qualifier("offerDAO")
	private OfferDAOIF offerDAO;

	@Autowired
	@Qualifier("offerCategoryDAO")
	private OfferCategoryDAOIF offerCategoryDAO;

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

}
