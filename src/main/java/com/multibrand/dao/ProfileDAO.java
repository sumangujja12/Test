package com.multibrand.dao;

public interface ProfileDAO {
	
	public String insertTransaction(String userUniqueID,String companyCode);
	
	public boolean validatePasswordLink(String transactionId);

	public String getUserNameforTxn(String transactionId);

	public int updateStatusFlag(String userName);
}
