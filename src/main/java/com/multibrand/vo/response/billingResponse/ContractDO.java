package com.multibrand.vo.response.billingResponse;

import java.util.Date;

import org.apache.axis.utils.StringUtils;

import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;



public class ContractDO {

	private OfferDO currentPlan;
	
	private AddressDO serviceAddressDO;

    private java.lang.String strMeterType ="";
    
    private java.lang.String strMeterNumber ="";
    
    private java.lang.String strContractLegacyAccount = "";
    
    private java.lang.String strAvgPrice;
    
    private java.lang.String strCancelFee;

    private java.lang.String strContractEndDate;

    private java.lang.String strContractID;

    private java.lang.String strContractStartDate;

    private java.lang.String strESIID;

    private java.lang.String strGuardLightFlag;

    private java.lang.String strMoveInDate;

    private java.lang.String strMoveOutDate;
    
    private java.lang.String eflDocID="";
    
    private java.lang.String eflSmartCode="";
    
    private java.lang.String tosDocID="";
    
    private java.lang.String tosSmartCode="";
    
    private java.lang.String yraacDocID="";
    
    private java.lang.String yraacSmartCode="";
    
    private java.lang.String strEflUrl="";
    
    private String strTouFlag;
    
    private String strSourceMoveOutDate; 
    
 	public String getStrTouFlag() {
		return strTouFlag;
	}

	public void setStrTouFlag(String strTouFlag) {
		this.strTouFlag = strTouFlag;
	}

	public java.lang.String getEflDocID() {
		return eflDocID;
	}

	public void setEflDocID(java.lang.String eflDocID) {
		this.eflDocID = eflDocID;
	}

	public java.lang.String getEflSmartCode() {
		return eflSmartCode;
	}

	public void setEflSmartCode(java.lang.String eflSmartCode) {
		this.eflSmartCode = eflSmartCode;
	}

	public java.lang.String getTosDocID() {
		return tosDocID;
	}

	public void setTosDocID(java.lang.String tosDocID) {
		this.tosDocID = tosDocID;
	}

	public java.lang.String getTosSmartCode() {
		return tosSmartCode;
	}

	public void setTosSmartCode(java.lang.String tosSmartCode) {
		this.tosSmartCode = tosSmartCode;
	}

	public java.lang.String getYraacDocID() {
		return yraacDocID;
	}

	public void setYraacDocID(java.lang.String yraacDocID) {
		this.yraacDocID = yraacDocID;
	}

	public java.lang.String getYraacSmartCode() {
		return yraacSmartCode;
	}

	public void setYraacSmartCode(java.lang.String yraacSmartCode) {
		this.yraacSmartCode = yraacSmartCode;
	}

	public AddressDO getServiceAddressDO() {
		return serviceAddressDO;
	}

	public void setServiceAddressDO(AddressDO serviceAddressDO) {
		this.serviceAddressDO = serviceAddressDO;
	}

	public java.lang.String getStrAvgPrice() {
		return strAvgPrice;
	}

	public void setStrAvgPrice(java.lang.String strAvgPrice) {
		this.strAvgPrice = strAvgPrice;
	}

	public java.lang.String getStrCancelFee() {
		return strCancelFee;
	}

	public void setStrCancelFee(java.lang.String strCancelFee) {
		this.strCancelFee = strCancelFee;
	}

	public java.lang.String getStrContractEndDate() {
		if ( DateUtil.matches(Constants.DATE_PATTERN_YYYY_MM_DD,strContractEndDate)) {
			return CommonUtil.changeDateFormat(strContractEndDate);
		} else {
			return strContractEndDate;
		}
	}

	public void setStrContractEndDate(java.lang.String strContractEndDate) {
		this.strContractEndDate = strContractEndDate;
	}

	public java.lang.String getStrContractID() {
		return strContractID;
	}

	public void setStrContractID(java.lang.String strContractID) {
		this.strContractID = strContractID;
	}

