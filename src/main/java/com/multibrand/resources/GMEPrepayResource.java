package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.BillingBO;
import com.multibrand.bo.PrepayBO;
import com.multibrand.dto.request.TemperatureRequest;
import com.multibrand.vo.request.CheckIfPrepayOfferRequest;
import com.multibrand.vo.response.CheckIfPrepayOfferResponse;
import com.multibrand.vo.response.SSBalanceAndUsageResponse;
import com.multibrand.vo.response.StartTollTagResponse;
import com.multibrand.vo.response.TemperaturedataResponse;
import com.multibrand.vo.response.billingResponse.PrepayDocReadResponse;
import com.multibrand.vo.response.billingResponse.PrepayDocUpdateResponse;

/**
 * 
 * @author dkrishn1
 * This resource is used to handle all GME prepay related API calls.
 */
@RestController
public class GMEPrepayResource {
private static Logger logger = LogManager.getLogger(GMEPrepayResource.class);
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	@Autowired
	BillingBO billingBO;
	
	@Autowired
	PrepayBO prepayBO;
	
	
	/** This service provides the account balance information from CCS system.
	 * 
	 * @author dkrishn1
	 * 
	 */
	@PostMapping(value = "/gmePrepay/protected/checkPrepayOffer", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CheckIfPrepayOfferResponse checkPrepayOffer(@RequestParam("offerCode") String offerCode,@RequestParam("companyCode") String companyCode, @RequestParam("brandName")String brandName){
		
		//Response retResponse= null;
		CheckIfPrepayOfferRequest checkIfPrepayOfferRequest = new CheckIfPrepayOfferRequest();
		checkIfPrepayOfferRequest.setBrandId(brandName);
		checkIfPrepayOfferRequest.setCompanyCode(companyCode);
		checkIfPrepayOfferRequest.setOfferCode(offerCode);

		CheckIfPrepayOfferResponse checkPrepayOfferResponse = prepayBO.checkPrepayOffer(checkIfPrepayOfferRequest, httpRequest.getSession(true).getId());
		
		
		//retResponse = Response.status(200).entity(checkPrepayOfferResponse).build();
		logger.info(" END ******* checkPrepayOffer API********** and JSON Response is:::"+checkPrepayOfferResponse);
	
		return checkPrepayOfferResponse;
	}
	
	/**
	 * @author mshukla1
	 * @param accountNumber
	 * @param fromDate
	 * @param toDate
	 * @param billToDate
	 * @param esid
	 * @param language
	 * @param noOfMonths
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "/gmePrepay/protected/getAcctBalanceAndUsageData", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public SSBalanceAndUsageResponse getBalance(@RequestParam("contractAccountNumber") String contractAccountNumber, 
			                   @RequestParam("fromDate") String fromDate,
			                   @RequestParam("toDate") String toDate,
			                   @RequestParam("billToDate") String billToDate,
			                   @RequestParam("esid") String esid,
			                   @RequestParam("language") String language,
			                   @RequestParam("noOfMonths") String noOfMonths,
			                   @RequestParam("companyCode") String companyCode, 
			                   @RequestParam("brandName")String brandName){
		
		//Response response = null;
		
		SSBalanceAndUsageResponse ssBalAndUsageRes = prepayBO.getBalanceAndUsageData(contractAccountNumber, fromDate, 
				                                         toDate, billToDate, esid, language, noOfMonths,companyCode,brandName);
		//response = Response.status(200).entity(ssBalAndUsageRes).build();
				
		return ssBalAndUsageRes;
		
	}
	
	/**
	 * This is pre pay doc read call
	 * @author mshukla1
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/gmePrepay/protected/prepayDocRead", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public PrepayDocReadResponse prepayDocRead(@RequestParam("contractAccountNumber") String contractAccountNumber,
			                      @RequestParam("companyCode") String companyCode
			                      ,@RequestParam("brandName")String brandName){
		//Response response = null;
		
		PrepayDocReadResponse ppdocReadresponse = prepayBO.prepayDocRead(contractAccountNumber,companyCode,brandName);
		//response = Response.status(200).entity(ppdocReadresponse).build();
		
		return ppdocReadresponse;
	}
	
	 /**
     * Prepay Doc update 
     * @author mshukla1
     * @param accountNumber
     * @param ppdId
     * @param reloadAmount
     * @param serviceEndDate
     * @param serviceStartDate
     * @param status
     * @param thresHoldAmt
     * @param tollTagEndDate
     * @param tollTagStartDate
     * @param uuid
     * @param companyCode
     * @param brandName
     * @return
     */
	@PostMapping(value = "/gmePrepay/protected/prepayDocUpdate", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public PrepayDocUpdateResponse prepayDocUpdate(@RequestParam("contractAccountNumber") String contractAccountNumber,
			                        @RequestParam("ppdId") String ppdId,
			                        @RequestParam("reloadAmount") String reloadAmount,
			                        @RequestParam("serviceEndDate")String serviceEndDate,
			                        @RequestParam("serviceStartDate")String serviceStartDate,
			                        @RequestParam("status")String status,
			                        @RequestParam("thresholdAmt")String thresHoldAmt,
			                        @RequestParam("tollTagEndDate")String tollTagEndDate,
			                        @RequestParam("tollTagStartDate")String tollTagStartDate,
			                        @RequestParam("uuid")String uuid,
			                        @RequestParam("companyCode") String companyCode,
			                        @RequestParam("brandName")String brandName){
		//Response response = null;
		PrepayDocUpdateResponse ppdUpdateResponse  =  prepayBO.prepayDocUpdate(contractAccountNumber
				                                         ,ppdId,reloadAmount,serviceEndDate,serviceStartDate
				                                         ,status,thresHoldAmt,tollTagEndDate,tollTagStartDate
				                                         ,uuid,companyCode,brandName);
		//response = Response.status(200).entity(ppdUpdateResponse).build();
				
		return ppdUpdateResponse;
	}
	
	/**
	 * StartTollTagMonitor
	 * @param paymentMode
	 * @param contractAccount
	 * @param brandName
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/gmePrepay/protected/startTollTagMonitor", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StartTollTagResponse startTollTagMonitor(@RequestParam("paymentMode")String paymentMode, @RequestParam("contractAccountNumber")String contractAccount, 
			                            @RequestParam("brandName")String brandName, @RequestParam("companyCode")String companyCode){
		//Response response = null;
		
		StartTollTagResponse tolltagResponse = prepayBO.startTollTagMonitor(paymentMode, contractAccount, brandName, companyCode, httpRequest.getSession(true).getId());
		
		//response = Response.status(200).entity(tolltagResponse).build();
		return tolltagResponse;
	}
	
	/**
	 * get temperature data
	 * @author gkrishn1
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@PostMapping(value = "/gmePrepay/protected/getTemperatureData", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public TemperaturedataResponse getTemperatureData( @Valid TemperatureRequest request){
		
		//Response response = null;
		
		TemperaturedataResponse tempResponse = prepayBO.getTemperatureData(request);
		
		//response = Response.status(200).entity(tempResponse).build();
				
		return tempResponse;
		
	}
}
