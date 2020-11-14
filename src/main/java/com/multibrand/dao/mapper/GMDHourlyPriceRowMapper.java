package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.Constants;
import com.multibrand.vo.response.gmd.HourlyPrice;

public class GMDHourlyPriceRowMapper implements RowMapper<HourlyPrice>, Constants
{

	Logger logger = LogManager.getLogger("NRGREST_Logger");

	@Override
	public HourlyPrice mapRow(ResultSet rs, int rowNum) throws SQLException
	{

		HourlyPrice hourlyPrice = new HourlyPrice();
		
		hourlyPrice.setPriceHr0015(rs.getString("SPP_MIN0015"));
		hourlyPrice.setPriceHr0030(rs.getString("SPP_MIN0030"));
		hourlyPrice.setPriceHr0045(rs.getString("SPP_MIN0045"));
		
		hourlyPrice.setPriceHr0100(rs.getString("SPP_MIN0100"));
		hourlyPrice.setPriceHr0115(rs.getString("SPP_MIN0115"));
		hourlyPrice.setPriceHr0130(rs.getString("SPP_MIN0130"));
		hourlyPrice.setPriceHr0145(rs.getString("SPP_MIN0145"));
		
		hourlyPrice.setPriceHr0200(rs.getString("SPP_MIN0200"));
		hourlyPrice.setPriceHr0215(rs.getString("SPP_MIN0215"));
		hourlyPrice.setPriceHr0230(rs.getString("SPP_MIN0230"));
		hourlyPrice.setPriceHr0245(rs.getString("SPP_MIN0245"));
		
		hourlyPrice.setPriceHr0300(rs.getString("SPP_MIN0300"));
		hourlyPrice.setPriceHr0315(rs.getString("SPP_MIN0315"));
		hourlyPrice.setPriceHr0330(rs.getString("SPP_MIN0330"));
		hourlyPrice.setPriceHr0345(rs.getString("SPP_MIN0345"));
		
		hourlyPrice.setPriceHr0400(rs.getString("SPP_MIN0400"));
		hourlyPrice.setPriceHr0415(rs.getString("SPP_MIN0415"));
		hourlyPrice.setPriceHr0430(rs.getString("SPP_MIN0430"));
		hourlyPrice.setPriceHr0445(rs.getString("SPP_MIN0445"));
		
		hourlyPrice.setPriceHr0500(rs.getString("SPP_MIN0500"));
		hourlyPrice.setPriceHr0515(rs.getString("SPP_MIN0515"));
		hourlyPrice.setPriceHr0530(rs.getString("SPP_MIN0530"));
		hourlyPrice.setPriceHr0545(rs.getString("SPP_MIN0545"));
		
		hourlyPrice.setPriceHr0600(rs.getString("SPP_MIN0600"));
		hourlyPrice.setPriceHr0615(rs.getString("SPP_MIN0615"));
		hourlyPrice.setPriceHr0630(rs.getString("SPP_MIN0630"));
		hourlyPrice.setPriceHr0645(rs.getString("SPP_MIN0645"));
		
		hourlyPrice.setPriceHr0700(rs.getString("SPP_MIN0700"));
		hourlyPrice.setPriceHr0715(rs.getString("SPP_MIN0715"));
		hourlyPrice.setPriceHr0730(rs.getString("SPP_MIN0730"));
		hourlyPrice.setPriceHr0745(rs.getString("SPP_MIN0745"));
		
		hourlyPrice.setPriceHr0800(rs.getString("SPP_MIN0800"));
		hourlyPrice.setPriceHr0815(rs.getString("SPP_MIN0815"));
		hourlyPrice.setPriceHr0830(rs.getString("SPP_MIN0830"));
		hourlyPrice.setPriceHr0845(rs.getString("SPP_MIN0845"));
		
		hourlyPrice.setPriceHr0900(rs.getString("SPP_MIN0900"));
		hourlyPrice.setPriceHr0915(rs.getString("SPP_MIN0915"));
		hourlyPrice.setPriceHr0930(rs.getString("SPP_MIN0930"));
		hourlyPrice.setPriceHr0945(rs.getString("SPP_MIN0945"));
		
		hourlyPrice.setPriceHr1000(rs.getString("SPP_MIN1000"));
		hourlyPrice.setPriceHr1015(rs.getString("SPP_MIN1015"));
		hourlyPrice.setPriceHr1030(rs.getString("SPP_MIN1030"));
		hourlyPrice.setPriceHr1045(rs.getString("SPP_MIN1045"));
		
		hourlyPrice.setPriceHr1100(rs.getString("SPP_MIN1100"));
		hourlyPrice.setPriceHr1115(rs.getString("SPP_MIN1115"));
		hourlyPrice.setPriceHr1130(rs.getString("SPP_MIN1130"));
		hourlyPrice.setPriceHr1145(rs.getString("SPP_MIN1145"));
		
		hourlyPrice.setPriceHr1200(rs.getString("SPP_MIN1200"));
		hourlyPrice.setPriceHr1215(rs.getString("SPP_MIN1215"));
		hourlyPrice.setPriceHr1230(rs.getString("SPP_MIN1230"));
		hourlyPrice.setPriceHr1245(rs.getString("SPP_MIN1245"));	
		
		
		hourlyPrice.setPriceHr1300(rs.getString("SPP_MIN1300"));
		hourlyPrice.setPriceHr1315(rs.getString("SPP_MIN1315"));
		hourlyPrice.setPriceHr1330(rs.getString("SPP_MIN1330"));
		hourlyPrice.setPriceHr1345(rs.getString("SPP_MIN1345"));
		
		hourlyPrice.setPriceHr1400(rs.getString("SPP_MIN1400"));
		hourlyPrice.setPriceHr1415(rs.getString("SPP_MIN1415"));
		hourlyPrice.setPriceHr1430(rs.getString("SPP_MIN1430"));
		hourlyPrice.setPriceHr1445(rs.getString("SPP_MIN1445"));
		
		hourlyPrice.setPriceHr1500(rs.getString("SPP_MIN1500"));
		hourlyPrice.setPriceHr1515(rs.getString("SPP_MIN1515"));
		hourlyPrice.setPriceHr1530(rs.getString("SPP_MIN1530"));
		hourlyPrice.setPriceHr1545(rs.getString("SPP_MIN1545"));
		
		hourlyPrice.setPriceHr1600(rs.getString("SPP_MIN1600"));
		hourlyPrice.setPriceHr1615(rs.getString("SPP_MIN1615"));
		hourlyPrice.setPriceHr1630(rs.getString("SPP_MIN1630"));
		hourlyPrice.setPriceHr1645(rs.getString("SPP_MIN1645"));
		
		hourlyPrice.setPriceHr1700(rs.getString("SPP_MIN1700"));
		hourlyPrice.setPriceHr1715(rs.getString("SPP_MIN1715"));
		hourlyPrice.setPriceHr1730(rs.getString("SPP_MIN1730"));
		hourlyPrice.setPriceHr1745(rs.getString("SPP_MIN1745"));
		
		hourlyPrice.setPriceHr1800(rs.getString("SPP_MIN1800"));
		hourlyPrice.setPriceHr1815(rs.getString("SPP_MIN1815"));
		hourlyPrice.setPriceHr1830(rs.getString("SPP_MIN1830"));
		hourlyPrice.setPriceHr1845(rs.getString("SPP_MIN1845"));
		
		hourlyPrice.setPriceHr1900(rs.getString("SPP_MIN1900"));
		hourlyPrice.setPriceHr1915(rs.getString("SPP_MIN1915"));
		hourlyPrice.setPriceHr1930(rs.getString("SPP_MIN1930"));
		hourlyPrice.setPriceHr1945(rs.getString("SPP_MIN1945"));
		
		hourlyPrice.setPriceHr2000(rs.getString("SPP_MIN2000"));
		hourlyPrice.setPriceHr2015(rs.getString("SPP_MIN2015"));
		hourlyPrice.setPriceHr2030(rs.getString("SPP_MIN2030"));
		hourlyPrice.setPriceHr2045(rs.getString("SPP_MIN2045"));
		
		hourlyPrice.setPriceHr2100(rs.getString("SPP_MIN2100"));
		hourlyPrice.setPriceHr2115(rs.getString("SPP_MIN2115"));
		hourlyPrice.setPriceHr2130(rs.getString("SPP_MIN2130"));
		hourlyPrice.setPriceHr2145(rs.getString("SPP_MIN2145"));
		
		hourlyPrice.setPriceHr2200(rs.getString("SPP_MIN2200"));
		hourlyPrice.setPriceHr2215(rs.getString("SPP_MIN2215"));
		hourlyPrice.setPriceHr2230(rs.getString("SPP_MIN2230"));
		hourlyPrice.setPriceHr2245(rs.getString("SPP_MIN2245"));
		
		hourlyPrice.setPriceHr2300(rs.getString("SPP_MIN2300"));
		hourlyPrice.setPriceHr2315(rs.getString("SPP_MIN2315"));
		hourlyPrice.setPriceHr2330(rs.getString("SPP_MIN2330"));
		hourlyPrice.setPriceHr2345(rs.getString("SPP_MIN2345"));
		
		
		hourlyPrice.setPriceHr2400(rs.getString("SPP_MIN2400"));
		
		return hourlyPrice;
	}

}
