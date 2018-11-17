package com.multibrand.bo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.multibrand.domain.SyncLDAPRequest;
import com.multibrand.helper.LDAPHelper;
import com.multibrand.service.LDAPService;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.response.LoginFailureResponse;
import com.multibrand.vo.response.LoginResponse;



/***
 * 
 * @author Kdeshmu1 This class is to contain all the business logic for all the
 *         Authentication APIs
 * 
 */

@Configuration("authenticationBO")
public class AuthenticationBO implements Constants{
	
	
	@Autowired
	private LDAPService ldapService;
	
	@Autowired
	private LDAPHelper ldapHelper;
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	public static final String UID_HEADER="SSO_UID";
	public static final String UNIQUEID_HEADER="SSO_UNIQUEID";
	public static final String ACCOUNTNUMBER_HEADER ="accountnumber";

	
	/**
	 * This function used for success login call
	 * @author Kdeshmu1
	 * @param userId
	 * @param hh
	 * @return
	 */
	public LoginResponse loginSuccessCall(String userId, HttpHeaders httpHeaders, HttpServletRequest req) {

		logger.info("AuthenticationBO.loginSuccessCall::::::::Start");
		String uid = null;
		String uuid = null;
		String accountNumber=null;
		String ldapInvalidLoginCount = null;
		int failureCount = 0;
		//Cookie c = null;
		LoginResponse loginResponse = new LoginResponse();

		MultivaluedMap<String, String> requestHeadersMap = httpHeaders.getRequestHeaders();
		
		logger.info("loginSuccessCall(...) >>>>>>>>>>>>>>> Headers <<<<<<<<<<<<<<");
		if(null != requestHeadersMap && requestHeadersMap.size() > 0) {
			for(String headerName : requestHeadersMap.keySet()) {
				logger.info("loginSuccessCall(...) >> Header Name [" + headerName + "] Header Value[" + requestHeadersMap.getFirst(headerName) + "]");
			}
		} else {
			logger.info("loginSuccessCall(...) >> No headers received.");
		}
		logger.info("loginSuccessCall(...) >>>>>>>>>>>>>>> Cookies <<<<<<<<<<<<<<");
		Map<String, Cookie> cookies = httpHeaders.getCookies();
		if(null != cookies && cookies.size() > 0) {
			for(String cookieName : cookies.keySet()) {
				logger.info("loginSuccessCall(...) >> Cookie Name ["+cookieName+"] Cookie Value["+cookies.get(cookieName).getValue()+"]");
			}
		}
				
		uid = readValueFromHeaderOrCookie(httpHeaders, UID_HEADER);
		uuid = readValueFromHeaderOrCookie(httpHeaders, UNIQUEID_HEADER);
		accountNumber = readValueFromHeaderOrCookie(httpHeaders, ACCOUNTNUMBER_HEADER);
		ldapInvalidLoginCount = readValueFromHeaderOrCookie(httpHeaders, SM_SSO_FAILURECOUNT);
		failureCount = getValueAsInteger(ldapInvalidLoginCount);
		
		if(failureCount > 0) {
			synchronizeLDAP(GME_RES_COMPANY_CODE, uid, LDAP_ORG_GME, ZERO, req);
		}
		logger.info("loginSuccessCall(...) "
				+ "uid["+uid+"] "
				+ "uuid["+uuid+"]"
				+ "accountNumber["+accountNumber+"]"
				+ "Unique ID["+uuid+"]"
				+ "SSO_FAILURECOUNT["+ldapInvalidLoginCount+"] failureCount :[" + failureCount + "]");
		
		loginResponse.setUserID(uid);
		loginResponse.setUserUniqueID(uuid);
		loginResponse.setAccountNumber(accountNumber);
		loginResponse.setResultCode(RESULT_CODE_SUCCESS);
		loginResponse.setResultDescription(MSG_SUCCESS);
		logger.info("AuthenticationBO.loginSuccessCall:::::::End");
		return loginResponse;
	}

	
	/**
	 * This function used for failure login call
	 * @author Kdeshmu1
	 * @param userId
	 * @param httpHeaders
	 * @return
	 */
	public LoginFailureResponse loginFailureCall(String userId, HttpHeaders httpHeaders, HttpServletRequest req) {
		
		logger.info("AuthenticationBO.loginFailureCall::::::::Start");
		LoginFailureResponse loginFailureResponse = new LoginFailureResponse();
		String ldapNoUserInLDAP = null;
		String ldapCustomLockOutFlag = null;
		String ldapInvalidLoginCount = null;
		String uid = null;
		String uuid = null;
		int failureCount = 0;
		int customLockOutValue = 0;
		
		int oamMaxInvalidLoginCount = DEFAULT_OAM_MAX_INVALID_LOGIN_COUNT;
		
		try {
			
			//If a configured value exists for max invalid login count, it will pickup from environment properties file else defaults to 5
			if(null != envMessageReader){
				 if(StringUtils.isNotBlank(envMessageReader.getMessage(OAM_MAX_INVALID_LOGIN_COUNT_FROM_ENV_PROP))
						 && getValueAsInteger(envMessageReader.getMessage(OAM_MAX_INVALID_LOGIN_COUNT_FROM_ENV_PROP)) > 0) {
					 oamMaxInvalidLoginCount = getValueAsInteger(envMessageReader.getMessage(OAM_MAX_INVALID_LOGIN_COUNT_FROM_ENV_PROP));
				 }
			}
		} catch (Exception ex) {
			logger.error("Error in getting configured invalid login count, thus defaulted its value to ["+oamMaxInvalidLoginCount+"]. Error:" + ex.getLocalizedMessage());
		}
		
		try {
			
			
			MultivaluedMap<String, String> requestHeadersMap = httpHeaders.getRequestHeaders();
			
			logger.info("loginFailureCall(...) >>>>>>>>>>>>>>> Headers <<<<<<<<<<<<<<");
			if(null != requestHeadersMap && requestHeadersMap.size() > 0) {
				for(String headerName : requestHeadersMap.keySet()) {
					logger.info("loginFailureCall(...) >> Header Name [" + headerName + "] Header Value[" + requestHeadersMap.getFirst(headerName) + "]");
				}
			} else {
				logger.info("No headers received.");
			}
			logger.info("loginFailureCall(...) >>>>>>>>>>>>>>> Cookies <<<<<<<<<<<<<<");
			Map<String, Cookie> cookies = httpHeaders.getCookies();
			if(null != cookies && cookies.size() > 0) {
				for(String cookieName : cookies.keySet()) {
					logger.info("loginFailureCall(...) >> Cookie Name ["+cookieName+"] Cookie Value["+cookies.get(cookieName).getValue()+"]");
				}
			}
			
			//Reading required Header (or Cookie) from SiteMinder for Login Failure 
			uid = readValueFromHeaderOrCookie(httpHeaders, UID_HEADER);
			uuid = readValueFromHeaderOrCookie(httpHeaders, UNIQUEID_HEADER);
			ldapNoUserInLDAP = readValueFromHeaderOrCookie(httpHeaders, NOUSERLDAP_HEADER);
			ldapCustomLockOutFlag = readValueFromHeaderOrCookie(httpHeaders, SM_SSO_LOCKEDFLAG);
			customLockOutValue = getValueAsInteger(ldapCustomLockOutFlag);
			ldapInvalidLoginCount = readValueFromHeaderOrCookie(httpHeaders, SM_SSO_FAILURECOUNT);
			failureCount = getValueAsInteger(ldapInvalidLoginCount);
			logger.info("loginFailureCall(...) "
					+ "uid["+uid+"] "
					+ "uuid["+uuid+"]"
					+ "noUserInLDAP["+ldapNoUserInLDAP+"] "
					+ "SSO_LOCKEDFLAG["+ldapCustomLockOutFlag+"] "
					+ "customLockOutValue["+customLockOutValue+"] "
					+ "SSO_FAILURECOUNT["+ldapInvalidLoginCount+"] failureCount :[" + failureCount + "]");
			
			//Setting various error codes based on the SiteMinder Header (or Cookie) values to send back to the caller 
			loginFailureResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			if(null != ldapNoUserInLDAP && StringUtils.isNotBlank(ldapNoUserInLDAP) && ldapNoUserInLDAP.equalsIgnoreCase(FLAG_TRUE)) {
				loginFailureResponse.setResultDescription(USER_NOT_FOUND_ERROR_CODE);
				loginFailureResponse.setErrorCode(MSG_USER_NOT_FOUND);
				logger.info("User ID["+uid+"] >> User Not Found in LDAP");
			} else {
				if(customLockOutValue > 0 ) {
					failureCount++;
					logger.info("User ID["+uid+"] >> User Account is in Locked state. Error code sent back is:[MSG_LOCKED]");
					loginFailureResponse.setResultDescription(USER_LOCKEDOUT_STATUS_ERROR_CODE);
					loginFailureResponse.setErrorCode(MSG_LOCKED_ERROR_CODE);
					loginFailureResponse.setInvalidLoginCount(failureCount+"");
					synchronizeLDAP(GME_RES_COMPANY_CODE, uid, LDAP_ORG_GME, (failureCount+""), req);					
				} else {
					// Update failureCount to 1 if its actual value recived from LDAP is greater or equal to 4.  
					// This is required to for the scenario where user tried 6th time but SiteMinder sends 
					// SSO_LOCKEDFLAG=0 with actual failure count
					// Note: SiteMinder unlocks user after 24 hours i.e. the header SSO_LOCKEDFLAG=0 (LDAP Attribute = CustomLockOutFlag') 
					//---------------------------
					failureCount = (failureCount >= (oamMaxInvalidLoginCount - 1) ? 1 : ++failureCount);
					
					if(failureCount < (oamMaxInvalidLoginCount-1)) {
						logger.info("User ID["+uid+"] >> Invalid Credentials. Failure Count is [" + failureCount + "] Error code sent back is:[MSG_BAD_LOGIN]");
						loginFailureResponse.setResultDescription(CREDENTIALS_MISMATCH_ERROR_CODE);
						loginFailureResponse.setErrorCode(MSG_BAD_LOGIN_ERROR_CODE);
						loginFailureResponse.setInvalidLoginCount(failureCount+"");
						synchronizeLDAP(GME_RES_COMPANY_CODE, uid, LDAP_ORG_GME, (failureCount+""), req);
					} else if(failureCount == (oamMaxInvalidLoginCount-1)) {
						logger.info("User ID["+uid+"] >> Invalid Credentials. Account is about to Lock. Failure Count is [" + failureCount + "] Error code sent back is:[MSG_LOCK_PENDING]");
						loginFailureResponse.setResultDescription(CREDENTIALS_MISMATCH_ERROR_CODE);
						loginFailureResponse.setErrorCode(MSG_LOCK_PENDING_ERROR_CODE);
						loginFailureResponse.setInvalidLoginCount(failureCount+"");
						synchronizeLDAP(GME_RES_COMPANY_CODE, uid, LDAP_ORG_GME, (failureCount+""), req);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Exception occured in loginFailureCall(...). Error is :" + ex.getLocalizedMessage(),  ex);
			loginFailureResponse.setResultDescription(USER_LOCKEDOUT_UNKNOWN_ERROR_CODE);
			loginFailureResponse.setErrorCode(MSG_EXCEPTION_ERROR_CODE);
		}
		
		logger.info("AuthenticationBO.loginFailureCall::::::::End");
		return loginFailureResponse;
	}
	
	
	/**
	 * Get a value from HTTP Header if exists else continues to verify in Cookie and gives its value if available.
	 * If no value found either in HTTP Header or in Cookie, it returns empty.   
	 * @param httpHeaders
	 * @param lookupItem
	 * @return
	 */
	private String readValueFromHeaderOrCookie(HttpHeaders httpHeaders, String lookupItem) {
		
		String resultValue = null;
		
		if(null != httpHeaders && StringUtils.isNotBlank(lookupItem)) {
			if(httpHeaders.getRequestHeaders().containsKey(lookupItem) 
					&& StringUtils.isNotBlank(httpHeaders.getRequestHeaders().getFirst(lookupItem))) {
				
				resultValue = httpHeaders.getRequestHeaders().getFirst(lookupItem);
				
			} else if (httpHeaders.getCookies().containsKey(lookupItem)
					&& null != httpHeaders.getCookies().get(lookupItem)
					&& StringUtils.isNotBlank(httpHeaders.getCookies().get(lookupItem).getValue())) {
				
				resultValue = httpHeaders.getCookies().get(lookupItem).getValue();
				logger.info("Cookie value for - " + lookupItem + " is : " + resultValue);
			}
		}
		
		return StringUtils.defaultIfEmpty(resultValue, "");
	}
	
	/**
	 * Converts a String value to an integer value 
	 * @param valueToBeReturnAsInteger
	 * @return
	 */
	private int getValueAsInteger(String valueToBeReturnAsInteger) {
		
		int defaultValue = 0;
		
		try {
			if(null != valueToBeReturnAsInteger && StringUtils.isNotBlank(valueToBeReturnAsInteger)) {
				defaultValue = Integer.parseInt(valueToBeReturnAsInteger);
			}
		} catch (Exception ex) {
			logger.error("Error in parsing the String value to Integer for[" + valueToBeReturnAsInteger + "]");
		}
		
		return defaultValue;
	}
	
	/**
	 * 
	 * Update Invalid Login Count in LDAP
	 * 
	 * @param companyCode
	 * @param userName
	 * @param ldapOrg
	 * @param failureCount
	 */
	private void synchronizeLDAP(String companyCode, String userName, String ldapOrg, String failureCount, HttpServletRequest req) {
		
		try {
			SyncLDAPRequest syncLDAPRequest = new SyncLDAPRequest();
			syncLDAPRequest.setStrComapnyCode(companyCode);
			syncLDAPRequest.setStrUserName(userName);
			syncLDAPRequest.setStrLDAPOrg(ldapOrg);
			syncLDAPRequest.setStrInvalidLoginCnt(failureCount);
			ldapService.synchronizeLDAP(syncLDAPRequest, ldapOrg, req.getSession(true).getId());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error occured while updating 'invalidlogincount' attribute in LDAP. Exception is :" + ex.getLocalizedMessage());
		}
		
	}


}
