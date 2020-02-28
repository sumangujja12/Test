
package com.multibrand.resources;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.ValidationBO;
import com.multibrand.dto.OESignupDTO;
import com.multibrand.dto.request.AffiliateOfferRequest;
import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidCalendarRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.GetOEKBAQuestionsRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.PerformPosIdAndBpMatchRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.UCCDataRequest;
import com.multibrand.dto.response.AffiliateOfferResponse;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.exception.OEException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.request.validation.BasicConstraint;
import com.multibrand.request.validation.SizeConstraint;
import com.multibrand.request.validation.ValidateGetMapppingRequestParam;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.TokenRequestVO;
import com.multibrand.vo.response.AgentDetailsResponse;
import com.multibrand.vo.response.EsidInfoTdspCalendarResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.PerformPosIdandBpMatchResponse;
import com.multibrand.vo.response.ProspectDataResponse;
import com.multibrand.vo.response.TokenizedResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;
import com.sun.jersey.api.core.InjectParam;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@Component
@Path("/sales")
public class SalesAPIResource extends BaseResource {
	
	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	// ~Autowire entries
	@Autowired
	OERequestHandler oeRequestHandler;

	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;

	/** Object of ValidationBO class. */
	@Autowired
	private ValidationBO validationBO;

	@Context
	private HttpServletRequest httpRequest;

