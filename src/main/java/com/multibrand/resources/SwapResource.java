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
import org.springframework.stereotype.Component;

import com.multibrand.bo.SwapBO;
import com.multibrand.vo.request.SubmitSwapRequest;
import com.multibrand.vo.response.SubmitSwapResponse;
import com.multibrand.vo.response.swapResponse.PendingSwapResponse;
import com.multibrand.vo.response.swapResponse.RolloverPlanResponse;


/** This Resource is to handle all the Billing Related API calls.
 * 
 * @author Kdeshmu1
 */
@Component
@Path("swapResource")
public class SwapResource {
	
	/** Object of SwapBO class. */
	@Autowired
	private SwapBO swapBO;
	
	@Context 
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
	@POST
	@Path("submitSwap")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response submitSwap(@FormParam("campaignCode") String campaignCode, @FormParam("offerCode") String offerCode,
			@FormParam("contractId") String contractId,@FormParam("esid") String esid,
			@FormParam("currentContractEndDate") String currentContractEndDate,@FormParam("languageCode") String languageCode,@FormParam("companyCode") String companyCode,
			@FormParam("accountNumber") String accountNumber,@FormParam("checkDigit") String checkDigit, @FormParam("bpNumber") String bpNumber, @FormParam("caName") String caName, @FormParam("servStreetNum") String servStreetNum,@FormParam("servStreetName") String servStreetName,@FormParam("servStreetAptNum") String servStreetAptNum,
			@FormParam("servCity") String servCity,@FormParam("servState") String servState,@FormParam("servZipCode") String servZipCode,
			@FormParam("billStreetNum") String billStreetNum,@FormParam("billStreetName") String billStreetName,@FormParam("billStreetAptNum") String billStreetAptNum,
			@FormParam("billAddrPOBox") String billAddrPOBox,@FormParam("billCity") String billCity,@FormParam("billState") String billState,
			@FormParam("billZipCode") String billZipCode,@FormParam("newContractBegins") String newContractBegins, @FormParam("newContractEnds") String newContractEnds, @FormParam("planName") String planName,
			@FormParam("marketingText") String marketingText, @FormParam("incentives") String incentives, @FormParam("avgPrice") String avgPrice,
			@FormParam("energyCharge") String energyCharge, @FormParam("baseCharge") String baseCharge, @FormParam("tdspCharge") String tdspCharge, @FormParam("planType") String planType,
			@FormParam("contractTerm") String contractTerm,@FormParam("cancelFee") String cancelFee,
			@FormParam("eflURL") String eflURL, @FormParam("eflSmartCode") String eflSmartCode, @FormParam("tosURL") String tosURL, @FormParam("tosSmartCode") String tosSmartCode,
			@FormParam("yraacURL") String yraacURL, @FormParam("yraacSmartCode") String yraacSmartCode, @FormParam("disclaimer") String disclaimer, @FormParam("toEmail") String toEmail, @FormParam("productContent") String productContent, @FormParam("promoCode") String promoCode,@FormParam("brandName") String brandName, @FormParam("offerDate") String offerDate, @FormParam("offerTime") String offerTime,
			@FormParam("clientSource") String clientSource){
		logger.debug("START SwapResource.submitSwap :: START");
		Response response = null;
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
		SubmitSwapResponse submitSwapResponse = swapBO.submitSwap(swapRequest,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(submitSwapResponse).build();
		logger.debug("End SwapResource.submitSwap :: END");	
		return response;
		
	}
	
	
	/**
	 * This service is to get RollOver Plan Details
	 * @author mshukla1
	 * @param contractId
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("getRolloverPlanDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getRolloverPlanDetails(@FormParam("contractId")String contractId,@FormParam("companyCode")String companyCode){
		Response response =null;
		RolloverPlanResponse rollOverPlanResponse = swapBO.getRollovePlanDetails(contractId, companyCode, httpRequest.getSession(true).getId());
		response =Response.status(200).entity(rollOverPlanResponse).build();
		return response;
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
	@POST
	@Path("pendingSwapDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response pendingSwapDetails(@FormParam("bpid")String bpid,@FormParam("accountNumber")String accountNumber,@FormParam("companyCode")String companyCode,
			@FormParam("brandName")String brandName,@FormParam("contractID")String contractID,@FormParam("esid")String esid,@FormParam("language")String language){
	
		Response response =null;
		PendingSwapResponse pendingSwapResponse = swapBO.pendingSwapDetails(bpid, accountNumber, companyCode,
				 brandName, contractID, esid,language, httpRequest.getSession(true).getId());
		response =Response.status(200).entity(pendingSwapResponse).build();
		return response;
	}
	
	
	
}
