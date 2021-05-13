package com.multibrand.dao.impl;  



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.UsageDAO;
import com.multibrand.dao.mapper.DailyUsageRowMapper;
import com.multibrand.dao.mapper.DailyWeeklyUsageRowMapper;
import com.multibrand.dao.mapper.GMDAllTimePriceRowMapper;
import com.multibrand.dao.mapper.GMDHourlyPriceRowMapper;
import com.multibrand.dao.mapper.GMDHourlyUsageRowMapper;
import com.multibrand.dao.mapper.HourlyUsageRowMapper;
import com.multibrand.dao.mapper.MonthlyUsageRowMapper;
import com.multibrand.dao.mapper.SmartMeterUsageRowMapper;
import com.multibrand.dao.mapper.WeeklyUsageRowMapper;
import com.multibrand.dao.mapper.ZoneRowMapper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.DailyWeeklyUsageRequestVO;
import com.multibrand.vo.request.MonthlyUsageRequestVO;
import com.multibrand.vo.request.SmartMeterUsageRequestVO;
import com.multibrand.vo.request.UsageRequestVO;
import com.multibrand.vo.request.WeeklyUsageRequestVO;
import com.multibrand.vo.response.DailyHourlyUsageResponseVO;
import com.multibrand.vo.response.DailyResponseVO;
import com.multibrand.vo.response.DailyWeeklyUsageResponse;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.HourlyUsage;
import com.multibrand.vo.response.MonthlyUsageResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageHistory;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponse;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.gmd.AllTimePrice;
import com.multibrand.vo.response.gmd.AllTimePriceResponseVO;
import com.multibrand.vo.response.gmd.DailyHourlyPriceResponseVO;
import com.multibrand.vo.response.gmd.GMDZoneByEsiIdResponseVO;
import com.multibrand.vo.response.gmd.HourlyPrice;
import com.multibrand.vo.response.gmd.Zone;
import oracle.jdbc.OracleTypes;


@Component("usageDao")
public class UsageDaoImpl implements UsageDAO, DBConstants
{

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	private JdbcTemplate smartJdbcTemplate;
	private JdbcTemplate smartMeterJdbcTemplate;

	@Autowired(required = true)
	public UsageDaoImpl(
			@Qualifier("smartMainJdbcTemplate") JdbcTemplate jdbcTemplate, 
			@Qualifier("smartMeterJdbcTemplate") JdbcTemplate smartMeterJdbcTemplate) {
		this.smartJdbcTemplate = jdbcTemplate;
		this.smartMeterJdbcTemplate = smartMeterJdbcTemplate;
	}

	/**
	 * This method get the hourly usage of the smart meter for predefined date.
	 * 
	 */
	@Override
	public DailyHourlyUsageResponseVO getHourlyUsageFromDB(UsageRequestVO request, String sessionId, String companyCode) {
		logger.info("Inside getHourlyUsageFromDB in UsageDaoImpl");
		long startTime = CommonUtil.getStartTime();
		
		DailyHourlyUsageResponseVO response = new DailyHourlyUsageResponseVO();
		logger.info("ca in the request is:{}",request.getContractAcctId());
		logger.info("esiid in the request is:{} ",request.getEsiId());
		logger.info("co in the request is:{}",request.getContractId());
		
		Map<String, Object> inParams = new LinkedHashMap<>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<>();
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(ZONE_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CUR_DT_IN_V,OracleTypes.DATE);
		inParamsTypeMap.put(CUR_DAY_IND_IN_V,OracleTypes.VARCHAR);
		inParamsTypeMap.put(DYHR_IND_IN_V,OracleTypes.VARCHAR);
		
       
		inParams.put(ESIID_IN_V,request.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V,request.getContractId() );
		inParams.put(CONTRACT_ACT_ID_IN_V, request.getContractAcctId());
		inParams.put(ZONE_ID_IN_V, request.getZoneId());
		if (request.getCurDtInd() != null && !request.getCurDtInd().equals("")) {
			inParams.put(CUR_DT_IN_V, CommonUtil.getSqlDate(
					request.getCurDtInd(), Constants.DT_FMT_REQUEST));
		} else {
			inParams.put(CUR_DT_IN_V, "");
		}
		inParams.put(CUR_DAY_IND_IN_V,request.getCurDayInd());
		inParams.put(DYHR_IND_IN_V,request.getDyHrInd());
		
		
		
		if (Constants.DAILY_INDICATOR.equalsIgnoreCase(request.getDyHrInd())) {
			outParamsTypeMap.put(DYHR_OUT_REC, new ResultObject(
					OracleTypes.CURSOR, new DailyUsageRowMapper()));
		} else {
			outParamsTypeMap.put(DYHR_OUT_REC, new ResultObject(
					OracleTypes.CURSOR, new HourlyUsageRowMapper()));
		}
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, WP_GET_DLY_USG_GME,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		logger.info(PROJECTEDBILL_PROC , "-{}" , elapsedTime);

		// END (TIME LOG)
		
		
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {
			
			
			if (Constants.DAILY_INDICATOR.equalsIgnoreCase(request.getDyHrInd())) {
				
				List<DailyResponseVO> storeResponseList = (List<DailyResponseVO>) storedProcResult
						.get(DYHR_OUT_REC);
				
				response.setDailyUsageList(storeResponseList);
			} else {
				List<HourlyUsage> storeResponseList = (List<HourlyUsage>) storedProcResult
						.get(DYHR_OUT_REC);
				response.setHourlyUsageList(storeResponseList);
				
			}
		}		
	
		utilityloggerHelper.logTransaction("getHourlyUsageFromDB", false,
				request, response, "", CommonUtil.getElapsedTime(startTime),
				"", sessionId, companyCode);

		logger.info("Exiting getHourlyUsageFromDB in UsageDaoImpl");
		return response;
	}
	
