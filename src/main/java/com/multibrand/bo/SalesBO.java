package com.multibrand.bo;

import java.util.Arrays;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.helper.OeBoHelper;
import com.multibrand.domain.EsidAddressResponse;
import com.multibrand.dto.request.AffiliateOfferRequest;

import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidDetailsRequest;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.SalesCleanupAddressRequest;
import com.multibrand.dto.request.SalesCreditCheckRequest;
import com.multibrand.dto.request.SalesCreditReCheckRequest;

import com.multibrand.dto.request.SalesEnrollmentRequest;

import com.multibrand.dto.request.SalesEsidCalendarRequest;
import com.multibrand.dto.request.SalesOfferDetailsRequest;
import com.multibrand.dto.request.SalesOfferRequest;
import com.multibrand.dto.request.SalesTDSPRequest;
import com.multibrand.dto.request.ValidateAddressRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidDetailsResponse;
import com.multibrand.dto.response.EsidValidationAddressResponse;
import com.multibrand.dto.response.IdentityResponse;
import com.multibrand.dto.response.SalesBaseResponse;
import com.multibrand.dto.response.SalesCleanupAddressResponse;
import com.multibrand.dto.response.SalesEnrollmentResponse;
import com.multibrand.dto.response.SalesOfferDetailsResponse;
import com.multibrand.dto.response.SalesOfferResponse;
import com.multibrand.dto.response.SalesTDSPResponse;
import com.multibrand.dto.response.ServiceLocationResponse;
import com.multibrand.dto.response.ValidateAddressResponse;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.LoggerUtil;
import com.multibrand.util.Token;
import com.multibrand.vo.request.SalesTokenRequest;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.ProspectDataInternalResponse;
import com.multibrand.vo.response.ProspectDataResponse;
import com.multibrand.vo.response.SalesCreditCheckResponse;
import com.multibrand.vo.response.SalesEsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.SalesTokenResponse;

@Component
public class SalesBO extends OeBoHelper implements Constants {

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");

	@Autowired
	private OEBO oeBO;
	
	@Autowired
	private ValidationBO validationBO;

	@Context
	private HttpServletRequest httpRequest;
	
	@Autowired
	OERequestHandler oeRequestHandler;

