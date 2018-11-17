package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="PromoOfferAvgPriceData")
public class PromoOfferAvgPriceData {

	
	 private java.math.BigDecimal avgPrice;

	    private java.lang.String dateEnd;

	    private java.lang.String dateStart;

	    private java.lang.String priceType;

	    private java.lang.String season;

		public java.math.BigDecimal getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(java.math.BigDecimal avgPrice) {
			this.avgPrice = avgPrice;
		}

		public java.lang.String getDateEnd() {
			return dateEnd;
		}

		public void setDateEnd(java.lang.String dateEnd) {
			this.dateEnd = dateEnd;
		}

		public java.lang.String getDateStart() {
			return dateStart;
		}

		public void setDateStart(java.lang.String dateStart) {
			this.dateStart = dateStart;
		}

		public java.lang.String getPriceType() {
			return priceType;
		}

		public void setPriceType(java.lang.String priceType) {
			this.priceType = priceType;
		}

		public java.lang.String getSeason() {
			return season;
		}

		public void setSeason(java.lang.String season) {
			this.season = season;
		}
	    
	    
	    
}
