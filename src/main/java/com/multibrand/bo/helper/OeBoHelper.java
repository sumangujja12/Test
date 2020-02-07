package com.multibrand.bo.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.BaseBO;
import com.multibrand.bo.BillingBO;
import com.multibrand.bo.ProfileBO;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.domain.SubmitEnrollResponse;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactRequestAttNamValPairMapEntry;
import com.multibrand.domain.UpdatePhoneDO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.helper.AsyncHelper;
import com.multibrand.helper.EmailHelper;
import com.multibrand.proxy.OEProxy;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.LoggerUtil;

/**
 * This class contains the Business related helper methods for intermediate
 * calls request creation to other BO, Proxy layers, response data handlers and
 * other supported methods for BO class.
 * 
 * @author jyogapa1
 * 
 * @version 1.0
 * 
 */
@Component
public class OeBoHelper extends BaseBO {

	LoggerUtil logger = LoggerUtil.getInstance("OeBoHelper");

	// ~Autowire entries
	@Autowired
	OERequestHandler oeRequestHandler;

	@Autowired
	ProfileBO profileBO;

	@Autowired
	BillingBO billingBO;

	@Autowired
	EmailHelper emailHelper;
	
	@Autowired
	private AsyncHelper asyncHelper; 

	@Autowired
	OEProxy oeProxy;

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	protected Boolean callUpdateAlertPrefernces(OESignupDTO oeSignUpDTO) {

		String METHOD_NAME = "OeBoHelper: callUpdateAlertPrefernces(..)";

		Boolean isCallSuccess = false;

		logger.debug("Start:" + METHOD_NAME);
		logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME);

		oeSignUpDTO.setRecentCallMade(CALL_NAME_UPDATE_ALERT_PREFERENCES);

	/*	try {
			UpdateAlertPrefRequest updateAlertPrefRequest = oeRequestHandler
					.createUpdateAlertPrefRequest(oeSignUpDTO);

			// Update alert preferences
			//UpdateAlertPreferencesResponseDTO updateAlertPrefResDTO = profileBO
					//.updateAlertPreferences(updateAlertPrefRequest);

			/*if (StringUtils.isBlank(updateAlertPrefResDTO.getErrorCode())) {
				if (oeSignUpDTO != null) {
					oeSignUpDTO
							.getPerson()
							.getContactPrefDTO()
							.setAlertMessage(
									updateAlertPrefResDTO
											.getResultDescription());

				}

				if (StringUtils.isBlank(updateAlertPrefResDTO.getResultCode())) {

					logger.info(oeSignUpDTO.printOETrackingID() + " ERROR:"
							+ METHOD_NAME
							+ " Alert Notifications could not be stored.");

					isCallSuccess = false;

				} else {
					logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME
							+ " UpdateAlertPref call ran Successfully.");

					isCallSuccess = true;
				}
				if (logger.isDebugEnabled())
					logger.debug(oeSignUpDTO.printOETrackingID()
							+ METHOD_NAME
							+ "::Alert preference response ID for updation is:: "
							+ updateAlertPrefResDTO.getResultCode());

			} else {
				oeSignUpDTO.getPerson().getContactPrefDTO()
						.setAlertMessage("");

				logger.info(oeSignUpDTO.printOETrackingID()
						+ " ERROR:"
						+ METHOD_NAME
						+ " Error code occured in the Update Alert Pref call is "
						+ updateAlertPrefResDTO.getErrorCode());

				logger.info(oeSignUpDTO.printOETrackingID()
						+ " ERROR:"
						+ METHOD_NAME
						+ " Error message occured in the Update Alert Pref call is "
						+ updateAlertPrefResDTO.getErrorDescription());

				isCallSuccess = false;
			}

		} catch (Exception e) {
			logger.error(oeSignUpDTO.printOETrackingID() + " ERROR:"
					+ METHOD_NAME, e);

			isCallSuccess = false;

		}

		logger.debug("End:" + METHOD_NAME);*/

		return true;
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 * @param langCode
	 * @return
	 */
	protected Boolean sendEnrollmentConfirmationEmail(OESignupDTO oeSignUpDTO,
			String langCode) {

		String METHOD_NAME = "OeBoHelper: sendEnrollmentConfirmationEmail(..)";
		logger.debug("Start:" + METHOD_NAME);

		Boolean isEmailSent = false;
		/**
		 * Send Enrollment Confirmation email using WS after the enrollment has
		 * happened but only for enrollments with status as N
		 */
		if (StringUtils.isNotBlank(oeSignUpDTO.getReqStatusCd())
				&& oeSignUpDTO.getReqStatusCd().equalsIgnoreCase(N_VALUE)) {
			String firstName = oeSignUpDTO.getPerson().getFirstName();
			String lastName = oeSignUpDTO.getPerson().getLastName();
			String emailAddress = oeSignUpDTO.getPerson().getEmailAddress();

			// TEMPORARILY COMMENTED TILL WE RECEIVE SPANISH TRANSLATION
			oeSignUpDTO.setRecentCallMade(CALL_NAME_EMAIL_CONFIRMATION);

			isEmailSent = emailHelper.sendEnrollmentConfirmationMail(firstName,
					lastName, emailAddress, langCode);

			logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME
					+ " Status is" + isEmailSent);

		}

		logger.debug("End:" + METHOD_NAME);

