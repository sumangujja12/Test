package com.multibrand.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.multibrand.dto.request.CheckPendingServiceRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.domain.GetEsiidResponse;



public interface AddressDAOIF{
	List<Map<String, Object>> getCityStateFromZip(String zipCode) ;
	
	List<CheckPendingServiceResponse> checkPendingRequest(CheckPendingServiceRequest pendingRequestCheckDTO);
	
	int getESIDCount(String esidNumber);
	
	List<Map<String,Object>> getESIDTypeList(String esidNumber);
	
	public EsidResponse getESIDDetails(EsidRequest esidRequest) throws SQLException,Exception;
}





