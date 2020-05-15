
package com.multibrand.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.ValidationBO;
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
import com.multibrand.dto.request.EsidCalendarRequest;
import com.multibrand.dto.request.GiactBankValidationRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
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
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.TLPOfferResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.dto.response.UpdateETFFlagToCRMResponse;
import com.multibrand.exception.OEException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GMEEnviornmentalImpact;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.GiactBankValidationResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.OfferResponse;
import com.multibrand.vo.response.TdspResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@Component
@Path("/"+Constants.OE_RESOURCE_API_BASE_PATH)
public class OEResource extends BaseResource {
	
	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	// ~Autowire entries
	@Autowired
	OERequestHandler oeRequestHandler;

	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;

	/** Object of ValidationBO class. */
	@Autowired
	private ValidationBO validationBO;

	@Context
	private HttpServletRequest httpRequest;

	@Resource(name = "webI18nMessageSource")
	private WebI18nMessageSource msgSource;

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	/**
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
	 * @return Response
	 */
	@POST
	@Path("getOffers")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOffers(@FormParam("languageCode") String locale,
			@FormParam("companyCode") String companyCode,
			@FormParam("brandId") String brandId,
			@FormParam("servStreetNum") String servStreetNum,
			@FormParam("servStreetName") String servStreetName,
			@FormParam("servStreetAptNum") String servStreetAptNum,
			@FormParam("servZipCode") String servZipCode,
			@FormParam("promoCode") String promoCode,
			@FormParam("tdspCode") String tdspCode,
			@FormParam("esid") String esid,
			@FormParam("transactionType") String transactionType) {
		StringBuffer logBuffer = new StringBuffer("OEResource.getOffers()::");
		logBuffer.append("locale=" + locale);
		logBuffer.append("~companyCode=" + companyCode);
		logBuffer.append("~brandId=" + brandId);
		logBuffer.append("~servStreetNum=" + servStreetNum);
		logBuffer.append("~servStreetName=" + servStreetName);
		logBuffer.append("~servStreetAptNum=" + servStreetAptNum);
		logBuffer.append("~servZipCode=" + servZipCode);
		logBuffer.append("~promoCode=" + promoCode);
		logBuffer.append("~tdspCode=" + tdspCode);
		logBuffer.append("~esid=" + esid);
		logBuffer.append("~transactionType=" + transactionType);
		logger.debug(logBuffer.toString());
		Response response = null;
		OfferResponse offerResponse = oeBO.getOffers(locale, companyCode,
				brandId, servStreetNum, servStreetName, servStreetAptNum,
				servZipCode, promoCode, tdspCode, esid,
				httpRequest.getSession(true).getId(), transactionType);
		response = Response.status(200).entity(offerResponse).build();
		return response;
	}

	/*
	 * START - CIRRO ENERGY
	 */

	/**
	 * @param companyCode
	 * @param brandId
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servZipCode
	 * @return Response
	 */
	@POST
	@Path("getTDSPDetails")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTDSPDetails(
			@FormParam("companyCode") String companyCode,
			@FormParam("brandId") String brandId,
			@FormParam("servStreetNum") String servStreetNum,
			@FormParam("servStreetName") String servStreetName,
			@FormParam("servStreetAptNum") String servStreetAptNum,
			@FormParam("servZipCode") String servZipCode) {

		Response response = null;
		TdspResponse tdspResponse = oeBO.getTDSPDetails(companyCode, brandId,
				servStreetNum, servStreetName, servStreetAptNum, servZipCode,
				httpRequest.getSession(true).getId());
		response = Response.status(200).entity(tdspResponse).build();
		return response;
	}

	/*
	 * END - CIRRO ENERGY
	 */

