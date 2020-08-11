package com.multibrand.resources;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.multibrand.bo.KBAMatrixUpdateBO;
import com.multibrand.dto.request.KBAMatrixUpdateRequest;
import com.multibrand.vo.response.KBAMatrixUpdateResponse;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * This Resource is to handle all the CRMO data load Related API calls.
 * 
 * @author rpendur1
 */
@Component
@Path("kbaResource")
public class KBAMatrixUpdateResources extends BaseResource {

	private static Logger logger = LogManager.getLogger(KBAMatrixUpdateResources.class);

	@Autowired
	private KBAMatrixUpdateBO kbaMatrixUpdateBO;
	
	@POST
	@Path(API_KBA_MATRIX_UPDATE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response kbaMatriUpdate(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) throws IOException {
		
		logger.info(" START ******* kbaMatriUpdate API**********");
		Gson gson = new Gson();
		
		String result = IOUtils.toString(uploadedInputStream, "UTF-8");
		
		KBAMatrixUpdateRequest kbaMatrixUpdateRequest  = null;
		if( result != null){		
			kbaMatrixUpdateRequest = gson.fromJson(result, KBAMatrixUpdateRequest.class);
			
		}
		
		KBAMatrixUpdateResponse kbaMatrixUpdateResponse = kbaMatrixUpdateBO.kbaMatriUpdate(kbaMatrixUpdateRequest); 
		
		Response response = Response.status(Response.Status.OK).entity(kbaMatrixUpdateResponse).build();
		
		logger.info("END of the kbaMatriUpdate API*************");
		return response;
	}

}
