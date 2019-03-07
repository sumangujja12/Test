package com.multibrand.bo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BillDAO;
import com.multibrand.domain.ActEbillRequest;
import com.multibrand.domain.ActEbillResponse;
import com.multibrand.domain.AddressDO;
import com.multibrand.domain.AmbCheckRequest;
import com.multibrand.domain.AmbCheckResponse;
import com.multibrand.domain.AmbOutputTab;
import com.multibrand.domain.AmbSignupRequest;
import com.multibrand.domain.AmbSignupResponse;
import com.multibrand.domain.BankCCInfoRequest;
import com.multibrand.domain.BankPaymentInstitutionResponse;
import com.multibrand.domain.CancelOtccPaymentResp;
import com.multibrand.domain.CancelSchdOtccPaymetReq;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.CrmProfileRequest;
import com.multibrand.domain.CrmProfileResponse;
import com.multibrand.domain.DeActEbillRequest;
import com.multibrand.domain.DeActEbillResponse;
import com.multibrand.domain.GetArRequest;
import com.multibrand.domain.PayByBankRequest;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.domain.ProgramStatus;
import com.multibrand.domain.ScheduleOtccPaymentRequest;
import com.multibrand.domain.ScheduleOtccPaymentResponse;
import com.multibrand.domain.UpdPaperBillRequest;
import com.multibrand.domain.UpdPaperBillResponse;
import com.multibrand.domain.ZesAmbOutput;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.BillHelper;
import com.multibrand.helper.EmailHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.proxy.BillingProxy;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.BillingService;
import com.multibrand.service.OfferService;
import com.multibrand.service.PaymentService;
import com.multibrand.service.ProfileService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.JavaBeanUtil;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.AMBEligibilityCheckRequest;
import com.multibrand.vo.request.AutoPayInfoRequest;
import com.multibrand.vo.request.AvgTempRequestVO;
import com.multibrand.vo.request.ProjectedBillRequestVO;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.request.SaveAMBSingupRequestVO;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.CancelPaymentResponse;
import com.multibrand.vo.response.PayByBankResponse;
import com.multibrand.vo.response.PayByCCResponse;
import com.multibrand.vo.response.ProjectedBillResponseList;
import com.multibrand.vo.response.RetroEligibilityResponse;
import com.multibrand.vo.response.billingResponse.AMBEligibiltyCheckResponseVO;
import com.multibrand.vo.response.billingResponse.AMBSignupResponseVO;
import com.multibrand.vo.response.billingResponse.ArMobileGMEResponse;
import com.multibrand.vo.response.billingResponse.AutoPayDetails;
import com.multibrand.vo.response.billingResponse.AutoPayInfoResponse;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankDetails;
import com.multibrand.vo.response.billingResponse.BankInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.BillInfoResponse;
import com.multibrand.vo.response.billingResponse.CcInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.ContractDO;
import com.multibrand.vo.response.billingResponse.CrCardDetails;
import com.multibrand.vo.response.billingResponse.EditCancelOTCCPaymentResponse;
import com.multibrand.vo.response.billingResponse.GetAccountDetailsResponse;
import com.multibrand.vo.response.billingResponse.GetArResponse;
import com.multibrand.vo.response.billingResponse.GetBillingAddressResponse;
import com.multibrand.vo.response.billingResponse.GetPaymentInstitutionResponse;
import com.multibrand.vo.response.billingResponse.PayAccount;
import com.multibrand.vo.response.billingResponse.PayAccountInfoResponse;
import com.multibrand.vo.response.billingResponse.PaymentMethodB;
import com.multibrand.vo.response.billingResponse.PaymentMethodCC;
import com.multibrand.vo.response.billingResponse.PaymentMethodsResponse;
import com.multibrand.vo.response.billingResponse.ScheduleOTCCPaymentResponse;
import com.multibrand.vo.response.billingResponse.StoreUpdatePayAccountResponse;
import com.multibrand.vo.response.billingResponse.UpdateInvoiceDeliveryResponse;
import com.multibrand.vo.response.billingResponse.UpdatePaperFreeBillingResponse;

import com.multibrand.vo.response.historyResponse.PaymentDO;
import com.multibrand.vo.response.historyResponse.PaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.SchedulePaymentResponse;

import oracle.net.aso.e;

/**
 * This BO class is to handle all the Billing Related API calls.
 * 
 * @author Kdeshmu1
 */
@Component
public class BillingBO extends BaseAbstractService implements Constants{

	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private BillHelper billingHelper;
	
	@Autowired
	private EmailHelper emailHelper;
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private BillingProxy billingProxy;
	
	@Autowired 
	private BillDAO billDao;
	
	@Autowired
	private HistoryBO historyBO;
	
	//@Autowired
	//private ReloadableResourceBundleMessageSource appConstMessageSource;

