package com.multibrand.vo.request;

import com.multibrand.vo.response.billingResponse.AddressDO;
import java.io.Serializable;

public class OESignupVO
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String esidNumber;
  private AddressDO serviceAddressDO;
  private String companyCode;
  private String brandId;
  private String charityId;
  private String charityName;
  private String charityPromo;
  private String locale;
  private ESIDDO esidDO;
  private String tdspCodeCCS;
  private String tdspCode;
  private String tdspName;
  private String geoZone;
  private String promoCodeEntered;
  private String transactionType;
  private String promoError;
  private String offerDate;
  private String offerTime;

  private String offerCategory ="ALL_OFFERS"; // PREPAY_OFFERS , ALL_OFFERS and POSTPAY_OFFERS
  
  public AddressDO getServiceAddressDO()
  {
    return this.serviceAddressDO;
  }
  public void setServiceAddressDO(AddressDO serviceAddressDO) {
    this.serviceAddressDO = serviceAddressDO;
  }
  public String getCompanyCode() {
    return this.companyCode;
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
public String getCharityId() {
	return charityId;
}
public void setCharityId(String charityId) {
	this.charityId = charityId;
}
public String getCharityName() {
	return charityName;
}
public void setCharityName(String charityName) {
	this.charityName = charityName;
}
public String getCharityPromo() {
	return charityPromo;
}
public void setCharityPromo(String charityPromo) {
	this.charityPromo = charityPromo;
}
public String getEsidNumber() {
    return this.esidNumber;
  }
  public void setEsidNumber(String esidNumber) {
    this.esidNumber = esidNumber;
  }
  public ESIDDO getEsidDO() {
    return this.esidDO;
  }
  public void setEsidDO(ESIDDO esidDO) {
    this.esidDO = esidDO;
  }
  public String getTdspCodeCCS() {
    return this.tdspCodeCCS;
  }
  public void setTdspCodeCCS(String tdspCodeCCS) {
    this.tdspCodeCCS = tdspCodeCCS;
  }
  public String getTdspCode() {
    return this.tdspCode;
  }
  public void setTdspCode(String tdspCode) {
    this.tdspCode = tdspCode;
  }
  public String getTdspName() {
    return this.tdspName;
  }
  public void setTdspName(String tdspName) {
    this.tdspName = tdspName;
  }
  public String getGeoZone() {
    return this.geoZone;
  }
  public void setGeoZone(String geoZone) {
    this.geoZone = geoZone;
  }
  public String getLocale() {
    return this.locale;
  }
  public void setLocale(String locale) {
    this.locale = locale;
  }
  public String getPromoCodeEntered() {
    return this.promoCodeEntered;
  }
  public void setPromoCodeEntered(String promoCodeEntered) {
    this.promoCodeEntered = promoCodeEntered;
  }
  public String getTransactionType() {
    return this.transactionType;
  }
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }
  public String getPromoError() {
    return this.promoError;
  }
  public void setPromoError(String promoError) {
    this.promoError = promoError;
  }
public String getOfferCategory() {
	return offerCategory;
}
public void setOfferCategory(String offerCategory) {
	this.offerCategory = offerCategory;
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