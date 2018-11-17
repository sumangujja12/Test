package com.multibrand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.multibrand.vo.request.SmartMeterUsageRequestVO;

public class SmartMeterPreparedStmtCreator implements PreparedStatementCreator
{
	/** instances of the request input services*/
	private SmartMeterUsageRequestVO requestVO = null;
	/** instances of the logger*/
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");


	public SmartMeterPreparedStmtCreator(SmartMeterUsageRequestVO requestVO) {
		this.requestVO = requestVO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection connection)
			throws SQLException
	{
		logger.debug("START-[SmartMeterPreparedStmtCreator-createPreparedStatement]");

		StringBuffer strBuffer = new StringBuffer();
		 
		 strBuffer.append(" SELECT "); 
		 strBuffer.append(" ACCOUNTID, SERVICEPOINTID, READ_SET_DATE, TDSP, PRODUCT, M2M_PRODUCT,"); 
		 strBuffer.append(" CUTOFF_T1, REIMBURSE_PCT_T1, REIMBURSE_PCT_T2, RATE, RATE_SUPP, RATE_LITE_UP,"); 
		 strBuffer.append(" CHARGE_SUN_CLUB, CHARGE_GMEC_DRIVER, MSC, MSC_SUPP, PREV_READDATE,"); 
		 strBuffer.append(" CURR_READDATE, NEXT_READDATE, WIND_BENEFIT_VALUE, SYSTEM_BENEFIT_VALUE,"); 
		 strBuffer.append(" FIRSTNAME, LASTNAME, EMAIL, STREET, CITY, ZIPCODE, USAGE_RID, ENERGY_TYPE,"); 
		 strBuffer.append(" HR01, HR02, HR03, HR04, HR05, HR06, HR07, HR08, HR09, HR10, HR11, HR12, "); 
		 strBuffer.append(" HR13, HR14, HR15, HR16, HR17, HR18, HR19, HR20, HR21, HR22, HR23, HR24, "); 
		 strBuffer.append(" HR25, INTERVAL_SUM, BUDGET_PLAN_AMOUNT, PASS_THRU_CHARGES, CURR_INSTALL_PLAN, LANG_PREF ");  
		 strBuffer.append(" FROM vw_acct_usage "); 
		 strBuffer.append(" WHERE "); 
		 strBuffer.append(" USAGE_RID > 0 "); 
		 strBuffer.append(" and SERVICEPOINTID in (?)"); 
	     strBuffer.append(" and ACCOUNTID in (?) ");
	     
	    
	     
	     if((requestVO.getStartDate() != null && !requestVO.getStartDate().equals(""))) {
	    	 strBuffer.append("  AND READ_SET_DATE >= ? ");
	    	 
	    	
	     }
	     
	     if(requestVO.getEndDate() != null && !requestVO.getEndDate().equals("")) {
	    	 strBuffer.append("  AND READ_SET_DATE <= ? ");
	     }
		
	     strBuffer.append(" ORDER BY SERVICEPOINTID, READ_SET_DATE, ENERGY_TYPE");
	     
	     logger.info("Sql of the Prepared Statement"+strBuffer.toString());
	     
	     PreparedStatement ps = connection.prepareStatement(strBuffer.toString());		
	     logger.debug("Exit-[SmartMeterPreparedStmtCreator-createPreparedStatement]");

	     
		return ps;
	}

}
