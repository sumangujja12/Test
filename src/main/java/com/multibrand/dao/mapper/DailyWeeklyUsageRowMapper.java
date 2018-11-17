package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.DailyWeeklyUsageResponse;


public class DailyWeeklyUsageRowMapper implements RowMapper<DailyWeeklyUsageResponse>, Constants
{

	@Override
	public DailyWeeklyUsageResponse mapRow(ResultSet rs, int arg1)
			throws SQLException
	{
		DailyWeeklyUsageResponse response  = new DailyWeeklyUsageResponse();
		
		
		response.setEsiId(CommonUtil.getBlankString(rs.getString("esiid")));
		response.setContractId(CommonUtil.getBlankString(rs.getString("contract_id")));
		response.setContractAcctId(CommonUtil.getBlankString(rs.getString("contract_acct_id")));
		response.setBusPartner(CommonUtil.getBlankString(rs.getString("bus_partner")));
		
		
		if (rs.getString("cur_bil_dt") != null) {
			response.setCurBillDt(CommonUtil.changeDateFormat(rs.getString("cur_bil_dt"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setCurBillDt("");  
		}
		
		response.setTotDayUsg(CommonUtil.getBlankString(rs.getString("tot_day_usg")));
		response.setTotDayCst(CommonUtil.getBlankString(rs.getString("tot_day_cst")));
		response.setDayTempHigh(CommonUtil.getBlankString(rs.getString("day_temp_high")));
		response.setDayTempLow(CommonUtil.getBlankString(rs.getString("day_temp_low")));
		response.setTotWkUsg(CommonUtil.getBlankString(rs.getString("tot_wk_usg")));
		response.setTotWkCst(CommonUtil.getBlankString(rs.getString("tot_wk_cst")));
		response.setWkAveTempHigh(CommonUtil.getBlankString(rs.getString("wk_ave_temp_high")));
		response.setWkAveTempLow(CommonUtil.getBlankString(rs.getString("wk_ave_temp_low")));
		
		
		return response;
	}

}
