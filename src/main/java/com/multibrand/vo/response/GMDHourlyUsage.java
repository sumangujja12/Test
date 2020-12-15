package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gmdHourlyUsage")
public class GMDHourlyUsage implements UsageInterface
{

	private String esiId;
	private String contractId;
	private String contractAcctId;
	private String busPartner;
	private String actualDay;	
			
	private String usageHr0015;
	private String usageHr0030;
	private String usageHr0045;

	private String usageHr0100;
	private String usageHr0115;
	private String usageHr0130;
	private String usageHr0145;
	
	private String usageHr0200;
	private String usageHr0215;
	private String usageHr0230;
	private String usageHr0245;
	
	private String usageHr0300;
	private String usageHr0315;
	private String usageHr0330;
	private String usageHr0345;
	
	private String usageHr0400;
	private String usageHr0415;
	private String usageHr0430;
	private String usageHr0445;
	
	private String usageHr0500;
	private String usageHr0515;
	private String usageHr0530;
	private String usageHr0545;
	
	private String usageHr0600;
	private String usageHr0615;
	private String usageHr0630;
	private String usageHr0645;
	
	private String usageHr0700;
	private String usageHr0715;
	private String usageHr0730;
	private String usageHr0745;
	
	private String usageHr0800;
	private String usageHr0815;
	private String usageHr0830;
	private String usageHr0845;
	
	private String usageHr0900;
	private String usageHr0915;
	private String usageHr0930;
	private String usageHr0945;
	
	private String usageHr1000;
	private String usageHr1015;
	private String usageHr1030;
	private String usageHr1045;
	
	private String usageHr1100;
	private String usageHr1115;
	private String usageHr1130;
	private String usageHr1145;
	
	private String usageHr1200;
	private String usageHr1215;
	private String usageHr1230;
	private String usageHr1245;
	
	private String usageHr1300;
	private String usageHr1315;
	private String usageHr1330;
	private String usageHr1345;
	
	
	private String usageHr1400;
	private String usageHr1415;
	private String usageHr1430;
	private String usageHr1445;
	
	private String usageHr1500;
	private String usageHr1515;
	private String usageHr1530;
	private String usageHr1545;
	
	
	private String usageHr1600;
	private String usageHr1615;
	private String usageHr1630;
	private String usageHr1645;
	
	private String usageHr1700;
	private String usageHr1715;
	private String usageHr1730;
	private String usageHr1745;
	
	private String usageHr1800;
	private String usageHr1815;
	private String usageHr1830;
	private String usageHr1845;
	
	private String usageHr1900;
	private String usageHr1915;
	private String usageHr1930;
	private String usageHr1945;
	
	private String usageHr2000;
	private String usageHr2015;
	private String usageHr2030;
	private String usageHr2045;
	
	private String usageHr2100;
	private String usageHr2115;
	private String usageHr2130;
	private String usageHr2145;
	
	private String usageHr2200;
	private String usageHr2215;
	private String usageHr2230;
	private String usageHr2245;
	
	private String usageHr2300;
	private String usageHr2315;
	private String usageHr2330;
	private String usageHr2345;
	
	private String usageHr2400;

	
	
	private String costHr0015;
	private String costHr0030;
	private String costHr0045;

	private String costHr0100;
	private String costHr0115;
	private String costHr0130;
	private String costHr0145;
	
	private String costHr0200;
	private String costHr0215;
	private String costHr0230;
	private String costHr0245;
	
	private String costHr0300;
	private String costHr0315;
	private String costHr0330;
	private String costHr0345;
	
	private String costHr0400;
	private String costHr0415;
	private String costHr0430;
	private String costHr0445;
	
	private String costHr0500;
	private String costHr0515;
	private String costHr0530;
	private String costHr0545;
	
	private String costHr0600;
	private String costHr0615;
	private String costHr0630;
	private String costHr0645;
	
	private String costHr0700;
	private String costHr0715;
	private String costHr0730;
	private String costHr0745;
	
	private String costHr0800;
	private String costHr0815;
	private String costHr0830;
	private String costHr0845;
	
	private String costHr0900;
	private String costHr0915;
	private String costHr0930;
	private String costHr0945;
	
	private String costHr1000;
	private String costHr1015;
	private String costHr1030;
	private String costHr1045;
	
	private String costHr1100;
	private String costHr1115;
	private String costHr1130;
	private String costHr1145;
	
	private String costHr1200;
	private String costHr1215;
	private String costHr1230;
	private String costHr1245;
	
	private String costHr1300;
	private String costHr1315;
	private String costHr1330;
	private String costHr1345;
	
	
	private String costHr1400;
	private String costHr1415;
	private String costHr1430;
	private String costHr1445;
	
	private String costHr1500;
	private String costHr1515;
	private String costHr1530;
	private String costHr1545;
	
	
	private String costHr1600;
	private String costHr1615;
	private String costHr1630;
	private String costHr1645;
	
	private String costHr1700;
	private String costHr1715;
	private String costHr1730;
	private String costHr1745;
	
	private String costHr1800;
	private String costHr1815;
	private String costHr1830;
	private String costHr1845;
	
	private String costHr1900;
	private String costHr1915;
	private String costHr1930;
	private String costHr1945;
	
	private String costHr2000;
	private String costHr2015;
	private String costHr2030;
	private String costHr2045;
	
	private String costHr2100;
	private String costHr2115;
	private String costHr2130;
	private String costHr2145;
	
	private String costHr2200;
	private String costHr2215;
	private String costHr2230;
	private String costHr2245;
	
	private String costHr2300;
	private String costHr2315;
	private String costHr2330;
	private String costHr2345;
	
	private String costHr2400;
	
