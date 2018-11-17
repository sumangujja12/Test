package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProjectedBillResponse")
public class ProjectedBillResponse 
{

	
	/** Instance to store esiid  */
	private String esIid ="";
	/** Instance to store contractId  */
	private String contractId ="";
	/** Instance to store contractAcctId  */
	private String contractAcctId ="";
	/** Instance to store busPartner  */
	private String busPartner ="";
	/** Instance to store billPeriodStart  */
	private String billPeriodStart;
	/** Instance to store billPeriodEnd  */
	private String billPeriodEnd;
	/** Instance to store actualDay  */
	private String actualDay;
	/** Instance to store projBillAmt  */
	private String projBillAmt;
	/** Instance to store totProjBilAmt  */
	private String totProjBilAmt;
	/** Instance to store actualBillAmt  */
	private String actualBillAmt;
	/** Instance to store totActualBilAmt  */
	private String totActualBilAmt;
	/** Instance to store actualUsage  */
	private String actualUsage;
	/** Instance to store totActUsg  */
	private String totActUsg;
	/** Instance to store dueDate  */
	private String dueDate;
	/** Instance to store invoiceDt  */
	private String invoiceDt;
	/** Instance to store touGroup  */
	private String touGroup;
	/** Instance to store zoneId  */
	private String zoneId;
	/** Instance to store companyCode  */
	private String companyCode;
	/** Instance to store product  */
	private String product;
	/** Instance to store projBillAmtLow  */
	private String projBillAmtLow;
	/** Instance to store projBillAmtHigh  */
	private String projBillAmtHigh;
	/** Instance to store totProjBilAmtLow  */
	private String totProjBilAmtLow;
	/** Instance to store totProjBilAmtHigh  */
	private String totProjBilAmtHigh;
	/**
	 * @return the esIid
	 */
	public String getEsIid()
	{
		return esIid;
	}
	/**
	 * @param esIid the esIid to set
	 */
	public void setEsIid(String esIid)
	{
		this.esIid = esIid;
	}
	/**
	 * @return the contractId
	 */
	public String getContractId()
	{
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId)
	{
		this.contractId = contractId;
	}
	/**
	 * @return the contractAcctId
	 */
	public String getContractAcctId()
	{
		return contractAcctId;
	}
	/**
	 * @param contractAcctId the contractAcctId to set
	 */
	public void setContractAcctId(String contractAcctId)
	{
		this.contractAcctId = contractAcctId;
	}
	/**
	 * @return the busPartner
	 */
	public String getBusPartner()
	{
		return busPartner;
	}
	/**
	 * @param busPartner the busPartner to set
	 */
	public void setBusPartner(String busPartner)
	{
		this.busPartner = busPartner;
	}
	/**
	 * @return the billPeriodStart
	 */
	public String getBillPeriodStart()
	{
		return billPeriodStart;
	}
	/**
	 * @param billPeriodStart the billPeriodStart to set
	 */
	public void setBillPeriodStart(String billPeriodStart)
	{
		this.billPeriodStart = billPeriodStart;
	}
	/**
	 * @return the billPeriodEnd
	 */
	public String getBillPeriodEnd()
	{
		return billPeriodEnd;
	}
	/**
	 * @param billPeriodEnd the billPeriodEnd to set
	 */
	public void setBillPeriodEnd(String billPeriodEnd)
	{
		this.billPeriodEnd = billPeriodEnd;
	}
	/**
	 * @return the actualDay
	 */
	public String getActualDay()
	{
		return actualDay;
	}
	/**
	 * @param actualDay the actualDay to set
	 */
	public void setActualDay(String actualDay)
	{
		this.actualDay = actualDay;
	}
	/**
	 * @return the projBillAmt
	 */
	public String getProjBillAmt()
	{
		return projBillAmt;
	}
	/**
	 * @param projBillAmt the projBillAmt to set
	 */
	public void setProjBillAmt(String projBillAmt)
	{
		this.projBillAmt = projBillAmt;
	}
	/**
	 * @return the totProjBilAmt
	 */
	public String getTotProjBilAmt()
	{
		return totProjBilAmt;
	}
	/**
	 * @param totProjBilAmt the totProjBilAmt to set
	 */
	public void setTotProjBilAmt(String totProjBilAmt)
	{
		this.totProjBilAmt = totProjBilAmt;
	}
	/**
	 * @return the actualBillAmt
	 */
	public String getActualBillAmt()
	{
		return actualBillAmt;
	}
	/**
	 * @param actualBillAmt the actualBillAmt to set
	 */
	public void setActualBillAmt(String actualBillAmt)
	{
		this.actualBillAmt = actualBillAmt;
	}
	/**
	 * @return the totActualBilAmt
	 */
	public String getTotActualBilAmt()
	{
		return totActualBilAmt;
	}
	/**
	 * @param totActualBilAmt the totActualBilAmt to set
	 */
	public void setTotActualBilAmt(String totActualBilAmt)
	{
		this.totActualBilAmt = totActualBilAmt;
	}
	/**
	 * @return the actualUsage
	 */
	public String getActualUsage()
	{
		return actualUsage;
	}
	/**
	 * @param actualUsage the actualUsage to set
	 */
	public void setActualUsage(String actualUsage)
	{
		this.actualUsage = actualUsage;
	}
	/**
	 * @return the totActUsg
	 */
	public String getTotActUsg()
	{
		return totActUsg;
	}
	/**
	 * @param totActUsg the totActUsg to set
	 */
	public void setTotActUsg(String totActUsg)
	{
		this.totActUsg = totActUsg;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate()
	{
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}
	/**
	 * @return the invoiceDt
	 */
	public String getInvoiceDt()
	{
		return invoiceDt;
	}
	/**
	 * @param invoiceDt the invoiceDt to set
	 */
	public void setInvoiceDt(String invoiceDt)
	{
		this.invoiceDt = invoiceDt;
	}
	/**
	 * @return the touGroup
	 */
	public String getTouGroup()
	{
		return touGroup;
	}
	/**
	 * @param touGroup the touGroup to set
	 */
	public void setTouGroup(String touGroup)
	{
		this.touGroup = touGroup;
	}
	/**
	 * @return the zoneId
	 */
	public String getZoneId()
	{
		return zoneId;
	}
	/**
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(String zoneId)
	{
		this.zoneId = zoneId;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode()
	{
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}
	/**
	 * @return the product
	 */
	public String getProduct()
	{
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(String product)
	{
		this.product = product;
	}
	/**
	 * @return the projBillAmtLow
	 */
	public String getProjBillAmtLow()
	{
		return projBillAmtLow;
	}
	/**
	 * @param projBillAmtLow the projBillAmtLow to set
	 */
	public void setProjBillAmtLow(String projBillAmtLow)
	{
		this.projBillAmtLow = projBillAmtLow;
	}
	/**
	 * @return the projBillAmtHigh
	 */
	public String getProjBillAmtHigh()
	{
		return projBillAmtHigh;
	}
	/**
	 * @param projBillAmtHigh the projBillAmtHigh to set
	 */
	public void setProjBillAmtHigh(String projBillAmtHigh)
	{
		this.projBillAmtHigh = projBillAmtHigh;
	}
	/**
	 * @return the totProjBilAmtLow
	 */
	public String getTotProjBilAmtLow()
	{
		return totProjBilAmtLow;
	}
	/**
	 * @param totProjBilAmtLow the totProjBilAmtLow to set
	 */
	public void setTotProjBilAmtLow(String totProjBilAmtLow)
	{
		this.totProjBilAmtLow = totProjBilAmtLow;
	}
	/**
	 * @return the totProjBilAmtHigh
	 */
	public String getTotProjBilAmtHigh()
	{
		return totProjBilAmtHigh;
	}
	/**
	 * @param totProjBilAmtHigh the totProjBilAmtHigh to set
	 */
	public void setTotProjBilAmtHigh(String totProjBilAmtHigh)
	{
		this.totProjBilAmtHigh = totProjBilAmtHigh;
	}
	

}
