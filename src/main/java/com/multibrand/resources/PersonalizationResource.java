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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;
import com.multibrand.service.PersonalizationService;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.TCSPersonalizedFlagsResponse;

/**
 * 
 * @author rpendur1
 * This resource is used to handle all Community Solar TCS read related API calls.
 */
@Component
@Path("/personalizationResource")
public class PersonalizationResource implements Constants {
	
	@Context 
	private HttpServletRequest httpRequest;

	@Autowired
	private PersonalizationService personalizationService;
	
	/**
	 * @param bp customer business partner id
	 * @param ca customer contract account
	 * @return
	 */
	@POST
	@Path("getPersonalizedFlags")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
	public Response getPersonalizedFlags(@FormParam("bp") String bp,@FormParam("ca") String ca){
		
		Response response = null;

		TCSPersonalizedFlagsResponse tcsPersonalizedFlagsResponse = new TCSPersonalizedFlagsResponse();
		
		TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO = personalizationService.getPersonalizedFlags(bp,ca);
		
		tcsPersonalizedFlagsResponse.setTcsPersonalizedFlagsDTO(tcsPersonalizedFlagsDTO);

		response = Response.status(200).entity(tcsPersonalizedFlagsResponse).build();
				
		return response;
	}
	
}
