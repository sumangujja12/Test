package com.multibrand.resources.requestHandlers;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.EsiDforAddressRequest;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.TosAmbWebRequest;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.TOSPipelineOffersRequest;


@Component
public class TOSRequestHandler {

	
	public CheckPendingMVORequest createRequestCheckPendingMVO(String contractNumber, String companyCode){
		
		CheckPendingMVORequest request = new CheckPendingMVORequest();
		request.setContractNumber(contractNumber);
		request.setCompanyCode(companyCode);
		
		return request;
		
	}
	
	public CheckPendingMoveInRequest createRequestCheckPendingMVI(String accountNumber, String bpid, String pointofDeliveryID, String companyCode){
		CheckPendingMoveInRequest request = new CheckPendingMoveInRequest();
		request.setContractAccountNumber(accountNumber);
		request.setBusinessPartnerNumber(bpid);
		request.setPointofDeliveryID(pointofDeliveryID);
		request.setCompanyCode(companyCode);
		
		return request;
		
	}
	
	public EsiDforAddressRequest createRequestESIDForAddress(String apartmentNumber, String city, String country, String state, String streetName, String streetNumber, String zip, String companyCode){
		EsiDforAddressRequest request = new EsiDforAddressRequest();
		request.setApartmentNumber(apartmentNumber);
		request.setCity(city);
		request.setCountry(country);
		//request.setPOBox(poBox);
		request.setState(state);
		request.setStreetName(streetName);
		request.setStreetNumber(streetNumber);
		request.setZip(zip);
		request.setCompanyCode(companyCode);	
		
		return request;
		
	}
	
	public EsidRequest createRequestESIDFormAddress(String apartmentNumber, String city, String country, String state, String streetName, String streetNumber, String zip, String companyCode){
		
		EsidRequest request = new EsidRequest();
		request.setServStreet(streetNumber + " " + streetName);
		request.setServStreetAptNum(apartmentNumber);
		request.setServCity(city);
		request.setServZipCode(zip);
		
		return request;
		
	}	
	
	
	public TransferServiceRequest getTransferServiceRequest(
			
			 String BusinessPartnerNumber, 
			 String ContractAccountNumber,
			 String ContractNumber,
			 String PointofDeliveryID,  
			 String StreetNumber,
			 String StreetName,
			 String ApartmentNumber,
			 String City,
			 String State,
			 String Zip,
			 String Country,
			 String POBox,
			 String BillingStreetNumber,
			 String BillingStreetName,
			 String BillingApartmentNumber,
			 String BillingCity,
			 String BillingState,
			 String BillingZip,
			 String BillingCountry,
			 String BillingPOBox,
			 String MVIDate,
			 String MVODate,
			 String InMeterAccessFlag,
			 String OutMeterAccessFlag,
			 String PermitType,
			 String CampaignCode,
			 String OfferCellTrackingCode,
			 String OfferCode,
			 String ContactEmail,
			 String OkToEmailFlag,
			 String PhoneAvailableFlag,
			 String ContactPhoneNumber,
			 String newServiceID,
			 String priorityMoveIn,
			 String companyCode,
			 String ambSignupOption){
		
		TransferServiceRequest request = new TransferServiceRequest();
		
		   request.setBusinessPartnerNumber(BusinessPartnerNumber);
		   request.setContractAccountNumber(ContractAccountNumber);
		   request.setContractNumber(ContractNumber);
		   request.setPointofDeliveryID(PointofDeliveryID);
		   request.setStreetNumber(StreetNumber);
		   request.setStreetName(StreetName);
		   request.setApartmentNumber(ApartmentNumber);
		   request.setCity(City);
		   request.setState(State);
		   request.setZip(Zip);
		   request.setCountry(Country);
		   request.setPOBox(POBox);
		   request.setBillingStreetNumber(BillingStreetNumber);
		   request.setBillingStreetName(BillingStreetName);
		   request.setBillingApartmentNumber(BillingApartmentNumber);
		   request.setBillingCity(BillingCity);
		   request.setBillingState(BillingState);
		   request.setBillingZip(BillingZip);
		   request.setBillingCountry(BillingCountry);
		   request.setBillingPOBox(BillingPOBox);
		   request.setMVIDate(CommonUtil.changeDateFormat(MVIDate,"MM/dd/yyyy","yyyy-MM-dd"));
		   request.setMVODate(CommonUtil.changeDateFormat(MVODate,"MM/dd/yyyy","yyyy-MM-dd"));
		   request.setInMeterAccessFlag(InMeterAccessFlag);
		   request.setOutMeterAccessFlag(OutMeterAccessFlag);
		   request.setPermitType(PermitType);
		   request.setCampaignCode(CampaignCode);
		   request.setOfferCellTrackingCode(OfferCellTrackingCode);
		   request.setOfferCode(OfferCode);
		   request.setContactEmail(ContactEmail);
		   request.setOkToEmailFlag(OkToEmailFlag);
		   request.setPhoneAvailableFlag(PhoneAvailableFlag);
		   request.setContactPhoneNumber(ContactPhoneNumber);
		   request.setNewServiceID(newServiceID);
		   request.setPriorityMoveIn(priorityMoveIn);
		   request.setCompanyCode(companyCode);
		   request.setAmbSignupOption(ambSignupOption);
		   request.setEsid(PointofDeliveryID);
		
		
		return request;
	}
	
