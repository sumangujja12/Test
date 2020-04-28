package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.gmd.AllTimePrice;

public class GMDAllTimePriceRowMapper implements RowMapper<AllTimePrice>, Constants
{

	Logger logger = LogManager.getLogger("NRGREST_Logger");

	@Override
	public AllTimePrice mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		
		AllTimePrice allTimePrice = new AllTimePrice();
		
		allTimePrice.setAvgAllTimePrice(rs.getString(1));
		
		
		return allTimePrice;
	}

}
