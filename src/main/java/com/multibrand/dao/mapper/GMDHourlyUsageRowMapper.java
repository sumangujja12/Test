package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.RowMapper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.GMDHourlyUsage;

public class GMDHourlyUsageRowMapper implements RowMapper<GMDHourlyUsage>, Constants
{

	Logger logger = LogManager.getLogger("NRGREST_Logger");

	@Override
	public GMDHourlyUsage mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		GMDHourlyUsage hourlyUsage = new GMDHourlyUsage();
		hourlyUsage.setEsiId(rs.getString("ESIID"));
		hourlyUsage.setContractId(rs.getString("CONTRACT_ID"));
		hourlyUsage.setContractAcctId(rs.getString("CONTRACT_ACCT_ID"));
		hourlyUsage.setBusPartner(CommonUtil.getBlankString(rs.getString("BUS_PARTNER")));
		
		if (rs.getString("ACTUAL_DAY") != null) {
			hourlyUsage.setActualDay(CommonUtil.changeDateFormat(
					rs.getString("ACTUAL_DAY"), DT_SQL_FMT_DB, DT_FMT));
		} else {
			hourlyUsage.setActualDay("");
		}
		
		
		hourlyUsage.setUsageHr0015(CommonUtil.getBlankString(rs.getString("USAGE_MIN0015")));
		hourlyUsage.setUsageHr0030(CommonUtil.getBlankString(rs.getString("USAGE_MIN0030")));
		hourlyUsage.setUsageHr0045(CommonUtil.getBlankString(rs.getString("USAGE_MIN0045")));
		
		hourlyUsage.setUsageHr0100(CommonUtil.getBlankString(rs.getString("USAGE_MIN0100")));
		hourlyUsage.setUsageHr0115(CommonUtil.getBlankString(rs.getString("USAGE_MIN0115")));
		hourlyUsage.setUsageHr0130(CommonUtil.getBlankString(rs.getString("USAGE_MIN0130")));
		hourlyUsage.setUsageHr0145(CommonUtil.getBlankString(rs.getString("USAGE_MIN0145")));
		
		
		hourlyUsage.setUsageHr0200(CommonUtil.getBlankString(rs.getString("USAGE_MIN0200")));
		hourlyUsage.setUsageHr0215(CommonUtil.getBlankString(rs.getString("USAGE_MIN0215")));
		hourlyUsage.setUsageHr0230(CommonUtil.getBlankString(rs.getString("USAGE_MIN0230")));
		hourlyUsage.setUsageHr0245(CommonUtil.getBlankString(rs.getString("USAGE_MIN0245")));
		
		hourlyUsage.setUsageHr0300(CommonUtil.getBlankString(rs.getString("USAGE_MIN0300")));
		hourlyUsage.setUsageHr0315(CommonUtil.getBlankString(rs.getString("USAGE_MIN0315")));
		hourlyUsage.setUsageHr0330(CommonUtil.getBlankString(rs.getString("USAGE_MIN0330")));
		hourlyUsage.setUsageHr0345(CommonUtil.getBlankString(rs.getString("USAGE_MIN0345")));
		
		hourlyUsage.setUsageHr0400(CommonUtil.getBlankString(rs.getString("USAGE_MIN0400")));
		hourlyUsage.setUsageHr0415(CommonUtil.getBlankString(rs.getString("USAGE_MIN0415")));
		hourlyUsage.setUsageHr0430(CommonUtil.getBlankString(rs.getString("USAGE_MIN0430")));
		hourlyUsage.setUsageHr0445(CommonUtil.getBlankString(rs.getString("USAGE_MIN0445")));
		
		
		hourlyUsage.setUsageHr0500(CommonUtil.getBlankString(rs.getString("USAGE_MIN0500")));
		hourlyUsage.setUsageHr0515(CommonUtil.getBlankString(rs.getString("USAGE_MIN0515")));
		hourlyUsage.setUsageHr0530(CommonUtil.getBlankString(rs.getString("USAGE_MIN0530")));
		hourlyUsage.setUsageHr0545(CommonUtil.getBlankString(rs.getString("USAGE_MIN0545")));
		
