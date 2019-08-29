package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.BillingBO;
import com.multibrand.bo.WebAgentBO;
import com.multibrand.vo.response.UpdateContactInfoResponse;
import com.multibrand.vo.response.billingResponse.UpdatePaperFreeBillingResponse;

@Component
@Path("webAgent")
public class WebAgentResource {

	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@Autowired
	WebAgentBO webAgentBO;
	
	/** Object of BillingBO class. */
	@Autowired
	private BillingBO billingBO;
	
	@POST
	@Path("updateEmailID")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateContactInfo(@FormParam("userName")String userName,@FormParam("email")String email){
		
		Response response = null;
		UpdateContactInfoResponse updateContactInfoResponse=webAgentBO.updateEmail(userName, email, "0271", httpRequest.getSession().getId());
		response = Response.status(200).entity(updateContactInfoResponse).build();
		return response;
		
	} 
	
	/** This service is to update the bill delivery option
	 * 
	 * @param accountNumber		Customer Account Number
	 * @param flag			
	 * @param companyCode
	 * @return response			Provide JSON/XML response 
	 */
	@POST
	@Path("updatePaperFreeBilling")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updatePaperFreeBilling(@FormParam("accountNumber") String accountNumber,@FormParam("flag") String flag,
			@FormParam("companyCode") String companyCode, @FormParam("bpNumber")String bpNumber, @FormParam("source")String source){
		
		Response response = null;
		UpdatePaperFreeBillingResponse updatePaperFreeBillingResponse = billingBO.updatePaperFreeBilling(accountNumber,flag,companyCode, httpRequest.getSession(true).getId(),bpNumber,source);
		response = Response.status(200).entity(updatePaperFreeBillingResponse).build();
		return response;
		
	}
	
}
