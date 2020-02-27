package com.multibrand.vo.request;

import com.multibrand.util.CommonUtil;

public class KBAQuestionAnswerVO {
	private int quizId;
	private int questionId;
	private int answerId;
	
	
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
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	
	public String toString(){
		return 	CommonUtil.doRender(this);
	}
}
