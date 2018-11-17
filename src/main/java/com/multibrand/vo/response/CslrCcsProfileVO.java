package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="cslrProfile")
public class CslrCcsProfileVO {

	@SerializedName("LeaseId")
	private String leaseId;

	@SerializedName("Partner")
	private String bpNumber;

	@SerializedName("ContractAcct")
	private String caNumber;

	@SerializedName("IdType")
	private String ccsResponseIdType;

	@SerializedName("LeaseStatus")
	private String leaseStatus;

	@SerializedName("ArDate")
	private String arDate;

	@SerializedName("CreditAmount")
	private String creditAmount;

	@SerializedName("BillAmount")
	private String billAmount;

	@SerializedName("BillDueDt")
	private String billDueDt;

	@SerializedName("PastDueAmt")
	private String pastDueAmt;

	@SerializedName("LastPaidAmt")
	private String lastPaidAmt;

	@SerializedName("LastPaymentDt")
	private String lastPaymentDt;

	@SerializedName("InvoiceNo")
	private String invoiceNo;

	@SerializedName("AutopayStatus")
	private String autopayStatus;

	@SerializedName("AutopaySchDate")
	private String autopaySchDate;

	@SerializedName("AutopayBankAc")
	private String autopayBankAc;

	@SerializedName("AutopayCcExpdt")
	private String autopayCcExpdt;
	
	@SerializedName("AutopayCcNo")
	private String autopayCcNo;

	@SerializedName("AutopayCcType")
	private String autopayCcType;

	@SerializedName("AutopayBankElg")
	private String autopayBankElg;

	@SerializedName("AutopayCcElg")
	private String autopayCcElg;
	
	@SerializedName("AutopayFlg")
	private String autopayFlg;

	@SerializedName("ApartmentNo")
	private String apartmentNo;

	@SerializedName("PoBox")
	private String poBox;

	@SerializedName("City")
	private String city;

	@SerializedName("State")
	private String state;

	@SerializedName("StreetName")
	private String streetName;

	@SerializedName("StreetNo")
	private String streetNo;

	@SerializedName("Country")
	private String country;

	@SerializedName("ZipCode")
	private String zipCode;

	@SerializedName("CoCode")
	private String coCode;

	@SerializedName("ErrorCode")
	private String errorCode;

	@SerializedName("ErrorMessage")
	private String errorMessage;

	/**
	 * @return the leaseId
	 */
	public String getLeaseId() {
		return leaseId;
	}

