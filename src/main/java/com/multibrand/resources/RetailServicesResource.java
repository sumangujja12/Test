package com.multibrand.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.RetailServicesBO;
import com.multibrand.dto.response.RetailServicesResponse;
import com.multibrand.service.RetailServicesService;
import com.multibrand.vo.request.CheckReliantCustomerStatusRequest;
import com.multibrand.vo.response.CheckReliantCustomerStatusResponse;
import com.multibrand.vo.response.CheckZipSecurityEligibilityResponse;
import com.multibrand.vo.response.GenericResponse;

@RestController
public class RetailServicesResource {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private RetailServicesService retailServicesService;
	
	@Autowired
	RetailServicesBO retailServicesBO;
	
	@PostMapping(value = "/retailServices/readHouseAgeAndHHIncome", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> readHouseAgeAndHHIncome(@RequestParam("esidOrAddressId") String esidOrAddressId) {
		logger.info(" START ******* readHouseAgeAndHHIncome API**********");
		RetailServicesResponse retailServicesResponse = new RetailServicesResponse();
		try{
			retailServicesResponse = retailServicesService.readHouseAgeAndHHIncome(esidOrAddressId);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE calling readHouseAgeAndHHIncome REST Service:", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(retailServicesResponse).build();
		logger.info(" END ******* readHouseAgeAndHHIncome API**********");
		return new ResponseEntity<Object>(retailServicesResponse, HttpStatus.OK);
	} 
	
	@PostMapping(value = "/retailServices/readReliantCustomerStatus", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> readReliantCustomerStatus(@RequestParam("email") String email,@RequestParam("fName") String fName,@RequestParam("lName") String lName,
			@RequestParam("street") String street,@RequestParam("houseNo") String houseNo,@RequestParam("apartmentNo") String apartmentNo,
			@RequestParam("city") String city,@RequestParam("state") String state,@RequestParam("zipCode") String zipCode) {
		logger.info(" START ******* readReliantCustomerStatus API**********");
		CheckReliantCustomerStatusRequest request =new CheckReliantCustomerStatusRequest();
		request.setEmail(email);
		request.setfName(fName);
		request.setlName(lName);
		request.setStreet(street);
		request.setHouseNo(houseNo);
		request.setApartmentNo(apartmentNo);
		request.setCity(city);
		request.setState(state);
		request.setZipCode(zipCode);
		
		CheckReliantCustomerStatusResponse checkReliantCustomerStatusResponse = new CheckReliantCustomerStatusResponse();
		try{
			checkReliantCustomerStatusResponse = retailServicesBO.readReliantCustomerStatus(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE calling readReliantCustomerStatus REST Service:", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(checkReliantCustomerStatusResponse).build();
		logger.info(" END ******* readReliantCustomerStatus API**********");
		return new ResponseEntity<GenericResponse>(checkReliantCustomerStatusResponse, HttpStatus.OK);
	} 
	
	@PostMapping(value = "/retailServices/checkZipForSecurityEligibility", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public 	ResponseEntity<GenericResponse> checkZipForSecurityEligibility(@RequestParam("zipCode") String zipCode) {
		logger.info(" START ******* checkZipForSecurityEligibility API**********");
		CheckZipSecurityEligibilityResponse checkZipSecurityEligibilityResponse = new CheckZipSecurityEligibilityResponse();
		try{
			checkZipSecurityEligibilityResponse = retailServicesBO.checkZipForSecurityEligibility(zipCode);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE calling checkZipForSecurityEligibility REST Service:", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(checkZipSecurityEligibilityResponse).build();
		logger.info(" END ******* checkZipForSecurityEligibility API**********");
		return new ResponseEntity<GenericResponse>(checkZipSecurityEligibilityResponse, HttpStatus.OK);
	} 
	
	
}
