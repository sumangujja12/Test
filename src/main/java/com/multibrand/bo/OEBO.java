package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.KbaDAO;
import com.multibrand.dao.PersonDao;
import com.multibrand.dao.ServiceLocationDao;
import com.multibrand.domain.AddressDTO;
import com.multibrand.domain.BpMatchCCSRequest;
import com.multibrand.domain.BpMatchCCSResponse;
import com.multibrand.domain.CampEnvironmentOutData;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.FactorDetailDO;
import com.multibrand.domain.GetEsiidResponse;
import com.multibrand.domain.KbaAnswerDTO;
import com.multibrand.domain.KbaErrorDTO;
import com.multibrand.domain.KbaQuestionDTO;
import com.multibrand.domain.KbaQuestionRequest;
import com.multibrand.domain.KbaQuestionResponse;
import com.multibrand.domain.KbaQuizAnswerDTO;
import com.multibrand.domain.KbaResponseAssessmentDTO;
import com.multibrand.domain.KbaResponseOutputDTO;
import com.multibrand.domain.KbaResponseReasonDTO;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.KbaSubmitAnswerResponse;
import com.multibrand.domain.NewCreditScoreRequest;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferPricingRequest;
import com.multibrand.domain.OfferRequestDTO;
import com.multibrand.domain.PayByBankRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.PromoCharityOutData;
import com.multibrand.domain.PromoOfferOutData;
import com.multibrand.domain.PromoOfferOutDataAvgPriceMapEntry;
import com.multibrand.domain.PromoOfferRequest;
import com.multibrand.domain.PromoOfferResponse;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.domain.ProspectRequest;
import com.multibrand.domain.ProspectResponse;
import com.multibrand.domain.SegmentedFlagsOutData;
import com.multibrand.domain.SubmitEnrollResponse;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.TdspDetailsResponse;
import com.multibrand.domain.TdspDetailsResponseStrTdspCodesEntry;
import com.multibrand.domain.UpdateCRMAgentInfoResponse;
import com.multibrand.dto.KBAErrorDTO;
import com.multibrand.dto.KBAResponseAssessmentDTO;
import com.multibrand.dto.KBAResponseReasonDTO;
import com.multibrand.dto.KBASubmitResultsDTO;
import com.multibrand.dto.KbaAnswerResponseDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.AddPersonRequest;
import com.multibrand.dto.request.AddServiceLocationRequest;
import com.multibrand.dto.request.AffiliateOfferRequest;
import com.multibrand.dto.request.AgentDetailsRequest;
import com.multibrand.dto.request.BankDepositPaymentRequest;
import com.multibrand.dto.request.CCDepositPaymentRequest;
import com.multibrand.dto.request.CheckPendingServiceRequest;
import com.multibrand.dto.request.CheckPermitRequest;
import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidDetailsRequest;
import com.multibrand.dto.request.GetEsiidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.GiactBankValidationRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.TLPOfferRequest;
import com.multibrand.dto.request.UCCDataRequest;
import com.multibrand.dto.request.UpdateETFFlagToCRMRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.BankDepositPaymentResponse;
import com.multibrand.dto.response.CCDepositPaymentResponse;
import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.dto.response.CheckPermitResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidDetailsResponse;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.dto.response.TLPOfferResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.dto.response.UpdateETFFlagToCRMResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.exception.OEException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.proxy.OEProxy;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.service.AddressService;
import com.multibrand.service.OEService;
import com.multibrand.service.OfferService;
import com.multibrand.service.PaymentService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.CommonUtil.validationFormatEnum;
import com.multibrand.util.CompanyMsgText;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.TogglzUtil;
import com.multibrand.util.Token;
import com.multibrand.vo.request.CharityDetailsVO;
import com.multibrand.vo.request.ESIDDO;
import com.multibrand.vo.request.EnrollmentReportDataRequest;
import com.multibrand.vo.request.KBAQuestionAnswerVO;
import com.multibrand.vo.request.OESignupVO;
import com.multibrand.vo.request.TokenRequestVO;
import com.multibrand.vo.response.AffiliateOfferDO;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.CampEnvironmentDO;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GMEEnviornmentalImpact;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.GiactBankValidationResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.OfferDO;
import com.multibrand.vo.response.OfferPriceDO;
import com.multibrand.vo.response.OfferPriceWraperDO;
import com.multibrand.vo.response.OfferResponse;
import com.multibrand.vo.response.POWOfferDO;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.vo.response.ProspectDataResponse;
import com.multibrand.vo.response.SegmentedFlagDO;
import com.multibrand.vo.response.ServiceAddressDO;
import com.multibrand.vo.response.TDSPChargeDO;
import com.multibrand.vo.response.TDSPDO;
import com.multibrand.vo.response.TLPOfferDO;
import com.multibrand.vo.response.TdspResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.vo.response.KBO.Option;
import com.multibrand.vo.response.KBO.Question;
import com.multibrand.vo.response.billingResponse.AddressDO;
import com.multibrand.web.i18n.WebI18nMessageSource;
import com.reliant.domain.AddressValidateResponse;



/**
 * @author vsood30
 *
 */
@Component
public class OEBO extends OeBoHelper implements Constants{
	
	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	// ~Autowire entries
	@Autowired
	BillingBO billingBO;
		
	@Autowired
	OEService oeService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private TOSService tosService;

	@Autowired
	private OfferService offerService;
	
	@Autowired
	@Qualifier("addressDAO")
	private AddressDAOIF addressDAO;

	@Autowired
	@Qualifier("serviceLocationDAO")
	private ServiceLocationDao serviceLocationDAO;
	
	@Resource(name = "personDAO")
	private PersonDao personDao;
	
	@Resource(name = "kbaDAO")
	private KbaDAO kbaDao;
	
	@Autowired
	private OEProxy oeProxy;
	
	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	@Autowired
	@Resource(name = "oweRPMFactors")
	private WebI18nMessageSource oweRPMFactors;

	// ~Autowire entries
	@Autowired
	OERequestHandler oeRequestHandler;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	ContentHelper contentHelper;
	
	 @Autowired
	private TogglzUtil togglzUtil;
	
