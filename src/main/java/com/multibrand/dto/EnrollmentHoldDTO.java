package com.multibrand.dto;

import com.multibrand.util.CommonUtil;

public class EnrollmentHoldDTO {

	private String holdType;
	private String holdStatus;
	private String holdTitle;
	private String holdDescription;
	
	public String getHoldType() {
		return holdType;
	}
	public void setHoldType(String holdType) {
		this.holdType = holdType;
	}
	public String getHoldStatus() {
		return holdStatus;
	}
	public void setHoldStatus(String holdStatus) {
		this.holdStatus = holdStatus;
	}

	
	public String getHoldTitle() {
		return holdTitle;
	}
	public void setHoldTitle(String holdTitle) {
		this.holdTitle = holdTitle;
	}
	public String getHoldDescription() {
		return holdDescription;
	}
	public void setHoldDescription(String holdDescription) {
		this.holdDescription = holdDescription;
	}
	public String toString(){
		return CommonUtil.doRender(this);
	}
	
	
	
}