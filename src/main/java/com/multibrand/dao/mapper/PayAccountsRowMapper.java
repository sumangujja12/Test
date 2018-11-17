package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.multibrand.util.Constants;
import com.multibrand.vo.response.billingResponse.PayAccount;
import com.multibrand.vo.response.billingResponse.PayAccountInfoResponse;

public class PayAccountsRowMapper implements ResultSetExtractor<PayAccountInfoResponse>, Constants {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Override
	public PayAccountInfoResponse extractData(ResultSet rs)
			throws SQLException {
		
		logger.info("PayAccountRowMapper-extractData Start:: " );
		
		PayAccountInfoResponse response = new PayAccountInfoResponse();
		
		List<PayAccount> payAccountList = new ArrayList<PayAccount>();
		
		
		while(rs.next()){
			
			PayAccount payAccount = new PayAccount();
			
			payAccount.setUserAccountNumber(rs.getString("USER_ACCOUNT_NUMBER"));
			payAccount.setOnlinePayAccountType(rs.getString("ONLINE_PAY_ACCOUNT_TYPE"));
			payAccount.setLastFourDigit(rs.getString("LAST_FOUR_DIGIT"));
			payAccount.setNameOnAccount(rs.getString("NAME_ON_ACCOUNT"));
			payAccount.setPayAccountNickName(rs.getString("PAY_ACCOUNT_NICKNAME"));
			payAccount.setPayAccountToken(rs.getString("PAY_ACCOUNT_TOKEN"));
			payAccount.setZipCode(rs.getString("ZIP_CODE"));
			payAccount.setActiveFlag(rs.getString("ACTIVE_FLAG"));
			payAccount.setLastPaymentDate(rs.getDate("LAST_PAYMENT_DATE"));
			payAccount.setActivationDate(rs.getDate("ACTIVATION_DATE"));
			payAccount.setCpdbCreationDate(rs.getDate("CPDB_CREATION_DATE"));
			payAccount.setCpdbUpdateDate(rs.getDate("CPDB_UPDATE_DATE"));
			payAccount.setVerifyCard(rs.getString("VERIFY_CARD"));
			payAccount.setRoutingNumber(rs.getString("ROUTING_NUMBER"));
			payAccount.setCcExpMonth(rs.getString("CC_EXP_MONTH"));
			payAccount.setCcExpYear(rs.getString("CC_EXP_YEAR"));
			payAccount.setOnlinePayAccountId(String.valueOf(rs.getInt("ONLINE_PAY_ACCOUNT_ID")));	
			payAccount.setCcType(rs.getString("CC_TYPE"));
			payAccount.setAutoPay(rs.getString("AUTO_PAY"));
			payAccount.setPaymentInstitutionName(rs.getString("PAYMENT_INSTITUTION_NAME"));
			payAccountList.add(payAccount);
			
		}
		
		response.setPayAccountList(payAccountList);
		
		logger.info("PayAccountRowMapper-extractData End:: " );
		return response;
	}

}
