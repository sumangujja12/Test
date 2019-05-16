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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.ProfileBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.helper.ProfileHelper;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.AcctValidationResponse;
import com.multibrand.vo.response.ChangeUsernameResponse;
import com.multibrand.vo.response.CirroStructureResponse;
import com.multibrand.vo.response.EnvironmentImpactsResponse;
import com.multibrand.vo.response.ForgotPasswordResponse;
import com.multibrand.vo.response.ForgotUserNameResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.PasswordValidityResponse;
import com.multibrand.vo.response.SecondaryNameResponse;
import com.multibrand.vo.response.SendMailForNewServiceAddressAddResponse;
import com.multibrand.vo.response.SendMailForPasswordChangeResponse;
import com.multibrand.vo.response.SmartMeterCheckResponse;
import com.multibrand.vo.response.UpdateBillingAddressResponse;
import com.multibrand.vo.response.UpdateContactInfoResponse;
import com.multibrand.vo.response.UpdatePasswordResponse;
import com.multibrand.vo.response.UserIdResponse;
import com.multibrand.vo.response.UserInfoResponse;
import com.multibrand.vo.response.ValidatePasswordLinkResponse;
import com.multibrand.vo.response.WsEnrollmentResponse;
import com.multibrand.vo.response.WsServiceResponse;
import com.multibrand.vo.response.WseEligiblityStatusResponse;
import com.multibrand.vo.response.profileResponse.GetBPInfoResponse;
import com.multibrand.vo.response.profileResponse.ProductUpdateResponse;
import com.multibrand.vo.response.profileResponse.ProfileCheckResponse;
import com.multibrand.vo.response.profileResponse.UpdateLanguageResponse;



/** This Resource is to handle all the Profile Related API calls.
 * 
 * @author rbansal30	
 */
@Component
@Path("profile")
public class ProfileResource {
	
	/** Object of ProfileBO class. */
	@Autowired
	private ProfileBO profileBO;
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@Autowired
	private ProfileHelper profileHelper;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	Logger logger =LogManager.getLogger("NRGREST_LOGGER");
	

