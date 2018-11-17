package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.multibrand.dao.RegistrationDAO;
import com.multibrand.dao.ResultObject;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.manager.BaseStoredProcedure;
import com.multibrand.manager.StoredProcedureManager;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.response.UserIdResponse;

@Component("registrationDao")
public class RegistrationDAOImpl implements RegistrationDAO, DBConstants
{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	@Autowired
	public RegistrationDAOImpl(@Qualifier("gmeResJdbcTemplate") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Resource(name="sqlQuerySource")
	protected AbstractMessageSource sqlMessage;
	 
	@Override
	public boolean createUserName(UserRegistrationRequest userReqVo)
	{
		logger.info(" START createUserName Method");
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();

		inParams.put(IN_COMPANY_CODE, userReqVo.getCompanyCode());
		inParams.put(IN_USER_UNIQUE_ID, userReqVo.getUniqueId());
		inParams.put(IN_USER_LOGIN_ID, userReqVo.getUserName());
		inParams.put(IN_USER_FIRST_NAME, userReqVo.getFirstName());
		inParams.put(IN_USER_LAST_NAME, userReqVo.getLastName());
		inParams.put(IN_GREETING_NAME, "");
		inParams.put(IN_EMAIL_ADDRESS, userReqVo.getEmail());
		inParams.put(IN_MOBILE_NUMBER, "");
		inParams.put(IN_CONTRACT_ACCOUNT_NUMBER,
				userReqVo.getAccountNumber());
		inParams.put(IN_OUT_CA_CHECK_DIGIT, "");
		inParams.put(IN_EMAIL_OPT_IN_FLAG, "");

		inParamsTypeMap.put(IN_COMPANY_CODE, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_USER_UNIQUE_ID, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_USER_LOGIN_ID, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_USER_FIRST_NAME, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_USER_LAST_NAME, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_GREETING_NAME, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_EMAIL_ADDRESS, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_MOBILE_NUMBER, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_CONTRACT_ACCOUNT_NUMBER, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_OUT_CA_CHECK_DIGIT, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_EMAIL_OPT_IN_FLAG, OracleTypes.VARCHAR);

		outParamsTypeMap.put(OUT_ERROR_CODE, new ResultObject(
				OracleTypes.VARCHAR));

		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		// Get Store procedure manager service instance
		StoredProcedureManager storedProcedure = StoredProcedureManager
				.getInstance();
		// execute the procedure
		storedProc = storedProcedure.createStoredProcedure(jdbcTemplate,
				CREATEUSER_PROC, inParams, inParamsTypeMap, outParamsTypeMap);

		// execute the procedure statement
		Map<String, Object> storedProcResult = storedProc.execute();
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(CREATEUSER_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)

		String outErrorCode = (String) storedProcResult.get(OUT_ERROR_CODE);
		logger.info("createUserName..The output is " + outErrorCode);
		logger.info(" createUserName END Method");
		if (StringUtils.isBlank(outErrorCode)) {
			return true;
		}

		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountEnrolled(UserRegistrationRequest userReqVo)
	{
		logger.info(" Start getUserNameExists Method");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("SELECT * from GME_RES_MAIN.OL_CA where contract_account_number =? AND company_code=? ");
		
		try {
			
			String request = "companyCode="+userReqVo.getCompanyCode()+",contractAccountNumber="+userReqVo.getAccountNumber();
			long startTime = CommonUtil.getStartTime();
			List<Map<String, Object>> objectList =  jdbcTemplate.queryForList(
					stringBuffer.toString(),
					new Object[] {userReqVo.getAccountNumber(),
						userReqVo.getCompanyCode() });
			utilityloggerHelper.logTransaction("isAccountEnrolled", false, request,objectList, "", CommonUtil.getElapsedTime(startTime), "", userReqVo.getSessionId(), userReqVo.getCompanyCode());
			if(objectList != null && objectList.size() >0) {
				return false;
			}

		} catch (DataAccessException exception) {
			logger.error("Error Occured calling the SQL in getUserNameExists "+exception.getMessage());
			logger.error("Error Occured calling the SQL in getUserNameExists "+exception.getCause());
		}
		logger.info(" END getUserNameExists Method");
		return true;

	}

	@Override
	public boolean getUserName(UserRegistrationRequest userReqVo)
	{
		logger.info(" Start getUserName Method");
		BaseStoredProcedure storedProc = null;
		Map<String, Object> inParams = new LinkedHashMap<String, Object>();
		Map<String, Integer> inParamsTypeMap = new LinkedHashMap<String, Integer>();
		Map<String, ResultObject> outParamsTypeMap = new LinkedHashMap<String, ResultObject>();

		inParams.put(IN_COMPANY_CODE, userReqVo.getCompanyCode());
		inParams.put(IN_EMAIL_ADDRESS, userReqVo.getEmail());
		inParams.put(IN_CONTRACT_ACCOUNT_NUMBER,
				userReqVo.getAccountNumber());
		inParamsTypeMap.put(IN_EMAIL_ADDRESS, OracleTypes.VARCHAR);
		inParamsTypeMap.put(IN_CONTRACT_ACCOUNT_NUMBER, OracleTypes.VARCHAR);

		inParamsTypeMap.put(IN_COMPANY_CODE, OracleTypes.VARCHAR);

		outParamsTypeMap.put(OUT_USER_NAME, new ResultObject(
				OracleTypes.VARCHAR));
		outParamsTypeMap.put(OUT_CA_VALID_FLAG, new ResultObject(
				OracleTypes.VARCHAR));
		outParamsTypeMap.put(OUT_EMAIL_BOUNCED_FLAG, new ResultObject(
				OracleTypes.VARCHAR));
		outParamsTypeMap.put(OUT_ERROR_CODE, new ResultObject(
				OracleTypes.VARCHAR));

		// START (TIME LOG)
		long entryTime;
		long elapsedTime;
		entryTime = System.currentTimeMillis();
		// Get Store procedure manager service instance
		StoredProcedureManager storedProcedure = StoredProcedureManager
				.getInstance();
		// execute the procedure
		storedProc = storedProcedure.createStoredProcedure(jdbcTemplate,
				GET_USERNAME_PROC, inParams, inParamsTypeMap, outParamsTypeMap);

		// execute the procedure statement
		Map<String, Object> storedProcResult = storedProc.execute();
		// Elapsed time in minutes (TIME LOG)
		elapsedTime = (System.currentTimeMillis() - entryTime) / (1000);
		String elapsedTimeDisp = elapsedTime > 0 ? elapsedTime + " seconds."
				: "less than a second.";
		logger.info(GET_USERNAME_PROC + "-" + elapsedTimeDisp);

		// END (TIME LOG)

		String outUserName = (String) storedProcResult.get(OUT_USER_NAME);

		logger.info("getUserName..The output is outUserName " + outUserName);
		String outErrorCode = (String) storedProcResult.get(OUT_ERROR_CODE);
		logger.info("getUserName..The output is outErrorCode" + outErrorCode);
		if (StringUtils.isNotBlank(outUserName)) {
			logger.info(" END getUserName Method");
			return false;
		}

		logger.info("getUserName..The output is " + outUserName);
		logger.info(" END getUserName Method");
		// TODO Auto-generated method stub
		return true;
	}

	public static void main(String[] argz)
	{
		FileSystemXmlApplicationContext file = new FileSystemXmlApplicationContext(
				"G:\\Redesign_WorkSpace\\GME_ResidentialWS\\WebContent\\WEB-INF\\spring\\NRGREST-dao-config.xml");

		JdbcTemplate gmeResJdbcTemplate = (JdbcTemplate) file
				.getBean("gmeResJdbcTemplate");

		RegistrationDAOImpl register = new RegistrationDAOImpl(
				gmeResJdbcTemplate);
		UserRegistrationRequest regDto = new UserRegistrationRequest();
		regDto.setAccountNumber("000000441435");
		regDto.setCompanyCode("0127");
		regDto.setEmail("smuruga1@reliant.com");
		regDto.setUniqueId("6772896B-696D-EC75-79C2-0F224E3FA83C");
		regDto.setUserName("smuruga1");
		regDto.setPassword("nrg123");
		regDto.setLastName("smarimuthu");
		regDto.setFirstName("siva");
		//register.createUserName(regDto);
		
		
		System.out.println(register.isAccountEnrolled(regDto));
		
		regDto = new UserRegistrationRequest();
		regDto.setAccountNumber("000000441435");
		regDto.setCompanyCode("0127");
		regDto.setEmail("smuruga1@reliant.com");
		regDto.setUniqueId("6772896B-696D-EC75-79C2-0F224E3FA83C");
		regDto.setUserName("smuruga1");
		regDto.setPassword("nrg123");
		regDto.setLastName("smarimuthu");
		regDto.setFirstName("siva");
		System.out.println(String.valueOf(register.getUserName(regDto)));
		
		System.out.println("hai"+StringUtils.defaultIfEmpty("ssss", "default"));
	}

	@Override
	public UserIdResponse getUserName(UserIdRequest request){
		String userName=Constants.EMPTY;
		UserIdResponse response = new UserIdResponse();
		String sql = sqlMessage.getMessage(DBConstants.QUERY_GET_USER_ID, null, null);
		
		if(logger.isDebugEnabled())
			logger.debug("Get user id sql "+sql);
		
		try{
			userName=jdbcTemplate.query(sql, new ResultSetExtractor<String>(){

				@Override
				public String extractData(ResultSet resultSet) throws SQLException,
						DataAccessException {
					return resultSet.next()?resultSet.getString(1):Constants.EMPTY;
			}	
			},CommonUtil.paddedCa(request.getContractaccountnumber()));
			if(logger.isDebugEnabled())
				logger.debug("UserID from DB for "+request.getContractaccountnumber()+" is "+userName);
			if(StringUtils.isNotBlank(userName)){
				response.setUserid(userName);
				response.setResultcode(Constants.ZERO);
				response.setResultdescription(Constants.MSG_SUCCESS);	
			}else{
				response.setResultcode(Constants.THREE);
				response.setResultdescription(Constants.RESULT_CODE_DESCRIPTION_NO_DATA);
				response.setErrorcode(Constants.MSG_USER_NOT_FOUND);
				response.setErrordescription(Constants.MSG_USR_NOT_FOUND);
			}
			
		}catch(DataAccessException ex){
			logger.error("Exception Occurred.... "+ex.getMessage());
			response.setResultcode(Constants.TWO);
			response.setResultdescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
			response.setErrorcode(Constants.MSG_EXCP_ERROR_CODE);
			response.setErrordescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}

}
