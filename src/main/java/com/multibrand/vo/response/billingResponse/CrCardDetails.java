package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrCardDetails")
public class CrCardDetails {
	
    private java.lang.String cardNumber;

    private java.lang.String cardType;
    
    private String billingZipCode;
    
    private String expYear;
    
    private String expMonth;
    
    private String onlinePayAccountNickName;
    
    private String onlinePayAccountId;
    
    private String nameOnAccount;
    
    private String defaultFlag;

	
	public java.lang.String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(java.lang.String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public java.lang.String getCardType() {
		return cardType;
	}

	public void setCardType(java.lang.String cardType) {
		this.cardType = cardType;
	}

	public String getBillingZipCode() {
		return billingZipCode;
	}

	public void setBillingZipCode(String billingZipCode) {
		this.billingZipCode = billingZipCode;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
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

	public String getNameOnAccount() {
		return nameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		this.nameOnAccount = nameOnAccount;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

}
