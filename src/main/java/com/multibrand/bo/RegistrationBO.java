package com.multibrand.bo;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.EmailHelper;
import com.multibrand.helper.LDAPHelper;
import com.multibrand.helper.RegistrationHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.ProfileService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.request.ValidateUserNameRequest;
import com.multibrand.vo.response.CreateUserResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.ValidateAccountForMobileResponse;
import com.multibrand.vo.response.ValidateAccountResponse;
import com.multibrand.vo.response.ValidateUsrNameResponse;

@Component
public class RegistrationBO extends BaseAbstractService implements Constants
{

	@Autowired
	private ProfileService profileService;

	@Autowired
	private RegistrationHelper registrationHelper;

	@Autowired
	private LDAPHelper lDAPHelper;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private EmailHelper emailHelper;
	
	@Autowired
	private TOSService tosService;
	
	//@Autowired
	//private ReloadableResourceBundleMessageSource appConstMessageSource;


	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
	 * This method will match the passed lastName with the lastName retrieved
	 * from the getProfile call to RedbullService
	 * 
	 * @author ahanda1
	 * @param accountNumber
	 * @param lastName
	 * @param companyCode
	 */
	public ValidateAccountResponse validateAccount(String accountNumber,
			String lastName,String companyCode, String sessionId)
	{

		logger.info("RegistrationBO.validateAccount :::: START");
		ProfileResponse response = null;
		ValidateAccountResponse validateAccountResponse = new ValidateAccountResponse();

		accountNumber = CommonUtil.paddedCa(accountNumber);

		try {
			
			Map<String, Object> responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
			if(responseMap!= null && responseMap.size()!= 0)
			{
				response= (ProfileResponse)responseMap.get("profileResponse");
			} 
			if(response != null && response.getContractAccountDO()!=null)
			{
				String lastNameFromProfile = response.getContractAccountDO().getStrExLastName();
				String firstNameFromProfile=response.getContractAccountDO().getStrExFirstName();
				//START US515 : GME_MyAccount_CompanyCode Validation :adadan
				logger.info("RegistrationBO.validateAccount :::: lastNameFromProfile--->{}",lastNameFromProfile);
				logger.info("RegistrationBO.validateAccount :::: firstNameFromProfile--->>{}",firstNameFromProfile);
				logger.info("RegistrationBO.validateAccount :::: lastName--->{}",lastName);
				logger.info("RegistrationBO.validateAccount :::: companyCode--->{}",companyCode);
				logger.info("RegistrationBO.validateAccount :::: COMPANY_CODE_GME --->{}",COMPANY_CODE_GME);

			if(companyCode!=null && companyCode.equals(COMPANY_CODE_GME) && !companyCode.equals(response.getContractAccountDO().getStrCompany())){
				
					validateAccountResponse.setResultCode(RESULT_CODE_FIVE);
					validateAccountResponse.setResultDescription(RESULT_CODE_NOT_GME_TX_ACC);
					validateAccountResponse.setLastName(response.getContractAccountDO().getStrLastName());
				}
				//END US515 : GME_MyAccount_CompanyCode Validation :adadan
				else if(lastName.trim().equalsIgnoreCase(lastNameFromProfile)) {
					
					validateAccountResponse.setResultCode(RESULT_CODE_SUCCESS);
					validateAccountResponse.setResultDescription(MSG_SUCCESS);
					validateAccountResponse.setFirstName(firstNameFromProfile);
					validateAccountResponse.setLastName(lastNameFromProfile);
					validateAccountResponse.setCheckDigit(response.getContractAccountDO().getStrCheckDigit());
				} else{
					validateAccountResponse.setResultCode(RESULT_CODE_TWO);
					validateAccountResponse.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
					validateAccountResponse.setLastName(lastNameFromProfile);
				}
			}
			else
			{
				validateAccountResponse.setResultCode(RESULT_CODE_THREE);
				validateAccountResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
		} catch (Exception e) {
			logger.error(e);
			validateAccountResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateAccountResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), validateAccountResponse);
		}
		logger.info("RegistrationBO.validateAccount :::: END");
		return validateAccountResponse;
	}

	/**
	 * @author smuruga1
	 * @param accountNumber
	 * @param lastName
	 * @param email
	 * @param firstName
	 * @param userName
	 * @param password
	 * @param companyCode
	 * @param sessionId
	 * @param languageCode
	 * @param applicationArea
	 * @param checkDigit
	 * @return
	 */
	public GenericResponse createUser(String accountNumber, String lastName,
			String email, String firstName, String userName, String password,
			String companyCode, String sessionId, String languageCode, String applicationArea, String checkDigit, String source, boolean isMarkettingPrefOptIn)
	{
		UserRegistrationRequest register = new UserRegistrationRequest();
		logger.info("START-[RegistrationBO-createUser]");
		String uniqueId = "";
		accountNumber = CommonUtil.paddedCa(accountNumber.trim());
		register.setAccountNumber(accountNumber);
		register.setEmail(email.trim());
		register.setCompanyCode(companyCode.trim());
		register.setFirstName(firstName);
		register.setLastName(lastName.trim());
		register.setUserName(userName.trim());
		register.setPassword(password);
		register.setSessionId(sessionId);
		
		CreateUserResponse genricResp = new CreateUserResponse();
		String businessPartner="";
		boolean isUdated=false;
		long startTime = CommonUtil.getStartTime();
		try {
			if (registrationHelper.isAccountEnrolled(register)) {
				
				uniqueId = lDAPHelper.createUser(register);
				utilityloggerHelper.logTransaction("createUserLdap", false, register,uniqueId, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
				if(logger.isDebugEnabled()){
					logger.debug(XmlUtil.pojoToXML(register));
					logger.debug(XmlUtil.pojoToXML(uniqueId));
				}
				logger.info("RegistrationBO-createUser - uniqueId]"+uniqueId);
				
				if(StringUtils.isBlank(uniqueId))
				{
					genricResp.setResultCode(RESULT_CODE_FOUR);
					genricResp.setResultDescription(RESULT_DESCRIPTION_USERNAME_EXISTS);
					return genricResp;
				}
				else
				{
					register.setUniqueId(uniqueId);
					Map<String, Object> responseMap = new HashMap<String, Object>();
					ProfileResponse profileRes = null;
					responseMap = profileService.getProfile(accountNumber,companyCode,sessionId);
					if(responseMap!= null && responseMap.size()!= 0)
					{
						profileRes= (ProfileResponse)responseMap.get("profileResponse");
					}
				    ContractAccountDO ctrdo = profileRes.getContractAccountDO();
				    if(ctrdo==null) {
				    	throw new OAMException(200, "Profile is not available", genricResp);
				    }
				   
				    this.logger.info("bp number " + ctrdo.getStrBPNumber());
				    businessPartner = ctrdo.getStrBPNumber();
				      
					isUdated = registrationHelper.createUserName(register);
					logger.info("RegistrationBO- after create user DB Call]"+isUdated);
					profileService.activateCRM(register,businessPartner, companyCode, sessionId);
					logger.info("Crm Activation Done....");
					profileService.updateContactInfo(register, businessPartner,companyCode, sessionId,source, isMarkettingPrefOptIn);
					logger.info("Crm profile updation Done....");
					// sending mail for online account registration
					HashMap<String, String> templateProps = new HashMap<String,String>();
					templateProps.put(ACCOUNT_NUMBER, accountNumber);
					templateProps.put(CHECK_DIGIT, checkDigit);
					templateProps.put(ACCOUNT_NAME, firstName+" "+lastName);
					templateProps.put(USER_ID, userName);						
					
					if(applicationArea!=null && applicationArea.equalsIgnoreCase("OE"))
					{
						if(StringUtils.isBlank(languageCode)|| languageCode.equalsIgnoreCase(LANGUAGE_CODE_EN))
						{						
							logger.info("Sending mail for online account registration EN");
							emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL),  "", CREATE_USER_EN, templateProps, companyCode);
						} else{
							logger.info("Sending mail for online account registration ES");
							emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL),  "", CREATE_USER_ES, templateProps, companyCode);
						}
					}
					
			}
			} else {
				logger.info("RegistrationBO- user already available in the database]");
				genricResp.setResultCode(RESULT_CODE_THREE);
				genricResp.setResultDescription(RESULT_CODE_ACCOUNT_ALREADY);
				return genricResp;
			}
						
			
		}
		catch (Exception e) {
			logger.info(" Exeception Occured in the registrationBO[createUser]"
					+ e.getMessage());
			logger.error(e);
			utilityloggerHelper.logTransaction("createUserLdap", false, register,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(register));
			
			if (isUdated) {
				logger.info("isUpdated :: "+ isUdated);
				genricResp.setResultCode(RESULT_CODE_SUCCESS);
				genricResp.setResultDescription(MSG_SUCCESS);
				
			}
			else
			{
				logger.info("isUpdated :: "+ isUdated);
				try {
					startTime = CommonUtil.getStartTime();
					lDAPHelper.deleteUser(userName);
				} catch (Exception exp) {
					logger.info(" Exeception Occured in the deleteUserLDAP Call"
							+ exp.getMessage());
					logger.error(e);
					utilityloggerHelper.logTransaction("createUserLdap", false, register,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
					logger.info(XmlUtil.pojoToXML(register));
				}
				genricResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				genricResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), genricResp);
			}
			
		}
		
		genricResp.setResultCode(RESULT_CODE_SUCCESS);
		genricResp.setResultDescription(MSG_SUCCESS);
		logger.info("END-[RegistrationBO-createUser]");
		
		if(genricResp.getResultCode()!=null && source!=null && businessPartner!=null &&
				(genricResp.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)||genricResp.getResultCode().equalsIgnoreCase(SUCCESS_CODE))&& 
				GME_RES_COMPANY_CODE.equalsIgnoreCase(companyCode)&&source.equalsIgnoreCase(MOBILE)){
			logger.info("Inside createUser:updateContactLog(...) block - in RegistrationBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(businessPartner);
			cssUpdateLogRequest.setContractAccountNumber(accountNumber);
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_CREATE_USER_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_CREATE_USER_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines("User with last name "+lastName+" and account number "+CommonUtil.stripLeadingZeros(accountNumber)+" has registered for My Account via the GME Mobile App with username "+userName+" on "+CommonUtil.getCurrentDateandTime()+".");
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(companyCode);
			logger.info("Start: call TOSService.updateContactLog(...)");
			try {
				tosService.updateContactLog(cssUpdateLogRequest);
			} catch(Exception e) {
				logger.error("Error in updateContactLog:"+e);
			}
			logger.info("End: call TOSService.updateContactLog(...)");
			logger.info("End createUser:updateContactLog(...) block - in RegistrationBO");
		}
		
		return genricResp;

	}

	public ValidateAccountForMobileResponse validateAccountForMobile(String accountNumber, String lastName, 
			String userName, String companyCode,String brandName, String sessionId) {
		
		ValidateAccountForMobileResponse validateAccountForMobileResponse = new ValidateAccountForMobileResponse();
		ValidateAccountResponse validateAccountResponse = null;
							
		try{
			//Step 1 - Validating Account name and last Name
			if(StringUtils.isNotEmpty(accountNumber.trim())&&StringUtils.isNotEmpty(lastName.trim())
					&&StringUtils.isNotEmpty(companyCode.trim())&&StringUtils.equalsIgnoreCase(COMPANY_CODE_GME, companyCode)){
				
					accountNumber=CommonUtil.paddedCa(accountNumber.trim());
					lastName=lastName.trim();
				
					validateAccountResponse = validateAccount(accountNumber,lastName,companyCode,sessionId);
				
								
				
				if(validateAccountResponse!=null && validateAccountResponse.getResultCode().equalsIgnoreCase("0")){
					UserRegistrationRequest register = new UserRegistrationRequest();
					
					register.setAccountNumber(accountNumber);
					register.setCompanyCode(companyCode);
					register.setFirstName(validateAccountResponse.getFirstName());
					register.setLastName(lastName);
					register.setUserName(userName);
					register.setSessionId(sessionId);
					
									
					//Step 2 - Checking if the account is already registered or not.	
					
					
					
					if (!(registrationHelper.isAccountEnrolled(register))) {
							
							logger.info("validateAccountForMobile - Account Already Registered - Please login with Username");
							validateAccountForMobileResponse.setResultCode(RESULT_CODE_THREE);
							validateAccountForMobileResponse.setResultDescription(RESULT_CODE_ACCOUNT_ALREADY);
							validateAccountForMobileResponse.setMessageText("Account Already Registered - Please login with Username");
							validateAccountForMobileResponse.setLastName(validateAccountResponse.getLastName());
							validateAccountForMobileResponse.setFirstName(validateAccountResponse.getFirstName());
							validateAccountForMobileResponse.setCheckDigit(validateAccountResponse.getCheckDigit());

							
						}else{

								if ( userName != null && StringUtils.isNotEmpty(userName.trim())) {
									userName = userName.trim();
									ValidateUserNameRequest validate = new ValidateUserNameRequest();
								if(StringUtils.equalsIgnoreCase(COMPANY_CODE_GME, companyCode))
									validate.setStrLDAPOrg(Constants.LDAP_ORGANISATION);
									validate.setStrUserName(userName);
								ValidateUsrNameResponse validateUsrNameResponse = null;
								validateUsrNameResponse=lDAPHelper.validateUsername(validate);
								if(validateUsrNameResponse!=null&&validateUsrNameResponse.isbSuccessFlag()){
									validateAccountForMobileResponse.setResultCode(RESULT_CODE_SUCCESS);
									logger.info("validateAccountForMobile -"+userName+" is a valid username - Success");
									validateAccountForMobileResponse.setUserName(userName);
									validateAccountForMobileResponse.setResultDescription(MSG_SUCCESS);
									validateAccountForMobileResponse.setMessageText("UserName is a valid username - Success");
									validateAccountForMobileResponse.setLastName(validateAccountResponse.getLastName());
									validateAccountForMobileResponse.setFirstName(validateAccountResponse.getFirstName());
									validateAccountForMobileResponse.setCheckDigit(validateAccountResponse.getCheckDigit());

									
								}else{
									logger.info("validateAccountForMobile - User Already Exists - Username already present in LDAP");
									validateAccountForMobileResponse.setResultCode(RESULT_CODE_FOUR);
									validateAccountForMobileResponse.setResultDescription(RESULT_DESCRIPTION_USERNAME_EXISTS);
									validateAccountForMobileResponse.setMessageText("User Already Exists - Username already present in LDAP");
									validateAccountForMobileResponse.setLastName(validateAccountResponse.getLastName());
									validateAccountForMobileResponse.setFirstName(validateAccountResponse.getFirstName());
									validateAccountForMobileResponse.setCheckDigit(validateAccountResponse.getCheckDigit());

									}
							}else{
								logger.info("validateAccountForMobile - Account information valid - you can now create username with this A/C and Lastname");
								validateAccountForMobileResponse.setResultCode(RESULT_CODE_SUCCESS);
								validateAccountForMobileResponse.setResultDescription(ACCOUNT_INFO_VALID);
								validateAccountForMobileResponse.setStatusCode("00");
								validateAccountForMobileResponse.setMessageText("Account information valid - Success you can now create username with this A/C and Lastname");
								validateAccountForMobileResponse.setLastName(validateAccountResponse.getLastName());
								validateAccountForMobileResponse.setFirstName(validateAccountResponse.getFirstName());
								validateAccountForMobileResponse.setCheckDigit(validateAccountResponse.getCheckDigit());
							}
							
						
						}
				
					
					}else{
						if(validateAccountResponse!=null && validateAccountResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_FIVE) && validateAccountResponse.getLastName().equalsIgnoreCase(lastName)){
							logger.info("validateAccountForMobile - This account is ineligible to be managed via My Account");
							validateAccountForMobileResponse.setResultCode(RESULT_CODE_FIVE);
							validateAccountForMobileResponse.setResultDescription(RESULT_CODE_NOT_GME_TX_ACC);
							validateAccountForMobileResponse.setLastName(lastName);
						}else{
						
						logger.info("validateAccountForMobile - Invalid Contract Account details- Account validation failed");
						validateAccountForMobileResponse.setResultCode(RESULT_CODE_TWO);
						validateAccountForMobileResponse.setResultDescription(RESULT_CODE_NO_MATCH_DESCRIPTION);
						validateAccountForMobileResponse.setMessageText("Invalid Contract Account details- Account validation failed");
						validateAccountForMobileResponse.setLastName(lastName);
						}
						
					}
				}else{
					logger.info("validateAccountForMobile - Invalid Input Parameters - Please check entered A/C number and Lastname");
					validateAccountForMobileResponse.setResultCode(RESULT_CODE_SIX);
					validateAccountForMobileResponse.setResultDescription(RESULT_CODE_INVALID_INPUT_PARAMETERS);
					validateAccountForMobileResponse.setMessageText("Invalid Input Parameters - Please check entered A/C number and Lastname");
					validateAccountForMobileResponse.setLastName(lastName);
					
				}
			}
			catch(Exception e)
			{
			logger.error(e);
			logger.info("Exeception Occured in the validateAccountForMobile Call");
			validateAccountForMobileResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateAccountForMobileResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			validateAccountForMobileResponse.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			validateAccountForMobileResponse.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION);
			validateAccountForMobileResponse.setLastName(lastName);
			validateAccountForMobileResponse.setMessageText("Exeception Occured in the validateAccountForMobile Call");
			throw new OAMException(200, e.getMessage(), validateAccountForMobileResponse);
		
			}
		
			return validateAccountForMobileResponse;
	

	
	}
}
