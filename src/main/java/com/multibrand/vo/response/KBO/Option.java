package com.multibrand.vo.response.KBO;

public class Option {
	
	private int optionId;
	private String optionText;
	private String keyAnswer = null ;  
	
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	public String getKeyAnswer() {
		return keyAnswer;
	}
	public void setKeyAnswer(String keyAnswer) {
		this.keyAnswer = keyAnswer;
	}
	

}
