package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.DailyResponseVO;

public class DailyUsageRowMapper implements RowMapper<DailyResponseVO>, Constants
{

	@Override
	public DailyResponseVO mapRow(ResultSet rs, int count) throws SQLException
	{

		DailyResponseVO dailyResponseVO = new DailyResponseVO();

		dailyResponseVO.setEsiId(CommonUtil.getBlankString(rs.getString("ESIID")));
		dailyResponseVO.setContractId(CommonUtil.getBlankString(rs.getString("CONTRACT_ID")));
		dailyResponseVO.setContractAcctId(CommonUtil.getBlankString(rs.getString("CONTRACT_ACCT_ID")));
		dailyResponseVO.setBusPartner(CommonUtil.getBlankString(rs.getString("BUS_PARTNER")));
		//dailyResponseVO.setZoneId(CommonUtil.getBlankString(rs.getString("ZONE_ID")));
		/*if (rs.getString("CUR_DT_IN_V") != null) {
			dailyResponseVO.setCurDtInd(CommonUtil.changeDateFormat(rs.getString("CUR_DT_IN_V"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			dailyResponseVO.setCurDtInd("");
		}
		
		dailyResponseVO.setCurDayInd(CommonUtil.getBlankString(rs.getString("CUR_DAY_IND_IN_V")));
		dailyResponseVO.setDyHrInd(CommonUtil.getBlankString(rs.getString("DYHR_IND_IN_V")));
		*/
		if (rs.getString("ACTUAL_DAY") != null) {
			dailyResponseVO.setActualDay(CommonUtil.getBlankString(CommonUtil.changeDateFormat(rs.getString("ACTUAL_DAY"),
					DT_SQL_FMT_DB, DT_FMT)));
		} else {
			dailyResponseVO.setActualDay("");
		}
		
		
		dailyResponseVO.setDayUsg(CommonUtil.getBlankString(rs.getString("day_usg")));
		dailyResponseVO.setDayCst(CommonUtil.getBlankString(rs.getString("day_cst")));
		dailyResponseVO.setDayTempHigh(CommonUtil.getBlankString(rs.getString("DAY_TEMP_HIGH")));
		dailyResponseVO.setDayTempLow(CommonUtil.getBlankString(rs.getString("DAY_TEMP_LOW")));

		return dailyResponseVO;
	}

}
