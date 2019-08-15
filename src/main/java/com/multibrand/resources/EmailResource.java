package com.multibrand.resources;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.request.CCSEmailRequest;
import com.multibrand.dto.request.EmailRequest;
import com.multibrand.dto.response.EmailResponse;
import com.multibrand.service.EmailService;
import com.multibrand.util.NRGRestUtil;

/*
 * @Author bbachin1
 *
 */
@RestController
public class EmailResource {
	
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private EmailService emailService;
	@PostMapping(value = "/emails/send/billPreference", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EmailResponse sendEBillEmail(CCSEmailRequest request) {
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
		//Response response = Response.status(Response.Status.OK).entity(emailResult).build();
		return emailResult;
	} 
	
	@PostMapping(value = "/emails/send", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EmailResponse sendEmail(EmailRequest request) {
		EmailResponse emailResult = emailService.sendEmail(request);
		//Response response = Response.status(Response.Status.OK).entity(emailResult).build();
		return emailResult;
	}
	
}