		hourlyUsage.setUsageHr0600(CommonUtil.getBlankString(rs.getString("USAGE_MIN0600")));
		hourlyUsage.setUsageHr0615(CommonUtil.getBlankString(rs.getString("USAGE_MIN0615")));
		hourlyUsage.setUsageHr0630(CommonUtil.getBlankString(rs.getString("USAGE_MIN0630")));
		hourlyUsage.setUsageHr0645(CommonUtil.getBlankString(rs.getString("USAGE_MIN0645")));
		
		hourlyUsage.setUsageHr0700(CommonUtil.getBlankString(rs.getString("USAGE_MIN0700")));
		hourlyUsage.setUsageHr0715(CommonUtil.getBlankString(rs.getString("USAGE_MIN0715")));
		hourlyUsage.setUsageHr0730(CommonUtil.getBlankString(rs.getString("USAGE_MIN0730")));
		hourlyUsage.setUsageHr0745(CommonUtil.getBlankString(rs.getString("USAGE_MIN0745")));
		
		hourlyUsage.setUsageHr0800(CommonUtil.getBlankString(rs.getString("USAGE_MIN0800")));
		hourlyUsage.setUsageHr0815(CommonUtil.getBlankString(rs.getString("USAGE_MIN0815")));
		hourlyUsage.setUsageHr0830(CommonUtil.getBlankString(rs.getString("USAGE_MIN0830")));
		hourlyUsage.setUsageHr0845(CommonUtil.getBlankString(rs.getString("USAGE_MIN0845")));
		
		hourlyUsage.setUsageHr0900(CommonUtil.getBlankString(rs.getString("USAGE_MIN0900")));
		hourlyUsage.setUsageHr0915(CommonUtil.getBlankString(rs.getString("USAGE_MIN0915")));
		hourlyUsage.setUsageHr0930(CommonUtil.getBlankString(rs.getString("USAGE_MIN0930")));
		hourlyUsage.setUsageHr0945(CommonUtil.getBlankString(rs.getString("USAGE_MIN0945")));
		
		hourlyUsage.setUsageHr1000(CommonUtil.getBlankString(rs.getString("USAGE_MIN1000")));
		hourlyUsage.setUsageHr1015(CommonUtil.getBlankString(rs.getString("USAGE_MIN1015")));
		hourlyUsage.setUsageHr1030(CommonUtil.getBlankString(rs.getString("USAGE_MIN1030")));
		hourlyUsage.setUsageHr1045(CommonUtil.getBlankString(rs.getString("USAGE_MIN1045")));
		
		
		hourlyUsage.setUsageHr1100(CommonUtil.getBlankString(rs.getString("USAGE_MIN1100")));
		hourlyUsage.setUsageHr1115(CommonUtil.getBlankString(rs.getString("USAGE_MIN1115")));
		hourlyUsage.setUsageHr1130(CommonUtil.getBlankString(rs.getString("USAGE_MIN1130")));
		hourlyUsage.setUsageHr1145(CommonUtil.getBlankString(rs.getString("USAGE_MIN1145")));
		
		hourlyUsage.setUsageHr1200(CommonUtil.getBlankString(rs.getString("USAGE_MIN1200")));
		hourlyUsage.setUsageHr1215(CommonUtil.getBlankString(rs.getString("USAGE_MIN1215")));
		hourlyUsage.setUsageHr1230(CommonUtil.getBlankString(rs.getString("USAGE_MIN1230")));
		hourlyUsage.setUsageHr1245(CommonUtil.getBlankString(rs.getString("USAGE_MIN1245")));
		
