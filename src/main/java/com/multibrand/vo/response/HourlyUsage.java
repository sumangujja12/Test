package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hourlyUsage")
public class HourlyUsage implements UsageInterface
{

	private String esiId;
	private String contractId;
	private String contractAcctId;
	private String busPartner;
	/*private String zoneId;
	private String curdtInd;
	private String curDayInd;
	private String dyHrInd;*/
	private String actualDay;
	private String usageHr01;
	private String usageHr02;
	private String usageHr03;
	private String usageHr04;
	private String usageHr05;
	private String usageHr06;
	private String usageHr07;
	private String usageHr08;
	private String usageHr09;
	private String usageHr10;
	private String usageHr11;
	private String usageHr12;
	private String usageHr13;
	private String usageHr14;
	private String usageHr15;
	private String usageHr16;
	private String usageHr17;
	private String usageHr18;
	private String usageHr19;
	private String usageHr20;
	private String usageHr21;
	private String usageHr22;
	private String usageHr23;
	private String usageHr24;
	private String costHr01;
	private String costHr02;
	private String costHr03;
	private String costHr04;
	private String costHr05;
	private String costHr06;
	private String costHr07;
	private String costHr08;
	private String costHr09;
	private String costHr10;
	private String costHr11;
	private String costHr12;
	private String costHr13;
	private String costHr14;
	private String costHr15;
	private String costHr16;
	private String costHr17;
	private String costHr18;
	private String costHr19;
	private String costHr20;
	private String costHr21;
	private String costHr22;
	private String costHr23;
	private String costHr24;
	private String dayUsg;
	private String dayCst;
	private String dayTempHigh;
	private String dayTempLow;
	/**
	 * @return the esiId
	 */
	public String getEsiId()
	{
		return esiId;
	}
	/**
	 * @param esiId the esiId to set
	 */
	public void setEsiId(String esiId)
	{
		this.esiId = esiId;
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
	 * @return the actualday
	 */
	public String getActualDay()
	{
		return actualDay;
	}
	/**
	 * @param actualday the actualday to set
	 */
	public void setActualDay(String actualDay)
	{
		this.actualDay = actualDay;
	}
	/**
	 * @return the usageHr01
	 */
	public String getUsageHr01()
	{
		return usageHr01;
	}
	/**
	 * @param usageHr01 the usageHr01 to set
	 */
	public void setUsageHr01(String usageHr01)
	{
		this.usageHr01 = usageHr01;
	}
	/**
	 * @return the usageHr02
	 */
	public String getUsageHr02()
	{
		return usageHr02;
	}
	/**
	 * @param usageHr02 the usageHr02 to set
	 */
	public void setUsageHr02(String usageHr02)
	{
		this.usageHr02 = usageHr02;
	}
	/**
	 * @return the usageHr03
	 */
	public String getUsageHr03()
	{
		return usageHr03;
	}
	/**
	 * @param usageHr03 the usageHr03 to set
	 */
	public void setUsageHr03(String usageHr03)
	{
		this.usageHr03 = usageHr03;
	}
	/**
	 * @return the usageHr04
	 */
	public String getUsageHr04()
	{
		return usageHr04;
	}
	/**
	 * @param usageHr04 the usageHr04 to set
	 */
	public void setUsageHr04(String usageHr04)
	{
		this.usageHr04 = usageHr04;
	}
	/**
	 * @return the usageHr05
	 */
	public String getUsageHr05()
	{
		return usageHr05;
	}
	/**
	 * @param usageHr05 the usageHr05 to set
	 */
	public void setUsageHr05(String usageHr05)
	{
		this.usageHr05 = usageHr05;
	}
	/**
	 * @return the usageHr06
	 */
	public String getUsageHr06()
	{
		return usageHr06;
	}
	/**
	 * @param usageHr06 the usageHr06 to set
	 */
	public void setUsageHr06(String usageHr06)
	{
		this.usageHr06 = usageHr06;
	}
	/**
	 * @return the usageHr07
	 */
	public String getUsageHr07()
	{
		return usageHr07;
	}
	/**
	 * @param usageHr07 the usageHr07 to set
	 */
	public void setUsageHr07(String usageHr07)
	{
		this.usageHr07 = usageHr07;
	}
	/**
	 * @return the usageHr08
	 */
	public String getUsageHr08()
	{
		return usageHr08;
	}
	/**
	 * @param usageHr08 the usageHr08 to set
	 */
	public void setUsageHr08(String usageHr08)
	{
		this.usageHr08 = usageHr08;
	}
	/**
	 * @return the usageHr09
	 */
	public String getUsageHr09()
	{
		return usageHr09;
	}
	/**
	 * @param usageHr09 the usageHr09 to set
	 */
	public void setUsageHr09(String usageHr09)
	{
		this.usageHr09 = usageHr09;
	}
	/**
	 * @return the usageHr10
	 */
	public String getUsageHr10()
	{
		return usageHr10;
	}
	/**
	 * @param usageHr10 the usageHr10 to set
	 */
	public void setUsageHr10(String usageHr10)
	{
		this.usageHr10 = usageHr10;
	}
	/**
	 * @return the usageHr11
	 */
	public String getUsageHr11()
	{
		return usageHr11;
	}
	/**
	 * @param usageHr11 the usageHr11 to set
	 */
	public void setUsageHr11(String usageHr11)
	{
		this.usageHr11 = usageHr11;
	}
	/**
	 * @return the usageHr12
	 */
	public String getUsageHr12()
	{
		return usageHr12;
	}
	/**
	 * @param usageHr12 the usageHr12 to set
	 */
	public void setUsageHr12(String usageHr12)
	{
		this.usageHr12 = usageHr12;
	}
	/**
	 * @return the usageHr13
	 */
	public String getUsageHr13()
	{
		return usageHr13;
	}
	/**
	 * @param usageHr13 the usageHr13 to set
	 */
	public void setUsageHr13(String usageHr13)
	{
		this.usageHr13 = usageHr13;
	}
	/**
	 * @return the usageHr14
	 */
	public String getUsageHr14()
	{
		return usageHr14;
	}
	/**
	 * @param usageHr14 the usageHr14 to set
	 */
	public void setUsageHr14(String usageHr14)
	{
		this.usageHr14 = usageHr14;
	}
	/**
	 * @return the usageHr15
	 */
	public String getUsageHr15()
	{
		return usageHr15;
	}
	/**
	 * @param usageHr15 the usageHr15 to set
	 */
	public void setUsageHr15(String usageHr15)
	{
		this.usageHr15 = usageHr15;
	}
	/**
	 * @return the usageHr16
	 */
	public String getUsageHr16()
	{
		return usageHr16;
	}
	/**
	 * @param usageHr16 the usageHr16 to set
	 */
	public void setUsageHr16(String usageHr16)
	{
		this.usageHr16 = usageHr16;
	}
	/**
	 * @return the usageHr17
	 */
	public String getUsageHr17()
	{
		return usageHr17;
	}
	/**
	 * @param usageHr17 the usageHr17 to set
	 */
	public void setUsageHr17(String usageHr17)
	{
		this.usageHr17 = usageHr17;
	}
	/**
	 * @return the usageHr18
	 */
	public String getUsageHr18()
	{
		return usageHr18;
	}
	/**
	 * @param usageHr18 the usageHr18 to set
	 */
	public void setUsageHr18(String usageHr18)
	{
		this.usageHr18 = usageHr18;
	}
	/**
	 * @return the usageHr19
	 */
	public String getUsageHr19()
	{
		return usageHr19;
	}
	/**
	 * @param usageHr19 the usageHr19 to set
	 */
	public void setUsageHr19(String usageHr19)
	{
		this.usageHr19 = usageHr19;
	}
	/**
	 * @return the usageHr20
	 */
	public String getUsageHr20()
	{
		return usageHr20;
	}
	/**
	 * @param usageHr20 the usageHr20 to set
	 */
	public void setUsageHr20(String usageHr20)
	{
		this.usageHr20 = usageHr20;
	}
	/**
	 * @return the usageHr21
	 */
	public String getUsageHr21()
	{
		return usageHr21;
	}
	/**
	 * @param usageHr21 the usageHr21 to set
	 */
	public void setUsageHr21(String usageHr21)
	{
		this.usageHr21 = usageHr21;
	}
	/**
	 * @return the usageHr22
	 */
	public String getUsageHr22()
	{
		return usageHr22;
	}
	/**
	 * @param usageHr22 the usageHr22 to set
	 */
	public void setUsageHr22(String usageHr22)
	{
		this.usageHr22 = usageHr22;
	}
	/**
	 * @return the usageHr23
	 */
	public String getUsageHr23()
	{
		return usageHr23;
	}
	/**
	 * @param usageHr23 the usageHr23 to set
	 */
	public void setUsageHr23(String usageHr23)
	{
		this.usageHr23 = usageHr23;
	}
	/**
	 * @return the usageHr24
	 */
	public String getUsageHr24()
	{
		return usageHr24;
	}
	/**
	 * @param usageHr24 the usageHr24 to set
	 */
	public void setUsageHr24(String usageHr24)
	{
		this.usageHr24 = usageHr24;
	}
	/**
	 * @return the costHr01
	 */
	public String getCostHr01()
	{
		return costHr01;
	}
	/**
	 * @param costHr01 the costHr01 to set
	 */
	public void setCostHr01(String costHr01)
	{
		this.costHr01 = costHr01;
	}
	/**
	 * @return the costHr02
	 */
	public String getCostHr02()
	{
		return costHr02;
	}
	/**
	 * @param costHr02 the costHr02 to set
	 */
	public void setCostHr02(String costHr02)
	{
		this.costHr02 = costHr02;
	}
	/**
	 * @return the costHr03
	 */
	public String getCostHr03()
	{
		return costHr03;
	}
	/**
	 * @param costHr03 the costHr03 to set
	 */
	public void setCostHr03(String costHr03)
	{
		this.costHr03 = costHr03;
	}
	/**
	 * @return the costHr04
	 */
	public String getCostHr04()
	{
		return costHr04;
	}
	/**
	 * @param costHr04 the costHr04 to set
	 */
	public void setCostHr04(String costHr04)
	{
		this.costHr04 = costHr04;
	}
	/**
	 * @return the costHr05
	 */
	public String getCostHr05()
	{
		return costHr05;
	}
	/**
	 * @param costHr05 the costHr05 to set
	 */
	public void setCostHr05(String costHr05)
	{
		this.costHr05 = costHr05;
	}
	/**
	 * @return the costHr06
	 */
	public String getCostHr06()
	{
		return costHr06;
	}
	/**
	 * @param costHr06 the costHr06 to set
	 */
	public void setCostHr06(String costHr06)
	{
		this.costHr06 = costHr06;
	}
	/**
	 * @return the costHr07
	 */
	public String getCostHr07()
	{
		return costHr07;
	}
	/**
	 * @param costHr07 the costHr07 to set
	 */
	public void setCostHr07(String costHr07)
	{
		this.costHr07 = costHr07;
	}
	/**
	 * @return the costHr08
	 */
	public String getCostHr08()
	{
		return costHr08;
	}
	/**
	 * @param costHr08 the costHr08 to set
	 */
	public void setCostHr08(String costHr08)
	{
		this.costHr08 = costHr08;
	}
	/**
	 * @return the costHr09
	 */
	public String getCostHr09()
	{
		return costHr09;
	}
	/**
	 * @param costHr09 the costHr09 to set
	 */
	public void setCostHr09(String costHr09)
	{
		this.costHr09 = costHr09;
	}
	/**
	 * @return the costHr10
	 */
	public String getCostHr10()
	{
		return costHr10;
	}
	/**
	 * @param costHr10 the costHr10 to set
	 */
	public void setCostHr10(String costHr10)
	{
		this.costHr10 = costHr10;
	}
	/**
	 * @return the costHr11
	 */
	public String getCostHr11()
	{
		return costHr11;
	}
	/**
	 * @param costHr11 the costHr11 to set
	 */
	public void setCostHr11(String costHr11)
	{
		this.costHr11 = costHr11;
	}
	/**
	 * @return the costHr12
	 */
	public String getCostHr12()
	{
		return costHr12;
	}
	/**
	 * @param costHr12 the costHr12 to set
	 */
	public void setCostHr12(String costHr12)
	{
		this.costHr12 = costHr12;
	}
	/**
	 * @return the costHr13
	 */
	public String getCostHr13()
	{
		return costHr13;
	}
	/**
	 * @param costHr13 the costHr13 to set
	 */
	public void setCostHr13(String costHr13)
	{
		this.costHr13 = costHr13;
	}
	/**
	 * @return the costHr14
	 */
	public String getCostHr14()
	{
		return costHr14;
	}
	/**
	 * @param costHr14 the costHr14 to set
	 */
	public void setCostHr14(String costHr14)
	{
		this.costHr14 = costHr14;
	}
	/**
	 * @return the costHr15
	 */
	public String getCostHr15()
	{
		return costHr15;
	}
	/**
	 * @param costHr15 the costHr15 to set
	 */
	public void setCostHr15(String costHr15)
	{
		this.costHr15 = costHr15;
	}
	/**
	 * @return the costHr16
	 */
	public String getCostHr16()
	{
		return costHr16;
	}
	/**
	 * @param costHr16 the costHr16 to set
	 */
	public void setCostHr16(String costHr16)
	{
		this.costHr16 = costHr16;
	}
	/**
	 * @return the costHr17
	 */
	public String getCostHr17()
	{
		return costHr17;
	}
	/**
	 * @param costHr17 the costHr17 to set
	 */
	public void setCostHr17(String costHr17)
	{
		this.costHr17 = costHr17;
	}
	/**
	 * @return the costHr18
	 */
	public String getCostHr18()
	{
		return costHr18;
	}
	/**
	 * @param costHr18 the costHr18 to set
	 */
	public void setCostHr18(String costHr18)
	{
		this.costHr18 = costHr18;
	}
	/**
	 * @return the costHr19
	 */
	public String getCostHr19()
	{
		return costHr19;
	}
	/**
	 * @param costHr19 the costHr19 to set
	 */
	public void setCostHr19(String costHr19)
	{
		this.costHr19 = costHr19;
	}
	/**
	 * @return the costHr20
	 */
	public String getCostHr20()
	{
		return costHr20;
	}
	/**
	 * @param costHr20 the costHr20 to set
	 */
	public void setCostHr20(String costHr20)
	{
		this.costHr20 = costHr20;
	}
	/**
	 * @return the costHr21
	 */
	public String getCostHr21()
	{
		return costHr21;
	}
	/**
	 * @param costHr21 the costHr21 to set
	 */
	public void setCostHr21(String costHr21)
	{
		this.costHr21 = costHr21;
	}
	/**
	 * @return the costHr22
	 */
	public String getCostHr22()
	{
		return costHr22;
	}
	/**
	 * @param costHr22 the costHr22 to set
	 */
	public void setCostHr22(String costHr22)
	{
		this.costHr22 = costHr22;
	}
	/**
	 * @return the costHr23
	 */
	public String getCostHr23()
	{
		return costHr23;
	}
	/**
	 * @param costHr23 the costHr23 to set
	 */
	public void setCostHr23(String costHr23)
	{
		this.costHr23 = costHr23;
	}
	/**
	 * @return the costHr24
	 */
	public String getCostHr24()
	{
		return costHr24;
	}
	/**
	 * @param costHr24 the costHr24 to set
	 */
	public void setCostHr24(String costHr24)
	{
		this.costHr24 = costHr24;
	}
	/**
	 * @return the dayUsg
	 */
	public String getDayUsg()
	{
		return dayUsg;
	}
	/**
	 * @param dayUsg the dayUsg to set
	 */
	public void setDayUsg(String dayUsg)
	{
		this.dayUsg = dayUsg;
	}
	/**
	 * @return the dayCst
	 */
	public String getDayCst()
	{
		return dayCst;
	}
	/**
	 * @param dayCst the dayCst to set
	 */
	public void setDayCst(String dayCst)
	{
		this.dayCst = dayCst;
	}
	/**
	 * @return the dayTempHigH
	 */
	public String getDayTempHigh()
	{
		return dayTempHigh;
	}
	/**
	 * @param dayTempHigH the dayTempHigH to set
	 */
	public void setDayTempHigh(String dayTempHigh)
	{
		this.dayTempHigh = dayTempHigh;
	}
	/**
	 * @return the dayTempLow
	 */
	public String getDayTempLow()
	{
		return dayTempLow;
	}
	/**
	 * @param dayTempLow the dayTempLow to set
	 */
	public void setDayTempLow(String dayTempLow)
	{
		this.dayTempLow = dayTempLow;
	}
	
	
	

}
