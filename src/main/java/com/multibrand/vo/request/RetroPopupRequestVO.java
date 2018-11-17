package com.multibrand.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

public class RetroPopupRequestVO implements FormEntityRequest, Constants,Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2740134933418582095L;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String contractAccountNumber;
	
	
	private String contractId;
	private String popupCloseFlag;
	private String currentBillCycleDate;
	private String lastUpdateUser;
	private String cpdbCreatedBy;
	
	private String invoiceNo;
	private String currentARAmount;
	private String companyCode;

	
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getPopupCloseFlag() {
		return popupCloseFlag;
	}
	public void setPopupCloseFlag(String popupCloseFlag) {
		this.popupCloseFlag = popupCloseFlag;
	}
	public String getCurrentBillCycleDate() {
		return currentBillCycleDate;
	}
	public void setCurrentBillCycleDate(String currentBillCycleDate) {
		this.currentBillCycleDate = currentBillCycleDate;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public String getCpdbCreatedBy() {
		return cpdbCreatedBy;
	}
	public void setCpdbCreatedBy(String cpdbCreatedBy) {
		this.cpdbCreatedBy = cpdbCreatedBy;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCurrentARAmount() {
		return currentARAmount;
	}
	public void setCurrentARAmount(String currentARAmount) {
		this.currentARAmount = currentARAmount;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
