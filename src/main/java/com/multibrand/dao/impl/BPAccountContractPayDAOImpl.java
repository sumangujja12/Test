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
import com.multibrand.dao.BPAccountContractPayDAO;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.mapper.BPContractAccountRowMapper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.response.BussinessPartnerDO;
import oracle.jdbc.OracleTypes;


@Component("SVTDAO")
public class BPAccountContractPayDAOImpl implements BPAccountContractPayDAO
{
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	private JdbcTemplate svtJdbcTemplate;
	
	
	@Autowired(required = true)
	public BPAccountContractPayDAOImpl(
			@Qualifier("svtJdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.svtJdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<BussinessPartnerDO> getSVTData(String bpNumber,
			String companyCode, String brandName, String sessionId)
	{
		logger.info("Starts getSVTDATA in BPAccountContractPayDAOImpl");
		logger.info("bp no in the request is:{}",bpNumber);
		
		List<BussinessPartnerDO> bussinessPartnerDOList = null;
		Map<String, Object> inParams = new LinkedHashMap<>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<>();
		BaseStoredProcedure storedProc = null;
		
		
		inParamsTypeMap.put("IN_BP_NUMBER_LIST",OracleTypes.VARCHAR );
		inParams.put("IN_BP_NUMBER_LIST", bpNumber);
		outParamsTypeMap.put("recordSet", new ResultObject(
				OracleTypes.CURSOR, new BPContractAccountRowMapper()));
		
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure
				.createStoredProcedure(svtJdbcTemplate, "CIRSVT_MAIN.GETSVTDATA",
						inParams, inParamsTypeMap, outParamsTypeMap);
		long startTime = CommonUtil.getStartTime();
		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		
		Map<String, Object> storedProcResult = storedProc.execute();

			
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info("GETSVTDATA -{}" , elapsedTimeDisp);
		
		if (storedProcResult != null ) {
			
			bussinessPartnerDOList = (List<BussinessPartnerDO>) storedProcResult
					.get("recordSet");
		}
		
		utilityloggerHelper.logTransaction("getSVTDATA", false, bpNumber, bussinessPartnerDOList, "",
				CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(bpNumber));
			logger.debug(XmlUtil.pojoToXML(bussinessPartnerDOList));
		}
		logger.info("Exiting getSVTDATA in BPAccountContractPayDAOImpl");
		
		
		return bussinessPartnerDOList;
	}
}
