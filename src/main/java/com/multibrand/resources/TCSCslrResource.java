package com.multibrand.resources;

import java.util.ArrayList;
import java.util.List;

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
import com.multibrand.dto.response.TCSBPDetailsDTO;
import com.multibrand.service.CSLRSalesforceService;
import com.multibrand.service.TCSCslrService;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.DeleteUsrNameRequest;
import com.multibrand.vo.response.DeleteUserProfileResponse;
import com.multibrand.vo.response.SFUserProfileUpdateResponse;
import com.multibrand.vo.response.SalesforceAccountResponse;
import com.multibrand.vo.response.SalesforceDashboardResponse;
import com.multibrand.vo.response.SalesforceTokenResponse;
import com.multibrand.vo.response.SalesforceUpdateAccountResponse;
import com.multibrand.vo.response.TCSBPDetailsResponse;

/**
 * 
 * @author rpendur1
 * This resource is used to handle all Community Solar TCS read related API calls.
 */
@Component
@Path("tcsCslrResource")
public class TCSCslrResource implements Constants {
private static Logger logger = LogManager.getLogger(TCSCslrResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;

	@Autowired
	private TCSCslrService tcsCslrService;
	/**
	 * @param agreementId agreementId
	 * @return
	 */
	@POST
	@Path("getBPDetails")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
	public Response getBPDetails( @FormParam("agreementId") String agreementId){
		
		Response response = null;

		TCSBPDetailsResponse tcsBPDetailsResponse = new TCSBPDetailsResponse();
		List<TCSBPDetailsDTO> tcsBPDetailsDTOList = new  ArrayList<TCSBPDetailsDTO>();
		
		tcsBPDetailsDTOList = tcsCslrService.getBPDetails(agreementId);
		
		tcsBPDetailsResponse.setTcsBPDetailsList(tcsBPDetailsDTOList);

		response = Response.status(200).entity(tcsBPDetailsResponse).build();
				
		return response;
		
	}	
}
