package com.multibrand.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.response.TCSBPDetailsDTO;
import com.multibrand.service.TCSCslrService;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.TCSBPDetailsResponse;

/**
 * 
 * @author rpendur1
 * This resource is used to handle all Community Solar TCS read related API calls.
 */
@RestController
public class TCSCslrResource implements Constants {
private static Logger logger = LogManager.getLogger(TCSCslrResource.class);
	
	@Autowired
	private TCSCslrService tcsCslrService;
	/**
	 * @param agreementId agreementId
	 * @return
	 */
	@PostMapping(value = "/tcsCslrResource/getBPDetails", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public TCSBPDetailsResponse getBPDetails(@RequestParam("agreementId") String agreementId){
		
		//Response response = null;

		TCSBPDetailsResponse tcsBPDetailsResponse = new TCSBPDetailsResponse();
		List<TCSBPDetailsDTO> tcsBPDetailsDTOList = new  ArrayList<TCSBPDetailsDTO>();
		
		tcsBPDetailsDTOList = tcsCslrService.getBPDetails(agreementId);
		
		tcsBPDetailsResponse.setTcsBPDetailsList(tcsBPDetailsDTOList);

		//response = Response.status(200).entity(tcsBPDetailsResponse).build();
				
		return tcsBPDetailsResponse;
		
	}	
}
