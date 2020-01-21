package com.multibrand.resources;

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

import com.multibrand.dto.request.WebHookRequest;
import com.multibrand.helper.WebHookHelper;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.WebHookResponse;

/**
 * 
 * @author vnagani
 * This resource is used to handle all vanilla direct related API calls.
 */
@Component
@Path("public/webHookResource")
public class WebHookResource implements Constants {
private static Logger logger = LogManager.getLogger(WebHookResource.class);
	
	@Autowired
	private WebHookHelper webHookHelper;
	
	/**
	 *  This service add VD payment details to database
	 * @param request WebHookRequest
	 * @return
	 */
	@POST
	@Path("acceptPayment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addWebHookData(WebHookRequest request){
				
		Response response=null;
		logger.info("Start-[WebHookResource-addWebHookData]");
		WebHookResponse userIdResponse = webHookHelper.addWebHookData(request);
		if(userIdResponse.getResultcode().equalsIgnoreCase(Constants.ZERO)) {
			response=Response.status(200).entity(userIdResponse).build();
		} else {
			response=Response.status(500).entity(userIdResponse).build();
		}
		
		logger.info("End-[WebHookResource-addWebHookData]");
		return response;
	}
	
	
}
