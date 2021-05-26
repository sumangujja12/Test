/**
 * 
 */
package com.multibrand.dto.request;

import javax.ws.rs.QueryParam;

public class CurrentEFLRequest {
	
	private static final long serialVersionUID = 1L;
	
	@QueryParam(value = "cA")
	private String cA;

	@QueryParam(value = "cO")
	private String cO;

	@QueryParam(value = "bP")
	private String bP;

	@QueryParam(value = "offerCode")
	private String offerCode;
	
	@QueryParam(value = "languageCode")
	private String languageCode;
	
	@QueryParam(value = "companyCode")
	private String companyCode;
	
	@QueryParam(value = "brandId")
	private String brandId;

	public String getcA() {
		return cA;
	}

	public void setcA(String cA) {
		this.cA = cA;
	}

	public String getcO() {
		return cO;
	}

	public void setcO(String cO) {
		this.cO = cO;
	}

	public String getbP() {
		return bP;
	}

	public void setbP(String bP) {
		this.bP = bP;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

}