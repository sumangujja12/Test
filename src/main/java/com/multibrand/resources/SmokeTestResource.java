package com.multibrand.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/*
 * <h1>Smoke Test API URL</h1>
 * This Resource is only for implementing API end points for Smoke Test 
 * Currently used by IBM UCD
 * @Author bbarman
 * @version 1.0
 * @since   02/02/2018
 */

@Component
@Path("smoketest")
public class SmokeTestResource extends BaseResource{
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");

	@GET
	@Path("verifystatus/{param}")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getMsg(@PathParam("param") String servername) {

		String output = "Application Server up on : " + servername + "\n";
		logger.info(output);

		return Response.status(200).entity(output).build();

	}
	
}
