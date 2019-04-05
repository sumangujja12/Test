package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;
import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name = "mobileResponse")
@Component
public class ArMobileGMEResponse extends GenericResponse {

	private String currentDueDate;
	private String currentArBalance;
	private String lastPayAmt;
	private String lastPayDate;
	private String pastDueAmt;
	private String creditAmt;
	private String recentPendingPayDate;

	public String getRecentPendingPayDate() {
		return recentPendingPayDate;
	}

	public void setRecentPendingPayDate(String recentPendingPayDate) {
		this.recentPendingPayDate = recentPendingPayDate;
	}

	public String getCurrentDueDate() {
		return currentDueDate;
	}

	public void setCurrentDueDate(String currentDueDate) {
		this.currentDueDate = currentDueDate;
	}

	public String getCurrentArBalance() {
		return currentArBalance;
	}

	public void setCurrentArBalance(String currentArBalance) {
		this.currentArBalance = currentArBalance;
	}

	public String getLastPayAmt() {
		return lastPayAmt;
	}

	public void setLastPayAmt(String lastPayAmt) {
		this.lastPayAmt = lastPayAmt;
	}

	public String getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(String lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	public String getPastDueAmt() {
		return pastDueAmt;
	}

	public void setPastDueAmt(String pastDueAmt) {
		this.pastDueAmt = pastDueAmt;
	}

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

}