	/**
	 * Method to return Company Code + Brand Id specific offers based on TDSP code or address or esid
	 * @param locale
	 * @param companyCode
	 * @param brandId
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servZipCode
	 * @param promoCode
	 * @param tdspCode
	 * @param esid
	 * @param sessionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OfferResponse getOffers(String locale, String companyCode, String brandId,
			String servStreetNum, String servStreetName,
			String servStreetAptNum, String servZipCode, String promoCode,
			String tdspCode, String esid, String sessionId,String transactionType) {

		logger.debug("OEBO.getOffers() Start");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfDate = new SimpleDateFormat(MM_dd_yyyy);
		SimpleDateFormat sdfTime = new SimpleDateFormat(TIME_FORMAT);
		OfferResponse offerResponse = new OfferResponse();
		OESignupVO oeSignupVO = new OESignupVO();
		oeSignupVO.setOfferDate(sdfDate.format(cal.getTime()));
		oeSignupVO.setOfferTime(sdfTime.format(cal.getTime()));
		offerResponse.setOfferDate(oeSignupVO.getOfferDate());
		offerResponse.setOfferTime(oeSignupVO.getOfferTime());
		
		//Starts - POD POW Changes -Arumugam
		oeSignupVO.setTransactionType(transactionType);
		//End - POD POW Changes -Arumugam
		
		String strESIDNumber = null;
		String strMeterType = "";
		String strPromoCode = "";
		String streetAddress = "";
		boolean eSenseEligible = false;
		Map<String, String> promoCodeMap = null;
		if (StringUtils.isBlank(companyCode)) {
			logger.debug("OEBO.getOffers()  Company Code is mandatory.");
			offerResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			offerResponse.setResultDescription(COMPANY_CODE_MANDATORY);
		} else if (StringUtils.isBlank(servZipCode)
				&& StringUtils.isBlank(tdspCode)
				&& StringUtils.isBlank(esid)) {
			logger.debug("OEBO.getOffers()  Zip code or TDSP code is mandatory.");
			offerResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			offerResponse.setResultDescription(ZIPCODE_OR_TDSP_OR_ESID_MANDATORY);
		} else {
			try {
				oeSignupVO.setCompanyCode(companyCode);
				oeSignupVO.setBrandId(brandId);
				if(null!=brandId && brandId.equals(CIRRO_BRAND_NAME)){
					oeSignupVO.setCharityId(CIRRO_DUMMY_CHARITY_ID); //Setting Dummy CharityId for Cirro for Reactive offers call
				}
				// If we got TDSP code from caller of this method ignore address
				// details.
				AddressDO serviceAddressDO = new AddressDO();
				oeSignupVO.setLocale(locale);
				//CASE 1: ESID entered
				if (StringUtils.isNotBlank(esid)) {
					logger.debug("OEBO.getOffers() getting offers based on ESID Number="+ oeSignupVO.getEsidNumber());
					logger.debug("OEBO.getOffers() Getting TDSP Code for ESID Number="+ oeSignupVO.getEsidNumber());
					oeSignupVO.setEsidNumber(esid);
					offerResponse.setEsid(esid);
					TdspByESIDResponse tdspByESIDResponse = this.tosService.ccsGetTDSPFromESID(esid,oeSignupVO.getCompanyCode(),sessionId);
					if ((tdspByESIDResponse != null) && (StringUtils.isNotBlank(tdspByESIDResponse.getServiceId()))) {
						String tdspCodeCCS = tdspByESIDResponse.getServiceId();
						tdspCodeCCS = tdspCodeCCS.trim();
						oeSignupVO.setTdspCodeCCS(tdspCodeCCS);
						offerResponse.setStrTDSPCode(tdspCodeCCS);
						oeSignupVO.setTdspCode(this.appConstMessageSource.getMessage("ccs.tdsp.web.equivalent."+ tdspCodeCCS,null, null));
						oeSignupVO.setTdspName(this.appConstMessageSource.getMessage(tdspCodeCCS, null,null));
						oeSignupVO.setGeoZone(null);
						logger.debug("OEBO.getOffers() Got TDSP Info from ESID."+oeSignupVO.getTdspCodeCCS()+ "~"+ oeSignupVO.getTdspCode()+ "~"+ oeSignupVO.getTdspName());
					} else {
						logger.debug("OEBO.getOffers() Failed in getting TDSP Code for ESID Number="+ oeSignupVO.getEsidNumber());
					}
				} 
				//CASE 2: TDSP code entered and NO ESID entered
				if (StringUtils.isBlank(oeSignupVO.getTdspCodeCCS()) && StringUtils.isNotBlank(tdspCode)) {
					logger.debug("OEBO.getOffers() getting offers based on TDSP code : "	+ tdspCode);
					tdspCode = tdspCode.trim();
					offerResponse.setStrTDSPCode(tdspCode);
					streetAddress = "";
					oeSignupVO.setTdspCodeCCS(tdspCode);
					oeSignupVO.setTdspCode(appConstMessageSource.getMessage(
							"ccs.tdsp.web.equivalent." + tdspCode, null, null));
					oeSignupVO.setTdspName(appConstMessageSource.getMessage(
							tdspCode, null, null));
					//Added by Vipul starts
					serviceAddressDO.setStrStreetNum(servStreetNum);
					serviceAddressDO.setStrStreetName(servStreetName);
					serviceAddressDO.setStrApartNum(servStreetAptNum);
					serviceAddressDO.setStrZip(servZipCode);					
					//Added by Vipul ends
					oeSignupVO.setGeoZone(null);
					oeSignupVO.setServiceAddressDO(serviceAddressDO);
				} 
				
				//CASE 3: Address and / or zip code entered and NO ESID and NO TDSP code entered
				if(StringUtils.isBlank(oeSignupVO.getTdspCodeCCS())) {
					logger.debug("OEBO.getOffers() getting offers based on address or zip code");
					serviceAddressDO.setStrStreetNum(servStreetNum);
					serviceAddressDO.setStrStreetName(servStreetName);
					serviceAddressDO.setStrApartNum(servStreetAptNum);
					serviceAddressDO.setStrZip(servZipCode);
					List<Map<String, Object>> cityStateList = null;
					try {
						cityStateList = this.addressService
								.getCityStateFromZip(servZipCode);

						for (Map<String, Object> cityStateMap : cityStateList) {
							serviceAddressDO.setStrCity((String) cityStateMap
									.get(CITY));
							serviceAddressDO.setStrState((String) cityStateMap
									.get(STATE));
							logger.debug("OEBO.getOffers() City From ZIP: "
									+ serviceAddressDO.getStrCity());
							logger.debug("OEBO.getOffers() State From ZIP: "
									+ serviceAddressDO.getStrState());
						}
					} catch (Exception e) {
						logger.error("OEBO.getOffers() Error with call addressService.getCityStateFromZip: "
								, e);
					}

					oeSignupVO.setServiceAddressDO(serviceAddressDO);
					streetAddress = serviceAddressDO.getStrStreetNum() + " "
							+ serviceAddressDO.getStrStreetName();
					
					if (StringUtils.isNotBlank(streetAddress)) {
						try {
							logger.debug("OEBO.getOffers()  street address entered! getting ESID info");
							oeSignupVO = getESIDInformation(oeSignupVO,	companyCode, sessionId);
							if (StringUtils.isNotBlank(oeSignupVO.getEsidNumber())) {
								strESIDNumber = oeSignupVO.getEsidNumber();
								offerResponse.setEsid(strESIDNumber);
								logger.debug("OEBO.getOffers() Getting TDSP Code for ESID Number="+ oeSignupVO.getEsidNumber());
								TdspByESIDResponse tdspByESIDResponse = this.tosService.ccsGetTDSPFromESID(strESIDNumber,oeSignupVO.getCompanyCode(),sessionId);
								if ((tdspByESIDResponse != null) && (StringUtils.isNotBlank(tdspByESIDResponse.getServiceId()))) {
									String tdspCodeCCS = tdspByESIDResponse.getServiceId();
									tdspCodeCCS = tdspCodeCCS.trim();
									oeSignupVO.setTdspCodeCCS(tdspCodeCCS);
									offerResponse.setStrTDSPCode(tdspCodeCCS);
									oeSignupVO.setTdspCode(this.appConstMessageSource.getMessage("ccs.tdsp.web.equivalent."+ tdspCodeCCS,null, null));
									oeSignupVO.setTdspName(this.appConstMessageSource.getMessage(tdspCodeCCS, null,null));
									oeSignupVO.setGeoZone(null);
									logger.debug("OEBO.getOffers() Got TDSP Info from ESID."
											+ oeSignupVO.getTdspCodeCCS()+ "~"+ oeSignupVO.getTdspCode()+ "~"+ oeSignupVO.getTdspName());
								} else {
									logger.debug("OEBO.getOffers() Failed in getting TDSP Code for ESID Number="
											+ oeSignupVO.getEsidNumber());
								}
							} else {
								logger.debug("OEBO.getOffers() getESIDInformation FAILED. Nevermind! Continuing with process... ");
							}
						} catch (Exception e) {
							logger.error("OEBO.getOffers() getESIDInformation FAILED. Nevermind! Continuing with process... "
									, e);
						}

						offerResponse.setServiceAddress(populateServiceAddressDO(oeSignupVO.getServiceAddressDO()));
					}

					if ((StringUtils.isBlank(oeSignupVO.getTdspCodeCCS()))) {
						logger.debug("OEBO.getOffers() Failed in getting TDSP Code for ESID! Now getting from the DB call");

						TdspDetailsResponse tdspDetailsResponse = this.addressService
								.getTDSP(oeSignupVO.getServiceAddressDO(),
										oeSignupVO.getCompanyCode());

						if ((tdspDetailsResponse != null)
								&& (StringUtils.isBlank(tdspDetailsResponse
										.getStrErrCode()))) {
							if (tdspDetailsResponse.isMultiTdsp()) {
								logger.debug("OEBO.getOffers() Got Multi TDSP codes from DB:"
										+ tdspDetailsResponse.getStrTdspCodes());
								oeSignupVO.setGeoZone(tdspDetailsResponse
										.getStrGeoZone());
								TdspDetailsResponseStrTdspCodesEntry[] strTdspCodesMap = tdspDetailsResponse
										.getStrTdspCodes();
								for (TdspDetailsResponseStrTdspCodesEntry tdspDetailsResponseStrTdspCodesEntry : strTdspCodesMap) {
									try {
										String ccsTDSPCode = this.appConstMessageSource
												.getMessage(
														tdspDetailsResponseStrTdspCodesEntry
																.getKey(), null,
														null);
										logger.debug("OEBO.getOffers() - Resolved ccsTDSPCode:"
												+ ccsTDSPCode);
										oeSignupVO.setTdspCodeCCS(ccsTDSPCode);
										oeSignupVO
												.setTdspCode(tdspDetailsResponseStrTdspCodesEntry
														.getKey());
										oeSignupVO
												.setTdspName(tdspDetailsResponseStrTdspCodesEntry
														.getValue());
										break;
										/* 
										 * Commented below code for performace optimization 
										 * 
										PromoOfferResponse promoOfferResponse = this.oeService
												.getOfferWithPricingFromCCS(
														createPromoOfferRequest(oeSignupVO),
														companyCode, sessionId);

										if (promoOfferResponse != null) {
											PromoOfferOutData[] promoOfferOutDataArray = promoOfferResponse
													.getOfferOuts();
											if ((promoOfferOutDataArray != null)
													&& (promoOfferOutDataArray.length > 0)) {
												logger.info("OEBO.getOffers() - Resolved ccsTDSPCode with Offers:"
														+ ccsTDSPCode);
												break;
											}
										}
										*/
									} catch (Exception localException) {
										logger.error("inside getOffers::exception in getOffers::  ", localException);
										logger.debug("OEBO.getOffers() - Non Supported TDSPCode:"
												+ tdspDetailsResponseStrTdspCodesEntry
														.getKey());
										// do nothing try your luck with another
										// TDSP code
									}
								}
							} else {
								logger.debug("OEBO.getOffers() Single TDSP code:"
										+ tdspDetailsResponse.getStrTdsp());
								oeSignupVO.setTdspCodeCCS(this.appConstMessageSource.getMessage(tdspDetailsResponse.getStrTdsp(), null, null));
								oeSignupVO.setTdspCode(tdspDetailsResponse.getStrTdsp());
								oeSignupVO.setTdspName(tdspDetailsResponse.getStrTdspName());
								oeSignupVO.setGeoZone(tdspDetailsResponse.getStrGeoZone());
							}
						} else {
							logger.debug("OEBO.getOffers() addressService.getTDSP() call results an Error: "
									+ tdspDetailsResponse.getStrErrMessage());
							oeSignupVO.setGeoZone(NOZ);
						}
					}
				}
				
				logger.debug("OEBO.getOffers() After all calculation getTdspCodeccs"	+ oeSignupVO.getTdspCodeCCS());
				logger.debug("OEBO.getOffers() After all calculation getTdspCode"	+ oeSignupVO.getTdspCode());
				logger.debug("OEBO.getOffers() After all calculation getTdspName"	+ oeSignupVO.getTdspName());
				logger.debug("OEBO.getOffers() After all calculation getGeoZone"	+ oeSignupVO.getGeoZone());

				if (StringUtils.isNotBlank(oeSignupVO.getTdspCode())) {
					offerResponse.setStrTDSPCode(oeSignupVO.getTdspCodeCCS());
					if (StringUtils.isBlank(offerResponse.getEsid()) 
							&& null!=oeSignupVO.getServiceAddressDO() 
							&& StringUtils.isNotBlank(oeSignupVO.getServiceAddressDO().getStrStreetName())) {
						try {
							oeSignupVO = getESIDInformation(oeSignupVO,
									companyCode, sessionId);
						} catch (Exception e) {
							logger.error("OEBO.getOffers() getESIDInformation call FAILED. Nevermind! Continuing with process.:"
									, e);
						}
					}

					if ((oeSignupVO.getEsidDO() != null)
							&& (!oeSignupVO.getEsidDO().isHasError())
							&& (oeSignupVO.getEsidDO().getMeterType() != null)) {
						strMeterType = oeSignupVO.getEsidDO().getMeterType();
					}

					promoCodeMap = splitPromoCodes(promoCode);
					logger.debug("OEBO.getOffers() strMeterType:" + strMeterType);
					if (METER_TYPE_AMSR.equals(strMeterType)) {
						strPromoCode = promoCodeMap.get(PROMO_CODE_2);
					} else if (METER_TYPE_AMSM.equals(strMeterType)) {
						strPromoCode =  promoCodeMap.get(PROMO_CODE_2);
					} else {
						strPromoCode =  promoCodeMap.get(PROMO_CODE_1);
					}

					logger.debug("OEBO.getOffers() Using promocode:"	+ strPromoCode);
					logger.debug("OEBO.getOffers() Using esenseflag:"+ eSenseEligible);
					oeSignupVO.setPromoCodeEntered(strPromoCode);

					Map<String, Object> resultMap = null;
					if (StringUtils.isNotBlank(oeSignupVO.getEsidNumber()) && StringUtils.isBlank(strPromoCode)) {
						//INC0240072 changes
						//Starts - POD POW Changes -Arumugam
						// 0271- Green Mountain, 0391 - CE - Cirro, 0121 -Reliant 
						if( (StringUtils.equals(companyCode,CIRRO_COMPANY_CODE) &&  (StringUtils.equals(brandId, CIRRO_BRAND_NAME)) ) || (StringUtils.equals(companyCode,GME_RES_COMPANY_CODE)) ||(StringUtils.equals(companyCode,COMPANY_CODE_RELIANT)) ){
							//Ends - POD POW Changes -Arumugam
							offerResponse.setStrOfferFetchSource(OFFER_FETCH_SOURCE_POW);
							logger.debug("OEBO.getOffers() Started getting regular POW offers.");
							resultMap = new HashMap<String, Object>();
							resultMap = getPOWOffers(oeSignupVO);
							logger.debug("OEBO.getOffers() Completed getting regular POW offers.");
						}else{
							logger.debug("Company code "+companyCode+" ,brandId "+brandId+" is NOT setup for POW Offers. Continue getting reactive offers!!!");
						}
					}

					if (null == resultMap || null == resultMap.get(OFFERS_LIST)	|| ((List<OfferDO>) resultMap.get(OFFERS_LIST)).isEmpty()) {
						offerResponse.setStrOfferFetchSource(OFFER_FETCH_SOURCE_REACTIVE);
						logger.debug("OEBO.getOffers() Started getting REACTIVE offers.");
						resultMap = getReactiveOffers(oeSignupVO);
						logger.debug("OEBO.getOffers() Completed getting REACTIVE offers.");
					}
					
					if (resultMap != null) {
						OfferDO[] offerDOArray = null;
						List<OfferDO> offersList = (List<OfferDO>) (resultMap).get(OFFERS_LIST);
						if ((offersList != null) && (offersList.size() > 0)) {
							offerDOArray = offersList.toArray(new OfferDO[offersList.size()]);
							offerResponse.setOfferDOList(offerDOArray);
						} else {
							offerResponse.setOfferDOList(offerDOArray);
						}
						offerResponse.setStrErrorCode((String) resultMap.get(CCS_ERROR));
					}
				}
				offerResponse.setResultCode(RESULT_CODE_SUCCESS);
				offerResponse.setResultDescription(MSG_SUCCESS);
			} catch (Exception e) {
				logger.error("Exceptions in OEBO.getOffers()" , e);
				offerResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				offerResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), offerResponse);
			}
		}

		logger.debug("OEBO.getOffers() End");
		return offerResponse;
	}

	private ServiceAddressDO populateServiceAddressDO(AddressDO addressDO) {
		ServiceAddressDO serviceAddressDO = new ServiceAddressDO();
		serviceAddressDO.setStrApartNum(addressDO.getStrApartNum());
		serviceAddressDO.setStrCity(addressDO.getStrCity());
		serviceAddressDO.setStrState(addressDO.getStrState());
		serviceAddressDO.setStrStreetName(addressDO.getStrStreetName());
		serviceAddressDO.setStrStreetNum(addressDO.getStrStreetNum());
		serviceAddressDO.setStrZip(addressDO.getStrZip());
		serviceAddressDO.setStrZipComplete(addressDO.getStrZipComplete());
		return serviceAddressDO;
	}

	/**
	 * Method to create promotional offer Request
	 * 
	 * @param oeSignupVO
	 * @return PromoOfferRequest
	 */
	private PromoOfferRequest createPromoOfferRequest(OESignupVO oeSignupVO) {
		logger.debug("OEBO.createPromoOfferRequest() start");
		PromoOfferRequest promoOfferRequest = new PromoOfferRequest();
		promoOfferRequest.setStrCompanyCode(oeSignupVO.getCompanyCode());
		if(StringUtils.isNotBlank(oeSignupVO.getBrandId())){
			promoOfferRequest.setStrBrandName(oeSignupVO.getBrandId());
		}
		if(StringUtils.isNotBlank(oeSignupVO.getCharityId())){
			promoOfferRequest.setStrcharityId(oeSignupVO.getCharityId());
		}
		promoOfferRequest.setStrDate(oeSignupVO.getOfferDate());
		promoOfferRequest.setStrTime(oeSignupVO.getOfferTime());
		if ((StringUtils.isNotBlank(oeSignupVO.getLocale()))
				&& ((ES_US.equalsIgnoreCase(oeSignupVO.getLocale())) || (LANGUAGE_CODE_ES
						.equalsIgnoreCase(oeSignupVO.getLocale())))) {
			promoOfferRequest.setStrLanguage(LANGUAGE_CODE_ES);
		} else {
			promoOfferRequest.setStrLanguage(LANGUAGE_CODE_EN);
		}
		promoOfferRequest.setStrPromoCode(oeSignupVO.getPromoCodeEntered());
		promoOfferRequest.setStrTdspCode(oeSignupVO.getTdspCodeCCS());
		logger.debug("OEBO.createPromoOfferRequest() StrCompanyCode:"
				+ promoOfferRequest.getStrCompanyCode());
		logger.debug("OEBO.createPromoOfferRequest() StrBrandName:"
				+ promoOfferRequest.getStrBrandName());
		logger.debug("OEBO.createPromoOfferRequest() StrcharityId:"
				+ promoOfferRequest.getStrcharityId());
		logger.debug("OEBO.createPromoOfferRequest() StrDate:"
				+ promoOfferRequest.getStrDate());
		logger.debug("OEBO.createPromoOfferRequest() StrTime:"
				+ promoOfferRequest.getStrTime());
		logger.debug("OEBO.createPromoOfferRequest() StrLanguage:"
				+ promoOfferRequest.getStrLanguage());
		logger.debug("OEBO.createPromoOfferRequest() StrPromoCode:"
				+ promoOfferRequest.getStrPromoCode());
		logger.debug("OEBO.createPromoOfferRequest() StrTdspCode:"
				+ promoOfferRequest.getStrTdspCode());
		logger.debug("OEBO.createPromoOfferRequest() end");
		return promoOfferRequest;
	}

	/**
	 * Method for Trillium cleanup, ESID and ESID Profile calls
	 * 
	 * @param oeSignupVO
	 * @param companyCode
	 * @param sessionId
	 * @return OESignupVO
	 */
	public OESignupVO getESIDInformation(OESignupVO oeSignupVO,
			String companyCode, String sessionId) {
		logger.info("OEBO.getESIDInformation() start");
		EsidProfileResponse esidProfileResponse = null;
		AddressDO serviceAddressDO = oeSignupVO.getServiceAddressDO();
		if (StringUtils.isNotBlank(serviceAddressDO.getStrStreetName())) {
			AddressValidateResponse addressResponse = null;
			try {
				addressResponse = this.addressService
						.performTrilliumCleanup(serviceAddressDO);
				serviceAddressDO.setTrilliumMatchStatus(addressResponse
						.getMatchStatusFlag());
			} catch (Exception e) {
				logger.error("inside getESIDInformation:: exception occured ::", e);
				serviceAddressDO.setTrilliumMatchStatus(NO_MATCH);
			}

			if ((addressResponse != null)
					&& (!NO_MATCH.equals(serviceAddressDO
							.getTrilliumMatchStatus()))
					&& (serviceAddressDO.getTrilliumMatchStatus() != null)) {
				logger.debug("OEBO.getESIDInformation() Trillium cleanup SUCCESSFUL");
				serviceAddressDO = handleAddressValidateResponse(addressResponse);
				serviceAddressDO.setTrilliumMatchStatus(addressResponse
						.getMatchStatusFlag());
				serviceAddressDO.setTrilliumCallStatus(addressResponse
						.getStatusValue());
				if ((addressResponse.getMatchStatusFlag() != null)
						&& (COMPLETE_MATCH.equals(addressResponse
								.getMatchStatusFlag()))) {
					logger.debug("OEBO.getESIDInformation() GENERATING TRILLIUM CLEANED ADDRESS DO SUCCESSFUL");
				}
				try {
					GetEsiidResponse esidResponse = this.addressService
							.getESID(serviceAddressDO,
									oeSignupVO.getCompanyCode());

					if ((esidResponse != null)
							&& (StringUtils.isBlank(esidResponse
									.getStrErrCode()))
							&& (StringUtils.isNotBlank(esidResponse
									.getStrESIID()))
							&& (!esidResponse.isMultiESIIDs())
							&& (!"NESID".equals(esidResponse.getStrESIID()))) {
						oeSignupVO.setEsidNumber(esidResponse.getStrESIID());
						logger.debug("OEBO.getESIDInformation() GETTING ESID SUCCESSFUL:"
								+ esidResponse.getStrErrCode()
								+ " :: "
								+ esidResponse.getStrESIID());
						esidProfileResponse = this.addressService
								.getESIDProfile(esidResponse.getStrESIID(),
										oeSignupVO.getCompanyCode());
						ESIDDO esidDO = setESIDDTO(esidProfileResponse);
						oeSignupVO.setEsidDO(esidDO);
						logger.debug("OEBO.getESIDInformation() GETTING ESID PROFILE SUCCESSFUL");
					} else {
						logger.debug("OEBO.getESIDInformation() GETTING ESID FAILED:"
								+ esidResponse.getStrErrCode());
					}
				} catch (ServiceException localServiceException) {
					logger.error("ServiceException in OEBO.getESIDInformation():"
							, localServiceException);
				}
			}
		}
		oeSignupVO.setServiceAddressDO(serviceAddressDO);
		return oeSignupVO;
	}

	/**
	 * Method to populate AddressDO from AddressValidateResponse
	 * 
	 * @param addressResponse
	 *            AddressValidateResponse
	 * @return AddressDO
	 */
	private AddressDO handleAddressValidateResponse(
			AddressValidateResponse addressResponse) {
		AddressDO trilliumCleanedAddressDTO = new AddressDO();
		trilliumCleanedAddressDTO.setStrStreetNum(addressResponse
				.getStreetNum());
		trilliumCleanedAddressDTO.setStrStreetName(addressResponse
				.getStreetName());
		trilliumCleanedAddressDTO
				.setStrApartNum(addressResponse.getAptNumber());
		trilliumCleanedAddressDTO.setStrCity(addressResponse.getCity());
		trilliumCleanedAddressDTO.setStrState(addressResponse.getState());
		String zipCode = addressResponse.getZipCode();
		trilliumCleanedAddressDTO.setStrZipComplete(zipCode);
		if (null != zipCode && zipCode.length() > 5) {
			trilliumCleanedAddressDTO.setStrZip(addressResponse.getZipCode()
					.substring(0, 5));
		} else {
			trilliumCleanedAddressDTO.setStrZip(addressResponse.getZipCode());
		}
		return trilliumCleanedAddressDTO;
	}

	/**
	 * Method to populate ESIDDO from EsidProfileResponse
	 * 
	 * @param esidProfileResponse
	 *            EsidProfileResponse
	 * @return ESIDDO
	 */
	private ESIDDO setESIDDTO(EsidProfileResponse esidProfileResponse) {
		logger.debug("OEBO.setESIDDTO() start");
		ESIDDO esidDO = new ESIDDO();
		if (esidProfileResponse != null) {
			if (StringUtils.isNotBlank(esidProfileResponse.getErrorCode())) {
				esidDO.setHasError(true);
				esidDO.setEsidProfileErrorCode(esidProfileResponse
						.getErrorCode());
				esidDO.setEsidProfileErrorDescription(esidProfileResponse
						.getErrorMessage());
			} else {
				esidDO.setHasError(false);
				esidDO.setEsidCount(1);
				esidDO.setEsidNumber(esidProfileResponse.getESID());
				esidDO.setEsidStatus(esidProfileResponse.getEsidStatus());
				esidDO.setMeterType(esidProfileResponse.getMeterType());
				esidDO.setPremiseType(esidProfileResponse.getPremiseType());
				esidDO.setRecentDisconnectFlag(esidProfileResponse
						.getRecentDisconnectFlag());
				esidDO.setSwitchHoldStatus(esidProfileResponse
						.getSwitchHoldStatus());
				//Start || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
				if(StringUtils.equalsIgnoreCase(esidProfileResponse.getBlockStatus(), FLAG_X )) {
					esidDO.setEsidBlocked(true);
				}else{
					esidDO.setEsidBlocked(false);
				}
				//END || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
				
			}
			logger.debug("OEBO.setESIDDTO() esidDTO:: " + esidDO);
		}
		logger.debug("OEBO.setESIDDTO() end");
		return esidDO;
	}
	
	/**
	 * Method to create Map of promotional codes from comma separated
	 * promotional codes
	 * 
	 * @param promoCodes
	 * @return Map<String, String>
	 */
	public Map<String, String> splitPromoCodes(String promoCodes) {
		Map<String, String> promoCodeMap = new HashMap<String, String>();
		logger.debug("OEBO.splitPromoCodes() Initial txtPromocode = "
				+ promoCodes);
		if ((StringUtils.isNotBlank(promoCodes)) && (promoCodes.contains(","))) {
			String[] promoCodesArr = promoCodes.split(",");
			if (promoCodesArr.length >= 2) {
				promoCodeMap.put(PROMO_CODE_1, promoCodesArr[0].trim());
				promoCodeMap.put(PROMO_CODE_2, promoCodesArr[1].trim());
			} else if (promoCodesArr.length == 1) {
				promoCodeMap.put(PROMO_CODE_1, promoCodesArr[0].trim());
				promoCodeMap.put(PROMO_CODE_2, promoCodesArr[0].trim());
			}
		} else {
			promoCodeMap.put(PROMO_CODE_1, promoCodes);
			promoCodeMap.put(PROMO_CODE_2, promoCodes);
		}

		logger.debug("OEBO.splitPromoCodes() AREPromoCode1 = "
				+ promoCodeMap.get(PROMO_CODE_1));
		logger.debug("OEBO.splitPromoCodes() AREPromoCode2 = "
				+ promoCodeMap.get(PROMO_CODE_2));
		return promoCodeMap;
	}

	/**
	 * Method to get POW Offers
	 * 
	 * @param oeSignupVO
	 * @return Map<String, Object>
	 * @throws ServiceException
	 */
	private Map<String, Object> getPOWOffers(OESignupVO oeSignupVO)
			throws ServiceException {
		logger.debug("OEBO.getPOWOffers() start");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(oeSignupVO.getTransactionType())) {
			oeSignupVO.setTransactionType(MVI);
		}
		 //Starts - POD POW Changes -Arumugam
		List<POWOfferDO> powOfferList = offerService.getPOWOfferCodes(
				oeSignupVO.getEsidNumber(), oeSignupVO.getTransactionType(),oeSignupVO.getCompanyCode(), oeSignupVO.getBrandId());
		 //Ends - POD POW Changes -Arumugam
		if ((powOfferList == null) || (powOfferList.isEmpty())) {
			logger.debug("OEBO.getPOWOffers() NO POW offers from DB. EsidNumber :"+oeSignupVO.getEsidNumber()+" TransactionType : "
							+oeSignupVO.getTransactionType()+" Company Code : "+oeSignupVO.getCompanyCode()+" BrandId : "+oeSignupVO.getBrandId());
			resultMap.put(ERR_CODE_KEY, ERR_GET_POW_OFFER);
			resultMap.put(ERROR_TYPE, ERROR_TYPE_DB);
			return resultMap;
		} 

		OfferPricingRequest offerPricingRequest = createOfferPricingRequest(
				powOfferList, oeSignupVO);
		logger.debug("POW Offer Pricing Request: "+ReflectionToStringBuilder.toString(offerPricingRequest,
                ToStringStyle.MULTI_LINE_STYLE));		
		PromoOfferResponse promoOfferResponse = this.offerService
				.getOfferPricingFromCCS(offerPricingRequest);
		logger.debug("POW Offer Pricing Request: "+ReflectionToStringBuilder.toString(offerPricingRequest,
                ToStringStyle.MULTI_LINE_STYLE));
		resultMap = constructOffers(promoOfferResponse, oeSignupVO);
		logger.debug("OEBO.getPOWOffers() end");
		return resultMap;
	}

	private OfferPricingRequest createOfferPricingRequest(
			List<POWOfferDO> powOfferList, OESignupVO oeSignupVO) {
		logger.debug("OEBO.createOfferPricingRequest() start");
		OfferPricingRequest offerPricingRequest = new OfferPricingRequest();
		if ((powOfferList != null) && (!powOfferList.isEmpty())) {
			offerPricingRequest.setStrCompanyCode(oeSignupVO.getCompanyCode());
			offerPricingRequest.setStrDate(oeSignupVO.getOfferDate());
			offerPricingRequest.setStrTime(oeSignupVO.getOfferTime());

			if ((StringUtils.isNotBlank(oeSignupVO.getLocale()))
					&& ((ES_US.equalsIgnoreCase(oeSignupVO.getLocale())) || (LANGUAGE_CODE_ES
							.equalsIgnoreCase(oeSignupVO.getLocale())))) {
				offerPricingRequest.setStrLanguage(LANGUAGE_CODE_ES);
			} else {
				offerPricingRequest.setStrLanguage(LANGUAGE_CODE_EN);
			}
						

			int nOfferCount = powOfferList.size();
			OfferRequestDTO[] offerRequestDTOArr = new OfferRequestDTO[nOfferCount];
			OfferRequestDTO offerRequestDTO = null;
			for (int nCount = 0; nCount < nOfferCount; nCount++) {
				offerRequestDTO = new OfferRequestDTO();
				offerRequestDTO.setStrOfferCode((powOfferList
						.get(nCount)).getOfferCode());
				offerRequestDTO.setStrCampaignCode(( powOfferList
						.get(nCount)).getCampaignCode());
				offerRequestDTO.setStrPromoCode(( powOfferList
						.get(nCount)).getPromotionCode());
				offerRequestDTOArr[nCount] = offerRequestDTO;
			}
			offerPricingRequest.setOfferRequestDTOs(offerRequestDTOArr);
		}
		logger.debug("OEBO.createOfferPricingRequest() end");
		return offerPricingRequest;
	}

	/**
	 * Method to construct Map of offers from PromoOfferResponse and OESignupVO
	 * 
	 * @param promoOfferResponse
	 * @param oeSignupVO
	 * @return Map<String, Object>
	 */
	private Map<String, Object> constructOffers(
			PromoOfferResponse promoOfferResponse, OESignupVO oeSignupVO) {
		logger.debug("OEBO.constructOffers() start");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<OfferDO> offerDOList = new ArrayList<OfferDO>();
		DecimalFormat decimalformat = new DecimalFormat("#0");
		PromoOfferOutData[] promoOfferOutDataArr=null;
		if (promoOfferResponse == null) {
			resultMap.put(ERR_CODE_KEY, ERR_GET_OFFER);
			resultMap.put(ERROR_TYPE, ERROR_TYPE_CCS);
			resultMap.put(CCS_ERROR, "");
			return resultMap;
		} else {
			logger.debug("promoOfferResponse.getStrErrCode():"
					+ promoOfferResponse.getStrErrCode());
			if (StringUtils.isNotBlank(promoOfferResponse.getStrErrCode())) {
				oeSignupVO.setPromoError("TDSPPROMONOTMATCH");
			}
			
			promoOfferOutDataArr = promoOfferResponse.getOfferOuts();
			if(null==promoOfferOutDataArr){
				resultMap.put("OESIGNUPVO", oeSignupVO);
				resultMap.put(OFFERS_LIST, offerDOList);
				resultMap.put(DISPLAYED_OFFER_CODE_LIST, null);
				resultMap.put(CCS_ERROR, promoOfferResponse.getStrErrCode());
				return resultMap;
			}
			// Fetch all the offers
			String offerCodes = getOfferCodes(promoOfferOutDataArr);
			logger.debug("OEBO.constructOffers() offer codes from CCS:"
					+ offerCodes);
			List<Map<String, Object>> offerCategoryLookupDetailsList = offerService
					.getOfferCategories(offerCodes);
			Map<String, HashMap<String, String>> categorizedOfferMap = filterOfferCodesBasedOnCategory(
					offerCategoryLookupDetailsList, offerCodes);
			logger.debug("OEBO.constructOffers() categorizedOfferMap:"
					+ categorizedOfferMap);
			Map<String, String> relevantCategoryMap = null;
			if (null != categorizedOfferMap) {
				relevantCategoryMap = categorizedOfferMap.get(ALL_OFFERS);
			}

			if ((promoOfferOutDataArr != null)
					&& (promoOfferOutDataArr.length > 0)) {
				logger.debug("OEBO.constructOffers() promoOfferOutDataArr.length:"
						+ promoOfferOutDataArr.length);
				for (PromoOfferOutData promoOfferOutData : promoOfferOutDataArr) {
					List<OfferPriceWraperDO> offerPriceDOList = populatePriceInfo(promoOfferOutData);
					OfferDO offerDO = new OfferDO();
					offerDO.setStrOfferCode(promoOfferOutData.getStrOfferCode());
					if (null != relevantCategoryMap) {
						logger.debug("OEBO.constructOffers() populate offer category");
						offerDO.setStrOfferCategory(relevantCategoryMap
								.get(promoOfferOutData.getStrOfferCode()));
					}
					offerDO.setAvgPriceMap(offerPriceDOList);
					offerDO.setStrCampaignCode(promoOfferOutData
							.getStrCampaignCode());
					offerDO.setStrCampaignDescription(promoOfferOutData
							.getStrCampaignDescription());
					offerDO.setStrCancelFee(decimalformat.format(Double
							.parseDouble(promoOfferOutData.getStrCancelFee()
									.toString())));
					offerDO.setStrContractTerm(decimalformat.format(Double
							.parseDouble(promoOfferOutData.getStrContractTerm())));
					offerDO.setStrCustClass(promoOfferOutData.getStrCustClass());
					offerDO.setStrCustomerSegment(promoOfferOutData
							.getStrCustomerSegment());
					offerDO.setStrDwellingType(promoOfferOutData
							.getStrDwellingType());
					offerDO.setStrEFLSmartCode(promoOfferOutData
							.getStrEFLSmartCode());
					offerDO.setStrEFLDocID(promoOfferOutData.getStrEFLDocID());
					offerDO.setStrIncentiveCode(promoOfferOutData
							.getStrIncentiveCode());
					offerDO.setStrIncentiveDescription(promoOfferOutData
							.getStrIncentiveDescription());
					offerDO.setStrIncentiveValue(promoOfferOutData
							.getStrIncentiveValue().toString());
					offerDO.setStrInvoiceOptions(promoOfferOutData
							.getStrInvoiceOptions());
					offerDO.setStrMarketSegment(promoOfferOutData
							.getStrMarketSegment());
					offerDO.setStrOfferCellTrackCode(promoOfferOutData
							.getStrOfferCellTrackCode());
					offerDO.setStrOfferCodeTitle(promoOfferOutData
							.getStrOfferCodeTitle());
					offerDO.setStrPayOptions(promoOfferOutData
							.getStrPayOptions());
					offerDO.setStrPenaltyDesciption(promoOfferOutData
							.getStrPenaltyDesciption());
					offerDO.setStrPenaltyValue(promoOfferOutData
							.getStrPenaltyValue().toString());
					if (promoOfferOutData.getStrPlanName().indexOf("®") != -1) {
						offerDO.setStrPlanName(promoOfferOutData
								.getStrPlanName().replaceAll("®", "�"));
					} else {
						offerDO.setStrPlanName(promoOfferOutData
								.getStrPlanName());
					}
					offerDO.setStrPlanType(promoOfferOutData.getStrPlanType());
					offerDO.setStrTOSDocID(promoOfferOutData.getStrTOSDocID());
					offerDO.setStrTOSSmartCode(promoOfferOutData
							.getStrTOSSmartCode());
					offerDO.setStrValidFromDate(promoOfferOutData
							.getStrValidFromDate());
					offerDO.setStrValidToDate(promoOfferOutData
							.getStrValidToDate());
					offerDO.setStrYRAACDocID(promoOfferOutData
							.getStrYRAACDocID());
					offerDO.setStrYRAACSmartCode(promoOfferOutData
							.getStrYRAACSmartCode());
					offerDO.setStrPromoCode(promoOfferOutData
							.getStrOfferCellTrackCode());
					offerDO.setStrProductCode(promoOfferOutData
							.getStrProductCode());
					offerDO.setStrProductPriceCode(promoOfferOutData
							.getStrProductPriceCode());
					// setting Environment data
					if (null != promoOfferResponse
							.getCampEnvironmentDetailsOuts()) {
						List<CampEnvironmentDO> offerCampEnvironmentDOList = getOfferSpecificEnvironmentDetails(
								promoOfferResponse
										.getCampEnvironmentDetailsOuts(),
								promoOfferOutData.getStrProductCode());
						if (null != offerCampEnvironmentDOList
								&& offerCampEnvironmentDOList.size() > 0) {
							logger.debug("Setting Environment data in OEBO.constructOffers() offerCampEnvironmentDOList size:"
									+ offerCampEnvironmentDOList.size());
							offerDO.setCampEnvironmentDetails(offerCampEnvironmentDOList
									.toArray(new CampEnvironmentDO[offerCampEnvironmentDOList
											.size()]));
						}
					}
					// setting Segmented flags data
					if (null != promoOfferResponse.getSegmentedFlagsOuts()) {
						List<SegmentedFlagDO> offerSegmentedFlagDOList = getOfferSpecificSegmentedFlags(
								promoOfferResponse.getSegmentedFlagsOuts(),
								promoOfferOutData.getStrOfferCode());
						if (null != offerSegmentedFlagDOList
								&& offerSegmentedFlagDOList.size() > 0) {
							offerDO.setSegmentedFlags(offerSegmentedFlagDOList
									.toArray(new SegmentedFlagDO[offerSegmentedFlagDOList
											.size()]));
						}
					}
					TDSPChargeDO tdspChargesDO = getTDSPChargesDTO(promoOfferOutData.getOfferTDSPCharges());
					offerDO.setTdspChargeDO(tdspChargesDO);
					offerDOList.add(offerDO);
				}
			}

			resultMap.put("OESIGNUPVO", oeSignupVO);
			resultMap.put(OFFERS_LIST, offerDOList);
			resultMap.put(DISPLAYED_OFFER_CODE_LIST, offerCodes);
			resultMap.put(CCS_ERROR, promoOfferResponse.getStrErrCode());

		}

		logger.debug("OEBO.constructOffers() resultMap from constructOffers:"
				+ resultMap);
		logger.debug("OEBO.constructOffers() end");
		return resultMap;
	}
	
	private TDSPChargeDO getTDSPChargesDTO(PromoOfferTDSPCharge[] offerTDSPCharges) {
		TDSPChargeDO tdspChargeDTO = new TDSPChargeDO();		
		String strBundlingTag=EMPTY;
		String strBundlingGroup=EMPTY;
		if(null!=offerTDSPCharges && offerTDSPCharges.length>0)
		{
			for(int nCount=0;nCount<offerTDSPCharges.length;nCount++){
				PromoOfferTDSPCharge promoOfferTDSPChargeEntry = offerTDSPCharges[nCount];

				strBundlingTag=promoOfferTDSPChargeEntry.getStrBundlingTag();
				strBundlingGroup = promoOfferTDSPChargeEntry.getStrBundlingGroup();
				
				if(StringUtils.equalsIgnoreCase(strBundlingTag,strBundlingGroup+"_MO")){
					tdspChargeDTO.setPerMonthValue(promoOfferTDSPChargeEntry.getStrValue());
				}
				
				if(StringUtils.equalsIgnoreCase(strBundlingTag,strBundlingGroup+"_KWH")){
					tdspChargeDTO.setPerKWValue(promoOfferTDSPChargeEntry.getStrValue());
				}
								
			}
			
			logger.info("TDSP perMonth Value"+tdspChargeDTO.getPerMonthValue());
			logger.info("TDSP perKWh Value"+tdspChargeDTO.getPerKWValue());
			
			//logger.info("---------------------------------");

		}
		return tdspChargeDTO;
	}

	/**
	 * Method to get SegmentedFlags for the given offer code
	 * 
	 * @param segmentedFlagsOutsArray
	 * @param strOfferCode
	 * @return List<SegmentedFlagDO>
	 */
	private List<SegmentedFlagDO> getOfferSpecificSegmentedFlags(
			SegmentedFlagsOutData[] segmentedFlagsOutsArray, String strOfferCode) {
		logger.debug("OEBO.getOfferSpecificSegmentedFlags() start");
		List<SegmentedFlagDO> offerSegmentedFlagDOList = new ArrayList<SegmentedFlagDO>();
		if (null != segmentedFlagsOutsArray
				&& segmentedFlagsOutsArray.length > 0 && null != strOfferCode) {
			for (SegmentedFlagsOutData segmentedFlagsOutData : segmentedFlagsOutsArray) {
				if (null != segmentedFlagsOutData
						&& segmentedFlagsOutData.getOfferCode()
								.equalsIgnoreCase(strOfferCode)) {
					SegmentedFlagDO segmentedFlagDO = new SegmentedFlagDO();
					segmentedFlagDO.setSegmentFlag(segmentedFlagsOutData
							.getSegmentFlag());
					offerSegmentedFlagDOList.add(segmentedFlagDO);
				}
			}
		}
		logger.debug("OEBO.getOfferSpecificSegmentedFlags() end");
		return offerSegmentedFlagDOList;
	}

	/**
	 * Method to get Offer Specific Environment Details for the given offer code
	 * 
	 * @param allCampEnvironmentOutDataArray
	 * @param ccsProductCode
	 * @return List<CampEnvironmentDO>
	 */
	private List<CampEnvironmentDO> getOfferSpecificEnvironmentDetails(
			CampEnvironmentOutData[] allCampEnvironmentOutDataArray,
			String ccsProductCode) {
		logger.debug("OEBO.getOfferSpecificEnvironmentDetails() allCampEnvironmentOutDataArray size:"
				+ allCampEnvironmentOutDataArray.length);
		logger.debug("OEBO.getOfferSpecificEnvironmentDetails() ccsProductCode:"
				+ ccsProductCode);
		List<CampEnvironmentDO> offerCampEnvironmentDOList = new ArrayList<CampEnvironmentDO>();
		if (null != allCampEnvironmentOutDataArray
				&& allCampEnvironmentOutDataArray.length > 0
				&& null != ccsProductCode) {
			for (CampEnvironmentOutData campEnvironmentOutData : allCampEnvironmentOutDataArray) {
				if (null != campEnvironmentOutData
						&& campEnvironmentOutData.getCcsProductCd()
								.equalsIgnoreCase(ccsProductCode)) {
					CampEnvironmentDO campEnvironmentDO = new CampEnvironmentDO();
					campEnvironmentDO.setCalcOperand(campEnvironmentOutData
							.getCalcOperand());
					campEnvironmentDO.setProductCode(campEnvironmentOutData
							.getCcsProductCd());
					campEnvironmentDO.setValue(campEnvironmentOutData
							.getValue().toString());
					offerCampEnvironmentDOList.add(campEnvironmentDO);
				}
			}
		}
		return offerCampEnvironmentDOList;
	}

	/**
	 * Method to populate offer pricing details
	 * 
	 * @param matchingCCSOffer
	 * @return List<OfferPriceWraperDO>
	 */
	private List<OfferPriceWraperDO> populatePriceInfo(
			PromoOfferOutData matchingCCSOffer) {
		logger.debug("OEBO.populatePriceInfo() start");
		PromoOfferOutDataAvgPriceMapEntry[] priceArr = matchingCCSOffer
				.getAvgPriceMap();
		List<OfferPriceWraperDO> offerPriceWraperDOList = new ArrayList<OfferPriceWraperDO>();
		for (int j = 0; j < priceArr.length; j++) {
			OfferPriceWraperDO offerPriceWraperDO = new OfferPriceWraperDO();
			OfferPriceDO offerPriceDO = new OfferPriceDO();
			PromoOfferOutDataAvgPriceMapEntry promoOfferOutDataAvgPriceMapEntry = priceArr[j];
			DecimalFormat decimalformat = new DecimalFormat("#0.0");
			DecimalFormat energyChargeDecimalformat = new DecimalFormat("#0.0000");
			DecimalFormat tdspDF = new DecimalFormat("#0.00");			
			if (promoOfferOutDataAvgPriceMapEntry.getValue().getAvgPrice() != null) {

				if (StringUtils.equals(promoOfferOutDataAvgPriceMapEntry
						.getValue().getPriceType(), TDSP_CHRG1)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry
								.getValue().getPriceType(), TDSP_CHRG2)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry
								.getValue().getPriceType(), S_CUSTCHRG)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry
								.getValue().getPriceType(), S_CUSTCHR2)) {

					offerPriceDO.setPrice(tdspDF.format(Double
							.valueOf(promoOfferOutDataAvgPriceMapEntry
									.getValue().getAvgPrice().toString())));
				}
				// Start | Sprint16 -US13873 | Pratyush -- 11/12/2018
				else if (StringUtils.equals(promoOfferOutDataAvgPriceMapEntry.getValue().getPriceType(), S_UNBUNDLE)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry.getValue().getPriceType(), EC)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry.getValue().getPriceType(), S_GME_UNB)
						|| StringUtils.equals(promoOfferOutDataAvgPriceMapEntry.getValue().getPriceType(), S_UNBUNDLE2)) 	
				{
					offerPriceDO.setPrice(energyChargeDecimalformat.format(Double
							.valueOf(promoOfferOutDataAvgPriceMapEntry
									.getValue().getAvgPrice().toString())));
				
				}
				else {

					offerPriceDO.setPrice(decimalformat.format(Double
							.valueOf(promoOfferOutDataAvgPriceMapEntry
									.getValue().getAvgPrice().toString())));
				}
			} else {
				offerPriceDO
					.setPrice(decimalformat.format(Double.valueOf("0")));

				
			}
			offerPriceDO.setStartDate(promoOfferOutDataAvgPriceMapEntry
					.getValue().getDateStart());
			offerPriceDO.setEndDate(promoOfferOutDataAvgPriceMapEntry
					.getValue().getDateEnd());
			offerPriceDO.setSeason(promoOfferOutDataAvgPriceMapEntry.getValue()
					.getSeason());
			offerPriceDO.setPriceType(promoOfferOutDataAvgPriceMapEntry
					.getValue().getPriceType());

			offerPriceWraperDO.setKey(promoOfferOutDataAvgPriceMapEntry
					.getKey());
			offerPriceWraperDO.setValue(offerPriceDO);

			offerPriceWraperDOList.add(offerPriceWraperDO);
		}
		logger.debug("OEBO.populatePriceInfo() end");
		return offerPriceWraperDOList;

	}

	/*
	 * private List<String> getOfferCodes(PromoOfferOutData[] promoOfferOutData)
	 * { List<String> offerCodeList = new ArrayList<String>(); if
	 * ((promoOfferOutData != null) && (promoOfferOutData.length > 0)) { for
	 * (int i = 0; i < promoOfferOutData.length; i++) if (promoOfferOutData[i]
	 * != null) { try {
	 * offerCodeList.add(promoOfferOutData[i].getStrOfferCode()); } catch
	 * (Exception e) { e.printStackTrace(); } } } return offerCodeList; }
	 */

	/**
	 * Method getOfferCodes.
	 * 
	 * @param promoOfferOutData
	 *            PromoOfferOutData[]
	 * @return String
	 */
	private String getOfferCodes(PromoOfferOutData[] promoOfferOutData) {
		ArrayList<String> offerCodeList = new ArrayList<String>();
		String offerCode = null;
		if (promoOfferOutData != null && promoOfferOutData.length > 0) {
			for (int i = 0; i < promoOfferOutData.length; i++) {
				if (promoOfferOutData[i] == null)
					continue;
				if (logger.isDebugEnabled()) {
					logger.debug("OEBO.getOfferCodes() promoOfferOutData[ " + i
							+ " ].getStrOfferCode: "
							+ promoOfferOutData[i].getStrOfferCode());
				}
				try {
					if (offerCodeList.contains(promoOfferOutData[i]
							.getStrOfferCode())) {
						continue;
					}
					offerCodeList.add(promoOfferOutData[i].getStrOfferCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("OEBO.getOfferCodes(): offercodeList"
						+ offerCodeList);
			}
			String[] offerCodeArray = offerCodeList.toArray(new String[1]);
			offerCode = CommonUtil.arrayToString(offerCodeArray,
					DELIMETER_COMMA);
		}
		return offerCode;
	}

	/*
	 * private ProductOfferDO parseProductXML(Document productXMLDocument) {
	 * return ProductOfferParser.parseData(productXMLDocument); }
	 */

	public Map<String, Object> getReactiveOffers(OESignupVO oeSignupVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CharityDetailsVO[] charityDetailsVOArr=null;
		PromoOfferOutData[] promoOfferOutDataArr=null;
		String strCCSError=null;
		try {
			PromoOfferRequest promoOfferRequest = createPromoOfferRequest(oeSignupVO);
			PromoOfferResponse promoOfferResponse = this.offerService.getOfferWithPricingFromCCS(promoOfferRequest);
			strCCSError=promoOfferResponse.getStrErrCode();
			//resultMap.put(CCS_ERROR, promoOfferResponse.getStrErrCode());
			promoOfferOutDataArr = promoOfferResponse.getOfferOuts();
			try{
				charityDetailsVOArr=getCharityDetails(promoOfferResponse.getCharityOuts());
			}catch(Exception e){
				logger.error("Exception in getting Charity Data: ", e);
			}
			
			if(null==promoOfferOutDataArr && null==charityDetailsVOArr){
				logger.debug("Offer Data and Charity Data is blank from first reactive offers call!!!");
				resultMap.put("OESIGNUPVO", oeSignupVO);
				resultMap.put(OFFERS_LIST, null);
				resultMap.put(DISPLAYED_OFFER_CODE_LIST, null);
				return resultMap;
			}else if (null==promoOfferOutDataArr && null!=charityDetailsVOArr && null!=oeSignupVO.getCharityId()){
				logger.debug("Offer Data is blank from first reactive offers call but Charity data is present!!!");
				for(CharityDetailsVO charityDetailsVO: charityDetailsVOArr){
					logger.debug("Got Charity: "+charityDetailsVO.getStrCharityId()+" ~ "+charityDetailsVO.getStrCharityName()+" ~ "+charityDetailsVO.getStrPromoCode());
					if(null!=charityDetailsVO.getStrCharityId() && oeSignupVO.getCharityId().equals(charityDetailsVO.getStrCharityId())
							&& StringUtils.isNotBlank(charityDetailsVO.getStrPromoCode())){
						oeSignupVO.setCharityName(charityDetailsVO.getStrCharityName());
						oeSignupVO.setCharityPromo(charityDetailsVO.getStrPromoCode());
						logger.debug("Re-executing getOfferWithPricingFromCCS call to get offers based on charity id "+charityDetailsVO.getStrCharityId()+" and promo code "+charityDetailsVO.getStrPromoCode());
						promoOfferRequest.setStrcharityId(charityDetailsVO.getStrCharityId());
						promoOfferRequest.setStrPromoCode(charityDetailsVO.getStrPromoCode());
						promoOfferResponse = this.offerService.getOfferWithPricingFromCCS(promoOfferRequest);
					}
				}
			}
			if(null!=promoOfferResponse && null!=promoOfferResponse.getOfferOuts()){
				logger.debug("SUCCESS!!! Offer Data is present. Now constructing offers!!!");
				resultMap = constructOffers(promoOfferResponse, oeSignupVO);
			}else{
				logger.debug("ERROR!!! Offer Data is blank from second reactive offers call!!!");
				resultMap.put("OESIGNUPVO", oeSignupVO);
				resultMap.put(OFFERS_LIST, null);
				resultMap.put(DISPLAYED_OFFER_CODE_LIST, null);
			}
		} catch (Exception e) {
			logger.error("Exception in OEBO.getReactiveOffers():"
					, e);
		}
		if(null!=resultMap && null==resultMap.get(CCS_ERROR) && null!=strCCSError){
			resultMap.put(CCS_ERROR, strCCSError);
		}
		return resultMap;
	}

	/**
	 * Method filterOfferCodesBasedOnCategory.
	 * 
	 * @param offerCategoryLookupDetailsList
	 *            List<Map<String,Object>>
	 * @param allOfferCodes
	 *            String
	 * @return HashMap<String,HashMap<String,String>>
	 */
	private Map<String, HashMap<String, String>> filterOfferCodesBasedOnCategory(
			List<Map<String, Object>> offerCategoryLookupDetailsList,
			String allOfferCodes) {
		String strOfferCategory;
		String strOfferCode;
		String strSaleable;
		Map<String, String> prepayOfferMap = new HashMap<String, String>();
		Map<String, String> postpayOfferMap = new HashMap<String, String>();
		Map<String, String> allOfferMap = new HashMap<String, String>();
		String[] allOfferCodesArr = allOfferCodes.replace(SINGLE_QUOTE, EMPTY)
				.split(DELIMETER_COMMA);
		List<String> allOfferCodesList = new ArrayList<String>();
		for (String offerCode : allOfferCodesArr) {
			allOfferCodesList.add(offerCode);
		}
		Map<String, HashMap<String, String>> categorizedOffersMap = new HashMap<String, HashMap<String, String>>();

		for (Map<String, Object> offerMap : offerCategoryLookupDetailsList) {
			strOfferCategory = (String) offerMap.get(OE_OFFER_CATEGORY);
			strOfferCode = (String) offerMap.get(OFFER_CODE);
			strSaleable = (String) offerMap.get(SALEABLE);

			if (StringUtils.isBlank(strOfferCategory)) {
				strOfferCategory = EMPTY;
			}
			if (null != strOfferCategory
					&& strOfferCategory.equals(OFFER_CATEGORY_PREPAY)) {
				if (null != strSaleable && FLAG_Y.equals(strSaleable)) {
					prepayOfferMap.put(strOfferCode, strOfferCategory);
				} else {
					continue;
				}
			} else {
				postpayOfferMap.put(strOfferCode, strOfferCategory);
			}
			allOfferCodesList.remove(strOfferCode);
			allOfferMap.put(strOfferCode, strOfferCategory);
		}
		for (String offerCode : allOfferCodesList) {
			postpayOfferMap.put(offerCode, EMPTY);
			allOfferMap.put(offerCode, EMPTY);
		}
		categorizedOffersMap.put(PREPAY_OFFERS,
				(HashMap<String, String>) prepayOfferMap);
		categorizedOffersMap.put(POSTPAY_OFFERS,
				(HashMap<String, String>) postpayOfferMap);
		categorizedOffersMap.put(ALL_OFFERS,
				(HashMap<String, String>) allOfferMap);
		return categorizedOffersMap;
	}

	/**
	 * Method to get TDSP details based on complete address or zip code
	 * @param companyCode
	 * @param brandId
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servZipCode
	 * @param sessionId
	 * @return
	 */
	public TdspResponse getTDSPDetails(String companyCode, String brandId,
			String servStreetNum, String servStreetName,
			String servStreetAptNum, String servZipCode, String sessionId) {
		TdspResponse tdspResponse = new TdspResponse();
		tdspResponse.setCompanyCode(companyCode);
		tdspResponse.setBrandId(brandId);
		OESignupVO oeSignupVO = new OESignupVO();
		oeSignupVO.setCompanyCode(companyCode);
		String tdspCodeCCS=null;
		List<TDSPDO> tdspDOList = new ArrayList<TDSPDO>();
		if (StringUtils.isBlank(servZipCode)) {
			logger.debug("OEBO.getTDSPDetails() Zip code is mandatory.");
			tdspResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			tdspResponse.setResultDescription(ZIPCODE_MANDATORY);
			return tdspResponse;
		}
		AddressDO serviceAddressDO = new AddressDO();
		serviceAddressDO.setStrStreetNum(servStreetNum);
		serviceAddressDO.setStrStreetName(servStreetName);
		serviceAddressDO.setStrApartNum(servStreetAptNum);
		serviceAddressDO.setStrZip(servZipCode);
		List<Map<String, Object>> cityStateList = null;
		try {
			cityStateList = this.addressService
					.getCityStateFromZip(servZipCode);

			for (Map<String, Object> cityStateMap : cityStateList) {
				serviceAddressDO.setStrCity((String) cityStateMap
						.get(CITY));
				serviceAddressDO.setStrState((String) cityStateMap
						.get(STATE));
				logger.debug("OEBO.getOffers() City From ZIP: "
						+ serviceAddressDO.getStrCity());
				logger.debug("OEBO.getOffers() State From ZIP: "
						+ serviceAddressDO.getStrState());
			}
		} catch (Exception e) {
			logger.error("OEBO.getOffers() Error with call addressService.getCityStateFromZip: "
					, e);
		}
		oeSignupVO.setServiceAddressDO(serviceAddressDO);
		if (StringUtils.isNotBlank(servStreetNum)
				|| StringUtils.isNotBlank(servStreetName)) {
			try {
				logger.debug("OEBO.getTDSPDetails()  street address entered! getting ESID info");
				oeSignupVO = getESIDInformation(oeSignupVO, companyCode,
						sessionId);
				if(null!=oeSignupVO.getServiceAddressDO()){
					tdspResponse.setServiceAddress(populateServiceAddressDO(oeSignupVO.getServiceAddressDO()));
				}
				if (StringUtils.isNotBlank(oeSignupVO.getEsidNumber())) {
					String strESIDNumber = oeSignupVO.getEsidNumber();
					tdspResponse.setEsid(strESIDNumber);
					logger.debug("OEBO.getTDSPDetails() Getting TDSP Code for ESID Number="
							+ oeSignupVO.getEsidNumber());
					TdspByESIDResponse tdspByESIDResponse = this.tosService
							.ccsGetTDSPFromESID(strESIDNumber,
									oeSignupVO.getCompanyCode(), sessionId);
					if ((tdspByESIDResponse != null)
							&& (StringUtils.isNotBlank(tdspByESIDResponse
									.getServiceId()))) {
						TDSPDO tdspDO = new TDSPDO();
						tdspCodeCCS = tdspByESIDResponse.getServiceId()
								.trim();
						tdspDO.setTdspCodeCCS(tdspCodeCCS);
						tdspDO.setTdspCodeWeb(this.appConstMessageSource
								.getMessage("ccs.tdsp.web.equivalent."
										+ tdspCodeCCS, null, null));
						tdspDO.setTdspName(this.appConstMessageSource
								.getMessage(tdspCodeCCS, null, null));

						tdspDOList.add(tdspDO);
						tdspResponse.setTdspData(tdspDOList);
						logger.debug("OEBO.getTDSPDetails() Got TDSP Info from ESID."
								+ tdspDO.getTdspCodeCCS()
								+ "~"
								+ tdspDO.getTdspCodeWeb()
								+ "~"
								+ tdspDO.getTdspName());
					} else {
						logger.debug("OEBO.getTDSPDetails() Failed in getting TDSP Code for ESID Number="
								+ oeSignupVO.getEsidNumber());
					}
				} else {
					logger.debug("OEBO.getTDSPDetails() getESIDInformation FAILED. Nevermind! Continuing with process... ");
				}
			} catch (Exception e) {
				logger.error("OEBO.getTDSPDetails() getESIDInformation FAILED. Nevermind! Continuing with process... "
						, e);
			}
		}

		if (StringUtils.isBlank(tdspCodeCCS)) {
	        logger.debug("OEBO.getTDSPDetails() Failed in getting TDSP Code for ESID! Now getting from the DB call");
	        TdspDetailsResponse tdspDetailsResponse = this.addressService.getTDSP(oeSignupVO.getServiceAddressDO(), oeSignupVO.getCompanyCode());
	        if ((tdspDetailsResponse != null) && (StringUtils.isBlank(tdspDetailsResponse.getStrErrCode()))) {
	          if (tdspDetailsResponse.isMultiTdsp()) {
	            logger.debug("OEBO.getTDSPDetails() Got Multi TDSP codes from DB:" + tdspDetailsResponse.getStrTdspCodes());
	            TdspDetailsResponseStrTdspCodesEntry[] strTdspCodesMap = tdspDetailsResponse.getStrTdspCodes();
	            for (TdspDetailsResponseStrTdspCodesEntry tdspDetailsResponseStrTdspCodesEntry : strTdspCodesMap) {
	              try {           	  	
		                String ccsTDSPCode = this.appConstMessageSource.getMessage(tdspDetailsResponseStrTdspCodesEntry.getKey(), null, null);
		                logger.debug("OEBO.getTDSPDetails() - Resolved ccsTDSPCode:"+ ccsTDSPCode);
		                TDSPDO tdspDO = new TDSPDO();
		                tdspDO.setTdspCodeCCS(ccsTDSPCode);
		                tdspDO.setTdspCodeWeb(tdspDetailsResponseStrTdspCodesEntry.getKey());
		                tdspDO.setTdspName(this.appConstMessageSource
								.getMessage(ccsTDSPCode, null, null));
		                tdspDOList.add(tdspDO);
	              	}catch (Exception localException) {
	              		logger.error("inside getTDSPDetails :: exception occured ", localException);
	            	  logger.debug("OEBO.getTDSPDetails() - Non Supported TDSPCode:"+ tdspDetailsResponseStrTdspCodesEntry.getKey());
	            	 // do nothing try your luck with another TDSP code 
	              }
	            }
	            tdspResponse.setTdspData(tdspDOList);
	          } else {
		            logger.debug("OEBO.getTDSPDetails() Single TDSP code:" + tdspDetailsResponse.getStrTdsp());
		            TDSPDO tdspDO = new TDSPDO();
		            tdspDO.setTdspCodeCCS(this.appConstMessageSource.getMessage(tdspDetailsResponse.getStrTdsp(), null, null));
		            tdspDO.setTdspCodeWeb(tdspDetailsResponse.getStrTdsp());
		            tdspDO.setTdspName(this.appConstMessageSource
							.getMessage(tdspDO.getTdspCodeCCS(), null, null));
					tdspDOList.add(tdspDO);
					tdspResponse.setTdspData(tdspDOList);
	          }
	        } else {
		          logger.debug("OEBO.getTDSPDetails() addressService.getTDSP() call results an Error: " + tdspDetailsResponse.getStrErrMessage());
		          tdspResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		          tdspResponse.setResultDescription("Error while getting TDSP code from Database");
	        }
	    }
		return tdspResponse;
	}
	
	/*
	 * Added method getCharityDetails to get CharityDetailsVO object : KB :16-Nov-2012
	 */
	public CharityDetailsVO[] getCharityDetails(PromoCharityOutData[] promoCharityOutData) {
		CharityDetailsVO[] charityDetailsVO = null;
		//ENTCR 13315 APPCR_104998 Promo Code Start by Thabitha Sethurman
		int voCount=0; 
		 List<CharityDetailsVO> removedCharityDetailsVO = new ArrayList<CharityDetailsVO>();
		//ENTCR 13315 APPCR_104998 Promo Code End by Thabitha Sethurman
		 try{
			 
			if(promoCharityOutData!=null && promoCharityOutData.length>0){
				logger.debug("promoCharityOutData.length ::"+promoCharityOutData.length);
				charityDetailsVO = new CharityDetailsVO[promoCharityOutData.length]; 
				 for(int i=0;i<promoCharityOutData.length;i++){
					 logger.debug(" Outside IF promoCharityOutData["+i+"]: "+promoCharityOutData[i]);
					 if(promoCharityOutData[i]==null ) {
						 logger.debug("promoCharityOutData["+i+"]: "+promoCharityOutData[i]);
						 continue;
					 }						 
					 logger.debug("promoCharityOutData["+i+"].getZcharityId:: "+promoCharityOutData[i].getZcharityId());
					 logger.debug("promoCharityOutData["+i+"].getZzChar1:: "+promoCharityOutData[i].getZzChar1());
					//ENTCR 13315 APPCR_104998 Promo Code - Start by Thabitha Sethurman
					 logger.debug("promoCharityOutData["+i+"].getZdefault():: "+promoCharityOutData[i].getZdefault());
					 logger.debug("promoCharityOutData["+i+"].getZpromoCd():: "+promoCharityOutData[i].getZpromoCd());
					 if(promoCharityOutData[i].getZdefault()!=null && promoCharityOutData[i].getZpromoCd()!=null) {
					 	if((promoCharityOutData[i].getZdefault().equals("X")) && !(promoCharityOutData[i].getZpromoCd().startsWith("X"))) {						
						 charityDetailsVO[voCount] = new CharityDetailsVO();						 
						
						 logger.debug("Before setting charity id and name");
						 charityDetailsVO[voCount].setStrCharityId(promoCharityOutData[i].getZcharityId());
						 logger.debug("charityDetailsVO["+voCount+"] Chairty Id ::"+charityDetailsVO[voCount].getStrCharityId());
						 charityDetailsVO[voCount].setStrCharityName(promoCharityOutData[i].getZzChar1());
						 if(promoCharityOutData[i].getZzChar1()!=null){							 
							 if(promoCharityOutData[i].getZzChar1().isEmpty()){								 
								 charityDetailsVO[voCount].setStrCharityName(promoCharityOutData[i].getZcharityId());
							 }							 
						 }else{							 
							 charityDetailsVO[voCount].setStrCharityName(promoCharityOutData[i].getZcharityId()); 
						}
						 
						 logger.debug("charityDetailsVO["+voCount+"] Chairty Name ::"+charityDetailsVO[voCount].getStrCharityName());	
						 
						 charityDetailsVO[voCount].setStrPromoCode(promoCharityOutData[i].getZpromoCd());
						 logger.debug("charityDetailsVO["+voCount+"] Chairty PromoCode ::"+charityDetailsVO[voCount].getStrPromoCode());
						 voCount++;
					 	} 
					 }
					
				 }
				 
				 if(charityDetailsVO.length>0) {
					 for(int i=0;i<charityDetailsVO.length;i++){
						// logger.info("charityDetailsVO["+i+"]"+charityDetailsVO[i]);
						      if (charityDetailsVO[i] != null)
						    	  removedCharityDetailsVO.add(charityDetailsVO[i]);						   					 
					 }
				 }				 	
			}
			//ENTCR 13315 APPCR_104998 Promo Code - End by Thabitha Sethurman
			if(null != charityDetailsVO)
				logger.debug("charityDetailsVO length:: "+charityDetailsVO.length);
			else
				logger.debug("charityDetailsVO is null:");
			
		 }catch (Exception e) {
			 logger.error("Exception in get Charity Details ::", e);
			e.printStackTrace();
		}
		 
		return removedCharityDetailsVO.toArray(new CharityDetailsVO[voCount]);	//ENTCR 13315 APPCR_104998 Promo Code by Thabitha Sethurman
	}
	

	/**
	 * 
	 * 
	 * @param oeSignupVO
	 * @return
	 * @throws OEException 
	 * @throws ServiceException
	 */
	@SuppressWarnings("rawtypes")
	public CheckPendingServiceResponse checkPendingRequest(CheckPendingServiceRequest pendingRequestCheckDTO) throws OEException {

		java.sql.Date transDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String locale = "en";
		String dtStartDate = "";
		String previousProviderName = null;
		CheckPendingServiceResponse finalPendingServiceResponse = null;
		if (pendingRequestCheckDTO.getLocaleLanguageCode().equalsIgnoreCase(ES_US))
			locale = "es";
		else
			locale = "en";
		try {
			List<CheckPendingServiceResponse> pendingRequestDTOList = addressDAO
					.checkPendingRequest(pendingRequestCheckDTO);
			if (null != pendingRequestDTOList
					&& pendingRequestDTOList.size() > 0) {
				for (CheckPendingServiceResponse pendingServiceRequestDTO : pendingRequestDTOList) {
					if (null != pendingServiceRequestDTO
							&& StringUtils.isNotBlank(pendingServiceRequestDTO
									.getTrackNo())) {
						String repCode = null;
						java.sql.Date dtPrevReqStartDate = null;
						List<Map<String, Object>> pendingRequestDetailsList = serviceLocationDAO
								.getPendingRequestDetails(pendingServiceRequestDTO
										.getTrackNo());
						try {
							for (Map pendingRequestMap : pendingRequestDetailsList) {
								repCode = (String) pendingRequestMap
										.get(REP_CD);
								logger.debug("checkPendingRequest - repCode :: "
										+ repCode);
								logger.debug("checkPendingRequest - SERVICE_START_DATE :: "
										+ pendingRequestMap
												.get(SERVICE_START_DATE));
								if (null != pendingRequestMap
										.get(SERVICE_START_DATE))
									dtPrevReqStartDate = new java.sql.Date(
											((java.sql.Timestamp) pendingRequestMap
													.get(SERVICE_START_DATE))
													.getTime());
								break;
							}
							if (StringUtils.isNotBlank(repCode)) {
								try {
									List<Map<String, Object>> previousProviderNameList = serviceLocationDAO
											.getPreviousProviderNameFromCode(repCode);
									for (Map previousProviderNameMap : previousProviderNameList) {
										previousProviderName = (String) previousProviderNameMap
												.get(DESCRIPTION);
									}
								} catch (Exception ex) {
									logger.error(
											"checkPendingRequest: Error in getting Previous providername::",
											ex);
									throw new ServiceException();
								}
							}
							if (null != dtPrevReqStartDate
									&& !dtPrevReqStartDate.equals(""))
								dtStartDate = sdf.format(dtPrevReqStartDate);
							pendingServiceRequestDTO
									.setPreviousProviderName(previousProviderName);
							pendingServiceRequestDTO
									.setServiceStartDate(dtStartDate);
						} catch (Exception ex1) {
							logger.error(
									"checkPendingRequest: Error in getting service start date",
									ex1);
							throw new ServiceException();
						}
						try {
							transDate = new java.sql.Date(
									pendingServiceRequestDTO.getCreationDate()
											.getTime());
						} catch (Exception ex2) {
							logger.error(" OEBO:checkPendingRequest():", ex2);
							transDate = new java.sql.Date(
									System.currentTimeMillis());
						}
						pendingServiceRequestDTO
								.setTransactionDate(CommonUtil
										.getFormattedTransactionDate(transDate,
												locale));

						logger.debug("pendingServiceRequestDTO :: "
								+ pendingServiceRequestDTO);
						finalPendingServiceResponse = pendingServiceRequestDTO;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error occured in OEBO:::", e);
			throw new OEException();

		}

		return finalPendingServiceResponse;
	}

	/**
	 * @param request
	 * @return TokenizedResponse
	 */
	public TokenizedResponse getTokenResponse(TokenRequestVO request) {

		logger.debug("getTokenResponse :::::::: Start");

		TokenizedResponse tokenizedResponse = new TokenizedResponse();

		try {

			String returnToken = "";
			
			if (StringUtils.isBlank(request.getActionCode())
					|| (!request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())
					    && !request.getActionCode().equalsIgnoreCase(Token.getSsnAction())))
			{
				tokenizedResponse.setResultCode(RESULT_CODE_SUCCESS);
				tokenizedResponse.setResultDescription(ACTION_CODE_INVALID);
				tokenizedResponse.setReturnToken(returnToken);
				tokenizedResponse.setErrorCode(HTTP_BAD_REQUEST);
				tokenizedResponse.setHttpStatus(Response.Status.BAD_REQUEST);
				logger.debug("getTokenResponse :::::::: Ends with invalid action code");
				return tokenizedResponse;
			}
			
			if (request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())) {
				returnToken = Token.getToken(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())) {
				returnToken = Token.getBankAccountToken(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())) {
				returnToken = Token.getDRLToken(request.getNumToBeTokenized());	
				tokenizedResponse.setReturnToken(returnToken);
			} else if (request.getActionCode().equalsIgnoreCase(Token.getSsnAction())) {
				returnToken = Token.getSSNToken(request.getNumToBeTokenized());
				tokenizedResponse.setReturnToken(returnToken);
			}
			
		} catch (Exception e) {
			logger.error(e);
			tokenizedResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			tokenizedResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			tokenizedResponse.setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR);
			//throw new OAMException(200, e.getMessage(), tokenizedResponse);
		}

		logger.debug("getTokenResponse :::::::: End");

		return tokenizedResponse;
	}
	
	/**
	 * @author jyogapa1
	 * 
	 * @param permitCheckRequest
	 * @param sessionId
	 * @return
	 * @throws OEException
	 */
	public CheckPermitResponse checkPermitRequirement(
			CheckPermitRequest permitCheckRequestDTO, String sessionId)
			throws OEException {
		String METHOD_NAME = "OEBO: checkPermitRequirement(..)";

		logger.debug("Start:" + METHOD_NAME);

		CheckPermitResponse response = new CheckPermitResponse();
		try {
			// Create permit check request of NRGWS domain
			PermitCheckRequest permitCheckRequest = oeRequestHandler.createPermitCheckRequest(permitCheckRequestDTO);

			// Get permit check response from NRGWS OEDomain via [OE proxy layer]
			PermitCheckResponse permitCheckResponse = oeProxy
					.checkPermitRequirment(permitCheckRequest, sessionId);

			// Handle NRGWS permitCheckResponse and parse it as NRGREST response.
			this.handlePermitCheckResponse(response, permitCheckResponse);
		} catch (RemoteException e) {
			logger.error(e);
			handleServiceException(response, METHOD_NAME, e);

		} catch (Exception e) {
			logger.error("ERROR:" + METHOD_NAME, e);
			handleServiceException(response, METHOD_NAME, e);
		}

		logger.debug("END:" + METHOD_NAME);

		return response;
	}
	
	/**
	 * Executes all the listed calls for submitting enrollment request, and
	 * returns enrollment response.
	 * 
	 * <p>
	 * List of sequence of calls made in this submit enrollment.
	 * <ul>
	 * <li><strong>Call 1<strong>: Submit online enrollment to CCS. (NRGWS)
	 * <li><strong>Call 2<strong>: DepositPayment TODO for Phase II
	 * <li><strong>Call 3<strong>: Submit auto pay TODO for Phase II
	 * <li><strong>Call 4<strong>: Activate EBill. (NRGWS)
	 * <li><strong>Call 5<strong>: Update contact information. (NRGWS)
	 * <li><strong>Call 6<strong>: Send confirmation Email TODO for Phase II
	 * <li><strong>Call 7<strong>: Get Person ID. (CHOICE DB)
	 * <li><strong>Call 8<strong>: Update service location. (CHOICE DB)
	 * <li><strong>Call 9<strong>: Update person details. (CHOICE DB)
	 * </ul>
	 * </p>
	 * 
	 * @param enrollmentRequest
	 *            instance of <code>EnrollmentRequest</code>.
	 * 
	 * @return EnrollmentResponse the enrollment response that contains result.
	 * 
	 * @throws OEException
	 *             if the enrollment call contains any error or failed.
	 * 
	 * @author Jenith (jyogapa1)
	 */
	public EnrollmentResponse submitEnrollment(EnrollmentRequest enrollmentRequest)
			throws OEException {
		String METHOD_NAME = "OEBO: submitEnrollment(..)";
		logger.debug("Start:" + METHOD_NAME);
		
		EnrollmentResponse response =  new EnrollmentResponse();
		response.setTrackingId(enrollmentRequest.getTrackingId());
		OESignupDTO oeSignUpDTO = null;
		
		if(StringUtils.isBlank(enrollmentRequest.getPromoCode()))
		{  //If Promo code is passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("promoCode may not be Empty");
			return response;	
		}
		
		try {
			// Create SignupDTO from the enrollment API request.
			oeSignUpDTO = oeRequestHandler.createOeSignupDtoByMinimal(enrollmentRequest);
			logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME);
			
			// Do the input normalization/sanitization
			this.initNormalization(oeSignUpDTO);
			logger.info("oeSignUpDTO : "+oeSignUpDTO);
			if (allowSubmitEnrollment(oeSignUpDTO, response)) {
	
				// Populate all Pre-requisite input for enrollment
				this.initPrerequisites(oeSignUpDTO);
				// 1. Call online enrollment submission to CCS.
				this.submitOnlineEnrollment(oeSignUpDTO);
			

				// TODO 2. Out of scope of Phase I. Leave as TBD in code
				// depositPayment();

				// TODO 3. Out of scope of Phase I. Leave as TBD in code
				// submitAutoPay();

				if (oeSignUpDTO.isEnrolled()) {

					// 4. Call activate EBill.
					this.activateEbill(oeSignUpDTO);

					// 5. Call update contact information.
					this.updateContact(oeSignUpDTO);

					// TODO 6. - Out of scope of Phase I. Leave as TBD in code
					// sendConfirmationEmail();
					if (StringUtils.equalsIgnoreCase(Constants.DSI_AGENT_ID,oeSignUpDTO.getAffiliateId())) {
						
						if((StringUtils.isNotBlank(oeSignUpDTO.getContractAccountNum()))
								&& (StringUtils.isNotBlank(oeSignUpDTO.getBusinessPartnerID()))) {
							UpdateCRMAgentInfoResponse updateResponse = oeService.updateCRMAgentInfo(oeSignUpDTO);
							logger.info(oeSignUpDTO.printOETrackingID()+" Agent CRM Update Status for CA : "+oeSignUpDTO.getContractAccountNum()+" Response Code : "+updateResponse.getResponseCode()+ " Msg : "+updateResponse.getResponseMsg());
							if(StringUtils.equalsIgnoreCase(updateResponse.getResponseCode(), S_VALUE)){
								oeSignUpDTO.setCcsAgentUpdateStatus(UPDATE_AGENT_SUCCESS_FLAG);
							} else{
								oeSignUpDTO.setCcsAgentUpdateStatus(UPDATE_AGENT_ERROR_FLAG);
							}
						} else {
							logger.info(oeSignUpDTO.printOETrackingID() + " Agent  :"
									+ oeSignUpDTO.getAgentID()
									+ " is not updated in CRM because CA :" + oeSignUpDTO.getContractAccountNum()
									+ " BPNumber :" + oeSignUpDTO.getBusinessPartnerID());
						}
				} else {
					logger.debug(oeSignUpDTO.printOETrackingID()+" There is no agent information to Update ");
				}
					
				}
			}
			
			// Update errorCode, reqStatusCD
			this.updateEnrollmentStatus(oeSignUpDTO);
			//Send date to TLP
			//START : OE :Sprint62 :US21019 :Kdeshmu1
			if(!StringUtils.equalsIgnoreCase(I_VALUE,oeSignUpDTO.getReqStatusCd()) && 
					StringUtils.equalsIgnoreCase(Constants.DSI_AGENT_ID,oeSignUpDTO.getAffiliateId()))
			{
				String tlpReportApiStatus = sendReliantEnrollmentDataToTLP (oeSignUpDTO);
				oeSignUpDTO.setTlpReportApiStatus(tlpReportApiStatus);
			}
			//END : OE :Sprint62 :US21019 :Kdeshmu1
			
		} catch (RemoteException e) {
			logger.error(e);
			this.handleSubmitEnrollmentError(oeSignUpDTO, e);
			handleServiceException(response, METHOD_NAME, e);

		} catch (Exception e) {
			logger.error(e);
			this.handleSubmitEnrollmentError(oeSignUpDTO, e);
		} 
		/**
		 * The Below given finally always runs in all scenarios and in case
		 * of exception also will execute the calls of below.
		 * 
		 */
		finally {
			// Calls 7, 8 and 9 are executed here.
			// Save Person and Location details in database.
			this.updatePersonAndServiceLocation(oeSignUpDTO);
		}
		
		// populate enrollment response for output.
		this.setEnrollmentResponse(response, oeSignUpDTO);
		
		logger.debug("END:" + METHOD_NAME);
				
		return response;
	}

	/**
	 * @param creditScoreRequest
	 * @param creditCheckRequest
	 * @return NewCreditScoreResponse
	 * @throws OAMException
	 */
	public NewCreditScoreResponse performCreditCheck(
			NewCreditScoreRequest creditScoreRequest, 
			CreditCheckRequest creditCheckRequest) throws OAMException {

		String affiliateId = creditCheckRequest.getAffiliateId();
		String locale = creditCheckRequest.getLanguageCode();
		/*string companyCode = creditCheckRequest.getCompanyCode();*/
		

		/* author Mayank Mishra */
		String METHOD_NAME = "OEBO: performCreditCheck(..)";

		logger.debug("Start:" + METHOD_NAME);

		Locale localeObj = null;
		StringBuilder creditFactor = new StringBuilder(EMPTY);

		if (locale.equalsIgnoreCase(S))
			localeObj = new Locale("es", "US");
		else
			localeObj = new Locale("en", "US");

		NewCreditScoreResponse response = new NewCreditScoreResponse();

		com.multibrand.domain.NewCreditScoreResponse newCreditScoreResponse = null;
		try {
			// getNewCreditScore from NRGWS OEDomain via [OE proxy layer]
			newCreditScoreResponse = oeProxy
					.getNewCreditScore(creditScoreRequest);
			
			StringBuilder creditFactorsText = new StringBuilder(EMPTY);

			if (newCreditScoreResponse.getArrayFactors() != null
					&& newCreditScoreResponse.getArrayFactors().length > 0) {
				FactorDetailDO[] factorsArray = newCreditScoreResponse
						.getArrayFactors();

				for (FactorDetailDO factObj : factorsArray) {
					if (!locale.equalsIgnoreCase(factObj.getLanguage()))
						continue;
					creditFactor.append(factObj.getKey_FACTOR() + SEMI_COLON);
					String key = factObj.getSource() + DOT + factObj.getType()
							+ DOT + factObj.getKey_FACTOR();
					creditFactorsText.append(oweRPMFactors.getMessage(key,
							null, localeObj) + SEMI_COLON);
				}
			}

			if(newCreditScoreResponse.getStrDepositAmt() != null && (Math
					.round(newCreditScoreResponse.getStrDepositAmt()
							.floatValue())>0)) {
				response.setCreditFactorsText(creditFactorsText.toString().split(
					String.valueOf(SEMI_COLON)));
			}
			if(newCreditScoreResponse.getStrDepositAmt() != null) {
				response.setDepositAmount(String.valueOf((Math
					.round(newCreditScoreResponse.getStrDepositAmt()
							.floatValue()))));
			}
			

	 	
/*Setting the CreditAgency info From zestNotifyHold*/	
			 CompanyMsgText.CREDIT_AGENCY_ENUM creditAgencyEnum=null;
			if((newCreditScoreResponse.getZesSecrtyNotifHold() != null)&&(newCreditScoreResponse.getZesSecrtyNotifHold().length > 0 )&& (newCreditScoreResponse.getZesSecrtyNotifHold()[0].getCrAgency()!=null)){
				response.setCreditAgency(newCreditScoreResponse.getZesSecrtyNotifHold()[0].getCrAgency());
				 creditAgencyEnum = CompanyMsgText.CREDIT_AGENCY_ENUM.valueOf(newCreditScoreResponse.getZesSecrtyNotifHold()[0].getCrAgency());
			}
			else{
				response.setCreditAgency(EMPTY);
			}
			
			
/*Setting the MscCode  to Freeze or Fraud and Status code to Stop or Continue */
			String zesNotifyHold  =  null;
					  if (newCreditScoreResponse.getZesSecrtyNotifHold() !=null) {

	                        if(newCreditScoreResponse.getZesSecrtyNotifHold().length > 0 && newCreditScoreResponse.getZesSecrtyNotifHold()[0] != null 

	                                        && newCreditScoreResponse.getZesSecrtyNotifHold()[0].getAlertCode() !=null) {

	                                zesNotifyHold  = newCreditScoreResponse.getZesSecrtyNotifHold()[0].getAlertCode();
	                        }
	                }
						CompanyMsgText.COMPANY_CODE_ENUM companyCodeEnum = null;
						if(StringUtils.isNotEmpty (creditCheckRequest.getBrandId()) && (creditCheckRequest.getBrandId().equals("CE"))){
							companyCodeEnum = CompanyMsgText.COMPANY_CODE_ENUM.valueOf("CC"+creditCheckRequest.getCompanyCode()+"_"+creditCheckRequest.getBrandId()); 
						}else{
							companyCodeEnum = CompanyMsgText.COMPANY_CODE_ENUM.valueOf("CC"+creditCheckRequest.getCompanyCode());
						}	
						
			if ((StringUtils.isNotEmpty(zesNotifyHold) && FREEZE_CREDIT_CHECK_ZES_SEC_NOTI_HOLD_ALERT_CODE.contains(zesNotifyHold))){
					response.setMessageCode(MESSAGE_CREDIT_FREEZE);
					response.setStatusCode(STATUS_CODE_STOP);
					response.setMessageText(msgSource.getMessage(TEXT_FREEZE_CREDIT_CHECK, 
							new String[] {creditAgencyEnum.getName(),creditAgencyEnum.getPhoneNumber(),companyCodeEnum.getMultiCompanyEmail(),companyCodeEnum.getMultiCompanyPhoneNumber()},
							CommonUtil.localeCode(creditCheckRequest.getLanguageCode()) ));		
			} 
			
			else if(StringUtils.isNotEmpty(zesNotifyHold)&& FRAUD_OR_MILITARY_CREDIT_CHECK_ZES_SEC_NOTI_HOLD_ALERT_CODE.contains(zesNotifyHold)){  
						response.setMessageCode(MESSAGE_CREDIT_FRAUD);
						response.setStatusCode(STATUS_CODE_STOP);	
						response.setMessageText(msgSource.getMessage(TEXT_FRAUD_CREDIT_CHECK, 
								new String[] {creditAgencyEnum.getName(),creditAgencyEnum.getPhoneNumber(),companyCodeEnum.getMultiCompanyEmail(),companyCodeEnum.getMultiCompanyPhoneNumber()},
								CommonUtil.localeCode(creditCheckRequest.getLanguageCode()) ));	
			}
			else{
				response.setStatusCode(STATUS_CODE_CONTINUE);
			}
			
			if (StringUtils.isNotEmpty(creditCheckRequest.getMviDate())
					&& StringUtils.isNotEmpty(newCreditScoreResponse
							.getStrDepositReason())) {
				if(newCreditScoreResponse.getStrDepositAmt() != null && (Math
					.round(newCreditScoreResponse.getStrDepositAmt()
							.floatValue())>0)) {
					Object[] inParams = { creditScoreRequest.getStrMoveinDate(),
							newCreditScoreResponse.getStrDueDate() };
					response.setDepositDueText(msgSource.getMessage(
							newCreditScoreResponse.getStrDepositReason(), inParams,
							localeObj));
				}
			} else if (StringUtils.isEmpty(creditCheckRequest.getMviDate())) {
				response.setDepositDueText(msgSource
						.getMessage(MESSAGE_CODE_CREDIT_CHECK_EMPTY_MVI_DATE));
			} else {
				response.setDepositDueText(EMPTY);
			}
			
		/*Setting DepositDueText Empty if we have Credit Freeze or Fraud*/	

			if(StringUtils.equals (response.getMessageCode(),MESSAGE_CREDIT_FREEZE)  || StringUtils.equals (response.getMessageCode(),MESSAGE_CREDIT_FRAUD)){
				response.setDepositDueText(EMPTY);
			}
			

			StringBuilder stringBuilder = new StringBuilder();
			String infoKey = EMPTY;
			String infoName = EMPTY;
			DateFormat df = new SimpleDateFormat(MM_dd_yyyy);
			Calendar c = Calendar.getInstance();
 
			String creditScoreHigh = EMPTY;
			String creditScoreLow = EMPTY;

			if (StringUtils.isNotBlank(newCreditScoreResponse
					.getStrCreditScoreHigh()))
				creditScoreHigh = newCreditScoreResponse
						.getStrCreditScoreHigh();
			else
				creditScoreHigh = NINENINETYNINE;

			if (StringUtils.isNotBlank(newCreditScoreResponse
					.getStrCreditScoreLow()))
				creditScoreLow = newCreditScoreResponse.getStrCreditScoreLow();
			else
				creditScoreLow = ONE;
			
				if(StringUtils.isNotBlank(newCreditScoreResponse.getStrCreditSource())){
					if (newCreditScoreResponse.getStrCreditSource()
							.equalsIgnoreCase(EQ)){
						infoKey = EQ_INFO;
						infoName = EQ_NAME;
					}
					else if(newCreditScoreResponse.getStrCreditSource()
							.equalsIgnoreCase(TU)){
						infoKey = TU_INFO;
						infoName = TU_NAME;
					}
					
					String companyCodeName = CommonUtil.getCompanyName(
							creditCheckRequest.getBrandId(),
							creditCheckRequest.getCompanyCode()
							);
					if (StringUtils.isNotBlank(newCreditScoreResponse.getStrCreditScore())	&& newCreditScoreResponse.getStrCreditScore()
									.equalsIgnoreCase(CREDIT_ZERO)) { // Zero credit
																		// scenario
						stringBuilder.append("ZERO");
						stringBuilder.append(UNDERSCORE);
						stringBuilder.append(creditScoreHigh);
						stringBuilder.append(UNDERSCORE);
						stringBuilder.append(creditScoreLow);
		
						
						Object[] inParams = { companyCodeName, df.format(c.getTime()),
								this.msgSource.getMessage(infoKey, null, localeObj),
								//newCreditScoreResponse.getStrCreditSource() 
								this.msgSource.getMessage(infoName, null, localeObj)
								};
						if(!StringUtils.equals(ZERO, response.getDepositAmount())) {
							if(StringUtils.equals(EQ_INFO, infoKey) || StringUtils.equals(TU_INFO, infoKey)){
							response.setDepositReasonText(StringEscapeUtils
								.escapeHtml((this.msgSource.getMessage(
										stringBuilder.toString(), inParams, localeObj))));}
							else
								response.setDepositReasonText(this.msgSource.getMessage(DEFAULT_INFO, null, localeObj));
						}
						} else { // Non-zero credit scenario
						stringBuilder.append("NONZERO");
						stringBuilder.append(UNDERSCORE);
						stringBuilder.append(creditScoreHigh);
						stringBuilder.append(UNDERSCORE);
						stringBuilder.append(creditScoreLow);
						Object[] inParams = { companyCodeName, df.format(c.getTime()),
								this.msgSource.getMessage(infoKey, null, localeObj),
								//newCreditScoreResponse.getStrCreditSource(),
								this.msgSource.getMessage(infoName, null, localeObj),
								newCreditScoreResponse.getStrCreditScore(),
								newCreditScoreResponse.getStrCreditScoreLow(),
								newCreditScoreResponse.getStrCreditScoreHigh() };												
						if(!StringUtils.equals(ZERO, response.getDepositAmount())) {
							if(StringUtils.equals(EQ_INFO, infoKey) || StringUtils.equals(TU_INFO, infoKey)){
								response.setDepositReasonText(StringEscapeUtils
										.escapeHtml((this.msgSource.getMessage(
												stringBuilder.toString(), inParams, localeObj))));}
							else
							{response.setDepositReasonText(this.msgSource.getMessage(DEFAULT_INFO, null, localeObj));}
						}
					}
			}
/*Setting DepositReasonText Empty if we have Credit Freeze or Fraud*/		
				
				if(StringUtils.equals (response.getMessageCode(),MESSAGE_CREDIT_FREEZE)  || StringUtils.equals (response.getMessageCode(),MESSAGE_CREDIT_FRAUD)){
					response.setDepositReasonText(EMPTY);
				} 
				
				
				
		} catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(RESULT_DESCRIPTION_CREDIT_CHECK_FAILED);
			response.setStatusCode(STATUS_CODE_STOP);
			response.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			response.setMessageText(this.msgSource.getMessage(
					MESSAGE_CODE_TECHNICAL_ERROR, null, localeObj));
			throw new OAMException(200, e.getMessage(), response);
		} catch (NoSuchMessageException e) {
			logger.error("inside performCreditCheck:: exception occured ::", e);
			response.setDepositReasonText(EMPTY);
		} catch (Exception e) {
			logger.error("ERROR:" + METHOD_NAME, e);
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(RESULT_DESCRIPTION_CREDIT_CHECK_FAILED);
			response.setStatusCode(STATUS_CODE_STOP);
			response.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			response.setMessageText(this.msgSource.getMessage(
					MESSAGE_CODE_TECHNICAL_ERROR, null, localeObj));
			throw new OAMException(200, e.getMessage(), response);
		} finally {
			logger.debug("Processing updateServiceLocation ...");
			Assert.notNull(
					creditScoreRequest.getTrackingNum(),
					"trackingId must not be null.");
			UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();

			if (StringUtils.isNotEmpty(creditScoreRequest.getTrackingNum()))
				requestData.setTrackingId(creditScoreRequest.getTrackingNum());
			requestData.setCompanyCode(creditScoreRequest.getStrCompanyCode());

			String personId = getPersonIdByTrackingNo(requestData
					.getTrackingId());

			// Update service location and person table only when a valid person
			// id
			// is returned from getPersonIdByTrackingNo

			if (StringUtils.isNotEmpty(personId)) {
				if (StringUtils.isNotBlank(response.getMessageCode()))
					requestData.setMessageCode(response.getMessageCode());

				/* Setting service addresses */
				requestData.setRecentCallMade(CREDIT_CHECK);
				requestData.setServStreetNum(creditCheckRequest
						.getServStreetNum());
				requestData.setServStreetName(creditCheckRequest
						.getServStreetName());
				if (StringUtils.isNotEmpty(creditCheckRequest
						.getServStreetAptNum()))
					requestData.setServStreetAptNum(creditCheckRequest
							.getServStreetAptNum());
				requestData.setServCity(creditCheckRequest.getServCity());
				requestData.setServState(creditCheckRequest.getServState());
				requestData.setServZipCode(creditCheckRequest
						.getServZipCode());

				requestData.setAffiliateId(affiliateId);
				if (StringUtils
						.isNotEmpty(creditScoreRequest.getStrOfferCode()))
					requestData.setOfferCode(creditScoreRequest
							.getStrOfferCode());

				/* Setting billing addresses */
				requestData.setBillStreetNum(creditCheckRequest
						.getBillStreetNum());
				requestData.setBillStreetName(creditCheckRequest
						.getBillStreetName());
				if (StringUtils.isNotEmpty(creditCheckRequest
						.getBillStreetAptNum()))
					requestData.setBillStreetAptNum(creditCheckRequest
							.getBillStreetAptNum());
				if (StringUtils.isNotEmpty(creditCheckRequest.getBillCity()))
					requestData
							.setBillCity(creditCheckRequest.getBillCity());
				requestData.setBillState(creditCheckRequest.getBillState());
				requestData.setBillZipCode(creditCheckRequest
						.getBillZipCode());
				requestData.setBillPoBox(creditCheckRequest.getBillPOBox());
				requestData.setServiceStartDate(creditCheckRequest.getMviDate());
				
				if(!StringUtils.equals(ZERO, response.getDepositAmount())) {
					requestData.setPayCode(YES);	
					requestData.setDepositCode(DEPOSIT_OWED);
					requestData.setDepositAmount(response.getDepositAmount());
					
				} else {					
					requestData.setPayCode(FLAG_NO);
					requestData.setDepositCode(DEPOSIT_NONE);
					requestData.setDepositAmount(ZERO);
				}
				

				/* Updating service location affiliate table */
				
				String errorCode = this.updateServiceLocation(requestData);
				if (StringUtils.isNotBlank(errorCode))
					logger.debug("Finished processing updateServiceLocation, errorCode = "
							+ errorCode);

				UpdatePersonRequest requestDataPerson = new UpdatePersonRequest();
				/* Updating person affiliate table */
				errorCode = EMPTY;
				requestDataPerson.setPersonId(personId);
				// requestDataPerson.setLanguageCode(locale);
				requestDataPerson.setFirstName(creditScoreRequest
						.getStrFirstName());
				requestDataPerson.setLastName(creditScoreRequest
						.getStrLastName());
				if (StringUtils.isNotBlank(creditScoreRequest.getStrSSN()))
					requestDataPerson.setSsn(creditScoreRequest.getStrSSN());
				if (StringUtils.isNotBlank(newCreditScoreResponse
						.getStrCreditBucket()))
					requestDataPerson.setCredLevelNum(newCreditScoreResponse
							.getStrCreditBucket());
				if (StringUtils.isNotBlank(newCreditScoreResponse
						.getStrCreditSource()))
					requestDataPerson.setCredSourceNum(newCreditScoreResponse
							.getStrCreditSource());
				if (StringUtils.isNotBlank(newCreditScoreResponse
						.getStrCreditScore()))
					requestDataPerson.setCredScoreNum(newCreditScoreResponse
							.getStrCreditScore());
				requestDataPerson.setAdvActionData(StringUtils.removeEnd(
						creditFactor.toString(), String.valueOf(SEMI_COLON)));


				if(StringUtils.isNotBlank(response.getDepositAmount())) {
					if (StringUtils.isNotBlank(newCreditScoreResponse
							.getStrDepositHold()) 
							&& newCreditScoreResponse.getStrDepositHold()
									.equalsIgnoreCase(YES))
						requestDataPerson.setCredStatusCode(HOLD);
					else
						requestDataPerson.setCredStatusCode(NOTICE);
				} else {
					requestDataPerson.setCredStatusCode(RELEASE);	
				}

				errorCode = this.updatePerson(requestDataPerson);
				if (StringUtils.isNotBlank(errorCode))
					logger.debug("Finished processing updateServiceLocation, errorCode = "
							+ errorCode);
			}
		}

		logger.debug("END:" + METHOD_NAME);

		return response;
	}

	/**
	 * @author jyogapa1
	 * 
	 *         Handles Permit check response.
	 * 
	 * @param response
	 * @param permitCheckResponse
	 * @throws Exception
	 */
	private void handlePermitCheckResponse(CheckPermitResponse response,
			PermitCheckResponse permitCheckResponse)
			throws Exception {
		if (permitCheckResponse != null
				&& StringUtils.isNotEmpty(permitCheckResponse.getStrErrCode())) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(permitCheckResponse.getStrErrCode());
		}

		if (permitCheckResponse != null) {
			// TODO Read NRGWS response data and populate it in NRGREST
			// response dto
			// JavaBeanUtil.copy(permitCheckResponse, response);
		} else {
			response.setResultCode(RESULT_CODE_NO_DATA);
			response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
		}
	}

	/**
	 * @author jyogapa1
	 * Handles Submit enrollment response.
	 * 
	 * @param response
	 * @param permitCheckResponse
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void handleSubmitEnrollmentResponse(
			EnrollmentResponse responseDto,
			SubmitEnrollResponse submitEnrollResponse) throws Exception {		
		if (submitEnrollResponse != null
				&& StringUtils.isNotEmpty(submitEnrollResponse.getStrErrCode())) {
			responseDto.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			responseDto.setResultDescription(submitEnrollResponse
					.getStrErrCode());
		}

		if (submitEnrollResponse != null) {
			// TODO Read NRGWS response data and populate it in NRGREST
			// response dto
			// JavaBeanUtil.copy(submitEnrollResponse, response);
		} else {
			responseDto.setResultCode(RESULT_CODE_NO_DATA);
			responseDto.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
		}
	}
	
	/**
	 * @author jyogapa1 (Jenith)
	 * 
	 * @param enrollmentResponse
	 * @param oeSignUpDTO
	 */
	private void setEnrollmentResponse(EnrollmentResponse enrollmentResponse,
			OESignupDTO oeSignUpDTO) {
		// TODO Set error code if any. (Reliant code base)
		// this.setErrorCode(oeSignUpDTO);

		if (BPSD.equalsIgnoreCase(oeSignUpDTO.getErrorCode())) {

			enrollmentResponse.setResultCode(RESULT_CODE_SUCCESS);
			enrollmentResponse.setResultDescription(msgSource
					.getMessage(RESULT_DESCRIPTION_BP_MATCH_UNRESOLVED));
			enrollmentResponse.setStatusCode(STATUS_CODE_CONTINUE);
			enrollmentResponse.setMessageCode(StringUtils.EMPTY);
			enrollmentResponse.setMessageText(StringUtils.EMPTY);
			enrollmentResponse.setErrorCode(StringUtils.EMPTY);
			enrollmentResponse.setErrorDescription(StringUtils.EMPTY);
			//enrollmentResponse.setTrackingId(StringUtils.EMPTY);
			enrollmentResponse.setIdocNumber(StringUtils.EMPTY);
			enrollmentResponse.setCaNumber(StringUtils.EMPTY);
			enrollmentResponse.setCheckDigit(StringUtils.EMPTY);
			enrollmentResponse.setBpid(StringUtils.EMPTY);

		} else if (NESID.equalsIgnoreCase(oeSignUpDTO.getErrorCode())||(SWHOLD.equalsIgnoreCase(oeSignUpDTO.getErrorCode()))||(StringUtils.isBlank(oeSignUpDTO.getErrorCode()))) {

			enrollmentResponse.setResultCode(RESULT_CODE_SUCCESS);
			enrollmentResponse.setResultDescription(StringUtils.EMPTY);
			enrollmentResponse.setStatusCode(STATUS_CODE_CONTINUE);
			enrollmentResponse.setMessageCode(StringUtils.EMPTY);
			enrollmentResponse.setMessageText(StringUtils.EMPTY);
			enrollmentResponse.setErrorCode(StringUtils.EMPTY);
			enrollmentResponse.setErrorDescription(StringUtils.EMPTY);
			
			/*enrollmentResponse.setTrackingId(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getTrackingNumber(), StringUtils.EMPTY)); */
			enrollmentResponse.setIdocNumber(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getIdocNumber(), StringUtils.EMPTY));
			enrollmentResponse.setCaNumber(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getContractAccountNum(), StringUtils.EMPTY));
			enrollmentResponse.setCheckDigit(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getCheckDigit(), StringUtils.EMPTY));
			enrollmentResponse.setBpid(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getBusinessPartnerID(), StringUtils.EMPTY));

		} 
		else {
			// set error code if any in response
			enrollmentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			enrollmentResponse.setResultDescription("Enrollment Call Failed with error: "+oeSignUpDTO.getErrorCode());
			enrollmentResponse.setStatusCode(STATUS_CODE_STOP);
			enrollmentResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			enrollmentResponse
					.setMessageText(getMessage(SUBMIT_ENROLLMENT_TECHNICAL_ERROR_MSG));

			// Added for error cases also:
			/*enrollmentResponse.setTrackingId(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getTrackingNumber(), StringUtils.EMPTY)); */
			enrollmentResponse.setIdocNumber(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getIdocNumber(), StringUtils.EMPTY));
			enrollmentResponse.setCaNumber(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getContractAccountNum(), StringUtils.EMPTY));
			enrollmentResponse.setCheckDigit(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getCheckDigit(), StringUtils.EMPTY));
			enrollmentResponse.setBpid(StringUtils.defaultIfEmpty(
					oeSignUpDTO.getBusinessPartnerID(), StringUtils.EMPTY));
		}
		// Set OE Signup DTO in the response
		// enrollmentResponse.setOeSignupDTO(oeSignUpDTO);
	}

	/**
	 * Handles Submit enrollment exception.
	 * 
	 * @param oeSignUpDTO
	 * @param e
	 */
	private void handleSubmitEnrollmentError(OESignupDTO oeSignUpDTO,
			Exception e) { /*
		String errorVariable = "";
		Boolean isCriticalExceptionHappen = false;
		if ((StringUtils.isBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
				.getContractAccountNum())))
				|| StringUtils.isBlank(CommonUtil
						.removeLeftPadZeros(oeSignUpDTO.getIdocNumber()))
				|| StringUtils
						.isBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
								.getBusinessPartnerID()))) {
			// viewPropKey=VIEW_PAGE_TECHNICAL_ERROR;
			if (!isCriticalExceptionHappen) {
				errorVariable = EXCEPTION_IN_SUBMIT_ENROLLMENT_CALL;
				isCriticalExceptionHappen = true;
			}
		}*/
		// Update for service location
		oeSignUpDTO.setErrorCode(CCSERR);
		oeSignUpDTO.setReqStatusCd(FLAG_N);

		logger.error(
				oeSignUpDTO.printOETrackingID()
						+ " inside EnrollmentHelper :: Exception in SubmitEnrollment call error is :: ",
				e);
	}
	
	/**
	 * Method for ESID and ESID Profile calls
	 * 
	 * @param serviceAddressDO
	 * @param companyCode
	 * @param trackingId
	 * @return ESIDDO
	 * 
	 */
	public ESIDDO getESIDInfo(AddressDO serviceAddressDO,
			String companyCode) {
		logger.info("OEBO.getESIDInfo() start");

		ESIDDO esidDO = new ESIDDO();
		EsidProfileResponse esidProfileResponse = null;
		if (StringUtils.isNotBlank(serviceAddressDO.getStrStreetName())) {
			AddressValidateResponse addressResponse = null;
			try {
				addressResponse = this.addressService
						.performTrilliumCleanup(serviceAddressDO);
				serviceAddressDO.setTrilliumMatchStatus(addressResponse
						.getMatchStatusFlag());
			} catch (Exception e) {
				logger.error("OEBO.getESIDInfo(): Exception in addressService.performTrilliumCleanup():", e);
				serviceAddressDO.setTrilliumMatchStatus(NO_MATCH);
			}
			logger.debug("OEBO.getESIDInfo() Trillium cleanup status: "+serviceAddressDO.getTrilliumMatchStatus());
			if ((addressResponse != null)
					&& (serviceAddressDO.getTrilliumMatchStatus() != null)
					&& (!NO_MATCH.equals(serviceAddressDO.getTrilliumMatchStatus()))
					) {
				serviceAddressDO = handleAddressValidateResponse(addressResponse);
				serviceAddressDO.setTrilliumMatchStatus(addressResponse
						.getMatchStatusFlag());
				serviceAddressDO.setTrilliumCallStatus(addressResponse
						.getStatusValue());
			}
		}

		try {
			GetEsiidResponse esidResponse = this.addressService.getESIDInfo(serviceAddressDO, companyCode);

			if ((esidResponse != null)
					&& (StringUtils.isBlank(esidResponse.getStrErrCode()))
					&& (StringUtils.isNotBlank(esidResponse.getStrESIID()))) {
				logger.debug("OEBO.getESIDInfo() GETTING ESID SUCCESSFUL:"+ esidResponse.getStrErrCode()+ " :: " + esidResponse.getStrESIID());
				esidDO.setEsidNumber(esidResponse.getStrESIID());
				if (esidResponse.getStrESIID().equalsIgnoreCase(NESID)
						|| esidResponse.getStrESIID().equalsIgnoreCase(MESID)
						|| esidResponse.getStrESIID().equalsIgnoreCase(NRESID)) {
					return esidDO;
				}else{
					esidProfileResponse = this.addressService.getESIDProfile(esidResponse.getStrESIID(),companyCode);
					esidDO = setESIDDTO(esidProfileResponse);
					esidDO.setEsidTDSP(esidResponse.getStrTDSP());
					logger.debug("OEBO.getESIDInfo() ESID PROFILE SUCESSFUL");
				}
			} else {
				logger.debug("OEBO.getESIDInfo() GETTING ESID FAILED:"+ esidResponse.getStrErrCode());
			}
		} catch (ServiceException localServiceException) {
			logger.error("ServiceException in OEBO.getESIDInfo():"
					, localServiceException);
		}
		return esidDO;
	}

	/**
	 * @param companyCode
	 * @param affiliateId
	 * @param brandId
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servZipCode (could be Zipcode only or ZipCode and Zip+4)
	 * @param tdspCodeCCS
	 * @param transactionType
	 * @param trackingId
	 * @param bpMatchFlag
	 * @param locale
	 * @return EsidInfoTdspCalendarResponse
	 * @throws OAMException
	 */	
	public EsidInfoTdspCalendarResponse getESIDAndCalendarDates(
			String companyCode, String affiliateId, String brandId, String servStreetNum,
			String servStreetName, String servStreetAptNum, String servZipCode,
			String tdspCodeCCS, String transactionType, String trackingId, String bpMatchFlag,
			String locale, String esid,String sessionId) throws OAMException {
		/* author Mayank Mishra */
		String METHOD_NAME = "OEBO: getESIDAndCalendarDates(..)";
		logger.debug("Start:" + METHOD_NAME);
		
		EsidInfoTdspCalendarResponse response = new EsidInfoTdspCalendarResponse();
		ESIDDO esidDo = new ESIDDO();
		AddressDO serviceAddressDO = new AddressDO();
		
		Locale localeObj = null;

		if (locale.equalsIgnoreCase(S))
			localeObj = new Locale("es", "US");
		else 
			localeObj = new Locale("en", "US");
		
		response.setEsid(EMPTY);
		response.setMeterType(EMPTY);
		response.setSwitchHoldFlag(EMPTY);
		
	    try {
			serviceAddressDO.setStrStreetNum(servStreetNum);
			serviceAddressDO.setStrStreetName(servStreetName);
			serviceAddressDO.setStrApartNum(servStreetAptNum);
			serviceAddressDO.setStrZip(CommonUtil.trimZipCode(servZipCode));
			List<Map<String, Object>> cityStateList = null;
			cityStateList = this.addressService.getCityStateFromZip(CommonUtil.trimZipCode(servZipCode));

			for (Map<String, Object> cityStateMap : cityStateList) {
				serviceAddressDO.setStrCity((String) cityStateMap.get(CITY));
				serviceAddressDO.setStrState((String) cityStateMap.get(STATE));
				logger.debug("OEBO.getESIDAndCalendarDates() City From ZIP: " + serviceAddressDO.getStrCity());
				logger.debug("OEBO.getESIDAndCalendarDates() State From ZIP: " + serviceAddressDO.getStrState());
			}

			if (StringUtils.isNotBlank(servStreetNum)
					&& StringUtils.isNotBlank(servStreetName)) {
				logger.debug("OEBO.getESIDAndCalendarDates() street address entered! getting ESID info");
				
				if(StringUtils.isEmpty(esid)) {
					esidDo = getESIDInfo(serviceAddressDO, companyCode);
				}else {
					EsidProfileResponse esidProfileResponse = this.addressService.getESIDProfile(esid,companyCode);
					esidDo = setESIDDTO(esidProfileResponse);
					//Start || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
					if(esidDo.isEsidBlocked()){
						response.setMessageCode(ESID_RESTRICTION);
						response.setMessageText(getAllBrandResponseMessage(companyCode, brandId, ESID_RESTRICTION_TEXT_MESSAGE, locale));
						response.setStatusCode(Constants.STATUS_CODE_STOP);
						return response;
					}
					//END || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari || 15/12/2019
					TdspByESIDResponse tdspByESIDResponse = this.tosService.ccsGetTDSPFromESID(esid,companyCode,sessionId);
					if ((tdspByESIDResponse != null) && (StringUtils.isNotBlank(tdspByESIDResponse.getServiceId()))) {
						String tdspCodeCCSForEsid = tdspByESIDResponse.getServiceId();
						esidDo.setEsidTDSP(this.appConstMessageSource.getMessage("ccs.tdsp.web.equivalent."
										+ tdspCodeCCSForEsid, null, null));						
						logger.info("TDSP Code:"+esidDo.getEsidTDSP());
					} else {
						response.setMessageCode(AREA_NOT_SERVICED);
						response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(locale)));
						response.setStatusCode(Constants.STATUS_CODE_STOP);
						response.setResultCode(Constants.RESULT_CODE_SUCCESS );
						return response;
					}
				}

				if (esidDo != null)
				{
					response.setMeterType(esidDo.getMeterType());
					response.setSwitchHoldFlag(esidDo.getSwitchHoldStatus());
					response.setTdspCode(tdspCodeCCS); 
					if(StringUtils.isNotBlank(esidDo.getEsidNumber())) {
						String strESIDNumber = esidDo.getEsidNumber();
						if (strESIDNumber.equalsIgnoreCase(MESID) || strESIDNumber.equalsIgnoreCase(NESID))
						{
							response.setEsid(EMPTY);
							response.setResultCode(RESULT_CODE_SUCCESS);
							response.setStatusCode(STATUS_CODE_CONTINUE);
							response.setMessageCode(strESIDNumber);
							if (MESID.equalsIgnoreCase(strESIDNumber)) {
								response.setMessageText(msgSource.getMessage(MESSAGE_CODE_MESID));
							} else if (NESID.equalsIgnoreCase(strESIDNumber)) {
								response.setMessageText(msgSource.getMessage(MESSAGE_CODE_NESID));
							}
							response.setTdspCode(EMPTY);
							response.setAvailableDates(EMPTY);
						} else if (strESIDNumber.equalsIgnoreCase(NRESID)) {
								response.setEsid(EMPTY);
								response.setResultCode(RESULT_CODE_SUCCESS);
								response.setStatusCode(STATUS_CODE_STOP);
								response.setMessageCode(strESIDNumber);
								response.setMessageText(msgSource.getMessage(MESSAGE_CODE_NRESID));
						} else {
							response.setEsid(strESIDNumber);
						}					
					}
					// Switch Hold ON scenario for SWI
					if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_SWITCH)
							&& StringUtils.equals(esidDo.getSwitchHoldStatus(),SWITCH_HOLD_STATUS_ON)) {
						logger.debug("ERROR:" + METHOD_NAME);
						response.setResultCode(RESULT_CODE_SUCCESS);
						//response.setResultDescription(RESULT_DESCRIPTION_SWITCH_HOLD);
						response.setStatusCode(STATUS_CODE_STOP);
						response.setAvailableDates(EMPTY);
						response.setSwitchHoldFlag(SWITCH_HOLD_STATUS_ON);
						response.setTdspFee(EMPTY);
						response.setMessageCode(MESSAGE_CODE_SWITCH_HOLD);
						response.setMessageText(this.msgSource.getMessage(MESSAGE_CODE_SWITCH_HOLD, null, localeObj));
						return response;
					}else if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_MOVE_IN)
							&& StringUtils.equals(esidDo.getSwitchHoldStatus(),SWITCH_HOLD_STATUS_ON)) {
						response.setResultCode(RESULT_CODE_SUCCESS);
						response.setStatusCode(STATUS_CODE_CONTINUE);
						response.setSwitchHoldFlag(SWITCH_HOLD_STATUS_ON);
						response.setMessageCode(MESSAGE_CODE_NOTIFY_SWITCH_HOLD);
						String messageCodeText = getMessageCodeTextForNotifySwitchHold(
								brandId, companyCode);
						response.setMessageText(messageCodeText);
					}
					
					// ESID Active in company scenario (ESID active)
					if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_SWITCH)
							&& (StringUtils.isNotEmpty(esidDo.getEsidStatus())					
									&& esidDo.getEsidStatus().equalsIgnoreCase(STATUS_ACTIVE))) {
						logger.debug("ERROR:" + METHOD_NAME);
						response.setResultCode(RESULT_CODE_SUCCESS);
						//response.setResultDescription(RESULT_DESCRIPTION_ACTIVE_ESID);
						response.setStatusCode(STATUS_CODE_STOP);
						response.setAvailableDates(EMPTY);
						response.setTdspFee(EMPTY);
						response.setMessageCode(MESSAGE_CODE_ESID_ACTIVE);
						response.setMessageText(this.msgSource.getMessage(MESSAGE_CODE_ESID_ACTIVE, null, localeObj));
						return response;
					}

					// Non-Residential scenario (Business meter scenario)
					if (StringUtils.isNotEmpty(esidDo.getPremiseType())
									&& !esidDo.getPremiseType().equalsIgnoreCase(RESI)) {
						logger.debug("ERROR:" + METHOD_NAME);
						response.setResultCode(RESULT_CODE_SUCCESS);
						//response.setResultDescription(RESULT_DESCRIPTION_BUSINESS_METER);
						response.setStatusCode(STATUS_CODE_STOP);
						response.setAvailableDates(EMPTY);
						response.setTdspFee(EMPTY);
						response.setMessageCode(MESSAGE_CODE_BUSINESS_METER);
						response.setMessageText(this.msgSource.getMessage(MESSAGE_CODE_BUSINESS_METER, null, localeObj));
						return response;
					}
				}//else return response;
			} 
			// GET tdsp calendar dates
			this.getTdspDates(companyCode, trackingId, transactionType,	tdspCodeCCS, bpMatchFlag, esidDo, response, localeObj);
	    }catch (Exception e) {
			logger.error("OEBO.getESIDInfo() Exception occurred when invoking getESIDInfo", e);
			e.printStackTrace();
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION + ": " + e.getMessage());
			response.setStatusCode(STATUS_CODE_CONTINUE);
			response.setMessageCode(EMPTY);
			response.setMessageText(EMPTY);
		}
		finally {
			// Call update service location
			this.updateServiceLocation(companyCode, affiliateId, trackingId, 
					serviceAddressDO, esidDo, response,esid);
		}
	    
	  //Default to EMPTY if statusflag is "OFF"
	  	if(StringUtils.equalsIgnoreCase("OFF",response.getSwitchHoldFlag())){
	  			response.setSwitchHoldFlag(EMPTY);
	  	}
	    
		logger.debug("END:" + METHOD_NAME);

		return response;
	}
	
	public EsidDetailsResponse getEsidDetails(EsidDetailsRequest request,String sessionId) {
		String methodName = "getEsidDetails(..)";
		logger.debug("Entering >> " + methodName);

		EsidDetailsResponse response = new EsidDetailsResponse();
		ESIDDO esidDo = new ESIDDO();
		AddressDO serviceAddressDO = new AddressDO();

		response.setEsidNumber(EMPTY);
		response.setMeterType(EMPTY);
		response.setSwitchHoldStatus(EMPTY);
		List<TDSPDO> tdspDOList = new ArrayList<TDSPDO>();

		try {
			serviceAddressDO.setStrStreetNum(request.getServStreetNum());
			serviceAddressDO.setStrStreetName(request.getServStreetName());
			serviceAddressDO.setStrApartNum(request.getServStreetAptNum());
			serviceAddressDO.setStrZip(CommonUtil.trimZipCode(request
					.getServZipCode()));
			List<Map<String, Object>> cityStateList = addressService
					.getCityStateFromZip(CommonUtil.trimZipCode(request
							.getServZipCode()));

			for (Map<String, Object> cityStateMap : cityStateList) {
				serviceAddressDO.setStrCity((String) cityStateMap.get(CITY));
				serviceAddressDO.setStrState((String) cityStateMap.get(STATE));
			}
			
			logger.debug("City from servZipCode: "
					+ serviceAddressDO.getStrCity());
			logger.debug("State from servZipCode: "
					+ serviceAddressDO.getStrState());

			if ((StringUtils.isNotEmpty(request.getServStreetNum())
					&& StringUtils.isNotEmpty(request.getServStreetName()))||(StringUtils.isNotEmpty(request.getEsid()))) {
				logger.debug("serviceAddressDO = "
						+ ReflectionToStringBuilder.toString(serviceAddressDO));
							
				
				if(StringUtils.isEmpty(request.getEsid())) {
					esidDo = getESIDInfo(serviceAddressDO, request.getCompanyCode());
				}else {
					
					EsidProfileResponse esidProfileResponse = this.addressService.getESIDProfile(request.getEsid(),request.getCompanyCode());
					esidDo = setESIDDTO(esidProfileResponse);
					TdspByESIDResponse tdspByESIDResponse = this.tosService.ccsGetTDSPFromESID(request.getEsid(),request.getCompanyCode(),sessionId);										
					if ((tdspByESIDResponse != null) && (StringUtils.isNotBlank(tdspByESIDResponse.getServiceId()))) {
						String tdspCodeCCSForEsid = tdspByESIDResponse.getServiceId();
						esidDo.setEsidTDSP(this.appConstMessageSource.getMessage("ccs.tdsp.web.equivalent."
										+ tdspCodeCCSForEsid, null, null));						
						logger.info("TDSP Code:"+esidDo.getEsidTDSP());
						
						TDSPDO tdspDO = new TDSPDO();
						tdspDO.setTdspCodeCCS(tdspCodeCCSForEsid);
						tdspDO.setTdspCodeWeb(this.appConstMessageSource
								.getMessage("ccs.tdsp.web.equivalent."
										+ tdspCodeCCSForEsid, null, null));
						tdspDO.setTdspName(this.appConstMessageSource
								.getMessage(tdspCodeCCSForEsid, null, null));

						tdspDOList.add(tdspDO);
						response.setEsidNumber(request.getEsid());
						response.setEsidTDSP(tdspCodeCCSForEsid);
					} else {
						response.setMessageCode(AREA_NOT_SERVICED);
						response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
						response.setStatusCode(Constants.STATUS_CODE_STOP);
						response.setResultCode(Constants.RESULT_CODE_SUCCESS );
						return response;
					}
				}				
				
				
				// Copy Esid details properties into response.
				BeanUtils.copyProperties(response, esidDo);
				logger.debug("esidDo = " + esidDo);
				
				// Set esidTDSP to its respective code if it contains numeric
				// digits:
				if (StringUtils.isNotBlank(response.getEsidTDSP())
						&& StringUtils.isNumeric(response.getEsidTDSP())) {
					String tdspCode = appConstMessageSource.getMessage(
							response.getEsidTDSP(), null, null);
					if (StringUtils.isNotBlank(tdspCode)) {
						response.setEsidTDSP(tdspCode);
					}
				}

				if (esidDo != null
						&& StringUtils.isNotEmpty(esidDo.getEsidNumber())) {
					String esidNumber = esidDo.getEsidNumber();
					if (MESID.equalsIgnoreCase(esidNumber)
							|| NRESID.equalsIgnoreCase(esidNumber)) {
						response.setEsidNumber(EMPTY);
						response.setResultCode(RESULT_CODE_SUCCESS);
						response.setStatusCode(STATUS_CODE_CONTINUE);
						response.setMessageCode(esidNumber);
						if (MESID.equalsIgnoreCase(esidNumber)) {
							response.setMessageText(msgSource
									.getMessage(MESSAGE_CODE_MESID));
						} else if (NRESID.equalsIgnoreCase(esidNumber)) {
							response.setMessageText(msgSource
									.getMessage(MESSAGE_CODE_NRESID));
						}
					} else {
						if (NESID.equalsIgnoreCase(esidNumber)) {
							response.setEsidNumber(EMPTY);
							response.setMessageCode(esidNumber);
							response.setMessageText(msgSource
									.getMessage(MESSAGE_CODE_NESID));
						} else {
							response.setEsidNumber(esidNumber);
						}
					}

					if (StringUtils.isNotBlank(esidDo.getMeterType())) {
						response.setMeterType(esidDo.getMeterType());
					}
				}
			}
		} catch (Exception e) {
			logger.error(
					"1. Exception occurred while invoking getEsidDetails method.",
					e);
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION + ": "
					+ e.getMessage());
			response.setStatusCode(STATUS_CODE_CONTINUE);
			response.setMessageCode(EMPTY);
			response.setMessageCode(EMPTY);
		}

		try {

			if (esidDo != null
					&& StringUtils.isNotEmpty(esidDo.getSwitchHoldStatus())) {
				response.setSwitchHoldStatus(esidDo.getSwitchHoldStatus());
			}

			// Switch Hold ON scenario for SWI
			if (esidDo != null
					&& TRANSACTION_TYPE_SWITCH.equalsIgnoreCase(request
							.getTransactionType())
					&& StringUtils.isNotEmpty(esidDo.getSwitchHoldStatus())
					&& SWITCH_HOLD_STATUS_ON.equalsIgnoreCase(esidDo
							.getSwitchHoldStatus())) {
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(RESULT_DESCRIPTION_SWITCH_HOLD);
				response.setStatusCode(STATUS_CODE_STOP);
				response.setSwitchHoldStatus(SWITCH_HOLD_STATUS_ON);
				response.setMessageCode(MESSAGE_CODE_SWITCH_HOLD);
				response.setMessageText(msgSource
						.getMessage(MESSAGE_CODE_SWITCH_HOLD));
				return response;
			}

			// Switch Hold ON scenario for MVI.
			if (esidDo != null
					&& TRANSACTION_TYPE_MOVE_IN.equalsIgnoreCase(request
							.getTransactionType())
					&& SWITCH_HOLD_STATUS_ON.equalsIgnoreCase(esidDo
							.getSwitchHoldStatus())) {

				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(RESULT_DESCRIPTION_SWITCH_HOLD);
				response.setStatusCode(STATUS_CODE_CONTINUE);
				response.setSwitchHoldStatus(SWITCH_HOLD_STATUS_ON);
				response.setMessageCode(MESSAGE_CODE_NOTIFY_SWITCH_HOLD);
				String messageCodeText = getMessageCodeTextForNotifySwitchHold(
						request.getBrandId(), request.getCompanyCode());
				response.setMessageText(messageCodeText);
				return response;
			}

			// Switch Hold and Active scenario (ESID active)
			if (esidDo != null
					&& TRANSACTION_TYPE_SWITCH.equalsIgnoreCase(request
							.getTransactionType())
					&& StringUtils.isNotEmpty(esidDo.getEsidStatus())
					&& STATUS_ACTIVE.equalsIgnoreCase(esidDo.getEsidStatus())) {
				response.setSwitchHoldStatus(SWITCH_HOLD_STATUS_ON);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(RESULT_DESCRIPTION_ACTIVE_ESID);
				response.setStatusCode(STATUS_CODE_STOP);
				response.setMessageCode(MESSAGE_CODE_ESID_ACTIVE);
				response.setMessageText(msgSource
						.getMessage(MESSAGE_CODE_ESID_ACTIVE));
				return response;
			}

			// Non-Residential scenario (Business meter scenario)
			if (esidDo != null
					&& (StringUtils.isNotEmpty(esidDo.getPremiseType()) && !esidDo
							.getPremiseType().equalsIgnoreCase(RESI))) {
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(RESULT_DESCRIPTION_BUSINESS_METER);
				response.setStatusCode(STATUS_CODE_STOP);
				response.setMessageCode(MESSAGE_CODE_BUSINESS_METER);
				response.setMessageText(msgSource
						.getMessage(MESSAGE_CODE_BUSINESS_METER));
				return response;
			}
			
		} catch (Exception e) {
			logger.error(
					"2. Exception occurred while invoking getEsidDetails method.",
					e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION + ": "
					+ e.getMessage());
		}
		
		if(StringUtils.isEmpty(request.getEsid())) {
		
			TdspResponse tdspResponse = getTDSPDetails(request.getCompanyCode(),
					request.getBrandId(), request.getServStreetNum(),
					request.getServStreetName(), request.getServStreetAptNum(),
					request.getServZipCode(), sessionId);
			response.setTdspData(tdspResponse.getTdspData());
		} else {
			
			response.setTdspData(tdspDOList);
		}
		logger.info("TDSP Data List :"+response.getTdspData());
		
		if(response.getTdspData() == null || response.getTdspData().size() == 0) {
			response.setMessageCode(AREA_NOT_SERVICED);
			response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			response.setResultDescription("Success");
			return response;
		}
		
		
		EsidInfoTdspCalendarResponse calendarResp = new EsidInfoTdspCalendarResponse();
		calendarResp.setMeterType(response.getMeterType());
		calendarResp.setEsid(response.getEsidNumber());
		String transactionType = (StringUtils.equals(request.getTransactionType(), SWI))? SWI : MVI;
		List<TDSPDO> tdspDataList = response.getTdspData();
		for(TDSPDO tdspDo : tdspDataList)
		{
			try {
				this.getTdspDates(request.getCompanyCode(), sessionId, transactionType, tdspDo.getTdspCodeCCS(), StringUtils.EMPTY, esidDo, calendarResp, new Locale(CommonUtil.localeCode(request.getLanguageCode())));
				tdspDo.setAvailableDates(calendarResp.getAvailableDates());
				tdspDo.setTdspFee(calendarResp.getTdspFee());
			} catch (Exception e) {
				logger.error("Exception in getTdspDetails Calendar Dates", e);
			}
		}
		response.setTdspData(tdspDataList);
		
		//Default to EMPTY if statusflag is "OFF"
		if(StringUtils.equalsIgnoreCase("OFF",response.getSwitchHoldStatus())){
			response.setSwitchHoldStatus(EMPTY);
		}
		logger.debug("Exiting << " + methodName);

		return response;
	}
	
	private String getMessageCodeTextForNotifySwitchHold(String brandId,
			String companyCode) {
		String messageCodeText = StringUtils.EMPTY;
		String companyName = CommonUtil.getCompanyName(brandId, companyCode);

		try {
			String urlNewOccpConfForm = StringUtils.EMPTY;
			if (COMPANY_NAME_RELIANT.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(
						MSG_KEY_URL_NEW_CONF_FORM_RELIANT, null, null);
			} else if (COMPANY_NAME_GME.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(
						MSG_KEY_URL_NEW_CONF_FORM_GME, null, null);
			} else if (COMPANY_NAME_CIRRO.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(
						MSG_KEY_URL_NEW_CONF_FORM_CIRRO, null, null);
			} else if (COMPANY_NAME_PENNYWISE.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(
						MSG_KEY_URL_NEW_CONF_FORM_PENNYWISE, null, null);
			}
			messageCodeText = msgSource.getMessage(
					MESSAGE_CODE_NOTIFY_SWITCH_HOLD,
					new String[] { urlNewOccpConfForm });
		} catch (Exception e) {
			logger.error(
					"Problem occurred while getting the message text for messageCode = "
							+ MESSAGE_CODE_NOTIFY_SWITCH_HOLD, e);
			messageCodeText = StringUtils.EMPTY;
		}
		return messageCodeText;
	}

	public String addPerson(AddPersonRequest request) {
		logger.debug("Entering >> addPerson");
		logger.debug("request = " + request);
		Assert.notNull(request, "request");
		String personId = personDao.addPerson(request);
		logger.debug("Exiting << addPerson");
		return personId;
	}
	
	public String updatePerson(UpdatePersonRequest request) {
		logger.debug("Entering >> updatePerson");
		logger.debug("request = " + request);
		String errorCode = personDao.updatePerson(request);
		logger.debug("Exiting << updatePerson");
		return errorCode;
	}
	
	public PersonResponse getPerson(String personId) {
		logger.debug("Entering >> getPerson");
		logger.debug("personId = " + personId);
		PersonResponse personResponse = personDao.getPerson(personId);
		logger.debug("Exiting << getPerson");
		return personResponse;
	}
	
	public String addServiceLocation(AddServiceLocationRequest request) {
		logger.debug("Entering >> addServiceLocation");
		logger.debug("request = " + request);
		Assert.notNull(request, "request");
		String trackingNo = serviceLocationDAO.addServiceLocation(request);
		logger.debug("Exiting << addServiceLocation");
		return trackingNo;
	}
	
	public String updateServiceLocation(UpdateServiceLocationRequest request) {
		logger.debug("Entering >> updateServiceLocation");
		logger.debug("request = " + request);
		String errorCode = serviceLocationDAO.updateServiceLocation(request);
		logger.debug("Exiting << updateServiceLocation");
		return errorCode;
	}
	
	public ServiceLocationResponse getEnrollmentData(String trackingId) {
		logger.info("Entering >> getEnrollmentData");
		logger.info("trackingId = " + trackingId);
		ServiceLocationResponse serviceLocationResponse = serviceLocationDAO
				.getServiceLocation(trackingId);
		logger.info("Exiting << getEnrollmentData");
		return serviceLocationResponse;
	}

	public String getPersonIdByTrackingNo(String trackingNo) {
		logger.debug("Entering >> getPersonIdByTrackingNo");
		logger.debug("trackingNo = " + trackingNo);
		Assert.notNull(trackingNo, "trackingNo must not be null.");
		String personId = personDao.getPersonIdByTrackingNo(trackingNo);
		logger.debug("personId = " + personId);
		logger.debug("Exiting << getPersonIdByTrackingNo");
		return personId;
	}

	public List<Map<String, String>> getPersonIdAndRetryCountByTrackingNo(
			String trackingNo) {
		logger.debug("Entering >> getPersonIdAndRetryCountByTrackingNo");
		logger.debug("trackingNo = " + trackingNo);
		Assert.notNull(trackingNo, "trackingNo must not be null.");
		List<Map<String, String>> dataList = personDao
				.getPersonIdAndRetryCountByTrackingNo(trackingNo);
		logger.debug("dataList = " + dataList);
		logger.debug("Exiting << getPersonIdAndRetryCountByTrackingNo");
		return dataList;
	}

	/**
	 * @author jyogapa1
	 * 
	 * Updates person and service location.
	 * 
	 * @param oeSignUpDTO
	 */
	private void updatePersonAndServiceLocation(OESignupDTO oeSignUpDTO) {
		
		if (StringUtils.isNotBlank(oeSignUpDTO.getTrackingNumber())) {
			// 7. Call to get the Person Id if trackingID is passed in the
			// request
			String personId = this.getPersonIdByTrackingNo(oeSignUpDTO
					.getTrackingNumber());
			if (StringUtils.isNotBlank(personId)) {
				oeSignUpDTO.getPerson().setPersonID(personId);

				// 8. Call save service location.
				this.updateServiceLocation(oeRequestHandler
						.createUpdateServiceLocationRequest(oeSignUpDTO));

				// 9. Call save Person table for the personId returned from the
				// getPersonId() call.
				this.updatePerson(oeRequestHandler
						.createUpdatePersonRequest(oeSignUpDTO));
			}
		}
	}
	
	/**
	 * Send confirmation email.
	 * 
	 * @param oeSignUpDTO
	 */
	@SuppressWarnings("unused")
	private void sendConfirmationEmail(OESignupDTO oeSignUpDTO) {

		Boolean isEmailSent = this.sendEnrollmentConfirmationEmail(oeSignUpDTO,
				oeSignUpDTO.getLocaleLanguageCode());

		//if (!(isEmailSent) && StringUtils.isBlank(errorVariable)
			//	&& !(isCriticalExceptionHappen)) {
			//errorVariable = ERROR_ENROLLMENT_CONFIRMATION_EMAIL;
		//}
	}
	
	
	/**
	 * Method returns the Busines Partner details of the prospect if the there is any past balance pending or
	 * <br/> already a customer or the details show the customer as BPSD
	 * @param response
	 * @param errorCd
	 * @param messageCode
	 * @param firstName
	 * @param lastName
	 * @param tdl
	 * @param maidenName
	 * @param companyCode
	 * @param servStreetAptNum
	 * @param servCity
	 * @param servState
	 * @param servStreetName
	 * @param servStreetNum
	 * @param servZipCode
	 * @param ssn
	 * @return
	 */
	public Map<String,Object> performBpMatch(PerformPosIdandBpMatchResponse response,String errorCd,String messageCode,
			String firstName,String lastName,String tdl,String maidenName,
			String companyCode,String servStreetAptNum,String servCity,String servState,String servStreetName,
			String servStreetNum,String servZipCode,String ssn, String brandID)
	{
		Map<String,Object> performBpMatchResponse=new HashMap<String, Object>();
		com.multibrand.dto.BPMatchDTO bpMatchDto=new com.multibrand.dto.BPMatchDTO();
		String addressMatchBPId=null;
		
		try{
			BpMatchCCSRequest bpMatchReq= oeRequestHandler.createBpmatchRequest(firstName, lastName, tdl, maidenName, companyCode, servStreetAptNum, servCity, servState, servStreetName, servStreetNum, servZipCode, ssn);
			BpMatchCCSResponse bpmatchResponse= oeService.getBPMatchStatusFromCCS(bpMatchReq);

			if(bpmatchResponse!=null){
				 addressMatchBPId = bpmatchResponse.getAddressMatchBpId();
			}
			
			//Mapping BpMatch call response to method response
			if(null != bpmatchResponse.getBpPastServiceHistoryDTO()){
				bpMatchDto.setMatchedPartnerID(bpmatchResponse.getBpPastServiceHistoryDTO().getMatchedPartnerId());
				response.setMatchedBP(bpmatchResponse.getBpPastServiceHistoryDTO().getMatchedPartnerId());}
			
			//Mapping BpMatch call response to method response for address matched BpId
			if(!StringUtils.isEmpty(bpmatchResponse.getAddressMatchBpId())) {
				bpMatchDto.setMatchedPartnerID(bpmatchResponse.getAddressMatchBpId());
				response.setMatchedBP(bpmatchResponse.getAddressMatchBpId());
				response.setBpMatchFlag(StringUtils.EMPTY);
			}

			/**** CASE 0: CCS returns the restricted flag as X show hard stop[ page enrollment and proceed further with the OE flow. 
			 *****/
			//Start US23696 || Recognize BP Restrictions In Affiliate API || kdeshmukh || 15/12/2019
			if(StringUtils.equalsIgnoreCase(bpmatchResponse.getBpMatchRestrictedFlag(), X_VALUE)){
				errorCd = BP_RESTRICT;
				response.setBpMatchFlag(errorCd);
				response.setMessageCode(BP_RESTRICTION);
				response.setStatusCode(STATUS_CODE_STOP);
				response.setMessageText(getAllBrandResponseMessage(companyCode, brandID, BP_RESTRICTION_TEXT_MESSAGE, ""));
			}
			//END US23696 || Recognize BP Restrictions In Affiliate API || kdeshmukh || 15/12/2019
			//NO BPMATCH FLAG
			else if(null!=bpmatchResponse.getBpNoMatchFlag() && bpmatchResponse.getBpNoMatchFlag().equals(X_VALUE)) {
				logger.debug(" CCS returns the flag NO_BPMATCH as true");
				errorCd = EMPTY;
				response.setBpMatchFlag(EMPTY);
			}						
			
			//Past Balance:
			else if(bpmatchResponse.getBalanceFlag().equals(X_VALUE))
			{   logger.debug("inside validatePosId:: after bpMatchCall :: Past Balance found");							
			response.setBpMatchFlag(BPSD);
			errorCd=BPSD;
			response.setMessageCode(PAST_BALANCE);
			messageCode=PAST_BALANCE;
			response.setStatusCode(STATUS_CODE_STOP);
			response.setMessageText(msgSource.getMessage(BP_MATCH_PAST_BALANCE_MSG_TXT));
			
			}

			//Current Customer
			else if(bpmatchResponse.getActiveCustomerFlag().equals(X_VALUE))
			{
				response.setBpMatchFlag(EMPTY);
				response.setMessageCode(CURRENT_CUSTOMER);
				messageCode=CURRENT_CUSTOMER;
				response.setStatusCode(STATUS_CODE_STOP);
				String currentCustomer=null;
				
				if(null!=bpmatchResponse.getBpActiveCustomerDTO() && null!=bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO()){
					response.setExistingCity(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrCity());
					response.setExistingState(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrState());
					response.setExistingStreetAddress(CommonUtil.getAddressLine1(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrStreetNum(),
							bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrStreetName()));
					response.setExistingZip(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrZip());
					response.setExistingAptNum(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrUnitNumber());
					currentCustomer=CommonUtil.getCompleteAddress(bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrUnitNumber(),
							bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrStreetNum(),
							bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrStreetName(),
							bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrCity(),
							bpmatchResponse.getBpActiveCustomerDTO().getActiveAddressDTO().getStrZip());
				}
				response.setMessageText(msgSource.getMessage(BP_MATCH_CURRENT_CUSTOMER_MSG_TXT)+" "+currentCustomer);
			}
			
			//exact match in ccs
			else if(null!=addressMatchBPId && !EMPTY.equals(addressMatchBPId)) {
				logger.debug("CCS returns the addressMatchBPId as not empty ");
				errorCd = EMPTY;
				response.setBpMatchFlag(EMPTY);
			}

			//Past History Check
			else if(bpmatchResponse.getBpYesMatchFlag().equals(X_VALUE))
			{
				if(bpmatchResponse.getBpPastServiceHistoryDTO() != null)
				{
					if(null==bpmatchResponse.getBpPastServiceHistoryDTO().getMatchedPartnerId() ||
							(bpmatchResponse.getBpPastServiceHistoryDTO().getMatchedPartnerId()).equals(EMPTY))
					{
						response.setBpMatchFlag(BPSD);
						errorCd=BPSD;
						logger.debug("inside performBpMatch:: just bpsd scenario");
					}
	
					else if(null==bpmatchResponse.getBpPastServiceHistoryDTO().getUniqueAddressNumber() ||
							bpmatchResponse.getBpPastServiceHistoryDTO().getUniqueAddressNumber().equals(EMPTY)) {
						logger.debug(" BP MATCH: BP Number is returned but service address is empty ");
						errorCd = EMPTY;
						response.setBpMatchFlag(EMPTY);
					}
					
					else if(null!=bpmatchResponse.getBpPastServiceHistoryDTO().getUniqueAddressNumber() ||
							!(bpmatchResponse.getBpPastServiceHistoryDTO().getUniqueAddressNumber()).equals(EMPTY))
					{
						logger.debug(" BP MATCH: Past Service History Found");
						response.setBpMatchFlag(BPSD);
						errorCd=BPSD;
						response.setStatusCode(STATUS_CODE_ASK);
						bpMatchDto.setAddressMatchFlag(X_VALUE);
						response.setMessageCode(PAST_SERVICE_HISTORY);
						messageCode=PAST_SERVICE_HISTORY;
						String pastHistoryAddress=null;
						
						logger.debug("inside performBpMatch:: past service history scenario");
						
						if(null!=bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO()){
							response.setExistingCity(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrCity());
							response.setExistingState(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrState());
							response.setExistingStreetAddress(CommonUtil.getAddressLine1(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrStreetNum(),
											bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrStreetName()));
							response.setExistingZip(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrZip());
							response.setExistingAptNum(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrUnitNumber());
							//pass past history address in message text
							pastHistoryAddress=CommonUtil.getCompleteAddress(bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrUnitNumber(),
									bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrStreetNum(),
									bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrStreetName(),
									bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrCity(),
									bpmatchResponse.getBpPastServiceHistoryDTO().getPastAddressDTO().getStrZip());
						}	
						response.setMessageText(msgSource.getMessage(BP_MATCH_PAST_SERVICE_HISTORY_MSG_TXT)+" "+pastHistoryAddress);
					}
				} else {
					response.setBpMatchFlag(BPSD);
				}
			}
			else
			{
				logger.debug("inside performBpMatch:: none of above scenarios satisfied");
				//something wrong with CCS and none of the above expected scenarios are fulfilled 
				response.setBpMatchFlag(BPSD);
				errorCd=BPSD;
				response.setStatusCode(STATUS_CODE_CONTINUE);
				response.setResultDescription("BPMATCH CALL FAILED and continuing with BPSD flow");

			}
			
			bpMatchDto=populateBPMatchDTOFromBpMatchCCSResponse(bpMatchDto, bpmatchResponse);
			
			
		}
		catch(Exception e)
		{
			logger.error("inside validatePosId:: Bpmatch call failed :: ", e);
			response.setBpMatchFlag(BPSD);
			errorCd=BPSD;
			response.setStatusCode(STATUS_CODE_CONTINUE);
			response.setResultDescription("BPMATCH CALL FAILED and continuing with error :: "+e.getMessage());
			
		}
		finally{

			performBpMatchResponse.put("response", response);
			performBpMatchResponse.put("messageCode", messageCode);
			performBpMatchResponse.put("errorCd", errorCd);
			performBpMatchResponse.put("bpMatchDTO", bpMatchDto);}

		return performBpMatchResponse;
	}
	
	/**
	 * Method populateBPMatchDTOFromBpMatchCCSResponse.
	 * @param bpMatchDTO BPMatchDTO
	 * @param bpMatchCCSResponse BpMatchCCSResponse
	 * @return BPMatchDTO
	 */
	private com.multibrand.dto.BPMatchDTO populateBPMatchDTOFromBpMatchCCSResponse(com.multibrand.dto.BPMatchDTO bpMatchDTO, 
			BpMatchCCSResponse bpMatchCCSResponse){
		logger.debug("populateBPMatchDTOFromBpMatchCCSResponse() Start" );
	 	//addressMatchFlag - Set in code based on logic
		String activeCustomerFlag = bpMatchCCSResponse.getActiveCustomerFlag();
		if (null != activeCustomerFlag && !(activeCustomerFlag.equals(EMPTY))) {
			if ((activeCustomerFlag.equalsIgnoreCase("Yes")
					|| activeCustomerFlag.equalsIgnoreCase("Y") || activeCustomerFlag
						.equalsIgnoreCase(X_VALUE))) {
				activeCustomerFlag = X_VALUE;
			} else {
				activeCustomerFlag = O_VALUE;
			}
		}
		
		String addressMatchFlag = bpMatchDTO.getAddressMatchFlag();
		if (null != addressMatchFlag && !(addressMatchFlag.equals(EMPTY))) {
			if ( addressMatchFlag
						.equalsIgnoreCase(X_VALUE)) {
				addressMatchFlag = X_VALUE;
			} else {
				addressMatchFlag = O_VALUE;
			}
		}
		bpMatchDTO.setAddressMatchFlag(addressMatchFlag);
	 	bpMatchDTO.setActiveCustomerFlag(activeCustomerFlag); 
	 	
	 	
	 	String pendingBalanceFlag = bpMatchCCSResponse.getBalanceFlag();
		if (null != pendingBalanceFlag && !(pendingBalanceFlag.equals(EMPTY))) {
			if ((pendingBalanceFlag.equalsIgnoreCase("Yes")
					|| pendingBalanceFlag.equalsIgnoreCase("Y") || pendingBalanceFlag
						.equalsIgnoreCase(X_VALUE))) {
				pendingBalanceFlag = X_VALUE;
			} else {
				pendingBalanceFlag = O_VALUE;
			}
		}
		bpMatchDTO.setPendingBalanceFlag(pendingBalanceFlag);
	 	if(bpMatchCCSResponse.getBpActiveCustomerDTO() != null){
	 		bpMatchDTO.setBpActiveContract(bpMatchCCSResponse.getBpActiveCustomerDTO().getActiveContractNumber());
	 	}
	 	
	 	String addressSearchPerformed = bpMatchCCSResponse.getAddressChkPerformed();
		if (null != addressSearchPerformed
				&& !(addressSearchPerformed.equals(EMPTY))) {
			if ((addressSearchPerformed.equalsIgnoreCase("Yes")
					|| addressSearchPerformed.equalsIgnoreCase("Y") || addressSearchPerformed
						.equalsIgnoreCase(X_VALUE))) {
				addressSearchPerformed = X_VALUE;
			} else {
				addressSearchPerformed = O_VALUE;
			}
		}
	 	bpMatchDTO.setAddressSearchPerformed(addressSearchPerformed);
	 	String errorCode = bpMatchCCSResponse.getReturnCode();
	 	
	 	logger.debug("populateBPMatchDTOFromBpMatchCCSResponse() errorCode:" +errorCode);
	 	
	 	if(null != errorCode && !errorCode.equals("00") ){
	 		logger.debug("ERROR: CCS is taking longer than 60 seconds or ERROR CODE FROM CCS : "+errorCode );
	 		bpMatchDTO.setBpMatchNoCCSResponse(X_VALUE);
	 	}
		
	 	logger.debug("SignUpServiceHelper::populateBPMatchDTOFromBpMatchCCSResponse() bpMatchDTO values" );
	 	logger.debug("ActiveCustomerFlag:" +bpMatchDTO.getActiveCustomerFlag());
	 	logger.debug("PendingBalanceFlag:" +bpMatchDTO.getPendingBalanceFlag());
	 	logger.debug("BpActiveContract:" +bpMatchDTO.getBpActiveContract());
	 	logger.debug("AddressSearchPerformed:" +bpMatchDTO.getAddressSearchPerformed());
	 	logger.debug("BpMatchNoCCSResponse:" +bpMatchDTO.getBpMatchNoCCSResponse());
	 
	 	
	 	logger.debug("populateBPMatchDTOFromBpMatchCCSResponse() end" );
	 	
		return bpMatchDTO;
		
	}
	
	public Map<String,Object> getPosIdTokenResponse(String tdl,
			String ssn,String affiliateId,String trackingId){
		logger.debug("inside performPosidAndBpMatch:: affiliate Id : "+affiliateId +":: inside if loop");
		//Make Token call 
		TokenRequestVO tokenRequest= new TokenRequestVO();
		Map<String,Object> getPosIdTokenResponse=new HashMap<String,Object>();
		
		TokenizedResponse tokenResponse=new TokenizedResponse();
		String tokenTdl=null;
		String tokenSSN=null;
		try{
			if(StringUtils.isBlank(ssn) && StringUtils.isBlank(tdl))
			{  //If DL & SSN are passed empty
				tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				tokenResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
				tokenResponse.setResultDescription("DL and SSN are empty");
				getPosIdTokenResponse.put("tokenResponse", tokenResponse);
				return getPosIdTokenResponse;
				
			}
			if(StringUtils.isNotBlank(ssn))
			{   logger.debug("inside performPosidAndBpMatch:: affiliate Id : "+affiliateId +":: setting ssn action ");
			
			
			//If SSN or DL is not of expected size the append zeroes
			if(ssn.length()<9)
				ssn=CommonUtil.addLeadingZeroes(ssn, 9);
			
				boolean isValidSSN=true;
				isValidSSN=CommonUtil.isValidFormat(ssn, validationFormatEnum.SSN);
				logger.debug("inside performPosidAndBpMatch:: isValidSSN value is :: "+isValidSSN);
				if(!isValidSSN)
				{
					logger.debug("inside performPosidAndBpMatch:: isValidSSN is "+isValidSSN);
					tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					tokenResponse.setResultDescription("SSN: in incorrect format");
					tokenResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
					logger.debug("Inside peformPosidAndBpMatch :: tracking is:: "+trackingId+":: affiliateId ::"+affiliateId+" SSN in invalid format");
					getPosIdTokenResponse.put("tokenResponse", tokenResponse);
					return getPosIdTokenResponse;
				}
				logger.debug("inside peformPosidAndBpMatch:: isValidSSN value is :: "+isValidSSN);
			
			tokenRequest.setActionCode(Constants.ACTION_CODE_SSN_ACTION);
			tokenRequest.setNumToBeTokenized(ssn);
			tokenResponse=getTokenResponse(tokenRequest);
			tokenSSN=tokenResponse.getReturnToken();
			}
			if( StringUtils.isNotBlank(tdl)){
			logger.debug("inside performPosidAndBpMatch:: affiliate Id : "+affiliateId +":: setting DL action ");
			//IF SSN or DL is not of expected size the append zeroes
			if(tdl.length()<10)
			  tdl=CommonUtil.addLeadingZeroes(tdl, 10);
			
				boolean isValidTDL=true;
				isValidTDL=CommonUtil.isValidFormat(tdl, validationFormatEnum.TDL);
				logger.debug("inside performPosidAndBpMatch:: isValidTDL value is :: "+isValidTDL);
				if(!isValidTDL)
				{
					logger.debug("inside performPosidAndBpMatch:: isValidTDL is "+isValidTDL);
					tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					tokenResponse.setResultDescription("TDL:  in incorrect format");
					tokenResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
					
					logger.debug("Inside peformPosidAndBpMatch :: tracking is:: "+trackingId+":: affiliateId ::"+affiliateId+" TDL in invalid format");
					getPosIdTokenResponse.put("tokenResponse", tokenResponse);
					return getPosIdTokenResponse;
				}
			tokenRequest.setActionCode(Constants.ACTION_CODE_DL_ACTION);
			tokenRequest.setNumToBeTokenized(tdl);
			tokenResponse=getTokenResponse(tokenRequest);
			tokenTdl=tokenResponse.getReturnToken();
			}
		getPosIdTokenResponse.put("tokenTdl", tokenTdl);
		getPosIdTokenResponse.put("tokenSSN", tokenSSN);
		getPosIdTokenResponse.put("tokenResponse", tokenResponse);
	}
	catch(Exception e)
	{logger.error("Exception making token call :: ", e);
	
	//Token server returned exception
		logger.debug("inside performPosidAndBpMatch:: Tokenization call returned with failure result code ");
		tokenResponse.setErrorDescription("Tokenization call returned failure code");
		tokenResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
		getPosIdTokenResponse.put("tokenResponse", tokenResponse);
		getPosIdTokenResponse.put("tokenTdl", tokenTdl);
		getPosIdTokenResponse.put("tokenSSN", tokenSSN);
	
	}
	
		return getPosIdTokenResponse;
	}
	
	
	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bpid
	 * @param bankAccountNumber
	 * @param bankRoutingNumber
	 * @param paymentAmount
	 * @param paymentDate
	 * @param companyCode
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @param sessionId
	 * @param brandName
	 * @param emailTypeId
	 * @return PayByBankResponse
	 */
	public BankDepositPaymentResponse submitBankPayment(BankDepositPaymentRequest paymentRequest ,
			String sessionId) {
		
		String accountNumber = paymentRequest.getCaNumber();
		String bpid = paymentRequest.getBpid();
		String bankAccountNumber = paymentRequest.getTokenizedBankAccountNumber();
		String bankRoutingNumber = paymentRequest.getTokenizedBankRoutingNumber();
		String paymentAmount = paymentRequest.getDepositAmount();		
		String companyCode = paymentRequest.getCompanyCode();
		String brandId = paymentRequest.getBrandId();

		PayByBankRequest request = new PayByBankRequest();
		request.setStrBankAccNumber(bankAccountNumber);
		request.setStrBPNumber(bpid);
		request.setStrBankRoutingNumber(bankRoutingNumber);
		request.setStrPayAmount(paymentAmount);		
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		request.setStrPaymentDate(CommonUtil.getCurrentDateFormatted(DATE_FORMAT));

		BankDepositPaymentResponse bankPaymentResponse = new BankDepositPaymentResponse();

		try {
			com.multibrand.domain.PayByBankResponse response = paymentService
					.submitBankPayment(request, companyCode, sessionId, brandId);
			

			String confNumber = response.getStrOTBDId();
			if (confNumber != null && !confNumber.equalsIgnoreCase("")) {
				bankPaymentResponse.setConfirmationNumber(confNumber);
			}

			if (response.getErrorCode() != null
					&& !response.getErrorCode().equalsIgnoreCase("")) {												
				bankPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				bankPaymentResponse.setStatusCode(STATUS_CODE_STOP);
				bankPaymentResponse.setMessageCode(response.getErrorCode());
				bankPaymentResponse.setMessageText(response.getErrorMessage());
			} else {
				bankPaymentResponse.setResultCode(RESULT_CODE_SUCCESS);
				bankPaymentResponse.setStatusCode(STATUS_CODE_CONTINUE);
			}

		} catch (RemoteException e) {
			bankPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			bankPaymentResponse.setStatusCode(STATUS_CODE_STOP);
			bankPaymentResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			logger.error("Exception in BillingBO.submitBankPayment: ",e);
			throw new OAMException(200, e.getMessage(), bankPaymentResponse);

		} catch (Exception e) {
			bankPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			bankPaymentResponse.setStatusCode(STATUS_CODE_STOP);
			bankPaymentResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			logger.error("Exception in BillingBO.submitBankPayment: ",e);
			throw new OAMException(200, e.getMessage(), bankPaymentResponse);
		} finally {
			// Call update service location
			this.updateServiceLocation(paymentRequest.getCompanyCode(), paymentRequest.getAffiliateId(),paymentRequest.getTrackingId(), paymentRequest.getDepositAmount(), bankPaymentResponse);
		}

		return bankPaymentResponse;

	}
	
	/***
	 * 
	 * Updates service location details for get esid and calendar dates
	 * 
	 * @param affiliateId
	 * @param trackingId
	 * @param companyCode
	 * @param depositAmount
	 * @param response
	 * 
	 * @author athirug1
	 */
	private void updateServiceLocation(String companyCode, String affiliateId, String trackingId, 
			String depositAmount, BankDepositPaymentResponse response) {
		logger.debug("Processing updateServiceLocation ...");
		Assert.notNull(Integer.parseInt(trackingId),
				"trackingId must not be null.");
		UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();

		requestData.setTrackingId(trackingId);
		requestData.setRecentCallMade(SUBMIT_BANK_DEPOSIT_PAYMENT);			

		requestData.setCompanyCode(companyCode);
		requestData.setPayCode(YES);
		
			/* Setting service addresses */			

			if (StringUtils.isNotEmpty(response.getMessageCode())) {
				requestData.setMessageCode(response.getMessageCode());
				requestData.setDepositCode(DEPOSIT_FAIL);
				requestData.setDepositAmount(depositAmount);
			} else { 
				requestData.setDepositAmount(depositAmount);
				requestData.setDepositCode(DEPOSIT_PAID);
				requestData.setMessageCode(" ");
			}
			requestData.setAffiliateId(affiliateId);

			/* Updating service location affiliate table */
			String errorCode = this.updateServiceLocation(requestData);
			if (StringUtils.isNotBlank(errorCode))
				logger.debug("Finished processing updateServiceLocation, errorCode = "
						+ errorCode);		
	}
	
	/**
	 * @param authType
	 * @param accountNumber
	 * @param bpid
	 * @param ccNumber
	 * @param cvvNumber
	 * @param expirationDate
	 * @param billingZip
	 * @param paymentAmount
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @param companyCode
	 * @param sessionId
	 * @param paymentDate
	 * @param brandName
	 * @param emailTypeId
	 * @return
	 */
	public CCDepositPaymentResponse submitCCPayment(CCDepositPaymentRequest paymentRequest, String sessionId) {

		logger.debug("BillingBO.submitCCPayment :: START");
		String accountNumber = paymentRequest.getCaNumber();
		String bpid = paymentRequest.getBpid();
		String paymentAmount = paymentRequest.getDepositAmount();	
		String companyCode = paymentRequest.getCompanyCode();
		String brandId = paymentRequest.getBrandId();		
		String tokenizedCCNumber = paymentRequest.getTokenizedCCNumber();
		String cvvNumber = paymentRequest.getCvvNumber();
		String expirationDate = paymentRequest.getExpirationDate();
		String billingZip = paymentRequest.getBillingZip();
		
				
		com.multibrand.domain.PayByCCRequest request = new com.multibrand.domain.PayByCCRequest();

		request.setStrBPNumber(bpid);
		request.setStrCANumber(accountNumber);
		request.setStrCCNumber(tokenizedCCNumber);
		if (cvvNumber != null)
			request.setStrCVVNumber(cvvNumber);
		request.setStrDuplicatePayment("X");
		request.setStrExpirationDate(expirationDate);
		request.setStrPayAmount(paymentAmount);
		request.setStrBillingZip(billingZip);

		CCDepositPaymentResponse ccDepositResponse = new CCDepositPaymentResponse();

		try {
			com.multibrand.domain.PayByCCResponse response = paymentService
					.submitCCPayment(request, companyCode, sessionId, brandId);			

			String confNumber = response.getStrXValidNum();
			if (confNumber != null && !confNumber.equalsIgnoreCase("")) {
				ccDepositResponse.setConfirmationNumber(confNumber);
			}

			if (response.getErrorCode() != null
					&& !response.getErrorCode().equalsIgnoreCase("")) {
				ccDepositResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				ccDepositResponse.setStatusCode(STATUS_CODE_STOP);	
				ccDepositResponse.setMessageCode(response.getErrorCode());
				ccDepositResponse.setMessageText(response.getErrorMessage());
			} else {
				ccDepositResponse.setResultCode(RESULT_CODE_SUCCESS);
				ccDepositResponse.setStatusCode(STATUS_CODE_CONTINUE);
			}

		} catch (RemoteException e) {
			ccDepositResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			ccDepositResponse.setStatusCode(STATUS_CODE_STOP);
			ccDepositResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			logger.error("Exception in BillingBO.submitCCPayment: ",e);
			throw new OAMException(200, e.getMessage(), ccDepositResponse);
		} catch (Exception e) {
			ccDepositResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			ccDepositResponse.setStatusCode(STATUS_CODE_STOP);
			ccDepositResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			logger.error("Exception in BillingBO.submitCCPayment: ",e);
			throw new OAMException(200, e.getMessage(), ccDepositResponse);
		}finally {
			this.updateServiceLocation(paymentRequest.getCompanyCode(), paymentRequest.getAffiliateId(),paymentRequest.getTrackingId(), paymentRequest.getDepositAmount(), ccDepositResponse);
		}

		logger.debug("BillingBO.submitCCPayment :: END");
		return ccDepositResponse;

	}
	
	/***
	 * 
	 * Updates service location details for get esid and calendar dates
	 * 
	 * @param affiliateId
	 * @param trackingId
	 * @param companyCode
	 * @param depositAmount
	 * @param response
	 * 
	 * @author athirug1
	 */
	private void updateServiceLocation(String companyCode, String affiliateId, String trackingId, 
			String depositAmount, CCDepositPaymentResponse response) {
		logger.debug("Processing updateServiceLocation ...");
		Assert.notNull(Integer.parseInt(trackingId),
				"trackingId must not be null.");
		UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();

		requestData.setTrackingId(trackingId);
		requestData.setRecentCallMade(SUBMIT_CC_DEPOSIT_PAYMENT);			

		requestData.setCompanyCode(companyCode);
		requestData.setPayCode(YES);			

			/* Setting service addresses */			

		if (StringUtils.isNotEmpty(response.getMessageCode())) {
			requestData.setMessageCode(response.getMessageCode());
			requestData.setDepositCode(DEPOSIT_FAIL);
			requestData.setDepositAmount(depositAmount);
		} else { 
			requestData.setDepositAmount(depositAmount);
			requestData.setDepositCode(DEPOSIT_PAID);
			requestData.setMessageCode(" ");
		}
		
		requestData.setAffiliateId(affiliateId);

		/* Updating service location affiliate table */
		String errorCode = this.updateServiceLocation(requestData);
		if (StringUtils.isNotBlank(errorCode))
			logger.debug("Finished processing updateServiceLocation, errorCode = "
					+ errorCode);		
	}
	
	/**
	 *Populates size boundary to be checked parameters for Submit Enrollment.
	 * 
	 * 
	 * @param enrollmentRequest
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private Map<String, Object> buildEnrollmentValidationSizeParams(
			EnrollmentRequest enrollmentRequest){
		
		Map<String, Object> sizeParamList = new HashMap<String, Object>();
		sizeParamList.put("servState", enrollmentRequest.getServState());
		sizeParamList.put("billState", enrollmentRequest.getBillState());
		
		return sizeParamList;
	}
	/**
	 * Populates size boundary to be checked parameters for Perform credit check.
	 * 
	 * @param billState
	 * @param servState
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private Map<String, Object> buildStateSizeParams(String billState, String servState)
	{
		/* Author Mayank Mishra */
		Map<String, Object> sizeParamList = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(servState))
			sizeParamList.put("servState", servState);
		if (StringUtils.isNotBlank(billState))
			sizeParamList.put("billState", billState);
		
		return sizeParamList;
	}

	/***
	 * 
	 * Updates service location details for get esid and calendar dates
	 * 
	 * @param affiliateId
	 * @param trackingId
	 * @param companyCode
	 * @param serviceAddressDO
	 * @param esidDo
	 * @param response
	 * 
	 * @author jyogapa1
	 */
	private void updateServiceLocation(String companyCode, String affiliateId, String trackingId, 
			AddressDO serviceAddressDO, ESIDDO esidDo,
			EsidInfoTdspCalendarResponse response,String requestEsidNumber) {
		logger.debug("Processing updateServiceLocation ...");
		Assert.notNull(Integer.parseInt(trackingId),
				"trackingId must not be null.");
		UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();

		requestData.setTrackingId(trackingId);

		String personId = getPersonIdByTrackingNo(requestData.getTrackingId());

		// Update service location affiliate table only when a valid person id
		// is returned from getPersonIdByTrackingNo

		if (StringUtils.isNotEmpty(personId)) {
			requestData.setRecentCallMade(ESID_CALENDAR_DATES);
			
			if(!StringUtils.isEmpty(requestEsidNumber)) {
				requestData.setEsid(requestEsidNumber);
				requestData.setEsidMatchFlag("2");	
				logger.info("EsidMatchFlag :"+requestData.getEsidMatchFlag());
			} else if (esidDo != null
					&& StringUtils.isNotBlank(esidDo.getEsidNumber())&&(!StringUtils.equalsIgnoreCase(esidDo.getEsidNumber(), NESID))) {
				requestData.setEsid(esidDo.getEsidNumber());
				requestData.setEsidMatchFlag("Y");
				logger.info("EsidMatchFlag is set as 1  EsidNumber:"+esidDo.getEsidNumber());
				if (StringUtils.isNotBlank(esidDo.getEsidStatus()))
					requestData.setEsidStatus(esidDo.getEsidStatus());
				if (StringUtils.isNotBlank(esidDo.getMeterType()))
					requestData.setMeterType(esidDo.getMeterType());
					
				
			} else {
				requestData.setEsidMatchFlag("N");
				logger.info("EsidMatchFlag is set as 0");
			}

			requestData.setCompanyCode(companyCode);
			if (StringUtils.isNotBlank(response.getMessageCode()))
				requestData.setMessageCode(response.getMessageCode());

			/* Setting service addresses */
			requestData.setServStreetNum(serviceAddressDO.getStrStreetNum());
			requestData.setServStreetName(serviceAddressDO.getStrStreetName());
			if (StringUtils.isNotEmpty(serviceAddressDO.getStrApartNum()))
				requestData.setServStreetAptNum(serviceAddressDO
						.getStrApartNum());
			requestData.setServCity(serviceAddressDO.getStrCity());
			requestData.setServState(serviceAddressDO.getStrState());
			requestData.setServZipCode(serviceAddressDO.getStrZip());

			if (StringUtils.isNotEmpty(response.getErrorCode()))
				requestData.setErrorCode(response.getErrorCode());
			if (StringUtils.isNotEmpty(response.getTdspCode()))
				requestData.setTdspCode(response.getTdspCode());
			requestData.setAffiliateId(affiliateId);

			if (StringUtils.isNotEmpty(response.getSwitchHoldFlag()))
				requestData.setSwitchHoldStatus(response.getSwitchHoldFlag());

			/* Updating service location affiliate table */
			String errorCode = this.updateServiceLocation(requestData);
			if (StringUtils.isNotBlank(errorCode))
				logger.debug("Finished processing updateServiceLocation, errorCode = "
						+ errorCode);
		}
	}

	/**
	 * Gets tdsp calendar dates.
	 * 
	 * @param companyCode
	 * @param trackingId
	 * @param transactionType
	 * @param tdspCodeCCS
	 * @param bpMatchFlag
	 * @param esidDo
	 * @param response
	 * @param locale
	 * @throws Exception 
	 */
	private void getTdspDates(String companyCode, String trackingId,
			String transactionType, String tdspCodeCCS, String bpMatchFlag,
			ESIDDO esidDo, EsidInfoTdspCalendarResponse response, Locale locale) throws Exception {
		String METHOD_NAME = "OEBO: getTdspDates(..)";

		logger.debug("Start:" + METHOD_NAME);
		String internaltdspCodeCCS="";
		
		if (esidDo != null && StringUtils.isNotEmpty(esidDo.getEsidTDSP())) {
			internaltdspCodeCCS = this.appConstMessageSource.getMessage(esidDo.getEsidTDSP(), null, null);
		}

		// TDSP mismatch scenario
		if ((StringUtils.isNotEmpty(tdspCodeCCS)
				&& StringUtils.isNotEmpty(internaltdspCodeCCS))
			&& !tdspCodeCCS.equalsIgnoreCase(internaltdspCodeCCS))
		{
			logger.debug("ERROR:" + METHOD_NAME);
			response.setResultCode(RESULT_CODE_SUCCESS);
			//response.setResultDescription(RESULT_DESCRIPTION_TDSP_MISMATCH);
			response.setStatusCode(STATUS_CODE_STOP);
			response.setErrorCode(TDSPSD);
			response.setTdspCode(internaltdspCodeCCS);
			response.setAvailableDates(EMPTY);
			response.setTdspFee(EMPTY);
			response.setMessageCode(MESSAGE_CODE_TDSP_MISMATCH);
			response.setMessageText(this.msgSource.getMessage(MESSAGE_CODE_TDSP_MISMATCH, null, locale));
			return;
		}

    	OetdspRequest request = new OetdspRequest();
    	DateFormat df = new SimpleDateFormat(MM_dd_yyyy);
		Calendar c = Calendar.getInstance();
		request.setTdsp(tdspCodeCCS);
		request.setStartDate(df.format(c.getTime()));
		request.setEndDate(df.format(DateUtils.addDays(c.getTime(), 59)));
		request.setStrCompanyCode(companyCode);
		//START : ALT Channel : Sprint6 :US7569 :Kdeshmu1
		if(StringUtils.isNotEmpty(esidDo.getEsidNumber())){
			request.setEsiid(esidDo.getEsidNumber());
		}else{
			request.setEsiid("");
		}
		//END : ALT Channel : Sprint6 :US7569 :Kdeshmu1
		// Get calendar specific days for tdspCodeCCS
    	String dateString = oeService.getTDSPSpecificCalendarDates(request, trackingId);
		List<String> allInclusiveDateList = CommonUtil.getDaysInBetween(c.getTime(),DateUtils.addDays(c.getTime(), 59));
    	if (allInclusiveDateList == null) {
    		response.setAvailableDates(EMPTY);
    		return;
    	}

	   	if (StringUtils.isNotBlank(dateString))
	   	{
	   		String[] strValues = StringUtils.split(dateString, '|');
	   		ArrayList<String> holidayDates = new ArrayList<String>(Arrays.asList(strValues));
	   		allInclusiveDateList.removeAll(holidayDates);
	   	}

    	String availableDatesNoFwdSlash = EMPTY;
    	if (allInclusiveDateList != null) {
	   		// Logic to remove dates based on below conditions:
	   		//	Include 2:00PM cut-off logic as an initial "default" check.
	   		//	If 'ESID Not Found' or 'SwitchHold' or 'BPSD' then do not check Meter Type, push the available date 4 days out 
	   		//	If there is no exception, then check meter type.
	   		//  If meterType='AMSR' then first available date is today, for all other meter Types, push out the date by 2 days.
    		
    		Calendar c2 = Calendar.getInstance();
    		c2.set(Calendar.HOUR_OF_DAY, 14);
    		c2.set(Calendar.MINUTE, 00);
    		c2.set(Calendar.SECOND, 00);
    		c2.set(Calendar.MILLISECOND, 00);
    		DateFormat df2 = new SimpleDateFormat(MM_dd_yyyy);
    		//START : ALT Channel : Sprint6 :US7569 :Kdeshmu1
    		/**if (c.getTimeInMillis() > c2.getTimeInMillis())
    			allInclusiveDateList.remove(df2.format(c.getTime()));**/
    		
    		Calendar c3 = Calendar.getInstance();
    		c3.set(Calendar.HOUR_OF_DAY, 17);
    		c3.set(Calendar.MINUTE, 00);
    		c3.set(Calendar.SECOND, 00);
    		c3.set(Calendar.MILLISECOND, 00);
    		
    		if (c.getTimeInMillis() > c3.getTimeInMillis()&& (StringUtils.equals(response.getMeterType(),METER_TYPE_AMSR)))
    		{
    			allInclusiveDateList.remove(df2.format(c.getTime()));
    		}
    		
    		else if(c.getTimeInMillis() > c2.getTimeInMillis()&& (!StringUtils.equals(response.getMeterType(),METER_TYPE_AMSR)))
    		{
    			allInclusiveDateList.remove(df2.format(c.getTime()));
    		}
    		//END  : ALT Channel : Sprint6 :US7569 :Kdeshmu1
    		if (allInclusiveDateList.size() > 0 // List still has data
    				&& (StringUtils.isBlank(response.getEsid()) // Blank ESID means no ESID found
    						|| StringUtils.equals(esidDo.getSwitchHoldStatus(),SWITCH_HOLD_STATUS_ON)) // Switch Hold Status On
    						|| (StringUtils.equals(bpMatchFlag,BPSD))) // BPSD true
    		{
    			// Remove first 4 available dates
    			for (int i = 0; i < PUSH_4; i++)
    				allInclusiveDateList.remove(0);

    		} else if (allInclusiveDateList.size() > 0 // List still has data
    				&& !StringUtils.equals(response.getMeterType(), METER_TYPE_AMSR)) // Meter type != AMSR
    		{
    			// Remove first 2 available dates
    			for (int i = 0; i < PUSH_2; i++)
    				allInclusiveDateList.remove(0);
    		}

    		String availableDates = StringUtils.join(allInclusiveDateList, SEMI_COLON);
    		availableDatesNoFwdSlash = StringUtils.replace(availableDates, FWD_SLASH , EMPTY);
    	}
    	
    	response.setAvailableDates(availableDatesNoFwdSlash);
    	response.setTdspCode(tdspCodeCCS);
    	if (StringUtils.isNotEmpty(tdspCodeCCS)	&& StringUtils.isNotEmpty(transactionType))
    	{
    		String keyForTdspFee = EMPTY;
    		String meterType = EMPTY;
    		if (!StringUtils.equals(response.getMeterType(),METER_TYPE_AMSR))
    		{
    			meterType = METER_TYPE_NON_AMSR;
    		} else{
    			meterType = response.getMeterType();
    		}
    		keyForTdspFee = meterType + UNDERSCORE + tdspCodeCCS + UNDERSCORE + transactionType;
    		response.setTdspFee(this.msgSource.getMessage(keyForTdspFee, null, locale));
    	} else {
    		response.setTdspFee(EMPTY);
    	}
    	
    	logger.debug("End:" + METHOD_NAME);
	}
	
	public AffiliateOfferResponse getAffiliateOffers(AffiliateOfferRequest request, String sessionId) {
		
		AffiliateOfferResponse response = new AffiliateOfferResponse();
		
		boolean isReactiveOffersEnabled = togglzUtil.getFeatureStatusFromTogglzByChannel(TOGGLZ_FEATURE_DEFAULT_REACTIVE_OFFER, request.getChannelType());
		
	if(StringUtils.isBlank(request.getPromoCode()) && !isReactiveOffersEnabled)
		{  //If Promo code is passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("promoCode may not be Empty");
			response.setErrorCode(HTTP_BAD_REQUEST);
			response.setErrorDescription(response.getResultDescription());
			response.setHttpStatus(Response.Status.BAD_REQUEST);
			return response;	
		}
		
		if(StringUtils.isBlank(request.getTdspCodeCCS()) && StringUtils.isBlank(request.getEsid()))
		{  //If Tdsp Code & Esid are passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("Tdsp Code and Esid are empty");
			response.setErrorCode(HTTP_BAD_REQUEST);
			response.setErrorDescription(response.getResultDescription());
			response.setHttpStatus(Response.Status.BAD_REQUEST);
			return response;	
		}
		
		if(!StringUtils.equals(request.getCompanyCode(), COMPANY_CODE_RELIANT) && !StringUtils.equals(request.getCompanyCode(), COMPANY_CODE_GME) && !StringUtils.equals(request.getCompanyCode(), COMPANY_CODE_CIRRO))
		{  //If Tdsp Code & Esid are passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("Company code "+request.getCompanyCode()+" is currently not supported");		
			response.setErrorCode(HTTP_BAD_REQUEST);
			response.setErrorDescription(response.getResultDescription());
			response.setHttpStatus(Response.Status.BAD_REQUEST);
			return response;			
		}
		
		if(StringUtils.isNotBlank(request.getTdspCodeCCS()) && !isServicedTDSPCode(request.getTdspCodeCCS())) {
			response.setMessageCode(AREA_NOT_SERVICED);
			response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			response.setOfferDate(DateUtil.getCurrentDateFormatted(MMddyyyy));
			response.setOfferTime(DateUtil.getCurrentDateFormatted(TIME_FORMAT));
			return response;
		}
		
		OfferResponse offerResponse = getOffers(request.getLanguageCode(),
				request.getCompanyCode(), request.getBrandId(), null,
				null, null, null, request.getPromoCode(),
				request.getTdspCodeCCS(), request.getEsid(), sessionId, request.getTransactionType());
		
		logger.info("OfferResponse : strErrorCode : "+offerResponse.getStrErrorCode());
		
						
		if(StringUtils.equalsIgnoreCase(MSG_CCSERR_8_GET_PROMO_OFFERS, offerResponse.getStrErrorCode()) || StringUtils.equalsIgnoreCase(MSG_CCSERR_E_GET_PROMO_OFFERS, offerResponse.getStrErrorCode())) {
			
			if(! isReactiveOffersEnabled){
				response.setMessageCode(PROMO_INVALID);
				response.setMessageText(msgSource.getMessage(PROMO_INVALID_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
				response.setStatusCode(Constants.STATUS_CODE_STOP);
				response.setResultCode(Constants.RESULT_CODE_SUCCESS );
				response.setResultDescription("Failed -"+offerResponse.getStrErrorCode());
				response = constructMainFields(response,offerResponse);
				return response;
			} else {
				offerResponse = getOffers(request.getLanguageCode(),
						request.getCompanyCode(), request.getBrandId(), null,
						null, null, null, null,
						request.getTdspCodeCCS(), request.getEsid(), sessionId, request.getTransactionType());
				offerResponse.setStatusCode(Constants.STATUS_CODE_CONTINUE);
				offerResponse.setMessageCode(PROMO_INVALID);
				offerResponse.setMessageText(msgSource.getMessage(PROMO_INVALID_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
			}
		}
			
		if(StringUtils.isEmpty(offerResponse.getStrTDSPCode())|| !isServicedTDSPCode(offerResponse.getStrTDSPCode())) {
			response.setMessageCode(AREA_NOT_SERVICED);			
			response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			if(StringUtils.isEmpty(offerResponse.getStrErrorCode())) {
				response.setResultDescription("Failed");
			} else {
				response.setResultDescription("Failed -"+offerResponse.getStrErrorCode());
			}
			response = constructMainFields(response,offerResponse);
		} else{			
			response = constructAffiliateOfferResponse(offerResponse,request);
			
			boolean isCMSEnabled = togglzUtil.getFeatureStatusFromTogglzByBrandId(TOGGLZ_FEATURE_CMS_OFFER_DATA, request.getCompanyCode(), request.getBrandId());			
			if(isCMSEnabled && response.getAffiliateOfferList() != null & response.getAffiliateOfferList().length>0)
			{				
				List<AffiliateOfferDO> affiliateOfferList =  new ArrayList<AffiliateOfferDO>(Arrays.asList(response.getAffiliateOfferList())); 			
				String cmsErrorOffers = contentHelper.fillAndFilterSDLContentOffer(affiliateOfferList, request.getCompanyCode(), request.getBrandId(), request.getLanguageCode());
				response.setCmsErrorOffers(cmsErrorOffers);	
				response.setAffiliateOfferList(affiliateOfferList.toArray(new AffiliateOfferDO[affiliateOfferList.size()]));
			}
		}
		
		
		
		return response;
	}
	
	private AffiliateOfferResponse constructAffiliateOfferResponse(OfferResponse offerResponse,AffiliateOfferRequest request) {
		AffiliateOfferResponse response = new AffiliateOfferResponse();
		
		response = constructCommonFields(response,offerResponse);
		response = constructMainFields(response,offerResponse);
		StringBuffer erpErrorOffers = new StringBuffer();
		AffiliateOfferDO[] offerDOList = constructAffiliateOfferDOList(offerResponse,request, erpErrorOffers);
		String erpErrorOffersStr = erpErrorOffers.toString();
		erpErrorOffersStr = erpErrorOffersStr. replaceAll(DELIMETER_COMMA_REGEX, EMPTY);		
		response.setErpErrorOffers(erpErrorOffersStr);
		response.setAffiliateOfferList(offerDOList);
		return response;
	}
	
	private AffiliateOfferResponse constructCommonFields(AffiliateOfferResponse response,OfferResponse offerResponse ) {
		response.setErrorCode(offerResponse.getErrorCode());
		response.setErrorDescription(offerResponse.getErrorDescription());
		response.setResultCode(offerResponse.getResultCode());
		response.setResultDescription(offerResponse.getResultDescription());
		response.setStatusCode(offerResponse.getStatusCode());
		response.setMessageCode(offerResponse.getMessageCode());
		response.setMessageText(offerResponse.getMessageText());
		return response;
	}
	
	private AffiliateOfferResponse constructMainFields(AffiliateOfferResponse response,OfferResponse offerResponse ) {
		response.setTdspCodeCCS(offerResponse.getStrTDSPCode());		
		response.setOfferDate(CommonUtil.changeDateFormat(offerResponse.getOfferDate(), MM_dd_yyyy, MMddyyyy));
		response.setOfferTime(offerResponse.getOfferTime());
		return response;
	}	
	
	private AffiliateOfferDO[] constructAffiliateOfferDOList(
			OfferResponse offerResponse, AffiliateOfferRequest request,StringBuffer erpErrorOffers) {
		OfferDO[] offerDOArr = offerResponse.getOfferDOList();		
		ArrayList<AffiliateOfferDO> affiliateOfferDOList = new ArrayList<AffiliateOfferDO>();
		if (offerDOArr != null) {			
			int i = 0;
			for (OfferDO offerDO : offerDOArr) {
				
				AffiliateOfferDO affiliateOfferDO = new AffiliateOfferDO();
				affiliateOfferDO.setSapPlanName(offerDO.getStrPlanName());
				affiliateOfferDO.setSapOfferTagline(offerDO
						.getStrOfferCodeTitle());
				affiliateOfferDO.setOfferCode(offerDO.getStrOfferCode());
				affiliateOfferDO.setPromoCode(offerDO
						.getStrOfferCellTrackCode());
				affiliateOfferDO.setCampaignCode(offerDO
						.getStrCampaignCode());
				affiliateOfferDO.setProductPriceCode(offerDO
						.getStrProductPriceCode());
				affiliateOfferDO.setMarketSegment(offerDO
						.getStrMarketSegment());
				affiliateOfferDO.setIncentiveCode(offerDO
						.getStrIncentiveCode());
				String contractTerm = StringUtils.isEmpty(offerDO
						.getStrContractTerm()) ? ZERO : offerDO
						.getStrContractTerm();
				int intContractTerm = Integer.parseInt(contractTerm);

				if (intContractTerm > 1) {
					affiliateOfferDO.setPlanType(PLAN_TYPE_FIXED);
					affiliateOfferDO.setContractTerm(contractTerm);
				} else {
					affiliateOfferDO.setPlanType(PLAN_TYPE_VARIABLE);
					affiliateOfferDO.setContractTerm(ZERO);
				}

				String webURL = getWebURL(request.getCompanyCode(),
						request.getBrandId());

				affiliateOfferDO.setEflURL(webURL + CONST_FILES
						+ offerDO.getStrEFLDocID() + CONST_DOT_PDF);
				affiliateOfferDO.setTosURL(webURL + CONST_FILES
						+ offerDO.getStrTOSDocID() + CONST_DOT_PDF);
				affiliateOfferDO.setYraacURL(webURL + CONST_FILES
						+ offerDO.getStrYRAACDocID() + CONST_DOT_PDF);
				affiliateOfferDO.setCancelFee(offerDO.getStrCancelFee());
				affiliateOfferDO.setEflSmartCode(offerDO
						.getStrEFLSmartCode());
				affiliateOfferDO.setTosSmartCode(offerDO
						.getStrTOSSmartCode());
				affiliateOfferDO.setYraacSmartCode(offerDO
						.getStrYRAACSmartCode());

				
				//Start - Alt Channels US8596 | Pratyush
				affiliateOfferDO.setAverageEFLPrice1000(getAveragePriceEFL1000(offerDO));
				affiliateOfferDO.setAverageEFLPrice2000(getAveragePricEFL2000(offerDO));
				affiliateOfferDO.setAverageEFLPrice500(getAveragePriceEFL500(offerDO));
				affiliateOfferDO.setAutoPayDiscount(getAutoPayPrice(offerDO));
				affiliateOfferDO.setDigitalDiscount(getDigitalDiscountPrice(offerDO));
				//End Alt Channels US8596
				
				//Start - Alt Channels -- US14171 | Pratyush -- 11/13/2018
				affiliateOfferDO.setUsageCredit(getAveragePriceEUsageCR(offerDO));
				affiliateOfferDO.setCreditMaxUsageThreshold(getAveragePriceMaxThreshold(offerDO));
				affiliateOfferDO.setCreditMinUsageThreshold(getAveragePriceMinThreshold(offerDO));
				//End - Alt Channels -- US14171
				
				if (!StringUtils.equalsIgnoreCase(
						offerDO.getStrOfferCategory(), CATEGORY_TWW)) {
					affiliateOfferDO
							.setAveragePrice500(getAveragePrice500(offerDO));
					affiliateOfferDO
							.setAveragePrice1000(getAveragePrice1000(offerDO));
					affiliateOfferDO
							.setAveragePrice2000(getAveragePrice2000(offerDO));
				} else {
					affiliateOfferDO.setAveragePrice500(getKeyPrice(
							offerDO, EFL_1R0500));
					affiliateOfferDO.setAveragePrice1000(getKeyPrice(
							offerDO, EFL_1R1000));
					affiliateOfferDO.setAveragePrice2000(getKeyPrice(
							offerDO, EFL_1R2000));
				}

				affiliateOfferDO.setOfferCategory(offerDO
						.getStrOfferCategory());

				if (StringUtils.equalsIgnoreCase(
						affiliateOfferDO.getOfferCategory(),
						CONSERVATION_CATEGORY)) {

					affiliateOfferDO.setEnergyChargeText(msgSource
							.getMessage(
									CONSERVATION_ENERGY_CHARGE,
									new String[] {
											getEnergyCharge(offerDO,
													request.getCompanyCode()),
											getEnergyCharge2(offerDO) },
									CommonUtil.localeCode(request
											.getLanguageCode())));
				} else {
					affiliateOfferDO.setEnergyChargeText(msgSource
							.getMessage(
									NOT_CONSERVATION_ENERGY_CHARGE,
									new String[] { getEnergyCharge(offerDO,
											request.getCompanyCode()) },
									CommonUtil.localeCode(request
											.getLanguageCode())));
				}
				String energyCharge = getEnergyCharge(offerDO,
						request.getCompanyCode());
				String usageAmt = getKeyPrice(offerDO, LPP_CAP);
				
				String baseCharge = getBaseCharge(offerDO);
				
				if (!StringUtils.isEmpty(baseCharge)) {
					String baseChargeText = msgSource.getMessage(
							BASE_CHARGE_PER_MONTH, new String[] { baseCharge },
							CommonUtil.localeCode(request.getLanguageCode()));
					if (StringUtils.isEmpty(usageAmt)) {
						affiliateOfferDO
								.setBaseUsageChargeText(baseChargeText);
					} else {

						DecimalFormat decimalformat = new DecimalFormat("#0");
						usageAmt = decimalformat.format(Double
								.parseDouble(usageAmt));

						String usageChargeText = msgSource.getMessage(
								USAGE_CHARGE_PER_MONTH, new String[] {
										getKeyPrice(offerDO, S_CUSTCHR2),
										usageAmt }, CommonUtil
										.localeCode(request.getLanguageCode()));
						;
						affiliateOfferDO
								.setBaseUsageChargeText(baseChargeText + "; "
										+ usageChargeText);
					}

				} else {
					affiliateOfferDO
							.setBaseUsageChargeText(StringUtils.EMPTY);					
				}
				
				boolean validOffer = checkMandatoryFields(affiliateOfferDO, energyCharge);
				if(!validOffer){
					erpErrorOffers.append(affiliateOfferDO.getOfferCode()+DELIMETER_COMMA);
					continue;
				}
				
				if(StringUtils.equals(request.getCompanyCode(), COMPANY_CODE_CIRRO)){
					String perMonthValue = getKeyPrice(offerDO, TDSP_CHRG1);
					String perKWValue = getKeyPrice(offerDO, TDSP_CHRG2);
					if (StringUtils.isEmpty(perMonthValue)
							&& StringUtils.isEmpty(perKWValue)) {
						affiliateOfferDO.setTdspChargeText(StringUtils.EMPTY);
					} else {
						
						Float perMonthFloatValue = Float.parseFloat(perMonthValue);
						Float perKWFloatValue = Float.parseFloat(perKWValue);
						if(perMonthFloatValue >0 || perKWFloatValue >0){
						
						affiliateOfferDO.setTdspChargeText(msgSource
								.getMessage(TDSP_CHARGE_TEXT,
										new String[] {
											perMonthValue,
											perKWValue },
										CommonUtil.localeCode(request
												.getLanguageCode())));
						} else {
							affiliateOfferDO.setTdspChargeText(StringUtils.EMPTY);
						}
					}
					
				} else {
					if (StringUtils.isEmpty(offerDO.getTdspChargeDO()
							.getPerMonthValue())
							&& StringUtils.isEmpty(offerDO.getTdspChargeDO()
									.getPerKWValue())) {
						affiliateOfferDO.setTdspChargeText(StringUtils.EMPTY);
					} else {
						affiliateOfferDO.setTdspChargeText(msgSource
								.getMessage(TDSP_CHARGE_TEXT,
										new String[] {
												offerDO.getTdspChargeDO()
														.getPerMonthValue(),
												offerDO.getTdspChargeDO()
														.getPerKWValue() },
										CommonUtil.localeCode(request
												.getLanguageCode())));
					}
				
				}
				
				affiliateOfferDOList.add(affiliateOfferDO);
			}
		}
		return affiliateOfferDOList.toArray(new AffiliateOfferDO[affiliateOfferDOList.size()]);
	}
	
	private boolean checkMandatoryFields(AffiliateOfferDO affiliateOfferDO, String energyCharge){
		boolean validOffer = true;
		if(StringUtils.isEmpty(affiliateOfferDO.getAveragePrice2000()) 
				|| StringUtils.isEmpty(affiliateOfferDO.getBaseUsageChargeText())
				||   StringUtils.isEmpty(energyCharge)){
			validOffer =false;
			logger.info("SAP Errored Offer Code "+affiliateOfferDO.getOfferCode()
						+ " AvgPrice : "+affiliateOfferDO.getAveragePrice2000()
						+ "// Base Charge : "+affiliateOfferDO.getBaseUsageChargeText()
						+ "// Energy Charge : "+energyCharge );
		}
		return validOffer;
	}
	
	private String getAveragePrice500(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_500_KEY);
	}
	
	private String getAveragePrice1000(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_1000_KEY);
	}
	
	private String getAveragePrice2000(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_2000_KEY);
	}

	
	private String getAveragePriceEFL500(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_EFL_500_KEY);
	}
	
	private String getAveragePriceEFL1000(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_EFL_1000_KEY);
	}
	
	private String getAveragePricEFL2000(OfferDO offerDO) {
		return getKeyPrice(offerDO,AVG_PRICE_EFL_2000_KEY);
	}
	
	private String getDigitalDiscountPrice(OfferDO offerDO) {
		return getKeyPrice(offerDO,EFL_EPD_KEY);
	}
	private String getAutoPayPrice(OfferDO offerDO) {
		return getKeyPrice(offerDO,EFL_AP_KEY);
	}
	
	//Start - Alt Channels -- US14171 | Pratyush 11/13/2018
	private String getAveragePriceEUsageCR(OfferDO offerDO) {
		return getKeyPrice(offerDO,E_USAGE_CR);
	}
	
	private String getAveragePriceMaxThreshold(OfferDO offerDO) {
		return getKeyPrice(offerDO,MAX_THRESHOLD);
	}
	private String getAveragePriceMinThreshold(OfferDO offerDO) {
		return getKeyPrice(offerDO,MIN_THRESHOLD);
	}
	//End - Alt Channels -- US14171
	
	private String getEnergyCharge(OfferDO offerDO,String companyCode){
		String operandName = StringUtils.EMPTY; 
		if(StringUtils.equals(companyCode, COMPANY_CODE_RELIANT)) {
			operandName = S_UNBUNDLE;
		}else if(StringUtils.equals(companyCode, COMPANY_CODE_GME)) {
			operandName = S_GME_UNB;
		}else {
			operandName = EC;
		}
		
		return getKeyPrice(offerDO,operandName);
	}
	
	private String getEnergyCharge2(OfferDO offerDO){
		return getKeyPrice(offerDO,S_UNBUNDLE2);
	}
	
	private String getBaseCharge(OfferDO offerDO){
		return getKeyPrice(offerDO,S_CUSTCHRG);
	}
	
	private String getKeyPrice(OfferDO offerDO, String key){
		String avgPrice = StringUtils.EMPTY;
		List<OfferPriceWraperDO> offerPriceList =  offerDO.getAvgPriceMap();
		
		for(OfferPriceWraperDO offerPrice :offerPriceList ) {
			if(StringUtils.equalsIgnoreCase(offerPrice.getKey(), key)) {
				avgPrice = offerPrice.getValue().getPrice();
				break;
			}
			
		}
		
		return avgPrice;
	}
	
	private String getWebURL(String companyCode, String brandId){
		String url = StringUtils.EMPTY;
		
		if(StringUtils.equals(companyCode,CIRRO_COMPANY_CODE) && StringUtils.equals(brandId, CIRRO_BRAND_NAME) ) {
			
			url = appConstMessageSource.getMessage(CIRRO_COMPANY_CODE+"."+CIRRO_BRAND_NAME+".web.url", null,null);
					
		} else {
			
			url = appConstMessageSource.getMessage(companyCode+".web.url", null,null);
			
		}
		
		return url;		
	}
	
