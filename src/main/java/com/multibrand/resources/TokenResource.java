package com.multibrand.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.bo.TokenBO;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.request.TokenRequestVO;


@Component
@Path("tokenResource")
public class TokenResource {
	
	@Autowired
	private TokenBO tokenBO;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * @param functionCode
	 * @param strNum
	 * @return Response
	 * */
	@POST
	@Path("getToken")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getToken(@FormParam("functionCode") String functionCode, @FormParam("strNum") String strNum) {

		long startTime = CommonUtil.getStartTime();
		logger.info("::::::::::::::::::::::::::::::::::::::Start TokenResource getToken::::::::::::::::::::::::::::::::::::::::::::::"
				+ startTime);

		

		Response response = null;
		TokenRequestVO tokenRequestVO = tokenBO.createTokenRequest(functionCode , strNum);
		String tokenizedResponse = tokenBO.getTokenResponse(tokenRequestVO);

		response = Response.status(Response.Status.OK).entity(tokenizedResponse).build();
		logger.info(
				"::::::::::::::::::::::::::::::::::::::End TokenResource getToken Service::::::::::::::::::::::::::::::::::::::::::::::"
						+ CommonUtil.getElapsedTime(startTime));
		return response;
	}
	
  
	
	

}
