package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.multibrand.vo.response.UserInfoResponse;


public class UserInfoRowMapper implements RowMapper<UserInfoResponse> {

	/**
	 * Pass user "unique ID" then fetch the user Profile information through
	 * data access layer [DAO] layer from GME CPDB Schema. returns only
	 * Active CA’s, userName, and legacy CA information.
	 * 
	 */
	public UserInfoResponse mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setAccountNumber(rs.getString("CA_NUMBER"));
		//userInfoResponse.setUserName(rs.getString("BUSINESS_PARTNER_ID"));
		//userInfoResponse.setLegacy_CA("legacy_CA");
		

		return userInfoResponse;
	}
}
