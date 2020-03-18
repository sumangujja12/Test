package com.multibrand.bo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.multibrand.dao.AddressDAOIF;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.domain.SubmitEnrollResponse;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactRequestAttNamValPairMapEntry;
import com.multibrand.domain.UpdatePhoneDO;
import com.multibrand.dto.request.GMDEnrollmentRequest;
import com.multibrand.dto.response.GMDEnrollmentResponse;
import com.multibrand.exception.NRGException;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.AsyncHelper;
import com.multibrand.proxy.OEProxy;
import com.multibrand.service.AddressService;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.GMDService;
import com.multibrand.service.OEService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.request.ESIDDO;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.gmd.GMDPricingResponse;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;

/**
 * This BO class is to handle all the GMD Related API calls.
 * 
 * @author rpendur1
 */
@Component
public class GMDBO extends BaseAbstractService implements Constants {

	@Autowired
	private GMDService gmdService;

	@Autowired
	BillingBO billingBO;

	@Autowired
	OEService oeService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private TOSService tosService;

	@Autowired
	@Qualifier("addressDAO")
	private AddressDAOIF addressDAO;

	@Autowired
	private OEProxy oeProxy;

	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;

	@Autowired
	private AsyncHelper asyncHelper;

	public GMDStatementBreakDownResponse getGMDStatementDetails(String accountNumber, String companyCode, String esiId,
			String year, String month, String sessionId) {

		GMDStatementBreakDownResponse gmdStatementBreakDownResp = new GMDStatementBreakDownResponse();

		try {
			gmdStatementBreakDownResp = gmdService.getGMDStatementDetails(accountNumber, companyCode, esiId, year,
					month, sessionId);

		} catch (NRGException e) {
			logger.error("Exception occured in getGMDStatementDetails :{}", e);
			gmdStatementBreakDownResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			gmdStatementBreakDownResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), gmdStatementBreakDownResp);
		}
		return gmdStatementBreakDownResp;

	}
	
	public GMDPricingResponse getGMDPriceDetails(String accountNumber, String contractId, String companyCode,
			String esiId,String sessionId) {

		GMDPricingResponse gmdPricingResp = new GMDPricingResponse();

		try {
			gmdPricingResp = gmdService.getGMDPriceDetails(accountNumber, contractId, companyCode, esiId, sessionId);

		} catch (NRGException e) {
			logger.error("Exception occured in getGMDPriceDetails :{}", e);
			gmdPricingResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			gmdPricingResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), gmdPricingResp);
		}
		return gmdPricingResp;

	}	

	public EsidInfoTdspCalendarResponse getESIDAndCalendarDates(String companyCode, String brandId, String tdspCodeCCS,
			String transactionType, String locale, String esid) {
		/* author Mayank Mishra */
		String methodName = "OEBO: getESIDAndCalendarDates(..)";
		logger.debug("Start:" + methodName);

		EsidInfoTdspCalendarResponse response = new EsidInfoTdspCalendarResponse();
		ESIDDO esidDo = new ESIDDO();
		// AddressDO serviceAddressDO = new AddressDO();

		Locale localeObj = null;

		if (locale.equalsIgnoreCase(LANG_ES))
			localeObj = new Locale("es", "US");
		else
			localeObj = new Locale("en", "US");

		response.setEsid(EMPTY);
		response.setMeterType(EMPTY);
		response.setSwitchHoldFlag(EMPTY);

		try {
			EsidProfileResponse esidProfileResponse = this.addressService.getESIDProfile(esid, companyCode);
			esidDo = setESIDDTO(esidProfileResponse);
			TdspByESIDResponse tdspByESIDResponse = this.tosService.ccsGetTDSPFromESID(esid, companyCode, "");
			if ((tdspByESIDResponse != null) && (StringUtils.isNotBlank(tdspByESIDResponse.getServiceId()))) {
				String tdspCodeCCSForEsid = tdspByESIDResponse.getServiceId();
				esidDo.setEsidTDSP(this.appConstMessageSource
						.getMessage("ccs.tdsp.web.equivalent." + tdspCodeCCSForEsid, null, null));
				logger.info("TDSP Code:" + esidDo.getEsidTDSP());
			} else {
				response.setErrorCode(AREA_NOT_SERVICED);
				response.setErrorDescription(msgSource.getMessage(AREA_NOT_SERVICED_TEXT, null, localeObj));
				response.setStatusCode(STATUS_CODE_STOP);
				return response;
			}

			if (esidDo != null) {
				response.setMeterType(esidDo.getMeterType());
				response.setSwitchHoldFlag(esidDo.getSwitchHoldStatus());
				response.setTdspCode(tdspCodeCCS);
				if (StringUtils.isNotBlank(esidDo.getEsidNumber())) {
					String strESIDNumber = esidDo.getEsidNumber();
					if (strESIDNumber.equalsIgnoreCase(MESID) || strESIDNumber.equalsIgnoreCase(NESID)) {
						response.setEsid(EMPTY);
						response.setErrorCode(RESULT_CODE_SUCCESS);
						response.setStatusCode(STATUS_CODE_CONTINUE);
						response.setErrorCode(strESIDNumber);
						if (MESID.equalsIgnoreCase(strESIDNumber)) {
							response.setErrorDescription(msgSource.getMessage(MESSAGE_CODE_MESID, null, localeObj));
						} else if (NESID.equalsIgnoreCase(strESIDNumber)) {
							response.setErrorDescription(msgSource.getMessage(MESSAGE_CODE_NESID, null, localeObj));
						}
						response.setTdspCode(EMPTY);
						response.setAvailableDates(EMPTY);
					} else if (strESIDNumber.equalsIgnoreCase(NRESID)) {
						response.setEsid(EMPTY);
						response.setStatusCode(STATUS_CODE_STOP);
						response.setErrorCode(strESIDNumber);
						response.setErrorDescription(msgSource.getMessage(MESSAGE_CODE_NRESID, null, localeObj));
					} else {
						response.setEsid(strESIDNumber);
					}
				}
				// Switch Hold ON scenario for SWI
				if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_SWITCH)
						&& StringUtils.equals(esidDo.getSwitchHoldStatus(), SWITCH_HOLD_STATUS_ON)) {
					logger.debug("ERROR:" + methodName);
					response.setStatusCode(STATUS_CODE_STOP);
					response.setAvailableDates(EMPTY);
					response.setSwitchHoldFlag(SWITCH_HOLD_STATUS_ON);
					response.setTdspFee(EMPTY);
					response.setErrorCode(MESSAGE_CODE_SWITCH_HOLD);
					response.setErrorDescription(this.msgSource.getMessage(MESSAGE_CODE_SWITCH_HOLD, null, localeObj));
					return response;
				} else if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_MOVE_IN)
						&& StringUtils.equals(esidDo.getSwitchHoldStatus(), SWITCH_HOLD_STATUS_ON)) {
					response.setStatusCode(STATUS_CODE_CONTINUE);
					response.setSwitchHoldFlag(SWITCH_HOLD_STATUS_ON);
					response.setErrorCode(MESSAGE_CODE_NOTIFY_SWITCH_HOLD);
					String messageCodeText = getMessageCodeTextForNotifySwitchHold(brandId, companyCode);
					response.setErrorDescription(messageCodeText);
				}

				// ESID Active in company scenario (ESID active)
				if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_SWITCH)
						&& (StringUtils.isNotEmpty(esidDo.getEsidStatus())
								&& esidDo.getEsidStatus().equalsIgnoreCase(STATUS_ACTIVE))) {
					logger.debug("ERROR:" + methodName);
					// response.setResultCode(RESULT_CODE_SUCCESS);
					// response.setResultDescription(RESULT_DESCRIPTION_ACTIVE_ESID);
					response.setStatusCode(STATUS_CODE_STOP);
					response.setAvailableDates(EMPTY);
					response.setTdspFee(EMPTY);
					response.setErrorCode(MESSAGE_CODE_ESID_ACTIVE);
					response.setErrorDescription(this.msgSource.getMessage(MESSAGE_CODE_ESID_ACTIVE, null, localeObj));
					return response;
				}

				// Non-Residential scenario (Business meter scenario)
				if (StringUtils.isNotEmpty(esidDo.getPremiseType())
						&& !esidDo.getPremiseType().equalsIgnoreCase(RESI)) {
					logger.debug("ERROR:{}" + methodName);
					// response.setResultDescription(RESULT_DESCRIPTION_BUSINESS_METER);
					response.setStatusCode(STATUS_CODE_STOP);
					response.setAvailableDates(EMPTY);
					response.setTdspFee(EMPTY);
					response.setErrorCode(MESSAGE_CODE_BUSINESS_METER);
					response.setErrorDescription(
							this.msgSource.getMessage(MESSAGE_CODE_BUSINESS_METER, null, localeObj));
					return response;
				}
			} // else return response;

			// GET tdsp calendar dates
			this.getTdspDates(companyCode, transactionType, tdspCodeCCS, esidDo, response, localeObj);
		} catch (Exception e) {
			logger.error("OEBO.getESIDInfo() Exception occurred when invoking getESIDInfo", e);
			response.setErrorCode(RESULT_CODE_SUCCESS);
			response.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION + ": " + e.getMessage());
			response.setStatusCode(STATUS_CODE_CONTINUE);
			// response.setMessageCode(EMPTY);
			// response.setMessageText(EMPTY);
		} finally {
			// Call update service location
			/*
			 * if (StringUtils.isNotBlank(trackingId)) {
			 * this.updateServiceLocation(companyCode, trackingId, esidDo, response, esid);
			 * }
			 */
			// response.setCompanyCode(companyCode);
			// response.setBrandName(brandId);
		}

		// Default to EMPTY if statusflag is "OFF"
		if (StringUtils.equalsIgnoreCase("OFF", response.getSwitchHoldFlag())) {
			response.setSwitchHoldFlag(EMPTY);
		}

		logger.debug("END:{}" + methodName);

		return response;
	}

	/**
	 * Method to populate ESIDDO from EsidProfileResponse
	 * 
	 * @param esidProfileResponse EsidProfileResponse
	 * @return ESIDDO
	 */
	private ESIDDO setESIDDTO(EsidProfileResponse esidProfileResponse) {
		logger.debug("OEBO.setESIDDTO() start");
		ESIDDO esidDO = new ESIDDO();
		if (esidProfileResponse != null) {
			if (StringUtils.isNotBlank(esidProfileResponse.getErrorCode())) {
				esidDO.setHasError(true);
				esidDO.setEsidProfileErrorCode(esidProfileResponse.getErrorCode());
				esidDO.setEsidProfileErrorDescription(esidProfileResponse.getErrorMessage());
			} else {
				esidDO.setHasError(false);
				esidDO.setEsidCount(1);
				esidDO.setEsidNumber(esidProfileResponse.getESID());
				esidDO.setEsidStatus(esidProfileResponse.getEsidStatus());
				esidDO.setMeterType(esidProfileResponse.getMeterType());
				esidDO.setPremiseType(esidProfileResponse.getPremiseType());
				esidDO.setRecentDisconnectFlag(esidProfileResponse.getRecentDisconnectFlag());
				esidDO.setSwitchHoldStatus(esidProfileResponse.getSwitchHoldStatus());
				// Start || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari ||
				// 15/12/2019
				if (StringUtils.equalsIgnoreCase(esidProfileResponse.getBlockStatus(), FLAG_X)) {
					esidDO.setEsidBlocked(true);
				} else {
					esidDO.setEsidBlocked(false);
				}
				// END || US23692: Affiliate API - Hard Stop Blocked ESIDs || atiwari ||
				// 15/12/2019

			}
			logger.debug("OEBO.setESIDDTO() esidDTO:: " + esidDO);
		}
		logger.debug("OEBO.setESIDDTO() end");
		return esidDO;
	}

	private String getMessageCodeTextForNotifySwitchHold(String brandId, String companyCode) {
		String messageCodeText = StringUtils.EMPTY;
		String companyName = CommonUtil.getCompanyName(brandId, companyCode);

		try {
			String urlNewOccpConfForm = StringUtils.EMPTY;
			if (COMPANY_NAME_RELIANT.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(MSG_KEY_URL_NEW_CONF_FORM_RELIANT, null, null);
			} else if (COMPANY_NAME_GME.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(MSG_KEY_URL_NEW_CONF_FORM_GME, null, null);
			} else if (COMPANY_NAME_CIRRO.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(MSG_KEY_URL_NEW_CONF_FORM_CIRRO, null, null);
			} else if (COMPANY_NAME_PENNYWISE.equalsIgnoreCase(companyName)) {
				urlNewOccpConfForm = appConstMessageSource.getMessage(MSG_KEY_URL_NEW_CONF_FORM_PENNYWISE, null, null);
			}
			messageCodeText = msgSource.getMessage(MESSAGE_CODE_NOTIFY_SWITCH_HOLD,
					new String[] { urlNewOccpConfForm });
		} catch (Exception e) {
			logger.error("Problem occurred while getting the message text for messageCode = "
					+ MESSAGE_CODE_NOTIFY_SWITCH_HOLD, e);
			messageCodeText = StringUtils.EMPTY;
		}
		return messageCodeText;
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
	private void getTdspDates(String companyCode, String transactionType, String tdspCodeCCS, ESIDDO esidDo,
			EsidInfoTdspCalendarResponse response, Locale locale) throws Exception {
		String METHOD_NAME = "OEBO: getTdspDates(..)";

		logger.debug("Start:" + METHOD_NAME);
		String internaltdspCodeCCS = "";

		if (esidDo != null && StringUtils.isNotEmpty(esidDo.getEsidTDSP())) {
			internaltdspCodeCCS = this.appConstMessageSource.getMessage(esidDo.getEsidTDSP(), null, null);
		}

		// TDSP mismatch scenario
		if ((StringUtils.isNotEmpty(tdspCodeCCS) && StringUtils.isNotEmpty(internaltdspCodeCCS))
				&& !tdspCodeCCS.equalsIgnoreCase(internaltdspCodeCCS)) {
			logger.debug("ERROR:" + METHOD_NAME);
			response.setResultCode(RESULT_CODE_SUCCESS);
			// response.setResultDescription(RESULT_DESCRIPTION_TDSP_MISMATCH);
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
		// START : ALT Channel : Sprint6 :US7569 :Kdeshmu1
		if (StringUtils.isNotEmpty(esidDo.getEsidNumber())) {
			request.setEsiid(esidDo.getEsidNumber());
		} else {
			request.setEsiid("");
		}
		// END : ALT Channel : Sprint6 :US7569 :Kdeshmu1
		// Get calendar specific days for tdspCodeCCS
		String dateString = oeService.getTDSPSpecificCalendarDates(request, "");
		List<String> allInclusiveDateList = CommonUtil.getDaysInBetween(c.getTime(),
				DateUtils.addDays(c.getTime(), 59));
		if (allInclusiveDateList == null) {
			response.setAvailableDates(EMPTY);
			return;
		}

		if (StringUtils.isNotBlank(dateString)) {
			String[] strValues = StringUtils.split(dateString, '|');
			ArrayList<String> holidayDates = new ArrayList<String>(Arrays.asList(strValues));
			allInclusiveDateList.removeAll(holidayDates);
		}

		String availableDatesNoFwdSlash = EMPTY;
		if (allInclusiveDateList != null) {
			// Logic to remove dates based on below conditions:
			// Include 2:00PM cut-off logic as an initial "default" check.
			// If 'ESID Not Found' or 'SwitchHold' or 'BPSD' then do not check Meter Type,
			// push the available date 4 days out
			// If there is no exception, then check meter type.
			// If meterType='AMSR' then first available date is today, for all other meter
			// Types, push out the date by 2 days.

			Calendar c2 = Calendar.getInstance();
			c2.set(Calendar.HOUR_OF_DAY, 14);
			c2.set(Calendar.MINUTE, 00);
			c2.set(Calendar.SECOND, 00);
			c2.set(Calendar.MILLISECOND, 00);
			DateFormat df2 = new SimpleDateFormat(MM_dd_yyyy);
			// START : ALT Channel : Sprint6 :US7569 :Kdeshmu1
			/**
			 * if (c.getTimeInMillis() > c2.getTimeInMillis())
			 * allInclusiveDateList.remove(df2.format(c.getTime()));
			 **/

			Calendar c3 = Calendar.getInstance();
			c3.set(Calendar.HOUR_OF_DAY, 17);
			c3.set(Calendar.MINUTE, 00);
			c3.set(Calendar.SECOND, 00);
			c3.set(Calendar.MILLISECOND, 00);

			if (c.getTimeInMillis() > c3.getTimeInMillis()
					&& (StringUtils.equals(response.getMeterType(), METER_TYPE_AMSR))) {
				allInclusiveDateList.remove(df2.format(c.getTime()));
			}

			else if (c.getTimeInMillis() > c2.getTimeInMillis()
					&& (!StringUtils.equals(response.getMeterType(), METER_TYPE_AMSR))) {
				allInclusiveDateList.remove(df2.format(c.getTime()));
			}
			// END : ALT Channel : Sprint6 :US7569 :Kdeshmu1
			if (allInclusiveDateList.size() > 0 // List still has data
					&& (StringUtils.isBlank(response.getEsid()) // Blank ESID means no ESID found
							|| StringUtils.equals(esidDo.getSwitchHoldStatus(), SWITCH_HOLD_STATUS_ON))) // BPSD true
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
			availableDatesNoFwdSlash = StringUtils.replace(availableDates, FWD_SLASH, EMPTY);
		}

		response.setAvailableDates(availableDatesNoFwdSlash);
		response.setTdspCode(tdspCodeCCS);
		if (StringUtils.isNotEmpty(tdspCodeCCS) && StringUtils.isNotEmpty(transactionType)) {
			String keyForTdspFee = EMPTY;
			String meterType = EMPTY;
			if (!StringUtils.equals(response.getMeterType(), METER_TYPE_AMSR)) {
				meterType = METER_TYPE_NON_AMSR;
			} else {
				meterType = response.getMeterType();
			}
			keyForTdspFee = meterType + UNDERSCORE + tdspCodeCCS + UNDERSCORE + transactionType;
			response.setTdspFee(this.msgSource.getMessage(keyForTdspFee, null, locale));
		} else {
			response.setTdspFee(EMPTY);
		}

		logger.debug("End:" + METHOD_NAME);
	}

	public GMDEnrollmentResponse submitEnrollment(GMDEnrollmentRequest enrollmentRequest) {
		String methodName = "OEBO: submitEnrollment(..)";
		logger.debug("Start:{}" + methodName);

		GMDEnrollmentResponse response = new GMDEnrollmentResponse();

		if (StringUtils.isBlank(enrollmentRequest.getPromoCode())) { // If Promo code is passed empty
			response.setStatusCode(STATUS_CODE_STOP);
			response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setErrorDescription("promoCode may not be Empty");
			return response;
		}

		try {
			// 1. Call online enrollment submission to CCS.
			response = this.submitOnlineEnrollment(enrollmentRequest);
			if (StringUtils.isNotBlank(response.getErrorCode())) {
				if (StringUtils.isNotBlank(response.getContractAccountNumber())
						&& StringUtils.isNotBlank(response.getBusinessPartnerNumber())) {
					this.updateContactInformation(response, enrollmentRequest);
				}
			}

		} catch (RemoteException e) {
			logger.error(e);
			this.handleSubmitEnrollmentError(response, enrollmentRequest, e);

		} catch (Exception e) {
			logger.error(e);
			this.handleSubmitEnrollmentError(response, enrollmentRequest, e);
		}
		/**
		 * The Below given finally always runs in all scenarios and in case of exception
		 * also will execute the calls of below.
		 * 
		 */
		finally {

		}

		logger.debug("END:{}" + methodName);

		return response;
	}

	/**
	 * Method createSubmitEnrollRequest.
	 * 
	 * @param oeSignUpDTO OESignupDTO
	 * @return SubmitEnrollRequest
	 */
	public SubmitEnrollRequest createSubmitEnrollRequest(GMDEnrollmentRequest oeSignUpDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("enrollmentService SubmitEnrollment creating request:: inside createSubmitEnrollRequest");
		}

		String bpFullName = EMPTY;
		String enrollmentType = EMPTY;
		String contactText = EMPTY;
		String moveInDate = EMPTY;
		String specialReadDate = EMPTY;
		String startSvrcDate = EMPTY;
		String notifyAddress = EMPTY;
		String pointOfDeliveryId = EMPTY;
		String requestedAmount = EMPTY;
		String reasonSEcurityDeposit = EMPTY;
		String depositCode = EMPTY;
		String agreementNumber = EMPTY;
		String enrollmentHoldType = EMPTY;

		// oeSignUpDTO = prepareAdditionalEnrollmentRequestInfo(request, oeSignUpDTO);
		// //VSOOD: COMMENTED FOR CLEANUP
		SubmitEnrollRequest submitEnrollRequest = new SubmitEnrollRequest();
		submitEnrollRequest.setStrBPType(BPTYPE);
		submitEnrollRequest.setStrBPGroup(BPGROUP);
		submitEnrollRequest.setStrBPFirstName(oeSignUpDTO.getFirstName());
		submitEnrollRequest.setStrBPMiddleInitial(oeSignUpDTO.getMiddleName());
		submitEnrollRequest.setStrBPMaidenName(oeSignUpDTO.getMaidenName());
		submitEnrollRequest.setStrBPLastName(oeSignUpDTO.getLastName());

		String personDobFormatted = CommonUtil.formatDateForNrgws(oeSignUpDTO.getDateOfBirth());
		submitEnrollRequest.setStrBPDOB(personDobFormatted);

		if (logger.isDebugEnabled()) {
			logger.debug(" enrollmentService SubmitEnrollment creating request:: checking for FullName");
		}

		if ((oeSignUpDTO.getMiddleName() != null)) {
			bpFullName = oeSignUpDTO.getFirstName() + " " + oeSignUpDTO.getMiddleName() + " "
					+ oeSignUpDTO.getLastName();

		} else {
			bpFullName = oeSignUpDTO.getFirstName() + " " + oeSignUpDTO.getLastName();
		}

		logger.debug("enrollmentService:createSubmitEnrollment::passed Person Name Details");

		submitEnrollRequest.setStrBPFullName(bpFullName);
		submitEnrollRequest.setStrNotifyName(
				bpFullName.length() > 34 ? bpFullName.substring(0, 34).toUpperCase() : bpFullName.toUpperCase());
		submitEnrollRequest.setStrCAName(
				bpFullName.length() > 34 ? bpFullName.substring(0, 34).toUpperCase() : bpFullName.toUpperCase());
		if (logger.isDebugEnabled()) {
			logger.debug("submitEnrollment request :: Name is " + bpFullName);
		}

		submitEnrollRequest.setStrCampaignCode(oeSignUpDTO.getCampaignCode());
		// START test logs
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressAptNumber())
				|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetName())
						| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetNumber())) {

			submitEnrollRequest.setStrBPStreet(oeSignUpDTO.getBillingAddressStreetName());
			submitEnrollRequest.setStrBPHouseNum(oeSignUpDTO.getBillingAddressStreetNumber());

		} else {
			submitEnrollRequest.setStrBPPOBoxCountry(COUNTRY_US);
			submitEnrollRequest.setStrBPPOBoxRegion(oeSignUpDTO.getBillingAdressState());
			if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressZipCode()))
				submitEnrollRequest.setStrBPPOBoxPostalCode(oeSignUpDTO.getBillingAddressZipCode());
			else
				submitEnrollRequest.setStrBPPOBoxPostalCode(oeSignUpDTO.getBillingAddressZipCode());
			submitEnrollRequest.setStrBPPOBox(oeSignUpDTO.getBillingAddressPoBox());
		}
		// END test logs

		if (logger.isDebugEnabled()) {
			logger.debug(
					"creating SubmitEnrollmentRequest in enrollmentService:: passed the null check for Billing Address");
		}
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressAptNumber())
				|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetName())
						| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetNumber())) {

			if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressAptNumber())) {
				notifyAddress = oeSignUpDTO.getBillingAddressStreetName() + ", APT "
						+ oeSignUpDTO.getBillingAddressAptNumber();
			} else {
				notifyAddress = oeSignUpDTO.getBillingAddressStreetName();
			}
		} else if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressPoBox())) {

			notifyAddress = "P.O.Box " + oeSignUpDTO.getBillingAddressPoBox();

		} else {
			notifyAddress = "";
		}

		submitEnrollRequest.setStrNotifyAddress(notifyAddress);

		submitEnrollRequest.setStrNotifyCity(oeSignUpDTO.getBillingAddressCity());
		submitEnrollRequest.setStrNotifyState(oeSignUpDTO.getBillingAdressState());
		submitEnrollRequest.setStrNotifyZip(oeSignUpDTO.getBillingAddressZipCode());

		startSvrcDate = CommonUtil.formatDateForNrgws(CommonUtil.getCurrentDateFormatted(MMddyyyy));
		moveInDate = startSvrcDate;

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,moveInDate is ::" + moveInDate);
		}

		if (StringUtils.isNotBlank(oeSignUpDTO.getTransactionType())
				&& MVI.equalsIgnoreCase(oeSignUpDTO.getTransactionType())) {
			enrollmentType = MOVEIN;
			contactText = "Move in " + startSvrcDate + ".Web.";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,contactText is ::" + contactText);
		}

		submitEnrollRequest.setStrEnrollmentType(enrollmentType);

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,enrollmentType is ::" + enrollmentType);
		}

		if (oeSignUpDTO.getPreferredLanguageCode() != null) {
			if ((oeSignUpDTO.getPreferredLanguageCode()).equalsIgnoreCase(ES)) {
				submitEnrollRequest.setStrlanguagePref(S);
			}

			else {
				submitEnrollRequest.setStrlanguagePref(E);
			}
		} else {
			submitEnrollRequest.setStrlanguagePref(E);
		}
		submitEnrollRequest.setStrSpecialReadDate(specialReadDate);
		submitEnrollRequest.setStrMovinDate(moveInDate);
		submitEnrollRequest.setStrBPEmail(oeSignUpDTO.getEmailAddress());
		// Fixed for Defect #91
		submitEnrollRequest.setStrBPHomeTelNum(oeSignUpDTO.getPhoneNumber());
		submitEnrollRequest.setStrSSN(EMPTY);

		if (StringUtils.isNotBlank(oeSignUpDTO.getEsiId())) {

			pointOfDeliveryId = oeSignUpDTO.getEsiId();
		} else {
			pointOfDeliveryId = ESIDNOTFOUND;
		}
		/*
		 * Following are standard dateFormat and TimeFormat defined
		 * 
		 * @JSINGH1@lntinfotech
		 */

		// START Default to system date and time if Offer Date/Offer Time are blank.
		// Added by Jenith on 04/16/2015
		String offerDateFormatted = null;
		String offerTimeFormatted = null;

		if (StringUtils.isBlank(oeSignUpDTO.getOfferDate())) {
			offerDateFormatted = DateUtil.getCurrentDateFormatted(MM_dd_yyyy);
		} else {
			offerDateFormatted = CommonUtil.formatDateForNrgws(oeSignUpDTO.getOfferDate());
		}

		if (StringUtils.isBlank(oeSignUpDTO.getOfferTime())) {
			offerTimeFormatted = DateUtil.getCurrentDateFormatted(TIME_FORMAT);
		} else {
			offerTimeFormatted = oeSignUpDTO.getOfferTime();
		}

		// END Default to system date and time if Offer Date/Offer Time are blank. Added
		// by Jenith on 04/16/2015

		submitEnrollRequest.setStrPointOfDeliveryID(pointOfDeliveryId);
		submitEnrollRequest.setStrEnrollmentDate(offerDateFormatted);
		submitEnrollRequest.setStrEnrollmentTime(offerTimeFormatted);
		submitEnrollRequest.setStrCACreadtionDate(offerDateFormatted);
		submitEnrollRequest.setStrOrgCreditRatingDate(offerDateFormatted);
		submitEnrollRequest.setStrCCSOrgCreditAgency(EMPTY);
		submitEnrollRequest.setStrBPOrgCreditScore(EMPTY);

		// Hardcoded as per Vishal Email dated on 04/09/2015
		submitEnrollRequest.setStrBPFileTestStatus(FLAG_C);

		submitEnrollRequest.setStrBPRegion(oeSignUpDTO.getBillingAdressState());
		submitEnrollRequest.setStrBPAptNum(oeSignUpDTO.getBillingAddressAptNumber());
		submitEnrollRequest.setStrBPCityPostalCode(oeSignUpDTO.getBillingAddressZipCode());
		submitEnrollRequest.setStrBPCity(oeSignUpDTO.getBillingAddressCity());

		submitEnrollRequest.setStrPromotionCode(oeSignUpDTO.getPromoCode());
		agreementNumber = WEB_PREFIX_OA_GME + CommonUtil.addLeadingZeroes(CommonUtil.getCurrentDateandTime(), 15);
		if (logger.isDebugEnabled()) {
			logger.debug(
					"EnrollmentService creating submitEnrollmentRequest,agreementNumber() is ::" + agreementNumber);
		}
		submitEnrollRequest.setStrAgreementNumber(agreementNumber);

		submitEnrollRequest.setStrSvrcFileTestStatus(FLAG_C);

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getPerson()() is ::"
					+ oeSignUpDTO.getFirstName());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getServiceAddress()() is ::"
					+ oeSignUpDTO.getServiceAddressAptNumber() + oeSignUpDTO.getBillingAddressStreetName()
					+ oeSignUpDTO.getServiceAddressStreetNumber() + oeSignUpDTO.getServiceAdressCity()
					+ oeSignUpDTO.getServiceAddressZipCode());
		}

		submitEnrollRequest.setStrSvrcStreet(oeSignUpDTO.getBillingAddressStreetName());
		submitEnrollRequest.setStrSvrcAptNum(oeSignUpDTO.getServiceAddressAptNumber());
		submitEnrollRequest.setStrSvrcHouseNum(oeSignUpDTO.getServiceAddressStreetNumber());
		submitEnrollRequest.setStrSvrcCity(oeSignUpDTO.getServiceAdressCity());
		submitEnrollRequest.setStrSvrcZip(oeSignUpDTO.getServiceAddressZipCode());
		submitEnrollRequest.setStrBPPOSidDLDate(EMPTY);
		submitEnrollRequest.setStrBPPosPOSidSSNDate(EMPTY);

		submitEnrollRequest.setStrOfferSequenceNumber(StringUtils.leftPad((oeSignUpDTO.getOfferCode()), 8, "0"));
		submitEnrollRequest.setStrProductPriceCode(EMPTY);
		submitEnrollRequest.setStrIncentiveCode(EMPTY);
		submitEnrollRequest.setStrmarketSegment(EMPTY);

		String DepositAmt = oeSignUpDTO.getPaymentAmount();
		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,DepositAmt is ::" + DepositAmt);
		}
		// txtPayAmt replaced with DepositAmt
		if (StringUtils.isNotBlank(DepositAmt)) {

			requestedAmount = DepositAmt;
			contactText += "$" + DepositAmt + " Deposit." + "Agr#" + agreementNumber;

			if (StringUtils.equals(DepositAmt, "0")) {
				depositCode = FLAG_B;
			} else {
				depositCode = FLAG_C;
			}
		} else {
			requestedAmount = EMPTY;
			reasonSEcurityDeposit = EMPTY;
			depositCode = EMPTY;
			contactText += "No Deposit." + "Agr#" + agreementNumber;

		}

		if (logger.isDebugEnabled()) {
			logger.debug("EnrollmentService creating submitEnrollmentRequest,contactText is ::" + contactText);
		}

		submitEnrollRequest.setStrContactText(contactText);

		submitEnrollRequest.setStrLogicalSystem(CHANNEL);
		submitEnrollRequest.setStrTransactionType(TRANSACTION_TYPE_CREATE);
		submitEnrollRequest.setStrInstallationType(INSTALLATION_TYPE);
		submitEnrollRequest.setStrNotifyWaiver(EMPTY);
		submitEnrollRequest.setStrBPCountryKey(COUNTRY_US);
		submitEnrollRequest.setStrSvrcCountryKey(COUNTRY_US);

		submitEnrollRequest.setStrExternalDunsNumber(EMPTY);
		submitEnrollRequest.setStrOutsideIndicators(EMPTY);
		submitEnrollRequest.setStrDivision(DIVISION);

		submitEnrollRequest.setStrBPSexAvailable(SEXAVAILABLE);
		submitEnrollRequest.setStrBPAddressType(ADDRESS_TYPE);
		submitEnrollRequest.setStrBPTimeZone(TIMEZONE);
		submitEnrollRequest.setStrSvrcTimeZone(TIMEZONE);
		submitEnrollRequest.setStrSvrcAddressType(ADDRESS_TYPE);
		submitEnrollRequest.setStrBPInfoRelease(EMPTY);

		submitEnrollRequest.setStrCompanyCode(oeSignUpDTO.getCompanyCode()); // Fixed prod issue of hardcoded company
																				// code

		submitEnrollRequest.setStrPaymentTerms(EMPTY);
		submitEnrollRequest.setStrValidToDate(EMPTY);
		submitEnrollRequest.setStrValidFromDate(EMPTY);
		submitEnrollRequest.setStrDLRegion(TX);
		submitEnrollRequest.setStrDLCountry(COUNTRY_US);
		submitEnrollRequest.setStrSvrcRegion(TX);
		submitEnrollRequest.setStrCACreationUser(CHANNEL);
		submitEnrollRequest.setStrPaperlessFlag(FLAG_N); // For Reliant we never set to paperless
		enrollmentHoldType = PPYHOLD;

		if (MOVEIN.equals(enrollmentType) && ON.equals(oeSignUpDTO.getSwitchHoldFlag())) {
			if (EMPTY.equals(enrollmentHoldType))
				enrollmentHoldType = SWITCHHOLD;
			else
				enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + SWITCHHOLD;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(
					"EnrollmentService creating submitEnrollmentRequest,enrollmentHoldType is ::" + enrollmentHoldType);
		}
		submitEnrollRequest.setStrEnrollmentHoldType(enrollmentHoldType);
		BigDecimal reqAmt = null;
		if (StringUtils.isNotBlank(requestedAmount)) {
			reqAmt = new BigDecimal(requestedAmount);
		} else {
			reqAmt = new BigDecimal("0.0");
		}
		submitEnrollRequest.setStrRequestedAmount(reqAmt);

		submitEnrollRequest.setStrDepositReason(reasonSEcurityDeposit);

		submitEnrollRequest.setStrDepositCode(depositCode);

		submitEnrollRequest.setStrPriorityMovinFlag("N");
		submitEnrollRequest.setStrFreqFlyerFirstName(EMPTY);
		submitEnrollRequest.setStrFreqFlyerLastName(EMPTY);
		submitEnrollRequest.setStrFreqFlyerNo(EMPTY);

		if (logger.isDebugEnabled()) {
			logger.debug("submitEnrollmentRequest: value of WebTSDP is ::" + oeSignUpDTO.getTdspCode());
		}
		submitEnrollRequest.setStrWebTsdp(oeSignUpDTO.getTdspCode());
		submitEnrollRequest.setStrAgentId(EMPTY);
		return submitEnrollRequest;
	}

	protected GMDEnrollmentResponse submitOnlineEnrollment(GMDEnrollmentRequest enrollmentRequest) throws Exception {
		GMDEnrollmentResponse response = new GMDEnrollmentResponse();
		String errorCode = null;

		/**
		 * BpMatchFlag is TRUE means the customer was found to be existing but could not
		 * fetch its bpnumber from DB so now customer care will get back to it
		 */

		enrollmentRequest.setRecentCallMade(CALL_NAME_SUBMIT_ENROLLMENT);

		SubmitEnrollRequest submitEnrollRequest = createSubmitEnrollRequest(enrollmentRequest);

		SubmitEnrollResponse submitEnrollResponse = oeProxy.submitEnrollment(submitEnrollRequest);

		// logger.info("Request: "
		// + XmlUtil.pojoToXMLwithRootElement(submitEnrollRequest,
		// "SubmitEnrollment"));
		logger.info("Error code: " + submitEnrollResponse.getStrErrCode());
		logger.info("Error message: " + submitEnrollResponse.getStrErrMessage());

		if (submitEnrollResponse.getStrErrCode() == null) {

			logger.info("Enrollment Happened Successfully for ESID " + enrollmentRequest.getEsiId());

			response.setBusinessPartnerNumber(submitEnrollResponse.getStrBPNumber());
			response.setIdocNumber(submitEnrollResponse.getStrIDOCNumber());
			response.setContractAccountNumber(submitEnrollResponse.getStrCANumber());
			if (StringUtils.isBlank(submitEnrollResponse.getStrCANumber())) {
				response.setCaCheckDigit(ZERO);
			} else {
				response.setCaCheckDigit(submitEnrollResponse.getStrCheckDigit());
			}
			// if (logger.isDebugEnabled()) {
			logger.info(enrollmentRequest.getEsiId() + "BPnumber is" + submitEnrollResponse.getStrBPNumber());
			logger.debug(
					enrollmentRequest.getEsiId() + "ContractAccount number is" + submitEnrollResponse.getStrCANumber());
			logger.debug(enrollmentRequest.getEsiId() + "CheckDigit from enrollment response is"
					+ submitEnrollResponse.getStrCheckDigit());
			if (submitEnrollResponse.getStrBPNumber() == null) {
				logger.debug(enrollmentRequest.getEsiId() + "bp numbr is null");
			}

			// }
		}

		else if (submitEnrollResponse.getStrErrCode() != null) {

			// if (logger.isDebugEnabled()) {
			logger.info(enrollmentRequest.getEsiId() + "Enrollment Error Message:"
					+ submitEnrollResponse.getStrErrMessage());
			// }

			errorCode = submitEnrollResponse.getStrErrCode();
			response.setErrorCode(errorCode);
			response.setErrorDescription(Constants.RESULT_DESCRIPTION_ENROLL_FAILED);
		}

		return response;
	}

	private void handleSubmitEnrollmentError(GMDEnrollmentResponse response, GMDEnrollmentRequest enrollmentRequest,
			Exception e) {

		response.setErrorCode(CCSERR);
		response.setErrorDescription(Constants.EXCEPTION_IN_SUBMIT_ENROLLMENT_CALL);

		logger.error(enrollmentRequest.getEsiId()
				+ " inside EnrollmentHelper :: Exception in SubmitEnrollment call error is :: ", e);
	}

	protected Boolean updateContactInformation(GMDEnrollmentResponse response, GMDEnrollmentRequest enrollmentRequest) {
		String METHOD_NAME = "OeBoHelper: updateContactInformation(..)";
		logger.debug("Start:" + METHOD_NAME);

		try {
			UpdateContactRequest updateContactRequest = new UpdateContactRequest();
			updateContactRequest.setStrBPNumber(response.getBusinessPartnerNumber());
			updateContactRequest.setStrUniqueId(response.getBusinessPartnerNumber());
			updateContactRequest.setStrCANumber(response.getContractAccountNumber());
			updateContactRequest.setStrCompanyCode(enrollmentRequest.getCompanyCode());
			updateContactRequest.setBrandName(enrollmentRequest.getBrandName());
			// updateContactRequest.setIsCreateCRMRecord(X_VALUE);
			updateContactRequest.setIsTelephoneUpdated(FLAG_Y);
			updateContactRequest.setIsEmailUpdated(FLAG_Y);
			updateContactRequest.setIsMktPrefUpdated(FLAG_Y);
			// setting the marketing preferences
			UpdateContactRequestAttNamValPairMapEntry[] attNamValPairMap = new UpdateContactRequestAttNamValPairMapEntry[2];
			attNamValPairMap[0] = new UpdateContactRequestAttNamValPairMapEntry();
			attNamValPairMap[0].setKey("MKT_EMAIL");
			attNamValPairMap[0].setValue(FLAG_N);

			attNamValPairMap[1] = new UpdateContactRequestAttNamValPairMapEntry();
			attNamValPairMap[1].setKey("OFFER_EMAIL");
			attNamValPairMap[1].setValue(FLAG_N);

			updateContactRequest.setAttNamValPairMap(attNamValPairMap);

			/** Cirro Changes Start - Msadriw1 */
			List<UpdatePhoneDO> updatePhoneDOList = new ArrayList<UpdatePhoneDO>();

			// set data for Home Phone Number
			logger.info("inside processOeEnrollment:: setting evening phone number ");
			/*
			 * String strEveningPhone = oeSignupDTO.getEveningPhoneNo1() +
			 * oeSignupDTO.getEveningPhoneNo2() + oeSignupDTO.getEveningPhoneNo3();
			 */
			logger.info("inside processOeEnrollment:: phone number is :: " + enrollmentRequest.getPhoneNumber());
			if (StringUtils.isNotBlank(enrollmentRequest.getPhoneNumber())) {
				logger.info("inside " + METHOD_NAME + ":: setting evening ph number to Update PhoneDO");
				UpdatePhoneDO homePhoneDO = new UpdatePhoneDO();
				homePhoneDO.setPhoneNumber(enrollmentRequest.getPhoneNumber());
				homePhoneDO.setRemark(REMARK_HOME);
				updatePhoneDOList.add(homePhoneDO);
				logger.info("Setting HOME Phone ~~~ " + enrollmentRequest.getPhoneNumber());
			}
			if (null != updatePhoneDOList && updatePhoneDOList.size() > 0) {
				UpdatePhoneDO[] phoneDOArr = updatePhoneDOList.toArray(new UpdatePhoneDO[0]);
				updateContactRequest.setPhoneDO(phoneDOArr);
			}
			updateContactRequest.setStrEmailId(enrollmentRequest.getEmailAddress());
			/** Cirro Changes End - Msadriw1 */

			// oeSignupDTO.setRecentCallMade(CALL_NAME_UPDATE_CONTACT_INFO);
			logger.info(METHOD_NAME + " tracking Number is :" + enrollmentRequest.getEsiId()
					+ ": testing and recent call made till now is " + enrollmentRequest.getRecentCallMade());
			// Calling updateContact asynchronously
			// ProfileDomainHelper profileDomainHelper = new ProfileDomainHelper();
			// UpdateContactResponse updateContactRes =
			// profileDomainHelper.updateContact(updateContactRequest);
			logger.info("Start: Async call updateContact(...)");
			asyncHelper.updateContactInCRM(updateContactRequest);
			logger.info("End: Async call updateContact(...)");
			// logger.debug("tracking Number is
			// :"+oeSignUpVo.getSvc_location_trackingNum()+"updateContactRes error is "
			// +updateContactRes.getErrorCode());
		} catch (Exception ex) {
			logger.error(
					METHOD_NAME + " Error updating Contact Preferences. Skipping and continuing" + ex.getMessage());
		}

		logger.debug("End:" + METHOD_NAME);

		return null;
	}
}