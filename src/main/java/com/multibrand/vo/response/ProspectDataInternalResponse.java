package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProspectDataInternalResponse")
public class ProspectDataInternalResponse extends ProspectDataResponse  {

	private String prospectBpID;
	private String prospectBpIDType;
	private String prospectCreditScore;
	private String prospectCreditSource;
	private String prospectCreditScoreDate;
	private String prospectCreditBucket;

	public String getProspectBpID() {
		return prospectBpID;
	}

	public void setProspectBpID(String prospectBpID) {
		this.prospectBpID = prospectBpID;
	}

	public String getProspectBpIDType() {
		return prospectBpIDType;
	}

	public void setProspectBpIDType(String prospectBpIDType) {
		this.prospectBpIDType = prospectBpIDType;
	}

	public String getProspectCreditScore() {
		return prospectCreditScore;
	}

	public void setProspectCreditScore(String prospectCreditScore) {
		this.prospectCreditScore = prospectCreditScore;
	}

	public String getProspectCreditSource() {
		return prospectCreditSource;
	}

	public void setProspectCreditSource(String prospectCreditSource) {
		this.prospectCreditSource = prospectCreditSource;
	}
	
	public String getProspectCreditScoreDate() {
		return prospectCreditScoreDate;
	}

	public void setProspectCreditScoreDate(String prospectCreditScoreDate) {
		this.prospectCreditScoreDate = prospectCreditScoreDate;
	}

	public String getProspectCreditBucket() {
		return prospectCreditBucket;
	}

	public void setProspectCreditBucket(String prospectCreditBucket) {
		this.prospectCreditBucket = prospectCreditBucket;
	}
}
