package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.impl.TestDataDAOImpl;
import com.multibrand.vo.response.TestDataResponse;

@Component
@Path("testdata")
public class TestDataResource {

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Context
	private HttpServletRequest httpRequest;

	@Autowired
	TestDataDAOImpl testDataDAO;

	@GET
	@Path("getCollection/{collectionName}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCollectionData(@PathParam("collectionName") String collectionName) {
		Response response = null;
		TestDataResponse tdResponse = testDataDAO.getTestCollection(collectionName);
		response = Response.status(200).entity(tdResponse).build();

		return response;
	}
}
