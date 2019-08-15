package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.BillingBO;
import com.multibrand.bo.WebAgentBO;
import com.multibrand.vo.response.UpdateContactInfoResponse;
import com.multibrand.vo.response.billingResponse.UpdatePaperFreeBillingResponse;

@RestController
public class WebAgentResource {

	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	@Autowired
	WebAgentBO webAgentBO;
	
	/** Object of BillingBO class. */
	@Autowired
	private BillingBO billingBO;
	
	@PostMapping(value = "/webAgent/updateEmailID", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UpdateContactInfoResponse updateContactInfo(@RequestParam("userName")String userName,@RequestParam("email")String email){
		
		//Response response = null;
		UpdateContactInfoResponse updateContactInfoResponse=webAgentBO.updateEmail(userName, email, "0271", httpRequest.getSession().getId());
		//response = Response.status(200).entity(updateContactInfoResponse).build();
		return updateContactInfoResponse;
		
	} 
	
	/** This service is to update the bill delivery option
	 * 
	 * @param accountNumber		Customer Account Number
	 * @param flag			
	 * @param companyCode
	 * @return response			Provide JSON/XML response 
	 */
	@PostMapping(value = "/webAgent/updatePaperFreeBilling", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UpdatePaperFreeBillingResponse updatePaperFreeBilling(@RequestParam("accountNumber") String accountNumber,@RequestParam("flag") String flag,
			@RequestParam("companyCode") String companyCode, @RequestParam("bpNumber")String bpNumber, @RequestParam("source")String source){
		
		//Response response = null;
		UpdatePaperFreeBillingResponse updatePaperFreeBillingResponse = billingBO.updatePaperFreeBilling(accountNumber,flag,companyCode, httpRequest.getSession(true).getId(),bpNumber,source);
		//response = Response.status(200).entity(updatePaperFreeBillingResponse).build();
		return updatePaperFreeBillingResponse;
		
	}
	
}