//Start || US23692: Affiliate API  ESID and BP Restriction for All Brands || atiwari || 04/12/2019
	private String getAllBrandResponseMessage(String companyCode, String brandId, String textMessageCode, String locale){
		
		String responseMessage=StringUtils.EMPTY;
		
		if(StringUtils.equals(companyCode,CIRRO_COMPANY_CODE) && StringUtils.equals(brandId, CIRRO_BRAND_NAME) ) {
			responseMessage = msgSource.getMessage(CIRRO_COMPANY_CODE+"."+CIRRO_BRAND_NAME+"."+textMessageCode,null,CommonUtil.localeCode(locale));		
		}else if(StringUtils.equals(companyCode,CIRRO_COMPANY_CODE) && (StringUtils.isBlank(brandId) || StringUtils.equals(brandId, BRAND_ID_PENNYWISE))){
			responseMessage = msgSource.getMessage(CIRRO_COMPANY_CODE+"."+textMessageCode,null,CommonUtil.localeCode(locale));
		}
		else{
			responseMessage = msgSource.getMessage(companyCode+"."+textMessageCode,null,CommonUtil.localeCode(locale));
		}
		return responseMessage;
	}
//END || US23692: Affiliate API  ESID and BP Restriction for All Brands || atiwari || 04/12/2019

	private boolean isServicedTDSPCode(String tdspCode){
		boolean isServiceFlag = false;
		String strtdspCodeList = appConstMessageSource.getMessage(SERVICED_TDSP_CODES, null,null);
		String[] tdspCodeArr= StringUtils.split(strtdspCodeList, ",");
		List<String> tdspCodeList = Arrays.asList(tdspCodeArr);
		isServiceFlag = tdspCodeList.contains(tdspCode);
		return isServiceFlag;
	}

	// Start : Validate for Power Genius Online Affiliates by KB
	public void sendPowerGeniusConfirmationEmail(String toEmail) throws Exception {
		
		
		boolean isEmailSent = this.sendPowerGeniusConfirmationEmailDlt(toEmail);
		
		logger.info("isEmailSent:"+ isEmailSent);
	}
	// End : Validate for Power Genius Online Affiliates by KB
	
	/**
	 * START : OE | Sprint 46 | US15066 | Kdeshmu1
	 * @param request
	 * @param sessionId
	 * @return
	 */
