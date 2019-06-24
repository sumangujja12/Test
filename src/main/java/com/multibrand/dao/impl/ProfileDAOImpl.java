package com.multibrand.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
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
		expiryDt.add(Calendar.MINUTE, 59);
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
			logger.error("Record not inserted into DB");
		
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
		boolean result = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DT_SQL_FMT_DB);
			String query = sqlMessage.getMessage(DBConstants.QUERY_GME_VALIDATE_PASSWORD_LINK, null, null);
			String expDate = gmeResJdbcTemplate.query(query, new Object[] { transactionId },
					new ResultSetExtractor<String>() {
						@Override
						public String extractData(ResultSet rs) throws SQLException, DataAccessException {
							String expirationDate = "";
							while (rs.next()) {
								expirationDate = rs.getString(1);
							}
							return expirationDate;
						}

					});
			Date convertedExpDate = sdf.parse(expDate);
			String sysDate = sdf.format(Calendar.getInstance().getTime());
			Date convertedSysDate = sdf.parse(sysDate);

			if (convertedExpDate.before(convertedSysDate)) {
				result = false;// expired
			} else {
				result = true;// valid
			}
			logger.info("profileDAO-validatePasswordLink :: End");
		} catch (Exception e) {
			logger.error("Exception Occured in validatePasswordLink ::: " + e);
		}
		return result;
	}

	@Override
	public String getUserNameforTxn(String transactionId) {
		
		String userName=null;
		
		try{
		String query = "select gme_res_main.Ol_ACCOUNT.USER_LOGIN_ID from gme_res_main.ol_external_request , gme_res_main.Ol_ACCOUNT where ol_external_request.user_unique_id = Ol_ACCOUNT.user_unique_id and ol_external_request.transaction_id = '"+transactionId+"'";
		userName = (String)gmeResJdbcTemplate.queryForObject(query, String.class);
		}catch(Exception e)
		{
		logger.error("Inside DB call expection block -  getUserNameforTxn"+e);
		}
		return userName;
	}
	
	@Override
	public int updateStatusFlag(String userName) {
		
		int result=0;
		try{
			
			String query =sqlMessage.getMessage(DBConstants.QUERY_GME_INSERT_PASSWORD_STATUS_CODE, null, null);
			
		/*	String query = "UPDATE gme_res_main.ol_external_request set gme_res_main.ol_external_request.STATUS_FLAG='C' where  STATUS_FLAG='O' and gme_res_main.ol_external_request.user_unique_id = ( select distinct gme_res_main.Ol_ACCOUNT.user_unique_id from gme_res_main.OL_EXTERNAL_REQUEST , gme_res_main.Ol_ACCOUNT "
			+ " where gme_res_main.ol_external_request.user_unique_id = gme_res_main.Ol_ACCOUNT.user_unique_id and gme_res_main.Ol_ACCOUNT.USER_LOGIN_ID = ? and STATUS_FLAG='O')";*/
			
		
			Object[] args = new Object[1];
			args[0] = userName;
			result = gmeResJdbcTemplate.update(query, args);
								
		}catch(Exception e)
		{
			logger.error("Inside DB call expection block -  updateStatusFlag"+e);
		}
		return result;
	}
	

}
