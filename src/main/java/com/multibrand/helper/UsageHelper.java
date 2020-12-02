package com.multibrand.helper;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.dao.UsageDAO;
import com.multibrand.vo.request.DailyWeeklyUsageRequestVO;
import com.multibrand.vo.request.MonthlyUsageRequestVO;
import com.multibrand.vo.request.SmartMeterUsageRequestVO;
import com.multibrand.vo.request.UsageRequestVO;
import com.multibrand.vo.request.WeeklyUsageRequestVO;
import com.multibrand.vo.response.DailyHourlyUsageResponseVO;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.gmd.AllTimePriceResponseVO;
import com.multibrand.vo.response.gmd.DailyHourlyPriceResponseVO;
import com.multibrand.vo.response.gmd.GMDZoneByEsiIdResponseVO;
import java.util.List;
import com.multibrand.vo.response.HourlyUsage;

@Component
public class UsageHelper
{
	@Resource(name = "usageDao")
	private UsageDAO usageDAOImpl;
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public DailyHourlyUsageResponseVO getHourlyUsageFromDB(UsageRequestVO request,String sessionId, String companyCode)
	{
		logger.info(" START getHourlyUsageFromDB Method");
		return usageDAOImpl.getHourlyUsageFromDB(request,sessionId, companyCode);
	}
	
	public DailyHourlyUsageResponseVO getGMDHourlyUsageFromDB(UsageRequestVO request,String sessionId, String companyCode)
	{
		logger.info(" START getGMDHourlyUsageFromDB Method");
		return usageDAOImpl.getGMDHourlyUsageFromDB(request,sessionId, companyCode);
	}	

	/**
	 * This will call the Usage DAO for the Weekly Usage Details
	 * @param weeklyUsageReq
	 * @return
	 */
	public WeeklyUsageResponseList getWeeklyUsageDetails(
			WeeklyUsageRequestVO weeklyUsageReq, String companyCode, String sessionId)
	{
		logger.info(" START getWeeklyUsageDetails Method");
		return usageDAOImpl.getWeeklyUsageDetails(weeklyUsageReq, companyCode, sessionId);
	}
	
	/**
	 * This will call the Usage DAO for the Monthly Usage Details
	 * @param monthlyUsageReq
	 * @return
	 */
	public MonthlyUsageResponseList getMonthlyUsageDetails(
			MonthlyUsageRequestVO monthlyUsageReq, String companyCode, String sessionId)
	{
		logger.info(" START getMonthlyUsageDetails Method");
		return usageDAOImpl.getMonthlyUsageDetails(monthlyUsageReq, companyCode, sessionId);
	}
	
	/**
	 * This will call the Usage DAO for the Daily Weekly Usage Details
	 * @param monthlyUsageReq
	 * @return
	 */
	public DailyWeeklyUsageResponseList getDailyWeeklyUsageDetails(
			DailyWeeklyUsageRequestVO dailyWeeklyUsageReq, String companyCode, String sessionId)
	{
		logger.info(" START getDailyWeeklyUsageDetails Method");
		return usageDAOImpl.getDailyWeeklyUsageDetails(dailyWeeklyUsageReq, companyCode, sessionId);
	}
	
	
	public SmartMeterUsageResponseList getSmartMeterUsageHistory(
			SmartMeterUsageRequestVO requestVO, String companyCode, String sessionId)
	{
		logger.info(" START getDailyWeeklyUsageDetails Method");
		return usageDAOImpl.getSmartMeterUsageHistory(requestVO, companyCode, sessionId);
	}
	
	/**
	 * This will call the Usage DAO for the getZoneIdByEsiId
	 * @param request
	 * @return
	 */
	public GMDZoneByEsiIdResponseVO getZoneIdByEsiId(
			UsageRequestVO request, String companyCode, String sessionId) {
		return usageDAOImpl.getZoneInformFromDB(request, companyCode, sessionId);
	}
	
	public DailyHourlyPriceResponseVO getGMDPriceFromDB(UsageRequestVO request,String sessionId, String companyCode) {
		return usageDAOImpl.getGMDPriceFromDB(request,sessionId, companyCode);
	}	
	
    public List<HourlyUsage> getWeeklyUsageByHuorlyDetails(String esiId, String contractId, String fromDate , String toDate)
    {
        logger.info(" START getWeeklyUsageByHuorlyDetails Method");
        return usageDAOImpl.getWeeklyUsageByHuorlyDetails(esiId, contractId, fromDate , toDate);
    }
	
	public AllTimePriceResponseVO getAllTimePriceFromDB(UsageRequestVO request,String sessionId, String companyCode) {
		return usageDAOImpl.getAllTimePriceFromDB(request,sessionId, companyCode);
	}	
}