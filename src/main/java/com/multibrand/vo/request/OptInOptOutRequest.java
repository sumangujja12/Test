package com.multibrand.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.Constants;

/**
 * 
 * @author dkrishn1
 *
 */
public class OptInOptOutRequest implements FormEntityRequest, Constants, Serializable{
	
	private String companyCode;
	private String brand;
	private String cellPhone;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String bPNumber;
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 12, groups = SizeConstraint.class)
	private String caNumber;
	
	private String optInFlag;
	private String reqCode;
	private String channel;
	private String houseNumer;
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getbPNumber() {
		return bPNumber;
	}
	public void setbPNumber(String bPNumber) {
		this.bPNumber = bPNumber;
	}
	public String getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}
	public String getOptInFlag() {
		return optInFlag;
	}
	public void setOptInFlag(String optInFlag) {
		this.optInFlag = optInFlag;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getHouseNumer() {
		return houseNumer;
	}
	public void setHouseNumer(String houseNumer) {
		this.houseNumer = houseNumer;
	}
	
	
}
