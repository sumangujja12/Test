package com.multibrand.dto.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;

@Component
public class NeiBPCARequest implements FormEntityRequest, Constants, Serializable {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5573962052855620478L;

	@NotBlank(groups = BasicConstraint.class)
	private String acctNumber;
	
	private String autopay;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billCity;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billCountry;
	
	private String billDistrict;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billHouseNum1;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billState;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billStreet;
	
	@NotBlank(groups = BasicConstraint.class)
	private String billZip;
	
	
	private String ebill;
	
	@NotBlank(groups = BasicConstraint.class)
	private String emailAddr;
	
	@NotBlank(groups = BasicConstraint.class)
	private String firstName;
	
	@NotBlank(groups = BasicConstraint.class)
	private String fullName;
	
	@NotBlank(groups = BasicConstraint.class)
	private String lastName;
	
	
	private String phoneNumber;
	
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String planId;
	
	@NotBlank(groups = BasicConstraint.class)
	private String planName;
	
	
	private String srvcCity;
	private String srvcCountry;
	private String srvcDistrict;
	private String srvcHouseNum1;
	private String srvcState;
	private String srvcStreet;
	private String srvcZip;
	
	@NotBlank(groups = BasicConstraint.class)
	private String utility;

	/**
	 * @return the iAcctNumber
	 */
	public String getAcctNumber() {
		return acctNumber;
	}

	/**
	 * @param iAcctNumber the iAcctNumber to set
	 */
	public void setAcctNumber(String iAcctNumber) {
		acctNumber = iAcctNumber;
	}

	/**
	 * @return the iAutopay
	 */
	public String getAutopay() {
		return autopay;
	}

	/**
	 * @param iAutopay the iAutopay to set
	 */
	public void setAutopay(String iAutopay) {
		autopay = iAutopay;
	}

	/**
	 * @return the iBillCity
	 */
	public String getBillCity() {
		return billCity;
	}

	/**
	 * @param iBillCity the iBillCity to set
	 */
	public void setBillCity(String iBillCity) {
		billCity = iBillCity;
	}

	/**
	 * @return the iBillCountry
	 */
	public String getBillCountry() {
		return billCountry;
	}

	/**
	 * @param iBillCountry the iBillCountry to set
	 */
	public void setBillCountry(String iBillCountry) {
		billCountry = iBillCountry;
	}

	/**
	 * @return the iBillDistrict
	 */
	public String getBillDistrict() {
		return billDistrict;
	}

	/**
	 * @param iBillDistrict the iBillDistrict to set
	 */
	public void setBillDistrict(String iBillDistrict) {
		billDistrict = iBillDistrict;
	}

	/**
	 * @return the iBillHouseNum1
	 */
	public String getBillHouseNum1() {
		return billHouseNum1;
	}

	/**
	 * @param iBillHouseNum1 the iBillHouseNum1 to set
	 */
	public void setBillHouseNum1(String iBillHouseNum1) {
		billHouseNum1 = iBillHouseNum1;
	}

	/**
	 * @return the iBillState
	 */
	public String getBillState() {
		return billState;
	}

	/**
	 * @param iBillState the iBillState to set
	 */
	public void setBillState(String iBillState) {
		billState = iBillState;
	}

	/**
	 * @return the iBillStreet
	 */
	public String getBillStreet() {
		return billStreet;
	}

	/**
	 * @param iBillStreet the iBillStreet to set
	 */
	public void setBillStreet(String iBillStreet) {
		billStreet = iBillStreet;
	}

	/**
	 * @return the iBillZip
	 */
	public String getBillZip() {
		return billZip;
	}

	/**
	 * @param iBillZip the iBillZip to set
	 */
	public void setBillZip(String iBillZip) {
		billZip = iBillZip;
	}

	/**
	 * @return the iEbill
	 */
	public String getEbill() {
		return ebill;
	}

	/**
	 * @param iEbill the iEbill to set
	 */
	public void setEbill(String iEbill) {
		ebill = iEbill;
	}

