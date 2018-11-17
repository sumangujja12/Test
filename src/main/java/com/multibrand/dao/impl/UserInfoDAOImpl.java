package com.multibrand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BaseCirroJdbcDAO;
import com.multibrand.dao.BaseCpdbJdbcDAO;
import com.multibrand.dao.BaseJdbcDAO;
import com.multibrand.dao.BasePennywiseJdbcDAO;
import com.multibrand.dao.UserInfoDAOIF;
import com.multibrand.dao.mapper.UserInfoRowMapper;
import com.multibrand.exception.OAMException;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.vo.request.UserIdRequest;
import com.multibrand.vo.response.UserIdResponse;
import com.multibrand.vo.response.UserInfoResponse;

@Component("userinfodao")
//public class UserInfoDAOImpl extends BaseCpdbJdbcDAO implements UserInfoDAOIF, Constants {
public class UserInfoDAOImpl implements UserInfoDAOIF, Constants {
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	BaseJdbcDAO basejdbcdao;
	BaseCpdbJdbcDAO basecpdbjdbcdao;
	BasePennywiseJdbcDAO pennywisejdbcdao; 
	BaseCirroJdbcDAO cirrojdbcdao; 
	
//	@Autowired(required=true)
//	public UserInfoDAOImpl(@Qualifier("cpdbJdbcTemplate") JdbcTemplate jdbcTemplate) {
//	super(jdbcTemplate);	
//	}
	
	@Resource(name="sqlQuerySource")
	protected AbstractMessageSource sqlMessage;
	
	@Override
	public UserInfoResponse getUserInfo(String userId, String companyCode) throws OAMException {
		String sqlQuery = "select BUSINESS_PARTNER_ID from BPCA where CONTRACT_ACCOUNT_NUMBER =?";
		
		logger.info("--------- Inside getUserInfo Method ----");
		UserInfoResponse userInfoResponse = null;
		try{
			 
			userInfoResponse = basejdbcdao.getJdbcTemplate().queryForObject(sqlQuery,new Object[]{userId}, new UserInfoRowMapper());
					
		} catch(Exception exception){
			System.err.println("getTdspNameByZipCode Error: " + exception.toString());
		}
		return userInfoResponse;	
	}
	
	@Resource(name = "cpdbJdbcTemplate")
    JdbcTemplate cpdbjdbcTemplate;
	
	@Resource(name = "pennywiseJdbcTemplate")
    JdbcTemplate pennywiseJdbcTemplate;
	
	@Resource(name = "cirroJdbcTemplate")
    JdbcTemplate cirroJdbcTemplate;
	
	@Override
	public UserIdResponse getUserName(UserIdRequest request) {
		logger.info("--------- Inside getUserName Method ----");
		
		String userName=Constants.EMPTY;
		UserIdResponse response = new UserIdResponse();
		String sql = null;
		
		JdbcTemplate jdbcTemplate = null;
				
		try{		
		
			if((StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_RELIANT))&&((StringUtils.equals(request.getBrandname(),Constants.BRAND_NAME_RELIANT))||(StringUtils.equals(request.getBrandname(),Constants.BRAND_ID_RELIANT)))){
				//System.out.println("RELIANT");
				sql = sqlMessage.getMessage(DBConstants.QUERY_GET_USER_ID, null, null);
				basecpdbjdbcdao = new BaseCpdbJdbcDAO(cpdbjdbcTemplate);	
				jdbcTemplate=basecpdbjdbcdao.getJdbcTemplate();
				userName=jdbcTemplate.query(sql, new ResultSetExtractor<String>(){

					@Override
					public String extractData(ResultSet resultSet) throws SQLException,
							DataAccessException {
						return resultSet.next()?resultSet.getString(1):Constants.EMPTY;
				}	
				},CommonUtil.paddedCa(request.getContractaccountnumber()));
			}else if((StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_PENNYWISE))&&((StringUtils.equals(request.getBrandname(),Constants.BRAND_NAME_PENNYWISE))||(StringUtils.equals(request.getBrandname(),Constants.BRAND_ID_PENNYWISE)))){
				//System.out.println("PENNYWISE");
				sql = sqlMessage.getMessage(DBConstants.QUERY_GET_USER_ID, null, null);
				pennywisejdbcdao = new BasePennywiseJdbcDAO(pennywiseJdbcTemplate);	
				jdbcTemplate=pennywisejdbcdao.getJdbcTemplate();
				userName=jdbcTemplate.query(sql, new ResultSetExtractor<String>(){

					@Override
					public String extractData(ResultSet resultSet) throws SQLException,
							DataAccessException {
						return resultSet.next()?resultSet.getString(1):Constants.EMPTY;
				}	
				},CommonUtil.paddedCa(request.getContractaccountnumber()));
			}else if((StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_CIRRO))&&((StringUtils.equals(request.getBrandname(),Constants.BRAND_NAME_CIRRO))||(StringUtils.equals(request.getBrandname(),Constants.BRAND_ID_CIRRO)))){
				//System.out.println("CIRRO");
				sql = sqlMessage.getMessage(DBConstants.QUERY_CIRRO_GET_USER_ID, null, null);
				cirrojdbcdao = new BaseCirroJdbcDAO(cirroJdbcTemplate);	
				jdbcTemplate=cirrojdbcdao.getJdbcTemplate();
				userName=jdbcTemplate.query(sql, new ResultSetExtractor<String>(){

					@Override
					public String extractData(ResultSet resultSet) throws SQLException,
							DataAccessException {
						return resultSet.next()?resultSet.getString(1):Constants.EMPTY;
				}	
				},request.getContractaccountnumber(),CommonUtil.paddedCa(request.getContractaccountnumber()));
				
			}else if(StringUtils.equals(request.getCompanycode(),Constants.COMPANY_CODE_GME)){
				//System.out.println("GME");
			}
			
			System.out.println("SQL:"+sql);
			
			if(logger.isDebugEnabled())
				logger.debug("Get user id sql "+sql);
			
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
			ex.printStackTrace();
			response.setResultcode(Constants.TWO);
			response.setResultdescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
			response.setErrorcode(Constants.MSG_EXCP_ERROR_CODE);
			response.setErrordescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
		}
		return response;
	}
}
	

