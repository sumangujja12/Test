package com.multibrand.dto.request;

import com.multibrand.exception.ValidateRequestException;

public class OAMMessageRequest implements BaseContentRequest{
	
	

	private String bpNumber, caNumber, coNumber, esiid, contractEndDate,companyCode, brandId, programName, displayMessage,type;
	
	
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



	public String getProgramName() {
		return programName;
	}



	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void validateRequest() throws ValidateRequestException {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OAMMessageRequest [bpNumber=");
		builder.append(bpNumber);
		builder.append(", caNumber=");
		builder.append(caNumber);
		builder.append(", coNumber=");
		builder.append(coNumber);
		builder.append(", esiid=");
		builder.append(esiid);
		builder.append(", contractEndDate=");
		builder.append(contractEndDate);
		builder.append(", companyCode=");
		builder.append(companyCode);
		builder.append(", brandId=");
		builder.append(brandId);
		builder.append(", programName=");
		builder.append(programName);
		builder.append(", displayMessage=");
		builder.append(displayMessage);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}


}
