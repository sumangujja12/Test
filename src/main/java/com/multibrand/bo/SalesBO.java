package com.multibrand.bo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.response.IdentityResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.response.GenericResponse;

import jodd.net.HttpStatus;

import javax.ws.rs.core.Response;

@Component
public class SalesBO extends OeBoHelper implements Constants {
	
	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	@Autowired
	private OEBO oeBO;
	
	public Response performPosidAndBpMatch(IdentityRequest request) throws Exception {
		PerformPosIdAndBpMatchRequest performPosidAndBPMatchRequest = new PerformPosIdAndBpMatchRequest();
		Response response = null;
		try {
			BeanUtils.copyProperties(request, performPosidAndBPMatchRequest);			
			
			response = oeBO.performPosidAndBpMatch(performPosidAndBPMatchRequest);
			
			if(response.getStatus() == Response.Status.OK.getStatusCode()){
			
			    GenericResponse affiliateResponse = (GenericResponse) response.getEntity();
			    
			    IdentityResponse identityResponse = new IdentityResponse();
			    
			    BeanUtils.copyProperties(affiliateResponse, identityResponse);	
			    
			    response = Response.status(200).entity(identityResponse).build();
			}
			
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch", e);
			throw e;
		}
		
		return response;
	}

}
