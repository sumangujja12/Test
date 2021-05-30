package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.domain.CheckPendingMVORequest;
import com.multibrand.domain.CheckPendingMoveInRequest;
import com.multibrand.domain.ContractDataRequest;
import com.multibrand.domain.ContractDataResponse;
import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.CreateContactLogResponse;
import com.multibrand.domain.EsiDforAddressRequest;
import com.multibrand.domain.EsiDforAddressResponse;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.OetdspRequest;
import com.multibrand.domain.OfferOfContractRequest;
import com.multibrand.domain.OfferOfContractResponse;
import com.multibrand.domain.PermitCheckRequest;
import com.multibrand.domain.PermitCheckResponse;
import com.multibrand.domain.ProgramAccountInfoRequest;
import com.multibrand.domain.ProgramAccountInfoResponse;
import com.multibrand.domain.TdspByESIDResponse;
import com.multibrand.domain.TdspByZipResponse;
import com.multibrand.domain.TransferServiceRequest;
import com.multibrand.domain.TransferServiceResponse;
import com.multibrand.dto.response.EsidResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.ContentHelper;
import com.multibrand.helper.ContractHelper;
import com.multibrand.helper.OfferHelper;
import com.multibrand.resources.requestHandlers.TOSRequestHandler;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.OEService;
import com.multibrand.service.ProfileService;
import com.multibrand.service.TOSService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.vo.request.ESIDData;
import com.multibrand.vo.request.TOSEligibleNonEligibleProductsRequest;
import com.multibrand.vo.request.TOSSubmitEligibleProductsRequest;
import com.multibrand.vo.response.CheckPendingMVOResponse;
import com.multibrand.vo.response.CheckPendingMoveInResponse;
import com.multibrand.vo.response.ContractOffer;
import com.multibrand.vo.response.ESIDForAddressResponse;
import com.multibrand.vo.response.OetdspResponse;
import com.multibrand.vo.response.OfferDO;
import com.multibrand.vo.response.OfferPriceWraperDO;
import com.multibrand.vo.response.OfferResponse;
import com.multibrand.vo.response.TOSEligibleNonEligibleProductsResponse;
import com.multibrand.vo.response.TOSSubmitEligibleProductsResponse;
import com.multibrand.vo.response.TosAmbWebResponse;
import com.multibrand.vo.response.tosResponse.OfferPlanContentResponse;

@Component
public class TOSBO extends BaseAbstractService implements Constants {
	

	
	@Autowired
	private TOSService tosService;
	
	@Autowired
	private OEBO oeBO;
	
	@Autowired
	private OEService oeService;
	
	
	@Autowired
	private ProfileService profileService;
	
	
	@Autowired
	TOSRequestHandler tosRequestHandler;
	
	@Autowired
	OfferHelper offerHelper;
	
	@Autowired
	ContractHelper contractHelper;
	
