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

import com.multibrand.dto.request.CCSEmailRequest;
import com.multibrand.dto.request.EmailRequest;
import com.multibrand.dto.response.EmailResponse;
import com.multibrand.service.EmailService;
import com.multibrand.util.NRGRestUtil;

/*
 * @Author bbachin1
 *
 */

@Component
@Path("emails")
public class EmailResource {
	
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private EmailService emailService;
	
	@POST
	@Path("send/billPreference")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response sendEBillEmail(CCSEmailRequest request) {
		EmailResponse emailResult = new EmailResponse();
		try{
			request.validateRequest();
			EmailRequest emailRequest = new EmailRequest();
			emailRequest.populateRequestForEBillBasedOnBrand(request);
			emailResult = emailService.sendEmail(emailRequest);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE SENDING CCS BILL PREFERENCE EMAIL:::", ex);
			emailResult.setResultdescription(NRGRestUtil.getMessageFromException(ex));
			emailResult.setResultcode("3");
		}
		Response response = Response.status(Response.Status.OK).entity(emailResult).build();
		return response;
	} 
	
	
	@POST
	@Path("send")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response sendEmail(EmailRequest request) {
		EmailResponse emailResult = emailService.sendEmail(request);
		Response response = Response.status(Response.Status.OK).entity(emailResult).build();
		return response;
	}
	
}
