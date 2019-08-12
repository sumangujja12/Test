package com.multibrand.resources;

import java.util.logging.Logger;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * <h1>Smoke Test API URL</h1>
 * This Resource is only for implementing API end points for Smoke Test 
 * Currently used by IBM UCD
 * @Author bbarman
 * @version 1.0
 * @since   02/02/2018
 */

@RestController
public class SmokeTestResource extends BaseResource{
	private static Logger logger = Logger.getLogger("NRGREST_LOGGER");

	@GetMapping(value="/smoketest/verifystatus/{param}" ,consumes = {MediaType.TEXT_PLAIN_VALUE }, produces = {MediaType.TEXT_PLAIN_VALUE})
	public Response getMsg(@PathParam("param") String servername) {

		String output = "Application Server up on : " + servername + "\n";
		logger.info(output);

		return Response.status(200).entity(output).build();

	}
	
}