		hourlyUsage.setUsageHr1300(CommonUtil.getBlankString(rs.getString("USAGE_MIN1300")));
		hourlyUsage.setUsageHr1315(CommonUtil.getBlankString(rs.getString("USAGE_MIN1315")));
		hourlyUsage.setUsageHr1330(CommonUtil.getBlankString(rs.getString("USAGE_MIN1330")));
		hourlyUsage.setUsageHr1345(CommonUtil.getBlankString(rs.getString("USAGE_MIN1345")));
		
		
		hourlyUsage.setUsageHr1400(CommonUtil.getBlankString(rs.getString("USAGE_MIN1400")));
		hourlyUsage.setUsageHr1415(CommonUtil.getBlankString(rs.getString("USAGE_MIN1415")));
		hourlyUsage.setUsageHr1430(CommonUtil.getBlankString(rs.getString("USAGE_MIN1430")));
		hourlyUsage.setUsageHr1445(CommonUtil.getBlankString(rs.getString("USAGE_MIN1445")));
		
		
		hourlyUsage.setUsageHr1500(CommonUtil.getBlankString(rs.getString("USAGE_MIN1500")));
		hourlyUsage.setUsageHr1515(CommonUtil.getBlankString(rs.getString("USAGE_MIN1515")));
		hourlyUsage.setUsageHr1530(CommonUtil.getBlankString(rs.getString("USAGE_MIN1530")));
		hourlyUsage.setUsageHr1545(CommonUtil.getBlankString(rs.getString("USAGE_MIN1545")));
		
		hourlyUsage.setUsageHr1600(CommonUtil.getBlankString(rs.getString("USAGE_MIN1600")));
		hourlyUsage.setUsageHr1615(CommonUtil.getBlankString(rs.getString("USAGE_MIN1615")));
		hourlyUsage.setUsageHr1630(CommonUtil.getBlankString(rs.getString("USAGE_MIN1630")));
		hourlyUsage.setUsageHr1645(CommonUtil.getBlankString(rs.getString("USAGE_MIN1645")));
		
		hourlyUsage.setUsageHr1700(CommonUtil.getBlankString(rs.getString("USAGE_MIN1700")));
		hourlyUsage.setUsageHr1715(CommonUtil.getBlankString(rs.getString("USAGE_MIN1715")));
		hourlyUsage.setUsageHr1730(CommonUtil.getBlankString(rs.getString("USAGE_MIN1730")));
		hourlyUsage.setUsageHr1745(CommonUtil.getBlankString(rs.getString("USAGE_MIN1745")));
		
		hourlyUsage.setUsageHr1800(CommonUtil.getBlankString(rs.getString("USAGE_MIN1800")));
		hourlyUsage.setUsageHr1815(CommonUtil.getBlankString(rs.getString("USAGE_MIN1815")));
		hourlyUsage.setUsageHr1830(CommonUtil.getBlankString(rs.getString("USAGE_MIN1830")));
		hourlyUsage.setUsageHr1845(CommonUtil.getBlankString(rs.getString("USAGE_MIN1845")));
		
		
		hourlyUsage.setUsageHr1900(CommonUtil.getBlankString(rs.getString("USAGE_MIN1900")));
		hourlyUsage.setUsageHr1915(CommonUtil.getBlankString(rs.getString("USAGE_MIN1915")));
		hourlyUsage.setUsageHr1930(CommonUtil.getBlankString(rs.getString("USAGE_MIN1930")));
		hourlyUsage.setUsageHr1945(CommonUtil.getBlankString(rs.getString("USAGE_MIN1945")));
		
		hourlyUsage.setUsageHr2000(CommonUtil.getBlankString(rs.getString("USAGE_MIN2000")));
		hourlyUsage.setUsageHr2015(CommonUtil.getBlankString(rs.getString("USAGE_MIN2015")));
		hourlyUsage.setUsageHr2030(CommonUtil.getBlankString(rs.getString("USAGE_MIN2030")));
		hourlyUsage.setUsageHr2045(CommonUtil.getBlankString(rs.getString("USAGE_MIN2045")));
		
		hourlyUsage.setUsageHr2100(CommonUtil.getBlankString(rs.getString("USAGE_MIN2100")));
		hourlyUsage.setUsageHr2115(CommonUtil.getBlankString(rs.getString("USAGE_MIN2115")));
		hourlyUsage.setUsageHr2130(CommonUtil.getBlankString(rs.getString("USAGE_MIN2130")));
		hourlyUsage.setUsageHr2145(CommonUtil.getBlankString(rs.getString("USAGE_MIN2145")));
		
