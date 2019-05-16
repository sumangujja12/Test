package com.multibrand.vo.response.billingResponse;


public class PayAccountDO{

	private boolean isAccountDuplicate;
	private boolean isNickNameExistsFlag;
	private boolean isCallSuccess;
	private boolean isCCExpMonthChange;
	private boolean isCCExpYearChange;
	private boolean isActiveFlagChange;
	
	public boolean getIsActiveFlagChange() {
		return isActiveFlagChange;
	}
	public void setActiveFlagChange(boolean isActiveFlagChange) {
		this.isActiveFlagChange = isActiveFlagChange;
	}
	private int rows;
	
	public boolean getIsCCExpMonthChange() {
		return isCCExpMonthChange;
	}
	public void setCCExpMonthChange(boolean isCCExpMonthChange) {
		this.isCCExpMonthChange = isCCExpMonthChange;
	}
	public boolean getIsCCExpYearChange() {
		return isCCExpYearChange;
	}
	public void setCCExpYearChange(boolean isCCExpYearChange) {
		this.isCCExpYearChange = isCCExpYearChange;
	}

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
