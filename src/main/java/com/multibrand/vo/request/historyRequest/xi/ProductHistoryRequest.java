package com.multibrand.vo.request.historyRequest.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_get_ProductHistory_Request", namespace = "http://reliant.com/xi/GreenMountain", propOrder = { "accountId","language" })
@XmlRootElement(name = "MT_get_ProductHistory_Request", namespace = "http://reliant.com/xi/GreenMountain")
public class ProductHistoryRequest {

	@XmlElement(name = "account_id")
	protected String accountId;
	
	@XmlElement(name = "language")
	protected String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
