package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.ValidateAddressHelper;
import com.multibrand.domain.ValidateCustReferralIdResponse;
import com.multibrand.domain.ValidatePaymentReceiptRequest;
import com.multibrand.domain.ValidatePosIdKBARequest;
import com.multibrand.domain.ValidatePosIdKBAResponse;
import com.multibrand.domain.ValidatePosIdRequest;
import com.multibrand.domain.ValidatePosIdResponse;
import com.multibrand.domain.ValidateReferralIdRequest;
import com.multibrand.dto.BPMatchDTO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.AddPersonRequest;
import com.multibrand.dto.request.AddServiceLocationRequest;
import com.multibrand.dto.request.AgentDetailsRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.UpdatePersonRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.request.ValidateAddressRequest;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.dto.response.ValidateAddressResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.exception.OEException;
import com.multibrand.proxy.ValidationProxy;
import com.multibrand.request.handlers.ValidationRequestHandler;
import com.multibrand.service.OEService;
import com.multibrand.service.ValidationService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.util.TogglzUtil;
import com.multibrand.vo.request.ValidateThirdPartyReceipt;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.domain.ValidatePaymentReceiptResponse;
import com.multibrand.vo.response.ValidateThirdPartyReceiptResponse;
import com.reliant.domain.AddressValidateRequest;
import com.reliant.domain.AddressValidateResponse;

/**
 * This BO class is to handle all the Validation Related API calls.
 * 
 * @author rbansal30
 */
@Component
public class ValidationBO extends BaseBO {

	@Autowired
	OEService oeService;

	/** Object of ValidationService class. */
	@Autowired
	private ValidationService validationService;

	@Autowired
	private ValidateAddressHelper validateAddressHelper;

	@Autowired
	private ValidationProxy validationProxy;

	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;

	// ~Autowire entries
	@Autowired
	ValidationRequestHandler validateRequestHandler;
	
