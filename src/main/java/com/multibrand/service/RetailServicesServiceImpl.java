package com.multibrand.service;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.dao.RetailServicesDAO;
import com.multibrand.dto.response.RetailServicesResponse;
import com.multibrand.vo.request.CheckReliantCustomerStatusRequest;
import com.multibrand.vo.response.CheckReliantCustomerStatusResponse;
import com.multibrand.vo.response.CheckZipSecurityEligibilityResponse;

@Component
public class RetailServicesServiceImpl extends BaseAbstractService implements RetailServicesService{
	
	@Autowired
	private RetailServicesDAO retailServicesDAO;

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Override
	public RetailServicesResponse readHouseAgeAndHHIncome(String addressIdorESID) {
		return retailServicesDAO.readHouseAgeAndHHIncome(addressIdorESID);
	}
	
	/**
	 * Call CCS REST service call to Read Reliant Customer Status
	 * @param request CheckReliantCustomerStatusRequest
	 * @return CheckReliantCustomerStatusResponse
	 * @throws Exception 
	 */
	public CheckReliantCustomerStatusResponse readReliantCustomerStatus(CheckReliantCustomerStatusRequest request) throws Exception {
		logger.debug("START :: RetailServicesServiceImpl.readReliantCustomerStatus");
		CheckReliantCustomerStatusResponse checkReliantCustomerStatusResponse = new CheckReliantCustomerStatusResponse();
		
			logger.info("Building the input args for Read Reliant Customer Status CCS REST call");
			String[] args = readReliantCustomerStatusArgs(request);
			String url = buildReliantCustomerStatusURL();
			MessageFormat urlFormat = new MessageFormat(url);
			url = urlFormat.format(args);
			logger.info("Read Reliant Customer Status CCS URL["+url+"]");

			org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
			
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
			HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info("Response received after Read Reliant Customer Status CCS call");
			
			String responseAsString = responseEntity.getBody();
			
			Gson gson = new Gson();
			boolean isCcsResponseSuccess = false;
			if(null != responseAsString) {
				logger.info("Read Reliant Customer Status Response is NOT empty");
				checkReliantCustomerStatusResponse = gson.fromJson(responseAsString, CheckReliantCustomerStatusResponse.class);
				logger.info("Read Reliant Customer Status Response is NOT empty and converted into required respose object");
				
				if(null != checkReliantCustomerStatusResponse 
						&& null != checkReliantCustomerStatusResponse.getCheckReliantCustomerStatusOutData() ) {
					
					isCcsResponseSuccess = true;
					checkReliantCustomerStatusResponse.setResultCode(RESULT_CODE_SUCCESS);
					
				} else {
					logger.info("Read Reliant Customer Status Response is empty and marked as FAILURE");
				}
			}
			
			if(!isCcsResponseSuccess) {
				checkReliantCustomerStatusResponse = new CheckReliantCustomerStatusResponse();
				checkReliantCustomerStatusResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				checkReliantCustomerStatusResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
		logger.debug("END :: RetailServicesServiceImpl.readReliantCustomerStatus");
		return checkReliantCustomerStatusResponse;
	}

	/**
	 * @param request
	 * @return
	 */
	private String[] readReliantCustomerStatusArgs(CheckReliantCustomerStatusRequest request) {
		
		String[] readReliantCustomerStatusInputArgs = new String[9];
		StringBuilder strBuilder = null;
		if(null == request) {
			return readReliantCustomerStatusInputArgs;
		}
		
		// /sap/opu/odata/sap/ZE_WEB_CUSTOMER_ACTIVE_CHECK_SRV/CustomerDetailsSet(EmailId={0},FirstName={1},LastName={2},Street={3},HouseNo={4},ApartmentNo={5},City={6},State={7},Zipcode={8})?sap-client=530&%24format=json
		/*
		 * Important Note:
		 * The order of building the String was made based on the CCS URL parameters input position.
		 * It is advised to keep the order as-is.  If it is required to modify, carefully 
		 * verify the CCS URL and change their position accordingly while building the String. 
		 */
		int iCount = 0;
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getEmail());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getfName());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getlName());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;

		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getStreet());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;

		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getHouseNo());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getApartmentNo());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getCity());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getState());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getZipCode());
		strBuilder.append(SINGLE_QUOTE);
		readReliantCustomerStatusInputArgs[iCount] = strBuilder.toString();
		iCount++;

		return readReliantCustomerStatusInputArgs;
	}
	
	private String buildReliantCustomerStatusURL() {
		return getEndPointUrl(CCS_READ_RELIANT_CUSTOMER_STATUS_URL);
	}

	/**
	 * Call CCS REST service call to Read Reliant Customer Status
	 * @param request CheckReliantCustomerStatusRequest
	 * @return CheckReliantCustomerStatusResponse
	 * @throws Exception 
	 */
	public CheckZipSecurityEligibilityResponse checkZipForSecurityEligibility(String zipCode) throws Exception {
		logger.debug("START :: RetailServicesServiceImpl.checkZipForSecurityEligibility");
		CheckZipSecurityEligibilityResponse checkZipSecurityEligibilityResponse = new CheckZipSecurityEligibilityResponse();
		
			logger.info("Building the input args for Security Eligibility CCS REST call");
			String[] args = checkZipForSecurityEligibilityArgs(zipCode);
			String url = buildSecurityEligibilityURL();
			MessageFormat urlFormat = new MessageFormat(url);
			url = urlFormat.format(args);
			logger.info("Security Eligibility CCS URL["+url+"]");

			org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
			
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth(PROP_CS_DEFAULT_WS_TIMEOUT_IN_SEC));
			HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info("Response received after Security Eligibility CCS call");
			
			String responseAsString = responseEntity.getBody();
			
			Gson gson = new Gson();
			boolean isCcsResponseSuccess = false;
			if(null != responseAsString) {
				logger.info("Security Eligibility Response is NOT empty");
				checkZipSecurityEligibilityResponse = gson.fromJson(responseAsString, CheckZipSecurityEligibilityResponse.class);
				logger.info("Security Eligibility Response is NOT empty and converted into required respose object");
				
				if(null != checkZipSecurityEligibilityResponse 
						&& null != checkZipSecurityEligibilityResponse.getCheckZipSecurityEligibilityOutData()) {
					isCcsResponseSuccess = true;
					checkZipSecurityEligibilityResponse.setResultCode(RESULT_CODE_SUCCESS);
					
				} else {
					logger.info("Security Eligibility Response Response is empty and marked as FAILURE");
				}
			}
			
			if(!isCcsResponseSuccess) {
				checkZipSecurityEligibilityResponse = new CheckZipSecurityEligibilityResponse();
				checkZipSecurityEligibilityResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				checkZipSecurityEligibilityResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
		logger.debug("END :: RetailServicesServiceImpl.checkZipForSecurityEligibility");
		return checkZipSecurityEligibilityResponse;
	}

	private String[] checkZipForSecurityEligibilityArgs(String zipCode) {
		String[] checkZipForSecurityEligibilityInputArgs = new String[1];
		StringBuilder strBuilder = null;
		if(null == zipCode) {
			return checkZipForSecurityEligibilityInputArgs;
		}
		
		/*
		 * Important Note:
		 * The order of building the String was made based on the CCS URL parameters input position.
		 * It is advised to keep the order as-is.  If it is required to modify, carefully 
		 * verify the CCS URL and change their position accordingly while building the String. 
		 */
		int iCount = 0;
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(zipCode);
		strBuilder.append(SINGLE_QUOTE);
		checkZipForSecurityEligibilityInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		return checkZipForSecurityEligibilityInputArgs;
	}
	
	private String buildSecurityEligibilityURL() {
		return getEndPointUrl(CCS_CHECK_SECURITY_ELIGIBILITY_URL);
	}
}