	/** This service is to validate user info and then get the User Name to send it over to the Customer via Email.
	 * @author cuppala
	 * @param accountNumber		Customer Account Identification no
	 * @param companyCode		Company code
	 * @param zip				Billing Zip Code
	 * @param languageCode		User preferred Language Code
	 * @param brandName			Company Brand name
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("forgotUserName")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response forgotUserName(@FormParam("accountNumber") String accountNumber,@FormParam("companyCode") String companyCode,@FormParam("zip") String zip,@FormParam("languageCode") String languageCode,@FormParam("brandName") String brandName){
		
		logger.info("accountNumber :"+accountNumber+"companyCode :"+companyCode+"zip :"+zip);
		
		Response response = null;
		String sessionId = httpRequest.getSession(true).getId();
		ForgotUserNameResponse forgotUserNameResponse  = profileBO.forgotUserName(accountNumber,companyCode,zip,languageCode,sessionId,brandName);
		
		
		response = Response.status(200).entity(forgotUserNameResponse).build();
		return response;
		
	}
	
	/** This service is to validate user info and then generate new password reset link to send it over to the Customer via Email.
	 * @author cuppala
	 * @param accountNumber		Customer Account Identification no
	 * @param companyCode		Company code
	 * @param zip				Billing Zip Code
	 * @param languageCode		User preferred Language Code
	 * @param brandName			Company Brand name
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("forgotPassword")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response forgotPassword(@FormParam("accountNumber") String accountNumber,@FormParam("companyCode") String companyCode,@FormParam("brandName") String brandName,@FormParam("zip") String zip,@FormParam("languageCode") String languageCode){
		
		Response response = null;
		String sessionId = httpRequest.getSession(true).getId();
		ForgotPasswordResponse forgotPasswordResponse = profileBO.forgotPassword(accountNumber,companyCode,brandName,languageCode,zip,sessionId);
		
		
		response = Response.status(200).entity(forgotPasswordResponse).build();
		return response;
		
	}
	
	/** This service is to validate Customer received Email i.e valid or expired.
	 * @author cuppala
	 * @param transactionId		Transaction Identification number received in email
	 * @param companyCode		Company code
	 * @param brandName			Company Brand name
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("validatePasswordlink")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response validateForgotPasswordLink(@FormParam("transactionId") String transactionId,@FormParam("companyCode") String companyCode,@FormParam("brandName") String brandName){
		Response response = null;
		String sessionId = httpRequest.getSession(true).getId();
		ValidatePasswordLinkResponse validatePasswordLinkResp = profileBO.validateForgotPasswordLink(transactionId,companyCode,brandName,sessionId);
		response = Response.status(200).entity(validatePasswordLinkResp).build();
		return response;
		
	}

	
	
	/** This service is to get the username or account number from LDAP.
	 * @author kdeshmu1
	 * @param userID		Customer User Identification no
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("getUserOrAcctNumber")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getUserOrAcctNumber(@FormParam("userId") String userID,@FormParam("companyCode") String companyCode){
		
		Response response = null;
//		companyCode="0391";
		UserInfoResponse userInfoResponse = profileBO.getUserOrAcctNumber(userID,companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(userInfoResponse).build();
		return response;
		
	}
	
	/** This service is to update the password in ldap
	 * @author kdeshmu1
	 * @param userName		Customer UserName
	 * @param newPassword		Customer  New Password
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("updatePassword")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updatePassword(@FormParam("userName") String userName,@FormParam("newPassword") String newPassword,
			@FormParam("companyCode") String companyCode){
		
		Response response = null;
		
		UpdatePasswordResponse updatePasswordResponse = profileBO.updatePassword(userName,newPassword,companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(updatePasswordResponse).build();
		return response;
		
	}
	
	/** This service is to update the password in ldap
	 * @author kdeshmu1
	 * @param userName		Customer UserName
	 * @param newPassword		Customer  New Password
	 * @return response			Provide JSON/XML customer  data response
	 */
	@POST
	@Path("updatePasswordBehindLogin")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updatePasswordBehindLogin(@FormParam("userName") String userName,@FormParam("newPassword") String newPassword,
			@FormParam("oldPassword") String oldPassword,@FormParam("companyCode") String companyCode){
		
		Response response = null;
		UpdatePasswordResponse updatePasswordResponse = profileBO.updatePasswordBehindLogin(userName,newPassword,oldPassword,companyCode, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(updatePasswordResponse).build();
		return response;
		
	}
	/**
	 * @author deshmu1
	 * @param accountNumber
	 * @param streetName
	 * @param streetNum
	 * @param city
	 * @param state
	 * @param aptNum
	 * @param country
	 * @param zip
	 * @param companyCode
	 * @param bpNumber
	 * @param poBox
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("updateBillingAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateBillingAddress(@FormParam("accountNumber") String accountNumber,@FormParam("streetName") String streetName,
			@FormParam("streetNum") String streetNum,@FormParam("city") String city,@FormParam("state") String state
			,@FormParam("aptNum") String aptNum,@FormParam("country") String country,@FormParam("zip") String zip,
			@FormParam("companyCode") String companyCode,@FormParam("bpNumber") String bpNumber,@FormParam("poBox") String poBox, @FormParam("brandName")String brandName){
		
		Response response = null;
		
		UpdateBillingAddressResponse updateBillingAddressResponse = profileBO.updateBillingAddress(accountNumber,streetName,streetNum,city,state,
				aptNum,country,zip,companyCode,bpNumber,poBox,httpRequest.getSession(true).getId(), brandName);
		response = Response.status(200).entity(updateBillingAddressResponse).build();
		
		
		
		return response;
		
	}
	
	/**
	 * This Method is to change the username
	 * @author Kdeshmu1
	 * @param userName
	 * * @param oldUserName
	 * * @param companyCode
	 * @return
	 */
	@POST
	@Path("changeUsername")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response changeUsername(@FormParam("userName") String userName,
			@FormParam("oldUserName") String oldUserName,@FormParam("companyCode") String companyCode){
		
		Response response = null;
		
		//companyCode="0391";
		ChangeUsernameResponse changeUsernameResponse = profileBO.changeUsername(userName,oldUserName,companyCode,httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(changeUsernameResponse).build();
		return response;
		
	}
	/**
	 * This Method is to update contact information
	 * @author Kdeshmu1
	 * @param accountNumber
	 * @param homePhone
	 * @param email
	 * @param bpNumber
	 * @param uniqueID
	 * @param userName
	 * @param companyCode
	 * @param workPhone
	 * @param cellPhone
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("updateContactInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateContactInfo(@FormParam("accountNumber") String accountNumber,@FormParam("homePhone") String homePhone ,
			@FormParam("email") String email,@FormParam("bpNumber") String bpNumber,@FormParam("uniqueID") String uniqueID ,
			@FormParam("userName") String userName ,@FormParam("companyCode") String companyCode,@FormParam("workPhone") String workPhone,
			@FormParam("cellPhone") String cellPhone, @FormParam("brandName")String brandName,@FormParam("marketingPref")String marketingPref, 
			@FormParam("existingEmail")String existingEmail, @FormParam("billingOptionChangeFlag") String billingOptionChangeFlag){
		
		Response response = null;
		//companyCode="0391";
		logger.info(" START ******* Input for the updateContactInfo API**********");
		UpdateContactInfoResponse updateContactInfoResponse = profileBO.updateContactInfo(accountNumber,homePhone,email,bpNumber,
				uniqueID,userName,companyCode,workPhone,cellPhone,httpRequest.getSession(true).getId(), brandName,marketingPref, existingEmail, billingOptionChangeFlag);
		response = Response.status(200).entity(updateContactInfoResponse).build();
			
		logger.info(" END ******* Input for the updateContactInfo API**********");
		return response;
		
	}
	

	@POST
	@Path("getContractInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getContractInfo(@FormParam("accountNumber") String accountNumber, 
			                        @FormParam("bpNumber") String bpNumber,
			                        @FormParam("languageCode") String languageCode, 
			                        @FormParam("companyCode") String companyCode,
			                        @FormParam("brandName") String brandName,
			                        @FormParam("contractId")String contractId,
			                        @FormParam("esid")String esid){
		
		Response response = null;
		logger.info("Start-[ProfileResource-getContractInfo]");
		GetContractInfoResponse getContractInfoResponse = profileBO.getContractInfo(accountNumber,bpNumber,esid,contractId,languageCode,companyCode,brandName,httpRequest.getSession(true).getId());
		

		response = Response.status(200).entity(getContractInfoResponse).build();
		logger.info("Start-[ProfileResource-getContractInfo]");
		return response;
		
	}

	@POST
	@Path("smartMeterCheck")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response smartMeterCheck(@FormParam("accountNumber") String accountNumber,@FormParam("esid") String esid ,
			@FormParam("companyCode") String companyCode){
		
		Response response = null;
		companyCode="0391";
		logger.info("Start-[ProfileResource-smartMeterCheck]");
		logger.info("::::::::::::::::::::::::::::::11");
		SmartMeterCheckResponse smeterCheckRep = profileBO.getSmartMeterCheck(
				accountNumber, esid, companyCode,httpRequest.getSession(true).getId());
		logger.info("::::::::::::::::::::::::::::::::::::12");
		response = Response.status(200).entity(smeterCheckRep).build();
		logger.info("END-[ProfileResource-smartMeterCheck]");
		return response;
		
	}

	/**
	 * This Method is to update products in CCS
	 * @param accountNumber
	 * @param action
	 * @param objectId
	 * @param extUi
	 * @param enrollType
	 * @param requestDate
	 * @param manuPartNo
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("productUpdate")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response productUpdate(@FormParam("accountNumber") String accountNumber,@FormParam("action") String action ,
			@FormParam("objectId") String objectId,@FormParam("extUi") String extUi,@FormParam("enrollType") String enrollType ,
			@FormParam("requestDate") String requestDate ,@FormParam("manuPartNo") String manuPartNo,@FormParam("companyCode") String companyCode){
		
		Response response = null;
		ProductUpdateResponse productResponse = new ProductUpdateResponse();
		productResponse = profileBO.productUpdate(accountNumber, action , objectId, extUi, enrollType , requestDate , manuPartNo, companyCode,httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(productResponse).build();
		return response;
		
	}
	/**
	 * 
	 */
	@POST
	@Path("environmentalImpacts")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response environmentalImpacts(@FormParam("accountNumber")String accountNumber,@FormParam("companyCode") String companyCode){
		
		Response response = null;
		EnvironmentImpactsResponse environmentImpactsResponse = new EnvironmentImpactsResponse();
		environmentImpactsResponse = profileBO.environmentalImpacts(accountNumber,companyCode, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(environmentImpactsResponse).build();
		return response;
		
	}
	
	
	/**
	 * @author mshukla1
	 * SecondaryName CURD call
	 */
	@POST
	@Path("secondaryNameUpdate")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response secondaryNameUpdate(@FormParam("accountNumber")String accountNumber,
										@FormParam("bpid")String bpid,
										@FormParam("action")String action,
										@FormParam("bpid2")String bpid2,
										@FormParam("firstName")String firstName,
										@FormParam("lastName")String lastName,
										@FormParam("middleName")String middleName,
										@FormParam("validFrom")String validFrom,
										@FormParam("validUntil")String validUntil,
										@FormParam("companyCode")String companyCode){
		
		Response response = null;
		logger.info("Start-[ProfileResource-secondaryNameUpdate]");
		SecondaryNameResponse secNameResponse =new SecondaryNameResponse();
		secNameResponse = profileBO.secondaryNameUpdate(accountNumber,bpid,action,bpid2,firstName,lastName,middleName,validFrom,validUntil,companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(secNameResponse).build();
		logger.info("End-[ProfileResource-secondaryNameUpdate]");
		return response;
	}
	
	@POST
	@Path("wseDeEnrollService")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response wseDeEnrollService(@FormParam("accountNumber")String accountNumber,
											 @FormParam("contractNumber")String contractNumber,
											  @FormParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEnrollDeEnrollService]");	
		Response response = null;
		WsServiceResponse wsResponse = profileBO.wsDeEnrollService(
				accountNumber, contractNumber,  companyCode,
				httpRequest.getSession(true).getId());
		response = Response.status(200).entity(wsResponse).build();
		logger.info("END-[ProfileResource-wseEnrollDeEnrollService]");	
		return response;
			
			
  }
	
	
	@POST
	@Path("wseEnrollService")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response wseEnrollService(@FormParam("contractNumber")String contractNumber,
									  @FormParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEnrollDeEnrollService]");	
		Response response = null;
		WsEnrollmentResponse wsResponse = profileBO.wsEnrollService(contractNumber, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(wsResponse).build();
		logger.info("END-[ProfileResource-wseEnrollDeEnrollService]");	
		return response;
			
			
  }
	
	@POST
	@Path("WseEligiblityStatusCall")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response wseEligibilityStatus(@FormParam("contractNumber")String contractNumber,
										 @FormParam("esid") String esid,
									     @FormParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEligibilityStatus]");	
		Response response = null;
		WseEligiblityStatusResponse wsResponse = profileBO
				.wseEligibilityStatus(contractNumber, esid, companyCode,
						httpRequest.getSession(true).getId());
		response = Response.status(200).entity(wsResponse).build();
		logger.info("END-[ProfileResource-wseEligibilityStatus]");	
		return response;
			
			
  }
	
	/**
	 * @author smuruga1
	 * @param bpId
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("getCirroStructure")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getCirroStructureCall(@FormParam("bpId")String bpId,
									  @FormParam("companyCode")String companyCode,
									  @FormParam("brandName")String brandName,
									  @FormParam("noOfDayBack")String noOfDayBack) {
		logger.info("Start-[ProfileResource-getCirroStructureCall]");	
		Response response = null;
		CirroStructureResponse wsResponse = profileBO.getCirroStructureCall(bpId, companyCode, brandName,noOfDayBack,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(wsResponse).build();
		logger.info("END-[ProfileResource-getCirroStructureCall]");	
		return response;	
  }
	
	/**
	 * 
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("validateAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response validateAccount(@FormParam("accountNumber")String caNumber,
									  @FormParam("companyCode")String companyCode,
									  @FormParam("brandName")String brandName) {
		logger.info("Start-[ProfileResource-validateAccount]");	
		Response response = null;
		AcctValidationResponse wsResponse = profileBO.validateAccount(caNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(wsResponse).build();
		logger.info("END-[ProfileResource-validateAccount]");	
		return response;	
  }
  

	@POST
	@Path("updateLanguage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateLanguage(@FormParam("businessPartnerId") String bpid, @FormParam("contractAccountNumber") String ca, @FormParam("languageCode")String languageCode, @FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName){
		Response response = null;
		UpdateLanguageResponse updateLanguageResponse = profileBO
				.updateLanguage(bpid, ca, languageCode, companyCode, brandName,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(updateLanguageResponse).build();
				
		return response;
		
	}
	
	
	/**
	 * 
	 * @param caNumber
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("getSVTData")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getSVTData(@FormParam("bpNumber")String bpNumber,
									  @FormParam("companyCode")String companyCode,
									  @FormParam("brandName")String brandName) {
		logger.info("Start-[ProfileResource-getSVTData]");	
		Response response = null;
		GetBPInfoResponse svtResponse = profileBO.getSVTData(bpNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(svtResponse).build();
		logger.info("END-[ProfileResource-getSVTData]");	
		return response;	
  }
	
	/**
	 * 
	 * @param companyCode
	 * @param brandName
	 * @param contractAccountNumber
	 * @param email
	 * @param checkDigit
	 * @return
	 */	
	@POST
	@Path("profileCheck")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response profileCheck (
			  @FormParam("companyCode")String companyCode,
			  @FormParam("brandName")String brandName,
			  @FormParam("contractAccountNumber") String contractAccountNumber,
			  @FormParam("email") String email,
			  @FormParam("checkDigit") String checkDigit ) {
		logger.info("Start-[ProfileResource-profileCheck]");	
		Response response = null;
		ProfileCheckResponse profileCheckResponse = profileBO.profileCheck(companyCode,brandName,contractAccountNumber,email,checkDigit,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(profileCheckResponse).build();
		logger.info("END-[ProfileResource-profileCheck]");	
		return response;
	
	
}
	/**
	 * Operation for fetching userId.
	 * @param userIdRequest UserIdRequest
	 * @return
	 */
	@POST
	@Path("getUserId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getUserId(UserIdRequest userIdRequest){
				
		Response response=null;
		logger.info("Start-[ProfileResource-getUserId]");
		UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		response=Response.status(200).entity(userIdResponse).build();
		logger.info("End-[ProfileResource-getUserId]");
		return response;
	}
	
	
	@POST
	@Path("sendMailForPasswordChange")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response sendMailForPasswordChange(
			 @FormParam("companyCode")String companyCode,
			  @FormParam("brandName")String brandName,
			  @FormParam("contractAccountNumber") String contractAccountNumber,
			  @FormParam("email") String email			
			){
				
		Response response=null;
		logger.info("Start-[ProfileResource-sendMailForPasswordChange]");
		//UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		
		SendMailForPasswordChangeResponse resp = profileBO.sendMailForPasswordChange(companyCode, brandName, email, contractAccountNumber, httpRequest.getSession(true).getId());
		
		response=Response.status(200).entity(resp).build();
		logger.info("End-[ProfileResource-sendMailForPasswordChange]");
		return response;
	}
	
	@POST
	@Path("sendMailForNewServiceAddressAddition")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response sendMailForNewServiceAddressAddition(
			 @FormParam("companyCode")String companyCode,
			  @FormParam("brandName")String brandName,
			  @FormParam("contractAccountNumber") String contractAccountNumber,
			  @FormParam("email") String email			
			){
				
		Response response=null;
		logger.info("Start-[ProfileResource-sendMailForNewServiceAddressAddition]");
		//UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		
		SendMailForNewServiceAddressAddResponse resp = profileBO.sendMailForNewServiceAddressAddition(companyCode, brandName, email, contractAccountNumber, httpRequest.getSession(true).getId());
		
		response=Response.status(200).entity(resp).build();
		logger.info("End-[ProfileResource-sendMailForNewServiceAddressAddition]");
		return response;
	}
	
	@POST
	@Path("validatePassword")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response validatePassword(@FormParam("userName") String userName, @FormParam("password") String password,
			@FormParam("companyCode") String companyCode) {
		Response response = null;
		PasswordValidityResponse passwordValidityResponse = profileBO.validatePassword(userName, password, companyCode);
		response = Response.status(200).entity(passwordValidityResponse).build();
		return response;
	}
	
}	
	