	@Autowired
	ContentHelper contentHelper;
		
	 
	/**
	 * @author ahanda1
	 * @param request
	 * @return
	 */
	public CheckPendingMVOResponse checkPendingMVO(CheckPendingMVORequest request, String companyCode, String sessionId){
		logger.info("TOSBO.checkPendingMVO :::::::: START");
		
		CheckPendingMVOResponse checkPendingMVOResponse = new CheckPendingMVOResponse();
		
		try {
			com.multibrand.domain.CheckPendingMVOResponse response = tosService.checkingPendingMVO(request, companyCode, sessionId);
			
			if(response!= null && org.apache.commons.lang3.StringUtils.isNotEmpty(response.getErrCode())){
				checkPendingMVOResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				checkPendingMVOResponse.setResultDescription(response.getErrCode());				
			}
			JavaBeanUtil.copy(response, checkPendingMVOResponse);
			
			
		} catch (RemoteException e) {
			logger.error(e);
			checkPendingMVOResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			checkPendingMVOResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), checkPendingMVOResponse);			
		} catch (Exception e) {
			logger.error(e);
			checkPendingMVOResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			checkPendingMVOResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), checkPendingMVOResponse);	
		}
		
	
		logger.info("TOSBO.checkPendingMVO :::::::: END");
		return checkPendingMVOResponse;
		
	}
	
	/**
	 * @author ahanda1
	 * @param request
	 * @return
	 */
	public CheckPendingMoveInResponse checkPendingMVI(CheckPendingMoveInRequest request, String companyCode, String sessionId){
		logger.info("TOSBO.checkPendingMVI :::::::: START");
		
		CheckPendingMoveInResponse checkPendingMVIResponse = new CheckPendingMoveInResponse();
		try {
			com.multibrand.domain.CheckPendingMoveInResponse response = tosService.checkingPendingMVI(request, companyCode, sessionId);
			
			if(response!= null && StringUtils.isNotEmpty(response.getErrCode())){
				checkPendingMVIResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				checkPendingMVIResponse.setResultDescription(response.getErrCode());				
			}
			
			JavaBeanUtil.copy(response, checkPendingMVIResponse);
			
			
		} catch (RemoteException e) {
			logger.error(e);
			checkPendingMVIResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			checkPendingMVIResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), checkPendingMVIResponse);			
		} catch (Exception e) {
			logger.error(e);
			checkPendingMVIResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			checkPendingMVIResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), checkPendingMVIResponse);	
		}
		
	
		logger.info("TOSBO.checkPendingMVI :::::::: END");
		return checkPendingMVIResponse;
		
	}
	
	/**
	 * @author ahanda1
	 * @param request
	 * @return
	 */
	public ESIDForAddressResponse getESIDForAddress(EsiDforAddressRequest request, String companyCode, String sessionId){
		logger.info("TOSBO.getESIDForAddress :::::::: START");
		
		ESIDForAddressResponse esidForAddressResponse = new ESIDForAddressResponse();
		
		try {
			EsiDforAddressResponse response = tosService.getESIDForAddress(request, companyCode, sessionId);
			
			if(response!= null && StringUtils.isNotEmpty(response.getErrCode())){
				esidForAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				esidForAddressResponse.setResultDescription(response.getErrCode());				
			}
			
			JavaBeanUtil.copy(response, esidForAddressResponse);
			
			
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
		
	
		logger.info("TOSBO.getESIDForAddress :::::::: END");
		return esidForAddressResponse;
		
	}
	/**
	 * @author stipern1
	 * @param request
	 * @return response
	 */
	/*public TOSPipelineOffersResponse tospipelineoffers(TOSPipelineOffersRequest tosrequest, String sessionId){
		logger.info("TOSBO.tospipelineoffers :::::::: START");
		
		TOSPipelineOffersResponse tospoResponse = new TOSPipelineOffersResponse();
		
		try {
			TOSPipelineOffersResponse response = null;
			EsiDforAddressRequest tosaddrrequest=tosRequestHandler.createRequestESIDForAddress(tosrequest.getApartmentNumber(), tosrequest.getCity(), tosrequest.getCountry(), tosrequest.getPoBox(), tosrequest.getState(), tosrequest.getStreetName(), tosrequest.getStreetNumber(), tosrequest.getZip(), tosrequest.getCompanyCode());
			EsiDforAddressResponse esidresponse = tosService.getESIDForAddress(tosaddrrequest, tosrequest.getCompanyCode(), sessionId);
			List<Contract> dbcontracts=null;
			
			logger.info("ESI profile error="+esidresponse.getErrCode());
			if(esidresponse!= null && StringUtils.isNotEmpty(esidresponse.getErrCode())){
				tospoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				tospoResponse.setResultDescription(esidresponse.getErrCode());	
				// if not found make db call
				// get esiid tdsp from table 
				long startTime = CommonUtil.getStartTime();
				dbcontracts=contractHelper.getContractInfo(tosrequest.getZip(), tosrequest.getStreetNumber(), tosrequest.getStreetName(), tosrequest.getCity(), tosrequest.getState(), tosrequest.getCountry(), tosrequest.getApartmentNumber(), tosrequest.getPoBox());
				utilityloggerHelper.logTransaction("getContractInfo", false, tosrequest,dbcontracts, "", CommonUtil.getElapsedTime(startTime), "", sessionId, tosrequest.getCompanyCode());
				
				logger.info(XmlUtil.pojoToXML(tosrequest));
		    	logger.info(XmlUtil.pojoToXML(dbcontracts));
				// still error return 
				if (dbcontracts==null || dbcontracts.size()==0)
					{ 	logger.info("NO RESPONSE from CONTRACTS in DB");
						return tospoResponse; 
					}
			} 
			
			// if found esid make esid details call for valid account
			if (esidresponse!= null && StringUtils.isEmpty(esidresponse.getErrCode()) && esidresponse.getPointofDeliveryID()!=null) {
				
				    long startTime = CommonUtil.getStartTime();
					EsidProfileResponse esidprofileresponse= addrService.getESIDProfile(esidresponse.getPointofDeliveryID(), esidresponse.getCompanyCode());
					utilityloggerHelper.logTransaction("getESIDProfile", false, "esid="+esidresponse.getPointofDeliveryID()+",companyCode="+esidresponse.getCompanyCode(),esidprofileresponse, esidprofileresponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, esidresponse.getCompanyCode());
					logger.info(XmlUtil.pojoToXML("esid="+esidresponse.getPointofDeliveryID()+",companyCode="+esidresponse.getCompanyCode()));
					logger.info(XmlUtil.pojoToXML(esidprofileresponse));

					String switchhold=esidprofileresponse.getSwitchHoldStatus();
					String segment=esidprofileresponse.getPremiseType();
					// setting the swtichhold status ::
					if(switchhold != null)
					tospoResponse.setSwitchHoldStatus(switchhold);
					
					// get switchhold status and the a segment
					// for switchhold and the business return will empty values
					if (  (segment!=null && !segment.equalsIgnoreCase("Residential")) || (switchhold!=null && (switchhold.equalsIgnoreCase("X")||switchhold.equalsIgnoreCase("y")))) {
						tospoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
						tospoResponse.setResultDescription("INVALID TYPE OF ACCOUNT");
						return tospoResponse;
					}
					
					String tdsp = esidresponse.getServiceId();
					if (tdsp==null) {
						// get from esid
						logger.info("TDSP= from ESID");
						TdspByESIDResponse tosesid=tosService.ccsGetTDSPFromESID(esidresponse.getPointofDeliveryID(), tosrequest.getCompanyCode(), sessionId);
						if (tosesid!=null && StringUtils.isEmpty(tosesid.getStrErrCode()))
								tdsp=tosesid.getServiceId();
						else {
						// get from zip
							logger.info("TDSP= from ZIP");
							TdspByZipResponse toszip=tosService.ccsGetTDSPFromZip(tosrequest.getZip(), tosrequest.getCompanyCode(),sessionId);
							if (toszip!=null&&StringUtils.isEmpty(toszip.getStrErrCode())) {
								tdsp=toszip.getServiceId();
							}
						}
						logger.info("TDSP="+tdsp);
					}
					if (tdsp!=null) {
						PromoOfferRequest promoOfferRequest=new PromoOfferRequest();
						promoOfferRequest.setStrPromoCode(tosrequest.getPromocode());
						promoOfferRequest.setStrLanguage(tosrequest.getLanguage());
						promoOfferRequest.setStrTdspCode(tdsp);
						promoOfferRequest.setStrCompanyCode(tosrequest.getCompanyCode());
						promoOfferRequest.setStrDate(org.apache.commons.lang.time.DateFormatUtils.format(new java.util.Date(), "MM/dd/yyyy"));
						promoOfferRequest.setStrTime("00:00:00");
						startTime = CommonUtil.getStartTime();
						PromoOfferResponse promoresponse=ofrService.getOfferWithPricingFromCCS(promoOfferRequest);
						utilityloggerHelper.logTransaction("getOfferWithPricingFromCCS", false, promoOfferRequest,promoresponse, promoresponse.getStrErrMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, tosrequest.getCompanyCode());
						logger.info(XmlUtil.pojoToXML(promoOfferRequest));
				    	logger.info(XmlUtil.pojoToXML(promoresponse));
						//get promooffers  return the results
						// if found results
						//PropertyUtils.copyProperties(tospoResponse, promoresponse);
				    	JavaBeanUtil.copy(promoresponse, tospoResponse);
					}
				} else{
					// no esid
					// otherwise try OfferDAO.getPOWOffer using ESID and TOS
					Map<String, Object> dbpows=null;
					if (dbcontracts.size()==1)
						dbpows=offerHelper.getPOWOffer(dbcontracts.get(0).getPointofDeliveryID(), "TOS");
					else {
						// for now use the first one
						if (StringUtils.isNotBlank(dbcontracts.get(0).getPointofDeliveryID())) 
							dbpows=offerHelper.getPOWOffer(dbcontracts.get(0).getPointofDeliveryID(), "TOS");
						else if(dbcontracts.size()>=2 && StringUtils.isNotBlank(dbcontracts.get(1).getPointofDeliveryID()))
							dbpows=offerHelper.getPOWOffer(dbcontracts.get(1).getPointofDeliveryID(), "TOS");
						else if(dbcontracts.size()>=3 && StringUtils.isNotBlank(dbcontracts.get(2).getPointofDeliveryID()))
							dbpows=offerHelper.getPOWOffer(dbcontracts.get(2).getPointofDeliveryID(), "TOS");
						else if(dbcontracts.size()>=4 && StringUtils.isNotBlank(dbcontracts.get(3).getPointofDeliveryID()))
							dbpows=offerHelper.getPOWOffer(dbcontracts.get(3).getPointofDeliveryID(), "TOS");						
					}
					@SuppressWarnings("unchecked")
					List<POWOfferDO> getpowofferdto =null;
					if (dbpows.get(DBConstants.OUT_CUR_POW_OFFER)!=null)
						getpowofferdto =(List<POWOfferDO>) dbpows.get(DBConstants.OUT_CUR_POW_OFFER);
					tospoResponse.setPowOffers(getpowofferdto);
					String errorcode=(String) dbpows.get(DBConstants.OUT_ERROR_CODE_LOWER);
					tospoResponse.setStrErrCode(errorcode);
					logger.info("Sending POW offers="+(getpowofferdto!=null?getpowofferdto.size():"0"));
					logger.info("Error="+errorcode);
				}
			
		} catch (RemoteException e) {
			logger.error(e);
			tospoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			tospoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), tospoResponse);			
		} catch (Exception e) {
			logger.error(e);
			tospoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			tospoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), tospoResponse);	
		}
		
		logger.info("TOSBO.tospipelineoffers :::::::: END");
		return tospoResponse;
	}
*/	
	
	public com.multibrand.vo.response.OfferOfContractResponse getOfferOfContract(OfferOfContractRequest request, String sessionId){
		
		logger.info("TOSBO.getOfferOfContract :::::::: Start");
		com.multibrand.vo.response.OfferOfContractResponse response = new com.multibrand.vo.response.OfferOfContractResponse();
	    try {
			OfferOfContractResponse offerOfContractResponse = tosService.getOfferOfContract(request, sessionId);
			
			if(offerOfContractResponse!= null && StringUtils.isNotEmpty(offerOfContractResponse.getErrCode())){
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(offerOfContractResponse.getErrCode());				
			}
			
			JavaBeanUtil.copy(offerOfContractResponse, response);
			
			
			
		} catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);			
		} catch (Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);	
		}
		
		
		logger.info("TOSBO.getOfferOfContract :::::::: End");
		return response;
	}
	
	/**
	 * @author ahanda1
	 * @param request
	 * @param sessionId
	 * @return
	 */
    public com.multibrand.vo.response.ProgramAccountInfoResponse getProgramAccountInfo(ProgramAccountInfoRequest request, String sessionId){
		
		logger.info("TOSBO.getProgramAccountInfo :::::::: Start");
		com.multibrand.vo.response.ProgramAccountInfoResponse response = new com.multibrand.vo.response.ProgramAccountInfoResponse();
	    try {
			ProgramAccountInfoResponse programAccountInfoResponse = tosService.getProgramAccountInfo(request, sessionId);
			
			if(programAccountInfoResponse!= null && StringUtils.isNotEmpty(programAccountInfoResponse.getErrCode())){
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(programAccountInfoResponse.getErrCode());				
			}
			
			JavaBeanUtil.copy(programAccountInfoResponse, response);
			
			
			
		} catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);			
		} catch (Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);	
		}
		
		
		logger.info("TOSBO.getProgramAccountInfo :::::::: End");
		return response;
	}
    
    /**
     * @author ahanda1
     * @param request
     * @param sessionId
     * @return
     */
    public com.multibrand.vo.response.CreateContactLogResponse updateContactLog(CreateContactLogRequest request, String sessionId){
		
		logger.info("TOSBO.updateContactLog :::::::: Start");
		com.multibrand.vo.response.CreateContactLogResponse response = new com.multibrand.vo.response.CreateContactLogResponse();
	    try {
	    	CreateContactLogResponse createContactLogResponse = tosService.updateContactLog(request, sessionId);
			
			if(createContactLogResponse!= null && StringUtils.isNotEmpty(createContactLogResponse.getErrCode())){
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(createContactLogResponse.getErrCode());				
			}
			
			JavaBeanUtil.copy(createContactLogResponse, response);
			
			
			
		} catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);			
		} catch (Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);	
		}
		
		
		logger.info("TOSBO.updateContactLog :::::::: End");
		return response;
	}
    
    /**
     * @author ahanda1
     * @param zip
     * @param companyCode
     * @param sessionId
     * @return
     */
