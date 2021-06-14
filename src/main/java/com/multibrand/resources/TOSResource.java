package com.multibrand.resources;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.TOSBO;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.resources.requestHandlers.TOSRequestHandler;
import com.multibrand.util.CommonUtil;
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

@Component
@Path("tos")
public class TOSResource extends BaseResource{
	
	@Autowired
	TOSRequestHandler tosRequestHandler;
	
	@Autowired
	TOSBO tosBO;
	
	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;
	
	@Context 
	private HttpServletRequest httpRequest;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;
	
	/**
	 * @author ahanda1
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("checkPendingMVO")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response checkPendingMVO(@FormParam("contractNumber")String contractNumber, @FormParam("companyCode") String companyCode){
		logger.debug("TOSResource.checkPendingMVO ::: START");
		CheckPendingMVORequest request = tosRequestHandler.createRequestCheckPendingMVO(contractNumber, companyCode);	
		
		
		CheckPendingMVOResponse checkPendingMVOResp = tosBO.checkPendingMVO(request, companyCode, httpRequest.getSession(true).getId());
		
		Response response = null;
		
		response = Response.status(200).entity(checkPendingMVOResp).build();
		
		logger.debug("TOSResource.checkPendingMVO ::: END");
		return response;		
		
	}
	
	/**
	 * @author ahanda1
	 * @param accountNumber
	 * @param bpid
	 * @param pointofDeliveryID
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("checkPendingMVI")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response checkPendingMVI(@FormParam("accountNumber")String accountNumber, @FormParam("bpid")String bpid, @FormParam("pointofDeliveryID") String pointofDeliveryID, @FormParam("companyCode") String companyCode){
		logger.debug("TOSResource.checkPendingMVI ::: START");
		
		CheckPendingMoveInRequest request = tosRequestHandler.createRequestCheckPendingMVI(accountNumber, bpid, pointofDeliveryID, companyCode);	
		
		
		CheckPendingMoveInResponse checkPendingMVIResp = tosBO.checkPendingMVI(request, companyCode,  httpRequest.getSession(true).getId());
		
		Response response = null;
		
		response = Response.status(200).entity(checkPendingMVIResp).build();
		
		logger.debug("TOSResource.checkPendingMVI ::: END");
		return response;		
		
	}
	
	
	@POST
	@Path("ESIDForAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getESIDForAddress(
			@FormParam("apartmentNumber")String apartmentNumber, 
			@FormParam("city")String city, 
			@FormParam("country")String country,
			//@FormParam("POBox")String poBox,
			@FormParam("state")String state,
			@FormParam("streetName")String streetName,
			@FormParam("streetNumber")String streetNumber,
			@FormParam("zip")String zip, 
			@FormParam("companyCode") String companyCode){
		
		logger.debug("TOSResource.getESIDForAddress ::: START");
		
		ESIDForAddressResponse esidForAddressResponse = new ESIDForAddressResponse();
		Response response = null;
		
		try {
			EsidRequest request = tosRequestHandler.createRequestESIDFormAddress(apartmentNumber, city, country, state, streetName, streetNumber, zip, companyCode);	
				
			EsidResponse getEsiidResponse = oeBO.getESIDDetails(request);
			
			esidForAddressResponse = tosBO.removeInactiveEsids(getEsiidResponse);
			esidForAddressResponse.setCompanyCode(companyCode);
			
					
			response = Response.status(Response.Status.OK).entity(esidForAddressResponse).build();
			} catch (RemoteException e) {
				logger.error(e);
				esidForAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				esidForAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), esidForAddressResponse);			
			} catch (Exception e) {
				logger.error(e);
				esidForAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				esidForAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), esidForAddressResponse);	
			}
		
		logger.debug("TOSResource.getESIDForAddress ::: END");
		return response;		
		
	}
	
	
/*	@POST
	@Path("TOSPipelineOffers")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response TOSPipelineOffers(
			@FormParam("apartmentNumber")String apartmentNumber, 
			@FormParam("city")String city, 
			@FormParam("country")String country,
			@FormParam("POBox")String poBox,
			@FormParam("state")String state,
			@FormParam("streetName")String streetName,
			@FormParam("streetNumber")String streetNumber,
			@FormParam("zip")String zip,
			@FormParam("promocode")String promocode,
			@FormParam("companyCode") String companyCode,
			@FormParam("language")String language){
		
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
	@POST
	@Path("getOfferOfContract")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getOfferOfContract(
			@FormParam("contractNumber")String contractNumber,
			@FormParam("companyCode") String companyCode,
			@FormParam("language")String language){
		
		logger.info("TOSResource.getOfferOfContract ::: START");
		Response response = null;
		OfferOfContractResponse tospor = new OfferOfContractResponse();
		OfferOfContractRequest tosreq=tosRequestHandler.getOfferOfContractRequest(contractNumber, companyCode, language);
		tospor=tosBO.getOfferOfContract(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(tospor).build();
		logger.info(" START ******* Input for the getOfferOfContract API**********");
		
		logger.info("	contractNumber	 "+contractNumber);
		logger.info("	companyCode	 "+companyCode);
		logger.info("	language	 "+language);
		
		logger.info(" OUTPUT of the getOfferOfContract API*************");
		String json = CommonUtil.wirteObjectToJson(response);
		logger.info(" Response  - "+json);
		
		
		logger.info("TOSResource.getOfferOfContract ::: END");
		return response;
	}
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param contractAccountNumber
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("getProgramAccountInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getProgramAccountInfo(
			@FormParam("businessPartnerId")String bpid,
			@FormParam("contractAccountNumber")String contractAccountNumber,
			@FormParam("contractNumber")String contractNumber,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getProgramAccountInfo ::: START");
		Response response = null;
		ProgramAccountInfoResponse tospor = new ProgramAccountInfoResponse();
		ProgramAccountInfoRequest tosreq=tosRequestHandler.getProgramAccountInfoRequest(bpid, contractAccountNumber, contractNumber, companyCode);
		tospor=tosBO.getProgramAccountInfo(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(tospor).build();
		
		logger.info("TOSResource.getProgramAccountInfo ::: END");
		return response;
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
	@POST
	@Path("updateContactLog")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response updateContactLog(
			@FormParam("businessPartnerId")String bpid,
			@FormParam("contractAccountNumber")String contractAccountNumber,
			@FormParam("contactClass")String contactClass,
			@FormParam("contactActivity")String contactActivity,
			@FormParam("commitFlag")String commitFlag,
			@FormParam("contactType")String contactType,
			@FormParam("division") String division,
			@FormParam("textLines")String textLines,
			@FormParam("formatCol")String formatCol,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.updateContactLog ::: START");
		Response response = null;
		CreateContactLogResponse resp = new CreateContactLogResponse();
		CreateContactLogRequest tosreq=tosRequestHandler.getUpdateContactLogRequest(bpid, contractAccountNumber, contactClass, contactActivity, commitFlag, contactType, division, textLines, formatCol, companyCode);
		resp=tosBO.updateContactLog(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.updateContactLog ::: END");
		return response;
	}
	
	
	/**
	 * @author ahanda1
	 * @param zip
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("getTdspByZip")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getTdspByZip(
			@FormParam("zip")String zip,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTdspByZip ::: START");
		Response response = null;
		TdspByZipResponse resp = new TdspByZipResponse();
		//TdspByZipRequest tosreq=tosRequestHandler.getTdspByZipRequest(zip, companyCode);
		resp=tosBO.getTdspByZip(zip,companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTdspByZip ::: END");
		return response;
	}
	
	
	@POST
	@Path("getTDSPFromESID")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getTDSPFromESID(
			@FormParam("esid")String esid,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTDSPFromESID ::: START");
		Response response = null;
		TdspByESIDResponse resp = new TdspByESIDResponse();
		resp=tosBO.getTDSPFromESID(esid,companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTDSPFromESID ::: END");
		return response;
	}
	
	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param ca
	 * @param companyCode
	 * @return
	 */
	
