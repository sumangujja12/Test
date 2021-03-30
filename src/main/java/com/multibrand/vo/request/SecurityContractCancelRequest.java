package com.multibrand.vo.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class SecurityContractCancelRequest implements Serializable {
	private static final long serialVersionUID = 5313732309176983924L;
	
	 private String straxLeadID;
	 private String caNumber;
	 private String cancellationDate;
	
     

	public SecurityContractCancelRequest(String straxLeadID, String caNumber, String cancellationDate) {
		super();
		this.straxLeadID = straxLeadID;
		this.caNumber = caNumber;
		this.cancellationDate = cancellationDate;
	}

	public SecurityContractCancelRequest() {
	
	}

	public String getStraxLeadID() {
		return straxLeadID;
	}

	public void setStraxLeadID(String straxLeadID) {
		this.straxLeadID = straxLeadID;
	}

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	

	
	
	

}
