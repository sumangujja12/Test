package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.dao.WeeklySummaryEmailDAO;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;

@Component
public class WeeklySummaryEmailHelper {


	@Resource(name = "weeklySummaryEmailDAO")
	private WeeklySummaryEmailDAO weeklySummaryEmailDAOImpl;

	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public WeeklySummaryEmailResponse gethistoricalWse(String contractAccountNumber, String companyCode){
		WeeklySummaryEmailResponse response =null;
		logger.info("Start WeeklySummaryEmailHelper.gethistoricalWse");
		
		response = weeklySummaryEmailDAOImpl.getHistoricalWse(contractAccountNumber, companyCode);
			
		logger.info("End WeeklySummaryEmailHelper.gethistoricalWse");
		return response;
	}
}
