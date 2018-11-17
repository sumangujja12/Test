package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author vsood30
 *
 */
public class PermitDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1360833116824021225L;
	private String permitType="";
	private String permitClass=""; //Permit Type1
	private String permitDetail=""; //Permit Type2
	private String cityCounty="";
	private String cityCountyName="";
	private String permitPhone="";
	
	public String getPermitType() {
		return permitType;
	}

	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}

	public String getPermitClass() {
		return permitClass;
	}

	public void setPermitClass(String permitClass) {
		this.permitClass = permitClass;
	}

	public String getPermitDetail() {
		return permitDetail;
	}

	public void setPermitDetail(String permitDetail) {
		this.permitDetail = permitDetail;
	}

	public String getCityCounty() {
		return cityCounty;
	}

	public void setCityCounty(String cityCounty) {
		this.cityCounty = cityCounty;
	}

	public String getCityCountyName() {
		return cityCountyName;
	}

	public void setCityCountyName(String cityCountyName) {
		this.cityCountyName = cityCountyName;
	}

	public String getPermitPhone() {
		return permitPhone;
	}

	public void setPermitPhone(String permitPhone) {
		this.permitPhone = permitPhone;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
	
	
}
