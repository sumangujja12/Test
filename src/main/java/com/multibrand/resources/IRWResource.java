package com.multibrand.resources;

import org.apache.logging.log4j.LogManager;
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
	
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(IRWResource.class);
	
	@Autowired
	private IRWService irwService;
	
	@PostMapping(value = "/irw/oammessage/read", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public OAMMessageResponse readOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.readOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE OAM MESSAGES:::", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return oamMessageRes;
	} 
	
	@PostMapping(value = "/irw/oammessage/write", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public OAMMessageResponse writeOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.writeOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE WRITING THE OAM MESSAGES:::", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return oamMessageRes;
	} 
	
	@PostMapping(value = "/irw/oammessage/check", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public OAMMessageResponse checkOAMMessage(OAMMessageRequest request) {
		OAMMessageResponse oamMessageRes = new OAMMessageResponse();
		try{
			request.validateRequest();
			oamMessageRes = irwService.checkOAMMessage(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE CHECKING THE OAM MESSAGES:::", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(oamMessageRes).build();
		return oamMessageRes;
	} 
	

}
