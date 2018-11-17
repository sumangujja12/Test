package com.multibrand.vo.response;

public class ValidateUsrNameResponse {

	private boolean bSuccessFlag;
	private String strErrorCode;
	private String strErrorText;
	public boolean isbSuccessFlag() {
		return bSuccessFlag;
	}
	public void setbSuccessFlag(boolean bSuccessFlag) {
		this.bSuccessFlag = bSuccessFlag;
	}
	public String getStrErrorCode() {
		return strErrorCode;
	}
	public void setStrErrorCode(String strErrorCode) {
		this.strErrorCode = strErrorCode;
	}
	public String getStrErrorText() {
		return strErrorText;
	}
	public void setStrErrorText(String strErrorText) {
		this.strErrorText = strErrorText;
	}
	
	
}
