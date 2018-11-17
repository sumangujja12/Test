package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SegmentedFlagsOutData")
public class SegmentedFlagsOutData {

	 private java.lang.String offerCode;

	    private java.lang.String segmentFlag;

		public java.lang.String getOfferCode() {
			return offerCode;
		}

		public void setOfferCode(java.lang.String offerCode) {
			this.offerCode = offerCode;
		}

		public java.lang.String getSegmentFlag() {
			return segmentFlag;
		}

		public void setSegmentFlag(java.lang.String segmentFlag) {
			this.segmentFlag = segmentFlag;
		}
	    
	    
}
