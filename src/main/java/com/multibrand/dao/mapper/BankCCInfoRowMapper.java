package com.multibrand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.multibrand.util.Constants;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankDetails;
import com.multibrand.vo.response.billingResponse.CrCardDetails;

public class BankCCInfoRowMapper implements ResultSetExtractor<BankCCInfoResponse>, Constants {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Override
	public BankCCInfoResponse extractData(ResultSet rs)
			throws SQLException {
		
		
		logger.info("Start BankCCInfoRowMapper :::");
		
		BankCCInfoResponse response = new BankCCInfoResponse();
		
		List<BankDetails> bankList = new ArrayList<BankDetails>() ;

	    List<CrCardDetails> ccList = new ArrayList<CrCardDetails>();

	    
	    while(rs.next()){
	    
	    	// extracting the bank records
	    	if(ONLINE_ACCOUNT_TYPE_BANK.equalsIgnoreCase(rs.getString("ONLINE_PAY_ACCOUNT_TYPE"))){
	    		BankDetails bank = new BankDetails();
	    		bank.setBankAccountHolderType(rs.getString("BANK_ACCT_HOLDER_TYPE"));
	    		bank.setBankAccountNumber(rs.getString("TOKEN_BANK_ACCT_NUMBER"));
	    		bank.setBankAccountType(rs.getString("BANK_ACCT_TYPE"));
	    		bank.setBankRoutingNumber(rs.getString("ROUTING_NUMBER"));
	    		bank.setDefaultFlag(rs.getString("DEFAULT_FLAG"));
	    		bank.setNameOnAccount(rs.getString("NAME_ON_ACCOUNT"));
	    		bank.setOnlinePayAccountId(String.valueOf(rs.getInt("ONLINE_PAY_ACCOUNT_ID")));
	    		bank.setOnlinePayAccountNickName(rs.getString("PAY_ACCOUNT_NICKNAME"));
	    		bankList.add(bank);
	    		
	    	}
	    	
	    	//extracting cc records
             if(ONLINE_ACCOUNT_TYPE_CC.equalsIgnoreCase(rs.getString("ONLINE_PAY_ACCOUNT_TYPE"))){
	    		CrCardDetails card = new CrCardDetails();
	    	    	
	    		card.setDefaultFlag(rs.getString("DEFAULT_FLAG"));
	    		card.setNameOnAccount(rs.getString("NAME_ON_ACCOUNT"));
	    		card.setOnlinePayAccountId(String.valueOf(rs.getInt("ONLINE_PAY_ACCOUNT_ID")));
	    		card.setOnlinePayAccountNickName(rs.getString("PAY_ACCOUNT_NICKNAME"));
	    		card.setBillingZipCode(rs.getString("CC_BILLING_ZIP_CODE"));
	    		card.setCardNumber(rs.getString("TOKEN_CC_NUMBER"));
	    		card.setCardType(rs.getString("CC_TYPE"));
	    		card.setExpMonth(String.valueOf(rs.getInt("CC_EXP_MONTH")));
	    		card.setExpYear(String.valueOf(rs.getInt("CC_EXP_YEAR")));
	    		ccList.add(card);
	    	}	    	
	    	
	    }
	    
	    //adding the bank and cclist to main response
	    response.setBankList(bankList);
	    response.setCcList(ccList);
	    
	    logger.info("End BankCCInfoRowMapper :::");
		
		return response;
	}
	
	

}
