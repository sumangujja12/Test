package com.multibrand.dto;

import java.util.List;

public class ContentOfferDTO {
	

	private String bpNumber, caNumber, coNumber, esiid, languageCode,contractEndDate, brandId, companyCode;
	private List<CCSDTO> ccsList;
	private List<String> programIdList;
	public String getBpNumber() {
		return bpNumber;
	}
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
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
	public String getEsiid() {
		return esiid;
	}
	public void setEsiid(String esiid) {
		this.esiid = esiid;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public List<CCSDTO> getCcsList() {
		return ccsList;
	}
	public void setCcsList(List<CCSDTO> ccsList) {
		this.ccsList = ccsList;
	}
	public List<String> getProgramIdList() {
		return programIdList;
	}
	public void setProgramIdList(List<String> programIdList) {
		this.programIdList = programIdList;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContentOfferDTO [bpNumber=");
		builder.append(bpNumber);
		builder.append(", caNumber=");
		builder.append(caNumber);
		builder.append(", coNumber=");
		builder.append(coNumber);
		builder.append(", esiid=");
		builder.append(esiid);
		builder.append(", languageCode=");
		builder.append(languageCode);
		builder.append(", contractEndDate=");
		builder.append(contractEndDate);
		builder.append(", brandId=");
		builder.append(brandId);
		builder.append(", companyCode=");
		builder.append(companyCode);
		builder.append(", ccsList=");
		builder.append(ccsList);
		builder.append(", programIdList=");
		builder.append(programIdList);
		builder.append("]");
		return builder.toString();
	}	

}