	 @Autowired
	private TogglzUtil togglzUtil;


	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
	 * This method is to do the trillium check on Client request.
	 * 
	 * @author rbansal30
	 * @param aptNumber		Apartment Number
	 * @param city			City
	 * @param country			Country
	 * @param state			State
	 * @param streetName		Street Name
	 * @param streetNum		Street Number
	 * @param zipCode			Zip Code
	 * @param companyCode		Company Code
	 * @return response Provide balance data in GetArResponse object.
	 */
	public com.multibrand.vo.response.AddressValidateResponse validateBillingAddress(String aptNumber, String city,
			String country, String state, String streetName, String streetNum, String zipCode, String companyCode,String poBox, String sessionId, String brandName) {

		AddressValidateResponse response = null;

		com.multibrand.vo.response.AddressValidateResponse addressValidationResponse = new com.multibrand.vo.response.AddressValidateResponse();

		try {

			AddressValidateRequest addressValidateRequest = new AddressValidateRequest();
			addressValidateRequest.setAptNumber(aptNumber);
			addressValidateRequest.setCity(city);
			addressValidateRequest.setCountry(country);
			addressValidateRequest.setState(state);
			addressValidateRequest.setStreetName(streetName);
			addressValidateRequest.setStreetNum(streetNum);
			addressValidateRequest.setZipCode(zipCode);

			response = validationService.validateBillingAddress(addressValidateRequest,companyCode, sessionId);
			logger.debug(response.getAptNumber());
			logger.debug(response.getState());

			JavaBeanUtil.copy(response, addressValidationResponse);
			if(response.getMatchStatusFlag()!=null && response.getMatchStatusFlag().trim().equalsIgnoreCase(NO_MATCH))
			{
				addressValidationResponse.setResultCode(RESULT_CODE_NO_DATA);
				addressValidationResponse.setResultDescription(response.getMatchStatusFlag());
			}
			else
			{
				addressValidationResponse.setResultCode(RESULT_CODE_SUCCESS);
				addressValidationResponse.setResultDescription(response.getMatchStatusFlag());
			}
		} catch (RemoteException e) {

			addressValidationResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			addressValidationResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), addressValidationResponse);
		} catch (Exception e) {
			addressValidationResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			addressValidationResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), addressValidationResponse);
		}
		return addressValidationResponse;		
	}




	//START ONLINE AFFILIATES - JSINGH1

	/**
	 * <p>This method validates the personal details and checks if there is any existing
	 *  business partner detail available for the prospect and merges the response from both calls.</p>
	 * @param performPosIdBpRequest
	 * @return {@link com.multibrand.vo.response.PerformPosIdandBpMatchResponse PerformPosIdandBpMatchResponse}
	 * @throws OEException 
	 */
	public com.multibrand.vo.response.PerformPosIdandBpMatchResponse validatePosId(
			PerformPosIdAndBpMatchRequest performPosIdBpRequest,OESignupDTO oESignupDTO, ServiceLocationResponse serviceLoationResponse) throws OEException
	{
		logger.debug(" START *******ValidationBO:: validatePosID API**********");
		logger.debug("inside validatePosId:: tracking id from Form Parameters is :: "+performPosIdBpRequest.getTrackingId());
		int retryCount=0;
		String personId=null;
		String retryCountStr=null;
		String posidStatus=null;
		String posidPii=null;
		String posIdDate=null;
		String messageCode=null;
		String errorCodeFromDB=null;
		String errorCodeFromAPI=null;
		String recentCallMade=null;
		LinkedHashSet<String> serviceLocationResponseerrorList = new LinkedHashSet<>();
		
		com.multibrand.vo.response.PerformPosIdandBpMatchResponse response= new com.multibrand.vo.response.PerformPosIdandBpMatchResponse();
		BPMatchDTO bpMatchDTO=new BPMatchDTO();
		/*
		 * setting tokenized values into response
		 */
		response.setTokenizedSSN(performPosIdBpRequest.getTokenizedSSN());
		response.setTokenizedTDL(performPosIdBpRequest.getTokenizedTDL());

		//isValidDate will confirm if DOB got processed properly into desired format
		/*boolean isValidDate=true;
		if(performPosIdBpRequest.getDob().equals(dobForPosId))
			isValidDate=false;
		logger.info("inside validatePosId:: is valid date is :: "+isValidDate);*/

		boolean posidHoldAllowed= togglzUtil.getFeatureStatusFromTogglzByChannel(TOGGLZ_FEATURE_ALLOW_POSID_SUBMISSION,performPosIdBpRequest.getChannelType());
		
		boolean posidHoldAllowedForAffilate = false;  
		if(StringUtils.equals(performPosIdBpRequest.getAffiliateId(), AFFILIATE_ID_COMPAREPOWER) 
				|| (StringUtils.equals(performPosIdBpRequest.getAffiliateId(), AFFILIATE_ID_DSI))) {
			posidHoldAllowedForAffilate = togglzUtil.getFeatureStatusFromTogglz(TOGGLZ_FEATURE_ALLOW_POSID_SUBMISSION+DOT+performPosIdBpRequest.getAffiliateId());
		}
		 
		
		if(serviceLoationResponse != null){
		 errorCodeFromDB=serviceLoationResponse.getErrorCode();
		}
		/*
		 * Processing preferredLanguage
		 */
		performPosIdBpRequest=preferredLanguage(performPosIdBpRequest);

		logger.info("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
				+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: preferred language is"
						+ " "+performPosIdBpRequest.getPreferredLanguage());

		serviceLocationResponseerrorList = serviceLocationResponseerrorList(performPosIdBpRequest,
				serviceLoationResponse, serviceLocationResponseerrorList);
		
		/*
		 * Step 2: Check if we have a Tracking Number in the call 
		 */
		if(StringUtils.isBlank(performPosIdBpRequest.getTrackingId())){
			retryCountStr="0";}
		/*
		 * Step 3: fetch person Id and retry Count if trackingID is a number
		 */
		else if (StringUtils.isNumeric(performPosIdBpRequest.getTrackingId())){
			logger.debug("inside validatePosId:: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +":: "
					+ "Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: tracking number is numeric ");

			if(null!=serviceLoationResponse 
					&& serviceLoationResponse.getPersonResponse() != null
					)
			{
				
				retryCount=	Integer.parseInt(serviceLoationResponse.getPersonResponse().getRetryCount());
				personId = serviceLoationResponse.getPersonResponse().getPersonId();
				logger.debug("inside validatePosId:: Tracking number :: "+performPosIdBpRequest.getTrackingId()+""
						+ " retry count from database is :: "+retryCount);

				/*
				 * Step 4: Check the retry count value if greater than 2 then 
				 * continue as PASS and assess POSIDHOLD (when Togglz is ON)
				 * or hardstop (when TOGGLZ is OFF)
				 */
				retryCount=retryCount+1;
				if(retryCount>3)
				{
					errorCodeFromAPI = processPerformPosidBPMatchAfterMaxRetryAllowed(performPosIdBpRequest, 
						 response, retryCount  );
					
					if(StringUtils.isNotEmpty(serviceLoationResponse.getErrorCode())){
						errorCodeFromAPI = serviceLoationResponse.getErrorCode();
					}
					
					return response;
				}

				response.setTrackingId(performPosIdBpRequest.getTrackingId());
				//increment retry count
				
				logger.debug("inside validatePosId:: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
						+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: retry count after increment is ::"+retryCount);
			}
		}
		else 
		{
			//Tracking number is invalid (alphabetic or special characters)
			logger.debug("inside validatePosId:: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
					+ ":: tracking id is not numeric :: trackind id :: "+performPosIdBpRequest.getTrackingId());
			response.setResultDescription("Invalid Tracking Number");
			response.setStatusCode(STATUS_CODE_STOP);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			return response;

		}

		logger.debug("inside validatePosId:: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: affiliate Id :"
				+ " "+performPosIdBpRequest.getAffiliateId() +":: retry Count is ::"+retryCount);
		ValidatePosIdKBAResponse validatePosIdKBAResponse= new ValidatePosIdKBAResponse();
		try{
			
				
			/*
			 * Step 5: Make validatePosId call
			 */
			validatePosIdKBAResponse = callValidateMethod(performPosIdBpRequest, validatePosIdKBAResponse);
			response.setErrorDescription(validatePosIdKBAResponse.getStrErroMessage());
			oESignupDTO.setPosidSNRO(validatePosIdKBAResponse.getPosidUniqueKey());
			/*
			 * PosId Scenario: 
			 */
			logger.debug("inside validatePosId:: response from posid cal is: "+CommonUtil.doRender(validatePosIdKBAResponse));
			if(null!=(validatePosIdKBAResponse) && isPosidValidated(validatePosIdKBAResponse))
			{
				logger.debug("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
						+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: POSID SUCCESSFULLY CONDUCTED");
				//Pass the parameters from NRG response to wrapper Response POJO
				
				posidStatus=POSID_FLAG_YES;
				serviceLocationResponseerrorList.remove(POSIDHOLD);
				recentCallMade=RECENT_CALL_MADE_BP_MATCH;
							
				errorCodeFromAPI = processPosidPassResponse( performPosIdBpRequest, 
										oESignupDTO, serviceLoationResponse,
										response,errorCodeFromAPI,messageCode, 
										serviceLocationResponseerrorList ,  
										bpMatchDTO, validatePosIdKBAResponse);
				
				posIdDate = oESignupDTO.getPosIdDate();
				posidPii = oESignupDTO.getPosidPii();
	
			}

			else if((null!=(validatePosIdKBAResponse)&&(StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExDlVerifydate(), POSID_BLANK_DATE))
					&&(StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExSsnVerifydate(), POSID_BLANK_DATE)) && 
					(StringUtils.isBlank( validatePosIdKBAResponse.getStrErroMessage()) 
							|| StringUtils.isBlank(validatePosIdKBAResponse.getStrErroCode())))|| StringUtils.equalsIgnoreCase(performPosIdBpRequest.getNoid(), FLAG_TRUE) ) 
			{
				
				errorCodeFromAPI = processPosidFailedResponse(performPosIdBpRequest, oESignupDTO, 
						serviceLoationResponse, response, 
						serviceLocationResponseerrorList, bpMatchDTO, 
						 posidHoldAllowedForAffilate, posidHoldAllowed, retryCount);
				posidStatus = oESignupDTO.getPosidStatus();
				
				if(StringUtils.equalsIgnoreCase(performPosIdBpRequest.getNoid(), FLAG_TRUE)) {
					posidPii=FLAG_OTHER;
					posIdDate=null;
				}
				
			}
			else
			{ 
				if(retryCount<=2){
					response.setStatusCode(STATUS_CODE_ASK);
					messageCode=POSID_FAIL;
					response.setMessageText(getMessage(POSID_FAIL_MSG_TXT));
					response.setMessageCode(messageCode);
					errorCodeFromAPI=POSIDHOLD;
				}
				else{
					logger.debug("inside com.multibrand.bo:: validatePosId ::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
							+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" ::this is 3rd attempt so POSID Failure Maxed out");
					response.setStatusCode(STATUS_CODE_STOP);
					messageCode=POSID_FAIL_MAX;
					response.setMessageCode(messageCode);
					response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
					errorCodeFromAPI=POSIDHOLD;
				}
				serviceLocationResponseerrorList.remove(BP_RESTRICT);
				serviceLocationResponseerrorList.remove(BPSD);
				serviceLocationResponseerrorList.add(errorCodeFromAPI);
			}

			//setting retrycount in response:
			response.setRetryCount(Integer.toString(retryCount));
						
			response.setKbaSuggestionFlag(validatePosIdKBAResponse.getKbaSuggestionFlag());	
			oESignupDTO.setKbaSuggestionFlag(validatePosIdKBAResponse.getKbaSuggestionFlag());
			if(serviceLocationResponseerrorList.contains(BPSD)) {
				errorCodeFromAPI = BPSD;
			}

		}
		catch(Exception e)
		{
			response.setStatusCode(STATUS_CODE_ASK);
			response.setMessageCode(POSID_FAIL);
			messageCode=POSID_FAIL;
			serviceLocationResponseerrorList.remove(BP_RESTRICT);
			serviceLocationResponseerrorList.remove(BPSD);
			serviceLocationResponseerrorList.remove(POSIDHOLD);
			response.setMessageText(getMessage(POSID_FAIL_MSG_TXT));
			logger.error("inside com.multibrand.bo:: validatePosId :: Exception making Posid REST Call", e);
			throw new OAMException(200, e.getMessage(), response);
		}
		finally{
			oESignupDTO.setErrorCdList(StringUtils.join(serviceLocationResponseerrorList,SYMBOL_PIPE));
			updateSLTable(performPosIdBpRequest, oESignupDTO, 
				serviceLoationResponse, response, errorCodeFromAPI, messageCode, serviceLocationResponseerrorList,
				bpMatchDTO, validatePosIdKBAResponse, posidPii, posIdDate, posidStatus, recentCallMade,retryCount,errorCodeFromAPI,errorCodeFromDB);
		}	

		return response;
	}
	//END ONLINE AFFILIATES - JSINGH1




	public LinkedHashSet<String> serviceLocationResponseerrorList(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
			ServiceLocationResponse serviceLoationResponse, LinkedHashSet<String> serviceLocationResponseerrorList) {
		if(StringUtils.isNotEmpty(performPosIdBpRequest.getTrackingId()) && serviceLoationResponse != null && StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())){
		    
				String[] errorCdArray =serviceLoationResponse.getErrorCdlist().split("\\|");
				serviceLocationResponseerrorList = new LinkedHashSet<>(Arrays.asList(errorCdArray));
		}
		return serviceLocationResponseerrorList;
	}




	public PerformPosIdAndBpMatchRequest preferredLanguage(PerformPosIdAndBpMatchRequest performPosIdBpRequest) {
		if(StringUtils.isNotBlank(performPosIdBpRequest.getPreferredLanguage()) && 
				performPosIdBpRequest.getPreferredLanguage().equalsIgnoreCase(S)) {
			performPosIdBpRequest.setPreferredLanguage(ES);
		}
		else {
			performPosIdBpRequest.setPreferredLanguage(EN);
		}
		return performPosIdBpRequest;
	}

	/**
	 * 
	 * @author jyogapa1 (Jenith)
	 * 
	 * This method is to do the Trillium check on Client requested address.
	 * 
	 * @param validateAddressRequestDTO
	 * @return
	 */
	public ValidateAddressResponse validateAddress(
			ValidateAddressRequest requestDto)
					throws OEException {

		String METHOD_NAME = "ValidationBO: validateAddress(..)";

		logger.debug("Start:" + METHOD_NAME);

		ValidateAddressResponse responseDto = new ValidateAddressResponse();

		try {

			// Create NRGWS request
			AddressValidateRequest addressValidateRequest = 
					validateAddressHelper.createAddressValidateRequest(requestDto);

			// Validate address via Address validation NRGWS proxy layer
			AddressValidateResponse addressValidateResponse = validationProxy
					.validateAddress(addressValidateRequest);

			// Handle NRGWS AddressValidateResponse and parse it as NRGREST
			// response.
			this.handleAddressValidateResponse(requestDto, responseDto,
					addressValidateResponse);

		} catch (RemoteException e) {
			logger.error("SERVICE ERROR:" + METHOD_NAME, e);
			handleServiceException(responseDto, METHOD_NAME, e, Boolean.FALSE);

		} catch (Exception e) {
			logger.error("ERROR:" + METHOD_NAME, e);
			handleServiceException(responseDto, METHOD_NAME, e, Boolean.FALSE);
		}

		logger.debug("END:" + METHOD_NAME);

		return responseDto;
	}

	/**
	 * @author jyogapa1
	 * 
	 * Handles NRGWS domain Address Validate response and returns into
	 * NRGREST ValidateAddressResponseDTO.
	 * 
	 * @param responseDto
	 * @param updateContactResponse
	 * @throws Exception
	 */
	private void handleAddressValidateResponse(ValidateAddressRequest requestDto,
			ValidateAddressResponse responseDto,
			AddressValidateResponse addressValidateResponse) throws Exception {

		// Read NRGWS address validate response data and populate it in NRGREST
		if (addressValidateResponse != null) {

			if (StringUtils.isBlank(addressValidateResponse
					.getErrorCode())) {
				// Address validation success
				validateAddressHelper.handleAddressValidationSuccess(responseDto, addressValidateResponse);
			} else {
				// Address validation failed
				validateAddressHelper.handleAddressValidationError(responseDto, addressValidateResponse);
			}

			// Set address validate response from NRGWS
			validateAddressHelper.populateValidateAddressResponse(responseDto, addressValidateResponse);	

		} else {
			responseDto.setResultCode(RESULT_CODE_NO_DATA);
			responseDto.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);

			// Set address validate response from NRGREST request
			validateAddressHelper.populateValidateAddressResponseSameAsRequest(responseDto, requestDto);
		}
	}

	/**
	 * Populates required parameters for Address Validation.
	 * 
	 * 
	 * @param validateAddressRequest
	 * @return
	 */
	@Deprecated
	private Map<String, Object> buildAddressValidationRequiredParams(
			ValidateAddressRequest validateAddressRequest) {

		Map<String, Object> mandatoryParamList = new HashMap<String, Object>();
		mandatoryParamList.put("affiliateId", validateAddressRequest.getAffiliateId());
		mandatoryParamList.put("companyCode", validateAddressRequest.getCompanyCode());
		mandatoryParamList.put("city", validateAddressRequest.getCity());
		mandatoryParamList.put("state", validateAddressRequest.getState());
		mandatoryParamList.put("zipCode", validateAddressRequest.getZipCode());

		return mandatoryParamList;
	}

	/**
	 * Populates size boundary to be checked parameters for Address Validation.
	 * 
	 * 
	 * @param validateAddressRequest
	 * @return
	 */
	@Deprecated
	private Map<String, Object> buildAddressValidationSizeParams(
			ValidateAddressRequest validateAddressRequest) {

		Map<String, Object> sizeParamList = new HashMap<String, Object>();
		sizeParamList.put("state", validateAddressRequest.getState());

		return sizeParamList;
	}

	/**
	 * @method getValidAge
	 * This method verifies if the Date of birth passed by prospect is 
	 * @param dob
	 * @return
	 * @throws Exception
	 */

	public boolean getValidAge(String dob) {
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar birthdateCalendar = Calendar.getInstance();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(MM_dd_yyyy);
			birthdateCalendar.setTime(sdf.parse(dob));
			int birthYear = birthdateCalendar.get(Calendar.YEAR);
			int yearsSinceBirth = thisYear - birthYear;
			logger.debug("inside getValidAge:: years since birth are :: "+yearsSinceBirth);
			if(yearsSinceBirth>=18 && yearsSinceBirth<=100)
			{return true;}
			else
				return false;
		}
		catch(Exception e )
		{
			logger.error("ParseException in getValidAge() :: ", e);
			return false;
		}

	}


	/**
	 * This method is used to populate request object for addPerson Call
	 * @param addPersonRequest
	 * @param performPosIdBpRequest
	 * @param posidPii
	 * @param posIdDate
	 * @param posidStatus
	 * @author jsingh1
	 */
	private void createAddPersonRequest(AddPersonRequest addPersonRequest,
			PerformPosIdAndBpMatchRequest performPosIdBpRequest,String posidPii,
			String posIdDate,String posidStatus)
	{
		addPersonRequest.setIdType(posidPii);
		addPersonRequest.setDob(performPosIdBpRequest.getDob());
		addPersonRequest.setEmail(performPosIdBpRequest.getEmail());
		addPersonRequest.setFirstName(performPosIdBpRequest.getFirstName());
		addPersonRequest.setMaidenName(performPosIdBpRequest.getMaidenName());
		addPersonRequest.setMiddleName(performPosIdBpRequest.getMiddleName());
		addPersonRequest.setIdNumber(performPosIdBpRequest.getTokenizedTDL());
		addPersonRequest.setSsn(performPosIdBpRequest.getTokenizedSSN());
		addPersonRequest.setPosIdStatus(posidStatus);
		//logic for mktpref
		if(StringUtils.isNotBlank(performPosIdBpRequest.getMktPref()) && 
				performPosIdBpRequest.getMktPref().equalsIgnoreCase("Y"))
			addPersonRequest.setEmailOptionRps(X_VALUE);
		else
			addPersonRequest.setEmailOptionRps(O_VALUE);

		addPersonRequest.setLastName(performPosIdBpRequest.getLastName());
		String posIdDateAdd=null;
		if(StringUtils.isNotBlank(posIdDate)){
			Date formattedposIdDate  = DateUtil.getDate(posIdDate, "MM/dd/yyyy");
			posIdDateAdd=DateUtil.getFormatedDate(formattedposIdDate, "MMddyyyy");
		}
		addPersonRequest.setPosIdDate(posIdDateAdd);
		addPersonRequest.setIdStateOfIssue(TX);
		addPersonRequest.setPhoneNum(performPosIdBpRequest.getPhoneNum());
		addPersonRequest.setLanguageCode(performPosIdBpRequest.getPreferredLanguage()); 
	}

	/**
	 * This method is used to populate request object for addServiceLocation Call
	 * @param addServiceLocation
	 * @param performPosIdBpRequest
	 * @param personId
	 * @param messageCode
	 * @param errorCd
	 * @author jsingh1
	 */
	private void createAddServiceLocationRequest(AddServiceLocationRequest addServiceLocation,
			PerformPosIdAndBpMatchRequest performPosIdBpRequest, String personId,
			String messageCode,String errorCd,String recentCallMade,OESignupDTO oESignupDTO, BPMatchDTO bpMatchDTO)
	{
		/*if(StringUtils.isNotBlank(performPosIdBpRequest.getTransactionType()) && 
				performPosIdBpRequest.getTransactionType().equalsIgnoreCase(MVI))
			addServiceLocation.setServiceRequestTypeCode("N");
		else
			addServiceLocation.setServiceRequestTypeCode(S);*/
		
		addServiceLocation.setServiceRequestTypeCode(performPosIdBpRequest.getTransactionType());
		addServiceLocation.setServAddressLine2(performPosIdBpRequest.getServStreetAptNum());
		addServiceLocation.setServStreetAptNum(performPosIdBpRequest.getServStreetAptNum());
		addServiceLocation.setCompanyCode(performPosIdBpRequest.getCompanyCode());
		addServiceLocation.setServCity(performPosIdBpRequest.getServCity());
		addServiceLocation.setServState(performPosIdBpRequest.getServState());
		addServiceLocation.setServStreetName(performPosIdBpRequest.getServStreetName());
		addServiceLocation.setServStreetNum(performPosIdBpRequest.getServStreetNum());		
		/*if(StringUtils.isNotEmpty(performPosIdBpRequest.getBillPOBox())) {
			addServiceLocation.setBillAddressLine1(performPosIdBpRequest.getBillPOBox());
		}else {
			addServiceLocation.setBillAddressLine1(CommonUtil.getAddressLine1(performPosIdBpRequest.getBillStreetNum(), performPosIdBpRequest.getBillStreetName()));
		}*/		
		addServiceLocation.setBillPoBox(performPosIdBpRequest.getBillPOBox());
		addServiceLocation.setBillCity(performPosIdBpRequest.getBillCity());
		addServiceLocation.setBillState(performPosIdBpRequest.getBillState());
		addServiceLocation.setBillAddressLine2(performPosIdBpRequest.getBillStreetAptNum());
		addServiceLocation.setBillStreetAptNum(performPosIdBpRequest.getBillStreetAptNum());
		addServiceLocation.setBillZipCode(performPosIdBpRequest.getBillZipCode());
		addServiceLocation.setServZipCode(performPosIdBpRequest.getServZipCode());
		addServiceLocation.setBillStreetName(performPosIdBpRequest.getBillStreetName());
		addServiceLocation.setBillStreetNum(performPosIdBpRequest.getBillStreetNum());
		addServiceLocation.setAffiliateId(performPosIdBpRequest.getAffiliateId());
		addServiceLocation.setCompanyCode(performPosIdBpRequest.getCompanyCode());
		addServiceLocation.setBrandId(performPosIdBpRequest.getBrandId());
		addServiceLocation.setRecentCallMade(recentCallMade);
		addServiceLocation.setPersonId(personId);
		addServiceLocation.setMessageCode(messageCode);
		logger.debug("inside validatePosId:: inside addservicelocation :: messagecode is :: "+messageCode);
		addServiceLocation.setErrorCode(errorCd);
		//START : OE :Sprint61 :US21009 :Kdeshmu1
		addServiceLocation.setAgentID(performPosIdBpRequest.getAgentID());
		addServiceLocation.setAgentFirstName(oESignupDTO.getAgentFirstName());
		addServiceLocation.setAgentLastName(oESignupDTO.getAgentLastName());
		addServiceLocation.setAgentType(oESignupDTO.getAgentType());
		addServiceLocation.setVendorCode(oESignupDTO.getVendorCode());
		addServiceLocation.setVendorName(oESignupDTO.getVendorName());
		addServiceLocation.setTlpReportApiStatus("");
		if(StringUtils.isNotBlank(oESignupDTO.getErrorCdList())){
			addServiceLocation.setErrorCdList(oESignupDTO.getErrorCdList());
		}else{
		addServiceLocation.setErrorCdList("");}
		addServiceLocation.setSystemNotes("");
		//Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		addServiceLocation.setEntryPoint(performPosIdBpRequest.getEntryPoint());
		addServiceLocation.setPartnerId(performPosIdBpRequest.getPartnerId());
		addServiceLocation.setPartnerDesc(performPosIdBpRequest.getPartnerName());
		addServiceLocation.setLocationId(performPosIdBpRequest.getLocationId());
		addServiceLocation.setLocationDesc(performPosIdBpRequest.getLocationName());
		addServiceLocation.setPageRevisited(performPosIdBpRequest.getPageRevisited());
		addServiceLocation.setProspectId(performPosIdBpRequest.getProspectId());
		
		addServiceLocation.setBypassPosid(performPosIdBpRequest.getBypassPosid());
		addServiceLocation.setIpAddress(performPosIdBpRequest.getIpAddress());
		addServiceLocation.setTabletId(performPosIdBpRequest.getTabletId());
		
		addServiceLocation.setEtfFlag(performPosIdBpRequest.getEtfFlag());
		
		addServiceLocation.setAbandonedEnrollStatFlag(performPosIdBpRequest.getAbandonedEnrollStatFlag());
		///Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		// Start || 13644  Product Backlog Item 13644: Introduce Channel Type in Sales APIs || atiwari || 24/01/2020
		addServiceLocation.setChannel(performPosIdBpRequest.getChannelType());
		// End || 13644  Product Backlog Item 13644: Introduce Channel Type in Sales APIs || atiwari || 24/01/2020
		//START TBD - Set value
		addServiceLocation.setProspectPreapprovedFlag(EMPTY);
		addServiceLocation.setProspectPartnerId(EMPTY);
		addServiceLocation.setBpNameMatchCode(EMPTY);
		addServiceLocation.setDeviceLatitude(EMPTY);
		addServiceLocation.setDeviceLongitude(EMPTY);
		addServiceLocation.setDeviceAccuracy(EMPTY);
		//addServiceLocation.setPendingBalAmount(EMPTY);
		//addServiceLocation.setPastServiceCa(EMPTY);
		addServiceLocation.setKbaSuggestionFlag(oESignupDTO.getKbaSuggestionFlag());
		addServiceLocation.setPosidSNRO(oESignupDTO.getPosidSNRO());
		addServiceLocation.setBpMatchScenarioId(bpMatchDTO.getBpMatchScenarioId());
		if(bpMatchDTO.getPendingBalanceAmount() != null){
			addServiceLocation.setPendingBalAmount(String.valueOf(bpMatchDTO.getPendingBalanceAmount()));
			addServiceLocation.setPastServiceCa(bpMatchDTO.getPastServiceCANumber());
		}else{
			addServiceLocation.setPendingBalAmount(EMPTY);
			addServiceLocation.setPastServiceCa(EMPTY);
		}
		//END TBD - Set value
		//END : OE :Sprint61 :US21009 :Kdeshmu1
		addServiceLocation.setCallExecuted(performPosIdBpRequest.getCallExecuted());
	}



	private void createUpdateServiceLocationRequest (UpdateServiceLocationRequest updateServiceLocation,
			PerformPosIdAndBpMatchRequest performPosIdBpRequest, String personId,String messageCode,
			String errorCd,BPMatchDTO bpMatchDTO,String recentCallMade,OESignupDTO oESignupDTO)
	{
		/*if(StringUtils.isNotBlank(performPosIdBpRequest.getTransactionType()) && 
				performPosIdBpRequest.getTransactionType().equalsIgnoreCase(MVI))
			updateServiceLocation.setServiceRequestTypeCode("N");
		else
			updateServiceLocation.setServiceRequestTypeCode(S);*/
		updateServiceLocation.setServiceRequestTypeCode(performPosIdBpRequest.getTransactionType());
		updateServiceLocation.setServAddressLine2(performPosIdBpRequest.getServStreetAptNum());
		updateServiceLocation.setCompanyCode(performPosIdBpRequest.getCompanyCode());
		updateServiceLocation.setServCity(performPosIdBpRequest.getServCity());
		updateServiceLocation.setServState(performPosIdBpRequest.getServState());
		updateServiceLocation.setServStreetName(performPosIdBpRequest.getServStreetName());
		updateServiceLocation.setServStreetNum(performPosIdBpRequest.getServStreetNum());
		/*updateServiceLocation.setBillAddressLine1(CommonUtil.getAddressLine1(performPosIdBpRequest.getBillStreetNum(),
				performPosIdBpRequest.getBillStreetName()));*/
		/*if(StringUtils.isNotEmpty(performPosIdBpRequest.getBillPOBox())) {
			updateServiceLocation.setBillAddressLine1(performPosIdBpRequest.getBillPOBox());
		}else {
			updateServiceLocation.setBillAddressLine1(CommonUtil.getAddressLine1(performPosIdBpRequest.getBillStreetNum(), performPosIdBpRequest.getBillStreetName()));
		}*/
		updateServiceLocation.setBillPoBox(performPosIdBpRequest.getBillPOBox());
		updateServiceLocation.setBillCity(performPosIdBpRequest.getBillCity());
		updateServiceLocation.setBillState(performPosIdBpRequest.getBillState());
		updateServiceLocation.setBillAddressLine2(performPosIdBpRequest.getBillStreetAptNum());
		updateServiceLocation.setBillStreetAptNum(performPosIdBpRequest.getBillStreetAptNum());
		updateServiceLocation.setServStreetAptNum(performPosIdBpRequest.getServStreetAptNum());
		updateServiceLocation.setBillZipCode(performPosIdBpRequest.getBillZipCode());
		updateServiceLocation.setServZipCode(performPosIdBpRequest.getServZipCode());
		updateServiceLocation.setBillStreetName(performPosIdBpRequest.getBillStreetName());
		updateServiceLocation.setBillStreetNum(performPosIdBpRequest.getBillStreetNum());
		updateServiceLocation.setAffiliateId(performPosIdBpRequest.getAffiliateId());
		updateServiceLocation.setTrackingId(performPosIdBpRequest.getTrackingId());
		updateServiceLocation.setCompanyCode(performPosIdBpRequest.getCompanyCode());
		updateServiceLocation.setBrandId(performPosIdBpRequest.getBrandId());
		logger.debug("inside validatePosId:: inside update request the message code is ::"+messageCode);
		updateServiceLocation.setMessageCode(messageCode);
		if(StringUtils.isNotBlank(errorCd)){
			updateServiceLocation.setErrorCode(errorCd);
		}else{
			updateServiceLocation.setErrorCode("$blank$");}
		updateServiceLocation.setRecentCallMade(recentCallMade);
		//bpmatch response update
		updateServiceLocation.setPendingBalanceFlag(bpMatchDTO.getPendingBalanceFlag());
		updateServiceLocation.setAddressMatchFlag(bpMatchDTO.getAddressMatchFlag());
		updateServiceLocation.setAddressSearchPerformed(bpMatchDTO.getAddressSearchPerformed());
		updateServiceLocation.setBpActiveContract(bpMatchDTO.getBpActiveContract());
		updateServiceLocation.setBpMatchNoCcsResponse(bpMatchDTO.getBpMatchNoCCSResponse());
		updateServiceLocation.setActiveCustomerFlag(bpMatchDTO.getActiveCustomerFlag());
		updateServiceLocation.setMatchedPartnerId(bpMatchDTO.getMatchedPartnerID());
		//START : OE :Sprint61 :US21009 :Kdeshmu1
		updateServiceLocation.setAgentID(performPosIdBpRequest.getAgentID());
		updateServiceLocation.setAgentFirstName(oESignupDTO.getAgentFirstName());
		updateServiceLocation.setAgentLastName(oESignupDTO.getAgentLastName());
		updateServiceLocation.setAgentType(oESignupDTO.getAgentType());
		updateServiceLocation.setVendorCode(oESignupDTO.getVendorCode());
		updateServiceLocation.setVendorName(oESignupDTO.getVendorName());
		updateServiceLocation.setTlpReportApiStatus("");
		if(StringUtils.isNotBlank(oESignupDTO.getErrorCdList())){
			updateServiceLocation.setErrorCdList(oESignupDTO.getErrorCdList());
		}else{
			updateServiceLocation.setErrorCdList("$blank$");}

		updateServiceLocation.setSystemNotes("");
		//END : OE :Sprint61 :US21009 :Kdeshmu1
		///Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		updateServiceLocation.setEntryPoint(performPosIdBpRequest.getEntryPoint());
		updateServiceLocation.setPartnerId(performPosIdBpRequest.getPartnerId());
		updateServiceLocation.setPartnerDesc(performPosIdBpRequest.getPartnerName());
		updateServiceLocation.setLocationId(performPosIdBpRequest.getLocationId());
		updateServiceLocation.setLocationDesc(performPosIdBpRequest.getLocationName());
		updateServiceLocation.setPageRevisited(performPosIdBpRequest.getPageRevisited());
		updateServiceLocation.setProspectId(performPosIdBpRequest.getProspectId());
		
		updateServiceLocation.setBypassPosid(performPosIdBpRequest.getBypassPosid());
		updateServiceLocation.setIpAddress(performPosIdBpRequest.getIpAddress());
		updateServiceLocation.setTabletId(performPosIdBpRequest.getTabletId());
		
		updateServiceLocation.setEtfFlag(performPosIdBpRequest.getEtfFlag());
		
		updateServiceLocation.setAbandonedEnrollStatFlag(performPosIdBpRequest.getAbandonedEnrollStatFlag());
		///Start : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
		// Start || 13644  Product Backlog Item 13644: Introduce Channel Type in Sales APIs || atiwari || 24/01/2020
		updateServiceLocation.setChannel(performPosIdBpRequest.getChannelType());
		// End || 13644  Product Backlog Item 13644: Introduce Channel Type in Sales APIs || atiwari || 24/01/2020
		//START TBD - Set value
		updateServiceLocation.setProspectPreapprovedFlag(oESignupDTO.getProspectPreapprovalStatus());
		updateServiceLocation.setProspectPartnerId(oESignupDTO.getProspectBpNumber());
		updateServiceLocation.setBpNameMatchCode(EMPTY);
		updateServiceLocation.setDeviceLatitude(EMPTY);
		updateServiceLocation.setDeviceLongitude(EMPTY);
		updateServiceLocation.setDeviceAccuracy(EMPTY);
		updateServiceLocation.setKbaSuggestionFlag(EMPTY);
		updateServiceLocation.setPosidSNRO(oESignupDTO.getPosidSNRO());
		if(bpMatchDTO.getPendingBalanceAmount() != null){
			updateServiceLocation.setPendingBalAmount(String.valueOf(bpMatchDTO.getPendingBalanceAmount()));
			updateServiceLocation.setPastServiceCa(bpMatchDTO.getPastServiceCANumber());
		}else{
			updateServiceLocation.setPendingBalAmount(EMPTY);
			updateServiceLocation.setPastServiceCa(EMPTY);
		}

		updateServiceLocation.setBpMatchScenarioId(bpMatchDTO.getBpMatchScenarioId());
		///END : OE : Sprint3 : 13643 - Add Missing Columns to  SLA table :Kdeshmu1
	}


	private void createUpdatePersonRequest(UpdatePersonRequest updatePerson,String posidPii,
			PerformPosIdAndBpMatchRequest performPosIdBpRequest,String personId, String posidStatus, String posIdDate,int retryCount, OESignupDTO oeSignupDTO )
	{
		updatePerson.setIdType(posidPii);
		updatePerson.setPersonId(personId.trim());
		updatePerson.setDob(performPosIdBpRequest.getDob());
		updatePerson.setEmail(performPosIdBpRequest.getEmail());
		updatePerson.setFirstName(performPosIdBpRequest.getFirstName());
		updatePerson.setMaidenName(performPosIdBpRequest.getMaidenName());
		updatePerson.setMiddleName(performPosIdBpRequest.getMiddleName());
		updatePerson.setIdNumber(performPosIdBpRequest.getTokenizedTDL());
		//logic for mktpref
		if(StringUtils.isNotBlank(performPosIdBpRequest.getMktPref()) && performPosIdBpRequest.getMktPref().equalsIgnoreCase("Y"))
			updatePerson.setEmailOptionRps(X_VALUE);
		else
			updatePerson.setEmailOptionRps(O_VALUE);
		updatePerson.setSsn(performPosIdBpRequest.getTokenizedSSN());
		updatePerson.setPosIdStatus(posidStatus);
		updatePerson.setLastName(performPosIdBpRequest.getLastName());

		if(StringUtils.isNotBlank(posIdDate)){
			Date formattedposIdDate  = DateUtil.getDate(posIdDate, MM_dd_yyyy );
			posIdDate=DateUtil.getFormatedDate(formattedposIdDate, MMddyyyy);}

		updatePerson.setPosIdDate(posIdDate);
		updatePerson.setIdStateOfIssue(TX);
		updatePerson.setPhoneNum(performPosIdBpRequest.getPhoneNum());
		updatePerson.setLanguageCode(performPosIdBpRequest.getPreferredLanguage());
		String retryCountStr=null;
		retryCountStr=Integer.toString(retryCount);
		updatePerson.setRetryCount(retryCountStr.trim());
		updatePerson.setTrackingId(performPosIdBpRequest.getTrackingId());
		updatePerson.setCredLevelNum(oeSignupDTO.getPerson().getCreditBucket());
		updatePerson.setCredScoreNum(oeSignupDTO.getPerson().getCreditScore());
		updatePerson.setCredSourceNum(oeSignupDTO.getPerson().getCreditSource());
		updatePerson.setCreditScoreDate(oeSignupDTO.getPerson().getCreditScoreDate());
	}
	
	
	
	public PerformPosIdandBpMatchResponse getInvalidDOBResponse(String affiliateId,String trackingId)
	{
		PerformPosIdandBpMatchResponse validatePosIdResponse= new PerformPosIdandBpMatchResponse();
		validatePosIdResponse.setStatusCode(STATUS_CODE_STOP);	
		validatePosIdResponse.setMessageCode(MESSAGE_CODE_INVALID_DOB);
		validatePosIdResponse.setMessageText(getMessage(MESSAGE_CODE_INVALID_DOB));
		validatePosIdResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		validatePosIdResponse.setResultDescription(RESULT_DESCRIPTION_INVALID_DOB);
		logger.debug("Inside peformPosidAndBpMatch :: tracking is:: "+trackingId+""
				+ ":: affiliateId ::"+affiliateId+" "
						+ "Invalid Age: Prospect must be at least 18 years old but not over 100 years old");
		
		return validatePosIdResponse;
	}
	
	
	/**
	 * This method is to do the referral id check on Client request.
	 * 
	 * @param referralId		Referral Id
	 * @param companyCode		Company Code
	 * @param brandId			Brand Id
	 * @return response ValidateCustReferralIdResponse.
	 */
	public ValidateCustReferralIdResponse validateReferralId(String referralId, String companyCode, String brandId) {

		ValidateCustReferralIdResponse response = null;

		//ValidateCustReferralIdResponse validationResponse = new ValidateCustReferralIdResponse();

		try {
			ValidateReferralIdRequest validateRequest = new ValidateReferralIdRequest();
			validateRequest.setReferralId(referralId);
			validateRequest.setCompanyCode(companyCode);
			validateRequest.setBrandId(brandId);

			response = validationService.validateReferralId(validateRequest);
			logger.debug(response.getCaNumber());
			logger.debug(response.getStatus());

			//JavaBeanUtil.copy(response, validationResponse);
		
		} catch (RemoteException e) {
			logger.error("SERVICE ERROR:validateReferralId:", e);
			response = new ValidateCustReferralIdResponse();
			response.setErrCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setErrMessage(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("SERVICE ERROR:validateReferralId:", e);
			response = new ValidateCustReferralIdResponse();
			response.setErrCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setErrMessage(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;		
	}
	
	public AgentDetailsResponse validateAgentID(String agentID) {

		AgentDetailsResponse response = new AgentDetailsResponse();
		AgentDetailsRequest request = new AgentDetailsRequest();
		request.setAgentID(agentID);
		try {
			response = oeService.getAgentDetails(request);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			logger.error("Exception in getting Agent Details: ", e);
		}
		logger.info("getAgentDetailsResponse : ResultCode : "+response.getResultCode());
		return response;
	}
	
	public PerformPosIdandBpMatchResponse getInvalidAgentIDResponse(String agentID,String trackingId)
	{
		PerformPosIdandBpMatchResponse validatePosIdResponse= new PerformPosIdandBpMatchResponse();
		validatePosIdResponse.setStatusCode(STATUS_CODE_STOP);	
		validatePosIdResponse.setMessageCode(MESSAGE_CODE_INVALID_AGENT_ID);
		validatePosIdResponse.setMessageText(MESSAGE_CODE_INVALID_AGENT_ID);
		validatePosIdResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		validatePosIdResponse.setResultDescription(MESSAGE_CODE_INVALID_AGENT_ID);
		logger.info("Inside peformPosidAndBpMatch :: tracking is:: "+trackingId+""
				+ ":: agentID ::"+agentID+" "
						+ "Agent ID is not valid");
		
		return validatePosIdResponse;
	}
	
	public boolean isPosidValidated(ValidatePosIdKBAResponse validatePosIdKBAResponse) {
		boolean status = false;
		
		if( validatePosIdKBAResponse != null && (StringUtils.isNotEmpty(validatePosIdKBAResponse.getExSsnVerifydate())
				||StringUtils.isNotEmpty(validatePosIdKBAResponse.getExDlVerifydate())) 
				&& (!StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExSsnVerifydate(), POSID_BLANK_DATE) 
						|| !StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExDlVerifydate(), POSID_BLANK_DATE) )) {
			status = true;
		}			
		return status;
	}
	
	public ValidatePosIdKBAResponse validatePosIdOldCSSCall(PerformPosIdAndBpMatchRequest performPosIdBpRequest) throws Exception  
	{
		ValidatePosIdKBAResponse validatePosIdKBAResponse = new ValidatePosIdKBAResponse();		
		ValidatePosIdRequest validatePosIdReq= validateRequestHandler.createPosIdRequest(performPosIdBpRequest.getDobForPosId(), performPosIdBpRequest.getTokenizedTDL(),
				performPosIdBpRequest.getCompanyCode(),
				performPosIdBpRequest.getMaidenName(),performPosIdBpRequest.getFirstName(), performPosIdBpRequest.getLastName(),
				performPosIdBpRequest.getTokenizedSSN(), performPosIdBpRequest.getMiddleName());
		ValidatePosIdResponse validatePosIdResponse=validationService.validatePosId(validatePosIdReq);
		validatePosIdKBAResponse.setExSsnVerifydate(POSID_BLANK_DATE);
		validatePosIdKBAResponse.setExDlVerifydate(POSID_BLANK_DATE);
		validatePosIdKBAResponse.setStrErroCode(validatePosIdResponse.getStrErroCode());
		validatePosIdKBAResponse.setStrErroMessage(validatePosIdResponse.getStrErroMessage());
		if(StringUtils.equalsIgnoreCase(validatePosIdResponse.getExIsValidSsn(), FLAG_X)){
			String validatedDate = DateUtil.getFormattedDate(Constants.RESPONSE_DATE_FORMAT,DATE_FORMAT, validatePosIdResponse.getExSsnVerifydate());
			validatePosIdKBAResponse.setExSsnVerifydate(validatedDate);
		}
		if(StringUtils.equalsIgnoreCase(validatePosIdResponse.getExIsValidDl(), FLAG_X)){
			String validatedDate = DateUtil.getFormattedDate(Constants.RESPONSE_DATE_FORMAT,DATE_FORMAT, validatePosIdResponse.getExDlVerifydate());
			validatePosIdKBAResponse.setExDlVerifydate(validatedDate);
		}
		
		return validatePosIdKBAResponse;
	}
	
	private Map<String,Object> processBPMatch(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
												OESignupDTO oESignupDTO, 
												ServiceLocationResponse serviceLoationResponse,
												PerformPosIdandBpMatchResponse response,
												String errorCd,
												String messageCode,
												LinkedHashSet<String> serviceLocationResponseerrorList){
	
		Map<String,Object> performBpMatchResponse=new HashMap<String, Object>();
		performBpMatchResponse=oeBO.performBpMatch(response,errorCd,messageCode, performPosIdBpRequest.getFirstName(),
				performPosIdBpRequest.getLastName(),performPosIdBpRequest.getTokenizedTDL(), performPosIdBpRequest.getMaidenName(),
				performPosIdBpRequest.getCompanyCode(), performPosIdBpRequest.getServStreetAptNum(), performPosIdBpRequest.getServCity(),
				performPosIdBpRequest.getServState(), performPosIdBpRequest.getServStreetName(), performPosIdBpRequest.getServStreetNum(),
				performPosIdBpRequest.getServZipCode(), performPosIdBpRequest.getTokenizedSSN(),performPosIdBpRequest.getBrandId());	
	
		//response=(PerformPosIdandBpMatchResponse)performBpMatchResponse.get("response");
		messageCode=(String)performBpMatchResponse.get("messageCode");
		errorCd=(String)performBpMatchResponse.get("errorCd");
		if(StringUtils.isEmpty(errorCd)){
			serviceLocationResponseerrorList.remove(BP_RESTRICT);
			serviceLocationResponseerrorList.remove(BPSD);
				
			
		}else{
			
			LinkedHashSet<String> errorCdSet = (LinkedHashSet<String>) performBpMatchResponse.get("errorCdSet");
			serviceLocationResponseerrorList.addAll(errorCdSet);
		}
		return performBpMatchResponse;
	}
	
	private String processPerformPosidBPMatchAfterMaxRetryAllowed
					(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
						PerformPosIdandBpMatchResponse response,
						int retryCount)
			{
		logger.debug("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
				+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: retry count is ::"
				+ ""+retryCount+" so POSID_FAIL_MAX message set");
		//need to change toggle name
		

		response.setStatusCode(STATUS_CODE_STOP);
		String messageCode=POSID_FAIL_MAX;
		response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
		String errorCd=POSIDHOLD;			
		response.setMessageCode(messageCode);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setRetryCount(Integer.toString(retryCount));
		response.setTrackingId(performPosIdBpRequest.getTrackingId());
		response.setGuid(performPosIdBpRequest.getGuid());
		logger.info("Settting message for hold scenario 3 "+response.getMessageText());
		return errorCd;	
	}
	
	private String processPosidPassResponse(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
			OESignupDTO oESignupDTO, 
			ServiceLocationResponse serviceLoationResponse,
			PerformPosIdandBpMatchResponse response,
			String errorCd,
			String messageCode,
			LinkedHashSet<String> serviceLocationResponseerrorList, 
			BPMatchDTO bpMatchDTO,
			ValidatePosIdKBAResponse validatePosIdKBAResponse){
		
		logger.debug("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
				+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: POSID SUCCESSFULLY CONDUCTED");
		String posidStatus=POSID_FLAG_YES;
		String posIdDate = null;
		String posidPii = null;
		if(!StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExDlVerifydate(), POSID_BLANK_DATE))
		{
			logger.debug("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
					+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: POSID conducted on Driver license");					
			posIdDate = DateUtil.getFormattedDate(DATE_FORMAT, Constants.RESPONSE_DATE_FORMAT, validatePosIdKBAResponse.getExDlVerifydate());					
			response.setPosidDLDate(posIdDate);
			posidPii=DL;
		}
		else if(!StringUtils.equalsIgnoreCase(validatePosIdKBAResponse.getExSsnVerifydate(), POSID_BLANK_DATE))
		{
			logger.debug("inside validatePosId::affiliate Id : "+performPosIdBpRequest.getAffiliateId() +""
					+ ":: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: POSID conducted on SSN");
			posIdDate = DateUtil.getFormattedDate(DATE_FORMAT, Constants.RESPONSE_DATE_FORMAT, validatePosIdKBAResponse.getExSsnVerifydate());					
			response.setPosidSSNDate(posIdDate);
			posidPii=SSN;
		}
		String recentCallMade=RECENT_CALL_MADE_BP_MATCH;
		/*
		 * Step 6: Make BpMatch call
		 */
		
						
		Map<String,Object> performBpMatchResponse = processBPMatch(performPosIdBpRequest, oESignupDTO, 
				serviceLoationResponse, response,errorCd,
				messageCode, serviceLocationResponseerrorList );
		

		response=(PerformPosIdandBpMatchResponse)performBpMatchResponse.get("response");
		messageCode=(String)performBpMatchResponse.get("messageCode");
		errorCd=((String)performBpMatchResponse.get("errorCd")).equals(CURRENT_CUSTOMER)?StringUtils.EMPTY:(String)performBpMatchResponse.get("errorCd");	
		BPMatchDTO returnedBpMatchDTO=(BPMatchDTO)performBpMatchResponse.get("bpMatchDTO");
		BeanUtils.copyProperties(returnedBpMatchDTO, bpMatchDTO);

		logger.debug("inside validatePosId:: status code after bpmatch call is:: "+response.getStatusCode());
		logger.debug("inside validatePosId:: errorcd after bpmatch is :: "+response.getErrorCode());
		logger.debug("inside validatePosId:: messagecode is after bpmatch ::"+response.getMessageCode());
		oESignupDTO.setRecentCallMade(recentCallMade);
		oESignupDTO.setPosIdDate(posIdDate);
		oESignupDTO.setPosidPii(posidPii);
		oESignupDTO.setPosidStatus(posidStatus);
		return errorCd;
	}
	
	private String processPosidFailedResponse(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
			OESignupDTO oESignupDTO, 
			ServiceLocationResponse serviceLoationResponse,
			PerformPosIdandBpMatchResponse response,
			LinkedHashSet<String> serviceLocationResponseerrorList, 
			BPMatchDTO bpMatchDTO,
			boolean posidHoldAllowedForAffilate,
			boolean posidHoldAllowed, 
			int retryCount)
	{
		String posidStatus = null;
		String messageCode;
		String errorCd;
		if(retryCount<=2 && !StringUtils.equalsIgnoreCase(performPosIdBpRequest.getNoid(), FLAG_TRUE)){
			response.setStatusCode(STATUS_CODE_ASK);
			messageCode=POSID_FAIL;
			response.setMessageCode(messageCode);
			response.setMessageText(getMessage(POSID_FAIL_MSG_TXT));
			posidStatus=POSID_FLAG_NO;
			errorCd=POSIDHOLD;
			serviceLocationResponseerrorList.remove(BP_RESTRICT);
			serviceLocationResponseerrorList.remove(BPSD);
			serviceLocationResponseerrorList.add(errorCd);
		}
		else{
			if(posidHoldAllowedForAffilate || posidHoldAllowed || StringUtils.equalsIgnoreCase(performPosIdBpRequest.getNoid(), FLAG_TRUE)){
				errorCd=POSIDHOLD;
				serviceLocationResponseerrorList.add(errorCd);
				messageCode = POSIDHOLD;
				Map<String,Object> performBpMatchResponse = processBPMatch(performPosIdBpRequest, 
						oESignupDTO, serviceLoationResponse, response,
						errorCd,messageCode, serviceLocationResponseerrorList );

				response=(PerformPosIdandBpMatchResponse)performBpMatchResponse.get("response");
				messageCode=(String)performBpMatchResponse.get("messageCode");
				String bpMatchErrorCd=(String)performBpMatchResponse.get("errorCd");
				if(StringUtils.isNotEmpty(bpMatchErrorCd)) {
					errorCd = bpMatchErrorCd;
				}else{
					errorCd=POSIDHOLD;
				}
				BPMatchDTO returnedBpMatchDTO=(BPMatchDTO)performBpMatchResponse.get("bpMatchDTO");
				BeanUtils.copyProperties(returnedBpMatchDTO, bpMatchDTO);
				
				setMessageCodeForBPMatchScenario(messageCode,response,performPosIdBpRequest);
			
				//response.setMessageCode(messageCode);
				logger.debug("inside validatePosId failed :: status code after bpmatch call is:: "+response.getStatusCode());
				logger.debug("inside validatePosId failed:: errorcd after bpmatch is :: "+response.getErrorCode());
				logger.debug("inside validatePosId failed:: messagecode is after bpmatch ::"+response.getMessageCode());
				
			}else{
				response.setStatusCode(STATUS_CODE_STOP);
				messageCode=POSID_FAIL_MAX;
				response.setMessageCode(messageCode);
				response.setMessageText(getMessage(POSID_FAIL_MAX_MSG_TXT));
				errorCd=POSIDHOLD;
				serviceLocationResponseerrorList.remove(BP_RESTRICT);
				serviceLocationResponseerrorList.remove(BPSD);
				serviceLocationResponseerrorList.add(errorCd);
			}
		}
		oESignupDTO.setPosidStatus(posidStatus);
		return errorCd;
	}
	
	private void createPersonAndServiceLocationRecordForPerformPosidBPMatchProcess(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
			OESignupDTO oESignupDTO, 
			ServiceLocationResponse serviceLoationResponse,
			PerformPosIdandBpMatchResponse response,
			String errorCd,
			String messageCode,
			LinkedHashSet<String> serviceLocationResponseerrorList, 
			BPMatchDTO bpMatchDTO,
			ValidatePosIdKBAResponse validatePosIdKBAResponse,
			String posidPii,
			String posIdDate,
			String posidStatus,
			String recentCallMade) throws OEException{
		try{//making addperson call if its 1st try
			logger.debug("inside com.multibrand.bo:: validatePosId ::making add person call and retrycount is 0");
			AddPersonRequest addPersonRequest= new AddPersonRequest();
			createAddPersonRequest(addPersonRequest, performPosIdBpRequest,posidPii,posIdDate,posidStatus);
			String personId=oeBO.addPerson(addPersonRequest);
			logger.debug("inside validatePosId:: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +":: persondId after addperson call is :: "+personId);
			
			oESignupDTO.getPerson().setPersonID(personId);
			
			/*
			 * Make addservicelocation if addperson was successful
			 */
			if(StringUtils.isNotBlank(personId))
			{
				logger.debug("inside validatePosId:: Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +":: making addservicelocation call now");
				AddServiceLocationRequest addServiceLocation =new AddServiceLocationRequest();						
				String guid= addServiceLocation.getGuid();											
				createAddServiceLocationRequest(addServiceLocation, performPosIdBpRequest, personId, messageCode, errorCd,recentCallMade,oESignupDTO, bpMatchDTO);
				performPosIdBpRequest.setTrackingId(oeBO.addServiceLocation(addServiceLocation));
				oESignupDTO.setTrackingNumber(performPosIdBpRequest.getTrackingId());
				//checking if addServiceLocation call was successful
				if(StringUtils.isNotBlank(performPosIdBpRequest.getTrackingId()))
				{
					response.setTrackingId(performPosIdBpRequest.getTrackingId());
					response.setGuid(guid);
					logger.debug("inside validatePosId:: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +"::"
							+ " tracking id after servicelocation call is :: "+performPosIdBpRequest.getTrackingId());
				}
				else
				{
					logger.error("inside com.multibrand.bo:: validatePosId :: addServiceLocation call failed");
					//response.setStatusCode(STATUS_CODE_STOP);
					//response.setResultDescription("addServiceLocation call failed");
					throw new OEException();
				}
				logger.debug("inside validatePosId:: affiliate Id : "+performPosIdBpRequest.getAffiliateId() +":: "
						+ "tracking id after servicelocation call is :: "+performPosIdBpRequest.getTrackingId());
			}
			else
			{
				logger.debug("inside validatePosId::Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" ::"
						+ " affiliate Id : "+performPosIdBpRequest.getAffiliateId() +":: addperson call failed ");
				// error in person call so redirect with errr message
				throw new OEException();
			}
		}
		catch(Exception e)
		{// when addperson or addservicelocation get error}
			//response.setStatusCode(STATUS_CODE_STOP);
		//	response.setResultDescription("Java exception making Database call for addPerson and addServiceLocation with exception ::"+e.getMessage());
			logger.error("Tracking Number ::"+performPosIdBpRequest.getTrackingId()+" :: affiliate Id : "
					+ ""+performPosIdBpRequest.getAffiliateId() +"::Exception while making addperson and addserviceLocation call :: ", e);
			throw new OEException();
		}
	}
	
	/**
	 * @author Kdeshmu1
	 * @param messageCode
	 * @param response
	 * @param performPosIdBpRequest
	 * @return
	 */
	private void setMessageCodeForBPMatchScenario(String messageCode,PerformPosIdandBpMatchResponse response,PerformPosIdAndBpMatchRequest performPosIdBpRequest)
	{
		if(StringUtils.equals(messageCode, POSIDHOLD)){
			response.setMessageCode(POSIDHOLD);
			response.setMessageText(msgSource.getMessage(POSID_HOLD_MSG_TXT,
					new String[] {CommonUtil.getCompanyName(performPosIdBpRequest.getBrandId(),performPosIdBpRequest.getCompanyCode())},
					CommonUtil.localeCode(performPosIdBpRequest.getLanguageCode())));
		}
		else if(StringUtils.equals(messageCode, PAST_BALANCE)){
			response.setMessageCode(POSID_PASTDUE);
			response.setMessageText(msgSource.getMessage(POSID_HOLD_MSG_TXT,
					new String[] {CommonUtil.getCompanyName(performPosIdBpRequest.getBrandId(),performPosIdBpRequest.getCompanyCode())},
					CommonUtil.localeCode(performPosIdBpRequest.getLanguageCode())).concat( msgSource.getMessage(POSID_PASTDUE_MSG_TXT,
							new String[] {CommonUtil.getCompanyName(performPosIdBpRequest.getBrandId(),performPosIdBpRequest.getCompanyCode())},
							CommonUtil.localeCode(performPosIdBpRequest.getLanguageCode()))));
		}
		else if(StringUtils.equals(messageCode, PAST_SERVICE_HISTORY)) {
			response.setMessageCode(POSID_PASTSERVICE);
			String pastHistoryAddress = "";
			if(null!=response){
				
				//pass past history address in message text
				 pastHistoryAddress=CommonUtil.getCompleteAddress(response.getExistingAptNum(),
						 CommonUtil.stripStreetNum(response.getExistingStreetAddress()),
						 CommonUtil.stripStreetName(response.getExistingStreetAddress()),
						response.getExistingCity(),
						response.getExistingZip());
			}
			response.setMessageText(msgSource.getMessage(POSID_HOLD_MSG_TXT,
					new String[] {CommonUtil.getCompanyName(performPosIdBpRequest.getBrandId(),performPosIdBpRequest.getCompanyCode())},
					CommonUtil.localeCode(performPosIdBpRequest.getLanguageCode())).concat(msgSource.getMessage(POSID_PASTSERVICE_MSG_TXT)+" "+pastHistoryAddress));
			
		}
		
	}
	
	/**
	 * Start | PBI 77693 | MBAR: Sprint 21 - EquiFax POSID REST IMPL | Jyothi | 11/23/2020
	 */
	public ValidatePosIdKBAResponse validatePOSIdwithKBA(ValidatePosIdKBARequest vaidationRequest) {
		ValidatePosIdKBAResponse validatePosIdKBAResponse = new ValidatePosIdKBAResponse();
		try{
			validatePosIdKBAResponse = validationService.validatePosIdWihKBA(vaidationRequest);
		}catch(Exception e){
			logger.debug("caught Exception in ValidationBO::validatePOSIdwithKBA(..)"+e);
		}
		return validatePosIdKBAResponse;
	}
	public ValidatePosIdKBAResponse callValidateMethod(PerformPosIdAndBpMatchRequest performPosIdBpRequest,ValidatePosIdKBAResponse validatePosIdKBAResponse) throws Exception {
	if(! StringUtils.equalsIgnoreCase(performPosIdBpRequest.getNoid(), FLAG_TRUE)){
		
		boolean isNewPosidCallEnabled = togglzUtil.getFeatureStatusFromTogglzByBrandId(TOGGLZ_FEATURE_NEW_POSID_CALL,performPosIdBpRequest.getCompanyCode(), performPosIdBpRequest.getBrandId());
		if(isNewPosidCallEnabled){
			ValidatePosIdKBARequest validatePosIdReq= validateRequestHandler.createPoisdWithKBARequest(performPosIdBpRequest);
			validatePosIdKBAResponse=validationService.validatePosIdWihKBA(validatePosIdReq);
		} else{
			validatePosIdKBAResponse= validatePosIdOldCSSCall(performPosIdBpRequest);
	}

}
	return validatePosIdKBAResponse;
	}
	
	public ValidateThirdPartyReceiptResponse validateThirdPartyReceipt(ValidateThirdPartyReceipt validateThirdPartyReceipt) {
		
		ValidateThirdPartyReceiptResponse response = new ValidateThirdPartyReceiptResponse();
		ValidatePaymentReceiptResponse validatePaymentReceiptResponse = null;
		try {
			
			ValidatePaymentReceiptRequest validatePaymentReceiptRequest = new ValidatePaymentReceiptRequest();
			validatePaymentReceiptRequest.setContractAccountNumber(validateThirdPartyReceipt.getContractAccountNumber());
			validatePaymentReceiptRequest.setPaymentAmount(validateThirdPartyReceipt.getPaymentAmount());
			validatePaymentReceiptRequest.setCompanyCode(validateThirdPartyReceipt.getCompanyCode());
			validatePaymentReceiptRequest.setCheckDigit("");
			validatePaymentReceiptRequest.setReceiptNumber(ZIRTUE_RECEIPT_NUMBER);
			
			Date date = new Date();
			String formattedPaymentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			validatePaymentReceiptRequest.setPaymentDate(formattedPaymentDate);
			
			validatePaymentReceiptResponse = validationService.validateThirdPartyReceipt(validatePaymentReceiptRequest);
			
			if(StringUtils.isNotBlank(validatePaymentReceiptResponse.getErrorCode())) {
				response.setErrorCode(validatePaymentReceiptResponse.getErrorCode());
				response.setErrorMessage(validatePaymentReceiptResponse.getErrorMessage());
			}
			
			else {
				JavaBeanUtil.copy(validatePaymentReceiptResponse, response);
			}
		}
		
		catch (RemoteException e) {
			logger.error("RemoteException::::: ValidationBO.validateThirdPartyReceipt()::::");
			response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setErrorMessage(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			logger.error("Exception::::: ValidationBO.validateThirdPartyReceipt()::::");
			response.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setErrorMessage(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
		

	}
	
	public void updateSLTable(PerformPosIdAndBpMatchRequest performPosIdBpRequest,
			OESignupDTO oESignupDTO, 
			ServiceLocationResponse serviceLoationResponse,
			PerformPosIdandBpMatchResponse response,
			String errorCd,
			String messageCode,
			LinkedHashSet<String> serviceLocationResponseerrorList, 
			BPMatchDTO bpMatchDTO,
			ValidatePosIdKBAResponse validatePosIdKBAResponse,
			String posidPii,
			String posIdDate,
			String posidStatus,
			String recentCallMade,
			int retryCount,
			String errorCodeFromAPI,
			String errorCodeFromDB) throws OEException{
		String personId = null;	
	if (retryCount==0)
	{
		createPersonAndServiceLocationRecordForPerformPosidBPMatchProcess(performPosIdBpRequest, oESignupDTO, 
				serviceLoationResponse, response, errorCodeFromAPI, messageCode, serviceLocationResponseerrorList,
				bpMatchDTO, validatePosIdKBAResponse, posidPii, posIdDate, posidStatus, recentCallMade);
		 personId = oESignupDTO.getPerson().getPersonID();
		
	}else{
		response.setGuid(serviceLoationResponse.getGuid());
		personId = serviceLoationResponse.getPersonId();
	}

	response.setTrackingId(performPosIdBpRequest.getTrackingId());

	logger.debug(performPosIdBpRequest.getTrackingId(), "inside validatePosId:: Tracking Number : : {}", performPosIdBpRequest.getAffiliateId(),": : affiliate Id : {}",":::: existing tracking number and  making updateperson call");

	//Checking if person Id is present then only make updatePerson call
	if(StringUtils.isNotBlank(personId))
	{
		logger.info("inside com.multibrand.bo:: validatePosId ::Person Id available so making updatePerson call");
		UpdatePersonRequest updatePerson = new UpdatePersonRequest();
		createUpdatePersonRequest(updatePerson, posidPii, performPosIdBpRequest, personId, posidStatus, posIdDate, retryCount, oESignupDTO);

		String updatePersonErrorCode=oeBO.updatePerson(updatePerson);
		logger.info(performPosIdBpRequest.getTrackingId(), "inside validatePosId:: Tracking Number:: {}", performPosIdBpRequest.getAffiliateId(),":: affiliate Id: {}",updatePersonErrorCode,"errorCode is {}");
	}
	else{
		logger.debug(performPosIdBpRequest.getTrackingId(), "inside validatePosId:: Tracking Number : : {}", performPosIdBpRequest.getAffiliateId(),": : affiliate Id : {}",":: Person Id was not present so skipped Update Person Call");
	}
	logger.debug(performPosIdBpRequest.getTrackingId(), "inside validatePosId:: Tracking Number :: {}", performPosIdBpRequest.getAffiliateId(),":: affiliate Id : {}",":: making updtaeservicelocation call now{}");
	//Making Update Servicelocation call now
	UpdateServiceLocationRequest updateServiceLocation= new UpdateServiceLocationRequest();
	if(null!=serviceLoationResponse && StringUtils.isNotBlank(serviceLoationResponse.getCallExecutedFromDB()))
		updateServiceLocation.setCallExecuted(CommonUtil.getPipeSeperatedCallExecutedParamForDB(performPosIdBpRequest.getCallExecuted(), serviceLoationResponse.getCallExecutedFromDB()));
	
	if((StringUtils.isBlank(errorCodeFromAPI)) && StringUtils.equalsIgnoreCase(POSIDHOLD, errorCodeFromDB)){
		errorCodeFromAPI = "";
	}else if((StringUtils.isBlank(errorCodeFromAPI))){
		errorCodeFromAPI = errorCodeFromDB;
	}
	createUpdateServiceLocationRequest(updateServiceLocation, performPosIdBpRequest, personId,
			messageCode, errorCodeFromAPI,bpMatchDTO,recentCallMade,oESignupDTO);
	String updateSrvLocationErrorCode=oeBO.updateServiceLocation(updateServiceLocation);
	logger.debug(performPosIdBpRequest.getTrackingId(), "inside validatePosId:: Tracking Number :: {}", performPosIdBpRequest.getAffiliateId(),":: affiliate Id : {}",updateSrvLocationErrorCode,"errorCode from updateservicelocation call is ::{}");
	logger.debug(" END *******ValidationBO:: validatePosID API**********");
}

}