		return isEmailSent;
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	protected void populateFrequentFlyerDetails(OESignupDTO oeSignUpDTO) {
		/*
		 * setting frequentFlyer name in session if person enters Freq flyer
		 * name on landing page it will be available in session if they update
		 * it on verify it will be available in session but if both cases fail
		 * default name is taken and before enrollments sets these values in
		 * session
		 */
		String offerCategory = oeSignUpDTO.getSelectedOffer()
				.getOfferCategory();
		if (StringUtils.isNotBlank(offerCategory) 
				&&((StringUtils.equalsIgnoreCase(offerCategory, OFFER_CATEGORY_UNITED))
					|| offerCategory.equalsIgnoreCase(OFFER_CATEGORY_AA)
					|| offerCategory.equalsIgnoreCase(OFFER_CATEGORY_SOUTHWEST))) {

			if (StringUtils.isBlank(oeSignUpDTO.getFrequentFlyerFirstName())
					&& StringUtils.isBlank(oeSignUpDTO
							.getFrequentFlyerLastName())) {

				oeSignUpDTO.setFrequentFlyerFirstName(oeSignUpDTO
						.getPerson().getFirstName());
				oeSignUpDTO.setFrequentFlyerLastName(oeSignUpDTO.getPerson()
						.getLastName());
			}
		}
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	protected void populateAgreementNumber(OESignupDTO oeSignUpDTO) {

		String agreementNumber = "";
	
		switch (Integer.valueOf(oeSignUpDTO.getCompanyCode())) {
			case 121:{
				agreementNumber = WEB_PREFIX_OA_RELIANT
						+ CommonUtil.addLeadingZeroes(
								oeSignUpDTO.getTrackingNumber(), 17);
				break;
			}
			case 271:{
				agreementNumber =WEB_PREFIX_OA_GME
						+ CommonUtil.addLeadingZeroes(
								oeSignUpDTO.getTrackingNumber(), 15);
				break;
			}
			case 391:{
				if (StringUtils.equals(oeSignUpDTO.getBrandId(), BRAND_ID_CE)) {
					agreementNumber = WEB_PREFIX_OA_CE
							+ CommonUtil.addLeadingZeroes(
									oeSignUpDTO.getTrackingNumber(), 15);
				}else {
					agreementNumber = WEB_PREFIX_OA_PW
							+ CommonUtil.addLeadingZeroes(
									oeSignUpDTO.getTrackingNumber(), 15);
					
				}
				break;
			}
			case 400:{
				agreementNumber = WEB_PREFIX_OA_EE
						+ CommonUtil.addLeadingZeroes(
								oeSignUpDTO.getTrackingNumber(), 15);
				break;
			}
		}
		oeSignUpDTO.setAgreementNumber(agreementNumber);
		// end agreement number
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 * @throws Exception
	 */
	protected void submitOnlineEnrollment(OESignupDTO oeSignUpDTO)
			throws Exception {

		String errorCode = null;
		String errorVariable = null;
		Boolean isCriticalExceptionHappen = false;
		Boolean isEnrollmentSuccess = null;

		/**
		 * BpMatchFlag is TRUE means the customer was found to be existing but
		 * could not fetch its bpnumber from DB so now customer care will get
		 * back to it
		 */

		/* Added check for sharyland TDSP Changes ph. 2 - Murtuza */
		if (!(oeSignUpDTO.isBpMatchFlag())
				&& !checkSharylandCustEsidStatus(oeSignUpDTO)) {

			oeSignUpDTO.setRecentCallMade(CALL_NAME_SUBMIT_ENROLLMENT);
			
			SubmitEnrollRequest submitEnrollRequest = oeRequestHandler
					.createSubmitEnrollRequest(oeSignUpDTO);

			SubmitEnrollResponse submitEnrollResponse = oeProxy
					.submitEnrollment(submitEnrollRequest);

			// logger.info("Request: "
			// + XmlUtil.pojoToXMLwithRootElement(submitEnrollRequest,
			// "SubmitEnrollment"));
			logger.info("Error code: " + submitEnrollResponse.getStrErrCode());
			logger.info("Error message: "
					+ submitEnrollResponse.getStrErrMessage());
			
			if (submitEnrollResponse.getStrErrCode() == null) {
				isEnrollmentSuccess = true;

				logger.info(oeSignUpDTO.printOETrackingID()
						+ "Enrollment Happened Successfully");

				isEnrollmentSuccess = BOOLEAN_TRUE;
				
				oeSignUpDTO.setBusinessPartnerID(submitEnrollResponse
						.getStrBPNumber());
				oeSignUpDTO.setIdocNumber(submitEnrollResponse
						.getStrIDOCNumber());
				oeSignUpDTO.setContractAccountNum(submitEnrollResponse
						.getStrCANumber());
				if (StringUtils.isBlank(submitEnrollResponse.getStrCANumber())) {
					oeSignUpDTO.setCheckDigit(ZERO);
				} else {
					oeSignUpDTO.setCheckDigit(submitEnrollResponse
							.getStrCheckDigit());
				}
				//if (logger.isDebugEnabled()) {
					logger.info(oeSignUpDTO.printOETrackingID()
							+ "BPnumber is"
							+ submitEnrollResponse.getStrBPNumber());
					logger.debug(oeSignUpDTO.printOETrackingID()
							+ "ContractAccount number is"
							+ submitEnrollResponse.getStrCANumber());
					logger.debug(oeSignUpDTO.printOETrackingID()
							+ "CheckDigit from enrollment response is"
							+ submitEnrollResponse.getStrCheckDigit());
					if (submitEnrollResponse.getStrBPNumber() == null) {
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "bp numbr is null");
					}

				//}
			}

			else if (submitEnrollResponse.getStrErrCode() != null) {
				isEnrollmentSuccess = BOOLEAN_FALSE;

				//if (logger.isDebugEnabled()) {
					logger.info(oeSignUpDTO.printOETrackingID()
							+ "Enrollment Error Message:"
							+ submitEnrollResponse.getStrErrMessage());
				//}

				errorCode = submitEnrollResponse.getStrErrCode();
				oeSignUpDTO.setErrorCode(errorCode);
				// submissionResponseMap.put(ENROLLMENT_STATUS, "Failure");
				// submissionResponseMap.put(ERR_CODE_KEY,submitenrollResponse.getStrErrCode()
				// );
				// submissionResponseMap.put(ERR_CODE_DESC,submitenrollResponse.getStrErrMessage());

				if (!isCriticalExceptionHappen) {
					errorVariable = ERROR_IN_SUBMIT_ENROLLMENT_CALL;
					isCriticalExceptionHappen = true;
				}
			}
		}

		oeSignUpDTO.setEnrolled(isEnrollmentSuccess);
		oeSignUpDTO.setErrorVariable(errorVariable);
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 * @return
	 * @throws Exception
	 */
	protected Boolean doDodEnrollment(OESignupDTO oeSignUpDTO) throws Exception {
		Boolean isDodEnrolled = null;
		// Start :DOD Enrollment Phase 2 Rel 4.1

		String status = null;
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "DOD Start::::::::::" + oeSignUpDTO.getChkDodEnrollment());
		}

		if (StringUtils.isNotBlank(oeSignUpDTO.getChkDodEnrollment())) {
			try {
				if (oeSignUpDTO.getChkDodEnrollment().equals("true")) {

					// TODO 10
					// status =
					// dodEnrollmentDao.addDoDEnrollment(oeSignUpDTO,request);

					if (logger.isDebugEnabled()) {
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "Add DOD Enrollment Status ::" + status);
					}
				}

				if (StringUtils.equalsIgnoreCase(status, "success")) {
					isDodEnrolled = true;
					// submissionResponseMap.put(DOD_ENROLLMENT_STATUS,
					// "success");
					if (logger.isDebugEnabled()) {
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "AddDODEnrollment call happened successfully");
					}

				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "error in AddDODEnrollment call and error Code is : "
								+ status);
					}
					isDodEnrolled = false;
				}
			} catch (Exception e) {
				logger.error(oeSignUpDTO.printOETrackingID()
						+ "Exception occured during call addDoDEnrollment", e);
				isDodEnrolled = false;

			}
		}
		// End :DOD Enrollment Phase 2 Rel 4.1

		return isDodEnrolled;

	}

	/**
	 * Checking for Enrollment Status after the call
	 * 
	 * @Jsingh1
	 */
	@Deprecated // Marked by Jenith on 06/24/2015
	protected void checkEnrollmentStatus(OESignupDTO oeSignUpDTO) throws Exception {
		String METHOD_NAME = "OEBOHelper: checkEnrollmentStatus(..)";
		logger.debug("Start:" + METHOD_NAME);

		String errorCode = "";
		String accountStatus = "";
	

		if ((StringUtils.isNotBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
				.getContractAccountNum())))
				&& StringUtils.isNotBlank(CommonUtil
						.removeLeftPadZeros(oeSignUpDTO.getIdocNumber()))
				&& StringUtils
						.isNotBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
								.getBusinessPartnerID()))
				&& StringUtils.isBlank(oeSignUpDTO.getErrorCode())) {
			accountStatus = NSD;
			errorCode = oeSignUpDTO.getErrorCode();
		} else if (StringUtils.isNotBlank(oeSignUpDTO.getErrorCode())) {
			accountStatus = SD;
			errorCode = oeSignUpDTO.getErrorCode();
			logger.info(oeSignUpDTO.printOETrackingID()
					+ "setting enrollment status:: Account status is ::"
					+ accountStatus);
		} else if ((StringUtils.isBlank(CommonUtil
				.removeLeftPadZeros(oeSignUpDTO.getContractAccountNum())))
				&& StringUtils.isBlank(CommonUtil
						.removeLeftPadZeros(oeSignUpDTO.getIdocNumber()))
				&& StringUtils
						.isBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
								.getBusinessPartnerID()))) {
			accountStatus = SD;
			logger.info(oeSignUpDTO.printOETrackingID()
					+ "setting enrollment status:: Account status is ::"
					+ accountStatus);

			/* START - Added check for sharyland TDSP Changes ph. 2 - Murtuza */
			/*
			 * if (!(oeSignUpDTO.isBpMatchFlag())) { errorcode = TIBCOSD; } else
			 * { errorcode = BPSD; }
			 */
			if (checkSharylandCustEsidStatus(oeSignUpDTO)) {
				errorCode = SHRHOLD;
			} else if (oeSignUpDTO.isBpMatchFlag()) {
				errorCode = BPSD;
			} else {
				errorCode = TIBCOSD;
			}
			/* END - Added check for sharyland TDSP Changes ph. 2 - Murtuza */

		} else if ((StringUtils.isBlank(CommonUtil
				.removeLeftPadZeros(oeSignUpDTO.getContractAccountNum())))
				&& StringUtils.isBlank(CommonUtil
						.removeLeftPadZeros(oeSignUpDTO.getIdocNumber()))
				&& StringUtils
						.isBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
								.getBusinessPartnerID()))
				&& StringUtils.isNotBlank(oeSignUpDTO.getErrorCode())) {

			accountStatus = SD;
			errorCode = TIBCOSD;
		}

		if (StringUtils.isNotBlank(accountStatus)) {
			if (accountStatus.equalsIgnoreCase(SD)) {
				if (null != oeSignUpDTO.getSelectedOffer()
						.getOfferCategory()
						&& OFFER_CATEGORY_PREPAY.equalsIgnoreCase(oeSignUpDTO
								.getSelectedOffer().getOfferCategory())) {
					// oeSignUpDTO.setReqStatusCd(I_VALUE);
					oeSignUpDTO.setReqStatusCd(N_VALUE);
				} else {
					oeSignUpDTO.setReqStatusCd(N_VALUE);
				}

			} else {
				if (oeSignUpDTO.getEsid().getEsidCount() != null 
						&& oeSignUpDTO.getEsid().getEsidCount() == 1) {
					oeSignUpDTO.setReqStatusCd(CA);
				} else {
					oeSignUpDTO.setReqStatusCd(N_VALUE);
				}
			}
		} else {
			oeSignUpDTO.setReqStatusCd(N_VALUE);
		}
		
		// Update for service location
		if(StringUtils.isNotEmpty(errorCode)) {
			oeSignUpDTO.setErrorCode(errorCode);
		}
		

		logger.debug("End:" + METHOD_NAME);
		logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME
				+ " - ReqStatusCd:" + oeSignUpDTO.getReqStatusCd());

		// end of checking enrollment status
	}

	/**
	 * Updating for Enrollment Status after the call
	 * 
	 * @author jyogapa1
	 * 
	 */
	protected void updateEnrollmentStatus(OESignupDTO oeSignUpDTO) {
		String METHOD_NAME = "OEBOHelper: updateEnrollmentStatus(..)";
		logger.debug("Start:" + METHOD_NAME);

		// START. Code cleanup merging and tweaking. Added by Jenith on 06/24/2015
		if (oeSignUpDTO.isBpMatchFlag()) {
			// Update for service location
			oeSignUpDTO.setErrorCode(BPSD);
			oeSignUpDTO.setReqStatusCd(FLAG_N);

			return;
		}
		
		
		if (oeSignUpDTO.getEsid() == null
				|| (StringUtils.isBlank(oeSignUpDTO.getEsid().getEsidNumber()))) {

			// Update for service location
			oeSignUpDTO.setErrorCode(NESID);
			oeSignUpDTO.setReqStatusCd(FLAG_N);
		}
		
		// Switch Hold ON and Move IN case
		if (StringUtils.equalsIgnoreCase(oeSignUpDTO.getServiceReqTypeCd(), MVI)
				&& StringUtils.equalsIgnoreCase(oeSignUpDTO.getSwitchHoldStatus(), ON) ) {

			// Update for service location
			oeSignUpDTO.setErrorCode(SWHOLD);
			oeSignUpDTO.setReqStatusCd(FLAG_N);
		}
		
		// END. Code cleanup merging and tweaking
		
		// Retaining previously set errorCode and requestStatusCode if, they are
		// set elsewhere:
		String errorCode = oeSignUpDTO.getErrorCode();
		String requestStatusCode = oeSignUpDTO.getReqStatusCd();

		if (!oeSignUpDTO.isEnrolled()) {
			errorCode = CCSERR;
			requestStatusCode = FLAG_N;

			// Before returning from here, explicitly setting the error code and
			// request status code:
			oeSignUpDTO.setErrorCode(errorCode);
			oeSignUpDTO.setReqStatusCd(requestStatusCode);
			return;
		}

		if ((StringUtils.isNotBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
				.getContractAccountNum())))
				&& StringUtils.isNotBlank(CommonUtil
						.removeLeftPadZeros(oeSignUpDTO.getIdocNumber()))
				&& StringUtils
						.isNotBlank(CommonUtil.removeLeftPadZeros(oeSignUpDTO
								.getBusinessPartnerID()))
				&& StringUtils.isBlank(oeSignUpDTO.getErrorCode())) {

			requestStatusCode = CA;

		}
		// Update for service location
		oeSignUpDTO.setErrorCode(errorCode);
		oeSignUpDTO.setReqStatusCd(requestStatusCode);

		logger.info(oeSignUpDTO.printOETrackingID() + METHOD_NAME
				+ " - ReqStatusCd:" + oeSignUpDTO.getReqStatusCd());

		logger.debug("End:" + METHOD_NAME);
	}
	
	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	protected void setErrorCode(OESignupDTO oeSignUpDTO) {
		String errorCode = null;
		// setting error code based on esidCount
		/* Sharyland TDSP Changes ph.2 Starts - Murtuza */
		if (checkSharylandCustEsidStatus(oeSignUpDTO)) {
			errorCode = SHRHOLD;
		}
		/* Sharyland TDSP Changes ph.2 Ends - Murtuza */
		else if (oeSignUpDTO.isBpMatchFlag())
			errorCode = BPSD;
		else if (oeSignUpDTO.getEsid().getEsidCount() != null
				&& oeSignUpDTO.getEsid().getEsidCount() == 0)
			errorCode = NESID;
		else if (oeSignUpDTO.getEsid().getEsidCount() != null
				&& oeSignUpDTO.getEsid().getEsidCount() > 1)
			errorCode = MESID;

		// Setting error code into session
		oeSignUpDTO.setErrorCode(errorCode);
	}

	/**
	 * This method determines if the Submit Enrollment calls should be proceed.
	 * 
	 * Case 1. If bpMatchFlag value in API request is passed as BPSD.
	 *
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	protected Boolean allowSubmitEnrollment(OESignupDTO oeSignUpDTO,
			EnrollmentResponse response) {
		String METHOD_NAME = "OEBOHelper: allowSubmitEnrollment(..)";
		logger.debug("Start:" + METHOD_NAME);

		Boolean allowSubmit = true;

		if (StringUtils.equals(oeSignUpDTO.getBpMatchText(), BPSD)) {
			oeSignUpDTO.setBpMatchFlag(BOOLEAN_TRUE);

			allowSubmit = false;
		}

		logger.debug("End:" + METHOD_NAME);

		return allowSubmit;
	}
	

	/**
	 * This method initializes all the OESignup API input with normalized for NRGWS.
	 * 
	 * Case 1. Tdsp code normalization
	 * Case 2. Date formatting (Done by during call request itself on-fly)
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	protected void initNormalization(OESignupDTO oeSignUpDTO) {
		String METHOD_NAME = "OEBOHelper: initNormalization(..)";
		logger.debug("Start:" + METHOD_NAME);
		
		String tdspCodeCCS = oeSignUpDTO.getTdspCodeCCS();
		
		// Convert CCS tdsp code to Web Tdsp code
		String tdspCodeWeb = this.getAppProperty(APP_KEY_CCS_TDSP_TO_WEB_TDSP_PREFIX + tdspCodeCCS);
		logger.info(APP_KEY_CCS_TDSP_TO_WEB_TDSP_PREFIX + tdspCodeCCS + " is " + tdspCodeWeb);
		
		// Set web tdsp code
		oeSignUpDTO.setTdspCode(tdspCodeWeb);
		
		logger.debug("End:" + METHOD_NAME);
	}
	
	/**
	 * This method populates all the OESignup API prerequisite input for submit enrollment.
	 * 
	 * Case 1. Set FrequentFlyerDetails
	 * Case 2. Set AgreementNumber
	 * 
	 * @author jyogapa1 (Jenith)
	 */
	protected void initPrerequisites(OESignupDTO oeSignUpDTO) {
		String METHOD_NAME = "OEBOHelper: initNormalization(..)";
		// Populate Frequent flyer name. Added by JENITH on 02/19/2015
		this.populateFrequentFlyerDetails(oeSignUpDTO);

		// Populate agreement number. Added by JENITH on 02/19/2015
		this.populateAgreementNumber(oeSignUpDTO);
		
		logger.debug("End:" + METHOD_NAME);
	}
	
	
	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	@SuppressWarnings("unused")
	protected void savePerson(OESignupDTO oeSignUpDTO) {
		// savePersonCall
		// TODO 7
		String savePersonErrorcode = null;
		// String savePersonErrorcode=genericOEService.savePerson(oeSignUpDTO);

		if (savePersonErrorcode == null) {
			if (logger.isDebugEnabled())
				logger.debug(oeSignUpDTO.printOETrackingID()
						+ "updatePersonInfo call happened successfully");
		} else {
			if (logger.isDebugEnabled())
				logger.debug(oeSignUpDTO.printOETrackingID()
						+ "error in updatePersonInfo call and error Code is : "
						+ savePersonErrorcode);
		}

	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	@SuppressWarnings("unused")
	protected void saveServiceLocation(OESignupDTO oeSignUpDTO) {

		logger.info(oeSignUpDTO.printOETrackingID()
				+ "Service Location Updated call from EnrollmentHelper:submitEnrollment()");

		String saveServcieErrorcode = null;
		// TODO 6
		// String saveServcieErrorcode=
		// genericOEService.saveServiceLocation(oeSignUpDTO);

		/**
		 * if during any error a redirection is missed the technical error page
		 * is set to nextview DEFAULT VIEW if happy path fails
		 */

		if (saveServcieErrorcode == null) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "UpdateServiceLocation call happened successfully");
		} else {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "error in updateSErviceLocation call and error Code is : "
					+ saveServcieErrorcode);
		}
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	protected void doNcbSave(OESignupDTO oeSignUpDTO) {
		String errorVariable = "";
		Boolean isCriticalExceptionHappen = false;
		try {
			if (null == oeSignUpDTO.getSelectedOffer().getOfferCategory()
					|| !OFFER_CATEGORY_PREPAY.equals(oeSignUpDTO
							.getSelectedOffer().getOfferCategory())) {
				if (oeSignUpDTO.isWeeklyEnrollmentFlag()) {
					if ((oeSignUpDTO.getEsid().getEsidCount() == 1)
							&& (oeSignUpDTO.getBusinessPartnerID() != null && StringUtils
									.isNotBlank(oeSignUpDTO
											.getBusinessPartnerID()))
							&& (oeSignUpDTO.getContractAccountNum() != null && StringUtils
									.isNotBlank(oeSignUpDTO
											.getContractAccountNum()))) {
						oeSignUpDTO
								.setRecentCallMade(CALL_NAME_SAVE_NCB_DETAILS);
						String errorInNcbAdd = null;
						// TODO
						// String
						// errorInNcbAdd=ncbDao.ncbServiceReqAdd(oeSignUpDTO);
						if (StringUtils.isBlank(errorInNcbAdd)) {
							logger.info(oeSignUpDTO.printOETrackingID()
									+ "NCB Details Added successfully");
						} else {
							logger.info(oeSignUpDTO.printOETrackingID()
									+ "NCB Details encountered an errorCode: "
									+ errorInNcbAdd);
							if (StringUtils.isBlank(errorVariable)
									&& !(isCriticalExceptionHappen)) {
								errorVariable = ERROR_IN_PROC_ADD_NON_COMMODITY_BILLING;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(oeSignUpDTO.printOETrackingID()
					+ "Exception in NCBDetails call is :: ", e);
			if (StringUtils.isBlank(errorVariable)
					&& !(isCriticalExceptionHappen)) {
				errorVariable = EXCEPTION_IN_PROC_ADD_NON_COMMODITY_BILLING;
			}
		}
	}

	/**
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	@SuppressWarnings("null")
	protected void doPrepayCalls(OESignupDTO oeSignUpDTO) {
		if (null != oeSignUpDTO.getSelectedOffer().getOfferCategory()
				&& OFFER_CATEGORY_PREPAY.equals(oeSignUpDTO
						.getSelectedOffer().getOfferCategory())) {

			String errorVariable = "";
			Boolean isCriticalExceptionHappen = false;

			oeSignUpDTO.setRecentCallMade(CALL_NAME_CREATE_PREPAY_DOC);

			// TODO 1
			String createPrepayDocOUtput = null;
			// String createPrepayDocOUtput=
			// enrollmentService.createPrepayDocTxn(oeSignUpDTO);

			if (createPrepayDocOUtput.equalsIgnoreCase("curlaction")) {
				// CALL Update alert preferences. Added by JENITH on 02/19/2015
				Boolean isAlertPrefUpdated = this
						.callUpdateAlertPrefernces(oeSignUpDTO);

				if (!(isAlertPrefUpdated) && StringUtils.isBlank(errorVariable)
						&& !(isCriticalExceptionHappen)) {
					errorVariable = EXCEPTION_IN_UPDATE_ALERT_PREFERENCE;
				}

				// CALL Update alert preferences. Added by JENITH on 02/19/2015
				oeSignUpDTO.setRecentCallMade(CALL_NAME_ADD_ALERT_PREFERENCES);

				// TODO 3
				String errorCdAddAlertPref = null;
				// String
				// errorCdAddAlertPref=alertDAOImpl.addAlertPref(oeSignUpDTO);

				if (StringUtils.isNotBlank(errorCdAddAlertPref)) {
					if (logger.isDebugEnabled())
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "inside EnrollmentHelper::inside SubmitEnrollment()::error in adding alert preferences to database");
					if (StringUtils.isBlank(errorVariable)
							&& !(isCriticalExceptionHappen)) {
						errorVariable = ERROR_IN_ADD_ALERT_PREFERENCE;
					}
				} else {
					logger.debug(oeSignUpDTO.printOETrackingID()
							+ "inside EnrollmentHelper::inside SubmitEnrollment()::Add alert Preference call added values into DB successfully");
				}

				// Making Payment for prepay
				try {
					logger.debug(oeSignUpDTO.printOETrackingID()
							+ "inside EnrollmentHelper::inside SubmitEnrollment():: entering PrepayPmtSubmission");
					Map<String, Object> prepayPmtResponseMap = new HashMap<String, Object>();
					// TODO 4
					// prepayPmtResponseMap=prepayPaymentSubmission(request,
					// oeSignUpDTO);
					oeSignUpDTO = (OESignupDTO) prepayPmtResponseMap
							.get(OE_SIGNUP_DTO);
					if (StringUtils.isBlank(errorVariable)) {
						errorVariable = (String) prepayPmtResponseMap
								.get(ERROR_VARIABLE);

					}
				} catch (Exception e) {
					logger.error(oeSignUpDTO.printOETrackingID()
							+ "exception occured in prepayDepositPayment ::", e);
					if (!isCriticalExceptionHappen) {
						errorVariable = EXCEPTION_IN_PREPAY_PAYMENT_SUBMISSION;
						isCriticalExceptionHappen = true;
					}

				}

			}

			else if (createPrepayDocOUtput.equalsIgnoreCase("errorcode")) {
				logger.info(oeSignUpDTO.printOETrackingID()
						+ "inside EnrollmentHelper::inside SubmitEnrollment()::createPrepayDOC call ran with error");
				logger.info(oeSignUpDTO.printOETrackingID()
						+ "inside EnrollmentHelper::inside SubmitEnrollment()::Redirecting to Technical Error");
				// viewPropKey=VIEW_PAGE_TECHNICAL_ERROR;
				if (!isCriticalExceptionHappen) {
					errorVariable = ERROR_IN_CREATE_PREPAY_DOC_CALL;
					isCriticalExceptionHappen = true;
				}

			}

		}

	}

	
	/**
	 * @author jyogapa1
	 * 
	 * Updates contact information asynchronously in CRM.
	 * 
	 * @param oeSignupDTO
	 * @return
	 */
	protected Boolean updateContactInformation(OESignupDTO oeSignupDTO){
		String METHOD_NAME = "OeBoHelper: updateContactInformation(..)";
		logger.debug("Start:" + METHOD_NAME);
		
		try{
			UpdateContactRequest updateContactRequest = new UpdateContactRequest();
			updateContactRequest.setStrBPNumber(oeSignupDTO.getBusinessPartnerID());
			updateContactRequest.setStrUniqueId(oeSignupDTO.getBusinessPartnerID());
			updateContactRequest.setStrCANumber(oeSignupDTO.getContractAccountNum());	
			updateContactRequest.setStrCompanyCode(oeSignupDTO.getCompanyCode());
			updateContactRequest.setBrandName(oeSignupDTO.getBrandId());
			//updateContactRequest.setIsCreateCRMRecord(X_VALUE);
			updateContactRequest.setIsTelephoneUpdated(FLAG_Y);
			updateContactRequest.setIsEmailUpdated(FLAG_Y);
			updateContactRequest.setIsMktPrefUpdated(FLAG_Y);
			//setting the marketing preferences
			UpdateContactRequestAttNamValPairMapEntry[] attNamValPairMap = new UpdateContactRequestAttNamValPairMapEntry[2];
			attNamValPairMap[0] = new UpdateContactRequestAttNamValPairMapEntry();
			attNamValPairMap[0].setKey("MKT_EMAIL");
			if(null != oeSignupDTO.getEmailSubscription() && !oeSignupDTO.getEmailSubscription().isEmpty() &&
					(FLAG_X.equalsIgnoreCase(oeSignupDTO.getEmailSubscription()) || 
							FLAG_Y.equalsIgnoreCase(oeSignupDTO.getEmailSubscription()) ||
							FLAG_TRUE.equalsIgnoreCase(oeSignupDTO.getEmailSubscription())) ){
				attNamValPairMap[0].setValue(FLAG_Y);
			} else {
				attNamValPairMap[0].setValue(FLAG_N);
			}
			
			attNamValPairMap[1] = new UpdateContactRequestAttNamValPairMapEntry();
		    attNamValPairMap[1].setKey("OFFER_EMAIL");
		    if(null != oeSignupDTO.getThirdPartyEmailSharing() && !oeSignupDTO.getThirdPartyEmailSharing().isEmpty() &&
		    		(FLAG_X.equalsIgnoreCase(oeSignupDTO.getThirdPartyEmailSharing()) || 
		    				FLAG_Y.equalsIgnoreCase(oeSignupDTO.getThirdPartyEmailSharing()) ||
		    				FLAG_TRUE.equalsIgnoreCase(oeSignupDTO.getThirdPartyEmailSharing()))){
		    	attNamValPairMap[1].setValue(FLAG_Y);
		    }
		    else{
		    	attNamValPairMap[1].setValue(FLAG_N);
		    }
			updateContactRequest.setAttNamValPairMap(attNamValPairMap);
			
			/** Cirro Changes Start - Msadriw1*/
			List<UpdatePhoneDO> updatePhoneDOList = new ArrayList<UpdatePhoneDO>();
			/*// set data for Work Phone Number
			String strDayPhone=oeSignUpVo.getDayPhoneNo1() + oeSignUpVo.getDayPhoneNo2() + oeSignUpVo.getDayPhoneNo3();
			if(StringUtils.isNotBlank(strDayPhone))
			{
				UpdatePhoneDO workPhoneDO = new UpdatePhoneDO();
				workPhoneDO.setPhoneNumber(strDayPhone);
				workPhoneDO.setRemark(REMARK_WORK);
				updatePhoneDOList.add(workPhoneDO);
				logger.info("Setting WORK Phone ~~~ "+strDayPhone);
			}

			//set data for Cell Phone Number
			String strCellPhone=oeSignUpVo.getCellPhoneNo1() + oeSignUpVo.getCellPhoneNo2() + oeSignUpVo.getCellPhoneNo3();
			if(StringUtils.isNotBlank(strCellPhone))
			{
				UpdatePhoneDO cellPhoneDO = new UpdatePhoneDO();
				cellPhoneDO.setPhoneNumber(strCellPhone);
				cellPhoneDO.setRemark(REMARK_CELL);
				updatePhoneDOList.add(cellPhoneDO);
				logger.info("Setting CELL Phone ~~~ "+strCellPhone);
			}*/
			
			//set data for Home Phone Number
			logger.info("inside processOeEnrollment:: setting evening phone number ");
			/*String strEveningPhone = oeSignupDTO.getEveningPhoneNo1()
					+ oeSignupDTO.getEveningPhoneNo2()
					+ oeSignupDTO.getEveningPhoneNo3();*/
			logger.info("inside processOeEnrollment:: phone number is :: "
					+ oeSignupDTO.getPerson().getPhoneNumber());
			if (StringUtils.isNotBlank(oeSignupDTO.getPerson().getPhoneNumber())) {
				logger.info("inside " + METHOD_NAME + ":: setting evening ph number to Update PhoneDO");
				UpdatePhoneDO homePhoneDO = new UpdatePhoneDO();
				homePhoneDO.setPhoneNumber(oeSignupDTO.getPerson().getPhoneNumber());
				homePhoneDO.setRemark(REMARK_HOME);
				updatePhoneDOList.add(homePhoneDO);
				logger.info("Setting HOME Phone ~~~ " + oeSignupDTO.getPerson().getPhoneNumber());
			}
			if (null != updatePhoneDOList && updatePhoneDOList.size() > 0) {
				UpdatePhoneDO[] phoneDOArr = updatePhoneDOList
						.toArray(new UpdatePhoneDO[0]);
				updateContactRequest.setPhoneDO(phoneDOArr);
			}
			updateContactRequest.setStrEmailId(oeSignupDTO.getPerson()
					.getEmailAddress());
			/** Cirro Changes End - Msadriw1 */

			//oeSignupDTO.setRecentCallMade(CALL_NAME_UPDATE_CONTACT_INFO);
			logger.info(METHOD_NAME + " tracking Number is :"
					+ oeSignupDTO.getTrackingNumber()
					+ ": testing and recent call made till now is "
					+ oeSignupDTO.getRecentCallMade());
			//Calling updateContact asynchronously 
			//ProfileDomainHelper profileDomainHelper = new ProfileDomainHelper();
			//UpdateContactResponse updateContactRes = profileDomainHelper.updateContact(updateContactRequest);
			logger.info("Start: Async call updateContact(...)");
			asyncHelper.updateContactInCRM(updateContactRequest);
			logger.info("End: Async call updateContact(...)");
			// logger.debug("tracking Number is :"+oeSignUpVo.getSvc_location_trackingNum()+"updateContactRes error is "
			// +updateContactRes.getErrorCode());
		} catch (Exception ex) {
			logger.error(METHOD_NAME
					+ " Error updating Contact Preferences. Skipping and continuing"
					+ ex.getMessage());
		}

		logger.debug("End:" + METHOD_NAME);
		
		return null;
	}
	
	/**
	 * 
	 * @author jyogapa1
	 * 
	 * Activates Ebill in CCS.
	 * 
	 * @param oeSignUpDTO
	 */
	protected void activateEbill(OESignupDTO oeSignUpDTO) {
		
		// Execute only if eBillFlag is set to ON (Y) and CA & BP found.
		if (StringUtils.equals(oeSignUpDTO.getEbillFlag(), FLAG_Y)
				&& StringUtils.isNotBlank(oeSignUpDTO.getContractAccountNum())
				&& StringUtils.isNotBlank(oeSignUpDTO.getBusinessPartnerID())) {

			billingBO.subscribePaperlessBilling(oeSignUpDTO.getContractAccountNum(),oeSignUpDTO.getCompanyCode());
		}

	}

	/**
	 * 
	 * @author jyogapa1
	 * 
	 * Updates contact in CRM.
	 * 
	 * 
	 * @param oeSignUpDTO
	 */
	protected void updateContact(OESignupDTO oeSignUpDTO) {
		
		// Execute only if CA & BP found.
		if (StringUtils.isNotBlank(oeSignUpDTO.getContractAccountNum())
				&& StringUtils.isNotBlank(oeSignUpDTO.getBusinessPartnerID())) {

			this.updateContactInformation(oeSignUpDTO);
		}
	}
	
	/** SharyLand Utilities changes ph.2 Start */
	private Boolean checkSharylandCustEsidStatus(OESignupDTO oeSignUpDTO) {
		Boolean isSharylandCustomer = BOOLEAN_FALSE;
		if (null != oeSignUpDTO && null != oeSignUpDTO.getEsidNumber()) {

			if (null != oeSignUpDTO.getTdspCode()
					&& oeSignUpDTO.getTdspCode().equals(
							TDSP_CD_SHARYLAND_UTILITIES)
					&& (StringUtils.isBlank(oeSignUpDTO.getEsid()
							.getEsidStatus())
							|| oeSignUpDTO.getEsid().getEsidStatus()
									.equals(ESID_STATUS_DE_ENERGIZED) || StringUtils
								.isBlank(oeSignUpDTO.getEsidNumber()))) {
				isSharylandCustomer = BOOLEAN_TRUE;
			}
		}
		return isSharylandCustomer;
	}
	
	// Start : Validate for Power Genius Online Affiliates by KB
	@SuppressWarnings({ "static-access" })
	protected Boolean sendPowerGeniusConfirmationEmailDlt(String toEmail) throws Exception {
		Boolean isEmailSent = false;


		isEmailSent = emailHelper.sendMailWithBCC(
		toEmail, toEmail, "PowerGenius.com - Enrollment Confirmation", 
		POWER_GENIUS_ENROLL_CONF_EN, new HashMap<String,String>(), RELIANT_COMPANY_CODE);


		return isEmailSent;
	}
	// End : Validate for Power Genius Online Affiliates by KB
	
	public String getTechnicalErrorMessage(String languageCode) {
		Locale localeObj = null;
		if (StringUtils.equalsIgnoreCase(languageCode, S) || StringUtils.equalsIgnoreCase(languageCode, ES))
			localeObj = new Locale("es", "US");
		else
			localeObj = new Locale("en", "US");
		return msgSource.getMessage(MESSAGE_CODE_TECHNICAL_ERROR, null, localeObj);
	}
}