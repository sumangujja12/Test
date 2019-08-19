package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.request.CslrUpdateBillinlgAddrRequest;
import com.multibrand.service.CslrCcsService;
import com.multibrand.vo.response.CslrCcsProfileResponse;
import com.multibrand.vo.response.CslrUpdateBillinlgAddrResponse;
import com.multibrand.vo.response.GenericResponse;

/**
 * 
 * @author mdasari1
 * This resource is used to handle all Community Solar CCS related API calls.
 */
@RestController
public class CslrCcsResource {
private static Logger logger = LogManager.getLogger(CslrCcsResource.class);
	
  	
	//@Autowired
	//private CSLRSalesforceService cslrSalesforceService;

	@Autowired
	private CslrCcsService cslrCcsService;
	
	/**
	 * Get CCS Profile fro Community Solar user
	 * @param caNumber
	 * @param bpNumber
	 * @param leaseId
	 * @param hh
	 * @param request
	 * @return
	 */
	@PostMapping(value="/cslrCcsResource/getCslrProfile", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getCslrProfile(
			@RequestParam("caNumber") String caNumber,
			@RequestParam("bpNumber") String bpNumber,
			@RequestParam("lease_Id") String leaseId,
			HttpHeaders hh,
			HttpServletRequest request){
		
		logger.debug("Start CslrCcsResource.getCslrProfile :: START");
		//Response response = null;
		
		CslrCcsProfileResponse cslrCcsProfileResponse = cslrCcsService.getCslrProfile(caNumber, bpNumber, leaseId);
		//Gson gson = new Gson();
		//logger.info(gson.toJson(cslrCcsProfileResponse, CslrCcsProfileResponse.class));
		//response = Response.status(200).entity(cslrCcsProfileResponse).build();
		logger.debug("END CslrCcsResource.getCslrProfile :: START");
		return new ResponseEntity<GenericResponse>(cslrCcsProfileResponse, HttpStatus.OK);
	}
	
	@PostMapping(value="/cslrCcsResource/cslrUpdateBillingAddress", consumes =  { MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse>  updateBillingAddr(@Valid CslrUpdateBillinlgAddrRequest request) {
		
		logger.debug("Start CslrCcsService.updateBillingAddr :: START");
		
		//Response response = null;
		
		CslrUpdateBillinlgAddrResponse cslrUpdateBillinlgAddrResponse = cslrCcsService.updateBillingAddress(request);
		//response = Response.status(200).entity(cslrUpdateBillinlgAddrResponse).build();

		logger.debug("End CslrCcsService.updateBillingAddr :: END");
		
		return new ResponseEntity<GenericResponse>(cslrUpdateBillinlgAddrResponse, HttpStatus.OK);
	}
	
}
