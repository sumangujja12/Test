package com.multibrand.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.service.ContentService;
import com.multibrand.vo.response.contentResponse.MobileContentResponse;

/**
 * @author asha
 * 
 */

@Component("contentBO")
public class ContentBO {
	
	
	@Autowired
	private ContentService contentService;
	
	public MobileContentResponse getContent() {
		return contentService.getMobileContentData();
	}

}