	/**
	 * 
	 * Get tokenized information based on provided input
	 * 
	 * @param actionCode
	 * @param numToBeTokenized
	 * @return Response
	 */
	@POST
	@Path(value = "/getToken/{actionCode}/{numToBeTokenized}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response getToken(
			@PathParam(value = "actionCode") String actionCode,
			@PathParam(value = "numToBeTokenized") String numToBeTokenized) {
		/* author Mayank Mishra */
		StringBuffer logBuffer = new StringBuffer("OEResource.getToken()::");
		logBuffer.append("actionCode=" + actionCode);
		logBuffer.append("~numToBeTokenized=" + numToBeTokenized);
		logger.debug(logBuffer.toString());
		Response response = null;
		TokenizedResponse tokenizedResponse = oeBO
				.getTokenResponse(oeRequestHandler.createTokenRequest(
						actionCode, numToBeTokenized));

		response = Response.status(Response.Status.OK)
				.entity(tokenizedResponse).build();

		return response;
	}

	@POST
	@Path("get/person/affiliate/id/{trackingNo}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response getPersonIdByTrackingNo(
			@PathParam("trackingNo") String trackingNo) {
		String personId = oeBO.getPersonIdByTrackingNo(trackingNo);
		Response response = Response.status(Response.Status.OK)
				.entity(personId).build();
		return response;
	}

	@POST
	@Path("get/person/affiliate/idretrycount/{trackingNo}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response getPersonIdAndRetryCountByTrackingNo(
			@PathParam("trackingNo") String trackingNo) {
		List<Map<String, String>> dataList = oeBO
				.getPersonIdAndRetryCountByTrackingNo(trackingNo);
		Response response = Response.status(Response.Status.OK)
				.entity(dataList).build();
		return response;
	}

	@POST
	@Path("add/person/affiliate/{affiliateId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response addPerson(@PathParam("affiliateId") String affiliateId,
			AddPersonRequest request) {
		String personId = oeBO.addPerson(request);
		Response response = Response.status(Response.Status.OK)
				.entity(personId).build();
		return response;
	}

	@POST
	@Path("update/person/affiliate/{affiliateId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response updatePerson(@PathParam("affiliateId") String affiliateId,
			UpdatePersonRequest request) {
		String errorCode = oeBO.updatePerson(request);
		Response response = Response.status(Response.Status.OK)
				.entity(errorCode).build();
		return response;
	}

	@POST
	@Path("get/person/affiliate/{personId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response getPerson(@PathParam("personId") String personId) {
		PersonResponse personResponse = oeBO.getPerson(personId);
		Response response = Response.status(Response.Status.OK)
				.entity(personResponse).build();
		return response;
	}

	@POST
	@Path("add/serviceLocation/affiliate/{affiliateId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response addServiceLocation(
			@PathParam("affiliateId") String affiliateId,
			AddServiceLocationRequest request) {
		request.setAffiliateId(affiliateId);
		String trackingNo = oeBO.addServiceLocation(request);
		Response response = Response.status(Response.Status.OK)
				.entity(trackingNo).build();
		return response;
	}

	@POST
	@Path("update/serviceLocation/affiliate/{affiliateId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response updateServiceLocation(
			@PathParam("affiliateId") String affiliateId,
			UpdateServiceLocationRequest request) {
		request.setAffiliateId(affiliateId);
		String errorCode = oeBO.updateServiceLocation(request);
		Response response = Response.status(Response.Status.OK)
				.entity(errorCode).build();
		return response;
	}

	
	

	/**
	 * Checks if a Pending request exists for the same Service address.
	 * 
	 * @param checkPendingServiceRequest
	 * 
	 * @return
	 * @throws OEException
	 * 
	 * @author Jenith
	 */
	@POST
	@Path("checkPendingRequest")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response checkPendingRequest(
			CheckPendingServiceRequest checkPendingServiceRequest)
			throws OEException {

		Response response = null;

		CheckPendingServiceResponse finalPendingServiceResponse = oeBO
				.checkPendingRequest(checkPendingServiceRequest);

		response = Response.status(Response.Status.OK)
				.entity(finalPendingServiceResponse).build();

		return response;
	}

