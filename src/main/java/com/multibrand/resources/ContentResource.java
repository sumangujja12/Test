package com.multibrand.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.ComponentByItemIdRequest;
import com.multibrand.dto.request.ComponentByItemIdsRequest;
import com.multibrand.dto.request.ContentOfferRequest;
import com.multibrand.dto.request.ContentUserPrefRequest;
import com.multibrand.dto.request.MessageContentRequest;
import com.multibrand.service.ContentService;

/*
 * @Author bbachin1
 */

@Component
@Path("personalize")
public class ContentResource {
	
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private ContentService contentService;
	
	
	@POST
	@Path("offer")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response readPersonalizedOffer(ContentOfferRequest request) {
		String offerResponse = "";
		try{
			request.validateRequest();
			offerResponse = contentService.getOffer(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER:::", ex);
		}
		Response response = Response.status(Response.Status.OK).entity(offerResponse).build();
		return response;
	} 
	
	
	@POST
	@Path("userprefernce/update")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUserPreference(ContentUserPrefRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.updateUserPreference(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return response;
	}
	
	
	@POST
	@Path("component/itemid")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response readComponentByItemId(ComponentByItemIdRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readComponentByItemId(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return response;
	} 
	
	
	@POST
	@Path("component/itemids")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response readComponentByItemIds(ComponentByItemIdsRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readComponentByItemIds(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return response;
	}
	
	@POST
	@Path("gme/messagecontent")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response readMessageContent(MessageContentRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readMessageContent(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return response;
	}
	

}
