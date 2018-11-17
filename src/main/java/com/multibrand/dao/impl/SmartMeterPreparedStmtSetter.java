package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.SmartMeterUsageRequestVO;

public class SmartMeterPreparedStmtSetter implements PreparedStatementSetter
{

	/** instances of the request input services*/
	private SmartMeterUsageRequestVO requestVO = null;
	/** instances of the logger*/
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public SmartMeterPreparedStmtSetter(SmartMeterUsageRequestVO requestVO) {
		this.requestVO = requestVO;
	}
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException
	{
		logger.debug("START-[SmartMeterPreparedStmtSetter-setValues]");
		ps.setString(1, requestVO.getServicePointId());
		ps.setString(2, requestVO.getAccountNumber());
		    
		if ((requestVO.getStartDate() != null && !requestVO.getStartDate()
				.equals(""))) {
			ps.setDate(3, CommonUtil.getSqlDate(requestVO.getStartDate(),
					Constants.DT_FMT_REQUEST));
		}

		if (requestVO.getEndDate() != null
				&& !requestVO.getEndDate().equals("")) {
			ps.setDate(4, CommonUtil.getSqlDate(requestVO.getEndDate(),
					Constants.DT_FMT_REQUEST));
		}
		logger.debug("END-[SmartMeterPreparedStmtSetter-setValues]");
		
	}

}
