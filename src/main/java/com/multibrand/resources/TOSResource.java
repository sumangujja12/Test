package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.TOSBO;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.EsiDforAddressRequest;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.resources.requestHandlers.TOSRequestHandler;
import com.multibrand.vo.request.TOSEligibleNonEligibleProductsRequest;
import com.multibrand.vo.request.TOSSubmitEligibleProductsRequest;
import com.multibrand.vo.response.CheckPendingMVOResponse;
import com.multibrand.vo.response.CheckPendingMoveInResponse;
import com.multibrand.vo.response.ContractDataResponse;
import com.multibrand.vo.response.CreateContactLogResponse;
import com.multibrand.vo.response.ESIDForAddressResponse;
import com.multibrand.vo.response.EsidProfileResponse;
import com.multibrand.vo.response.OetdspResponse;
import com.multibrand.vo.response.OfferOfContractResponse;
import com.multibrand.vo.response.PermitCheckResponse;
import com.multibrand.vo.response.ProgramAccountInfoResponse;
import com.multibrand.vo.response.TOSEligibleNonEligibleProductsResponse;
import com.multibrand.vo.response.TOSSubmitEligibleProductsResponse;
import com.multibrand.vo.response.TdspByESIDResponse;
import com.multibrand.vo.response.TdspByZipResponse;
import com.multibrand.vo.response.TosAmbWebResponse;
import com.multibrand.vo.response.TransferServiceResponse;

@RestController
public class TOSResource {
	
	@Autowired
	TOSRequestHandler tosRequestHandler;
	
	@Autowired
	TOSBO tosBO;
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	/**
	 * @author ahanda1
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/checkPendingMVO", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CheckPendingMVOResponse checkPendingMVO(@RequestParam("contractNumber")String contractNumber, @RequestParam("companyCode") String companyCode){
		logger.debug("TOSResource.checkPendingMVO ::: START");
		CheckPendingMVORequest request = tosRequestHandler.createRequestCheckPendingMVO(contractNumber, companyCode);	
		
		
		CheckPendingMVOResponse checkPendingMVOResp = tosBO.checkPendingMVO(request, companyCode, httpRequest.getSession(true).getId());
		
		//Response response = null;
		
		//response = Response.status(200).entity(checkPendingMVOResp).build();
		
		logger.debug("TOSResource.checkPendingMVO ::: END");
		return checkPendingMVOResp;		
		
	}
	
	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bpid
	 * @param pointofDeliveryID
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/checkPendingMVI", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CheckPendingMoveInResponse checkPendingMVI(@RequestParam("accountNumber")String accountNumber, @RequestParam("bpid")String bpid, @RequestParam("pointofDeliveryID") String pointofDeliveryID, @RequestParam("companyCode") String companyCode){
		logger.debug("TOSResource.checkPendingMVI ::: START");
		
		CheckPendingMoveInRequest request = tosRequestHandler.createRequestCheckPendingMVI(accountNumber, bpid, pointofDeliveryID, companyCode);	
		
		
		CheckPendingMoveInResponse checkPendingMVIResp = tosBO.checkPendingMVI(request, companyCode,  httpRequest.getSession(true).getId());
		
		//Response response = null;
		
		//response = Response.status(200).entity(checkPendingMVIResp).build();
		
		logger.debug("TOSResource.checkPendingMVI ::: END");
		return checkPendingMVIResp;		
		
	}
	
	@PostMapping(value = "/tos/ESIDForAddress", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ESIDForAddressResponse getESIDForAddress(
			@RequestParam("apartmentNumber")String apartmentNumber, 
			@RequestParam("city")String city, 
			@RequestParam("country")String country,
			//@RequestParam("POBox")String poBox,
			@RequestParam("state")String state,
			@RequestParam("streetName")String streetName,
			@RequestParam("streetNumber")String streetNumber,
			@RequestParam("zip")String zip, 
			@RequestParam("companyCode") String companyCode){
		
		logger.debug("TOSResource.getESIDForAddress ::: START");
		
		EsiDforAddressRequest request = tosRequestHandler.createRequestESIDForAddress(apartmentNumber, city, country, state, streetName, streetNumber, zip, companyCode);	
				
		ESIDForAddressResponse esidForAddResp = tosBO.getESIDForAddress(request, companyCode, httpRequest.getSession(true).getId());
		
		//Response response = null;
		
		//response = Response.status(200).entity(esidForAddResp).build();
		
		logger.debug("TOSResource.getESIDForAddress ::: END");
		return esidForAddResp;		
		
	}
	
	
/*	@POST
	@Path("TOSPipelineOffers")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response TOSPipelineOffers(
			@RequestParam("apartmentNumber")String apartmentNumber, 
			@RequestParam("city")String city, 
			@RequestParam("country")String country,
			@RequestParam("POBox")String poBox,
			@RequestParam("state")String state,
			@RequestParam("streetName")String streetName,
			@RequestParam("streetNumber")String streetNumber,
			@RequestParam("zip")String zip,
			@RequestParam("promocode")String promocode,
			@RequestParam("companyCode") String companyCode,
			@RequestParam("language")String language){
		
		logger.debug("TOSResource.TOSPipelineOffers ::: START");
		Response response = null;
		TOSPipelineOffersResponse tospor = new TOSPipelineOffersResponse();
		TOSPipelineOffersRequest tosreq=tosRequestHandler.tospipelineRequest(apartmentNumber, city, country, poBox, state, streetName, streetNumber, zip, promocode, companyCode, language);
		tospor=tosBO.tospipelineoffers(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(tospor).build();
		
		logger.debug("TOSResource.TOSPipelineOffers ::: END");
		return response;
	}*/
	
