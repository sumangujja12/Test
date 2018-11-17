package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="OfferResponse")
public class OfferResponse extends GenericResponse {
  private String strTDSPCode;
  private String strOfferFetchSource;
  private String strErrorCode;
  private String esid;
  private ServiceAddressDO serviceAddress;
  private OfferDO[] offerDOList;
  private String offerDate;
  private String offerTime;
  
	public OfferResponse() {

	}

	public OfferResponse(OfferDO[] offerDO, String strErrCode,
			String strErrMessage) {
		this.offerDOList = offerDO;
	}

	public OfferDO[] getOfferDOList() {
		return offerDOList;
	}

	public void setOfferDOList(OfferDO[] offerDOList) {
		this.offerDOList = offerDOList;
	}

	public String getStrTDSPCode() {
		return this.strTDSPCode;
	}

	public void setStrTDSPCode(String strTDSPCode) {
		this.strTDSPCode = strTDSPCode;
	}

	public String getStrOfferFetchSource() {
		return this.strOfferFetchSource;
	}

	public void setStrOfferFetchSource(String strOfferFetchSource) {
		this.strOfferFetchSource = strOfferFetchSource;
	}

	public ServiceAddressDO getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(ServiceAddressDO serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getStrErrorCode() {
		return strErrorCode;
	}

	public void setStrErrorCode(String strErrorCode) {
		this.strErrorCode = strErrorCode;
	}

	public String getEsid() {
		return esid;
	}

	public void setEsid(String esid) {
		this.esid = esid;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public String getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}

	
}