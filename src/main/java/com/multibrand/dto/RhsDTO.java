package com.multibrand.dto;

import java.io.Serializable;

import com.multibrand.util.CommonUtil;

/**
 * 
 * @author jyogapa1
 * 
 */
public class RhsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String avgProgCode;
	private String pbProgCode;
	private String rhssf1ProgCode;
	private String rhssf2ProgCode;
	private String rhsmf1ProgCode;
	private String rhsmf2ProgCode;
	private String displayOptionCodes;

	private String avgProgName;
	private String pbProgName;
	private String rhssf1ProgName;
	private String rhssf2ProgName;
	private String rhsmf1ProgName;
	private String rhsmf2ProgName;
	
	/**
	 * @return the avgProgCode
	 */
	public String getAvgProgCode() {
		return avgProgCode;
	}



	/**
	 * @param avgProgCode the avgProgCode to set
	 */
	public void setAvgProgCode(String avgProgCode) {
		this.avgProgCode = avgProgCode;
	}



	/**
	 * @return the pbProgCode
	 */
	public String getPbProgCode() {
		return pbProgCode;
	}



	/**
	 * @param pbProgCode the pbProgCode to set
	 */
	public void setPbProgCode(String pbProgCode) {
		this.pbProgCode = pbProgCode;
	}



	/**
	 * @return the rhssf1ProgCode
	 */
	public String getRhssf1ProgCode() {
		return rhssf1ProgCode;
	}



	/**
	 * @param rhssf1ProgCode the rhssf1ProgCode to set
	 */
	public void setRhssf1ProgCode(String rhssf1ProgCode) {
		this.rhssf1ProgCode = rhssf1ProgCode;
	}



	/**
	 * @return the rhssf2ProgCode
	 */
	public String getRhssf2ProgCode() {
		return rhssf2ProgCode;
	}



	/**
	 * @param rhssf2ProgCode the rhssf2ProgCode to set
	 */
	public void setRhssf2ProgCode(String rhssf2ProgCode) {
		this.rhssf2ProgCode = rhssf2ProgCode;
	}



	/**
	 * @return the rhsmf1ProgCode
	 */
	public String getRhsmf1ProgCode() {
		return rhsmf1ProgCode;
	}



	/**
	 * @param rhsmf1ProgCode the rhsmf1ProgCode to set
	 */
	public void setRhsmf1ProgCode(String rhsmf1ProgCode) {
		this.rhsmf1ProgCode = rhsmf1ProgCode;
	}



	/**
	 * @return the rhsmf2ProgCode
	 */
	public String getRhsmf2ProgCode() {
		return rhsmf2ProgCode;
	}



	/**
	 * @param rhsmf2ProgCode the rhsmf2ProgCode to set
	 */
	public void setRhsmf2ProgCode(String rhsmf2ProgCode) {
		this.rhsmf2ProgCode = rhsmf2ProgCode;
	}



	/**
	 * @return the displayOptionCodes
	 */
	public String getDisplayOptionCodes() {
		return displayOptionCodes;
	}



	/**
	 * @param displayOptionCodes the displayOptionCodes to set
	 */
	public void setDisplayOptionCodes(String displayOptionCodes) {
		this.displayOptionCodes = displayOptionCodes;
	}



	/**
	 * @return the avgProgName
	 */
	public String getAvgProgName() {
		return avgProgName;
	}



	/**
	 * @param avgProgName the avgProgName to set
	 */
	public void setAvgProgName(String avgProgName) {
		this.avgProgName = avgProgName;
	}



	/**
	 * @return the pbProgName
	 */
	public String getPbProgName() {
		return pbProgName;
	}



	/**
	 * @param pbProgName the pbProgName to set
	 */
	public void setPbProgName(String pbProgName) {
		this.pbProgName = pbProgName;
	}



	/**
	 * @return the rhssf1ProgName
	 */
	public String getRhssf1ProgName() {
		return rhssf1ProgName;
	}



	/**
	 * @param rhssf1ProgName the rhssf1ProgName to set
	 */
	public void setRhssf1ProgName(String rhssf1ProgName) {
		this.rhssf1ProgName = rhssf1ProgName;
	}



	/**
	 * @return the rhssf2ProgName
	 */
	public String getRhssf2ProgName() {
		return rhssf2ProgName;
	}



	/**
	 * @param rhssf2ProgName the rhssf2ProgName to set
	 */
	public void setRhssf2ProgName(String rhssf2ProgName) {
		this.rhssf2ProgName = rhssf2ProgName;
	}



	/**
	 * @return the rhsmf1ProgName
	 */
	public String getRhsmf1ProgName() {
		return rhsmf1ProgName;
	}



	/**
	 * @param rhsmf1ProgName the rhsmf1ProgName to set
	 */
	public void setRhsmf1ProgName(String rhsmf1ProgName) {
		this.rhsmf1ProgName = rhsmf1ProgName;
	}



	/**
	 * @return the rhsmf2ProgName
	 */
	public String getRhsmf2ProgName() {
		return rhsmf2ProgName;
	}



	/**
	 * @param rhsmf2ProgName the rhsmf2ProgName to set
	 */
	public void setRhsmf2ProgName(String rhsmf2ProgName) {
		this.rhsmf2ProgName = rhsmf2ProgName;
	}



	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}

}
