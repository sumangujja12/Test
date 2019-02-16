package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.ProfileDAO;
import com.multibrand.util.Constants;
import com.multibrand.util.DBConstants;
import com.multibrand.util.GuidGenerator;

@Repository("profileDAO")
public class ProfileDAOImpl extends AbstractSpringDAO implements ProfileDAO, Constants {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired(required = true)
	public ProfileDAOImpl(
			@Qualifier("gmeResJdbcTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(ProfileDAOImpl.class);
	}

	@Resource(name = "gmeResJdbcTemplate")
    JdbcTemplate gmeResJdbcTemplate;
	
	
	/**
	 * Method to insert forgot password request with transaction ID into database
	 * 
	 * @author cuppala
	 * @return String
	 */
	public String insertTransaction(final String userUniqueID, final String companyCode) {
		
	
		logger.info("profileDAO-insertTransaction :: Start");
	
		String returnValue =null;
		final String requestType = "PASSWORD RESET";
		
		int rows; // rows added or updated;
		try{
		final String transactionId = GuidGenerator.getGuid(true);
		Calendar expiryDt = Calendar.getInstance();
		Date expirationDate = new Date();
		expiryDt.add(Calendar.MINUTE, 30);
		expirationDate.setTime(expiryDt.getTimeInMillis());			
		//transactionId = GuidGenerator.getGuid(true);
		final Timestamp   sqlDate = new java.sql.Timestamp(expirationDate.getTime()); 
		
		//String query =	"INSERT INTO gme_res_main.OL_EXTERNAL_REQUEST (COMPANY_CODE, TRANSACTION_ID, USER_UNIQUE_ID, STATUS_FLAG,REQUEST_TYPE, EXPIRATION_DATE) VALUES ( ?, ?, ?, ?, ?, ?)";
		
		String query =sqlMessage.getMessage(DBConstants.QUERY_GME_INSERT_PASSWORD_TNX, null, null);
						
		rows = gmeResJdbcTemplate.update(query, new PreparedStatementSetter(){
			
			public void setValues(PreparedStatement ps){
				try {
				ps.setString(1, companyCode);
				ps.setString(2, transactionId);
				ps.setString(3, userUniqueID);
				ps.setString(4,"O");
				ps.setString(5, requestType);
				ps.setTimestamp(6, sqlDate);
				} catch (Exception e) {
					logger.error("Expection in gmeResJdbcTemplate.insert"+e);
					
				}
	        }
		
		});
		if(rows==1)
			logger.info("Inside ProfileDAO:insertTransaction -DB call transactionId"+transactionId);
		else
			new SQLException("Record not inserted into DB");
		
		returnValue=transactionId;
		}
		catch(Exception e)
		{
	
			logger.error("Inside DB call expection block"+e);
			
		}
				
		
		logger.info("profileDAO-insertTransaction :: End");
		return returnValue;
		
		
	}
	
	public boolean validatePasswordLink(final String transactionId) {
		
		
		logger.info("profileDAO-validatePasswordLink :: Start");
	
		boolean result=false;
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String query = "SELECT to_char(expiration_date,'YYYY-MM-DD hh24:mi:ss') FROM gme_res_main.OL_EXTERNAL_REQUEST WHERE TRANSACTION_ID ='"+transactionId+"'";
			String expDate = (String)gmeResJdbcTemplate.queryForObject(query, String.class);
			
			
			Date convertedExpDate = sdf.parse(expDate);
						
			String sysDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			Date convertedSysDate = sdf.parse(sysDate);
			logger.info("Chakri 1111111111"+convertedExpDate.before(convertedSysDate));
			if(convertedExpDate.before(convertedSysDate))
			{
				result = false;//expired
				
			}
			else
			{
				result = true;//valid
			}
						
			}
			catch(Exception e)
			{
	
			logger.error("Inside DB call expection block"+e);
			
			}
				
		
			logger.info("profileDAO-validatePasswordLink :: End");
			return result;
		
		
	}

	@Override
	public boolean checkPasswordLinkValidity(final String transactionId) {
		
		boolean result=false;
		
		try{
		String query = "SELECT STATUS_FLAG FROM gme_res_main.OL_EXTERNAL_REQUEST WHERE TRANSACTION_ID ='"+transactionId+"'";
		String Status = (String)gmeResJdbcTemplate.queryForObject(query, String.class);
		
		if(Status.equalsIgnoreCase("O"))
			result=true;
		else
			result=false;
		}catch(Exception e)
		{
			
		logger.error("Inside DB call expection block -  checkPasswordLinkValidity"+e);
		
		}
		
		return result;
	}

	@Override
	public boolean updatePasswordLinkValidity(final String transactionId) {
		boolean result=false;
		try{
			String updateQuery = "update Student set age = ? where id = ?";
			String query = "UPDATE gme_res_main.OL_EXTERNAL_REQUEST set STATUS_FLAG = ? WHERE TRANSACTION_ID = ?";
			String Status = (String)gmeResJdbcTemplate.queryForObject(query, String.class);
			
			if(Status.equalsIgnoreCase("O"))
				result=true;
			else
				result=false;
			}catch(Exception e)
			{
				
			logger.error("Inside DB call expection block -  checkPasswordLinkValidity"+e);
			
			}
		
		return result;
	}
	
}
