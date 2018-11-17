package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;



@XmlRootElement(name="PendingSwapDetails")
@Component
public class PendingSwapDetailsResponse extends GenericResponse {

	
	private String strBPNumber;
	private String strCANumber;
	private PendingSwapDO[] pendingSwapDO ;
	
	

	
	/**
	 * @return the strBPNumber
	 */
	public String getStrBPNumber() {
		return strBPNumber;
	}

	/**
	 * @param strBPNumber the strBPNumber to set
	 */
	public void setStrBPNumber(String strBPNumber) {
		this.strBPNumber = strBPNumber;
	}

	/**
	 * @return the strCANumber
	 */
	public String getStrCANumber() {
		return strCANumber;
	}

	/**
	 * @param strCANumber the strCANumber to set
	 */
	public void setStrCANumber(String strCANumber) {
		this.strCANumber = strCANumber;
	}
	
	/**
	 * @return the pendingSwapDO
	 */
	public PendingSwapDO[] getPendingSwapDO() {
		return pendingSwapDO;
	}

	/**
	 * @param pendingSwapDO the pendingSwapDO to set
	 */
	public void setPendingSwapDO(PendingSwapDO[] pendingSwapDO) {
		this.pendingSwapDO = pendingSwapDO;
	}
}
