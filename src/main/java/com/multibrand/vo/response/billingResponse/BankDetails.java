package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BankDetails")
public class BankDetails {

	    private java.lang.String bankAccountNumber;

	    private java.lang.String bankRoutingNumber;
	    
	    private String bankAccountType;
	    
	    private String bankAccountHolderType;
	    
	    private String nameOnAccount;
	    
	    private String onlinePayAccountNickName;
	    
	    private String onlinePayAccountId;
	    
	    private String defaultFlag;

		public java.lang.String getBankAccountNumber() {
			return bankAccountNumber;
		}

		public void setBankAccountNumber(java.lang.String bankAccountNumber) {
			this.bankAccountNumber = bankAccountNumber;
		}

		public java.lang.String getBankRoutingNumber() {
			return bankRoutingNumber;
		}

		public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
			this.bankRoutingNumber = bankRoutingNumber;
		}

		public String getBankAccountType() {
			return bankAccountType;
		}

		public void setBankAccountType(String bankAccountType) {
			this.bankAccountType = bankAccountType;
		}

		public String getBankAccountHolderType() {
			return bankAccountHolderType;
		}

		public void setBankAccountHolderType(String bankAccountHolderType) {
			this.bankAccountHolderType = bankAccountHolderType;
		}

		public String getNameOnAccount() {
			return nameOnAccount;
		}

		public void setNameOnAccount(String nameOnAccount) {
			this.nameOnAccount = nameOnAccount;
		}

		public String getOnlinePayAccountNickName() {
			return onlinePayAccountNickName;
		}

		public void setOnlinePayAccountNickName(String onlinePayAccountNickName) {
			this.onlinePayAccountNickName = onlinePayAccountNickName;
		}

		public String getOnlinePayAccountId() {
			return onlinePayAccountId;
		}

		public void setOnlinePayAccountId(String onlinePayAccountId) {
			this.onlinePayAccountId = onlinePayAccountId;
		}

		public String getDefaultFlag() {
			return defaultFlag;
		}

		public void setDefaultFlag(String defaultFlag) {
			this.defaultFlag = defaultFlag;
		}
   
	    
}