	private String dayUsg;
	private String dayCst;
	private String dayTempHigh;
	private String dayTempLow;
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
	 * @return the contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the contractAcctId
	 */
	public String getContractAcctId() {
		return contractAcctId;
	}
	/**
	 * @param contractAcctId the contractAcctId to set
	 */
	public void setContractAcctId(String contractAcctId) {
		this.contractAcctId = contractAcctId;
	}
	/**
	 * @return the busPartner
	 */
	public String getBusPartner() {
		return busPartner;
	}
	/**
	 * @param busPartner the busPartner to set
	 */
	public void setBusPartner(String busPartner) {
		this.busPartner = busPartner;
	}
	/**
	 * @return the actualDay
	 */
	public String getActualDay() {
		return actualDay;
	}
	/**
	 * @param actualDay the actualDay to set
	 */
	public void setActualDay(String actualDay) {
		this.actualDay = actualDay;
	}
	/**
	 * @return the usageHr0015
	 */
	public String getUsageHr0015() {
		return usageHr0015;
	}
	/**
	 * @param usageHr0015 the usageHr0015 to set
	 */
	public void setUsageHr0015(String usageHr0015) {
		this.usageHr0015 = usageHr0015;
	}
	/**
	 * @return the usageHr0030
	 */
	public String getUsageHr0030() {
		return usageHr0030;
	}
	/**
	 * @param usageHr0030 the usageHr0030 to set
	 */
	public void setUsageHr0030(String usageHr0030) {
		this.usageHr0030 = usageHr0030;
	}
	/**
	 * @return the usageHr0045
	 */
	public String getUsageHr0045() {
		return usageHr0045;
	}
	/**
	 * @param usageHr0045 the usageHr0045 to set
	 */
	public void setUsageHr0045(String usageHr0045) {
		this.usageHr0045 = usageHr0045;
	}
	/**
	 * @return the usageHr0100
	 */
	public String getUsageHr0100() {
		return usageHr0100;
	}
	/**
	 * @param usageHr0100 the usageHr0100 to set
	 */
	public void setUsageHr0100(String usageHr0100) {
		this.usageHr0100 = usageHr0100;
	}
	/**
	 * @return the usageHr0115
	 */
	public String getUsageHr0115() {
		return usageHr0115;
	}
	/**
	 * @param usageHr0115 the usageHr0115 to set
	 */
	public void setUsageHr0115(String usageHr0115) {
		this.usageHr0115 = usageHr0115;
	}
	/**
	 * @return the usageHr0130
	 */
	public String getUsageHr0130() {
		return usageHr0130;
	}
	/**
	 * @param usageHr0130 the usageHr0130 to set
	 */
	public void setUsageHr0130(String usageHr0130) {
		this.usageHr0130 = usageHr0130;
	}
	/**
	 * @return the usageHr0145
	 */
	public String getUsageHr0145() {
		return usageHr0145;
	}
	/**
	 * @param usageHr0145 the usageHr0145 to set
	 */
	public void setUsageHr0145(String usageHr0145) {
		this.usageHr0145 = usageHr0145;
	}
	/**
	 * @return the usageHr0200
	 */
	public String getUsageHr0200() {
		return usageHr0200;
	}
	/**
	 * @param usageHr0200 the usageHr0200 to set
	 */
	public void setUsageHr0200(String usageHr0200) {
		this.usageHr0200 = usageHr0200;
	}
	/**
	 * @return the usageHr0215
	 */
	public String getUsageHr0215() {
		return usageHr0215;
	}
	/**
	 * @param usageHr0215 the usageHr0215 to set
	 */
	public void setUsageHr0215(String usageHr0215) {
		this.usageHr0215 = usageHr0215;
	}
	/**
	 * @return the usageHr0230
	 */
	public String getUsageHr0230() {
		return usageHr0230;
	}
	/**
	 * @param usageHr0230 the usageHr0230 to set
	 */
	public void setUsageHr0230(String usageHr0230) {
		this.usageHr0230 = usageHr0230;
	}
	/**
	 * @return the usageHr0245
	 */
	public String getUsageHr0245() {
		return usageHr0245;
	}
	/**
	 * @param usageHr0245 the usageHr0245 to set
	 */
	public void setUsageHr0245(String usageHr0245) {
		this.usageHr0245 = usageHr0245;
	}
	/**
	 * @return the usageHr0300
	 */
	public String getUsageHr0300() {
		return usageHr0300;
	}
	/**
	 * @param usageHr0300 the usageHr0300 to set
	 */
	public void setUsageHr0300(String usageHr0300) {
		this.usageHr0300 = usageHr0300;
	}
	/**
	 * @return the usageHr0315
	 */
	public String getUsageHr0315() {
		return usageHr0315;
	}
	/**
	 * @param usageHr0315 the usageHr0315 to set
	 */
	public void setUsageHr0315(String usageHr0315) {
		this.usageHr0315 = usageHr0315;
	}
	/**
	 * @return the usageHr0330
	 */
	public String getUsageHr0330() {
		return usageHr0330;
	}
	/**
	 * @param usageHr0330 the usageHr0330 to set
	 */
	public void setUsageHr0330(String usageHr0330) {
		this.usageHr0330 = usageHr0330;
	}
	/**
	 * @return the usageHr0345
	 */
	public String getUsageHr0345() {
		return usageHr0345;
	}
	/**
	 * @param usageHr0345 the usageHr0345 to set
	 */
	public void setUsageHr0345(String usageHr0345) {
		this.usageHr0345 = usageHr0345;
	}
	/**
	 * @return the usageHr0400
	 */
	public String getUsageHr0400() {
		return usageHr0400;
	}
	/**
	 * @param usageHr0400 the usageHr0400 to set
	 */
	public void setUsageHr0400(String usageHr0400) {
		this.usageHr0400 = usageHr0400;
	}
	/**
	 * @return the usageHr0415
	 */
	public String getUsageHr0415() {
		return usageHr0415;
	}
	/**
	 * @param usageHr0415 the usageHr0415 to set
	 */
	public void setUsageHr0415(String usageHr0415) {
		this.usageHr0415 = usageHr0415;
	}
	/**
	 * @return the usageHr0430
	 */
	public String getUsageHr0430() {
		return usageHr0430;
	}
	/**
	 * @param usageHr0430 the usageHr0430 to set
	 */
	public void setUsageHr0430(String usageHr0430) {
		this.usageHr0430 = usageHr0430;
	}
	/**
	 * @return the usageHr0445
	 */
	public String getUsageHr0445() {
		return usageHr0445;
	}
	/**
	 * @param usageHr0445 the usageHr0445 to set
	 */
	public void setUsageHr0445(String usageHr0445) {
		this.usageHr0445 = usageHr0445;
	}
	/**
	 * @return the usageHr0500
	 */
	public String getUsageHr0500() {
		return usageHr0500;
	}
	/**
	 * @param usageHr0500 the usageHr0500 to set
	 */
	public void setUsageHr0500(String usageHr0500) {
		this.usageHr0500 = usageHr0500;
	}
	/**
	 * @return the usageHr0515
	 */
	public String getUsageHr0515() {
		return usageHr0515;
	}
	/**
	 * @param usageHr0515 the usageHr0515 to set
	 */
	public void setUsageHr0515(String usageHr0515) {
		this.usageHr0515 = usageHr0515;
	}
	/**
	 * @return the usageHr0530
	 */
	public String getUsageHr0530() {
		return usageHr0530;
	}
	/**
	 * @param usageHr0530 the usageHr0530 to set
	 */
	public void setUsageHr0530(String usageHr0530) {
		this.usageHr0530 = usageHr0530;
	}
	/**
	 * @return the usageHr0545
	 */
	public String getUsageHr0545() {
		return usageHr0545;
	}
	/**
	 * @param usageHr0545 the usageHr0545 to set
	 */
	public void setUsageHr0545(String usageHr0545) {
		this.usageHr0545 = usageHr0545;
	}
	/**
	 * @return the usageHr0600
	 */
	public String getUsageHr0600() {
		return usageHr0600;
	}
	/**
	 * @param usageHr0600 the usageHr0600 to set
	 */
	public void setUsageHr0600(String usageHr0600) {
		this.usageHr0600 = usageHr0600;
	}
	/**
	 * @return the usageHr0615
	 */
	public String getUsageHr0615() {
		return usageHr0615;
	}
	/**
	 * @param usageHr0615 the usageHr0615 to set
	 */
	public void setUsageHr0615(String usageHr0615) {
		this.usageHr0615 = usageHr0615;
	}
	/**
	 * @return the usageHr0630
	 */
	public String getUsageHr0630() {
		return usageHr0630;
	}
	/**
	 * @param usageHr0630 the usageHr0630 to set
	 */
	public void setUsageHr0630(String usageHr0630) {
		this.usageHr0630 = usageHr0630;
	}
	/**
	 * @return the usageHr0645
	 */
	public String getUsageHr0645() {
		return usageHr0645;
	}
	/**
	 * @param usageHr0645 the usageHr0645 to set
	 */
	public void setUsageHr0645(String usageHr0645) {
		this.usageHr0645 = usageHr0645;
	}
	/**
	 * @return the usageHr0700
	 */
	public String getUsageHr0700() {
		return usageHr0700;
	}
	/**
	 * @param usageHr0700 the usageHr0700 to set
	 */
	public void setUsageHr0700(String usageHr0700) {
		this.usageHr0700 = usageHr0700;
	}
	/**
	 * @return the usageHr0715
	 */
	public String getUsageHr0715() {
		return usageHr0715;
	}
	/**
	 * @param usageHr0715 the usageHr0715 to set
	 */
	public void setUsageHr0715(String usageHr0715) {
		this.usageHr0715 = usageHr0715;
	}
	/**
	 * @return the usageHr0730
	 */
	public String getUsageHr0730() {
		return usageHr0730;
	}
	/**
	 * @param usageHr0730 the usageHr0730 to set
	 */
	public void setUsageHr0730(String usageHr0730) {
		this.usageHr0730 = usageHr0730;
	}
	/**
	 * @return the usageHr0745
	 */
	public String getUsageHr0745() {
		return usageHr0745;
	}
	/**
	 * @param usageHr0745 the usageHr0745 to set
	 */
	public void setUsageHr0745(String usageHr0745) {
		this.usageHr0745 = usageHr0745;
	}
	/**
	 * @return the usageHr0800
	 */
	public String getUsageHr0800() {
		return usageHr0800;
	}
	/**
	 * @param usageHr0800 the usageHr0800 to set
	 */
	public void setUsageHr0800(String usageHr0800) {
		this.usageHr0800 = usageHr0800;
	}
	/**
	 * @return the usageHr0815
	 */
	public String getUsageHr0815() {
		return usageHr0815;
	}
	/**
	 * @param usageHr0815 the usageHr0815 to set
	 */
	public void setUsageHr0815(String usageHr0815) {
		this.usageHr0815 = usageHr0815;
	}
	/**
	 * @return the usageHr0830
	 */
	public String getUsageHr0830() {
		return usageHr0830;
	}
	/**
	 * @param usageHr0830 the usageHr0830 to set
	 */
	public void setUsageHr0830(String usageHr0830) {
		this.usageHr0830 = usageHr0830;
	}
	/**
	 * @return the usageHr0845
	 */
	public String getUsageHr0845() {
		return usageHr0845;
	}
	/**
	 * @param usageHr0845 the usageHr0845 to set
	 */
	public void setUsageHr0845(String usageHr0845) {
		this.usageHr0845 = usageHr0845;
	}
	/**
	 * @return the usageHr0900
	 */
	public String getUsageHr0900() {
		return usageHr0900;
	}
	/**
	 * @param usageHr0900 the usageHr0900 to set
	 */
	public void setUsageHr0900(String usageHr0900) {
		this.usageHr0900 = usageHr0900;
	}
	/**
	 * @return the usageHr0915
	 */
	public String getUsageHr0915() {
		return usageHr0915;
	}
	/**
	 * @param usageHr0915 the usageHr0915 to set
	 */
	public void setUsageHr0915(String usageHr0915) {
		this.usageHr0915 = usageHr0915;
	}
	/**
	 * @return the usageHr0930
	 */
	public String getUsageHr0930() {
		return usageHr0930;
	}
	/**
	 * @param usageHr0930 the usageHr0930 to set
	 */
	public void setUsageHr0930(String usageHr0930) {
		this.usageHr0930 = usageHr0930;
	}
	/**
	 * @return the usageHr0945
	 */
	public String getUsageHr0945() {
		return usageHr0945;
	}
	/**
	 * @param usageHr0945 the usageHr0945 to set
	 */
	public void setUsageHr0945(String usageHr0945) {
		this.usageHr0945 = usageHr0945;
	}
	/**
	 * @return the usageHr1000
	 */
	public String getUsageHr1000() {
		return usageHr1000;
	}
	/**
	 * @param usageHr1000 the usageHr1000 to set
	 */
	public void setUsageHr1000(String usageHr1000) {
		this.usageHr1000 = usageHr1000;
	}
	/**
	 * @return the usageHr1015
	 */
	public String getUsageHr1015() {
		return usageHr1015;
	}
	/**
	 * @param usageHr1015 the usageHr1015 to set
	 */
	public void setUsageHr1015(String usageHr1015) {
		this.usageHr1015 = usageHr1015;
	}
	/**
	 * @return the usageHr1030
	 */
	public String getUsageHr1030() {
		return usageHr1030;
	}
	/**
	 * @param usageHr1030 the usageHr1030 to set
	 */
	public void setUsageHr1030(String usageHr1030) {
		this.usageHr1030 = usageHr1030;
	}
	/**
	 * @return the usageHr1045
	 */
	public String getUsageHr1045() {
		return usageHr1045;
	}
	/**
	 * @param usageHr1045 the usageHr1045 to set
	 */
	public void setUsageHr1045(String usageHr1045) {
		this.usageHr1045 = usageHr1045;
	}
	/**
	 * @return the usageHr1100
	 */
	public String getUsageHr1100() {
		return usageHr1100;
	}
	/**
	 * @param usageHr1100 the usageHr1100 to set
	 */
	public void setUsageHr1100(String usageHr1100) {
		this.usageHr1100 = usageHr1100;
	}
	/**
	 * @return the usageHr1115
	 */
	public String getUsageHr1115() {
		return usageHr1115;
	}
	/**
	 * @param usageHr1115 the usageHr1115 to set
	 */
	public void setUsageHr1115(String usageHr1115) {
		this.usageHr1115 = usageHr1115;
	}
	/**
	 * @return the usageHr1130
	 */
	public String getUsageHr1130() {
		return usageHr1130;
	}
	/**
	 * @param usageHr1130 the usageHr1130 to set
	 */
	public void setUsageHr1130(String usageHr1130) {
		this.usageHr1130 = usageHr1130;
	}
	/**
	 * @return the usageHr1145
	 */
	public String getUsageHr1145() {
		return usageHr1145;
	}
	/**
	 * @param usageHr1145 the usageHr1145 to set
	 */
	public void setUsageHr1145(String usageHr1145) {
		this.usageHr1145 = usageHr1145;
	}
	/**
	 * @return the usageHr1200
	 */
	public String getUsageHr1200() {
		return usageHr1200;
	}
	/**
	 * @param usageHr1200 the usageHr1200 to set
	 */
	public void setUsageHr1200(String usageHr1200) {
		this.usageHr1200 = usageHr1200;
	}
	/**
	 * @return the usageHr1215
	 */
	public String getUsageHr1215() {
		return usageHr1215;
	}
	/**
	 * @param usageHr1215 the usageHr1215 to set
	 */
	public void setUsageHr1215(String usageHr1215) {
		this.usageHr1215 = usageHr1215;
	}
	/**
	 * @return the usageHr1230
	 */
	public String getUsageHr1230() {
		return usageHr1230;
	}
	/**
	 * @param usageHr1230 the usageHr1230 to set
	 */
	public void setUsageHr1230(String usageHr1230) {
		this.usageHr1230 = usageHr1230;
	}
	/**
	 * @return the usageHr1245
	 */
	public String getUsageHr1245() {
		return usageHr1245;
	}
	/**
	 * @param usageHr1245 the usageHr1245 to set
	 */
	public void setUsageHr1245(String usageHr1245) {
		this.usageHr1245 = usageHr1245;
	}
	/**
	 * @return the usageHr1300
	 */
	public String getUsageHr1300() {
		return usageHr1300;
	}
	/**
	 * @param usageHr1300 the usageHr1300 to set
	 */
	public void setUsageHr1300(String usageHr1300) {
		this.usageHr1300 = usageHr1300;
	}
	/**
	 * @return the usageHr1315
	 */
	public String getUsageHr1315() {
		return usageHr1315;
	}
	/**
	 * @param usageHr1315 the usageHr1315 to set
	 */
	public void setUsageHr1315(String usageHr1315) {
		this.usageHr1315 = usageHr1315;
	}
	/**
	 * @return the usageHr1330
	 */
	public String getUsageHr1330() {
		return usageHr1330;
	}
	/**
	 * @param usageHr1330 the usageHr1330 to set
	 */
	public void setUsageHr1330(String usageHr1330) {
		this.usageHr1330 = usageHr1330;
	}
	/**
	 * @return the usageHr1345
	 */
	public String getUsageHr1345() {
		return usageHr1345;
	}
	/**
	 * @param usageHr1345 the usageHr1345 to set
	 */
	public void setUsageHr1345(String usageHr1345) {
		this.usageHr1345 = usageHr1345;
	}
	/**
	 * @return the usageHr1400
	 */
	public String getUsageHr1400() {
		return usageHr1400;
	}
	/**
	 * @param usageHr1400 the usageHr1400 to set
	 */
	public void setUsageHr1400(String usageHr1400) {
		this.usageHr1400 = usageHr1400;
	}
	/**
	 * @return the usageHr1415
	 */
	public String getUsageHr1415() {
		return usageHr1415;
	}
	/**
	 * @param usageHr1415 the usageHr1415 to set
	 */
	public void setUsageHr1415(String usageHr1415) {
		this.usageHr1415 = usageHr1415;
	}
	/**
	 * @return the usageHr1430
	 */
	public String getUsageHr1430() {
		return usageHr1430;
	}
	/**
	 * @param usageHr1430 the usageHr1430 to set
	 */
	public void setUsageHr1430(String usageHr1430) {
		this.usageHr1430 = usageHr1430;
	}
	/**
	 * @return the usageHr1445
	 */
	public String getUsageHr1445() {
		return usageHr1445;
	}
	/**
	 * @param usageHr1445 the usageHr1445 to set
	 */
	public void setUsageHr1445(String usageHr1445) {
		this.usageHr1445 = usageHr1445;
	}
	/**
	 * @return the usageHr1500
	 */
	public String getUsageHr1500() {
		return usageHr1500;
	}
	/**
	 * @param usageHr1500 the usageHr1500 to set
	 */
	public void setUsageHr1500(String usageHr1500) {
		this.usageHr1500 = usageHr1500;
	}
	/**
	 * @return the usageHr1515
	 */
	public String getUsageHr1515() {
		return usageHr1515;
	}
	/**
	 * @param usageHr1515 the usageHr1515 to set
	 */
	public void setUsageHr1515(String usageHr1515) {
		this.usageHr1515 = usageHr1515;
	}
	/**
	 * @return the usageHr1530
	 */
	public String getUsageHr1530() {
		return usageHr1530;
	}
	/**
	 * @param usageHr1530 the usageHr1530 to set
	 */
	public void setUsageHr1530(String usageHr1530) {
		this.usageHr1530 = usageHr1530;
	}
	/**
	 * @return the usageHr1545
	 */
	public String getUsageHr1545() {
		return usageHr1545;
	}
	/**
	 * @param usageHr1545 the usageHr1545 to set
	 */
	public void setUsageHr1545(String usageHr1545) {
		this.usageHr1545 = usageHr1545;
	}
	/**
	 * @return the usageHr1600
	 */
	public String getUsageHr1600() {
		return usageHr1600;
	}
	/**
	 * @param usageHr1600 the usageHr1600 to set
	 */
	public void setUsageHr1600(String usageHr1600) {
		this.usageHr1600 = usageHr1600;
	}
	/**
	 * @return the usageHr1615
	 */
	public String getUsageHr1615() {
		return usageHr1615;
	}
	/**
	 * @param usageHr1615 the usageHr1615 to set
	 */
	public void setUsageHr1615(String usageHr1615) {
		this.usageHr1615 = usageHr1615;
	}
	/**
	 * @return the usageHr1630
	 */
	public String getUsageHr1630() {
		return usageHr1630;
	}
	/**
	 * @param usageHr1630 the usageHr1630 to set
	 */
	public void setUsageHr1630(String usageHr1630) {
		this.usageHr1630 = usageHr1630;
	}
	/**
	 * @return the usageHr1645
	 */
	public String getUsageHr1645() {
		return usageHr1645;
	}
	/**
	 * @param usageHr1645 the usageHr1645 to set
	 */
	public void setUsageHr1645(String usageHr1645) {
		this.usageHr1645 = usageHr1645;
	}
	/**
	 * @return the usageHr1700
	 */
	public String getUsageHr1700() {
		return usageHr1700;
	}
	/**
	 * @param usageHr1700 the usageHr1700 to set
	 */
	public void setUsageHr1700(String usageHr1700) {
		this.usageHr1700 = usageHr1700;
	}
	/**
	 * @return the usageHr1715
	 */
	public String getUsageHr1715() {
		return usageHr1715;
	}
	/**
	 * @param usageHr1715 the usageHr1715 to set
	 */
	public void setUsageHr1715(String usageHr1715) {
		this.usageHr1715 = usageHr1715;
	}
	/**
	 * @return the usageHr1730
	 */
	public String getUsageHr1730() {
		return usageHr1730;
	}
	/**
	 * @param usageHr1730 the usageHr1730 to set
	 */
	public void setUsageHr1730(String usageHr1730) {
		this.usageHr1730 = usageHr1730;
	}
	/**
	 * @return the usageHr1745
	 */
	public String getUsageHr1745() {
		return usageHr1745;
	}
	/**
	 * @param usageHr1745 the usageHr1745 to set
	 */
	public void setUsageHr1745(String usageHr1745) {
		this.usageHr1745 = usageHr1745;
	}
	/**
	 * @return the usageHr1800
	 */
	public String getUsageHr1800() {
		return usageHr1800;
	}
	/**
	 * @param usageHr1800 the usageHr1800 to set
	 */
	public void setUsageHr1800(String usageHr1800) {
		this.usageHr1800 = usageHr1800;
	}
	/**
	 * @return the usageHr1815
	 */
	public String getUsageHr1815() {
		return usageHr1815;
	}
	/**
	 * @param usageHr1815 the usageHr1815 to set
	 */
	public void setUsageHr1815(String usageHr1815) {
		this.usageHr1815 = usageHr1815;
	}
	/**
	 * @return the usageHr1830
	 */
	public String getUsageHr1830() {
		return usageHr1830;
	}
	/**
	 * @param usageHr1830 the usageHr1830 to set
	 */
	public void setUsageHr1830(String usageHr1830) {
		this.usageHr1830 = usageHr1830;
	}
	/**
	 * @return the usageHr1845
	 */
	public String getUsageHr1845() {
		return usageHr1845;
	}
	/**
	 * @param usageHr1845 the usageHr1845 to set
	 */
	public void setUsageHr1845(String usageHr1845) {
		this.usageHr1845 = usageHr1845;
	}
	/**
	 * @return the usageHr1900
	 */
	public String getUsageHr1900() {
		return usageHr1900;
	}
	/**
	 * @param usageHr1900 the usageHr1900 to set
	 */
	public void setUsageHr1900(String usageHr1900) {
		this.usageHr1900 = usageHr1900;
	}
	/**
	 * @return the usageHr1915
	 */
	public String getUsageHr1915() {
		return usageHr1915;
	}
	/**
	 * @param usageHr1915 the usageHr1915 to set
	 */
	public void setUsageHr1915(String usageHr1915) {
		this.usageHr1915 = usageHr1915;
	}
	/**
	 * @return the usageHr1930
	 */
	public String getUsageHr1930() {
		return usageHr1930;
	}
	/**
	 * @param usageHr1930 the usageHr1930 to set
	 */
	public void setUsageHr1930(String usageHr1930) {
		this.usageHr1930 = usageHr1930;
	}
	/**
	 * @return the usageHr1945
	 */
	public String getUsageHr1945() {
		return usageHr1945;
	}
	/**
	 * @param usageHr1945 the usageHr1945 to set
	 */
	public void setUsageHr1945(String usageHr1945) {
		this.usageHr1945 = usageHr1945;
	}
	/**
	 * @return the usageHr2000
	 */
	public String getUsageHr2000() {
		return usageHr2000;
	}
	/**
	 * @param usageHr2000 the usageHr2000 to set
	 */
	public void setUsageHr2000(String usageHr2000) {
		this.usageHr2000 = usageHr2000;
	}
	/**
	 * @return the usageHr2015
	 */
	public String getUsageHr2015() {
		return usageHr2015;
	}
	/**
	 * @param usageHr2015 the usageHr2015 to set
	 */
	public void setUsageHr2015(String usageHr2015) {
		this.usageHr2015 = usageHr2015;
	}
	/**
	 * @return the usageHr2030
	 */
	public String getUsageHr2030() {
		return usageHr2030;
	}
	/**
	 * @param usageHr2030 the usageHr2030 to set
	 */
	public void setUsageHr2030(String usageHr2030) {
		this.usageHr2030 = usageHr2030;
	}
	/**
	 * @return the usageHr2045
	 */
	public String getUsageHr2045() {
		return usageHr2045;
	}
	/**
	 * @param usageHr2045 the usageHr2045 to set
	 */
	public void setUsageHr2045(String usageHr2045) {
		this.usageHr2045 = usageHr2045;
	}
	/**
	 * @return the usageHr2100
	 */
	public String getUsageHr2100() {
		return usageHr2100;
	}
	/**
	 * @param usageHr2100 the usageHr2100 to set
	 */
	public void setUsageHr2100(String usageHr2100) {
		this.usageHr2100 = usageHr2100;
	}
	/**
	 * @return the usageHr2115
	 */
	public String getUsageHr2115() {
		return usageHr2115;
	}
	/**
	 * @param usageHr2115 the usageHr2115 to set
	 */
	public void setUsageHr2115(String usageHr2115) {
		this.usageHr2115 = usageHr2115;
	}
	/**
	 * @return the usageHr2130
	 */
	public String getUsageHr2130() {
		return usageHr2130;
	}
	/**
	 * @param usageHr2130 the usageHr2130 to set
	 */
	public void setUsageHr2130(String usageHr2130) {
		this.usageHr2130 = usageHr2130;
	}
	/**
	 * @return the usageHr2145
	 */
	public String getUsageHr2145() {
		return usageHr2145;
	}
	/**
	 * @param usageHr2145 the usageHr2145 to set
	 */
	public void setUsageHr2145(String usageHr2145) {
		this.usageHr2145 = usageHr2145;
	}
	/**
	 * @return the usageHr2200
	 */
	public String getUsageHr2200() {
		return usageHr2200;
	}
	/**
	 * @param usageHr2200 the usageHr2200 to set
	 */
	public void setUsageHr2200(String usageHr2200) {
		this.usageHr2200 = usageHr2200;
	}
	/**
	 * @return the usageHr2215
	 */
	public String getUsageHr2215() {
		return usageHr2215;
	}
	/**
	 * @param usageHr2215 the usageHr2215 to set
	 */
	public void setUsageHr2215(String usageHr2215) {
		this.usageHr2215 = usageHr2215;
	}
	/**
	 * @return the usageHr2230
	 */
	public String getUsageHr2230() {
		return usageHr2230;
	}
	/**
	 * @param usageHr2230 the usageHr2230 to set
	 */
	public void setUsageHr2230(String usageHr2230) {
		this.usageHr2230 = usageHr2230;
	}
	/**
	 * @return the usageHr2245
	 */
	public String getUsageHr2245() {
		return usageHr2245;
	}
	/**
	 * @param usageHr2245 the usageHr2245 to set
	 */
	public void setUsageHr2245(String usageHr2245) {
		this.usageHr2245 = usageHr2245;
	}
	/**
	 * @return the usageHr2300
	 */
	public String getUsageHr2300() {
		return usageHr2300;
	}
	/**
	 * @param usageHr2300 the usageHr2300 to set
	 */
	public void setUsageHr2300(String usageHr2300) {
		this.usageHr2300 = usageHr2300;
	}
	/**
	 * @return the usageHr2315
	 */
	public String getUsageHr2315() {
		return usageHr2315;
	}
	/**
	 * @param usageHr2315 the usageHr2315 to set
	 */
	public void setUsageHr2315(String usageHr2315) {
		this.usageHr2315 = usageHr2315;
	}
	/**
	 * @return the usageHr2330
	 */
	public String getUsageHr2330() {
		return usageHr2330;
	}
	/**
	 * @param usageHr2330 the usageHr2330 to set
	 */
	public void setUsageHr2330(String usageHr2330) {
		this.usageHr2330 = usageHr2330;
	}
	/**
	 * @return the usageHr2345
	 */
	public String getUsageHr2345() {
		return usageHr2345;
	}
	/**
	 * @param usageHr2345 the usageHr2345 to set
	 */
	public void setUsageHr2345(String usageHr2345) {
		this.usageHr2345 = usageHr2345;
	}
	/**
	 * @return the usageHr2400
	 */
	public String getUsageHr2400() {
		return usageHr2400;
	}
	/**
	 * @param usageHr2400 the usageHr2400 to set
	 */
	public void setUsageHr2400(String usageHr2400) {
		this.usageHr2400 = usageHr2400;
	}
	/**
	 * @return the costHr0015
	 */
	public String getCostHr0015() {
		return costHr0015;
	}
	/**
	 * @param costHr0015 the costHr0015 to set
	 */
	public void setCostHr0015(String costHr0015) {
		this.costHr0015 = costHr0015;
	}
	/**
	 * @return the costHr0030
	 */
	public String getCostHr0030() {
		return costHr0030;
	}
	/**
	 * @param costHr0030 the costHr0030 to set
	 */
	public void setCostHr0030(String costHr0030) {
		this.costHr0030 = costHr0030;
	}
	/**
	 * @return the costHr0045
	 */
	public String getCostHr0045() {
		return costHr0045;
	}
	/**
	 * @param costHr0045 the costHr0045 to set
	 */
	public void setCostHr0045(String costHr0045) {
		this.costHr0045 = costHr0045;
	}
	/**
	 * @return the costHr0100
	 */
	public String getCostHr0100() {
		return costHr0100;
	}
	/**
	 * @param costHr0100 the costHr0100 to set
	 */
	public void setCostHr0100(String costHr0100) {
		this.costHr0100 = costHr0100;
	}
	/**
	 * @return the costHr0115
	 */
	public String getCostHr0115() {
		return costHr0115;
	}
	/**
	 * @param costHr0115 the costHr0115 to set
	 */
	public void setCostHr0115(String costHr0115) {
		this.costHr0115 = costHr0115;
	}
	/**
	 * @return the costHr0130
	 */
	public String getCostHr0130() {
		return costHr0130;
	}
	/**
	 * @param costHr0130 the costHr0130 to set
	 */
	public void setCostHr0130(String costHr0130) {
		this.costHr0130 = costHr0130;
	}
	/**
	 * @return the costHr0145
	 */
	public String getCostHr0145() {
		return costHr0145;
	}
	/**
	 * @param costHr0145 the costHr0145 to set
	 */
	public void setCostHr0145(String costHr0145) {
		this.costHr0145 = costHr0145;
	}
	/**
	 * @return the costHr0200
	 */
	public String getCostHr0200() {
		return costHr0200;
	}
	/**
	 * @param costHr0200 the costHr0200 to set
	 */
	public void setCostHr0200(String costHr0200) {
		this.costHr0200 = costHr0200;
	}
	/**
	 * @return the costHr0215
	 */
	public String getCostHr0215() {
		return costHr0215;
	}
	/**
	 * @param costHr0215 the costHr0215 to set
	 */
	public void setCostHr0215(String costHr0215) {
		this.costHr0215 = costHr0215;
	}
	/**
	 * @return the costHr0230
	 */
	public String getCostHr0230() {
		return costHr0230;
	}
	/**
	 * @param costHr0230 the costHr0230 to set
	 */
	public void setCostHr0230(String costHr0230) {
		this.costHr0230 = costHr0230;
	}
	/**
	 * @return the costHr0245
	 */
	public String getCostHr0245() {
		return costHr0245;
	}
	/**
	 * @param costHr0245 the costHr0245 to set
	 */
	public void setCostHr0245(String costHr0245) {
		this.costHr0245 = costHr0245;
	}
	/**
	 * @return the costHr0300
	 */
	public String getCostHr0300() {
		return costHr0300;
	}
	/**
	 * @param costHr0300 the costHr0300 to set
	 */
	public void setCostHr0300(String costHr0300) {
		this.costHr0300 = costHr0300;
	}
	/**
	 * @return the costHr0315
	 */
	public String getCostHr0315() {
		return costHr0315;
	}
	/**
	 * @param costHr0315 the costHr0315 to set
	 */
	public void setCostHr0315(String costHr0315) {
		this.costHr0315 = costHr0315;
	}
	/**
	 * @return the costHr0330
	 */
	public String getCostHr0330() {
		return costHr0330;
	}
	/**
	 * @param costHr0330 the costHr0330 to set
	 */
	public void setCostHr0330(String costHr0330) {
		this.costHr0330 = costHr0330;
	}
	/**
	 * @return the costHr0345
	 */
	public String getCostHr0345() {
		return costHr0345;
	}
	/**
	 * @param costHr0345 the costHr0345 to set
	 */
	public void setCostHr0345(String costHr0345) {
		this.costHr0345 = costHr0345;
	}
	/**
	 * @return the costHr0400
	 */
	public String getCostHr0400() {
		return costHr0400;
	}
	/**
	 * @param costHr0400 the costHr0400 to set
	 */
	public void setCostHr0400(String costHr0400) {
		this.costHr0400 = costHr0400;
	}
	/**
	 * @return the costHr0415
	 */
	public String getCostHr0415() {
		return costHr0415;
	}
	/**
	 * @param costHr0415 the costHr0415 to set
	 */
	public void setCostHr0415(String costHr0415) {
		this.costHr0415 = costHr0415;
	}
	/**
	 * @return the costHr0430
	 */
	public String getCostHr0430() {
		return costHr0430;
	}
	/**
	 * @param costHr0430 the costHr0430 to set
	 */
	public void setCostHr0430(String costHr0430) {
		this.costHr0430 = costHr0430;
	}
	/**
	 * @return the costHr0445
	 */
	public String getCostHr0445() {
		return costHr0445;
	}
	/**
	 * @param costHr0445 the costHr0445 to set
	 */
	public void setCostHr0445(String costHr0445) {
		this.costHr0445 = costHr0445;
	}
	/**
	 * @return the costHr0500
	 */
	public String getCostHr0500() {
		return costHr0500;
	}
	/**
	 * @param costHr0500 the costHr0500 to set
	 */
	public void setCostHr0500(String costHr0500) {
		this.costHr0500 = costHr0500;
	}
	/**
	 * @return the costHr0515
	 */
	public String getCostHr0515() {
		return costHr0515;
	}
	/**
	 * @param costHr0515 the costHr0515 to set
	 */
	public void setCostHr0515(String costHr0515) {
		this.costHr0515 = costHr0515;
	}
	/**
	 * @return the costHr0530
	 */
	public String getCostHr0530() {
		return costHr0530;
	}
	/**
	 * @param costHr0530 the costHr0530 to set
	 */
	public void setCostHr0530(String costHr0530) {
		this.costHr0530 = costHr0530;
	}
	/**
	 * @return the costHr0545
	 */
	public String getCostHr0545() {
		return costHr0545;
	}
	/**
	 * @param costHr0545 the costHr0545 to set
	 */
	public void setCostHr0545(String costHr0545) {
		this.costHr0545 = costHr0545;
	}
	/**
	 * @return the costHr0600
	 */
	public String getCostHr0600() {
		return costHr0600;
	}
	/**
	 * @param costHr0600 the costHr0600 to set
	 */
	public void setCostHr0600(String costHr0600) {
		this.costHr0600 = costHr0600;
	}
	/**
	 * @return the costHr0615
	 */
	public String getCostHr0615() {
		return costHr0615;
	}
	/**
	 * @param costHr0615 the costHr0615 to set
	 */
	public void setCostHr0615(String costHr0615) {
		this.costHr0615 = costHr0615;
	}
	/**
	 * @return the costHr0630
	 */
	public String getCostHr0630() {
		return costHr0630;
	}
	/**
	 * @param costHr0630 the costHr0630 to set
	 */
	public void setCostHr0630(String costHr0630) {
		this.costHr0630 = costHr0630;
	}
	/**
	 * @return the costHr0645
	 */
	public String getCostHr0645() {
		return costHr0645;
	}
	/**
	 * @param costHr0645 the costHr0645 to set
	 */
	public void setCostHr0645(String costHr0645) {
		this.costHr0645 = costHr0645;
	}
	/**
	 * @return the costHr0700
	 */
	public String getCostHr0700() {
		return costHr0700;
	}
	/**
	 * @param costHr0700 the costHr0700 to set
	 */
	public void setCostHr0700(String costHr0700) {
		this.costHr0700 = costHr0700;
	}
	/**
	 * @return the costHr0715
	 */
	public String getCostHr0715() {
		return costHr0715;
	}
	/**
	 * @param costHr0715 the costHr0715 to set
	 */
	public void setCostHr0715(String costHr0715) {
		this.costHr0715 = costHr0715;
	}
	/**
	 * @return the costHr0730
	 */
	public String getCostHr0730() {
		return costHr0730;
	}
	/**
	 * @param costHr0730 the costHr0730 to set
	 */
	public void setCostHr0730(String costHr0730) {
		this.costHr0730 = costHr0730;
	}
	/**
	 * @return the costHr0745
	 */
	public String getCostHr0745() {
		return costHr0745;
	}
	/**
	 * @param costHr0745 the costHr0745 to set
	 */
	public void setCostHr0745(String costHr0745) {
		this.costHr0745 = costHr0745;
	}
	/**
	 * @return the costHr0800
	 */
	public String getCostHr0800() {
		return costHr0800;
	}
	/**
	 * @param costHr0800 the costHr0800 to set
	 */
	public void setCostHr0800(String costHr0800) {
		this.costHr0800 = costHr0800;
	}
	/**
	 * @return the costHr0815
	 */
	public String getCostHr0815() {
		return costHr0815;
	}
	/**
	 * @param costHr0815 the costHr0815 to set
	 */
	public void setCostHr0815(String costHr0815) {
		this.costHr0815 = costHr0815;
	}
	/**
	 * @return the costHr0830
	 */
	public String getCostHr0830() {
		return costHr0830;
	}
	/**
	 * @param costHr0830 the costHr0830 to set
	 */
	public void setCostHr0830(String costHr0830) {
		this.costHr0830 = costHr0830;
	}
	/**
	 * @return the costHr0845
	 */
	public String getCostHr0845() {
		return costHr0845;
	}
	/**
	 * @param costHr0845 the costHr0845 to set
	 */
	public void setCostHr0845(String costHr0845) {
		this.costHr0845 = costHr0845;
	}
	/**
	 * @return the costHr0900
	 */
	public String getCostHr0900() {
		return costHr0900;
	}
	/**
	 * @param costHr0900 the costHr0900 to set
	 */
	public void setCostHr0900(String costHr0900) {
		this.costHr0900 = costHr0900;
	}
	/**
	 * @return the costHr0915
	 */
	public String getCostHr0915() {
		return costHr0915;
	}
	/**
	 * @param costHr0915 the costHr0915 to set
	 */
	public void setCostHr0915(String costHr0915) {
		this.costHr0915 = costHr0915;
	}
	/**
	 * @return the costHr0930
	 */
	public String getCostHr0930() {
		return costHr0930;
	}
	/**
	 * @param costHr0930 the costHr0930 to set
	 */
	public void setCostHr0930(String costHr0930) {
		this.costHr0930 = costHr0930;
	}
	/**
	 * @return the costHr0945
	 */
	public String getCostHr0945() {
		return costHr0945;
	}
	/**
	 * @param costHr0945 the costHr0945 to set
	 */
	public void setCostHr0945(String costHr0945) {
		this.costHr0945 = costHr0945;
	}
	/**
	 * @return the costHr1000
	 */
	public String getCostHr1000() {
		return costHr1000;
	}
	/**
	 * @param costHr1000 the costHr1000 to set
	 */
	public void setCostHr1000(String costHr1000) {
		this.costHr1000 = costHr1000;
	}
	/**
	 * @return the costHr1015
	 */
	public String getCostHr1015() {
		return costHr1015;
	}
	/**
	 * @param costHr1015 the costHr1015 to set
	 */
	public void setCostHr1015(String costHr1015) {
		this.costHr1015 = costHr1015;
	}
	/**
	 * @return the costHr1030
	 */
	public String getCostHr1030() {
		return costHr1030;
	}
	/**
	 * @param costHr1030 the costHr1030 to set
	 */
	public void setCostHr1030(String costHr1030) {
		this.costHr1030 = costHr1030;
	}
	/**
	 * @return the costHr1045
	 */
	public String getCostHr1045() {
		return costHr1045;
	}
	/**
	 * @param costHr1045 the costHr1045 to set
	 */
	public void setCostHr1045(String costHr1045) {
		this.costHr1045 = costHr1045;
	}
	/**
	 * @return the costHr1100
	 */
	public String getCostHr1100() {
		return costHr1100;
	}
	/**
	 * @param costHr1100 the costHr1100 to set
	 */
	public void setCostHr1100(String costHr1100) {
		this.costHr1100 = costHr1100;
	}
	/**
	 * @return the costHr1115
	 */
	public String getCostHr1115() {
		return costHr1115;
	}
	/**
	 * @param costHr1115 the costHr1115 to set
	 */
	public void setCostHr1115(String costHr1115) {
		this.costHr1115 = costHr1115;
	}
	/**
	 * @return the costHr1130
	 */
	public String getCostHr1130() {
		return costHr1130;
	}
	/**
	 * @param costHr1130 the costHr1130 to set
	 */
	public void setCostHr1130(String costHr1130) {
		this.costHr1130 = costHr1130;
	}
	/**
	 * @return the costHr1145
	 */
	public String getCostHr1145() {
		return costHr1145;
	}
	/**
	 * @param costHr1145 the costHr1145 to set
	 */
	public void setCostHr1145(String costHr1145) {
		this.costHr1145 = costHr1145;
	}
	/**
	 * @return the costHr1200
	 */
	public String getCostHr1200() {
		return costHr1200;
	}
	/**
	 * @param costHr1200 the costHr1200 to set
	 */
	public void setCostHr1200(String costHr1200) {
		this.costHr1200 = costHr1200;
	}
	/**
	 * @return the costHr1215
	 */
	public String getCostHr1215() {
		return costHr1215;
	}
	/**
	 * @param costHr1215 the costHr1215 to set
	 */
	public void setCostHr1215(String costHr1215) {
		this.costHr1215 = costHr1215;
	}
	/**
	 * @return the costHr1230
	 */
	public String getCostHr1230() {
		return costHr1230;
	}
	/**
	 * @param costHr1230 the costHr1230 to set
	 */
	public void setCostHr1230(String costHr1230) {
		this.costHr1230 = costHr1230;
	}
	/**
	 * @return the costHr1245
	 */
	public String getCostHr1245() {
		return costHr1245;
	}
	/**
	 * @param costHr1245 the costHr1245 to set
	 */
	public void setCostHr1245(String costHr1245) {
		this.costHr1245 = costHr1245;
	}
	/**
	 * @return the costHr1300
	 */
	public String getCostHr1300() {
		return costHr1300;
	}
	/**
	 * @param costHr1300 the costHr1300 to set
	 */
	public void setCostHr1300(String costHr1300) {
		this.costHr1300 = costHr1300;
	}
	/**
	 * @return the costHr1315
	 */
	public String getCostHr1315() {
		return costHr1315;
	}
	/**
	 * @param costHr1315 the costHr1315 to set
	 */
	public void setCostHr1315(String costHr1315) {
		this.costHr1315 = costHr1315;
	}
	/**
	 * @return the costHr1330
	 */
	public String getCostHr1330() {
		return costHr1330;
	}
	/**
	 * @param costHr1330 the costHr1330 to set
	 */
	public void setCostHr1330(String costHr1330) {
		this.costHr1330 = costHr1330;
	}
	/**
	 * @return the costHr1345
	 */
	public String getCostHr1345() {
		return costHr1345;
	}
	/**
	 * @param costHr1345 the costHr1345 to set
	 */
	public void setCostHr1345(String costHr1345) {
		this.costHr1345 = costHr1345;
	}
	/**
	 * @return the costHr1400
	 */
	public String getCostHr1400() {
		return costHr1400;
	}
	/**
	 * @param costHr1400 the costHr1400 to set
	 */
	public void setCostHr1400(String costHr1400) {
		this.costHr1400 = costHr1400;
	}
	/**
	 * @return the costHr1415
	 */
	public String getCostHr1415() {
		return costHr1415;
	}
	/**
	 * @param costHr1415 the costHr1415 to set
	 */
	public void setCostHr1415(String costHr1415) {
		this.costHr1415 = costHr1415;
	}
	/**
	 * @return the costHr1430
	 */
	public String getCostHr1430() {
		return costHr1430;
	}
	/**
	 * @param costHr1430 the costHr1430 to set
	 */
	public void setCostHr1430(String costHr1430) {
		this.costHr1430 = costHr1430;
	}
	/**
	 * @return the costHr1445
	 */
	public String getCostHr1445() {
		return costHr1445;
	}
	/**
	 * @param costHr1445 the costHr1445 to set
	 */
	public void setCostHr1445(String costHr1445) {
		this.costHr1445 = costHr1445;
	}
	/**
	 * @return the costHr1500
	 */
	public String getCostHr1500() {
		return costHr1500;
	}
	/**
	 * @param costHr1500 the costHr1500 to set
	 */
	public void setCostHr1500(String costHr1500) {
		this.costHr1500 = costHr1500;
	}
	/**
	 * @return the costHr1515
	 */
	public String getCostHr1515() {
		return costHr1515;
	}
	/**
	 * @param costHr1515 the costHr1515 to set
	 */
	public void setCostHr1515(String costHr1515) {
		this.costHr1515 = costHr1515;
	}
	/**
	 * @return the costHr1530
	 */
	public String getCostHr1530() {
		return costHr1530;
	}
	/**
	 * @param costHr1530 the costHr1530 to set
	 */
	public void setCostHr1530(String costHr1530) {
		this.costHr1530 = costHr1530;
	}
	/**
	 * @return the costHr1545
	 */
	public String getCostHr1545() {
		return costHr1545;
	}
	/**
	 * @param costHr1545 the costHr1545 to set
	 */
	public void setCostHr1545(String costHr1545) {
		this.costHr1545 = costHr1545;
	}
	/**
	 * @return the costHr1600
	 */
	public String getCostHr1600() {
		return costHr1600;
	}
	/**
	 * @param costHr1600 the costHr1600 to set
	 */
	public void setCostHr1600(String costHr1600) {
		this.costHr1600 = costHr1600;
	}
	/**
	 * @return the costHr1615
	 */
	public String getCostHr1615() {
		return costHr1615;
	}
	/**
	 * @param costHr1615 the costHr1615 to set
	 */
	public void setCostHr1615(String costHr1615) {
		this.costHr1615 = costHr1615;
	}
	/**
	 * @return the costHr1630
	 */
	public String getCostHr1630() {
		return costHr1630;
	}
	/**
	 * @param costHr1630 the costHr1630 to set
	 */
	public void setCostHr1630(String costHr1630) {
		this.costHr1630 = costHr1630;
	}
	/**
	 * @return the costHr1645
	 */
	public String getCostHr1645() {
		return costHr1645;
	}
	/**
	 * @param costHr1645 the costHr1645 to set
	 */
	public void setCostHr1645(String costHr1645) {
		this.costHr1645 = costHr1645;
	}
	/**
	 * @return the costHr1700
	 */
	public String getCostHr1700() {
		return costHr1700;
	}
	/**
	 * @param costHr1700 the costHr1700 to set
	 */
	public void setCostHr1700(String costHr1700) {
		this.costHr1700 = costHr1700;
	}
	/**
	 * @return the costHr1715
	 */
	public String getCostHr1715() {
		return costHr1715;
	}
	/**
	 * @param costHr1715 the costHr1715 to set
	 */
	public void setCostHr1715(String costHr1715) {
		this.costHr1715 = costHr1715;
	}
	/**
	 * @return the costHr1730
	 */
	public String getCostHr1730() {
		return costHr1730;
	}
	/**
	 * @param costHr1730 the costHr1730 to set
	 */
	public void setCostHr1730(String costHr1730) {
		this.costHr1730 = costHr1730;
	}
	/**
	 * @return the costHr1745
	 */
	public String getCostHr1745() {
		return costHr1745;
	}
	/**
	 * @param costHr1745 the costHr1745 to set
	 */
	public void setCostHr1745(String costHr1745) {
		this.costHr1745 = costHr1745;
	}
	/**
	 * @return the costHr1800
	 */
	public String getCostHr1800() {
		return costHr1800;
	}
	/**
	 * @param costHr1800 the costHr1800 to set
	 */
	public void setCostHr1800(String costHr1800) {
		this.costHr1800 = costHr1800;
	}
	/**
	 * @return the costHr1815
	 */
	public String getCostHr1815() {
		return costHr1815;
	}
	/**
	 * @param costHr1815 the costHr1815 to set
	 */
	public void setCostHr1815(String costHr1815) {
		this.costHr1815 = costHr1815;
	}
	/**
	 * @return the costHr1830
	 */
	public String getCostHr1830() {
		return costHr1830;
	}
	/**
	 * @param costHr1830 the costHr1830 to set
	 */
	public void setCostHr1830(String costHr1830) {
		this.costHr1830 = costHr1830;
	}
	/**
	 * @return the costHr1845
	 */
	public String getCostHr1845() {
		return costHr1845;
	}
	/**
	 * @param costHr1845 the costHr1845 to set
	 */
	public void setCostHr1845(String costHr1845) {
		this.costHr1845 = costHr1845;
	}
	/**
	 * @return the costHr1900
	 */
	public String getCostHr1900() {
		return costHr1900;
	}
	/**
	 * @param costHr1900 the costHr1900 to set
	 */
	public void setCostHr1900(String costHr1900) {
		this.costHr1900 = costHr1900;
	}
	/**
	 * @return the costHr1915
	 */
	public String getCostHr1915() {
		return costHr1915;
	}
	/**
	 * @param costHr1915 the costHr1915 to set
	 */
	public void setCostHr1915(String costHr1915) {
		this.costHr1915 = costHr1915;
	}
	/**
	 * @return the costHr1930
	 */
	public String getCostHr1930() {
		return costHr1930;
	}
	/**
	 * @param costHr1930 the costHr1930 to set
	 */
	public void setCostHr1930(String costHr1930) {
		this.costHr1930 = costHr1930;
	}
	/**
	 * @return the costHr1945
	 */
	public String getCostHr1945() {
		return costHr1945;
	}
	/**
	 * @param costHr1945 the costHr1945 to set
	 */
	public void setCostHr1945(String costHr1945) {
		this.costHr1945 = costHr1945;
	}
	/**
	 * @return the costHr2000
	 */
	public String getCostHr2000() {
		return costHr2000;
	}
	/**
	 * @param costHr2000 the costHr2000 to set
	 */
	public void setCostHr2000(String costHr2000) {
		this.costHr2000 = costHr2000;
	}
	/**
	 * @return the costHr2015
	 */
	public String getCostHr2015() {
		return costHr2015;
	}
	/**
	 * @param costHr2015 the costHr2015 to set
	 */
	public void setCostHr2015(String costHr2015) {
		this.costHr2015 = costHr2015;
	}
	/**
	 * @return the costHr2030
	 */
	public String getCostHr2030() {
		return costHr2030;
	}
	/**
	 * @param costHr2030 the costHr2030 to set
	 */
	public void setCostHr2030(String costHr2030) {
		this.costHr2030 = costHr2030;
	}
	/**
	 * @return the costHr2045
	 */
	public String getCostHr2045() {
		return costHr2045;
	}
	/**
	 * @param costHr2045 the costHr2045 to set
	 */
	public void setCostHr2045(String costHr2045) {
		this.costHr2045 = costHr2045;
	}
	/**
	 * @return the costHr2100
	 */
	public String getCostHr2100() {
		return costHr2100;
	}
	/**
	 * @param costHr2100 the costHr2100 to set
	 */
	public void setCostHr2100(String costHr2100) {
		this.costHr2100 = costHr2100;
	}
	/**
	 * @return the costHr2115
	 */
	public String getCostHr2115() {
		return costHr2115;
	}
	/**
	 * @param costHr2115 the costHr2115 to set
	 */
	public void setCostHr2115(String costHr2115) {
		this.costHr2115 = costHr2115;
	}
	/**
	 * @return the costHr2130
	 */
	public String getCostHr2130() {
		return costHr2130;
	}
	/**
	 * @param costHr2130 the costHr2130 to set
	 */
	public void setCostHr2130(String costHr2130) {
		this.costHr2130 = costHr2130;
	}
	/**
	 * @return the costHr2145
	 */
	public String getCostHr2145() {
		return costHr2145;
	}
	/**
	 * @param costHr2145 the costHr2145 to set
	 */
	public void setCostHr2145(String costHr2145) {
		this.costHr2145 = costHr2145;
	}
	/**
	 * @return the costHr2200
	 */
	public String getCostHr2200() {
		return costHr2200;
	}
	/**
	 * @param costHr2200 the costHr2200 to set
	 */
	public void setCostHr2200(String costHr2200) {
		this.costHr2200 = costHr2200;
	}
	/**
	 * @return the costHr2215
	 */
	public String getCostHr2215() {
		return costHr2215;
	}
	/**
	 * @param costHr2215 the costHr2215 to set
	 */
	public void setCostHr2215(String costHr2215) {
		this.costHr2215 = costHr2215;
	}
	/**
	 * @return the costHr2230
	 */
	public String getCostHr2230() {
		return costHr2230;
	}
	/**
	 * @param costHr2230 the costHr2230 to set
	 */
	public void setCostHr2230(String costHr2230) {
		this.costHr2230 = costHr2230;
	}
	/**
	 * @return the costHr2245
	 */
	public String getCostHr2245() {
		return costHr2245;
	}
	/**
	 * @param costHr2245 the costHr2245 to set
	 */
	public void setCostHr2245(String costHr2245) {
		this.costHr2245 = costHr2245;
	}
	/**
	 * @return the costHr2300
	 */
	public String getCostHr2300() {
		return costHr2300;
	}
	/**
	 * @param costHr2300 the costHr2300 to set
	 */
	public void setCostHr2300(String costHr2300) {
		this.costHr2300 = costHr2300;
	}
	/**
	 * @return the costHr2315
	 */
	public String getCostHr2315() {
		return costHr2315;
	}
	/**
	 * @param costHr2315 the costHr2315 to set
	 */
	public void setCostHr2315(String costHr2315) {
		this.costHr2315 = costHr2315;
	}
	/**
	 * @return the costHr2330
	 */
	public String getCostHr2330() {
		return costHr2330;
	}
	/**
	 * @param costHr2330 the costHr2330 to set
	 */
	public void setCostHr2330(String costHr2330) {
		this.costHr2330 = costHr2330;
	}
	/**
	 * @return the costHr2345
	 */
	public String getCostHr2345() {
		return costHr2345;
	}
	/**
	 * @param costHr2345 the costHr2345 to set
	 */
	public void setCostHr2345(String costHr2345) {
		this.costHr2345 = costHr2345;
	}
	/**
	 * @return the costHr2400
	 */
	public String getCostHr2400() {
		return costHr2400;
	}
	/**
	 * @param costHr2400 the costHr2400 to set
	 */
	public void setCostHr2400(String costHr2400) {
		this.costHr2400 = costHr2400;
	}
	/**
	 * @return the dayUsg
	 */
	public String getDayUsg() {
		return dayUsg;
	}
	/**
	 * @param dayUsg the dayUsg to set
	 */
	public void setDayUsg(String dayUsg) {
		this.dayUsg = dayUsg;
	}
	/**
	 * @return the dayCst
	 */
	public String getDayCst() {
		return dayCst;
	}
	/**
	 * @param dayCst the dayCst to set
	 */
	public void setDayCst(String dayCst) {
		this.dayCst = dayCst;
	}
	/**
	 * @return the dayTempHigh
	 */
	public String getDayTempHigh() {
		return dayTempHigh;
	}
	/**
	 * @param dayTempHigh the dayTempHigh to set
	 */
	public void setDayTempHigh(String dayTempHigh) {
		this.dayTempHigh = dayTempHigh;
	}
	/**
	 * @return the dayTempLow
	 */
	public String getDayTempLow() {
		return dayTempLow;
	}
	/**
	 * @param dayTempLow the dayTempLow to set
	 */
	public void setDayTempLow(String dayTempLow) {
		this.dayTempLow = dayTempLow;
	}
}