public UpdateETFFlagToCRMResponse updateETFFlagToCRM(UpdateETFFlagToCRMRequest request, String sessionId) {
		
	UpdateETFFlagToCRMResponse response = new UpdateETFFlagToCRMResponse();
			
		if(StringUtils.isBlank(request.getAccount())&& StringUtils.isBlank(request.getPartner()))
			{  //If Promo code is passed empty
				response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
				response.setResultDescription("contractAccountnumber and Partner ID may not be Empty");
				return response;	
			}
			
		try {
			response = oeService.updateETFFlagToCRM(request);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			logger.error("Exception in updateETFFlagToCRM: ", e);
		}
		logger.info("updateETFFlagToCRM : ResultCode : "+response.getResultCode());
		return response;
		
		}
	
/**
 * Alternate Channel : Sprint 13 :US 11783 
 * @author KDeshmu1	
 * @param request
 * @param sessionId
 * @return
 */
public TLPOfferResponse getOfferForTLP(TLPOfferRequest request, String sessionId) {
		
	TLPOfferResponse response = new TLPOfferResponse();
		
	if(StringUtils.isBlank(request.getPromoCode()))
		{  //If Promo code is passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("promoCode may not be Empty");
			return response;	
		}
		
				
		if(!StringUtils.equals(request.getCompanyCode(), COMPANY_CODE_RELIANT) )
		{  //If Tdsp Code & Esid are passed empty
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
			response.setResultDescription("Company code "+request.getCompanyCode()+" is currently not supported");			
			return response;			
		}
		
		if(StringUtils.isNotBlank(request.getTdspCodeCCS()) && !isServicedTDSPCode(request.getTdspCodeCCS())) {
			response.setMessageCode(AREA_NOT_SERVICED);
			//response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,null));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			return response;
		}
		
		OfferResponse offerResponse = getOffers(null,
				request.getCompanyCode(), null, null,
				null, null, null, request.getPromoCode(),
				request.getTdspCodeCCS(),null, sessionId,null);
		
		logger.info("OfferResponse : strErrorCode : "+offerResponse.getStrErrorCode());
		if(StringUtils.equalsIgnoreCase(MSG_CCSERR_8_GET_PROMO_OFFERS, offerResponse.getStrErrorCode()) || StringUtils.equalsIgnoreCase(MSG_CCSERR_E_GET_PROMO_OFFERS, offerResponse.getStrErrorCode())) {
			response.setMessageCode(PROMO_INVALID);
			//response.setMessageText(msgSource.getMessage(PROMO_INVALID_TEXT,null,null));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			response.setResultDescription("Failed -"+offerResponse.getStrErrorCode());
			//response = constructMainFields(response,offerResponse);
			
		} else if(StringUtils.isEmpty(offerResponse.getStrTDSPCode())|| !isServicedTDSPCode(offerResponse.getStrTDSPCode())) {
			response.setMessageCode(AREA_NOT_SERVICED);			
			//response.setMessageText(msgSource.getMessage(AREA_NOT_SERVICED_TEXT,null,CommonUtil.localeCode(request.getLanguageCode())));
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setResultCode(Constants.RESULT_CODE_SUCCESS );
			if(StringUtils.isEmpty(offerResponse.getStrErrorCode())) {
				response.setResultDescription("Failed");
			} else {
				response.setResultDescription("Failed -"+offerResponse.getStrErrorCode());
			}
			//response = constructMainFields(response,offerResponse);
		} else{
			TLPOfferDO offerDOList[] = constructTLPOfferDOList(offerResponse,request);
			response.setTLPOfferList(offerDOList);
		}
		
		
		return response;
	}
	
