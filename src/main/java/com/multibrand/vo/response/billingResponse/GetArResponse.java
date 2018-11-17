package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.vo.response.GenericResponse;


@XmlRootElement(name="GetArResponse")
@Component
public class GetArResponse extends GenericResponse{
	
	
	private String strCurrentDueDate;
	private String strCurrentARBalance;
	private String strLastPayAmt;
	private String strLastPayDate;	
	private String strPastDueAmt;
	private String strCreditAmt;
	
	public String getStrCreditAmt() {
		return strCreditAmt;
	}
	public void setStrCreditAmt(String strCreditAmt) {
		this.strCreditAmt = strCreditAmt;
	}
	@XmlElement(name="dueDate")
	public String getStrCurrentDueDate() {
		return strCurrentDueDate;
	}
	public void setStrCurrentDueDate(String strCurrentDueDate) {
		this.strCurrentDueDate = strCurrentDueDate;
	}
	
	@XmlElement(name="currentBalance")
	public String getStrCurrentARBalance() {
		return strCurrentARBalance;
	}
	public void setStrCurrentARBalance(String strCurrentARBalance) {
		this.strCurrentARBalance = strCurrentARBalance;
	}
	
	@XmlElement(name="lastPaymentAmount")
	public String getStrLastPayAmt() {
		return strLastPayAmt;
	}
	public void setStrLastPayAmt(String strLastPayAmt) {
		this.strLastPayAmt = strLastPayAmt;
	}
	
	@XmlElement(name="lastPaymentDate")
	public String getStrLastPayDate() {
		return strLastPayDate;
	}
	public void setStrLastPayDate(String strLastPayDate) {
		this.strLastPayDate = strLastPayDate;
	}
	
	@XmlElement(name="pastDueAmount")
	public String getStrPastDueAmt() {
		return strPastDueAmt;
	}
	public void setStrPastDueAmt(String strPastDueAmt) {
		this.strPastDueAmt = strPastDueAmt;
	}
	
}