	/**
	 * @return the iEmailAddr
	 */
	public String getEmailAddr() {
		return emailAddr;
	}

	/**
	 * @param iEmailAddr the iEmailAddr to set
	 */
	public void setEmailAddr(String iEmailAddr) {
		emailAddr = iEmailAddr;
	}

	/**
	 * @return the iFirstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param iFirstName the iFirstName to set
	 */
	public void setFirstName(String iFirstName) {
		firstName = iFirstName;
	}

	/**
	 * @return the iFullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param iFullName the iFullName to set
	 */
	public void setFullName(String iFullName) {
		fullName = iFullName;
	}

	/**
	 * @return the iLastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param iLastName the iLastName to set
	 */
	public void setLastName(String iLastName) {
		lastName = iLastName;
	}

	/**
	 * @return the iPhoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param iPhoneNumber the iPhoneNumber to set
	 */
	public void setPhoneNumber(String iPhoneNumber) {
		phoneNumber = iPhoneNumber;
	}

	/**
	 * @return the iPlanId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param iPlanId the iPlanId to set
	 */
	public void setPlanId(String iPlanId) {
		planId = iPlanId;
	}

	/**
	 * @return the iPlanName
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param iPlanName the iPlanName to set
	 */
	public void setPlanName(String iPlanName) {
		planName = iPlanName;
	}

	/**
	 * @return the iSrvcCity
	 */
	public String getSrvcCity() {
		return srvcCity;
	}

	/**
	 * @param iSrvcCity the iSrvcCity to set
	 */
	public void setSrvcCity(String iSrvcCity) {
		srvcCity = iSrvcCity;
	}

	/**
	 * @return the iSrvcCountry
	 */
	public String getSrvcCountry() {
		return srvcCountry;
	}

	/**
	 * @param iSrvcCountry the iSrvcCountry to set
	 */
	public void setSrvcCountry(String iSrvcCountry) {
		srvcCountry = iSrvcCountry;
	}

	/**
	 * @return the iSrvcDistrict
	 */
	public String getSrvcDistrict() {
		return srvcDistrict;
	}

	/**
	 * @param iSrvcDistrict the iSrvcDistrict to set
	 */
	public void setSrvcDistrict(String iSrvcDistrict) {
		srvcDistrict = iSrvcDistrict;
	}

	/**
	 * @return the iSrvcHouseNum1
	 */
	public String getSrvcHouseNum1() {
		return srvcHouseNum1;
	}

	/**
	 * @param iSrvcHouseNum1 the iSrvcHouseNum1 to set
	 */
	public void setSrvcHouseNum1(String iSrvcHouseNum1) {
		srvcHouseNum1 = iSrvcHouseNum1;
	}

	/**
	 * @return the iSrvcState
	 */
	public String getSrvcState() {
		return srvcState;
	}

	/**
	 * @param iSrvcState the iSrvcState to set
	 */
	public void setSrvcState(String iSrvcState) {
		srvcState = iSrvcState;
	}

	/**
	 * @return the iSrvcStreet
	 */
	public String getSrvcStreet() {
		return srvcStreet;
	}

	/**
	 * @param iSrvcStreet the iSrvcStreet to set
	 */
	public void setSrvcStreet(String iSrvcStreet) {
		srvcStreet = iSrvcStreet;
	}

	/**
	 * @return the iSrvcZip
	 */
	public String getSrvcZip() {
		return srvcZip;
	}

	/**
	 * @param iSrvcZip the iSrvcZip to set
	 */
	public void setSrvcZip(String iSrvcZip) {
		srvcZip = iSrvcZip;
	}

	/**
	 * @return the iUtility
	 */
	public String getUtility() {
		return utility;
	}

	/**
	 * @param iUtility the iUtility to set
	 */
	public void setUtility(String iUtility) {
		utility = iUtility;
	}
	
	@Override
	public String toString() {
		return super.toString() + CommonUtil.doRender(this);
	}

	
}
