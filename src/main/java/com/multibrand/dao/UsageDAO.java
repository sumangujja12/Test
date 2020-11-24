package com.multibrand.dao;

import java.util.List;

import com.multibrand.vo.request.DailyWeeklyUsageRequestVO;
import com.multibrand.vo.request.MonthlyUsageRequestVO;
import com.multibrand.vo.request.SmartMeterUsageRequestVO;
import com.multibrand.vo.request.UsageRequestVO;
import com.multibrand.vo.request.WeeklyUsageRequestVO;
import com.multibrand.vo.response.DailyHourlyUsageResponseVO;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.HourlyUsage;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.gmd.AllTimePriceResponseVO;
import com.multibrand.vo.response.gmd.DailyHourlyPriceResponseVO;
import com.multibrand.vo.response.gmd.GMDZoneByEsiIdResponseVO;

public interface UsageDAO
{
	public DailyHourlyUsageResponseVO getHourlyUsageFromDB(UsageRequestVO request, String companyCode, String sessionId);
	public DailyHourlyUsageResponseVO getGMDHourlyUsageFromDB(UsageRequestVO request, String companyCode, String sessionId);
	public MonthlyUsageResponseList getMonthlyUsageDetails(MonthlyUsageRequestVO monthlyUsageReq, String companyCode, String sessionId);
	public WeeklyUsageResponseList getWeeklyUsageDetails(WeeklyUsageRequestVO weekUsageReq, String companyCode, String sessionId);
	public DailyWeeklyUsageResponseList getDailyWeeklyUsageDetails(DailyWeeklyUsageRequestVO dailyWeeklyUsageReq, String companyCode, String sessionId);
	public SmartMeterUsageResponseList getSmartMeterUsageHistory(SmartMeterUsageRequestVO requestVO, String companyCode, String sessionId);
	public DailyHourlyPriceResponseVO getGMDPriceFromDB(UsageRequestVO request, String companyCode, String sessionId);
	public GMDZoneByEsiIdResponseVO getZoneInformFromDB(UsageRequestVO request, String companyCode, String sessionId);
	public List<HourlyUsage> getWeeklyUsageByHuorlyDetails(String esiId, String contractId, String fromDate , String toDate);
	public AllTimePriceResponseVO getAllTimePriceFromDB(UsageRequestVO request, String sessionId, String companyCode);
}
