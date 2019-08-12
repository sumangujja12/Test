package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.PreferenceBO;
import com.multibrand.dto.request.ActivationRequest;
import com.multibrand.dto.request.DeactivateRequest;
import com.multibrand.dto.request.GetAllBPRequest;
import com.multibrand.dto.request.GetContactInfoRequest;
import com.multibrand.dto.request.ReadContactAlertRequest;
import com.multibrand.dto.request.SaveUpdateAlertPrefRequest;
import com.multibrand.dto.request.SendActivateRequest;
import com.multibrand.dto.request.SendNewActivateRequest;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.vo.request.OptInOptOutRequest;
import com.multibrand.vo.request.PrivacyPreferencesRequest;
import com.multibrand.vo.request.SMSOptInOutEligibilityRequest;
import com.multibrand.vo.response.ActivationResponse;
import com.multibrand.vo.response.DeactivationResponse;
import com.multibrand.vo.response.GetAllBPsResponse;
import com.multibrand.vo.response.GetContactAlertPrefsResponse;
import com.multibrand.vo.response.GetContactInfoResponse;
import com.multibrand.vo.response.OptInOptOutResponse;
import com.multibrand.vo.response.PrivacyPreferenceResponse;
import com.multibrand.vo.response.SMSOptInOutEligibilityResponse;
import com.multibrand.vo.response.UpdationResponse;

/**
 * 
 * @author mshukla1 This resource is used to handle preferences related API
 *         calls
 */

@RestController
public class PreferencesResource {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	PreferenceBO preferenceBO;
	
	@Autowired
	ErrorContentHelper errorContentHelper;

	@Context
	private HttpServletRequest httpRequest;

	/**
	 * This method is used activate phone
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/activatePhone", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response activatePhone(@Valid ActivationRequest request) {

		Response response = null;
		ActivationResponse actResponse = preferenceBO.activatePhone(
				request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(actResponse).build();
		return response;
	}

	/**
	 * This method is used to send activation code
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/sendActivate", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response sendActivate(@Valid SendActivateRequest request) {

		Response response = null;
		ActivationResponse actResponse = preferenceBO.sendActivate(request,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(actResponse).build();
		return response;
	}

	/**
	 * This method is Used to send a new Activation Code to the Phone
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/sendNewActivate", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response sendNewActivate(@Valid SendNewActivateRequest request) {

		Response response = null;
		ActivationResponse actResponse = preferenceBO.sendNewActivate(request,
				httpRequest.getSession(true).getId());
		response = Response.status(200).entity(actResponse).build();
		return response;
	}

	/**
	 * This method is get status of Account
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/getContactInfo", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response getContactInformation(@Valid GetContactInfoRequest request) {
		Response response = null;

		GetContactInfoResponse contactInfoResp = preferenceBO
				.getContactInformation(request,
						httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(contactInfoResp).build();
		return response;
	}

	/**
	 * This method is used to get All BP's associated with phoneNumber
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/getAllBPs", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response getAllBP(@Valid GetAllBPRequest request) {
		Response response = null;
		GetAllBPsResponse getAllBpResp = preferenceBO.getAllBP(request, httpRequest.getSession(true).getId());

		response = Response.status(200).entity(getAllBpResp).build();
		return response;
	}

	/**
	 * This method is used to DeActivate the Phone for SMS Alerts
	 * 
	 *  @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/deactivatePhone", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response deactivatePhone(@Valid DeactivateRequest request) {
		Response response = null;
		DeactivationResponse deactivationResponse = preferenceBO
				.deactivatePhone(request, httpRequest.getSession(true)
								.getId());
		response = Response.status(200).entity(deactivationResponse).build();
		return response;
	}
	
	
	/**This method is used to read contact alert preferences
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/readContactAlertPref", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response readContactAlertPrefs(@Valid ReadContactAlertRequest request){
		
		Response response = null;
		GetContactAlertPrefsResponse getContAlertPref = preferenceBO
				.readContactPrefs(request,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(getContAlertPref).build();
		
		return response;
	}
	
	/**
	 * This method is used to update contact alert preferences
	 *  @param request
	 * @return
	 */
	@PostMapping(value = "/preferences/protected/saveUpdateContactAlertPrefs", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response saveUpdateContactAlertPrefs(@Valid SaveUpdateAlertPrefRequest request){
		
		Response response = null;
		
		UpdationResponse updationResponse = preferenceBO
				.saveUpdateContactAlertPref(request, httpRequest.getSession(true)
								.getId());
		response = Response.status(200).entity(updationResponse).build();
		return response;
		
	}
	
	@PostMapping(value = "/preferences/savePrivacyPreference", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response savePrivacyPreference(PrivacyPreferencesRequest request){
		
		Response response = null;
		
		PrivacyPreferenceResponse resp = preferenceBO.savePrivacyPreference(request);
		response = Response.status(200).entity(resp).build();
		return response;
		
	}
	/** US12887 - DK | SMS ALERTS | 10/15/2018 **/
	
	/**
	 * @author dkrishn1
	 * This method returns SMS OPT IN  and OPT OUT Eligibility from CCS via NRGWS Service Layer for given BP/CA combination.
	 * @param request The SMSOptInOutEligibilityRequest Object
	 * @return The SMSOptInOutEligibilityResponse Object
	 */
	@PostMapping(value = "/preferences/checkOptInOutEligibility", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response checkOptInOutEligibility(@Valid SMSOptInOutEligibilityRequest request){
		
		Response response = null;		
		SMSOptInOutEligibilityResponse resp = preferenceBO.checkOptInOutEligibility(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		return response;
		
	}
	/** END | US12887 - DK | SMS ALERTS | 10/15/2018 **/
	
	/** US12884 - DK | SMS ALERTS | 10/16/2018 **/
	
	/**
	 * @author dkrishn1
	 * This method returns SMS OPT IN  and OPT OUT Eligibility from CCS via NRGWS Service Layer for given BP/CA combination.
	 * @param request The SMSOptInOutEligibilityRequest Object
	 * @return The SMSOptInOutEligibilityResponse Object
	 */
	@PostMapping(value = "/preferences/OptInOptOut", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response OptInOptOut(@Valid OptInOptOutRequest request){		
		Response response = null;				
		OptInOptOutResponse resp = preferenceBO.OptInOptOutToSMS(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		return response;
		
	}
	/** END | US12884 - DK | SMS ALERTS | 10/16/2018 **/
}
