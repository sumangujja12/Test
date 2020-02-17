package com.multibrand.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.multibrand.dto.request.CheckPendingServiceRequest;
import com.multibrand.dto.request.GetEsiidRequest;
import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.dto.response.EsiidResponse;
import com.multibrand.domain.GetEsiidResponse;



public interface AddressDAOIF{
	List<Map<String, Object>> getCityStateFromZip(String zipCode) ;
	
	List<CheckPendingServiceResponse> checkPendingRequest(CheckPendingServiceRequest pendingRequestCheckDTO);
	
	int getESIDCount(String esidNumber);
	
	List<Map<String,Object>> getESIDTypeList(String esidNumber);
	
	public EsiidResponse getESIDDetails(GetEsiidRequest getEsiidRequest) throws SQLException,Exception;
}





