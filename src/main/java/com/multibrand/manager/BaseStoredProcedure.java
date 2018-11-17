package com.multibrand.manager;

import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.multibrand.dao.ResultObject;
import com.multibrand.util.DBConstants;


/**
 * This base class is typically used by StoredProcedureManager to execute all
 * procedures
 * 
 * @author Siva Murugan M
 * 
 */

public abstract class BaseStoredProcedure extends StoredProcedure implements DBConstants  {

	private Map<String, Object> inputParams = null;
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	protected abstract BaseStoredProcedure createSp();

	public BaseStoredProcedure(JdbcTemplate jdbcTemplate,
			String storedProcName, Map<String, Object> inputParams) {

		super(jdbcTemplate, storedProcName);
		this.inputParams = inputParams;
	}

	public Map<String, Object> execute() {
		logger.debug(" BaseStoredProcedure - execute() : Executing stored proc..... ");
		
		return execute(inputParams);
	}

	/**
	 * 
	 * Declares input data type(s) and output data type(s) of procedure
	 * parameters which can be generic to all.
	 * 
	 * @param operation
	 * @param inParams
	 * @param outParams
	 * @param firstParam 
	 */
	protected void declareParameters(Map<String, Integer> inParams,
			Map<String, ResultObject> outParams, String firstParam) {

		com.multibrand.dao.ResultObject resultType = null;
		Integer paramDataType = null;
		Object resultHandler = null;
		boolean bInputParamFirst = true;
		
		if(null!=firstParam && OUTPUT.equals(firstParam))	bInputParamFirst=false;
		
		//INPUT PARAMS
		if(bInputParamFirst){
			for (String paramName : inParams.keySet()) {
				declareParameter(new SqlParameter(paramName, inParams.get(paramName)));
			}
		}
		
		//OUTPUT PARAMS
		for (String paramName : outParams.keySet()) {
			resultType = outParams.get(paramName);
			paramDataType = resultType.getSqlType();
			resultHandler = resultType.getResultHandler();

			if (resultHandler != null) {
				// Row Mapper special parser for result set
				if (resultHandler instanceof RowMapper) {
					declareParameter(new SqlOutParameter(paramName, paramDataType,
							(RowMapper<?>) resultHandler));
				}

				// Row Mapper special parser for result set
				if (resultHandler instanceof RowCallbackHandler) {
					declareParameter(new SqlOutParameter(paramName, paramDataType,
							(RowCallbackHandler) resultHandler));
				}

				// Row Mapper special parser for result set
				if (resultHandler instanceof ResultSetExtractor) {
					declareParameter(new SqlOutParameter(paramName, paramDataType,
							(ResultSetExtractor<?>) resultHandler));
				}

			} else {
				declareParameter(new SqlOutParameter(paramName, paramDataType));
			}
		}
		
		//INPUT PARAMS
		if(!bInputParamFirst){
			for (String paramName : inParams.keySet()) {
				declareParameter(new SqlParameter(paramName, inParams.get(paramName)));
			}
		}
	}
	
}
