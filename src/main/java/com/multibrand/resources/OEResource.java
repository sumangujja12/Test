
package com.multibrand.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.ValidationBO;
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
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.vo.response.TdspResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@RestController
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

	@Autowired
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
	@PostMapping(value = "/oeResource/getOffers", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getOffers(@RequestParam("languageCode") String locale,
			@RequestParam("companyCode") String companyCode,
			@RequestParam("brandId") String brandId,
			@RequestParam("servStreetNum") String servStreetNum,
			@RequestParam("servStreetName") String servStreetName,
			@RequestParam("servStreetAptNum") String servStreetAptNum,
			@RequestParam("servZipCode") String servZipCode,
			@RequestParam("promoCode") String promoCode,
			@RequestParam("tdspCode") String tdspCode,
			@RequestParam("esid") String esid,
			@RequestParam("transactionType") String transactionType) {
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
		//Response response = null;
		OfferResponse offerResponse = oeBO.getOffers(locale, companyCode,
				brandId, servStreetNum, servStreetName, servStreetAptNum,
				servZipCode, promoCode, tdspCode, esid,
				httpRequest.getSession(true).getId(), transactionType);
		//response = Response.status(200).entity(offerResponse).build();
		return new ResponseEntity<GenericResponse>(offerResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/getTDSPDetails", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getTDSPDetails(
			@RequestParam("companyCode") String companyCode,
			@RequestParam("brandId") String brandId,
			@RequestParam("servStreetNum") String servStreetNum,
			@RequestParam("servStreetName") String servStreetName,
			@RequestParam("servStreetAptNum") String servStreetAptNum,
			@RequestParam("servZipCode") String servZipCode) {

		//Response response = null;
		TdspResponse tdspResponse = oeBO.getTDSPDetails(companyCode, brandId,
				servStreetNum, servStreetName, servStreetAptNum, servZipCode,
				httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(tdspResponse).build();
		return new ResponseEntity<GenericResponse>(tdspResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/getToken/{actionCode}/{numToBeTokenized}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<GenericResponse> getToken(
			@PathVariable(value = "actionCode") String actionCode,
			@PathVariable(value = "numToBeTokenized") String numToBeTokenized) {
		/* author Mayank Mishra */
		StringBuffer logBuffer = new StringBuffer("OEResource.getToken()::");
		logBuffer.append("actionCode=" + actionCode);
		logBuffer.append("~numToBeTokenized=" + numToBeTokenized);
		logger.debug(logBuffer.toString());
		//Response response = null;
		TokenizedResponse tokenizedResponse = oeBO
				.getTokenResponse(oeRequestHandler.createTokenRequest(
						actionCode, numToBeTokenized));

		//response = Response.status(Response.Status.OK)
		//		.entity(tokenizedResponse).build();

		return new ResponseEntity<GenericResponse>(tokenizedResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/performCreditCheck", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> performCreditCheck(
			@Valid CreditCheckRequest creditCheckRequest) throws OEException {
		/* author Mayank Mishra */
		ResponseEntity<GenericResponse> response = null;
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
			response = new ResponseEntity<GenericResponse>(newCreditScoreResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/getESIDAndCalendarDates", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getESIDAndCalendarDates(
			@Valid EsidCalendarRequest esidCalendarRequest) {
		/* author Mayank Mishra */
		//Response response = null;

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
		//response = Response.status(Response.Status.OK)
		//		.entity(esidInfoTdspResponse).build();

		return new ResponseEntity<GenericResponse>(esidInfoTdspResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/oeResource/get/person/affiliate/id/{trackingNo}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<String> getPersonIdByTrackingNo(
			@PathVariable("trackingNo") String trackingNo) {
		String personId = oeBO.getPersonIdByTrackingNo(trackingNo);
		return new ResponseEntity<String>(personId, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/get/person/affiliate/idretrycount/{trackingNo}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<List<Map<String, String>>> getPersonIdAndRetryCountByTrackingNo(
			@PathVariable("trackingNo") String trackingNo) {
		List<Map<String, String>> dataList = oeBO
				.getPersonIdAndRetryCountByTrackingNo(trackingNo);
		return new ResponseEntity<List<Map<String, String>>>(dataList, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/add/person/affiliate/{affiliateId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<String> addPerson(@PathVariable("affiliateId") String affiliateId,
			AddPersonRequest request) {
		String personId = oeBO.addPerson(request);
		
		return new ResponseEntity<String>(personId, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/update/person/affiliate/{affiliateId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<String>  updatePerson(@PathVariable("affiliateId") String affiliateId,
			UpdatePersonRequest request) {
		String errorCode = oeBO.updatePerson(request);
		return new ResponseEntity<String>(errorCode, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/get/person/affiliate/{personId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<Object>  getPerson(@PathVariable("personId") String personId) {
		PersonResponse personResponse = oeBO.getPerson(personId);
		return new ResponseEntity<Object>(personResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/add/serviceLocation/affiliate/{affiliateId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<String>  addServiceLocation(
			@PathVariable("affiliateId") String affiliateId,
			AddServiceLocationRequest request) {
		request.setAffiliateId(affiliateId);
		String trackingNo = oeBO.addServiceLocation(request);
		return new ResponseEntity<String>(trackingNo, HttpStatus.OK);
	}

	@PostMapping(value = "/oeResource/update/serviceLocation/affiliate/{affiliateId}", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<String>  updateServiceLocation(
			@PathVariable("affiliateId") String affiliateId,
			UpdateServiceLocationRequest request) {
		request.setAffiliateId(affiliateId);
		String errorCode = oeBO.updateServiceLocation(request);
		return new ResponseEntity<String>(errorCode, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/submitEnrollment", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,  MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitEnrollment(@Valid EnrollmentRequest enrollmentRequest)
			throws OEException {
		//Response response = null;

		// Start Submit enrollment call
		EnrollmentResponse enrollmentResponse = oeBO
				.submitEnrollment(enrollmentRequest);

		// Build Submit enrollment response
		//response = Response.status(Response.Status.OK)
		//		.entity(enrollmentResponse).build();

		return new ResponseEntity<GenericResponse>(enrollmentResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/checkPendingRequest", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<GenericResponse> checkPendingRequest(
			CheckPendingServiceRequest checkPendingServiceRequest)
			throws OEException {

		//Response response = null;

		CheckPendingServiceResponse finalPendingServiceResponse = oeBO
				.checkPendingRequest(checkPendingServiceRequest);

		//response = Response.status(Response.Status.OK)
		//		.entity(finalPendingServiceResponse).build();

		return new ResponseEntity<GenericResponse>(finalPendingServiceResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/checkPermitRequirement", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@Deprecated
	private ResponseEntity<GenericResponse> checkPermitRequirement(
			CheckPermitRequest checkPermitRequest) throws OEException {

		//Response response = null;

		CheckPermitResponse permitCheckResponse = oeBO.checkPermitRequirement(
				checkPermitRequest, httpRequest.getSession(true).getId());

		//response = Response.status(Response.Status.OK)
		//		.entity(permitCheckResponse).build();

		return new ResponseEntity<GenericResponse>(permitCheckResponse, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/performPosidAndBpMatch", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> performPosidAndBpMatch(
			@Valid PerformPosIdAndBpMatchRequest performPosIdBpRequest) {
		
		/* author Jasveen Singh */
		logger.info(" START ******* performPosidAndBpMatch API**********");

		ResponseEntity<GenericResponse> response = null;
		String dobForPosId=null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		String resultCode = null;
		String errorDesc = null;
		boolean isValidAge = false;
		AgentDetailsResponse agentDetailsResponse;
		OEBO oeBo = null;
		TokenizedResponse tokenResponse = null;
		Map<String, Object> getPosIdTokenResponse = null;
		OESignupDTO oESignupDTO = new OESignupDTO();
		GenericResponse genericResponse;
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
			if(StringUtils.equalsIgnoreCase(Constants.DSI_AGENT_ID,performPosIdBpRequest.getAffiliateId())){
				mandatoryParamList.put("agentId",
						performPosIdBpRequest.getAgentID());
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
					response  = CommonUtil.buildNotValidResponse(errorDesc,
					Constants.STATUS_CODE_ASK);
				}
				logger.info("Inside performCreditCheck:: errorDesc is " + errorDesc);
			
				//return response;
				return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK); 
			}
			
			isValidAge=validationBO.getValidAge(dobForPosId);
			if( !isValidAge )
			{
				logger.info("inside performPosidAndBpMatch::Invalid Age: Prospect must be at least 18 years old but not "
						+ "over 100 years old or invalid date format");
				PerformPosIdandBpMatchResponse validPosIdResponse= validationBO.getInvalidDOBResponse(performPosIdBpRequest.getAffiliateId(),
						performPosIdBpRequest.getTrackingId());				
				
				response = new ResponseEntity<GenericResponse>(validPosIdResponse, HttpStatus.OK);
				return response;
			}
			//START : OE :Sprint61 :US21009 :Kdeshmu1
			if(StringUtils.isNotBlank(performPosIdBpRequest.getAgentID())){
				agentDetailsResponse=validationBO.validateAgentID(performPosIdBpRequest.getAgentID());
				if(!RESULT_CODE_SUCCESS.equalsIgnoreCase(agentDetailsResponse.getResultCode()))
				{
					logger.info("Agent Id is not valid");
					PerformPosIdandBpMatchResponse validPosIdResponse= validationBO.getInvalidAgentIDResponse(performPosIdBpRequest.getAgentID(),
							performPosIdBpRequest.getTrackingId());				
					
					response = new ResponseEntity<GenericResponse>(validPosIdResponse, HttpStatus.OK); 
					return response;
				}else{
					oESignupDTO.setAgentID(performPosIdBpRequest.getAgentID());
					oESignupDTO.setAgentFirstName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentFirstName());
					oESignupDTO.setAgentLastName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentLastName());
					oESignupDTO.setAgentType(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentType());
					oESignupDTO.setVendorCode(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentVendorCode());
					oESignupDTO.setVendorName(agentDetailsResponse.getAgentDetailsResponseOutData().getResult().get(0).getAgentVendorName());
				}
			}//END : OE :Sprint61 :US21009 :Kdeshmu1
			
		}
		catch(Exception e)
		{
			logger.info("inside performPosidAndBpMatch :: unable to validate age or Agent ID for the prospect.", e);
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
							.validatePosId(performPosIdBpRequest,oESignupDTO );
					response = new ResponseEntity<GenericResponse>(validPosIdResponse, HttpStatus.OK);
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
					response = new ResponseEntity<GenericResponse>(tokenResponse, HttpStatus.OK);
					return response;
				}
			} else if (tokenResponse.getResultCode().equals(
			Constants.RESULT_CODE_EXCEPTION_FAILURE)) { // if validation fail for this scenario

				response = new ResponseEntity<GenericResponse>(tokenResponse, HttpStatus.OK);
				return response;
			} else {
				tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
				tokenResponse.setMessageText(msgSource
						.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
				tokenResponse
						.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
				response = new ResponseEntity<GenericResponse>(tokenResponse, HttpStatus.OK);
				return response;
			}
		} else {
			tokenResponse.setStatusCode(Constants.STATUS_CODE_STOP);
			tokenResponse.setMessageCode(Constants.TOKEN_SERVER_DOWN);
			tokenResponse.setMessageText(msgSource
					.getMessage(TOKEN_SERVER_DOWN_MSG_TXT));
			tokenResponse
					.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			response = new ResponseEntity<GenericResponse>(tokenResponse, HttpStatus.OK);
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
	
	@PostMapping(value = "/oeResource/submitBankDepositPayment", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitBankPayment(@Valid BankDepositPaymentRequest request) {

		//Response response = null;
		BankDepositPaymentResponse bankResp = oeBO.submitBankPayment(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(bankResp).build();
		return new ResponseEntity<GenericResponse>(bankResp, HttpStatus.OK);
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
	@PostMapping(value = "/oeResource/submitCCDepositPayment", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitCCDepositPayment(
			@Valid CCDepositPaymentRequest request) {

		//Response response = null;
		CCDepositPaymentResponse CCResp = oeBO.submitCCPayment(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(CCResp).build();
		return new ResponseEntity<GenericResponse>(CCResp, HttpStatus.OK);
	}
	
	@PostMapping(value = "/oeResource/getAffiliateOffers", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getAffiliateOffers(
			@Valid AffiliateOfferRequest request) {
		//Response response = null;
		AffiliateOfferResponse offerResponse = oeBO.getAffiliateOffers(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(offerResponse).build();
		return new ResponseEntity<GenericResponse>(offerResponse, HttpStatus.OK);
	}
	
	/**
	 * Alternate Channel : Sprint 13 :US 11783 
	 * @author KDeshmu1
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/oeResource/offer-data-for-TLP", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getOfferDataForTLP(
			@Valid TLPOfferRequest request) {
		//Response response = null;
		TLPOfferResponse offerResponse = oeBO.getOfferForTLP(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(offerResponse).build();
		return new ResponseEntity<GenericResponse>(offerResponse, HttpStatus.OK);
	}
	
	/**
	 * START : OE | Sprint 46 | US15066 | Kdeshmu1
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/oeResource/updateETFFlagToCRM", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> updateETFFlagToCRM(
			@Valid UpdateETFFlagToCRMRequest request) {
		//Response response = null;
		UpdateETFFlagToCRMResponse updateETFFlagToCRMResponse = oeBO.updateETFFlagToCRM(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(updateETFFlagToCRMResponse).build();
		return new ResponseEntity<GenericResponse>(updateETFFlagToCRMResponse, HttpStatus.OK);
	}
	

	/**
	 * Alternate Channel : Sprint 14 :US 11783 
	 * @author KDeshmu1
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/oeResource/getAgentDetails", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getAgentDetails(
			@Valid AgentDetailsRequest request) {
		//Response response = null;
		AgentDetailsResponse agentDetailsResponse = oeBO.getAgentDetails(request,
				httpRequest.getSession(true).getId());
		//response = Response.status(Response.Status.OK).entity(agentDetailsResponse).build();
		return new ResponseEntity<GenericResponse>(agentDetailsResponse, HttpStatus.OK);
	}
	

	@PostMapping(value = "/oeResource/submitUCCData", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitUCCData(@Valid UCCDataRequest request) {

		ResponseEntity<GenericResponse> response = null;
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
		response = new ResponseEntity<GenericResponse>(uccResp, HttpStatus.OK);
		return response;
	}
	
	/**
	 * This method is responsible for get environment impact for GME mobile app
	 * @author NGASPerera 
	 * @return
	 */
	@PostMapping(value = "/oeResource/getEnviornmentalImpactForAllGMECommunity", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getEnviornmentalImpactForAllGMECommunity(){
		//Response response;
		GMEEnviornmentalImpact impact = oeBO.getEnviornmentalImpactForAllGMECommunity();
		//response = Response.status(Response.Status.OK).entity(impact).build();
		return new ResponseEntity<Object>(impact, HttpStatus.OK);
	}
	
	/**
	 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
	 * @author Nkatragadda
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/oeResource/validateBankDetailsGiact", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE  }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> validateBankDetailsGiact(@Valid GiactBankValidationRequest bankDetailsValidationRequest) {
		//Response response;
		GiactBankValidationResponse bankDetailsValidationResponse = oeBO.validateBankDetailsGiact(bankDetailsValidationRequest);
		//response = Response.status(Response.Status.OK).entity(bankDetailsValidationResponse).build();
		return new ResponseEntity<Object>(bankDetailsValidationResponse, HttpStatus.OK);
	}
}
