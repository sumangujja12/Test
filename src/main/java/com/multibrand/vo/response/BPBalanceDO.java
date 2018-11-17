package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlElement;


public class BPBalanceDO
{

	private String strCurrentDueDate;
	private String strCurrentARBalance;
	private String strLastPayAmt;
	private String strLastPayDate;	
	private String strPastDueAmt;
	private String strCreditAmt;
	/**
	 * @return the strCurrentDueDate
	 */
	@XmlElement(name="dueDate")
	public String getStrCurrentDueDate()
	{
		return strCurrentDueDate;
	}
	/**
	 * @param strCurrentDueDate the strCurrentDueDate to set
	 */
	public void setStrCurrentDueDate(String strCurrentDueDate)
	{
		this.strCurrentDueDate = strCurrentDueDate;
	}
	/**
	 * @return the strCurrentARBalance
	 */
	@XmlElement(name="currentBalance")
	public String getStrCurrentARBalance()
	{
		return strCurrentARBalance;
	}
	/**
	 * @param strCurrentARBalance the strCurrentARBalance to set
	 */
	public void setStrCurrentARBalance(String strCurrentARBalance)
	{
		this.strCurrentARBalance = strCurrentARBalance;
	}
	/**
	 * @return the strLastPayAmt
	 */
	@XmlElement(name="lastPaymentAmount")
	public String getStrLastPayAmt()
	{
		return strLastPayAmt;
	}
	/**
	 * @param strLastPayAmt the strLastPayAmt to set
	 */
	public void setStrLastPayAmt(String strLastPayAmt)
	{
		this.strLastPayAmt = strLastPayAmt;
	}
	/**
	 * @return the strLastPayDate
	 */
	@XmlElement(name="lastPaymentDate")
	public String getStrLastPayDate()
	{
		return strLastPayDate;
	}
	/**
	 * @param strLastPayDate the strLastPayDate to set
	 */
	public void setStrLastPayDate(String strLastPayDate)
	{
		this.strLastPayDate = strLastPayDate;
	}
	/**
	 * @return the strPastDueAmt
	 */
	@XmlElement(name="pastDueAmount")
	public String getStrPastDueAmt()
	{
		return strPastDueAmt;
	}
	/**
	 * @param strPastDueAmt the strPastDueAmt to set
	 */
	public void setStrPastDueAmt(String strPastDueAmt)
	{
		this.strPastDueAmt = strPastDueAmt;
	}
	/**
	 * @return the strCreditAmt
	 */
	public String getStrCreditAmt()
	{
		return strCreditAmt;
	}
	/**
	 * @param strCreditAmt the strCreditAmt to set
	 */
	public void setStrCreditAmt(String strCreditAmt)
	{
		this.strCreditAmt = strCreditAmt;
	}
	
	
	
	
}
