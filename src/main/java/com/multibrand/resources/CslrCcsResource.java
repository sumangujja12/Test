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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.CslrUpdateBillinlgAddrRequest;
import com.multibrand.service.CslrCcsService;
import com.multibrand.vo.response.CslrCcsProfileResponse;
import com.multibrand.vo.response.CslrUpdateBillinlgAddrResponse;

/**
 * 
 * @author mdasari1
 * This resource is used to handle all Community Solar CCS related API calls.
 */
@Component
@Path("cslrCcsResource")
public class CslrCcsResource {
private static Logger logger = LogManager.getLogger(CslrCcsResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;
	
	//@Autowired
	//private CSLRSalesforceService cslrSalesforceService;

	@Autowired
	private CslrCcsService cslrCcsService;
	
	/**
	 * Get CCS Profile fro Community Solar user
	 * @param caNumber
	 * @param bpNumber
	 * @param leaseId
	 * @param hh
	 * @param request
	 * @return
	 */
	@POST
	@Path("getCslrProfile")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response getCslrProfile(
			@FormParam("caNumber") String caNumber,
			@FormParam("bpNumber") String bpNumber,
			@FormParam("lease_Id") String leaseId,
			@Context HttpHeaders hh,
			@Context HttpServletRequest request){
		
		logger.debug("Start CslrCcsResource.getCslrProfile :: START");
		Response response = null;
		
		CslrCcsProfileResponse cslrCcsProfileResponse = cslrCcsService.getCslrProfile(caNumber, bpNumber, leaseId);
		//Gson gson = new Gson();
		//logger.info(gson.toJson(cslrCcsProfileResponse, CslrCcsProfileResponse.class));
		response = Response.status(200).entity(cslrCcsProfileResponse).build();
		logger.debug("END CslrCcsResource.getCslrProfile :: START");
		return response;
	}
	
	@POST
	@Path("cslrUpdateBillingAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response updateBillingAddr(@Valid CslrUpdateBillinlgAddrRequest request) {
		
		logger.debug("Start CslrCcsService.updateBillingAddr :: START");
		
		Response response = null;
		
		CslrUpdateBillinlgAddrResponse cslrUpdateBillinlgAddrResponse = cslrCcsService.updateBillingAddress(request);
		response = Response.status(200).entity(cslrUpdateBillinlgAddrResponse).build();

		logger.debug("End CslrCcsService.updateBillingAddr :: END");
		
		return response;
	}
	
}
