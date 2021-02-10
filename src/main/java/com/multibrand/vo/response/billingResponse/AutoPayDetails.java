package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.StringUtils;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

@XmlRootElement(name="AutoPayDetails")
public class AutoPayDetails {

	private java.lang.String bankAccountNumber;

    private java.lang.String bankId;

    private java.lang.String bankName;

    private java.lang.String bankRoutingNumber;

    private java.lang.String cardId;

    private java.lang.String cardNumber;

    private java.lang.String cardType;

    private java.lang.String contractAccount;

    private java.lang.String expDate;

    private java.lang.String payment;
    
  	private String bpid;
  	  
  	private String businessPartnerId;
  	
	public java.lang.String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(java.lang.String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public java.lang.String getBankId() {
		return bankId;
	}

	public void setBankId(java.lang.String bankId) {
		this.bankId = bankId;
	}

	public java.lang.String getBankName() {
		return bankName;
	}

	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}

	public java.lang.String getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

	public java.lang.String getCardId() {
		return cardId;
	}

	public void setCardId(java.lang.String cardId) {
		this.cardId = cardId;
	}

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

	public java.lang.String getContractAccount() {
		return contractAccount;
	}

	public void setContractAccount(java.lang.String contractAccount) {
		this.contractAccount = contractAccount;
	}

	public java.lang.String getExpDate() {
		if(StringUtils.isNotEmpty(expDate) && this.expDate.equalsIgnoreCase(Constants.invalidDate)){
			return "";
			}
		return CommonUtil.changeDateFormat(expDate);
	}

	public void setExpDate(java.lang.String expDate) {
		this.expDate = expDate;
	}

	public java.lang.String getPayment() {
		return payment;
	}

	public void setPayment(java.lang.String payment) {
		this.payment = payment;
	}

	public String getBpid() {
		return bpid;
	}

	public void setBpid(String bpid) {
		this.bpid = bpid;
		this.businessPartnerId = bpid;
	}
	

	public String getBusinessPartnerId() {
		return businessPartnerId;
	}
	public void setBusinessPartnerId(String businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}
}
