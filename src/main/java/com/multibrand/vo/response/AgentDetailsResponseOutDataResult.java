package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="result1")
public class AgentDetailsResponseOutDataResult {
	
	@SerializedName("agentID")
	private String agentID;
	@SerializedName("agentType")
    private String agentType;
    @SerializedName("agentTypeDesc")
    private String agentTypeDesc;
	@SerializedName("agentFirstName")
    private String agentFirstName;
	@SerializedName("agentLastName")
    private String agentLastName;
	@SerializedName("agentVendorCode")
    private String agentVendorCode;
	@SerializedName("agentVendorName")
    private String agentVendorName;
	@SerializedName("AgentPartnerSet")
    private AgentPartnerDTO agentPartnerDTO;
	
	
	
	public String getAgentTypeDesc() {
		return agentTypeDesc;
	}
	public void setAgentTypeDesc(String agentTypeDesc) {
		this.agentTypeDesc = agentTypeDesc;
	}
	public String getAgentVendorName() {
		return agentVendorName;
	}
	public void setAgentVendorName(String agentVendorName) {
		this.agentVendorName = agentVendorName;
	}
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getAgentFirstName() {
		return agentFirstName;
	}

	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}

	public String getAgentLastName() {
		return agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public String getAgentVendorCode() {
		return agentVendorCode;
	}

	public void setAgentVendorCode(String agentVendorCode) {
		this.agentVendorCode = agentVendorCode;
	}

	public AgentPartnerDTO getAgentPartnerDTO() {
		return agentPartnerDTO;
	}

	public void setAgentPartnerDTO(AgentPartnerDTO agentPartnerDTO) {
		this.agentPartnerDTO = agentPartnerDTO;
	}

	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [agentID=" + agentID + ", agentType="
				+ agentType + ",firstName=" + agentFirstName + ", lastName="
				+ agentLastName + ",agentVendorCode="
				+ agentVendorCode + "]";
	}
    
	
}
