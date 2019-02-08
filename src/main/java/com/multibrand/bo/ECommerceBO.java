package com.multibrand.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.service.ECommerceService;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.response.GoogleProductSetResponse;

/**
 * 
 * @author HChoudhary
 */
@Component
public class ECommerceBO extends OeBoHelper{

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	@Autowired
	ECommerceService ecommerceService;
	
	
	public GoogleProductSetResponse getGoogleProductSet(){
		
		GoogleProductSetResponse response = new GoogleProductSetResponse();
		try{
			response = ecommerceService.getGoogleProductSet();
		}catch(Exception ex){
			logger.error("Exception in getting google product set: ", ex);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);			
		}
		
		return response;
	}
}
