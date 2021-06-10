package com.multibrand.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.OfferDAOIF;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.mapper.GetPOWOfferRowMapper;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.vo.response.POWOfferDO;

//Starts - POD POW Changes -Arumugam
@Repository("offerDAO")
public class OfferDAOImpl implements OfferDAOIF,DBConstants,Constants{
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	private JdbcTemplate podJdbcTemplate;
	
	@Resource(name="sqlQuerySource")
	protected AbstractMessageSource sqlMessage;
	  
	@Autowired
	public OfferDAOImpl(
			@Qualifier("podJdbcTemplate") JdbcTemplate podJdbcTemplate) {
		this.podJdbcTemplate = podJdbcTemplate;
	}
	
	 //Ends - POD POW Changes -Arumugam
	
	 public AbstractMessageSource getSqlMessage()
	  {
	    return this.sqlMessage;
	  }

	  public void setSqlMessage(AbstractMessageSource sqlMessage) {
	    this.sqlMessage = sqlMessage;
	  }
	 //Starts - POD POW Changes -Arumugam
	public Map<String,Object> getPOWOffer(String strESID, String strTransactionType,String companyCode, String brandId) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		BaseStoredProcedure storedProc = null;
		String companyName = StringUtils.EMPTY;
		//TODO: DE: Will DE have any POW offers?
		if( (StringUtils.equals(companyCode,CIRRO_COMPANY_CODE) &&  (StringUtils.equals(brandId, CIRRO_BRAND_NAME)) )  ){
			companyName ="cirro";
		} else if ( StringUtils.equals(companyCode,GME_RES_COMPANY_CODE)) {
			companyName ="gme";
		}  else if(StringUtils.equals(companyCode,COMPANY_CODE_RELIANT)) {
			companyName ="reliant";
		}
		//TODO: DE: create store proc for DE if necessary
		String sqlQuery = sqlMessage.getMessage(companyName+"."+PROC_CPDB_GET_POW_OFFER , null, null );
		 //Ends - POD POW Changes -Arumugam
		logger.info("sqlQuery from getPOWOffer():: "+sqlQuery);
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap =  new LinkedHashMap<String, ResultObject> ();
		
		inParams.put(IN_ESID,strESID);
		inParams.put(IN_TRANSACTION_TYPE,strTransactionType);
		
		// Set input parameters type list
		inParamsTypeMap.put(IN_ESID, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_TRANSACTION_TYPE, OracleTypes.VARCHAR);
			
		// Set output parameters type list
		outParamsTypeMap.put(OUT_CUR_POW_OFFER,	new ResultObject(OracleTypes.CURSOR, new GetPOWOfferRowMapper()));
		outParamsTypeMap.put(OUT_ERROR_CODE_LOWER, new ResultObject(Types.VARCHAR));
		
		// Get Store procedure manager service instance
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		// execute the procedure
		storedProc = storedProcedure.createStoredProcedure(
				podJdbcTemplate, sqlQuery, inParams, inParamsTypeMap, outParamsTypeMap,INPUT);
		
		// execute the procedure statement
		try{
			Map<String, Object> storedProcResult = storedProc.execute();
			String error_code = (String) storedProcResult.get(OUT_ERROR_CODE_LOWER);
		
			List<POWOfferDO> getpowofferdto = (List<POWOfferDO>) storedProcResult.get(OUT_CUR_POW_OFFER);
			logger.info("error_code from getPOWOffer() :: "+error_code);
			resultMap.put(OUT_ERROR_CODE_LOWER,error_code);
			resultMap.put(OUT_CUR_POW_OFFER,getpowofferdto);
		}catch(Exception e){
			logger.error("EXCEPTION from getPOWOffer() :: " ,e);
		}
		return resultMap;
	}
	
	  protected List<Map<String, Object>> getMapDataWithoutParam(JdbcTemplate jdbcTemplate,String sqlQuery) {
		    List<Map<String, Object>> resultList = null;
		    resultList = jdbcTemplate.queryForList(sqlQuery);

		    return resultList;
	  }
}
