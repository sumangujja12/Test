package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.dao.WebHookDAOIF;
import com.multibrand.dto.request.WebHookRequest;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.WebHookResponse;

@Component
public class WebHookHelper {

	@Resource(name="webhookdao")
	private WebHookDAOIF webHookDAOImpl;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	public WebHookResponse addWebHookData(WebHookRequest request){
		WebHookResponse response =null;
		logger.info("Start WebHookHelper addWebHookData");
		
		response = CommonUtil.validateWebHookRequest(request);
		
		if(StringUtils.isNotBlank(response.getResultcode())){
			logger.info("WebHook Request validation failed  ");
			return response;
		}else{
			response = webHookDAOImpl.addWebHookData(request);
		}
		
		logger.info("End WebHookHelper addWebHookData");
		return response;
	}
	
}
