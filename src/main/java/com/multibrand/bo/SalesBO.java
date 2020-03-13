package com.multibrand.bo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.SalesEsidCalendarRequest;
import com.multibrand.dto.response.IdentityResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.SalesEsidInfoTdspCalendarResponse;


@Component
public class SalesBO extends OeBoHelper implements Constants {
	
	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	@Autowired
	private OEBO oeBO;
	
	@Context
	private HttpServletRequest httpRequest;

	
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
	
	
	/**
	 * @author Kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SalesEsidInfoTdspCalendarResponse getSalesESIDAndCalendarDates(SalesEsidCalendarRequest salesEsidInfoTdspResponse, HttpServletRequest httpRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEsidInfoTdspCalendarResponse salesEsidInfoTdspCalendarResponse = new SalesEsidInfoTdspCalendarResponse();
		String bpMatchFlag= null;
		try {
			if(StringUtils.isNotEmpty(salesEsidInfoTdspResponse.getGuid())){
				 serviceLoationResponse=oeBO.getEnrollmentData(salesEsidInfoTdspResponse.getTrackingId(),salesEsidInfoTdspResponse.getGuid() );
			}else{
				 serviceLoationResponse=oeBO.getEnrollmentData(salesEsidInfoTdspResponse.getTrackingId() );
			}	
			
			
			if((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(),BPSD))
				&& (!StringUtils.equalsIgnoreCase(salesEsidInfoTdspResponse.getPastServiceMatchedFlag(),"Y"))){
				bpMatchFlag=BPSD;
			}
			EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
					.getESIDAndCalendarDates(salesEsidInfoTdspResponse.getCompanyCode(),
							salesEsidInfoTdspResponse.getAffiliateId(),
							salesEsidInfoTdspResponse.getBrandId(),
							serviceLoationResponse.getServStreetNum(),
							serviceLoationResponse.getServStreetName(),
							serviceLoationResponse.getServStreetAptNum(),
							serviceLoationResponse.getServZipCode(),
							serviceLoationResponse.getTdspCode(),
							serviceLoationResponse.getServiceRequestTypeCode(),
							salesEsidInfoTdspResponse.getTrackingId(),
							bpMatchFlag,
							salesEsidInfoTdspResponse.getLanguageCode(),
							serviceLoationResponse.getEsid(),
							httpRequest.getSession(true).getId(),
							serviceLoationResponse.getErrorCode());
			
					    
			    BeanUtils.copyProperties(esidInfoTdspResponse, salesEsidInfoTdspCalendarResponse);	
			    
			
				
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch"+ e.getMessage());
			throw e;
		}
		
		return salesEsidInfoTdspCalendarResponse;
	}

}