	/**
	 * This method is to get balance information from CCS system.
	 * 
	 * @author Kdeshmu1
	 * @param accountNumber
	 *            Customer Account Number ,BP Number
	 * @return response Provide balance data in GetArResponse object.
	 */
	public com.multibrand.vo.response.billingResponse.GetArResponse getBalance(
			String accountNumber, String bpNumber, String companyCode, String sessionId, String brandName) {

		com.multibrand.domain.GetArResponse response = null;
		GetArResponse getArResponse = new GetArResponse();
		try {
			
			GetArRequest arRequest = new GetArRequest(bpNumber, accountNumber,
					"", companyCode);
			response = billingService.getAR(arRequest, companyCode, sessionId);
			JavaBeanUtil.copy(response, getArResponse);
			if(!StringUtils.isEmpty(response.getStrCreditAmt()))
				getArResponse.setStrCreditAmt(StringUtils.trim(CommonUtil.normalizeNegativeNumber(response.getStrCreditAmt())));
			else
				getArResponse.setStrCreditAmt("");
			getArResponse.setResultCode(RESULT_CODE_SUCCESS);
			getArResponse.setResultDescription(MSG_SUCCESS);			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getArResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			getArResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), getArResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getArResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			getArResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), getArResponse);
		}
		return getArResponse;
	}

	/**
	 * This method retrieve the billing address from the getProfileCall for
	 * Redbull Servivce profile domain
	 * 
	 * @author ahanda1
	 * @param accountNumber
	 * @return
	 */

	public GetBillingAddressResponse getBillingAddress(String accountNumber, String companyCode, String sessionId) {

		ProfileResponse profileResponse = null;
		GetBillingAddressResponse getBillingAddressResp = new GetBillingAddressResponse();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			logger.info("Billing response before");
			responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
			
			
			
			if(responseMap!= null && responseMap.size()!= 0)
			{
				profileResponse= (ProfileResponse)responseMap.get("profileResponse");
			}
			
			logger.info("Billing response after ");
			
			
			if(profileResponse.getContractAccountDO()!= null)
			{
				AddressDO billingAddress = profileResponse.getContractAccountDO().getBillingAddressDO();
				
				
				
				JavaBeanUtil.copy(billingAddress, getBillingAddressResp);
				getBillingAddressResp.setResultCode(RESULT_CODE_SUCCESS);
				getBillingAddressResp.setResultDescription(MSG_SUCCESS);
			}
			else
			{
				
				
			    getBillingAddressResp.setResultCode(RESULT_CODE_THREE);
			    getBillingAddressResp.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
		} catch (RemoteException e) {
			
			getBillingAddressResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			getBillingAddressResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), getBillingAddressResp);
		} catch (Exception e) {
			getBillingAddressResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			getBillingAddressResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), getBillingAddressResp);
		}
		return getBillingAddressResp;
	}

	/**
	 * 
	 * *This method retrieve the account details from the getProfileCall for
	 * Redbull Service profile domain
	 * @author ahanda1
	 * @param accountNumber
	 * @return
	 */
	public GetAccountDetailsResponse getAccountDetails(String accountNumber, String companyCode, String brandName, String sessionId) {

		ProfileResponse response = null;
		GetAccountDetailsResponse accountDetailsResp = new GetAccountDetailsResponse();
		Map<String, Object> responseMap = new HashMap<String, Object>();

		try {			
			responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
			if(responseMap!= null && responseMap.size()!= 0)
			{
			response= (ProfileResponse)responseMap.get("profileResponse");			
			if(response.getContractAccountDO()!=null)
			{
				JavaBeanUtil.copy(response, accountDetailsResp);
				if(accountDetailsResp.getContractAccountDO().getStrMultiContractFlag()!=null && accountDetailsResp.getContractAccountDO().getStrMultiContractFlag().trim().equalsIgnoreCase("X"))
				{
					accountDetailsResp.getContractAccountDO().setStrMultiContractFlag("Y");
				}
				//Changes Start for adding EFL, TOS & YRAAC codes
			   ContractDO[] contractDO = accountDetailsResp.getContractAccountDO().getListOfContracts();
				for(ContractDO contract:contractDO)
				{
					
					logger.info("contract MVO date :::::: " + contract.getStrMoveOutDate());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					////US US12202 Changes - DK - 09/19/2019
					if(StringUtils.isNotBlank(contract.getStrContractID()))
					{
						String strTouAndCO = (String)responseMap.get(contract.getStrContractID());
						if(StringUtils.isNotBlank(strTouAndCO))
						{
							final String co = strTouAndCO.split("-")[0];
							final String strTouFlag = strTouAndCO.split("-")[1];
							if(contract.getStrContractID().equalsIgnoreCase(co))
							{
								contract.setStrTouFlag(strTouFlag);
								logger.info("strTouFlag :::::: " +strTouFlag);
							}
						}
					}
					
					try{
						//START::GME Residential OE Phase-II - Setting OfferCategory
						if(null != contract.getCurrentPlan() && StringUtils.isNotBlank(contract.getCurrentPlan().getStrOfferCode())) {
							contract.getCurrentPlan().setStrOfferCategory(EMPTY); // set default offer category as empty instead of 'null'
							List<Map<String, Object>> offerCategoryLookupDetailsList = offerService.getOfferCategories(contract.getCurrentPlan().getStrOfferCode());
							if(null != offerCategoryLookupDetailsList && offerCategoryLookupDetailsList.size() > 0) {
								Map<String, Object> offerCategoryMap =  offerCategoryLookupDetailsList.get(0);
								if(null != offerCategoryMap && offerCategoryMap.containsKey(OE_OFFER_CATEGORY) ) {
									if(null != offerCategoryMap.get(OE_OFFER_CATEGORY)) {
										contract.getCurrentPlan().setStrOfferCategory(offerCategoryMap.get(OE_OFFER_CATEGORY).toString());
									}
								}
							}
						}
						//END::GME Residential OE Phase-II
					}catch(Exception ex){
						logger.error("Error in getting current plan offer category! Skip and continue." +ex.getMessage());
					}
					
					if(contract.getStrMoveOutDate()!=null && !contract.getStrMoveOutDate().equals(""))
					{
						Date mvoDate = sdf.parse(contract.getStrMoveOutDate());
						logger.info("Parsed Date object : " + mvoDate );
						Date currentDate = sdf.parse(sdf.format(Calendar.getInstance().getTime()));
						if(mvoDate.after(currentDate)|| mvoDate.equals(currentDate))
						{
							logger.info("Active Contract!! MVO Date :: "+ mvoDate);
							com.multibrand.vo.response.OfferDO offer = new com.multibrand.vo.response.OfferDO();
							offer = profileService.getCurrentOfferDocs(accountNumber, accountDetailsResp.getContractAccountDO().getStrBPNumber(), contract.getStrESIID(), contract.getStrContractID(), accountDetailsResp.getContractAccountDO().getStrLanguageCode(), companyCode, offer, sessionId);
							contract.setEflDocID(offer.getStrEFLDocID());
							contract.setEflSmartCode(offer.getStrEFLSmartCode());
							contract.setTosDocID(offer.getStrTOSDocID());
							contract.setTosSmartCode(offer.getStrTOSSmartCode());
							contract.setYraacDocID(offer.getStrYRAACDocID());
							contract.setYraacSmartCode(offer.getStrYRAACSmartCode());
						}else{
							logger.info("Inactive contract!! MVO Date :: " +mvoDate);
						}
					}
					else
					{
						logger.info("Inactive contract!! MVO Date :: " +contract.getStrMoveOutDate()+" or Company code is "+companyCode);
					}
						
						// disabled this catch because this code is already in try catch block which handles response in case of exceptions of type Exception. Enabling this catch block wouldn't allow proper response generation for Exception scenario
					/*}catch (Exception ex){
						logger.error("Exception occured in date parsing!!" + ex);
					}*/
				}
				//Changes End for adding EFL, TOS & YRAAC codes
				CrmProfileRequest crmProfileRequest = new CrmProfileRequest();
				crmProfileRequest.setStrCANumber(accountNumber);
				if(response != null && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)&& StringUtils.isNotEmpty(response.getSuperBPID())){
					logger.info("Brand name :::: CE :::::::");
					logger.info("Super BPID :::: "+ response.getSuperBPID());
					crmProfileRequest.setStrBPNumber(response.getSuperBPID());
				} else{
				    crmProfileRequest.setStrBPNumber(accountDetailsResp.getContractAccountDO().getStrBPNumber());
				}
				CrmProfileResponse crmProfileResponse = profileService.getCRMProfile(crmProfileRequest, companyCode, sessionId);
				JavaBeanUtil.copy(crmProfileResponse, accountDetailsResp);
				accountDetailsResp.setResultCode(RESULT_CODE_SUCCESS);
				accountDetailsResp.setResultDescription(MSG_SUCCESS);
				//US- || DK || Payment Receipt Validation | 10/31/2018
				Map<String, Object> paymentReceiptlogMap =  billingHelper.getThirdPartyPaymentLog(companyCode, accountNumber);
				if (null == paymentReceiptlogMap || paymentReceiptlogMap.isEmpty()) {
					accountDetailsResp.setPaymentReceiptPopupShowFlag(FLAG_N);
				} 
				else {
					accountDetailsResp.setPaymentReceiptPopupShowFlag(FLAG_Y);
				}
				//US- || DK || Payment Receipt Validation | 10/31/2018
			}
			}
			else
			{
				accountDetailsResp.setResultCode(RESULT_CODE_CCS_ERROR);
				accountDetailsResp.setResultDescription(response.getErrorCode());
			}				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			accountDetailsResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			accountDetailsResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), accountDetailsResp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			accountDetailsResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			accountDetailsResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), accountDetailsResp);
		}
		return accountDetailsResp;

	}
	/**
	 * 
	 * *This method update billing option
	 * 
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param flag
	 * @return
	 */
	public UpdatePaperFreeBillingResponse updatePaperFreeBilling(String accountNumber,String flag,String companyCode, String sessionId) {

		ActEbillResponse actEbillResponse = null;
		ActEbillRequest actEbillRequest = null;
		DeActEbillResponse deActEbillResponse = null;
		DeActEbillRequest deActEbillRequest = null;
		UpdatePaperFreeBillingResponse updatePaperFreeBillingResponse = new UpdatePaperFreeBillingResponse();
		try {
			
			if(flag!=null && (flag.equalsIgnoreCase("true")||flag.equalsIgnoreCase("false")))
			{
			 if(flag.equalsIgnoreCase("true")){
				
				actEbillRequest = new ActEbillRequest();
				actEbillRequest.setStrCompanyCode(companyCode);
				actEbillRequest.setStrContAcct(accountNumber);
				actEbillRequest.setStrContract("");
				//actEbillRequest.setStrPaperBill(Constants.PAPER_FLAG);
				actEbillRequest.setStrPaperBill("");
				actEbillRequest.setStrSource(Constants.EBPP_SOURCE);
				actEbillResponse = billingService.activateEbill(actEbillRequest, companyCode, sessionId);				
				if (actEbillResponse.getStrRespCode()!=null && actEbillResponse.getStrRespCode().equals("0")){
					updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_SUCCESS);
					updatePaperFreeBillingResponse.setResultDescription(MSG_SUCCESS);
				}
				else
				{
					updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_NO_DATA);
					updatePaperFreeBillingResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				}
			 }else{
				deActEbillRequest = new DeActEbillRequest();
				deActEbillRequest.setStrCompanyCode(companyCode);
				deActEbillRequest.setStrContAcc(accountNumber);
				deActEbillRequest.setStrDeactivationReason("");
				deActEbillRequest.setStrMessage("");
				deActEbillResponse = billingService.deactivateEbill(deActEbillRequest, companyCode, sessionId);
				if (deActEbillResponse.getStrResponseCode()!=null && deActEbillResponse.getStrResponseCode().equals("0")){
					updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_SUCCESS);
					updatePaperFreeBillingResponse.setResultDescription(MSG_SUCCESS);
				}
				else
				{
					updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_NO_DATA);
					updatePaperFreeBillingResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				}
			 }
			}
			else
			{
				updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_NO_DATA);
				updatePaperFreeBillingResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
		} catch (RemoteException e) {
			updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePaperFreeBillingResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), updatePaperFreeBillingResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updatePaperFreeBillingResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePaperFreeBillingResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), updatePaperFreeBillingResponse);
		}
		return updatePaperFreeBillingResponse;
	}
	
	
	
	/**
	 * @author ahanda1	
	 * @param accountNumber
	 * @param bpid
	 * @param bankAccountNumber
	 * @param bankRoutingNumber
	 * @param paymentAmount
	 * @param paymentDate
	 * @param companyCode
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @param sessionId
	 * @param brandName
	 * @param emailTypeId
	 * @return PayByBankResponse
	 */
	public PayByBankResponse submitBankPayment(String accountNumber, String bpid, String bankAccountNumber, String bankRoutingNumber, String paymentAmount,
			String paymentDate,String companyCode, String accountName, String accountChkDigit, String locale,  String email, String sessionId, 
			String brandName, String emailTypeId) {
		                                         
		PayByBankRequest request = new PayByBankRequest();
		request.setStrBankAccNumber(bankAccountNumber);
		request.setStrBPNumber(bpid);
		request.setStrBankRoutingNumber(bankRoutingNumber);
		request.setStrPayAmount(paymentAmount);
		request.setStrPaymentDate(paymentDate);
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		
		PayByBankResponse payByBankResponse = new PayByBankResponse();
		
		try {
			com.multibrand.domain.PayByBankResponse response = paymentService.submitBankPayment(request, companyCode, sessionId,brandName);
			
			if(response.getStrXCODE()!= null)payByBankResponse.setStrXCODE(response.getStrXCODE());
			
			String confNumber = response.getStrOTBDId();
			if(confNumber!=null && !confNumber.equalsIgnoreCase("")){
				payByBankResponse.setStrOTBDId(confNumber);
			}
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				payByBankResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				payByBankResponse.setResultDescription(response.getErrorMessage());
				payByBankResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)payByBankResponse.setErrorMessage(response.getErrorMessage());
			}else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !CIRRO_BRAND_NAME.equalsIgnoreCase(brandName) && 
					!CommonUtil.checkIfGMEPrepay(companyCode, brandName, emailTypeId)){
				String submitPayFlag = getEndPointUrl(SUBMIT_PAY_EMAIL_FLAG);
				if(StringUtils.isNotBlank(submitPayFlag) && StringUtils.equalsIgnoreCase(submitPayFlag, "Y")){
					logger.info("Sending mail for payment successful");
					
					HashMap<String, String> templateProps = new HashMap<String,String>();
					
					String transactionDate = (new SimpleDateFormat(MM_dd_yyyy)).format(Calendar.getInstance().getTime());
					
					String maskBankAcctNumber = CommonUtil.maskBankAccountNo(bankAccountNumber);
					
					String splitPaymentAmount = "";
					
					if(!StringUtils.isEmpty(paymentAmount))
						splitPaymentAmount = CommonUtil.splitPaymentAmountDecimal(paymentAmount);
					
					templateProps.put(TRANSACTION_DATE, transactionDate);
					templateProps.put(PAYMENT_AMOUNT, splitPaymentAmount);
					templateProps.put(SCHEDULED_PAYMENT_DATE, paymentDate);	
					templateProps.put(BANK_ACCOUNT_NUMBER, maskBankAcctNumber);
					templateProps.put(BANK_ROUTING_NUMBER, bankRoutingNumber);
					
					if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){
						templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK);
						logger.info("Sending mail for successful payment EN");
						emailHelper.sendMailWithBCC(email,this.envMessageReader.getMessage(QC_BCC_MAIL), "", BILL_PAY_BANK_EN, templateProps, companyCode);
					} else{
						templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_BANK_ES);
						logger.info("Sending mail for successful payment ES");
						emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL), "", BILL_PAY_BANK_ES, templateProps, companyCode);
					}
				}
			}
			
		} catch (RemoteException e) {

			payByBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			payByBankResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), payByBankResponse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			payByBankResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			payByBankResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), payByBankResponse);
			
		}
	
		
		return payByBankResponse;
		
	}
	
	/**
	 * @param authType
	 * @param accountNumber
	 * @param bpid
	 * @param ccNumber
	 * @param cvvNumber
	 * @param expirationDate
	 * @param billingZip
	 * @param paymentAmount
	 * @param accountName
	 * @param accountChkDigit
	 * @param locale
	 * @param email
	 * @param companyCode
	 * @param sessionId
	 * @param paymentDate
	 * @param brandName
	 * @param emailTypeId
	 * @return
	 */
	public PayByCCResponse submitCCPayment(String authType, String accountNumber, String bpid, String ccNumber, String cvvNumber, String expirationDate,
			String billingZip, String paymentAmount, String accountName, String accountChkDigit, String locale, String email, String companyCode, 
			String sessionId, String paymentDate, String brandName, String emailTypeId){
		
		
		logger.info("BillingBO.submitCCPayment :: START");
		com.multibrand.domain.PayByCCRequest request = new com.multibrand.domain.PayByCCRequest();
		
		request.setStrBPNumber(bpid);
		request.setStrCANumber(accountNumber);
		request.setStrCCNumber(ccNumber);
		if(cvvNumber!=null)
			request.setStrCVVNumber(cvvNumber);
		request.setStrDuplicatePayment("X");
		request.setStrExpirationDate(expirationDate);
		request.setStrPayAmount(paymentAmount);
		request.setStrBillingZip(billingZip);

		PayByCCResponse payByCCResponse = new PayByCCResponse();
		
		try {
			com.multibrand.domain.PayByCCResponse response = paymentService.submitCCPayment(request, companyCode, sessionId,brandName);
			
			if(response.getStrXCode()!= null)payByCCResponse.setStrXCODE(response.getStrXCode());
			
			String confNumber = response.getStrXValidNum();
			if(confNumber!=null && !confNumber.equalsIgnoreCase("")){
				payByCCResponse.setStrXValidNum(confNumber);
			}
			
			if(response.getErrorCode()!=null && !response.getErrorCode().equalsIgnoreCase("")){
				payByCCResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				payByCCResponse.setResultDescription(response.getErrorMessage());
				payByCCResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)payByCCResponse.setErrorMessage(response.getErrorMessage());
			}else if(!CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && !CIRRO_BRAND_NAME.equalsIgnoreCase(brandName) && 
					!CommonUtil.checkIfGMEPrepay(companyCode, brandName, emailTypeId)){
				String submitPayFlag = getEndPointUrl(SUBMIT_PAY_EMAIL_FLAG);
				if(StringUtils.isNotBlank(submitPayFlag) && StringUtils.equalsIgnoreCase(submitPayFlag, "Y")){
	                logger.info("Sending mail for payment successful");
					
					HashMap<String, String> templateProps = new HashMap<String,String>();
					
					String transactionDate = (new SimpleDateFormat(MM_dd_yyyy)).format(Calendar.getInstance().getTime());
					
					String cardType = "";
					if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZVIS))
						cardType = VISA;
					else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZMCD))
						cardType = MASTERCARD;
					else if(!StringUtils.isEmpty(authType) && authType.equalsIgnoreCase(ZDSC))
						cardType = DISCOVER;
					
					String maskCCNumber = CommonUtil.maskCCNo(ccNumber);
					
					String splitPaymentAmount = "";
					
					if(!StringUtils.isEmpty(paymentAmount))
						splitPaymentAmount = CommonUtil.splitPaymentAmountDecimal(paymentAmount);
					
					templateProps.put(TRANSACTION_DATE, transactionDate);
					templateProps.put(PAYMENT_AMOUNT, splitPaymentAmount);
					templateProps.put(SCHEDULED_PAYMENT_DATE, paymentDate);	
					templateProps.put(CARD_TYPE, cardType);
					templateProps.put(CARD_NUMBER, maskCCNumber);
					templateProps.put(EXP_DATE, expirationDate);
					
					if(StringUtils.isBlank(locale)|| locale.equalsIgnoreCase(LANGUAGE_CODE_EN)){
						templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD);
						logger.info("Sending mail for successful payment EN");
						emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL), "", BILL_PAY_CC_EN, templateProps, companyCode);
					} else{
						templateProps.put(PAYMENT_METHOD, PAYMENT_METHOD_CARD_ES);
						logger.info("Sending mail for successful payment ES");
						emailHelper.sendMailWithBCC(email, this.envMessageReader.getMessage(QC_BCC_MAIL), "", BILL_PAY_CC_ES, templateProps, companyCode);
					}
				}
			}
			
		} catch (RemoteException e) {

			payByCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			payByCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), payByCCResponse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			payByCCResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			payByCCResponse.setResultDescription("Exception Occured");
			throw new OAMException(200, e.getMessage(), payByCCResponse);
			
		}
		
		logger.info("BillingBO.submitCCPayment :: END");
		return payByCCResponse;
	
	}
	
	/**
	 * @author smuruga1
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	public ProjectedBillResponseList getProjectedBill(String esiId,
			String accountNumber,
			String companyCode,String sessionId)
	{
		logger.info("START-[BillingBO-getProjectedBill]");
		ProjectedBillRequestVO projReq = new ProjectedBillRequestVO();
		ProjectedBillResponseList projBillResp = null;
		projReq.setContractAccountNumber(CommonUtil.paddedCa(accountNumber));
		projReq.setCompanyCode(companyCode);
		projReq.setEsIid(esiId);
		try {
			projBillResp = billingHelper.getProjectedBillDetails(projReq, companyCode, sessionId);

			if (projBillResp != null
					&& (projBillResp.getProjectedBillResponse() != null && projBillResp
							.getProjectedBillResponse().size() > 0)) {
				projBillResp.setResultCode(RESULT_CODE_SUCCESS);
				projBillResp.setResultDescription(MSG_SUCCESS);

			} else {
				projBillResp = new ProjectedBillResponseList();
				projBillResp.setResultCode(RESULT_CODE_NO_DATA);
				projBillResp
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

			//JavaBeanUtil.copy(projBillResp, projBillResp);
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getProjectedBill"
					+ e.getMessage());
			logger.info(e.getMessage());
			logger.info(e.getCause());
			logger.error(" Error "+e.getMessage());
			projBillResp = new ProjectedBillResponseList();
			projBillResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			projBillResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), projBillResp);
		}
		logger.info("END-[BillingBO-getProjectedBill]");
		return projBillResp;
	}
	
	
	/**
	 * @author smuruga1
	 * @param billStartDate
	 * 		 instance to store the billStart Date
	 * @param billEndDate
	 * 		 instance to store the billEnd Date
	 * @param zoneId
	 * 		  instance to store the zoneId
	 * @param sessionId
	 * 	   instance to store the sessionId
	 * @return AvgTempResponse
	 * 		response object hold the average temperatureValue
	 */
	public AvgTempResponse getAvgTempBill(String billStartDate, String billEndDate, 
			 String zoneId, String companyCode, String sessionId)
	{
		logger.info("START-[BillingBO-getAvgTempBill]");
		AvgTempResponse avgTempResponse = null;
		AvgTempRequestVO avgTmpRequestVO = new AvgTempRequestVO();
		
		logger.info(" billStartDate "+billStartDate);
		logger.info(" billEndDate "+billEndDate);
		logger.info(" zoneId "+zoneId);
		avgTmpRequestVO.setBillStartDate(billStartDate);
		avgTmpRequestVO.setBillEndDate(billEndDate);
		avgTmpRequestVO.setZoneId(zoneId);
		
		
		try {
			avgTempResponse = billingHelper.getAverageTempBill(avgTmpRequestVO, companyCode,sessionId);

			if (avgTempResponse != null) {
				avgTempResponse.setResultCode(RESULT_CODE_SUCCESS);
				avgTempResponse.setResultDescription(MSG_SUCCESS);

			}
			else {
				avgTempResponse = new AvgTempResponse();
				avgTempResponse.setResultCode(RESULT_CODE_NO_DATA);
				avgTempResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

			//JavaBeanUtil.copy(avgTempResponse, avgTempResponse);
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getAvgTempBill"
					+ e.getCause());
			//System.out.println(e.getMessage());
			//System.out.println(e.getCause());
			logger.error(" Error "+e.getMessage());
			avgTempResponse = new AvgTempResponse();
			avgTempResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			avgTempResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), avgTempResponse);
		}
		
		
		logger.info("END-[BillingBO-getAvgTempBill]");
		return avgTempResponse;
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param bpid
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	public BillInfoResponse billInfo(
			String accountNumber,String bpid,String contractNumber,
			String companyCode, String brandName,String sessionId )
	{
		logger.info("START-[BillingBO-billInfo]");
		try {
			BillInfoResponse billInfoResponse = billingService.billInfo(accountNumber, bpid, contractNumber, sessionId, companyCode);

			logger.info("END-[BillingBO-getProjectedBill]");
			return billInfoResponse;			
		} 
		catch (RemoteException e) {
			logger.info(" Exeception Occured in the getProjectedBill"
					+ e.getMessage());
			BillInfoResponse billInfoResponse = new BillInfoResponse();
			billInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			billInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), billInfoResponse);
		}
		catch (Exception e) {
			logger.info(" Exeception Occured in the getProjectedBill"
					+ e.getMessage());
			BillInfoResponse billInfoResponse = new BillInfoResponse();
			billInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			billInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), billInfoResponse);
		}
		
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param paymentId
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public CancelPaymentResponse doCancelPayment(String accountNumber,
			String companyCode,String paymentId,String brandName,String sessionId)
	{
		logger.info("START-[BillingBO-doCancelPayment]");
		CancelPaymentResponse cancelPaymentResponse =  new CancelPaymentResponse();
		com.multibrand.domain.CancelPaymentResponse response = null;
		
		try {
			response = paymentService.doCancelPayment(accountNumber, companyCode, paymentId,brandName, sessionId);
			
			if (response.getErrorCode()!= null && !response.getErrorCode().equals("")) {
			
				cancelPaymentResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)cancelPaymentResponse.setResultDescription(response.getErrorMessage());
				cancelPaymentResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)cancelPaymentResponse.setErrorMessage(response.getErrorMessage());

			} else {
				
				cancelPaymentResponse.setSuccessCode(response.getStrStatus());
				cancelPaymentResponse.setResultCode(RESULT_CODE_SUCCESS);
				cancelPaymentResponse.setResultDescription(MSG_SUCCESS);
				
			}
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the doCancelPayment"
					+ e.getMessage());
			logger.info(e.getMessage());
			logger.info(e.getCause());
			logger.error(" Error "+e.getMessage());
			cancelPaymentResponse = new CancelPaymentResponse();
			cancelPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			cancelPaymentResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), cancelPaymentResponse);
		}
		logger.info("END-[BillingBO-doCancelPayment]");
		return cancelPaymentResponse;
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param ebillflag
	 * @param paperFlag
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	public UpdateInvoiceDeliveryResponse updateInvoiceDelivery(
			String accountNumber, String ebillflag, String paperFlag, 
			String companyCode, String brandName, String sessionId, String email)
	{
		logger.info("START-[BillingBO-updateInvoiceDelivery]");
		ActEbillResponse actEbillResponse = null;
		ActEbillRequest actEbillRequest = null;
		DeActEbillResponse deActEbillResponse = null;
		DeActEbillRequest deActEbillRequest = null;
		UpdPaperBillRequest updPaperbillReq = null;
		UpdateInvoiceDeliveryResponse updatePaperFreeBillingResponse = new UpdateInvoiceDeliveryResponse();
		try {

			if (((ebillflag == null || ebillflag.isEmpty()) && (paperFlag == null || paperFlag.isEmpty()))
					|| ((ebillflag != null && ebillflag.equalsIgnoreCase("false"))
							&& (paperFlag != null && paperFlag.equalsIgnoreCase("false")))) {
				updatePaperFreeBillingResponse
						.setResultCode(RESULT_CODE_NO_DATA);
				updatePaperFreeBillingResponse
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				return updatePaperFreeBillingResponse;
			} 
			
			if (ebillflag != null && (ebillflag.equalsIgnoreCase("true"))) {
				logger.info("Call the Ebill Activate method");
				actEbillRequest = new ActEbillRequest();
				actEbillRequest.setStrCompanyCode(companyCode);
				actEbillRequest.setStrContAcct(accountNumber);
				actEbillRequest.setStrContract("");
						
				// actEbillRequest.setStrPaperBill(Constants.PAPER_FLAG);
				if (paperFlag != null && paperFlag.equalsIgnoreCase("true")) {
					actEbillRequest.setStrPaperBill(Constants.PAPERLESS_FLAG);
				} else {
					actEbillRequest.setStrPaperBill("");
				}
				actEbillRequest.setStrSource(Constants.EBPP_SOURCE);
				actEbillResponse = billingService.activateEbill(
						actEbillRequest, companyCode, sessionId);
				if (actEbillResponse.getStrRespCode() != null
						&& actEbillResponse.getStrRespCode().equals("0")) {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_SUCCESS);
					updatePaperFreeBillingResponse
							.setResultDescription(MSG_SUCCESS);
				} else {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_NO_DATA);
					updatePaperFreeBillingResponse
							.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
					return updatePaperFreeBillingResponse;
				}
			} else {

				logger.info("Call the Ebill de-Activate method");
				deActEbillRequest = new DeActEbillRequest();
				deActEbillRequest.setStrCompanyCode(companyCode);
				deActEbillRequest.setStrContAcc(accountNumber);
				deActEbillRequest
						.setStrDeactivationReason("");
				deActEbillRequest.setStrMessage("");
				deActEbillResponse = billingService.deactivateEbill(
						deActEbillRequest, companyCode, sessionId);
				if (deActEbillResponse.getStrResponseCode() != null
						&& deActEbillResponse.getStrResponseCode().equals("0")) {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_SUCCESS);
					updatePaperFreeBillingResponse
							.setResultDescription(MSG_SUCCESS);
				} else {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_NO_DATA);
					updatePaperFreeBillingResponse
							.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
					return updatePaperFreeBillingResponse;
				}

			}
			
			/*if ((paperFlag != null && (paperFlag.equalsIgnoreCase("true"))) && 
					(ebillflag != null && ebillflag.equalsIgnoreCase("false"))) {*/
			if (paperFlag != null && paperFlag.equalsIgnoreCase("true")) {
				updPaperbillReq = new UpdPaperBillRequest();
				updPaperbillReq.setStrCompanyCode(companyCode);
				updPaperbillReq.setStrContAcct(accountNumber);
				updPaperbillReq.setStrPaperBill(PAPERLESS_FLAG);
				UpdPaperBillResponse paperBillresponse = billingService.updatePaperBill(
						updPaperbillReq, companyCode, sessionId);
				
				if (paperBillresponse.getStrResponseCode() != null
						&& paperBillresponse.getStrResponseCode().equals("0")) {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_SUCCESS);
					updatePaperFreeBillingResponse
							.setResultDescription(MSG_SUCCESS);
				} else {
					updatePaperFreeBillingResponse
							.setResultCode(RESULT_CODE_NO_DATA);
					updatePaperFreeBillingResponse
							.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				}
				
				
			}
			
		} catch (RemoteException e) {
			updatePaperFreeBillingResponse
					.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePaperFreeBillingResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.info("Exception Occured !!!! in the UpdateDeliveryMethod Call");
			throw new OAMException(200, e.getMessage(),
					updatePaperFreeBillingResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updatePaperFreeBillingResponse
					.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			updatePaperFreeBillingResponse
					.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(),
					updatePaperFreeBillingResponse);
		}
		
		
		// test if the result code to above process is success and companyCode is of Cirro, then send the mail for billing option update
		if (RESULT_CODE_SUCCESS.equalsIgnoreCase(updatePaperFreeBillingResponse.getResultCode())&&
				CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
			 
			ProfileResponse profileResponse = null;
			try {
				Map<String, Object> responseMap = new HashMap<String, Object>();
				responseMap = profileService.getProfile(accountNumber, companyCode, sessionId);
				if(responseMap!= null && responseMap.size()!= 0)
				{
					profileResponse= (ProfileResponse)responseMap.get("profileResponse");
				}
				else{
					logger.info("Couldn't find the profile for given account number, so couldn't send mail");
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Exception in fetching profile " + e1);
				
			}
			
			ContractAccountDO caDO  = null;
			
			if (profileResponse != null) {
				caDO = profileResponse.getContractAccountDO();
				if(caDO != null){
					
					logger.info("Found profile for given account number, Found contract account info");
					
					String caName = caDO.getCAName();
					
					if(caName != null && !caName.equalsIgnoreCase("")){
						logger.info("Found CA Name : " + caName + ", Sending Mail for Billing option update");
						
						HashMap<String, String> templateProperties = new HashMap<String,String>();

						templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(caName));


						try {

						Boolean status = EmailHelper.sendMail( email ,"", CIRRO_BILLING_OPTION_CHANGE_EN, templateProperties, companyCode);

						logger.info("Billing option Update Email sent status : " + status);

						} catch (Exception e) {
					    logger.info("Exception in sending Billing Option Update Email " );
						// TODO Auto-generated catch block
                        logger.info(e);
						logger.error(e);

						}
						
						
					} else{
						logger.info("Couldn't find CA Name : " + caName + ", so couldn't send mail for billing option update");	
					}
					
				}else{
					logger.info("Found the profile for given account number but couldn't find the contract account info, so couldn't send mail");
				}                 
			 
						
			}else{
				logger.info("Couldn't find the profile for given account number, so couldn't send mail");
			}
			
			
		 }
		
		
		
		return updatePaperFreeBillingResponse;

	}

	
	/**
	 * 
	 * @param bpid
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public BankCCInfoResponse getBankCCInfo(String bpid, String companyCode,String brandName,String sessionId)
	{
		logger.info("START-[BillingBO-getBankCCInfo]");
		// padding the bpid with 0s
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		
		boolean databaseInfoFlag = false;
		
		BankCCInfoResponse bankCCInfoResponse =  new BankCCInfoResponse();
		BankCCInfoRequest request =  new BankCCInfoRequest();
		request.setBpid(bpid);
		request.setBrandName(brandName);
		request.setCompanyCode(companyCode);
		
		
		com.multibrand.domain.BankCCInfoResponse response = null;
		
		long startTime = CommonUtil.getStartTime();
		
		try {
			
			// making db call
			bankCCInfoResponse = billingHelper.getBankCCInfoDB(bpid, companyCode, sessionId, brandName);
			
			// MASKING THE CC AND BANK ACCOUNT INFO BEFORE LOGGING ::::: START
			
			// making copy of response for logging for masking
			BankCCInfoResponse responseCopy  = new BankCCInfoResponse();
			JavaBeanUtil.copy(bankCCInfoResponse, responseCopy);
			List<CrCardDetails> crCardDetails = responseCopy.getCcList();
			if(crCardDetails!=null){
				for(CrCardDetails crcd : crCardDetails){
					if(StringUtils.isNotEmpty(crcd.getCardNumber())){
						crcd.setCardNumber(CommonUtil.maskCCNo(crcd.getCardNumber()));
					}
				}
			}
			
			List<BankDetails> bankDetails = responseCopy.getBankList();
			if(bankDetails!=null){
				for(BankDetails bd : bankDetails){
					if(StringUtils.isNotEmpty(bd.getBankAccountNumber())){
						bd.setBankAccountNumber(CommonUtil.maskBankAccountNo(bd.getBankAccountNumber()));
					}
				}
			}
			// MASKING THE CC AND BANK ACCOUNT INFO BEFORE LOGGING ::::: STOP
			utilityloggerHelper.logTransaction("getBankCCInfoDB", false, request,responseCopy, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(responseCopy));
			}	
			//setting the database info flage to true;
			databaseInfoFlag = true;
			logger.info("Was DB call successful :::: " + databaseInfoFlag);
		
			
			
			//if there will be any exception in db call then this success code will not be set and ccs call will not be made
			bankCCInfoResponse.setResultCode(RESULT_CODE_SUCCESS);
			bankCCInfoResponse.setResultDescription(MSG_SUCCESS);
			
			// ccs call for auto pay details
			response = billingService.getBankCCInfo(request);
			
			if (response.getErrorCode()!= null && !response.getErrorCode().equals("")) {
			
				bankCCInfoResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)bankCCInfoResponse.setResultDescription(response.getErrorMessage());
				bankCCInfoResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)bankCCInfoResponse.setErrorMessage(response.getErrorMessage());

			} else {
				// checking for invalid BPID
				if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_BPID)){
					bankCCInfoResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankCCInfoResponse.setResultDescription(CCS_INVALID_BPID_RESULT_DESCRIPTION);
				}else{
				// copy the data from NRGWS Layer to REST Response
					// only auto pay details 'll be copied due to property name similarities
					JavaBeanUtil.copy(response, bankCCInfoResponse);
					
					// NO need to set the result code for success as its already set after db call
					//bankCCInfoResponse.setResultCode(RESULT_CODE_SUCCESS);
					//bankCCInfoResponse.setResultDescription(MSG_SUCCESS);					
					/*for(AutoPayDetails autoPayDetails : bankCCInfoResponse.getAutoPayDetailsList()){
						// keep values for only one type of auto pay detail
						if(StringUtils.isNotEmpty(autoPayDetails.getBankAccountNumber()))*/
				    }
				
				}
			

		
			
			// MASKING THE CC AND BANK ACCOUNT INFO BEFORE LOGGING ::::: START
			
			// making copy of final response for logging purpose
			responseCopy  = new BankCCInfoResponse();
			JavaBeanUtil.copy(bankCCInfoResponse, responseCopy);
			
			
			AutoPayDetails apDetails[] = responseCopy.getAutoPayDetailsList();
			if(apDetails!=null){
				for(AutoPayDetails apd : apDetails){
					if(StringUtils.isNotEmpty(apd.getBankAccountNumber())){
						apd.setBankAccountNumber(CommonUtil.maskBankAccountNo(apd.getBankAccountNumber()));
					}
					if(StringUtils.isNotEmpty(apd.getCardNumber())){
						apd.setCardNumber(CommonUtil.maskCCNo(apd.getCardNumber()));
					}
				}
			}
			
			// MASKING THE CC AND BANK ACCOUNT INFO BEFORE LOGGING ::::: STOP
			utilityloggerHelper.logTransaction("getBankCCInfoCCS", false, request,responseCopy, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(responseCopy));
			}
			
		} catch (Exception e) {
		   if(logger.isDebugEnabled())
			logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			if(databaseInfoFlag){
				// excepiton happened in CCS call
				utilityloggerHelper.logTransaction("getBankCCInfoCCS", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
				bankCCInfoResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				bankCCInfoResponse.setResultDescription(RESULT_DESCRIPTION_CCS_EXCEPTION);
				throw new OAMException(200, e.getMessage(), bankCCInfoResponse);
			}else{
				// exception happened in DB call
				utilityloggerHelper.logTransaction("getBankCCInfoDB", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
				bankCCInfoResponse = new BankCCInfoResponse();
				bankCCInfoResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				bankCCInfoResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				throw new OAMException(200, e.getMessage(), bankCCInfoResponse);
			}
			
			
		}
		logger.info("END-[BillingBO-getBankCCInfo]");
		return bankCCInfoResponse;
	}
	
	/**
	 * for update the bank info based on bpid
	 * @param bpid
	 * @param bankAcNo
	 * @param bankRoutingNo
	 * @param updateFlag
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public BankInfoUpdateResponse updateBankInfo(String bpid, String bankAcNo, String bankRoutingNo, String updateFlag, String accountNickName, String defaultFlag, String bankAccountType,
			String bankAccountHolderType, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName,String sessionId,String emailId)
	{
		logger.info("START-[BillingBO-updateBankInfo]");
		//padding the bpid with 0s
		BankInfoUpdateResponse bankInfoUpdateResponse =  null;
		
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		
		String maskedBankAcc = null;
		if(StringUtils.isNotEmpty(bankAcNo)) maskedBankAcc = CommonUtil.maskBankAccountNo(bankAcNo);
		
		long startTime = CommonUtil.getStartTime();
		String request = "bpid="+ bpid+", bankAccountNumber="+ maskedBankAcc+ ", bankRoutingNo="+bankRoutingNo+", updateFlag=" + updateFlag+ ", accountNickName=" + accountNickName+", defaultFlag="+ defaultFlag +", bankAccountType="+bankAccountType+
				", bankAccountHolderType=" + bankAccountHolderType+ ", nameOnAccount=" + nameOnAccount+ ", onlinePayAccountId="+onlinePayAccountId+", companyCode=" + companyCode+", brandName="+brandName;
		
		try{
			
			bankInfoUpdateResponse = billingHelper.updateBankInfoDB(bpid, bankAcNo, bankRoutingNo, updateFlag, accountNickName, defaultFlag, bankAccountType, bankAccountHolderType, nameOnAccount, onlinePayAccountId, companyCode, brandName, sessionId);
		
			bankInfoUpdateResponse.setResultCode(RESULT_CODE_SUCCESS);
			bankInfoUpdateResponse.setResultDescription(MSG_SUCCESS);
			
			utilityloggerHelper.logTransaction("updateBankInfo", false, request,bankInfoUpdateResponse, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(bankInfoUpdateResponse));
			}
		
			//START: US914: Cirro - Email Notification for Payment option OAM_Changes - adadan
			if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)&&UPDATE_PAYMENT_OPTIONS_UPDATE.equalsIgnoreCase(updateFlag)){
				 
				HashMap<String, String> templateProperties = new HashMap<String,String>();
				//START: US1629: Logic for name in Title Case - adadan
				String titleCasedNamed = toTitleCase(nameOnAccount);
				templateProperties.put(CUSTOMER_NAME, titleCasedNamed);
				//END: US1629: Logic for name in Title Case - adadan
				try {
					Boolean status = EmailHelper.sendMail( emailId ,"", CIRRO_PAYMENT_OPTIONS_UPDATE_EN, templateProperties, companyCode);
					logger.info("New Service Added Email sent status : " + status);

					} catch (Exception e) {
				    logger.info("Exception in sending New Service Added Email " );
                    logger.info(e);
					logger.error(e);

					}
			}
			//END: US914: Cirro - Email Notification for Payment option OAM_Changes - adadan
			
			// START : US1403 : Cirro Email for adding a bank : ahanda1
			if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName) && 
					(UPDATE_BANK_INFO_ADD.equalsIgnoreCase(updateFlag)||UPDATE_BANK_INFO_DELETE.equalsIgnoreCase(updateFlag)
							||UPDATE_BANK_INFO_UPDATE.equalsIgnoreCase(updateFlag))){
				
				HashMap<String, String> templateProperties = new HashMap<String, String>();
                       templateProperties.put(CUSTOMER_NAME, CommonUtil.capitalizeAllWords(nameOnAccount));
				
				try {
					Boolean status = EmailHelper.sendMail( emailId ,"", CIRRO_ADD_BANK_CONF_EXTERNAL_ID, templateProperties, companyCode);
					logger.info("New Bank account payment option added conf mail : " + status);

					} catch (Exception e) {
				    logger.info("Exception in sending New Bank account payment option added conf mail " );
                    logger.info(e);
					logger.error(e);

					}
			}
			// END : US1403 : Cirro Email for adding a bank : ahanda1
			
		/*BankInfoUpdateRequest request = new BankInfoUpdateRequest();
		request.setBankAccountNumber(bankAcNo);
		request.setBankRoutingNumber(bankRoutingNo);
		request.setBpid(bpid);
		request.setUpdateFlag(updateFlag);
		request.setBrandName(brandName);
		request.setCompanyCode(companyCode);
		

		
		BankInfoUpdateResponse bankInfoUpdateResponse =  new BankInfoUpdateResponse();
		com.multibrand.domain.BankInfoUpdateResponse response = null;
		
		try {
			response = billingService.updateBankInfo(request, companyCode, sessionId);
			
			if (response.getErrorCode()!= null && !response.getErrorCode().equals("")) {
			
				bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)bankInfoUpdateResponse.setResultDescription(response.getErrorMessage());
				bankInfoUpdateResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)bankInfoUpdateResponse.setErrorMessage(response.getErrorMessage());

			} else {
			
				if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_BPID_OR_UPDATE_FLAG)){
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankInfoUpdateResponse.setResultDescription(CCS_INVALID_BPID_OR_UPDATE_FLAG_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INSERT_UPDATE_ERROR)){
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_INSERT_UPDATE_ERROR_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR)){
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD)){
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT)){
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					bankInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT_RESULT_DESCRIPTION);
				}
				if(StringUtils.isEmpty(response.getEReturnCode())) {
					bankInfoUpdateResponse.setResultCode(RESULT_CODE_SUCCESS);
					bankInfoUpdateResponse.setResultDescription(MSG_SUCCESS);	
					}		
								
				}*/
		
			
		} catch (Exception e) {
			
			utilityloggerHelper.logTransaction("updateBankInfo", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
			  logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			bankInfoUpdateResponse = new BankInfoUpdateResponse();
			bankInfoUpdateResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			bankInfoUpdateResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), bankInfoUpdateResponse);
		}
		logger.info("END-[BillingBO-updateBankInfo]");
		return bankInfoUpdateResponse;
	}

	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param ccNumber
	 * @param expDate
	 * @param updateFlag
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public CcInfoUpdateResponse updateCCInfo(String bpid, String ccType, String ccNumber, String expMonth, String expYear, String billingZipCode, String updateFlag, String accountNickName, String defaultFlag, String nameOnAccount, String onlinePayAccountId, String companyCode,String brandName,String sessionId, String emailId)
	{
		logger.info("START-[BillingBO-updateCCInfo]");
		//padding the bpid with 0s
				
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		
		String maskedCCNo = null;
		if(StringUtils.isNotEmpty(ccNumber)) maskedCCNo = CommonUtil.maskCCNo(ccNumber);
		
		CcInfoUpdateResponse ccInfoUpdateResponse =  null;
		long startTime = CommonUtil.getStartTime();
		String request = "bpid="+ bpid+", ccNumber="+ maskedCCNo+ ", ccType="+ccType+", updateFlag=" + updateFlag+ ", accountNickName=" + accountNickName+", defaultFlag="+ defaultFlag +", billingZipCode="+billingZipCode+
				", expMonth=" + expMonth+ ", expYear="+expYear+ ", nameOnAccount=" + nameOnAccount+ ", onlinePayAccountId="+onlinePayAccountId+", companyCode=" + companyCode+", brandName="+brandName;
		
		
		try{
			
			
			ccInfoUpdateResponse = billingHelper.updateCCInfoDB(bpid, ccType, ccNumber, expMonth, expYear, billingZipCode, updateFlag, accountNickName, defaultFlag, nameOnAccount, onlinePayAccountId, companyCode, brandName, sessionId);
			
			ccInfoUpdateResponse.setResultCode(RESULT_CODE_SUCCESS);
			ccInfoUpdateResponse.setResultDescription(MSG_SUCCESS);
			
			utilityloggerHelper.logTransaction("updateCCInfo", false, request,ccInfoUpdateResponse, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
			   logger.debug(XmlUtil.pojoToXML(request));
			   logger.debug(XmlUtil.pojoToXML(ccInfoUpdateResponse));
			}
		
			//START: US915: Cirro Email Notification for Credit Card OAM Changes - adadan
			if (CIRRO_COMPANY_CODE.equalsIgnoreCase(companyCode) && CIRRO_BRAND_NAME.equalsIgnoreCase(brandName)){
				 
				HashMap<String, String> templateProperties = new HashMap<String,String>();
				//START: US1629: Logic for name in Title Case - adadan
				String titleCasedNamed = toTitleCase(nameOnAccount);
				templateProperties.put(CUSTOMER_NAME, titleCasedNamed);
				//END: US1629: Logic for name in Title Case - adadan
				try {
					Boolean status = EmailHelper.sendMail( emailId ,"", CIRRO_CREDIT_DEBIT_UPDATE_EN, templateProperties, companyCode);
					logger.info("New Service Added Email sent status : " + status);

					} catch (Exception e) {
				    logger.info("Exception in sending Credit Card Added Email " );
                    logger.info(e);
					logger.error(e);

					}
			}
			//END: US915: Cirro Email Notification for Credit Card OAM Changes - adadan
			
		/*CcInfoUpdateRequest request = new CcInfoUpdateRequest();
		request.setCompanyCode(companyCode);
		request.setCcNumber(ccNumber);
		if(StringUtils.isNotEmpty(expDate))
			request.setExpDate(CommonUtil.changeDateFormat(expDate, MM_dd_yyyy, yyyy_MM_dd));
		request.setBpid(bpid);
		request.setUpdateFlag(updateFlag);
		request.setBrandName(brandName);

		
		CcInfoUpdateResponse ccInfoUpdateResponse =  new CcInfoUpdateResponse();
		com.multibrand.domain.CcInfoUpdateResponse response = null;
		
		try {
			response = billingService.updateCCInfo(request, companyCode, sessionId);
			
			if (response.getErrorCode()!= null && !response.getErrorCode().equals("")) {
			
				ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(response.getErrorMessage()!=null)ccInfoUpdateResponse.setResultDescription(response.getErrorMessage());
				ccInfoUpdateResponse.setErrorCode(response.getErrorCode());
				if(response.getErrorMessage()!=null)ccInfoUpdateResponse.setErrorMessage(response.getErrorMessage());

			} else {
			
				if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_BPID_OR_UPDATE_FLAG)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_INVALID_BPID_OR_UPDATE_FLAG_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INSERT_UPDATE_ERROR)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_INSERT_UPDATE_ERROR_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_DELETE_UPDATE_ERROR_ON_INCOMING_METHOD_RESULT_DESCRIPTION);
				}if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_INVALID_TOKEN_DATA_INPUT_RESULT_DESCRIPTION);
				}
				
				if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_CC_UPDATE_ERROR)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_CC_UPDATE_ERROR_RESULT_DESCRIPTION);
				}
				if(StringUtils.isNotEmpty(response.getEReturnCode()) && response.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_CC_BP_MISMATCH)){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_CCS_ERROR);
					ccInfoUpdateResponse.setResultDescription(CCS_ERETURN_CODE_CC_BP_MISMATCH_RESULT_DESCRIPTION);
				}
				
				if(StringUtils.isEmpty(response.getEReturnCode())){
					ccInfoUpdateResponse.setResultCode(RESULT_CODE_SUCCESS);
					ccInfoUpdateResponse.setResultDescription(MSG_SUCCESS);	
					}		
								
				}
		*/
			
		} catch (Exception e) {
			utilityloggerHelper.logTransaction("updateCCInfo", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			ccInfoUpdateResponse = new CcInfoUpdateResponse();
			ccInfoUpdateResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			ccInfoUpdateResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), ccInfoUpdateResponse);
		}
		logger.info("END-[BillingBO-updateCCInfo]");
		return ccInfoUpdateResponse;
	}

	/**
	 * @author ahanda1
	 * @param bpid
	 * @param contractAccountNumber
	 * @param ccNumber
	 * @param expMonth
	 * @param expYear
	 * @param paymentAmount
	 * @param scheduledDate
	 * @param zipCode
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public ScheduleOTCCPaymentResponse scheduleOneTimeCCPayment(String bpid,String contractAccountNumber, String ccNumber, String expMonth, String expYear, String paymentAmount, String scheduledDate, String  zipCode, String companyCode, String brandName, String sessionId)
	{
		logger.info("START-[BillingBO-scheduleOneTimeCCPayment]");
		//padding the bpid with 0s
				
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		
		ScheduleOTCCPaymentResponse scheduleOTCCPaymentResponse =  new ScheduleOTCCPaymentResponse();
		
		ScheduleOtccPaymentRequest request= new ScheduleOtccPaymentRequest();
		request.setBpNumber(bpid);
		request.setCompanyCode(companyCode);
		request.setContractAccount(contractAccountNumber);
		// converting the exp date for CC in MMyy format as required by NRGWS call
		String ccExpDate = expMonth + expYear.substring(2);
		request.setExpDate(ccExpDate);
		request.setScheduledDate(CommonUtil.changeDateFormat(scheduledDate, MM_dd_yyyy, yyyy_MM_dd));
		request.setPaymentAmount(paymentAmount);
		request.setTokCCNumber(ccNumber);
		request.setZipCode(zipCode);
		
		
		
	
		try{
			
			
			ScheduleOtccPaymentResponse response = paymentService.scheduleOneTimeCCPayment(request, sessionId);
			
			if(StringUtils.isEmpty(response.getErrorCode())){
			
				// successfully scheduled
			   scheduleOTCCPaymentResponse.seteTrackingId(response.getETrackingId());
			   scheduleOTCCPaymentResponse.setResultCode(RESULT_CODE_SUCCESS);
			   scheduleOTCCPaymentResponse.setResultDescription(MSG_SUCCESS);
			
			} else{
				
				// ccs error
				
				scheduleOTCCPaymentResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(StringUtils.isNotEmpty(response.getErrorMessage())){
					scheduleOTCCPaymentResponse.setResultDescription(response.getErrorMessage());
				}
				// setting the ccs error code
				scheduleOTCCPaymentResponse.setErrorCode(response.getErrorCode());
				
			}
		
		
			
		} catch (Exception e) {
			// masking cc number before logging
		    request.setTokCCNumber(CommonUtil.maskCCNo(request.getTokCCNumber()));
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			scheduleOTCCPaymentResponse = new ScheduleOTCCPaymentResponse();
			scheduleOTCCPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			scheduleOTCCPaymentResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), scheduleOTCCPaymentResponse);
		}
		logger.info("END-[BillingBO-scheduleOneTimeCCPayment]");
		return scheduleOTCCPaymentResponse;
	}

	/**
	 * @author ahanda1
	 * @param bpid
	 * @param contractAccountNumber
	 * @param trackingId
	 * @param action
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public EditCancelOTCCPaymentResponse editCancelOTCCPayment(String bpid,String contractAccountNumber, String trackingId, String action,String companyCode, String brandName, String sessionId)
	{
		logger.info("START-[BillingBO-editCancelOTCCPayment]");
		//padding the bpid with 0s
				
		if(StringUtils.isNotEmpty(bpid))bpid = StringUtils.leftPad(bpid,  10, "0");
		
		EditCancelOTCCPaymentResponse editCancelOTCCPaymentResponse =  new EditCancelOTCCPaymentResponse();
		
		CancelSchdOtccPaymetReq request= new CancelSchdOtccPaymetReq();
		request.setBpNumber(bpid);
		request.setCompanyCode(companyCode);
		request.setTrackingId(trackingId);
		request.setContractAccount(contractAccountNumber);
		request.setAction(action);
		
		
		
	
		try{
			
			
			CancelOtccPaymentResp response = paymentService.editCancelOTCCPayment(request, sessionId);
			
			if(StringUtils.isEmpty(response.getErrorCode())){
			
				// successful call
			   editCancelOTCCPaymentResponse.setResultCode(RESULT_CODE_SUCCESS);
			   editCancelOTCCPaymentResponse.setResultDescription(MSG_SUCCESS);
			
			} else{
				
				// ccs error
				
				editCancelOTCCPaymentResponse.setResultCode(RESULT_CODE_CCS_ERROR);
				if(StringUtils.isNotEmpty(response.getErrorMessage())){
					editCancelOTCCPaymentResponse.setResultDescription(response.getErrorMessage());
				}
				// setting the ccs error code
				editCancelOTCCPaymentResponse.setErrorCode(response.getErrorCode());
				
			}
		
		
			
		} catch (Exception e) {
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			editCancelOTCCPaymentResponse = new EditCancelOTCCPaymentResponse();
			editCancelOTCCPaymentResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			editCancelOTCCPaymentResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), editCancelOTCCPaymentResponse);
		}
		logger.info("END-[BillingBO-editCancelOTCCPayment]");
		return editCancelOTCCPaymentResponse;
	}
	

	/**
	 * Method writes to CCS for paperless billing subscribption
	 * @param contractAccountNo
	 * @param companyCode 
	 * @return boolean
	 */
	public boolean subscribePaperlessBilling(String contractAccountNo, String companyCode) {
		boolean status = true;
		try {			
			ActEbillRequest ebillRequest = new ActEbillRequest();
			ebillRequest.setStrContAcct(contractAccountNo);
			ebillRequest.setStrContract("");
			ebillRequest.setStrPaperBill(PAPER_FLAG_OFF);
			ebillRequest.setStrSource(EBPP_SOURCE);
			ebillRequest.setStrCompanyCode(companyCode);
			ActEbillResponse ebillResponse = billingProxy.activateEbill(ebillRequest, null);
			logger.info(ReflectionToStringBuilder.toString(ebillResponse,
						ToStringStyle.MULTI_LINE_STYLE));
			status = ebillResponse.getStrRespCode().equals("0");
			
		}catch(Exception en) {
			logger.error("Exception in subscribePaperlessBilling : "+en.getMessage());
			status = false;
		}
		return status;
	}
	
	
	/**
	 * for fetching the pay accounts for given contract account number
	 * 
	 * @param contractAccountNumber
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 */
	public PayAccountInfoResponse getPayAccounts(String contractAccountNumber, String companyCode, String brandName, String sessionId)
	{
		logger.info("START-[BillingBO-getPayAccounts]");
		
		String request = "contractAccountNumber="+contractAccountNumber+", companyCode="+ companyCode+ ", brandName=" +brandName+ ", sessionId" + sessionId;

		
		PayAccountInfoResponse response = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			response = billDao.getPayAccounts(contractAccountNumber);
			
			utilityloggerHelper.logTransaction("getPayAccounts", false, request,response, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			utilityloggerHelper.logTransaction("getPayAccounts", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			response = new PayAccountInfoResponse();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("END-[BillingBO-getPayAccounts]");
		return response;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param sessionId
	 * @return
	 */
	public StoreUpdatePayAccountResponse storePayAccount(StoreUpdatePayAccountRequest request, String sessionId)throws OAMException
	{
		logger.info("START-[BillingBO-storePayAccount]");
		
		StoreUpdatePayAccountResponse response = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			
			if(!StringUtils.isEmpty(request.getContractAccountNumber())){
			int rowsAdded = billDao.storePayAccount(request);
			
			if(rowsAdded != 0){
				logger.info("payment account successfully added :: ");
				response = new StoreUpdatePayAccountResponse();
				response.setSuccessFlag(true);
			}
			}else{
				logger.info("payment account not added as contract account number is null or empty :: ");
				response = new StoreUpdatePayAccountResponse();
				response.setSuccessFlag(false);
			}
			
			utilityloggerHelper.logTransaction("storePayAccount", false, request,response, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			utilityloggerHelper.logTransaction("storePayAccount", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			response = new StoreUpdatePayAccountResponse();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("END-[BillingBO-storePayAccount]");
		return response;
	}
	
	
	public StoreUpdatePayAccountResponse updatePayAccount(StoreUpdatePayAccountRequest request, String sessionId)throws OAMException
	{
		logger.info("START-[BillingBO-updatePayAccount]");
		
		StoreUpdatePayAccountResponse response = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			
			
			int rowsAdded = billDao.updatePayAccount(request);
			
			if(rowsAdded != 0){
				logger.info("payment account successfully updated :: ");
				response = new StoreUpdatePayAccountResponse();
				response.setSuccessFlag(true);
			} else{
				logger.info("no payment account update :: ");
				response = new StoreUpdatePayAccountResponse();
				response.setSuccessFlag(false);
			}
			
			utilityloggerHelper.logTransaction("updatePayAccount", false, request,response, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
            if(logger.isDebugEnabled()){
            	logger.debug(XmlUtil.pojoToXML(request));
            	logger.debug(XmlUtil.pojoToXML(response));
            }
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			   logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			utilityloggerHelper.logTransaction("updatePayAccount", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			response = new StoreUpdatePayAccountResponse();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("END-[BillingBO-storePayAccount]");
		return response;
	}
	
	/**
	 * 
	 * @param bpNumber
	 * @param caNumber
	 * @param coNumber
	 * @param companyCode
	 * @param strSessionId
	 * @return
	 */
	public AMBEligibiltyCheckResponseVO ambeligibilityCheck(AMBEligibilityCheckRequest ambEligRequest,
			String strSessionId)
	{
		AMBEligibiltyCheckResponseVO response = new AMBEligibiltyCheckResponseVO();
		try {

			AmbCheckRequest request = new AmbCheckRequest();
			request.setBpNumber(ambEligRequest.getBpNumber());
			request.setCaNumber(ambEligRequest.getAccountNumber());
			request.setCoNumber(ambEligRequest.getContractId());
			AmbCheckResponse responseService = billingService
					.ambeligibilityCheck(request, ambEligRequest.getCompanyCode(), strSessionId);
			logger.info(String.valueOf(responseService.getDeffAmt()));
			if(responseService != null && StringUtils.isBlank(responseService.getErrCode())) {
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				if(responseService.getAmbWebTab() != null) {
					response.setAmbWebTab(responseService.getAmbWebTab());
				} else {
					response.setAmbWebTab(new AmbOutputTab[0]);
				}
				
				if(responseService.getPrgStatus() != null) {
					response.setPrgStatus(responseService.getPrgStatus());
				} else {
					response.setPrgStatus(new ProgramStatus());
				}
				
				logger.info(String.valueOf(responseService.getDeffAmt()));
				response.setDeffAmt(responseService.getDeffAmt());
				response.setAmbAmt(responseService.getAmbAmt());
				response.setRespStatus(responseService.getRespStatus());
				
			} else {
				response.setResultCode(RESULT_CODE_CCS_ERROR);
				response.setResultDescription(responseService.getErrMessage());
			}

		} catch (RemoteException e) {
			e.printStackTrace();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
	}
	
	/**
	 * 
	 * @param requestVO SaveAMBSingupRequestVO
	 * @param strSessionId HttpSession
	 * @return
	 */
	public AMBSignupResponseVO saveAMBSignUp(SaveAMBSingupRequestVO requestVO,
			String strSessionId)
	{

		AMBSignupResponseVO response = new AMBSignupResponseVO();
		ZesAmbOutput zesAmbOutput = new ZesAmbOutput();
		try {
			AmbSignupRequest request = new AmbSignupRequest();
			if(!StringUtils.isBlank(requestVO.getAmbAmount())) {
				request.setAmbAmount(new BigDecimal(requestVO.getAmbAmount()));
			} 
			
			request.setBpNumber(requestVO.getBpNumber());
			request.setCaNumber(requestVO.getAccountNumber());
			request.setCoNumber(requestVO.getContractId());
            //Added Retro flag and abm web tab data- Start
			if(StringUtils.isNotBlank(requestVO.getRetroFlag()) && StringUtils.equals("true", requestVO.getRetroFlag())){
				request.setRetroFlag(Constants.X_VALUE);
			}else{
				request.setRetroFlag("");
			}
			
			if(StringUtils.isNotBlank(requestVO.getAmtAdjust()))
				zesAmbOutput.setAmtAdjust(new BigDecimal(requestVO.getAmtAdjust()));
			
			if(StringUtils.isNotBlank(requestVO.getAmbAmount()))
			    zesAmbOutput.setAmtFinal(new BigDecimal(requestVO.getAmbAmount()));
			
			if(StringUtils.isNotBlank(requestVO.getBbpBasis()))
			    zesAmbOutput.setBbpBasis(new BigDecimal(requestVO.getBbpBasis()));
			
			if(StringUtils.isNotBlank(requestVO.getBillAllocDate())){
				zesAmbOutput.setBillAllocDate(requestVO.getBillAllocDate());	
			}else{
				zesAmbOutput.setBillAllocDate(CommonUtil.getCurrentDateYYYYMMDD());
			}
			zesAmbOutput.setEstSign(requestVO.getEstSign());
			zesAmbOutput.setInvoice(requestVO.getInvoice());
			zesAmbOutput.setResStatus(requestVO.getResStatus());
			request.setAbmWebTab(zesAmbOutput);
			//Added Retro flag and abm web tab data- End
			
			AmbSignupResponse responseService = billingService.saveAMBSignUp(
					request, requestVO.getCompanyCode(), strSessionId);
			
			if (responseService != null
					&& StringUtils.isBlank(responseService.getErrCode())) {
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				response.setRespStatus(responseService.getRespStatus());
				
				HashMap<String, String> templateProps = new HashMap<String,String>();
				templateProps.put(ACCOUNT_NUMBER, requestVO.getAccountNumber());
				templateProps.put(CHECK_DIGIT, requestVO.getCheckDigit());
				templateProps.put(BP_NUMBER, requestVO.getBpNumber());
				String serviceAddress = "";
				serviceAddress = requestVO.getServStreetNum()+ " "+ requestVO.getServStreetName();
				if(!StringUtils.isEmpty(requestVO.getServStreetAptNum()))
					serviceAddress = serviceAddress	+ ", APT# "+ requestVO.getServStreetAptNum();
				String serviceCity = requestVO.getServCity();
				String serviceState = requestVO.getServState();
				String serviceZipCode = requestVO.getServZipCode();
				templateProps.put(SERVICE_ADDRESS, serviceAddress);
				templateProps.put(SERVICE_CITY, serviceCity);
				templateProps.put(SERVICE_STATE, serviceState);
				templateProps.put(SERVICE_ZIP, serviceZipCode);
				templateProps.put(ESID, requestVO.getEsid());
				//templateProps.put("dateSubmitted ", CommonUtil.);
				String transactionDate = (new SimpleDateFormat(MM_dd_yyyy)).format(Calendar.getInstance().getTime());
				templateProps.put(DATE_SUBMITTED,transactionDate);
				String ambAmount = CommonUtil.stringToDecimalFormat(requestVO.getAmbAmount());
				String billingAddress = "";
				if(StringUtils.isEmpty(requestVO.getBillAddrPOBox()))
				{
					billingAddress = requestVO.getBillStreetNum()+ " "+ requestVO.getBillStreetName();
					if(!StringUtils.isEmpty(requestVO.getBillStreetAptNum()))
						billingAddress = billingAddress	+ ", APT# "+ requestVO.getBillStreetAptNum();
				}
				else
				{
					billingAddress = "P.O. Box "+ requestVO.getBillAddrPOBox();
				}
				String billingCity = requestVO.getBillCity();
				String billingState = requestVO.getBillState();
				String billingZipCode = requestVO.getBillZipCode();
				
				templateProps.put(BILLING_ADDRESS, billingAddress);
				templateProps.put(BILLING_CITY, billingCity);
				templateProps.put(BILLING_STATE, billingState);
				templateProps.put(BILLING_ZIP, billingZipCode);
				 logger.info("Email send to To requestVO.getLanguageCode("+requestVO.getLanguageCode());
				 logger.info("Email send to To Address"+requestVO.getToEmail());
				 String bccMailAddress = this.envMessageReader.getMessage(QC_BCC_MAIL)+","+this.envMessageReader.getMessage(SWAP_BCC_MAIL);
				 if(StringUtils.equals(requestVO.getCompanyCode(),Constants.COMPANY_CODE_GME)){
						//System.out.println("GME");
						logger.info("Email send to GME as per company code");
						if(StringUtils.isBlank(requestVO.getLanguageCode())|| requestVO.getLanguageCode().equalsIgnoreCase(LANGUAGE_CODE_EN))
						 {
							 if(ambAmount.equals("0.00"))
								 templateProps.put(AMB_AMOUNT,"");
							 else
							 {
								 String strAmbAmount = "<![CDATA[<tr><td class=\"float_1_trans\">]]>Estimated Monthly Payment:<![CDATA[</td><td class=\"float_2_trans\" align=\"right\">]]>$"+ambAmount+"<![CDATA[</td></tr>]]>";
								 templateProps.put(AMB_AMOUNT,strAmbAmount);
							 }
							 logger.info("Email send To Address"+requestVO.getToEmail());
							 if(StringUtils.isNotBlank(requestVO.getRetroFlag()) && StringUtils.equals("true", requestVO.getRetroFlag())){
								 emailHelper.sendMailWithBCC(requestVO.getToEmail(), bccMailAddress,  "", GME_SUBMIT_RETRO_AMB_EN_US, templateProps, requestVO.getCompanyCode());
								}
							 else{
							 emailHelper.sendMailWithBCC(requestVO.getToEmail(), bccMailAddress,  "", GME_SUBMIT_AMB_EN_US, templateProps, requestVO.getCompanyCode());
							 }
						 } 
						 else 
						 {
							 if(ambAmount.equals("0.00"))
								 templateProps.put(AMB_AMOUNT,"");
							 else
							 {
								 String strAmbAmount = "<![CDATA[<tr><td class=\"float_1_trans\">]]>Pago mensual estimado:<![CDATA[</td><td class=\"float_2_trans\" align=\"right\">]]>$"+ambAmount+"<![CDATA[</td></tr>]]>";
								 templateProps.put(AMB_AMOUNT,strAmbAmount);
							 }
							 if(StringUtils.isNotBlank(requestVO.getRetroFlag()) && StringUtils.equals("true", requestVO.getRetroFlag())){
								 emailHelper.sendMailWithBCC(requestVO.getToEmail(), bccMailAddress,  "", GME_SUBMIT_RETRO_AMB_ES_US, templateProps, requestVO.getCompanyCode());
								}
							 else{
							 emailHelper.sendMailWithBCC(requestVO.getToEmail(), bccMailAddress,  "", GME_SUBMIT_AMB_ES_US, templateProps, requestVO.getCompanyCode()); 
						    }
						 }
					}else if(StringUtils.equals(requestVO.getCompanyCode(),Constants.COMPANY_CODE_CIRRO)){
						//System.out.println("CIRRO");
						logger.info("Email send to Cirro as per company code");
						
							 if(ambAmount.equals("0.00"))
								 templateProps.put(AMB_AMOUNT,"");
							 else
							 {
								 String strAmbAmount = "<![CDATA[<tr><td class=\"float_1_trans\">]]>Estimated Monthly Payment:<![CDATA[</td><td class=\"float_2_trans\" align=\"right\">]]>$"+ambAmount+"<![CDATA[</td></tr>]]>";
								 templateProps.put(AMB_AMOUNT,strAmbAmount);
							 }
							 logger.info("Email send To Address"+requestVO.getToEmail());
							 emailHelper.sendMail(requestVO.getToEmail(),  "", CIRRO_AVG_BILLING_CONF_EXTERNAL_ID_EN, templateProps, requestVO.getCompanyCode());			
						
					}
			} else {
				response.setResultCode(RESULT_CODE_CCS_ERROR);
				response.setResultDescription(responseService.getErrCode());
			}

		} catch (RemoteException e) {
			e.printStackTrace();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
	}
	
	/**
	 * This method is responsible for getting
	 * autopay information from NRGWS based on
	 * business partner Id.
	 * @param request
	 * @return
	 */
	public AutoPayInfoResponse getAutopayInfo(AutoPayInfoRequest request){

       AutoPayInfoResponse response = new AutoPayInfoResponse();
       String bpId = request.getBusinessPartnerID();	
       if(StringUtils.isNotEmpty(bpId))
    	   bpId = StringUtils.leftPad(bpId,  10, "0");
    		
       logger.info("Business partner ID "+bpId);
       BankCCInfoRequest bankCCInfoReq =  new BankCCInfoRequest();
       bankCCInfoReq.setBpid(bpId);
       bankCCInfoReq.setBrandName(request.getBrandName());
       bankCCInfoReq.setCompanyCode(request.getCompanyCode());
       
       try {
		com.multibrand.domain.BankCCInfoResponse bankCCInfoResponse = billingService.getBankCCInfo(bankCCInfoReq);

		if (bankCCInfoResponse.getErrorCode()!= null && !bankCCInfoResponse.getErrorCode().equals("")) {
			
			response.setResultCode(RESULT_CODE_CCS_ERROR);
			
			if(bankCCInfoResponse.getErrorMessage()!=null)
				 response.setResultDescription(bankCCInfoResponse.getErrorMessage());
			
			response.setErrorCode(bankCCInfoResponse.getErrorCode());
			if(bankCCInfoResponse.getErrorMessage()!=null)
				response.setErrorDescription(bankCCInfoResponse.getErrorMessage());

		} else {
			// checking for invalid BPID
			if(StringUtils.isNotEmpty(bankCCInfoResponse.getEReturnCode()) && bankCCInfoResponse.getEReturnCode().equalsIgnoreCase(CCS_ERETURN_CODE_INVALID_BPID)){
				response.setResultCode(RESULT_CODE_CCS_ERROR);
				response.setResultDescription(CCS_INVALID_BPID_RESULT_DESCRIPTION);
			}else{
			    // copy the data from NRGWS Layer to REST Response
				logger.info(bankCCInfoResponse.getAutoPayDetailsList().length);
				JavaBeanUtil.copy(bankCCInfoResponse,response);
				response.setResultCode(SUCCESS_CODE);
			    }
			}
		
	   }catch(RemoteException ex){
		 response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		 response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		 throw new OAMException(200, ex.getMessage(), response);
	   }
       catch (Exception e) {
		 response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		 response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		 throw new OAMException(200, e.getMessage(), response);
	   }
       return response;
		
	}
	
	/**
	 * @param routingNumber
	 * @return
	 */
	public GetPaymentInstitutionResponse getPaymentInstitutionName(String routingNumber){
		
		GetPaymentInstitutionResponse response = new GetPaymentInstitutionResponse();
		BankPaymentInstitutionResponse bankPayInstRes = null;
		
		try {
			bankPayInstRes= paymentService.getBankPaymentInstitution(routingNumber);
			
			if(StringUtils.isNotBlank(bankPayInstRes.getErrorCode()) 
					 && StringUtils.equalsIgnoreCase(bankPayInstRes.getErrorCode(), ERR_CODE_53)){
				response.setResultCode(EMPTY);
				response.setResultCode(RESULT_CODE_THREE);
				response.setResultDescription(ERR_CODE_53_DESC);
				response.setErrorCode(bankPayInstRes.getErrorCode());
			}else{
				response.setPaymentInstitutionName(bankPayInstRes.getPaymentInstitutionName());
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				response.setErrorCode(EMPTY);
			}
		} catch (RemoteException e) {
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Remote Exception occurred:: "+e.getMessage());
		}catch(Exception e){
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			logger.error("Exception occurred:: "+e.getMessage());
		}
		return response;
	}
	
	public RetroEligibilityResponse insertRetroPopup(RetroPopupRequestVO request, String sessionId)throws OAMException
	{
		logger.info("START-[BillingBO-insertRetroPopup]");
		
		RetroEligibilityResponse response = null;
		long startTime = CommonUtil.getStartTime();
		
		try {
			
			
			int rowsAdded = billDao.insertRetroPopup(request);
			
			if(rowsAdded != 0){
				logger.info("Rows added :: ");
				response = new RetroEligibilityResponse();
				response.setSuccessFlag(true);
				response.setResultCode(RESULT_CODE_SUCCESS);
			} else{
				logger.info("No Rows Added :: ");
				response = new RetroEligibilityResponse();
				response.setSuccessFlag(true);
				response.setResultCode(RESULT_CODE_NO_DATA);
			}
			
			utilityloggerHelper.logTransaction("insertRetroPopup", false, request,response, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
            if(logger.isDebugEnabled()){
            	logger.debug(XmlUtil.pojoToXML(request));
            	logger.debug(XmlUtil.pojoToXML(response));
            }
		} catch (Exception e) {
			if(logger.isDebugEnabled())
			   logger.debug(XmlUtil.pojoToXML(request));
			logger.error(e);
			utilityloggerHelper.logTransaction("insertRetroPopup", false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
			response = new RetroEligibilityResponse();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}
		logger.info("END-[BillingBO-insertRetroPopup]");
		return response;
	}
	
	/**
	 * @author adadan
	 * @param contractAccountNumber
	 * @param invoiceNo
	 * @param contractId
	 * @param currentARAmount
	 * @param companyCode
	 * @return
	 */
	public RetroEligibilityResponse checkRetroEligibility(String contractAccountNumber,
			String invoiceNo,String contractId,String currentARAmount,
			String companyCode,String sessionId)
	{
		logger.info("START-[BillingBO-checkRetroEligibility]");
		RetroPopupRequestVO retroReq = new RetroPopupRequestVO();
		RetroEligibilityResponse retroEligResp = null;
		logger.info("Before Padding contractAccountNumber  ** : "+contractAccountNumber);
		retroReq.setContractAccountNumber(CommonUtil.paddedCa(contractAccountNumber));
		logger.info("After Padding contractAccountNumber  ** : "+contractAccountNumber);
		retroReq.setInvoiceNo(invoiceNo);
		retroReq.setContractId(contractId);
		retroReq.setCurrentARAmount(currentARAmount);
		retroReq.setCompanyCode(companyCode);
		
		
		try {
			retroEligResp = billingHelper.checkRetroEligibility(retroReq, companyCode, sessionId);
			if (retroEligResp != null) {
				retroEligResp.setResultCode(RESULT_CODE_SUCCESS);
				retroEligResp.setResultDescription(MSG_SUCCESS);
			}
			else {
				retroEligResp = new RetroEligibilityResponse();
				retroEligResp.setResultCode(RESULT_CODE_NO_DATA);
				retroEligResp.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
		} catch (Exception e) {
			//e.printstackTrace();
			logger.info(" Exeception Occured in the checkRetroEligibility"
					+ e.getCause());
			//System.out.println(e.getMessage());
			//System.out.println(e.getCause());
			logger.error(" Error "+e.getMessage());
			retroEligResp = new RetroEligibilityResponse();
			retroEligResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			retroEligResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), retroEligResp);
		}
		
		logger.info("END-[BillingBO-checkRetroEligibility]");
		return retroEligResp;
	}


   /** This method is responsible for getting all pending payments and 
    *  last paid date and assign to SchedulePaymentResponse
    * @author NGASPerera
    * @param accountNumber
    * @param startDate 
    * @param endDate
    * @param companyCode
    * @param brandName
    * @param sessionId
    * @return
    */
	public SchedulePaymentResponse getSchedulePayments(String accountNumber, String companyCode, String brandName,
			String sessionId) {
		List<PaymentDO> pendingPayments = new ArrayList<PaymentDO>();
		String lastPaymentDate="";
		SchedulePaymentResponse schedulePayment = new SchedulePaymentResponse();
		String startDate = null;
		DateFormat formatter = new SimpleDateFormat(Constants.yyyyMMdd);
		String endDate = formatter.format(new Date());
		try {
			PaymentHistoryResponse paymentHistoryResponse = historyBO.fetchPaymentHistory(accountNumber, startDate,
					endDate, companyCode, brandName, sessionId);
			PaymentDO[] payments = paymentHistoryResponse.getPaymentDO();
			if (payments != null && payments.length > 0) {
				pendingPayments = getPendingPayemts(payments);
				lastPaymentDate=getLastPaymentDate(payments);
				
				if(pendingPayments!=null && pendingPayments.size()>0){
					schedulePayment.setPendingPayments(pendingPayments);
				}
				schedulePayment.setLastPaymentDate(lastPaymentDate);
			
			} else {
				schedulePayment.setErrorCode(Constants.RESULT_CODE_NO_DATA);
				schedulePayment.setErrorDescription(Constants.RESULT_CODE_DESCRIPTION_NO_DATA);
			}
		} catch (Exception e) {
			schedulePayment.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			schedulePayment.setErrorDescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
			logger.info("Exeception Occured in the ::getSchedulePayments" + e);
		}
		return schedulePayment;
	}
	
	/** This method returns all pending payments and sorted by payment date
	 * @param PaymentDO[] historyPayments
	 * @return List<PaymentDO>
	 */
	public List<PaymentDO> getPendingPayemts(PaymentDO[] historyPayments) {
		List<PaymentDO> pendingPayments = new ArrayList<PaymentDO>();
		if (historyPayments != null && historyPayments.length > 0) {
			for (PaymentDO payment : historyPayments) {
				// retrieve pending payment details
				if (Constants.PAYMENT_PENDING_STATUS.equalsIgnoreCase(payment.getStatus())) {
					pendingPayments.add(payment);
				}
			}
			// sort the pending payments by payment date
			if (pendingPayments.size() > 0) {
				sortByPaymentDateAsc(pendingPayments);
				// schedulePayment.setPendingPayments(pendingPayments);
			}

		}

		return pendingPayments;
	}

	/**
	 * This method return last payment date
	 * @param PaymentDO[] historyPayments
	 * @return String lastPaidDate
	 */
	public String getLastPaymentDate(PaymentDO[] historyPayments) {
		String lastPaymentDate = "";
		List<PaymentDO> paidPayments = new ArrayList<PaymentDO>();
		if (historyPayments != null && historyPayments.length > 0) {
			for (PaymentDO payment : historyPayments) {
				// retrieve pending payment details
				if (Constants.PAYMENT_PAID_STATUS.equalsIgnoreCase(payment.getStatus())) {
					paidPayments.add(payment);
				}
			}
			// sort paid payments by payment date
			if (paidPayments.size() > 0) {
				sortByPaymentDateDesc(paidPayments);
				lastPaymentDate = paidPayments.get(0).getPaymentDate();
			}
		}
		return lastPaymentDate;
	}	
		
	/**
	 * This method sort List<PaymentDO> in ascending order
	 * @param List<PaymentDO> unsortedList
	 * @return List<PaymentDO> sortedList
	 */
	public List<PaymentDO> sortByPaymentDateAsc(List<PaymentDO> unsortedList) {
		Collections.sort(unsortedList, new Comparator<PaymentDO>() {

			@Override
			public int compare(PaymentDO p1, PaymentDO p2) {
				int compareInt = 0;
				if (p1.getPaymentDate() != null && p2.getPaymentDate() != null) {
					try {
						Date date1 = new SimpleDateFormat(Constants.yyyyMMdd).parse(p1.getPaymentDate());
						Date date2 = new SimpleDateFormat(Constants.yyyyMMdd).parse(p2.getPaymentDate());
						return (date1.getTime() > date2.getTime() ? 1 : -1); // ascending
					} catch (ParseException e) {
						compareInt = 0;
						logger.info("Error Occured in :::sortByPaymentDateAsc", e);
					}
				}
				return compareInt;
			}
		});

		return unsortedList;
	}
	
	/**
	 * This method sort List<PaymentDO> in descending order
	 * @param List<PaymentDO> unsortedList
	 * @return List<PaymentDO> sortedList
	 */
	public List<PaymentDO> sortByPaymentDateDesc(List<PaymentDO> unsortedList) {
		Collections.sort(unsortedList, new Comparator<PaymentDO>() {

			@Override
			public int compare(PaymentDO p1, PaymentDO p2) {
				int compareInt = 0;
				if (p1.getPaymentDate() != null && p2.getPaymentDate() != null) {
					try {
						Date date1 = new SimpleDateFormat(Constants.yyyyMMdd).parse(p1.getPaymentDate());
						Date date2 = new SimpleDateFormat(Constants.yyyyMMdd).parse(p2.getPaymentDate());
						return (date1.getTime() > date2.getTime() ? -1 : 1); // descending
					} catch (ParseException e) {
						compareInt = 0;
						logger.info("Error Occured in :::sortByPaymentDateAsc", e);
					}

				}
				return compareInt;
			}
		});

		return unsortedList;
	}
	
	/**
	 * This method is responsible for getting account balance details for gme
	 * mobile
	 * 
	 * @param accountNumber
	 * @param bpNumber
	 * @param companyCode
	 * @param sessionId
	 * @param brandName
	 * @return MobileGmeArResponse
	 */
	public ArMobileGMEResponse getBalanceForGMEMobile(String accountNumber, String bpNumber, String companyCode,
			String sessionId, String brandName) {
		ArMobileGMEResponse mobileArResponse = new ArMobileGMEResponse();
		try {
			GetArResponse arResponse = getBalance(accountNumber, bpNumber, companyCode, sessionId, brandName);
			if (arResponse != null) {
				mobileArResponse.setCurrentDueDate(arResponse.getStrCurrentDueDate());
				mobileArResponse.setCurrentArBalance(arResponse.getStrCurrentARBalance());
				mobileArResponse.setLastPayAmt(arResponse.getStrLastPayAmt());
				mobileArResponse.setLastPayDate(arResponse.getStrLastPayDate());
				mobileArResponse.setPastDueAmt(arResponse.getStrPastDueAmt());
				mobileArResponse.setCreditAmt(arResponse.getStrCreditAmt());
			}
			// setting payment dates
			String recentPendingPayDate = retriveRecentPendingPaymentDate(accountNumber, companyCode, brandName,sessionId);
			if (!recentPendingPayDate.isEmpty()) {
				mobileArResponse.setRecentPendingPayDate(recentPendingPayDate);
			}
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getBalanceForGMEMobile" + e.getCause());
			mobileArResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			mobileArResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return mobileArResponse;
	}
	
	/**
	 * This method is responsible for getting last payment date and recent pending payment date
	 * @author NGASPerera
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return MobileGmeArResponse
	 */
	public String retriveRecentPendingPaymentDate(String accountNumber, String companyCode, String brandName,
			String sessionId) {
		String recentPendingPaymentDate = "";
		// set end date as current date
		DateFormat formatter = new SimpleDateFormat(Constants.yyyyMMdd);
		String endDate = formatter.format(new Date());
		List<PaymentDO> pendingPayments = new ArrayList<PaymentDO>();
		// getting all payment history
		PaymentHistoryResponse paymentHistoryResponse = historyBO.fetchPaymentHistory(accountNumber, null, endDate,
				companyCode, brandName, sessionId);
		if (paymentHistoryResponse != null) {
			PaymentDO[] payments = paymentHistoryResponse.getPaymentDO();
			if (payments != null && payments.length > 0) {
				for (PaymentDO payment : payments) {
					// retrieve pending payment details
					if (Constants.PAYMENT_PENDING_STATUS.equalsIgnoreCase(payment.getStatus())) {
						pendingPayments.add(payment);
					}
				}
				// sort pending payments by date
				if (!pendingPayments.isEmpty()) {
					pendingPayments = sortByPaymentDateAsc(pendingPayments);
					recentPendingPaymentDate = pendingPayments.get(0).getPaymentDate();
				}
			}

		}
		return recentPendingPaymentDate;
	}

	
	/**
	 * This API is responsible for returning account balance for GME mobile
	 * @author Cuppala
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 */
	public PaymentMethodsResponse getPaymentMethods(String contractAccountNumber, String companyCode, String sessionId,
			String brandName) {
		logger.info("START-[BillingBO-getPaymentMethods]");
		PaymentMethodsResponse response = new PaymentMethodsResponse();
		GetAccountDetailsResponse accountDetailsResponse = new GetAccountDetailsResponse();
		
		try {
			
			if(StringUtils.isNotEmpty(contractAccountNumber.trim())&&StringUtils.isNotEmpty(companyCode.trim())&&StringUtils.equalsIgnoreCase(COMPANY_CODE_GME, companyCode)){
				
				contractAccountNumber=CommonUtil.paddedCa(contractAccountNumber.trim());
					
			
			
			accountDetailsResponse = getAccountDetails(contractAccountNumber, companyCode, brandName, sessionId);
			
			if(accountDetailsResponse!=null && (accountDetailsResponse.getResultCode().equalsIgnoreCase("0"))){
			String NCAFlag = ((accountDetailsResponse.getContractAccountDO().getStrNCAStatus().trim()).equalsIgnoreCase("X")?"false":"true");
			String NCCAFlag = ((accountDetailsResponse.getContractAccountDO().getStrNCCAStatus().trim()).equalsIgnoreCase("X")?"false":"true");
			
 
			List<Object> paymentMethodsList = new ArrayList<Object>();
			PayAccountInfoResponse payAccountInfoResp = new PayAccountInfoResponse();
			payAccountInfoResp = getPayAccounts(contractAccountNumber, companyCode, brandName, sessionId);
			
			if(payAccountInfoResp!=null && (payAccountInfoResp.getResultCode().equalsIgnoreCase("0"))
					&&(payAccountInfoResp.getPayAccountList().size()>0)){
			List<PayAccount> responselist = payAccountInfoResp.getPayAccountList();	
					
			//To get Credit card info
			for(int i=0;i<payAccountInfoResp.getPayAccountList().size();i++){
				PaymentMethodB paymentMethodB = new PaymentMethodB();
				PaymentMethodCC paymentMethodCC = new PaymentMethodCC();
				if((responselist.get(i).getOnlinePayAccountType()).equalsIgnoreCase("C"))
				{	
					paymentMethodCC.setIsAllowed(NCCAFlag);
					paymentMethodCC.setIsRegisteredWithAutopay(responselist.get(i).getAutoPay());
					paymentMethodCC.setNameOnAccount(responselist.get(i).getNameOnAccount());
					paymentMethodCC.setCreditCardExpYear(responselist.get(i).getCcExpYear());
					paymentMethodCC.setCreditCardExpMonth(responselist.get(i).getCcExpMonth());
					paymentMethodCC.setCreditCardType(responselist.get(i).getCcType());
					paymentMethodCC.setPaymentMethodType(responselist.get(i).getOnlinePayAccountType());
					paymentMethodCC.setPaymentMethodToken(responselist.get(i).getPayAccountToken());
					paymentMethodCC.setPaymentMethodNickName(responselist.get(i).getPayAccountNickName());
					paymentMethodCC.setZipCode(responselist.get(i).getZipCode());
					paymentMethodsList.add(i,paymentMethodCC);
				}else{
					
					paymentMethodB.setIsAllowed(NCAFlag);
					paymentMethodB.setIsRegisteredWithAutopay(responselist.get(i).getAutoPay());
					paymentMethodB.setNameOnAccount(responselist.get(i).getNameOnAccount());
					paymentMethodB.setRoutingNumber(responselist.get(i).getRoutingNumber());
					paymentMethodB.setPaymentMethodType(responselist.get(i).getOnlinePayAccountType());
					paymentMethodB.setPaymentMethodToken(responselist.get(i).getPayAccountToken());
					paymentMethodB.setPaymentMethodNickName(responselist.get(i).getPayAccountNickName());
					paymentMethodB.setZipCode(responselist.get(i).getZipCode());
					paymentMethodsList.add(i,paymentMethodB);
												
				}
			}
			response.setPaymentMethodsList(paymentMethodsList);
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(MSG_SUCCESS);
			response.setMessageCode("Successfully retrieved all Menthods of Payments");
						
			}else
				{
							
							response.setResultCode(RESULT_CODE_NO_DATA);
							response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
							response.setMessageCode("No Data was retrieved from getPayAccounts call");
				}
				}
				else
				{
							response.setResultCode(RESULT_CODE_CCS_ERROR);
							response.setResultDescription(accountDetailsResponse.getErrorCode());
							response.setMessageCode("Could not find Account Details");
				}
			}else
				{
					response.setResultCode(RESULT_CODE_FIVE);
					response.setResultDescription(RESULT_CODE_INVALID_INPUT_PARAMETERS);
					response.setMessageCode("Invalid Input Parameters - Please check entered A/C number and Company Code");
				
				}
			
		} catch (Exception e) {
			logger.error(" Error in getPaymentMethods call "+e.getMessage());
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		logger.info("END-[BillingBO-getPaymentMethods]");
		return response;
	}

}