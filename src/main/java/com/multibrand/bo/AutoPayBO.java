package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.AutoPayBankRequest;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.domain.ValidateCCRequest;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.EmailHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.PaymentService;
import com.multibrand.service.ProfileService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.AutoPayInfoRequest;
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

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private EmailHelper emailHelper;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private TOSService tosService;
	
	@Autowired
	private BillingBO billingBO;
	
	//@Autowired
	//ReloadableResourceBundleMessageSource appConstMessageSource;
	
	public ValidateBankResponse validateBankDetails(String ca, String bankAccountNumber, String bankRoutingNumber, String companyCode, String sessionId, String brandName){
		
		ValidateBankResponse validateBankResponse = new ValidateBankResponse();
		
		logger.info("AutoPayBO.validateBankDetails :: START");
		com.multibrand.domain.ValidateBankResponse response=null;
		
		try {
			 response = paymentService.validateBankDetails(ca, bankAccountNumber, bankRoutingNumber, companyCode, sessionId ,brandName);
			 			
			 logger.info("Redbull Service Response :: " + response);
			 
			 if(response.getStrYCODE()!=null)validateBankResponse.setStrYCODE(response.getStrYCODE());
				
				if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
					validateBankResponse.setErrorCode(response.getErrorCode());
					if(response.getErrorMessage()!=null)validateBankResponse.setErrorMessage(response.getErrorMessage());
					validateBankResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					if(response.getErrorMessage()!=null)validateBankResponse.setResultDescription(response.getErrorMessage());
				}
				
				
				
			} catch (RemoteException e) {

				validateBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				validateBankResponse.setResultDescription("Exception Occured");
				throw new OAMException(200, e.getMessage(), validateBankResponse);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				validateBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				validateBankResponse.setResultDescription("Exception Occured");
				throw new OAMException(200, e.getMessage(), validateBankResponse);
				
			}
					
		logger.info("AutoPayBO.validateBankDetails :: END");
		return validateBankResponse;
	}
	
	
	public AutoPayBankResponse submitBankAutoPay(String accountNumber, String bankAccountNumber, String bankRountingNumber, String companyCode, String accountName, String accountChkDigit, String locale,  String email, String sessionId,String emailTypeId, String brandName, String bpNumber, String source){
		
		logger.info("AutoPayBO.submitBankAutoPay :: START");
		AutoPayBankResponse autoPayBankResponse = new AutoPayBankResponse();
		
		AutoPayBankRequest request = new AutoPayBankRequest();
		request.setStrBankAccNumber(bankAccountNumber);
		request.setStrBankRoutingNumber(bankRountingNumber);
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		String maskBankAcctNumber = CommonUtil.maskBankAccountNo(bankAccountNumber);
		String bankLastDigits = maskBankAcctNumber.substring(maskBankAcctNumber.length()-3, maskBankAcctNumber.length());
		
		try {
			com.multibrand.domain.AutoPayBankResponse response = paymentService.submitBankAutoPay(request, companyCode, sessionId,brandName);
			
			if(response.getStrStatus()!= null)autoPayBankResponse.setStrStatus(response.getStrStatus());
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				autoPayBankResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)autoPayBankResponse.setResultDescription(response.getErrorMessage());
				autoPayBankResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)autoPayBankResponse.setErrorMessage(response.getErrorMessage());
			}else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)
					&& !CommonUtil.checkIfGMEPrepay(companyCode, brandName, emailTypeId)){
				
                logger.info("Sending mail for auto pay enrollment successful");		
                
				HashMap<String, String> templateProps = new HashMap<String,String>();
					
				templateProps.put(BANK_ACCOUNT_NUMBER, maskBankAcctNumber);
				templateProps.put(BANK_ROUTING_NUMBER, bankRountingNumber);
				
				if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK);
					logger.info("Sending mail for successful auto pay enrollment EN");
					emailHelper.sendMailWithBCC(email,this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTO_PAY_ENROLL_CONF_BANK_EN, templateProps, companyCode);
				} else{
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK_ES);
					logger.info("Sending mail for successful auto pay enrollment ES");
					emailHelper.sendMailWithBCC(email,this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTO_PAY_ENROLL_CONF_BANK_ES, templateProps, companyCode);
				}
				
			}
			
			if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
				Map<String, Object> responseMap = new HashMap<String, Object>();
				ProfileResponse profileResponse = null;
				 responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
				if(responseMap!= null && responseMap.size()!= 0)
				{
					profileResponse = (ProfileResponse)responseMap.get("profileResponse");
				}
				else{
					logger.info("Couldn't find the profile for given account number, so couldn't send mail");
				}
				ContractAccountDO caDO  = null;
				
				if (profileResponse != null) {
					caDO = profileResponse.getContractAccountDO();
					if(caDO != null){
						
						logger.info("Found profile for given account number, Found contract account info");
						
						String caName = caDO.getCAName();
						
						if(caName != null && !caName.equalsIgnoreCase("")){
							logger.info("Found CA Name : " + caName + ", Sending Mail for auto pay update");
							
							HashMap<String, String> templateProperties = new HashMap<String,String>();

							templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));


							try {

							Boolean status = EmailHelper.sendMail( email ,"", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN, templateProperties, companyCode);

							logger.info("Auto Pay Update Email sent status : " + status);

							} catch (Exception e) {
						    logger.info("Exception in sending Auto Pay Update Email " );
							// TODO Auto-generated catch block
	                        logger.info(e);
							logger.error(e);

							}
							
							
						} else{
							logger.info("Couldn't find CA Name : " + caName + ", so couldn't send mail auto pay update");	
						}
						
					}else{
						logger.info("Found the profile for given account number but couldn't find the contract account info, so couldn't send mail");
					}                 
				 
							
				}else{
					logger.info("Couldn't find the profile for given account number, so couldn't send mail");
				}
				
				
			 }
			
			
			
		} catch (RemoteException e) {

			autoPayBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayBankResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), autoPayBankResponse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			autoPayBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayBankResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), autoPayBankResponse);
			
		}
		
		if(autoPayBankResponse.getResultCode()!=null && bpNumber!=null && source!=null &&
				(autoPayBankResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)||autoPayBankResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE))&& 
				GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode)&&source.equalsIgnoreCase(MOBILE))
		{
			logger.info("Inside submitBankAutoPay:updateContactLog(...) block - in AutoPayBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(bpNumber);
			cssUpdateLogRequest.setContractAccountNumber(accountNumber);
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_BANK_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_ENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines("User with account number "+CommonUtil.stripLeadingZeros(accountNumber)+" enrolled in autoPay using a bank account with last 3 digits "+bankLastDigits+" on "+CommonUtil.getCurrentDateandTime()+".");
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(companyCode);
						
			logger.info("Start: call TOSService.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch(Exception e) {
				logger.error("Error in updateContactLog:"+e);
			}
			logger.info("End: call TOSService.updateContactLog(...)");
			logger.info("End submitBankAutoPay:updateContactLog(...) block - in AutoPayBO");
		}	
		
		logger.info("AutoPayBO.submitBankAutoPay :: END");
		return autoPayBankResponse;
		
	}
	
	
	public ValidateCCResponse validateCCDetails(String authType, String accountNumber, String bpid, String ccNumber, String cvvNumber, String expirationDate, String billingZip, String companyCode, String sessionId, String brandName){
		
		logger.info("AutoPayBO.validateCCDetails :: START");
		
		ValidateCCResponse validateCCResponse = new ValidateCCResponse(); 
		
		ValidateCCRequest request = new ValidateCCRequest();
		request.setStrAuthorizationType(authType);
		request.setStrBillingZip(billingZip);
		request.setStrBPNumber(bpid);
		if(accountNumber == null || accountNumber.equalsIgnoreCase("")){
			request.setStrCANumber("");
		}else{
			request.setStrCANumber(accountNumber);
		}
		request.setStrCCNumber(ccNumber);
		request.setStrExpirationDate(expirationDate);
		request.setStrCVVNumber(cvvNumber);
		request.setStrCompanyCode(companyCode);
		try {
			com.multibrand.domain.ValidateCCResponse response = paymentService.validateCCDetails(request, companyCode, sessionId,brandName);
			
			if(response.getStrXCODE()!= null)validateCCResponse.setStrXCODE(response.getStrXCODE());
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				validateCCResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)validateCCResponse.setResultDescription(response.getErrorMessage());
				validateCCResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)validateCCResponse.setErrorMessage(response.getErrorMessage());
			}
			
			
			
		} catch (RemoteException e) {

			validateCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), validateCCResponse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			validateCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), validateCCResponse);
			
		}
		
		logger.info("AutoPayBO.validateCCDetails :: END");
		return validateCCResponse;
		
		
	}
	
	
