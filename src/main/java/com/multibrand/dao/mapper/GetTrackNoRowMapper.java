package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.GMDServiceLocationDetailsDTO;


public class GetTrackNoRowMapper implements RowMapper<GMDServiceLocationDetailsDTO> {

	
	public GMDServiceLocationDetailsDTO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		GMDServiceLocationDetailsDTO gmdServiceLocationDetailsDTO = new GMDServiceLocationDetailsDTO();
		gmdServiceLocationDetailsDTO.setTrackingId("TRACKING_NUMBER");

		return gmdServiceLocationDetailsDTO;
	}
}
