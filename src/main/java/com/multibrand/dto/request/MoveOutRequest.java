package com.multibrand.dto.request;

import com.multibrand.util.CommonUtil;

public class MoveOutRequest implements FormEntityRequest {
	
	private String contractAccountNumber;
	private String esiId;
	private String moveOutDate;
	private String moveOutReason;
	
	/**
	 * @return the contractAccountNumber
	 */
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	/**
	 * @param contractAccountNumber the contractAccountNumber to set
	 */
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	/**
	 * @return the esiId
	 */
	public String getEsiId() {
		return esiId;
	}
	/**
	 * @param esiId the esiId to set
	 */
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	/**
	 * @return the moveOutDate
	 */
	public String getMoveOutDate() {
		return moveOutDate;
	}
	/**
	 * @param moveOutDate the moveOutDate to set
	 */
	public void setMoveOutDate(String moveOutDate) {
		this.moveOutDate = moveOutDate;
	}
	/**
	 * @return the moveOutReason
	 */
	public String getMoveOutReason() {
		return moveOutReason;
	}
	/**
	 * @param moveOutReason the moveOutReason to set
	 */
	public void setMoveOutReason(String moveOutReason) {
		this.moveOutReason = moveOutReason;
	}
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