	/**
	 * This method get the hourly usage of the smart meter for predefined date.
	 * 
	 */
	@Override
	public DailyHourlyUsageResponseVO getGMDHourlyUsageFromDB(UsageRequestVO request, String sessionId, String companyCode) {
		logger.info("Inside getGMDHourlyUsageFromDB in UsageDaoImpl");
		long startTime = CommonUtil.getStartTime();
		
		DailyHourlyUsageResponseVO response = new DailyHourlyUsageResponseVO();
		logger.info("ca in the request is {}",request.getContractAcctId());
		logger.info("esiid in the request is {}",request.getEsiId());
		logger.info("co in the request is {}",request.getContractId());
		
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(ZONE_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CUR_DT_IN_V,OracleTypes.DATE);
		inParamsTypeMap.put(CUR_DAY_IND_IN_V,OracleTypes.VARCHAR);
		inParamsTypeMap.put(DYHR_IND_IN_V,OracleTypes.VARCHAR);
		
       
		inParams.put(ESIID_IN_V,request.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V,request.getContractId() );
		inParams.put(CONTRACT_ACT_ID_IN_V, request.getContractAcctId());
		inParams.put(ZONE_ID_IN_V, request.getZoneId());
		if (request.getCurDtInd() != null && !request.getCurDtInd().equals("")) {
			inParams.put(CUR_DT_IN_V, CommonUtil.getSqlDate(
					request.getCurDtInd(), Constants.DT_FMT_REQUEST));
		} else {
			inParams.put(CUR_DT_IN_V, "");
		}
		inParams.put(CUR_DAY_IND_IN_V,request.getCurDayInd());
		inParams.put(DYHR_IND_IN_V,request.getDyHrInd());
		
		
		
		if (Constants.DAILY_INDICATOR.equalsIgnoreCase(request.getDyHrInd())) {
			outParamsTypeMap.put(DYHR_OUT_REC, new ResultObject(
					OracleTypes.CURSOR, new DailyUsageRowMapper()));
		} else {
			outParamsTypeMap.put(DYHR_OUT_REC, new ResultObject(
					OracleTypes.CURSOR, new GMDHourlyUsageRowMapper()));
		}
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, WP_GET_15MIN_USG_COST,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(PROJECTEDBILL_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)
		
		
		logger.info("hai"+storedProcResult.get("RET_TYP_OUT_V"));
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {
			
			
			if (Constants.DAILY_INDICATOR.equalsIgnoreCase(request.getDyHrInd())) {
				
				List<DailyResponseVO> storeResponseList = (List<DailyResponseVO>) storedProcResult
						.get(DYHR_OUT_REC);
				
				
				if (storeResponseList != null) {
					response.setDailyUsageList(storeResponseList);
				}
			} else {
				List<HourlyUsage> storeResponseList = (List<HourlyUsage>) storedProcResult
						.get(DYHR_OUT_REC);
				
				
				if (storeResponseList != null) {
					response.setHourlyUsageList(storeResponseList);
					logger.info("SIZE of the List"+storeResponseList.size());
				}
				
			}
			
			
			
		}		
	
		utilityloggerHelper.logTransaction("getHourlyUsageFromDB", false,
				request, response, "", CommonUtil.getElapsedTime(startTime),
				"", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("Exiting getHourlyUsageFromDB in UsageDaoImpl");
		return response;
	}	
	
		
		@Override
		public MonthlyUsageResponseList getMonthlyUsageDetails (
			MonthlyUsageRequestVO monthlyUsageReq, String companyCode, String sessionId)
		{
		logger.info("Inside getMonthlyUsageDetails in UsageDaoImpl");
		
		long startTime = CommonUtil.getStartTime();
		
		MonthlyUsageResponse response = null;
		logger.info("Values in the request are CA is "
				+ monthlyUsageReq.getContractAccountId() + " and CO is "
				+ monthlyUsageReq.getContractId());

		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);    
		inParamsTypeMap.put(ZONE_ID, OracleTypes.VARCHAR);  
		inParamsTypeMap.put(CUR_DT_IN_V, OracleTypes.DATE);
		
		inParams.put(ESIID_IN_V, monthlyUsageReq.getEsiid());
		inParams.put(CONTRACT_ID_IN_V, monthlyUsageReq.getContractId());
		inParams.put(CONTRACT_ACT_ID_IN_V, monthlyUsageReq.getContractAccountId());
		inParams.put(ZONE_ID, monthlyUsageReq.getZoneId());
		inParams.put(CUR_DT_IN_V,CommonUtil.getSqlDate(monthlyUsageReq.getCurDate(),Constants.DT_FMT_REQUEST));
		

		outParamsTypeMap.put("mon_out_rec", new ResultObject(
				OracleTypes.CURSOR, new MonthlyUsageRowMapper()));

		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, MONTHLYUSAGE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(MONTHLYUSAGE_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)
		

		MonthlyUsageResponseList responseList = null;
		
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {

			responseList = new MonthlyUsageResponseList();
			List<MonthlyUsageResponse> storeResponseList = (List<MonthlyUsageResponse>) storedProcResult
					.get("mon_out_rec");
			logger.info("inside the loop");
			if (storeResponseList != null) {
				responseList.setMonthlyUsageResponse(storeResponseList);
				logger.info("inside the loop:{}",storeResponseList.size());
			} 
		}
		utilityloggerHelper.logTransaction("getMonthlyUsageDetails", false,
				monthlyUsageReq, responseList,
				responseList != null ? responseList.getResultDescription():"",
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(monthlyUsageReq));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("Exiting getMonthlyUsageDetails in UsageDaoImpl");
		
		return responseList;
	}