	public CreateContactLogRequest createContactLogRequest(
			 String commitFlag,
			 String contactActivity,
			 String contactClass,
			 String contactType,
			 String division,
			 String formatCol,
			 String textLines,
			 String bpid,
			 String contractAccountNumber,
			 String companyCode){
		
		CreateContactLogRequest request = new CreateContactLogRequest();
		
		 request.setCommitFlag(commitFlag);
		 request.setContactActivity(contactActivity);
		 request.setContactClass(contactClass);
		 request.setContactType(contactType);
		 request.setDivision(division);
		 request.setFormatCol(formatCol);
		 request.setTextLines(textLines);
		 request.setCompanyCode(companyCode);
		
		return request;
	}
	public TOSPipelineOffersRequest tospipelineRequest(String apartmentNumber, String city, String country, String poBox, String state, String streetName, String streetNumber, String zip, String promocode, String companyCode, String language){
		
		TOSPipelineOffersRequest request = new TOSPipelineOffersRequest();
		
		request.setApartmentNumber(apartmentNumber);
		request.setCity(city);
		request.setCountry(country);
		request.setPoBox(poBox);
		request.setState(state);
		request.setStreetName(streetName);
		request.setStreetNumber(streetNumber);
		request.setZip(zip);
		request.setPromocode(promocode);
		request.setCompanyCode(companyCode);
		request.setLanguage(language);
		return request;
	}
	
	
     public OfferOfContractRequest getOfferOfContractRequest(String contractNumber,String companyCode, String language){
		
		OfferOfContractRequest request = new OfferOfContractRequest();
		
		request.setContractNumber(contractNumber);
		request.setCompanyCode(companyCode);
		request.setLanguage(language);
		return request;
	}
     
     public ProgramAccountInfoRequest getProgramAccountInfoRequest(String bpid, String contractAccountNumber,String contractNumber,String companyCode){
 		
 		ProgramAccountInfoRequest request = new ProgramAccountInfoRequest();
 		
 		request.setContractNumber(contractNumber);
 		request.setCompanyCode(companyCode);
 		request.setContractAccountNumber(contractAccountNumber);
 		request.setBusinessPartnerNumber(bpid);
 		return request;
 	}
     