	public Response performPosidAndBpMatch(IdentityRequest request) throws Exception {
		PerformPosIdAndBpMatchRequest performPosidAndBPMatchRequest = new PerformPosIdAndBpMatchRequest();
		Response response = null;
		try {
			
			if(StringUtils.isNotEmpty(request.getGuid()) && StringUtils.isEmpty(request.getTrackingId())){
				IdentityResponse bpMatchResponse = new IdentityResponse();
				bpMatchResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				bpMatchResponse.setErrorCode(HTTP_BAD_REQUEST);
				bpMatchResponse.setErrorDescription("trackingId cannot be empty");					
				response=Response.status(Response.Status.BAD_REQUEST).entity(bpMatchResponse).build();
				return response;
			} else if(StringUtils.isNotEmpty(request.getTrackingId()) && StringUtils.isEmpty(request.getGuid())){
				IdentityResponse bpMatchResponse = new IdentityResponse();
				bpMatchResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				bpMatchResponse.setErrorCode(HTTP_BAD_REQUEST);
				bpMatchResponse.setErrorDescription("guid cannot be empty");					
				response=Response.status(Response.Status.BAD_REQUEST).entity(bpMatchResponse).build();
				return response;
			}
			
			BeanUtils.copyProperties(request, performPosidAndBPMatchRequest);
			response = oeBO.performPosidAndBpMatch(performPosidAndBPMatchRequest);
			if (response.getEntity() instanceof GenericResponse) {
				GenericResponse affiliateResponse = (GenericResponse) response.getEntity();
				IdentityResponse identityResponse = new IdentityResponse();
				BeanUtils.copyProperties(affiliateResponse, identityResponse);
				response = Response.status(response.getStatus()).entity(identityResponse).build();
				response.getMetadata().add(CONST_TRACKING_ID, identityResponse.getTrackingId());
				response.getMetadata().add(CONST_GUID, identityResponse.getGuid());
				
				if(StringUtils.isEmpty(identityResponse.getTrackingId())){
					IdentityResponse bpMatchResponse = new IdentityResponse();
					bpMatchResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					bpMatchResponse.setErrorCode(HTTP_INTERNAL_SERVER_ERROR);
					bpMatchResponse.setErrorDescription("Database save operation failed!");					
					response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(bpMatchResponse).build();
					return response;
				} 
				
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch", e);
			throw e;
		}
		return response;
	}

	/**
	 * @author Kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SalesBaseResponse getSalesESIDAndCalendarDates(SalesEsidCalendarRequest salesEsidCalendarRequest,
			HttpServletRequest httpRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEsidInfoTdspCalendarResponse salesEsidInfoTdspCalendarResponse = new SalesEsidInfoTdspCalendarResponse();
		String bpMatchFlag = null;
		SalesBaseResponse response = null;
		try {
			serviceLoationResponse = oeBO.getEnrollmentData(salesEsidCalendarRequest.getTrackingId(),
					salesEsidCalendarRequest.getGuid());
			if (null != serviceLoationResponse) {
				if(!oeBO.isEnrollmentAlreadySubmitted(serviceLoationResponse))
					{
					bpMatchFlag = extractBpMatchFlag(salesEsidCalendarRequest,serviceLoationResponse );
					EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO.getESIDAndCalendarDates(
							salesEsidCalendarRequest.getCompanyCode(), salesEsidCalendarRequest.getAffiliateId(),
							salesEsidCalendarRequest.getBrandId(), serviceLoationResponse.getServStreetNum(),
							serviceLoationResponse.getServStreetName(), serviceLoationResponse.getServStreetAptNum(),
							serviceLoationResponse.getServZipCode(), serviceLoationResponse.getTdspCode(),
							serviceLoationResponse.getServiceRequestTypeCode(), salesEsidCalendarRequest.getTrackingId(),
							bpMatchFlag, salesEsidCalendarRequest.getLanguageCode(), serviceLoationResponse.getEsid(),
							httpRequest.getSession(true).getId(), serviceLoationResponse.getErrorCode(), serviceLoationResponse,
							salesEsidCalendarRequest.getCallExecuted()
							);
	
					BeanUtils.copyProperties(esidInfoTdspResponse, salesEsidInfoTdspCalendarResponse);
					response = salesEsidInfoTdspCalendarResponse;
				} else{
					response = salesEsidInfoTdspCalendarResponse.populateAlreadySubmittedEnrollmentResponse();
				}
			} else {
				response = salesEsidInfoTdspCalendarResponse.populateInvalidTrackingAndGuidResponse();
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch" + e.getMessage());
			throw e;
		}

		return response;
	}
	
	private String extractBpMatchFlag(SalesEsidCalendarRequest salesEsidCalendarRequest, 
			ServiceLocationResponse serviceLoationResponse ) throws Exception {
		String bpMatchFlag = null;
		if ((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(), BPSD))
				&& (StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(), FLAG_YES))) {
			if (StringUtils.isNotBlank(serviceLoationResponse.getErrorCdlist())) {
				String[] errorCdArray = serviceLoationResponse.getErrorCdlist().split(ERROR_CD_LIST_SPLIT_PATTERN);

				LinkedHashSet<String> serviceLocationResponseErrorList = new LinkedHashSet<>(Arrays.asList(errorCdArray));
				//BeanUtils.copyProperties(serviceLocationResponseErrorListTemp, serviceLocationResponseErrorList);
				if(!serviceLocationResponseErrorList.contains(PBSD)){
				serviceLocationResponseErrorList.remove(BPSD);
				/*boolean errorCode = oeBO.updateErrorCodeinSLA(salesEsidCalendarRequest.getTrackingId(),
						salesEsidCalendarRequest.getGuid(), StringUtils.EMPTY,
						StringUtils.join(serviceLocationResponseErrorList, SYMBOL_PIPE));*/
				serviceLoationResponse.setErrorCode(StringUtils.EMPTY);
				serviceLoationResponse.setErrorCdlist(StringUtils.join(serviceLocationResponseErrorList, SYMBOL_PIPE));
				}else{
					bpMatchFlag = BPSD;
				}
			}

			

		} else if ((StringUtils.equalsIgnoreCase(serviceLoationResponse.getErrorCode(), BPSD))
				&& (!StringUtils.equalsIgnoreCase(salesEsidCalendarRequest.getPastServiceMatchedFlag(), FLAG_YES))) {
			bpMatchFlag = BPSD;
		}
		
		return bpMatchFlag;
		
	}
	
	public SalesTokenResponse getTokenResponse(SalesTokenRequest request) {

		logger.debug("getTokenResponse :::::::: Start");

		SalesTokenResponse tokenizedResponse = new SalesTokenResponse();
		String returnToken = null;
		
		if (StringUtils.isBlank(request.getActionCode())
				|| (!request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())
						&& !request.getActionCode().equalsIgnoreCase(Token.getSsnAction()))) {
			tokenizedResponse.setReturnToken(returnToken);
			tokenizedResponse.setStatusCode(STATUS_CODE_STOP);
			tokenizedResponse.setErrorCode(HTTP_BAD_REQUEST);
			tokenizedResponse.setErrorDescription(ACTION_CODE_INVALID);
			tokenizedResponse.setHttpStatus(Response.Status.BAD_REQUEST);
			logger.debug("getTokenResponse :::::::: Ends with invalid action code");
			return tokenizedResponse;
		}

		if (request.getActionCode().equalsIgnoreCase(Token.getCreditCardAction())) {
			returnToken = Token.getToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getBankAccountAction())) {
			returnToken = Token.getBankAccountToken(request.getNumToBeTokenized());
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getDriverLicenceAction())) {
			String tdl=request.getNumToBeTokenized();
			if(request.getNumToBeTokenized().length()<10)
			  tdl=CommonUtil.addLeadingZeroes(request.getNumToBeTokenized(), 10);
			returnToken = Token.getDRLToken(tdl);
			tokenizedResponse.setReturnToken(returnToken);
		} else if (request.getActionCode().equalsIgnoreCase(Token.getSsnAction())) {
			String ssn=request.getNumToBeTokenized();
			if(request.getNumToBeTokenized().length()<9)
				ssn=CommonUtil.addLeadingZeroes(request.getNumToBeTokenized(), 9);
			returnToken = Token.getSSNToken(ssn);
			tokenizedResponse.setReturnToken(returnToken);
		}

		logger.debug("getTokenResponse :::::::: End");
		return tokenizedResponse;
	}

	public SalesOfferResponse getSalesOffers(SalesOfferRequest request, String sessionId) {
		AffiliateOfferRequest affiliateOfferRequest = new AffiliateOfferRequest();
		SalesOfferResponse salesOfferResponse = new SalesOfferResponse();
		try {
			BeanUtils.copyProperties(request, affiliateOfferRequest);
			AffiliateOfferResponse affiliateOfferResponse = oeBO.getAffiliateOffers(affiliateOfferRequest, sessionId);
			BeanUtils.copyProperties(affiliateOfferResponse, salesOfferResponse);
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getSalesOffers()", e);
			throw e;
		}
		return salesOfferResponse;
	}

	public SalesBaseResponse getProspectData(ProspectDataRequest request) {
		ProspectDataResponse prospectDataExternalResponse = null;
		try {
			ProspectDataInternalResponse prospectDataInternalResponse = oeBO.getProspectData(request);
			if (StringUtils.equals(CHANNEL_AA, request.getChannelType())
					|| StringUtils.equals(CHANNEL_AFF, request.getChannelType())) {
				prospectDataExternalResponse = new ProspectDataResponse();
				BeanUtils.copyProperties(prospectDataInternalResponse, prospectDataExternalResponse);
				return prospectDataExternalResponse;
			} else {
				return prospectDataInternalResponse;
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getSalesOffers()", e);
			throw e;
		}	
	}
	
	public SalesBaseResponse  performCreditCheck(SalesCreditCheckRequest request){
		
		SalesCreditCheckResponse salesCreditCheckResponse = new SalesCreditCheckResponse();
		
		NewCreditScoreResponse newCreditScoreResponse = new NewCreditScoreResponse();
		ServiceLocationResponse serviceLocationResponse = null;
		SalesBaseResponse response = null;
		try{
			serviceLocationResponse=oeBO.getEnrollmentData(request.getTrackingId(),request.getGuid() );
			if (null!= serviceLocationResponse){
				
				if(!oeBO.isEnrollmentAlreadySubmitted(serviceLocationResponse))
				{
					
					CreditCheckRequest creditCheckRequest = oeRequestHandler.createCreditCheckRequest(request, serviceLocationResponse);
					newCreditScoreResponse =  oeBO
					.performCreditCheck(oeRequestHandler
							.createNewCreditScoreRequest(creditCheckRequest),
							creditCheckRequest, serviceLocationResponse);
					BeanUtils.copyProperties(newCreditScoreResponse, salesCreditCheckResponse);	
					response= 	salesCreditCheckResponse;
				} else {
					response =salesCreditCheckResponse.populateAlreadySubmittedEnrollmentResponse();
				}
			}else{
				response =salesCreditCheckResponse.populateInvalidTrackingAndGuidResponse();
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performCreditCheck()", e);
			throw e; 
		}	
		return response;
	}
	
	public SalesBaseResponse  performCreditReCheck(SalesCreditReCheckRequest request){
		
		SalesCreditCheckResponse salesCreditCheckResponse = new SalesCreditCheckResponse();
		
		NewCreditScoreResponse newCreditScoreResponse = new NewCreditScoreResponse();
		ServiceLocationResponse serviceLocationResponse = null;
		SalesBaseResponse response = null;
		try{
			serviceLocationResponse=oeBO.getEnrollmentData(request.getTrackingId(),request.getGuid() );
			if (null!= serviceLocationResponse){
				
				if(!oeBO.isEnrollmentAlreadySubmitted(serviceLocationResponse))
				{
					if(!StringUtils.equalsIgnoreCase(serviceLocationResponse.getServiceRequestTypeCode(), S)){
						if(StringUtils.isEmpty(request.getMviDate())) {
							response = salesCreditCheckResponse;
							response.setStatusCode(Constants.STATUS_CODE_STOP);
							response.setErrorCode(HTTP_BAD_REQUEST);
							response.setErrorDescription("mviDate is required for move-in");
							response.setHttpStatus(Response.Status.BAD_REQUEST);
							return response;
						}
					}
					
					CreditCheckRequest creditCheckRequest = oeRequestHandler.createCreditReCheckRequest(request, serviceLocationResponse);
					newCreditScoreResponse =  oeBO
					.performCreditCheck(oeRequestHandler
							.createNewCreditScoreRequest(creditCheckRequest),
							creditCheckRequest, serviceLocationResponse);
					BeanUtils.copyProperties(newCreditScoreResponse, salesCreditCheckResponse);	
					response= 	salesCreditCheckResponse;	
				} else {
					response =salesCreditCheckResponse.populateAlreadySubmittedEnrollmentResponse();
				}
			}else{
				response =salesCreditCheckResponse.populateInvalidTrackingAndGuidResponse();
			}
		} catch (Exception e) {
			logger.error("Exception in SalesBO.performCreditCheck()", e);
			throw e; 
		}
		return response;

	}

	/**
	 * @author Asingh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public SalesEnrollmentResponse submitEnrollment(SalesEnrollmentRequest enrollmentRequest) throws Exception {
		ServiceLocationResponse serviceLoationResponse = null;
		SalesEnrollmentResponse salesEnrollmentresponse = new SalesEnrollmentResponse();
		try {
			serviceLoationResponse = oeBO.getEnrollmentData(enrollmentRequest.getTrackingId(),enrollmentRequest.getGuid());
			if (null != serviceLoationResponse) {
				if(!oeBO.isEnrollmentAlreadySubmitted(serviceLoationResponse))
				{
					EnrollmentRequest request = oeRequestHandler.createSubmitEnrollmentRequest(enrollmentRequest, serviceLoationResponse);
					EnrollmentResponse enrollmentResponse = oeBO.submitEnrollment(request, serviceLoationResponse);
					BeanUtils.copyProperties(enrollmentResponse, salesEnrollmentresponse);
				} else {
					salesEnrollmentresponse.populateAlreadySubmittedEnrollmentResponse();
				}
			} else {
				salesEnrollmentresponse.populateInvalidTrackingAndGuidResponse();
			}

		} catch (Exception e) {
			logger.error("Exception in SalesBO.performPosidAndBpMatch" + e.getMessage());
			throw e;
		}

		return salesEnrollmentresponse;

	}
	
	public SalesCleanupAddressResponse getCleanedUpAddress(SalesCleanupAddressRequest salesCleanupAddressRequest) throws Exception{
		SalesCleanupAddressResponse salesCleanupAddressResponse = new SalesCleanupAddressResponse();
		ValidateAddressRequest validateAddressRequest = new ValidateAddressRequest();
		try {
			
			// Do cleanup address input parameters extra validation errors check
			if (StringUtils.isBlank(salesCleanupAddressRequest.getStreetAddress())
					&& StringUtils.isBlank(salesCleanupAddressRequest.getPoBox())){
				salesCleanupAddressResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				salesCleanupAddressResponse.setErrorCode(HTTP_BAD_REQUEST);
				salesCleanupAddressResponse.setErrorDescription(BILLING_ADDRESS_EMPTY_ERROR_MESSAGE);
				salesCleanupAddressResponse.setHttpStatus(Response.Status.BAD_REQUEST);
				return salesCleanupAddressResponse;
			}else if (StringUtils.isNotBlank(salesCleanupAddressRequest.getStreetAddress())
					&& StringUtils.isNotBlank(salesCleanupAddressRequest.getPoBox())) {
				salesCleanupAddressResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				salesCleanupAddressResponse.setErrorCode(HTTP_BAD_REQUEST);
				salesCleanupAddressResponse.setErrorDescription(BILLING_ADDRESS_ERROR_MESSAGE);
				salesCleanupAddressResponse.setHttpStatus(Response.Status.BAD_REQUEST);
				return salesCleanupAddressResponse;
			}
			BeanUtils.copyProperties(salesCleanupAddressRequest, validateAddressRequest);
			// Do cleanup address using Trillium
			ValidateAddressResponse validateAddressResponse = validationBO.validateAddress(validateAddressRequest);
			BeanUtils.copyProperties(validateAddressResponse, salesCleanupAddressResponse);
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getCleanupAddress()", e);
			throw e;
		}
		return salesCleanupAddressResponse;
	}

	public AffiliateOfferResponse getOfferDetails(SalesOfferDetailsRequest salesOfferDetailsRequest){
	
		AffiliateOfferResponse affiliateOfferResponse = new AffiliateOfferResponse();
		try {
			affiliateOfferResponse = oeBO.getOfferDetails(salesOfferDetailsRequest);
		} catch (Exception e) {
			logger.error("Exception in SalesBO.getOfferDetails" + e.getMessage());
			throw e;
		}
		return affiliateOfferResponse;
	}

	public SalesTDSPResponse getTDSP(SalesTDSPRequest salesTDSPRequest, String sessionId) {
		SalesTDSPResponse salesTDSPResponse = new SalesTDSPResponse();
		try {
		
			EsidDetailsRequest esidDetailsRequest = new EsidDetailsRequest();
			BeanUtils.copyProperties(salesTDSPRequest, esidDetailsRequest);
			EsidDetailsResponse esidDetailsResponse = oeBO.getEsidDetails(esidDetailsRequest, sessionId);
			BeanUtils.copyProperties(esidDetailsResponse, salesTDSPResponse);
		}catch(Exception e){
			logger.error("Exception in SalesBO.getTDSP()", e);
			throw e;
		}
		return salesTDSPResponse;
	}

	public EsidValidationAddressResponse validateESID(String esid) {
		EsidValidationAddressResponse esidValidationAddressResponse  = new EsidValidationAddressResponse();
		EsidAddressResponse esidAddressResponse = oeBO.validateESID(esid);
		esidValidationAddressResponse = constructEsidValidationResponse(esidAddressResponse,esidValidationAddressResponse);
		return esidValidationAddressResponse;
	}
	
	private EsidValidationAddressResponse constructEsidValidationResponse(EsidAddressResponse esidAddressResponse,
			EsidValidationAddressResponse esidValidationAddressResponse){
	    esidValidationAddressResponse.setStrAddressLine1(esidAddressResponse.getStrAddressLine1());
		esidValidationAddressResponse.setStrAddressLine2(esidAddressResponse.getStrAddressLine2());
		esidValidationAddressResponse.setStrAptNumber(esidAddressResponse.getStrAptNumber());
		esidValidationAddressResponse.setStrCity(esidAddressResponse.getStrCity());
		esidValidationAddressResponse.setStrErrCode(esidAddressResponse.getStrErrCode());
		esidValidationAddressResponse.setStrErrMessage(esidAddressResponse.getStrErrMessage());
		esidValidationAddressResponse.setStrEsidClass(esidAddressResponse.getStrEsidClass());
		esidValidationAddressResponse.setStrEsidDeposit(esidAddressResponse.getStrEsidDeposit());
		esidValidationAddressResponse.setStrEsidTdsp(esidAddressResponse.getStrEsidTdsp());
		esidValidationAddressResponse.setStrPremiseType(esidAddressResponse.getStrPremiseType());
		esidValidationAddressResponse.setStrState(esidAddressResponse.getStrState());
		esidValidationAddressResponse.setStrStatus(esidAddressResponse.getStrStatus());
		esidValidationAddressResponse.setStrZip(esidAddressResponse.getStrZip());
		esidValidationAddressResponse.setStrZipFour(esidAddressResponse.getStrZipFour());
		
		return esidValidationAddressResponse;
	}

}