	public java.lang.String getStrContractStartDate() {
		if ( DateUtil.matches(Constants.DATE_PATTERN_YYYY_MM_DD,strContractStartDate)) {
			return CommonUtil.changeDateFormat(strContractStartDate);
		} else {
			return strContractStartDate;
		}
	}

	public OfferDO getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(OfferDO currentPlan) {
		this.currentPlan = currentPlan;
	}

	public void setStrContractStartDate(java.lang.String strContractStartDate) {
		this.strContractStartDate = strContractStartDate;
	}

	public java.lang.String getStrESIID() {
		return strESIID;
	}

	public void setStrESIID(java.lang.String strESIID) {
		this.strESIID = strESIID;
	}

	public java.lang.String getStrGuardLightFlag() {
		return strGuardLightFlag;
	}

	public void setStrGuardLightFlag(java.lang.String strGuardLightFlag) {
		this.strGuardLightFlag = strGuardLightFlag;
	}

	public java.lang.String getStrMoveInDate() {
		if ( DateUtil.matches(Constants.DATE_PATTERN_YYYY_MM_DD,strMoveInDate)) {
			return CommonUtil.changeDateFormat(strMoveInDate);
		} else {
			return strMoveInDate;
		}
	}

	public void setStrMoveInDate(java.lang.String strMoveInDate) {
		this.strMoveInDate = strMoveInDate;
	}

	public java.lang.String getStrMoveOutDate() {
		if ( DateUtil.matches(Constants.DATE_PATTERN_YYYY_MM_DD,strMoveOutDate)) {
			return CommonUtil.changeDateFormat(strMoveOutDate);
		} else {
			return strMoveOutDate;
		}
	}


	public void setStrMoveOutDate(java.lang.String strMoveOutDate) {
		this.strMoveOutDate = strMoveOutDate;
		setStrSourceMoveOutDate(strMoveOutDate);
	}

	/**
	 * @return the strMeterType
	 */
	public java.lang.String getStrMeterType()
	{
		return strMeterType;
	}

	/**
	 * @param strMeterType the strMeterType to set
	 */
	public void setStrMeterType(java.lang.String strMeterType)
	{
		this.strMeterType = strMeterType;
	}

	/**
	 * @return the strMeterNumber
	 */
	public java.lang.String getStrMeterNumber()
	{
		return strMeterNumber;
	}

	/**
	 * @param strMeterNumber the strMeterNumber to set
	 */
	public void setStrMeterNumber(java.lang.String strMeterNumber)
	{
		this.strMeterNumber = strMeterNumber;
	}
	
	/**
	 * @return the strContractLegacyAccount
	 */
	public java.lang.String getStrContractLegacyAccount() {
		return strContractLegacyAccount;
	}

	/**
	 * @param strContractLegacyAccount the strContractLegacyAccount to set
	 */
	public void setStrContractLegacyAccount(
			java.lang.String strContractLegacyAccount) {
		this.strContractLegacyAccount = strContractLegacyAccount;
	}

	/**
	 * @return the strSourceMoveOutDate
	 */
	public Date getStrSourceMoveOutDate() {
		if(!StringUtils.isEmpty(strSourceMoveOutDate) && strSourceMoveOutDate.indexOf("-")>-1){
			return DateUtil.getDate(strSourceMoveOutDate,"yyyy-MM-dd");
		}
		return null;
	}

	/**
	 * @param strSourceMoveOutDate the strSourceMoveOutDate to set
	 */
	public void setStrSourceMoveOutDate(String strSourceMoveOutDate) {
		this.strSourceMoveOutDate = strSourceMoveOutDate;
	}

	public java.lang.String getStrEflUrl() {
		return strEflUrl;
	}

	public void setStrEflUrl(java.lang.String strEflUrl) {
		this.strEflUrl = strEflUrl;
	}

	
    
}