public AutoPayCCResponse submitCCAutoPay(String authType, String accountName,  String accountNumber, String bpid, String ccNumber, String expirationDate, String billingZip, String companyCode, String email, String sessionId, String locale,String emailTypeId,String brandName, String source){
		
		logger.info("AutoPayBO.submitCCAutoPay :: START");
		
		AutoPayCCResponse	autoPayCCResponse = new AutoPayCCResponse();
		
		com.multibrand.domain.AutoPayCCRequest request = new com.multibrand.domain.AutoPayCCRequest();
		request.setStrCCType(authType);
		request.setStrBPNumber(bpid);
		request.setStrCANumber(accountNumber);
		request.setStrCCNumber(ccNumber);
		request.setStrExpirationDate(expirationDate);
		request.setStrCAName(accountName);
		
		String cardType = "";
		if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZVIS))
			cardType = VISA;
		else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZMCD))
			cardType = MASTERCARD;
		else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZDSC))
			cardType = DISCOVER;
		
		String maskCCNumber = CommonUtil.maskCCNo(ccNumber);
		String ccLastDigits = maskCCNumber.substring(maskCCNumber.length()-4, maskCCNumber.length());
		
		
		try {
			com.multibrand.domain.AutoPayCCResponse response = paymentService.submitCCAutoPay(request, companyCode, sessionId,brandName);
			
			if(response.getStrStatus() != null)autoPayCCResponse.setStrStatus(response.getStrStatus());
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				autoPayCCResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)autoPayCCResponse.setResultDescription(response.getErrorMessage());
				autoPayCCResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)autoPayCCResponse.setErrorMessage(response.getErrorMessage());
			}else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)
					&& !CommonUtil.checkIfGMEPrepay(companyCode, brandName, emailTypeId)){
				
                logger.info("Sending mail for enrollment successful");
				
				HashMap<String, String> templateProps = new HashMap<String,String>();
					
				templateProps.put(CARD_TYPE, cardType);
				templateProps.put(CARD_NUMBER, maskCCNumber);
				templateProps.put(EXP_DATE, expirationDate);
				
				if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD);
					logger.info("Sending mail for successful auto pay enrollment EN");
					emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL),  "", AUTO_PAY_ENROLL_CONF_CC_EN, templateProps, companyCode);
				} else{
					templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD_ES);
					logger.info("Sending mail for successful auto pay enrollment ES");
					emailHelper.sendMailWithBCC(email,this.envMessageReader.getMessage(QC_BCC_MAIL),  "", AUTO_PAY_ENROLL_CONF_CC_ES, templateProps, companyCode);
				}
				
			}
			
			if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
				Map<String, Object> responseMap = new HashMap<String, Object>();
				ProfileResponse profileResponse = null;
				responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
				if(responseMap!= null && responseMap.size()!= 0)
				{
					profileResponse= (ProfileResponse)responseMap.get("profileResponse");
				}
				else{
					logger.info("Couldn't find the profile for given account number, so couldn't send mail");
				}
				ContractAccountDO caDO  = null;
				
				if (profileResponse != null) {
					caDO = profileResponse.getContractAccountDO();
					if(caDO != null){
						
						logger.info("Found profile for given account number, Found contract account info");
						
						String caName = caDO.getCAName();
						
						if(caName != null && !caName.equalsIgnoreCase("")){
							logger.info("Found CA Name : " + caName + ", Sending Mail for auto pay update");
							
							HashMap<String, String> templateProperties = new HashMap<String,String>();

							templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));


							try {

							Boolean status = EmailHelper.sendMail( email ,"", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN, templateProperties, companyCode);

							logger.info("Auto Pay Update Email sent status : " + status);

							} catch (Exception e) {
						    logger.info("Exception in sending Auto Pay Update Email " );
							// TODO Auto-generated catch block
	                        logger.info(e);
							logger.error(e);

							}
							
							
						} else{
							logger.info("Couldn't find CA Name : " + caName + ", so couldn't send mail auto pay update");	
						}
						
					}else{
						logger.info("Found the profile for given account number but couldn't find the contract account info, so couldn't send mail");
					}                 
				 
							
				}else{
					logger.info("Couldn't find the profile for given account number, so couldn't send mail");
				}
				
				
			 }
			
			
			
		} catch (RemoteException e) {

			autoPayCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), autoPayCCResponse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			autoPayCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			autoPayCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), autoPayCCResponse);
			
		}
		
		if(autoPayCCResponse.getResultCode()!=null && source!=null &&
				(autoPayCCResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)||autoPayCCResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE))&& 
				GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode)&&source.equalsIgnoreCase(MOBILE)){
			logger.info("Inside submitCCAutoPay:updateContactLog(...) block - in AutoPayBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(bpid);
			cssUpdateLogRequest.setContractAccountNumber(accountNumber);
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_CC_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_ENROLL_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines("User with account number "+CommonUtil.stripLeadingZeros(accountNumber)+" enrolled in autoPay using a "+cardType+" card with last four digits "+ccLastDigits+" on "+CommonUtil.getCurrentDateandTime()+".");
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(companyCode);
			
			logger.info("Start: call TOSService.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch(Exception e) {
				logger.error("Error in updateContactLog:"+e);
			}
			logger.info("End: call TOSService.updateContactLog(...)");
			logger.info("End submitCCAutoPay:updateContactLog(...) block - in AutoPayBO");
		}
		
		logger.info("AutoPayBO.submitCCAutoPay :: END");
		return autoPayCCResponse;
		
		
	}

