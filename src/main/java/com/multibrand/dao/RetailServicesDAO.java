package com.multibrand.dao;

import org.springframework.stereotype.Component;

import com.multibrand.dto.response.RetailServicesResponse;

@Component
public interface RetailServicesDAO {
	
	public RetailServicesResponse readHouseAgeAndHHIncome(String addressIdorESID);
	
}
