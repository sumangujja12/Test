package com.multibrand.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Component;

import com.multibrand.dao.IRWDAO;
import com.multibrand.dao.ResultObject;
import com.multibrand.dao.mapper.IRWReadOAMMapper;
import com.multibrand.dataObjects.DisplayMessageTab;
import com.multibrand.dto.IRWDTO;
import com.multibrand.dto.MessageDTO;
import com.multibrand.dto.request.OAMMessageRequest;
import com.multibrand.dto.response.OAMMessageResponse;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.DBConstants;
import com.multibrand.util.DateUtil;

import oracle.jdbc.OracleTypes;

@Component
public class IRWDAOImpl implements IRWDAO, DBConstants{
	
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public IRWDAOImpl(@Qualifier("gmeResJdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public OAMMessageResponse readOAMMessage(OAMMessageRequest request) {
		
		OAMMessageResponse response = new OAMMessageResponse();
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		
		try{
			inParams.put("in_bp", request.getBpNumber());
			inParams.put("in_ca", request.getCaNumber());
			inParams.put("in_co", request.getCoNumber());
			inParams.put("in_esiid", request.getEsiid());
			inParams.put("in_ced", DateUtil.getDate(request.getContractEndDate(), "MM/dd/yyyy"));
			inParams.put("in_cocode", request.getCompanyCode());
			inParams.put("in_brandid", CommonUtil.getAltBrandID(request.getBrandId(), request.getCompanyCode()));//Need the brand ID like "GME"
			inParams.put("in_prgm_nm", request.getProgramName());
			
			inParamsTypeMap.put("in_bp", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_ca", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_co", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_esiid", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_ced", OracleTypes.DATE);
			inParamsTypeMap.put("in_cocode", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_brandid", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_prgm_nm", OracleTypes.VARCHAR);
			
			if(logger.isDebugEnabled()){
				logger.debug("inParams:" + inParams);
			}
			
			outParamsTypeMap.put("out_disp_msg_typ", new ResultObject(OracleTypes.CURSOR, new IRWReadOAMMapper()));
			
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
			storedProc = storedProcedure.createStoredProcedure(jdbcTemplate,GME_OAM_MSSG_READ_PROC, inParams, inParamsTypeMap, outParamsTypeMap);
	
			Map resultMap = storedProc.execute();
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
					: "less than a second.";
			logger.info(GME_OAM_MSSG_READ_PROC + "-" + elapsedTimeDisp);
			
			@SuppressWarnings("unchecked")
			List<IRWDTO> IRWResultList = (List<IRWDTO>) resultMap.get("out_disp_msg_typ");
			if(null != IRWResultList && IRWResultList.size() > 0){
				List<MessageDTO> messageList = new ArrayList<MessageDTO>();
				for(IRWDTO dto: IRWResultList){
					MessageDTO msg = new MessageDTO();
					msg.setMessage(dto.getMessageName());
					msg.setSdlCode(dto.getMessageName());
					msg.setProgram(request.getProgramName());
					msg.setType(request.getType());
					messageList.add(msg);
				}
				response.setMessageList(messageList);
			}
		}catch(Exception ex){
			logger.error("Exception occured while readOAMMessage::::", ex);
			response.setErrorMessage(ex.getMessage());
			response.setErrorCode("SQL_ERR");
		}
		return response;
	}

	@Override
	public OAMMessageResponse writeOAMMessage(OAMMessageRequest request) {
		
		OAMMessageResponse response = new OAMMessageResponse();
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		try{
			inParams.put("in_bp", request.getBpNumber());
			inParams.put("in_ca", request.getCaNumber());
			inParams.put("in_co", request.getCoNumber());
			inParams.put("in_esiid", request.getEsiid());
			inParams.put("in_ced", DateUtil.getDate(request.getContractEndDate(), "MM/dd/yyyy"));
			inParams.put("in_cocode", request.getCompanyCode());
			inParams.put("in_brandid", CommonUtil.getAltBrandID(request.getBrandId(), request.getCompanyCode()));
			inParams.put("in_prgm_nm", request.getProgramName());
			DisplayMessageTab[] tabArray = new DisplayMessageTab[1];
 			DisplayMessageTab tab = new DisplayMessageTab();
			tab.setDisplayMessage(request.getDisplayMessage());
			tabArray[0] = tab;
			inParams.put("in_disp_msg", new SqlArrayValue(tabArray));
			if(logger.isDebugEnabled()){
				logger.info("inParams:" + inParams);
			}
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			BaseStoredProcedure sp = createSpWrite(jdbcTemplate,GME_OAM_MSSG_WRITE_PROC,inParams);
			Map resultMap = sp.execute();
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info(GME_OAM_MSSG_WRITE_PROC + "-" + elapsedTimeDisp);
			logger.info(GME_OAM_MSSG_WRITE_PROC + "-" + resultMap);
		}catch(Exception ex){
			logger.error("Exception occured while writeOAMMessage::::", ex);
			response.setErrorMessage(ex.getMessage());
			response.setErrorCode("SQL_ERR");
		}
		return response;
	}


	@Override
	public OAMMessageResponse checkOAMMessage(OAMMessageRequest request) {
		
		OAMMessageResponse response = new OAMMessageResponse();
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();
		
		try{
			inParams.put("in_bp", request.getBpNumber());
			inParams.put("in_ca", request.getCaNumber());
			inParams.put("in_co", request.getCoNumber());
			inParams.put("in_esiid", request.getEsiid());
			inParams.put("in_ced", DateUtil.getDate(request.getContractEndDate(), "MM/dd/yyyy"));
			inParams.put("in_cocode", request.getCompanyCode());
			inParams.put("in_brandid", CommonUtil.getAltBrandID(request.getBrandId(), request.getCompanyCode()));
			inParams.put("in_prgm_nm", request.getProgramName());
			inParams.put("in_disp_msg", request.getDisplayMessage());
			
			inParamsTypeMap.put("in_bp", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_ca", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_co", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_esiid", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_ced", OracleTypes.DATE);
			inParamsTypeMap.put("in_cocode", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_brandid", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_prgm_nm", OracleTypes.VARCHAR);
			inParamsTypeMap.put("in_disp_msg", OracleTypes.VARCHAR);
			outParamsTypeMap.put("out_disp_msg_typ", new ResultObject(OracleTypes.CURSOR, new IRWReadOAMMapper()));
			if(logger.isDebugEnabled()){
				logger.info("inParams:" + inParams);
			}
			long entryTime;
			long elapsedTime;
			entryTime = System.currentTimeMillis();
			StoredProcedureManager storedProcedure = StoredProcedureManager.getInstance();
			storedProc = storedProcedure.createStoredProcedure(jdbcTemplate,GME_OAM_MSSG_CHECK_PROC, inParams, inParamsTypeMap, outParamsTypeMap);
			Map resultMap = storedProc.execute();
			elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
			String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds." : "less than a second.";
			logger.info(GME_OAM_MSSG_CHECK_PROC + "-" + elapsedTimeDisp);
			logger.info(GME_OAM_MSSG_CHECK_PROC + "-" + resultMap);
			
			List<IRWDTO> IRWResultList = (List<IRWDTO>) resultMap.get("out_disp_msg_typ");
			if(null != IRWResultList && IRWResultList.size() > 0){
				List<MessageDTO> messageList = new ArrayList<MessageDTO>();
				for(IRWDTO dto: IRWResultList){
					MessageDTO msg = new MessageDTO();
					msg.setMessage(dto.getMessageName());
					msg.setProgram(request.getDisplayMessage());
					msg.setType(request.getType());
					msg.setSdlCode(request.getDisplayMessage());
					messageList.add(msg);
				}
				response.setMessageList(messageList);
			}
		}catch(Exception ex){
			logger.error("Exception occured while checkOAMMessage::::", ex);
			response.setErrorMessage(ex.getMessage());
			response.setErrorCode("SQL_ERR");
		}
		return response;
		
	
	}
	
	
	
	public BaseStoredProcedure createSpWrite(
			JdbcTemplate jdbcTemplate, String storedProcName,
			Map<String, Object> inputParams  ) {
		if(logger.isDebugEnabled()){
			logger.debug("START:StoredProcedureManager::createSpWrite(...) >>>");
		}
		BaseStoredProcedure sp = null;

		// START Creating the stored procedure
		sp = new BaseStoredProcedure(jdbcTemplate, storedProcName, inputParams) {
			@Override
			public BaseStoredProcedure createSp() {
				// Set input parameters
				// Parameters should be declared in same order here that
				// they are declared in the stored procedure.
				// define params with syntax:  param_name, param_type
				declareParameter(new SqlParameter("in_bp", Types.VARCHAR));
				declareParameter(new SqlParameter("in_ca", Types.VARCHAR));
				declareParameter(new SqlParameter("in_co", Types.VARCHAR));
				declareParameter(new SqlParameter("in_esiid", Types.VARCHAR));
				declareParameter(new SqlParameter("in_ced", Types.DATE));
				declareParameter(new SqlParameter("in_cocode", Types.VARCHAR));
				declareParameter(new SqlParameter("in_brandid", Types.VARCHAR));
				declareParameter(new SqlParameter("in_prgm_nm", Types.VARCHAR));
				declareParameter(new SqlParameter("in_disp_msg", OracleTypes.ARRAY, "DISP_MSG_TYP_TAB"));

				// compile the statement
				compile();
				return this;
			}
		}.createSp();

		// END Creating the stored procedure
		logger.debug("END:StoredProcedureManager::createSpWrite(...) <<<");

		return sp;

	}
	
	
	public BaseStoredProcedure createSpCheck(
			JdbcTemplate jdbcTemplate, String storedProcName,
			Map<String, Object> inputParams  ) {
		if(logger.isDebugEnabled()){
			logger.debug("START:StoredProcedureManager::createSpCheck(...) >>>");
		}
		BaseStoredProcedure sp = null;

		// START Creating the stored procedure
		sp = new BaseStoredProcedure(jdbcTemplate, storedProcName, inputParams) {
			@Override
			public BaseStoredProcedure createSp() {
				// Set input parameters
				// Parameters should be declared in same order here that
				// they are declared in the stored procedure.
				// define params with syntax:  param_name, param_type
				declareParameter(new SqlParameter("in_bp", Types.VARCHAR));
				declareParameter(new SqlParameter("in_ca", Types.VARCHAR));
				declareParameter(new SqlParameter("in_co", Types.VARCHAR));
				declareParameter(new SqlParameter("in_esiid", Types.VARCHAR));
				declareParameter(new SqlParameter("in_ced", Types.DATE));
				declareParameter(new SqlParameter("in_cocode", Types.VARCHAR));
				declareParameter(new SqlParameter("in_brandid", Types.VARCHAR));
				declareParameter(new SqlParameter("in_prgm_nm", Types.VARCHAR));
				declareParameter(new SqlParameter("in_disp_msg", Types.VARCHAR));
				declareParameter(new SqlOutParameter("out_disp_msg_typ", OracleTypes.CURSOR));

				// compile the statement
				compile();
				return this;
			}
		}.createSp();

		// END Creating the stored procedure
		if(logger.isDebugEnabled()){
			logger.debug("END:StoredProcedureManager::createSpCheck(...) <<<");
		}

		return sp;

	}
	
}
