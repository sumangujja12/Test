package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import com.multibrand.bo.ValidationBO;
import com.multibrand.vo.request.ValidateThirdPartyReceipt;
import com.multibrand.vo.response.ValidateThirdPartyReceiptResponse;

@Component
@Path("validateResource")

public class ValidateThirdPartyResource {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	private ValidationBO validationBO;

	@Context
	private HttpServletRequest httpRequest;

	@POST
	@Path("/protected/zirtuePayment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response validateThirdPartyReceipt(ValidateThirdPartyReceipt request) {
		Response response = null;
		ValidateThirdPartyReceiptResponse validateThirdPartyReceiptResponse = validationBO.validateThirdPartyReceipt(request);

		response = Response.status(200).entity(validateThirdPartyReceiptResponse).build();
		return response;

	}

}
