package com.multibrand.vo.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.multibrand.dto.KBAErrorDTO;
import com.multibrand.dto.KBAResponseAssessmentDTO;
import com.multibrand.dto.KBAResponseReasonDTO;
import com.multibrand.util.Constants;

public class KbaAnswerResponse extends GenericResponse{
	private List<KBAErrorDTO> errorList;
	private String returnCode;
	private String returnMessage;

	private String strErrCode;
	private String strErrMessage;
	private String transactionKey;
	private String decision;
	private String identityScore;
	private String overallScore;
	private String interactiveQscore;
	private String fraudlevel;
	private List<KBAResponseReasonDTO> kbaReasonList =new ArrayList<KBAResponseReasonDTO>();
	private List<KBAResponseAssessmentDTO> verificationAssessmentList;
	private String ssnVerifyDate;
	private String drivingLicenceVerifyDate;
	private String errorMessage;
	
	private String companyCode;
	private String brandId;
	private String trackingId;
	
	
	
	public List<KBAResponseReasonDTO> getKbaReasonList() {
		return kbaReasonList;
	}
	public void setKbaReasonList(List<KBAResponseReasonDTO> kbaReasonList) {
		this.kbaReasonList = kbaReasonList;
	}
	public List<KBAResponseAssessmentDTO> getVerificationAssessmentList() {
		return verificationAssessmentList;
	}
	public void setVerificationAssessmentList(List<KBAResponseAssessmentDTO> verificationAssessmentList) {
		this.verificationAssessmentList = verificationAssessmentList;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
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
	
	public String getSsnVerifyDate() {
		return ssnVerifyDate;
	}
	public void setSsnVerifyDate(String ssnVerifyDate) {
		this.ssnVerifyDate = ssnVerifyDate;
	}
	public String getDrivingLicenceVerifyDate() {
		return drivingLicenceVerifyDate;
	}
	public void setDrivingLicenceVerifyDate(String drivingLicenceVerifyDate) {
		this.drivingLicenceVerifyDate = drivingLicenceVerifyDate;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<KBAErrorDTO> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<KBAErrorDTO> errorList) {
		this.errorList = errorList;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public String getStrErrCode() {
		return strErrCode;
	}
	public void setStrErrCode(String strErrCode) {
		this.strErrCode = strErrCode;
	}
	public String getStrErrMessage() {
		return strErrMessage;
	}
	public void setStrErrMessage(String strErrMessage) {
		this.strErrMessage = strErrMessage;
	}
	public String getTransactionKey() {
		return transactionKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getIdentityScore() {
		return identityScore;
	}
	public void setIdentityScore(String identityScore) {
		this.identityScore = identityScore;
	}
	public String getOverallScore() {
		return overallScore;
	}
	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
	}
	public String getInteractiveQscore() {
		return interactiveQscore;
	}
	public void setInteractiveQscore(String interactiveQscore) {
		this.interactiveQscore = interactiveQscore;
	}
	public String getFraudlevel() {
		return fraudlevel;
	}
	public void setFraudlevel(String fraudlevel) {
		this.fraudlevel = fraudlevel;
	}
	
	
	public String getReasonCodeList(){
		String reasonCodeList = StringUtils.EMPTY;
		if(kbaReasonList != null){
			for(KBAResponseReasonDTO kBAResponseReasonDTO: this.kbaReasonList){
				reasonCodeList = reasonCodeList+kBAResponseReasonDTO.getReasonCode()+Constants.DELIMETER_PIPE;
			}
			if(reasonCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				reasonCodeList = reasonCodeList.substring(0, reasonCodeList.length()-1);
			}
		}
		if(reasonCodeList.length()>256){
			reasonCodeList=reasonCodeList.substring(0,255);
		}
		return  reasonCodeList;
	}
	
}
