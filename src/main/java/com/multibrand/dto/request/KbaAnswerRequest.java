package com.multibrand.dto.request;

import java.util.List;
import com.multibrand.vo.request.KBAQuestionAnswerVO;


public class KbaAnswerRequest extends SalesOERequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionKey;
	private List<KBAQuestionAnswerVO> questionList; 	

	public List<KBAQuestionAnswerVO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<KBAQuestionAnswerVO> questionList) {
		this.questionList = questionList;
	}

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}	
}
