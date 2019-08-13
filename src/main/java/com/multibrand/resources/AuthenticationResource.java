package com.multibrand.resources;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.AuthenticationBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.AuthenticationResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.LoginFailureResponse;
import com.multibrand.vo.response.LoginResponse;

/***
 * 
 *  @author Kdeshmu1
 *  This class is the Resource Class for all the Authentication APIs to be exposed as REST Service. 
 *
 */
@RestController
public class AuthenticationResource implements Constants  {
	
	
	
	@Autowired 
	AuthenticationBO authenticationBO;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	
	
	
	/***
	 * loginSuccessCall
	 *  @author Kdeshmu1
	 *  @description  call after login success
	 */
	@PostMapping(value="/authorization/loginSuccessCall", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse loginSuccessCall(String userId,@RequestHeader MultiValueMap<String, String> hh, HttpServletRequest request){
		logger.debug("Inside loginSuccessCall of AuthenticationResource");
		//Response response = null;
		LoginResponse loginSuccessCallResponse = authenticationBO.loginSuccessCall(userId,hh, request);
		//response = Response.status(200).entity(loginSuccessCallResponse).build();
		logger.debug("Exiting loginSuccessCall of AuthenticationResource");
		return loginSuccessCallResponse;
	}
	
	/***
	 * loginFailureCall
	 *  @author Kdeshmu1
	 *  @description  call after login failure
	 */
	@PostMapping(value="/authorization/loginFailureCall", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse loginFailureCall(String userId,@RequestHeader MultiValueMap<String, String> hh, HttpServletRequest request){
		logger.debug("Inside loginFailureCall of AuthenticationResource");
		//Response response = null;
		LoginFailureResponse loginFailureCallResponse = authenticationBO.loginFailureCall(userId,hh, request);
		//response = Response.status(200).entity(loginFailureCallResponse).build();
		logger.debug("Exiting loginFailureCall of AuthenticationResource");
		return loginFailureCallResponse;
	}
	
	@PostMapping(value="/authorization/refreshtoken", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse refreshToken() {
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
		//Response response = Response.status(200).entity(authResponse).build();
		return authResponse;

	}
	

	
}