public com.multibrand.vo.response.TdspByZipResponse getTdspByZip(String zip, String companyCode,String sessionId){
		
		logger.info("TOSBO.getTdspByZip :::::::: Start");
		com.multibrand.vo.response.TdspByZipResponse response = new com.multibrand.vo.response.TdspByZipResponse();
	    try {
	    	TdspByZipResponse tdspByZipResponse = tosService.ccsGetTDSPFromZip(zip, companyCode, sessionId);
			
			if(tdspByZipResponse!= null && StringUtils.isNotEmpty(tdspByZipResponse.getStrErrCode())){
				response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				response.setResultDescription(tdspByZipResponse.getStrErrCode());				
			}
			
			JavaBeanUtil.copy(tdspByZipResponse, response);
			response.setServiceIdDescription(appConstMessageSource.getMessage(response.getServiceId(), null, null));
			
			
		} catch (RemoteException e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);			
		} catch (Exception e) {
			logger.error(e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);	
		}
		
		
		logger.info("TOSBO.getTdspByZip :::::::: End");
		return response;
	}

/**
 * @author ahanda1
 * @param esid
 * @param companyCode
 * @param sessionId
 * @return
 */
public com.multibrand.vo.response.TdspByESIDResponse getTDSPFromESID(String esid, String companyCode,String sessionId){
	
	logger.info("TOSBO.getTDSPFromESID :::::::: Start");
	com.multibrand.vo.response.TdspByESIDResponse response = new com.multibrand.vo.response.TdspByESIDResponse();
    try {
    	TdspByESIDResponse tdspByESIDResponse = tosService.ccsGetTDSPFromESID(esid, companyCode, sessionId);
		
		if(tdspByESIDResponse!= null && StringUtils.isNotEmpty(tdspByESIDResponse.getStrErrCode())){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(tdspByESIDResponse.getStrErrCode());				
		}
		
		JavaBeanUtil.copy(tdspByESIDResponse, response);
		
		
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.getTDSPFromESID :::::::: End");
	return response;
}


/**
 * @author ahanda1
 * @param request
 * @param sessionId
 * @return
 */
public com.multibrand.vo.response.ContractDataResponse getContractData(ContractDataRequest request, String sessionId){
	
	logger.info("TOSBO.getContractData :::::::: Start");
	com.multibrand.vo.response.ContractDataResponse response = new com.multibrand.vo.response.ContractDataResponse();
    try {
    	
    	ContractDataResponse contractDataResponse = tosService.getContractData(request, sessionId);
		
		if(contractDataResponse!= null && StringUtils.isNotEmpty(contractDataResponse.getErrCode())){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(contractDataResponse.getErrCode());				
		}
		
		JavaBeanUtil.copy(contractDataResponse, response);
		
		
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.getContractData :::::::: End");
	return response;
}

/**
 * @author ahanda1
 * @param request
 * @param sessionId
 * @return
 */
public com.multibrand.vo.response.TransferServiceResponse saveTransferServiceDetails(TransferServiceRequest request, String sessionId, String accountName, String contactName, String email, String locale, String billDeliveryMethod, String additionalProgs, String cancelFee, String planName, String planType, String contractTerm, String avgPrice, String tosUrl, String tosId, String eflUrl, String eflId, String yraacUrl, String yraacId, String existingStreetNumber, String existingStreetName, String existingApartmentNumber, String existingCity, String existingState, String existingZip, String existingCountry, String checkDigit ){
	
	logger.info("TOSBO.saveTransferServiceDetails :::::::: Start");
	com.multibrand.vo.response.TransferServiceResponse response = new com.multibrand.vo.response.TransferServiceResponse();
    try {
    	
    	TransferServiceResponse transferServiceResponse = tosService.saveTransferServiceDetails(request, sessionId);
		
		if(transferServiceResponse!= null && StringUtils.isNotEmpty(transferServiceResponse.getErrorCode())){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(transferServiceResponse.getErrorCode());				
		}else{
		
		  //TOS submission successful
		  JavaBeanUtil.copy(transferServiceResponse, response);
		
		  logger.info("Sending mail for successful TOS submission");
			
		  HashMap<String, String> templateProps = new HashMap<String,String>();
		  
		  String serviceAddress = request.getStreetNumber()+ " "+ request.getStreetName();
			if(!StringUtils.isEmpty(request.getApartmentNumber()))
				serviceAddress = serviceAddress	+ ", APT# "+ request.getApartmentNumber();
		  
		  String existingServiceAddress=existingStreetNumber+ " "+ existingStreetName;
			if(!StringUtils.isEmpty(existingApartmentNumber))
				existingServiceAddress = existingServiceAddress	+ ", APT# "+ existingApartmentNumber;
		  
		  
		  String billingAddress="";
			if(StringUtils.isEmpty(request.getBillingPOBox()))
			{
				billingAddress = request.getBillingStreetNumber()+ " "+ request.getBillingStreetName();
				if(!StringUtils.isEmpty(request.getBillingApartmentNumber()))
					billingAddress = billingAddress	+ ", APT# "+ request.getBillingApartmentNumber();
			}
			else
			{
				billingAddress = "P.O. Box "+ request.getBillingPOBox();
			}
		  
		  
		  templateProps.put(BP_NUMBER, request.getBusinessPartnerNumber());
		  templateProps.put(ACCOUNT_NUMBER_HIDDEN, request.getContractAccountNumber());
		  templateProps.put(ACCOUNT_NUMBER, request.getContractAccountNumber().replaceFirst("^0+(?!$)", ""));
		  templateProps.put(CHECK_DIGIT, checkDigit);
		  templateProps.put(ACCOUNT_NAME,accountName);
		  templateProps.put(CONTRACT_NUMBER, request.getContractNumber());
		  templateProps.put(ESID, request.getPointofDeliveryID());
		  
		  templateProps.put(SERVICE_ADDRESS, serviceAddress);
		  templateProps.put(SERVICE_CITY, request.getCity());
		  templateProps.put(SERVICE_STATE, request.getState());
		  templateProps.put(SERVICE_ZIP, request.getZip());
		  
		  
		  templateProps.put(BILLING_ADDRESS, billingAddress);
		  templateProps.put(BILLING_CITY, request.getBillingCity());
		  templateProps.put(BILLING_STATE, request.getBillingState());
		  templateProps.put(BILLING_ZIP, request.getBillingZip());
		  
		  templateProps.put(EXISTING_SERVICE_ADDRESS,existingServiceAddress);
		  templateProps.put(EXISTING_SERVICE_CITY, existingCity);
		  templateProps.put(EXISTING_SERVICE_STATE, existingState);
		  templateProps.put(EXISTING_SERVICE_ZIP, existingZip);
		  
		  templateProps.put(POWER_ON,CommonUtil.changeDateFormat(request.getMVIDate(),"yyyy-MM-dd","MM/dd/yyyy"));
		  
		  templateProps.put(POWER_OFF, CommonUtil.changeDateFormat(request.getMVODate(),"yyyy-MM-dd","MM/dd/yyyy"));
		  String submitDate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		  templateProps.put(SUBMIT_DATE, submitDate);
		  templateProps.put(TRANSACTION_NUMBER, response.getIDO_number());
		  
		  templateProps.put(CONTACT_NAME, contactName);
		  templateProps.put(CONTACT_PHONE_NUMBER, request.getContactPhoneNumber());
		  templateProps.put(CONTACT_EMAIL_ADDRESS, request.getContactEmail());
		  templateProps.put(BILL_DELIVERY_METHOD, billDeliveryMethod);
		  
		  
		  if(StringUtils.isNotEmpty(cancelFee))templateProps.put(CANCEL_FEE, cancelFee);
		  if(StringUtils.isNotEmpty(planName))templateProps.put(PLAN_NAME, planName);
		  if(StringUtils.isNotEmpty(planType))templateProps.put(PLAN_TYPE, planType);
		  if(StringUtils.isNotEmpty(contractTerm))templateProps.put(CONTRACT_TERM, contractTerm);
		  if(StringUtils.isNotEmpty(avgPrice))templateProps.put(AVG_PRICE,avgPrice);
		  /*templateProps.put(EFL_URL, eflUrl);
		  templateProps.put(EFL_SMART_CODE, eflId);
		  templateProps.put(TOS_URL, tosUrl);
		  templateProps.put(TOS_SMART_CODE, tosId);
		  templateProps.put(YRAAC_URL, yraacUrl);
		  templateProps.put(YRAAC_SMART_CODE, yraacId);
		  */
		  
		  String bccMailAddress = this.envMessageReader.getMessage(QC_BCC_MAIL)+","+this.envMessageReader.getMessage(SWAP_BCC_MAIL);
		  
		  if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){
			  templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD);
			  logger.info("Sending mail for successful TOS submission EN");
			  if(StringUtils.isNotEmpty(additionalProgs))
			  {
				  String strAdditionalProg = "<![CDATA[<tr><td style=\"vertical-align:top;\">]]>Additional Programs:<![CDATA[</td><td class=\"fRight alignRight\">]]>"+additionalProgs+"<![CDATA[</td></tr>]]>";
				  templateProps.put(ADDITION_PROGS, strAdditionalProg);
			  }else{
				  templateProps.put(ADDITION_PROGS, "");
			  }				
			  //emailHelper.sendMailWithBCC(email, bccMailAddress,  "", SUBMIT_TOS_CONF_EN, templateProps, request.getCompanyCode());
		  } else{
			  templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD_ES);
			  if(StringUtils.isNotEmpty(additionalProgs))
			  {
				  String strAdditionalProg = "<![CDATA[<tr><td style=\"vertical-align:top;\">]]>Programas adicionales:<![CDATA[</td><td class=\"fRight alignRight\">]]>"+additionalProgs+"<![CDATA[</td></tr>]]>";
				  templateProps.put(ADDITION_PROGS, strAdditionalProg);
			  }else{
				  templateProps.put(ADDITION_PROGS, "");
			  }	
			  logger.info("Sending mail for successful TOS submission ES");
			  //emailHelper.sendMailWithBCC(email, bccMailAddress,  "", SUBMIT_TOS_CONF_ES, templateProps, request.getCompanyCode());
		  }
		  	response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(MSG_SUCCESS);
				
		}
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.saveTransferServiceDetails :::::::: End");
	return response;
}


