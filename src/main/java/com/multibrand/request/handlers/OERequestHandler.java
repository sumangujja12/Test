package com.multibrand.request.handlers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.domain.AlertPrefDTO;
import com.multibrand.domain.BpMatchCCSRequest;
import com.multibrand.domain.FactorDetailDO;
import com.multibrand.domain.KbaQuestionRequest;
import com.multibrand.domain.KbaQuizAnswerDTO;
import com.multibrand.domain.KbaSubmitAnswerRequest;
import com.multibrand.domain.NewCreditScoreRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.SubmitEnrollRequest;
import com.multibrand.domain.UpdateAlertPrefRequest;
import com.multibrand.dto.AddressDTO;
import com.multibrand.dto.BPMatchDTO;
import com.multibrand.dto.CreditCheckDTO;
import com.multibrand.dto.ESIDDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.OfferDTO;
import com.multibrand.dto.PersonDTO;
import com.multibrand.dto.request.CheckPermitRequest;
import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.GetOEKBAQuestionsRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.SalesCreditCheckRequest;
import com.multibrand.dto.request.SalesCreditReCheckRequest;
import com.multibrand.dto.request.SalesEnrollmentRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.response.PersonResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.FormatConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidDateTime;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.request.ESIDDO;
import com.multibrand.vo.request.KBAQuestionAnswerVO;
import com.multibrand.vo.request.OESignupVO;
import com.multibrand.vo.request.TokenRequestVO;
import com.multibrand.vo.response.billingResponse.AddressDO;

@Component
public class OERequestHandler implements Constants {

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");

	@Autowired
	OEBO oeBo;

	/**
	 * 
	 * Creates permit check request for NRGWS
	 * 
	 * 
	 * @return
	 */
	public PermitCheckRequest createPermitCheckRequest(CheckPermitRequest permitCheckRequestDTO) {

		PermitCheckRequest request = new PermitCheckRequest();

		request.setStrApartmentNumber(permitCheckRequestDTO.getServiceAddress().getUnitNum());
		request.setStrStreetName(permitCheckRequestDTO.getServiceAddress().getStreetName());
		request.setStrStreetNum(permitCheckRequestDTO.getServiceAddress().getStreetNum());
		request.setStrPOBox(permitCheckRequestDTO.getServiceAddress().getPoBox());
		request.setStrCity(permitCheckRequestDTO.getServiceAddress().getCity());
		request.setStrState(permitCheckRequestDTO.getServiceAddress().getState());
		request.setStrZip(permitCheckRequestDTO.getServiceAddress().getZipcode());
		request.setStrCountry(permitCheckRequestDTO.getServiceAddress().getCountry());
		request.setStrPermitType(permitCheckRequestDTO.getPermitType());
		request.setStrCompanyCode(permitCheckRequestDTO.getCompanyCode());

		return request;
	}

	/**
	 * 
	 * @Jenith
	 * 
	 * 		Creates update service location request.
	 * 
	 * 
	 * @return
	 */
	public UpdateServiceLocationRequest createUpdateServiceLocationRequest(OESignupDTO oeSignupDTO) {

		UpdateServiceLocationRequest request = new UpdateServiceLocationRequest();

		request.setAffiliateId(oeSignupDTO.getAffiliateId());
		request.setBrandId(oeSignupDTO.getBrandId());
		request.setCompanyCode(oeSignupDTO.getCompanyCode());
		request.setTrackingId(oeSignupDTO.getTrackingNumber());
		request.setServiceRequestTypeCode(oeSignupDTO.getServiceReqTypeCd());
		if (BPSD.equalsIgnoreCase(oeSignupDTO.getErrorCode()) || NESID.equalsIgnoreCase(oeSignupDTO.getErrorCode())) {
			request.setRequestStatusCode(FLAG_N);
		} else {
			request.setRequestStatusCode(oeSignupDTO.getReqStatusCd());
		}
		request.setErrorCode(oeSignupDTO.getErrorCode());
		if (StringUtils.isNotEmpty(oeSignupDTO.getErrorCdList())) {
			request.setErrorCdList(oeSignupDTO.getErrorCdList());
		}
		request.setServiceZipOverrideFlag(oeSignupDTO.getServiceZipOverrideFlag());
		request.setFrequentFlyerNumber(oeSignupDTO.getFrequentFlyerNumber());

		request.setPersonId(oeSignupDTO.getPerson().getPersonID());

		// set esid number in the service location table
		if (oeSignupDTO.getEsid() != null) {
			logger.info("Esid Number::: " + oeSignupDTO.getEsid().getEsidNumber());
			request.setEsid(oeSignupDTO.getEsid().getEsidNumber());
		}

		request.setServStreetNum(oeSignupDTO.getServiceAddress().getStreetNum());
		request.setServStreetName(oeSignupDTO.getServiceAddress().getStreetName());
		request.setServStreetAptNum(oeSignupDTO.getServiceAddress().getUnitNum());
		request.setServCity(oeSignupDTO.getServiceAddress().getCity());
		request.setServState(oeSignupDTO.getServiceAddress().getState());
		request.setServZipCode(oeSignupDTO.getServiceAddress().getZipcode());

		request.setBillStreetNum(oeSignupDTO.getBillingAddress().getStreetNum());
		request.setBillStreetName(oeSignupDTO.getBillingAddress().getStreetName());
		request.setBillStreetAptNum(oeSignupDTO.getBillingAddress().getUnitNum());
		request.setBillCity(oeSignupDTO.getBillingAddress().getCity());
		request.setBillState(oeSignupDTO.getBillingAddress().getState());
		request.setBillZipCode(oeSignupDTO.getBillingAddress().getZipcode());
		request.setBillPoBox(oeSignupDTO.getBillingAddress().getPoBox());

		request.setMailStreetNum(oeSignupDTO.getBillingAddress().getStreetNum());
		request.setMailStreetName(oeSignupDTO.getBillingAddress().getStreetName());
		request.setMailStreetAptNum(oeSignupDTO.getBillingAddress().getUnitNum());
		request.setMailCity(oeSignupDTO.getBillingAddress().getCity());
		request.setMailState(oeSignupDTO.getBillingAddress().getState());
		request.setMailZipCode(oeSignupDTO.getBillingAddress().getZipcode());

		logger.info("Mailing Address Zip Code : " + oeSignupDTO.getBillingAddress().getZipcode());

		request.setOfferCode(oeSignupDTO.getSelectedOffer().getOfferCode());
		request.setOfferCodeTitle(oeSignupDTO.getSelectedOffer().getOfferCodeTitle());
		request.setOfferCellTrackCodeSelected(oeSignupDTO.getSelectedOffer().getOfferCellTrackCodeSelected());
		request.setOfferCategory(oeSignupDTO.getSelectedOffer().getOfferCategory());
		request.setPlanName(oeSignupDTO.getSelectedOffer().getPlanName());

		request.setRecentCallMade(oeSignupDTO.getRecentCallMade());

		// Setting fields to fix Defect #56:
		request.setContractAccountNum(oeSignupDTO.getContractAccountNum());
		request.setServiceStartDate(oeSignupDTO.getServiceStartDate());
		request.setCaCheckDigit(oeSignupDTO.getCheckDigit());
		// Setting fields to fix Defect #57:
		request.setPromoCodeEntered(oeSignupDTO.getSelectedOffer().getOfferCellTrackCodeSelected());

		// Fixed for Defect #67
		request.setReferralId(oeSignupDTO.getReferralID());

		// Fixed for Defect #61
		if (!StringUtils.equals(ZERO, oeSignupDTO.getCreditCheck().getDepositAmount())) {
			request.setPayCode(YES);
			request.setDepositCode(DEPOSIT_OWED);
			request.setDepositAmount(oeSignupDTO.getCreditCheck().getDepositAmount());

		} else {
			request.setPayCode(FLAG_NO);
			request.setDepositCode(DEPOSIT_NONE);
			request.setDepositAmount(ZERO);
		}
		request.setTlpReportApiStatus(oeSignupDTO.getTlpReportApiStatus());
		request.setPdfCaptureFlag(oeSignupDTO.getPdfCaptureFlag());
		request.setAgentUpResponse(oeSignupDTO.getAgentUpResponse());
		request.setTpv_status(oeSignupDTO.getTpvStatus());
		request.setCampaignCd(oeSignupDTO.getCampaignCd());
		request.setSystemNotes(oeSignupDTO.getSystemNotes());
		request.setCallExecuted(oeSignupDTO.getCallExecuted());
		return request;
	}