	@POST
	@Path("getContractData")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getContractData(
			@FormParam("businessPartnerId")String bpid,
			@FormParam("contractAccountNumber")String ca,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getContractData ::: START");
		Response response = null;
		ContractDataResponse resp = new ContractDataResponse();
		ContractDataRequest tosreq=tosRequestHandler.getContractDataRequest(bpid, ca, companyCode);
		resp=tosBO.getContractData(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getContractData ::: END");
		return response;
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
	@POST
	@Path("saveTransferServiceDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response saveTransferServiceDetails(
			@FormParam("businessPartnerId") String BusinessPartnerNumber, 
			@FormParam("contractAccountNumber") String ContractAccountNumber,
			@FormParam("checkDigit")String checkDigit,
			@FormParam("contractNumber") String ContractNumber,
			@FormParam("contractAccountName")String contractAccountName,
			@FormParam("esid")String PointofDeliveryID,  
			@FormParam("streetNumber") String StreetNumber,
			@FormParam("streetName")String StreetName,
			@FormParam("apartmentNumber")String ApartmentNumber,
			@FormParam("city") String City,
			 @FormParam("state")String State,
			 @FormParam("zip")String Zip,
			 @FormParam("country")String Country,
			 @FormParam("poBox")String POBox,
			 @FormParam("billingStreetNumber")String BillingStreetNumber,
			 @FormParam("billingStreetName")String BillingStreetName,
			 @FormParam("billingApartmentNumber")String BillingApartmentNumber,
			 @FormParam("billingCity")String BillingCity,
			 @FormParam("billingState")String BillingState,
			 @FormParam("billingZip")String BillingZip,
			 @FormParam("billingCountry")String BillingCountry,
			 @FormParam("billingPoBox")String BillingPOBox,
			 @FormParam("mviDate")String MVIDate,
			 @FormParam("mvoDate")String MVODate,
			 @FormParam("inMeterAccessFlag")String InMeterAccessFlag,
			 @FormParam("outMeterAccessFlag")String OutMeterAccessFlag,
			 @FormParam("permitType")String PermitType,
			 @FormParam("campaignCode")String CampaignCode,
			 @FormParam("offerCellTrackingCode")String OfferCellTrackingCode,
			 @FormParam("offerCode")String OfferCode,
			 @FormParam("contactEmail")String ContactEmail,
			 @FormParam("okToEmailFlag")String OkToEmailFlag,
			 @FormParam("phoneAvailableFlag")String PhoneAvailableFlag,
			 @FormParam("contactName")String contactName,
			 @FormParam("contactPhoneNumber")String ContactPhoneNumber,
			 @FormParam("newServiceId")String newServiceID,
			 @FormParam("priorityMoveIn")String priorityMoveIn,
			 @FormParam("companyCode")String companyCode,
			 @FormParam("email")String email,
			 @FormParam("languageCode") String languageCode,
			 @FormParam("billDeliveryMethod")String billDeliveryMethod,
			 @FormParam("additionalProgram")String additionalProgs,
			 @FormParam("cancelFee")String cancelFee,
			 @FormParam("planName")String planName,
			 @FormParam("planType")String planType,
			 @FormParam("contractTerm")String contractTerm,
			 @FormParam("avgPrice")String avgPrice,
			 @FormParam("tosUrl")String tosUrl,
			 @FormParam("tosId")String tosId,
			 @FormParam("eflUrl")String eflUrl,
			 @FormParam("eflId")String eflId,
			 @FormParam("yraacUrl")String yraacUrl,
			 @FormParam("yraacId")String yraacId,
			 @FormParam("existingStreetNumber") String existingStreetNumber,
		 	 @FormParam("existingStreetName")String existingStreetName,
			 @FormParam("existingApartmentNumber")String existingApartmentNumber,
			 @FormParam("existingCity") String existingCity,
			 @FormParam("existingState")String existingState,
			 @FormParam("existingZip")String existingZip,
			 @FormParam("existingCountry")String existingCountry,
			 @FormParam("ambSignupOption")String ambSignupOption){
		
		logger.info("TOSResource.saveTransferServiceDetails ::: START");
		Response response = null;
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
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.saveTransferServiceDetails ::: END");
		return response;
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
	@POST
	@Path("getTDSPSpecificCalendarDates")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getTDSPSpecificCalendarDates(
			@FormParam("startDate")String startDate,
			@FormParam("endDate")String endDate,
			@FormParam("tdsp")String tdsp,
		//	@FormParam("trackingNum")String trackingNum,
			@FormParam("esiid") String esiid,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getTDSPSpecificCalendarDates ::: START");
		Response response = null;
		OetdspResponse resp = new OetdspResponse();
		OetdspRequest tosreq=tosRequestHandler.getTDSPSpecificCalendarDatesRequest(startDate,endDate,tdsp,companyCode,esiid);
		resp=tosBO.getTDSPSpecificCalendarDates(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getTDSPSpecificCalendarDates ::: END");
		return response;
	}	
	
	

	@POST
	@Path("checkPermitRequirment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response checkPermitRequirment(
			@FormParam("streetName")String streetName,
			@FormParam("streetNum")String streetNum,
			@FormParam("poBox")String poBox,
			@FormParam("apartmentNum")String apartmentNum,
			@FormParam("city") String city,
			@FormParam("state")String state,
			@FormParam("zip")String zip,
			@FormParam("country")String country,
			@FormParam("permitType")String permitType,			
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.checkPermitRequirment ::: START");
		Response response = null;
		PermitCheckResponse resp = new PermitCheckResponse();
		PermitCheckRequest tosreq=tosRequestHandler.checkPermitRequirmentRequest(streetName,streetNum,poBox,apartmentNum,city, state, zip,country, permitType,companyCode);
		resp=tosBO.checkPermitRequirment(tosreq, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.checkPermitRequirment ::: END");
		return response;
	}
	
	/**
	 * @author ahanda1
	 * @param esid
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("getESIDProfile")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getESIDProfile(
			@FormParam("esid")String esid,
			@FormParam("companyCode") String companyCode){
		
		logger.info("TOSResource.getESIDProfile ::: START");
		Response response = null;
		EsidProfileResponse resp = new EsidProfileResponse();
		
		resp=tosBO.getESIDProfile(esid, companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(resp).build();
		
		logger.info("TOSResource.getESIDProfile ::: END");
		return response;
	}
	
	
	/*@POST
	@Path("validateAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response validateAddress(
			@FormParam("streetName")String streetName,
			@FormParam("streetNum")String streetNum,
			@FormParam("poBox")String poBox,
			@FormParam("apartmentNum")String apartmentNum,
			@FormParam("city") String city,
			@FormParam("state")String state,
			@FormParam("zip")String zip,
			@FormParam("country")String country,			
			@FormParam("companyCode") String companyCode){
		
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
	
	@POST
	@Path("getTosAMBDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response getTosAMBDetails(@FormParam("campaignCode") String campaignCode,
			@FormParam("esid") String esid,
			@FormParam("contractAccountNumber") String contractAccountNumber,
			@FormParam("contractID") String contractID,
			@FormParam("offerCellCd")String offerCellCd,
			@FormParam("offerCode")String offerCode,
			@FormParam("companyCode")String companyCode) {
		TosAmbWebResponse tosAmbResponse = new TosAmbWebResponse();
		logger.info("TOSResource.getTosAMBDetails ::: START");
		Response response = null;
		
		com.multibrand.domain.TosAmbWebRequest req = tosRequestHandler.createWebTosAMBRequest(campaignCode, esid, contractAccountNumber, contractID, offerCellCd, offerCode, companyCode);
		tosAmbResponse = tosBO.getTosAMBDetails(req, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(tosAmbResponse).build();
		
		logger.info("TOSResource.getESIDProfile ::: END");
		return response;
	}
	
	/**
	 * @author vanagani
	 * @param request TOSEligibleNonEligibleProductsRequest
	 * @return
	 */
	@POST
	@Path("tosEligibleNonEligibleProducts")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response tosEligibleNonEligibleProducts(@Valid TOSEligibleNonEligibleProductsRequest request){
		logger.debug("TOSResource.tosEligibleNonEligibleProducts ::: START");
		
		TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProductsResponse = tosBO.tosEligibleNonEligibleProducts(request);
		
		Response response = Response.status(200).entity(tosEligibleNonEligibleProductsResponse).build();
		
		logger.debug("TOSResource.tosEligibleNonEligibleProducts ::: END");
		return response;		
		
	}
	
	/**
	 * @author vanagani
	 * @param request TOSSubmitEligibleProductsRequest
	 * @return
	 */
	@POST
	@Path("tosSubmitEligibleProducts")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public Response tosSubmitEligibleProducts(@Valid TOSSubmitEligibleProductsRequest request){
		logger.debug("TOSResource.tosSubmitEligibleProducts ::: START");
		
		TOSSubmitEligibleProductsResponse tosSubmitEligibleProductsResponse = tosBO.tosSubmitEligibleProducts(request);
		
		Response response = Response.status(200).entity(tosSubmitEligibleProductsResponse).build();
		
		logger.debug("TOSResource.tosSubmitEligibleProducts ::: END");
		return response;		
		
	}
	
	public void setHttpRequest(HttpServletRequest httpReq){
		this.httpRequest = httpReq;
	}
	
}
