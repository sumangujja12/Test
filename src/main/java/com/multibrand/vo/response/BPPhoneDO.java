package com.multibrand.vo.response;

public class BPPhoneDO {
	
	private java.lang.String phoneNumber;
    private java.lang.String remark;
    private java.lang.String smsActiveFlag;
    private java.lang.String phoneBounceFlag;
    
	public java.lang.String getSmsActiveFlag() {
		return smsActiveFlag;
	}
	public void setSmsActiveFlag(java.lang.String smsActiveFlag) {
		this.smsActiveFlag = smsActiveFlag;
	}
	public java.lang.String getPhoneBounceFlag() {
		return phoneBounceFlag;
	}
	public void setPhoneBounceFlag(java.lang.String phoneBounceFlag) {
		this.phoneBounceFlag = phoneBounceFlag;
	}
	public java.lang.String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(java.lang.String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
}
