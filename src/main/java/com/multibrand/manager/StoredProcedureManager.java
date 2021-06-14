package com.multibrand.manager;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.multibrand.dao.ResultObject;

/**
 * This factory class is used to produce stored procedure.
 * 
 * It creates StoredProcedure object with the specified input/output parameters, 
 * compiles and returns the StoredProcedure object.
 * 
 * @author smuruga1
 * @version 1.0
 * 
 * 
 */
@Service("storedProcedure")
public class StoredProcedureManager implements com.multibrand.util.DBConstants{
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	// Private instance
	private static StoredProcedureManager instance;

	// Get Singleton instance
	public synchronized static StoredProcedureManager getInstance() {
		if (instance == null)
			instance = new StoredProcedureManager();
		return instance;
	}

	/**
	 * Returns the GENERIC/COMMON compiled procedure based on the procedure name
	 * and input and output parameters details.
	 * 
	 * @author smuruga1
	 * 
	 * @param jdbcTemplate
	 * @param storedProcName
	 * @param inParamValuesMap
	 * @param inParamTypeMap
	 * @param outParamTypeMap
	 * @param firstParam - Indicates Input or Output parameters are specified first in procedure.
	 * @return BaseStoredProcedure
	 */
	public BaseStoredProcedure createStoredProcedure(JdbcTemplate jdbcTemplate,
			String storedProcName, Map<String, Object> inParamValuesMap,
			final Map<String, Integer> inParamTypeMap,
			final Map<String, ResultObject> outParamTypeMap, final String firstParam) {

		String METHOD_NAME = " StoredProcedureManager: createStoredProcedure(..)";
		logger.debug("START:" + METHOD_NAME);

		BaseStoredProcedure sp = null;

		// START Creating the stored procedure IMPL
		sp = new BaseStoredProcedure(jdbcTemplate, storedProcName, inParamValuesMap) {
			@Override
			public BaseStoredProcedure createSp() {
				// Set input and parameters type
				declareParameters(inParamTypeMap, outParamTypeMap, firstParam);
				
				// compile the stored procedure
				compile();
				
				return this;
			}
		}.createSp();

		// END Creating the stored procedure IMPL
		logger.debug("END:" + METHOD_NAME);

		return sp;
	}
	
	/**
	 * Returns the GENERIC/COMMON compiled procedure based on the procedure name
	 * and input and output parameters details 
	 * 
	 * Note: Default type, INPUT parameters are first.
	 * 
	 * @author smuruga1
	 * 
	 * @param jdbcTemplate
	 * @param storedProcName
	 * @param inParamValuesMap
	 * @param inParamTypeMap
	 * @param outParamTypeMap
	 * 
	 * @return BaseStoredProcedure
	 */
	public BaseStoredProcedure createStoredProcedure(JdbcTemplate jdbcTemplate,
			String storedProcName, Map<String, Object> inParamValuesMap,
			final Map<String, Integer> inParamTypeMap,
			final Map<String, ResultObject> outParamTypeMap) {

		String METHOD_NAME = " StoredProcedureManager: createStoredProcedure(..)";
		logger.debug("START:" + METHOD_NAME);

		BaseStoredProcedure sp 
				= this.createStoredProcedure(jdbcTemplate, storedProcName, inParamValuesMap, 
						inParamTypeMap, outParamTypeMap, INPUT);
		
		// END Creating the stored procedure IMPL
		logger.debug("END:" + METHOD_NAME);
		
		return sp;
	}
	
	
	
	
	
	
}
