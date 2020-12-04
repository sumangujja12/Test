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

import com.multibrand.dto.request.NeiBPCARequest;
import com.multibrand.exception.NRGException;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.service.NEISimplySmartService;
import com.nrg.cxfstubs.nei.bpca.ZEIsuNeiCreateBpCaResponse;


@Component
@Path("nei")
public class NEISimplySmartResource {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Context
	private HttpServletRequest httpRequest;

	@Autowired
	ErrorContentHelper errorContentHelper;

	@Autowired
	public NEISimplySmartService nEISimplySmartService;

	
	@GET
	@Path("/health")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getLive() {
		Response response = null;
		response = Response.status(200).entity("Service is up and running").build();
		return response;
	}

	@POST
	@Path("/createBPCA")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNEIBPCA(@Valid NeiBPCARequest request) {
		logger.debug("Start NEISimplySmartService.createNEIBPCA :: START");
		Response response = null;
		
		ZEIsuNeiCreateBpCaResponse ccsResponse = null;
		try {
			ccsResponse = nEISimplySmartService.createNEIBPCA(request, httpRequest.getSession(true).getId());
		} catch (NRGException e) {
			logger.error("Exception occured in NEISimplySmartResource:createNEIBPCA :{}", e);
		}
		response = Response.status(Response.Status.OK).entity(ccsResponse).build();
		logger.debug("End NEISimplySmartService.createNEIBPCA :: END");

		return response;

	}

}
