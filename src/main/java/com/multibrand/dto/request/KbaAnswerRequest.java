package com.multibrand.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.multibrand.dto.KBAErrorDTO;
import com.multibrand.dto.KBAQuestionDTO;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.KbaAnswerResponse;


public class KbaAnswerRequest extends BaseAffiliateRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionKey;
	private String trackingId;
	private List<KBAQuestionDTO> questionList; 
	private String returnCode;
	private String returnMessage;
	private List<KBAErrorDTO> errorList = new ArrayList<KBAErrorDTO>();
	
	private String strErrCode;
	private String strErrMessage;
	private KbaAnswerResponse kbaAnswerResponse;
	

	public KbaAnswerResponse getKbaAnswerResponse() {
		return kbaAnswerResponse;
	}

	public void setKbaAnswerResponse(KbaAnswerResponse kbaAnswerResponse) {
		this.kbaAnswerResponse = kbaAnswerResponse;
	}

	public List<KBAQuestionDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<KBAQuestionDTO> questionList) {
		this.questionList = questionList;
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

	public List<KBAErrorDTO> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<KBAErrorDTO> errorList) {
		this.errorList = errorList;
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

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	
	public String getErrorCodeList(){
		String errorCodeList = StringUtils.EMPTY;
		if(errorList != null){
			for(KBAErrorDTO kBAErrorDTO: this.errorList){
				errorCodeList = errorCodeList+kBAErrorDTO.getErrorCode()+Constants.DELIMETER_PIPE;
			}
			if(errorCodeList.endsWith(Constants.DELIMETER_PIPE)) {
				errorCodeList = errorCodeList.substring(0, errorCodeList.length()-1);
			}
		}
		if(errorCodeList.length()>40){
			errorCodeList=errorCodeList.substring(0,39);
		}
		return  errorCodeList;
	}
	
	public String getErrorMsgList(){
		String errorMsgList = StringUtils.EMPTY;
		if(errorList != null){
			for(KBAErrorDTO kBAErrorDTO: this.errorList){
				errorMsgList = errorMsgList+kBAErrorDTO.getErrorMsg()+Constants.DELIMETER_PIPE;
			}
			if(errorMsgList.endsWith(Constants.DELIMETER_PIPE)) {
				errorMsgList = errorMsgList.substring(0, errorMsgList.length()-1);
			}
		}
		if(errorMsgList.length()>256){
			errorMsgList=errorMsgList.substring(0,255);
		}
		return  errorMsgList;
	}
	
	

	public String getSapReturnCd(){
		
		String sap_return_cd=null;
		if(StringUtils.isNotBlank(this.strErrCode)){
			sap_return_cd = "99999";
		}else{
			sap_return_cd = this.returnCode;
		}
		return  sap_return_cd;
	}
	
	public String getSapReturnMsg(){
		
		String sap_return_msg=null;
		if(StringUtils.isNotBlank(this.strErrCode)){
			
			sap_return_msg = this.strErrMessage;
		}else{
			
			sap_return_msg = this.returnMessage;
		}
		return  sap_return_msg;
	}
	
}
