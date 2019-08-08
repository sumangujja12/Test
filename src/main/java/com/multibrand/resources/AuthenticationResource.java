package com.multibrand.resources;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.AuthenticationBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.AuthenticationResponse;
import com.multibrand.vo.response.LoginFailureResponse;
import com.multibrand.vo.response.LoginResponse;







/***
 * 
 *  @author Kdeshmu1
 *  This class is the Resource Class for all the Authentication APIs to be exposed as REST Service. 
 *
 */
@RestController(value="authorization")
public class AuthenticationResource implements Constants  {
	
	
	
	
	@Autowired 
	AuthenticationBO authenticationBO;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	@Context 
	private HttpServletRequest httpRequest;
	
	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	
	
	/***
	 * loginSuccessCall
	 *  @author Kdeshmu1
	 *  @description  call after login success
	 */
	@PostMapping(path="loginSuccessCall", consumes =  MediaType.APPLICATION_FORM_URLENCODED, produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response loginSuccessCall(@FormParam("userId")String userId,@Context HttpHeaders hh, @Context HttpServletRequest request){
		logger.debug("Inside loginSuccessCall of AuthenticationResource");
		Response response = null;
		
		LoginResponse loginSuccessCallResponse = authenticationBO.loginSuccessCall(userId,hh, request);
		
		
		response = Response.status(200).entity(loginSuccessCallResponse).build();
		logger.debug("Exiting loginSuccessCall of AuthenticationResource");
		return response;
	}
	
	/***
	 * loginFailureCall
	 *  @author Kdeshmu1
	 *  @description  call after login failure
	 */
	@PostMapping(path="loginFailureCall", consumes =  MediaType.APPLICATION_FORM_URLENCODED, produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response loginFailureCall(@FormParam("userId") String userId,@Context HttpHeaders hh, @Context HttpServletRequest request){
		logger.debug("Inside loginFailureCall of AuthenticationResource");
		Response response = null;
		LoginFailureResponse loginFailureCallResponse = authenticationBO.loginFailureCall(userId,hh, request);
		
		
		response = Response.status(200).entity(loginFailureCallResponse).build();
		logger.debug("Exiting loginFailureCall of AuthenticationResource");
		return response;
	}
	
	@PostMapping(path="refreshtoken", consumes =  MediaType.APPLICATION_FORM_URLENCODED, produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response refreshToken() {
		AuthenticationResponse authResponse = new AuthenticationResponse();
		try {
			logger.debug("Refreshing authentication token... ");
			Calendar now = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss SSS");
			String refreshVal = CommonUtil.get(Constants.REFRESH_TOKEN_LENGTH);
			authResponse.setRefreshVal(refreshVal);
			
			
			logger.debug("User Session Extended on [" + sdf.format(now.getTime()) + "]");
			
		} catch (Exception e) {
			logger.debug("Error getting while refreshing authentication token... " +e);
			authResponse.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			authResponse.setErrorDescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
			
		}
		Response response = Response.status(200).entity(authResponse).build();
		return response;

	}
	

	
}
