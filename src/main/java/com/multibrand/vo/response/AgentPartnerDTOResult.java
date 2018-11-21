package com.multibrand.vo.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AgentPartnerDTOResult {
  
	@SerializedName("agentID")
	private String agentID;
	@SerializedName("partnerID")
    private String partnerID;
	@SerializedName("Dealer")
    private String dealer;
	@SerializedName("agentVendorCode")
    private String agentVendorCode;
	@SerializedName("partnerDescription")
    private String partnerDescription;
	@SerializedName("PartnerPromoSet")
    private AgentLocationDTO agentLocationDTO;
	
	
	public String getPartnerDescription() {
		return partnerDescription;
	}

	public void setPartnerDescription(String partnerDescription) {
		this.partnerDescription = partnerDescription;
	}

	public String getAgentVendorCode() {
		return agentVendorCode;
	}

	public void setAgentVendorCode(String agentVendorCode) {
		this.agentVendorCode = agentVendorCode;
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public AgentLocationDTO getAgentLocationDTO() {
		return agentLocationDTO;
	}

	public void setAgentLocationDTO(AgentLocationDTO agentLocationDTO) {
		this.agentLocationDTO = agentLocationDTO;
	}

	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [dealer=" + dealer + ", partnerID="	+ partnerID + "]";
	}
	

}