	/**
	 * 
	 * Checks permit requirements.
	 * 
	 * @param checkPermitRequest
	 * 
	 * @return
	 * @throws OEException
	 * 
	 * @author Jenith
	 * 
	 */
	@POST
	@Path("checkPermitRequirement")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Deprecated
	private Response checkPermitRequirement(
			CheckPermitRequest checkPermitRequest) throws OEException {

		Response response = null;

		CheckPermitResponse permitCheckResponse = oeBO.checkPermitRequirement(
				checkPermitRequest, httpRequest.getSession(true).getId());

		response = Response.status(Response.Status.OK)
				.entity(permitCheckResponse).build();

		return response;
	}

	/**
	 * After the user has tokenized their payment information by going to the
	 * NRG page for payment data entry, and has received a CA for submitting
	 * their enrollment request, this call can be used to submit a deposit
	 * payment using bank account information
	 * 
	 * <p>
	 * The following properties of the enrollment are included in the request.
	 * 
	 * <ul>
	 * <li><strong> affiliateId<strong>
	 * <li><strong> companyCode<strong>
	 * <li><strong> brandId<strong>
	 * <li><strong> trackingId<strong>
	 * <li><strong> languageCode<strong>
	 * <li><strong> caNumber<strong>
	 * <li><strong> bpid<strong>
	 * <li><strong> tokenizedBankAccountNumber <strong>
	 * <li><strong> tokenizedBankRoutingNumber <strong>
	 * <li><strong> depositAmount <strong>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @param request
	 *            instance of <code>BankDepositPaymentRequest</code>.
	 * 
	 * @return {@link com.multibrand.dto.response.BankDepositPaymentResponse
	 *         BankDepositPaymentResponse}
	 * 
	 * 
	 * @author Arumugam
	 */

