package com.multibrand.vo.response.billingResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contractData")
public class PpdContractData {

	
	private String CITY;
	private String COUNTRY_KEY;
	private String COUNTRY_NAME;
	private String DISTRICT;
	private String FULL_NAME;
	private String HOUSE_NO;
	private byte[] PARTNER_GUID;
	private String PARTNER_NO;
	private String PAYMENT_DESC;
	private String PAYMENT_TYPE;
	private String POSTAL_CODE;
	private String PPD_CONTRACT_ACC;
	private String STATE;
	private String STATE_DESC;
	private String STREET;

	@XmlElement(name="state")
	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	@XmlElement(name="stateDescription")
	public String getSTATE_DESC() {
		return STATE_DESC;
	}

	public void setSTATE_DESC(String sTATE_DESC) {
		STATE_DESC = sTATE_DESC;
	}

	@XmlElement(name="street")
	public String getSTREET() {
		return STREET;
	}

	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}

	@XmlElement(name="city")
	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	@XmlElement(name="countryKey")
	public String getCOUNTRY_KEY() {
		return COUNTRY_KEY;
	}

	public void setCOUNTRY_KEY(String cOUNTRY_KEY) {
		COUNTRY_KEY = cOUNTRY_KEY;
	}

	@XmlElement(name="countryName")
	public String getCOUNTRY_NAME() {
		return COUNTRY_NAME;
	}

	public void setCOUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}

	@XmlElement(name="district")
	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	@XmlElement(name="fullName")
	public String getFULL_NAME() {
		return FULL_NAME;
	}

	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}

	@XmlElement(name="houseNo")
	public String getHOUSE_NO() {
		return HOUSE_NO;
	}

	public void setHOUSE_NO(String hOUSE_NO) {
		HOUSE_NO = hOUSE_NO;
	}

	@XmlElement(name="partnerGuid")
	public byte[] getPARTNER_GUID() {
		return PARTNER_GUID;
	}

	public void setPARTNER_GUID(byte[] pARTNER_GUID) {
		PARTNER_GUID = pARTNER_GUID;
	}

	@XmlElement(name="businessPartnerId")
	public String getPARTNER_NO() {
		return PARTNER_NO;
	}

	public void setPARTNER_NO(String pARTNER_NO) {
		PARTNER_NO = pARTNER_NO;
	}

	@XmlElement(name="paymentDescription")
	public String getPAYMENT_DESC() {
		return PAYMENT_DESC;
	}

	public void setPAYMENT_DESC(String pAYMENT_DESC) {
		PAYMENT_DESC = pAYMENT_DESC;
	}

	@XmlElement(name="paymentType")
	public String getPAYMENT_TYPE() {
		return PAYMENT_TYPE;
	}

	public void setPAYMENT_TYPE(String pAYMENT_TYPE) {
		PAYMENT_TYPE = pAYMENT_TYPE;
	}

	@XmlElement(name="zipcode")
	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}

	@XmlElement(name="ppdContractAccount")
	public String getPPD_CONTRACT_ACC() {
		return PPD_CONTRACT_ACC;
	}

	public void setPPD_CONTRACT_ACC(String pPD_CONTRACT_ACC) {
		PPD_CONTRACT_ACC = pPD_CONTRACT_ACC;
	}

}
