package com.multibrand.vo.response;


import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="AgentDetailsResponse")
public class ProspectDataResponse extends GenericResponse  {


	private String prospectBpID;
	private String prospectBpIDType;
	private String prospectPreApprovalFlag;
	private String prospectCreditScore;
	private String prospectCreditSource;
	private String prospectCreditScoreDate;
	private String prospectCreditBucket;
	private Response.Status httpStatus;
	
	


	public Response.Status getHttpStatus() {
		return httpStatus;
	}



	public void setHttpStatus(Response.Status httpStatus) {
		this.httpStatus = httpStatus;
	}



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



	public String getProspectPreApprovalFlag() {
		return prospectPreApprovalFlag;
	}



	public void setProspectPreApprovalFlag(String prospectPreApprovalFlag) {
		this.prospectPreApprovalFlag = prospectPreApprovalFlag;
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
