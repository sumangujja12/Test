package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.multibrand.bo.RegistrationBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.ValidateAccountForMobileResponse;
import com.multibrand.vo.response.ValidateAccountResponse;


/**
 * 
 * @author ahanda1
 *
 */

@Controller
public class Registration {
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	@Autowired
	RegistrationBO registrationBO;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/** This service is to provide the profile information from CCS system.
	 * 
	 * @author rbansal30
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML customer profile data response
	 */
	@PostMapping(value = "/registration/validateAccount", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response validateAccount(@FormParam("accountNumber") String accountNumber, @FormParam("lastName") String lastName,@FormParam("companyCode")String companyCode){
		
		Response response = null;
		ValidateAccountResponse validateAccountResponse = registrationBO.validateAccount(accountNumber, lastName,companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(validateAccountResponse).build();
		return response;
		
	}
	
	/**
	 * @author smuruga1
	 * This service is used to create the LDAP Login for the users.
	 * @author smuruga1
	 * @param accountNumber
	 * @param lastName
	 * @param email
	 * @param firstName
	 * @param userName
	 * @param password
	 * @param companyCode
	 * @param languageCode
	 * @param applicationArea
	 * @param checkDigit
	 * @return
	 */
	@PostMapping(value = "/registration/createUser", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response createUser(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("lastName") String lastName,
			@FormParam("email") String email,
			@FormParam("firstName") String firstName,
			@FormParam("userName") String userName,
			@FormParam("password") String password,
			@FormParam("companyCode") String companyCode,
			@FormParam("languageCode")String languageCode,
			@FormParam("applicationArea")String applicationArea,
			@FormParam("checkDigit")String checkDigit,
			@FormParam("source")String source)
	{
		
		logger.debug("Inside createUser in Registration Resource");
		Response response = null;
		logger.info("START-[Registration-createUser]");
		GenericResponse responseVo = registrationBO.createUser(accountNumber,
				lastName, email, firstName, userName, password, companyCode,httpRequest.getSession(true).getId(),languageCode,applicationArea,checkDigit,source);
		response = Response.status(200).entity(responseVo).build();
		logger.info("END-[Registration-createUser]");
		return response;
		
	}
	
	
	/**
	 * @author cuppala
	 * This service is used to create the LDAP Login for the users.
	 * @author cuppala
	 * @param accountNumber
	 * @param lastName
	 * @param email
	 * @param firstName
	 * @param userName
	 * @param password
	 * @param companyCode
	 * @param languageCode
	 * @param applicationArea
	 * @param checkDigit
	 * @return
	 */
	@PostMapping(value = "/registration/validateAccountForMobile", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response validateAccountForMobile(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("lastName") String lastName,
			@FormParam("userName") String userName,
			@FormParam("companyCode") String companyCode,
			 @FormParam("brandName")String brandName)
	{
		
		logger.info("START-[Registration-validateAccountForMobile]");
		Response response = null;
		ValidateAccountForMobileResponse validateAccountForMobileResponse = null;
		
		String sessionId = httpRequest.getSession(true).getId();
		validateAccountForMobileResponse= registrationBO.validateAccountForMobile(accountNumber,
				lastName, userName, companyCode,brandName,sessionId);
		response = Response.status(200).entity(validateAccountForMobileResponse).build();
		logger.info("END-[Registration-validateAccountForMobile]");
		return response;
		
	}
	
	
}