/**
 * Alternate Channel : Sprint 13 :US 11783 
 * @author KDeshmu1
 * @param offerResponse
 * @param request
 * @return
 */
private TLPOfferDO[] constructTLPOfferDOList(
		OfferResponse offerResponse, TLPOfferRequest request) {
	OfferDO[] offerDOArr = offerResponse.getOfferDOList();
	TLPOfferDO[] tlpOfferDOArr = null;
	if (offerDOArr != null) {
		tlpOfferDOArr = new TLPOfferDO[offerDOArr.length];
		int i = 0;
		for (OfferDO offerDO : offerDOArr) {
			tlpOfferDOArr[i] = new TLPOfferDO();
			
			tlpOfferDOArr[i].setOfferCode(offerDO.getStrOfferCode());
			tlpOfferDOArr[i].setOfferTeaser(offerDO.getStrOfferCodeTitle());		
			i++;
		}
	}
	return tlpOfferDOArr;
	
}
	
	public AgentDetailsResponse getAgentDetails(AgentDetailsRequest request, String sessionId) {
		
		AgentDetailsResponse response = new AgentDetailsResponse();
			
		if(StringUtils.isBlank(request.getAgentID()))
			{  //If Promo code is passed empty
				response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
				response.setResultDescription("agent id may not be Empty");
				logger.info("getAgentDetailsResponse : ResultCode : XXX");
				return response;	
			}
			
		try {
			response = oeService.getAgentDetails(request);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			logger.error("Exception in getting Agent Details: ", e);
		}
		logger.info("getAgentDetailsResponse : ResultCode : "+response.getResultCode());
		return response;
		
		}




	

	public UCCDataResponse submitUCCData(UCCDataRequest uccDataRequest, String sessionId){
		
		
		UCCDataResponse uccDataResponse = new UCCDataResponse();
		UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();

		if (StringUtils.isNotEmpty(uccDataRequest.getTrackingId()))
			requestData.setTrackingId(uccDataRequest.getTrackingId());
		requestData.setCompanyCode(uccDataRequest.getCompanyCode());

		ServiceLocationResponse serviceLocationResponse =  getEnrollmentData(uccDataRequest.getTrackingId());
		
		if(serviceLocationResponse != null){
			
			if( (!StringUtils.equalsIgnoreCase(serviceLocationResponse.getPersonResponse().getFirstName(), uccDataRequest.getFirstName())) 
					|| (!StringUtils.equalsIgnoreCase(serviceLocationResponse.getPersonResponse().getLastName(), uccDataRequest.getLastName())) ) {
				
				uccDataResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				uccDataResponse.setStatusCode(STATUS_CODE_STOP);
				uccDataResponse.setMessageCode(MESSAGE_CODE_INFO_MISMATCH);
				uccDataResponse.setMessageText(MESSAGE_TEXT_INFO_MISMATCH);
				
			}else{
				
				String personId = getPersonIdByTrackingNo(requestData
						.getTrackingId());
		
				// Update service location and person table only when a valid person
				// id
				// is returned from getPersonIdByTrackingNo
		
				if (StringUtils.isNotEmpty(personId)) {
		
					/* Setting service addresses */
					requestData.setRecentCallMade(UCC_DATA);
					
					requestData.setSecurityMethod(SECURITY_METHOD_UCC);
					
					if(!StringUtils.equals(ZERO, uccDataRequest.getDepositAmount())) {
						requestData.setPayCode(YES);	
						requestData.setDepositCode(DEPOSIT_OWED);
						requestData.setDepositAmount(uccDataRequest.getDepositAmount());
						
					} else {					
						requestData.setPayCode(FLAG_NO);
						requestData.setDepositCode(DEPOSIT_NONE);
						requestData.setDepositAmount(ZERO);
					}
					
		
					/* Updating service location affiliate table */
					
					String errorCode = this.updateServiceLocation(requestData);
					if (StringUtils.isNotBlank(errorCode)){
						logger.debug("Finished processing updateServiceLocation, errorCode = "
								+ errorCode);
																				
						uccDataResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
						uccDataResponse.setStatusCode(STATUS_CODE_STOP);
						uccDataResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
						uccDataResponse.setMessageText(MESSAGE_TEXT_TRACKING_NUMBER_NOT_UPDATED);
						
					} else {
						uccDataResponse.setResultCode(RESULT_CODE_SUCCESS);
						uccDataResponse.setStatusCode(STATUS_CODE_CONTINUE);
					}
		
					UpdatePersonRequest requestDataPerson = new UpdatePersonRequest();
					/* Updating person affiliate table */
					errorCode = EMPTY;
					requestDataPerson.setPersonId(personId);
					// requestDataPerson.setLanguageCode(locale);
					requestDataPerson.setFirstName(uccDataRequest.getFirstName());
					requestDataPerson.setLastName(uccDataRequest.getLastName());					
						requestDataPerson.setSsn(uccDataRequest.getTokenizedSSN());		
						requestDataPerson.setCredLevelNum(uccDataRequest
								.getCreditBucket());
						requestDataPerson.setCredSourceNum(uccDataRequest
								.getCreditSource());
						requestDataPerson.setCredScoreNum(uccDataRequest
								.getCreditScore());
						requestDataPerson.setAdvActionData(uccDataRequest
								.getCreditFactors());
		
					errorCode = this.updatePerson(requestDataPerson);
					if (StringUtils.isNotBlank(errorCode)) {
						logger.debug("Finished processing updateServiceLocation, errorCode = "
								+ errorCode);
						
						uccDataResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
						uccDataResponse.setStatusCode(STATUS_CODE_STOP);
						uccDataResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
						uccDataResponse.setMessageText(MESSAGE_TEXT_PERSON_NOT_UPDATED);
						
					} else {
						uccDataResponse.setResultCode(RESULT_CODE_SUCCESS);
						uccDataResponse.setStatusCode(STATUS_CODE_CONTINUE);
					}
				}else{
					uccDataResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					uccDataResponse.setStatusCode(STATUS_CODE_STOP);
					uccDataResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
					uccDataResponse.setMessageText(MESSAGE_TEXT_PERSON_NOT_FOUND);
					
				}
			}
		}else{
			uccDataResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			uccDataResponse.setStatusCode(STATUS_CODE_STOP);
			uccDataResponse.setMessageCode(MESSAGE_CODE_TECHNICAL_ERROR);
			uccDataResponse.setMessageText(MESSAGE_TEXT_TRACKING_NUMBER_NOT_FOUND);
		}
		
		return uccDataResponse;
	}
	
	/**
	 * This method is responsible for get environment impact for GME mobile app
	 * @author NGASPerera
	 * @return GMEEnviornmentalImpact
	 */
	public GMEEnviornmentalImpact getEnviornmentalImpactForAllGMECommunity() {
		GMEEnviornmentalImpact gMEEnviornmentalImpact = new GMEEnviornmentalImpact();
		try{
		double treesPerSecond = Double
				.parseDouble(appConstMessageSource.getMessage(Constants.TREES_PER_SECOND, null, null));
		double co2PerSecond = Double
				.parseDouble(appConstMessageSource.getMessage(Constants.CO2_PER_SECOND, null, null));
		long baselineTotal = Long.parseLong(appConstMessageSource.getMessage(Constants.TOATL_BASELINE, null, null));
		long treesBaselineTotal = Long
				.parseLong(appConstMessageSource.getMessage(Constants.TOTAL_BASELINE_TREE, null, null));
		Calendar cal = Calendar.getInstance();
		// This year is hard coded, because web team still using 2018 (used same as for the compatibility)
		// need to remove this below one line to get the current year
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		Date baselineDate = cal.getTime();
		long secondsSinceBaseline = dateUtil.getTimeDifferenceInSeconds(new Date(), baselineDate);
		long totTreesPlantedForCurrentYr = Math.round((treesPerSecond * secondsSinceBaseline) + treesBaselineTotal);
		long totPoundOfCarbonForCurrentYr = Math.round(((co2PerSecond * secondsSinceBaseline) + baselineTotal));
		gMEEnviornmentalImpact.setToatlTreesPlantedForCurrentYr(totTreesPlantedForCurrentYr);
		gMEEnviornmentalImpact.setTotalPoundOfCO2ForCurrentYr(totPoundOfCarbonForCurrentYr);
		gMEEnviornmentalImpact.setResultCode(SUCCESS_CODE);
		gMEEnviornmentalImpact.setResultDescription(MSG_SUCCESS);
		} catch(Exception ex){
			gMEEnviornmentalImpact.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			gMEEnviornmentalImpact.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return gMEEnviornmentalImpact;
	}
	/**
	 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
	 * @author Nkatragadda
	 * @param request
	 * @return
	 */
	public GiactBankValidationResponse validateBankDetailsGiact(GiactBankValidationRequest bankDetailsValidationRequest) {
		GiactBankValidationResponse bankDetailsValidationResponse=new GiactBankValidationResponse();
		try{
		bankDetailsValidationResponse = oeService.validateBankDetailsGiact(bankDetailsValidationRequest);
		}catch(Exception e){
			logger.debug("caught Exception in OEBO::validateBankDetailsGiact(..)"+e);
		}
		return bankDetailsValidationResponse;
	}
	/**
	 * 
	 * @param oeSignUpDTO
	 * @return
	 * @throws ParseException 
	 */
	public String sendReliantEnrollmentDataToTLP (OESignupDTO oeSignUpDTO) throws ParseException{
		
		EnrollmentReportDataRequest request=setEnrollmentReportDataRequest(oeSignUpDTO);
		
		//Invoke IOT Service call
		String restJsonResponse = oeService.createAndCallServiceReturnStatus(request, REST_IOT_ENROLLMENT_REPORT_DATA_SUBMIT_URL, IOT_ENROLLMENT_REPORT_DATA_SUBMIT_REST_TIME_OUT_IN_SEC);
		
		logger.info("Enrollment Rest Data restResponse from IOT=={}"+restJsonResponse);
		
		return restJsonResponse;
	}
	
private EnrollmentReportDataRequest setEnrollmentReportDataRequest(OESignupDTO oeSignUpDTO) throws ParseException {
		
		EnrollmentReportDataRequest request = new EnrollmentReportDataRequest();
			        
		request.setTrackingId(oeSignUpDTO.getTrackingNumber());

		String guid = CommonUtil.generateUUID();
		request.setGuid(guid);
		
		/*if(null!=oeSignUpDTO.getProspectDTO())
		{
			request.setProspectId(oeSignUpDTO.getProspectDTO().getProspectId());
		}*/
		request.setProspectId("");
		
		request.setTdspCode(oeSignUpDTO.getTdspCodeCCS());
		
		
			request.setVendorCode(oeSignUpDTO.getVendorCode());
			request.setVendorName(oeSignUpDTO.getVendorName());
			if(null!=oeSignUpDTO.getAgentID())
			{
				request.setAgentId(oeSignUpDTO.getAgentID());
					
			}
			/*if(null!=oeSignUpDTO.getAgentTypeDescription())
			{
				//request.setAgentTypeDescription(oeSignUpDTO.getSelectedAgentDTO().getAgentTypeDescription());
			}*/
			request.setAgentTypeDescription("");
		
			request.setPartnerId("");
			
			request.setLocationId("");
		
		if(null!=oeSignUpDTO.getPerson())
		{
				request.setAccountFirstName(oeSignUpDTO.getPerson().getFirstName());
				request.setAccountLastName(oeSignUpDTO.getPerson().getLastName());
		}
		/*if(null!=oeSignUpDTO.getCreditCheckDTO())
		{
				request.setDepositHold(oeSignUpDTO.getCreditCheckDTO().getDepositHold());
		}*/
		request.setDepositHold("");
		request.setEsid(oeSignUpDTO.getEsidNumber());	
		
		
			
			request.setContractTerm("NA");
			request.setCancelFee("NA");	
		
		
		if(null!=oeSignUpDTO.getServiceAddress())
		{
				request.setServiceAddressStreet(oeSignUpDTO.getServiceAddress().getStreetNum()+ SPACE + oeSignUpDTO.getServiceAddress().getStreetName());
				request.setServiceAddressUnit(oeSignUpDTO.getServiceAddress().getUnitNum());
				request.setServiceAddressState(oeSignUpDTO.getServiceAddress().getState());
				request.setServicAddresseCity(oeSignUpDTO.getServiceAddress().getCity());
				request.setServiceAddressZip(oeSignUpDTO.getServiceAddress().getZipcode());
				
		}
		if(null!=oeSignUpDTO.getBillingAddress())
		{	
			if(null!=oeSignUpDTO.getBillingAddress().getStreetAddress()){
				request.setBillingAddressStreet(oeSignUpDTO.getBillingAddress().getStreetNum()+ SPACE + oeSignUpDTO.getBillingAddress().getStreetName());
			}
			else
			{
				request.setBillingAddressStreet("PO BOX "+oeSignUpDTO.getBillingAddress().getPoBox());
			}
				request.setBillingAddressUnit(oeSignUpDTO.getBillingAddress().getUnitNum());
				request.setBillingAddressCity(oeSignUpDTO.getBillingAddress().getCity());
				request.setBillingAddressState(oeSignUpDTO.getBillingAddress().getState());
				request.setBillingAddressZip(oeSignUpDTO.getBillingAddress().getZipcode());
		}
		 
		request.setSameServiceBillAddressFlag(oeSignUpDTO.getSameBillingServiceAddressFlag());
	
		Date serDate=new SimpleDateFormat("ddMMyyyy").parse(oeSignUpDTO.getServiceStartDate());
		String finalSerDate = new SimpleDateFormat("dd/MM/YYYY").format(serDate);
		request.setServiceStartDate(finalSerDate.toString());
		String timeStamp = new SimpleDateFormat("dd-MMM-YYYY hh:mm:ss aa").format(new Date());
		request.setCreationDateTimestamp(timeStamp);
		
		if(null!=oeSignUpDTO.getSelectedOffer())
		{
		request.setOfferCode(oeSignUpDTO.getSelectedOffer().getOfferCode());
		request.setPromoCode(oeSignUpDTO.getSelectedOffer().getOfferCellTrackCodeSelected());
		request.setPlanName(oeSignUpDTO.getSelectedOffer().getPlanName());
		request.setOfferTeaser(oeSignUpDTO.getSelectedOffer().getOfferTeaser());
		}
		
		//request.setOfferTeaser(CommonUtil.getAlphaNumeric(oeSignUpDTO.getSelectedOfferDTO().getStrOfferCodeTitle()));
		
		
		request.setContractAccountNumber(oeSignUpDTO.getContractAccountNum());
		request.setDeviceLatitude("");
		request.setDeviceLongitude("");
		request.setDeviceLocationAccuracy("");
		request.setEnrollmentStatus(oeSignUpDTO.getReqStatusCd());
		
		request.setPhoneNumber(oeSignUpDTO.getPerson().getPhoneNumber());
		request.setEmailAddress(oeSignUpDTO.getPerson().getEmailAddress());
		
		request.setTabletID("");
		request.setKbaDecision("");
		request.setKbaReasonCodes("");
		
		request.setDateOfBirth(oeSignUpDTO.getPerson().getDateOfBirth());
		request.setEnrollmentType(oeSignUpDTO.getServiceReqTypeCd());
		request.setDeviceType("");
		return request;
	}

/**
 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
 * @author 289347
 * @param request
 * @return
 */
public GetKBAQuestionsResponse getKBAQuestions(GetKBAQuestionsRequest request) {
	
	GetKBAQuestionsResponse response = new GetKBAQuestionsResponse();
	KbaQuestionRequest kbaQuestionRequest = new KbaQuestionRequest();
	KbaQuestionResponse kbaQuestionResponse = new KbaQuestionResponse();
	List<Question> questions = new ArrayList<>();
	try {
					
		logger.debug("inside getKBAQuestions::Tracking Number ::{} :: preferred language is:{}"+request.getTrackingId());
		kbaQuestionRequest = createKBAQuestionRequest(request);
		 kbaQuestionResponse = oeService.getKBAQuestionList(kbaQuestionRequest);
		 if (kbaQuestionResponse != null && (kbaQuestionResponse.getQuestionList() != null
					&& kbaQuestionResponse.getQuestionList().length > 0)) {
				
				getKBAQuestion(kbaQuestionResponse.getQuestionList(), questions);
				
				response.setStatusCode(STATUS_CODE_CONTINUE);
				response.setTransactionKey(kbaQuestionResponse.getTransactionKey());
				response.setQuestions(questions);
			} else {
				response.setStatusCode(STATUS_CODE_CONTINUE);
				response.setMessageCode(POSID_FAIL);
				response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));


			}
		 boolean addKBAErrorCode=this.addKBADetails(kbaQuestionResponse);

	} catch (Exception e) {
		response.setStatusCode(STATUS_CODE_CONTINUE);
		response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION);
		response.setMessageCode(POSID_FAIL);
		response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
	} finally {
		try{
				// Making Update Servicelocation call now
				if(StringUtils.isNotBlank(kbaQuestionResponse.getTransactionKey())
						&& StringUtils.isNotEmpty(request.getTrackingId())){
				UpdateServiceLocationRequest updateServiceLocationRequest = new UpdateServiceLocationRequest();
				updateServiceLocationRequest.setTrackingId(request.getTrackingId());
				updateServiceLocationRequest.setKbaTransactionKey(kbaQuestionResponse.getTransactionKey());;
				this.updateServiceLocation(updateServiceLocationRequest);
				response.setTrackingId(request.getTrackingId());
				
				
			}
		}catch(Exception e){
			response.setStatusCode(STATUS_CODE_STOP);
			response.setResultDescription("Java exception making Database call for getKBaQuestion-updateServiceLocation with exception ::"+e.getMessage());
			logger.error("Tracking Number ::"+request.getTrackingId()+" :: affiliate Id : "
					+ ""+request.getAffiliateId() +"::Exception while making getKBaQuestion-updateserviceLocation call :: ", e);
		}
		
	}

	return response;
}

