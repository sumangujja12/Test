package com.multibrand.dto;

import java.io.Serializable;
import java.util.List;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class CreditCheckDTO implements Serializable{
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4481324787169736666L;
	private String creditScoreNum;
	private String creditSourceNum;
	private String creditStatusCd;
	private String creditStatusDate;
	//private String advActionData;
	private String depositAmount;
	private String activationFee;
	private String bondPrice;
	private String accSecStatus;
	private String isPayUpfront;
	private String securityMethod;
	private String activationFeeCd;
	private String creditBucket;
	private String depCode;
	private String depositHold;
	private String creditScoreMax;
	private String creditScoreMin;
	
    private boolean reassesDeposit=false;  //aakash
    
    private String creditType;              //aakash

    private String reasonSecurityDeposit;   //aakash
    private String depositDueDate;          //aakash
 
    
    private String depositPMScore;          //aakash
    private String creditScoreDate;         //aakash
    private String defaultDepositAmount;    //aakash
    private String payCode;                 //aakash
	private List<String> factorsKey;
	
	
	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
    
    public String getDepositDueDate() {
		return depositDueDate;
	}

	public void setDepositDueDate(String depositDueDate) {
		this.depositDueDate = depositDueDate;
	}

	public String getDepositPMScore() {
		return depositPMScore;
	}

	public void setDepositPMScore(String depositPMScore) {
		this.depositPMScore = depositPMScore;
	}

	public String getCreditScoreDate() {
		return creditScoreDate;
	}

	public void setCreditScoreDate(String creditScoreDate) {
		this.creditScoreDate = creditScoreDate;
	}

	public String getDefaultDepositAmount() {
		return defaultDepositAmount;
	}

	public void setDefaultDepositAmount(String defaultDepositAmount) {
		this.defaultDepositAmount = defaultDepositAmount;
	}

	
	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	
	
	public String getDepositHold() {
		return depositHold;
	}

	public void setDepositHold(String depositHold) {
		this.depositHold = depositHold;
	}
	

	public String getCreditScoreMax() {
		return creditScoreMax;
	}

	public void setCreditScoreMax(String creditScoreMax) {
		this.creditScoreMax = creditScoreMax;
	}
	
	public String getCreditBucket() {
		return creditBucket;
	}

	public void setCreditBucket(String creditBucket) {
		this.creditBucket = creditBucket;
	}

	public String getCreditScoreNum() {
		return creditScoreNum;
	}

	public void setCreditScoreNum(String creditScoreNum) {
		this.creditScoreNum = creditScoreNum;
	}

	public String getCreditSourceNum() {
		return creditSourceNum;
	}

	public void setCreditSourceNum(String creditSourceNum) {
		this.creditSourceNum = creditSourceNum;
	}

	public String getCreditStatusCd() {
		return creditStatusCd;
	}

	public void setCreditStatusCd(String creditStatusCd) {
		this.creditStatusCd = creditStatusCd;
	}

	public String getCreditStatusDate() {
		return creditStatusDate;
	}

	public void setCreditStatusDate(String creditStatusDate) {
		this.creditStatusDate = creditStatusDate;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getActivationFee() {
		return activationFee;
	}

	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}

	public String getBondPrice() {
		return bondPrice;
	}

	public void setBondPrice(String bondPrice) {
		this.bondPrice = bondPrice;
	}

	public String getAccSecStatus() {
		return accSecStatus;
	}

	public void setAccSecStatus(String accSecStatus) {
		this.accSecStatus = accSecStatus;
	}

	public String getIsPayUpfront() {
		return isPayUpfront;
	}

	public void setIsPayUpfront(String isPayUpfront) {
		this.isPayUpfront = isPayUpfront;
	}

	public String getSecurityMethod() {
		return securityMethod;
	}

	public void setSecurityMethod(String securityMethod) {
		this.securityMethod = securityMethod;
	}

	public String getActivationFeeCd() {
		return activationFeeCd;
	}

	public void setActivationFeeCd(String activationFeeCd) {
		this.activationFeeCd = activationFeeCd;
	}

	public void setFactorsKey(List<String> factorsKey) {
		this.factorsKey = factorsKey;
	}

	public List<String> getFactorsKey() {
		return factorsKey;
	}

	public void setReasonSecurityDeposit(String reasonSecurityDeposit) {
		this.reasonSecurityDeposit = reasonSecurityDeposit;
	}

	public String getReasonSecurityDeposit() {
		return reasonSecurityDeposit;
	}

	public void setCreditScoreMin(String creditScoreMin) {
		this.creditScoreMin = creditScoreMin;
	}

	public String getCreditScoreMin() {
		return creditScoreMin;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setReassesDeposit(boolean reassesDeposit) {
		this.reassesDeposit = reassesDeposit;
	}

	public boolean isReassesDeposit() {
		return reassesDeposit;
	}

	
	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}	
}
