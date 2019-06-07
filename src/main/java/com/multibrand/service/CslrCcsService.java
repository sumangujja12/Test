package com.multibrand.service;

import java.text.MessageFormat;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.multibrand.dto.request.CslrUpdateBillinlgAddrRequest;
import com.multibrand.vo.response.CslrCcsProfileResponse;
import com.multibrand.vo.response.CslrUpdateBillinlgAddrResponse;


@Service("cslrCcsService")
public class CslrCcsService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	
	/**
	 * Update Billing Address at BP Level in CCS for Community Solar brand
	 * @param request
	 * @return
	 */
	public CslrUpdateBillinlgAddrResponse updateBillingAddress(CslrUpdateBillinlgAddrRequest request) {
		logger.debug("START :: CslrCcsService.updateBillingAddress");
		CslrUpdateBillinlgAddrResponse cslrUpdateBillinlgAddrResponse = new CslrUpdateBillinlgAddrResponse();
		
		try {
			logger.info("Building the input args for CSLR Update Billing Addr CCS call");
			String[] args = getUpdateBillAddrArgs(request);
			String url = buildCCSUrlForCslrUpdateBillingAddress();
			MessageFormat urlFormat = new MessageFormat(url);
			url = urlFormat.format(args);
			logger.info("CSLR Update Billing Addr CCS URL["+url+"]");

			org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
			
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth());
			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info("Response received after CSLR Update Billing Addr in CCS");
			
			String responseAsString = responseEntity.getBody();
			
			Gson gson = new Gson();
			boolean isCcsResponseSuccess = false;
			if(null != responseAsString) {
				logger.info("CSLR Update Billing Addr Response is NOT empty");
				cslrUpdateBillinlgAddrResponse = gson.fromJson(responseAsString, CslrUpdateBillinlgAddrResponse.class);
				logger.info("CSLR Update Billing Addr Response is NOT empty and converted into required respose object");
				if(null != cslrUpdateBillinlgAddrResponse 
						&& null != cslrUpdateBillinlgAddrResponse.getCslrUpdateBillinlgAddrVO()
						&& StringUtils.isNotEmpty(cslrUpdateBillinlgAddrResponse.getCslrUpdateBillinlgAddrVO().getCcsResponseStatusMessage())) {
					if(CCS_UPD_BILL_ADDR_SUCCESS_RESPONSE_MSG.equals(cslrUpdateBillinlgAddrResponse.getCslrUpdateBillinlgAddrVO().getCcsResponseStatusMessage())) {
						logger.info("CSLR Update Billing Addr Response is NOT empty and received SUCCESS ["+
								cslrUpdateBillinlgAddrResponse.getCslrUpdateBillinlgAddrVO().getCcsResponseStatusMessage() +"] message");
						isCcsResponseSuccess = true;
						cslrUpdateBillinlgAddrResponse.setResultCode(RESULT_CODE_SUCCESS);
					} else {
						logger.info("CSLR Update Billing Addr Response is NOT empty and marked as FAILURE. CCS Msg is ["+
								cslrUpdateBillinlgAddrResponse.getCslrUpdateBillinlgAddrVO().getCcsResponseStatusMessage() +"]");
					}
				}
			}
			
			if(!isCcsResponseSuccess && cslrUpdateBillinlgAddrResponse!=null) {
				cslrUpdateBillinlgAddrResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				cslrUpdateBillinlgAddrResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
			
		} catch (Exception ex) {
			cslrUpdateBillinlgAddrResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			cslrUpdateBillinlgAddrResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Error occured while Updating Billing Address in CCS for CSLR.", ex);
		}
		logger.debug("END :: CslrCcsService.updateBillingAddress");
		return cslrUpdateBillinlgAddrResponse;
	}
	
	/**
	 * Retrieve User Profile from CCS for NRG Community Solar users
	 * @param ca
	 * @param bp
	 * @param leaseId
	 * @return
	 */
	public CslrCcsProfileResponse getCslrProfile(String ca, String bp, String leaseId) {
		logger.debug("START :: CslrCcsService.getCslrProfile");
		CslrCcsProfileResponse profileResponse = new CslrCcsProfileResponse();
		
		try {
			String[] args = new String[]{SINGLE_QUOTE+leaseId+SINGLE_QUOTE, SINGLE_QUOTE+bp+SINGLE_QUOTE, SINGLE_QUOTE+ca+SINGLE_QUOTE};
			String url = buildCCSUrlForCslrProfile();
			MessageFormat urlFormat = new MessageFormat(url);
			url = urlFormat.format(args);
			logger.info("CSLR CCS Profile URL with args["+url+"]");
			
			org.springframework.http.HttpHeaders headers = getBasicAuthSpringHttpHeadersForCCS();
			
			RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactoryForBasicAuth());
			HttpEntity<String> request = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			logger.info("After invoking CSLR CCS Profile call");
			
			String responseAsString = response.getBody();
			
			Gson gson = new Gson();
			boolean isCcsResponseSuccess = false;
			if(null != responseAsString) {
				logger.info("CSLR CCS Profile Response["+responseAsString+"]");
				profileResponse = gson.fromJson(responseAsString, CslrCcsProfileResponse.class);
				if(null != profileResponse.getCslrCcsProfileVO() 
						&& StringUtils.isNotEmpty(profileResponse.getCslrCcsProfileVO().getLeaseId()) 
						&& StringUtils.isNotEmpty(profileResponse.getCslrCcsProfileVO().getBpNumber())
						&& StringUtils.isNotEmpty(profileResponse.getCslrCcsProfileVO().getCaNumber())
						) {
					isCcsResponseSuccess = true;
					logger.info("CSLR CCS Profil eResponse status[SUCCESS]");
					profileResponse.setResultCode(RESULT_CODE_SUCCESS);
				}
			}
			
			if(!isCcsResponseSuccess) {
				logger.info("CSLR CCS Profile Response status[FAILURE]");
				profileResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				profileResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			}
		} catch (Exception ex) {
			logger.error("Error occured while retrieving CSLR CCS AR/Profile data.", ex); 
			profileResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			profileResponse.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
		}
		logger.debug("END :: CslrCcsService.getCslrProfile");
		return profileResponse;
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

	
	/**
	 * @return
	 */
	private String buildCCSUrlForCslrProfile() {
		return getEndPointUrl(CCS_GET_CSLR_PROFILE);
	}

	private String buildCCSUrlForCslrUpdateBillingAddress() {
		return getEndPointUrl(CCS_UPDATE_CSLR_BILLING_ADDR_AT_BP_LEVEL);
	}

	/**
	 * @param request
	 * @return
	 */
	private String[] getUpdateBillAddrArgs(CslrUpdateBillinlgAddrRequest request) {
		String[] updateBillAddrInputArgs = new String[13];
		StringBuilder strBuilder = null;
		if(null == request) {
			return updateBillAddrInputArgs;
		}
		
		/*
		 * Important Note:
		 * The order of building the String was made based on the CCS URL parameters input position.
		 * It is advised to keep the order as-is.  If at at it is required to modify, carefully 
		 * verify the CCS URL and change their position accordingly while building the String. 
		 */
		int iCount = 0;
		//Addr Number
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getAddrNumber());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		//City
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getCity());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;
		
		//Country
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getCountry());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//Street Number
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getStreetNum());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//Unit/APT Number
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getUnitNum());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//Lease ID
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getLeaseId());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//NameCo
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getNameCo());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//BP Number
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getBpNumber());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//Zip Code
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getZipCode());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//PO Box
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getPoBox());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//State
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getState());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//Street Name
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getStreetName());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();
		iCount++;

		//CA Number
		strBuilder = new StringBuilder();
		strBuilder.append(SINGLE_QUOTE);
		strBuilder.append(request.getCaNumber());
		strBuilder.append(SINGLE_QUOTE);
		updateBillAddrInputArgs[iCount] = strBuilder.toString();

		return updateBillAddrInputArgs;
	}
	
}
