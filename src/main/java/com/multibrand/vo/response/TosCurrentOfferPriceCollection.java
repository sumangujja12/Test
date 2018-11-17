package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.util.CommonUtil;

@XmlRootElement
public class TosCurrentOfferPriceCollection {
	
	
	    private java.lang.String avgPrice;

	    private java.lang.String endDate;

	    private java.lang.String priceType;

	    private java.lang.String season;

	    private java.lang.String startDate;

		public java.lang.String getAvgPrice() {
			return avgPrice;
		}

		public void setAvgPrice(java.lang.String avgPrice) {
			this.avgPrice = avgPrice;
		}

		public java.lang.String getEndDate() {
			return endDate;
		}

		public void setEndDate(java.lang.String endDate) {
			this.endDate =  CommonUtil.changeDateFormat(endDate);
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

		public java.lang.String getStartDate() {
			return startDate;
		}

		public void setStartDate(java.lang.String startDate) {
			this.startDate =  CommonUtil.changeDateFormat(startDate);
		}
	    
	    
}