	/**
	 * @author ahanda1
	 * @param contractNumber
	 * @param companyCode
	 * @param language
	 * @return
	 */
	@PostMapping(value = "/tos/getOfferOfContract", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OfferOfContractResponse getOfferOfContract(
			@RequestParam("contractNumber")String contractNumber,
			@RequestParam("companyCode") String companyCode,
			@RequestParam("language")String language){
		
		logger.info("TOSResource.getOfferOfContract ::: START");
		//Response response = null;
		OfferOfContractResponse tospor = new OfferOfContractResponse();
		OfferOfContractRequest tosreq=tosRequestHandler.getOfferOfContractRequest(contractNumber, companyCode, language);
		tospor=tosBO.getOfferOfContract(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(tospor).build();
		logger.info(" START ******* Input for the getOfferOfContract API**********");
		
		logger.info("	contractNumber	 "+contractNumber);
		logger.info("	companyCode	 "+companyCode);
		logger.info("	language	 "+language);
		
		logger.info(" OUTPUT of the getOfferOfContract API*************");
		//String json = CommonUtil.wirteObjectToJson(response);
		//logger.info(" Response  - "+json);
		
		
		logger.info("TOSResource.getOfferOfContract ::: END");
		return tospor;
	}
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param contractAccountNumber
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/getProgramAccountInfo", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public ProgramAccountInfoResponse getProgramAccountInfo(
			@RequestParam("businessPartnerId")String bpid,
			@RequestParam("contractAccountNumber")String contractAccountNumber,
			@RequestParam("contractNumber")String contractNumber,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getProgramAccountInfo ::: START");
		//Response response = null;
		ProgramAccountInfoResponse tospor = new ProgramAccountInfoResponse();
		ProgramAccountInfoRequest tosreq=tosRequestHandler.getProgramAccountInfoRequest(bpid, contractAccountNumber, contractNumber, companyCode);
		tospor=tosBO.getProgramAccountInfo(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(tospor).build();
		
		logger.info("TOSResource.getProgramAccountInfo ::: END");
		return tospor;
	}
	
	/**
	 * 
	 * @param bpid
	 * @param contractAccountNumber
	 * @param contactClass
	 * @param contactActivity
	 * @param commitFlag
	 * @param contactType
	 * @param division
	 * @param textLines
	 * @param formatCol
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/updateContactLog", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public CreateContactLogResponse updateContactLog(
			@RequestParam("businessPartnerId")String bpid,
			@RequestParam("contractAccountNumber")String contractAccountNumber,
			@RequestParam("contactClass")String contactClass,
			@RequestParam("contactActivity")String contactActivity,
			@RequestParam("commitFlag")String commitFlag,
			@RequestParam("contactType")String contactType,
			@RequestParam("division") String division,
			@RequestParam("textLines")String textLines,
			@RequestParam("formatCol")String formatCol,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.updateContactLog ::: START");
		//Response response = null;
		CreateContactLogResponse resp = new CreateContactLogResponse();
		CreateContactLogRequest tosreq=tosRequestHandler.getUpdateContactLogRequest(bpid, contractAccountNumber, contactClass, contactActivity, commitFlag, contactType, division, textLines, formatCol, companyCode);
		resp=tosBO.updateContactLog(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.updateContactLog ::: END");
		return resp;
	}
	
	
	/**
	 * @author ahanda1
	 * @param zip
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/getTdspByZip", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TdspByZipResponse getTdspByZip(
			@RequestParam("zip")String zip,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTdspByZip ::: START");
		//Response response = null;
		TdspByZipResponse resp = new TdspByZipResponse();
		//TdspByZipRequest tosreq=tosRequestHandler.getTdspByZipRequest(zip, companyCode);
		resp=tosBO.getTdspByZip(zip,companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTdspByZip ::: END");
		return resp;
	}
	
	@PostMapping(value = "/tos/getTDSPFromESID", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TdspByESIDResponse getTDSPFromESID(
			@RequestParam("esid")String esid,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTDSPFromESID ::: START");
		//Response response = null;
		TdspByESIDResponse resp = new TdspByESIDResponse();
		resp=tosBO.getTDSPFromESID(esid,companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTDSPFromESID ::: END");
		return resp;
	}
	
	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param ca
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/getContractData", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public ContractDataResponse getContractData(
			@RequestParam("businessPartnerId")String bpid,
			@RequestParam("contractAccountNumber")String ca,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getContractData ::: START");
		//Response response = null;
		ContractDataResponse resp = new ContractDataResponse();
		ContractDataRequest tosreq=tosRequestHandler.getContractDataRequest(bpid, ca, companyCode);
		resp=tosBO.getContractData(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getContractData ::: END");
		return resp;
	}
	
	/**
	 * @author ahanda1
	 * @param BusinessPartnerNumber
	 * @param ContractAccountNumber
	 * @param ContractNumber
	 * @param PointofDeliveryID
	 * @param StreetNumber
	 * @param StreetName
	 * @param ApartmentNumber
	 * @param City
	 * @param State
	 * @param Zip
	 * @param Country
	 * @param POBox
	 * @param BillingStreetNumber
	 * @param BillingStreetName
	 * @param BillingApartmentNumber
	 * @param BillingCity
	 * @param BillingState
	 * @param BillingZip
	 * @param BillingCountry
	 * @param BillingPOBox
	 * @param MVIDate
	 * @param MVODate
	 * @param InMeterAccessFlag
	 * @param OutMeterAccessFlag
	 * @param PermitType
	 * @param CampaignCode
	 * @param OfferCellTrackingCode
	 * @param OfferCode
	 * @param ContactEmail
	 * @param OkToEmailFlag
	 * @param PhoneAvailableFlag
	 * @param ContactPhoneNumber
	 * @param newServiceID
	 * @param priorityMoveIn
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/saveTransferServiceDetails", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TransferServiceResponse saveTransferServiceDetails(
			@RequestParam("businessPartnerId") String BusinessPartnerNumber, 
			@RequestParam("contractAccountNumber") String ContractAccountNumber,
			@RequestParam("checkDigit")String checkDigit,
			@RequestParam("contractNumber") String ContractNumber,
			@RequestParam("contractAccountName")String contractAccountName,
			@RequestParam("esid")String PointofDeliveryID,  
			@RequestParam("streetNumber") String StreetNumber,
			@RequestParam("streetName")String StreetName,
			@RequestParam("apartmentNumber")String ApartmentNumber,
			@RequestParam("city") String City,
			 @RequestParam("state")String State,
			 @RequestParam("zip")String Zip,
			 @RequestParam("country")String Country,
			 @RequestParam("poBox")String POBox,
			 @RequestParam("billingStreetNumber")String BillingStreetNumber,
			 @RequestParam("billingStreetName")String BillingStreetName,
			 @RequestParam("billingApartmentNumber")String BillingApartmentNumber,
			 @RequestParam("billingCity")String BillingCity,
			 @RequestParam("billingState")String BillingState,
			 @RequestParam("billingZip")String BillingZip,
			 @RequestParam("billingCountry")String BillingCountry,
			 @RequestParam("billingPoBox")String BillingPOBox,
			 @RequestParam("mviDate")String MVIDate,
			 @RequestParam("mvoDate")String MVODate,
			 @RequestParam("inMeterAccessFlag")String InMeterAccessFlag,
			 @RequestParam("outMeterAccessFlag")String OutMeterAccessFlag,
			 @RequestParam("permitType")String PermitType,
			 @RequestParam("campaignCode")String CampaignCode,
			 @RequestParam("offerCellTrackingCode")String OfferCellTrackingCode,
			 @RequestParam("offerCode")String OfferCode,
			 @RequestParam("contactEmail")String ContactEmail,
			 @RequestParam("okToEmailFlag")String OkToEmailFlag,
			 @RequestParam("phoneAvailableFlag")String PhoneAvailableFlag,
			 @RequestParam("contactName")String contactName,
			 @RequestParam("contactPhoneNumber")String ContactPhoneNumber,
			 @RequestParam("newServiceId")String newServiceID,
			 @RequestParam("priorityMoveIn")String priorityMoveIn,
			 @RequestParam("companyCode")String companyCode,
			 @RequestParam("email")String email,
			 @RequestParam("languageCode") String languageCode,
			 @RequestParam("billDeliveryMethod")String billDeliveryMethod,
			 @RequestParam("additionalProgram")String additionalProgs,
			 @RequestParam("cancelFee")String cancelFee,
			 @RequestParam("planName")String planName,
			 @RequestParam("planType")String planType,
			 @RequestParam("contractTerm")String contractTerm,
			 @RequestParam("avgPrice")String avgPrice,
			 @RequestParam("tosUrl")String tosUrl,
			 @RequestParam("tosId")String tosId,
			 @RequestParam("eflUrl")String eflUrl,
			 @RequestParam("eflId")String eflId,
			 @RequestParam("yraacUrl")String yraacUrl,
			 @RequestParam("yraacId")String yraacId,
			 @RequestParam("existingStreetNumber") String existingStreetNumber,
		 	 @RequestParam("existingStreetName")String existingStreetName,
			 @RequestParam("existingApartmentNumber")String existingApartmentNumber,
			 @RequestParam("existingCity") String existingCity,
			 @RequestParam("existingState")String existingState,
			 @RequestParam("existingZip")String existingZip,
			 @RequestParam("existingCountry")String existingCountry,
			 @RequestParam("ambSignupOption")String ambSignupOption){
		
		logger.info("TOSResource.saveTransferServiceDetails ::: START");
		//Response response = null;
		TransferServiceResponse resp = new TransferServiceResponse();
		TransferServiceRequest tosreq=tosRequestHandler.getTransferServiceRequest(
	      BusinessPartnerNumber, 
		  ContractAccountNumber,
		  ContractNumber,
		  PointofDeliveryID,  
		  StreetNumber,
		  StreetName,
		  ApartmentNumber,
		  City,
		  State,
		  Zip,
		  Country,
		  POBox,
		  BillingStreetNumber,
		  BillingStreetName,
		  BillingApartmentNumber,
		  BillingCity,
		  BillingState,
		  BillingZip,
		  BillingCountry,
		  BillingPOBox,
		  MVIDate,
		  MVODate,
		  InMeterAccessFlag,
		  OutMeterAccessFlag,
		  PermitType,
		  CampaignCode,
		  OfferCellTrackingCode,
		  OfferCode,
		  ContactEmail,
		  OkToEmailFlag,
		  PhoneAvailableFlag,
		  ContactPhoneNumber,
		  newServiceID,
		  priorityMoveIn,
		  companyCode,
		  ambSignupOption);
		resp=tosBO.saveTransferServiceDetails(tosreq, httpRequest.getSession(true).getId(), contractAccountName, contactName,email,languageCode,billDeliveryMethod, additionalProgs, cancelFee, planName, planType, contractTerm, avgPrice, tosUrl, tosId, eflUrl, eflId, yraacUrl, yraacId,existingStreetNumber, existingStreetName,existingApartmentNumber, existingCity, existingState, existingZip, existingCountry, checkDigit);
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.saveTransferServiceDetails ::: END");
		return resp;
	}
	
	/**
	 * @author ahanda1
	 * @param startDate
	 * @param endDate
	 * @param tdsp
	 * @param trackingNum
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/getTDSPSpecificCalendarDates", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public OetdspResponse getTDSPSpecificCalendarDates(
			@RequestParam("startDate")String startDate,
			@RequestParam("endDate")String endDate,
			@RequestParam("tdsp")String tdsp,
		//	@RequestParam("trackingNum")String trackingNum,
			@RequestParam("esiid") String esiid,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTDSPSpecificCalendarDates ::: START");
		//Response response = null;
		OetdspResponse resp = new OetdspResponse();
		OetdspRequest tosreq=tosRequestHandler.getTDSPSpecificCalendarDatesRequest(startDate,endDate,tdsp,companyCode,esiid);
		resp=tosBO.getTDSPSpecificCalendarDates(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTDSPSpecificCalendarDates ::: END");
		return resp;
	}	
	
	
	@PostMapping(value = "/tos/checkPermitRequirment", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public PermitCheckResponse checkPermitRequirment(
			@RequestParam("streetName")String streetName,
			@RequestParam("streetNum")String streetNum,
			@RequestParam("poBox")String poBox,
			@RequestParam("apartmentNum")String apartmentNum,
			@RequestParam("city") String city,
			@RequestParam("state")String state,
			@RequestParam("zip")String zip,
			@RequestParam("country")String country,
			@RequestParam("permitType")String permitType,			
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.checkPermitRequirment ::: START");
		//Response response = null;
		PermitCheckResponse resp = new PermitCheckResponse();
		PermitCheckRequest tosreq=tosRequestHandler.checkPermitRequirmentRequest(streetName,streetNum,poBox,apartmentNum,city, state, zip,country, permitType,companyCode);
		resp=tosBO.checkPermitRequirment(tosreq, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.checkPermitRequirment ::: END");
		return resp;
	}
	
	/**
	 * @author ahanda1
	 * @param esid
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/tos/getESIDProfile", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public EsidProfileResponse getESIDProfile(
			@RequestParam("esid")String esid,
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getESIDProfile ::: START");
		//Response response = null;
		EsidProfileResponse resp = new EsidProfileResponse();
		
		resp=tosBO.getESIDProfile(esid, companyCode,httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getESIDProfile ::: END");
		return resp;
	}
	
	
	/*@POST
	@Path("validateAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response validateAddress(
			@RequestParam("streetName")String streetName,
			@RequestParam("streetNum")String streetNum,
			@RequestParam("poBox")String poBox,
			@RequestParam("apartmentNum")String apartmentNum,
			@RequestParam("city") String city,
			@RequestParam("state")String state,
			@RequestParam("zip")String zip,
			@RequestParam("country")String country,			
			@RequestParam("companyCode") String companyCode){
		
		logger.info("TOSResource.validateAddress ::: START");
		Response response = null;
		ContractDataResponse resp = new ContractDataResponse();
		AddressValidateRequest tosreq=tosRequestHandler.validateAddressRequest(streetName,streetNum,poBox,apartmentNum,city, state, zip,country);
		resp=tosBO.getContractData(tosreq, companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.validateAddress ::: END");
		return response;
	}*/
	
	/**
	 * Web method for AMB eligibility during TOS flow
	 * @author DKrishn1
	 * @param TosAmbWebRequest
	 * @return TosAmbWebResponse
	 */
	@PostMapping(value = "/tos/getTosAMBDetails", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TosAmbWebResponse getTosAMBDetails(@RequestParam("campaignCode") String campaignCode,
			@RequestParam("esid") String esid,
			@RequestParam("contractAccountNumber") String contractAccountNumber,
			@RequestParam("contractID") String contractID,
			@RequestParam("offerCellCd")String offerCellCd,
			@RequestParam("offerCode")String offerCode,
			@RequestParam("companyCode")String companyCode) {
		TosAmbWebResponse tosAmbResponse = new TosAmbWebResponse();
		logger.info("TOSResource.getTosAMBDetails ::: START");
		//Response response = null;
		
		com.multibrand.domain.TosAmbWebRequest req = tosRequestHandler.createWebTosAMBRequest(campaignCode, esid, contractAccountNumber, contractID, offerCellCd, offerCode, companyCode);
		tosAmbResponse = tosBO.getTosAMBDetails(req, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(tosAmbResponse).build();
		
		logger.info("TOSResource.getESIDProfile ::: END");
		return tosAmbResponse;
	}
	
	/**
	 * @author vanagani
	 * @param request TOSEligibleNonEligibleProductsRequest
	 * @return
	 */
	@PostMapping(value = "/tos/tosEligibleNonEligibleProducts", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE  }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProducts(@Valid TOSEligibleNonEligibleProductsRequest request){
		logger.debug("TOSResource.tosEligibleNonEligibleProducts ::: START");
		
		TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProductsResponse = tosBO.tosEligibleNonEligibleProducts(request);
		
		//Response response = Response.status(200).entity(tosEligibleNonEligibleProductsResponse).build();
		
		logger.debug("TOSResource.tosEligibleNonEligibleProducts ::: END");
		return tosEligibleNonEligibleProductsResponse;		
		
	}
	
	/**
	 * @author vanagani
	 * @param request TOSSubmitEligibleProductsRequest
	 * @return
	 */
	@PostMapping(value = "/tos/tosSubmitEligibleProducts", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE  }, produces = {	MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })	
	public TOSSubmitEligibleProductsResponse tosSubmitEligibleProducts(@Valid TOSSubmitEligibleProductsRequest request){
		logger.debug("TOSResource.tosSubmitEligibleProducts ::: START");
		
		TOSSubmitEligibleProductsResponse tosSubmitEligibleProductsResponse = tosBO.tosSubmitEligibleProducts(request);
		
		//Response response = Response.status(200).entity(tosSubmitEligibleProductsResponse).build();
		
		logger.debug("TOSResource.tosSubmitEligibleProducts ::: END");
		return tosSubmitEligibleProductsResponse;		
		
	}
	
}