	/**
	 * This method get the weekly usage of the smart meter for predefined date.
	 * 
	 */
	@Override
	public WeeklyUsageResponseList getWeeklyUsageDetails(WeeklyUsageRequestVO weeklyUsageReq, String companyCode, String sessionId)
	{
		logger.info("Inside getWeeklyUsageDetails in UsageDaoImpl");
		
		long startTime = CommonUtil.getStartTime();
		
		WeeklyUsageResponse weeklyUsageResponse = null;
		logger.info("Values in the request are CA is "
				+ weeklyUsageReq.getContractAccountNumber() + " and CO is "
				+ weeklyUsageReq.getContractId());

		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(ZONE_ID, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CUR_DT_IN_V, OracleTypes.DATE);
		
		inParams.put(ESIID_IN_V,weeklyUsageReq.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V, weeklyUsageReq.getContractId());
		inParams.put(CONTRACT_ACT_ID_IN_V,weeklyUsageReq.getContractAccountNumber());
		inParams.put(ZONE_ID, weeklyUsageReq.getZoneId());
		inParams.put(CUR_DT_IN_V,CommonUtil.getSqlDate(weeklyUsageReq.getCurDate(),Constants.DT_FMT_REQUEST));

		
		
		outParamsTypeMap.put(WK_WSE_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new WeeklyUsageRowMapper()));
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, WEEKLYUSAGE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(WEEKLYUSAGE_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)
		
