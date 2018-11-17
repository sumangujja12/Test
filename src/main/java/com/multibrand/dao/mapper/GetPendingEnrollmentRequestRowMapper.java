package com.multibrand.dao.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.util.DBConstants;

public class GetPendingEnrollmentRequestRowMapper implements RowMapper<CheckPendingServiceResponse> ,DBConstants {

	public CheckPendingServiceResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
		CheckPendingServiceResponse pendingServiceResponse = new CheckPendingServiceResponse();
		pendingServiceResponse.setTrackNo(rs.getString(OUT_TRACKING_NUMBER));
		pendingServiceResponse.setFirstName(rs.getString(OUT_NAME_FIRST));
		pendingServiceResponse.setMiddleInitial(rs.getString(OUT_NAME_MIDDLE));
		pendingServiceResponse.setLastName(rs.getString(OUT_NAME_LAST));
		pendingServiceResponse.setStreetAddress(rs.getString(OUT_SERVICE_ADD_LINE_1));
		pendingServiceResponse.setUnitNum(rs.getString(OUT_SERVICE_ADD_LINE_2));
		pendingServiceResponse.setCity(rs.getString(OUT_SERVICE_CITY));
		pendingServiceResponse.setZipcode(rs.getString(OUT_SERVICE_ZIP));
		pendingServiceResponse.setCreationDate(rs.getTimestamp((OUT_CREATION_DATE)));
		return pendingServiceResponse;
	}	
}
