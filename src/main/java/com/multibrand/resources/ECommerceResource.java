package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.ECommerceBO;
import com.multibrand.vo.response.GoogleProductSetResponse;

/**
 * 
 * @author HChoudhary
 *
 */
@Component
@Path("ecommerceResource")
public class ECommerceResource extends BaseResource{

	@Autowired
	ECommerceBO ecommerceBO;
	
	@Context
	private HttpServletRequest httpRequest;
	
	/**
	 * 
	 * @return response
	 */
	@POST
	@Path("googleProductSet")
	@Produces({MediaType.APPLICATION_JSON})
	public Response googleProductSet(){Response response = null;
		GoogleProductSetResponse googleProductSetResponse = ecommerceBO.googleProductSet();
		response = Response.status(Response.Status.OK).entity(googleProductSetResponse).build();
		return response;
		
	}

}
