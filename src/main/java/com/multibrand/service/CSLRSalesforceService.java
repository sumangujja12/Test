package com.multibrand.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.multibrand.dao.impl.CSLRProfileDAOImpl;
import com.multibrand.dto.request.CommunitySolarWebDashboardRequest;
import com.multibrand.dto.request.CommunitySolarWebUpdateRequest;
import com.multibrand.dto.request.SFUserProfileUpdateSyncRequest;
import com.multibrand.dto.request.SalesforceDashboardRequest;
import com.multibrand.dto.request.SalesforceUpdateRequest;
import com.multibrand.helper.LDAPHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.ChangeEmailUsrNameRequest;
import com.multibrand.vo.request.DeleteUsrNameRequest;
import com.multibrand.vo.request.ValidateUserNameRequest;
import com.multibrand.vo.response.ChangeUsernameResponse;
import com.multibrand.vo.response.DeleteUserProfileResponse;
import com.multibrand.vo.response.SFUserProfileUpdateResponse;
import com.multibrand.vo.response.SalesforceAccountResponse;
import com.multibrand.vo.response.SalesforceDashboardResponse;
import com.multibrand.vo.response.SalesforceTokenResponse;
import com.multibrand.vo.response.SalesforceUpdateAccountResponse;


@Service("cslrSalesforceService")
public class CSLRSalesforceService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	private LDAPHelper ldapHelper;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	CSLRProfileDAOImpl cslrProfileDAO;
	
	public SalesforceTokenResponse createRestTokenTemplateAndCallService() {

		Gson gson = new Gson();
		SalesforceTokenResponse response = new SalesforceTokenResponse();
		String restResponse = null;
		try {
			
			String url = buildSalesforceTokenURL();
			
			// Create the request body as a MultiValueMap
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

			body.add(GRANNT_TYPE, this.envMessageReader.getMessage(SALESFORCE_GRANT_TYPE));
			body.add(CLIENT_ID, this.envMessageReader.getMessage(SALESFORCE_CLIENT_ID));
			body.add(CLIENT_SECRET, this.envMessageReader.getMessage(SALESFORCE_CLIENT_SECRET));
			body.add(USER_NAME, this.envMessageReader.getMessage(SALESFORCE_USER_NAME));
			body.add(PASSWORD, this.envMessageReader.getMessage(SALESFORCE_USER_PASSWORD));

			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			HttpEntity entity = new HttpEntity(body, headers);
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class, map);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";

			response = gson.fromJson(restResponse, SalesforceTokenResponse.class);
			if(null != response  && response.getAccessToken()!= null) {
				
				response.setResultCode(RESULT_CODE_SUCCESS);
				
			} else {
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
		} catch (HttpClientErrorException e) {
			logger.error(" CSLRSalesforceService - createRestTokenTemplateAndCallService() ::: "+e.getMessage());
			response.setResultCode(e.getStatusCode().toString());
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
	   } catch (Exception ex) {
			logger.error(" CSLRSalesforceService - createRestTokenTemplateAndCallService() ::: "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
		}
		return response;
	}
	
	public SalesforceAccountResponse createRestGetAccountAndCallService(String accessToken, String leaseId) {

		Gson gson = new Gson();
		SalesforceAccountResponse response = new SalesforceAccountResponse();
		String restResponse = null;
		try {
			
						
			// Create the request body as a URI Builder
			URIBuilder builder = new URIBuilder();
			builder.setPath(buildSalesforceGetAccountURL())
			//builder.setScheme("https").setHost("cs13.salesforce.com").setPath("/services/apexrest/SharedSolar/Account/")
			    .setParameter(SALESFORCE_LEASE_ID, leaseId);
			URI uri = builder.build();

			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.set(SALESFORCE_AUTHORIZATION, SALESFORCE_OAUTH + accessToken);
						
			HttpEntity entity = new HttpEntity(headers);
			
			Map<String, String> map = new HashMap<String, String>();
			HttpEntity<String> responseEntity = restTemplate.exchange(builder.build(), HttpMethod.GET, entity, String.class);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";

			response = gson.fromJson(restResponse, SalesforceAccountResponse.class);
			
			if(null != response  && response.getMessage().equalsIgnoreCase(SF_RESP_MSG_SUCCESS)) {
				
				response.setResultCode(RESULT_CODE_SUCCESS);
				
			} else {
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
			
		} catch (HttpClientErrorException e) {
				logger.error(" CSLRSalesforceService - createRestGetAccountAndCallService() ::: "+e.getMessage()); 
				response.setResultCode(e.getStatusCode().toString());
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		} catch (Exception ex) {
			logger.error(" CSLRSalesforceService - createRestGetAccountAndCallService() ::: "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}	
	
	public SalesforceAccountResponse createRestAccRegAndCallService(String accessToken, String leaseId, String utilityAccNo) {

		Gson gson = new Gson();
		SalesforceAccountResponse response = new SalesforceAccountResponse();
		String restResponse = null;
		try {
			
						
			// Create the request body as a URI Builder
			URIBuilder builder = new URIBuilder();
			builder.setPath(buildSalesforceAccountRegistrationURL())
			    .setParameter(SALESFORCE_LEASE_ID, leaseId)
				.setParameter(SALESFORCE_UTILITY_ACC_NO, utilityAccNo);
			URI uri = builder.build();

			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.set(SALESFORCE_AUTHORIZATION, SALESFORCE_OAUTH + accessToken);
			
			HttpEntity entity = new HttpEntity(headers);
			
			Map<String, String> map = new HashMap<String, String>();
			HttpEntity<String> responseEntity = restTemplate.exchange(builder.build(), HttpMethod.GET, entity, String.class);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";

			response = gson.fromJson(restResponse, SalesforceAccountResponse.class);
			
			if(null != response  && ! response.getAgreementIDMatchStatus().equalsIgnoreCase(SF_RESP_NO_MATCH)) {
				
				response.setResultCode(RESULT_CODE_SUCCESS);
				
			} else {
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
		} catch (HttpClientErrorException e) {
			logger.error(" CSLRSalesforceService - createRestAccRegAndCallService() ::: "+e.getMessage());
			response.setResultCode(e.getStatusCode().toString());
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		} catch (Exception ex) {
			logger.error(" CSLRSalesforceService - createRestAccRegAndCallService() ::: "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}	
	
	public SalesforceDashboardResponse createRestDashboardAndCallService(CommunitySolarWebDashboardRequest request) {

		Gson gson = new Gson();
		SalesforceDashboardResponse response = new SalesforceDashboardResponse();
		String restResponse = null;
		try {
			
			String url = buildSalesforceDashboardURL(); 
			
			// create request body
			SalesforceDashboardRequest salesforceReq = new SalesforceDashboardRequest(); 
			
			salesforceReq.setLeaseId(request.getLeaseId());
			salesforceReq.setGetAccount(request.getAccountInfo());
			salesforceReq.setGetUserProduction(request.getUserProductionInfo());
			salesforceReq.setGetFacility(request.getFacilityInfo());
			salesforceReq.setGetFacilityProduction(request.getFacilityProductionInfo());
			salesforceReq.setGetContract(request.getContractInfo());
									
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(SALESFORCE_AUTHORIZATION, SALESFORCE_OAUTH + request.getAccessToken());
			
			HttpEntity entity = new HttpEntity(gson.toJson(salesforceReq), headers);
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class, map);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";

			response = gson.fromJson(restResponse, SalesforceDashboardResponse.class);
			
			
			if(null != response  && response.getMessage().equalsIgnoreCase(SF_RESP_MSG_SUCCESS)) {
				
				response.setResultCode(RESULT_CODE_SUCCESS);
				
			} else {
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
		} catch (HttpClientErrorException e) {
			logger.error(" CSLRSalesforceService - createRestDashboardAndCallService() ::: "+e.getMessage()); 
			
			response.setResultCode(e.getStatusCode().toString());
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		} catch (Exception ex) {
			logger.error(" CSLRSalesforceService - createRestDashboardAndCallService() ::: "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}	
	

	public SalesforceUpdateAccountResponse createRestUpdateAccountAndCallService(CommunitySolarWebUpdateRequest request) {
		Gson gson = new Gson();
		SalesforceUpdateAccountResponse response = new SalesforceUpdateAccountResponse();
		String restResponse = null;
		try {

			String url = buildSalesforceUpdateAccountURL(); 
		
			// create request body
			
			SalesforceUpdateRequest salesforceReq = new SalesforceUpdateRequest();
			
			salesforceReq.setLeaseId(request.getLeaseId());
			salesforceReq.setEmail(request.getEmail());
			salesforceReq.setPhone(request.getPhone());
			salesforceReq.setRegistrationDate(request.getRegistrationDate());
			salesforceReq.setLoginDate(request.getLoginDate());
			salesforceReq.setStreet(request.getStreet());
			salesforceReq.setCity(request.getCity());
			salesforceReq.setState(request.getState());
			salesforceReq.setZip(request.getZip());
			salesforceReq.setProductionEmailOptIn(request.getProductionEmailOptIn());
									
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(SALESFORCE_AUTHORIZATION, SALESFORCE_OAUTH + request.getAccessToken());
			

			HttpEntity entity = new HttpEntity(gson.toJson(salesforceReq), headers);
			logger.info("request:"+entity);
			logger.info("gson.toJson(salesforceReq):"+gson.toJson(salesforceReq));
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class, map);
			restResponse = null != responseEntity.getBody() ? responseEntity.getBody() : "";


			response = gson.fromJson(restResponse, SalesforceUpdateAccountResponse.class);
			
			if(null != response  && response.getMessage().equalsIgnoreCase(SF_RESP_MSG_SUCCESS) ) {
				
				response.setResultCode(RESULT_CODE_SUCCESS);
				
			} else {
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
			
		} catch (HttpClientErrorException e) {
			logger.error(" CSLRSalesforceService - createRestUpdateAccountAndCallService() ::: "+e.getMessage());
			response.setResultCode(e.getStatusCode().toString());
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		} catch (Exception ex) {

			logger.error(" CSLRSalesforceService - createRestUpdateAccountAndCallService() ::: "+ex.getMessage()); 
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}
	
	
	/**
	 * This function is to update the password after login
	 * @param SFUserProfileUpdateSyncRequest request

	 * @return
	 */
	public SFUserProfileUpdateResponse updateUserProfileSync(SFUserProfileUpdateSyncRequest request) {

		long startTime = CommonUtil.getStartTime();
		SFUserProfileUpdateResponse response = new SFUserProfileUpdateResponse();
		
		ValidateUserNameRequest validateUserReq = new ValidateUserNameRequest();
		validateUserReq.setStrLDAPOrg(LDAP_ORG_KEY_CSLR);
		validateUserReq.setStrUserName(request.getOldEmailAddress());
		
		ValidateUserNameRequest newvalidateUserReq = new ValidateUserNameRequest();
		newvalidateUserReq.setStrLDAPOrg(LDAP_ORG_KEY_CSLR);
		newvalidateUserReq.setStrUserName(request.getNewEmailAddress());
		
		Map<String, String> basicAttribute = new HashMap<String, String>();
		
		try{
						
			logger.debug(" Step1 : Verify oldemail  exist in LDAP");
			//First check if oldemail address exist in LDAP,Exist return error code and stop the process
			if (ldapHelper.validateUsername(validateUserReq).isbSuccessFlag() ) {
				response.setResultCode(Constants.SF_SYNC_ERROR_CODE01);
				response.setResultDescription(Constants.SF_SYNC_ERROR_CODE01_DESC);
				return response;
			}
			logger.debug(" Step2 : Verify newEmail address exist in LDAP");
			
			//Second check if newEmail address exist in LDAP,Exist return error code and stop the process
			if ( ! request.getOldEmailAddress().equalsIgnoreCase(request.getNewEmailAddress())) {
				if (!ldapHelper.validateUsername(newvalidateUserReq).isbSuccessFlag())
				{	
					response.setResultCode(Constants.SF_SYNC_ERROR_CODE02);
					response.setResultDescription(Constants.SF_SYNC_ERROR_CODE02_DESC);
					return response;
				}
			}
			logger.debug(" Update LDAP userid = newEmail	");
			
			logger.info(" Step3 : Update  in LDAP with new email adress");
			ChangeEmailUsrNameRequest changeEmailUsrNameRequest = new ChangeEmailUsrNameRequest();
			
			changeEmailUsrNameRequest.setStrLDAPOrg(LDAP_ORG_KEY_CSLR);
			changeEmailUsrNameRequest.setStrNewEmailUserName(request.getNewEmailAddress());
			changeEmailUsrNameRequest.setStrOldUsrName(request.getOldEmailAddress());
			
			ChangeUsernameResponse changeUsrNameRes =ldapHelper.changeUserName(changeEmailUsrNameRequest);
			
			logger.debug(" Step3a : modify attributes  in LDAP");
			
			basicAttribute.put("mail",changeEmailUsrNameRequest.getStrNewEmailUserName());
			
			ldapHelper.replaceExistingUserDetails("uid", changeEmailUsrNameRequest.getStrNewEmailUserName(),
					changeEmailUsrNameRequest.getStrLDAPOrg(), basicAttribute);
			
			
			if (changeUsrNameRes != null && !changeUsrNameRes.getErrorCode().equalsIgnoreCase(Constants.SUCCESS_CODE)) {
				response.setResultCode(Constants.SF_SYNC_ERROR_CODE03);
				response.setResultDescription(Constants.SF_SYNC_ERROR_CODE03_DESC);
				return response;
			} else {
				logger.debug(" Update user name in DB");
				logger.debug(" Step4 : Update user in webdatabse");
				
				Map<String, Object> resultList = cslrProfileDAO.UpdateUserDetails(request.getOldEmailAddress() ,request.getNewEmailAddress());
				
				if ( resultList.get(ERR_CODE_KEY) != null && !((String)resultList.get(ERR_CODE_KEY)).equalsIgnoreCase("0")) {
	    			
	    			//revert user details from ldap
					logger.debug(" Step5 : Revert user in LDAP due to error code:"+resultList.get(ERR_CODE_KEY));
					logger.debug(" Step5 : Revert user in LDAP");
					changeEmailUsrNameRequest.setStrLDAPOrg(LDAP_ORG_KEY_CSLR);
					changeEmailUsrNameRequest.setStrNewEmailUserName(request.getOldEmailAddress());
					changeEmailUsrNameRequest.setStrOldUsrName(request.getNewEmailAddress());
					
					changeUsrNameRes =ldapHelper.changeUserName(changeEmailUsrNameRequest);
					
					
					basicAttribute.put("mail",changeEmailUsrNameRequest.getStrNewEmailUserName());
					ldapHelper.replaceExistingUserDetails("uid", changeEmailUsrNameRequest.getStrNewEmailUserName(),
							changeEmailUsrNameRequest.getStrLDAPOrg(), basicAttribute);
					
					response.setResultCode(Constants.SF_SYNC_ERROR_CODE04);
					response.setResultDescription(Constants.SF_SYNC_ERROR_CODE04_DESC);
	    			
	    		} else {
	    			response.setResultCode(Constants.SUCCESS_CODE);
					response.setResultDescription(Constants.SF_SYNC_ERROR_CODE_DESC);
					return response;
	    		}
			}
    		
		} catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				response.setResultCode(SF_SYNC_ERROR_CODE05);
				response.setResultDescription(SF_SYNC_ERROR_CODE05_DESC);
			
		} finally {
			
			utilityloggerHelper.logTransaction("updateCSUserProfileSync", false, request,response, response.getResultDescription(), 
					CommonUtil.getElapsedTime(startTime), "", "", "0806");
			
		}
		
		return response;
	}
	
	/**
	 * @param accessToken
	 * @param contractDocumentId
	 * @return
	 */
	public byte[] getAgreementPDF(String accessToken, String contractDocumentId) {
		
		byte[] responseAsByteArray = null;
		try {
			logger.info("Trying to get the Lease Agreement PDF for ["+contractDocumentId+"]");
			//String docID = "00PA000000jCYN4MAO";
			//accessToken = "00D5B0000000Nif!AQIAQJjrsAYdiUtDJuUHLbqQ.zxgCRNK17DtuI8HLJKUZMoj9_yVeT5XLPJu3Pq.4fyAfpzi2diteir9zYxJFCJDkfF35ONe";
			String url = buildSalesforceAgreementPdfURL();
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(CONST_DOCID, contractDocumentId);
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(SALESFORCE_AUTHORIZATION, SALESFORCE_OAUTH + accessToken);
			
			HttpEntity entity = new HttpEntity(headers);
			Map<String, String> map = new HashMap<String, String>();
			HttpEntity<byte[]> responseEntity = restTemplate.exchange(builder.buildAndExpand(uriParams).toUri(), HttpMethod.GET, entity, byte[].class);
			
			if(responseEntity != null && null != responseEntity.getBody()) {
				responseAsByteArray = responseEntity.getBody();
				logger.info("Lease Agreement PDF is available in Salesforce for["+contractDocumentId+"]");
			} else {
				logger.error("Error in getting Least Agreement PDF for["+contractDocumentId+"]");
			}
		} catch (HttpClientErrorException hceEx) {
			logger.error("Error in getting Least Agreement PDF["+contractDocumentId+"]. Status Code["+hceEx.getStatusCode()+"] Status Desc["+hceEx.getStatusText()+"]", hceEx);
		} catch (Exception ex) {
			logger.error("Error in getting Least Agreement PDF["+contractDocumentId+"]. Error:", ex);
		}
		return responseAsByteArray;
	}
	
	/**
	 * This function is to delete user profile
	 * 
	 * @param DeleteUsrNameRequest request
	 * @return DeleteUserProfileResponse response
	 */
	public DeleteUserProfileResponse deleteUserProfile(DeleteUsrNameRequest request) {

		DeleteUserProfileResponse response = new DeleteUserProfileResponse();
				
		try{
			
			ldapHelper.deleteUser(request);

		} catch(Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
	return response;

	}	
	/**
	 * Create a thread safe CloseableHttpClient to communicate with Salesforce system 
	 * @return CloseableHttpClient - Returns the thread safe CloseableHttpClient
	 *         object
	 * @throws Exception
	 *             - Throws the exception.
	 */
	private HttpClient getCloseableHttpClient() throws Exception {

		// Create SSLContext by loading the trust store available in the JVM it
		// is running including self-signed certs.
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();

		// Create SSL Connection factory. Use of NoopHostnameVerifier prevents
		// accessing the salesforce server other than from the designated host.
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2" }, null,
				NoopHostnameVerifier.INSTANCE);
		HttpClientBuilder builder = HttpClients.custom();

		// Set retry count to 3. This tries 3 times before give-up.
		builder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));

		// Get the thread safe CloseableHttpClient

		return builder.setSSLSocketFactory(sslsf).build();
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		int timeOut = getRestCallTimeOut(SALESFORCE_TIME_OUT_IN_SEC, 60);
		if (logger.isDebugEnabled()) {
			logger.debug("TIME OUT FOR THE REST CALL::::::" + timeOut);
		}
		factory.setReadTimeout(timeOut);
		factory.setConnectTimeout(timeOut);
		factory.setHttpClient(getCloseableHttpClient());
		return factory;
	}

	private String buildSalesforceTokenURL() {
		return getEndPointUrl(SALESFORCE_TOKEN_SERVICE);
	}

	private String buildSalesforceGetAccountURL() {
		return getEndPointUrl(SALESFORCE_GET_ACCOUNT_SERVICE);
	}
	
	private String buildSalesforceAccountRegistrationURL() {
		return getEndPointUrl(SALESFORCE_ACCOUNT_REG_SERVICE);
	}
	private String buildSalesforceDashboardURL() {
		return getEndPointUrl(SALESFORCE_DASHBOARD_SERVICE);
	}
	
	private String buildSalesforceUpdateAccountURL() {
		return getEndPointUrl(SALESFORCE_UPDATE_ACCOUNT_SERVICE);
	}

	private String buildSalesforceAgreementPdfURL() {
		return getEndPointUrl(SALESFORCE_GET_AGREEMENT_PDF_SERVICE);
	}

	/*
	private int getRestCallTimeOut() {
		String timeOutStr = getTimeOutForKey(SALESFORCE_TIME_OUT_IN_SEC);
		if (org.apache.commons.lang.StringUtils.isNotBlank(timeOutStr)) {
			return Integer.parseInt(timeOutStr) * 1000;
		}
		return 60 * 1000;
	}
	*/
	
}
