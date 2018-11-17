package com.multibrand.vo.response;

import java.math.BigDecimal;

public class ActBalSummaryS {

	
	private BigDecimal creditAmt;
	private BigDecimal debitAmt;
	private String postDate;
	private BigDecimal ppdBal;	
	private BigDecimal ppdDppBal;
	private String transactionText;

	/*private String actKey;
	private String creditAmtCurr;	
	private String debitAmtCurr;
	private String documentDt;
	private String documentNo;	
	private BigDecimal lidaAmt;
	private String lidaAmtCurr;	
	private String ppdBalCurr;
	private String ppdDppBalCurr;
	private String sortKey;
	*/

	/**
	 * @return the creditAmt
	 */
	public BigDecimal getCreditAmt() {
		return creditAmt;
	}
	/**
	 * @param creditAmt the creditAmt to set
	 */
	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}
	/**
	 * @return the debitAmt
	 */
	public BigDecimal getDebitAmt() {
		return debitAmt;
	}
	/**
	 * @param debitAmt the debitAmt to set
	 */
	public void setDebitAmt(BigDecimal debitAmt) {
		this.debitAmt = debitAmt;
	}
	/**
	 * @return the postDate
	 */
	public String getPostDate() {
		return postDate;
	}
	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(String postDate) {
		if(null != postDate && postDate.length() > 0)
			this.postDate = postDate.replaceAll("-", "");
		else
			this.postDate = postDate;
	}
	/**
	 * @return the ppdBal
	 */
	public BigDecimal getPpdBal() {
		return ppdBal;
	}
	/**
	 * @param ppdBal the ppdBal to set
	 */
	public void setPpdBal(BigDecimal ppdBal) {
		this.ppdBal = ppdBal;
	}
	/**
	 * @return the ppdDppBal
	 */
	public BigDecimal getPpdDppBal() {
		return ppdDppBal;
	}
	/**
	 * @param ppdDppBal the ppdDppBal to set
	 */
	public void setPpdDppBal(BigDecimal ppdDppBal) {
		this.ppdDppBal = ppdDppBal;
	}
	/**
	 * @return the transactionText
	 */
	public String getTransactionText() {
		return transactionText;
	}
	/**
	 * @param transactionText the transactionText to set
	 */
	public void setTransactionText(String transactionText) {
		this.transactionText = transactionText;
	}
	
}
