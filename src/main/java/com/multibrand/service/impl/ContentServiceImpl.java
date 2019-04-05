package com.multibrand.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.multibrand.dao.ContentDao;
import com.multibrand.dto.ComponentDTO;
import com.multibrand.dto.MobileContentDto;
import com.multibrand.dto.request.BaseContentRequest;
import com.multibrand.dto.request.ComponentByItemIdRequest;
import com.multibrand.dto.request.ComponentByItemIdsRequest;
import com.multibrand.dto.request.ContentOfferRequest;
import com.multibrand.dto.request.ContentUserPrefRequest;
import com.multibrand.dto.request.MessageContentRequest;
import com.multibrand.service.BaseRestService;
import com.multibrand.service.ContentService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.contentResponse.MobileContent;
import com.multibrand.vo.response.contentResponse.MobileContentItem;
import com.multibrand.vo.response.contentResponse.MobileContentResponse;

/**
 * @author bbachin1
 * 
 */
@Component
public class ContentServiceImpl extends BaseRestService implements ContentService, Constants {

	@Autowired
	ContentDao contentDao;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Override
	public String getOffer(BaseContentRequest request) {

		String returnResp = "";
		try {
			ContentOfferRequest req = (ContentOfferRequest) request;
			Gson gson = new Gson();
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			returnResp = createRestTemplateAndCallService(gson.toJson(req), GET_OFFER_REST_URL, "PSZ");// PSZ =
																										// Personalization
																										// API
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info("Execution Time for GET_OFFER_REST_URL:" + elapsedTimeDisp);

		} catch (Exception ex) {
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER::::", ex);
			return CommonUtil.getErrorJson(ex, "002");
		}
		return returnResp;
	}

	@Override
	public String updateUserPreference(BaseContentRequest request) {

		String returnResp = "";
		try {
			ContentUserPrefRequest req = (ContentUserPrefRequest) request;
			Gson gson = new Gson();
			returnResp = createRestTemplateAndCallService(gson.toJson(req), USER_UPDATE_PREF_REST_URL, "PSZ");// PSZ =
																												// Personalization
																												// API
		} catch (Exception ex) {
			logger.error("ERROR OCCURED WHILE UPDATING THE USER PREFERENCE::::", ex);
			returnResp = "{}";
		}
		return returnResp;
	}

	@Override
	public String readComponentByItemId(BaseContentRequest request) {

		String returnResp = "";
		try {
			ComponentByItemIdRequest req = (ComponentByItemIdRequest) request;
			Gson gson = new Gson();
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			returnResp = createRestTemplateAndCallService(gson.toJson(req), READ_COMPONENT_BY_ITEMID, "SDL");
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info("Execution Time for readComponentByItemId:" + elapsedTimeDisp);
		} catch (Exception ex) {
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER::::", ex);
			returnResp = "{}";
		}
		return returnResp;
	}

	@Override
	public String readComponentByItemIds(BaseContentRequest request) {

		String returnResp = "";
		try {
			ComponentByItemIdsRequest req = (ComponentByItemIdsRequest) request;
			Gson gson = new Gson();
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			returnResp = createRestTemplateAndCallService(gson.toJson(req), READ_COMPONENT_BY_ITEMIDS, "SDL");// SDL =
																												// Content
																												// API
			returnResp = replaceDynamicContentForServices(returnResp, req);
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info("Execution Time for readComponentByItemIds:" + elapsedTimeDisp);
		} catch (Exception ex) {
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER::::", ex);
			returnResp = "{}";
		}
		return returnResp;
	}

	private String replaceDynamicContentForServices(String returnResp, ComponentByItemIdsRequest req) throws Exception {

		String returnText = "";
		returnText = returnResp;
		if (returnResp.indexOf("%%") != -1) { // if dynamic values contains in
												// the html insert the values
												// from the request

			for (ComponentDTO arr : req.getComponentArray()) {
				if (logger.isDebugEnabled()) {
					logger.debug(arr);
				}
				if (null != arr.getDynamicFields()) {
					for (String dynamicFields : arr.getDynamicFields()) {
						if (logger.isDebugEnabled()) {
							logger.debug("dynamicFields" + dynamicFields);
						}
						String[] stringArray = StringUtils.split(dynamicFields, ':');
						logger.info("key:" + stringArray[0] + " value:" + stringArray[1]);
						returnText = StringUtils.replace(returnText, "%%" + stringArray[0] + "%%", stringArray[1]);
						if (logger.isDebugEnabled()) {
							logger.debug("returnText after replacement:" + returnText);
						}
					}
				}
			}

		} else {

			logger.info("No need to dynamically replace string returning now");
		}
		// logger.info(req.getComponentArray().get(0).getDynamicFields().get(0));
		if (logger.isDebugEnabled()) {
			logger.debug("returnText: " + returnText);
		}
		return returnText;
	}

	// WIP
	@Override
	public String readMessageContent(BaseContentRequest request) {

		String returnResp = "";
		try {
			MessageContentRequest req = (MessageContentRequest) request;
			Gson gson = new Gson();
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			returnResp = createRestTemplateAndCallService(gson.toJson(req), READ_MESSAGE_CONTENT, "SDL");// SDL =
																											// Content
																											// API
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info("Execution Time for readMessageContent:" + elapsedTimeDisp);
		} catch (Exception ex) {
			logger.error("ERROR OCCURED WHILE GETTING THE OFFER::::", ex);
			returnResp = "{}";
		}
		return returnResp;
	}
	
}
