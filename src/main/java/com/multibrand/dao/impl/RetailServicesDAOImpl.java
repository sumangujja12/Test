package com.multibrand.dao.impl;

import java.sql.Types;
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
import com.multibrand.dao.RetailServicesDAO;
import com.multibrand.dao.mapper.RetailServicesRowMapper;
import com.multibrand.dto.RetailServicesDTO;
import com.multibrand.dto.response.RetailServicesResponse;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.DBConstants;

import oracle.jdbc.OracleTypes;

@Component
public class RetailServicesDAOImpl implements RetailServicesDAO, DBConstants{
	
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RetailServicesDAOImpl(@Qualifier("podJdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RetailServicesResponse readHouseAgeAndHHIncome(String addressIdorESID) {
		RetailServicesResponse response = new RetailServicesResponse();
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		
		try{
			inParams.put("in_var", addressIdorESID);
			
			inParamsTypeMap.put("in_var", OracleTypes.VARCHAR);

			if(logger.isDebugEnabled()){
				logger.debug("inParams:" + inParams);
			}
			
			outParamsTypeMap.put(CUR_OUT_ROWSET, new ResultObject(OracleTypes.CURSOR, new RetailServicesRowMapper()));
			outParamsTypeMap.put(OUT_ERROR_CODE_LOWER, new ResultObject(Types.VARCHAR));
			
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
			
			storedProc = storedProcedure.createStoredProcedure(jdbcTemplate,PROC_GET_HOUSE_AGE_HH_INCOME, inParams, inParamsTypeMap, outParamsTypeMap);
	
			Map<String, Object> resultMap = storedProc.execute();
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
					: "less than a second.";
			logger.info(PROC_GET_HOUSE_AGE_HH_INCOME + "-" + elapsedTimeDisp);
			String error_code = (String) resultMap.get(OUT_ERROR_CODE_LOWER);
			
			List<RetailServicesDTO> retailServicesResultList = (List<RetailServicesDTO>) resultMap.get(CUR_OUT_ROWSET);
			if(null != retailServicesResultList && retailServicesResultList.size() > 0){
				RetailServicesDTO retailServicesDTO = retailServicesResultList.get(0);
				response.setHhincome(retailServicesDTO.getHhincome());
				response.setHomeAge(retailServicesDTO.getHomeAge());	
			} else {
				response.setHhincome("");
				response.setHomeAge("");	
			}
			response.setErrorCode(error_code);
			
		}catch(Exception ex){
			logger.error("Exception occured while reading table", ex);
			response.setErrorCode("SQL_ERR:"+ex);
		}
		return response;
	}

	
	
	
}