		WeeklyUsageResponseList weeklyUsageResponseList = null;
		
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {
			weeklyUsageResponseList = new WeeklyUsageResponseList();
			List<WeeklyUsageResponse> storeResponseList = (List<WeeklyUsageResponse>) storedProcResult
					.get("wk_wse_out_rec");

			if (storeResponseList != null) {
				weeklyUsageResponseList.setWeeklyUsageResponse(storeResponseList);
			}
		}
		
		
		utilityloggerHelper.logTransaction("getWeeklyUsageDetails", false,
				weeklyUsageReq, weeklyUsageResponseList,
				weeklyUsageResponseList != null ? weeklyUsageResponseList.getResultDescription():"",
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(weeklyUsageReq));
			logger.debug(XmlUtil.pojoToXML(weeklyUsageResponseList));
		}
		logger.info("Exiting getWeeklyUsageDetails in UsageDaoImpl");
		
		return weeklyUsageResponseList;
	}
	
	/**
	 * This method get the daily weekly usage of the smart meter for predefined date.
	 * 
	 */
	@Override
	public DailyWeeklyUsageResponseList getDailyWeeklyUsageDetails(DailyWeeklyUsageRequestVO dailyWeeklyUsageReq, String companyCode, String sessionId)
	{
		logger.info("Inside getDailyWeeklyUsageDetails in UsageDaoImpl");
		
		long startTime = CommonUtil.getStartTime();
		
		DailyWeeklyUsageResponse dailyWeeklyUsageResponse = null;
		logger.info("Values in the request are CA is "
				+ dailyWeeklyUsageReq.getContractAccountNumber() + " and CO is "
				+ dailyWeeklyUsageReq.getContractId());

		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(ZONE_ID, OracleTypes.VARCHAR);
		inParamsTypeMap.put(WK_DT_IN_V, OracleTypes.DATE);
		inParamsTypeMap.put(CUR_WK_IN_IN_V, OracleTypes.VARCHAR);

		
		inParams.put(ESIID_IN_V,dailyWeeklyUsageReq.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V, dailyWeeklyUsageReq.getContractId());
		inParams.put(CONTRACT_ACT_ID_IN_V,dailyWeeklyUsageReq.getContractAccountNumber());
		inParams.put(ZONE_ID, dailyWeeklyUsageReq.getZoneId());
		inParams.put(WK_DT_IN_V,CommonUtil.getSqlDate(dailyWeeklyUsageReq.getWkDtInV(),Constants.DT_FMT_REQUEST));
		inParams.put(CUR_WK_IN_IN_V, dailyWeeklyUsageReq.getCurWkInV());

		
		
		outParamsTypeMap.put(WK_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new DailyWeeklyUsageRowMapper()));
		outParamsTypeMap.put(WK_PCT_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new DailyWeeklyUsageRowMapper()));
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, DAILYWEEKLYUSAGE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(DAILYWEEKLYUSAGE_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)
		
		DailyWeeklyUsageResponseList dailyWeeklyUsageResponseList = null;
		
		if (storedProcResult != null
				&& (storedProcResult.get("RET_TYP_OUT_V") == null || storedProcResult
						.get("RET_TYP_OUT_V").equals(""))) {
			dailyWeeklyUsageResponseList = new DailyWeeklyUsageResponseList();
			List<DailyWeeklyUsageResponse> storeResponseList = (List<DailyWeeklyUsageResponse>) storedProcResult
					.get(WK_OUT_REC);
			
			if (storeResponseList != null) {
				dailyWeeklyUsageResponseList.setDailyWeeklyUsageResponse(storeResponseList);
			}
		}
		
		
		utilityloggerHelper.logTransaction("getDailyWeeklyUsageDetails", false,
				dailyWeeklyUsageReq, dailyWeeklyUsageResponseList,
				dailyWeeklyUsageResponseList != null ? dailyWeeklyUsageResponseList.getResultDescription():"",
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(dailyWeeklyUsageReq));
			logger.debug(XmlUtil.pojoToXML(dailyWeeklyUsageResponseList));
		}
		logger.info("Exiting getDailyWeeklyUsageDetails in UsageDaoImpl");
		
		return dailyWeeklyUsageResponseList;
	}
	
	/**
	 * This method fetch the data for the smart meter reader web services call.
	 */
	@Override
	public SmartMeterUsageResponseList getSmartMeterUsageHistory(
			SmartMeterUsageRequestVO requestVO, String companyCode,
			String sessionId)
	{
		logger.info("Inside getSmartMeterUsageHistory in UsageDaoImpl");
		
		long startTime = CommonUtil.getStartTime();
		 SmartMeterUsageResponseList responseList= new SmartMeterUsageResponseList();
		
		 SmartMeterPreparedStmtCreator stmtCr = new SmartMeterPreparedStmtCreator(requestVO);
		 SmartMeterPreparedStmtSetter stmtSetter = new SmartMeterPreparedStmtSetter(requestVO);
		 
		// START (TIME LOG)
		 long entryTime;
		 long elapsedTime;
		 entryTime = System.currentTimeMillis();
	    
		List<SmartMeterUsageHistory> returnList = (List<SmartMeterUsageHistory>) smartMeterJdbcTemplate
				.query(stmtCr, stmtSetter,
						new SmartMeterUsageRowMapper());
		
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info("SMARTMETERREADQUERY" + "-" + elapsedTimeDisp);
		
		if(returnList != null && returnList.size() >0) {
			responseList.setSmartMeterUsageHistoryList(returnList);
			logger.info("responseList"+responseList.getSmartMeterUsageHistoryList().size());
		}
		
		utilityloggerHelper.logTransaction("getSmartMeterUsageHistory", false,
				requestVO, responseList,
				responseList.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(requestVO));
			logger.debug(XmlUtil.pojoToXML(responseList));
		}
		logger.info("Exiting getSmartMeterUsageHistory in UsageDaoImpl");
		
		return responseList;
	}
	
	   @Override
	    public List<HourlyUsage> getWeeklyUsageByHuorlyDetails(String esiId, String contractId, String fromDate,
	            String toDate) {
	        logger.info("UsageDaoImpl-getWeeklyUsageByHuorlyDetails :: Start");
	 
	        String query = "SELECT esiid, contract_id, contract_acct_id, bus_partner, actual_day, usage_hr01, usage_hr02, usage_hr03, usage_hr04, usage_hr05, usage_hr06, usage_hr07, usage_hr08, usage_hr09, usage_hr10, usage_hr11, usage_hr12, usage_hr13, usage_hr14, usage_hr15, usage_hr16, usage_hr17, usage_hr18, usage_hr19, usage_hr20, usage_hr21, usage_hr22, usage_hr23, usage_hr24, cost_hr01, cost_hr02, cost_hr03, cost_hr04, cost_hr05, cost_hr06, cost_hr07, cost_hr08, cost_hr09, cost_hr10, cost_hr11, cost_hr12, cost_hr13, cost_hr14, cost_hr15, cost_hr16, cost_hr17, cost_hr18, cost_hr19, cost_hr20, cost_hr21, cost_hr22, cost_hr23, cost_hr24, total_usage_day day_usg, total_cost_day day_cst, day_temp_high,day_temp_low  "
	                + "FROM SMART_SYNC.wp_hr_day_ods  "
	                + "WHERE esiid = ? AND contract_id = ? AND actual_day between TO_DATE (?, 'mm/dd/YYYY') AND TO_DATE (?, 'mm/dd/YYYY')";
	 
	        Object[] args = { esiId, contractId, fromDate, toDate };
	        logger.info("UsageDaoImpl-getWeeklyUsageByHuorlyDetails :: End");
	        return smartJdbcTemplate.query(query, args, new HourlyUsageRowMapper());
	 
	    }
	
