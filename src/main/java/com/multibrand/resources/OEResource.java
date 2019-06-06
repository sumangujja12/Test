
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.ValidationBO;
import com.multibrand.domain.BankDetailsValidationRequest;
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
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.UpdateETFFlagToCRMRequest;
import com.multibrand.dto.request.TLPOfferRequest;
import com.multibrand.dto.request.UCCDataRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.BankDepositPaymentResponse;
import com.multibrand.dto.response.CCDepositPaymentResponse;
import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.dto.response.CheckPermitResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.UpdateETFFlagToCRMResponse;
import com.multibrand.dto.response.TLPOfferResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.exception.OEException;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GMEEnviornmentalImpact;
import com.multibrand.vo.response.GiactBankValidationResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.OfferResponse;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.vo.response.TdspResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@Component
@Path("oeResource")
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

	/**
	 * 
	 * This call determines whether a deposit will be required based on the
	 * information provided, and provides details about the amount of the
	 * deposit and reasons for deposit if one is required.
	 * 
	 * @param affiliateId
	 * @param companyCode
	 * @param brandId
	 * @param locale
	 * @param trackingId
	 * @param firstName
	 * @param lastName
	 * @param tokenizedSSN
	 * @param matchedBP
	 * @param transactionType
	 * @param esid
	 * @param offerCode
	 * @param mviDate
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servCity
	 * @param servState
	 * @param servZipCode
	 * @param billStreetNum
	 * @param billStreetName
	 * @param billStreetAptNum
	 * @param billCity
	 * @param billState
	 * @param billZipCode
	 * @param billPOBox
	 * @param bpMatchFlag
	 *            (BPSD or blank)
	 * @return {@link com.multibrand.vo.response.NewCreditScoreResponse
	 *         NewCreditScoreResponse}
	 * 
	 */

	@POST
	@Path("performCreditCheck")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditCheck(
			@Valid CreditCheckRequest creditCheckRequest) throws OEException {
		/* author Mayank Mishra */
		Response response = null;
		HashMap<String, Object> mandatoryParamList = new HashMap<String, Object>();

		if (StringUtils.isBlank(creditCheckRequest.getBillStreetNum())
				&& StringUtils.isBlank(creditCheckRequest.getBillStreetName())) {
			// Either Billing PO box or Billing Street num/name should be
			// supplied
			mandatoryParamList.put("billPOBox",
					creditCheckRequest.getBillPOBox());
		} else {
			mandatoryParamList.put("billStreetNum",
					creditCheckRequest.getBillStreetNum());
			mandatoryParamList.put("billStreetName",
					creditCheckRequest.getBillStreetName());
		}

		if (StringUtils.isBlank(creditCheckRequest.getLanguageCode()))
			creditCheckRequest
					.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);

		HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
				.checkMandatoryParam(mandatoryParamList);

		String resultCode = (String) mandatoryParamCheckResponse
				.get("resultCode");

		logger.debug("inside performCreditCheck:: resultcode is :: "+resultCode);
		
		if (StringUtils.isNotBlank(creditCheckRequest.getBpMatchFlag())
				&& creditCheckRequest.getBpMatchFlag().equalsIgnoreCase(BPSD))
			creditCheckRequest.setMatchedBP(EMPTY);

		if (StringUtils.isNotBlank(resultCode)
				&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {
			NewCreditScoreResponse newCreditScoreResponse = oeBO
					.performCreditCheck(oeRequestHandler
							.createNewCreditScoreRequest(creditCheckRequest),
							creditCheckRequest);
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

		return response;
	}

	/**
	 * 
	 * This call locates the unique identifier needed to ensure electricity
	 * service at the correct location, identifies any issues with the location
	 * that might affect receiving service, and determines available dates to
	 * start service for eligible locations.
	 * 
	 * @param affiliateId
	 * @param companyCode
	 *            (0121 - Reliant)
	 * @param brandId
	 *            (PW - Pennywise, CE - Cirro, blank)
	 * @param locale
	 *            (E - English or S - Spanish or null - defaulted to E)
	 * @param trackingId
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servZipCode
	 *            (Zip or Zip+5)
	 * @param transactionType
	 *            (Should be either MVI or SWI)
	 * @param bpMatchFlag
	 *            (BPSD or blank)
	 * @param tdspCodeCCS
	 *            (D0001, D0002...)
	 * @return {@link com.multibrand.vo.response.EsidInfoTdspCalendarResponse
	 *         EsidInfoTdspCalendarResponse}
	 */
	@POST
	@Path("getESIDAndCalendarDates")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDAndCalendarDates(
			@Valid EsidCalendarRequest esidCalendarRequest) {
		/* author Mayank Mishra */
		Response response = null;

		if (StringUtils.isBlank(esidCalendarRequest.getLanguageCode()))
			esidCalendarRequest
					.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);

		EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
				.getESIDAndCalendarDates(esidCalendarRequest.getCompanyCode(),
						esidCalendarRequest.getAffiliateId(),
						esidCalendarRequest.getBrandId(),
						esidCalendarRequest.getServStreetNum(),
						esidCalendarRequest.getServStreetName(),
						esidCalendarRequest.getServStreetAptNum(),
						esidCalendarRequest.getServZipCode(),
						esidCalendarRequest.getTdspCodeCCS(),
						esidCalendarRequest.getTransactionType(),
						esidCalendarRequest.getTrackingId(),
						esidCalendarRequest.getBpMatchFlag(),
						esidCalendarRequest.getLanguageCode(),
						esidCalendarRequest.getEsid(),
						httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK)
				.entity(esidInfoTdspResponse).build();

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
	 * 
	 * This call takes all the relevant information and submits for service,
	 * tagging those that require manual intervention within our system.
	 * Successful enrollments will return a contract account number. (Does not
	 * include deposit payment handling.)
	 * 
	 * <p>
	 * The following properties of the enrollment are included in the request.
	 * <ul>
	 * <li><strong>affiliateId<strong>
	 * <li><strong>companyCode<strong>
	 * <li><strong>brandId<strong>
	 * <li><strong>trackingId<strong>
	 * <li><strong>languageCode<strong>
	 * <li><strong>ebillFlag<strong>
	 * <li><strong>bpMatchFlag<strong>
	 * <li><strong>mviDate<strong>
	 * <li><strong>transactionType<strong>
	 * <li><strong>tdspCodeCCS<strong>
	 * <li><strong>offerDate<strong>
	 * <li><strong>offerTime<strong>
	 * <li><strong>switchHoldFlag<strong>
	 * <li><strong>mktPref<strong>
	 * <li><strong>matchedBP<strong>
	 * <li><strong>campaignCode<strong>
	 * <li><strong>offerCode<strong>
	 * <li><strong>promoCode<strong>
	 * <li><strong>productPriceCode<strong>
	 * <li><strong>incentiveCode<strong>
	 * <li><strong>marketSegment<strong>
	 * <li><strong>firstName<strong>
	 * <li><strong>middleName<strong>
	 * <li><strong>lastName<strong>
	 * <li><strong>maidenName<strong>
	 * <li><strong>dob<strong>
	 * <li><strong>posidDLDate<strong>
	 * <li><strong>posidSSNDate<strong>
	 * <li><strong>email<strong>
	 * <li><strong>phoneNum<strong>
	 * <li><strong>tokenizedSSN<strong>
	 * <li><strong>tokenizedTDL<strong>
	 * <li><strong>billStreetName<strong>
	 * <li><strong>billStreetNum<strong>
	 * <li><strong>billStreetAptNum<strong>
	 * <li><strong>billCity<strong>
	 * <li><strong>billState<strong>
	 * <li><strong>billZipCode<strong>
	 * <li><strong>servStreetName<strong>
	 * <li><strong>servStreetNum<strong>
	 * <li><strong>servStreetAptNum<strong>
	 * <li><strong>servCity<strong>
	 * <li><strong>servState<strong>
	 * <li><strong>servZipCode<strong>
	 * <li><strong>creditSource<strong>
	 * <li><strong>creditBucket<strong>
	 * <li><strong>creditScore<strong>
	 * <li><strong>depositAmount<strong>
	 * <li><strong>creditFactors<strong>
	 * <li><strong>esid<strong>
	 * <li><strong>referrerId<strong>
	 * <li><strong>preferredLanguage<strong>
	 * </ul>
	 * </p>
	 * 
	 * @param enrollmentRequest
	 *            instance of <code>EnrollmentRequest</code>.
	 * 
	 * @return Response the enrollment output (JSON format).
	 * 
	 * @throws OEException
	 *             if the enrollment call contains any error or failed.
	 * 
	 * 
	 * @author Jenith
	 */
	@POST
	@Path("submitEnrollment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(@Valid EnrollmentRequest enrollmentRequest)
			throws OEException {
		Response response = null;

		// Start Submit enrollment call
		EnrollmentResponse enrollmentResponse = oeBO
				.submitEnrollment(enrollmentRequest);

		// Build Submit enrollment response
		response = Response.status(Response.Status.OK)
				.entity(enrollmentResponse).build();

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

	// START ONLINE AFFILIATES PROJECT - JSINGH1
	// @SuppressWarnings("unchecked")
	/**
	 * This call performs online verification of prospect's identification and
	 * checks for pre-existing matching customer information that may affect
	 * handling of this enrollment request.
	 * <ul>
	 * <li><strong> lastName<strong>
	 * <li><strong> firstName<strong>
	 * <li><strong> middleName <strong>
	 * <li><strong> dob<strong>
	 * <li><strong> tdl<strong>
	 * <li><strong> ssn<strong>
	 * <li><strong> maidenName<strong>
	 * <li><strong> companyCode <strong>
	 * <li><strong> brandId <strong>
	 * <li><strong> affiliateId <strong>
	 * <li><strong>trackingId<strong>
	 * <li><strong> email <strong>
	 * <li><strong> languageCode<strong>
	 * <li><strong> phoneNumber <strong>
	 * <li><strong> mktPref<strong>
	 * <li><strong> transactionType <strong>
	 * <li><strong> servStreetNum <strong>
	 * <li><strong> servStreetName <strong>
	 * <li><strong> servStreetAptNum <strong>
	 * <li><strong> servCity <strong>
	 * <li><strong> servState <strong>
	 * <li><strong> servZipCode<strong>
	 * <li><strong> billStreetNum <strong>
	 * <li><strong> billStreetName <strong>
	 * <li><strong> billStreetAptNum <strong>
	 * <li><strong> billCity <strong>
	 * <li><strong> billState <strong>
	 * <li><strong> billZipCode <strong>
	 * <li><strong> billPOBox<strong>
	 * </ul>
	 * 
	 * @return {@link com.multibrand.vo.response.TokenizedResponse
	 *         TokenizedResponse},
	 *         {@link com.multibrand.vo.response.PerformPosIdandBpMatchResponse
	 *         PerformPosIdandBpMatchResponse}
	 */
	@POST
	@Path("performPosidAndBpMatch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performPosidAndBpMatch(
			@Valid PerformPosIdAndBpMatchRequest performPosIdBpRequest) {
		
		/* author Jasveen Singh */
		logger.info(" START ******* performPosidAndBpMatch API**********");

		Response response = null;
		String dobForPosId=null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		String resultCode = null;
		String errorDesc = null;
		boolean isValidAge = false;
		OEBO oeBo = null;
		TokenizedResponse tokenResponse = null;
		Map<String, Object> getPosIdTokenResponse = null;
		
		// Start Validating DOB- Jsingh1
		//Checking if DOB lies in Valid age Range (18-100)
		try{
			logger.info("inside performPosidAndBpMatch:: formatting DOB to Posid acceptable format");
			
			dobForPosId=CommonUtil.formatDateForNrgws(performPosIdBpRequest.getDob());
			performPosIdBpRequest.setDobForPosId(dobForPosId);
			
			logger.info("inside performPosidAndBpMatch:: dob for posid call is:: "+dobForPosId);
			
			mandatoryParamList = new HashMap<String, Object>();

			if (StringUtils.isBlank(performPosIdBpRequest.getBillStreetNum())
					&& StringUtils.isBlank(performPosIdBpRequest.getBillStreetName())) {
				// Either Billing PO box or Billing Street num/name should be supplied
				mandatoryParamList.put("billPOBox",
						performPosIdBpRequest.getBillPOBox());
			} else {
				mandatoryParamList.put("billStreetNum",
						performPosIdBpRequest.getBillStreetNum());
				mandatoryParamList.put("billStreetName",
						performPosIdBpRequest.getBillStreetName());
			}

			mandatoryParamCheckResponse = CommonUtil
			.checkMandatoryParam(mandatoryParamList);
			resultCode = (String) mandatoryParamCheckResponse
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
				logger.info("Inside performCreditCheck:: errorDesc is " + errorDesc);
			
				return response;
			}
			
			isValidAge=validationBO.getValidAge(dobForPosId);
			if( !isValidAge )
			{
				logger.info("inside performPosidAndBpMatch::Invalid Age: Prospect must be at least 18 years old but not "
						+ "over 100 years old or invalid date format");
				PerformPosIdandBpMatchResponse validPosIdResponse= validationBO.getInvalidDOBResponse(performPosIdBpRequest.getAffiliateId(),
						performPosIdBpRequest.getTrackingId());				
				
				response = Response.status(200).entity(validPosIdResponse)
						.build();
				return response;
			}
		}
		catch(Exception e)
		{
			logger.info("inside performPosidAndBpMatch :: unable to validate age for the prospect.", e);
		}				
		//End Validating DOB- Jsingh1
		
		oeBo = new OEBO();
		tokenResponse = new TokenizedResponse();
		getPosIdTokenResponse = new HashMap<String, Object>();
		
		// Changing language code to suitable locale
		performPosIdBpRequest.setLanguageCode(CommonUtil
				.localeCode(performPosIdBpRequest.getLanguageCode()));
		logger.info("inside validatePosId::after local change languageCode langauge is :: "
				+ performPosIdBpRequest.getLanguageCode());
		
		getPosIdTokenResponse = oeBo.getPosIdTokenResponse(
				performPosIdBpRequest.getTdl(), performPosIdBpRequest.getSsn(),
				performPosIdBpRequest.getAffiliateId(),
				performPosIdBpRequest.getTrackingId());
		
		if (getPosIdTokenResponse != null) {
			tokenResponse = (TokenizedResponse) getPosIdTokenResponse
					.get("tokenResponse");
			performPosIdBpRequest.setTokenTDL((String) getPosIdTokenResponse
					.get("tokenTdl"));
			performPosIdBpRequest.setTokenSSN((String) getPosIdTokenResponse
					.get("tokenSSN"));

			if (tokenResponse.getResultCode().equals(Constants.RESULT_CODE_SUCCESS)
			&& StringUtils.isNotBlank(tokenResponse.getReturnToken())) {
				logger.info("inside performPosidAndBpMatch:: affiliate Id : "
						+ performPosIdBpRequest.getAffiliateId()
						+ ":: got token back.");
				if (!CommonUtil.checkTokenDown(tokenResponse.getReturnToken())) {
					
					PerformPosIdandBpMatchResponse validPosIdResponse = validationBO
							.validatePosId(performPosIdBpRequest );
					response = Response.status(200).entity(validPosIdResponse)
							.build();
					logger.info("inside performPosidAndBpMatch:: affiliate Id : "
							+ performPosIdBpRequest.getAffiliateId()
							+ "::rendering response pojo :: " + response);
					
					// Start : Validate for Power Genius Online Affiliates by KB
					logger.info("affiliate Id : "
					+ performPosIdBpRequest.getAffiliateId()
					+ "::validPosIdResponse.getBpMatchFlag() : " 
					+ validPosIdResponse.getBpMatchFlag() 
					+ "::validPosIdResponse.getStatusCode() :"
					+ validPosIdResponse.getStatusCode());
					
					logger.info("Condition 1 : "  + ((performPosIdBpRequest.getAffiliateId()!=null && 
							(performPosIdBpRequest.getAffiliateId().trim() == "372529" ||
							performPosIdBpRequest.getAffiliateId().trim().equals("372529")))));
					
					logger.info("Condition 2 : "  + (validPosIdResponse.getBpMatchFlag()!=null &&
							!validPosIdResponse.getBpMatchFlag().trim().isEmpty()));
					
					logger.info("Condition 3 : "  + (validPosIdResponse.getStatusCode() !=null && 
							validPosIdResponse.getStatusCode()!="00"));
					
					logger.info("Condition : "  + ((performPosIdBpRequest.getAffiliateId()!=null && 
							(performPosIdBpRequest.getAffiliateId().trim() == "372529" ||
							performPosIdBpRequest.getAffiliateId().trim().equals("372529"))) &&
									
							(validPosIdResponse.getBpMatchFlag()!=null && 
							!validPosIdResponse.getBpMatchFlag().trim().isEmpty()) &&
									
							(validPosIdResponse.getStatusCode() !=null && 
							validPosIdResponse.getStatusCode()!="00")));
							
					if(
							(performPosIdBpRequest.getAffiliateId()!=null && 
							(performPosIdBpRequest.getAffiliateId().trim() == "372529" ||
							performPosIdBpRequest.getAffiliateId().trim().equals("372529"))) &&
							
							(validPosIdResponse.getBpMatchFlag()!=null && 
							!validPosIdResponse.getBpMatchFlag().trim().isEmpty()) &&
							
							(validPosIdResponse.getStatusCode()!=null && 
							validPosIdResponse.getStatusCode()!="00")){				
						
						logger.info("inside sendPowerGeniusConfirmationEmail");
						try{
							oeBo.sendPowerGeniusConfirmationEmail(performPosIdBpRequest.getEmail());
						}catch(Exception e){
							logger.error("inside performPosidAndBpMatch :: email sent failed for Power genius Online affiliates.", e);							
						}
					}
					// End : Validate for Power Genius Online Affiliates by KB
				}

				else { // returning exception and error as token server is down
					logger.info("inside performPosidAndBpMatch:: Token Server Down ");
					tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
					tokenResponse.setMessageText(msgSource
							.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
					tokenResponse
							.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
					response = Response.status(200).entity(tokenResponse)
							.build();
					return response;
				}
			} else if (tokenResponse.getResultCode().equals(
			Constants.RESULT_CODE_EXCEPTION_FAILURE)) { // if validation fail for this scenario

				response = Response.status(200).entity(tokenResponse).build();
				return response;
			} else {
				tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
				tokenResponse.setMessageText(msgSource
						.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
				tokenResponse
						.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
				response = Response.status(200).entity(tokenResponse).build();
				return response;
			}
		} else {
			tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
			tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
			tokenResponse.setMessageText(msgSource
					.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
			tokenResponse
					.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			response = Response.status(200).entity(tokenResponse).build();
			return response;
		}
		return response;
	}
	// END ONLINE AFFILIATES PROJECT - JSINGH1

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
	
	@POST
	@Path("getAffiliateOffers")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAffiliateOffers(
			@Valid AffiliateOfferRequest request) {
		Response response = null;
		AffiliateOfferResponse offerResponse = oeBO.getAffiliateOffers(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(offerResponse).build();
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
		response = Response.status(Response.Status.OK).entity(agentDetailsResponse).build();
		return response;
	}
	

	
	@POST
	@Path("submitUCCData")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitUCCData(@Valid UCCDataRequest request) {

		Response response = null;
		String errorDesc = null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		
		mandatoryParamList = new HashMap<String, Object>();

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
	public Response validateBankDetailsGiact(BankDetailsValidationRequest bankDetailsValidationRequest) {
		Response response;
		GiactBankValidationResponse bankDetailsValidationResponse = oeBO.validateBankDetailsGiact(bankDetailsValidationRequest);
		response = Response.status(Response.Status.OK).entity(bankDetailsValidationResponse).build();
		return response;
	}
}