		hourlyUsage.setUsageHr2200(CommonUtil.getBlankString(rs.getString("USAGE_MIN2200")));
		hourlyUsage.setUsageHr2215(CommonUtil.getBlankString(rs.getString("USAGE_MIN2215")));
		hourlyUsage.setUsageHr2230(CommonUtil.getBlankString(rs.getString("USAGE_MIN2230")));
		hourlyUsage.setUsageHr2245(CommonUtil.getBlankString(rs.getString("USAGE_MIN2245")));
		
		hourlyUsage.setUsageHr2300(CommonUtil.getBlankString(rs.getString("USAGE_MIN2300")));
		hourlyUsage.setUsageHr2315(CommonUtil.getBlankString(rs.getString("USAGE_MIN2315")));
		hourlyUsage.setUsageHr2330(CommonUtil.getBlankString(rs.getString("USAGE_MIN2330")));
		hourlyUsage.setUsageHr2345(CommonUtil.getBlankString(rs.getString("USAGE_MIN2345")));
		
		hourlyUsage.setUsageHr2400(CommonUtil.getBlankString(rs.getString("USAGE_MIN2400")));
		
		hourlyUsage.setCostHr0015(CommonUtil.getBlankString(rs.getString("COST_MIN0015")));
		hourlyUsage.setCostHr0030(CommonUtil.getBlankString(rs.getString("COST_MIN0030")));
		hourlyUsage.setCostHr0045(CommonUtil.getBlankString(rs.getString("COST_MIN0045")));
		
		hourlyUsage.setCostHr0100(CommonUtil.getBlankString(rs.getString("COST_MIN0100")));
		hourlyUsage.setCostHr0115(CommonUtil.getBlankString(rs.getString("COST_MIN0115")));
		hourlyUsage.setCostHr0130(CommonUtil.getBlankString(rs.getString("COST_MIN0130")));
		hourlyUsage.setCostHr0145(CommonUtil.getBlankString(rs.getString("COST_MIN0145")));
		
		
		hourlyUsage.setCostHr0200(CommonUtil.getBlankString(rs.getString("COST_MIN0200")));
		hourlyUsage.setCostHr0215(CommonUtil.getBlankString(rs.getString("COST_MIN0215")));
		hourlyUsage.setCostHr0230(CommonUtil.getBlankString(rs.getString("COST_MIN0230")));
		hourlyUsage.setCostHr0245(CommonUtil.getBlankString(rs.getString("COST_MIN0245")));
		
		hourlyUsage.setCostHr0300(CommonUtil.getBlankString(rs.getString("COST_MIN0300")));
		hourlyUsage.setCostHr0315(CommonUtil.getBlankString(rs.getString("COST_MIN0315")));
		hourlyUsage.setCostHr0330(CommonUtil.getBlankString(rs.getString("COST_MIN0330")));
		hourlyUsage.setCostHr0345(CommonUtil.getBlankString(rs.getString("COST_MIN0345")));
		
		hourlyUsage.setCostHr0400(CommonUtil.getBlankString(rs.getString("COST_MIN0400")));
		hourlyUsage.setCostHr0415(CommonUtil.getBlankString(rs.getString("COST_MIN0415")));
		hourlyUsage.setCostHr0430(CommonUtil.getBlankString(rs.getString("COST_MIN0430")));
		hourlyUsage.setCostHr0445(CommonUtil.getBlankString(rs.getString("COST_MIN0445")));
		
		
		hourlyUsage.setCostHr0500(CommonUtil.getBlankString(rs.getString("COST_MIN0500")));
		hourlyUsage.setCostHr0515(CommonUtil.getBlankString(rs.getString("COST_MIN0515")));
		hourlyUsage.setCostHr0530(CommonUtil.getBlankString(rs.getString("COST_MIN0530")));
		hourlyUsage.setCostHr0545(CommonUtil.getBlankString(rs.getString("COST_MIN0545")));
		
