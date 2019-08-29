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

import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;
import com.multibrand.service.IRWService;

/*
 * @Author bbachin1
 */

@Component
@Path("irw")
public class IRWResource {
	
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private IRWService irwService;
	
	
	@POST
	@Path("oammessage/read")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response readOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.readOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE OAM MESSAGES:::", ex);
		}
		Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return response;
	} 
	
	
	@POST
	@Path("oammessage/write")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response writeOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.writeOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE WRITING THE OAM MESSAGES:::", ex);
		}
		Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return response;
	} 
	
	
	@POST
	@Path("oammessage/check")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response checkOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.checkOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE CHECKING THE OAM MESSAGES:::", ex);
		}
		Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return response;
	} 
	

}
