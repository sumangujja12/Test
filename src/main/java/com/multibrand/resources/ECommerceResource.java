package com.multibrand.resources;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.ECommerceBO;
import com.multibrand.vo.response.GoogleProductSetResponse;

/**
 * 
 * @author HChoudhary
 *
 */
@RestController
public class ECommerceResource extends BaseResource{

	@Autowired
	ECommerceBO ecommerceBO;
	
		
	/**
	 * 
	 * @return response
	 */
	@PostMapping(value="/public/ecommerceResource/googleProductSet", produces = {MediaType.APPLICATION_JSON_VALUE })
	public Response getGoogleProductSet(){Response response = null;
		GoogleProductSetResponse googleProductSetResponse = ecommerceBO.getGoogleProductSet();
		response = Response.status(Response.Status.OK).entity(googleProductSetResponse).build();
		return response;
		
	}

}