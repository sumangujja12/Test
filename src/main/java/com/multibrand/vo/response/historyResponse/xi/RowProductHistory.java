package com.multibrand.vo.response.historyResponse.xi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author ahanda1
 * Response POJO for XI invoice list call. To be used with JAXB for marshalling and unmarshalling
 * ProductHistoryResponse POJO will contain a list of this pojo
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "row", namespace="http://reliant.com/xi/GreenMountain")
@XmlRootElement(name="row", namespace="http://reliant.com/xi/GreenMountain")
public class RowProductHistory {
	
	@XmlElement(name = "esi_id")
	protected String esid;
	
	@XmlElement(name = "product")
	protected String product;
	
	@XmlElement(name = "product_start_dt")
	protected String productStartDate;
	
	@XmlElement(name = "product_end_dt")
	protected String productEndDate;
	
	@XmlElement(name = "doc_id")
	protected String docId;
	
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	@XmlElement(name = "address_line_1")
	protected String addressLine1;
	
	@XmlElement(name = "address_line_2")
	protected String addressLine2;
	
	@XmlElement(name = "city")
	protected String city;
	
	@XmlElement(name = "county")
	protected String county;
	
	@XmlElement(name = "state")
	protected String state;
	
	@XmlElement(name = "zipcode")
	protected String zipCode;
	
	@XmlElement(name = "country")
	protected String country;	
	
	@XmlElement(name = "Result_Code")
	protected String Result_Code;
	
	@XmlElement(name = "Result_Description")
	protected String Result_Description;

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductStartDate() {
		return productStartDate;
	}

	public void setProductStartDate(String productStartDate) {
		this.productStartDate = productStartDate;
	}

	public String getProductEndDate() {
		return productEndDate;
	}

	public void setProductEndDate(String productEndDate) {
		this.productEndDate = productEndDate;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getResult_Code() {
		return Result_Code;
	}

	public void setResult_Code(String result_Code) {
		Result_Code = result_Code;
	}

	public String getResult_Description() {
		return Result_Description;
	}

	public void setResult_Description(String result_Description) {
		Result_Description = result_Description;
	}

	
}
