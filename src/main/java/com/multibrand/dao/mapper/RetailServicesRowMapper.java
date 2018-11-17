package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.RetailServicesDTO;

public class RetailServicesRowMapper implements RowMapper<RetailServicesDTO> {
	
	@Override
	public RetailServicesDTO mapRow(ResultSet rs, int index) throws SQLException {

		RetailServicesDTO obj = new RetailServicesDTO();
		
		obj.setHhincome(rs.getString("hh_income"));
		obj.setHomeAge(rs.getString("age_of_home"));
		
		return obj;
	}

}
