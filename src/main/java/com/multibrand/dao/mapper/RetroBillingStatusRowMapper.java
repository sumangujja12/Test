package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.response.RetroEligibilityResponse;

public class RetroBillingStatusRowMapper implements RowMapper<RetroEligibilityResponse>{

	/**
	 *
	 * Returns RETRO AVERAGE BILLING ELIGIBILITY STATUS
	 * 
	 */
	public RetroEligibilityResponse mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		RetroEligibilityResponse retroDto = new RetroEligibilityResponse();
		
		retroDto.setRetroSignupEligible(Boolean.parseBoolean(rs.getString("out_retro_elig_status")));

		return retroDto;
	}
}
