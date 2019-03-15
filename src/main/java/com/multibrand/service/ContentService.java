package com.multibrand.service;

import org.springframework.stereotype.Component;

import com.multibrand.dto.request.BaseContentRequest;
import com.multibrand.vo.response.contentResponse.MobileContentResponse;

/**
 * @author bbachin1
 * 
 */
@Component
public interface ContentService {
	
	public String getOffer(BaseContentRequest Request);
	
	public String updateUserPreference(BaseContentRequest Request);
	
	public String readComponentByItemId(BaseContentRequest Request);
	
	public String readComponentByItemIds(BaseContentRequest Request);

	public String readMessageContent(BaseContentRequest Request);

}
