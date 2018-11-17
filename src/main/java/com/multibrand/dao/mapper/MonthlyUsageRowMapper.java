package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.MonthlyUsageResponse;

public class MonthlyUsageRowMapper implements RowMapper<MonthlyUsageResponse>, Constants
{

	@Override
	public MonthlyUsageResponse mapRow(ResultSet rs, int arg1)
			throws SQLException
	{
		MonthlyUsageResponse response  = new MonthlyUsageResponse();
		
		
		response.setEsiId(CommonUtil.getBlankString(rs.getString("esiid")));
		response.setContractId(CommonUtil.getBlankString(rs.getString("contract_id")));
		response.setContractAcctId(CommonUtil.getBlankString(rs.getString("contract_acct_id")));
		response.setBusPartner(CommonUtil.getBlankString(rs.getString("bus_partner")));
		response.setYearMonthNo(CommonUtil.getBlankString(rs.getString("year_month_no")));
		response.setTotalUsageMonth(CommonUtil.getBlankString(rs.getString("mon_usg")));
		response.setTotalMonthCost(CommonUtil.getBlankString(rs.getString("mon_cst")));
		response.setMonthAveTempHigh(CommonUtil.getBlankString(rs.getString("mon_ave_temp_high")));
		response.setMonthAveTempLow(CommonUtil.getBlankString(rs.getString("mon_ave_temp_low")));
		response.setTotalUsageYear(CommonUtil.getBlankString(rs.getString("yr_usg")));
		response.setTotalYearCost(CommonUtil.getBlankString(rs.getString("yr_cst")));
		response.setYearAveTempHigh(CommonUtil.getBlankString(rs.getString("yr_ave_temp_high")));
		response.setYearAveTempLow(CommonUtil.getBlankString(rs.getString("yr_ave_temp_low")));
		
		return response;
	}

}
