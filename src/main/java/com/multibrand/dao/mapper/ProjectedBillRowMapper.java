package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.ProjectedBillResponse;

public class ProjectedBillRowMapper implements RowMapper<ProjectedBillResponse>, Constants
{

	@Override
	public ProjectedBillResponse mapRow(ResultSet rs, int arg1)
			throws SQLException
	{
		ProjectedBillResponse response  = new ProjectedBillResponse();
		
		
		response.setEsIid(rs.getString("ESIID"));
		response.setContractId(rs.getString("CONTRACT_ID"));
		response.setContractAcctId(rs.getString("CONTRACT_ACCT_ID"));
		response.setBusPartner(rs.getString("BUS_PARTNER"));
		
		if (rs.getString("START_DT") != null) {
			response.setBillPeriodStart(CommonUtil.changeDateFormat(rs.getString("START_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setBillPeriodStart("");
		}
		
		if (rs.getString("END_DT") != null) {
			response.setBillPeriodEnd(CommonUtil.changeDateFormat(rs.getString("END_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setBillPeriodEnd("");
		}
		
		
		if (rs.getString("ACTUAL_DAY") != null) {
			response.setActualDay(CommonUtil.changeDateFormat(rs.getString("ACTUAL_DAY"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setActualDay("");
		}
		
		if (rs.getString("proj_bill_amt") != null) {
			response.setProjBillAmt(CommonUtil.getRoundingDecimalCost(rs
					.getString("proj_bill_amt")));
		} else {
			response.setProjBillAmt("");
		}
		
		if (rs.getString("TOT_PROJ_BIL_AMT") != null) {
			response.setTotProjBilAmt(CommonUtil.getRoundingDecimalCost(rs
					.getString("TOT_PROJ_BIL_AMT")));
		} else {
			response.setTotProjBilAmt("");
		}
		
		if (rs.getString("actual_bill_amt") != null) {
			response.setActualBillAmt(CommonUtil.getRoundingDecimalCost(rs
					.getString("actual_bill_amt")));
		} else {
			response.setActualBillAmt("");
		}
		
		if (rs.getString("tot_actual_bil_amt") != null) {
			response.setTotActualBilAmt(CommonUtil.getRoundingDecimalCost(rs
					.getString("tot_actual_bil_amt")));
		} else {
			response.setTotActualBilAmt("");
		}
		
		if (rs.getString("ACT_USG") != null) {
			response.setActualUsage(CommonUtil.getRoundingDecimal(
					rs.getString("ACT_USG"), 20));
		} else {
			response.setActualUsage("");
		}
		
		if (rs.getString("tot_act_usg") != null) {
			response.setTotActUsg(CommonUtil.getRoundingDecimal(
					rs.getString("tot_act_usg"), 20));
		} else {
			response.setTotActUsg("");
		}
		
		if (rs.getString("DUE_DT") != null) {
			response.setDueDate(CommonUtil.changeDateFormat(rs.getString("DUE_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setDueDate("");
		}
		
		if (rs.getString("INVOICE_DT") != null) {
			response.setInvoiceDt(CommonUtil.changeDateFormat(rs.getString("INVOICE_DT"),
					DT_SQL_FMT_DB, DT_FMT));
		} else {
			response.setInvoiceDt("");
		}
		
		if (rs.getString("TOU_GROUP") != null) {
			response.setTouGroup(rs.getString("TOU_GROUP"));
		} else {
			response.setTouGroup("");
		}
		
		if (rs.getString("ZONE_ID") != null) {
			response.setZoneId(rs.getString("ZONE_ID"));
		} else {
			response.setZoneId("");
		}
		
		if (rs.getString("COMPANY_CD") != null) {
			response.setCompanyCode(rs.getString("COMPANY_CD"));
		} else {
			response.setCompanyCode("");
		}
		
		if (rs.getString("PRODUCT") != null) {
			response.setProduct(rs.getString("PRODUCT"));
		} else {
			response.setProduct("");
		}
		
		if (rs.getString("proj_bill_amt_low") != null) {
			response.setProjBillAmtLow(CommonUtil.getRoundingDecimalCost(
					rs.getString("proj_bill_amt_low")));
		} else {
			response.setProjBillAmtLow("");
		}
		
		
		if (rs.getString("PROJ_BILL_AMT_HIGH") != null) {
			response.setProjBillAmtHigh(CommonUtil.getRoundingDecimalCost(
					rs.getString("PROJ_BILL_AMT_HIGH")));
		} else {
			response.setProjBillAmtHigh("");
		}
		
		
		if (rs.getString("tot_proj_bil_amt_low") != null) {
			response.setTotProjBilAmtLow(CommonUtil.getRoundingDecimalCost(
					rs.getString("tot_proj_bil_amt_low")));
		} else {
			response.setTotProjBilAmtLow("");
		}
		
		
		if (rs.getString("TOT_PROJ_BIL_AMT_HIGH") != null) {
			response.setTotProjBilAmtHigh(CommonUtil.getRoundingDecimalCost(
					rs.getString("TOT_PROJ_BIL_AMT_HIGH")));
		} else {
			response.setTotProjBilAmtHigh("");
		}
		
		
		
		return response;
	}

}
