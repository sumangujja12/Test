package com.multibrand.vo.response;


import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.dto.response.SalesBaseResponse;

@XmlRootElement(name="ProspectDataResponse")
public class ProspectDataResponse extends SalesBaseResponse  {

	private String prospectPreApprovalFlag;

	public String getProspectPreApprovalFlag() {
		return prospectPreApprovalFlag;
	}

	public void setProspectPreApprovalFlag(String prospectPreApprovalFlag) {
		this.prospectPreApprovalFlag = prospectPreApprovalFlag;
	}
}
