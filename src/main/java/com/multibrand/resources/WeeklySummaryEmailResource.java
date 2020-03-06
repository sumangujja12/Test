package com.multibrand.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.helper.WeeklySummaryEmailHelper;
import com.multibrand.vo.request.WeeklySummaryEmailRequest;
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
	 * @param WeeklySummaryEmailRequest
	 * @return
	 */
	@POST
	@Path("/historicalWse")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON})
	public Response gethistoricalWse(@Valid WeeklySummaryEmailRequest request){
		
		logger.debug("Start-[WeeklySummaryEmailResource.gethistoricalWse]");
		Response response = null;
			
		WeeklySummaryEmailResponse weeklySummaryEmailResponse = weeklySummaryEmailHelper.gethistoricalWse(request);
		response = Response.status(200).entity(weeklySummaryEmailResponse).build();
		logger.info("End-[WeeklySummaryEmailResource-gethistoricalWse]");
		
		return response;
	}
}
