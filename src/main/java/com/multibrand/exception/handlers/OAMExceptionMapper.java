package com.multibrand.exception.handlers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.exception.OAMException;

@Component
@Provider
public class OAMExceptionMapper implements ExceptionMapper<OAMException>{

	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER"); 
	
	@Context
	private HttpHeaders headers;
	
	@Override
	public Response toResponse(final OAMException e) {
	
		
		ResponseBuilder rb = Response.status(e.getErrorCode()).entity(e.getResponse());
		//"Jersey mapped runtime exception: " + e.getClass().getName()
	    List<MediaType> accepts = headers.getAcceptableMediaTypes();
	    if (accepts!=null && accepts.size() > 0) {
	        //just pick the first one
	        MediaType m = accepts.get(0);
	        
	        if(logger.isDebugEnabled()){
		        logger.debug("header.getAcceptableMediaTypes() ::: "+ headers.getAcceptableMediaTypes());
		        logger.debug("header.getAcceptableMediaTypes().get(0) ::: "+ headers.getAcceptableMediaTypes().get(0));
	        }
	        rb = rb.type(m);
	    }
	    else {
	        //if not specified, use the entity type
	    	if(logger.isDebugEnabled())
	    		logger.debug("header.getMediaType() ::: "+ headers.getMediaType());
	        rb = rb.type(headers.getMediaType()); // set the response type to the entity type.
	    }
	    return rb.build();
	}
}
