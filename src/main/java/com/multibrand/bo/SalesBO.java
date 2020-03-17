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
import java.util.Arrays;
import java.util.LinkedHashSet;


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
		    GenericResponse affiliateResponse = (GenericResponse) response.getEntity();
			IdentityResponse identityResponse = new IdentityResponse();
			BeanUtils.copyProperties(affiliateResponse, identityResponse);	
			response = Response.status(identityResponse.getHttpStatus()).entity(identityResponse).build();	
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
	public Response getSalesESIDAndCalendarDates(SalesEsidCalendarRequest salesEsidCalendarRequest, HttpServletRequest httpRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEsidInfoTdspCalendarResponse salesEsidInfoTdspCalendarResponse = new SalesEsidInfoTdspCalendarResponse();
		String bpMatchFlag= null;
		String error="";
		LinkedHashSet<String> serviceLocationResponseErrorList = new LinkedHashSet<>();
		Response response = null;
		try{
			serviceLoationResponse=oeBO.getEnrollmentData(salesEsidCalendarRequest.getTrackingId(),salesEsidCalendarRequest.getGuid() );
			if (null!= serviceLoationResponse){
				if((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(),BPSD))
					&& (StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(),"Y"))){
					//bpMatchFlag=BPSD;
					if(StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())){
						String[] errorCdArray =serviceLoationResponse.getErrorCdlist().split("\\|");
					
						serviceLocationResponseErrorList = new LinkedHashSet<>(Arrays.asList(errorCdArray));
						serviceLocationResponseErrorList.remove(BPSD);
					}
						
						boolean errorCode = oeBO.updateErrorCodeinSLA(salesEsidCalendarRequest.getTrackingId(),salesEsidCalendarRequest.getGuid(),error,StringUtils.join(serviceLocationResponseErrorList,SYMBOL_PIPE));
						
				}else if((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(),BPSD))
						&& (!StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(),"Y"))){
					bpMatchFlag=BPSD;
				}
				EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
						.getESIDAndCalendarDates(salesEsidCalendarRequest.getCompanyCode(),
								salesEsidCalendarRequest.getAffiliateId(),
								salesEsidCalendarRequest.getBrandId(),
								serviceLoationResponse.getServStreetNum(),
								serviceLoationResponse.getServStreetName(),
								serviceLoationResponse.getServStreetAptNum(),
								serviceLoationResponse.getServZipCode(),
								serviceLoationResponse.getTdspCode(),
								serviceLoationResponse.getServiceRequestTypeCode(),
								salesEsidCalendarRequest.getTrackingId(),
								bpMatchFlag,
								salesEsidCalendarRequest.getLanguageCode(),
								serviceLoationResponse.getEsid(),
								httpRequest.getSession(true).getId(),
								serviceLoationResponse.getErrorCode());
				
					    
			    BeanUtils.copyProperties(esidInfoTdspResponse, salesEsidInfoTdspCalendarResponse);	
			    
			}else{
				//These values need to set after discussion
				salesEsidInfoTdspCalendarResponse.setStatusCode(STATUS_CODE_CONTINUE);
				salesEsidInfoTdspCalendarResponse.setMessageCode(EMPTY);
				salesEsidInfoTdspCalendarResponse.setMessageText(EMPTY);
				salesEsidInfoTdspCalendarResponse.setErrorDescription("No Data in SLA Table");
			}
			response = Response.status(salesEsidInfoTdspCalendarResponse.getHttpStatus()).entity(salesEsidInfoTdspCalendarResponse).build();	
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch"+ e.getMessage());
			throw e;
		}
		
		return response;
	}
}
