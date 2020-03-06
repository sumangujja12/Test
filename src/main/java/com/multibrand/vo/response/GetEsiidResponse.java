package com.multibrand.vo.response;

import java.io.Serializable;
import java.util.List;

import com.multibrand.vo.request.ESIDDO;


public class GetEsiidResponse extends GenericResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6374796615856422775L;
	
	private boolean isMultiESIIDs;
	private String strStreet;
	private String strZipCode;
	private String strCity;
	private String strState;
	private String strAprtNum;
	private String strESIID;
	private String strESSIDClass;
	private String strESIDDeposit;
	private String strTDSP;
	private String strErrCode;
	private String strErrMsg;
	private String strTDSPName;
	private List<ESIDDO> esidList;

	public GetEsiidResponse(boolean isMultiESIIDs, String strStreet,
			String strZipCode, String strCity, String strState,
			String strAprtNum, String strESIID, String strESSIDClass,
			String strESIDDeposit, String strTDSP, String strErrCode,
			String strErrMsg, String strTDSPName, List<ESIDDO> esidList) {
		super();
		this.isMultiESIIDs = isMultiESIIDs;
		this.strStreet = strStreet;
		this.strZipCode = strZipCode;
		this.strCity = strCity;
		this.strState = strState;
		this.strAprtNum = strAprtNum;
		this.strESIID = strESIID;
		this.strESSIDClass = strESSIDClass;
		this.strESIDDeposit = strESIDDeposit;
		this.strTDSP = strTDSP;
		this.strErrCode = strErrCode;
		this.strErrMsg = strErrMsg;
		this.strTDSPName = strTDSPName;
		this.setEsidList(esidList);
	}

	/**
	 * 
	 */
	public GetEsiidResponse() {
		super();
	}

	public boolean isMultiESIIDs() {
		return isMultiESIIDs;
	}

	public void setMultiESIIDs(boolean isMultiESIIDs) {
		this.isMultiESIIDs = isMultiESIIDs;
	}

	public String getStrStreet() {
		return strStreet;
	}

	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}

	public String getStrZipCode() {
		return strZipCode;
	}

	public void setStrZipCode(String strZipCode) {
		this.strZipCode = strZipCode;
	}

	public String getStrCity() {
		return strCity;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}

	public String getStrAprtNum() {
		return strAprtNum;
	}

	public void setStrAprtNum(String strAprtNum) {
		this.strAprtNum = strAprtNum;
	}

	public String getStrESIID() {
		return strESIID;
	}

	public void setStrESIID(String strESIID) {
		this.strESIID = strESIID;
	}

	public String getStrESSIDClass() {
		return strESSIDClass;
	}

	public void setStrESSIDClass(String strESSIDClass) {
		this.strESSIDClass = strESSIDClass;
	}

	public String getStrESIDDeposit() {
		return strESIDDeposit;
	}

	public void setStrESIDDeposit(String strESIDDeposit) {
		this.strESIDDeposit = strESIDDeposit;
	}

	public String getStrTDSP() {
		return strTDSP;
	}

	public void setStrTDSP(String strTDSP) {
		this.strTDSP = strTDSP;
	}

	public String getStrErrCode() {
		return strErrCode;
	}

	public void setStrErrCode(String strErrCode) {
		this.strErrCode = strErrCode;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getStrTDSPName() {
		return strTDSPName;
	}

	public void setStrTDSPName(String strTDSPName) {
		this.strTDSPName = strTDSPName;
	}

	public List<ESIDDO> getEsidList() {
		return esidList;
	}

	public void setEsidList(List<ESIDDO> esidList) {
		this.esidList = esidList;
	}


}
