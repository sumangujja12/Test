package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.OEBO;
import com.multibrand.dto.request.EsidDetailsRequest;
import com.multibrand.dto.response.EsidDetailsResponse;

/**
 * The resource will offer ESID service functionalities.
 * 
 * @author NRG Energy
 */
@RestController
public class EsidResource extends BaseResource {

	@SuppressWarnings("unused")
	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	private OEBO oeBo;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	/**
	 * Gives ESID details.
	 * 
	 * @param request
	 *            Request message of type {@link EsidDetailsRequest} .
	 * @return JSON response message of type {@link EsidDetailsResponse}
	 */
	@PostMapping(value = "/esidResource/getEsidDetails", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public EsidDetailsResponse getEsidDetails(@Valid EsidDetailsRequest request) {
		EsidDetailsResponse serviceResponse = oeBo.getEsidDetails(request,httpRequest.getSession(true).getId());
		/*
		 * Response response = Response.status(Response.Status.OK)
		 * .entity(serviceResponse).build();
		 */
		return serviceResponse;
	}
}
