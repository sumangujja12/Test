package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;
import com.multibrand.util.DBConstants;


public class TCSPersonalizedFlagsRowMapper implements RowMapper<TCSPersonalizedFlagsDTO>{
	public TCSPersonalizedFlagsDTO mapRow(ResultSet rs, int rowNum)
	throws SQLException {		
		
		TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO = new TCSPersonalizedFlagsDTO();
		
		tcsPersonalizedFlagsDTO.setOwnershipFlag(rs.getString(DBConstants.DB_OWNERSHIP_FLG));
		tcsPersonalizedFlagsDTO.setSecurityEligibleFlag(rs.getString(DBConstants.DB_SECURITY_ELIGIBLE_FLG));
		tcsPersonalizedFlagsDTO.setCashPaymentsFlag(rs.getString(DBConstants.DB_CASH_PAYMENTS_FLG));
		tcsPersonalizedFlagsDTO.setContractElapsedDays(rs.getString(DBConstants.DB_CONTR_ELAPSED_DAYS));
		tcsPersonalizedFlagsDTO.setReliantAppUserFlag(rs.getString(DBConstants.DB_RELIANT_APP_USER_FLG));
		tcsPersonalizedFlagsDTO.setSecurityActive(rs.getString(DBConstants.DB_SECURITY_ACTIVE));
		tcsPersonalizedFlagsDTO.setPolrCustomer(rs.getString(DBConstants.DB_POLR_CUST));
		return tcsPersonalizedFlagsDTO;
		
	}
}
