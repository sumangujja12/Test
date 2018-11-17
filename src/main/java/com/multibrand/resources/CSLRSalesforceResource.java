package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.CommunitySolarWebDashboardRequest;
import com.multibrand.dto.request.CommunitySolarWebUpdateRequest;
import com.multibrand.dto.request.SFUserProfileUpdateSyncRequest;
import com.multibrand.service.CSLRSalesforceService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.DeleteUsrNameRequest;
import com.multibrand.vo.response.DeleteUserProfileResponse;
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
@Component
@Path("cslrSalesforceResource")
public class CSLRSalesforceResource implements Constants {
private static Logger logger = LogManager.getLogger(CSLRSalesforceResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@Autowired
	private CSLRSalesforceService cslrSalesforceService;
	
	/** This service provides the oauth token details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@POST
	@Path("getAccessToken")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAccessToken(@Context HttpHeaders hh, @Context HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.getAccessToken :: START");
		Response response = null;
		
		SalesforceTokenResponse salesforceTokenResponse = cslrSalesforceService.createRestTokenTemplateAndCallService();
		
		response = Response.status(200).entity(salesforceTokenResponse).build();
		logger.debug("End CSSalesforceResource.getAccessToken :: END");
		return response;
	}
	
	/** This service provides the account profile details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@POST
	@Path("getAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response getAccount(
			@FormParam("access_token") String accessToken,
			@FormParam("lease_Id") String leaseId,
			@Context HttpHeaders hh,
			@Context HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.getAccount :: START");
		Response response = null;
		
		SalesforceAccountResponse salesforceAccountResponse = cslrSalesforceService.createRestGetAccountAndCallService(accessToken, leaseId);
		
		response = Response.status(200).entity(salesforceAccountResponse).build();
		logger.debug("End CSSalesforceResource.getAccount :: END");
		return response;
	}
	
	/** This service provides the account profile details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@POST
	@Path("accountRegistration")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response accountRegistration(
			@FormParam("access_token") String accessToken,
			@FormParam("lease_Id") String leaseId,
			@FormParam("utility_Account_Number") String utilityAccountNumber,
			@Context HttpHeaders hh,
			@Context HttpServletRequest request){
		
		logger.debug("Start CSSalesforceResource.accountRegistration :: START");
		Response response = null;
		
		SalesforceAccountResponse salesforceAccountResponse = cslrSalesforceService.createRestAccRegAndCallService(accessToken, leaseId, utilityAccountNumber);
		
		response = Response.status(200).entity(salesforceAccountResponse).build();
		logger.debug("End CSSalesforceResource.accountRegistration :: END");
		return response;
	}
	
	/** This service provides the user production and community Solar production details from Salesforce system.
	 * 
	 * @author rpendur1
	 * 
	 */
	@POST
	@Path("dashboardPortalService")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response dashboardService(@Valid CommunitySolarWebDashboardRequest request){
		
		logger.debug("Start CSSalesforceResource.dashboardService :: START");
				
		Response response = null;
		
		SalesforceDashboardResponse salesforceDashboardResponse = cslrSalesforceService.
				createRestDashboardAndCallService(request );
		
		response = Response.status(200).entity(salesforceDashboardResponse).build();
		logger.debug("End CSSalesforceResource.accountRegistration :: END");
		return response;
	}		
	/**
	 * This service provides the account profile details from Salesforce system
	 * @author rpendur1
	 * @return
	 */
	@POST
	@Path("updateAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response updateAccount( @Valid CommunitySolarWebUpdateRequest request){
		
		logger.debug("Start CSSalesforceResource.updateAccount :: START");
		Response response = null;
		
		SalesforceUpdateAccountResponse tempResponse = cslrSalesforceService.createRestUpdateAccountAndCallService(request);
		
		response = Response.status(200).entity(tempResponse).build();
			
		logger.debug("End CSSalesforceResource.updateAccount :: END");
		
		return response;
		
	}
	/**
	 * @param SFUserProfileUpdateSyncRequest request
	 * @return
	 */
	@POST
	@Path("/protected/updateSFUserProfile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
	public Response UpdateUserProfileSync( @Valid SFUserProfileUpdateSyncRequest request){
		
		Response response = null;
		
		SFUserProfileUpdateResponse tempResponse = new SFUserProfileUpdateResponse();
		
		tempResponse = cslrSalesforceService.updateUserProfileSync(request);
		response = Response.status(200).entity(tempResponse).build();
				
		return response;
		
	}
	/**
	 * @param SFUserProfileUpdateSyncRequest request
	 * @return
	 */
	@POST
	@Path("deleteUser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response DeleteUserProfileResponse( @Valid DeleteUsrNameRequest request){
		
		Response response = null;
		
		DeleteUserProfileResponse tempResponse = new DeleteUserProfileResponse();
		
		tempResponse = cslrSalesforceService.deleteUserProfile(request);
		response = Response.status(200).entity(tempResponse).build();
				
		return response;
		
	}
	
	@POST
	@Path("getSignedAgreementPDF")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_OCTET_STREAM})
	public Response getAgreementPDF(
			@FormParam("accessToken") String accessToken,
			@FormParam("contractDocumentId") String contractDocumentId,
			@Context HttpHeaders hh,
			@Context HttpServletRequest request){
		
		logger.debug("Start CSLRSalesforceResource.getAgreementPDF :: START");
		Response response = null;
		byte[] fileContent = null;
		
		try {
			fileContent = cslrSalesforceService.getAgreementPDF(accessToken, contractDocumentId);
			/*// Read a PDF from server location
			File pdfFile = new File(request.getSession().getServletContext().getRealPath("pdf/CS Portal Content.pdf"));
			FileInputStream fis = new FileInputStream(pdfFile);
			fileContent = IOUtils.toByteArray(fis);
			*/
			ResponseBuilder  responseBuilder = Response.ok(fileContent);
			responseBuilder.type(MediaType.APPLICATION_OCTET_STREAM);
			responseBuilder.header(META_CONTENT_DISPOSITION, META_FILENAME + 
					StringUtils.defaultIfEmpty(contractDocumentId, CONST_AGREEMENT_PDF_DEFAULT_NAME) + CONST_DOT_PDF);
			response = responseBuilder.build();
		} catch (Exception ex) {
			response = null;
			logger.error("Error occured while retrieving the Lease Agreement PDF for doc id ["+contractDocumentId+"]", ex);
			ex.printStackTrace();
		}
		
		logger.debug("END CSLRSalesforceResource.getAgreementPDF :: END");
		return response;
	}	
	
}
