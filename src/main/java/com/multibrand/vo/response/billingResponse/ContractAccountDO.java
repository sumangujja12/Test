package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlElement;

import com.multibrand.util.CommonUtil;



public class ContractAccountDO {
	
	private java.lang.String CAName;

    private ContractDO[] listOfContracts;
    
    private AddressDO billingAddressDO;

    private java.lang.String strAPBankAccNum;

    private java.lang.String strAPCCExpDate;

    private java.lang.String strAPCCNum;

    private java.lang.String strAPCCType;

    private java.lang.String strAutoBankEli;

    private java.lang.String strAutoCCElig;

    private java.lang.String strAutoPayFlag;

    private java.lang.String strAvgBillFlag;

    private java.lang.String strAvlBillFlag;

    private java.lang.String strBPNumber;

    private java.lang.String strBalBillFlag;

    private java.lang.String strBrandName;

    private java.lang.String strCANumber;

    private java.lang.String strCharityId;

    private java.lang.String strCharityName;

    private java.lang.String strCheckDigit;

    private java.lang.String strCompany;

    private java.lang.String strCurrentInvoiceID;

    private java.lang.String strCustSegment;

    private java.lang.String strDNPFlag;

    private java.lang.String strDisconnectAmt;

    private java.lang.String strDisconnectDate;

    private java.lang.String strEbillFlag;

    private java.lang.String strEnrollSource;

    private java.lang.String strLanguageCode;

    private java.lang.String strMultiContractFlag;

    private java.lang.String strNCAStatus;

    private java.lang.String strNCCAStatus;

    private java.lang.String strPaperFlag;

    private java.lang.String strPaymentType;
    
    //START : OAM :KD	
  	private String strLegacyAccount;
  	private String strConversionDate;
  	private String strExFirstName;
  	private String strExLastName;
  	
  	
  	@XmlElement(name="legacyAccountNumber")
  	public String getStrLegacyAccount() {
  		return strLegacyAccount;
  	}
  	public void setStrLegacyAccount(String strLegacyAccount) {
  		this.strLegacyAccount = strLegacyAccount;
  	}
  	
  	@XmlElement(name="accountConversionDate")
  	public String getStrConversionDate() {
  		return CommonUtil.changeDateFormat(strConversionDate);
  	}
  	public void setStrConversionDate(String strConversionDate) {
  		this.strConversionDate = strConversionDate;
  	}
  	
  	@XmlElement(name="strFirstName")
  	public String getStrExFirstName() {
  		return strExFirstName;
  	}
  	public void setStrExFirstName(String strExFirstName) {
  		this.strExFirstName = strExFirstName;
  	}
  	
  	@XmlElement(name="strLastName")
  	public String getStrExLastName() {
  		return strExLastName;
  	}
  	public void setStrExLastName(String strExLastName) {
  		this.strExLastName = strExLastName;
  	}
  	//END : OAM :KD
 

	public java.lang.String getCAName() {
		return CAName;
	}

	public void setCAName(java.lang.String cAName) {
		CAName = cAName;
	}

	public ContractDO[] getListOfContracts() {
		return listOfContracts;
	}

	public void setListOfContracts(
			ContractDO[] listOfContracts) {
		this.listOfContracts = listOfContracts;
	}

	public java.lang.String getStrAPBankAccNum() {
		return strAPBankAccNum;
	}

	public void setStrAPBankAccNum(java.lang.String strAPBankAccNum) {
		this.strAPBankAccNum = strAPBankAccNum;
	}

	public java.lang.String getStrAPCCExpDate() {
		return CommonUtil.changeDateFormat(strAPCCExpDate);
	}

	public void setStrAPCCExpDate(java.lang.String strAPCCExpDate) {
		this.strAPCCExpDate = strAPCCExpDate;
	}

	public java.lang.String getStrAPCCNum() {
		return strAPCCNum;
	}

	public void setStrAPCCNum(java.lang.String strAPCCNum) {
		this.strAPCCNum = strAPCCNum;
	}

	public java.lang.String getStrAPCCType() {
		return strAPCCType;
	}

	public void setStrAPCCType(java.lang.String strAPCCType) {
		this.strAPCCType = strAPCCType;
	}

	public java.lang.String getStrAutoBankEli() {
		return strAutoBankEli;
	}

	public void setStrAutoBankEli(java.lang.String strAutoBankEli) {
		this.strAutoBankEli = strAutoBankEli;
	}

	public java.lang.String getStrAutoCCElig() {
		return strAutoCCElig;
	}

	public void setStrAutoCCElig(java.lang.String strAutoCCElig) {
		this.strAutoCCElig = strAutoCCElig;
	}

	public java.lang.String getStrAutoPayFlag() {
		return strAutoPayFlag;
	}

	public void setStrAutoPayFlag(java.lang.String strAutoPayFlag) {
		this.strAutoPayFlag = strAutoPayFlag;
	}

	public java.lang.String getStrAvgBillFlag() {
		return strAvgBillFlag;
	}

	public void setStrAvgBillFlag(java.lang.String strAvgBillFlag) {
		this.strAvgBillFlag = strAvgBillFlag;
	}

