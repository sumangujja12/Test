package com.multibrand.vo.response.swapResponse;

import javax.xml.bind.annotation.XmlRootElement;



import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.PendingSwapDO;

@XmlRootElement(name="PendingSwapResponse")
public class PendingSwapResponse extends GenericResponse {

	private String strBPNumber = "";
	private String strCANumber = "";
	private PendingSwapDO[] pendingSwapDO  ;
	private String errorMessage = "";
	private String errorCode = "";
	public String getStrBPNumber() {
		return strBPNumber;
	}
	public void setStrBPNumber(String strBPNumber) {
		this.strBPNumber = strBPNumber;
	}
	public String getStrCANumber() {
		return strCANumber;
	}
	public void setStrCANumber(String strCANumber) {
		this.strCANumber = strCANumber;
	}
	public PendingSwapDO[] getPendingSwapDO() {
		return pendingSwapDO;
	}
	public void setPendingSwapDO(PendingSwapDO[] pendingSwapDO) {
		this.pendingSwapDO = pendingSwapDO;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
