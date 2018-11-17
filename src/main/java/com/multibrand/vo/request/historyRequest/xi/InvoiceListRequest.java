package com.multibrand.vo.request.historyRequest.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT_get_InvoiceList_Request", namespace="http://reliant.com/xi/GreenMountain", propOrder = {
	    "accountId",
	    "startDate",
	    "stopDate"
	})
@XmlRootElement(name="MT_get_InvoiceList_Request", namespace="http://reliant.com/xi/GreenMountain")
public class InvoiceListRequest {
	
	 @XmlElement(name = "account_id")
	    protected String accountId;
	    @XmlElement(name = "start_date")
	    protected String startDate;
	    @XmlElement(name = "stop_date")
	    protected String stopDate;
	    
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getStopDate() {
			return stopDate;
		}
		public void setStopDate(String stopDate) {
			this.stopDate = stopDate;
		}

}
