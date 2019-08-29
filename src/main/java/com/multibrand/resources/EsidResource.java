package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
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

import com.multibrand.bo.OEBO;
import com.multibrand.dto.request.EsidDetailsRequest;
import com.multibrand.dto.response.EsidDetailsResponse;

/**
 * The resource will offer ESID service functionalities.
 * 
 * @author NRG Energy
 */
@Component
@Path("esidResource")
public class EsidResource extends BaseResource {

	@SuppressWarnings("unused")
	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	private OEBO oeBo;
	
	@Context
	private HttpServletRequest httpRequest;
	
	/**
	 * Gives ESID details.
	 * 
	 * @param request
	 *            Request message of type {@link EsidDetailsRequest} .
	 * @return JSON response message of type {@link EsidDetailsResponse}
	 */
	@POST
	@Path("getEsidDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEsidDetails(@Valid EsidDetailsRequest request) {
		EsidDetailsResponse serviceResponse = oeBo.getEsidDetails(request,httpRequest.getSession(true).getId());
		Response response = Response.status(Response.Status.OK)
				.entity(serviceResponse).build();
		return response;
	}
}
