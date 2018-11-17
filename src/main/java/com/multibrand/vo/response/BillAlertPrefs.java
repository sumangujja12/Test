package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BillAlertPrefs")
public class BillAlertPrefs {

	private String myBillIsDuePref;
	private String myCostExceeds;
	private String myCostExceedsPref;
	private String myProjBillExceeds;
	private String myProjBillExceedsPref;

	public String getMyBillIsDuePref() {
		return myBillIsDuePref;
	}

	public void setMyBillIsDuePref(String myBillIsDuePref) {
		this.myBillIsDuePref = myBillIsDuePref;
	}

	public String getMyCostExceeds() {
		return myCostExceeds;
	}

	public void setMyCostExceeds(String myCostExceeds) {
		this.myCostExceeds = myCostExceeds;
	}

	public String getMyCostExceedsPref() {
		return myCostExceedsPref;
	}

	public void setMyCostExceedsPref(String myCostExceedsPref) {
		this.myCostExceedsPref = myCostExceedsPref;
	}

	public String getMyProjBillExceeds() {
		return myProjBillExceeds;
	}

	public void setMyProjBillExceeds(String myProjBillExceeds) {
		this.myProjBillExceeds = myProjBillExceeds;
	}

	public String getMyProjBillExceedsPref() {
		return myProjBillExceedsPref;
	}

	public void setMyProjBillExceedsPref(String myProjBillExceedsPref) {
		this.myProjBillExceedsPref = myProjBillExceedsPref;
	}
}
