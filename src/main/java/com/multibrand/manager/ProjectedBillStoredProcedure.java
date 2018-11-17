package com.multibrand.manager;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.multibrand.util.DBConstants;




public class ProjectedBillStoredProcedure extends StoredProcedure implements DBConstants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	private Map<String, Object> inParams = null;  
	
	public ProjectedBillStoredProcedure(JdbcTemplate jdbcTemplate, String spName,Map<String, Object> inParams){
		super(jdbcTemplate,spName);
		this.inParams=inParams;
		logger.info("Inside ProjectedBillStoredProcedure  ");
		//IN Parameters
	
		declareParameter(new SqlParameter(BILL_STRT_DT, OracleTypes.DATE));
		declareParameter(new SqlParameter(BILL_END_DT, OracleTypes.DATE));
		declareParameter(new SqlParameter(ZONE_ID, OracleTypes.VARCHAR));
		
		declareParameter(new SqlOutParameter(RET_CD, OracleTypes.VARCHAR));
		
		declareParameter(new SqlOutParameter(AVG_TEMP, OracleTypes.NUMBER));
		declareParameter(new SqlParameter(TYP_TEMP, OracleTypes.VARCHAR));
		logger.info("Exiting ProjectedBillStoredProcedure ");
	}

}
