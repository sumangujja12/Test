package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.ws.rs.FormParam;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.ProfileDAO;
import com.multibrand.domain.AcctValidationRequest;
import com.multibrand.domain.AllAccountDetailsRequest;
import com.multibrand.domain.ChangeUsrNameRequest;
import com.multibrand.domain.ChangeUsrNameResponse;
import com.multibrand.domain.CirroStructureCallRequest;
import com.multibrand.domain.CirroStructureCallResponse;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.CrmProfileRequest;
import com.multibrand.domain.CrmProfileResponse;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.LanguageUpdateRequest;
import com.multibrand.domain.LanguageUpdateResponse;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.domain.UpdateAddressRequest;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactRequestAttNamValPairMapEntry;
import com.multibrand.domain.UpdatePhoneDO;
import com.multibrand.domain.WseEnrollmentRequest;
import com.multibrand.domain.WseEnrollmentResponse;
import com.multibrand.domain.WseEsenseEligibilityDO;
import com.multibrand.domain.WseEsenseEligibilityRequest;
import com.multibrand.domain.WseEsenseEligibilityResponse;
import com.multibrand.domain.WseServiceRequest;
import com.multibrand.domain.WseServiceResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.BPAccountContractPayHelper;
import com.multibrand.helper.EmailHelper;
import com.multibrand.helper.LDAPHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.proxy.ProfileProxy;
import com.multibrand.service.LDAPService;
import com.multibrand.service.ProfileService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.SecondaryNameUpdateReqVO;
import com.multibrand.vo.request.ValidateUserNameRequest;
import com.multibrand.vo.response.AcctValidationResponse;
import com.multibrand.vo.response.BussinessPartnerDO;
import com.multibrand.vo.response.ChangeUsernameResponse;
import com.multibrand.vo.response.CirroContractAccountDO;
import com.multibrand.vo.response.CirroContractDO;
import com.multibrand.vo.response.CirroStructureResponse;
import com.multibrand.vo.response.ContractInfoDO;
import com.multibrand.vo.response.EnvironmentImpactsResponse;
import com.multibrand.vo.response.ForgotPasswordResponse;
import com.multibrand.vo.response.ForgotUserNameResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.SecondaryNameResponse;
import com.multibrand.vo.response.SendMailForNewServiceAddressAddResponse;
import com.multibrand.vo.response.SendMailForPasswordChangeResponse;
import com.multibrand.vo.response.SmartMeterCheckResponse;
import com.multibrand.vo.response.UpdateBillingAddressResponse;
import com.multibrand.vo.response.UpdateContactInfoResponse;
import com.multibrand.vo.response.UpdatePasswordResponse;
import com.multibrand.vo.response.UserInfoResponse;
import com.multibrand.vo.response.ValidatePasswordLinkResponse;
import com.multibrand.vo.response.WsEnrollmentResponse;
import com.multibrand.vo.response.WsServiceResponse;
import com.multibrand.vo.response.WseEligiblityStatusResponse;
import com.multibrand.vo.response.WseEsenseEligibility;
import com.multibrand.vo.response.billingResponse.GetAccountDetailsResponse;
import com.multibrand.vo.response.billingResponse.GetBillingAddressResponse;
import com.multibrand.vo.response.profileResponse.GetBPInfoResponse;
import com.multibrand.vo.response.profileResponse.ProductUpdateResponse;
import com.multibrand.vo.response.profileResponse.ProfileCheckResponse;
import com.multibrand.vo.response.profileResponse.UpdateLanguageResponse;


/** This BO class is to handle all the Profile Related API calls.
 * 
 * @author rbansal30	
 */
@Component
public class ProfileBO extends BaseBO {
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private LDAPService ldapService;
	
	@Autowired
	private LDAPHelper ldapHelper;
	
	@Autowired
	private ProfileDAO profileDAO;
	
	@Autowired
	private BPAccountContractPayHelper bpAccountPayHelper;
		
	@Autowired
	private ProfileProxy profileProxy;
	

	@Autowired
	private BillingBO billingBO;
	
