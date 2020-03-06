package com.multibrand.dao;

import com.multibrand.dto.request.WebHookRequest;
import com.multibrand.vo.response.WebHookResponse;

public interface WebHookDAOIF {
	public WebHookResponse addWebHookData(WebHookRequest request);
}





