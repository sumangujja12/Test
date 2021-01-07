package com.multibrand.vo.request;

public class TOSEligibleNonEligibleProductsRequest {
	
	//ESID='1008901012126233830100'&VKONT='000000279951'&VERTRAG=''&OFFER='50498249'&PROMO=''&ZIPCODE=''&BUKRS='0121'
	
	private String esid; 
	private String zipCode;
	private String caNumber;  //VKONT
	private String coNumber;  //VERTRAG
	private String offerCode;
	private String promoCode;
	private String companyCode;  //BUKRS
	
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getCoNumber() {
		return coNumber;
	}
	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Override
	public String toString() {
		return "TOSEligibleNonEligibleProductsRequest [esid=" + esid +
				", zipCode=" + zipCode + ", caNumber=" + caNumber + ","
				 + " coNumber=" + coNumber + ""
				 + ", offerCode= " + offerCode + " "
				 + ", promoCode= " + promoCode + ", "
				 +" , companyCode= " + companyCode + "]";
	}
	
}
