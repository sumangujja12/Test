package com.multibrand.dto;

import java.io.Serializable;
import java.util.List;

public class KBAQuestionDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quizId;
	private int questionId;
	private String questionText;
	
	private List<KBAAnswerDTO> answerList;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<KBAAnswerDTO> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<KBAAnswerDTO> answerList) {
		this.answerList = answerList;
	}
	
	
}
