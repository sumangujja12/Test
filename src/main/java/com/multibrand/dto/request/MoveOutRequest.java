package com.multibrand.dto.request;

public class MoveOutRequest implements FormEntityRequest {
	
	private String contractAccountNumber;
	private String esiId;
	private String futureDate;
	private String moveOutReason;
	
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getEsiId() {
		return esiId;
	}
	public void setEsiId(String esiId) {
		this.esiId = esiId;
	}
	public String getFutureDate() {
		return futureDate;
	}
	public void setFutureDate(String futureDate) {
		this.futureDate = futureDate;
	}
	public String getMoveOutReason() {
		return moveOutReason;
	}
	public void setMoveOutReason(String moveOutReason) {
		this.moveOutReason = moveOutReason;
	}
	

}
