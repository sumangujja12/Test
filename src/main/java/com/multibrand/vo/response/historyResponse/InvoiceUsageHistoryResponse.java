package com.multibrand.vo.response.historyResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="InvoiceUsageHistoryResponse")
public class InvoiceUsageHistoryResponse extends GenericResponse {
	
	
	private BillPaymentHistory[] billPaymentHistory;

	@XmlElement(name="billDo")
	public BillPaymentHistory[] getBillPaymentHistory() {
		return billPaymentHistory;
	}

	public void setBillPaymentHistory(BillPaymentHistory[] billPaymentHistory) {
		this.billPaymentHistory = billPaymentHistory;
	}
	
	
	

}
