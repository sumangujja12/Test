package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.SmartMeterUsageHistory;

public class SmartMeterUsageRowMapper implements ResultSetExtractor<Object>, Constants
{


	@Override
	public List<SmartMeterUsageHistory> extractData(ResultSet rs)
			throws SQLException, DataAccessException
	{
		List<SmartMeterUsageHistory> smartMeterList = new LinkedList<SmartMeterUsageHistory>();
		
		if(rs == null ) {
			return smartMeterList;
		}
		while(rs.next()) {
			SmartMeterUsageHistory smartMeterUsageHistory = new SmartMeterUsageHistory();
			
			
			smartMeterUsageHistory.setAccountId(CommonUtil.getBlankString(rs.getString("ACCOUNTID")));
			smartMeterUsageHistory.setServicePointId(CommonUtil.getBlankString(rs.getString("SERVICEPOINTID")));
			smartMeterUsageHistory.setReadSetDate(CommonUtil.changeDateFormat(rs.getString("READ_SET_DATE"),
						DT_SQL_FMT_DB, DT_FMT));
			System.out.println("Date"+CommonUtil.changeDateFormat(rs.getString("READ_SET_DATE"),
						DT_SQL_FMT_DB, DT_FMT));
			smartMeterUsageHistory.setTdsp(CommonUtil.getBlankString(rs.getString("TDSP")));
			smartMeterUsageHistory.setProduct(CommonUtil.getBlankString(rs.getString("PRODUCT")));
			smartMeterUsageHistory.setM2mProduct(CommonUtil.getBlankString(rs.getString("M2M_PRODUCT")));
			smartMeterUsageHistory.setCutOffT1(CommonUtil.getBlankString(rs.getString("CUTOFF_T1")));
			smartMeterUsageHistory.setReimbursePctT1(CommonUtil.getBlankString(rs.getString("REIMBURSE_PCT_T1")));
			smartMeterUsageHistory.setReimbursePctT2(CommonUtil.getBlankString(rs.getString("REIMBURSE_PCT_T2")));
			smartMeterUsageHistory.setRate(CommonUtil.getBlankString(rs.getString("RATE")));
			smartMeterUsageHistory.setRateSupp(CommonUtil.getBlankString(rs.getString("RATE_SUPP")));
			smartMeterUsageHistory.setRateLiteUp(CommonUtil.getBlankString(rs.getString("RATE_LITE_UP")));
			smartMeterUsageHistory.setChargeSunClub(CommonUtil.getBlankString(rs.getString("CHARGE_SUN_CLUB")));
			smartMeterUsageHistory.setChargeGmecDriver(CommonUtil.getBlankString(rs.getString("CHARGE_GMEC_DRIVER")));
			smartMeterUsageHistory.setMsc(CommonUtil.getBlankString(rs.getString("MSC")));
			smartMeterUsageHistory.setMscSupp(rs.getString("MSC_SUPP"));
			smartMeterUsageHistory.setPrevReadDate(CommonUtil.changeDateFormat(rs.getString("PREV_READDATE"),
						DT_SQL_FMT_DB, DT_FMT));
			smartMeterUsageHistory.setCurrReadDate(CommonUtil.changeDateFormat(rs.getString("CURR_READDATE"),
					DT_SQL_FMT_DB, DT_FMT));
			smartMeterUsageHistory.setNextReadDate(CommonUtil.changeDateFormat(rs.getString("NEXT_READDATE"),
					DT_SQL_FMT_DB, DT_FMT));
			smartMeterUsageHistory.setWindBenefitValue(CommonUtil.getBlankString(rs.getString("WIND_BENEFIT_VALUE")));
			smartMeterUsageHistory.setSystemBenefitValue(CommonUtil.getBlankString(rs.getString("SYSTEM_BENEFIT_VALUE")));
			smartMeterUsageHistory.setFirstName(CommonUtil.getBlankString(rs.getString("FIRSTNAME")));
			smartMeterUsageHistory.setLastName(CommonUtil.getBlankString(rs.getString("LASTNAME")));
			smartMeterUsageHistory.setEmail(CommonUtil.getBlankString(rs.getString("EMAIL")));
			smartMeterUsageHistory.setStreet(CommonUtil.getBlankString(rs.getString("STREET")));
			smartMeterUsageHistory.setCity(CommonUtil.getBlankString(rs.getString("CITY")));
			smartMeterUsageHistory.setZipCode(CommonUtil.getBlankString(rs.getString("ZIPCODE")));
			smartMeterUsageHistory.setUsageRid(CommonUtil.getBlankString(rs.getString("USAGE_RID")));
			smartMeterUsageHistory.setEnergyType(CommonUtil.getBlankString(rs.getString("ENERGY_TYPE")));
			smartMeterUsageHistory.setHr01(CommonUtil.getBlankString(rs.getString("HR01")));
			smartMeterUsageHistory.setHr02(CommonUtil.getBlankString(rs.getString("HR02")));
			smartMeterUsageHistory.setHr03(CommonUtil.getBlankString(rs.getString("HR03")));
			smartMeterUsageHistory.setHr04(CommonUtil.getBlankString(rs.getString("HR04")));
			smartMeterUsageHistory.setHr05(CommonUtil.getBlankString(rs.getString("HR05")));
			smartMeterUsageHistory.setHr06(CommonUtil.getBlankString(rs.getString("HR06")));
			smartMeterUsageHistory.setHr07(CommonUtil.getBlankString(rs.getString("HR07")));
			smartMeterUsageHistory.setHr08(CommonUtil.getBlankString(rs.getString("HR08")));
			smartMeterUsageHistory.setHr09(CommonUtil.getBlankString(rs.getString("HR09")));
			smartMeterUsageHistory.setHr10(CommonUtil.getBlankString(rs.getString("HR10")));
			smartMeterUsageHistory.setHr11(CommonUtil.getBlankString(rs.getString("HR11")));
			smartMeterUsageHistory.setHr12(CommonUtil.getBlankString(rs.getString("HR12")));
			smartMeterUsageHistory.setHr13(CommonUtil.getBlankString(rs.getString("HR13")));
			smartMeterUsageHistory.setHr14(CommonUtil.getBlankString(rs.getString("HR14")));
			smartMeterUsageHistory.setHr15(CommonUtil.getBlankString(rs.getString("HR15")));
			smartMeterUsageHistory.setHr16(CommonUtil.getBlankString(rs.getString("HR16")));
			smartMeterUsageHistory.setHr17(CommonUtil.getBlankString(rs.getString("HR17")));
			smartMeterUsageHistory.setHr18(CommonUtil.getBlankString(rs.getString("HR18")));
			smartMeterUsageHistory.setHr19(CommonUtil.getBlankString(rs.getString("HR19")));
			smartMeterUsageHistory.setHr20(CommonUtil.getBlankString(rs.getString("HR20")));
			smartMeterUsageHistory.setHr21(CommonUtil.getBlankString(rs.getString("HR21")));
			smartMeterUsageHistory.setHr22(CommonUtil.getBlankString(rs.getString("HR22")));
			smartMeterUsageHistory.setHr23(CommonUtil.getBlankString(rs.getString("HR23")));
			smartMeterUsageHistory.setHr24(CommonUtil.getBlankString(rs.getString("HR24")));
			smartMeterUsageHistory.setHr25(CommonUtil.getBlankString(rs.getString("HR25")));
			smartMeterUsageHistory.setIntervalSum(CommonUtil.getBlankString(rs.getString("INTERVAL_SUM")));
			smartMeterUsageHistory.setBudgetPlanAmount(CommonUtil.getBlankString(rs.getString("BUDGET_PLAN_AMOUNT")));
			smartMeterUsageHistory.setPassThruCharges(CommonUtil.getBlankString(rs.getString("PASS_THRU_CHARGES")));
			smartMeterUsageHistory.setCurrInstallPlan(CommonUtil.getBlankString(rs.getString("CURR_INSTALL_PLAN")));
			smartMeterUsageHistory.setLangPref(CommonUtil.getBlankString(rs.getString("LANG_PREF")));
			smartMeterList.add(smartMeterUsageHistory);
			
		}
		
		
		return smartMeterList;
	}

}