		hourlyUsage.setCostHr0600(CommonUtil.getBlankString(rs.getString("COST_MIN0600")));
		hourlyUsage.setCostHr0615(CommonUtil.getBlankString(rs.getString("COST_MIN0615")));
		hourlyUsage.setCostHr0630(CommonUtil.getBlankString(rs.getString("COST_MIN0630")));
		hourlyUsage.setCostHr0645(CommonUtil.getBlankString(rs.getString("COST_MIN0645")));
		
		hourlyUsage.setCostHr0700(CommonUtil.getBlankString(rs.getString("COST_MIN0700")));
		hourlyUsage.setCostHr0715(CommonUtil.getBlankString(rs.getString("COST_MIN0715")));
		hourlyUsage.setCostHr0730(CommonUtil.getBlankString(rs.getString("COST_MIN0730")));
		hourlyUsage.setCostHr0745(CommonUtil.getBlankString(rs.getString("COST_MIN0745")));
		
		hourlyUsage.setCostHr0800(CommonUtil.getBlankString(rs.getString("COST_MIN0800")));
		hourlyUsage.setCostHr0815(CommonUtil.getBlankString(rs.getString("COST_MIN0815")));
		hourlyUsage.setCostHr0830(CommonUtil.getBlankString(rs.getString("COST_MIN0830")));
		hourlyUsage.setCostHr0845(CommonUtil.getBlankString(rs.getString("COST_MIN0845")));
		
		hourlyUsage.setCostHr0900(CommonUtil.getBlankString(rs.getString("COST_MIN0900")));
		hourlyUsage.setCostHr0915(CommonUtil.getBlankString(rs.getString("COST_MIN0915")));
		hourlyUsage.setCostHr0930(CommonUtil.getBlankString(rs.getString("COST_MIN0930")));
		hourlyUsage.setCostHr0945(CommonUtil.getBlankString(rs.getString("COST_MIN0945")));
		
		hourlyUsage.setCostHr1000(CommonUtil.getBlankString(rs.getString("COST_MIN1000")));
		hourlyUsage.setCostHr1015(CommonUtil.getBlankString(rs.getString("COST_MIN1015")));
		hourlyUsage.setCostHr1030(CommonUtil.getBlankString(rs.getString("COST_MIN1030")));
		hourlyUsage.setCostHr1045(CommonUtil.getBlankString(rs.getString("COST_MIN1045")));
		
		
		hourlyUsage.setCostHr1100(CommonUtil.getBlankString(rs.getString("COST_MIN1100")));
		hourlyUsage.setCostHr1115(CommonUtil.getBlankString(rs.getString("COST_MIN1115")));
		hourlyUsage.setCostHr1130(CommonUtil.getBlankString(rs.getString("COST_MIN1130")));
		hourlyUsage.setCostHr1145(CommonUtil.getBlankString(rs.getString("COST_MIN1145")));
		
		hourlyUsage.setCostHr1200(CommonUtil.getBlankString(rs.getString("COST_MIN1200")));
		hourlyUsage.setCostHr1215(CommonUtil.getBlankString(rs.getString("COST_MIN1215")));
		hourlyUsage.setCostHr1230(CommonUtil.getBlankString(rs.getString("COST_MIN1230")));
		hourlyUsage.setCostHr1245(CommonUtil.getBlankString(rs.getString("COST_MIN1245")));
		
