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
		
		hourlyPrice.setPriceHr01(rs.getString("SPP_HR01"));
		hourlyPrice.setPriceHr02(rs.getString("SPP_HR02"));
		hourlyPrice.setPriceHr03(rs.getString("SPP_HR03"));
		
		hourlyPrice.setPriceHr04(rs.getString("SPP_HR04"));
		hourlyPrice.setPriceHr05(rs.getString("SPP_HR05"));
		hourlyPrice.setPriceHr06(rs.getString("SPP_HR05"));	
		
		hourlyPrice.setPriceHr07(rs.getString("SPP_HR07"));
		hourlyPrice.setPriceHr08(rs.getString("SPP_HR08"));
		hourlyPrice.setPriceHr09(rs.getString("SPP_HR09"));
		hourlyPrice.setPriceHr10(rs.getString("SPP_HR10"));
		hourlyPrice.setPriceHr11(rs.getString("SPP_HR11"));
		hourlyPrice.setPriceHr12(rs.getString("SPP_HR12"));
		hourlyPrice.setPriceHr13(rs.getString("SPP_HR13"));
		hourlyPrice.setPriceHr14(rs.getString("SPP_HR14"));
		hourlyPrice.setPriceHr15(rs.getString("SPP_HR15"));
		hourlyPrice.setPriceHr16(rs.getString("SPP_HR16"));
		hourlyPrice.setPriceHr17(rs.getString("SPP_HR17"));
		hourlyPrice.setPriceHr18(rs.getString("SPP_HR18"));
		hourlyPrice.setPriceHr19(rs.getString("SPP_HR19"));
		hourlyPrice.setPriceHr20(rs.getString("SPP_HR20"));
		hourlyPrice.setPriceHr21(rs.getString("SPP_HR21"));
		hourlyPrice.setPriceHr22(rs.getString("SPP_HR22"));
		hourlyPrice.setPriceHr23(rs.getString("SPP_HR23"));
		hourlyPrice.setPriceHr24(rs.getString("SPP_HR24"));
		
		return hourlyPrice;
	}

}