/**
 * @author ahanda1
 * @param request
 * @param sessionId
 * @return
 */
public OetdspResponse getTDSPSpecificCalendarDates(OetdspRequest request, String sessionId){
	
	logger.info("TOSBO.getTDSPSpecificCalendarDates :::::::: Start");
	OetdspResponse response = new OetdspResponse();
	
    
    try {
    	
    	String dateString = oeService.getTDSPSpecificCalendarDates(request, sessionId);
		
		if (dateString!= null && !dateString.trim().equalsIgnoreCase(""))
			response.setDateString(dateString);
		
		response.setCurrentDate(new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime()));
		response.setCurrentTime(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.getTDSPSpecificCalendarDates :::::::: End");
	return response;
}


/**
 * @author ahanda1
 * @param request
 * @param sessionId
 * @return
 */
public com.multibrand.vo.response.PermitCheckResponse checkPermitRequirment(PermitCheckRequest request, String sessionId){
	
	logger.info("TOSBO.checkPermitRequirment :::::::: Start");
	com.multibrand.vo.response.PermitCheckResponse response = new com.multibrand.vo.response.PermitCheckResponse();
    try {
    	
    	PermitCheckResponse permitCheckResponse = oeService.checkPermitRequirment(request, sessionId);
		
		if(permitCheckResponse!= null && StringUtils.isNotEmpty(permitCheckResponse.getStrErrCode())){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(permitCheckResponse.getStrErrCode());				
		}
		
		JavaBeanUtil.copy(permitCheckResponse, response);
		
		
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.checkPermitRequirment :::::::: End");
	return response;
}

/**
 * @author ahanda1
 * @param esid
 * @param companyCode
 * @param sessionId
 * @return
 */
public com.multibrand.vo.response.EsidProfileResponse getESIDProfile(String esid, String companyCode, String sessionId){
	
	logger.info("TOSBO.getESIDProfile :::::::: Start");
	com.multibrand.vo.response.EsidProfileResponse response = new com.multibrand.vo.response.EsidProfileResponse();
    try {
    	
    	EsidProfileResponse esidProfileResponse = profileService.getESIDProfile(companyCode, esid, sessionId);
		
		if(esidProfileResponse!= null && StringUtils.isNotEmpty(esidProfileResponse.getErrorCode())){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(esidProfileResponse.getErrorCode());				
		}
		
		JavaBeanUtil.copy(esidProfileResponse, response);
		
		
		
	} catch (RemoteException e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);			
	} catch (Exception e) {
		logger.error(e);
		response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		throw new OAMException(200, e.getMessage(), response);	
	}
	
	
	logger.info("TOSBO.getESIDProfile :::::::: End");
	return response;
}
	/**
	 * @author DKRISHN1
	 * @param tosAMBRequest
	 * @return
	 */
	public TosAmbWebResponse getTosAMBDetails(com.multibrand.domain.TosAmbWebRequest tosAMBRequest, String sessionId)
	{
		logger.info("TOSBO.getTosAMBDetails :::::::: Start");
		com.multibrand.vo.response.TosAmbWebResponse res = new com.multibrand.vo.response.TosAmbWebResponse();
		try{
			com.multibrand.domain.TosAmbWebResponse response = tosService.getTosAMBDetails(tosAMBRequest, sessionId);
			
			if(response!= null && StringUtils.isNotEmpty(response.getErrorCode())){
				logger.info("TOSBO.getTosAMBDetails :::::::: response.getErrorCode::::::::::"+response.getErrorCode());
				res.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				res.setResultDescription(response.getErrorCode());				
			}
			
			JavaBeanUtil.copy(response, res);
			
		} catch (RemoteException e) {
			logger.error(e);
			res.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			res.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), res);			
		} catch (Exception e) {
			logger.error(e);
			res.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			res.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), res);	
		}	
	
		logger.info("TOSBO.checkPendingMVO :::::::: END");
		return res;
		
	}

	public TOSEligibleNonEligibleProductsResponse tosEligibleNonEligibleProducts(TOSEligibleNonEligibleProductsRequest request) {
		
		TOSEligibleNonEligibleProductsResponse response = new TOSEligibleNonEligibleProductsResponse();
		try {
			response = tosService.tosEligibleNonEligibleProducts(request);
		} catch (Exception ex) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Error occured while getting eligible, non-eligible TOS products from CCS.", ex);
		}
		return response;
	}

	public TOSSubmitEligibleProductsResponse tosSubmitEligibleProducts(TOSSubmitEligibleProductsRequest request) {

		TOSSubmitEligibleProductsResponse response = new TOSSubmitEligibleProductsResponse();
		try {
			response = tosService.tosSubmitEligibleProducts(request);
		} catch (Exception ex) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Error occured while executing TOSBO.tosSubmitEligibleProducts().", ex);
		}
		return response;
	}
	
	public ESIDForAddressResponse removeInactiveEsids(EsidResponse esiidResponse){
		logger.info("TOSBO.removeInactiveEsids :::::::: START");
		
		ESIDForAddressResponse esIdForAddressResponse = new ESIDForAddressResponse();
		try {
			 List<ESIDData>  esidList = new  ArrayList<>();
		
			 //Filtering Inaqctive ESID's
			if ( esiidResponse.getEsidList() != null && !esiidResponse.getEsidList().isEmpty()) {
				
				for (ESIDData esIdData : esiidResponse.getEsidList()) {
					
					if ( esIdData.getEsidStatus().equalsIgnoreCase(STATUS_ACTIVE)) {
						
						esidList.add(esIdData);
					}
				}
				esiidResponse.setEsidList(esidList);
			}
			
			if ( esiidResponse.getEsidList() != null 
					&& !esiidResponse.getEsidList().isEmpty() 
					&&  esiidResponse.getEsidList().size() == 1) {
				esIdForAddressResponse.setPointofDeliveryID(esiidResponse.getEsidList().get(0).getEsidNumber());
				esIdForAddressResponse.setServiceId(this.appConstMessageSource
						.getMessage(esiidResponse.getEsidList().get(0).getEsidTDSP(), null,
								null));
				esIdForAddressResponse.setCustomerClass(esiidResponse.getEsidList().get(0).getEsidClass());

				
				esIdForAddressResponse.setMeterType("METERED");
				
			} else {
				esIdForAddressResponse.setPointofDeliveryID(ESIDNOTFOUND);
				esIdForAddressResponse.setResultCode("1");
				esIdForAddressResponse.setResultDescription("MSG_ERR_ESI_LOOKUP");
				esIdForAddressResponse.setResultDisplayText("Sorry! Something went wrong. Please try again");
			}
			
			
			
		}  catch (Exception e) {
			logger.error(e);
			esIdForAddressResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			esIdForAddressResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
					
		}
		
	
		logger.info("TOSBO.removeInactiveEsids :::::::: END");
		return esIdForAddressResponse;
		
	}
	
	
	public OfferPlanContentResponse tosMoveOutPlans(String locale, String companyCode, String brandId,
			String servStreetNum, String servStreetName,
			String servStreetAptNum, String servZipCode, String promoCode,
			String tdspCode, String esid, String sessionId,String transactionType) {
		
		OfferPlanContentResponse response = new OfferPlanContentResponse();
		OfferResponse offerResponse = oeBO.getOffers(locale, companyCode,
				brandId, servStreetNum, servStreetName, servStreetAptNum,
				servZipCode, promoCode, tdspCode, esid,
				sessionId, transactionType);
		
		List<ContractOffer> contractList = new LinkedList<>();
		Set <String> offerCode = null;
		
		OfferDO[] offerStrAr = offerResponse.getOfferDOList();
		
		if (offerStrAr != null && offerStrAr.length > 1) {
			
			offerCode =  new TreeSet<>();
			for (OfferDO offerVO : offerStrAr) {
				if (StringUtils.isNotBlank(offerVO.getStrOfferCode())) {
					ContractOffer contractOffer = new ContractOffer();
					offerCode.add(contentHelper.loadContractOfferResponse(contractOffer, offerVO));
					
					List<OfferPriceWraperDO> offerPriceWraperDOList = offerVO.getAvgPriceMap();
					
					String avgPrice = getAvgPrice( offerPriceWraperDOList);
					
					String offerFamily = getOfferFamilyPrice(offerPriceWraperDOList);
					
					contractOffer.setPrice(avgPrice);
					contractOffer.setOfferFamily(offerFamily);
					
					contractList.add(contractOffer);
				}
			}
			
			response.setPlans(contractList);
			
			
		}
		return response;
	}

	/**
	 * @param OfferPriceWraperDOList
	 */
	public String getAvgPrice(List<OfferPriceWraperDO> offerPriceWraperDOList) {

		String avgPrice = "";

		if (offerPriceWraperDOList != null) {
			for (OfferPriceWraperDO offerPriceWraperDO : offerPriceWraperDOList) {
				if (offerPriceWraperDO.getKey().equalsIgnoreCase("EFL_BR2000")) {

					avgPrice = offerPriceWraperDO.getValue().getPrice();
				}

			}
		}

		return avgPrice;
	}
	
	/**
	 * @param OfferPriceWraperDOList
	 */
	public String getOfferFamilyPrice(List<OfferPriceWraperDO> offerPriceWraperDOList) {

		String offerFamily = "";

		if (offerPriceWraperDOList != null) {
			for (OfferPriceWraperDO offerPriceWraperDO : offerPriceWraperDOList) {
				if (offerPriceWraperDO.getKey().equalsIgnoreCase("E_FAMILY")) {

					offerFamily = offerPriceWraperDO.getValue().getPrice();
				}

			}
		}

		return offerFamily;
	}	

}
