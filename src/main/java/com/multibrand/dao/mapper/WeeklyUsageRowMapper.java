package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.WeeklyUsageResponse;

public class WeeklyUsageRowMapper implements RowMapper<WeeklyUsageResponse>, Constants
{

	@Override
	public WeeklyUsageResponse mapRow(ResultSet rs, int arg1)
			throws SQLException
	{
		WeeklyUsageResponse weeklyUsageResponse  = new WeeklyUsageResponse();
		
		
		if (rs.getString("WK_STRT_DT") != null) {
			weeklyUsageResponse.setWkStrtDt(CommonUtil.changeDateFormat(rs.getString("WK_STRT_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			weeklyUsageResponse.setWkStrtDt("");
		}
		
		if (rs.getString("WK_END_DT") != null) {
			weeklyUsageResponse.setWkEndDt(CommonUtil.changeDateFormat(rs.getString("WK_END_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			weeklyUsageResponse.setWkEndDt("");
		}
		if ( StringUtils.isNotBlank(rs.getString("WK_AVG_USG")) ) {
			weeklyUsageResponse.setWkAvgUsg(CommonUtil.getRoundingDecimal(
					rs.getString("WK_AVG_USG"), 3));

		} else {
			weeklyUsageResponse.setWkAvgUsg("");
		}
		
		if ( StringUtils.isNotBlank(rs.getString("WK_AVGUSG_PCT")) ) {
			weeklyUsageResponse.setWkAvgusgPct(CommonUtil.getRoundingDecimal(
					rs.getString("WK_AVGUSG_PCT"), 3));

		} else {
			weeklyUsageResponse.setWkAvgusgPct("");
		}
		
		
		weeklyUsageResponse.setWkUsgInd(CommonUtil.getBlankString(rs
				.getString("WK_USG_IND")));
		
		if ( StringUtils.isNotBlank(rs.getString("WK_AVG_TEMP"))) {
			weeklyUsageResponse.setWkAvgTemp(CommonUtil.getRoundingDecimal(
					rs.getString("WK_AVG_TEMP"), 3));

		} else {
			weeklyUsageResponse.setWkAvgTemp("");
		}
		
		if ( StringUtils.isNotBlank(rs.getString("WK_AVGTEMP_DIFF")) ) {
			weeklyUsageResponse.setWkAvgTempDiff(CommonUtil.getRoundingDecimal(
					rs.getString("WK_AVGTEMP_DIFF"), 3));

		} else {
			weeklyUsageResponse.setWkAvgTempDiff("");
		}
		
		
		weeklyUsageResponse.setWkTempInd(CommonUtil.getBlankString(rs
				.getString("WK_TEMP_IND")));
				
		return weeklyUsageResponse;
	}
}
