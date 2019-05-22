package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.CreateContactLogRequest;
import com.multibrand.domain.PendingSwapRequest;
import com.multibrand.domain.PendingSwapResponseMaster;
import com.multibrand.domain.RolloverPlanDetailsRequest;
import com.multibrand.domain.RolloverPlanDetailsResponse;
import com.multibrand.domain.SwapRequest;
import com.multibrand.domain.SwapResponse;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.AsyncHelper;
import com.multibrand.helper.EmailHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.SwapService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.vo.request.SubmitSwapRequest;
import com.multibrand.vo.response.SubmitSwapResponse;
import com.multibrand.vo.response.swapResponse.PendingSwapResponse;
import com.multibrand.vo.response.swapResponse.RolloverPlanResponse;

/**
 * This BO class is to handle all the Swap Related API calls.
 * 
 * @author Kdeshmu1
 */
@Component
public class SwapBO extends BaseAbstractService implements Constants {

	@Autowired
	SwapService swapService;
	
	@Autowired
	EmailHelper emailHelper;
	
	@Autowired
	AsyncHelper asyncHelper;
	
	

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

/**
 *  This method is for submitting swap info to CCS
 * @param campaignCode
 * @param offerCode
 * @param contractId
 * @param esiId
 * @param contractEndDate
 * @param locale
 * @param companyCode
 * @param accountNumber
 * @param servStreetNum
 * @param servStreetName
 * @param servStreetAptNum
 * @param servCity
 * @param servState
 * @param servZipCode
 * @param billStreetNum
 * @param billStreetName
 * @param billStreetAptNum
 * @param billAddrPOBox
 * @param billCity
 * @param billState
 * @param billZipCode
 * @param contractStartDate
 * @param planName
 * @param avgPrice
 * @param planType
 * @param contractTerm
 * @param cancelFee
 * @param toEmail
 * @param sessionId
 * @param clientSource
 * @return
 */
	public SubmitSwapResponse submitSwap(SubmitSwapRequest request, String sessionId,String source) {

		SwapResponse response = null;
		
		SubmitSwapResponse submitSwapResponse = new SubmitSwapResponse();
		
		try {

			SwapRequest swapRequest = new SwapRequest();
			String agreementNumber = "";
			if(request.getBrandName()!=null && request.getBrandName().equals(CIRRO_BRAND_NAME)) 
			{
				agreementNumber = this.generateAgreementNumber(Constants.WEBCE);
			}
			else 
			{
				agreementNumber = this.generateAgreementNumber(Constants.WEBGM);
			}
			swapRequest.setAgreementNumber(agreementNumber);			
			swapRequest.setCampaignCode(request.getCampaignCode());
			swapRequest.setOfferCode(request.getOfferCode());
			swapRequest.setContractId(request.getContractId());
			SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			swapRequest.setDateSigned(sdfDate.format(Calendar.getInstance()
					.getTime()));
			swapRequest.setEsid(request.getEsid());
			if(request.getOfferDate()!=null && !request.getOfferDate().equals(""))
				swapRequest.setOfferDate(request.getOfferDate());
			else
				swapRequest.setOfferDate(sdfDate.format(Calendar.getInstance().getTime()));
			if(request.getOfferTime()!=null && !request.getOfferTime().equals(""))
				swapRequest.setOfferTime(request.getOfferTime());
			else
				swapRequest.setOfferTime(sdfTime.format(Calendar.getInstance().getTime()));
			swapRequest.setPromoCode(request.getPromoCode());
			String rOEffectiveDate = setROEffectiveDate(request.getCurrentContractEndDate(), request.getLanguageCode(), request.getBrandName());
			swapRequest.setRoEffectiveDate(rOEffectiveDate);
			swapRequest.setStrCompanyCode(request.getCompanyCode());
			swapRequest.setClient(request.getClientSource()); //CHG0020873 
			response = swapService.submitSwap(swapRequest, request.getCompanyCode(), sessionId);
			JavaBeanUtil.copy(response, submitSwapResponse);
			if(submitSwapResponse.getErrorCode()!=null && submitSwapResponse.getErrorCode().equalsIgnoreCase(MSG_ERR_SUBMIT_SWAP))
			{
				logger.info(" submitSwap Error code is  ===> "+response.getErrorCode());
				submitSwapResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				submitSwapResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				return submitSwapResponse;
			}
			submitSwapResponse.setResultCode(RESULT_CODE_SUCCESS);
			submitSwapResponse.setResultDescription(MSG_SUCCESS);
			// Sending mail for submit swap
			logger.info("Sending mail for submit swap");
			HashMap<String, String> templateProps = new HashMap<String,String>();
			templateProps.put(ACCOUNT_NUMBER, request.getAccountNumber());
			templateProps.put(CHECK_DIGIT, request.getCheckDigit());
			templateProps.put(BP_NUMBER, request.getBpNumber());
			String serviceAddress = "";
			serviceAddress = request.getServStreetNum()+ " "+ request.getServStreetName();
			if(!StringUtils.isEmpty(request.getServStreetAptNum()))
				serviceAddress = serviceAddress	+ ", APT# "+ request.getServStreetAptNum();
			String serviceCity = request.getServCity();
			String serviceState = request.getServState();
			String serviceZipCode = request.getServZipCode();
			templateProps.put(SERVICE_ADDRESS, serviceAddress);
			templateProps.put(SERVICE_CITY, serviceCity);
			templateProps.put(SERVICE_STATE, serviceState);
			templateProps.put(SERVICE_ZIP, serviceZipCode);
			templateProps.put(ESID, request.getEsid());	
			
			String billingAddress = "";
			if(StringUtils.isEmpty(request.getBillAddrPOBox()))
			{
				billingAddress = request.getBillStreetNum()+ " "+ request.getBillStreetName();
				if(!StringUtils.isEmpty(request.getBillStreetAptNum()))
					billingAddress = billingAddress	+ ", APT# "+ request.getBillStreetAptNum();
			}
			else
			{
				billingAddress = "P.O. Box "+ request.getBillAddrPOBox();
			}
			String billingCity = request.getBillCity();
			String billingState = request.getBillState();
			String billingZipCode = request.getBillZipCode();
			
			templateProps.put(BILLING_ADDRESS, billingAddress);
			templateProps.put(BILLING_CITY, billingCity);
			templateProps.put(BILLING_STATE, billingState);
			templateProps.put(BILLING_ZIP, billingZipCode);
			String newPlanName = "<![CDATA["+request.getPlanName()+"]]>";
			templateProps.put(NEW_PLAN_NAME,newPlanName);
			templateProps.put(AVG_PRICE, request.getAvgPrice());
			templateProps.put(CONTRACT_TERM, request.getContractTerm());
			templateProps.put(CANCEL_FEE, request.getCancelFee());
			templateProps.put(EFL_URL, request.getEflURL());
			templateProps.put(EFL_SMART_CODE, request.getEflSmartCode());
			templateProps.put(TOS_URL, request.getTosURL());
			templateProps.put(TOS_SMART_CODE, request.getTosSmartCode());
			templateProps.put(YRAAC_URL, request.getYraacURL());
			templateProps.put(YRAAC_SMART_CODE, request.getYraacSmartCode());	
			String bccMailAddress = "";
			
			// Cirro Energy changes
			if(request.getBrandName()!=null && request.getBrandName().equals(CIRRO_BRAND_NAME))  
			{
				templateProps.put("Company_Code", request.getCompanyCode());
				templateProps.put("Brand_Id", request.getBrandName());
				
				if(StringUtils.isBlank(request.getNewContractBegins()))
					templateProps.put(CONTRACT_BEGINS, "");
				else
					templateProps.put(CONTRACT_BEGINS, "<![CDATA["+request.getNewContractBegins()+"]]>");
				
				if(StringUtils.isBlank(request.getNewContractEnds()))
					templateProps.put(CONTRACT_ENDS, "");
				else
					templateProps.put(CONTRACT_ENDS, "<![CDATA["+request.getNewContractEnds()+"]]>");
				
				if(StringUtils.isBlank(request.getMarketingText()))	
					templateProps.put(MARKETING_TEXT, "");	
				else
					templateProps.put(MARKETING_TEXT, "<![CDATA["+request.getMarketingText()+"]]>");
				
				if(StringUtils.isBlank(request.getDisclaimer()))	
					templateProps.put(DISCLAIMER, "");		
				else
					templateProps.put(DISCLAIMER, "<![CDATA["+request.getDisclaimer()+"]]>");
				
				logger.info("CIRRO - HASH MAP Swap Template Parameter====> "+templateProps);

				bccMailAddress = this.envMessageReader.getMessage(QC_CIRRO_BCC_MAIL)+","+this.envMessageReader.getMessage(SWAP_CIRRO_BCC_MAIL);

				if(StringUtils.isBlank(request.getLanguageCode())|| request.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN)){						
					logger.info("Sending mail for Cirro submit swap EN - Brand ["+request.getBrandName()+"] ");
					//emailHelper.sendMailWithBCC(request.getToEmail(), bccMailAddress, "", CIRRO_SUBMIT_SWAP_CONF_EN, templateProps, request.getCompanyCode());
				} else{
					logger.info("Sending mail for Cirro submit swap ES - Brand ["+request.getBrandName()+"] ");
					//emailHelper.sendMailWithBCC(request.getToEmail(), bccMailAddress, "", CIRRO_SUBMIT_SWAP_CONF_ES, templateProps, request.getCompanyCode());
				}				
			}
			else 
			{
				//Changes Start for Adding New Contract Ends in GME Email
				templateProps.put(PRODUCT_CONTENT, request.getProductContent());
				bccMailAddress = this.envMessageReader.getMessage(QC_BCC_MAIL)+","+this.envMessageReader.getMessage(SWAP_BCC_MAIL);
				if(StringUtils.isBlank(request.getLanguageCode())|| request.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN)){
					if(StringUtils.isBlank(request.getNewContractBegins()))
						templateProps.put(CONTRACT_BEGINS, "");
					else
					{
						String strContractBegins = "<![CDATA[<tr><td>]]>Contract Begins:<![CDATA[</td><td class=\"fRight\">]]>"+request.getNewContractBegins()+"<![CDATA[</td></tr>]]>";
						templateProps.put(CONTRACT_BEGINS, strContractBegins);
					}
					if(StringUtils.isBlank(request.getNewContractEnds()))
						templateProps.put(CONTRACT_ENDS, "");
					else
					{
						String strContractEnds = "<![CDATA[<tr><td>]]>Contract Ends:<![CDATA[</td><td class=\"fRight\">]]>"+request.getNewContractEnds()+"<![CDATA[</td></tr>]]>";
						templateProps.put(CONTRACT_ENDS, strContractEnds);
					}
					logger.info("Sending mail for GME submit swap EN");
					//emailHelper.sendMailWithBCC(request.getToEmail(), bccMailAddress, "", SUBMIT_SWAP_CONF_EN, templateProps, request.getCompanyCode());
				} else{
					if(StringUtils.isBlank(request.getNewContractBegins()))
						templateProps.put(CONTRACT_BEGINS, "");
					else
					{
						String strContractBegins = "<![CDATA[<tr><td>]]>Inicio del contrato:<![CDATA[</td><td class=\"fRight\">]]>"+request.getNewContractBegins()+"<![CDATA[</td></tr>]]>";
						templateProps.put(CONTRACT_BEGINS, strContractBegins);
					}
					if(StringUtils.isBlank(request.getNewContractEnds()))
						templateProps.put(CONTRACT_ENDS, "");
					else
					{
						String strContractEnds = "<![CDATA[<tr><td>]]>Contrato Finaliza:<![CDATA[</td><td class=\"fRight\">]]>"+request.getNewContractEnds()+"<![CDATA[</td></tr>]]>";
						templateProps.put(CONTRACT_ENDS, strContractEnds);
					}
					logger.info("Sending mail for GME submit swap ES");
					//emailHelper.sendMailWithBCC(request.getToEmail(), bccMailAddress, "", SUBMIT_SWAP_CONF_ES, templateProps, request.getCompanyCode());
				}
				logger.info("GME - HASH MAP Swap Template Parameter====> "+templateProps);
				//Changes End for Adding New Contract Ends in GME Email
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error("error is : "+e);
			submitSwapResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			submitSwapResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), submitSwapResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error is : "+e);
			submitSwapResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			submitSwapResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), submitSwapResponse);
		}
		
		if(submitSwapResponse.getResultCode()!=null && source!=null &&
				(submitSwapResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS)||submitSwapResponse.getResultCode().equalsIgnoreCase(SUCCESS_CODE))&& 
				GME_RES_COMPANY_CODE.equalsIgnoreCase(request.getCompanyCode())&&source.equalsIgnoreCase(MOBILE))
		{
			logger.info("Inside submitSwap:updateContactLog(...) block - in SwapBO");
			CreateContactLogRequest cssUpdateLogRequest = new CreateContactLogRequest();
			cssUpdateLogRequest.setBusinessPartnerNumber(request.getBpNumber());
			cssUpdateLogRequest.setContractAccountNumber(request.getAccountNumber());
			cssUpdateLogRequest.setContactClass(CONTACT_LOG_SWAP_CONTACT_CLASS);
			cssUpdateLogRequest.setContactActivity(CONTACT_LOG_SWAP_CONTACT_ACTIVITY);
			cssUpdateLogRequest.setCommitFlag(CONTACT_LOG_COMMIT_FLAG);
			cssUpdateLogRequest.setContactType(CONTACT_LOG_CONTACT_TYPE);
			cssUpdateLogRequest.setDivision(CONTACT_LOG_DIVISION);
			cssUpdateLogRequest.setTextLines("User with name "+request.getCaName()+" and account number "+request.getAccountNumber()+" has submitted a plan swap request for Contract ID "+request.getContractId()+", ESIID "+request.getEsid()+"+ via the GME Mobile App on "+CommonUtil.getCurrentDateandTime()+". Offer details:"
					+ "Offer Code: "+request.getOfferCode()+""
					+ "Plan Name: "+request.getPlanName()+""
					+ "Term Length: "+request.getContractTerm()+" months+"
					+ "Average Price: "+request.getAvgPrice()+""
					+ "Cancelation Fee: "+request.getCancelFee()+""
					+ "iDoc No: "+submitSwapResponse.getIDOCNumber()+""
					+ "EFL SmartCode: "+request.getEflSmartCode()+""
					+ "TOS SmartCode: "+request.getTosSmartCode()+"");
			cssUpdateLogRequest.setFormatCol("");//Should be Blank
			cssUpdateLogRequest.setCompanyCode(request.getCompanyCode());
			
			logger.info("Start: Async call ContactLogHelper.updateContactLog(...)");
			asyncHelper.asychUpdateContactLog(cssUpdateLogRequest);
			logger.info("End: Async call ContactLogHelper.updateContactLog(...)");
			logger.info("End submitSwap:updateContactLog(...) block - in SwapBO");
		}
		
		
		return submitSwapResponse;
	}

	/**
	 * Method to generate agreement number from the given contractId
	 * @param brandId 
	 * 
	 * @return String
	 */
	public String generateAgreementNumber(String brandId) {
		return brandId+ generateUniqueNumber();
	}

	/**
	 * Method to generate unique Number
	 * @return String
	 */
	public String generateUniqueNumber()
	{
		Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyHHmmss");
    	String str = sdf.format(cal.getTime());
    	return str; 
	}
	
	/**
	 * @author kdeshmu1
	 * @param contractEndDate
	 * @param locale
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String setROEffectiveDate(String contractEndDate, String locale, String brandName) {
		logger.info("SwapHelper:setROEffectiveDate() start");
		java.util.Date today = new java.util.Date();
		String strRequestDate = null;
		String swapROEffectiveDate = null;
		try {
			if (StringUtils.isNotBlank(contractEndDate)) {
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						Constants.RESPONSE_DATE_FORMAT);
				SimpleDateFormat sdf2 = new SimpleDateFormat(
						Constants.MM_dd_yyyy);
				java.util.Date swapDate = sdf2.parse(contractEndDate);
				java.util.Date termDate = sdf1
						.parse(Constants.CONTRACT_END_DATE);
				java.text.SimpleDateFormat enSdf = new java.text.SimpleDateFormat(
						Constants.MM_dd_yyyy, new Locale("en",
								Constants.COUNTRY_US));
				if (StringUtils.isNotBlank(locale)
						&& locale.equalsIgnoreCase(Constants.LANG_ES)) {
					enSdf = new java.text.SimpleDateFormat(
							Constants.MM_dd_yyyy, new Locale("es",
									Constants.COUNTRY_US));
				}

				java.util.Date requestDate = new java.util.Date();
				strRequestDate = enSdf.format(requestDate);

				int value1 = swapDate.compareTo(termDate);
				int value2 = swapDate.compareTo(today);

				if (value1 == 0) {
					swapROEffectiveDate = enSdf.format(today);
				} else {
					if ((value1 < 0) && (value2 < 0)) {
						swapROEffectiveDate = enSdf.format(today);
					} else {
						if(!CIRRO_BRAND_NAME.equals(brandName))
						{
							// Updated logic for GME to send future date + 1 to CCS in RO Effective Date
							Calendar cal = Calendar.getInstance(); 
							cal.setTime(swapDate); 
							cal.add(Calendar.DATE, 1);
							swapDate = cal.getTime();							
						}
						swapROEffectiveDate = enSdf.format(swapDate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" requestSubmittedDate is " + swapROEffectiveDate);
		logger.info(" swap request date is " + strRequestDate);
		logger.info("SwapHelper:setROEffectiveDate() end");
		return swapROEffectiveDate;
	}
	
/**
 * 
 * @param contractId
 * @param companyCode
 * @param sessionId
 * @return
 */
	public RolloverPlanResponse getRollovePlanDetails(String contractId,
			String companyCode, String sessionId) {

		RolloverPlanDetailsResponse response = null;
		RolloverPlanResponse rollOverPlanResponse = new RolloverPlanResponse();
		RolloverPlanDetailsRequest request = new RolloverPlanDetailsRequest();
		request.setContractId(contractId);
		request.setStrCompanyCode(companyCode);

		try {
			response = swapService.getRolloverPlanDetails(request, companyCode, sessionId);
			JavaBeanUtil.copy(response, rollOverPlanResponse);

			rollOverPlanResponse.setResultCode(RESULT_CODE_SUCCESS);
			rollOverPlanResponse.setResultDescription(MSG_SUCCESS);
		} catch (RemoteException e) {
            logger.info("Remote Exception ");
			rollOverPlanResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			rollOverPlanResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), rollOverPlanResponse);
		} catch (Exception e) {
			logger.info("Exception occurred ");
			rollOverPlanResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			rollOverPlanResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), rollOverPlanResponse);
		}

		return rollOverPlanResponse;

	}
	
	/**
	 * @author kdeshmu1
	 * @param bpid
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @param contractID
	 * @param esid
	 * @param language
	 * @param sessionId
	 * @return
	 */
	public PendingSwapResponse pendingSwapDetails(String bpid, String accountNumber,String  companyCode,
			String brandName, String contractID, String esid,String language, String sessionId) {

		PendingSwapResponseMaster response = null;
		PendingSwapRequest request = new PendingSwapRequest();
		
		PendingSwapResponse pendingSwapResponse = new PendingSwapResponse();
		
		request.setStrBPNumber(bpid);
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		request.setStrContractID(contractID);
		request.setStrESIID(esid);
		request.setStrLanguage(language);
		

		try {
				logger.info(" START of the pendingSwapDetails() method of SwapBO");
				response = swapService.pendingSwapDetails(request, companyCode, sessionId);
				
				JavaBeanUtil.copy(response, pendingSwapResponse);
				
	
				pendingSwapResponse.setResultCode(RESULT_CODE_SUCCESS);
				
				pendingSwapResponse.setResultDescription(MSG_SUCCESS);
				
		} catch (RemoteException e) {
            logger.info("Remote Exception ");
            pendingSwapResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
            pendingSwapResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), pendingSwapResponse);
		} catch (Exception e) {
			logger.info("Exception occurred ");
			pendingSwapResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			pendingSwapResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), pendingSwapResponse);
		}

		return pendingSwapResponse;

	}
	
}


