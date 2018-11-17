package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.response.TCSBPDetailsDTO;
import com.multibrand.util.DBConstants;


public class TCSBPDetailsRowMapper implements RowMapper<TCSBPDetailsDTO>{
	public TCSBPDetailsDTO mapRow(ResultSet rs, int rowNum)
	throws SQLException {		
		
		TCSBPDetailsDTO tcsBPDetailsDTO = new TCSBPDetailsDTO();
		
		tcsBPDetailsDTO.setBpNumber(rs.getString(DBConstants.DB_BUSINESS_PARTNER_ID));
		tcsBPDetailsDTO.setCaNumber(rs.getString(DBConstants.DB_CONTRACT_ACCOUNT_NUMBER));
		tcsBPDetailsDTO.setCompanyCode(rs.getString(DBConstants.DB_COMPANY_CODE));
		tcsBPDetailsDTO.setLeaseId(rs.getString(DBConstants.DB_LEASE_ID));
		
		return tcsBPDetailsDTO;
	}
}
