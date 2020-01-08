package com.multibrand.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.helper.WeeklySummaryEmailHelper;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;

/**
 * 
 * This resource is used to handle all Historical WeeklySummaryEmail Service related calls.
 */
@Component
@Path("/wse")
public class WeeklySummaryEmailResource  extends BaseResource {
	
	@Autowired
	private WeeklySummaryEmailHelper weeklySummaryEmailHelper;
	
	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * Get WSE Archive data from 
	 * @param contract_account_number
	 * @param company_code
	 * @return
	 */
	@GET
	@Path("/historicalWse")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response gethistoricalWse(
			@FormParam("contract_account_number") String contractAccountNumber,
			@FormParam("company_code") String companyCode){
		
		logger.debug("Start-[WeeklySummaryEmailResource.gethistoricalWse]");
		Response response = null;
		
		if(contractAccountNumber == null || contractAccountNumber.trim().length() == 0) {
	        return Response.serverError().entity("Contract Account Number cannot be blank").build();
	    }
		if(companyCode == null || companyCode.trim().length() == 0) {
	        return Response.serverError().entity("Company Code cannot be blank").build();
	    }
		
		WeeklySummaryEmailResponse weeklySummaryEmailResponse = weeklySummaryEmailHelper.gethistoricalWse(contractAccountNumber, companyCode);
		response = Response.status(200).entity(weeklySummaryEmailResponse).build();
		logger.info("End-[WeeklySummaryEmailResource-gethistoricalWse]");
		
		return response;
	}
}
