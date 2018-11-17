
package com.multibrand.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.ResultObject;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.Constants;

import oracle.jdbc.OracleTypes;

@Repository("cslrProfileDAO")
public class CSLRProfileDAOImpl extends AbstractSpringDAO  implements Constants {
	/**
	 * Holds a Logger instance
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	
	@Autowired(required = true)
	public CSLRProfileDAOImpl(
			@Qualifier("cslrJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(CSLRProfileDAOImpl.class);
	}
	
	
	public Map<String, Object> UpdateUserDetails(String OldUserNmae, String newUserNmae) {
	
		BaseStoredProcedure storedProc = null;
		Map<String, Object> storedProcResult = null;		
		Map<String, Object> resultMap = null;
		
		// procedure parameters map
		Map<String, Object> inParams = new HashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		
		String dbErrCode ="0";
		
		
		String method_name = "CSLRProfileDAOImpl: UpdateUserDetails()";
		logger.debug("START:" + method_name);
		
		try{
			
			// Result map for this method
			resultMap = new HashMap<String, Object>();
			
			String procName = getSqlMessage().getMessage(SP_CSLR_UPDATE_USER_DETAILS, null, null);


			// Get Store procedure manager service instance
			StoredProcedureManager storedProcedure = StoredProcedureManager
					.getInstance();

			// Set input values (parameters) to the procedure

			inParams.put(CONST_IN_OLD_USER_LOGIN_ID, OldUserNmae);
			inParams.put(CONST_IN_NEW_USER_LOGIN_ID, newUserNmae);
			
			
			// Set input parameters type list
			
			inParamsTypeMap.put(CONST_IN_OLD_USER_LOGIN_ID, OracleTypes.VARCHAR);
			inParamsTypeMap.put(CONST_IN_NEW_USER_LOGIN_ID, OracleTypes.VARCHAR);
			
								
			logger.info("Procedure Name:" + procName + "In Params:" + inParams);
						
			outParamsTypeMap.put(CONST_OUT_OUT_ERROR_CODE, new ResultObject(Types.VARCHAR));
			outParamsTypeMap.put(CONST_OUT_ERROR_MSG, new ResultObject(Types.VARCHAR));
			

			// START (TIME LOG)
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();

			// Create the procedure, INPUT parameters as first argument(default)

			storedProc = storedProcedure.createStoredProcedure(
					getJdbcTemplate(), procName, inParams, inParamsTypeMap,
					outParamsTypeMap, INPUT);

			// execute the procedure statement
			storedProcResult = storedProc.execute();
			

			// END (TIME LOG)
			if(storedProcResult!=null){
				dbErrCode = (String) storedProcResult.get(CONST_OUT_OUT_ERROR_CODE);

				if( null != dbErrCode) {
					logger.error("OldUserNmae Id :[" + OldUserNmae +"] Error from SP["+procName+"]. Error Code[" + dbErrCode + "]");
					
				} else {
					
					logger.info("user creation Successfully for OldUserId [" + OldUserNmae +"]  ");  
				}
			}
			
			
			// Populate result map
			resultMap.put(Constants.ERR_CODE_KEY, dbErrCode);
			
		} catch(DataAccessException ex){
			dbErrCode= "-1";
			logger.error("Error in :" + method_name + ":" + ex);
			resultMap.put(Constants.ERR_CODE_KEY, dbErrCode);
		} catch(Exception e){
			dbErrCode= "-1";
			logger.error("Error in :" + method_name + ":" + e);
			resultMap.put(Constants.ERR_CODE_KEY, dbErrCode);
		}
		
		return resultMap;

 }		
}
