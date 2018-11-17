package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TdspResponse")
public class TdspResponse extends GenericResponse{
	private String companyCode;
	private String brandId;
	private String esid;
	private List<TDSPDO> tdspData;
	private ServiceAddressDO serviceAddress;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public List<TDSPDO> getTdspData() {
		return tdspData;
	}
	public void setTdspData(List<TDSPDO> tdspData) {
		this.tdspData = tdspData;
	}
	public ServiceAddressDO getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(ServiceAddressDO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
}
