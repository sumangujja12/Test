package com.multibrand.vo.request;

import java.io.Serializable;

public class StraxContractCancelRequest implements Serializable {
	private static final long serialVersionUID = 5313732309176983924L;
	
	 private String straxLeadID;
	 private String caNumber;
	 private String cancellationDate;
	
     

	public StraxContractCancelRequest(String straxLeadID, String caNumber, String cancellationDate) {
		super();
		this.straxLeadID = straxLeadID;
		this.caNumber = caNumber;
		this.cancellationDate = cancellationDate;
	}

	public StraxContractCancelRequest() {
	
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
