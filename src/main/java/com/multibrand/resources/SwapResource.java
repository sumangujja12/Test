package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.SwapBO;
import com.multibrand.vo.request.SubmitSwapRequest;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.SubmitSwapResponse;
import com.multibrand.vo.response.swapResponse.PendingSwapResponse;
import com.multibrand.vo.response.swapResponse.RolloverPlanResponse;


/** This Resource is to handle all the Billing Related API calls.
 * 
 * @author Kdeshmu1
 */
@RestController
public class SwapResource {
	
	/** Object of SwapBO class. */
	@Autowired
	private SwapBO swapBO;
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	/**
	 * @author Kdeshmu1
	 * @modified ahanda1 : removed form params that were not required.
	 * @modified rbansal30 : added/modified form params.
	 * @param campaignCode
	 * @param offerCode
	 * @param contractId
	 * @param esid
	 * @param currentContractEndDate
	 * @param languageCode
	 * @param companyCode
	 * @param accountNumber
	 * @param checkDigit
	 * @param bpNumber
	 * @param caName
	 * @param servStreetNum
	 * @param servStreetName
	 * @param servStreetAptNum
	 * @param servCity
	 * @param servState
	 * @param servZipCode
	 * @param billStreetNum
	 * @param billStreetName
	 * @param billStreetAptNum
	 * @param billAddrPOBox
	 * @param billCity
	 * @param billState
	 * @param billZipCode
	 * @param newContractBegins
	 * @param newContractEnds
	 * @param planName
	 * @param marketingText
	 * @param incentives
	 * @param avgPrice
	 * @param energyCharge
	 * @param baseCharge
	 * @param tdspCharge
	 * @param planType
	 * @param contractTerm
	 * @param cancelFee
	 * @param eflURL
	 * @param eflSmartCode
	 * @param tosURL
	 * @param tosSmartCode
	 * @param yraacURL
	 * @param yraacSmartCode
	 * @param disclaimer
	 * @param toEmail
	 * @param productContent
	 * @param promoCode
	 * @param brandName
	 * @param offerDate
	 * @param offerTime
	 * @param clientSource
	 * @return
	 */
	@PostMapping(value = "/swapResource/submitSwap", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> submitSwap(@RequestParam("campaignCode") String campaignCode, @RequestParam("offerCode") String offerCode,
			@RequestParam("contractId") String contractId,@RequestParam("esid") String esid,
			@RequestParam("currentContractEndDate") String currentContractEndDate,@RequestParam("languageCode") String languageCode,@RequestParam("companyCode") String companyCode,
			@RequestParam("accountNumber") String accountNumber,@RequestParam("checkDigit") String checkDigit, @RequestParam("bpNumber") String bpNumber, @RequestParam("caName") String caName, @RequestParam("servStreetNum") String servStreetNum,@RequestParam("servStreetName") String servStreetName,@RequestParam("servStreetAptNum") String servStreetAptNum,
			@RequestParam("servCity") String servCity,@RequestParam("servState") String servState,@RequestParam("servZipCode") String servZipCode,
			@RequestParam("billStreetNum") String billStreetNum,@RequestParam("billStreetName") String billStreetName,@RequestParam("billStreetAptNum") String billStreetAptNum,
			@RequestParam("billAddrPOBox") String billAddrPOBox,@RequestParam("billCity") String billCity,@RequestParam("billState") String billState,
			@RequestParam("billZipCode") String billZipCode,@RequestParam("newContractBegins") String newContractBegins, @RequestParam("newContractEnds") String newContractEnds, @RequestParam("planName") String planName,
			@RequestParam("marketingText") String marketingText, @RequestParam("incentives") String incentives, @RequestParam("avgPrice") String avgPrice,
			@RequestParam("energyCharge") String energyCharge, @RequestParam("baseCharge") String baseCharge, @RequestParam("tdspCharge") String tdspCharge, @RequestParam("planType") String planType,
			@RequestParam("contractTerm") String contractTerm,@RequestParam("cancelFee") String cancelFee,
			@RequestParam("eflURL") String eflURL, @RequestParam("eflSmartCode") String eflSmartCode, @RequestParam("tosURL") String tosURL, @RequestParam("tosSmartCode") String tosSmartCode,
			@RequestParam("yraacURL") String yraacURL, @RequestParam("yraacSmartCode") String yraacSmartCode, @RequestParam("disclaimer") String disclaimer, @RequestParam("toEmail") String toEmail, @RequestParam("productContent") String productContent, @RequestParam("promoCode") String promoCode,@RequestParam("brandName") String brandName, @RequestParam("offerDate") String offerDate, @RequestParam("offerTime") String offerTime,
			@RequestParam("clientSource") String clientSource,@RequestParam("source") String source){
		logger.debug("START SwapResource.submitSwap :: START");
		//Response response = null;
		SubmitSwapRequest swapRequest = new SubmitSwapRequest();
		swapRequest.setCampaignCode(campaignCode);
		swapRequest.setOfferCode(offerCode);
		swapRequest.setContractId(contractId);
		swapRequest.setEsid(esid);
		swapRequest.setCurrentContractEndDate(currentContractEndDate);
		swapRequest.setLanguageCode(languageCode);
		swapRequest.setCompanyCode(companyCode);
		swapRequest.setAccountNumber(accountNumber);
		swapRequest.setCheckDigit(checkDigit);
		swapRequest.setBpNumber(bpNumber);
		swapRequest.setCaName(caName);
		swapRequest.setServStreetNum(servStreetNum);
		swapRequest.setServStreetName(servStreetName);
		swapRequest.setServStreetAptNum(servStreetAptNum);
		swapRequest.setServCity(servCity);
		swapRequest.setServState(servState);
		swapRequest.setServZipCode(servZipCode);
		swapRequest.setBillStreetNum(billStreetNum);
		swapRequest.setBillStreetName(billStreetName);
		swapRequest.setBillStreetAptNum(billStreetAptNum);
		swapRequest.setBillAddrPOBox(billAddrPOBox);
		swapRequest.setBillCity(billCity);
		swapRequest.setBillState(billState);
		swapRequest.setBillZipCode(billZipCode);
		swapRequest.setNewContractBegins(newContractBegins);
		swapRequest.setNewContractEnds(newContractEnds);
		swapRequest.setPlanName(planName);
		swapRequest.setMarketingText(marketingText);
		swapRequest.setIncentives(incentives);
		swapRequest.setAvgPrice(avgPrice);
		swapRequest.setEnergyCharge(energyCharge);
		swapRequest.setBaseCharge(baseCharge);
		swapRequest.setTdspCharge(tdspCharge);
		swapRequest.setPlanType(planType);
		swapRequest.setContractTerm(contractTerm);
		swapRequest.setCancelFee(cancelFee);
		swapRequest.setEflURL(eflURL);
		swapRequest.setEflSmartCode(eflSmartCode);
		swapRequest.setTosURL(tosURL);
		swapRequest.setTosSmartCode(tosSmartCode);
		swapRequest.setYraacURL(yraacURL);
		swapRequest.setYraacSmartCode(yraacSmartCode);
		swapRequest.setDisclaimer(disclaimer);
		swapRequest.setToEmail(toEmail);
		swapRequest.setProductContent(productContent);
		swapRequest.setPromoCode(promoCode);
		swapRequest.setBrandName(brandName);
		swapRequest.setOfferDate(offerDate);
		swapRequest.setOfferTime(offerTime);
		swapRequest.setClientSource(clientSource);//CHG0020873
		SubmitSwapResponse submitSwapResponse = swapBO.submitSwap(swapRequest,httpRequest.getSession(true).getId(), source);
		//response = Response.status(200).entity(submitSwapResponse).build();
		logger.debug("End SwapResource.submitSwap :: END");	
		return new ResponseEntity<GenericResponse>(submitSwapResponse, HttpStatus.OK);
		
	}
	
	
	/**
	 * This service is to get RollOver Plan Details
	 * @author mshukla1
	 * @param contractId
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/swapResource/getRolloverPlanDetails", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getRolloverPlanDetails(@RequestParam("contractId")String contractId,@RequestParam("companyCode")String companyCode){
		//Response response =null;
		RolloverPlanResponse rollOverPlanResponse = swapBO.getRollovePlanDetails(contractId, companyCode, httpRequest.getSession(true).getId());
		//response =Response.status(200).entity(rollOverPlanResponse).build();
		return new ResponseEntity<GenericResponse>(rollOverPlanResponse, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param bpid
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @param contractID
	 * @param esid
	 * @param language
	 * @return
	 */
	@PostMapping(value = "/swapResource/pendingSwapDetails", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> pendingSwapDetails(@RequestParam("bpid")String bpid,@RequestParam("accountNumber")String accountNumber,@RequestParam("companyCode")String companyCode,
			@RequestParam("brandName")String brandName,@RequestParam("contractID")String contractID,@RequestParam("esid")String esid,@RequestParam("language")String language){
	
		//Response response =null;
		PendingSwapResponse pendingSwapResponse = swapBO.pendingSwapDetails(bpid, accountNumber, companyCode,
				 brandName, contractID, esid,language, httpRequest.getSession(true).getId());
		//response =Response.status(200).entity(pendingSwapResponse).build();
		return new ResponseEntity<GenericResponse>(pendingSwapResponse, HttpStatus.OK);
	}
	
	
	
}
