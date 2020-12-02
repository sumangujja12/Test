package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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

import com.multibrand.exception.NRGException;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.service.SimplySmartService;
import javax.ws.rs.core.Response;

@Component
@Path("/ssResource")
public class SimplySmartResource {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Context
	private HttpServletRequest httpRequest;

	@Autowired
	ErrorContentHelper errorContentHelper;

	@Autowired
	public SimplySmartService simplySmartService;

	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bankAccountNumber
	 * @param bankRountingNumber
	 * @param companyCode
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @return
	 */

	@GET
	@Path("/isAlive")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getLive() {
		Response response = null;
		response = Response.status(200).entity("Get Live").build();
		return response;
	}

	@POST
	@Path("/createBPACA")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createBPACAAccount(@FormParam("companyCode") String companyCode, @FormParam("esiId") String esiId) {
		logger.debug("Start SimplySmartService.createBPACAAccount :: START");
		Response response = null;

		try {
			simplySmartService.createBPCADetails(companyCode, esiId, httpRequest.getSession(true).getId());
		} catch (NRGException e) {
			logger.error("Exception occured in getGMDStatementDetails :{}", e);
		}
		response = Response.status(200).entity("Created BPCA Account").build();
		logger.debug("End AutoPayResource.submitBankAutoPay :: END");

		return response;

	}

}
