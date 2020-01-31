package com.multibrand.vo.response.KBO;

import java.util.List;

public class Question {
	
	private int questionId;
	private String questionText;
	private int quizeId;
	
	private List<Option> options;
	
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuizeId() {
		return quizeId;
	}
	public void setQuizeId(int quizeId) {
		this.quizeId = quizeId;
	}

}
