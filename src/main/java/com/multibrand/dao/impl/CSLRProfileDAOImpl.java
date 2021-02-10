
package com.multibrand.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@Autowired(required = true)
	public CSLRProfileDAOImpl(
			@Qualifier("cslrJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(CSLRProfileDAOImpl.class);
	}
	
	
	public Map<String, Object> updateUserDetails(String oldUserNmae, String newUserNmae) {
	
		BaseStoredProcedure storedProc = null;
		Map<String, Object> storedProcResult = null;		
		Map<String, Object> resultMap = new HashMap();
		
		// procedure parameters map
		Map<String, Object> inParams = new HashMap();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap();
		
		String dbErrCode ="0";
		
		
		String methodName = "CSLRProfileDAOImpl: UpdateUserDetails()";
		logger.debug("START:{}" , methodName);
		
		try{			
			
			String procName = getSqlMessage().getMessage(SP_CSLR_UPDATE_USER_DETAILS, null, null);


			// Get Store procedure manager service instance
			StoredProcedureManager storedProcedure = StoredProcedureManager
					.getInstance();

			// Set input values (parameters) to the procedure

			inParams.put(CONST_IN_OLD_USER_LOGIN_ID, oldUserNmae);
			inParams.put(CONST_IN_NEW_USER_LOGIN_ID, newUserNmae);
			
			
			// Set input parameters type list
			
			inParamsTypeMap.put(CONST_IN_OLD_USER_LOGIN_ID, OracleTypes.VARCHAR);
			inParamsTypeMap.put(CONST_IN_NEW_USER_LOGIN_ID, OracleTypes.VARCHAR);
			
								
			logger.info("Procedure Name:{} In Params: {} " , procName , inParams);
						
			outParamsTypeMap.put(CONST_OUT_OUT_ERROR_CODE, new ResultObject(Types.VARCHAR));
			outParamsTypeMap.put(CONST_OUT_ERROR_MSG, new ResultObject(Types.VARCHAR));
			

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
					logger.error("OldUserNmae Id :[{} Error from SP {} Error Code {}] ",oldUserNmae, procName, dbErrCode);
					
				} else {
					
					logger.info("user creation Successfully for OldUserId [{} ]", oldUserNmae);  
				}
			}
			
			
			// Populate result map
			resultMap.put(Constants.ERR_CODE_KEY, dbErrCode);
			
		} catch(Exception ex){
			dbErrCode= "-1";
			logger.error("Error in :{} :{}" , methodName , ex);
			resultMap.put(Constants.ERR_CODE_KEY, dbErrCode);
		}
		
		return resultMap;

 }		
}
