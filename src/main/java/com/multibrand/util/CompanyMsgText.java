package com.multibrand.util;

public class CompanyMsgText{
	
public static enum CREDIT_AGENCY_ENUM{
		
		EQ("EQ", "Equifax","866-349-5191" ),
		TU("TU", "TransUnion","800-916-8800");

		private String code;		
		private String name;
		private String phoneNumber;
		
		CREDIT_AGENCY_ENUM(String code, String name, String phoneNumber){
			this.code = code;
			this.name= name;
			this.phoneNumber=phoneNumber;			
		}

		public String getCode() {
			return code;
		}


		public String getName() {
			return name;
		}


		public String getPhoneNumber() {
			return phoneNumber;
		}

	}
	
	//TODO: Add DE company code entry
	public static enum COMPANY_CODE_ENUM{
		CC0121("0121",  "www.reliant.com","1-866-RELIANT" ),
		CC0271("0271", "www.greenmountainenergy.com","1-855-236-1312"),
		CC0391_CE("0391_CE","www.cirroenergy.com","1-844-808-5607"),
		CC0391("0391","www.pennywisepower.com","1-877-455-4674");
		
		private String code;				
		private String multiCompanyEmail;
		private String multiCompanyPhoneNumber;
		
		COMPANY_CODE_ENUM(String code, String multiCompanyEmail,String multiCompanyPhoneNumber ){
			this.code = code;			
			this.multiCompanyEmail=multiCompanyEmail;	
			this.multiCompanyPhoneNumber=multiCompanyPhoneNumber;	
		}
		public String getCode() {
			return code;
		}

		public String getMultiCompanyEmail() {
			return multiCompanyEmail;
		}

		public String getMultiCompanyPhoneNumber() {
			return multiCompanyPhoneNumber;
		}	
	}
}
