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
	private String IAcctNumber;
	
	private String IAutopay;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillCity;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillCountry;
	
	private String IBillDistrict;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillHouseNum1;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillState;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillStreet;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IBillZip;
	
	
	private String IEbill;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IEmailAddr;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IFirstName;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IFullName;
	
	@NotBlank(groups = BasicConstraint.class)
	private String ILastName;
	
	
	private String IPhoneNumber;
	
	
	@NotBlank(groups = BasicConstraint.class)
	@Length(max = 10, groups = SizeConstraint.class)
	private String IPlanId;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IPlanName;
	
	
	private String ISrvcCity;
	private String ISrvcCountry;
	private String ISrvcDistrict;
	private String ISrvcHouseNum1;
	private String ISrvcState;
	private String ISrvcStreet;
	private String ISrvcZip;
	
	@NotBlank(groups = BasicConstraint.class)
	private String IUtility;

	/**
	 * @return the iAcctNumber
	 */
	public String getIAcctNumber() {
		return IAcctNumber;
	}

	/**
	 * @param iAcctNumber the iAcctNumber to set
	 */
	public void setIAcctNumber(String iAcctNumber) {
		IAcctNumber = iAcctNumber;
	}

	/**
	 * @return the iAutopay
	 */
	public String getIAutopay() {
		return IAutopay;
	}

	/**
	 * @param iAutopay the iAutopay to set
	 */
	public void setIAutopay(String iAutopay) {
		IAutopay = iAutopay;
	}

	/**
	 * @return the iBillCity
	 */
	public String getIBillCity() {
		return IBillCity;
	}

	/**
	 * @param iBillCity the iBillCity to set
	 */
	public void setIBillCity(String iBillCity) {
		IBillCity = iBillCity;
	}

	/**
	 * @return the iBillCountry
	 */
	public String getIBillCountry() {
		return IBillCountry;
	}

	/**
	 * @param iBillCountry the iBillCountry to set
	 */
	public void setIBillCountry(String iBillCountry) {
		IBillCountry = iBillCountry;
	}

	/**
	 * @return the iBillDistrict
	 */
	public String getIBillDistrict() {
		return IBillDistrict;
	}

	/**
	 * @param iBillDistrict the iBillDistrict to set
	 */
	public void setIBillDistrict(String iBillDistrict) {
		IBillDistrict = iBillDistrict;
	}

	/**
	 * @return the iBillHouseNum1
	 */
	public String getIBillHouseNum1() {
		return IBillHouseNum1;
	}

	/**
	 * @param iBillHouseNum1 the iBillHouseNum1 to set
	 */
	public void setIBillHouseNum1(String iBillHouseNum1) {
		IBillHouseNum1 = iBillHouseNum1;
	}

	/**
	 * @return the iBillState
	 */
	public String getIBillState() {
		return IBillState;
	}

	/**
	 * @param iBillState the iBillState to set
	 */
	public void setIBillState(String iBillState) {
		IBillState = iBillState;
	}

	/**
	 * @return the iBillStreet
	 */
	public String getIBillStreet() {
		return IBillStreet;
	}

	/**
	 * @param iBillStreet the iBillStreet to set
	 */
	public void setIBillStreet(String iBillStreet) {
		IBillStreet = iBillStreet;
	}

	/**
	 * @return the iBillZip
	 */
	public String getIBillZip() {
		return IBillZip;
	}

	/**
	 * @param iBillZip the iBillZip to set
	 */
	public void setIBillZip(String iBillZip) {
		IBillZip = iBillZip;
	}

	/**
	 * @return the iEbill
	 */
	public String getIEbill() {
		return IEbill;
	}

	/**
	 * @param iEbill the iEbill to set
	 */
	public void setIEbill(String iEbill) {
		IEbill = iEbill;
	}

	/**
	 * @return the iEmailAddr
	 */
	public String getIEmailAddr() {
		return IEmailAddr;
	}

	/**
	 * @param iEmailAddr the iEmailAddr to set
	 */
	public void setIEmailAddr(String iEmailAddr) {
		IEmailAddr = iEmailAddr;
	}

	/**
	 * @return the iFirstName
	 */
	public String getIFirstName() {
		return IFirstName;
	}

	/**
	 * @param iFirstName the iFirstName to set
	 */
	public void setIFirstName(String iFirstName) {
		IFirstName = iFirstName;
	}

	/**
	 * @return the iFullName
	 */
	public String getIFullName() {
		return IFullName;
	}

	/**
	 * @param iFullName the iFullName to set
	 */
	public void setIFullName(String iFullName) {
		IFullName = iFullName;
	}

	/**
	 * @return the iLastName
	 */
	public String getILastName() {
		return ILastName;
	}

	/**
	 * @param iLastName the iLastName to set
	 */
	public void setILastName(String iLastName) {
		ILastName = iLastName;
	}

	/**
	 * @return the iPhoneNumber
	 */
	public String getIPhoneNumber() {
		return IPhoneNumber;
	}

	/**
	 * @param iPhoneNumber the iPhoneNumber to set
	 */
	public void setIPhoneNumber(String iPhoneNumber) {
		IPhoneNumber = iPhoneNumber;
	}

	/**
	 * @return the iPlanId
	 */
	public String getIPlanId() {
		return IPlanId;
	}

	/**
	 * @param iPlanId the iPlanId to set
	 */
	public void setIPlanId(String iPlanId) {
		IPlanId = iPlanId;
	}

	/**
	 * @return the iPlanName
	 */
	public String getIPlanName() {
		return IPlanName;
	}

	/**
	 * @param iPlanName the iPlanName to set
	 */
	public void setIPlanName(String iPlanName) {
		IPlanName = iPlanName;
	}

	/**
	 * @return the iSrvcCity
	 */
	public String getISrvcCity() {
		return ISrvcCity;
	}

	/**
	 * @param iSrvcCity the iSrvcCity to set
	 */
	public void setISrvcCity(String iSrvcCity) {
		ISrvcCity = iSrvcCity;
	}

	/**
	 * @return the iSrvcCountry
	 */
	public String getISrvcCountry() {
		return ISrvcCountry;
	}

	/**
	 * @param iSrvcCountry the iSrvcCountry to set
	 */
	public void setISrvcCountry(String iSrvcCountry) {
		ISrvcCountry = iSrvcCountry;
	}

	/**
	 * @return the iSrvcDistrict
	 */
	public String getISrvcDistrict() {
		return ISrvcDistrict;
	}

	/**
	 * @param iSrvcDistrict the iSrvcDistrict to set
	 */
	public void setISrvcDistrict(String iSrvcDistrict) {
		ISrvcDistrict = iSrvcDistrict;
	}

	/**
	 * @return the iSrvcHouseNum1
	 */
	public String getISrvcHouseNum1() {
		return ISrvcHouseNum1;
	}

	/**
	 * @param iSrvcHouseNum1 the iSrvcHouseNum1 to set
	 */
	public void setISrvcHouseNum1(String iSrvcHouseNum1) {
		ISrvcHouseNum1 = iSrvcHouseNum1;
	}

	/**
	 * @return the iSrvcState
	 */
	public String getISrvcState() {
		return ISrvcState;
	}

	/**
	 * @param iSrvcState the iSrvcState to set
	 */
	public void setISrvcState(String iSrvcState) {
		ISrvcState = iSrvcState;
	}

	/**
	 * @return the iSrvcStreet
	 */
	public String getISrvcStreet() {
		return ISrvcStreet;
	}

	/**
	 * @param iSrvcStreet the iSrvcStreet to set
	 */
	public void setISrvcStreet(String iSrvcStreet) {
		ISrvcStreet = iSrvcStreet;
	}

	/**
	 * @return the iSrvcZip
	 */
	public String getISrvcZip() {
		return ISrvcZip;
	}

	/**
	 * @param iSrvcZip the iSrvcZip to set
	 */
	public void setISrvcZip(String iSrvcZip) {
		ISrvcZip = iSrvcZip;
	}

	/**
	 * @return the iUtility
	 */
	public String getIUtility() {
		return IUtility;
	}

	/**
	 * @param iUtility the iUtility to set
	 */
	public void setIUtility(String iUtility) {
		IUtility = iUtility;
	}
	
	@Override
	public String toString() {
		// return ReflectionToStringBuilder.toString(this);
		return super.toString() + CommonUtil.doRender(this);
	}

	
}