/**
 * @author kdeshmu1
 * @param accountNumber
 * @param companyCode
 * @return
 */
public DeEnrollResponse deEnroll(String accountNumber,String companyCode, String sessionId, String email, String locale,String brandName, String bpNumber, String source ){
	
	
	logger.info("AutoPayBO.deEnroll :: START");
	
	DeEnrollResponse response  = new DeEnrollResponse();
	ProfileResponse profileResponse = new ProfileResponse();
	Map<String, Object> responseMap = new HashMap<String, Object>();
	String payMethodIndicator = "";
	String authType ="";
	String maskCCNumber="";
	String maskBankAcctNumber="";
	String cardType = "";
	try {
		
		if (bpNumber!= null && source!= null && GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode) && source.equalsIgnoreCase(MOBILE))
		{
			AutoPayInfoRequest autoPayRequest = new AutoPayInfoRequest();
			AutoPayInfoResponse autoPayResponse = new AutoPayInfoResponse();
			autoPayRequest.setBusinessPartnerID(bpNumber);
			autoPayRequest.setCompanyCode(companyCode);
			autoPayRequest.setBrandName(brandName);
			autoPayResponse =  billingBO.getAutopayInfo(autoPayRequest);
			AutoPayDetails[] autoPayDetailsList = autoPayResponse.getAutoPayDetailsList();
			if(autoPayResponse!=null&&autoPayResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE)&&autoPayDetailsList.length>0)
			{
				payMethodIndicator = autoPayDetailsList[0].getPayment();
				if(payMethodIndicator.equalsIgnoreCase(AUTOPAY_G_FLAG)){
					authType = autoPayDetailsList[0].getCardType();
					maskCCNumber = CommonUtil.maskCCNo(autoPayDetailsList[0].getCardNumber());
					String temp = maskCCNumber.substring(maskCCNumber.length()-4, maskCCNumber.length());
					maskCCNumber = temp;
					}else{
					maskBankAcctNumber = CommonUtil.maskBankAccountNo(autoPayDetailsList[0].getBankAccountNumber());
					String temp = maskBankAcctNumber.substring(maskBankAcctNumber.length()-3, maskBankAcctNumber.length());
					maskBankAcctNumber = temp;
					
					}
					
			}else
			{
					logger.error("Nothing to De Eroll Skipping updateContactLog(...) entry");
			}		
		}
		
		
		response = paymentService.deEnroll(accountNumber,companyCode,sessionId,brandName);
		if(response.getSuccessCode()!=null && response.getSuccessCode().equals("00")){
		 response.setResultCode(RESULT_CODE_SUCCESS);
		 response.setResultDescription(MSG_SUCCESS);
		 
		 //if (companyCode != null && !companyCode.equalsIgnoreCase("0391") && brandName != null && !brandName.equalsIgnoreCase("CE")){
		 if (!CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
		 //sending mail for successful deenroll
		 logger.info("Sending mail for successful de-enroll for auto pay");
		    HashMap<String, String> templateProps = new HashMap<String,String>();
			if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){						
			logger.info("Sending mail for successful auto pay de-enrollment EN");
			emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTOPAY_DEENROLL_EN, templateProps, companyCode);
			} else{
				logger.info("Sending mail for successful auto pay de-enrollment ES");
				emailHelper.sendMailWithBCC(email,this.envMessageReader.getMessage(QC_BCC_MAIL), "", AUTOPAY_DEENROLL_ES, templateProps, companyCode);
			}
		 }

		 
		 if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
			responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
			if(responseMap!= null && responseMap.size()!= 0)
			{
				profileResponse= (ProfileResponse)responseMap.get("profileResponse");
			}
			else
			{
				logger.info("Couldn't find the profile for given account number, so couldn't send mail");
			}
			ContractAccountDO caDO  = null;
			
			if (profileResponse != null) {
				caDO = profileResponse.getContractAccountDO();
				if(caDO != null){
					
					logger.info("Found profile for given account number, Found contract account info");
					
					String caName = caDO.getCAName();
					
					if(caName != null && !caName.equalsIgnoreCase("")){
						logger.info("Found CA Name : " + caName + ", Sending Mail for auto pay update");
						
						HashMap<String, String> templateProperties = new HashMap<String,String>();

						templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));


						try {

						Boolean status = EmailHelper.sendMail( email ,"", CIRRO_AUTO_PAY_UPDATE_EXTERNAL_ID_EN, templateProperties, companyCode);

						logger.info("Auto Pay Update Email sent status : " + status);

						} catch (Exception e) {
					    logger.info("Exception in sending Auto Pay Update Email " );
						// TODO Auto-generated catch block
                        logger.info(e);
						logger.error(e);

						}
						
						
					} else{
						logger.info("Couldn't find CA Name : " + caName + ", so couldn't send mail auto pay update");	
					}
					
				}else{
					logger.info("Found the profile for given account number but couldn't find the contract account info, so couldn't send mail");
				}                 
			 
						
			}else{
				logger.info("Couldn't find the profile for given account number, so couldn't send mail");
			}
			
			
		 }
		
		 
		}
		else if (response.getSuccessCode()!=null && response.getSuccessCode().equals("01"))
		{
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(RESULT_CODE_CCS_ERROR_DESCRIPTION_01);
		}
		else if (response.getSuccessCode()!=null && response.getSuccessCode().equals("02"))
		{
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(RESULT_CODE_CCS_ERROR_DESCRIPTION_02);
		}
		else if (response.getSuccessCode()!=null && response.getSuccessCode().equals("03"))
		{
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			response.setResultDescription(RESULT_CODE_CCS_ERROR_DESCRIPTION_03);
		}
	} catch (RemoteException e) {
		logger.info("Remote Exception "+e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);
	}
	catch (Exception e) {
		logger.info("Exception "+e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);
	}
	
		if (response.getResultCode()!= null && bpNumber!= null && source!= null
				&& (response.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)
						|| response.getResultCode().equalsIgnoreCase(SUCCESS_CODE))
				&& GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode) && source.equalsIgnoreCase(MOBILE))
	{
			
		logger.info("Inside deEnroll:updateContactLog(...) block - in AutoPayBO");		
		
		CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
		cssUpdateLogRequest.setBusinessPartnerNumber(bpNumber);
		cssUpdateLogRequest.setContractAccountNumber(accountNumber);
			if(payMethodIndicator.equalsIgnoreCase(AUTOPAY_G_FLAG)){
				if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZVIS))
					cardType = VISA;
				else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZMCD))
					cardType = MASTERCARD;
				else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZDSC))
					cardType = DISCOVER;
				cssUpdateLogRequest.setContactClass(CONTACT_LOG_CC_CONTACT_CLASS);
				cssUpdateLogRequest.setContactActivity(CONTACT_LOG_DEENROLL_CONTACT_ACTIVITY);
				cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
				cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
				cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
				cssUpdateLogRequest.setTextLines("User with account number "+CommonUtil.stripLeadingZeros(accountNumber)+" de-enrolled in autoPay using a "+cardType+" card with last four digits "+maskCCNumber+" on "+CommonUtil.getCurrentDateandTime()+".");
			}else{
				
				cssUpdateLogRequest.setContactClass(CONTACT_LOG_BANK_CONTACT_CLASS);
				cssUpdateLogRequest.setContactActivity(CONTACT_LOG_DEENROLL_CONTACT_ACTIVITY);
				cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
				cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
				cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
				cssUpdateLogRequest.setTextLines("User with account number "+CommonUtil.stripLeadingZeros(accountNumber)+" de-enrolled in autoPay using a bank account with last 3 digits "+maskBankAcctNumber+" on "+CommonUtil.getCurrentDateandTime()+".");
			}
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(companyCode);
					
			logger.info("Start: call TOSService.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch(Exception e) {
				logger.error("Error in updateContactLog:"+e);
			}
			logger.info("End: call TOSService.updateContactLog(...)");
			logger.info("End deEnroll:updateContactLog(...) block - in AutoPayBO");	
	}	

	logger.info("AutoPayBO.deEnroll :: END");
	return response;
}
}
