package com.multibrand.exception.handlers;



import com.multibrand.exception.OAMException;

//@Component
//@Provider
public class OAMExceptionMapper {//implements ExceptionMapper<OAMException>{

	
//	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER"); 
//	
//	@Context
//	private HttpHeaders headers;
//	
//	@Override
//	public Response toResponse(final OAMException e) {
//	
//		
//		ResponseBuilder rb = Response.status(e.getErrorCode()).entity(e.getResponse());
//		//"Jersey mapped runtime exception: " + e.getClass().getName()
//	    List<MediaType> accepts = headers.getAcceptableMediaTypes();
//	    if (accepts!=null && accepts.size() > 0) {
//	        //just pick the first one
//	        MediaType m = accepts.get(0);
//	        
//	        if(logger.isDebugEnabled()){
//		        logger.debug("header.getAcceptableMediaTypes() ::: "+ headers.getAcceptableMediaTypes());
//		        logger.debug("header.getAcceptableMediaTypes().get(0) ::: "+ headers.getAcceptableMediaTypes().get(0));
//	        }
//	        rb = rb.type(m);
//	    }
//	    else {
//	        //if not specified, use the entity type
//	    	if(logger.isDebugEnabled())
//	    		logger.debug("header.getMediaType() ::: "+ headers.getMediaType());
//	        rb = rb.type(headers.getMediaType()); // set the response type to the entity type.
//	    }
//	    return rb.build();
//	}
}
