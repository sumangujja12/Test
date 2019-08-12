package com.multibrand.resources;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;
import com.multibrand.service.IRWService;

/*
 * @Author bbachin1
 */

@RestController
public class IRWResource {
	
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private IRWService irwService;
	
	@PostMapping(value = "/irw/oammessage/read", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
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
	
	@PostMapping(value = "/irw/oammessage/write", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
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
	
	@PostMapping(value = "/irw/oammessage/check", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
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
