package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.multibrand.vo.response.GenericResponse;
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
@RestController
public class ProfileResource {
	
	/** Object of ProfileBO class. */
	@Autowired
	private ProfileBO profileBO;
	
	@Autowired 
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
	@PostMapping(value = "profile/forgotUserName", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse forgotUserName(@RequestParam("accountNumber") String accountNumber,@RequestParam("companyCode") String companyCode,@RequestParam("zip") String zip,@RequestParam("languageCode") String languageCode,@RequestParam("brandName") String brandName){
		
		logger.info("accountNumber :"+accountNumber+"companyCode :"+companyCode+"zip :"+zip);
		
		
		String sessionId = httpRequest.getSession(true).getId();
		ForgotUserNameResponse forgotUserNameResponse  = profileBO.forgotUserName(accountNumber,companyCode,zip,languageCode,sessionId,brandName);
		
		return forgotUserNameResponse;
		
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
	@PostMapping(value = "profile/forgotPassword", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse forgotPassword(@RequestParam("accountNumber") String accountNumber,@RequestParam("companyCode") String companyCode,@RequestParam("brandName") String brandName,@RequestParam("zip") String zip,@RequestParam("languageCode") String languageCode){
		
		String sessionId = httpRequest.getSession(true).getId();
		ForgotPasswordResponse forgotPasswordResponse = profileBO.forgotPassword(accountNumber,companyCode,brandName,languageCode,zip,sessionId);
				
		return forgotPasswordResponse;
		
	}
	
	/** This service is to validate Customer received Email i.e valid or expired.
	 * @author cuppala
	 * @param transactionId		Transaction Identification number received in email
	 * @param companyCode		Company code
	 * @param brandName			Company Brand name
	 * @return response			Provide JSON/XML customer  data response
	 */
	@PostMapping(value = "profile/validatePasswordlink", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse validateForgotPasswordLink(@RequestParam("transactionId") String transactionId,@RequestParam("companyCode") String companyCode,@RequestParam("brandName") String brandName){
		String sessionId = httpRequest.getSession(true).getId();
		ValidatePasswordLinkResponse validatePasswordLinkResp = profileBO.validateForgotPasswordLink(transactionId,companyCode,brandName,sessionId);
		return validatePasswordLinkResp;
	}

	
	
	/** This service is to get the username or account number from LDAP.
	 * @author kdeshmu1
	 * @param userID		Customer User Identification no
	 * @return response			Provide JSON/XML customer  data response
	 */
	@PostMapping(value = "profile/getUserOrAcctNumber", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getUserOrAcctNumber(@RequestParam("userId") String userID,@RequestParam("companyCode") String companyCode){
		UserInfoResponse userInfoResponse = profileBO.getUserOrAcctNumber(userID,companyCode,httpRequest.getSession(true).getId());
		return userInfoResponse;
		
	}
	
	/** This service is to update the password in ldap
	 * @author kdeshmu1
	 * @param userName		Customer UserName
	 * @param newPassword		Customer  New Password
	 * @return response			Provide JSON/XML customer  data response
	 */
	@PostMapping(value = "profile/updatePassword", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse updatePassword(@RequestParam("userName") String userName,@RequestParam("newPassword") String newPassword,
			@RequestParam("companyCode") String companyCode){
		
		
		
		UpdatePasswordResponse updatePasswordResponse = profileBO.updatePassword(userName,newPassword,companyCode, httpRequest.getSession(true).getId());
		
		return updatePasswordResponse;
		
	}
	
	/** This service is to update the password in ldap
	 * @author kdeshmu1
	 * @param userName		Customer UserName
	 * @param newPassword		Customer  New Password
	 * @return response			Provide JSON/XML customer  data response
	 */
	@PostMapping(value = "profile/updatePasswordBehindLogin", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse updatePasswordBehindLogin(@RequestParam("userName") String userName,@RequestParam("newPassword") String newPassword,
			@RequestParam("oldPassword") String oldPassword,@RequestParam("companyCode") String companyCode){
		
		
		UpdatePasswordResponse updatePasswordResponse = profileBO.updatePasswordBehindLogin(userName,newPassword,oldPassword,companyCode, httpRequest.getSession(true).getId());
		
		
		return updatePasswordResponse;
		
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
	@PostMapping(value = "profile/updateBillingAddress", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse updateBillingAddress(@RequestParam("accountNumber") String accountNumber,@RequestParam("streetName") String streetName,
			@RequestParam("streetNum") String streetNum,@RequestParam("city") String city,@RequestParam("state") String state
			,@RequestParam("aptNum") String aptNum,@RequestParam("country") String country,@RequestParam("zip") String zip,
			@RequestParam("companyCode") String companyCode,@RequestParam("bpNumber") String bpNumber,@RequestParam("poBox") String poBox, @RequestParam("brandName")String brandName){
		
		
		
		UpdateBillingAddressResponse updateBillingAddressResponse = profileBO.updateBillingAddress(accountNumber,streetName,streetNum,city,state,
				aptNum,country,zip,companyCode,bpNumber,poBox,httpRequest.getSession(true).getId(), brandName);
		
		return updateBillingAddressResponse;
		
	}
	
	/**
	 * This Method is to change the username
	 * @author Kdeshmu1
	 * @param userName
	 * * @param oldUserName
	 * * @param companyCode
	 * @return
	 */
	@PostMapping(value = "profile/changeUsername", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse changeUsername(@RequestParam("userName") String userName,
			@RequestParam("oldUserName") String oldUserName,@RequestParam("companyCode") String companyCode){
		
		
		
		//companyCode="0391";
		ChangeUsernameResponse changeUsernameResponse = profileBO.changeUsername(userName,oldUserName,companyCode,httpRequest.getSession(true).getId());
		
		
		return changeUsernameResponse;
		
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
	@PostMapping(value = "profile/updateContactInfo", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse updateContactInfo(@RequestParam("accountNumber") String accountNumber,@RequestParam("homePhone") String homePhone ,
			@RequestParam("email") String email,@RequestParam("bpNumber") String bpNumber,@RequestParam("uniqueID") String uniqueID ,
			@RequestParam("userName") String userName ,@RequestParam("companyCode") String companyCode,@RequestParam("workPhone") String workPhone,
			@RequestParam("cellPhone") String cellPhone, @RequestParam("brandName")String brandName,@RequestParam("marketingPref")String marketingPref, 
			@RequestParam("existingEmail")String existingEmail, @RequestParam("billingOptionChangeFlag") String billingOptionChangeFlag){
		
		
		//companyCode="0391";
		logger.info(" START ******* Input for the updateContactInfo API**********");
		UpdateContactInfoResponse updateContactInfoResponse = profileBO.updateContactInfo(accountNumber,homePhone,email,bpNumber,
				uniqueID,userName,companyCode,workPhone,cellPhone,httpRequest.getSession(true).getId(), brandName,marketingPref, existingEmail, billingOptionChangeFlag);
		
			
		logger.info(" END ******* Input for the updateContactInfo API**********");
		return updateContactInfoResponse;
		
	}
	
	@PostMapping(value = "profile/getContractInfo", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getContractInfo(@RequestParam("accountNumber") String accountNumber, 
			                        @RequestParam("bpNumber") String bpNumber,
			                        @RequestParam("languageCode") String languageCode, 
			                        @RequestParam("companyCode") String companyCode,
			                        @RequestParam("brandName") String brandName,
			                        @RequestParam("contractId")String contractId,
			                        @RequestParam("esid")String esid){
		
		
		logger.info("Start-[ProfileResource-getContractInfo]");
		GetContractInfoResponse getContractInfoResponse = profileBO.getContractInfo(accountNumber,bpNumber,esid,contractId,languageCode,companyCode,brandName,httpRequest.getSession(true).getId());
		

		
		logger.info("Start-[ProfileResource-getContractInfo]");
		return getContractInfoResponse;
		
	}

	@PostMapping(value = "profile/smartMeterCheck", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse smartMeterCheck(@RequestParam("accountNumber") String accountNumber,@RequestParam("esid") String esid ,
			@RequestParam("companyCode") String companyCode){
		
		
		companyCode="0391";
		logger.info("Start-[ProfileResource-smartMeterCheck]");
		logger.info("::::::::::::::::::::::::::::::11");
		SmartMeterCheckResponse smeterCheckRep = profileBO.getSmartMeterCheck(
				accountNumber, esid, companyCode,httpRequest.getSession(true).getId());
		logger.info("::::::::::::::::::::::::::::::::::::12");
		
		logger.info("END-[ProfileResource-smartMeterCheck]");
		return smeterCheckRep;
		
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
	@PostMapping(value = "profile/productUpdate", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse productUpdate(@RequestParam("accountNumber") String accountNumber,@RequestParam("action") String action ,
			@RequestParam("objectId") String objectId,@RequestParam("extUi") String extUi,@RequestParam("enrollType") String enrollType ,
			@RequestParam("requestDate") String requestDate ,@RequestParam("manuPartNo") String manuPartNo,@RequestParam("companyCode") String companyCode,@RequestParam("bpNumber")String bpNumber,@RequestParam("source")String source){
		
		
		ProductUpdateResponse productResponse = new ProductUpdateResponse();
		productResponse = profileBO.productUpdate(accountNumber, action , objectId, extUi, enrollType , requestDate , manuPartNo, companyCode,httpRequest.getSession(true).getId(),bpNumber,source);
		
		
		return productResponse;
		
	}
	/**
	 * 
	 */
	@PostMapping(value = "profile/environmentalImpacts", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse environmentalImpacts(@RequestParam("accountNumber")String accountNumber,@RequestParam("companyCode") String companyCode){
		
		
		EnvironmentImpactsResponse environmentImpactsResponse = new EnvironmentImpactsResponse();
		environmentImpactsResponse = profileBO.environmentalImpacts(accountNumber,companyCode, httpRequest.getSession(true).getId());
		
		return environmentImpactsResponse;
		
	}
	
	
	/**
	 * @author mshukla1
	 * SecondaryName CURD call
	 */
	@PostMapping(value = "profile/secondaryNameUpdate", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse secondaryNameUpdate(@RequestParam("accountNumber")String accountNumber,
										@RequestParam("bpid")String bpid,
										@RequestParam("action")String action,
										@RequestParam("bpid2")String bpid2,
										@RequestParam("firstName")String firstName,
										@RequestParam("lastName")String lastName,
										@RequestParam("middleName")String middleName,
										@RequestParam("validFrom")String validFrom,
										@RequestParam("validUntil")String validUntil,
										@RequestParam("companyCode")String companyCode){
		
		
		logger.info("Start-[ProfileResource-secondaryNameUpdate]");
		SecondaryNameResponse secNameResponse =new SecondaryNameResponse();
		secNameResponse = profileBO.secondaryNameUpdate(accountNumber,bpid,action,bpid2,firstName,lastName,middleName,validFrom,validUntil,companyCode, httpRequest.getSession(true).getId());
		
		logger.info("End-[ProfileResource-secondaryNameUpdate]");
		return secNameResponse;
	}
	
	@PostMapping(value = "profile/wseDeEnrollService", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse wseDeEnrollService(@RequestParam("accountNumber")String accountNumber,
											 @RequestParam("contractNumber")String contractNumber,
											  @RequestParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEnrollDeEnrollService]");	
		
		WsServiceResponse wsResponse = profileBO.wsDeEnrollService(
				accountNumber, contractNumber,  companyCode,
				httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-wseEnrollDeEnrollService]");	
		return wsResponse;
			
			
  }
	
	@PostMapping(value = "profile/wseEnrollService", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse wseEnrollService(@RequestParam("contractNumber")String contractNumber,
									  @RequestParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEnrollDeEnrollService]");	
		
		WsEnrollmentResponse wsResponse = profileBO.wsEnrollService(contractNumber, companyCode, httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-wseEnrollDeEnrollService]");	
		return wsResponse;
			
			
  }
	
	@PostMapping(value = "profile/WseEligiblityStatusCall", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse wseEligibilityStatus(@RequestParam("contractNumber")String contractNumber,
										 @RequestParam("esid") String esid,
									     @RequestParam("companyCode")String companyCode) {
		logger.info("Start-[ProfileResource-wseEligibilityStatus]");	
		
		WseEligiblityStatusResponse wsResponse = profileBO
				.wseEligibilityStatus(contractNumber, esid, companyCode,
						httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-wseEligibilityStatus]");	
		return wsResponse;
			
			
  }
	
	/**
	 * @author smuruga1
	 * @param bpId
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "profile/getCirroStructure", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getCirroStructureCall(@RequestParam("bpId")String bpId,
									  @RequestParam("companyCode")String companyCode,
									  @RequestParam("brandName")String brandName,
									  @RequestParam("noOfDayBack")String noOfDayBack) {
		logger.info("Start-[ProfileResource-getCirroStructureCall]");	
		
		CirroStructureResponse wsResponse = profileBO.getCirroStructureCall(bpId, companyCode, brandName,noOfDayBack,httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-getCirroStructureCall]");	
		return wsResponse;	
  }
	
	/**
	 * 
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "profile/validateAccount", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse validateAccount(@RequestParam("accountNumber")String caNumber,
									  @RequestParam("companyCode")String companyCode,
									  @RequestParam("brandName")String brandName) {
		logger.info("Start-[ProfileResource-validateAccount]");	
		
		AcctValidationResponse wsResponse = profileBO.validateAccount(caNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-validateAccount]");	
		return wsResponse;	
  }
  
	@PostMapping(value = "profile/updateLanguage", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse updateLanguage(@RequestParam("businessPartnerId") String bpid, @RequestParam("contractAccountNumber") String ca, @RequestParam("languageCode")String languageCode, @RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName){
		
		UpdateLanguageResponse updateLanguageResponse = profileBO
				.updateLanguage(bpid, ca, languageCode, companyCode, brandName,httpRequest.getSession(true).getId());
		
		return updateLanguageResponse;
		
	}
	
	
	/**
	 * 
	 * @param caNumber
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "profile/getSVTData", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getSVTData(@RequestParam("bpNumber")String bpNumber,
									  @RequestParam("companyCode")String companyCode,
									  @RequestParam("brandName")String brandName) {
		logger.info("Start-[ProfileResource-getSVTData]");	
		
		GetBPInfoResponse svtResponse = profileBO.getSVTData(bpNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-getSVTData]");	
		return svtResponse;	
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
	@PostMapping(value = "profile/profileCheck", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse profileCheck (
			  @RequestParam("companyCode")String companyCode,
			  @RequestParam("brandName")String brandName,
			  @RequestParam("contractAccountNumber") String contractAccountNumber,
			  @RequestParam("email") String email,
			  @RequestParam("checkDigit") String checkDigit ) {
		logger.info("Start-[ProfileResource-profileCheck]");	
		
		ProfileCheckResponse profileCheckResponse = profileBO.profileCheck(companyCode,brandName,contractAccountNumber,email,checkDigit,httpRequest.getSession(true).getId());
		
		logger.info("END-[ProfileResource-profileCheck]");	
		return profileCheckResponse;
	
	
}
	/**
	 * Operation for fetching userId.
	 * @param userIdRequest UserIdRequest
	 * @return
	 */
	@PostMapping(value = "profile/getUserId", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserIdResponse getUserId(UserIdRequest userIdRequest){
				
		
		logger.info("Start-[ProfileResource-getUserId]");
		UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		
		logger.info("End-[ProfileResource-getUserId]");
		return userIdResponse;
	}
	
	@PostMapping(value = "profile/sendMailForPasswordChange", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public SendMailForPasswordChangeResponse sendMailForPasswordChange(
			 @RequestParam("companyCode")String companyCode,
			  @RequestParam("brandName")String brandName,
			  @RequestParam("contractAccountNumber") String contractAccountNumber,
			  @RequestParam("email") String email			
			){
				
		
		logger.info("Start-[ProfileResource-sendMailForPasswordChange]");
		//UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		
		SendMailForPasswordChangeResponse resp = profileBO.sendMailForPasswordChange(companyCode, brandName, email, contractAccountNumber, httpRequest.getSession(true).getId());
		
		
		logger.info("End-[ProfileResource-sendMailForPasswordChange]");
		return resp;
	}
	
	@PostMapping(value = "profile/sendMailForNewServiceAddressAddition", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public SendMailForNewServiceAddressAddResponse sendMailForNewServiceAddressAddition(
			 @RequestParam("companyCode")String companyCode,
			  @RequestParam("brandName")String brandName,
			  @RequestParam("contractAccountNumber") String contractAccountNumber,
			  @RequestParam("email") String email			
			){
				
		
		logger.info("Start-[ProfileResource-sendMailForNewServiceAddressAddition]");
		//UserIdResponse userIdResponse = profileHelper.getuserId(userIdRequest);
		
		SendMailForNewServiceAddressAddResponse resp = profileBO.sendMailForNewServiceAddressAddition(companyCode, brandName, email, contractAccountNumber, httpRequest.getSession(true).getId());
		
		
		logger.info("End-[ProfileResource-sendMailForNewServiceAddressAddition]");
		return resp;
	}
	
	@PostMapping(value = "profile/validatePassword", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public PasswordValidityResponse validatePassword(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("companyCode") String companyCode) {
		logger.info("Start-[ProfileResource-validatePassword]");
		PasswordValidityResponse passwordValidityResponse = profileBO.validatePassword(userName, password, companyCode);
		logger.info("End-[ProfileResource-validatePassword]");
		return passwordValidityResponse;
	}
	
}	
	
