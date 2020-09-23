package com.multibrand.bo;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.impl.GMDOEDAOImpl;
import com.multibrand.domain.AlertPrefDTO;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.PpdCreateRequest;
import com.multibrand.domain.PpdCreateResponse;
import com.multibrand.domain.SSDomain;
import com.multibrand.domain.SSDomainPortBindingStub;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.domain.SubmitEnrollResponse;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.UpdateAlertPrefRequest;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactRequestAttNamValPairMapEntry;
import com.multibrand.domain.UpdatePhoneDO;
import com.multibrand.dto.GMDPersonDetailsDTO;
import com.multibrand.dto.GMDServiceLocationDetailsDTO;
import com.multibrand.dto.request.GMDEnrollmentRequest;
import com.multibrand.dto.response.GMDEnrollmentResponse;
import com.multibrand.exception.NRGException;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.AsyncHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.proxy.OEProxy;
import com.multibrand.service.AddressService;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.GMDService;
import com.multibrand.service.OEService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.request.ESIDDO;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.gmd.GMDOfferResponse;
import com.multibrand.vo.response.gmd.GMDPricingResponse;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;
import com.multibrand.vo.response.gmd.HourlyPriceResponse;

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
	private HistoryBO historyBO;

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
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	private AsyncHelper asyncHelper;
	
	@Autowired
	private GMDOEDAOImpl gmdOEDAOImpl;

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
							
			HourlyPriceResponse response = historyBO.getGMDPrice(accountNumber, contractId, esiId, sessionId, companyCode);
			
			gmdPricingResp = gmdService.getGMDPriceDetails(accountNumber, contractId, companyCode, esiId, sessionId, response);

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

		String methodName = "OEBO: getESIDAndCalendarDates(..)";
		logger.debug("Start in getESIDAndCalendarDates:{}" , methodName);

		EsidInfoTdspCalendarResponse response = new EsidInfoTdspCalendarResponse();
		ESIDDO esidDo = null;

		Locale localeObj = new Locale("en", "US");


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
				response.setTdspCode(tdspCodeCCSForEsid);
				tdspCodeCCS = tdspCodeCCSForEsid;
				logger.info("TDSP Code:{}" , esidDo.getEsidTDSP());
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
					logger.debug("ERROR in getESIDAndCalendarDates:{}" , methodName);
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

				if (transactionType.equalsIgnoreCase(TRANSACTION_TYPE_SWITCH)
						&& (StringUtils.isNotEmpty(esidDo.getEsidStatus())
								&& esidDo.getEsidStatus().equalsIgnoreCase(STATUS_ACTIVE))) {
					logger.debug("ERROR:{}" , methodName);

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
					logger.debug("ERROR:{}" , methodName);
					response.setStatusCode(STATUS_CODE_STOP);
					response.setAvailableDates(EMPTY);
					response.setTdspFee(EMPTY);
					response.setErrorCode(MESSAGE_CODE_BUSINESS_METER);
					response.setErrorDescription(
							this.msgSource.getMessage(MESSAGE_CODE_BUSINESS_METER, null, localeObj));
					return response;
				}
			} 

			// GET tdsp calendar dates
			this.getTdspDates(companyCode, transactionType, tdspCodeCCS, esidDo, response, localeObj);
		} catch (Exception e) {
			logger.error("OEBO.getESIDInfo() Exception occurred when invoking getESIDInfo", e);
			response.setErrorCode(RESULT_CODE_SUCCESS);
			response.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION + ": " + e.getMessage());
			response.setStatusCode(STATUS_CODE_CONTINUE);

		} 

		// Default to EMPTY if statusflag is "OFF"
		if (StringUtils.equalsIgnoreCase("OFF", response.getSwitchHoldFlag())) {
			response.setSwitchHoldFlag(EMPTY);
		}

		logger.debug("END:{}" , methodName);

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
				if (StringUtils.equalsIgnoreCase(esidProfileResponse.getBlockStatus(), FLAG_X)) {
					esidDO.setEsidBlocked(true);
				} else {
					esidDO.setEsidBlocked(false);
				}


			}
			logger.debug("OEBO.setESIDDTO() esidDTO::{} " , esidDO);
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
		String methodName = "OEBO: getTdspDates(..)";

		logger.debug("Start getTdspDates:{}" , methodName);
		String internaltdspCodeCCS = "";

		if (esidDo != null && StringUtils.isNotEmpty(esidDo.getEsidTDSP())) {
			internaltdspCodeCCS = this.appConstMessageSource.getMessage(esidDo.getEsidTDSP(), null, null);
		}

		// TDSP mismatch scenario
		if ((StringUtils.isNotEmpty(tdspCodeCCS) && StringUtils.isNotEmpty(internaltdspCodeCCS))
				&& !tdspCodeCCS.equalsIgnoreCase(internaltdspCodeCCS)) {
			response.setResultCode(RESULT_CODE_SUCCESS);
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
		if (StringUtils.isNotEmpty(esidDo.getEsidNumber())) {
			request.setEsiid(esidDo.getEsidNumber());
		} else {
			request.setEsiid("");
		}
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

		logger.debug("End getTdspDates:{}" , methodName);
	}

	public GMDEnrollmentResponse submitEnrollment(GMDEnrollmentRequest enrollmentRequest, String sessionId) {
		String methodName = "OEBO: submitEnrollment(..)";
		logger.debug("Start submitEnrollment:{}" , methodName);

		GMDEnrollmentResponse response = new GMDEnrollmentResponse();

		long startTime = CommonUtil.getStartTime();
		
		try {
			// 1. Call online enrollment submission to CCS.
			response = this.submitOnlineEnrollment(enrollmentRequest);
			if (StringUtils.isNotBlank(response.getContractAccountNumber())
					&& StringUtils.isNotBlank(response.getBusinessPartnerNumber())) {
				
				this.prepayDocCreate(response, enrollmentRequest);
				this.updateContactInformation(response, enrollmentRequest);
			}

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
			utilityloggerHelper.logTransaction("getGMDsubmitEnrollment", false,
					enrollmentRequest, response,
					response.getResultDescription(),
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					enrollmentRequest.getCompanyCode());
		}

		logger.debug("END:{}" , methodName);

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

		String bpFullName = "";
		String enrollmentType = "";
		String contactText = "";
		String moveInDate = "";
		String startSvrcDate = oeSignUpDTO.getServiceStartDate();
		String notifyAddress = "";
		String pointOfDeliveryId = "";
		String requestedAmount = "";
		String reasonSEcurityDeposit = "";
		String depositCode = "";
		String agreementNumber = "";
		String enrollmentHoldType = "";

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

		logger.debug(" enrollmentService SubmitEnrollment creating request:: checking for FullName");

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
		
		logger.debug("submitEnrollment request :: Name is {}" , bpFullName);

		submitEnrollRequest.setStrCampaignCode(oeSignUpDTO.getCampaignCode());
		// START test logs
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressAptNumber())
				|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetName())
						|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetNumber())) {

			submitEnrollRequest.setStrBPStreet(oeSignUpDTO.getBillingAddressStreetName());
			submitEnrollRequest.setStrBPHouseNum(oeSignUpDTO.getBillingAddressStreetNumber());

		} else {
			submitEnrollRequest.setStrBPPOBoxCountry(COUNTRY_US);
			submitEnrollRequest.setStrBPPOBoxRegion(oeSignUpDTO.getBillingAddressState());
			submitEnrollRequest.setStrBPPOBoxPostalCode(oeSignUpDTO.getBillingAddressZipCode());
			submitEnrollRequest.setStrBPPOBox(oeSignUpDTO.getBillingAddressPoBox());
		}
		// END test logs

		logger.debug(
					"creating SubmitEnrollmentRequest in enrollmentService:: passed the null check for Billing Address");

		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressAptNumber())
				|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetName())
						|| StringUtils.isNotBlank(oeSignUpDTO.getBillingAddressStreetNumber())) {

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
		submitEnrollRequest.setStrNotifyState(oeSignUpDTO.getBillingAddressState());
		submitEnrollRequest.setStrNotifyZip(oeSignUpDTO.getBillingAddressZipCode());

		moveInDate = oeSignUpDTO.getServiceStartDate();

		logger.debug("EnrollmentService creating submitEnrollmentRequest,moveInDate is ::{}" , moveInDate);


		if (StringUtils.isNotBlank(oeSignUpDTO.getTransactionType())
				&& MVI.equalsIgnoreCase(oeSignUpDTO.getTransactionType())) {
			enrollmentType = MOVEIN;
			contactText = "Move in " + startSvrcDate + ".Web.";
		} else if (StringUtils.isNotBlank(oeSignUpDTO.getTransactionType())
				&& SWI.equalsIgnoreCase(oeSignUpDTO.getTransactionType())) {
			enrollmentType = SWITCH;			
			if(StringUtils.isNotBlank(startSvrcDate)){
				contactText = "Selected Date Switch." + startSvrcDate + ".Web.";
			}
			else{
		        contactText = "Standard Switch. Web.";
			}
		}		

		logger.debug("EnrollmentService creating submitEnrollmentRequest,contactText is ::{}" , contactText);


		submitEnrollRequest.setStrEnrollmentType(enrollmentType);

		logger.debug("EnrollmentService creating submitEnrollmentRequest,enrollmentType is ::{}" , enrollmentType);


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
		submitEnrollRequest.setStrSpecialReadDate(moveInDate);
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

		submitEnrollRequest.setStrBPRegion(oeSignUpDTO.getBillingAddressState());
		submitEnrollRequest.setStrBPAptNum(oeSignUpDTO.getBillingAddressAptNumber());
		submitEnrollRequest.setStrBPCityPostalCode(oeSignUpDTO.getBillingAddressZipCode());
		submitEnrollRequest.setStrBPCity(oeSignUpDTO.getBillingAddressCity());

		submitEnrollRequest.setStrPromotionCode(this.appConstMessageSource
				.getMessage("gmd.promo.web.equivalent." + oeSignUpDTO.getTdspCode(), null, null));
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 
		agreementNumber = WEB_PREFIX_OA_GME + StringUtils.leftPad(String.valueOf(timestamp.getTime()).substring(0,10), 15, '0');

		logger.debug(
					"EnrollmentService creating submitEnrollmentRequest,agreementNumber() is ::{}" , agreementNumber);
		
		submitEnrollRequest.setStrAgreementNumber(agreementNumber);

		submitEnrollRequest.setStrSvrcFileTestStatus(FLAG_C);

			logger.debug("EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getPerson()() is ::{}"
					, oeSignUpDTO.getFirstName());


		submitEnrollRequest.setStrSvrcStreet(oeSignUpDTO.getBillingAddressStreetName());
		submitEnrollRequest.setStrSvrcAptNum(oeSignUpDTO.getServiceAddressAptNumber());
		submitEnrollRequest.setStrSvrcHouseNum(oeSignUpDTO.getServiceAddressStreetNumber());
		submitEnrollRequest.setStrSvrcCity(oeSignUpDTO.getServiceAddressCity());
		submitEnrollRequest.setStrSvrcZip(oeSignUpDTO.getServiceAddressZipCode());
		submitEnrollRequest.setStrBPPOSidDLDate(EMPTY);
		submitEnrollRequest.setStrBPPosPOSidSSNDate(EMPTY);

		submitEnrollRequest.setStrOfferSequenceNumber(StringUtils.leftPad((this.appConstMessageSource
				.getMessage("gmd.offer.web.equivalent." + oeSignUpDTO.getTdspCode(), null, null)), 8, "0"));
		submitEnrollRequest.setStrProductPriceCode(EMPTY);
		submitEnrollRequest.setStrIncentiveCode(EMPTY);
		submitEnrollRequest.setStrmarketSegment("RS");

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
		submitEnrollRequest.setStrDLRegion(EMPTY);
		submitEnrollRequest.setStrDLCountry(EMPTY);
		submitEnrollRequest.setStrSvrcRegion(EMPTY);
		submitEnrollRequest.setStrCACreationUser(CHANNEL);
		submitEnrollRequest.setStrPaperlessFlag(FLAG_N); // For Reliant we never set to paperless
		enrollmentHoldType = PPYHOLD;

		if (MOVEIN.equals(enrollmentType) && ON.equals(oeSignUpDTO.getSwitchHoldFlag())) {
			if (EMPTY.equals(enrollmentHoldType))
				enrollmentHoldType = SWITCHHOLD;
			else
				enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + SWITCHHOLD;
		}

		logger.debug(
					"EnrollmentService creating submitEnrollmentRequest,enrollmentHoldType is ::{}" , enrollmentHoldType);
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

		logger.debug("submitEnrollmentRequest: value of WebTSDP is ::{}" , oeSignUpDTO.getTdspCode());

		submitEnrollRequest.setStrWebTsdp(this.appConstMessageSource
				.getMessage("ccs.tdsp.web.equivalent." + oeSignUpDTO.getTdspCode(), null, null));
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
		if(gmdOEDAOImpl.checkTrackNo(enrollmentRequest)) {
			response.setErrorCode(Constants.RESULT_CODE_TWO);
			response.setErrorDescription("Enrollment already registered for Esid (" + enrollmentRequest.getEsiId()
					+ ") with Start Date - " + enrollmentRequest.getServiceStartDate());
			return response;
		}

		SubmitEnrollRequest submitEnrollRequest = createSubmitEnrollRequest(enrollmentRequest);
		submitEnrollRequest.setBypassPosId(FLAG_X);//This is fo only GMD application
		Gson gson = new Gson();
		logger.info("Before submitting JSON:{0}", gson.toJson(submitEnrollRequest));
		SubmitEnrollResponse submitEnrollResponse = oeProxy.submitEnrollment(submitEnrollRequest);

		logger.info("Error code: {}" , submitEnrollResponse.getStrErrCode());
		logger.info("Error message: {}" , submitEnrollResponse.getStrErrMessage());

		if (submitEnrollResponse.getStrErrCode() == null) {

			logger.info("Enrollment Happened Successfully for ESID {}" , enrollmentRequest.getEsiId());

			response.setBusinessPartnerNumber(submitEnrollResponse.getStrBPNumber());
			response.setIdocNumber(submitEnrollResponse.getStrIDOCNumber());
			response.setContractAccountNumber(submitEnrollResponse.getStrCANumber());
			if (StringUtils.isBlank(submitEnrollResponse.getStrCANumber())) {
				response.setCaCheckDigit(ZERO);
			} else {
				response.setCaCheckDigit(submitEnrollResponse.getStrCheckDigit());
			}
			logger.info("BPnumber is{}" , submitEnrollResponse.getStrBPNumber());

			if (submitEnrollResponse.getStrBPNumber() == null) {
				logger.debug(enrollmentRequest.getEsiId() , "bp numbr is null {}");
			}
			GMDPersonDetailsDTO personDetailsDTO = getGMDPersonDetailsDTO(submitEnrollRequest,submitEnrollResponse,enrollmentRequest);
			GMDServiceLocationDetailsDTO serviceLocationDetailsDTO = getGMDServiceLocationDetailsDTO(
					submitEnrollRequest, submitEnrollResponse, enrollmentRequest,
					personDetailsDTO.getPersonId());
			boolean isReutrn = gmdOEDAOImpl.inserPersonDetails(personDetailsDTO);
			if (isReutrn) {
				gmdOEDAOImpl.insertServiceLocationLocation(serviceLocationDetailsDTO);
			}

		}

		else if (submitEnrollResponse.getStrErrCode() != null) {

			logger.info(enrollmentRequest.getEsiId() + "Enrollment Error Message:"
					+ submitEnrollResponse.getStrErrMessage());

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
		String methodName = "OeBoHelper: updateContactInformation(..)";
		logger.debug("{}" , methodName);

		try {
			UpdateContactRequest updateContactRequest = new UpdateContactRequest();
			updateContactRequest.setStrBPNumber(response.getBusinessPartnerNumber());
			updateContactRequest.setStrUniqueId(response.getBusinessPartnerNumber());
			updateContactRequest.setStrCANumber(response.getContractAccountNumber());
			updateContactRequest.setStrCompanyCode(enrollmentRequest.getCompanyCode());
			updateContactRequest.setBrandName(enrollmentRequest.getBrandName());
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
			List<UpdatePhoneDO> updatePhoneDOList = new ArrayList<>();


			logger.info("inside processOeEnrollment:: phone number is :: {}" , enrollmentRequest.getPhoneNumber());
			if (StringUtils.isNotBlank(enrollmentRequest.getPhoneNumber())) {
				logger.info("inside  setting evening ph number to Update PhoneDO");
				UpdatePhoneDO homePhoneDO = new UpdatePhoneDO();
				homePhoneDO.setPhoneNumber(enrollmentRequest.getPhoneNumber());
				homePhoneDO.setRemark(REMARK_HOME);
				updatePhoneDOList.add(homePhoneDO);
				logger.info("Setting HOME Phone ~~~ {}" , enrollmentRequest.getPhoneNumber());
			}
			if ( !updatePhoneDOList.isEmpty()) {
				UpdatePhoneDO[] phoneDOArr = updatePhoneDOList.toArray(new UpdatePhoneDO[0]);
				updateContactRequest.setPhoneDO(phoneDOArr);
			}
			updateContactRequest.setStrEmailId(enrollmentRequest.getEmailAddress());
		
			logger.info("Start: Async call updateContact(...)");
			asyncHelper.updateContactInCRM(updateContactRequest);
			logger.info("End: Async call updateContact(...)");

		} catch (Exception ex) {
			logger.error(
					" Error updating Contact Preferences. Skipping and continuing{}" , ex);
		}

		logger.debug("End:{}" , methodName);

		return false;
	}
	
	/**
	 * @param enrollmentRequest
	 * @return
	 */
	public String prepayDocCreate(GMDEnrollmentResponse response, GMDEnrollmentRequest enrollmentRequest) {
		
		PpdCreateResponse pPDCreateResponse=new PpdCreateResponse(); 
		String createPrepayDocOutput = EMPTY;
		try{

			logger.info("SubmitEnrollment :: inside function in prepayDocCreate");
		
		SSDomain proxyClient = getSSDomainProxyClient();
		PpdCreateRequest pPDCreateRequest = createPrepayDocCreateRequest(response, enrollmentRequest);
		
		pPDCreateResponse = proxyClient.prepayDocCreate(pPDCreateRequest);
		
		String prepayDocId = pPDCreateResponse.getPrepayDocID();
	    logger.info("inside EnrollmentService :: inside prepayDocCreate Call ::prapayDocId created is ");
	    if (prepayDocId != null && (!prepayDocId.equals("") && Long.parseLong(prepayDocId) != 0))
	        {
	            createPrepayDocOutput="curlaction";
					
	            UpdateAlertPrefRequest updateAlertPrefRequest = createUpdateAlertPrefRequest(response, enrollmentRequest);
				asyncHelper.asychUpdatePrepayAlertPreferences(updateAlertPrefRequest);
	            
	        }
	    else{
	            String errorCode = pPDCreateResponse.getErrorCode();
	            createPrepayDocOutput="errorCode";
	            logger.info("errorCode in prepayDocCreate call is ::{}",errorCode);
	       }
		 
		}
		catch(Exception e)
		{
			pPDCreateResponse.setErrorCode("MSG_ERR_SUBMIT_ENROLLMENT");
			pPDCreateResponse.setErrorMessage(e.getMessage());
			logger.error("inside enrollmentService:: in prepayDocCreate() ::prepayDocCreate Call Failed with error ::{}",e);
			
		}
		
		return createPrepayDocOutput;
	}	
	
	
	/**
	 * @param enrollmentRequest
	 * @return
	 */
public PpdCreateRequest createPrepayDocCreateRequest(GMDEnrollmentResponse response, GMDEnrollmentRequest enrollmentRequest) {
		
		logger.debug("inside EnrollmentHelper :: inside createPrepayDocTxn");
		String srvcStartDate;
		PpdCreateRequest pPDCreateRequest = new PpdCreateRequest();
		pPDCreateRequest.setBrandName(enrollmentRequest.getBrandName());
		pPDCreateRequest.setCompanyCode(enrollmentRequest.getCompanyCode());
		pPDCreateRequest.setContractAccountNumber(response.getContractAccountNumber());
		
		if(StringUtils.isNotBlank(enrollmentRequest.getServiceStartDate())) {
			srvcStartDate = enrollmentRequest.getServiceStartDate();
		} else{
			SimpleDateFormat sdfDate = new SimpleDateFormat(MM_dd_yyyy);
			Calendar cal = Calendar.getInstance();
			srvcStartDate=sdfDate.format(cal.getTime());
		}
		
		pPDCreateRequest.setServiceStartDate(srvcStartDate);
		
		pPDCreateRequest.setStatus("0001");
		
		pPDCreateRequest.setTollTagStartDate(srvcStartDate);
		pPDCreateRequest.setTollTagEndDate(INDEFINITE_END_DATE);		   
		return pPDCreateRequest;
	}

	/**
	 * Method createUpdateAlertPrefRequest.
	 * 
	 * @param enrollmentRequest
	 *            GMDEnrollmentRequest
	 * @return UpdateAlertPrefRequest
	 */
	public UpdateAlertPrefRequest createUpdateAlertPrefRequest(GMDEnrollmentResponse response, GMDEnrollmentRequest enrollmentRequest) {

		String payReceivedEmailVal = "E";
		String weeklyBalEmailVal = "E";
		String payReceivedSmsVal = EMPTY;
		String weeklyBalSmsVal = EMPTY;
		String payReceivedPhoneVal = EMPTY;
		String weeklyBalPhoneVal = EMPTY;

		
		String strPayReceived = payReceivedEmailVal + payReceivedSmsVal + payReceivedPhoneVal;
		String strWeeklySum = weeklyBalEmailVal + weeklyBalSmsVal + weeklyBalPhoneVal;


		UpdateAlertPrefRequest updateAlertPrefRequest = new UpdateAlertPrefRequest();
		AlertPrefDTO[] unSubscribeRequests = null;
		updateAlertPrefRequest.setStrBPNunber(response.getBusinessPartnerNumber());

		updateAlertPrefRequest.setStrCANumber(response.getContractAccountNumber());

		updateAlertPrefRequest
				.setStrCompanyCode(appConstMessageSource.getMessage(Constants.PROP_COMPANY_CODE, null, null));

		String strCONumber = EMPTY;
		String esid = EMPTY;

		updateAlertPrefRequest.setStrCONumber(strCONumber);
		updateAlertPrefRequest.setStrESID(esid);
		String strRequestType = TWO;
		updateAlertPrefRequest.setStrRequestType(strRequestType);
		updateAlertPrefRequest.setStrSubscriberId(PREPAY);
		updateAlertPrefRequest.setUnSubscribeRequests(unSubscribeRequests); // unSubscribeRequests
																			// are
																			// set
																			// to
																			// null.
		AlertPrefDTO dailyBalSumDTO = null;
		AlertPrefDTO tollTagBalDTO = null;
		AlertPrefDTO payReceivedDTO = null;
		AlertPrefDTO weeklySumDTO = null;
		List<AlertPrefDTO> subscribeRequestList = new ArrayList<>();
		
		dailyBalSumDTO = new AlertPrefDTO();
		dailyBalSumDTO.setStrEventId(GME_PP_ALERT_DBA1);
		dailyBalSumDTO.setStrParamName(COMM_PREF);
		dailyBalSumDTO.setStrParamValue("E");
		subscribeRequestList.add(dailyBalSumDTO);
		
		tollTagBalDTO = new AlertPrefDTO();
		tollTagBalDTO.setStrEventId(PP_ALERT4);
		tollTagBalDTO.setStrParamName(COMM_PREF);
		tollTagBalDTO.setStrParamValue("E");
		subscribeRequestList.add(tollTagBalDTO);		

		if (!StringUtils.isEmpty(strPayReceived)) {
			payReceivedDTO = new AlertPrefDTO();
			payReceivedDTO.setStrEventId(LOW_ALERT);
			payReceivedDTO.setStrParamName(COMM_PREF);
			payReceivedDTO.setStrParamValue(strPayReceived);
			subscribeRequestList.add(payReceivedDTO);
		}
		if (!StringUtils.isEmpty(strWeeklySum)) {
			weeklySumDTO = new AlertPrefDTO();
			weeklySumDTO.setStrEventId(PAY_ALERT);
			weeklySumDTO.setStrParamName(COMM_PREF);
			weeklySumDTO.setStrParamValue(strWeeklySum);
			subscribeRequestList.add(weeklySumDTO);
		}
		int len = subscribeRequestList.size();
		AlertPrefDTO[] alert = new AlertPrefDTO[len];
		for (int i = 0; i < len; i++) {
			alert[i] = subscribeRequestList.get(i);

			logger.debug("Value of the parameter in AlertPrefDTO: "
					+ alert[i].getStrParamValue());

		}
		updateAlertPrefRequest.setSubscribeRequests(alert);

		return updateAlertPrefRequest;

	}

	public GMDOfferResponse getGMDOfferDocs(String tdspCode) {
		String methodName = "OEBO: getGMDOfferDocs(..)";
		logger.debug("Start:{}" , methodName);
		
		String baseURL= envMessageReader.getMessage(Constants.GME_BASE_URL);
		
		String legalDocsExt = "/files/";
		
		GMDOfferResponse gmdOfferResponse = new GMDOfferResponse();
		
		gmdOfferResponse.setStrEFLDocID(baseURL.trim()+legalDocsExt+this.appConstMessageSource
		.getMessage("gmd.offer.efl.equivalent." + tdspCode, null, null));
		
		gmdOfferResponse.setStrTOSDocID(baseURL.trim()+legalDocsExt +this.appConstMessageSource
		.getMessage("gmd.offer.tos.equivalent." + tdspCode, null, null));
		
		gmdOfferResponse.setStrYRAACDocID(baseURL.trim()+legalDocsExt+this.appConstMessageSource
		.getMessage("gmd.offer.yraac.equivalent." + tdspCode, null, null));	
		
		gmdOfferResponse.setStrPrepayDisID(baseURL.trim()+legalDocsExt+this.appConstMessageSource
		.getMessage("gmd.offer.predis.equivalent." + tdspCode, null, null));			
		
		
		
		logger.debug("End:{}" , methodName);
		return gmdOfferResponse;
		
	}
	
	protected SSDomain getSSDomainProxyClient()  {
		return (SSDomain) getServiceProxy(SSDomainPortBindingStub.class, SS_SERVICE_ENDPOINT_URL);
	}	
	
	
	private GMDPersonDetailsDTO getGMDPersonDetailsDTO(SubmitEnrollRequest submitEnrollRequest,
			SubmitEnrollResponse submitEnrollResponse, GMDEnrollmentRequest enrollmentRequest) {
		GMDPersonDetailsDTO gmdPersonDetailsDTO = new GMDPersonDetailsDTO();

		gmdPersonDetailsDTO.setBusinessPartnerNumber(submitEnrollResponse.getStrBPNumber());
		gmdPersonDetailsDTO.setDateOfBirth(enrollmentRequest.getDateOfBirth());
		gmdPersonDetailsDTO.setEmailAddress(enrollmentRequest.getEmailAddress());
		gmdPersonDetailsDTO.setIdocNumber(submitEnrollResponse.getStrIDOCNumber());
		gmdPersonDetailsDTO.setLanguagePref(submitEnrollRequest.getStrlanguagePref());
		gmdPersonDetailsDTO.setNameFirst(submitEnrollRequest.getStrBPFirstName());
		gmdPersonDetailsDTO.setNameLast(submitEnrollRequest.getStrBPLastName());
		gmdPersonDetailsDTO.setPersonId(gmdOEDAOImpl.getNextValForSequence(DBConstants.GMD_PERSON_SEQ));
		gmdPersonDetailsDTO.setPhoneNumber(submitEnrollRequest.getStrBPHomeTelNum());
		gmdPersonDetailsDTO.setMaidenName(submitEnrollRequest.getStrBPMaidenName());
		gmdPersonDetailsDTO.setMiddleName(submitEnrollRequest.getStrBPMiddleInitial());
		gmdPersonDetailsDTO.setTokenizedCCNumber(enrollmentRequest.getCcNumber());
		gmdPersonDetailsDTO.setCcType(enrollmentRequest.getCcInstituteCode());
		if (StringUtils.isNotBlank(enrollmentRequest.getExpirationDate())
				&& StringUtils.contains(enrollmentRequest.getExpirationDate(), "/")) {
			String[] expArr = enrollmentRequest.getExpirationDate().split("/");
			gmdPersonDetailsDTO.setCcExpiryMonth(expArr[0]);
			gmdPersonDetailsDTO.setCcExpiryYear(expArr[1]);
		}
		return gmdPersonDetailsDTO;
	}
	
	
	private GMDServiceLocationDetailsDTO getGMDServiceLocationDetailsDTO(SubmitEnrollRequest submitEnrollRequest,
			SubmitEnrollResponse submitEnrollResponse, GMDEnrollmentRequest enrollmentRequest, Integer personId) {
		GMDServiceLocationDetailsDTO gmdServiceLocationDetailsDTO = new GMDServiceLocationDetailsDTO();
		if (StringUtils.isNotBlank(enrollmentRequest.getBillingAddressAptNumber())
				|| StringUtils.isNotBlank(enrollmentRequest.getBillingAddressStreetName())
				|| StringUtils.isNotBlank(enrollmentRequest.getBillingAddressState())
				|| StringUtils.isNotBlank(enrollmentRequest.getBillingAddressZipCode())) {

			gmdServiceLocationDetailsDTO.setBillAddressLine1(enrollmentRequest.getBillingAddressAptNumber() + " "
					+ enrollmentRequest.getBillingAddressStreetName());
			gmdServiceLocationDetailsDTO.setBillCity(enrollmentRequest.getBillingAddressCity());
			gmdServiceLocationDetailsDTO.setBillState(enrollmentRequest.getBillingAddressState());
			gmdServiceLocationDetailsDTO.setBillZipCode(enrollmentRequest.getBillingAddressZipCode());

		} else {
			gmdServiceLocationDetailsDTO.setAddressBillSameAsServiceFlag(Constants.YES);
		}

		gmdServiceLocationDetailsDTO.setServAddressLine1(
				enrollmentRequest.getServiceAddressAptNumber() + " " + enrollmentRequest.getServiceAddressStreetName());
		gmdServiceLocationDetailsDTO.setServCity(enrollmentRequest.getServiceAddressCity());
		gmdServiceLocationDetailsDTO.setServState(enrollmentRequest.getServiceAddressState());
		gmdServiceLocationDetailsDTO.setServZipCode(enrollmentRequest.getServiceAddressZipCode());

		gmdServiceLocationDetailsDTO.setBrandId(enrollmentRequest.getBrandName());
		gmdServiceLocationDetailsDTO.setCompanyCode(enrollmentRequest.getCompanyCode());
		gmdServiceLocationDetailsDTO.setBussinessPartnerNumber(submitEnrollResponse.getStrBPNumber());
		gmdServiceLocationDetailsDTO.setCaCheckDigit(submitEnrollResponse.getStrCheckDigit());
		gmdServiceLocationDetailsDTO.setCampaignCode(enrollmentRequest.getCampaignCode());
		gmdServiceLocationDetailsDTO.setContractAccountNumber(submitEnrollResponse.getStrCANumber());
		gmdServiceLocationDetailsDTO.setEnrollSource(submitEnrollRequest.getStrLogicalSystem());
		gmdServiceLocationDetailsDTO.setErrorCodesList(submitEnrollRequest.getStrEnrollmentHoldType());
		gmdServiceLocationDetailsDTO.setEsid(submitEnrollRequest.getStrPointOfDeliveryID());
		gmdServiceLocationDetailsDTO.setEsidStatus(EMPTY);
		gmdServiceLocationDetailsDTO.setGeoZone(EMPTY);
		gmdServiceLocationDetailsDTO.setPromoType(submitEnrollRequest.getStrPromotionCode());
		gmdServiceLocationDetailsDTO.setOfferCodeTitle(EMPTY);
		gmdServiceLocationDetailsDTO.setOfferCode(enrollmentRequest.getOfferCode());
		gmdServiceLocationDetailsDTO.setSignupChannelCode(EMPTY);
		gmdServiceLocationDetailsDTO.setPromoCodeEntered(submitEnrollRequest.getStrPromotionCode());
		gmdServiceLocationDetailsDTO.setServiceRequestTypeCode(enrollmentRequest.getTransactionType());
		gmdServiceLocationDetailsDTO.setSwitchHoldStatus(enrollmentRequest.getSwitchHoldFlag());
		gmdServiceLocationDetailsDTO.setServiceStartDate(submitEnrollRequest.getStrMovinDate());
		gmdServiceLocationDetailsDTO.setTdspCode(enrollmentRequest.getTdspCode());
		gmdServiceLocationDetailsDTO
				.setTrackingId(String.valueOf(gmdOEDAOImpl.getNextValForSequence(DBConstants.GMD_TRACKING_SEQ)));
		gmdServiceLocationDetailsDTO.setPersonId(String.valueOf(personId));

		return gmdServiceLocationDetailsDTO;

	}
}