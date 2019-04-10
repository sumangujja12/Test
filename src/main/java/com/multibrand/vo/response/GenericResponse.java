package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.util.Constants;

/**
 * 
 * @author ahanda1
 *
 *Base Response class. All the REST response classes will extend this class.
 *
 */
@XmlRootElement
public class GenericResponse implements Constants{
	
	private String resultCode = RESULT_CODE_SUCCESS;
	private String resultDescription="";
	private String errorCode="";
	private String errorDescription="";
	private String messageCode="";
	private String messageText="";
	private String resultDisplayCode="";
 	private String resultDisplayText="";
	private String statusCode=STATUS_CODE_CONTINUE;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}
	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}
	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	/**
	 * @return the resultDisplayText
	 */
 	@JsonIgnore
	public String getResultDisplayCode() {
		return resultDisplayCode;
	}
	/**
	 * @param resultDisplayText the resultDisplayText to set
	 */
	public void setResultDisplayCode(String resultDisplayCode) {
		this.resultDisplayCode = resultDisplayCode;
	}
	public String getResultDisplayText() {
		return resultDisplayText;
	}
	public void setResultDisplayText(String resultDisplayText) {
		this.resultDisplayText = StringEscapeUtils.unescapeHtml(resultDisplayText);
	}
}