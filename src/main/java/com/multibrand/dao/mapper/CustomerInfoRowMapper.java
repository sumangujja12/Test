package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.multibrand.dataObjects.Contract;


public class CustomerInfoRowMapper implements RowMapper<Contract> {

	/**
	 * Create contract objects
	 * 
	 */
	public Contract mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		Contract c = new Contract();
		c.setPointofDeliveryID(rs.getString("esi_id"));
		c.setContractEndDate(rs.getDate("contract_end_date"));
		c.setContractStartDate(rs.getDate("contract_start_date"));
		c.setContractNumber(rs.getString("contract_id"));
		c.setTDSP(rs.getString("tdsp_code"));
		return c;
	}
}
