package com.multibrand.dto;

import java.io.Serializable;


public class KBAResponseAssessmentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assessmentName;
	private String assessmentValue;
	
	
	public String getAssessmentName() {
		return assessmentName;
	}
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	public String getAssessmentValue() {
		return assessmentValue;
	}
	public void setAssessmentValue(String assessmentValue) {
		this.assessmentValue = assessmentValue;
	}

}