/**
 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
 * @param request
 * @return
 */
private KbaQuestionRequest createKBAQuestionRequest(GetKBAQuestionsRequest request){
	KbaQuestionRequest kbaQuestionRequest = new KbaQuestionRequest();
	kbaQuestionRequest.setCompanyCode(request.getCompanyCode());
	kbaQuestionRequest.setBrandName(request.getBrandId());
	kbaQuestionRequest.setChannel(CHANNEL);
	kbaQuestionRequest.setChannelType(CHANNEL_TYPE_AA);
	kbaQuestionRequest.setLanguageCode(request.getLanguageCode());
	
	kbaQuestionRequest.setFirstName(request.getFirstName());
	kbaQuestionRequest.setLastName(request.getLastName());
	kbaQuestionRequest.setMiddleName(request.getMiddleName());	
	kbaQuestionRequest.setDob(request.getDob());
	kbaQuestionRequest.setTokenizedSSN(request.getTokenSSN());		
	if(StringUtils.isNotEmpty(request.getTokenTDL())){
        kbaQuestionRequest.setTokenizedDrl(request.getTokenTDL());        
        kbaQuestionRequest.setDlrState(request.getDrivingLicenseState());
    } 
	
//	kbaQuestionRequest.setTokenizedDrl("KR0PK39V-2290");
//	kbaQuestionRequest.setDlrState("TX");
//	kbaQuestionRequest.setTokenizedSSN("2RD6VE6-5840");
//	kbaQuestionRequest.setDlrState(null);
	
	
	kbaQuestionRequest.setHomePhone(request.getPhoneNum());
	kbaQuestionRequest.setEmailAddress(request.getEmail());
	kbaQuestionRequest.setIpAddress(request.getIpAddress());
	kbaQuestionRequest.setEsid(request.getEsid());
	kbaQuestionRequest.setPosidBasedKBAFlag(FLAG_X);
	kbaQuestionRequest.setFailFromPosidFlag(FLAG_X);
	
	
	AddressDTO serviceAddressDTO = new AddressDTO();
	serviceAddressDTO.setStrStreetNum(request.getServStreetNum());
	serviceAddressDTO.setStrStreetName(request.getServStreetName());		
	serviceAddressDTO.setStrUnitNumber(request.getServStreetAptNum());
	serviceAddressDTO.setStrCity(request.getServCity());
	serviceAddressDTO.setStrState(request.getServState());
	serviceAddressDTO.setStrZip(request.getServZipCode());
	
	kbaQuestionRequest.setServiceAddress(serviceAddressDTO);
	kbaQuestionRequest.setPosidUniqueKey(request.getPosidUniqueKey());
	
	return kbaQuestionRequest;
}