	/**
	 * @param leaseId
	 *            the leaseId to set
	 */
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}

	/**
	 * @return the bpNumber
	 */
	public String getBpNumber() {
		return bpNumber;
	}

	/**
	 * @param bpNumber
	 *            the bpNumber to set
	 */
	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}

	/**
	 * @return the caNumber
	 */
	public String getCaNumber() {
		return caNumber;
	}

	/**
	 * @param caNumber
	 *            the caNumber to set
	 */
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	/**
	 * @return the ccsResponseIdType
	 */
	public String getCcsResponseIdType() {
		return ccsResponseIdType;
	}

	/**
	 * @param ccsResponseIdType
	 *            the ccsResponseIdType to set
	 */
	public void setCcsResponseIdType(String ccsResponseIdType) {
		this.ccsResponseIdType = ccsResponseIdType;
	}

	/**
	 * @return the leaseStatus
	 */
	public String getLeaseStatus() {
		return leaseStatus;
	}

	/**
	 * @param leaseStatus
	 *            the leaseStatus to set
	 */
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

	/**
	 * @return the arDate
	 */
	public String getArDate() {
		return arDate;
	}

	/**
	 * @param arDate
	 *            the arDate to set
	 */
	public void setArDate(String arDate) {
		this.arDate = arDate;
	}

	/**
	 * @return the creditAmount
	 */
	public String getCreditAmount() {
		if(StringUtils.isNotEmpty(creditAmount)) {
			return creditAmount.trim();
		}
		return creditAmount;
	}

	/**
	 * @param creditAmount
	 *            the creditAmount to set
	 */
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * @return the billAmount
	 */
	public String getBillAmount() {
		if(StringUtils.isNotEmpty(billAmount)) {
			return billAmount.trim();
		}
		return billAmount;
	}

	/**
	 * @param billAmount
	 *            the billAmount to set
	 */
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * @return the billDueDt
	 */
	public String getBillDueDt() {
		return billDueDt;
	}

	/**
	 * @param billDueDt
	 *            the billDueDt to set
	 */
	public void setBillDueDt(String billDueDt) {
		this.billDueDt = billDueDt;
	}

	/**
	 * @return the pastDueAmt
	 */
	public String getPastDueAmt() {
		if(StringUtils.isNotEmpty(pastDueAmt)) {
			return pastDueAmt.trim();
		}
		return pastDueAmt;
	}

	/**
	 * @param pastDueAmt
	 *            the pastDueAmt to set
	 */
	public void setPastDueAmt(String pastDueAmt) {
		this.pastDueAmt = pastDueAmt;
	}

	/**
	 * @return the lastPaidAmt
	 */
	public String getLastPaidAmt() {
		if(StringUtils.isNotEmpty(lastPaidAmt)) {
			return lastPaidAmt.trim();
		}
		return lastPaidAmt;
	}

	/**
	 * @param lastPaidAmt
	 *            the lastPaidAmt to set
	 */
	public void setLastPaidAmt(String lastPaidAmt) {
		this.lastPaidAmt = lastPaidAmt;
	}

	/**
	 * @return the lastPaymentDt
	 */
	public String getLastPaymentDt() {
		return lastPaymentDt;
	}

	/**
	 * @param lastPaymentDt
	 *            the lastPaymentDt to set
	 */
	public void setLastPaymentDt(String lastPaymentDt) {
		this.lastPaymentDt = lastPaymentDt;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the autopayStatus
	 */
	public String getAutopayStatus() {
		return autopayStatus;
	}

	/**
	 * @param autopayStatus
	 *            the autopayStatus to set
	 */
	public void setAutopayStatus(String autopayStatus) {
		this.autopayStatus = autopayStatus;
	}

	/**
	 * @return the autopaySchDate
	 */
	public String getAutopaySchDate() {
		return autopaySchDate;
	}

	/**
	 * @param autopaySchDate
	 *            the autopaySchDate to set
	 */
	public void setAutopaySchDate(String autopaySchDate) {
		this.autopaySchDate = autopaySchDate;
	}

	/**
	 * @return the autopayBankAc
	 */
	public String getAutopayBankAc() {
		return autopayBankAc;
	}

	/**
	 * @param autopayBankAc
	 *            the autopayBankAc to set
	 */
	public void setAutopayBankAc(String autopayBankAc) {
		this.autopayBankAc = autopayBankAc;
	}

	/**
	 * @return the autopayCcExpdt
	 */
	public String getAutopayCcExpdt() {
		return autopayCcExpdt;
	}

	/**
	 * @param autopayCcExpdt
	 *            the autopayCcExpdt to set
	 */
	public void setAutopayCcExpdt(String autopayCcExpdt) {
		this.autopayCcExpdt = autopayCcExpdt;
	}

	/**
	 * @return the autopayCcType
	 */
	public String getAutopayCcType() {
		return autopayCcType;
	}

	/**
	 * @param autopayCcType
	 *            the autopayCcType to set
	 */
	public void setAutopayCcType(String autopayCcType) {
		this.autopayCcType = autopayCcType;
	}

	/**
	 * @return the autopayBankElg
	 */
	public String getAutopayBankElg() {
		return autopayBankElg;
	}

	/**
	 * @param autopayBankElg
	 *            the autopayBankElg to set
	 */
	public void setAutopayBankElg(String autopayBankElg) {
		this.autopayBankElg = autopayBankElg;
	}

	/**
	 * @return the autopayFlg
	 */
	public String getAutopayFlg() {
		return autopayFlg;
	}

	/**
	 * @param autopayFlg
	 *            the autopayFlg to set
	 */
	public void setAutopayFlg(String autopayFlg) {
		this.autopayFlg = autopayFlg;
	}

	/**
	 * @return the apartmentNo
	 */
	public String getApartmentNo() {
		return apartmentNo;
	}

	/**
	 * @param apartmentNo
	 *            the apartmentNo to set
	 */
	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}

	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}

	/**
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetNo
	 */
	public String getStreetNo() {
		return streetNo;
	}

	/**
	 * @param streetNo
	 *            the streetNo to set
	 */
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the coCode
	 */
	public String getCoCode() {
		return coCode;
	}

	/**
	 * @param coCode
	 *            the coCode to set
	 */
	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		if(StringUtils.isEmpty(errorCode)) {
			return "00";
		}
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the autopayCcNo
	 */
	public String getAutopayCcNo() {
		return autopayCcNo;
	}

	/**
	 * @param autopayCcNo the autopayCcNo to set
	 */
	public void setAutopayCcNo(String autopayCcNo) {
		this.autopayCcNo = autopayCcNo;
	}

	/**
	 * @return the autopayCcElg
	 */
	public String getAutopayCcElg() {
		return autopayCcElg;
	}

	/**
	 * @param autopayCcElg the autopayCcElg to set
	 */
	public void setAutopayCcElg(String autopayCcElg) {
		this.autopayCcElg = autopayCcElg;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		
		if(StringUtils.isEmpty(this.zipCode)){
			this.zipCode ="";
		}else if(zipCode.length()>=5){
			this.zipCode = this.zipCode.substring(0, 5);
		}
		
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
}
