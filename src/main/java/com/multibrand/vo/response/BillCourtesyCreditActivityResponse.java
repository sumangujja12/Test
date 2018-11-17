package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.util.Constants;

@XmlRootElement(name="BillCourtesyCreditActivityResponse")
@Component
public class BillCourtesyCreditActivityResponse implements Constants {
	
	private String resultCode;
	private String resultDescription=MSG_SUCCESS;
	private String returnObjectNo="";
	
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultDescription
	 */
	public String getResultDescription() {
		return resultDescription;
	}
	/**
	 * @param resultDescription the resultDescription to set
	 */
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	/**
	 * @return the returnObjectNo
	 */
	public String getReturnObjectNo() {
		return returnObjectNo;
	}
	/**
	 * @param returnObjectNo the returnObjectNo to set
	 */
	public void setReturnObjectNo(String returnObjectNo) {
		this.returnObjectNo = returnObjectNo;
	}
		
}