	public java.lang.String getStrAvlBillFlag() {
		return strAvlBillFlag;
	}

	public void setStrAvlBillFlag(java.lang.String strAvlBillFlag) {
		this.strAvlBillFlag = strAvlBillFlag;
	}

	public java.lang.String getStrBPNumber() {
		return strBPNumber;
	}

	public void setStrBPNumber(java.lang.String strBPNumber) {
		this.strBPNumber = strBPNumber;
	}

	public java.lang.String getStrBalBillFlag() {
		return strBalBillFlag;
	}

	public void setStrBalBillFlag(java.lang.String strBalBillFlag) {
		this.strBalBillFlag = strBalBillFlag;
	}

	public java.lang.String getStrBrandName() {
		return strBrandName;
	}

	public void setStrBrandName(java.lang.String strBrandName) {
		this.strBrandName = strBrandName;
	}

	public java.lang.String getStrCANumber() {
		return strCANumber;
	}

	public void setStrCANumber(java.lang.String strCANumber) {
		this.strCANumber = strCANumber;
	}

	public java.lang.String getStrCharityId() {
		return strCharityId;
	}

	public void setStrCharityId(java.lang.String strCharityId) {
		this.strCharityId = strCharityId;
	}

	public java.lang.String getStrCharityName() {
		return strCharityName;
	}

	public void setStrCharityName(java.lang.String strCharityName) {
		this.strCharityName = strCharityName;
	}

	public java.lang.String getStrCheckDigit() {
		return strCheckDigit;
	}

	public void setStrCheckDigit(java.lang.String strCheckDigit) {
		this.strCheckDigit = strCheckDigit;
	}

	public java.lang.String getStrCompany() {
		return strCompany;
	}

	public void setStrCompany(java.lang.String strCompany) {
		this.strCompany = strCompany;
	}

	public java.lang.String getStrCurrentInvoiceID() {
		return strCurrentInvoiceID;
	}

	public void setStrCurrentInvoiceID(java.lang.String strCurrentInvoiceID) {
		this.strCurrentInvoiceID = strCurrentInvoiceID;
	}

	public java.lang.String getStrCustSegment() {
		return strCustSegment;
	}

	public void setStrCustSegment(java.lang.String strCustSegment) {
		this.strCustSegment = strCustSegment;
	}

	public java.lang.String getStrDNPFlag() {
		return strDNPFlag;
	}

	public void setStrDNPFlag(java.lang.String strDNPFlag) {
		this.strDNPFlag = strDNPFlag;
	}

	public java.lang.String getStrDisconnectAmt() {
		return strDisconnectAmt;
	}

	public void setStrDisconnectAmt(java.lang.String strDisconnectAmt) {
		this.strDisconnectAmt = strDisconnectAmt;
	}

	public java.lang.String getStrDisconnectDate() {
		return CommonUtil.changeDateFormat(strDisconnectDate);
	}

	public void setStrDisconnectDate(java.lang.String strDisconnectDate) {
		this.strDisconnectDate = strDisconnectDate;
	}

	public java.lang.String getStrEbillFlag() {
		return strEbillFlag;
	}

	public void setStrEbillFlag(java.lang.String strEbillFlag) {
		this.strEbillFlag = strEbillFlag;
	}

	public java.lang.String getStrEnrollSource() {
		return strEnrollSource;
	}

	public void setStrEnrollSource(java.lang.String strEnrollSource) {
		this.strEnrollSource = strEnrollSource;
	}

	public java.lang.String getStrLanguageCode() {
		return strLanguageCode;
	}

	public void setStrLanguageCode(java.lang.String strLanguageCode) {
		this.strLanguageCode = strLanguageCode;
	}

	public java.lang.String getStrMultiContractFlag() {
		return strMultiContractFlag;
	}

	public void setStrMultiContractFlag(java.lang.String strMultiContractFlag) {
		this.strMultiContractFlag = strMultiContractFlag;
	}

	public java.lang.String getStrNCAStatus() {
		return strNCAStatus;
	}

	public void setStrNCAStatus(java.lang.String strNCAStatus) {
		this.strNCAStatus = strNCAStatus;
	}

	public java.lang.String getStrNCCAStatus() {
		return strNCCAStatus;
	}

	public void setStrNCCAStatus(java.lang.String strNCCAStatus) {
		this.strNCCAStatus = strNCCAStatus;
	}

	public java.lang.String getStrPaperFlag() {
		return strPaperFlag;
	}

	public void setStrPaperFlag(java.lang.String strPaperFlag) {
		this.strPaperFlag = strPaperFlag;
	}

	public java.lang.String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(java.lang.String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}
	/**
	 * @return the billingAddressDO
	 */
	public AddressDO getBillingAddressDO()
	{
		return billingAddressDO;
	}
	/**
	 * @param billingAddressDO the billingAddressDO to set
	 */
	public void setBillingAddressDO(AddressDO billingAddressDO)
	{
		this.billingAddressDO = billingAddressDO;
	}
    
    
    
	
}
