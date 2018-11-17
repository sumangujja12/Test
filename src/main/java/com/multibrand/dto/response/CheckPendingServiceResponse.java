package com.multibrand.dto.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

/**
 * @author jyogapa1
 *
 */
@XmlRootElement(name="PendingServiceResponse")
public class CheckPendingServiceResponse extends GenericResponse {

	private String trackNo="";
	private String firstName="";
	private String middleInitial="";
	private String lastName="";
	private String streetAddress="";
	private String unitNum="";
	private String city="";
	private String zipcode="";
	private String transactionDate="";
	private String previousProviderName="";
	private String serviceStartDate="";
	private java.sql.Timestamp creationDate=null;
	
	
	public CheckPendingServiceResponse() {
		super();
	}
	
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPreviousProviderName() {
		return previousProviderName;
	}
	public void setPreviousProviderName(String previousProviderName) {
		this.previousProviderName = previousProviderName;
	}
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	public java.sql.Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(java.sql.Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