	@Resource(name = "webI18nMessageSource")
	private WebI18nMessageSource msgSource;

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@GET
	@Path(API_OFFERS)	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAffiliateOffers(@InjectParam AffiliateOfferRequest request ) {			
		Response response=null;
		try{						
			AffiliateOfferResponse offerResponse = oeBO.getAffiliateOffers(request,	httpRequest.getSession(true).getId());
			Response.Status status = offerResponse.getHttpStatus() != null ? offerResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(offerResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			// Not logging Offer API calls - vsood
   			//utilityloggerHelper.logSalesAPITransaction(API_GET_AFFILIATE_OFFERS, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}

	@POST
	@Path(API_POSID)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performPosidAndBpMatch(
			@Valid PerformPosIdAndBpMatchRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		
		try{
			
			if(StringUtils.isNotEmpty(request.getGuid())){
				if(StringUtils.isEmpty(request.getTrackingId())){
					PerformPosIdandBpMatchResponse bpMatchResponse = new PerformPosIdandBpMatchResponse();
					bpMatchResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					bpMatchResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
					bpMatchResponse.setResultDescription("trackingNumber may not be Empty");
					bpMatchResponse.setErrorCode(HTTP_BAD_REQUEST);
					bpMatchResponse.setErrorDescription(bpMatchResponse.getResultDescription());					
					response=Response.status(Response.Status.BAD_REQUEST).entity(bpMatchResponse).build();
					return response;
				}
			}
			
			if(StringUtils.isNotEmpty(request.getTrackingId())){
				if(StringUtils.isEmpty(request.getGuid())){
					PerformPosIdandBpMatchResponse bpMatchResponse = new PerformPosIdandBpMatchResponse();
					bpMatchResponse.setStatusCode(Constants.STATUS_CODE_STOP);
					bpMatchResponse.setResultCode(Constants.RESULT_CODE_EXCEPTION_FAILURE );
					bpMatchResponse.setResultDescription("guid may not be Empty");
					bpMatchResponse.setErrorCode(HTTP_BAD_REQUEST);
					bpMatchResponse.setErrorDescription(bpMatchResponse.getResultDescription());					
					response=Response.status(Response.Status.BAD_REQUEST).entity(bpMatchResponse).build();
					return response;
				}
			}
			
			response = oeBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
			logger.error(e);
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			
   		}finally{
   			try {
   				utilityloggerHelper.logSalesAPITransaction(API_POSID, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   			} catch(Exception en){
   				logger.error("Exception utilityloggerHelper.logSalesAPITransaction ",en);
   			}
   		}
       return response;
	}
	
	@POST
	@Path(API_AVAILABLE_DATES)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDAndCalendarDates(
			@Valid EsidCalendarRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			if (StringUtils.isBlank(request.getLanguageCode())) request.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
				EsidInfoTdspCalendarResponse esidInfoTdspResponse = oeBO
					.getESIDAndCalendarDates(request.getCompanyCode(),
							request.getAffiliateId(),
							request.getBrandId(),
							request.getServStreetNum(),
							request.getServStreetName(),
							request.getServStreetAptNum(),
							request.getServZipCode(),
							request.getTdspCodeCCS(),
							request.getTransactionType(),
							request.getTrackingId(),
							request.getBpMatchFlag(),
							request.getLanguageCode(),
							request.getEsid(),
							httpRequest.getSession(true).getId());
				response = Response.status(Response.Status.OK).entity(esidInfoTdspResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_AVAILABLE_DATES, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_CHECK_CREDIT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditCheck(
			@Valid CreditCheckRequest request) throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			HashMap<String, Object> mandatoryParamList = new HashMap<String, Object>();
	
			if (StringUtils.isBlank(request.getBillStreetNum())
					&& StringUtils.isBlank(request.getBillStreetName())) {
				// Either Billing PO box or Billing Street num/name should be
				// supplied
				mandatoryParamList.put("billPOBox",
						request.getBillPOBox());
			} else {
				mandatoryParamList.put("billStreetNum",
						request.getBillStreetNum());
				mandatoryParamList.put("billStreetName",
						request.getBillStreetName());
			}
	
			if (StringUtils.isBlank(request.getLanguageCode()))
				request
						.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
	
			HashMap<String, Object> mandatoryParamCheckResponse = CommonUtil
					.checkMandatoryParam(mandatoryParamList);
	
			String resultCode = (String) mandatoryParamCheckResponse
					.get("resultCode");
	
			logger.debug("inside performCreditCheck:: resultcode is :: "+resultCode);
			
			if (StringUtils.isNotBlank(request.getBpMatchFlag())
					&& request.getBpMatchFlag().equalsIgnoreCase(BPSD))
				request.setMatchedBP(EMPTY);
	
			if (StringUtils.isNotBlank(resultCode)
					&& resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {
				NewCreditScoreResponse newCreditScoreResponse = oeBO
						.performCreditCheck(oeRequestHandler
								.createNewCreditScoreRequest(request),
								request);
				response = Response.status(Response.Status.OK)
						.entity(newCreditScoreResponse).build();
			} else {
				String errorDesc = (String) mandatoryParamCheckResponse
						.get("errorDesc");
				if (StringUtils.isNotBlank(errorDesc)) {
					response = CommonUtil.buildNotValidResponse(resultCode,
							errorDesc);
				} else {
					response = CommonUtil.buildNotValidResponse(errorDesc,
							Constants.STATUS_CODE_ASK);
				}
				logger.debug("Inside performCreditCheck:: errorDesc is " + errorDesc);
			}
			logger.debug("END ******* performCreditCheck API**********");
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CHECK_CREDIT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
		return response;
	}
	
	@POST
	@Path(API_CREDIT_DATA)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitUCCData(@Valid UCCDataRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		String errorDesc = null;
		HashMap<String, Object> mandatoryParamList = null;
		HashMap<String, Object> mandatoryParamCheckResponse = null;
		
		mandatoryParamList = new HashMap<String, Object>();
		try{
			// Either Billing PO box or Billing Street num/name should be supplied
			mandatoryParamList.put("firstName",
					request.getFirstName());

			mandatoryParamList.put("lastName",
					request.getLastName());
			mandatoryParamList.put("depositAmount",
					request.getDepositAmount());
		

		mandatoryParamCheckResponse = CommonUtil
		.checkMandatoryParam(mandatoryParamList);
		String resultCode = (String) mandatoryParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) mandatoryParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		
		HashMap<String, Object> nagativeParamList = null;
		HashMap<String, Object> negativeParamCheckResponse = null;
		
		nagativeParamList = new HashMap<String, Object>();


		nagativeParamList.put("depositAmount",
				request.getDepositAmount());

		if(StringUtils.isNotEmpty(request.getCreditScore())) {
			nagativeParamList.put("creditScore",
					request.getCreditScore());
		}

		

		negativeParamCheckResponse = CommonUtil
		.checkNegaviteValueInParam(nagativeParamList);
		 resultCode = (String) negativeParamCheckResponse
		.get("resultCode");
		
		if (StringUtils.isNotBlank(resultCode)
				&& !resultCode.equalsIgnoreCase(Constants.SUCCESS_CODE)) {

					errorDesc = (String) negativeParamCheckResponse
					.get("errorDesc");
					
					if (StringUtils.isNotBlank(errorDesc)) {
						response = CommonUtil.buildNotValidResponse(resultCode,
						errorDesc);
					} else {
						response = CommonUtil.buildNotValidResponse(errorDesc,
						Constants.STATUS_CODE_ASK);
					}
					logger.info("Inside submitUCCData:: errorDesc is " + errorDesc);
				
					return response;
				}
		
		
		UCCDataResponse uccResp = oeBO.submitUCCData(request,
				httpRequest.getSession(true).getId());
		response = Response.status(Response.Status.OK).entity(uccResp).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CREDIT_DATA, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
	@Path(API_SUBMIT_ENROLLMENT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitEnrollment(@Valid EnrollmentRequest request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
	    try{
	    	EnrollmentResponse enrollmentResponse = oeBO.submitEnrollment(request);
	    	response = Response.status(Response.Status.OK).entity(enrollmentResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_SUBMIT_ENROLLMENT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@POST
    @Path(API_GET_KBA_QUESTIONS)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getKBAQuestions(@Valid GetKBAQuestionsRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
       try{
        	GetKBAQuestionsResponse getKBAQuestionsResponse = oeBO.getKBAQuestions(request);
            response = Response.status(Response.Status.OK).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_GET_KBA_QUESTIONS, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
       return response;
    }
	
	@POST
	@Path(API_KBA_RESULT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitKBAAnswers(@Valid KbaAnswerRequest request) throws Exception {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
		
		try{
			KbaAnswerResponse kbaAnsweresponse = oeBO.submitKBAAnswers(request);
			response = Response.status(Response.Status.OK).entity(kbaAnsweresponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_KBA_RESULT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	/**
	 * START :OE ADO SPrint4 : Tokenization API
	 * @author Asingh
	 * @param actionCode
	 * @param numToBeTokenized
	 * @return
	 */
	@GET
	@Path(API_TOKEN)	
	@Produces({ MediaType.APPLICATION_JSON })
	@ValidateGetMapppingRequestParam
	public Response getTokenResponse(@InjectParam TokenRequestVO request) throws Exception {
		Response response=null;
		long startTime = CommonUtil.getStartTime();
		try{
			request.setActionCode(request.getActionCode());
			request.setNumToBeTokenized(request.getNumToBeTokenized());
			TokenizedResponse tokenizedResponse = oeBO.getTokenResponse(request);
			Response.Status status = tokenizedResponse.getHttpStatus() != null ? tokenizedResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(tokenizedResponse).build();
		}catch(Exception e){ 
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
			logger.error(e.fillInStackTrace());
		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_TOKEN, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
		return response;
	}
	
	@GET
	@Path(API_PROSPECT)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProspectData(@QueryParam(value = "prospectID")   String prospectID,
			@QueryParam(value = "last4SSN")   String lastFourSSN,
			@QueryParam(value = "companyCode")   String companyCode,
			@QueryParam(value = "languageCode")   String languageCode) {
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		ProspectDataRequest request = null;
		try{
			request = new ProspectDataRequest();
			request.setCompanyCode(companyCode);
			request.setProspectID(prospectID);
			request.setLastfourdigitSSN(lastFourSSN);
		ProspectDataResponse prospectDataResponse = oeBO.getProspectData(prospectID,lastFourSSN,companyCode);
		Response.Status status = prospectDataResponse.getHttpStatus() != null ? prospectDataResponse.getHttpStatus() :Response.Status.OK;
		response = Response.status(status).entity(prospectDataResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(languageCode))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_PROSPECT, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
		return response;
	}

	@POST
	@Path(API_ESID)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDDetails(@Valid EsidRequest request){
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		try{
			EsidResponse getEsiidResponse = oeBO.getESIDDetails(request);
			response = Response.status(Response.Status.OK).entity(getEsiidResponse).build();
		}catch (Exception e) {
	   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
	   			logger.error(e.fillInStackTrace());
	   	}finally{
	   			utilityloggerHelper.logSalesAPITransaction(API_ESID, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
	   	}
		return response;	
	}
	
	@POST
    @Path(KBA_OE)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getKBAQuestionsWithinOE(@Valid GetOEKBAQuestionsRequest request){
		long startTime = CommonUtil.getStartTime();
		Response response=null;
       try{
    	   
        	GetKBAQuestionsResponse getKBAQuestionsResponse = oeBO.getKBAQuestionsWithinOE(request);
            response = Response.status(Response.Status.OK).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new GenericResponse()).setGenericErrorResponse(e, oeBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(KBA_OE, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
    }

}