/**
 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
 * @param questionList
 * @param questions
 */
private void getKBAQuestion(KbaQuestionDTO[] questionList, List<Question> questions) {

	for (KbaQuestionDTO question : questionList) {
		Question q = new Question();
		q.setQuestionId(question.getQuestionId());
		q.setQuestionText(question.getQuestionText());
		q.setQuizeId(question.getQuizId());

		List<Option> options = new ArrayList<>();
		if (question.getAnswerList() != null && question.getAnswerList().length > 0) {
			for (KbaAnswerDTO answer : question.getAnswerList()) {
				Option option = new Option();
				option.setOptionId(answer.getAnswerId());
				option.setOptionText(answer.getContent());
				if(answer.isCorrectAnswer()){
				option.setKeyAnswer(FLAG_X);
				}
				options.add(option);
			}
			q.setOptions(options);
		}

		questions.add(q);
	}

}

/**
 * Start: OE : Sprint3 : 14064 - Create New KBA Question API :Kdeshmu1
 * @author Kdeshmu1
 * @param request
 * @return
 * @throws Exception
 */
public boolean addKBADetails(KbaQuestionResponse request) throws Exception {
	logger.debug("Entering >> addKBADetails");
	logger.debug("request = " + request);
	boolean errorCode = kbaDao.addKbaDetails(request);
	logger.debug("Exiting << addKBADetails");
	return errorCode;
}

