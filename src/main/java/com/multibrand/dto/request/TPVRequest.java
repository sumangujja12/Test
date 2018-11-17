/**
 * 
 */
package com.multibrand.dto.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.util.Constants;

/**
 * @author kbhulla1
 *
 */
public class TPVRequest  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getIvAcctNum() {
		return IvAcctNum;
	}
	public void setIvAcctNum(String ivAcctNum) {
		IvAcctNum = ivAcctNum;
	}
	public String getIvApprovedBy() {
		return IvApprovedBy;
	}
	public void setIvApprovedBy(String ivApprovedBy) {
		IvApprovedBy = ivApprovedBy;
	}
	public String getIvDateTime() {
		return IvDateTime;
	}
	public void setIvDateTime(String ivDateTime) {
		IvDateTime = ivDateTime;
	}
	public String getIvReason() {
		return IvReason;
	}
	public void setIvReason(String ivReason) {
		IvReason = ivReason;
	}
	public String getIvResult() {
		return IvResult;
	}
	public void setIvResult(String ivResult) {
		IvResult = ivResult;
	}
	public String getIvTransactionid() {
		return IvTransactionid;
	}
	public void setIvTransactionid(String ivTransactionid) {
		IvTransactionid = ivTransactionid;
	}
	public String IvAcctNum;
	public String IvApprovedBy;
	public String IvDateTime;
	public String IvReason;
	public String IvResult;
	public String IvTransactionid;
}
