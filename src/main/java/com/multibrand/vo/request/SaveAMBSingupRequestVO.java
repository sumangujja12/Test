package com.multibrand.vo.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.multibrand.dto.request.FormEntityRequest;
import com.multibrand.util.Constants;

public class SaveAMBSingupRequestVO  implements FormEntityRequest, Constants,
Serializable
{
	
	private static final long serialVersionUID = 9164113061059935719L;
	private String bpNumber;
	private String accountNumber;
	private String checkDigit;
	private String contractId;
	private String languageCode;
	private String esid;
	private String servStreetNum;
	private String servStreetName;
	private String servStreetAptNum;
	private String servCity;
	private String servState;
	private String servZipCode;
	private String billStreetNum;
	private String billStreetName;
	private String billStreetAptNum;
	private String billAddrPOBox;
	private String billCity;
	private String billState;
	private String billZipCode;
	private String ambAmount;  
	private String toEmail;
	private String companyCode;
	private String retroFlag;
	private String amtAdjust;
	private String amtFinal;
	private String bbpBasis;
	private String billAllocDate;
	private String estSign;
	private String invoice;
	private String resStatus;
	private String brandName;
	
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the esid
	 */
	public String getEsid()
	{
		return esid;
	}
	/**
	 * @param esid the esid to set
	 */
	public void setEsid(String esid)
	{
		this.esid = esid;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode()
	{
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber()
	{
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the checkDigit
	 */
	public String getCheckDigit()
	{
		return checkDigit;
	}
	/**
	 * @param checkDigit the checkDigit to set
	 */
	public void setCheckDigit(String checkDigit)
	{
		this.checkDigit = checkDigit;
	}
	/**
	 * @return the bpNumber
	 */
	public String getBpNumber()
	{
		return bpNumber;
	}
	/**
	 * @param bpNumber the bpNumber to set
	 */
	public void setBpNumber(String bpNumber)
	{
		this.bpNumber = bpNumber;
	}
	
	/**
	 * @return the servStreetNum
	 */
	public String getServStreetNum()
	{
		return servStreetNum;
	}
	/**
	 * @param servStreetNum the servStreetNum to set
	 */
	public void setServStreetNum(String servStreetNum)
	{
		this.servStreetNum = servStreetNum;
	}
	/**
	 * @return the servStreetName
	 */
	public String getServStreetName()
	{
		return servStreetName;
	}
	/**
	 * @param servStreetName the servStreetName to set
	 */
	public void setServStreetName(String servStreetName)
	{
		this.servStreetName = servStreetName;
	}
	/**
	 * @return the servStreetAptNum
	 */
	public String getServStreetAptNum()
	{
		return servStreetAptNum;
	}
	/**
	 * @param servStreetAptNum the servStreetAptNum to set
	 */
	public void setServStreetAptNum(String servStreetAptNum)
	{
		this.servStreetAptNum = servStreetAptNum;
	}
	/**
	 * @return the servCity
	 */
	public String getServCity()
	{
		return servCity;
	}
	/**
	 * @param servCity the servCity to set
	 */
	public void setServCity(String servCity)
	{
		this.servCity = servCity;
	}
	/**
	 * @return the servState
	 */
	public String getServState()
	{
		return servState;
	}
	/**
	 * @param servState the servState to set
	 */
	public void setServState(String servState)
	{
		this.servState = servState;
	}
	/**
	 * @return the servZipCode
	 */
	public String getServZipCode()
	{
		return servZipCode;
	}
	/**
	 * @param servZipCode the servZipCode to set
	 */
	public void setServZipCode(String servZipCode)
	{
		this.servZipCode = servZipCode;
	}
	/**
	 * @return the billStreetNum
	 */
	public String getBillStreetNum()
	{
		return billStreetNum;
	}
	/**
	 * @param billStreetNum the billStreetNum to set
	 */
	public void setBillStreetNum(String billStreetNum)
	{
		this.billStreetNum = billStreetNum;
	}
	/**
	 * @return the billStreetName
	 */
	public String getBillStreetName()
	{
		return billStreetName;
	}
	/**
	 * @param billStreetName the billStreetName to set
	 */
	public void setBillStreetName(String billStreetName)
	{
		this.billStreetName = billStreetName;
	}
	/**
	 * @return the billStreetAptNum
	 */
	public String getBillStreetAptNum()
	{
		return billStreetAptNum;
	}
	/**
	 * @param billStreetAptNum the billStreetAptNum to set
	 */
	public void setBillStreetAptNum(String billStreetAptNum)
	{
		this.billStreetAptNum = billStreetAptNum;
	}
	/**
	 * @return the billAddrPOBox
	 */
	public String getBillAddrPOBox()
	{
		return billAddrPOBox;
	}
	/**
	 * @param billAddrPOBox the billAddrPOBox to set
	 */
	public void setBillAddrPOBox(String billAddrPOBox)
	{
		this.billAddrPOBox = billAddrPOBox;
	}
	/**
	 * @return the billCity
	 */
	public String getBillCity()
	{
		return billCity;
	}
	/**
	 * @param billCity the billCity to set
	 */
	public void setBillCity(String billCity)
	{
		this.billCity = billCity;
	}
	/**
	 * @return the billState
	 */
	public String getBillState()
	{
		return billState;
	}
	/**
	 * @param billState the billState to set
	 */
	public void setBillState(String billState)
	{
		this.billState = billState;
	}
	/**
	 * @return the billZipCode
	 */
	public String getBillZipCode()
	{
		return billZipCode;
	}
	/**
	 * @param billZipCode the billZipCode to set
	 */
	public void setBillZipCode(String billZipCode)
	{
		this.billZipCode = billZipCode;
	}
	/**
	 * @return the ambAmount
	 */
	public String getAmbAmount()
	{
		return ambAmount;
	}
	/**
	 * @param ambAmount the ambAmount to set
	 */
	public void setAmbAmount(String ambAmount)
	{
		this.ambAmount = ambAmount;
	}
	/**
	 * @return the languageCode
	 */
	public String getLanguageCode()
	{
		return languageCode;
	}
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode)
	{
		this.languageCode = languageCode;
	}
	public String getRetroFlag() {
		return retroFlag;
	}
	public void setRetroFlag(String retroFlag) {
		this.retroFlag = retroFlag;
	}
	public String getAmtAdjust() {
		return amtAdjust;
	}
	public void setAmtAdjust(String amtAdjust) {
		this.amtAdjust = amtAdjust;
	}
	
	
	public String getAmtFinal() {
		return amtFinal;
	}
	public void setAmtFinal(String amtFinal) {
		this.amtFinal = amtFinal;
	}
	public String getBbpBasis() {
		return bbpBasis;
	}
	public void setBbpBasis(String bbpBasis) {
		this.bbpBasis = bbpBasis;
	}
	public String getBillAllocDate() {
		return billAllocDate;
	}
	public void setBillAllocDate(String billAllocDate) {
		this.billAllocDate = billAllocDate;
	}
	public String getEstSign() {
		return estSign;
	}
	public void setEstSign(String estSign) {
		this.estSign = estSign;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getResStatus() {
		return resStatus;
	}
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	/**
	 * @return String representation of this request.
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
		
	
}
