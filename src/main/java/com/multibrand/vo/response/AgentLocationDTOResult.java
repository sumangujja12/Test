package com.multibrand.vo.response;

import com.google.gson.annotations.SerializedName;

public class AgentLocationDTOResult {
  
	@SerializedName("agentID")
	private String agentID;
	@SerializedName("partnerID")
    private String partnerID;
	@SerializedName("locationID")
    private String locationID;
	@SerializedName("promoCode")
    private String promoCode;
	@SerializedName("locationDescription")
    private String locationDescription;
	
	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
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

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	@Override
	public String toString() {
		return "CheckReliantCustomerStatusOutData [promoCode=" + promoCode + ", locationID="	+ locationID + "]";
	}
	

}