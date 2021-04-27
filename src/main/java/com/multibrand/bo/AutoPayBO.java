package com.multibrand.bo;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.domain.AutoPayBankRequest;
import com.multibrand.domain.BankDetailsValidationRequest;
import com.multibrand.domain.BankDetailsValidationResponse;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.ValidateCCRequest;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.EmailHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.PaymentService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.AutoPayInfoRequest;
import com.multibrand.vo.request.autopay.AutoPayRequest;
import com.multibrand.vo.response.AutoPayBankResponse;
import com.multibrand.vo.response.AutoPayCCResponse;
import com.multibrand.vo.response.DeEnrollResponse;
import com.multibrand.vo.response.ValidateBankResponse;
import com.multibrand.vo.response.ValidateCCResponse;
import com.multibrand.vo.response.billingResponse.AutoPayDetails;
import com.multibrand.vo.response.billingResponse.AutoPayInfoResponse;


/**
 * 
 * @author ahanda1
 *
 */
@Component
public class AutoPayBO extends BaseAbstractService implements Constants{

	
	@Autowired
	private PaymentService paymentService;
			
	@Autowired
	private TOSService tosService;
	
	@Autowired
	private BillingBO billingBO;
	
	private static final String ACCOUNT_NUMBER_LABEL ="User with account number ";
	private static final Map<String, String> deEnrollMap = new HashMap<>();

	static 
	{
		deEnrollMap.put("01", "Invalid Contract Account");
		deEnrollMap.put("02", "Autopay de-enroll failed");
		deEnrollMap.put("03", "Invalid Payment Method");
	}
	
	
	
	
    