		hourlyUsage.setCostHr1300(CommonUtil.getBlankString(rs.getString("COST_MIN1300")));
		hourlyUsage.setCostHr1315(CommonUtil.getBlankString(rs.getString("COST_MIN1315")));
		hourlyUsage.setCostHr1330(CommonUtil.getBlankString(rs.getString("COST_MIN1330")));
		hourlyUsage.setCostHr1345(CommonUtil.getBlankString(rs.getString("COST_MIN1345")));
		
		
		hourlyUsage.setCostHr1400(CommonUtil.getBlankString(rs.getString("COST_MIN1400")));
		hourlyUsage.setCostHr1415(CommonUtil.getBlankString(rs.getString("COST_MIN1415")));
		hourlyUsage.setCostHr1430(CommonUtil.getBlankString(rs.getString("COST_MIN1430")));
		hourlyUsage.setCostHr1445(CommonUtil.getBlankString(rs.getString("COST_MIN1445")));
		
		
		hourlyUsage.setCostHr1500(CommonUtil.getBlankString(rs.getString("COST_MIN1500")));
		hourlyUsage.setCostHr1515(CommonUtil.getBlankString(rs.getString("COST_MIN1515")));
		hourlyUsage.setCostHr1530(CommonUtil.getBlankString(rs.getString("COST_MIN1530")));
		hourlyUsage.setCostHr1545(CommonUtil.getBlankString(rs.getString("COST_MIN1545")));
		
		hourlyUsage.setCostHr1600(CommonUtil.getBlankString(rs.getString("COST_MIN1600")));
		hourlyUsage.setCostHr1615(CommonUtil.getBlankString(rs.getString("COST_MIN1615")));
		hourlyUsage.setCostHr1630(CommonUtil.getBlankString(rs.getString("COST_MIN1630")));
		hourlyUsage.setCostHr1645(CommonUtil.getBlankString(rs.getString("COST_MIN1645")));
		
		hourlyUsage.setCostHr1700(CommonUtil.getBlankString(rs.getString("COST_MIN1700")));
		hourlyUsage.setCostHr1715(CommonUtil.getBlankString(rs.getString("COST_MIN1715")));
		hourlyUsage.setCostHr1730(CommonUtil.getBlankString(rs.getString("COST_MIN1730")));
		hourlyUsage.setCostHr1745(CommonUtil.getBlankString(rs.getString("COST_MIN1745")));
		
		hourlyUsage.setCostHr1800(CommonUtil.getBlankString(rs.getString("COST_MIN1800")));
		hourlyUsage.setCostHr1815(CommonUtil.getBlankString(rs.getString("COST_MIN1815")));
		hourlyUsage.setCostHr1830(CommonUtil.getBlankString(rs.getString("COST_MIN1830")));
		hourlyUsage.setCostHr1845(CommonUtil.getBlankString(rs.getString("COST_MIN1845")));
		
		
		hourlyUsage.setCostHr1900(CommonUtil.getBlankString(rs.getString("COST_MIN1900")));
		hourlyUsage.setCostHr1915(CommonUtil.getBlankString(rs.getString("COST_MIN1915")));
		hourlyUsage.setCostHr1930(CommonUtil.getBlankString(rs.getString("COST_MIN1930")));
		hourlyUsage.setCostHr1945(CommonUtil.getBlankString(rs.getString("COST_MIN1945")));
		
		hourlyUsage.setCostHr2000(CommonUtil.getBlankString(rs.getString("COST_MIN2000")));
		hourlyUsage.setCostHr2015(CommonUtil.getBlankString(rs.getString("COST_MIN2015")));
		hourlyUsage.setCostHr2030(CommonUtil.getBlankString(rs.getString("COST_MIN2030")));
		hourlyUsage.setCostHr2045(CommonUtil.getBlankString(rs.getString("COST_MIN2045")));
		
		hourlyUsage.setCostHr2100(CommonUtil.getBlankString(rs.getString("COST_MIN2100")));
		hourlyUsage.setCostHr2115(CommonUtil.getBlankString(rs.getString("COST_MIN2115")));
		hourlyUsage.setCostHr2130(CommonUtil.getBlankString(rs.getString("COST_MIN2130")));
		hourlyUsage.setCostHr2145(CommonUtil.getBlankString(rs.getString("COST_MIN2145")));
		
		hourlyUsage.setCostHr2200(CommonUtil.getBlankString(rs.getString("COST_MIN2200")));
		hourlyUsage.setCostHr2215(CommonUtil.getBlankString(rs.getString("COST_MIN2215")));
		hourlyUsage.setCostHr2230(CommonUtil.getBlankString(rs.getString("COST_MIN2230")));
		hourlyUsage.setCostHr2245(CommonUtil.getBlankString(rs.getString("COST_MIN2245")));
		
