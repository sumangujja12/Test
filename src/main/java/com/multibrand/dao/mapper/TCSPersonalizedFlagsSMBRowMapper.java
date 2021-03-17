package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.response.TCSPersonalizedFlagsSMBDTO;
import com.multibrand.util.DBConstants;


public class TCSPersonalizedFlagsSMBRowMapper implements RowMapper<TCSPersonalizedFlagsSMBDTO>{
	public TCSPersonalizedFlagsSMBDTO mapRow(ResultSet rs, int rowNum)
	throws SQLException {		
		
		TCSPersonalizedFlagsSMBDTO tcsPersonalizedFlagsSMBDTO = new TCSPersonalizedFlagsSMBDTO();
		
		tcsPersonalizedFlagsSMBDTO.setPolrCustomer(rs.getString(DBConstants.DB_POLR_CUST));
		return tcsPersonalizedFlagsSMBDTO;
		
	}
}
