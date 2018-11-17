package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.IRWDTO;

public class IRWReadOAMMapper implements RowMapper {
	
	@Override
	public IRWDTO mapRow(ResultSet rs, int index) throws SQLException {

		IRWDTO obj = new IRWDTO();
		
		obj.setMessageName(rs.getString("msg_name1"));
		return obj;
	}

}