/*	*//**
	    * Method main.
	    * @param argz String[]
	 * @throws SQLException 
	    *//*
	   public static void main(String[] argz) throws SQLException
	{
		   ClassPathXmlApplicationContext file = new ClassPathXmlApplicationContext(
				"NRGREST-dao-config.xml");

		JdbcTemplate jdbcTemplate = (JdbcTemplate) file
				.getBean("smartMainJdbcTemplate");
		JdbcTemplate smartMeterJdbcTemplate = (JdbcTemplate) file
				.getBean("smartMeterJdbcTemplate");

	UsageDaoImpl iviewDAO = new UsageDaoImpl(jdbcTemplate);
	UsageRequestVO usage = new UsageRequestVO();
	usage.setContractAcctId("000000314550");
	usage.setContractId("0000775131");
	usage.setEsiId("1008901006189052307100");
	usage.setZoneId("");
	usage.setCurDayInd("");
	usage.setCurDtInd("09/12/2010");
	usage.setDyHrInd("H");
	iviewDAO.getHourlyUsageFromDB(usage, "","");
		
		UsageDaoImpl iviewDAO = new UsageDaoImpl(jdbcTemplate,smartMeterJdbcTemplate);
		SmartMeterUsageRequestVO requestVO = new SmartMeterUsageRequestVO();
		requestVO.setAccountNumber("40007333");
		requestVO.setServicePointId("10443720005499323");
		requestVO.setStartDate("01/01/2014");
		requestVO.setEndDate("01/31/2014");
		iviewDAO.getSmartMeterUsageHistory(requestVO, "", "");
		AvgTempRequestVO avgInVo = new AvgTempRequestVO();12-SEP-10
		avgInVo.setBillEndDate("20130920");
		avgInVo.setBillStartDate("20131019");
		avgInVo.setZoneId("HOUST");
		iviewDAO.getAverageTempBill(avgInVo, "");
		//billStartDate=20130920&billEndDate=20131019&zoneId=HOUST
		
		String [] str = new String [] {"siva","murugan"};
		
		
	}
*/
	
	/**
	 * This method getGMDPriceFromDB of the smart meter for predefined date.
	 * 
	 */
	@Override
	public DailyHourlyPriceResponseVO getGMDPriceFromDB(UsageRequestVO request, String sessionId, String companyCode) {
		
		logger.info("Inside getGMDPriceFromDB in UsageDaoImpl");
		long startTime = CommonUtil.getStartTime();
		
		DailyHourlyPriceResponseVO response = new DailyHourlyPriceResponseVO();

		
		Map<String, Object> inParams = new LinkedHashMap<>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<>();
		
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);
		inParamsTypeMap.put(CUR_DT_IN_V,OracleTypes.DATE);
		
       
		inParams.put(ESIID_IN_V,request.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V,"" );
		inParams.put(CONTRACT_ACT_ID_IN_V, "");
		if (request.getCurDtInd() != null && !request.getCurDtInd().equals("")) {
			inParams.put(CUR_DT_IN_V, CommonUtil.getSqlDate(
					request.getCurDtInd(), Constants.DT_FMT_REQUEST));
		} else {
			inParams.put(CUR_DT_IN_V, "");
		}
		
		
		outParamsTypeMap.put(PRICE_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new GMDHourlyPriceRowMapper()));
		
		
		
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, GMDSMART_PRICE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(GMDSMART_PRICE_PROC + "-{}" , elapsedTimeDisp);

		// END (TIME LOG)
		
		if (storedProcResult != null
				&& (storedProcResult.get(RET_TYP_OUT_V) == null || storedProcResult
						.get(RET_TYP_OUT_V).equals(""))) {
			
			List<HourlyPrice> storeResponseList = (List<HourlyPrice>)  storedProcResult
					.get(PRICE_OUT_REC);
			if (storeResponseList != null) {
				response.setHourlyPriceList(storeResponseList);
			}
						
			
		}		
	
		utilityloggerHelper.logTransaction("getGMDPriceFromDB", false,
				request, response, "", CommonUtil.getElapsedTime(startTime),
				"", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("Exiting getGMDPriceFromDB in UsageDaoImpl");
		return response;
	}	
	
	/**
	 * This method getZoneInformFromDB of the smart meter for predefined date.
	 * 
	 */
	@Override
	public GMDZoneByEsiIdResponseVO getZoneInformFromDB(UsageRequestVO request, String sessionId, String companyCode) {
		
		logger.info("Inside getZoneInformFromDB in UsageDaoImpl");
		long startTime = CommonUtil.getStartTime();
		
		GMDZoneByEsiIdResponseVO response = new GMDZoneByEsiIdResponseVO();
		logger.info("ca in the request is{} ", request.getContractAcctId());
		logger.info("esiid in the request is {}", request.getEsiId());
		logger.info("co in the request is {}", request.getContractId());
		
		Map<String, Object> inParams = new LinkedHashMap<>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<>();
		
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V,OracleTypes.VARCHAR );
				
       
		inParams.put(ESIID_IN_V,request.getEsiId() );
		
		
		outParamsTypeMap.put(ZONE_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new ZoneRowMapper()));
		
		
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, GMDSMART_ZONE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
			
		Map<String, Object> storedProcResult = storedProc.execute();
		
			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(GMDSMART_ZONE_PROC + "-{}" , elapsedTimeDisp);

		// END (TIME LOG)
		
		if (storedProcResult != null
				&& (storedProcResult.get(RET_TYP_OUT_V) == null || storedProcResult
						.get(RET_TYP_OUT_V).equals(""))) {
			
			List<Zone> storeResponseList = (List<Zone>)storedProcResult
					.get(ZONE_OUT_REC);
			if (storeResponseList != null) {
				response.setZoneList(storeResponseList);
			}
						
			
		}		
		
		utilityloggerHelper.logTransaction("getZoneInformFromDB", false,
				request, response, "", CommonUtil.getElapsedTime(startTime),
				"", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("Exiting getZoneInformFromDB in UsageDaoImpl");
		return response;
	}	
	
	/**
	 * This method getAll time price of the smart meter for given account number.
	 * 
	 */
	@Override
	public AllTimePriceResponseVO getAllTimePriceFromDB(UsageRequestVO request, String sessionId, String companyCode) {
		
		logger.info("Inside getAllTinePriceFromDB in UsageDaoImpl");
		long startTime = CommonUtil.getStartTime();
		
		AllTimePriceResponseVO response = new AllTimePriceResponseVO();
		logger.info("ca in the request is{} ", request.getContractAcctId());
		logger.info("esiid in the request is {}", request.getEsiId());
		logger.info("co in the request is {}", request.getContractId());
		
		Map<String, Object> inParams = new LinkedHashMap<>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<>();
		
		BaseStoredProcedure storedProc = null;
		
		inParamsTypeMap.put(ESIID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ID_IN_V,OracleTypes.VARCHAR );
		inParamsTypeMap.put(CONTRACT_ACT_ID_IN_V, OracleTypes.VARCHAR);		
       
		inParams.put(ESIID_IN_V,request.getEsiId() );
		inParams.put(CONTRACT_ID_IN_V,request.getContractId() );
		inParams.put(CONTRACT_ACT_ID_IN_V,request.getContractAcctId() );
		
		
		outParamsTypeMap.put(AT_PRICE_OUT_REC, new ResultObject(
				OracleTypes.CURSOR, new GMDAllTimePriceRowMapper()));
		
		
		outParamsTypeMap.put(RET_TYP_OUT_V, new ResultObject(OracleTypes.VARCHAR));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(smartJdbcTemplate, GMDSMART_ALL_TIME_PRICE_PROC,
						inParams, inParamsTypeMap, outParamsTypeMap);
		
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
			Map<String, Object> storedProcResult = storedProc.execute();
		
			
			// Elapsed time in minutes (TIME LOG)
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
					: "less than a second.";
			logger.info(GMDSMART_ALL_TIME_PRICE_PROC + "-{}" , elapsedTimeDisp);
	
			// END (TIME LOG)
		
			if (storedProcResult != null
					&& (storedProcResult.get(RET_TYP_OUT_V) == null || storedProcResult
							.get(RET_TYP_OUT_V).equals(""))) {
				
				List<AllTimePrice> storeResponseList = (List<AllTimePrice>)storedProcResult
						.get(AT_PRICE_OUT_REC);
				if (storeResponseList != null) {
					response.setAllTimePriceList(storeResponseList);
				}
							
				
			}		
		

		utilityloggerHelper.logTransaction("getAllTimePriceFromDB", false,
				request, response, "", CommonUtil.getElapsedTime(startTime),
				"", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("Exiting getAllTimePriceFromDB in UsageDaoImpl");
		return response;
	}
	
}