     public CreateContactLogRequest getUpdateContactLogRequest(String bpid, String contractAccountNumber,String contactClass, String contactActivity, String commitFlag, String contactType, String division, String textLines, String formatCol, String companyCode){
  		
    	 CreateContactLogRequest request = new CreateContactLogRequest();
  		
  		
  		request.setCompanyCode(companyCode);
  		request.setContractAccountNumber(contractAccountNumber);
  		request.setBusinessPartnerNumber(bpid);
  		request.setContactClass(contactClass);
  		request.setContactType(contactType);
  		request.setContactActivity(contactActivity);
  		request.setCommitFlag(commitFlag);
  		request.setDivision(division);
  		request.setFormatCol(formatCol);
  		request.setTextLines(textLines);
  		return request;
  	}
     
     /*public TdspByZipRequest getTdspByZipRequest(String zip, String companyCode){
   		
    	 TdspByZipRequest request = new TdspByZipRequest();
  		
  		
  		request.setCompanyCode(companyCode);
  		request.setZip(zip);
  		return request;
  	}*/
     
     
     
     public ContractDataRequest getContractDataRequest(String bpid, String contractAccountNumber, String companyCode){
   		
    	 ContractDataRequest request = new ContractDataRequest();
  	
  		request.setCompanyCode(companyCode);
  		request.setContractAccountNumber(contractAccountNumber);
  		request.setBusinessPartnerNumber(bpid);
  		
  		return request;
  	}
     
     public OetdspRequest getTDSPSpecificCalendarDatesRequest(String startDate, String endDate,String tdsp, String companyCode, String esiid){
    		
    	 OetdspRequest request = new OetdspRequest();
  	
  		 request.setStartDate(startDate);
  		 request.setEndDate(endDate);
  		 request.setStrCompanyCode(companyCode);
  		 request.setTdsp(tdsp);
  		 //request.setTrackingNum(trackingNum);
  		 if(StringUtils.equals(companyCode, Constants.COMPANY_CODE_GME)) {
  			 request.setEsiid(esiid);
  		 }
  		
  		return request;
  	}
     
     public PermitCheckRequest checkPermitRequirmentRequest(String streetName, String streetNum,String poBox, String apartmentNum, String city, String state, String zip, String country, String permitType, String companyCode){
 		
    	 PermitCheckRequest request = new PermitCheckRequest();
  	
  		 request.setStrApartmentNumber(apartmentNum);
  		 request.setStrStreetName(streetName);
  		 request.setStrStreetNum(streetNum);
  		 request.setStrPOBox(poBox);
  		 request.setStrCity(city);
  		 request.setStrState(state);
  		 request.setStrZip(zip);
  		 request.setStrCountry(country);
  		 request.setStrPermitType(permitType);
  		 request.setStrCompanyCode(companyCode);
  		
  		return request;
  	}
     
     /*public AddressValidateRequest validateAddressRequest(String streetName, String streetNum,String poBox, String apartmentNum, String city, String state, String zip, String country){
  		
    	 AddressValidateRequest request = new AddressValidateRequest();
  	
  		request.setAptNumber(apartmentNum);
  		request.setCity(city);
  		request.setCountry(country);
  		request.setPOBox(poBox);
  		request.setState(state);
  		request.setStreetName(streetName);
  		request.setStreetNum(streetNum);
  		request.setZipCode(zip);
  		
  		return request;
  	}*/
     
     public TosAmbWebRequest createWebTosAMBRequest(String campaignCode,
		String esid,String contractAccountNumber,String contractID,String offerCellCd,String offerCode,
		String companyCode)
     {
    	 TosAmbWebRequest request = new TosAmbWebRequest();
    	 request.setCompanyCode(companyCode);
    	 request.setContractAccountNumber(contractAccountNumber);
    	 request.setContractID(contractID);
    	 request.setEsid(esid);
    	 request.setCampaignCode(campaignCode);
    	 request.setOfferCellCd(offerCellCd);
    	 request.setOfferCode(offerCode);    	 
    	 
    	 return request;
     }
     

}
