package com.multibrand.dao.impl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.AddressDAOIF;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.mapper.EsiidInfoResponseRowMapper;
import com.multibrand.dao.mapper.GetPendingEnrollmentRequestRowMapper;
import com.multibrand.dto.request.CheckPendingServiceRequest;
import com.multibrand.dto.request.GetEsiidRequest;
import com.multibrand.dto.response.CheckPendingServiceResponse;
import com.multibrand.dto.response.EsiidResponse;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.ESIDDO;

@Repository("addressDAO")
public class AddressDAOImpl extends AbstractSpringDAO implements
		AddressDAOIF, Constants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired(required = true)
	public AddressDAOImpl(
			@Qualifier("choiceJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(AddressDAOImpl.class);
	}

	public List<Map<String, Object>> getCityStateFromZip(String zipCode) {
		logger.debug("AddressDAOImpl.getCityStateFromZip() Start");
		String sqlQuery = "select CITY,STATE from T_ZIP_CODE where zip_cd ="
				+ zipCode;

		List<Map<String, Object>> getCityStateList = getMapDataWithoutParam(sqlQuery);
		logger.debug("AddressDAOImpl.getCityStateFromZip() Start");

		return getCityStateList;
	}

	/**
	 * method to check for pending request in database
	 * 
	 * @author jsingh1
	 * @return map
	 */
	public List<CheckPendingServiceResponse> checkPendingRequest(
			CheckPendingServiceRequest pendingRequestCheckDTO) {
		logger.debug("AddressDAOImpl :: inside checkPendingRequest");

		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();

		String sqlQuery = sqlMessage.getMessage(
				PROC_GET_SERVICE_REQUEST_DETAILS, null, null);

		inParams.put(in_addr1, CommonUtil
				.getValue(pendingRequestCheckDTO.getServiceAddress().getStreetNum()).trim().toUpperCase()
				+ " "
				+ CommonUtil.getValue(pendingRequestCheckDTO.getServiceAddress().getStreetName()).trim()
						.toUpperCase());
		inParams.put(in_addr2, CommonUtil.getValue(pendingRequestCheckDTO.getServiceAddress().getUnitNum())
				.trim().toUpperCase());
		inParams.put(in_city, CommonUtil.getValue(pendingRequestCheckDTO.getServiceAddress().getCity())
				.trim());
		inParams.put(in_state, TX);
		inParams.put(in_zip_code,
				CommonUtil.getValue(pendingRequestCheckDTO.getServiceAddress().getZipcode()).trim());

		if (pendingRequestCheckDTO.getEsid().getEsidCount() == 1) {
			inParams.put(in_esid, pendingRequestCheckDTO.getEsid().getEsidNumber());
		} else {
			inParams.put(in_esid, EMPTY);
		}
		inParams.put(in_req_status_cd, "CD");

		inParamsTypeMap.put(in_addr1, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_addr2, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_city, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_state, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_zip_code, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_esid, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_req_status_cd, OracleTypes.VARCHAR);

		logger.debug(in_addr1 + " :: " + inParams.get(in_addr1));
		logger.debug(in_addr2 + " :: " + inParams.get(in_addr2));
		logger.debug(in_city + " :: " + inParams.get(in_city));
		logger.debug(in_state + " :: " + inParams.get(in_state));
		logger.debug(in_zip_code + " :: " + inParams.get(in_zip_code));
		logger.debug(in_esid + " :: " + inParams.get(in_esid));
		logger.debug(in_req_status_cd + " :: " + inParams.get(in_req_status_cd));

		outParamsTypeMap
				.put(OUT_CUR_PENDING_REQUEST, new ResultObject(
						OracleTypes.CURSOR,
						new GetPendingEnrollmentRequestRowMapper()));

		// Get Store procedure manager service instance
		StoredProcedureManager storedProcedure = StoredProcedureManager
				.getInstance();
		// execute the procedure
		storedProc = storedProcedure.createStoredProcedure(getJdbcTemplate(),
				sqlQuery, inParams, inParamsTypeMap, outParamsTypeMap, OUTPUT);
		// execute the procedure statement
		Map<String, Object> storedProcResult = storedProc.execute();
		List<CheckPendingServiceResponse> pendingServiceReqDTOList = (List<CheckPendingServiceResponse>) storedProcResult
				.get(OUT_CUR_PENDING_REQUEST);
		logger.debug("AddressDAOImpl :: Exiting  checkPendingRequest:: "
				+ pendingServiceReqDTOList);
		return pendingServiceReqDTOList;
	}
	
	public int getESIDCount(String esidNumber) {
		if(logger.isDebugEnabled()) {
			logger.debug("getESIDCount : Entered the method : esidNumber= " + esidNumber );
		}
		int esidCount = 0 ;
		String sqlQuery = sqlMessage.getMessage(QUERY_GET_ESIDCOUNT, new Object [] {"'"+esidNumber+"'"}, null);
		logger.debug("getESIDCount : sqlQuery for esidCount: " + sqlQuery);
		
		String retVal = getDataWithOutParam(sqlQuery);
		
		try{
		   if(StringUtils.isNotBlank(retVal)){
			esidCount = Integer.parseInt(retVal) ;}
		
		}catch(Exception e){
			esidCount=0;
			logger.debug("::::AddressDAOImpl:::getESIDCount:::failed::",e);
			//TODO - Verify $$Exception$$ Handing  
        }
		if(logger.isDebugEnabled()) {
			logger.debug("getESIDCount : before returning value of getESIDCount : " + esidCount);
		}
		return esidCount;
	}
	
	public List<Map<String,Object> > getESIDTypeList(String esidNumber) {
		if(logger.isDebugEnabled()) {
			logger.debug("getESIDTypeList : Entered the method : esidNumber= " + esidNumber );
		}
		String sqlQuery = sqlMessage.getMessage(QUERY_GET_ESIDTYPE, new Object [] {"'"+esidNumber+"'"}, null);
		logger.debug("getESIDTypeList : sqlQuery for getESIDTypeList: " + sqlQuery);
		List<Map<String,Object> > esidTypeList = getMapDataWithoutParam(sqlQuery);
		return esidTypeList;
	}

	/**
	* Start || PBI 15786: Update ESID Call || atiwari
	* @author atiwari
	* @param getEsiidRequest GetEsiidRequest
	* @return GetEsiidResponse
	* @throws SQLException,Exception 
	*/
	@Override
	public EsiidResponse getESIDDetails(GetEsiidRequest getEsiidRequest) throws SQLException,Exception {
		logger.debug("AddressDAOImpl ::getESIDDetails");
		EsiidResponse esiidResponse=new EsiidResponse();
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = null;
		Map<String, Integer> inParamsTypeMap = null;
		Map<String, ResultObject> outParamsTypeMap = null;
		Map<String, Object> storedProcResult= null;
		inParams = new LinkedHashMap<String, Object>();
		inParamsTypeMap = new LinkedHashMap<String, Integer>();
		outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		String sqlQuery = sqlMessage.getMessage(
				PROC_GET_ESIID_INFO, null, null);
		inParamsTypeMap.put(in_addr, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_city, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_state, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_zip, OracleTypes.VARCHAR);
		inParamsTypeMap.put(in_aptno, OracleTypes.VARCHAR);
		
		inParams.put(in_addr, CommonUtil
				.getValue(getEsiidRequest.getStrStreet()).trim().toUpperCase());
		inParams.put(in_city, CommonUtil.getValue(getEsiidRequest.getStrCity()).trim());
		inParams.put(in_state, TX);
		inParams.put(in_zip,
				CommonUtil.getValue(getEsiidRequest.getStrZipCode()).trim());
		inParams.put(in_aptno,
				CommonUtil.getValue(getEsiidRequest.getStrAprtNum()).trim());
		
		for (Map.Entry<String, Object> entry : inParams.entrySet()) {
			logger.debug("inputParamKey : " + entry.getKey() + " inputParamValue : " + entry.getValue());
		}
		/*inParams.forEach((inputParamValue,inputParamCount) -> logger.info("inParams value"+ inputParamValue +"  inputParamCount "+inputParamCount));*/
		outParamsTypeMap.put(OUT_CURR_GET_ESI, new ResultObject(OracleTypes.CURSOR,new EsiidInfoResponseRowMapper()));
		StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
		storedProc = storedProcedure.createStoredProcedure(getJdbcTemplate(),sqlQuery, inParams, inParamsTypeMap, outParamsTypeMap, OUTPUT);
		storedProcResult = storedProc.execute();
		esiidResponse.setEsidList((List<ESIDDO>)storedProcResult.get(OUT_CURR_GET_ESI));
		return esiidResponse;
	}
}
