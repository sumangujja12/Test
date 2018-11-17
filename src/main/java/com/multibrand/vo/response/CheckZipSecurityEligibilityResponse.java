package com.multibrand.vo.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="CheckZipSecurityEligibilityResponse")
public class CheckZipSecurityEligibilityResponse extends GenericResponse implements Serializable {

	private static final long serialVersionUID = -3671204145307613817L;
	
	@SerializedName("d")
	CheckZipSecurityEligibilityOutData checkZipSecurityEligibilityOutData;

	public CheckZipSecurityEligibilityOutData getCheckZipSecurityEligibilityOutData() {
		return checkZipSecurityEligibilityOutData;
	}

	public void setCheckZipSecurityEligibilityOutData(
			CheckZipSecurityEligibilityOutData checkZipSecurityEligibilityOutData) {
		this.checkZipSecurityEligibilityOutData = checkZipSecurityEligibilityOutData;
	}

	@Override
	public String toString() {
		return "CheckZipSecurityEligibilityResponse [checkZipSecurityEligibilityOutData="
				+ checkZipSecurityEligibilityOutData + "]";
	}
	
}