	public ValidateBankResponse validateBankDetails(String ca, String bankAccountNumber, String bankRoutingNumber, String companyCode, String sessionId, String brandName){
		
		ValidateBankResponse validateBankResponse = new ValidateBankResponse();
		
		logger.info("AutoPayBO.validateBankDetails :: START");
		com.multibrand.domain.ValidateBankResponse response=null;
		
		try {
			 response = paymentService.validateBankDetails(ca, bankAccountNumber, bankRoutingNumber, companyCode, sessionId ,brandName);
			 			
			 logger.info("Redbull Service Response :: {}" , response);
			 
			 if(response.getStrYCODE()!=null)validateBankResponse.setStrYCODE(response.getStrYCODE());
				
				if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")) {
					validateBankResponse.setErrorCode(response.getErrorCode());
					if(response.getErrorMessage()!=null)validateBankResponse.setErrorMessage(response.getErrorMessage());
					validateBankResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					if(response.getErrorMessage()!=null)validateBankResponse.setResultDescription(response.getErrorMessage());
				}
			} catch (Exception e) {

				validateBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				validateBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), validateBankResponse);
				
			}
					
		logger.info("AutoPayBO.validateBankDetails :: END");
		return validateBankResponse;
	}
	
	
	public AutoPayBankResponse submitBankAutoPay(AutoPayRequest autoPayRequest, String sessionId, HttpHeaders httpHeaders) {
		
		logger.info("AutoPayBO.submitBankAutoPay :: START");
		AutoPayBankResponse autoPayBankResponse = new AutoPayBankResponse();
		
		AutoPayBankRequest request = new AutoPayBankRequest();
		request.setStrBankAccNumber(autoPayRequest.getBankAccountNumber());
		request.setStrBankRoutingNumber(autoPayRequest.getBankRoutingNumber());
		request.setStrCANumber(autoPayRequest.getAccountNumber());
		request.setStrCompanyCode(autoPayRequest.getCompanyCode());
		String maskBankAcctNumber = CommonUtil.maskBankAccountNo(autoPayRequest.getBankAccountNumber());
		String bankLastDigits = maskBankAcctNumber.substring(maskBankAcctNumber.length()-3, maskBankAcctNumber.length());
		
		try {
			
			printHeaders(httpHeaders);
			
			BankDetailsValidationRequest bankDetailsValidationRequest = createValidateBankDetailsGIACTRequest(autoPayRequest);
			
			BankDetailsValidationResponse validateBankResp = paymentService.validateBankDetailsGIACT(bankDetailsValidationRequest);
			
			 logger.info("Sending mail for auto pay enrollment successful:{}", validateBankResp);		
			
			 if(validateBankResp!=null && validateBankResp.getExReturnCode()!=null 
						&&  STATUS_CODE_CONTINUE.equalsIgnoreCase(validateBankResp.getExReturnCode())) {
			com.multibrand.domain.AutoPayBankResponse response = paymentService.submitBankAutoPay(request, autoPayRequest.getCompanyCode(), sessionId,autoPayRequest.getBrandName());
			
			if(response.getStrStatus()!= null)autoPayBankResponse.setStrStatus(response.getStrStatus());
			
			handleBankAutoPayResponse(autoPayRequest, autoPayBankResponse, maskBankAcctNumber, response);
			
			sendCirroBankAutoPayConfirmationEmail(autoPayRequest);
		 } else if(validateBankResp!=null && validateBankResp.getExReturnCode().equalsIgnoreCase(ERROR_01)){
				autoPayBankResponse.setResultCode(GIACT_ERROR_01);
				autoPayBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}else if(validateBankResp!=null &&validateBankResp.getErrorCode().equalsIgnoreCase(ERROR_02)){
				autoPayBankResponse.setResultCode(GIACT_ERROR_02);
				autoPayBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}else if(validateBankResp!=null && validateBankResp.getErrorCode().equalsIgnoreCase(ERROR_03)){
				autoPayBankResponse.setResultCode(GIACT_ERROR_03);
				autoPayBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}else{
				autoPayBankResponse.setResultCode(BANK_AUTOPAY_INVALID_BANK_ACCOUNT);
				autoPayBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
		} catch (Exception e) {

			autoPayBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayBankResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), autoPayBankResponse);
			
		}
		
		writeAutoPayBankContactLog(autoPayRequest, autoPayBankResponse, bankLastDigits);	
		
		logger.info("AutoPayBO.submitBankAutoPay :: END");
		return autoPayBankResponse;
		
	}


	/**
	 * @param autoPayRequest
	 * @param autoPayBankResponse
	 * @param maskBankAcctNumber
	 * @param response
	 * @throws Exception
	 */
	public void handleBankAutoPayResponse(AutoPayRequest autoPayRequest, AutoPayBankResponse autoPayBankResponse,
			String maskBankAcctNumber, com.multibrand.domain.AutoPayBankResponse response) throws Exception {
		if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
			autoPayBankResponse.setResultCode(RESULT_CODE_CCS_ERROR);
			if(response.getErrorMessage()!=null)autoPayBankResponse.setResultDescription(response.getErrorMessage());
			autoPayBankResponse.setErrorCode(response.getErrorCode());
			if(response.getErrorMessage()!=null)autoPayBankResponse.setErrorMessage(response.getErrorMessage());
		} else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(autoPayRequest.getCompanyCode()) && !CIRRO_BRAND_NAME.equalsIgnoreCase(autoPayRequest.getBrandName())
				&& !CommonUtil.checkIfGMEPrepay(autoPayRequest.getCompanyCode(), autoPayRequest.getBrandName(), autoPayRequest.getEmailTypeId())) {
			
		    logger.info("Sending mail for auto pay enrollment successful");		
		    
			HashMap<String, String> templateProps = new HashMap<>();
				
			templateProps.put(BANK_ACCOUNT_NUMBER, maskBankAcctNumber);
			templateProps.put(BANK_ROUTING_NUMBER, autoPayRequest.getBankRoutingNumber());
			
			if(StringUtils.isBlank(autoPayRequest.getLanguageCode())|| autoPayRequest.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN)){
				templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK);
				logger.info("Sending mail for successful auto pay enrollment EN");
				EmailHelper.sendMailWithBCC(autoPayRequest.getEmail(),this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTO_PAY_ENROLL_CONF_BANK_EN, templateProps, autoPayRequest.getCompanyCode());
			} else{
				templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK_ES);
				logger.info("Sending mail for successful auto pay enrollment ES");
				EmailHelper.sendMailWithBCC(autoPayRequest.getEmail(),this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTO_PAY_ENROLL_CONF_BANK_ES, templateProps, autoPayRequest.getCompanyCode());
			}
			
		}
	}


	/**
	 * @param httpHeaders
	 */
	public void printHeaders(HttpHeaders httpHeaders) {
		MultivaluedMap<String, String> requestHeadersMap = httpHeaders.getRequestHeaders();
		
		logger.info("submitBankAutoPay(...) >>>>>>>>>>>>>>> Headers <<<<<<<<<<<<<<");
		if(null != requestHeadersMap && requestHeadersMap.size() > 0) {
			for(String headerName : requestHeadersMap.keySet()) {
				logger.info("submitBankAutoPay(...) >> Header Name [{}] Header Value[{}]", headerName, requestHeadersMap.getFirst(headerName));
			}
		}
	}


	private void sendCirroBankAutoPayConfirmationEmail(AutoPayRequest autoPayRequest) {
		if (CIRRO_COMPANY_CODE.equalsIgnoreCase(autoPayRequest.getCompanyCode()) && CIRRO_BRAND_NAME.equalsIgnoreCase(autoPayRequest.getBrandName())){
			
			HashMap<String, String> templateProperties = new HashMap<>();

			templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(autoPayRequest.getCaName()));


			try {

			Boolean status = EmailHelper.sendMail( autoPayRequest.getEmail() ,"", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN, templateProperties, autoPayRequest.getCompanyCode());

			logger.info("Auto Pay Update Email sent status : {}" , status);

			} catch (Exception e) {
		    logger.info("Exception in sending Auto Pay Update Email:{} ",e.getMessage() );

			} 
			
		 }
	}


	private void writeAutoPayBankContactLog(AutoPayRequest autoPayRequest, AutoPayBankResponse autoPayBankResponse,
			String bankLastDigits) {
		if(autoPayBankResponse.getResultCode()!=null && autoPayRequest.getBpNumber()!=null && autoPayRequest.getSource()!=null &&
				(autoPayBankResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)||autoPayBankResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE))&& 
				GME_RES_COMPANY_CODE.equalsIgnoreCase(autoPayRequest.getCompanyCode())&&autoPayRequest.getSource().equalsIgnoreCase(MOBILE))
		{
			logger.info("Inside submitBankAutoPay:updateContactLog(...) block - in AutoPayBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(autoPayRequest.getBpNumber());
			cssUpdateLogRequest.setContractAccountNumber(autoPayRequest.getAccountNumber());
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_BANK_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_ENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines(ACCOUNT_NUMBER_LABEL + CommonUtil.stripLeadingZeros(autoPayRequest.getAccountNumber())+" enrolled in autoPay using a bank account with last 3 digits "+bankLastDigits+" on "+CommonUtil.getCurrentDateandTime()+".");
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(autoPayRequest.getCompanyCode());
						
			logger.info("Start: call submitBankAutoPay.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch(Exception e) {
				logger.error("Error in writeAutoPayBankContactLog:{}",e.getMessage());
			}

			logger.info("End submitBankAutoPay:updateContactLog(...) block - in AutoPayBO");
		}
	}
	
	
	public ValidateCCResponse validateCCDetails(AutoPayRequest autoPayRequest, String sessionId){
				
		logger.info("AutoPayBO.validateCCDetails :: START");
		
		ValidateCCResponse validateCCResponse = new ValidateCCResponse(); 
		
		ValidateCCRequest request = new ValidateCCRequest();
		request.setStrAuthorizationType(autoPayRequest.getAuthType());
		request.setStrBillingZip(autoPayRequest.getBillingZip());
		request.setStrBPNumber(autoPayRequest.getBpid());
		if(autoPayRequest.getAccountNumber() == null || autoPayRequest.getAccountNumber().equalsIgnoreCase("")){
			request.setStrCANumber("");
		}else{
			request.setStrCANumber(autoPayRequest.getAccountNumber());
		}
		request.setStrCCNumber(autoPayRequest.getCcNumber());
		request.setStrExpirationDate(autoPayRequest.getExpirationDate());
		request.setStrCVVNumber(autoPayRequest.getCvvNumber());
		request.setStrCompanyCode(autoPayRequest.getCompanyCode());
		try {
			com.multibrand.domain.ValidateCCResponse response = paymentService.validateCCDetails(request, autoPayRequest.getCompanyCode(), sessionId,autoPayRequest.getBrandName());
			
			if(response.getStrXCODE()!= null)validateCCResponse.setStrXCODE(response.getStrXCODE());
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				validateCCResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)validateCCResponse.setResultDescription(response.getErrorMessage());
				validateCCResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)validateCCResponse.setErrorMessage(response.getErrorMessage());
			}
			
			
			
		} catch (Exception e) {

			validateCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateCCResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), validateCCResponse);
			
		}
		
		logger.info("AutoPayBO.validateCCDetails :: END");
		return validateCCResponse;
		
		
	}
	
	
