package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.service.NEISimplySmartService;

@Component
@Path("nei")
public class NEISimplySmartResource extends BaseResource {

	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	boolean thrown = false;

	@Autowired
	public NEISimplySmartService neiSimplySmartService;

	@Context
	public HttpServletRequest httpRequest;

	@Autowired
	ErrorContentHelper errorContentHelper;

	
	@GET
	@Path("health")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getLive() {
		Response response = null;
		response = Response.status(200).entity("PayPal Pay Bill Resouse Health Up and Running").build();
		return response;
	}

	@POST
	@Path("paypalPayment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response paypalPayment(@Valid NEIPaypalPaymentRequest request) {
        
		String sessionId = httpRequest.getSession(true).getId();
		logger.debug("{} START:NEISimplySmartResource:paypalPayment", sessionId);
		Response response = null;
		NEIPaypalPaymentResponse paymentResponse = null;

		try {
			paymentResponse = neiSimplySmartService.paypalPayment(request,sessionId);

		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("").build();
			logger.error(e.fillInStackTrace());
		}
		
		response = Response.status(Response.Status.OK).entity(paymentResponse).build();
		logger.debug("{} END:NEISimplySmartResource:paypalPayment", sessionId);
		return response;

	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

}
