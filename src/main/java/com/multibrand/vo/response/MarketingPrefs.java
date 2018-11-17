package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MarketingPrefs")
public class MarketingPrefs {

	private String prodServMktFlag;
	private String thirdPartyMktFlag;
	private String efficiencyMktFlag;
	
	public String getProdServMktFlag() {
		return prodServMktFlag;
	}
	public void setProdServMktFlag(String prodServMktFlag) {
		this.prodServMktFlag = prodServMktFlag;
	}
	public String getThirdPartyMktFlag() {
		return thirdPartyMktFlag;
	}
	public void setThirdPartyMktFlag(String thirdPartyMktFlag) {
		this.thirdPartyMktFlag = thirdPartyMktFlag;
	}
	public String getEfficiencyMktFlag() {
		return efficiencyMktFlag;
	}
	public void setEfficiencyMktFlag(String efficiencyMktFlag) {
		this.efficiencyMktFlag = efficiencyMktFlag;
	}
	
	
	
	
}
