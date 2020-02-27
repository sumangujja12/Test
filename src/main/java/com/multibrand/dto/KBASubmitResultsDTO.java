package com.multibrand.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.multibrand.util.Constants;
import com.multibrand.vo.response.KbaAnswerResponse;

public class KBASubmitResultsDTO {

	private List<KBAErrorDTO> errorList;
	private KbaAnswerResponseDTO KbaAnswerResponseDTO;
	private String returnCode;
	private String returnMessage;

	private String strErrCode;
	private String strErrMessage;
	public List<KBAErrorDTO> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<KBAErrorDTO> errorList) {
		this.errorList = errorList;
	}
	
	public KbaAnswerResponseDTO getKbaAnswerResponseDTO() {
		return KbaAnswerResponseDTO;
	}
	public void setKbaAnswerResponseDTO(KbaAnswerResponseDTO kbaAnswerResponseDTO) {
		KbaAnswerResponseDTO = kbaAnswerResponseDTO;
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
	public String getErrorCodeList(){
		String errorCodeList = StringUtils.EMPTY;
		if(errorList != null){
			for(KBAErrorDTO cartItem: this.errorList){
				errorCodeList = errorCodeList+cartItem.getErrorCode()+Constants.DELIMETER_PIPE;
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