	@Autowired
	protected EnvMessageReader envMessageReader;

	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * This function is to update the password
	 * @author kdeshmu1
	 * @param userID
	 * @return null
	 */
	public UpdatePasswordResponse updatePassword(String userId, String newPassword, String companyCode, String sessionId) {

		
		Attributes attrs= null;
		int update =0;
		com.multibrand.vo.response.UpdatePasswordResponse updatePasswordResponse = new com.multibrand.vo.response.UpdatePasswordResponse();
		long startTime = CommonUtil.getStartTime();
		String request = "userId="+userId+",newPassword=##########"+",companyCode="+companyCode;

		try{
		
			attrs = ldapHelper.modPasswordUserinfo(userId,DirContext.REPLACE_ATTRIBUTE,newPassword);
			if(attrs!=null){
				if(StringUtils.equalsIgnoreCase(companyCode, "0271")){
					update = profileDAO.updateStatusFlag(userId);
					if(update==0)
						logger.error("Status Code='C'- duplicate transaction - profileDAO.updateStatusFlag call");
					else
						logger.info("Status Code changed to 'C'");
				}
				logger.info("::::::::::::::::Password updated successfully");
				updatePasswordResponse.setResultCode(RESULT_CODE_SUCCESS);
				updatePasswordResponse.setResultDescription(MSG_SUCCESS);
			}	
			else
			{
				logger.info(":::::::::::::Password not updated");
				updatePasswordResponse.setResultCode(RESULT_CODE_THREE);
				updatePasswordResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			utilityloggerHelper.logTransaction("updatePassword", false, request,updatePasswordResponse, updatePasswordResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(updatePasswordResponse));
			}
		}catch (Exception e) {
			logger.error(e);
			updatePasswordResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePasswordResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("updatePassword", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(updatePasswordResponse));
			}
			throw new OAMException(200, e.getMessage(),updatePasswordResponse);
		}
		return updatePasswordResponse;
	}
	
	
	/**
	 * This function is to update the password after login
	 * @param userId
	 * @param newPassword
	 * @param oldPassword
	 * @param companyCode
	 * @return
	 */
	public UpdatePasswordResponse updatePasswordBehindLogin(String userId, String newPassword,String oldPassword, String companyCode, String sessionId) {

		long startTime = CommonUtil.getStartTime();
		String request = "userId="+userId+",companyCode="+companyCode+",newPassword=###########"+",oldPassword=###########";
		
		com.multibrand.vo.response.UpdatePasswordResponse updatePasswordResponse = new com.multibrand.vo.response.UpdatePasswordResponse();
		com.multibrand.vo.request.ValidateUserNameRequest validateUserReq = new ValidateUserNameRequest();
		validateUserReq.setStrLDAPOrg(LDAP_ORGANISATION);
		validateUserReq.setStrUserName(userId);
		try{
			
			if(!ldapHelper.validateUsername(validateUserReq).isbSuccessFlag())
			{	
			 if (ldapHelper.validateUser(userId, oldPassword)){
				ldapHelper.modPasswordUserinfo(userId,DirContext.REPLACE_ATTRIBUTE,newPassword);
				updatePasswordResponse.setResultCode(RESULT_CODE_SUCCESS);
				updatePasswordResponse.setResultDescription(MSG_SUCCESS);
			 }else{
				updatePasswordResponse.setResultCode(RESULT_CODE_FOUR);
				updatePasswordResponse.setResultDescription(RESULT_CODE_DESC_PWD_MISMATCH);
			 }
			}
			else
			{
				updatePasswordResponse.setResultCode(RESULT_CODE_NO_DATA);
				updatePasswordResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			utilityloggerHelper.logTransaction("updatePasswordBehindLogin", false, request,updatePasswordResponse, updatePasswordResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(updatePasswordResponse));
			}
		}
//		catch (NamingException e) {
//			updatePasswordResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
//			updatePasswordResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
//			throw new OAMException(200, e.getMessage(),updatePasswordResponse);
//		}
		catch (Exception e) {
			logger.error(e);
			updatePasswordResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePasswordResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("updatePasswordBehindLogin", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(updatePasswordResponse));
			}
			throw new OAMException(200, e.getMessage(),updatePasswordResponse);
		}
		return updatePasswordResponse;

	}
	
	/**
	 * This method is will get user and email with his Username.
	 * @author cuppala
	 * @param userId
	 * @param companyCode
	 * @param zip
	 * @param brandName
	 * @return
	 */
		
	public ForgotUserNameResponse forgotUserName(String userId,String companyCode,String zip, String languageCode, String sessionId, String brandName){
		
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		ForgotUserNameResponse response = new ForgotUserNameResponse();
		String request = userId+"userId";
		logger.info("Inside ProfileBO - forgotUserName call");
		long startTime = CommonUtil.getStartTime();
		try {
			if(StringUtils.isNotEmpty(userId.trim())&&StringUtils.isNotEmpty(zip.trim())&&StringUtils.isNotEmpty(companyCode.trim())&&StringUtils.equalsIgnoreCase(COMPANY_CODE_GME, companyCode)){
			
			userInfoResponse = getUserOrAcctNumber(userId,companyCode,sessionId);
			
			if(userInfoResponse!=null && userInfoResponse.getResultCode().equalsIgnoreCase("0")){
			String accountNumber=userInfoResponse.getAccountNumber();
		    String userName=userInfoResponse.getUserName();
		    String emailID=userInfoResponse.getEmailID();
		    
		    GetBillingAddressResponse billingAddressResp = billingBO.getBillingAddress(accountNumber, companyCode, sessionId);
		  		  	    
		    if((CommonUtil.trimZipCode(billingAddressResp.getStrZip())).equalsIgnoreCase(zip.trim())){
		    	response.setUserName(userName);
		  	
		    	logger.info("User Email id"+emailID);		
		    
		       if(!(emailID.equalsIgnoreCase(null)&&StringUtils.isEmpty(emailID))){
				HashMap<String, String> templateProperties = new HashMap<String, String>();
               templateProperties.put("USER_NAME", userName);
               Boolean status = false;
               if(StringUtils.isEmpty(languageCode))
               	languageCode="E";
               
           			if(languageCode.equalsIgnoreCase("ES")){
		           		//status = EmailHelper.sendMail( emailID ,"", GME_PASSWORD_CHANGE_ES_US, templateProperties, companyCode);
		           		logger.info("Spanish Conf mail send for Password reset: " + status);
							}else{

		               		logger.info("Setting languageCode to English");
		               		status = EmailHelper.sendMail( emailID ,"", GME_USERNAME_EN_US, templateProperties, companyCode);
								logger.info("English Conf mail send for Password reset: " + status);
							}
             }
			 else{
				 logger.info("Email Address validation failed");
				 response.setResultCode(RESULT_CODE_SIX);
	             response.setResultDescription(RESULT_DESCRIPTION_EMAIL_NOTFOUND);
				 response.setMessageText("Email Address validation failed");
				 response.setUserName(userName);
			    
			 }
		       }else{
		    	response.setResultCode(RESULT_CODE_SEVEN);
           		response.setResultDescription(RESULT_DESCRIPTION_INVALID_ZIP_CODE);
           		response.setMessageText("Invalid Zip Code");
           		response.setUserName(userName);
		    }
			}else{
						
				logger.info("Invalid Contract Account details- Account validation failed");
				response.setResultCode(RESULT_CODE_TWO);
				response.setResultDescription(RESULT_CODE_INVALID_ACCOUNT_NUMBER_DESCRIPTION);
				response.setMessageText("Invalid Contract Account details- Account validation failed");
			}
			}else{
				response.setResultCode(RESULT_CODE_FIVE);
				response.setResultDescription(RESULT_CODE_INVALID_INPUT_PARAMETERS);
				response.setMessageText("Invalid Input Parameters");
			}	
			} catch (Exception e) {
				logger.info("Inside Exception block of ProfileBO - forgotUserName call"+e);
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				utilityloggerHelper.logTransaction("forgotUserName", false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
				if(logger.isDebugEnabled()){
					logger.debug(XmlUtil.pojoToXML(request));
					logger.debug(XmlUtil.pojoToXML(response));
				}
			}
		
	       
		return response;
		
		
	}
	
	
	/**
	 * This method is to get the username or/and account number from ldap.
	 * @author Cuppala
	 * @param userId/accountNumber
	 * @param companyCode
	 * @param brandName
	 * @param zip
	 * @return
	 */
		
public ForgotPasswordResponse forgotPassword(String userIdOrAcNum,String companyCode,String brandName,String languageCode, String zip, String sessionId){
		
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		String request = userIdOrAcNum+"userIdOrAcNum";
		
		
		//getEnviromentProp
		
		
		long startTime = CommonUtil.getStartTime();
		try {
			
			if(StringUtils.isNotEmpty(userIdOrAcNum.trim())&&StringUtils.isNotEmpty(zip.trim())
					&&StringUtils.isNotEmpty(companyCode.trim())&&StringUtils.equalsIgnoreCase(COMPANY_CODE_GME, companyCode)){
							
				//Call to get user information including Email id.
				userInfoResponse = getUserOrAcctNumber(userIdOrAcNum,companyCode,sessionId);
			
				if(userInfoResponse!=null && userInfoResponse.getResultCode().equalsIgnoreCase("0")){
					String accountNumber=userInfoResponse.getAccountNumber();
				    String userName=userInfoResponse.getUserName();
				    String emailID=userInfoResponse.getEmailID();
				  
				    String userUniqueID=userInfoResponse.getUserUniqueID();
				    System.out.println("userUniqueID chakriiiiiiiiiii"+userUniqueID);
				    
				    //Call to get billing address
				    GetBillingAddressResponse billingAddressResp = billingBO.getBillingAddress(accountNumber, companyCode, sessionId);
				    
				        
				    if(CommonUtil.trimZipCode(billingAddressResp.getStrZip()).equalsIgnoreCase(zip.trim())){
				    	GetAccountDetailsResponse getAccountDetailsResponse =billingBO.getAccountDetails(accountNumber, companyCode, brandName, sessionId);
				    
				  	    boolean EMAIL_VERIFIED=true;
				   	    	
				  
					  	if((getAccountDetailsResponse.getEmailID().equalsIgnoreCase(emailID))&&EMAIL_VERIFIED){
						String TransID = profileDAO.insertTransaction(userUniqueID, companyCode);
						
						String resetPasswordURL = envMessageReader.getMessage(GME_MYACCOUNT_PASSWORD_RESET_URL)+TransID;
						logger.info("Reset URL ="+resetPasswordURL);
						
						String nonZeroAccountNumber = CommonUtil.stripLeadingZeros(accountNumber);
						
						HashMap<String, String> templateProperties = new HashMap<String, String>();
		                templateProperties.put("ACCOUNT_NUMBER", nonZeroAccountNumber);
		               templateProperties.put("LOGIN_URL", envMessageReader.getMessage(GME_MYACCOUNT_LOGIN_URL));
		                
		                    Boolean status = false;
		                    if(StringUtils.isEmpty(languageCode))
		                    	languageCode="E";
		                    
		                	if(languageCode.equalsIgnoreCase("ES")){
		                		resetPasswordURL = resetPasswordURL+"/ES";;
		                		 templateProperties.put("RESET_PASSWORD_URL",resetPasswordURL);
		                		//status = EmailHelper.sendMail( emailID ,"", GME_PASSWORD_CHANGE_ES_US, templateProperties, companyCode);
		                		logger.info("Spanish Conf mail send for Password reset: " + status);
								}else{
 		
			                		logger.info("Setting languageCode to English");
			                		resetPasswordURL = resetPasswordURL+"/EN";
			                		 templateProperties.put("RESET_PASSWORD_URL",resetPasswordURL);
			                		status = EmailHelper.sendMail( emailID ,"", GME_PASSWORD_CHANGE_EN_US, templateProperties, companyCode);
									logger.info("English Conf mail send for Password reset: " + status);
		
		                	}
		                	
		                
		                response.setIsCallSuccess(BOOLEAN_TRUE);
		                response.setResultCode(RESULT_CODE_SUCCESS);
		                response.setResultDescription(MSG_SUCCESS);
		                response.setMessageText("Password reset call sucessfull");
					 }
					 else{
						 logger.info("Email Address validation failed");
						 response.setResultCode(RESULT_CODE_SIX);
			             response.setResultDescription(RESULT_DESCRIPTION_EMAIL_NOTFOUND);
						 response.setIsCallSuccess(BOOLEAN_FALSE);
						 response.setMessageText("Email Address validation failed");
					 }
					 }
					 else{
				    	response.setResultCode(RESULT_CODE_SEVEN);
		            	response.setResultDescription(RESULT_DESCRIPTION_INVALID_ZIP_CODE);
		            	response.setIsCallSuccess(BOOLEAN_FALSE);
				    	response.setMessageText("Invalid Zip Code");
				    }
				}else{
					
					logger.info("Invalid Contract Account details- Account validation failed");
					response.setResultCode(RESULT_CODE_TWO);
					response.setResultDescription(RESULT_CODE_INVALID_ACCOUNT_NUMBER_DESCRIPTION);
					response.setMessageText("Invalid Contract Account details- Account validation failed");
				}
			}else{
				response.setResultCode(RESULT_CODE_FIVE);
				response.setResultDescription(RESULT_CODE_INVALID_INPUT_PARAMETERS);
				response.setMessageText("Invalid Input Parameters");
				response.setIsCallSuccess(BOOLEAN_FALSE);
			}
			}catch (Exception e) {
					
					logger.error("Exception is forgotPassword"+e);
					response.setIsCallSuccess(BOOLEAN_FALSE);
					response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
					utilityloggerHelper.logTransaction("forgotPassword", false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
					if(logger.isDebugEnabled()){
						logger.debug(XmlUtil.pojoToXML(request));
						logger.debug(XmlUtil.pojoToXML(response));
					}

				}
			      
		return response;
	}
	
/**
 * This method is to get the validate forgot password link.
 * @author Cuppala
 * @param transactionId
 * @param companyCode
 * @param brandName
 * @return
 */
	
	public ValidatePasswordLinkResponse validateForgotPasswordLink(String transactionId,String companyCode,String brandName, String sessionId){
		
		ValidatePasswordLinkResponse response= new ValidatePasswordLinkResponse();
		long startTime = CommonUtil.getStartTime();
		String request = transactionId+"transactionId";
		String userName = profileDAO.getUserNameforTxn(transactionId);;
		
		try {
			if(companyCode.equalsIgnoreCase("0271") && brandName.equalsIgnoreCase("GME") && !StringUtils.isEmpty(transactionId)){
				if(profileDAO.validatePasswordLink(transactionId))
				{
				response.setValid(true);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				response.setUserName(userName);
				response.setMessageText("Link Valid");
								
				}
				else
				{
				
				response.setValid(false);
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				response.setUserName(userName);
				response.setMessageText("Link Expired");
				
				}
				
			}
			else
			{
				response.setResultCode(RESULT_CODE_FIVE);
				response.setResultDescription(RESULT_CODE_INVALID_INPUT_PARAMETERS);
				response.setMessageText("Invalid Paramenter");
			}
			
		} catch (Exception e) {
			logger.error("Exception is validateForgotPasswordLink"+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("validateForgotPasswordLink", false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		}
		return response;
		
	}

		
		
	
	
	// Start | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019  	
		/**
		 * This method is to get the user name or/and account number from ldap.
		 * 
		 * @author mshukla1
		 * @param userId
		 * @return
		 * 
		 * 	Updated/Refactored of @see {@link #_getUserOrAcctNumber_Old(String, String, String)} 
		 * 		by Jenith on 2/5/2019 
		 * 
		 *  for GME Admin Tool Issue Fix.
		 *  
		 *  @author JYogapa1
		 *  
		 */
		public UserInfoResponse getUserOrAcctNumber(String userId, String companyCode, String sessionId) {
			long startTime = CommonUtil.getStartTime();
			String METHOD_NAME = "getUserOrAcctNumber";
			
			// User info response with empty.
			UserInfoResponse userResponse = null;
			
			String requestParams = "userId=" + userId + ",companyCode=" + companyCode;
			try {
				// Get User info response from LDAP.
				userResponse = ldapHelper.getUserorAcctInfo(userId, companyCode);
				// Get CA from User info response.
				String caNumber = userResponse.getAccountNumber();
				
				String bpNumber = Constants.EMPTY;
				
				// User response is empty.
				if (StringUtils.isBlank(caNumber)
						&& StringUtils.isBlank(userResponse.getUserName())) {
					
					// Set empty user response
					userResponse.setAccountNumber(Constants.EMPTY);
					userResponse.setUserName(Constants.EMPTY);
					userResponse.setEmailID(Constants.EMPTY);
					
					// Set failure code in response.
					userResponse.setResultCode(Constants.RESULT_CODE_THREE);
					userResponse.setResultDescription(Constants.RESULT_CODE_DESCRIPTION_NO_DATA);
				} else {
					logger.info("Sets User Profile E-mail.");
					
					// Set EMail and success response.
					this.loadUserProfileEmail(userResponse, caNumber, bpNumber, companyCode, sessionId);
				}
				
				// Log transaction.
				utilityloggerHelper.logTransaction(METHOD_NAME, false, requestParams, userResponse,
						userResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				// Log request and response.
				super.logRequestAndResponse(logger, requestParams, userResponse);
			} catch (Exception e) {
				// Set failure code in response.
				this.setUserInfoExceptionMessage(userResponse);
				// Log transaction.
				utilityloggerHelper.logTransaction(METHOD_NAME, false, requestParams, userResponse,
						userResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				
				// Log request and response.
				super.logRequestAndResponse(logger, requestParams, userResponse);
				
				throw new OAMException(200, e.getMessage(), userResponse);
			}
			
			return userResponse;
		}
	
	/**
	 * Gets Profile call and reads BP number to get Email.
	 * 
	 * @param userResponse
	 * @param caNumber
	 * @param bpNumber
	 * @param companyCode
	 * @param sessionId
	 * @throws Exception
	 * 
	 * @author JYogapa1
	 */
	private void loadUserProfileEmail(UserInfoResponse userResponse, String caNumber, String bpNumber,
			String companyCode, String sessionId) throws Exception {
		logger.info("CCS Profile Call to fetch BP");
		Map<String, Object> profileResponseMap = null;
		ProfileResponse profileResponse = null;
		// Get profile info as map.
		profileResponseMap = profileService.getProfile(caNumber, companyCode, sessionId);
		// Get profile data from map if it is not empty.
		if (profileResponseMap != null && profileResponseMap.size() != 0) {
			// Get profile data response.
			profileResponse = (ProfileResponse) profileResponseMap.get(PROFILE_RESPONSE_KEY);
		}
		// Get BP number from profile response.
		if (profileResponse != null && profileResponse.getContractAccountDO() != null) {
			bpNumber = profileResponse.getContractAccountDO().getStrBPNumber();
		}
		// Get EMail.
		String emailAddress = this.getEmailAddress(caNumber, bpNumber, companyCode, sessionId);
		// Set Email in response.
		userResponse.setEmailID(emailAddress);
		// Set success code in response.
		userResponse.setResultCode(RESULT_CODE_SUCCESS);
		userResponse.setResultDescription(MSG_SUCCESS);
	}

	/**
	 * Sets exception error message in user info response.
	 * 
	 * @param userResponse
	 */
	private void setUserInfoExceptionMessage(UserInfoResponse userResponse) {
		if (userResponse == null) {
			userResponse = new UserInfoResponse();
		}

		// Set failure code in response.
		userResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		userResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
	}
	
	/**
	 * Calls the CRM profile call and returns the Email for the given CA and BP.
	 * 
	 * @param caNumber
	 * @param bpNumber
	 * @param companyCode
	 * @param sessionId
	 * 
	 * @return E-mail
	 * 
	 * @author JYogapa1
	 * 
	 * @throws Exception 
	 */
	public String getEmailAddress(String caNumber, String bpNumber, String companyCode, String sessionId)
			throws Exception {
		logger.info("CRM Profile Call to fetch Email");
		String emailFound = null;
		CrmProfileRequest crmProfileRequest = new CrmProfileRequest();
		CrmProfileResponse crmProfileResponse = null;
		
		// Set crm profile request input parameters.
		crmProfileRequest.setStrCANumber(caNumber);
		crmProfileRequest.setStrBPNumber(bpNumber);
		// Call getCRMProfile NRGWS.
		try {
			crmProfileResponse = profileService.getCRMProfile(crmProfileRequest, companyCode, sessionId);
			emailFound = (crmProfileResponse != null ? crmProfileResponse.getEmailID() : Constants.EMPTY);
		} catch (Exception e) {
			logger.error(
					"getUserOrAcctNumber > getEmailAddress: Email address is not found due to exception in the getCRMProfile call. ",
					e.getMessage());
			throw e;
		}
		return emailFound;
	}
	
	// End | US16458 | MBAR: Sprint 14 - GME Admin tool password reset issue fixes. | Jenith | 2/5/2019  	

	
	/**
	 * This method is to to update the billing address in the CSS
	 * 
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param addressId
	 * @param country
	 * @param zip
	 * @return
	 */
	public UpdateBillingAddressResponse updateBillingAddress(String accountNumber, String streetName, String streetNum, String city, String state
			, String aptNum, String country, String zip, String companyCode, String bpNumber,String poBox, String sessionId, String brandName){
			
		UpdateBillingAddressResponse updateBillingAddressResponse = new UpdateBillingAddressResponse();
		
		com.multibrand.domain.UpdateAddressResponse response = null;
		try {
			
			UpdateAddressRequest request =  new UpdateAddressRequest();
			request.setStrCANumber(accountNumber);
			request.setStrCity(city);
			request.setStrAptNum(aptNum);
			request.setStrState(state);
			request.setStrZip(zip);
			request.setStrCountry(country);
			request.setStrBPNumber(bpNumber);
			request.setStrStreetName(streetName);
			request.setStrStreetNum(streetNum);
			request.setStrCompanyCode(companyCode);
			request.setStrPOBox(poBox);
			response = profileService.updateBillingAddress(request, companyCode, sessionId);
			logger.info(response.getErrorCode());
			logger.info(response.getErrorMessage());
			if (response.getErrorCode().equals(null)|| response.getErrorCode().equals("") )
			{
				updateBillingAddressResponse.setResultCode(RESULT_CODE_SUCCESS);
				updateBillingAddressResponse.setResultDescription(MSG_SUCCESS);
			}
			else{
				updateBillingAddressResponse.setResultCode(RESULT_CODE_NO_DATA);
				updateBillingAddressResponse.setResultDescription(response.getErrorCode());
			}
				
		} catch (RemoteException e) {
			
			updateBillingAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateBillingAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),updateBillingAddressResponse);
		} catch (Exception e) {
			
			updateBillingAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateBillingAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),updateBillingAddressResponse);
		}
		return updateBillingAddressResponse;
	}
	/**
	 * 
	 * @author Kdeshmu1
	 * @param userName
	 * @param oldUserName
	 * @param companyCode
	 * @return changeUsernameResponse  ChangeUsernameResponse
	 */
	public ChangeUsernameResponse changeUsername(String userName,String oldUserName, String companyCode, String sessionId) {
		
		ChangeUsernameResponse changeUsernameResponse = new ChangeUsernameResponse();
		
		try{
			ChangeUsrNameResponse response = new ChangeUsrNameResponse();
			ChangeUsrNameRequest changeUsrNameRequest =new ChangeUsrNameRequest();
			changeUsrNameRequest.setStrCompanyCode(companyCode);
			changeUsrNameRequest.setStrLDAPOrg(Constants.LDAP_ORG_GME);
			//changeUsrNameRequest.setStrLDAPOrg("pennywise.com");
			changeUsrNameRequest.setStrNewUserName(userName);
			changeUsrNameRequest.setStrOldUserName(oldUserName);
			
			response = ldapService.changeUsername(changeUsrNameRequest, companyCode, sessionId);
			System.out.println(response.getStrErrorCode());
			if (response.getStrErrorCode()==null || response.getStrErrorCode().equals("")){
				changeUsernameResponse.setResultCode(RESULT_CODE_SUCCESS);
				changeUsernameResponse.setResultDescription(MSG_SUCCESS);
			}
			else if(response.getStrErrorCode()!=null && response.getStrErrorCode().equals("MSG_USER_EXISTS")){
				changeUsernameResponse.setResultCode(RESULT_CODE_FOUR);
				changeUsernameResponse.setResultDescription(RESULT_DESCRIPTION_USERNAME_EXISTS);
			}
			else {
				changeUsernameResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				changeUsernameResponse.setResultDescription(response.getStrErrorCode());
			}
		}catch (RemoteException e) {
			
			changeUsernameResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			changeUsernameResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),changeUsernameResponse);
		}
		catch (Exception e) {
			
			changeUsernameResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			changeUsernameResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),changeUsernameResponse);
		}
		return changeUsernameResponse;
	}
	
	/**
	 * @author Kdeshmu1
	 * @param accountNumber
	 * @param phone
	 * @param email
	 * @param bpNumber
	 * @param uniqueID
	 * @param userName
	 * @param companyCode
	 * @return updateContactInfoResponse UpdateContactInfoResponse
	 */
	public UpdateContactInfoResponse updateContactInfo(String accountNumber, String homePhone,String email,String bpNumber,
		String uniqueID,String userName,String companyCode,String workPhone, String cellPhone, String sessionId, String brandName,String marketingPref, String existingEmail, String billingOptionChangeFlag) {

		UpdateContactInfoResponse updateContactInfoResponse = new UpdateContactInfoResponse();
		
		try{
			com.multibrand.domain.UpdateContactResponse response = new com.multibrand.domain.UpdateContactResponse();
			UpdateContactRequest updateContactRequest =new UpdateContactRequest();
			
			// Start GME Prepay OAM
			UpdateContactRequestAttNamValPairMapEntry updateContactRequestAttNamValPairMapEntry[] = new UpdateContactRequestAttNamValPairMapEntry[1];
			
			if (StringUtils.isNotBlank(marketingPref)) {
			logger.info("Updating marketing pref MKT_EMAIL for CA/BP:::: "+accountNumber+"/"+bpNumber+" marketingPref value "+marketingPref);
			if(FLAG_YES.equalsIgnoreCase(marketingPref))
				updateContactRequestAttNamValPairMapEntry[0] = new UpdateContactRequestAttNamValPairMapEntry(
						MKT_EMAIL, FLAG_YES);
			else
				updateContactRequestAttNamValPairMapEntry[0] = new UpdateContactRequestAttNamValPairMapEntry(
						MKT_EMAIL, FLAG_N);
			}
			// End GME Prepay OAM
			
	    	updateContactRequest.setAttNamValPairMap(updateContactRequestAttNamValPairMapEntry);
			updateContactRequest.setStrCompanyCode(companyCode);
			updateContactRequest.setBrandName(brandName);
		
			// set the email flag only if the email is not null or empty
			if(StringUtils.isNotEmpty(email))updateContactRequest.setIsEmailUpdated(Constants.FLAG_YES);
			
			updateContactRequest.setStrCANumber(accountNumber);
			updateContactRequest.setStrEmailId(email);
			//updateContactRequest.setStrLDAPOrg("pennywise.com");
			if(!CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
			updateContactRequest.setStrLDAPOrg(Constants.LDAP_ORG_GME);
			}
			updateContactRequest.setStrBPNumber(bpNumber);
			updateContactRequest.setStrUniqueId(uniqueID);
			updateContactRequest.setUserName(userName);
			UpdatePhoneDO phone = null;
			
			List<UpdatePhoneDO> phones = new ArrayList<UpdatePhoneDO>();
			
			
				phone = new UpdatePhoneDO();
		    	phone.setRemark(REMARK_HOME);
		    	phone.setPhoneNumber(homePhone);
		    	phones.add(phone);
		    	
			
			
		    	phone = new UpdatePhoneDO();
		    	phone.setRemark(REMARK_WORK);
		    	phone.setPhoneNumber(workPhone);
		    	phones.add(phone); 	
			    
			
			
		    	phone = new UpdatePhoneDO();
		    	phone.setRemark(REMARK_CELL);
		    	phone.setPhoneNumber(cellPhone);
		    	phones.add(phone);
			    
		    // Do not set the telephone updated flag if all phones are null or empty 
	    	if(!(StringUtils.isEmpty(homePhone)) || !(StringUtils.isEmpty(workPhone)) || !(StringUtils.isEmpty(cellPhone))) {
	    		updateContactRequest.setPhoneDO(phones.toArray(new UpdatePhoneDO[0])); 
	    		updateContactRequest.setIsTelephoneUpdated(Constants.FLAG_YES);
	    	
	    	}
	    	
	    		    	
			response = profileService.updateContactInfoWS(updateContactRequest, companyCode, sessionId);
			
			if (response.getErrorCode()==null || response.getErrorCode().equals("")){
				updateContactInfoResponse.setResultCode(RESULT_CODE_SUCCESS);
				updateContactInfoResponse.setResultDescription(MSG_SUCCESS);
				
				// send mail for email update for Cirro, and if this api is NOT called after billing option update
				if(companyCode!=null && CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !FLAG_TRUE.equalsIgnoreCase(billingOptionChangeFlag)){
					if(StringUtils.isNotEmpty(email)){
						// if email is not empty or blank or null then it was marked for updation and if control is here, it means that its updated successfully
						Map<String, Object> responseMap = new HashMap<String, Object>();
						ProfileResponse profileResponse = null;
						responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
						if(responseMap!= null && responseMap.size()!= 0)
						{
							profileResponse= (ProfileResponse)responseMap.get("profileResponse");
						}
						ContractAccountDO caDO  = null;
						
						if (profileResponse != null) {
							caDO = profileResponse.getContractAccountDO();
							if(caDO != null){
								
								logger.info("Found profile for given account number, Found contract account info");
								
								String caName = caDO.getCAName();
								
								if(caName != null && !caName.equalsIgnoreCase("")){
									logger.info("Found CA Name : " + caName + ", Sending Mail for email update");
									
									HashMap<String, String> templateProperties = new HashMap<String,String>();

									templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));


									try {
										
										String toEmailIds = email;
										if(StringUtils.isNotEmpty(existingEmail)){
											toEmailIds = email +","+ existingEmail;
										}

									Boolean status = EmailHelper.sendMail( toEmailIds ,"", CIRRO_EMAIL_CHANGE_EXTERNAL_ID_EN, templateProperties, companyCode);

									logger.info("Email for email id change sent status : " + status);

									} catch (Exception e) {
								    logger.info("Exception in sending email for email change " );
									// TODO Auto-generated catch block
			                        logger.info(e);
									logger.error(e);

									}
									
									
								} else{
									logger.info("Couldn't find CA Name : " + caName + ", so couldn't send mail for email id update");	
								}
								
							}else{
								logger.info("Found the profile for given account number but couldn't find the contract account info, so couldn't send mail for email id update");
							}                 
						 
									
						}else{
							logger.info("Couldn't find the profile for given account number, so couldn't send mail for email id update");
						}
						
						
						
					}				
					
				}
				
				
			}else{
				updateContactInfoResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				logger.info("::::::::::::::::errrrror code :::" +response.getErrorCode() );
				logger.info("::::::::::::::::error Message :::" +response.getErrorMessage() );
				updateContactInfoResponse.setResultDescription(response.getErrorCode());
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			updateContactInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateContactInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),updateContactInfoResponse);
		}
		return updateContactInfoResponse;
	}
	
	
	public GetContractInfoResponse getContractInfo(String accountNumber,String bpNumber, String esid, String contractId, String languageCode, String companyCode, String brandName, String sessionId) {

		GetContractInfoResponse response = new GetContractInfoResponse();
		logger.info("Start getContractINfo - in ProfileBO");
		try {
			response = profileService.getContractInfo(accountNumber,bpNumber,esid,contractId,languageCode, companyCode, sessionId);
			
		} catch (RemoteException e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("End getContractINfo - in ProfileBO");
		return response;
	}
	
	public SmartMeterCheckResponse getSmartMeterCheck(String accountNumber,
			String esid, String companyCode, String sessionId)
	{
		
		EsidProfileResponse response  = null;
		SmartMeterCheckResponse smeterCheckRep = new SmartMeterCheckResponse();
		try {
			
			response = profileService.getESIDProfile(companyCode, esid, sessionId);
			
			if (!StringUtils.isEmpty(response.getErrorCode())) {
				smeterCheckRep.setResultCode(RESULT_CODE_CCS_ERROR);
				smeterCheckRep.setResultDescription(response.getErrorCode());
				
			} else {
				
				String strMeterType = response.getMeterType();
				if (StringUtils.isNotBlank(strMeterType)
						&& (METER_TYPE_AMSR.equals(strMeterType) || METER_TYPE_AMSM.equals(strMeterType))) {
					smeterCheckRep.setFlag(FLAG_TRUE);
					
				} else {
					smeterCheckRep.setFlag(FLAG_FALSE);

				}

				smeterCheckRep.setResultCode(RESULT_CODE_SUCCESS);
				smeterCheckRep.setResultDescription(MSG_SUCCESS);
			}
			
			
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			smeterCheckRep.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			smeterCheckRep.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),smeterCheckRep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			smeterCheckRep.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			smeterCheckRep.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),smeterCheckRep);
		}
		

		return smeterCheckRep;
	}
	
	public ProductUpdateResponse productUpdate(String accountNumber, String action ,
			 String objectId, String extUi, String enrollType ,
			 String requestDate , String manuPartNo, String companyCode, String sessionId)
	{
		ProductUpdateResponse productResponse = new ProductUpdateResponse();
		try
		{
			String strRequestDate = CommonUtil.changeDateFormat(requestDate, MM_dd_yyyy, yyyy_MM_dd);
			productResponse = profileService.productUpdate(accountNumber, action, objectId, extUi, enrollType, strRequestDate, manuPartNo, companyCode, sessionId);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			productResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			productResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),productResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			productResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			productResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),productResponse);
		}
		return productResponse;
	}
	
	public EnvironmentImpactsResponse environmentalImpacts(String accountNumber, String companyCode, String sessionId){
		EnvironmentImpactsResponse response = null;
				
		try {
			response =profileService.environmentImpacts(accountNumber, companyCode, sessionId);
			
			
		} catch (RemoteException e) {
			logger.info("Remote Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),response);
		}
		catch (Exception e) {
			logger.info("Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),response);
		}		
		return response;
	}
	

	public SecondaryNameResponse secondaryNameUpdate(String accountNumber,
			String bpid,String action, String bpid2, String firstName, String lastName,
			String middleName, String validFrom, String validUntil,
			String companyCode, String sessionId) {
		logger.info("ProfileBO - secondaryNameUpdate method starts...");
		SecondaryNameUpdateReqVO request = new SecondaryNameUpdateReqVO();
		SecondaryNameResponse response = new SecondaryNameResponse();
		
		request.setAccountNumber(accountNumber);
		request.setAction(action);
		request.setBpid(bpid);
		request.setBpid2(bpid2);
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setMiddleName(middleName);
		request.setValidFrom(CommonUtil.changeDateFormat(validFrom, MM_dd_yyyy, yyyy_MM_dd));
		request.setValidUntil(CommonUtil.changeDateFormat(validUntil, MM_dd_yyyy, yyyy_MM_dd));
		
		try{
			response = profileService.secondaryNameUpdate(request, companyCode, sessionId);
			if(response.getSecondaryNames() != null && response.getSecondaryNames().length>0)
			{
				response.setResultCode(RESULT_CODE_SUCCESS);
			    response.setResultDescription(MSG_SUCCESS);
			}
		}catch(RemoteException re)
		{
			logger.info("Remote Exception occurred...in secondaryNameUpdate-ProfileBO");
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, re.getMessage(), response);
		}
		catch (Exception e) {
			logger.info("Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("ProfileBO - secondaryNameUpdate method end...");
		
		return response;
	}
	
	/**
	 * @author smuruga1
	 * @param accountNumber
	 * @param contractNumber
	 * @param userId
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public WsServiceResponse wsDeEnrollService(String accountNumber,
			String contractNumber, String companyCode, String sessionId)
	{
		logger.info("ProfileBO - wsDeEnrollService method starts...");
		long startTime = CommonUtil.getStartTime();
		WseServiceRequest request = new WseServiceRequest();
		WsServiceResponse genricResp = new WsServiceResponse();
		WseServiceResponse response = null;
		request.setCaNum(CommonUtil.paddedCa(accountNumber));

		request.setCoNum(contractNumber);
		request.setChannel(WS_CHANNEL);
		request.setContactLog(WS_CONTACT_LOG);

		request.setContActivity(WS_CONTACT_ACTIVITY_D_EN);
		request.setContText(WS_CONTACT_TEXT_D_EN);
		String endDate = CommonUtil.getCurrentDateYYYYMMDD();
		logger.info("endDate ---" + endDate);
		request.setReqEndDate(endDate);
		request.setTerminationDate(endDate);

		request.setContClass(WS_CONTACT_CLASS);
		request.setProgram(WS_PROGRAM);
		request.setPrevDate("");

		try {
			response = profileService.wsDeEnrollService(request,
					companyCode, sessionId);
			if (response.getReturnCode() != null && response
							.getReturnCode().equalsIgnoreCase("0")) {
				genricResp.setResultCode(RESULT_CODE_SUCCESS);
				genricResp.setResultDescription(MSG_SUCCESS);
				genricResp.setSuccessCode(response.getReturnCode());
				
			} else {
				genricResp.setResultCode(RESULT_CODE_NO_MATCH);
				genricResp.setResultDescription(RESULT_DESCRIPTION_DEENROLL_FAILED);
				genricResp.setSuccessCode(response.getReturnCode());
			}
		}  catch (Exception e) {
			logger.info("Exception wsEnrollDeEnrollService" + e);
			utilityloggerHelper.logTransaction("wsEnrollDeEnrollService", false,
					request, e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			genricResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			genricResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		//utility logging already done in service layer for proper response
		/*utilityloggerHelper.logTransaction("wsEnrollDeEnrollService", false,
				request, genricResp, genricResp.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		logger.info(XmlUtil.pojoToXML(request));
		logger.info(XmlUtil.pojoToXML(genricResp));*/		
		logger.info("ProfileBO - wsEnrollDeEnrollService method end...");
		return genricResp;

	}
	
	
	/**
	 * @author smuruga1
	 * @param contractNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public WsEnrollmentResponse wsEnrollService(String contractNumber, String companyCode, String sessionId)
	{
		
		logger.info("ProfileBO - wsEnrollService method starts...");
		long startTime = CommonUtil.getStartTime();
		WseEnrollmentRequest request = new WseEnrollmentRequest();
		WsEnrollmentResponse wseEnrollResponse = new WsEnrollmentResponse();
		WseEnrollmentResponse response = null;
		
		request.setChannel(WS_CHANNEL);
		request.setContract(contractNumber);
		request.setCompanyCode(companyCode);
		request.setProgramName(WS_PROGRAM);
		
		try {
			response = profileService.wsEnrollService(request,
					companyCode, sessionId);
			if ( response != null && response.getExCode() != null && response
							.getExCode().equalsIgnoreCase("0") ) {
				wseEnrollResponse.setResultCode(RESULT_CODE_SUCCESS);
				wseEnrollResponse.setResultDescription(MSG_SUCCESS);
				wseEnrollResponse.setWseRequestDate(CommonUtil
						.changeDateFormat(response.getWseRequestDate(),
								DT_AVGTEMP_FMT, DT_FMT));
				wseEnrollResponse.setWseTerminationDate(CommonUtil
						.changeDateFormat(response.getWseTerminationDate(),
								DT_AVGTEMP_FMT, DT_FMT));
				wseEnrollResponse.setSucessCode(response.getExCode());
				wseEnrollResponse.setErrorCode(CommonUtil
						.getBlankString(response.getErrorCode()));
				wseEnrollResponse.setErrorMessage(CommonUtil
						.getBlankString(response.getErrorMessage()));
				
			} else {
				wseEnrollResponse.setResultCode(RESULT_CODE_NO_MATCH);
				wseEnrollResponse.setResultDescription(RESULT_DESCRIPTION_ENROLL_FAILED);
				wseEnrollResponse.setErrorCode(CommonUtil
						.getBlankString(response.getErrorCode()));
				wseEnrollResponse.setErrorMessage(CommonUtil
						.getBlankString(response.getErrorMessage()));
				wseEnrollResponse.setWseRequestDate(CommonUtil.getBlankString(
						response.getWseRequestDate()).replaceAll("-", ""));
				wseEnrollResponse.setWseTerminationDate(CommonUtil
						.getBlankString(response.getWseTerminationDate())
						.replaceAll("-", ""));
				wseEnrollResponse.setSucessCode(response.getExCode());
				
			}
		}  catch (Exception e) {
			logger.error("Exception wsEnrollDeEnrollService" + e);
			utilityloggerHelper.logTransaction("wsEnrollService", false, request,
					e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			wseEnrollResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			wseEnrollResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		//utility logging already done in service layer for proper response
		/*utilityloggerHelper.logTransaction("wsEnrollService", false, request,
				wseEnrollResponse, wseEnrollResponse.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		logger.info(XmlUtil.pojoToXML(request));
		logger.info(XmlUtil.pojoToXML(wseEnrollResponse));	*/	
		logger.info("ProfileBO - wsEnrollService method end...");
		return wseEnrollResponse;
		
		
	}
	
	/**
	 * @author smuruga1
	 * @param contractNumber
	 * @param esid
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public WseEligiblityStatusResponse wseEligibilityStatus(String contractNumber,String esid, String companyCode, String sessionId)
	{
		logger.info("ProfileBO - wseEligibilityStatus method starts...");
		WseEligiblityStatusResponse wseResponse = new WseEligiblityStatusResponse();
		
		AllAccountDetailsRequest  request = new AllAccountDetailsRequest();
		request.setCompanyCode(companyCode);
		WseEsenseEligibilityRequest wseEsenseEligibilityRequest=  new WseEsenseEligibilityRequest();
		wseEsenseEligibilityRequest.setContractId(contractNumber);
		wseEsenseEligibilityRequest.setEsid(esid);
		request.setWseEsenseEligibilityRequest(wseEsenseEligibilityRequest);
		logger.debug("contractNumber"+contractNumber);
		logger.debug("esid"+esid);
		logger.debug("companyCode"+companyCode);
		try {
			WseEsenseEligibilityResponse wseEligibilityResponse = profileService
					.wseEsenseEligibilityStatus(request, companyCode, sessionId);
			logger.info("ProfileBO - After Service method ...");
			if (wseEligibilityResponse != null
					&& (wseEligibilityResponse.getWseEsenseEligibilityItem() != null && wseEligibilityResponse
							.getWseEsenseEligibilityItem().length > 0)) {
				logger.info("ProfileBO - After Service method .IF LOOP..");
				wseResponse.setResultCode(RESULT_CODE_SUCCESS);
				wseResponse.setResultDescription(MSG_SUCCESS);
				
				List<WseEsenseEligibility> responseList = new LinkedList<WseEsenseEligibility>();
				
				for (WseEsenseEligibilityDO contractEligibilty : wseEligibilityResponse
						.getWseEsenseEligibilityItem()) {
					WseEsenseEligibility eligibility = new WseEsenseEligibility();
					logger.debug("ProfileBO - INSIDE FOR LOOOP.."+contractEligibilty
							.getContractId());
					eligibility.setContractNumber(contractEligibilty
							.getContractId());
					eligibility
							.setEligibilty(CommonUtil
									.getBlankString(contractEligibilty
											.getEligibilty()));
					eligibility.setEsid(contractEligibilty.getEsid());
					eligibility.setProgName(CommonUtil
							.getBlankString(contractEligibilty.getProgName()));
					eligibility.setStatus(CommonUtil
							.getBlankString(contractEligibilty.getStatus()));
					eligibility.setErrorCode(CommonUtil
							.getBlankString(contractEligibilty.getErrorCode()));
					eligibility.setErrorMessage(CommonUtil
							.getBlankString(contractEligibilty
									.getErrorMessage()));
					responseList.add(eligibility);
				}
				wseResponse.setWseEsenseEligibilityList(responseList);

			} else {
				logger.info("ProfileBO - After Service method .ELSE LOOP..");
				wseResponse.setResultCode(RESULT_CODE_NO_DATA);
				wseResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				List<WseEsenseEligibility> responseList = new LinkedList<WseEsenseEligibility>();
				wseResponse.setWseEsenseEligibilityList(responseList);
			}

		} catch (Exception e) {
			
			long startTime = CommonUtil.getStartTime();
			logger.error("Exception wseEligibilityStatus" + e);
			utilityloggerHelper.logTransaction("wseEligibilityStatus", false, request,
					e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			wseResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			wseResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			List<WseEsenseEligibility> responseList = new LinkedList<WseEsenseEligibility>();
			wseResponse.setWseEsenseEligibilityList(responseList);
			throw new OAMException(200, e.getMessage(), wseResponse);
		}
		
		logger.info("ProfileBO - wseEligibilityStatus method end...");

		return wseResponse;
	}
	
	
	/**
	 * 
	 * @param bpId
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public CirroStructureResponse getCirroStructureCall(String bpId,
			String companyCode, String brandName,String noOfDayBack, String sessionId)
	{
		logger.info("ProfileBO - getCirroStructureCall method starts...");
		CirroStructureResponse cirroStructureResponse = new CirroStructureResponse();

		CirroStructureCallResponse cirroServiceResponse = null;
		CirroStructureCallRequest cirroStructureCallRequest = new CirroStructureCallRequest();

		cirroStructureCallRequest.setCompanyCode(companyCode);
		cirroStructureCallRequest.setStrBpId(bpId);
		cirroStructureCallRequest.setNoOfDaysBack(noOfDayBack);
		cirroStructureCallRequest.setBrandName(brandName);
		System.out.println("bpId"+bpId);
		System.out.println("companyCode"+companyCode);
		System.out.println("noOfDayBack"+noOfDayBack);
		System.out.println("brandName"+brandName);
		try {
			cirroServiceResponse = profileService.getCirroStructureCall(
					cirroStructureCallRequest, companyCode, sessionId);
						
			if (cirroServiceResponse != null
					&& (cirroServiceResponse.getErrorCode() == null || cirroServiceResponse
							.getErrorCode().equals("")) && (cirroServiceResponse.getContractAccountDo() != null  
							|| cirroServiceResponse.getContractDo() != null)) {
				logger.info("ProfileBO -getCirroStructureCall  After Service method .IF LOOP..");
				
				List<CirroContractAccountDO> cirroContractAccountDOList = new LinkedList<CirroContractAccountDO>();
				cirroStructureResponse
						.setContractAccountDo(cirroContractAccountDOList);

				List<CirroContractDO> cirroContractDOList = new LinkedList<CirroContractDO>();
				cirroStructureResponse.setContractDo(cirroContractDOList);
				
				cirroStructureResponse.setStrFirstName(cirroServiceResponse.getStrFirstName());
				cirroStructureResponse.setStrLastName(cirroServiceResponse.getStrLastName());
				cirroStructureResponse.setStrOrganization(cirroServiceResponse.getStrOrganization());
				cirroStructureResponse.setStrSuperBPFirstName(cirroServiceResponse.getStrSuperBPFirstName());
				cirroStructureResponse.setStrSuperBPLastName(cirroServiceResponse.getStrSuperBPLastName());
				cirroStructureResponse.setStrSuperBPOrganization(cirroServiceResponse.getStrSuperBPOrganization());
				
				com.multibrand.domain.CirroContractAccountDO []  responseActList = cirroServiceResponse.getContractAccountDo();
				if(responseActList != null) {
					
					for(int i=0; i<responseActList.length; i++) {
						com.multibrand.domain.CirroContractAccountDO tempAccountVO = responseActList[i];
						CirroContractAccountDO cTempAccountVO = new CirroContractAccountDO();
						cTempAccountVO.setCollectiveCA(CommonUtil.getBlankString(tempAccountVO.getCollectiveCA()));
						cTempAccountVO.setStrCANumber(tempAccountVO.getStrCANumber());
						cTempAccountVO.setStrBPId(tempAccountVO.getStrBPId());
						if(tempAccountVO.getStrConversionDate()!=null && !tempAccountVO.getStrConversionDate().equals("0000-00-00") && !tempAccountVO.getStrConversionDate().equals(""))
							cTempAccountVO.setStrConversionDate(CommonUtil.changeDateFormat(tempAccountVO.getStrConversionDate(), DT_AVGTEMP_FMT, DT_FMT));
						cirroContractAccountDOList.add(cTempAccountVO);
					}
					
				}
				
				com.multibrand.domain.CirroContractDO []  responseContractList = cirroServiceResponse.getContractDo();
				
				if(responseContractList != null) {
					
					for(int i=0; i<responseContractList.length; i++) {
						com.multibrand.domain.CirroContractDO tempContractVO = responseContractList[i];
						CirroContractDO cTempContracttVO = new CirroContractDO();
						cTempContracttVO.setStrCANumber(tempContractVO.getStrCANumber());
						cTempContracttVO.setStrContractID(tempContractVO.getStrContractID());
						cTempContracttVO.setStrContractLegacyAccount(tempContractVO.getStrContractLegacyAccount());
						cTempContracttVO.setStrESIID(tempContractVO.getStrESIID());
						cTempContracttVO.setStrBPId(tempContractVO.getStrBPId());
						cTempContracttVO.setCollectiveCA(CommonUtil.getBlankString(tempContractVO.getCollectiveCA()));
						
						if(tempContractVO.getStrMoveInDate()!=null && !tempContractVO.getStrMoveInDate().equals("0000-00-00") && !tempContractVO.getStrMoveInDate().equals(""))
							cTempContracttVO.setStrMoveInDate(CommonUtil.changeDateFormat(tempContractVO.getStrMoveInDate(),DT_AVGTEMP_FMT, DT_FMT));
						if(tempContractVO.getStrMoveOutDate()!=null && !tempContractVO.getStrMoveOutDate().equals("0000-00-00") && !tempContractVO.getStrMoveOutDate().equals(""))
							cTempContracttVO.setStrMoveOutDate((CommonUtil.changeDateFormat(tempContractVO.getStrMoveOutDate(),DT_AVGTEMP_FMT, DT_FMT)));
						cirroContractDOList.add(cTempContracttVO);
					}
					
				}
				
				//JavaBeanUtil.copy(cirroServiceResponse, cirroStructureResponse);

				cirroStructureResponse.setResultCode(RESULT_CODE_SUCCESS);
				cirroStructureResponse.setResultDescription(MSG_SUCCESS);
			} else {
				logger.info("ProfileBO - After Service method .ELSE LOOP..");
				cirroStructureResponse.setResultCode(RESULT_CODE_NO_DATA);
				cirroStructureResponse
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);

				List<CirroContractAccountDO> cirroContractAccountDO = new LinkedList<CirroContractAccountDO>();
				cirroStructureResponse
						.setContractAccountDo(cirroContractAccountDO);

				List<CirroContractDO> cirroContractDO = new LinkedList<CirroContractDO>();
				cirroStructureResponse.setContractDo(cirroContractDO);

			}

		} catch (Exception e) {
			long startTime = CommonUtil.getStartTime();
			logger.error("Exception getCirroStructureCall" + e);
			System.out.println("getCirroStructureCall"+e.getCause());
			System.out.println(e.getMessage());
			utilityloggerHelper.logTransaction("getCirroStructureCall", false, cirroStructureCallRequest,
					e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(cirroStructureCallRequest));
			cirroStructureResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			cirroStructureResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			List<CirroContractAccountDO> cirroContractAccountDO = new LinkedList<CirroContractAccountDO>();
			cirroStructureResponse
					.setContractAccountDo(cirroContractAccountDO);

			List<CirroContractDO> cirroContractDO = new LinkedList<CirroContractDO>();
			cirroStructureResponse.setContractDo(cirroContractDO);
		}

		logger.info("ProfileBO - getCirroStructureCall method END...");
		return cirroStructureResponse;

	}
	
	/**
	 * 
	 * @param caNumber
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public AcctValidationResponse validateAccount(String caNumber, String companyCode,
			String brandName, String sessionId)
	{
		logger.info("ProfileBO - validateAccount method starts...");
		AcctValidationRequest request = new AcctValidationRequest();
		AcctValidationResponse response = new AcctValidationResponse();
		
		request.setBrandName(brandName);
		request.setCompanyCode(companyCode);
		request.setContractAccountNumber(caNumber);
		
		try {
			com.multibrand.domain.AcctValidationResponse acctValidateResponse = profileService.validateAccount(request, companyCode, sessionId);
			
						
			if (acctValidateResponse != null
					&& (acctValidateResponse.getErrorCode() == null || acctValidateResponse
							.getErrorCode().equals(""))) {
				logger.info("ProfileBO -getCirroStructureCall  After Service method .IF LOOP..");
				System.out.println(" Gettin the value  "+acctValidateResponse.getErrorCode());
				
				response.setBpId(acctValidateResponse.getBpId());
				response.setBrandName(acctValidateResponse.getBrandName());
				response.setCaNumber(acctValidateResponse.getCaNumber());
				response.setCompanyCode(acctValidateResponse.getCompanyCode());
				if(acctValidateResponse.getDateOfBirth()!=null && !acctValidateResponse.getDateOfBirth().equals("0000-00-00") && !acctValidateResponse.getDateOfBirth().equals(""))
					response.setDateOfBirth(CommonUtil.changeDateFormat(acctValidateResponse.getDateOfBirth(),DT_AVGTEMP_FMT, DT_FMT));
				response.setIdNumber(acctValidateResponse.getIdNumber());
				response.setIdNumber2(acctValidateResponse.getIdNumber2());
				response.setNameFirst(acctValidateResponse.getNameFirst());
				response.setNameLast(acctValidateResponse.getNameLast());
				response.setOraganizationName(acctValidateResponse.getOraganizationName());
				
				response.setSuperBPIdNumber(acctValidateResponse.getSuperBPIdNumber());
				response.setSuperBPIdNumber2(acctValidateResponse.getSuperBPIdNumber2());
				response.setSuperBPNameFirst(acctValidateResponse.getSuperBPNameFirst());
				response.setSuperBPNameLast(acctValidateResponse.getSuperBPNameLast());
				response.setSuperBPOraganizationName(acctValidateResponse.getSuperBPOraganizationName());
				response.setSuperBPId(acctValidateResponse.getSuperBPId());
				if(acctValidateResponse.getSuperBPDateOfBirth()!=null && !acctValidateResponse.getSuperBPDateOfBirth().equals("0000-00-00") && !acctValidateResponse.getSuperBPDateOfBirth().equals(""))
					response.setSuperBPdateOfBirth(CommonUtil.changeDateFormat(acctValidateResponse.getSuperBPDateOfBirth(),DT_AVGTEMP_FMT, DT_FMT));
				
				com.multibrand.domain.ContractInfoDO []  responseActList = acctValidateResponse.getContractInfo();
				List<ContractInfoDO> contractVOList = new LinkedList<ContractInfoDO>();
				if(responseActList != null) {
					
					for(int i=0; i<responseActList.length; i++) {
						com.multibrand.domain.ContractInfoDO tempAccountVO = responseActList[i];
						ContractInfoDO tempContractInfo = new ContractInfoDO();
						tempContractInfo.setContractId(tempAccountVO.getContractId());
						tempContractInfo.setContractLegacyAccount(tempAccountVO.getContractLegacyAccount());
						tempContractInfo.setEsid(tempAccountVO.getEsid());
						contractVOList.add(tempContractInfo);
					}
					
				}
				response.setContractInfo(contractVOList);			
				//JavaBeanUtil.copy(cirroServiceResponse, cirroStructureResponse);

				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
			} 
			else if (acctValidateResponse != null && (acctValidateResponse.getErrorCode() != null && acctValidateResponse.getErrorCode().equals("MSG_CCSERR_1- Invalid Input"))) 
			{ 
				logger.info("ProfileBO - After Service method .ELSE LOOP..");
				response.setResultCode(CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA);
				response
						.setResultDescription(CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA_RESULT_DESCRIPTION);
				List<ContractInfoDO> contractVOList = new LinkedList<ContractInfoDO>();
				response.setContractInfo(contractVOList);

			} 
			else {
				logger.info("ProfileBO - After Service method .ELSE LOOP..");
				response.setResultCode(RESULT_CODE_NO_DATA);
				response
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				List<ContractInfoDO> contractVOList = new LinkedList<ContractInfoDO>();
				response.setContractInfo(contractVOList);
			}

		} catch (Exception e) {
			long startTime = CommonUtil.getStartTime();
			logger.error("Exception validateAccount" + e);
			System.out.println("validateAccount"+e.getCause());
			utilityloggerHelper.logTransaction("validateAccount", false, request,
					e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			List<ContractInfoDO> contractVOList = new LinkedList<ContractInfoDO>();
			response.setContractInfo(contractVOList);
			
		}

		logger.info("ProfileBO - validateAccount method END...");
		return response;
	}
	
	
/**
 * updating language preference through CCS call through NRGWS using either bpid or ca or both
 * 	
 * @param bpid
 * @param ca
 * @param languageCode
 * @param companyCode
 * @param brandName
 * @param sessionId
 * @return
 */
public UpdateLanguageResponse updateLanguage(String bpid, String ca, String languageCode, String companyCode, String brandName, String sessionId){
		
		logger.info("START-[ProfileBO-updateLanguage]");
		//padding the bpid with 0s
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		// padding the CA with 0s
		if(StringUtils.isNotEmpty(ca))ca = StringUtils.leftPad(ca, 12, "0");
		
		LanguageUpdateRequest request = new LanguageUpdateRequest();
		request.setCompanyCode(companyCode);
		if(StringUtils.isNotEmpty(bpid))request.setBpid(bpid);
		if(StringUtils.isNotEmpty(ca)) request.setContractAccount(ca);
		request.setLanguageCode(languageCode);
		request.setBrandName(brandName);

		
		UpdateLanguageResponse updateLanguageResponse =  new UpdateLanguageResponse();
		LanguageUpdateResponse response = null;
		
		try {
			response = profileService.updateLanguage(request, companyCode, sessionId);
			
			if (response.getErrorCode()!= null && !response.getErrorCode().equals("")) {
			
				updateLanguageResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)updateLanguageResponse.setResultDescription(response.getErrorMessage());
				updateLanguageResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)updateLanguageResponse.setErrorMessage(response.getErrorMessage());

			} else {
				
				if(StringUtils.isEmpty(response.getEReturnCode())){
					updateLanguageResponse.setResultCode(RESULT_CODE_SUCCESS);
					updateLanguageResponse.setResultDescription(MSG_SUCCESS);	
				}else{
					if( response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA)){
						updateLanguageResponse.setResultCode(RESULT_CODE_CCS_ERROR);
						updateLanguageResponse.setResultDescription(CCS_ERETURN_CODE_INPUT_ERROR_OR_INVALID_DATA_RESULT_DESCRIPTION);
					}if(response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_UPDATE_ERROR)){
						updateLanguageResponse.setResultCode(RESULT_CODE_CCS_ERROR);
						updateLanguageResponse.setResultDescription(CCS_ERETURN_CODE_UPDATE_ERROR_RESULT_DESCRIPTION);
					}				
					
				}				
								
			}
		
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the updateLanguage"
					+ e.getMessage());
			logger.info(e.getMessage());
			logger.info(e.getCause());
			logger.error(" Error "+e.getMessage());
			updateLanguageResponse = new UpdateLanguageResponse();
			updateLanguageResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updateLanguageResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), updateLanguageResponse);
		}
		logger.info("END-[ProfileBO-updateLanguage]");
		return updateLanguageResponse;
	}

	/**
	 * 
	 * @param bpNumber
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public GetBPInfoResponse getSVTData(String bpNumber,
			String companyCode, String brandName, String sessionId)
	{
		logger.info("ProfileBO - getSVTData method starts...");
		long startTime = CommonUtil.getStartTime();
		GetBPInfoResponse response = new GetBPInfoResponse();
		try {
			
			List<BussinessPartnerDO> bussinessPartnerDOList = bpAccountPayHelper.getSVTData(bpNumber, companyCode, brandName, sessionId);
			
			if (bussinessPartnerDOList != null && bussinessPartnerDOList.size() > 0) {
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				response.setBussinessPartnerInfo(bussinessPartnerDOList);

			} else {
				
				response.setResultCode(RESULT_CODE_NO_DATA);
				response
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				
				List<BussinessPartnerDO> bpBussinessPartnerDOList = new LinkedList<BussinessPartnerDO>();
				
				response.setBussinessPartnerInfo(bpBussinessPartnerDOList);
			}
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getSVTData"
					+ e.getMessage());
			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error "+e.getMessage());
			utilityloggerHelper.logTransaction("getSVTDATA", false,
					bpNumber, e,
					"",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			
			
			
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			List<BussinessPartnerDO> bpBussinessPartnerDOList = new LinkedList<BussinessPartnerDO>();
			response.setBussinessPartnerInfo(bpBussinessPartnerDOList);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		logger.info("END-[ProfileBO-getSVTData]");
		return response;
	}

	public ProfileCheckResponse profileCheck (String companyCode,String brandName,String contractAccountNumber,String email,String checkDigit,String sessionId){
		logger.info("Start-[ProfileResource-profileCheck]");
		String request = "companyCode="+companyCode+",brandName="+brandName+" ,contractAccountNumber="+contractAccountNumber+",email="+email+",checkDigit="+checkDigit;
		long startTime = CommonUtil.getStartTime();
		ProfileCheckResponse response = new ProfileCheckResponse();
		ProfileResponse profileResponse = null;
		boolean errorFlag = false;
		try{			
			logger.info("Doing CCS Profile Call....");
			Map<String, Object> responseMap = new HashMap<String, Object>();			
			 responseMap = profileService.getProfile(contractAccountNumber, companyCode, sessionId);
			 if(responseMap!= null && responseMap.size()!= 0)
			 {
				 profileResponse = (ProfileResponse)responseMap.get("profileResponse");
			 }
			if(profileResponse != null && profileResponse.getContractAccountDO()!=null)
			{
				String bpId = profileResponse.getContractAccountDO().getStrBPNumber();
				logger.info("bpId :::: "+bpId);
				String ccsChkDigit = profileResponse.getContractAccountDO().getStrCheckDigit()!=null ? profileResponse.getContractAccountDO().getStrCheckDigit() : "";
				logger.info("ccsChkDigit :::: "+ccsChkDigit);
				String ccsCompanyCode = profileResponse.getContractAccountDO().getStrCompany()!=null ? profileResponse.getContractAccountDO().getStrCompany() : "";
				logger.info("ccsCompanyCode :::: "+ccsCompanyCode);
				String ccsEmail = "";
				if(companyCode!=null && companyCode.trim().equals(ccsCompanyCode.trim()) && checkDigit!=null && checkDigit.trim().equals(ccsChkDigit.trim()))
				{
					if(StringUtils.isNotEmpty(bpId))
					{
						CrmProfileRequest crmProfileRequest = new CrmProfileRequest();
						crmProfileRequest.setStrCANumber(contractAccountNumber);
						crmProfileRequest.setStrBPNumber(bpId);
						logger.info("Doing CRM Profile Call....");
						CrmProfileResponse crmProfileResponse = profileService.getCRMProfile(crmProfileRequest, companyCode, sessionId);
						ccsEmail = crmProfileResponse.getEmailID()!=null ? crmProfileResponse.getEmailID() : "";
						if(email!=null && email.trim().equalsIgnoreCase(ccsEmail.trim()))
						{
							response.setResultDescription(MSG_SUCCESS);
							response.setResultCode(RESULT_CODE_SUCCESS);
						}
						else
						{
							logger.info("email not matched");
							errorFlag = true;
						}
					}
					else
					{
						logger.info("BP Id is blank/null");
						errorFlag = true;
					}
					
				}
				else
				{
					logger.info("company code/ chk digit not matched");
					errorFlag = true;
				}
				if (errorFlag)
				{
					logger.info("No match block");
					response.setCaNumber(contractAccountNumber);
					response.setCheckDigit(ccsChkDigit);
					response.setCompanyCode(ccsCompanyCode);
					response.setEmail(ccsEmail);
					response.setResultDescription(NO_MATCH);
					response.setResultCode(RESULT_CODE_TWO);
				}
			}
			else
			{
				logger.info("No data block");
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				response.setResultCode(RESULT_CODE_NO_DATA);		
			}
		}
		catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("profileCheck", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
			throw new OAMException(200, e.getMessage(), response);
		}
		catch (Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("profileCheck", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
			throw new OAMException(200, e.getMessage(), response);
		}
		utilityloggerHelper.logTransaction("profileCheck", false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("END-[ProfileResource-profileCheck]");
		return response;
	}	
	
	/**
	 * 
	 * @param companyCode
	 * @param email
	 * @param brandName
	 * @param caNumber
	 * @return
	 */
	public SendMailForPasswordChangeResponse  sendMailForPasswordChange(String companyCode, String brandName, String email, String caNumber, String sessionId){
		
		SendMailForPasswordChangeResponse response = new SendMailForPasswordChangeResponse();
		
		if (companyCode != null && CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode)
				&& CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)) {
			if (StringUtils.isNotEmpty(email)) {

			  if(StringUtils.isNotEmpty(caNumber)){
				try {
					Map<String, Object> responseMap = new HashMap<String, Object>();
					ProfileResponse profileResponse = null;
					responseMap = profileService.getProfile(caNumber, companyCode, sessionId);					
					if(responseMap!= null && responseMap.size()!= 0)
					{
						profileResponse= (ProfileResponse)responseMap.get("profileResponse");
					}				
					ContractAccountDO caDO = null;

					if (profileResponse != null) {
						caDO = profileResponse.getContractAccountDO();
						if (caDO != null) {

							logger.info("Found profile for given account number, Found contract account info");

							String caName = caDO.getCAName();

							if (caName != null && !caName.equalsIgnoreCase("")) {
								logger.info("Found CA Name : " + caName + ", Sending Mail for password change");

								HashMap<String, String> templateProperties = new HashMap<String, String>();

								templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));

								try {

									Boolean status = EmailHelper.sendMail(email, "", CIRRO_PASSWORD_CHANGE_EXTERNAL_ID,
											templateProperties, companyCode);

									logger.info("Email for password change sent status : " + status);

								} catch (Exception e) {
									logger.info("Exception in sending email for password change ");
									// TODO Auto-generated catch block
									logger.info(e);
									logger.error(e);

								}

							} else {
								logger.info("Couldn't find CA Name : " + caName
										+ ", so couldn't send mail for password change");
							}

						} else {
							logger.info(
									"Found the profile for given account number but couldn't find the contract account info, so couldn't send mail for password change");
						}

					} else {
						logger.info(
								"Couldn't find the profile for given account number, so couldn't send mail for password change");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("Error in sending mail for password change ");
					logger.info(e);
					logger.error(e);
					response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				}
				
			  }else{
					logger.info("contract account number is empty or null so couldn't send mail for password change");
					
			  }

			} else {

				logger.info("email is empty or null so couldn't send mail for password change");
			}

		}

		return response;
	}
	

	/**
	 * 
	 * @param companyCode
	 * @param email
	 * @param brandName
	 * @param caNumber
	 * @return
	 */
	public SendMailForNewServiceAddressAddResponse  sendMailForNewServiceAddressAddition(String companyCode, String brandName, String email, String caNumber, String sessionId){
		
		SendMailForNewServiceAddressAddResponse response = new SendMailForNewServiceAddressAddResponse();
		
		if (companyCode != null && CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode)
				&& CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)) {
			if (StringUtils.isNotEmpty(email)) {

			  if(StringUtils.isNotEmpty(caNumber)){
				try {
					Map<String, Object> responseMap = new HashMap<String, Object>();
					ProfileResponse profileResponse = null;
					responseMap =  profileService.getProfile(caNumber, companyCode, sessionId);
					if(responseMap!= null && responseMap.size()!= 0)
					{
						profileResponse= (ProfileResponse)responseMap.get("profileResponse");
					}
					else {
						logger.info(
								"Couldn't find the profile for given account number, so couldn't send mail for new service address addition");
					}
					ContractAccountDO caDO = null;

					if (profileResponse != null) {
						caDO = profileResponse.getContractAccountDO();
						if (caDO != null) {

							logger.info("Found profile for given account number, Found contract account info");

							String caName = caDO.getCAName();

							if (caName != null && !caName.equalsIgnoreCase("")) {
								logger.info("Found CA Name : " + caName + ", Sending Mail for new service address addition");

								HashMap<String, String> templateProperties = new HashMap<String, String>();

								templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));

								try {

									Boolean status = EmailHelper.sendMail(email, "", CIRRO_NEW_SERVICE_ADDRESS_ADD_EXTERNAL_ID,
											templateProperties, companyCode);

									logger.info("Email for password change sent status : " + status);

								} catch (Exception e) {
									logger.info("Exception in sending email for new service address addition ");
									// TODO Auto-generated catch block
									logger.info(e);
									logger.error(e);

								}

							} else {
								logger.info("Couldn't find CA Name : " + caName
										+ ", so couldn't send mail for new service address addition");
							}

						} else {
							logger.info(
									"Found the profile for given account number but couldn't find the contract account info, so couldn't send mail for new service address addition");
						}

					} else {
						logger.info(
								"Couldn't find the profile for given account number, so couldn't send mail for new service address addition");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("Error in sending mail for new service address addition ");
					logger.info(e);
					logger.error(e);
					response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
					response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				}
				
			  }else{
					logger.info("contract account number is empty or null so couldn't send mail for new service address addition");
					
			  }

			} else {

				logger.info("email is empty or null so couldn't send mail for new service address addition");
			}

		}

		return response;
	}
	
	
}
