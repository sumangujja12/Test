package com.multibrand.resources;

import static java.util.Collections.singletonList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.request.CommunitySolarWebDashboardRequest;
import com.multibrand.dto.request.CommunitySolarWebUpdateRequest;
import com.multibrand.dto.request.SFUserProfileUpdateSyncRequest;
import com.multibrand.service.CSLRSalesforceService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.DeleteUsrNameRequest;
import com.multibrand.vo.response.DeleteUserProfileResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.SFUserProfileUpdateResponse;
import com.multibrand.vo.response.SalesforceAccountResponse;
import com.multibrand.vo.response.SalesforceDashboardResponse;
import com.multibrand.vo.response.SalesforceTokenResponse;
import com.multibrand.vo.response.SalesforceUpdateAccountResponse;

/**
 * 
 * @author rpendur1
 * This resource is used to handle all Community Solar Salesforce related API calls.
 */
@RestController
public class CSLRSalesforceResource implements Constants {
private static Logger logger = LogManager.getLogger(CSLRSalesforceResource.class);
	
 	
	@Autowired
	private CSLRSalesforceService cslrSalesforceService;
	
	/** This service provides the oauth token details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@PostMapping(value = "/cslrSalesforceResource/getAccessToken", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getAccessToken(HttpHeaders hh, HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.getAccessToken :: START");
		//Response response = null;
		
		SalesforceTokenResponse salesforceTokenResponse = cslrSalesforceService.createRestTokenTemplateAndCallService();
		
		//response = Response.status(200).entity(salesforceTokenResponse).build();
		logger.debug("End CSSalesforceResource.getAccessToken :: END");
		return new ResponseEntity<GenericResponse>(salesforceTokenResponse, HttpStatus.OK);
	}
	
	/** This service provides the account profile details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@PostMapping(value = "/cslrSalesforceResource/getAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getAccount(
			@RequestParam("access_token") String accessToken,
			@RequestParam("lease_Id") String leaseId,
			HttpHeaders hh,
			HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.getAccount :: START");
		//Response response = null;
		
		SalesforceAccountResponse salesforceAccountResponse = cslrSalesforceService.createRestGetAccountAndCallService(accessToken, leaseId);
		
		//response = Response.status(200).entity(salesforceAccountResponse).build();
		logger.debug("End CSSalesforceResource.getAccount :: END");
		return new ResponseEntity<GenericResponse>(salesforceAccountResponse, HttpStatus.OK);
	}
	
	/** This service provides the account profile details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@PostMapping(value = "/cslrSalesforceResource/accountRegistration", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> accountRegistration(
			@RequestParam("access_token") String accessToken,
			@RequestParam("lease_Id") String leaseId,
			@RequestParam("utility_Account_Number") String utilityAccountNumber,
			HttpHeaders hh,
			HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.accountRegistration :: START");
		//Response response = null;
		
		SalesforceAccountResponse salesforceAccountResponse = cslrSalesforceService.createRestAccRegAndCallService(accessToken, leaseId, utilityAccountNumber);
		
		//response = Response.status(200).entity(salesforceAccountResponse).build();
		logger.debug("End CSSalesforceResource.accountRegistration :: END");
		return new ResponseEntity<GenericResponse>(salesforceAccountResponse, HttpStatus.OK);
	}
	
	/** This service provides the user production and community Solar production details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@PostMapping(value = "/cslrSalesforceResource/dashboardPortalService", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> dashboardService(@Valid CommunitySolarWebDashboardRequest request){
		
		logger.debug("Start CSSalesforceResource.dashboardService :: START");
				
		//Response response = null;
		
		SalesforceDashboardResponse salesforceDashboardResponse = cslrSalesforceService.
				createRestDashboardAndCallService(request );
		
		//response = Response.status(200).entity(salesforceDashboardResponse).build();
		logger.debug("End CSSalesforceResource.accountRegistration :: END");
		return new ResponseEntity<GenericResponse>(salesforceDashboardResponse, HttpStatus.OK);
	}		
	/**
	 * This service provides the account profile details from Salesforce system
	 * @author rpendur1
	 * @return
	 */
	@PostMapping(value = "/cslrSalesforceResource/updateAccount", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> updateAccount( @Valid CommunitySolarWebUpdateRequest request){
		
		logger.debug("Start CSSalesforceResource.updateAccount :: START");
		//Response response = null;
		
		SalesforceUpdateAccountResponse tempResponse = cslrSalesforceService.createRestUpdateAccountAndCallService(request);
		
		//response = Response.status(200).entity(tempResponse).build();
			
		logger.debug("End CSSalesforceResource.updateAccount :: END");
		
		return new ResponseEntity<GenericResponse>(tempResponse, HttpStatus.OK);
		
	}
	/**
	 * @param SFUserProfileUpdateSyncRequest request
	 * @return
	 */
	@PostMapping(value = "/cslrSalesforceResource/protected/updateSFUserProfile", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GenericResponse> UpdateUserProfileSync( @Valid SFUserProfileUpdateSyncRequest request){
		
		//Response response = null;
		
		SFUserProfileUpdateResponse tempResponse = new SFUserProfileUpdateResponse();
		
		tempResponse = cslrSalesforceService.updateUserProfileSync(request);
		//response = Response.status(200).entity(tempResponse).build();
				
		return new ResponseEntity<GenericResponse>(tempResponse, HttpStatus.OK);
		
	}
	/**
	 * @param SFUserProfileUpdateSyncRequest request
	 * @return
	 */
	@PostMapping(value = "/cslrSalesforceResource/deleteUser", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GenericResponse> DeleteUserProfileResponse( @Valid DeleteUsrNameRequest request){
		
		//Response response = null;
		
		DeleteUserProfileResponse tempResponse = new DeleteUserProfileResponse();
		
		tempResponse = cslrSalesforceService.deleteUserProfile(request);
		//response = Response.status(200).entity(tempResponse).build();
				
		return new ResponseEntity<GenericResponse>(tempResponse, HttpStatus.OK); 
		
	}
	
	@PostMapping(value = "/cslrSalesforceResource/getSignedAgreementPDF", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<byte[]> getAgreementPDF(
			@RequestParam("accessToken") String accessToken,
			@RequestParam("contractDocumentId") String contractDocumentId,
			HttpHeaders hh,
			HttpServletRequest request){
		
		logger.debug("Start CSLRSalesforceResource.getAgreementPDF :: START");
		ResponseEntity<byte[]> response = null;
		byte[] fileContent = null;
		
		try {
			fileContent = cslrSalesforceService.getAgreementPDF(accessToken, contractDocumentId);
			/*// Read a PDF from server location
			File pdfFile = new File(request.getSession().getServletContext().getRealPath("pdf/CS Portal Content.pdf"));
			FileInputStream fis = new FileInputStream(pdfFile);
			fileContent = IOUtils.toByteArray(fis);
			*/
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String,String>();
			headers.put(HTTP.CONTENT_TYPE, singletonList(MediaType.APPLICATION_OCTET_STREAM_VALUE));
			headers.put(META_CONTENT_DISPOSITION, singletonList(META_FILENAME + 
					StringUtils.defaultIfEmpty(contractDocumentId, CONST_AGREEMENT_PDF_DEFAULT_NAME) + CONST_DOT_PDF));
			response = new ResponseEntity<byte[]>(fileContent,headers,  HttpStatus.OK);
		} catch (Exception ex) {
			response = null;
			logger.error("Error occured while retrieving the Lease Agreement PDF for doc id ["+contractDocumentId+"]", ex);
			ex.printStackTrace();
		}
		
		logger.debug("END CSLRSalesforceResource.getAgreementPDF :: END");
		return response;
	}	
	
}
