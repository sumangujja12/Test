package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.bo.RegistrationBO;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.ValidateAccountResponse;


/**
 * 
 * @author ahanda1
 *
 */

@Service
@Path("registration")
public class Registration {
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@Autowired
	RegistrationBO registrationBO;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/** This service is to provide the profile information from CCS system.
	 * 
	 * @author rbansal30
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML customer profile data response
	 */
	@POST
	@Path("validateAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
	@POST
	@Path("createUser")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
			@FormParam("checkDigit")String checkDigit)
	{
		
		logger.debug("Inside createUser in Registration Resource");
		Response response = null;
		logger.info("START-[Registration-createUser]");
		GenericResponse responseVo = registrationBO.createUser(accountNumber,
				lastName, email, firstName, userName, password, companyCode,httpRequest.getSession(true).getId(),languageCode,applicationArea,checkDigit);
		response = Response.status(200).entity(responseVo).build();
		logger.info("END-[Registration-createUser]");
		return response;
		
	}
	
	
}
