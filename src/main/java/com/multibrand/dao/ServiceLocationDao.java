package com.multibrand.dao;

import java.util.List;
import java.util.Map;

import com.multibrand.dto.request.AddServiceLocationRequest;
import com.multibrand.dto.request.UpdateServiceLocationRequest;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.exception.OEException;

public interface ServiceLocationDao {

	public List<Map<String, Object>> getPendingRequestDetails(
			String trackingNumber) throws OEException;

	public List<Map<String, Object>> getPreviousProviderNameFromCode(
			String repCode) throws OEException;

	public String addServiceLocation(AddServiceLocationRequest request);

	public String updateServiceLocation(UpdateServiceLocationRequest request);

	public ServiceLocationResponse getServiceLocation(String trackingId);
	public ServiceLocationResponse getEnrollmentData(String trackingId,String guid);

}
