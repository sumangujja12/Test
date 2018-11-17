package com.multibrand.vo.response;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.multibrand.util.CommonUtil;

@Component
@XmlRootElement(name = "CheckPendingMVO")
public class CheckPendingMVOResponse extends GenericResponse {
	
	  // private java.lang.String errCode;

	    // private java.lang.String errMessage;

	    private java.lang.String forcedMoveOut;

	    private java.lang.String moveOutDate;

	    private java.lang.String pendingMoveOutExists;
	    /*TOS REDESIGN-DK*/
	    private String tosAMBenrollment;
	    private BigDecimal ambDeferredBalance;
	    private BigDecimal  depositAmount;
	    /*TOS REDESIGN-DK*/
	    

		public java.lang.String getForcedMoveOut() {
			return forcedMoveOut;
		}

		public void setForcedMoveOut(java.lang.String forcedMoveOut) {
			this.forcedMoveOut = forcedMoveOut;
		}

		public java.lang.String getMoveOutDate() {
			return moveOutDate;
		}

		public void setMoveOutDate(java.lang.String moveOutDate) {
			this.moveOutDate =  CommonUtil.changeDateFormat(moveOutDate);
		}

		public java.lang.String getPendingMoveOutExists() {
			return pendingMoveOutExists;
		}

		public void setPendingMoveOutExists(java.lang.String pendingMoveOutExists) {
			this.pendingMoveOutExists = pendingMoveOutExists;
		}

		public String getTosAMBenrollment() {
			return tosAMBenrollment;
		}

		public void setTosAMBenrollment(String tosAMBenrollment) {
			this.tosAMBenrollment = tosAMBenrollment;
		}

		public BigDecimal getAmbDeferredBalance() {
			return ambDeferredBalance;
		}

		public void setAmbDeferredBalance(BigDecimal ambDeferredBalance) {
			this.ambDeferredBalance = ambDeferredBalance;
		}

		public BigDecimal getDepositAmount() {
			return depositAmount;
		}

		public void setDepositAmount(BigDecimal depositAmount) {
			this.depositAmount = depositAmount;
		}
	    
	    
	    
}