	/**
	 * 
	 * @Jenith
	 * 
	 * 		Creates update person request.
	 * 
	 * 
	 * @return
	 */
	public UpdatePersonRequest createUpdatePersonRequest(OESignupDTO oeSignupDTO) {

		UpdatePersonRequest request = new UpdatePersonRequest();

		request.setPersonId(oeSignupDTO.getPerson().getPersonID());
		request.setFirstName(oeSignupDTO.getPerson().getFirstName());
		request.setLastName(oeSignupDTO.getPerson().getLastName());
		request.setEmail(oeSignupDTO.getPerson().getEmailAddress());
		request.setLanguageCode(oeSignupDTO.getPerson().getLanguagePref());
		request.setPhoneNum(oeSignupDTO.getPerson().getPhoneNumber());

		// Setting fields to fix Defect #57:
		request.setIdocNumber(oeSignupDTO.getIdocNumber());
		request.setBusinessPartnerId(oeSignupDTO.getBusinessPartnerID());

		return request;
	}

	/**
	 * 
	 * 
	 * @param locale
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servCity
	 * @param servZipCode
	 * @param esidCount
	 * @param esid
	 * @return
	 */
	@Deprecated
	private OESignupVO createOeSignupVo(String locale, String servStreetNum, String servStreetName,
			String servStreetAptNum, String servCity, String servZipCode, Integer esidCount, String esid) {

		OESignupVO oeSignupVO = new OESignupVO();

		// Language code
		oeSignupVO.setLocale(locale);

		// Service Address
		AddressDO serviceAddressDO = new AddressDO();
		serviceAddressDO.setStrStreetName(servStreetName);
		serviceAddressDO.setStrStreetNum(servStreetNum);
		serviceAddressDO.setStrApartNum(servStreetAptNum);
		serviceAddressDO.setStrCity(servCity);
		serviceAddressDO.setStrZip(servZipCode);

		oeSignupVO.setServiceAddressDO(serviceAddressDO);

		// ESI ID
		ESIDDO esiIdDo = new ESIDDO();
		esiIdDo.setEsidCount(esidCount);
		esiIdDo.setEsidNumber(esid);

		oeSignupVO.setEsidDO(esiIdDo);

		return oeSignupVO;
	}

	public TokenRequestVO createTokenRequest(String actionCode, String numToBeTokenized) {

		TokenRequestVO request = new TokenRequestVO();

		request.setActionCode(actionCode);
		request.setNumToBeTokenized(numToBeTokenized);

		return request;
	}

	/**
	 * Method createSubmitEnrollRequest.
	 * 
	 * @param oeSignUpDTO
	 *            OESignupDTO
	 * @return SubmitEnrollRequest
	 */

	public SubmitEnrollRequest createSubmitEnrollRequest(OESignupDTO oeSignUpDTO) {

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "enrollmentService SubmitEnrollment creating request:: inside createSubmitEnrollRequest");
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

		// oeSignUpDTO = prepareAdditionalEnrollmentRequestInfo(request,
		// oeSignUpDTO); //VSOOD: COMMENTED FOR CLEANUP
		SubmitEnrollRequest submitEnrollRequest = new SubmitEnrollRequest();
		submitEnrollRequest.setStrBPType(BPTYPE);
		submitEnrollRequest.setStrBPGroup(BPGROUP);
		submitEnrollRequest.setStrBPFirstName(oeSignUpDTO.getPerson().getFirstName());
		submitEnrollRequest.setStrBPMiddleInitial(oeSignUpDTO.getPerson().getMiddleName());
		submitEnrollRequest.setStrBPMaidenName(oeSignUpDTO.getPerson().getMaidenName());
		submitEnrollRequest.setStrBPLastName(oeSignUpDTO.getPerson().getLastName());

