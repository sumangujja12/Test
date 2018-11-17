package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.CommonUtil;

@XmlRootElement(name="OfferOfContractResponse")
public class OfferOfContractResponse extends GenericResponse {
		
	private java.lang.String agreementTerms;

    private java.lang.String apartmentNumber;

    private TosCurrentOfferPriceCollection[] avgPrice;

    private java.lang.String campaignCode;

    private java.lang.String city;

    private java.lang.String companyCode;

    private java.lang.String contractExpiration;

    private java.lang.String contractTerm;

    private java.lang.String country;

    private java.lang.String currentPlan;

    private java.lang.String customerRights;

    private java.lang.String electricityFactsLabel;

    private java.lang.String offerCellTrackingCode;

    private java.lang.String offerCode;

    private java.lang.String POBox;

    private java.lang.String productPriceCode;

    private java.lang.String state;

    private java.lang.String streetName;

    private java.lang.String streetNumber;

    private java.lang.String zip;

	public java.lang.String getAgreementTerms() {
		return agreementTerms;
	}

	public void setAgreementTerms(java.lang.String agreementTerms) {
		this.agreementTerms = agreementTerms;
	}

	public java.lang.String getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(java.lang.String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public TosCurrentOfferPriceCollection[] getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(TosCurrentOfferPriceCollection[] avgPrice) {
		this.avgPrice = avgPrice;
	}

	public java.lang.String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(java.lang.String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.lang.String getContractExpiration() {
		return contractExpiration;
	}

	public void setContractExpiration(java.lang.String contractExpiration) {
		this.contractExpiration = CommonUtil.changeDateFormat(contractExpiration);
	}

	public java.lang.String getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(java.lang.String contractTerm) {
		this.contractTerm = contractTerm;
	}

	public java.lang.String getCountry() {
		return country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	public java.lang.String getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(java.lang.String currentPlan) {
		this.currentPlan = currentPlan;
	}

	public java.lang.String getCustomerRights() {
		return customerRights;
	}

	public void setCustomerRights(java.lang.String customerRights) {
		this.customerRights = customerRights;
	}

	public java.lang.String getElectricityFactsLabel() {
		return electricityFactsLabel;
	}

	public void setElectricityFactsLabel(java.lang.String electricityFactsLabel) {
		this.electricityFactsLabel = electricityFactsLabel;
	}

	public java.lang.String getOfferCellTrackingCode() {
		return offerCellTrackingCode;
	}

	public void setOfferCellTrackingCode(java.lang.String offerCellTrackingCode) {
		this.offerCellTrackingCode = offerCellTrackingCode;
	}

	public java.lang.String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(java.lang.String offerCode) {
		this.offerCode = offerCode;
	}

	public java.lang.String getPOBox() {
		return POBox;
	}

	public void setPOBox(java.lang.String pOBox) {
		POBox = pOBox;
	}

	public java.lang.String getProductPriceCode() {
		return productPriceCode;
	}

	public void setProductPriceCode(java.lang.String productPriceCode) {
		this.productPriceCode = productPriceCode;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getStreetName() {
		return streetName;
	}

	public void setStreetName(java.lang.String streetName) {
		this.streetName = streetName;
	}

	public java.lang.String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(java.lang.String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public java.lang.String getZip() {
		return zip;
	}

	public void setZip(java.lang.String zip) {
		this.zip = zip;
	}

}