public AutoPayCCResponse submitCCAutoPay(AutoPayRequest autoPayRequest, String sessionId){
	
		
		logger.info("AutoPayBO.submitCCAutoPay :: START");
		
		AutoPayCCResponse	autoPayCCResponse = new AutoPayCCResponse();
		
		com.multibrand.domain.AutoPayCCRequest request = new com.multibrand.domain.AutoPayCCRequest();
		request.setStrCCType(autoPayRequest.getAuthType());
		request.setStrBPNumber(autoPayRequest.getBpid());
		request.setStrCANumber(autoPayRequest.getAccountNumber());
		request.setStrCCNumber(autoPayRequest.getCcNumber());
		request.setStrExpirationDate(autoPayRequest.getExpirationDate());
		request.setStrCAName(autoPayRequest.getAccountName());
		
		String cardType = getCeditCardType(autoPayRequest.getAuthType());
		
		String maskCCNumber = CommonUtil.maskCCNo(autoPayRequest.getCcNumber());
		String ccLastDigits = maskCCNumber.substring(maskCCNumber.length()-4, maskCCNumber.length());
		
		
		try {
			com.multibrand.domain.AutoPayCCResponse response = paymentService.submitCCAutoPay(request, autoPayRequest.getCompanyCode(), sessionId,autoPayRequest.getBrandName());
			
			if(response.getStrStatus() != null)autoPayCCResponse.setStrStatus(response.getStrStatus());
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")) {
				autoPayCCResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)autoPayCCResponse.setResultDescription(response.getErrorMessage());
				autoPayCCResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)autoPayCCResponse.setErrorMessage(response.getErrorMessage());
				
			}else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(autoPayRequest.getCompanyCode()) && !CIRRO_BRAND_NAME.equalsIgnoreCase(autoPayRequest.getBrandName())
					&& !CommonUtil.checkIfGMEPrepay(autoPayRequest.getCompanyCode(), autoPayRequest.getBrandName(), autoPayRequest.getEmailTypeId())) {
				
                logger.info("Sending mail for enrollment successful");
				
				HashMap<String, String> templateProps = new HashMap<>();
					
				templateProps.put(CARD_TYPE, cardType);
				templateProps.put(CARD_NUMBER, maskCCNumber);
				templateProps.put(EXP_DATE, autoPayRequest.getExpirationDate());
				
				if(StringUtils.isBlank(autoPayRequest.getLanguageCode())|| autoPayRequest.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN)){
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD);
					logger.info("Sending mail for successful auto pay enrollment EN");
					EmailHelper.sendMailWithBCC(autoPayRequest.getEmail(), this.envMessageReader.getMessage(QC_BCC_MAIL),  "", AUTO_PAY_ENROLL_CONF_CC_EN, templateProps, autoPayRequest.getCompanyCode());
				} else{
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD_ES);
					logger.info("Sending mail for successful auto pay enrollment ES");
					EmailHelper.sendMailWithBCC(autoPayRequest.getEmail(),this.envMessageReader.getMessage(QC_BCC_MAIL),  "", AUTO_PAY_ENROLL_CONF_CC_ES, templateProps, autoPayRequest.getCompanyCode());
				}
				
			}
			
			cirroAutoPayEmailConfirmation(autoPayRequest.getCompanyCode(), autoPayRequest.getEmail(), autoPayRequest.getBrandName(), autoPayRequest.getCaName());
			writeAutoPayContactLog(autoPayRequest.getAccountNumber(), autoPayRequest.getBpid(), autoPayRequest.getCompanyCode(), autoPayRequest.getSource(), autoPayCCResponse, cardType, ccLastDigits);
			
			
		} catch (Exception e) {

			autoPayCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayCCResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), autoPayCCResponse);
			
		}		
		logger.info("AutoPayBO.submitCCAutoPay :: END");
		return autoPayCCResponse;
		
		
	}


	private void cirroAutoPayEmailConfirmation( String companyCode, String email, String brandName, String caName) throws Exception {
		if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)) {

			HashMap<String, String> templateProperties = new HashMap<>();

			templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));

			try {

				Boolean status = EmailHelper.sendMail(email, "", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN,
						templateProperties, companyCode);

				logger.info("Auto Pay Update Email sent status : {}", status);

			} catch (Exception e) {
				logger.info("Exception in sending Auto Pay Update Email ");

			}
		}

	}

	private void writeAutoPayContactLog(String accountNumber, String bpid, String companyCode, String source,
			AutoPayCCResponse autoPayCCResponse, String cardType, String ccLastDigits) {
		
		if (autoPayCCResponse.getResultCode() != null && source != null
				&& (autoPayCCResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)
						|| autoPayCCResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE))
				&& GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode) && source.equalsIgnoreCase(MOBILE)) {
			
			logger.info("Inside submitCCAutoPay:updateContactLog(...) block - in AutoPayBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(bpid);
			cssUpdateLogRequest.setContractAccountNumber(accountNumber);
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_CC_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_ENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines(ACCOUNT_NUMBER_LABEL + CommonUtil.stripLeadingZeros(accountNumber)
					+ " enrolled in autoPay using a " + cardType + " card with last four digits " + ccLastDigits
					+ " on " + CommonUtil.getCurrentDateandTime() + ".");
			cssUpdateLogRequest.setFormatCol("");// Should be Blank
			cssUpdateLogRequest.setCompanyCode(companyCode);

			logger.info("Start: call TOSService.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch (Exception e) {
				logger.error("Error in updateContactLog:{}", e.getMessage());
			}
			logger.info("End: call TOSService.updateContactLog(...)");
			logger.info("End submitCCAutoPay:updateContactLog(...) block - in AutoPayBO");
		}
	}


	private String getCeditCardType(String authType) {
		String cardType = "";
		if (!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZVIS))
			cardType = VISA;
		else if (!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZMCD))
			cardType = MASTERCARD;
		else if (!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZDSC))
			cardType = DISCOVER;
		else if (!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZAMX))
			cardType = AMERICANEXPRESS;
		return cardType;
	}

