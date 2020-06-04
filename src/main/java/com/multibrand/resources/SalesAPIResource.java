
package com.multibrand.resources;

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
import com.multibrand.dto.request.EsidRequest;
import com.multibrand.dto.request.GetKBAQuestionsRequest;
import com.multibrand.dto.request.GetOEKBAQuestionsRequest;
import com.multibrand.dto.request.IdentityRequest;
import com.multibrand.dto.request.KbaAnswerRequest;
import com.multibrand.dto.request.ProspectDataRequest;
import com.multibrand.dto.request.SalesCleanupAddressRequest;
import com.multibrand.dto.request.SalesCreditCheckRequest;
import com.multibrand.dto.request.SalesCreditReCheckRequest;
import com.multibrand.dto.request.SalesEnrollmentRequest;
import com.multibrand.dto.request.SalesEsidCalendarRequest;
import com.multibrand.dto.request.SalesHoldLookupRequest;
import com.multibrand.dto.request.SalesOfferRequest;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.dto.response.SalesBaseResponse;
import com.multibrand.dto.response.SalesCleanupAddressResponse;
import com.multibrand.dto.response.SalesEnrollmentResponse;
import com.multibrand.dto.response.SalesHoldLookupResponse;
import com.multibrand.dto.response.SalesOfferResponse;
import com.multibrand.exception.OEException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.request.handlers.OERequestHandler;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.SalesTokenRequest;
import com.multibrand.vo.response.GetKBAQuestionsResponse;
import com.multibrand.vo.response.KbaAnswerResponse;
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
			request.setCallExecuted(API_IDENTITY);
			response = salesBO.performPosidAndBpMatch(request);
		} catch (Exception e) {
			logger.error(e);
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			
   		}finally{
   			try {
   				utilityloggerHelper.logSalesAPITransaction(API_IDENTITY, false, request, response, CommonUtil.getElapsedTime(startTime), CommonUtil.getTrackingIdFromResponse(response), EMPTY, request.getAffiliateId(), CommonUtil.getGuIdFromResponse(response));
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
			request.setCallExecuted(API_AVAILABLE_DATES);
			if (StringUtils.isBlank(request.getLanguageCode())) request.setLanguageCode(Constants.LOCALE_LANGUAGE_CODE_E);
			SalesBaseResponse salesBaseResponse = salesBO.getSalesESIDAndCalendarDates(request,httpRequest);
			
			Response.Status status = salesBaseResponse.getHttpStatus() != null ? salesBaseResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status)
					.entity(salesBaseResponse).build();
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_AVAILABLE_DATES, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
   		}
       return response;
	}
	
	@POST
	@Path(API_CHECK_CREDIT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditCheck(SalesCreditCheckRequest request) throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			request.setCallExecuted(API_CHECK_CREDIT);
			SalesBaseResponse newCreditScoreResponse = salesBO
						.performCreditCheck(request);
			Response.Status status = newCreditScoreResponse.getHttpStatus() != null ? newCreditScoreResponse.getHttpStatus() :Response.Status.OK;
				response = Response.status(status)
						.entity(newCreditScoreResponse).build();
			
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CHECK_CREDIT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
   		}
		return response;
	}
	
	@POST
	@Path(API_RECHECK_CREDIT)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response performCreditRecheck(SalesCreditReCheckRequest request) throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			request.setCallExecuted(API_RECHECK_CREDIT);
			SalesBaseResponse newCreditScoreResponse = salesBO
						.performCreditReCheck(request);
			Response.Status status = newCreditScoreResponse.getHttpStatus() != null ? newCreditScoreResponse.getHttpStatus() :Response.Status.OK;
				response = Response.status(status)
						.entity(newCreditScoreResponse).build();
			
		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_RECHECK_CREDIT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
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
			request.setCallExecuted(API_SUBMIT_ENROLLMENT);
	    	SalesEnrollmentResponse salesEnrollmentResponse = salesBO.submitEnrollment(request);
	    	Response.Status status = salesEnrollmentResponse.getHttpStatus() != null ? salesEnrollmentResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(salesEnrollmentResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_SUBMIT_ENROLLMENT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
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
   			utilityloggerHelper.logSalesAPITransaction(API_GET_KBA_QUESTIONS, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY, request.getAffiliateId(), EMPTY);
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
			request.setCallExecuted(API_KBA_RESULT);
			KbaAnswerResponse kbaAnsweresponse = oeBO.submitKBAAnswers(request);
			Response.Status status = kbaAnsweresponse.getHttpStatus() != null ? kbaAnsweresponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(kbaAnsweresponse).build();
			
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_KBA_RESULT, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
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
   			utilityloggerHelper.logSalesAPITransaction(API_PROSPECT, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY, request.getAffiliateId(), EMPTY);
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
	   			utilityloggerHelper.logSalesAPITransaction(API_ESID, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY,request.getAffiliateId(), EMPTY);
	   	}
		return response;	
	}
	
	@POST
	@Path(API_ESID_RESIDENTIAL)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
	public Response getESIDResidentialDetails(EsidRequest request){
		Response response = null;
		long startTime = CommonUtil.getStartTime();
		try{
			EsidResponse getEsiidResponse = oeBO.getESIDResidentialDetails(request);
			response = Response.status(Response.Status.OK).entity(getEsiidResponse).build();
		}catch (Exception e) {
	   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
	   			logger.error(e.fillInStackTrace());
	   	}finally{
	   			utilityloggerHelper.logSalesAPITransaction(API_ESID, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY,request.getAffiliateId(), EMPTY);
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
    	   	request.setCallExecuted(KBA_OE);
        	SalesBaseResponse getKBAQuestionsResponse = oeBO.getKBAQuestionsWithinOE(request);
        	Response.Status status = getKBAQuestionsResponse.getHttpStatus() != null ? getKBAQuestionsResponse.getHttpStatus() :Response.Status.OK;
            response = Response.status(status).entity(getKBAQuestionsResponse).build();
   		} catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(KBA_OE, false, request, response, CommonUtil.getElapsedTime(startTime), request.getTrackingId(), EMPTY, request.getAffiliateId(), request.getGuid());
   		}
       return response;
    }
	
	@POST
	@Path(API_CLEANUP_ADDRESS)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response cleanupAddress(SalesCleanupAddressRequest  request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		Response response = null;
		try{
			SalesCleanupAddressResponse salesCleanupAddressResponse = salesBO.getCleanedUpAddress(request);
	    	Response.Status status = salesCleanupAddressResponse.getHttpStatus() != null ? salesCleanupAddressResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(salesCleanupAddressResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_CLEANUP_ADDRESS, false, request, response, CommonUtil.getElapsedTime(startTime), EMPTY, EMPTY, request.getAffiliateId(), EMPTY);
   		}
       return response;
	}

	@GET
	@Path(API_GET_HOLD)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEnrollementHoldInformation(@InjectParam SalesHoldLookupRequest  request)
			throws OEException {
		long startTime = CommonUtil.getStartTime();
		logger.info("SalesHoldLookupRequest :"+request);
		Response response = null;
		try{
			SalesHoldLookupResponse salesHoldLookupResponse = oeBO.getSalesHoldList(request);
	    	Response.Status status = salesHoldLookupResponse.getHttpStatus() != null ? salesHoldLookupResponse.getHttpStatus() :Response.Status.OK;
			response = Response.status(status).entity(salesHoldLookupResponse).build();
	    } catch (Exception e) {
   			response=Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new SalesBaseResponse()).populateGenericErrorResponse(e, salesBO.getTechnicalErrorMessage(request.getLanguageCode()))).build();
   			logger.error(e.fillInStackTrace());
   		}finally{
   			utilityloggerHelper.logSalesAPITransaction(API_GET_HOLD, false, request, response, CommonUtil.getElapsedTime(startTime), request.getCaNumber(), EMPTY, request.getAffiliateId(), EMPTY);
   		}
       return response;
	}

}
