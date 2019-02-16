package com.multibrand.dao;

public interface ProfileDAO {
	
	public String insertTransaction(String userUniqueID,String companyCode);
	
	public boolean validatePasswordLink(String transactionId);
	
	public boolean checkPasswordLinkValidity(String transactionId);
	
	public boolean updatePasswordLinkValidity(String transactionId);

}
