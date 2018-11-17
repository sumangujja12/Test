package com.multibrand.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.Constants;


@XmlRootElement(name = "billCourtesyCreditActivityRequest")

public class BillCourtesyCreditActivityRequest implements FormEntityRequest, Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9113739934701577346L;
	
	private String bpNumber;
	
	@NotNull
	private String caNumber;
	
	@NotNull
	private String uan;
	
	@NotNull
	private String courtesyCreditCode ;
	
	private String notes;
	
	private String url;

	/**
	 * @return the bpNumber
	 */
	public String getBpNumber() {
		return bpNumber;
	}

	/**
	 * @param bpNumber the bpNumber to set
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
	 * @param caNumber the caNumber to set
	 */
	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	

	/**
	 * @return the uan
	 */
	public String getUan() {
		return uan;
	}

	/**
	 * @param uan the uan to set
	 */
	public void setUan(String uan) {
		this.uan = uan;
	}

	/**
	 * @return the courtesyCreditCode
	 */
	public String getCourtesyCreditCode() {
		return courtesyCreditCode;
	}

	/**
	 * @param courtesyCreditCode the courtesyCreditCode to set
	 */
	public void setCourtesyCreditCode(String courtesyCreditCode) {
		this.courtesyCreditCode = courtesyCreditCode;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