/**
 * @author kdeshmu1
 * @param accountNumber
 * @param companyCode
 * @return
 */
public DeEnrollResponse deEnroll(AutoPayRequest request, String sessionId){
	
	logger.info("AutoPayBO.deEnroll :: START");
	
	DeEnrollResponse response  = new DeEnrollResponse();
	String payMethodIndicator = "";
	String authType ="";
	String maskCCNumber="";
	String maskBankAcctNumber="";
	try {
		
		if (request.getSource() != null && GME_RES_COMPANY_CODE.equalsIgnoreCase(request.getCompanyCode()) && request.getSource().equalsIgnoreCase(MOBILE))
		{
			AutoPayInfoRequest autoPayRequest = new AutoPayInfoRequest();

			autoPayRequest.setBusinessPartnerID(request.getBpNumber());
			autoPayRequest.setCompanyCode(request.getCompanyCode());
			autoPayRequest.setBrandName(request.getBrandName());
			AutoPayInfoResponse autoPayResponse =  billingBO.getAutopayInfo(autoPayRequest);
			AutoPayDetails[] autoPayDetailsList = autoPayResponse.getAutoPayDetailsList();
			if(autoPayResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE)&&autoPayDetailsList.length>0)
			{
					payMethodIndicator = autoPayDetailsList[0].getPayment();
					if (payMethodIndicator.equalsIgnoreCase(AUTOPAY_G_FLAG)) {
						authType = autoPayDetailsList[0].getCardType();
						maskCCNumber = CommonUtil.maskCCNo(autoPayDetailsList[0].getCardNumber());
						String temp = maskCCNumber.substring(maskCCNumber.length() - 4, maskCCNumber.length());
						maskCCNumber = temp;
					} else {
						maskBankAcctNumber = CommonUtil.maskBankAccountNo(autoPayDetailsList[0].getBankAccountNumber());
						String temp = maskBankAcctNumber.substring(maskBankAcctNumber.length() - 3,
								maskBankAcctNumber.length());
						maskBankAcctNumber = temp;

					}
					
			}	
		}
		
		
		response = paymentService.deEnroll(request.getAccountNumber(), request.getCompanyCode(),sessionId,request.getBrandName());
		if(response.getSuccessCode()!=null && response.getSuccessCode().equals("00")){
		 response.setResultCode(RESULT_CODE_SUCCESS);
		 response.setResultDescription(MSG_SUCCESS);
		 
		 sendDeEnrollConfirmationEmail(request);
		 
		 sendCirroDeEnrollEmailConfirmation(request);
		 
		}
		else if (response.getSuccessCode()!=null && response.getSuccessCode().equals("01"))
		{
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(deEnrollMap.get(response.getSuccessCode()));
		}
	
	} catch (Exception e) {
		logger.info("Exception Exception:{}",e.getMessage());
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);
	}
	
	
		writeDeEnrollContactLog(request, response, payMethodIndicator, authType, maskCCNumber, maskBankAcctNumber);	

	logger.info("AutoPayBO.deEnroll :: END");
	return response;
}


