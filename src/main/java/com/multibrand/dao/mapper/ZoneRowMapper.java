package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.gmd.Zone;

public class ZoneRowMapper implements RowMapper<Zone>, Constants
{

	@Override
	public Zone mapRow(ResultSet rs, int count) throws SQLException
	{

		Zone zone = new Zone();

		zone.setZoneId(CommonUtil.getBlankString(rs.getString("CNGSTN_ZN_DS")));
		
		
		return zone;
	}

}
