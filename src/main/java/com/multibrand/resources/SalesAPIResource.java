
package com.multibrand.resources;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.OEBO;
import com.multibrand.bo.SalesBO;
import com.multibrand.dto.request.CreditCheckRequest;
import com.multibrand.dto.request.EnrollmentRequest;
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.GetOEKBAQuestionsRequest;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.SalesEnrollmentRequest;
import com.multibrand.dto.request.SalesEsidCalendarRequest;
import com.multibrand.dto.request.SalesOfferRequest;
import com.multibrand.dto.request.UCCDataRequest;
import com.multibrand.dto.response.EnrollmentResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.IdentityResponse;
import com.multibrand.dto.response.SalesBaseResponse;
import com.multibrand.dto.response.SalesEnrollmentResponse;
import com.multibrand.dto.response.SalesOfferResponse;
import com.multibrand.dto.response.UCCDataResponse;
import com.multibrand.exception.OEException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.SalesTokenRequest;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
import com.multibrand.vo.response.NewCreditScoreResponse;
import com.multibrand.vo.response.SalesTokenResponse;
import com.multibrand.web.i18n.WebI18nMessageSource;
import com.sun.jersey.api.core.InjectParam;

/**
 * This Resource is to handle all the Online Enrollment API calls.
 * 
 * @author NRG Energy
 */
@Component
@Path("/"+Constants.SALES_API_BASE_PATH)
public class SalesAPIResource extends BaseResource {
	
	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	OERequestHandler oeRequestHandler;

	/** Object of oeBO class. */
	@Autowired
	private OEBO oeBO;

	@Context
	private HttpServletRequest httpRequest;

	@Resource(name = "webI18nMessageSource")
	private WebI18nMessageSource msgSource;

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private SalesBO salesBO;
	
	@GET
	@Path(API_OFFERS)	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAffiliateOffers(@InjectParam SalesOfferRequest request ) {			
		Response response=null;
		try{						
			SalesOfferResponse offerResponse = salesBO.getSalesOffers(request,	httpRequest.getSession(true).getId());
			Response.Status status = offerResponse.getHttpStatus() != null ? offerResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(offerResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			// Not logging Offer API calls - vsood
   			//utilityloggerHelper.logSalesAPITransaction(API_GET_AFFILIATE_OFFERS, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}

	@POST
	@Path(API_IDENTITY)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performPosidAndBpMatch(IdentityRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		
		try{
			
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
			response = salesBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
			logger.error(e);
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			
   		}finally{
   			try {
   				utilityloggerHelper.logSalesAPITransaction(API_IDENTITY, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
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
	public Response getESIDAndCalendarDates(SalesEsidCalendarRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			if (StringUtils.isBlank(request.getLanguageCode())) request.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
			SalesBaseResponse salesBaseResponse = salesBO.getSalesESIDAndCalendarDates(request,httpRequest);
			response=Response.status(Response.Status.BAD_REQUEST).entity(salesBaseResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
	public Response performCreditCheck(CreditCheckRequest request) throws OEException {
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
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
	public Response submitUCCData(UCCDataRequest request) {
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
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
	public Response submitEnrollment(SalesEnrollmentRequest request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
	    	if(StringUtils.isNotEmpty(request.getGuid()) && StringUtils.isEmpty(request.getTrackingId())){
	    		SalesEnrollmentResponse salesEnrollmentResponse = new SalesEnrollmentResponse();
	    		salesEnrollmentResponse.setStatusCode(Constants.STATUS_CODE_STOP);
	    		salesEnrollmentResponse.setErrorCode(HTTP_BAD_REQUEST);
	    		salesEnrollmentResponse.setErrorDescription("trackingId cannot be empty");					
				response=Response.status(Response.Status.BAD_REQUEST).entity(salesEnrollmentResponse).build();
				return response;
			} else if(StringUtils.isNotEmpty(request.getTrackingId()) && StringUtils.isEmpty(request.getGuid())){
				SalesEnrollmentResponse salesEnrollmentResponse = new SalesEnrollmentResponse();
				salesEnrollmentResponse.setStatusCode(Constants.STATUS_CODE_STOP);
				salesEnrollmentResponse.setErrorCode(HTTP_BAD_REQUEST);
				salesEnrollmentResponse.setErrorDescription("guid cannot be empty");					
				response=Response.status(Response.Status.BAD_REQUEST).entity(salesEnrollmentResponse).build();
				return response;
			}
	    	SalesEnrollmentResponse salesEnrollmentResponse = salesBO.getSalesSubmitEnrollment(request);
	    	Response.Status status = salesEnrollmentResponse.getHttpStatus() != null ? salesEnrollmentResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(salesEnrollmentResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
    public Response getKBAQuestions(GetKBAQuestionsRequest request) {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
       try{
        	GetKBAQuestionsResponse getKBAQuestionsResponse = oeBO.getKBAQuestions(request);
            response = Response.status(Response.Status.OK).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
	public Response submitKBAAnswers(KbaAnswerRequest request) throws Exception {
		long startTime = CommonUtil.getStartTime();
		Response response=null;
		
		try{
			KbaAnswerResponse kbaAnsweresponse = oeBO.submitKBAAnswers(request);
			response = Response.status(Response.Status.OK).entity(kbaAnsweresponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_KBA_RESULT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
	}
	
	@GET
	@Path(API_TOKEN)	
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTokenResponse(@InjectParam SalesTokenRequest request) throws Exception {
		Response response=null;
		try{
			request.setActionCode(request.getActionCode());
			request.setNumToBeTokenized(request.getNumToBeTokenized());
			SalesTokenResponse tokenizedResponse = salesBO.getTokenResponse(request);
			Response.Status status = tokenizedResponse.getHttpStatus() != null ? tokenizedResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(tokenizedResponse).build();
		}catch(Exception e){ 
			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
			logger.error(e.fillInStackTrace());
		}finally{
			// Commented purposefully and should not log transaction.
   		//	utilityloggerHelper.logSalesAPITransaction(API_TOKEN, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY);
   		}
		return response;
	}
	
	@GET
	@Path(API_PROSPECT)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProspectData(@InjectParam ProspectDataRequest request) {
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		try{
			SalesBaseResponse prospectDataResponse = salesBO.getProspectData(request);
			Response.Status status = prospectDataResponse.getHttpStatus() != null ? prospectDataResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(prospectDataResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
	public Response getESIDDetails(EsidRequest request){
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		try{
			EsidResponse getEsiidResponse = oeBO.getESIDDetails(request);
			response = Response.status(Response.Status.OK).entity(getEsiidResponse).build();
		}catch (Exception e) {
	   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
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
    public Response getKBAQuestionsWithinOE(GetOEKBAQuestionsRequest request){
		long startTime = CommonUtil.getStartTime();
		Response response=null;
       try{
        	SalesBaseResponse getKBAQuestionsResponse = oeBO.getKBAQuestionsWithinOE(request);
        	Response.Status status = getKBAQuestionsResponse.getHttpStatus() != null ? getKBAQuestionsResponse.getHttpStatus() :Response.Status.OK;
            response = Response.status(status).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(KBA_OE, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY);
   		}
       return response;
    }

}