private void writeDeEnrollContactLog(AutoPayRequest request, DeEnrollResponse response, String payMethodIndicator,
		String authType, String maskCCNumber, String maskBankAcctNumber) {
	if (response.getResultCode()!= null && request.getBpNumber()!= null && request.getSource()!= null
			&& (response.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)
					|| response.getResultCode().equalsIgnoreCase(SUCCESS_CODE))
			&& GME_RES_COMPANY_CODE.equalsIgnoreCase(request.getCompanyCode()) && request.getSource().equalsIgnoreCase(MOBILE))
{
		
	logger.info("Inside deEnroll:updateContactLog(...) block - in AutoPayBO");		
	
	CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
	cssUpdateLogRequest.setBusinessPartnerNumber(request.getBpNumber());
	cssUpdateLogRequest.setContractAccountNumber(request.getAccountNumber());
		if(payMethodIndicator.equalsIgnoreCase(AUTOPAY_G_FLAG)){

			String ccType = getCeditCardType(authType);
			
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_CC_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_DEENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines(ACCOUNT_NUMBER_LABEL + CommonUtil.stripLeadingZeros(request.getAccountNumber())+" de-enrolled in autoPay using a "+ccType+" card with last four digits "+maskCCNumber+" on "+CommonUtil.getCurrentDateandTime()+".");
		}else{
			
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_BANK_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_DEENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines(ACCOUNT_NUMBER_LABEL + CommonUtil.stripLeadingZeros(request.getAccountNumber())+" de-enrolled in autoPay using a bank account with last 3 digits "+maskBankAcctNumber+" on "+CommonUtil.getCurrentDateandTime()+".");
		}
		cssUpdateLogRequest.setFormatCol("");//Should be Blank
		cssUpdateLogRequest.setCompanyCode(request.getCompanyCode());
				
		logger.info("Start: call TOSService.updateContactLog(...)");
		try {
			tosService.updateContactLog(cssUpdateLogRequest);
		} catch(Exception e) {
			logger.error("Error in updateContactLog:{}",e.getMessage());
		}
		logger.info("End: call TOSService.updateContactLog(...)");
		logger.info("End deEnroll:updateContactLog(...) block - in AutoPayBO");	
}
}


	private void sendCirroDeEnrollEmailConfirmation(AutoPayRequest request) {
		if (CIRRO_COMPANY_CODE.equalsIgnoreCase(request.getCompanyCode())
				&& CIRRO_BRAND_NAME.equalsIgnoreCase(request.getBrandName())) {

			HashMap<String, String> templateProperties = new HashMap<>();

			templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(request.getCaName()));

			try {

				Boolean status = EmailHelper.sendMail(request.getEmail(), "", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN,
						templateProperties, request.getCompanyCode());

				logger.info("Auto Pay Update Email sent status :{} ", status);

			} catch (Exception e) {
				logger.info("Exception in sending Auto Pay Update Email ");
				logger.info(e);
				logger.error(e);

			}

		}
	}


	private void sendDeEnrollConfirmationEmail(AutoPayRequest request) throws Exception {
		if (!CIRRO_COMPANY_CODE.equalsIgnoreCase(request.getCompanyCode())
				&& !CIRRO_BRAND_NAME.equalsIgnoreCase(request.getBrandName())) {
			// sending mail for successful deenroll
			logger.info("Sending mail for successful de-enroll for auto pay");
			HashMap<String, String> templateProps = new HashMap<>();

			if (StringUtils.isBlank(request.getLanguageCode())
					|| request.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN)) {
				logger.info("Sending mail for successful auto pay de-enrollment EN");
				EmailHelper.sendMailWithBCC(request.getEmail(), this.envMessageReader.getMessage(QC_BCC_MAIL), "",
						AUTOPAY_DEENROLL_EN, templateProps, request.getCompanyCode());
			} else {
				logger.info("Sending mail for successful auto pay de-enrollment ES");
				EmailHelper.sendMailWithBCC(request.getEmail(), this.envMessageReader.getMessage(QC_BCC_MAIL), "",
						AUTOPAY_DEENROLL_ES, templateProps, request.getCompanyCode());
			}
		}
	}
	
	/**
	 * Method createValidateBankDetailsGIACTRequest used to create a complete request Object for BankValidation Call Using GIACT API 
	 * @param contractAccountNum String
	 * @param trackingNumber String
	 * @param bankDTO BankAccountDTO
	 * @return BankDetailsValidationRequest
	 */
	private BankDetailsValidationRequest createValidateBankDetailsGIACTRequest(AutoPayRequest autoPayRequest)
	{
		BankDetailsValidationRequest bankDetailsValidationRequest = new BankDetailsValidationRequest();
		bankDetailsValidationRequest.setBankAccountNumber(autoPayRequest.getBankAccountNumber());
		bankDetailsValidationRequest.setCompanyCode(GME_RES_COMPANY_CODE);
		bankDetailsValidationRequest.setContractAccountNumber(autoPayRequest.getAccountNumber());
		bankDetailsValidationRequest.setRoutingNumber(autoPayRequest.getBankRoutingNumber());
		bankDetailsValidationRequest.setTrackingNumber("");
		return bankDetailsValidationRequest;
		
	}	
}
