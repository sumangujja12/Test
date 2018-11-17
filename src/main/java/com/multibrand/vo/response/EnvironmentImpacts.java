package com.multibrand.vo.response;

import com.multibrand.util.CommonUtil;

public class EnvironmentImpacts {

	
	private String contractNumber;
	private String installation;
	private String moveOutDate;
	private String esiid;
	private String operand;
	private String value;
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getInstallation() {
		return installation;
	}
	public void setInstallation(String installation) {
		this.installation = installation;
	}
	public String getMoveOutDate() {
		return CommonUtil.changeDateFormat(moveOutDate);
	}
	public void setMoveOutDate(String moveOutDate) {
		this.moveOutDate = moveOutDate;
	}
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getOperand() {
		return operand;
	}
	public void setOperand(String operand) {
		this.operand = operand;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	

	}