/**
 * Start: OE : Sprint3 : 14065 - Create New KBA Answer API :asingh
 * @author 
 * @param request
 * @return
 */
public KbaAnswerResponse submitKBAAnswers(KbaAnswerRequest kbaAnswerRequest) throws Exception{
	KbaSubmitAnswerRequest request = new KbaSubmitAnswerRequest();
	KbaAnswerResponse response = new KbaAnswerResponse();
	KBASubmitResultsDTO kbaSubmitResultsDTO = new KBASubmitResultsDTO();
	//KbaSubmitAnswerResponse kbaSubmitAnswerResponse = new KbaSubmitAnswerResponse();
	try{

		List<KBAQuestionAnswerVO> questionAnswerList = constructKBAQuestionAnswerVOList(kbaAnswerRequest);
		logger.info("KBAHelper.submitKBAAnswer questionAnswerList"+questionAnswerList);
		request.setTransactionKey(kbaAnswerRequest.getTransactionKey());
		
		KbaQuizAnswerDTO[] answerArr = new KbaQuizAnswerDTO[questionAnswerList.size()];
		int i =0;
		for(KBAQuestionAnswerVO answerVO:questionAnswerList){
			KbaQuizAnswerDTO quizAnswerDTO = new KbaQuizAnswerDTO();
			quizAnswerDTO.setAnswerId(answerVO.getAnswerId());
			quizAnswerDTO.setQuestionId(answerVO.getQuestionId());
			quizAnswerDTO.setQuizId(answerVO.getQuizId());
			answerArr[i] = quizAnswerDTO;
			i++;
		}
		request.setKbaQuizAnswerArr(answerArr);

		KbaSubmitAnswerResponse kbaSubmitAnswerResponse = oeService.submitKBAAnswer(request);
		logger.info(kbaAnswerRequest.getTrackingId()+" kbaSubmitAnswerResponse : "+CommonUtil.doRender(kbaSubmitAnswerResponse));
	    kbaSubmitResultsDTO = constructKBAResponseOutputDTO(kbaSubmitAnswerResponse);
		logger.info("kbaResponseOutputDTO : "+CommonUtil.doRender(kbaSubmitResultsDTO));
		//kbaAnswerRequest.setKbaAnswerResponse(kbaSubmitResultsDTO.getKbaSubmitAnswerResponseOutput());
		
		if(StringUtils.isEmpty(kbaSubmitAnswerResponse.getStrErrCode())){				
			String returnCode = kbaSubmitAnswerResponse.getReturnCode();
			int intReturnCode = 0;
			if(!StringUtils.isEmpty(returnCode)){
				intReturnCode = Integer.parseInt(returnCode);
			}
			if(intReturnCode ==0){
				if(null != kbaSubmitAnswerResponse 
						&& StringUtils.isNotEmpty(kbaSubmitAnswerResponse.getSsnVerifyDate()) 
						&&  !StringUtils.equalsIgnoreCase(kbaSubmitAnswerResponse.getSsnVerifyDate(), POSID_BLANK_DATE)){
				
					String validatedDate = DateUtil.getFormattedDate(DATE_FORMAT, RESPONSE_DATE_FORMAT,
							kbaSubmitAnswerResponse.getSsnVerifyDate());
					response.setSsnVerifyDate(validatedDate);
					
					
				} else if(null != kbaSubmitAnswerResponse 
						&& StringUtils.isNotEmpty(kbaSubmitAnswerResponse.getDlVerifyDate()) 
						&& !StringUtils.equalsIgnoreCase(kbaSubmitAnswerResponse.getDlVerifyDate(), POSID_BLANK_DATE)){								
					
					String validatedDate = DateUtil.getFormattedDate(DATE_FORMAT, RESPONSE_DATE_FORMAT,
							kbaSubmitAnswerResponse.getDlVerifyDate());
					response.setDrivingLicenceVerifyDate(validatedDate);
					
				}else{
					response.setStatusCode(STATUS_CODE_CONTINUE);
					response.setMessageCode(POSID_FAIL_MAX);
					response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
				}
				
				response.setDrivingLicenceVerifyDate(kbaSubmitAnswerResponse.getDlVerifyDate());
				if(null != kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput()){
					if(StringUtils.isBlank(kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput().getDecision())){
						response.setErrorCode(RETRY_NOT_ALLOWED);
						response.setErrorDescription(RETRY_NOT_ALLOWED_TXT);
					}
				response.setDecision(kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput().getDecision());
				}
			} else{
				logger.info("Return msg in KbaSubmitAnswerResponse is:"+kbaSubmitAnswerResponse.getReturnMessage());
				response.setStatusCode(STATUS_CODE_CONTINUE);
				response.setMessageCode(POSID_FAIL_MAX);
				response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
			}
		}else{
			logger.info("Error in KBAService.submitKBAAnswer method errorCode :"+kbaSubmitAnswerResponse.getStrErrCode());
			logger.info("Error in KBAService.submitKBAAnswer method errorCodeErrorMsg:"+kbaSubmitAnswerResponse.getStrErrMessage());
			response.setStatusCode(STATUS_CODE_CONTINUE);
			response.setMessageCode(POSID_FAIL_MAX);
			response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
		}
		//update kba_api
		boolean updateKBAErrorCode=this.updateKbaDetails(kbaSubmitResultsDTO);
	}catch (Exception e) {
		response.setStatusCode(STATUS_CODE_STOP);
		response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);		
		
	}finally{
		try{
		if(StringUtils.isNotBlank(kbaAnswerRequest.getTrackingId())){
			//update service location affiliate
			UpdateServiceLocationRequest requestData = new UpdateServiceLocationRequest();
             requestData.setRecentCallMade(CALL_NAME_KBA_SUBMIT);	
             requestData.setTrackingId(kbaAnswerRequest.getTrackingId());
             //update RECENT_MSG_CD
             requestData.setMessageCode(response.getMessageCode());
            this.updateServiceLocation(requestData);
        }
	}catch(Exception e){
		response.setStatusCode(STATUS_CODE_STOP);
		response.setResultDescription("Java exception making Database call for submitKbaAnswers-updateServiceLocation with exception ::"+e.getMessage());
		logger.error("Tracking Number ::"+kbaAnswerRequest.getTrackingId()+ "Exception while making submitKbaAnswers-updateserviceLocation call :: ", e);
	}
	}
	return response;
	}

private KBASubmitResultsDTO constructKBAResponseOutputDTO(KbaSubmitAnswerResponse kbaSubmitAnswerResponse){
	KBASubmitResultsDTO kbaSubmitResultsDTO = new KBASubmitResultsDTO();
			
	List<KBAErrorDTO> kbaErrorDTOList = getKBAErrorList(kbaSubmitAnswerResponse);
	kbaSubmitResultsDTO.setErrorList(kbaErrorDTOList);				
	
	KbaAnswerResponseDTO kbaResponseOutputDTO = getKBAResponseOutputDTO(kbaSubmitAnswerResponse);
	kbaSubmitResultsDTO.setKbaAnswerResponseDTO(kbaResponseOutputDTO);
	
	kbaSubmitResultsDTO.setReturnCode(kbaSubmitAnswerResponse.getReturnCode());
	kbaSubmitResultsDTO.setReturnMessage(kbaSubmitAnswerResponse.getReturnMessage());			
	kbaSubmitResultsDTO.setStrErrCode(kbaSubmitAnswerResponse.getStrErrCode());
	kbaSubmitResultsDTO.setStrErrMessage(kbaSubmitAnswerResponse.getStrErrMessage());
	return kbaSubmitResultsDTO;
}

private List<KBAErrorDTO> getKBAErrorList(KbaSubmitAnswerResponse kbaSubmitAnswerResponse){
	List<KBAErrorDTO> kbaErrorDTOList = null;
	if(kbaSubmitAnswerResponse.getErrorList() != null && kbaSubmitAnswerResponse.getErrorList().length >0){
		kbaErrorDTOList = new ArrayList();
		for(KbaErrorDTO errorDTO:kbaSubmitAnswerResponse.getErrorList()){
			KBAErrorDTO kbaErrorDTO = new KBAErrorDTO();
			kbaErrorDTO.setErrorCode(errorDTO.getErrorCode());
			kbaErrorDTO.setErrorDescription(errorDTO.getErrorDescription());
			kbaErrorDTO.setErrorMsg(errorDTO.getErrorMsg());
			kbaErrorDTO.setTransactionKey(errorDTO.getTransactionKey());
			kbaErrorDTOList.add(kbaErrorDTO);
		}
	}
	return kbaErrorDTOList;
}

private KbaAnswerResponseDTO getKBAResponseOutputDTO(KbaSubmitAnswerResponse kbaSubmitAnswerResponse){
	KbaAnswerResponseDTO kbaResponseOutputDTO = null;
	if( (kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput() != null) && (!StringUtils.isEmpty(kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput().getDecision()))){
		kbaResponseOutputDTO = getKBAResponseOutputDTO(kbaSubmitAnswerResponse.getKbaSubmitAnswerResponseOutput());
	}
					
	return kbaResponseOutputDTO;
}

private KbaAnswerResponseDTO getKBAResponseOutputDTO(KbaResponseOutputDTO responseDTO){
	KbaAnswerResponseDTO kbaResponseOutputDTO = new KbaAnswerResponseDTO();
	kbaResponseOutputDTO.setTransactionKey(responseDTO.getTransactionKey());
	kbaResponseOutputDTO.setDecision(responseDTO.getDecision());
	kbaResponseOutputDTO.setFraudlevel(responseDTO.getFraudlevel());
	kbaResponseOutputDTO.setIdentityScore(responseDTO.getIdentityScore());
	kbaResponseOutputDTO.setInteractiveQscore(responseDTO.getInteractiveQscore());
	kbaResponseOutputDTO.setOverallScore(responseDTO.getOverallScore());
	
	List<KBAResponseReasonDTO> kbaReasonList = new ArrayList();
	
	if(responseDTO.getKbaReasonList() != null && (responseDTO.getKbaReasonList().length >0)){
		
		for(KbaResponseReasonDTO reasonDTO :responseDTO.getKbaReasonList() ){
			KBAResponseReasonDTO kbaReasonDTO = new KBAResponseReasonDTO();
			kbaReasonDTO.setReasonCode(reasonDTO.getReasonCode());
			kbaReasonDTO.setReasonDesc(reasonDTO.getReasonDesc());
			
			kbaReasonList.add(kbaReasonDTO);
		}
	}
	
	kbaResponseOutputDTO.setKbaReasonList(kbaReasonList);
	
	List<KBAResponseAssessmentDTO> verificationAssessmentList = new ArrayList();
	
	if(responseDTO.getVerificationAssessmentList() != null && (responseDTO.getVerificationAssessmentList().length >0)){
		for(KbaResponseAssessmentDTO assessment: responseDTO.getVerificationAssessmentList()){
			KBAResponseAssessmentDTO kbaAssessmentDTO = new KBAResponseAssessmentDTO();
			kbaAssessmentDTO.setAssessmentName(assessment.getAssessmentName());
			kbaAssessmentDTO.setAssessmentValue(assessment.getAssessmentValue());
			
			verificationAssessmentList.add(kbaAssessmentDTO);
		}
	}
	
	kbaResponseOutputDTO.setVerificationAssessmentList(verificationAssessmentList);
	
	return kbaResponseOutputDTO;
}

private List<KBAQuestionAnswerVO> constructKBAQuestionAnswerVOList(KbaAnswerRequest kbaAnswerRequest){
	List<KBAQuestionAnswerVO> questionAnswerList = new ArrayList();
	
	if(kbaAnswerRequest.getQuestionList() != null){		
		ObjectMapper mapper = new ObjectMapper();
		questionAnswerList = mapper.convertValue(kbaAnswerRequest.getQuestionList(), new TypeReference<List<KBAQuestionAnswerVO>>() { });
		
	}
	return questionAnswerList;
}

/**
 * Start: OE : Sprint3 : 14065 - Create New KBA Answers API :asingh
 * @author asingh
 * @param request
 * @return
 * @throws Exception
 */
public boolean updateKbaDetails(KBASubmitResultsDTO request) throws Exception {
	logger.debug("Entering in method: updateKbaDetails");
	logger.debug("request = " + request);
	boolean errorCode = kbaDao.updateKbaDetails(request);
	logger.debug("Exiting in method: updateKbaDetails");
	return errorCode;
}

/**
* Start || PBI 15786: Update ESID Call || atiwari
* @author atiwari
* @param getEsiidRequest GetEsiidRequest
* @return com.multibrand.vo.response.GetEsiidResponse
* @throws SQLException, Exception
*/
public com.multibrand.vo.response.GetEsiidResponse getESIDDetails(GetEsiidRequest getEsiidRequest) throws Exception{
com.multibrand.vo.response.GetEsiidResponse esidResponse=null;
esidResponse = addressDAO.getESIDDetails(getEsiidRequest);
return esidResponse;

}

/**
 * Start ;ADO :Sprint 4 :: To get Prospect Data
 * @author Kdeshmu1
 * @param prospectId
 * @param lastFourDigitSsn
 * @param companyCode
 * @return com.multibrand.vo.response.ProspectDataResponse
 */
public ProspectDataResponse getProspectData(String prospectId, String  lastFourDigitSsn,String companyCode) {
	
	ProspectDataResponse response = new ProspectDataResponse();
		
	if(StringUtils.isBlank(prospectId))
		{  
		response.setStatusCode(Constants.STATUS_CODE_STOP);
		response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
		response.setResultDescription("ProspectID may not be Empty");
		response.setErrorCode(HTTP_BAD_REQUEST);
		response.setErrorDescription(response.getResultDescription());
		response.setHttpStatus(Response.Status.BAD_REQUEST);
		return response;
		}
	if( StringUtils.isBlank(lastFourDigitSsn))
	{  
		response.setStatusCode(Constants.STATUS_CODE_STOP);
		response.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
		response.setResultDescription("LastfourdigitSSN may not be Empty");
		response.setErrorCode(HTTP_BAD_REQUEST);
		response.setErrorDescription(response.getResultDescription());
		response.setHttpStatus(Response.Status.BAD_REQUEST);
		return response;
	}
		
	try {
		
		ProspectRequest prospectRequest = new ProspectRequest();
		prospectRequest.setCompanyCode(companyCode);
		prospectRequest.setLastfourdigitSSN(lastFourDigitSsn);
		prospectRequest.setProspectId(prospectId);
		
		ProspectResponse prospectResponse = oeService.getProspectData(prospectRequest);
		
		if (prospectResponse != null && prospectResponse.getStatusCode().equalsIgnoreCase(S_VALUE)){
			response.setProspectBpID(prospectResponse.getPartner());
			response.setProspectBpIDType(prospectResponse.getBpType());
			response.setProspectCreditBucket(prospectResponse.getCreditBucket());
			response.setProspectCreditScore(prospectResponse.getCreditScore());
			response.setProspectCreditScoreDate(prospectResponse.getCreditDate());
			response.setProspectCreditSource(prospectResponse.getCreditSource());
			response.setProspectPreApprovalFlag(prospectResponse.getCreditSegmentIndicator());
			response.setStatusCode(Constants.STATUS_CODE_CONTINUE);
			response.setResultCode(RESULT_CODE_SUCCESS);
		}else{
			response.setStatusCode(Constants.STATUS_CODE_STOP);
			response.setMessageCode(NO_PROSPECT_MATCH_FOUND);
			if(prospectResponse != null){
				response.setMessageText(prospectResponse.getErrorMessage());//Jay to confirm the msg
			}
			response.setHttpStatus(Response.Status.BAD_REQUEST);
		}
		
	} catch (Exception e) {
		response.setStatusCode(Constants.STATUS_CODE_STOP);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		response.setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR);
		response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION);
		logger.error("Exception in getting Prospect Details: ", e);
	}
	logger.info("ProspectDataResponse : ResultCode : "+response.getResultCode());
	return response;
	
	}

}

	