package com.multibrand.vo.request;

public class CheckIfPrepayOfferRequest {
	  
	  private String OfferCode;
	  private String companyCode;
	  private String brandId;
	  private String locale;
	
		public String getOfferCode() {
			return OfferCode;
		}
		public void setOfferCode(String offerCode) {
			OfferCode = offerCode;
		}
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
		public String getLocale() {
			return locale;
		}
		public void setLocale(String locale) {
			this.locale = locale;
		}
	  
}
