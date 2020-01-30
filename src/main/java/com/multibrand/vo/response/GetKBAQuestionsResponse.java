package com.multibrand.vo.response;

import java.util.List;


import com.multibrand.vo.response.KBO.Question;



public class GetKBAQuestionsResponse extends GenericResponse {

	private static final long serialVersionUID = 1L;
	
	private String transactionKey;
	private String trackingId= null;	
	private List<Question> questions;
	
	public String getTransactionKey() {
		return transactionKey;
	}
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	
	
}
