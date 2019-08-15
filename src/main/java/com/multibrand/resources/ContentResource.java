package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.ContentBO;
import com.multibrand.dto.request.ComponentByItemIdRequest;
import com.multibrand.dto.request.ComponentByItemIdsRequest;
import com.multibrand.dto.request.ContentOfferRequest;
import com.multibrand.dto.request.ContentUserPrefRequest;
import com.multibrand.dto.request.MessageContentRequest;
import com.multibrand.service.ContentService;
import com.multibrand.util.Constants;
/*
 * @Author bbachin1
 */
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;

@RestController
public class ContentResource implements Constants{
	
	private static Logger logger = LogManager.getLogger(ContentResource.class);
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ContentBO contentServiceBO;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	@PostMapping(value="/personalize/offer", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String readPersonalizedOffer(ContentOfferRequest request) {
		String offerResponse = "";
		try{
			request.validateRequest();
			offerResponse = contentService.getOffer(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER:::", ex);
		}
		//Response response = Response.status(Response.Status.OK).entity(offerResponse).build();
		return offerResponse;
	} 
	
	@PostMapping(value="/personalize/userprefernce/update", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String updateUserPreference(ContentUserPrefRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.updateUserPreference(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		//Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return Updresponse;
	}
	
	@PostMapping(value="/personalize/component/itemid", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String readComponentByItemId(ComponentByItemIdRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readComponentByItemId(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		//Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return Updresponse;
	} 
	
	@PostMapping(value="/personalize/component/itemids", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String readComponentByItemIds(ComponentByItemIdsRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readComponentByItemIds(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		//Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return Updresponse;
	}
	
	
	@PostMapping(value="/personalize/gme/messagecontent", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String readMessageContent(MessageContentRequest request) {
		String Updresponse = "";
		try{
			request.validateRequest();
			Updresponse = contentService.readMessageContent(request);
		}catch(Exception ex){
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE:::", ex);
			//Updresponse.setErrorMessage(NRGRestUtil.getMessageFromException(ex));
			//Updresponse.setErrorCode("3");
		}
		//Response response = Response.status(Response.Status.OK).entity(Updresponse).build();
		return Updresponse;
	}
	
	/**
	 * @author SMarimuthu
	 * @param request
	 * @return
	 */
	@PostMapping(value="/personalize/gme/getPlanOffers", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ContractOfferPlanContentResponse getMultiBrandPlanOffers(ContractInfoRequest request){
		
		//Response response = null;
		logger.info("Start-[ContentResource-getMultiBrandPlanOffers]");
		ContractOfferPlanContentResponse getContractInfoResponse = contentServiceBO.getMultiBrandPlanOffers(request, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(getContractInfoResponse).build();
		logger.info("END-[ContentResource-getMultiBrandPlanOffers]");
		return getContractInfoResponse;
	}
	
}
