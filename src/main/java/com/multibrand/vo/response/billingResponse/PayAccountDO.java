package com.multibrand.vo.response.billingResponse;


public class PayAccountDO{

	private boolean isAccountDuplicate;
	private boolean isNickNameExistsFlag;
	private boolean isCallSuccess;
	private int rows;
	
	public boolean isAccountDuplicate() {
		return isAccountDuplicate;
	}
	public void setAccountDuplicate(boolean isAccountDuplicate) {
		this.isAccountDuplicate = isAccountDuplicate;
	}
	public boolean isNickNameExistsFlag() {
		return isNickNameExistsFlag;
	}
	public void setNickNameExistsFlag(boolean isNickNameExistsFlag) {
		this.isNickNameExistsFlag = isNickNameExistsFlag;
	}
	public boolean isCallSuccess() {
		return isCallSuccess;
	}
	public void setCallSuccess(boolean isCallSuccess) {
		this.isCallSuccess = isCallSuccess;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	
}
