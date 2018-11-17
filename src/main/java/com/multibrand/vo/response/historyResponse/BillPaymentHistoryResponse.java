package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="BillPaymentHistoryResponse")
public class BillPaymentHistoryResponse extends GenericResponse{
	
	private BillPaymentHistory[] billPaymentHistory;
	
	private String ccsDataFlag ="O";
	
	@XmlElement(name="billDo")
	public BillPaymentHistory[] getBillPaymentHistory() {
		return billPaymentHistory;
	}

	public void setBillPaymentHistory(BillPaymentHistory[] billPaymentHistory) {
		this.billPaymentHistory = billPaymentHistory;
	}

	


	public String getCcsDataFlag() {
		return ccsDataFlag;
	}

	public void setCcsDataFlag(String ccsDataFlag) {
		this.ccsDataFlag = ccsDataFlag;
	}

	 
}