		hourlyUsage.setCostHr2300(CommonUtil.getBlankString(rs.getString("COST_MIN2300")));
		hourlyUsage.setCostHr2315(CommonUtil.getBlankString(rs.getString("COST_MIN2315")));
		hourlyUsage.setCostHr2330(CommonUtil.getBlankString(rs.getString("COST_MIN2330")));
		hourlyUsage.setCostHr2345(CommonUtil.getBlankString(rs.getString("COST_MIN2345")));
		
		hourlyUsage.setCostHr2400(CommonUtil.getBlankString(rs.getString("COST_MIN2400")));
		
		////ESIID=1008901012189134726100 BUS_PARTNER=0011147012 CONTRACT_ACCT_ID=000016566188 CONTRACT_ID=0074238961 ACTUAL_DAY=2020-10-23 00:00:00.0 YEAR_WEEK_NO=202043 USAGE_MIN0015=1.446 USAGE_MIN0030=0.422 USAGE_MIN0045=1.272 USAGE_MIN0100=0.833 USAGE_MIN0115=0.667 USAGE_MIN0130=1.168 USAGE_MIN0145=0.46 USAGE_MIN0200=0.993 USAGE_MIN0215=0.367 USAGE_MIN0230=0.954 USAGE_MIN0245=0.574 USAGE_MIN0300=0.35 USAGE_MIN0315=1.057 USAGE_MIN0330=0.547 USAGE_MIN0345=0.544 USAGE_MIN0400=1.065 USAGE_MIN0415=0.241 USAGE_MIN0430=0.833 USAGE_MIN0445=0.268 USAGE_MIN0500=1.064 USAGE_MIN0515=0.435 USAGE_MIN0530=0.484 USAGE_MIN0545=0.792 USAGE_MIN0600=0.461 USAGE_MIN0615=0.379 USAGE_MIN0630=0.564 USAGE_MIN0645=0.728 USAGE_MIN0700=0.627 USAGE_MIN0715=0.822 USAGE_MIN0730=0.176 USAGE_MIN0745=0.187 USAGE_MIN0800=0.555 USAGE_MIN0815=0.183 USAGE_MIN0830=0.21 USAGE_MIN0845=0.195 USAGE_MIN0900=0.554 USAGE_MIN0915=0.199 USAGE_MIN0930=0.206 USAGE_MIN0945=0.413 USAGE_MIN1000=0.444 USAGE_MIN1015=0.202 USAGE_MIN1030=0.269 USAGE_MIN1045=0.651 USAGE_MIN1100=0.227 USAGE_MIN1115=0.307 USAGE_MIN1130=0.654 USAGE_MIN1145=0.261 USAGE_MIN1200=0.282 USAGE_MIN1215=0.678 USAGE_MIN1230=0.324 USAGE_MIN1245=0.295 USAGE_MIN1300=0.648 USAGE_MIN1315=0.334 USAGE_MIN1330=0.311 USAGE_MIN1345=0.729 USAGE_MIN1400=0.329 USAGE_MIN1415=0.348 USAGE_MIN1430=0.778 USAGE_MIN1445=0.246 USAGE_MIN1500=0.368 USAGE_MIN1515=0.564 USAGE_MIN1530=0.286 USAGE_MIN1545=0.606 USAGE_MIN1600=0.38 USAGE_MIN1615=0.297 USAGE_MIN1630=0.748 USAGE_MIN1645=0.311 USAGE_MIN1700=0.315 USAGE_MIN1715=0.789 USAGE_MIN1730=0.288 USAGE_MIN1745=0.849 USAGE_MIN1800=0.482 USAGE_MIN1815=0.928 USAGE_MIN1830=0.743 USAGE_MIN1845=0.858 USAGE_MIN1900=1.114 USAGE_MIN1915=0.859 USAGE_MIN1930=0.873 USAGE_MIN1945=1.389 USAGE_MIN2000=0.829 USAGE_MIN2015=0.97 USAGE_MIN2030=1.062 USAGE_MIN2045=0.72 USAGE_MIN2100=1.19 USAGE_MIN2115=0.923 USAGE_MIN2130=0.772 USAGE_MIN2145=1.03 USAGE_MIN2200=1.28 USAGE_MIN2215=0.957 USAGE_MIN2230=1.018 USAGE_MIN2245=0.762 USAGE_MIN2300=0.965 USAGE_MIN2315=0.959 USAGE_MIN2330=0.795 USAGE_MIN2345=0.806 USAGE_MIN2400=0.278 COST_MIN0015=3.29 COST_MIN0030=0.96 COST_MIN0045=2.85 COST_MIN0100=1.82 COST_MIN0115=1.45 COST_MIN0130=2.45 COST_MIN0145=0.94 COST_MIN0200=1.93 COST_MIN0215=0.73 COST_MIN0230=1.88 COST_MIN0245=1.1 COST_MIN0300=0.67 COST_MIN0315=2.16 COST_MIN0330=1.13 COST_MIN0345=1.1 COST_MIN0400=2.17 COST_MIN0415=0.5 COST_MIN0430=1.73 COST_MIN0445=0.57 COST_MIN0500=2.28 COST_MIN0515=0.97 COST_MIN0530=1.08 COST_MIN0545=1.87 COST_MIN0600=1.06 COST_MIN0615=1.32 COST_MIN0630=2.25 COST_MIN0645=2.72 COST_MIN0700=1.81 COST_MIN0715=1.88 COST_MIN0730=0.38 COST_MIN0745=0.4 COST_MIN0800=1.27 COST_MIN0815=0.46 COST_MIN0830=0.55 COST_MIN0845=0.52 COST_MIN0900=1.53 COST_MIN0915=0.56 COST_MIN0930=0.57 COST_MIN0945=1.18 COST_MIN1000=1.22 COST_MIN1015=0.56 COST_MIN1030=0.82 COST_MIN1045=2.02 COST_MIN1100=0.69 COST_MIN1115=0.93 COST_MIN1130=1.86 COST_MIN1145=0.83 COST_MIN1200=1.04 COST_MIN1215=2.22 COST_MIN1230=1.21 COST_MIN1245=1.25 COST_MIN1300=3.36 COST_MIN1315=1.23 COST_MIN1330=null COST_MIN1345=null COST_MIN1400=null COST_MIN1415=null COST_MIN1430=null COST_MIN1445=null COST_MIN1500=null COST_MIN1515=null COST_MIN1530=null COST_MIN1545=null COST_MIN1600=null COST_MIN1615=null COST_MIN1630=null COST_MIN1645=null COST_MIN1700=null COST_MIN1715=null COST_MIN1730=null COST_MIN1745=null COST_MIN1800=null COST_MIN1815=null COST_MIN1830=null COST_MIN1845=null COST_MIN1900=null COST_MIN1915=null COST_MIN1930=null COST_MIN1945=null COST_MIN2000=null COST_MIN2015=null COST_MIN2030=null COST_MIN2045=null COST_MIN2100=null COST_MIN2115=null COST_MIN2130=null COST_MIN2145=null COST_MIN2200=null COST_MIN2215=null COST_MIN2230=null COST_MIN2245=null COST_MIN2300=null COST_MIN2315=null COST_MIN2330=null COST_MIN2345=null COST_MIN2400=null 
		//TOTAL_DAY_USAGE=59.975 TOTAL_DAY_COST=null DAY_TEMP_HIGH=null DAY_TEMP_LOW=null
		
		hourlyUsage.setDayUsg(CommonUtil.getBlankString(rs.getString("TOTAL_DAY_USAGE")));
		hourlyUsage.setDayCst(CommonUtil.getBlankString(rs.getString("TOTAL_DAY_COST")));
		hourlyUsage.setDayTempHigh(CommonUtil.getDefaultTemperature(rs.getString("DAY_TEMP_HIGH")));
		hourlyUsage.setDayTempLow(CommonUtil.getDefaultTemperature(rs.getString("DAY_TEMP_LOW")));


		return hourlyUsage;
	}

}
