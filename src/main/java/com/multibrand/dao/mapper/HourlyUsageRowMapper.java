package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.HourlyUsage;

public class HourlyUsageRowMapper implements RowMapper<HourlyUsage>, Constants
{

	Logger logger = LogManager.getLogger("NRGREST_Logger");

	@Override
	public HourlyUsage mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		
		HourlyUsage hourlyUsage = new HourlyUsage();
		hourlyUsage.setEsiId(rs.getString("ESIID"));
		hourlyUsage.setContractId(rs.getString("CONTRACT_ID"));
		hourlyUsage.setContractAcctId(rs.getString("CONTRACT_ACCT_ID"));
		hourlyUsage.setBusPartner(CommonUtil.getBlankString(rs.getString("BUS_PARTNER")));
	/*	hourlyUsage.setZoneId(CommonUtil.getBlankString(rs.getString("ZONE_ID")));
		hourlyUsage.setCurdtInd(CommonUtil.getBlankString(rs.getString("CUR_DT_IN_V")));
		hourlyUsage.setCurDayInd(CommonUtil.getBlankString(rs.getString("CUR_DAY_IND_IN_V")));
		hourlyUsage.setDyHrInd(CommonUtil.getBlankString(rs.getString("DYHR_IND_IN_V")));*/
		
		if (rs.getString("ACTUAL_DAY") != null) {
			hourlyUsage.setActualDay(CommonUtil.changeDateFormat(
					rs.getString("ACTUAL_DAY"), DT_SQL_FMT_DB, DT_FMT));
		} else {
			hourlyUsage.setActualDay("");
		}
		
		
		
		hourlyUsage.setUsageHr01(CommonUtil.getBlankString(rs.getString("USAGE_HR01")));
		hourlyUsage.setUsageHr02(CommonUtil.getBlankString(rs.getString("USAGE_HR02")));
		hourlyUsage.setUsageHr03(CommonUtil.getBlankString(rs.getString("USAGE_HR03")));
		hourlyUsage.setUsageHr04(CommonUtil.getBlankString(rs.getString("USAGE_HR04")));
		hourlyUsage.setUsageHr05(CommonUtil.getBlankString(rs.getString("USAGE_HR05")));
		hourlyUsage.setUsageHr06(CommonUtil.getBlankString(rs.getString("USAGE_HR06")));
		hourlyUsage.setUsageHr07(CommonUtil.getBlankString(rs.getString("USAGE_HR07")));
		hourlyUsage.setUsageHr08(CommonUtil.getBlankString(rs.getString("USAGE_HR08")));
		hourlyUsage.setUsageHr09(CommonUtil.getBlankString(rs.getString("USAGE_HR09")));
		hourlyUsage.setUsageHr10(CommonUtil.getBlankString(rs.getString("USAGE_HR10")));
		hourlyUsage.setUsageHr11(CommonUtil.getBlankString(rs.getString("USAGE_HR11")));
		hourlyUsage.setUsageHr12(CommonUtil.getBlankString(rs.getString("USAGE_HR12")));
		hourlyUsage.setUsageHr13(CommonUtil.getBlankString(rs.getString("USAGE_HR13")));
		hourlyUsage.setUsageHr14(CommonUtil.getBlankString(rs.getString("USAGE_HR14")));
		hourlyUsage.setUsageHr15(CommonUtil.getBlankString(rs.getString("USAGE_HR15")));
		hourlyUsage.setUsageHr16(CommonUtil.getBlankString(rs.getString("USAGE_HR16")));
		hourlyUsage.setUsageHr17(CommonUtil.getBlankString(rs.getString("USAGE_HR17")));
		hourlyUsage.setUsageHr18(CommonUtil.getBlankString(rs.getString("USAGE_HR18")));
		hourlyUsage.setUsageHr19(CommonUtil.getBlankString(rs.getString("USAGE_HR19")));
		hourlyUsage.setUsageHr20(CommonUtil.getBlankString(rs.getString("USAGE_HR20")));
		hourlyUsage.setUsageHr21(CommonUtil.getBlankString(rs.getString("USAGE_HR21")));
		hourlyUsage.setUsageHr22(CommonUtil.getBlankString(rs.getString("USAGE_HR22")));
		hourlyUsage.setUsageHr23(CommonUtil.getBlankString(rs.getString("USAGE_HR23")));
		hourlyUsage.setUsageHr24(CommonUtil.getBlankString(rs.getString("USAGE_HR24")));
		hourlyUsage.setCostHr01(CommonUtil.getBlankString(rs.getString("COST_HR01")));
		hourlyUsage.setCostHr02(CommonUtil.getBlankString(rs.getString("COST_HR02")));
		hourlyUsage.setCostHr03(CommonUtil.getBlankString(rs.getString("COST_HR03")));
		hourlyUsage.setCostHr04(CommonUtil.getBlankString(rs.getString("COST_HR04")));
		hourlyUsage.setCostHr05(CommonUtil.getBlankString(rs.getString("COST_HR05")));
		hourlyUsage.setCostHr06(CommonUtil.getBlankString(rs.getString("COST_HR06")));
		hourlyUsage.setCostHr07(CommonUtil.getBlankString(rs.getString("COST_HR07")));
		hourlyUsage.setCostHr08(CommonUtil.getBlankString(rs.getString("COST_HR08")));
		hourlyUsage.setCostHr09(CommonUtil.getBlankString(rs.getString("COST_HR09")));
		hourlyUsage.setCostHr10(CommonUtil.getBlankString(rs.getString("COST_HR10")));
		hourlyUsage.setCostHr11(CommonUtil.getBlankString(rs.getString("COST_HR11")));
		hourlyUsage.setCostHr12(CommonUtil.getBlankString(rs.getString("COST_HR12")));
		hourlyUsage.setCostHr13(CommonUtil.getBlankString(rs.getString("COST_HR13")));
		hourlyUsage.setCostHr14(CommonUtil.getBlankString(rs.getString("COST_HR14")));
		hourlyUsage.setCostHr15(CommonUtil.getBlankString(rs.getString("COST_HR15")));
		hourlyUsage.setCostHr16(CommonUtil.getBlankString(rs.getString("COST_HR16")));
		hourlyUsage.setCostHr17(CommonUtil.getBlankString(rs.getString("COST_HR17")));
		hourlyUsage.setCostHr18(CommonUtil.getBlankString(rs.getString("COST_HR18")));
		hourlyUsage.setCostHr19(CommonUtil.getBlankString(rs.getString("COST_HR19")));
		hourlyUsage.setCostHr20(CommonUtil.getBlankString(rs.getString("COST_HR20")));
		hourlyUsage.setCostHr21(CommonUtil.getBlankString(rs.getString("COST_HR21")));
		hourlyUsage.setCostHr22(CommonUtil.getBlankString(rs.getString("COST_HR22")));
		hourlyUsage.setCostHr23(CommonUtil.getBlankString(rs.getString("COST_HR23")));
		hourlyUsage.setCostHr24(CommonUtil.getBlankString(rs.getString("COST_HR24")));
		hourlyUsage.setDayUsg(CommonUtil.getBlankString(rs.getString("DAY_USG")));
		hourlyUsage.setDayCst(CommonUtil.getBlankString(rs.getString("DAY_CST")));
		hourlyUsage.setDayTempHigh(CommonUtil.getBlankString(rs.getString("DAY_TEMP_HIGH")));
		hourlyUsage.setDayTempLow(CommonUtil.getBlankString(rs.getString("DAY_TEMP_LOW")));


		return hourlyUsage;
	}

}
