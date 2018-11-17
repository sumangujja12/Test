package com.multibrand.dao.jdbc.sp;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.multibrand.util.DBConstants;

public class GenericStoredProcedure extends StoredProcedure implements
		DBConstants {

	public GenericStoredProcedure(JdbcTemplate jdbcTemplate, String spName,
			Map<String, Object> inParams, Map<String, Integer> outParams,
			String operation) {
		super(jdbcTemplate, spName);

		// declare parameters
		declareParameters(operation, inParams, outParams);

		compile();
	}

	private void declareParameters(String operation,
			Map<String, Object> inParams, Map<String, Integer> outParams) {

		for (String key : inParams.keySet()) {
			declareParameter(new SqlParameter(key, OracleTypes.VARCHAR));
		}

		for (String key : outParams.keySet()) {
			declareParameter(new SqlOutParameter(key, outParams.get(key)));
		}
	}

}
