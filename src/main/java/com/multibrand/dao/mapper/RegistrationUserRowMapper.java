package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.vo.request.UserRegistrationRequest;

public class RegistrationUserRowMapper implements RowMapper<UserRegistrationRequest>
{

	@Override
	public UserRegistrationRequest mapRow(ResultSet rs, int row) throws SQLException
	{
		UserRegistrationRequest registerVO = new UserRegistrationRequest();
		registerVO.setUserName(rs.getString("user_login_id"));
		
		return registerVO;
	}

}
