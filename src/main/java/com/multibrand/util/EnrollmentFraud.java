package com.multibrand.util;

public class EnrollmentFraud implements Constants{
	
public static enum ENROLLMENT_FRAUD_ENUM {
		
		DUPLICATE_ENROLLMENT(MESSAGE_CODE_DUPLICATE_SUBMISSION, "A request for service has already been submitted for this information. Duplicate submission is not allowed.","DUPLICATE_SUBMISSION" ),
		POSID_HOLD(ERROR_CD_ENROLLMENT_NOT_ALLOWED, "POSID Hold Submission not allowed","POSIDHOLD_FRAUD" ),
		RESTRICTED_BP(ERROR_CD_ENROLLMENT_NOT_ALLOWED, "Restricted BP Submission not allowed","RESTRICTED_BP_FRAUD"),
		SWITCH_HOLD(ERROR_CD_ENROLLMENT_NOT_ALLOWED, "Switch Hold Submission not allowed for Switch Transaction","SWITCH_HOLD_FRAUD"),
		CREDIT_FREEZE(ERROR_CD_ENROLLMENT_NOT_ALLOWED, "Credit Freeze / Fraud Submission not allowed","CREDIT_FREEZE_FRAUD"),
		CREDIT_CALL_SKIP(ERROR_CD_ENROLLMENT_NOT_ALLOWED,"Credit not checked ", "CREDIT_API_SKIPPED"),    
		DATE_CALL_SKIP(ERROR_CD_ENROLLMENT_NOT_ALLOWED,"Available Dates not checked ", "DATES_API_SKIPPED"),
	    CREDIT_CHECK_FRAUD(ERROR_CD_ENROLLMENT_NOT_ALLOWED, ENROLLMENT_NOT_ALLOWED_TEXT,"CREDIT_CHECK_FRAUD"),
		CURRENT_CUSTOMER(ERROR_CD_ENROLLMENT_NOT_ALLOWED,"This API does not support Existing Customer enrollment","CURR_CUST_FRAUD"),
		BUSINESS_METER(ERROR_CD_ENROLLMENT_NOT_ALLOWED,"This API does not support Business Meter Enrollment","BIZ_METER_FRAUD");
		
		private String fraudErrorCode;	
		private String fraudErrorMessage;
		private String fraudSystemNotes;
		
		
		ENROLLMENT_FRAUD_ENUM(String fraudErrorCode, String fraudErrorMessage, String fraudSystemNotes){
			this.fraudErrorCode = fraudErrorCode;
			this.fraudErrorMessage= fraudErrorMessage;
			this.fraudSystemNotes=fraudSystemNotes;			
		}


		public String getFraudErrorCode() {
			return fraudErrorCode;
		}


		private void setFraudErrorCode(String fraudErrorCode) {
			this.fraudErrorCode = fraudErrorCode;
		}


		public String getFraudErrorMessage() {
			return fraudErrorMessage;
		}


		private void setFraudErrorMessage(String fraudErrorMessage) {
			this.fraudErrorMessage = fraudErrorMessage;
		}


		public String getFraudSystemNotes() {
			return fraudSystemNotes;
		}


		private void setFraudSystemNotes(String fraudSystemNotes) {
			this.fraudSystemNotes = fraudSystemNotes;
		}

	}
}