		String personDobFormatted = CommonUtil.formatDateForNrgws(oeSignUpDTO.getPerson().getDateOfBirth());
		submitEnrollRequest.setStrBPDOB(personDobFormatted);

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "enrollmentService SubmitEnrollment creating request:: checking for FullName");
		}

		if ((oeSignUpDTO.getPerson().getMiddleName() != null)) {
			bpFullName = oeSignUpDTO.getPerson().getFirstName() + " " + oeSignUpDTO.getPerson().getMiddleName() + " "
					+ oeSignUpDTO.getPerson().getLastName();

		} else {
			bpFullName = oeSignUpDTO.getPerson().getFirstName() + " " + oeSignUpDTO.getPerson().getLastName();
		}

		logger.debug(oeSignUpDTO.printOETrackingID() + "enrollmentService:createSubmitEnrollment::passed PersonDTO");

		/**
		 * Setting Account name for Recurring Request Procedure should be
		 * necessarily no more than 34 in Length
		 * 
		 * @Jsingh1
		 */
		oeSignUpDTO.setAccountName(
				bpFullName.length() > 34 ? bpFullName.substring(0, 34).toUpperCase() : bpFullName.toUpperCase());

		submitEnrollRequest.setStrBPFullName(bpFullName);
		submitEnrollRequest.setStrNotifyName(
				bpFullName.length() > 34 ? bpFullName.substring(0, 34).toUpperCase() : bpFullName.toUpperCase());
		submitEnrollRequest.setStrCAName(
				bpFullName.length() > 34 ? bpFullName.substring(0, 34).toUpperCase() : bpFullName.toUpperCase());
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID() + "submitEnrollment request :: Name is " + bpFullName);
		}

		submitEnrollRequest.setStrCampaignCode(oeSignUpDTO.getSelectedOffer().getCampaignCode());
		// START test logs
		if (oeSignUpDTO.getBillingAddress() != null) {
			logger.info(oeSignUpDTO.printOETrackingID() + "billingAddressDTO is not null");
			if ((oeSignUpDTO.getBillingAddress().getStreetAddress()) == null) {
				logger.info(oeSignUpDTO.printOETrackingID() + "StreetAddress is null");
			}
		}
		// END test logs

		if ((oeSignUpDTO.getBillingAddress().getStreetAddress()) != null)

		{
			submitEnrollRequest.setStrBPStreet(oeSignUpDTO.getBillingAddress().getStreetName());
			submitEnrollRequest.setStrBPHouseNum(oeSignUpDTO.getBillingAddress().getStreetNum());

		} else {
			submitEnrollRequest.setStrBPPOBoxCountry(COUNTRY_US);
			submitEnrollRequest.setStrBPPOBoxRegion(oeSignUpDTO.getBillingAddress().getState());
			if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getZipcodeComplete()))
				submitEnrollRequest.setStrBPPOBoxPostalCode(oeSignUpDTO.getBillingAddress().getZipcodeComplete());
			else
				submitEnrollRequest.setStrBPPOBoxPostalCode(oeSignUpDTO.getBillingAddress().getZipcode());
			submitEnrollRequest.setStrBPPOBox(oeSignUpDTO.getBillingAddress().getPoBox());
		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "creating SubmitEnrollmentRequest in enrollmentService:: passed the null check for Billing Address");
		}
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getStreetAddress())) {

			if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getUnitNum())) {
				notifyAddress = oeSignUpDTO.getBillingAddress().getStreetAddress() + ", APT "
						+ oeSignUpDTO.getBillingAddress().getUnitNum();
			} else {
				notifyAddress = oeSignUpDTO.getBillingAddress().getStreetAddress();
			}
		} else if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getPoBox())) {

			notifyAddress = "P.O.Box " + oeSignUpDTO.getBillingAddress().getPoBox();

		} else {
			notifyAddress = "";
		}

		submitEnrollRequest.setStrNotifyAddress(notifyAddress);

		submitEnrollRequest.setStrNotifyCity(oeSignUpDTO.getBillingAddress().getCity());
		submitEnrollRequest.setStrNotifyState(oeSignUpDTO.getBillingAddress().getState());
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getZipcodeComplete()))
			submitEnrollRequest.setStrNotifyZip(oeSignUpDTO.getBillingAddress().getZipcodeComplete());
		else
			submitEnrollRequest.setStrNotifyZip(oeSignUpDTO.getBillingAddress().getZipcode());
		startSvrcDate = CommonUtil.formatDateForNrgws(oeSignUpDTO.getServiceStartDate());
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "enrollmentService: createSubmitEnrollmentRequest :: billing address zipcode is:"
					+ oeSignUpDTO.getBillingAddress().getZipcodeComplete());
		}

		if ("".equals(startSvrcDate)) {
			specialReadDate = "";
			moveInDate = "";
		}

		else {
			specialReadDate = startSvrcDate;
			moveInDate = startSvrcDate;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,specialReadDate is ::" + specialReadDate);
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,moveInDate is ::" + moveInDate);
		}
		// Start: Selected Date Switch Contact Log update for OE - Dipika
		// Pethaperumal
		if (StringUtils.isNotBlank(oeSignUpDTO.getServiceReqTypeCd())
				&& SWI.equalsIgnoreCase(oeSignUpDTO.getServiceReqTypeCd())) {

			enrollmentType = SWITCH;

			if (StringUtils.isNotBlank(startSvrcDate)) {
				contactText = "Selected Date Switch." + startSvrcDate + ".Web.";
			} else {
				contactText = "Standard Switch. Web.";
			}
			// End: Selected Date Switch Contact Log update for OE - Dipika
			// Pethaperumal
		} else if (StringUtils.isNotBlank(oeSignUpDTO.getServiceReqTypeCd())
				&& MVI.equalsIgnoreCase(oeSignUpDTO.getServiceReqTypeCd())) {
			enrollmentType = MOVEIN;
			contactText = "Move in " + startSvrcDate + ".Web.";
		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,contactText is ::" + contactText);
		}

		submitEnrollRequest.setStrEnrollmentType(enrollmentType);

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,enrollmentType is ::" + enrollmentType);
		}

		if (oeSignUpDTO.getPerson().getLanguagePref() != null) {
			if ((oeSignUpDTO.getPerson().getLanguagePref()).equalsIgnoreCase(ES)) {
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
		submitEnrollRequest.setStrBPEmail(oeSignUpDTO.getPerson().getEmailAddress());
		// Fixed for Defect #91
		submitEnrollRequest.setStrBPHomeTelNum(oeSignUpDTO.getPerson().getPhoneNumber());
		submitEnrollRequest.setStrSSN(oeSignUpDTO.getPerson().getTokenizedSSN());

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,CreditCheckDTO is ::"
					+ oeSignUpDTO.getCreditCheck());
		}

		submitEnrollRequest.setStrCreditBucket(oeSignUpDTO.getCreditCheck().getCreditBucket());
		submitEnrollRequest.setStrCreditBureauSource(oeSignUpDTO.getCreditCheck().getCreditScoreNum());
		submitEnrollRequest.setStrDLNum(oeSignUpDTO.getPerson().getTokenizedDL());
		//
		// Fix for INC0078321: Web enrollments are tying themselves to 1
		// installation when NO ESID IS FOUND
		// if (StringUtils.isNotBlank(oeSignUpDTO.getEsidNumber())) {
		//
		// Fixes for QC Project Online Affiliates: Defect ID 22: ESID not being
		// submitted on submit enrollment call
		if (null != oeSignUpDTO.getEsid() && StringUtils.isNotBlank(oeSignUpDTO.getEsid().getEsidNumber())) {

			pointOfDeliveryId = oeSignUpDTO.getEsid().getEsidNumber();
		} else {
			pointOfDeliveryId = ESIDNOTFOUND;
		}
		/*
		 * Following are standard dateFormat and TimeFormat defined
		 * 
		 * @JSINGH1@lntinfotech
		 */

		// START Default to system date and time if Offer Date/Offer Time are
		// blank. Added by Jenith on 04/16/2015
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

		// END Default to system date and time if Offer Date/Offer Time are
		// blank. Added by Jenith on 04/16/2015

		submitEnrollRequest.setStrPointOfDeliveryID(pointOfDeliveryId);
		submitEnrollRequest.setStrEnrollmentDate(offerDateFormatted);
		submitEnrollRequest.setStrEnrollmentTime(offerTimeFormatted);
		submitEnrollRequest.setStrCACreadtionDate(offerDateFormatted);
		submitEnrollRequest.setStrOrgCreditRatingDate(offerDateFormatted);
		submitEnrollRequest.setStrCCSOrgCreditAgency(oeSignUpDTO.getCreditCheck().getCreditSourceNum());
		submitEnrollRequest.setStrBPOrgCreditScore(oeSignUpDTO.getCreditCheck().getCreditScoreNum());

		/*
		 * if (logger.isDebugEnabled()) {
		 * logger.debug(oeSignUpDTO.printOETrackingID()
		 * +"EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getBillingAddOverrideFlag() is ::"
		 * + oeSignUpDTO.getBillingAddOverrideFlag());}
		 * 
		 * if (null!=oeSignUpDTO.getBillingAddOverrideFlag() &&
		 * oeSignUpDTO.getBillingAddOverrideFlag().equalsIgnoreCase(N_VALUE)) {
		 * submitEnrollRequest.setStrBPFileTestStatus(D_VALUE); } else {
		 * submitEnrollRequest.setStrBPFileTestStatus(FLAG_C); }
		 */

		// Hardcoded as per Vishal Email dated on 04/09/2015
		submitEnrollRequest.setStrBPFileTestStatus(FLAG_C);

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getBillingAddress() is ::"
					+ oeSignUpDTO.getBillingAddress());
		}
		submitEnrollRequest.setStrBPRegion(oeSignUpDTO.getBillingAddress().getState());
		submitEnrollRequest.setStrBPAptNum(oeSignUpDTO.getBillingAddress().getUnitNum());
		if (StringUtils.isNotBlank(oeSignUpDTO.getBillingAddress().getZipcodeComplete()))
			submitEnrollRequest.setStrBPCityPostalCode(oeSignUpDTO.getBillingAddress().getZipcodeComplete());
		else
			submitEnrollRequest.setStrBPCityPostalCode(oeSignUpDTO.getBillingAddress().getZipcode());
		submitEnrollRequest.setStrBPCity(oeSignUpDTO.getBillingAddress().getCity());

		submitEnrollRequest.setStrPromotionCode(oeSignUpDTO.getSelectedOffer().getOfferCellTrackCodeSelected());

		agreementNumber = oeSignUpDTO.getAgreementNumber();
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,agreementNumber() is ::" + agreementNumber);
		}
		submitEnrollRequest.setStrAgreementNumber(agreementNumber);

		/*
		 * if (null!=oeSignUpDTO.getServiceAddressOverrideFlag() &&
		 * oeSignUpDTO.getServiceAddressOverrideFlag().equalsIgnoreCase(FLAG_NO)
		 * ) { submitEnrollRequest.setStrSvrcFileTestStatus(D_VALUE); } else {
		 * submitEnrollRequest.setStrSvrcFileTestStatus(FLAG_C); }
		 */

		submitEnrollRequest.setStrSvrcFileTestStatus(FLAG_C);

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getPerson()() is ::"
					+ oeSignUpDTO.getPerson());
		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getServiceAddress()() is ::"
					+ oeSignUpDTO.getServiceAddress());
		}

		submitEnrollRequest.setStrSvrcStreet(oeSignUpDTO.getServiceAddress().getStreetName());
		submitEnrollRequest.setStrSvrcAptNum(oeSignUpDTO.getServiceAddress().getUnitNum());
		submitEnrollRequest.setStrSvrcHouseNum(oeSignUpDTO.getServiceAddress().getStreetNum());
		submitEnrollRequest.setStrSvrcCity(oeSignUpDTO.getServiceAddress().getCity());
		if (StringUtils.isNotBlank(oeSignUpDTO.getServiceAddress().getZipcodeComplete()))
			submitEnrollRequest.setStrSvrcZip(oeSignUpDTO.getServiceAddress().getZipcodeComplete());
		else
			submitEnrollRequest.setStrSvrcZip(oeSignUpDTO.getServiceAddress().getZipcode());
		if (oeSignUpDTO.getPerson().getPosidDLDate() != null) {
			String posidDLDateFormatted = CommonUtil.formatDateForNrgws(oeSignUpDTO.getPerson().getPosidDLDate());
			submitEnrollRequest.setStrBPPOSidDLDate(posidDLDateFormatted);
		} else {
			submitEnrollRequest.setStrBPPOSidDLDate(EMPTY);
		}
		logger.info("PosidDLDate:" + oeSignUpDTO.getPerson().getPosidDLDate());
		if (oeSignUpDTO.getPerson().getPosidSSNDate() != null) {
			String posidSSNDateFormatted = CommonUtil.formatDateForNrgws(oeSignUpDTO.getPerson().getPosidSSNDate());
			submitEnrollRequest.setStrBPPosPOSidSSNDate(posidSSNDateFormatted);
		} else {
			submitEnrollRequest.setStrBPPosPOSidSSNDate(EMPTY);
		}
		logger.info("PosidSSNDate:" + oeSignUpDTO.getPerson().getPosidSSNDate());
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getSelectedOffer().getStrOfferCode() is ::"
					+ oeSignUpDTO.getSelectedOffer().getOfferCode());
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getSelectedOffer().getStrProductPriceCode() is ::"
					+ oeSignUpDTO.getSelectedOffer().getProductPriceCode() + ". However passing as blank");
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getSelectedOffer().getStrIncentiveCode() is ::"
					+ oeSignUpDTO.getSelectedOffer().getIncentiveCode());
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,oeSignUpDTO.getSelectedOffer().getStrMarketSegment() is ::"
					+ oeSignUpDTO.getSelectedOffer().getMarketSegment());
		}
		submitEnrollRequest.setStrOfferSequenceNumber(
				StringUtils.leftPad((oeSignUpDTO.getSelectedOffer().getOfferCode()), 8, "0"));

		if (oeSignUpDTO.getSelectedOffer() != null) {
			// Passing blank value for StrProductPriceCode since the one
			// returned from CCS was more than 7 characters long
			// and field restriction in CCS is 7 characters.
			// submitEnrollRequest.setStrProductPriceCode(oeSignUpDTO.getSelectedOffer().getStrProductPriceCode());
			submitEnrollRequest.setStrProductPriceCode(EMPTY);
			submitEnrollRequest.setStrIncentiveCode(oeSignUpDTO.getSelectedOffer().getIncentiveCode());
			submitEnrollRequest.setStrmarketSegment(oeSignUpDTO.getSelectedOffer().getMarketSegment());
		} else {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "inside EnrollmentService ::createSubmitEnrollmentrequest ::seleceted OfferDTO is null");
		}

		String DepositAmt = oeSignUpDTO.getCreditCheck().getDepositAmount();
		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,DepositAmt is ::" + DepositAmt);
		}
		// txtPayAmt replaced with DepositAmt
		if ((null == oeSignUpDTO.getSelectedOffer().getOfferCategory()
				|| !OFFER_CATEGORY_PREPAY.equals(oeSignUpDTO.getSelectedOffer().getOfferCategory()))
				&& StringUtils.isNotBlank(DepositAmt)) {

			requestedAmount = DepositAmt;
			contactText += "$" + DepositAmt + " Deposit." + "Agr#" + agreementNumber;

			if (logger.isDebugEnabled()) {
				logger.debug(oeSignUpDTO.printOETrackingID()
						+ "EnrollmentService creating submitEnrollmentRequest,getDepositHold is ::"
						+ oeSignUpDTO.getCreditCheck().getDepositHold());
			}

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

		contactText = contactText + CommonUtil.printAffiliateId(oeSignUpDTO);

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,contactText is ::" + contactText);
		}

		submitEnrollRequest.setStrContactText(contactText);

		/*
		 * value of all Constants in submitEnrollRequest START from here
		 * 
		 * @JSingh1@lntinfotech.com
		 */

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

		submitEnrollRequest.setStrCompanyCode(oeSignUpDTO.getCompanyCode()); // Fixed
																				// prod
																				// issue
																				// of
																				// hardcoded
																				// company
																				// code

		submitEnrollRequest.setStrPaymentTerms(EMPTY);
		submitEnrollRequest.setStrValidToDate(EMPTY);
		submitEnrollRequest.setStrValidFromDate(EMPTY);
		submitEnrollRequest.setStrDLRegion(TX);
		submitEnrollRequest.setStrDLCountry(COUNTRY_US);
		submitEnrollRequest.setStrSvrcRegion(TX);
		submitEnrollRequest.setStrCACreationUser(CHANNEL);
		submitEnrollRequest.setStrPaperlessFlag(FLAG_N); // For Reliant we never
															// set to paperless

		if (null != oeSignUpDTO.getSelectedOffer().getOfferCategory()
				&& OFFER_CATEGORY_PREPAY.equals(oeSignUpDTO.getSelectedOffer().getOfferCategory())) {
			if (EMPTY.equals(enrollmentHoldType))
				enrollmentHoldType = PPYHOLD;
			else
				enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + PPYHOLD;
		}

		/*
		 * CONVERT ALL String to BIGDECIMAL for all values using BIGDECIMAL in
		 * webservice end and passing STRING in frontend
		 * 
		 * @Jsingh1@lntinfotech
		 */
		if (null != oeSignUpDTO.getCreditCheck() && null != oeSignUpDTO.getCreditCheck().getSecurityMethod()
				&& oeSignUpDTO.getCreditCheck().getSecurityMethod().equalsIgnoreCase(SURETY_BOND)) {
			reasonSEcurityDeposit = Z1; // z1 for account activation fee

			BigDecimal activation_fee = new BigDecimal(oeSignUpDTO.getCreditCheck().getActivationFee());
			submitEnrollRequest.setCustFee(activation_fee);

			BigDecimal bond_Price = new BigDecimal(oeSignUpDTO.getCreditCheck().getBondPrice());
			submitEnrollRequest.setBondPrice(bond_Price);

			submitEnrollRequest.setAcctSecStatus(oeSignUpDTO.getCreditCheck().getAccSecStatus());
			depositCode = N_VALUE;
			if ((oeSignUpDTO.getCreditCheck().getIsPayUpfront()).equalsIgnoreCase(FLAG_X)) {
				if (EMPTY.equals(enrollmentHoldType))
					enrollmentHoldType = ACCSECHOLD;
				else
					enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + ACCSECHOLD;
			}
		}

		if (MOVEIN.equals(enrollmentType) && ON.equals(oeSignUpDTO.getSwitchHoldStatus())) {
			if (EMPTY.equals(enrollmentHoldType))
				enrollmentHoldType = SWITCHHOLD;
			else
				enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + SWITCHHOLD;
		}


		if (StringUtils.isNotBlank(oeSignUpDTO.getErrorCdList())) {
			String[] errorCdArray = oeSignUpDTO.getErrorCdList().split("\\|");
			for (String holdType : errorCdArray) {
				if (StringUtils.equalsIgnoreCase(holdType, POSIDHOLD)) {
					if (EMPTY.equals(enrollmentHoldType))
						enrollmentHoldType = POSID;
					else
						enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + POSID;
				}

				if (StringUtils.equalsIgnoreCase(holdType, HOLD_DNP)) {
					if (EMPTY.equals(enrollmentHoldType))
						enrollmentHoldType = HOLD_DNP;

					else

						enrollmentHoldType = enrollmentHoldType + SYMBOL_COMMA + HOLD_DNP;
				}

			}
    }

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "EnrollmentService creating submitEnrollmentRequest,enrollmentHoldType is ::"
					+ enrollmentHoldType);
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

		/*
		 * Applying toString() method from java.lang.Boolean to convert boolean
		 * to String
		 * 
		 * @Jsingh1@lntinfotech
		 */
		if (oeSignUpDTO.isPriorityMovein())
			submitEnrollRequest.setStrPriorityMovinFlag("Y");
		else
			submitEnrollRequest.setStrPriorityMovinFlag("N");

		/*
		 * if BPMATCH call returns existing BpNUmber for the prospect
		 * matchedPartnerId will be populated and later used for enrollment on
		 * same bpNumber and will be later passed on as new BPNumber
		 * 
		 * @Jsingh1@lntinfotech
		 */
		if (oeSignUpDTO.getBpMatch() != null) {
			if ((oeSignUpDTO.getBpMatch().getMatchedPartnerID()) != null) {
				submitEnrollRequest.setStrBPNumber(oeSignUpDTO.getBpMatch().getMatchedPartnerID());
			}
		}
		/*
		 * frequent flyer will be checked here
		 */

		if (oeSignUpDTO.getFrequentFlyerFirstName() == null) {
			String firstName = oeSignUpDTO.getPerson().getFirstName();
			oeSignUpDTO.setFrequentFlyerFirstName(firstName);
		}

		if (oeSignUpDTO.getFrequentFlyerLastName() == null) {
			String lastName = oeSignUpDTO.getPerson().getLastName();
			oeSignUpDTO.setFrequentFlyerLastName(lastName);
		}

		/*
		 * Checking for conditions for OffercategoryLookup for setting values
		 * for frequentflyer names and freqFlyerNo
		 * 
		 * @Jsingh1
		 */
		if (null != oeSignUpDTO.getSelectedOffer().getOfferCategory()
				&& OFFER_CATEGORY_PREPAY.equals(oeSignUpDTO.getSelectedOffer().getOfferCategory())) {
			submitEnrollRequest.setStrFreqFlyerFirstName(EMPTY);
			submitEnrollRequest.setStrFreqFlyerLastName(EMPTY);
			submitEnrollRequest.setStrFreqFlyerNo(EMPTY);

		} else {
			String offerCategory = oeSignUpDTO.getSelectedOffer().getOfferCategory();

			if (StringUtils.isNotBlank(oeSignUpDTO.getSelectedOffer().getOfferCategory())) {

				if ((offerCategory.equalsIgnoreCase(OFFER_CATEGORY_UNITED))
						|| offerCategory.equalsIgnoreCase(OFFER_CATEGORY_AA)
						|| offerCategory.equalsIgnoreCase(OFFER_CATEGORY_SOUTHWEST)) {
					submitEnrollRequest.setStrFreqFlyerFirstName(oeSignUpDTO.getFrequentFlyerFirstName());
					submitEnrollRequest.setStrFreqFlyerLastName(oeSignUpDTO.getFrequentFlyerLastName());

					if (oeSignUpDTO.getFrequentFlyerNumber() != null) {
						submitEnrollRequest.setStrFreqFlyerNo(oeSignUpDTO.getFrequentFlyerNumber());
					}
					if (oeSignUpDTO.getFrequentFlyerNumber() == null) {
						submitEnrollRequest.setStrFreqFlyerNo(EMPTY);
					}
				} else if (offerCategory.equalsIgnoreCase("MC")) {
					submitEnrollRequest.setStrFreqFlyerFirstName(EMPTY);
					submitEnrollRequest.setStrFreqFlyerLastName(EMPTY);
					submitEnrollRequest.setStrFreqFlyerNo(oeSignUpDTO.getRealtorID());

				} else if (offerCategory.equalsIgnoreCase("CEP")) {
					submitEnrollRequest.setStrFreqFlyerFirstName(EMPTY);
					submitEnrollRequest.setStrFreqFlyerLastName(EMPTY);
					submitEnrollRequest.setStrFreqFlyerNo(oeSignUpDTO.getCid());
				}
			} else {
				submitEnrollRequest.setStrFreqFlyerFirstName(EMPTY);
				submitEnrollRequest.setStrFreqFlyerLastName(EMPTY);
				submitEnrollRequest.setStrFreqFlyerNo(EMPTY);
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID()
					+ "Inside EnrollmentService :: createSubmitEnrollRequest : getFactorsKey :: "
					+ oeSignUpDTO.getCreditCheck().getFactorsKey());
		}

		try {
			FactorDetailDO[] arrayFactors = {};
			List<String> factorsKeyList = oeSignUpDTO.getCreditCheck().getFactorsKey();
			if (null != factorsKeyList && factorsKeyList.size() > 0) {
				int factCnt = 0;
				String[] factStrArray = null;
				/*
				 * String[] FactorKey = (oeSignUpDTO.getCreditCheck()
				 * .getFactorsKey()).split(",");
				 */
				logger.debug(oeSignUpDTO.printOETrackingID()
						+ "createSubmitEnrollRequest: KEY FACTORS: factorsKeyList.size():: " + factorsKeyList.size());
				arrayFactors = new FactorDetailDO[factorsKeyList.size()];
				logger.debug(oeSignUpDTO.printOETrackingID() + "createSubmitEnrollRequest: KEY FACTORS: arrayFactors:: "
						+ arrayFactors);
				for (String factStr : factorsKeyList) {
					logger.debug(oeSignUpDTO.printOETrackingID() + "createSubmitEnrollRequest: KEY FACTORS: factStr:: "
							+ factStr);
					factStrArray = factStr.split("\\" + DOT);
					if (factStrArray.length >= 3) {
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "createSubmitEnrollRequest: KEY FACTORS: factStrArray:: " + factStrArray);
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "createSubmitEnrollRequest: KEY FACTORS: factStrArray.length:: "
								+ factStrArray.length);
						logger.debug(oeSignUpDTO.printOETrackingID()
								+ "Inside EnrollmentService :: createSubmitEnrollRequest : factStrArray :: "
								+ factStrArray[1] + " **** " + factStrArray[0] + "****" + factStrArray[2]);
						arrayFactors[factCnt] = new FactorDetailDO(factStrArray[2], "", "", "", factStrArray[0],
								factStrArray[1]);
						factCnt++;
					}
				}
				submitEnrollRequest.setArrayFactors(arrayFactors);
			}
		} catch (Exception ex) {
			logger.error(oeSignUpDTO.printOETrackingID()
					+ "Error in createSubmitEnrollRequest - getting Key Factors: Skipping and Continuing", ex);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignUpDTO.printOETrackingID() + "submitEnrollmentRequest: value of WebTSDP is ::"
					+ oeSignUpDTO.getTdspCode());
		}
		submitEnrollRequest.setStrWebTsdp(oeSignUpDTO.getTdspCode());
		// START : OE :Sprint62 :US21019 :Kdeshmu1
		submitEnrollRequest.setStrAgentId(oeSignUpDTO.getAgentID());
		// END : OE :Sprint62 :US21019 :Kdeshmu1

		// if (logger.isDebugEnabled()) {
		logger.info(oeSignUpDTO.printOETrackingID() + "submitEnrollmentRequest: returning submitEnrollRequest ::"
				+ submitEnrollRequest.toString());// }
		return submitEnrollRequest;
	}

	/**
	 * Method createUpdateAlertPrefRequest.
	 * 
	 * @param oeSignupDTO
	 *            OESignupDTO
	 * @return UpdateAlertPrefRequest
	 */
	public UpdateAlertPrefRequest createUpdateAlertPrefRequest(OESignupDTO oeSignupDTO) {

		logger.info(
				oeSignupDTO.printOETrackingID() + "EnrollmentService:: inside function createUpdateAlertPrefRequest");

		String lowAcctBalEmailVal = EMPTY;
		String payReceivedEmailVal = EMPTY;
		String weeklyBalEmailVal = EMPTY;
		String lowAcctBalSmsVal = EMPTY;
		String payReceivedSmsVal = EMPTY;
		String weeklyBalSmsVal = EMPTY;
		String lowAcctBalPhoneVal = EMPTY;
		String payReceivedPhoneVal = EMPTY;
		String weeklyBalPhoneVal = EMPTY;

		// loop to avoid Null Pointer
		if (null != oeSignupDTO.getPerson().getContactPrefDTO()) {
			logger.debug(oeSignupDTO.printOETrackingID()
					+ "Inside EnrollmentService :: inside CreateUpdateAlertPref Request :: ContactAlertPrefDTO is not NUll");

			lowAcctBalEmailVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getLowActBalEmail()) ? "E" : "";
			payReceivedEmailVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getPayReceivedEmail()) ? "E" : "";
			weeklyBalEmailVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getWeeklyBalEmail()) ? "E" : "";

			lowAcctBalSmsVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getLowActBalSMS()) ? "S" : "";
			payReceivedSmsVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getPayReceivedSMS()) ? "S" : "";
			weeklyBalSmsVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getWeeklyBalSMS()) ? "S" : "";

			lowAcctBalPhoneVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getLowActBalPhone()) ? "O" : "";
			payReceivedPhoneVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getPayReceivedPhone()) ? "O" : "";
			weeklyBalPhoneVal = FLAG_YES_PROFILE
					.equalsIgnoreCase(oeSignupDTO.getPerson().getContactPrefDTO().getWeeklyBalPhone()) ? "O" : "";

		}

		String strLowAcctBal = lowAcctBalEmailVal + lowAcctBalSmsVal + lowAcctBalPhoneVal;
		String strPayReceived = payReceivedEmailVal + payReceivedSmsVal + payReceivedPhoneVal;
		String strWeeklySum = weeklyBalEmailVal + weeklyBalSmsVal + weeklyBalPhoneVal;

		if (logger.isDebugEnabled()) {
			logger.debug(oeSignupDTO.printOETrackingID() + "for Low account balance we have" + strLowAcctBal);
			logger.debug(oeSignupDTO.printOETrackingID() + "for Pat Received options we have" + strLowAcctBal);
			logger.debug(oeSignupDTO.printOETrackingID() + "for Weekly Summary Options we have" + strLowAcctBal);
		}
		UpdateAlertPrefRequest updateAlertPrefRequest = new UpdateAlertPrefRequest();
		AlertPrefDTO[] unSubscribeRequests = null;
		updateAlertPrefRequest.setStrBPNunber(oeSignupDTO.getBusinessPartnerID());

		updateAlertPrefRequest.setStrCANumber(oeSignupDTO.getContractAccountNum());

		// TODO
		// updateAlertPrefRequest.setStrCompanyCode(appConstMessageSource.getMessage(Constants.PROP_COMPANY_CODE,
		// null, null));

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
		AlertPrefDTO lowAccBalDTO = null;
		AlertPrefDTO payReceivedDTO = null;
		AlertPrefDTO weeklySumDTO = null;
		List<AlertPrefDTO> subscribeRequestList = new ArrayList<AlertPrefDTO>();
		if (!StringUtils.isEmpty(strLowAcctBal)) {
			lowAccBalDTO = new AlertPrefDTO();
			lowAccBalDTO.setStrEventId(BAL_ALERT);
			lowAccBalDTO.setStrParamName(COMM_PREF);
			lowAccBalDTO.setStrParamValue(strLowAcctBal);
			subscribeRequestList.add(lowAccBalDTO);
		}

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
			if (logger.isDebugEnabled()) {
				logger.debug(oeSignupDTO.printOETrackingID() + "Value of the parameter in AlertPrefDTO: "
						+ alert[i].getStrParamValue());
			}

		}
		updateAlertPrefRequest.setSubscribeRequests(alert);

		return updateAlertPrefRequest;

	}

	public NewCreditScoreRequest createNewCreditScoreRequest(CreditCheckRequest creditCheckRequest) {

		NewCreditScoreRequest creditScoreRequest = new NewCreditScoreRequest();

		creditScoreRequest.setTrackingNum(creditCheckRequest.getTrackingId());
		if (StringUtils.isNotEmpty(creditCheckRequest.getBillPOBox())) {
			// Overwrite Billing Addr with Serv Addr if billPOBox is provided
			creditScoreRequest.setStrBillAptNum(creditCheckRequest.getServStreetAptNum());
			creditScoreRequest.setStrBillCity(creditCheckRequest.getServCity());
			creditScoreRequest.setStrBillCountry(COUNTRY_US);
			creditScoreRequest.setStrBillPOBox(EMPTY);
			creditScoreRequest.setStrBillPostCode(creditCheckRequest.getServZipCode());
			creditScoreRequest.setStrBillRegion(creditCheckRequest.getServState());
			creditScoreRequest.setStrBillStreetName(creditCheckRequest.getServStreetName());
			creditScoreRequest.setStrBillStreetNum(creditCheckRequest.getServStreetNum());
		} else {
			creditScoreRequest.setStrBillAptNum(creditCheckRequest.getBillStreetAptNum());
			creditScoreRequest.setStrBillCity(creditCheckRequest.getBillCity());
			creditScoreRequest.setStrBillCountry(COUNTRY_US);
			creditScoreRequest.setStrBillPOBox(creditCheckRequest.getBillPOBox());
			creditScoreRequest.setStrBillPostCode(creditCheckRequest.getBillZipCode());
			creditScoreRequest.setStrBillRegion(creditCheckRequest.getBillState());
			creditScoreRequest.setStrBillStreetName(creditCheckRequest.getBillStreetName());
			creditScoreRequest.setStrBillStreetNum(creditCheckRequest.getBillStreetNum());
		}
		creditScoreRequest.setStrBusinessPartner(creditCheckRequest.getMatchedBP());
		creditScoreRequest.setStrCompanyCode(creditCheckRequest.getCompanyCode());
		if (StringUtils.isNotBlank(creditCheckRequest.getTransactionType())
				&& creditCheckRequest.getTransactionType().equalsIgnoreCase(SWI))
			creditScoreRequest.setStrEnrollType(SWITCH);
		else if (StringUtils.isNotBlank(creditCheckRequest.getTransactionType())
				&& creditCheckRequest.getTransactionType().equalsIgnoreCase(MVI))
			creditScoreRequest.setStrEnrollType(MOVEIN);
		creditScoreRequest.setStrDefaultScore("0400");
		creditScoreRequest.setStrDefaultSource("TU");
		creditScoreRequest.setStrGMEChannel(CHANNEL);
		creditScoreRequest.setStrGMEAcpCheck("X");
		creditScoreRequest.setStrEssId(creditCheckRequest.getEsid());
		creditScoreRequest.setStrFirstName(creditCheckRequest.getFirstName());
		creditScoreRequest.setStrLastName(creditCheckRequest.getLastName());
		if (StringUtils.isNotBlank(creditCheckRequest.getMviDate()))
			creditScoreRequest.setStrMoveinDate(CommonUtil.formatDateForNrgws(creditCheckRequest.getMviDate()));
		else
			creditScoreRequest.setStrMoveinDate(CommonUtil.formatDateForNrgws(HISTORICAL_DATE));
		creditScoreRequest.setStrOfferCode(creditCheckRequest.getOfferCode());
		creditScoreRequest.setStrSSN(creditCheckRequest.getTokenizedSSN());
		creditScoreRequest.setStrSVRcAptNum(creditCheckRequest.getServStreetAptNum());
		creditScoreRequest.setStrSVRcCity(creditCheckRequest.getServCity());
		creditScoreRequest.setStrSVRcCountry(COUNTRY_US);
		creditScoreRequest.setStrSVRcPostCode(creditCheckRequest.getServZipCode());
		creditScoreRequest.setStrSVRcRegion(creditCheckRequest.getServState());
		creditScoreRequest.setStrSVRcStreetName(creditCheckRequest.getServStreetName());
		creditScoreRequest.setStrSVRcStreetNum(creditCheckRequest.getServStreetNum());
		return creditScoreRequest;
	}

	public BpMatchCCSRequest createBpmatchRequest(String firstName, String lastName, String tdl, String maidenName,
			String companyCode, String servStreetAptNum, String servCity, String servState, String servStreetName,
			String servStreetNum, String servZipCode, String ssn) {
		BpMatchCCSRequest bpMatchReq = new BpMatchCCSRequest();
		bpMatchReq.setStrFirstName(firstName);
		bpMatchReq.setStrLastName(lastName);
		bpMatchReq.setStrLicenceNo(tdl);
		bpMatchReq.setStrMaidenName(maidenName);
		bpMatchReq.setStrCompanyCode(companyCode);
		bpMatchReq.setStrLicenceRegion(TX);
		bpMatchReq.setStrSSN(ssn);

		bpMatchReq.setStrServiceAptNumber(servStreetAptNum);
		bpMatchReq.setStrServiceState(servState);
		bpMatchReq.setStrServiceCity(servCity);
		bpMatchReq.setStrServiceStreetName(servStreetName);
		bpMatchReq.setStrServiceStreetNumber(servStreetNum);
		bpMatchReq.setStrServiceZip(servZipCode);
		return bpMatchReq;
	}

	/**
	 * 
	 * Populate OESignupDTO from the API minimal/trimmed input request object.
	 * 
	 * 
	 * @param enrollmentRequest
	 * @param serviceLoationResponse 
	 * @return
	 */
	public OESignupDTO createOeSignupDtoByMinimal(EnrollmentRequest enrollmentRequest, OESignupDTO oeSignupDTO, ServiceLocationResponse serviceLoationResponse) {

		//OESignupDTO oeSignupDTO = new OESignupDTO();

		// First level data
		oeSignupDTO.setAffiliateId(enrollmentRequest.getAffiliateId());
		oeSignupDTO.setCompanyCode(enrollmentRequest.getCompanyCode());
		oeSignupDTO.setBrandId(enrollmentRequest.getBrandId());
		oeSignupDTO.setTrackingNumber(enrollmentRequest.getTrackingId());
		oeSignupDTO.setEbillFlag(enrollmentRequest.getEbillFlag());
		oeSignupDTO.setBpMatchText(enrollmentRequest.getBpMatchFlag());
		oeSignupDTO.setServiceStartDate(enrollmentRequest.getMviDate());
		oeSignupDTO.setServiceReqTypeCd(enrollmentRequest.getTransactionType());
		oeSignupDTO.setTdspCodeCCS(enrollmentRequest.getTdspCodeCCS());
		oeSignupDTO.setOfferDate(enrollmentRequest.getOfferDate());
		oeSignupDTO.setOfferTime(enrollmentRequest.getOfferTime());
		oeSignupDTO.setSwitchHoldStatus(enrollmentRequest.getSwitchHoldFlag());
		if(serviceLoationResponse != null){
		oeSignupDTO.setReqStatusCd(serviceLoationResponse.getRequestStatusCode());}
		// Offer data
		OfferDTO selectedOffer = new OfferDTO();
		selectedOffer.setCampaignCode(enrollmentRequest.getCampaignCode());
		selectedOffer.setOfferCode(enrollmentRequest.getOfferCode());
		selectedOffer.setOfferCellTrackCodeSelected(enrollmentRequest.getPromoCode());
		selectedOffer.setProductPriceCode(enrollmentRequest.getProductPriceCode());
		selectedOffer.setIncentiveCode(enrollmentRequest.getIncentiveCode());
		selectedOffer.setMarketSegment(enrollmentRequest.getMarketSegment());
		selectedOffer.setOfferCodeTitle(enrollmentRequest.getSapOfferTagline());

		oeSignupDTO.setSelectedOffer(selectedOffer);

		// Billing address
		AddressDTO billingAddress = new AddressDTO();
		billingAddress.setStreetName(enrollmentRequest.getBillStreetName());
		billingAddress.setStreetNum(enrollmentRequest.getBillStreetNum());
		billingAddress.setUnitNum(enrollmentRequest.getBillStreetAptNum());
		billingAddress.setCity(enrollmentRequest.getBillCity());
		billingAddress.setState(enrollmentRequest.getBillState());
		billingAddress.setZipcodeComplete(enrollmentRequest.getBillZipCode());
		billingAddress.setZipcode(enrollmentRequest.getBillZipCode());
		billingAddress.setPoBox(enrollmentRequest.getBillPOBox());

		oeSignupDTO.setBillingAddress(billingAddress);

		// Service address
		AddressDTO serviceAddress = new AddressDTO();
		serviceAddress.setStreetName(enrollmentRequest.getServStreetName());
		serviceAddress.setStreetNum(enrollmentRequest.getServStreetNum());
		serviceAddress.setUnitNum(enrollmentRequest.getServStreetAptNum());
		serviceAddress.setCity(enrollmentRequest.getServCity());
		serviceAddress.setState(enrollmentRequest.getServState());
		serviceAddress.setZipcodeComplete(enrollmentRequest.getServZipCode());

		oeSignupDTO.setServiceAddress(serviceAddress);

		// Fixed for Defect #67:
		oeSignupDTO.setReferralID(enrollmentRequest.getReferralId());

		// ESIID
		ESIDDTO esid = new ESIDDTO();
		esid.setEsidNumber(enrollmentRequest.getEsid());

		oeSignupDTO.setEsid(esid);

		// Read Person, BP and Credit information from tables
		this.readPersonBpCreditData(oeSignupDTO, enrollmentRequest);

		return oeSignupDTO;
	}

	/**
	 * Populates person and credit data at OESignupDTO from
	 * service_location_affiliate and person_affiliate tables.
	 * 
	 * 
	 * @param oeSignupDTO
	 * @param enrollmentRequest
	 */
	private void readPersonBpCreditData(OESignupDTO oeSignupDTO, EnrollmentRequest enrollmentRequest) {

		// Get enrollment data from person and service location affiliates
		// tables
		ServiceLocationResponse servLocResponse = oeBo.getEnrollmentData(enrollmentRequest.getTrackingId());

		if (servLocResponse != null) {
			// Bp match
			BPMatchDTO bpMatch = new BPMatchDTO();

			// matchedBP
			bpMatch.setMatchedPartnerID(servLocResponse.getMatchedPartnerId());

			// set bp match
			oeSignupDTO.setBpMatch(bpMatch);
			// START : OE :Sprint62 :US21019 :Kdeshmu1
			oeSignupDTO.setAgentID(servLocResponse.getAgentID());
			oeSignupDTO.setAgentType(servLocResponse.getAgentType());
			oeSignupDTO.setAgentFirstName(servLocResponse.getAgentFirstName());
			oeSignupDTO.setAgentLastName(servLocResponse.getAgentLastName());
			oeSignupDTO.setVendorCode(servLocResponse.getVendorCode());
			oeSignupDTO.setVendorName(servLocResponse.getVendorName());
			oeSignupDTO.setTlpReportApiStatus(servLocResponse.getTlpReportApiStatus());
			oeSignupDTO.getSelectedOffer().setOfferTeaser(servLocResponse.getOfferCodeTitle());
			oeSignupDTO.getSelectedOffer().setPlanName(servLocResponse.getPlanName());
			// END : OE :Sprint62 :US21019 :Kdeshmu1
			// read person_affiliate table response
			PersonResponse personResponse = servLocResponse.getPersonResponse();

			if (personResponse != null) {

				// mkt pref
				oeSignupDTO.setEmailSubscription(personResponse.getEmailOptionRps());

				// person information
				this.populatePerson(oeSignupDTO, personResponse);

				// credit check information
				this.populateCreditInfo(oeSignupDTO, personResponse);
			}

			// credit deposit amount
			oeSignupDTO.getCreditCheck().setDepositAmount(servLocResponse.getDepositAmount());
		}
	}

	/**
	 * Sets person data from Person response (table).
	 * 
	 * @param oeSignupDTO
	 * @param personResponse
	 */
	private void populatePerson(OESignupDTO oeSignupDTO, PersonResponse personResponse) {
		// Person data
		PersonDTO person = new PersonDTO();

		person.setStateOfIssue(TX);
		person.setFirstName(personResponse.getFirstName());
		person.setMiddleName(personResponse.getMiddleName());
		person.setLastName(personResponse.getLastName());
		person.setMaidenName(personResponse.getMaidenName());
		person.setTokenizedSSN(personResponse.getSsn());
		person.setTokenizedDL(personResponse.getIdNumber());

		// Get Id Type
		String idType = personResponse.getIdType();
		logger.debug("IdType:" + idType);
		logger.debug("PosidDate:" + personResponse.getPosIdDate());

		// Set posid data based on the id type (dl/ssn)
		if (StringUtils.equalsIgnoreCase(DL, idType)) {
			person.setPosidDLDate(personResponse.getPosIdDate());
		} else if (StringUtils.equalsIgnoreCase(SSN, idType)) {
			person.setPosidSSNDate(personResponse.getPosIdDate());
		}

		logger.debug("DOB:" + personResponse.getDob());
		person.setDateOfBirth(personResponse.getDob());
		person.setLanguagePref(personResponse.getLanguageCode());
		person.setEmailAddress(personResponse.getEmail());
		logger.debug("PHONE:" + personResponse.getPhoneNum());
		person.setPhoneNumber(personResponse.getPhoneNum());

		// set person
		oeSignupDTO.setPerson(person);
	}

	/**
	 * Sets credit data from Person response (table).
	 * 
	 * @param oeSignupDTO
	 * @param personResponse
	 */
	private void populateCreditInfo(OESignupDTO oeSignupDTO, PersonResponse personResponse) {
		// Credit data
		CreditCheckDTO creditCheck = new CreditCheckDTO();

		creditCheck.setCreditSourceNum(personResponse.getCredSourceNum());
		creditCheck.setCreditBucket(personResponse.getCredLevelNum());
		creditCheck.setCreditScoreNum(personResponse.getCredScoreNum());

		if (StringUtils.isNotBlank(personResponse.getAdvActionData())) {
			creditCheck.setFactorsKey(CommonUtil.convertAsList(DELIMETER_COMMA, personResponse.getAdvActionData()));
		}

		// set credit check
		oeSignupDTO.setCreditCheck(creditCheck);
	}

	public CreditCheckRequest createCreditCheckRequest(SalesCreditCheckRequest salesCreditCheckRequest,
			ServiceLocationResponse serviceLocationResponse) {

		CreditCheckRequest creditCheckRequest = new CreditCheckRequest();
		BeanUtils.copyProperties(salesCreditCheckRequest, creditCheckRequest);
		creditCheckRequest.setFirstName(serviceLocationResponse.getPersonResponse().getFirstName());
		creditCheckRequest.setLastName(serviceLocationResponse.getPersonResponse().getLastName());
		creditCheckRequest.setTokenizedSSN(serviceLocationResponse.getPersonResponse().getSsn());

		String transactionType = StringUtils.equalsIgnoreCase(serviceLocationResponse.getServiceRequestTypeCode(), S)
				? SWI : MVI;
		creditCheckRequest.setTransactionType(transactionType);
		String esid = StringUtils.isEmpty(serviceLocationResponse.getEsid()) ? EMPTY : serviceLocationResponse.getEsid();
		creditCheckRequest.setEsid(esid);
		creditCheckRequest.setServStreetNum(serviceLocationResponse.getServStreetNum());
		creditCheckRequest.setServStreetName(serviceLocationResponse.getServStreetName());
		creditCheckRequest.setServStreetAptNum(serviceLocationResponse.getServStreetAptNum());
		creditCheckRequest.setServCity(serviceLocationResponse.getServCity());
		creditCheckRequest.setServState(serviceLocationResponse.getServState());
		creditCheckRequest.setServZipCode(serviceLocationResponse.getServZipCode());

		creditCheckRequest.setBillStreetNum(serviceLocationResponse.getBillStreetNum());
		creditCheckRequest.setBillStreetName(serviceLocationResponse.getBillStreetName());
		creditCheckRequest.setBillStreetAptNum(serviceLocationResponse.getBillStreetAptNum());
		creditCheckRequest.setBillCity(serviceLocationResponse.getBillCity());
		creditCheckRequest.setBillState(serviceLocationResponse.getBillState());
		creditCheckRequest.setBillZipCode(serviceLocationResponse.getBillZipCode());
		creditCheckRequest.setBillPOBox(serviceLocationResponse.getBillPoBox());
		creditCheckRequest.setCallExecuted(salesCreditCheckRequest.getCallExecuted());
		if (!StringUtils.equalsIgnoreCase(serviceLocationResponse.getErrorCode(), BPSD)) {
			creditCheckRequest.setMatchedBP(serviceLocationResponse.getMatchedPartnerId());
		} else {
			creditCheckRequest.setBpMatchFlag(BPSD);
		}
		creditCheckRequest.setCallExecuted(CommonUtil.getPipeSeperatedCallExecutedParamForDB(salesCreditCheckRequest.getCallExecuted(),serviceLocationResponse.getCallExecutedFromDB()));
		return creditCheckRequest;
	}

	public CreditCheckRequest createCreditReCheckRequest(SalesCreditReCheckRequest salesCreditReCheckRequest,
			ServiceLocationResponse serviceLocationResponse) {
		CreditCheckRequest creditCheckRequest = createCreditCheckRequest(salesCreditReCheckRequest,
				serviceLocationResponse);
		creditCheckRequest.setTokenizedSSN(salesCreditReCheckRequest.getTokenizedSSN());
		return creditCheckRequest;
	}

	public EnrollmentRequest createSubmitEnrollmentRequest(SalesEnrollmentRequest enrollmentRequest, ServiceLocationResponse serviceLoationResponse) {
		EnrollmentRequest request = new EnrollmentRequest();
		request.setChannelType(enrollmentRequest.getChannelType());
		request.setAffiliateId(enrollmentRequest.getAffiliateId());
		request.setCompanyCode(enrollmentRequest.getCompanyCode());
		request.setBrandId(enrollmentRequest.getBrandId());
		request.setEbillFlag(enrollmentRequest.getEbillFlag());
		request.setLanguageCode(enrollmentRequest.getLanguageCode());
		request.setTrackingId(enrollmentRequest.getTrackingId());

		if (StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())) {
			String[] errorCdArray = serviceLoationResponse.getErrorCdlist().split(ERROR_CD_LIST_SPLIT_PATTERN);
			for (String holdtype : errorCdArray) {
				if (holdtype.equalsIgnoreCase(BPSD) || holdtype.equalsIgnoreCase(PBSD)) {
					request.setBpMatchFlag(BPSD);
				}

				if (holdtype.equalsIgnoreCase(SWITCHHOLD)) {
					request.setSwitchHoldFlag(ON);
				}
			}
		}
		request.setMviDate(serviceLoationResponse.getServiceStartDate());
		request.setTransactionType(serviceLoationResponse.getServiceRequestTypeCode());
		request.setTdspCodeCCS(serviceLoationResponse.getTdspCode());
		request.setOfferCode(enrollmentRequest.getOfferCode());
		request.setPromoCode(enrollmentRequest.getPromoCode());
		request.setCampaignCode(enrollmentRequest.getCampaignCode());
		request.setProductPriceCode(enrollmentRequest.getProductPriceCode());
		request.setIncentiveCode(enrollmentRequest.getIncentiveCode());
		request.setMarketSegment(enrollmentRequest.getMarketSegment());
		request.setSapOfferTagline(serviceLoationResponse.getOfferCodeTitle());
		// Billing address
		request.setBillStreetName(serviceLoationResponse.getBillStreetName());
		request.setBillStreetNum(serviceLoationResponse.getBillStreetNum());
		request.setBillStreetAptNum(serviceLoationResponse.getBillStreetAptNum());
		request.setBillCity(serviceLoationResponse.getBillCity());
		request.setBillState(serviceLoationResponse.getBillState());
		request.setBillZipCode(serviceLoationResponse.getBillZipCode());
		request.setBillPOBox(serviceLoationResponse.getBillPoBox());

		// Service address
		request.setServStreetName(serviceLoationResponse.getServStreetName());
		request.setServStreetNum(serviceLoationResponse.getServStreetNum());
		request.setServStreetAptNum(serviceLoationResponse.getServStreetAptNum());
		request.setServCity(serviceLoationResponse.getServCity());
		request.setServState(serviceLoationResponse.getServState());
		request.setServZipCode(serviceLoationResponse.getServZipCode());

		request.setReferralId(serviceLoationResponse.getReferralId());
		request.setEsid(serviceLoationResponse.getEsid());
		request.setCallExecuted(enrollmentRequest.getCallExecuted());
		return request;
	}
	
	public KbaQuestionRequest createKBAQuestionRequest(GetKBAQuestionsRequest request){
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
		kbaQuestionRequest.setTokenizedSSN(request.gettokenizedSSN());		
		if(StringUtils.isNotEmpty(request.getTokenizedTDL())){
	        kbaQuestionRequest.setTokenizedDrl(request.getTokenizedTDL());        
	        kbaQuestionRequest.setDlrState(request.getDrivingLicenseState());
	    } 
		
//		kbaQuestionRequest.setTokenizedDrl("KR0PK39V-2290");
//		kbaQuestionRequest.setDlrState("TX");
//		kbaQuestionRequest.setTokenizedSSN("2RD6VE6-5840");
//		kbaQuestionRequest.setDlrState(null);
		
		
		kbaQuestionRequest.setHomePhone(request.getPhoneNum());
		kbaQuestionRequest.setEmailAddress(request.getEmail());
		kbaQuestionRequest.setIpAddress(request.getIpAddress());
		kbaQuestionRequest.setEsid(request.getEsid());
		kbaQuestionRequest.setPosidBasedKBAFlag(FLAG_X);
		kbaQuestionRequest.setFailFromPosidFlag(FLAG_X);
		
		
		com.multibrand.domain.AddressDTO serviceAddressDTO = new com.multibrand.domain.AddressDTO();
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
	
	public KbaSubmitAnswerRequest createKBASubmitAnswerRequest(KbaAnswerRequest kbaAnswerRequest) {
		KbaSubmitAnswerRequest request = new KbaSubmitAnswerRequest();
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
		return request;
	}
	
	private List<KBAQuestionAnswerVO> constructKBAQuestionAnswerVOList(KbaAnswerRequest kbaAnswerRequest){
		List<KBAQuestionAnswerVO> questionAnswerList = new ArrayList();
		
		if(kbaAnswerRequest.getQuestionList() != null){		
			ObjectMapper mapper = new ObjectMapper();
			questionAnswerList = mapper.convertValue(kbaAnswerRequest.getQuestionList(), new TypeReference<List<KBAQuestionAnswerVO>>() { });
			
		}
		return questionAnswerList;
	}
	
	public KbaQuestionRequest createKBAQuestionRequest(ServiceLocationResponse serviceLocationResponse,GetOEKBAQuestionsRequest getOEKBAQuestionsRequest) throws ParseException{
		KbaQuestionRequest kbaQuestionRequest = new KbaQuestionRequest(); ;
		
		
		kbaQuestionRequest.setCompanyCode(getOEKBAQuestionsRequest.getCompanyCode());
		String brandName = CommonUtil.getBrandIdFromCompanycodeForCCS(getOEKBAQuestionsRequest.getCompanyCode(), getOEKBAQuestionsRequest.getBrandId());
		kbaQuestionRequest.setBrandName(brandName);
		kbaQuestionRequest.setChannel(CHANNEL);
		kbaQuestionRequest.setChannelType(CHANNEL_TYPE_AA);
		String langCode = (StringUtils.equalsIgnoreCase(getOEKBAQuestionsRequest.getLanguageCode(), LANG_ES))? LANG_ES:LANG_EN;
		kbaQuestionRequest.setLanguageCode(langCode);
		
		kbaQuestionRequest.setFirstName(serviceLocationResponse.getPersonResponse().getFirstName());
		kbaQuestionRequest.setLastName(serviceLocationResponse.getPersonResponse().getLastName());
		kbaQuestionRequest.setMiddleName(serviceLocationResponse.getPersonResponse().getMiddleName());	
		
		Date serDate=new SimpleDateFormat("MMddyyyy").parse(serviceLocationResponse.getPersonResponse().getDob());
		String finalSerDate = new SimpleDateFormat("MM/dd/yyyy").format(serDate);
		kbaQuestionRequest.setDob(finalSerDate.toString());
		
		kbaQuestionRequest.setTokenizedSSN(serviceLocationResponse.getPersonResponse().getSsn());		
		if(StringUtils.isNotEmpty(serviceLocationResponse.getPersonResponse().getIdNumber())){
	        kbaQuestionRequest.setTokenizedDrl(serviceLocationResponse.getPersonResponse().getIdNumber());        
	        kbaQuestionRequest.setDlrState(serviceLocationResponse.getPersonResponse().getIdStateOfIssue());
	    }
		
//		kbaQuestionRequest.setTokenizedDrl("KR0PK39V-2290");
//		kbaQuestionRequest.setDlrState("TX");
//		kbaQuestionRequest.setTokenizedSSN("2RD6VE6-5840");
//		kbaQuestionRequest.setDlrState(null);
		
		
		kbaQuestionRequest.setHomePhone(serviceLocationResponse.getPersonResponse().getPhoneNum());
		kbaQuestionRequest.setEmailAddress(serviceLocationResponse.getPersonResponse().getEmail());
		
		
		
		kbaQuestionRequest.setIpAddress("");
		kbaQuestionRequest.setEsid(serviceLocationResponse.getEsid());
		kbaQuestionRequest.setPosidBasedKBAFlag(FLAG_X);
		kbaQuestionRequest.setFailFromPosidFlag(FLAG_X);
		
		
		com.multibrand.domain.AddressDTO serviceAddressDTO = new com.multibrand.domain.AddressDTO();
		String streetNum = serviceLocationResponse.getServAddressLine1().substring(0, serviceLocationResponse.getServAddressLine1().indexOf(" "));
		String streetName = serviceLocationResponse.getServAddressLine1().substring(serviceLocationResponse.getServAddressLine1().indexOf(" "));
		
		serviceAddressDTO.setStrStreetNum(streetNum);
		serviceAddressDTO.setStrStreetName(streetName);		
		serviceAddressDTO.setStrUnitNumber(serviceLocationResponse.getServAddressLine2());
		serviceAddressDTO.setStrCity(serviceLocationResponse.getServCity());
		serviceAddressDTO.setStrState(serviceLocationResponse.getServState());
		serviceAddressDTO.setStrZip(serviceLocationResponse.getServZipCode());
		
		kbaQuestionRequest.setServiceAddress(serviceAddressDTO);
		kbaQuestionRequest.setPosidUniqueKey(serviceLocationResponse.getPosidSNRO());
		
		return kbaQuestionRequest;
	}
}