	@POST
	@Path("submitBankDepositPayment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitBankPayment(@Valid BankDepositPaymentRequest request) {

		Response response = null;
		BankDepositPaymentResponse bankResp = oeBO.submitBankPayment(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(bankResp).build();
		return response;
	}

	/**
	 * After the user has tokenized their payment information by going to the
	 * NRG page for payment data entry, and has received a CA for submitting
	 * their enrollment request, this call can be used to submit a deposit
	 * payment using credit card information.
	 * <p>
	 * The following properties of the enrollment are included in the request.
	 * 
	 * <ul>
	 * <li><strong> affiliateId<strong>
	 * <li><strong> companyCode<strong>
	 * <li><strong> brandId<strong>
	 * <li><strong> trackingId<strong>
	 * <li><strong> languageCode<strong>
	 * <li><strong> caNumber<strong>
	 * <li><strong> bpid<strong>
	 * <li><strong> tokenizedCCNumber <strong>
	 * <li><strong> cvvNumber <strong>
	 * <li><strong> expirationDate <strong>
	 * <li><strong> billingZip <strong>
	 * <li><strong> depositAmount <strong>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @param request
	 *            instance of <code>CCDepositPaymentRequest</code>.
	 * 
	 * @return {@link com.multibrand.dto.response.CCDepositPaymentResponse
	 *         CCDepositPaymentResponse}
	 * 
	 * 
	 * @author Arumugam
	 */

	@POST
	@Path("submitCCDepositPayment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitCCDepositPayment(
			@Valid CCDepositPaymentRequest request) {

		Response response = null;
		CCDepositPaymentResponse CCResp = oeBO.submitCCPayment(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(CCResp).build();
		return response;
	}
	
		
	/**
	 * Alternate Channel : Sprint 13 :US 11783 
	 * @author KDeshmu1
	 * @param request
	 * @return
	 */
	@POST
	@Path("offer-data-for-TLP")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfferDataForTLP(
			@Valid TLPOfferRequest request) {
		Response response = null;
		TLPOfferResponse offerResponse = oeBO.getOfferForTLP(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(offerResponse).build();
		return response;
	}
	
	/**
	 * START : OE | Sprint 46 | US15066 | Kdeshmu1
	 * @param request
	 * @return
	 */
	@POST
	@Path("updateETFFlagToCRM")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateETFFlagToCRM(
			@Valid UpdateETFFlagToCRMRequest request) {
		Response response = null;
		UpdateETFFlagToCRMResponse updateETFFlagToCRMResponse = oeBO.updateETFFlagToCRM(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(updateETFFlagToCRMResponse).build();
		return response;
	}
	

	/**
	 * Alternate Channel : Sprint 14 :US 11783 
	 * @author KDeshmu1
	 * @param request
	 * @return
	 */
	@POST
	@Path("getAgentDetails")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAgentDetails(
			@Valid AgentDetailsRequest request) {
		Response response = null;
		AgentDetailsResponse agentDetailsResponse = oeBO.getAgentDetails(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(agentDetailsResponse).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	/**
	 * This method is responsible for get environment impact for GME mobile app
	 * @author NGASPerera 
	 * @return
	 */
	@POST
	@Path("/getEnviornmentalImpactForAllGMECommunity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEnviornmentalImpactForAllGMECommunity(){
		Response response;
		GMEEnviornmentalImpact impact = oeBO.getEnviornmentalImpactForAllGMECommunity();
		response = Response.status(Response.Status.OK).entity(impact).build();
		return response;
	}
	
	/**
	 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
	 * @author Nkatragadda
	 * @param request
	 * @return
	 */
	@POST
	@Path("validateBankDetailsGiact")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response validateBankDetailsGiact(@Valid GiactBankValidationRequest bankDetailsValidationRequest) {
		Response response;
		GiactBankValidationResponse bankDetailsValidationResponse = oeBO.validateBankDetailsGiact(bankDetailsValidationRequest);
		response = Response.status(Response.Status.OK).entity(bankDetailsValidationResponse).build();
		return response;
	}
	
	/*****************************************************************************************************************************************************************
	 ******************************************************************************** LEGACY SALES APIs BELOW ****************************************************************
	 *****************************************************************************************************************************************************************/
	@POST
	@Path(API_LEGACY_GET_AFFILIATE_OFFERS)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAffiliateOffers(	@Valid AffiliateOfferRequest request) {
		Response response=null;
		try{
			AffiliateOfferResponse offerResponse = oeBO.getAffiliateOffers(request,	httpRequest.getSession(true).getId());
			response = Response.status(Response.Status.OK).entity(offerResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			// Not logging Offer API calls - vsood
   			//utilityloggerHelper.logSalesAPITransaction(API_GET_AFFILIATE_OFFERS, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_LEGACY_PERFORM_POSID_AND_BPMATCH)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performPosidAndBpMatch(
			@Valid PerformPosIdAndBpMatchRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;		
		try{
			request.setCallExecuted(API_LEGACY_PERFORM_POSID_AND_BPMATCH);
			response = oeBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_LEGACY_PERFORM_POSID_AND_BPMATCH, false, request, response, CommonUtil.getElapsedTime(startTime),  CommonUtil.getTrackingIdFromResponse(response), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_LEGACY_GET_ESID_AND_CALENDAR_DATES)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDAndCalendarDates(
			@Valid EsidCalendarRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			request.setCallExecuted(API_LEGACY_GET_ESID_AND_CALENDAR_DATES);
			if (StringUtils.isBlank(request.getLanguageCode())) request.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
				EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
					.getESIDAndCalendarDates(request.getCompanyCode(),
							request.getAffiliateId(),
							request.getBrandId(),
							request.getServStreetNum(),
							request.getServStreetName(),
							request.getServStreetAptNum(),
							request.getServZipCode(),
							request.getTdspCodeCCS(),
							request.getTransactionType(),
							request.getTrackingId(),
							request.getBpMatchFlag(),
							request.getLanguageCode(),
							request.getEsid(),
							httpRequest.getSession(true).getId(),
							null,
							null, API_LEGACY_GET_ESID_AND_CALENDAR_DATES
							);
				response = Response.status(Response.Status.OK).entity(esidInfoTdspResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_LEGACY_GET_ESID_AND_CALENDAR_DATES, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_LEGACY_PERFORM_CREDIT_CHECK)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditCheck(
			@Valid CreditCheckRequest request) throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			request.setCallExecuted(API_LEGACY_PERFORM_CREDIT_CHECK);
			HashMap<String, Object> mandatoryParamList = new HashMap<String, Object>();
	
			if (StringUtils.isBlank(request.getBillStreetNum())
					&& StringUtils.isBlank(request.getBillStreetName())) {
				// Either Billing PO box or Billing Street num/name should be
				// supplied
				mandatoryParamList.put("billPOBox",
						request.getBillPOBox());
			} else {
				mandatoryParamList.put("billStreetNum",
						request.getBillStreetNum());
				mandatoryParamList.put("billStreetName",
						request.getBillStreetName());
			}
	
			if (StringUtils.isBlank(request.getLanguageCode()))
				request
						.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
	
			HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
					.checkMandatoryParam(mandatoryParamList);
	
			String resultCode = (String) mandatoryParamCheckResponse
					.get("resultCode");
	
			logger.debug("inside performCreditCheck:: resultcode is :: "+resultCode);
			
			if (StringUtils.isNotBlank(request.getBpMatchFlag())
					&& request.getBpMatchFlag().equalsIgnoreCase(BPSD))
				request.setMatchedBP(EMPTY);
	
			if (StringUtils.isNotBlank(resultCode)
					&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {
				NewCreditScoreResponse newCreditScoreResponse = oeBO
						.performCreditCheck(oeRequestHandler
								.createNewCreditScoreRequest(request),
								request,null);
				response = Response.status(Response.Status.OK)
						.entity(newCreditScoreResponse).build();
			} else {
				String errorDesc = (String) mandatoryParamCheckResponse
						.get("errorDesc");
				if (StringUtils.isNotBlank(errorDesc)) {
					response = CommonUtil.buildNotValidResponse(resultCode,
							errorDesc);
				} else {
					response = CommonUtil.buildNotValidResponse(errorDesc,
							Constants.STATUS_CODE_ASK);
				}
				logger.debug("Inside performCreditCheck:: errorDesc is " + errorDesc);
			}
			logger.debug("END ******* performCreditCheck API**********");
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_LEGACY_PERFORM_CREDIT_CHECK, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
		return response;
	}
	
	@POST
	@Path(API_LEGACY_SUBMIT_UCC_DATA)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitUCCData(@Valid UCCDataRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		String errorDesc = null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		
		mandatoryParamList = new HashMap<String, Object>();
		try{
			request.setCallExecuted(API_LEGACY_SUBMIT_UCC_DATA);
			// Either Billing PO box or Billing Street num/name should be supplied
			mandatoryParamList.put("firstName",
					request.getFirstName());

			mandatoryParamList.put("lastName",
					request.getLastName());
			mandatoryParamList.put("depositAmount",
					request.getDepositAmount());
		

		mandatoryParamCheckResponse = CommonUtil
		.checkMandatoryParam(mandatoryParamList);
		String resultCode = (String) mandatoryParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		
		HashMap<String, Object> nagativeParamList = null;
		HashMap<String, Object> negativeParamCheckResponse = null;
		
		nagativeParamList = new HashMap<String, Object>();


		nagativeParamList.put("depositAmount",
				request.getDepositAmount());

		if(StringUtils.isNotEmpty(request.getCreditScore())) {
			nagativeParamList.put("creditScore",
					request.getCreditScore());
		}

		

		negativeParamCheckResponse = CommonUtil
		.checkNegaviteValueInParam(nagativeParamList);
		 resultCode = (String) negativeParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) negativeParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		UCCDataResponse uccResp = oeBO.submitUCCData(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(uccResp).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_LEGACY_SUBMIT_UCC_DATA, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_LEGACY_SUBMIT_ENROLLMENT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(@Valid EnrollmentRequest request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			request.setCallExecuted(API_LEGACY_SUBMIT_ENROLLMENT);
	    	EnrollmentResponse enrollmentResponse = oeBO.submitEnrollment(request, null);
	    	response = Response.status(enrollmentResponse.getHttpStatus()).entity(enrollmentResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).getGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_LEGACY_SUBMIT_ENROLLMENT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	/*****************************************************************************************************************************************************************
	 ******************************************************************************** LEGACY SALES APIs ABOVE ****************************************************************
	 *****************************************************************************************************************************************************************/

}
