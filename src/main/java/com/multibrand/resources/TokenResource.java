package com.multibrand.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.TokenBO;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.request.TokenRequestVO;


@RestController
public class TokenResource {
	
	@Autowired
	private TokenBO tokenBO;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * @param functionCode
	 * @param strNum
	 * @return Response
	 * */
	@PostMapping(value = "/tokenResource/getToken", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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
