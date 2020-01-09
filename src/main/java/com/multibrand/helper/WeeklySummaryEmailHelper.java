package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.WeeklySummaryEmailDAO;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.request.WeeklySummaryEmailRequest;
import com.multibrand.vo.response.WeeklySummaryEmailResponse;

@Component
public class WeeklySummaryEmailHelper implements Constants{


	@Resource(name = "weeklySummaryEmailDAO")
	private WeeklySummaryEmailDAO weeklySummaryEmailDAOImpl;

	@Autowired
	protected EnvMessageReader envMessageReader;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	public WeeklySummaryEmailResponse gethistoricalWse(WeeklySummaryEmailRequest request){
		
		logger.info("Start WeeklySummaryEmailHelper.gethistoricalWse");
		WeeklySummaryEmailResponse response = null;
		int wseReportWeeks = Constants.DEFAULT_WSE_REPORT_WEEKS;
		
		try {
			
			//If a configured value exists for WSE Report weeks, it will pickup from environment properties file else defaults to 12
			if(null != envMessageReader){
				 if(StringUtils.isNotBlank(envMessageReader.getMessage(WSE_REPORT_WEEKS_FROM_ENV_PROP))
						 && getValueAsInteger(envMessageReader.getMessage(WSE_REPORT_WEEKS_FROM_ENV_PROP)) > 0) {
					 wseReportWeeks = getValueAsInteger(envMessageReader.getMessage(WSE_REPORT_WEEKS_FROM_ENV_PROP));
				 }
			}
		} catch (Exception ex) {
			logger.error("Error in getting configured invalid login count, thus defaulted its value to ["+wseReportWeeks+"]. Error:" + ex.getLocalizedMessage());
		}
		
		int wseReportTotalNumOfDays = wseReportWeeks * 7;
		request.setWseReportTotaldays(wseReportTotalNumOfDays);		
		response = weeklySummaryEmailDAOImpl.getHistoricalWse(request);
			
		logger.info("End WeeklySummaryEmailHelper.gethistoricalWse");
		return response;
	}
	
	/**
	 * Converts a String value to an integer value 
	 * @param valueToBeReturnAsInteger
	 * @return
	 */
	private int getValueAsInteger(String valueToBeReturnAsInteger) {
		
		int defaultValue = 0;
		
		try {
			if(null != valueToBeReturnAsInteger && StringUtils.isNotBlank(valueToBeReturnAsInteger)) {
				defaultValue = Integer.parseInt(valueToBeReturnAsInteger);
			}
		} catch (Exception ex) {
			logger.error("Error in parsing the String value to Integer for[" + valueToBeReturnAsInteger + "]");
		}
		
		return defaultValue;
	}
}
