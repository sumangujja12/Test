package com.multibrand.resources;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.BillingBO;
import com.multibrand.dto.request.BillCourtesyCreditActivityRequest;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.resources.requestHandlers.BillingRequestHandler;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.BillingService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.AMBEligibilityCheckRequest;
import com.multibrand.vo.request.AutoPayInfoRequest;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.request.SaveAMBSingupRequestVO;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.BillCourtesyCreditActivityResponse;
import com.multibrand.vo.response.CancelPaymentResponse;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.PayByBankResponse;
import com.multibrand.vo.response.PayByCCResponse;
import com.multibrand.vo.response.ProjectedBillResponseList;
import com.multibrand.vo.response.RetroEligibilityResponse;
import com.multibrand.vo.response.billingResponse.AMBEligibiltyCheckResponseVO;
import com.multibrand.vo.response.billingResponse.AMBSignupResponseVO;
import com.multibrand.vo.response.billingResponse.ArMobileGMEResponse;
import com.multibrand.vo.response.billingResponse.AutoPayInfoResponse;
import com.multibrand.vo.response.billingResponse.BankCCInfoResponse;
import com.multibrand.vo.response.billingResponse.BankInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.BillInfoResponse;
import com.multibrand.vo.response.billingResponse.CcInfoUpdateResponse;
import com.multibrand.vo.response.billingResponse.CheckSwapEligibilityResponse;
import com.multibrand.vo.response.billingResponse.EditCancelOTCCPaymentResponse;
import com.multibrand.vo.response.billingResponse.GetAccountDetailsResponse;
import com.multibrand.vo.response.billingResponse.GetArResponse;
import com.multibrand.vo.response.billingResponse.GetBillingAddressResponse;
import com.multibrand.vo.response.billingResponse.GetPaymentInstitutionResponse;
import com.multibrand.vo.response.billingResponse.PayAccountInfoResponse;
import com.multibrand.vo.response.billingResponse.PaymentMethodsResponse;
import com.multibrand.vo.response.billingResponse.ScheduleOTCCPaymentResponse;
import com.multibrand.vo.response.billingResponse.StoreUpdatePayAccountResponse;
import com.multibrand.vo.response.billingResponse.UpdateInvoiceDeliveryResponse;
import com.multibrand.vo.response.billingResponse.UpdatePaperFreeBillingResponse;
import com.multibrand.vo.response.historyResponse.SchedulePaymentResponse;


/** This Resource is to handle all the Billing Related API calls.
 * 
 * @author Kdeshmu1
 */
@RestController
public class BillingResource {
	
	private static Logger logger = LogManager.getLogger(BillingResource.class);
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	/** Object of BillingBO class. */
	@Autowired
	private BillingBO billingBO;
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	@Autowired
	private BillingRequestHandler billingRequestHandler;
	
	/** This service is to provide the balance information from CCS system.
	 * 
	 * @author Kdeshmu1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML balance data response
	 */
	@PostMapping(value="/billResource/getBalance", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GetArResponse getBalance(@RequestParam("accountNumber") String accountNumber, @RequestParam("bpid") String bpNumber,
			@RequestParam("companyCode") String companyCode, @RequestParam("brandName")String brandName){
		GetArResponse getArResponse = billingBO.getBalance(accountNumber,bpNumber,companyCode, httpRequest.getSession(true).getId(),brandName);
		return getArResponse;
		
	}
	
	
	/** This service is to provide billing address using profile domain from Redbull Service
	 * 
	 * @author ahanda1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML response containing billing address
	 */
	@PostMapping(value="/billResource/getBillingAddress", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GetBillingAddressResponse getBillingAddress(@RequestParam("accountNumber") String accountNumber, @RequestParam("companyCode") String companyCode){
		GetBillingAddressResponse billingAddressResp = billingBO.getBillingAddress(accountNumber, companyCode, httpRequest.getSession(true).getId());
		return billingAddressResp;
	}
	
	
	/** This service is to provide account details using profile domain from Redbull Service
	 * 
	 * @author ahanda1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML response containing all the account details
	 */
	@PostMapping(value="/billResource/getAccountDetails", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GetAccountDetailsResponse getAccountDetails(@RequestParam("accountNumber") String accountNumber,@RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName){
		logger.info(" START ******* getAccountDetails API**********");
		GetAccountDetailsResponse getAccountDetailsResp = billingBO.getAccountDetails(accountNumber, companyCode,brandName, httpRequest.getSession(true).getId());
		logger.info("END of the getAccountDetails API*************");
		return getAccountDetailsResp;
		
	}
	

	/*@GET
	@value("billPDFImage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	@Produces("application/pdf")
	public GenericResponse getBillPDFImage() throws URISyntaxException{
		
				
		Response response = null;
		URL fileURL = BillingResource.class.getResource("SampleEbill.pdf");
		File file = new File(fileURL.toURI());
		response = Response.status(200).entity(file).header("Content-Disposition",
				"attachment; filename=ebill.pdf").build();
		
		return response;
		
	}*/

	/** This service is to update the bill delivery option
	 * 
	 * @author kdeshmu1
	 * @param accountNumber		Customer Account Number
	 * @param flag			
	 * @param companyCode
	 * @return response			Provide JSON/XML response 
	 */
	@PostMapping(value="/billResource/updatePaperFreeBilling", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UpdatePaperFreeBillingResponse updatePaperFreeBilling(@RequestParam("accountNumber") String accountNumber,@RequestParam("flag") String flag,
			@RequestParam("companyCode") String companyCode,@RequestParam("bpNumber")String bpNumber, @RequestParam("source")String source){
		
		//Response response = null;
		UpdatePaperFreeBillingResponse updatePaperFreeBillingResponse = billingBO.updatePaperFreeBilling(accountNumber,flag,companyCode, httpRequest.getSession(true).getId(),bpNumber,source);
		
		
		//response = Response.status(200).entity(updatePaperFreeBillingResponse).build();
		return updatePaperFreeBillingResponse;
		
	}
	
	
	/**
	 * For submitting payment through Bank
	 * 
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
	 * @return
	 */
	@PostMapping(value="/billResource/submitBankPayment", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public PayByBankResponse submitBankPayment(@RequestParam("accountNumber") String accountNumber, @RequestParam("bpid")String bpid,
			@RequestParam("bankAccountNumber") String bankAccountNumber, @RequestParam("bankRoutingNumber") String bankRoutingNumber, @RequestParam("paymentAmount")String paymentAmount,
			@RequestParam("paymentDate")String paymentDate, @RequestParam("companyCode") String companyCode, @RequestParam("accountName") String accountName, 
			@RequestParam("accountChkDigit") String accountChkDigit, @RequestParam("languageCode")String locale, @RequestParam("email") String email,
			@RequestParam("brandName") String brandName, @RequestParam("emailTypeId") String emailTypeId){
		
		logger.debug("Start BillingResource.submitBankPayment :: START");
		//Response response = null;
		
		PayByBankResponse payByBankResp = billingBO.submitBankPayment(accountNumber, bpid, bankAccountNumber,bankRoutingNumber, paymentAmount, paymentDate, companyCode, 
				accountName, accountChkDigit, locale, email, httpRequest.getSession(true).getId(), brandName, emailTypeId);
		
		
		//response = Response.status(200).entity(payByBankResp).build();
		logger.debug("END BillingResource.submitBankPayment :: END");
		return payByBankResp;
	}
	
	/**
	 * 
	 * 
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
	 * @return
	 */
	@PostMapping(value="/billResource/submitCCPayment", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public PayByCCResponse submitCCPayment(@RequestParam("authType")String authType, @RequestParam("accountNumber") String accountNumber, @RequestParam("bpid")String bpid,
			@RequestParam("ccNumber") String ccNumber, @RequestParam("cvvNumber") String cvvNumber, @RequestParam("expirationDate")String expirationDate,
			@RequestParam("billingZip")String billingZip, @RequestParam("paymentAmount") String paymentAmount, @RequestParam("accountName") String accountName,
			@RequestParam("accountChkDigit") String accountChkDigit, @RequestParam("languageCode")String locale, @RequestParam("email") String email, 
			@RequestParam("companyCode")String companyCode, @RequestParam("paymentDate") String paymentDate,@RequestParam("brandName") String brandName,
			@RequestParam("emailTypeId") String emailTypeId){
		logger.debug("Start BillingResource.submitCCPayment :: START");
		//Response response = null;

		logger.debug("AccountNumber" + accountNumber);
		logger.debug("bpid"+ bpid);
		logger.debug("ccNumber"+CommonUtil.maskCCNo(ccNumber));
		logger.debug("cvvNumber" + cvvNumber);
		logger.debug("expirationDate"+ expirationDate);
		logger.debug("billingZip"+billingZip);
		logger.debug("paymentAmount"+paymentAmount);
		logger.debug("accountName"+ accountName);
		logger.debug("accountChkDigit"+accountChkDigit);
		logger.debug("locale"+locale);
		logger.debug("email"+ email);
		logger.debug("companyCode"+ companyCode);
		logger.debug("brandName"+ brandName);
		logger.debug("emailTypeId"+ emailTypeId);
		
		
		PayByCCResponse payByCCResp = billingBO.submitCCPayment(authType, accountNumber, bpid, ccNumber, cvvNumber, expirationDate, billingZip, paymentAmount, accountName, 
				accountChkDigit, locale, email, companyCode, httpRequest.getSession(true).getId(), paymentDate, brandName, emailTypeId);
		
		
		//response = Response.status(200).entity(payByCCResp).build();
				
		logger.debug("END BillingResource.submitCCPayment :: END");
		return payByCCResp;
	}
	
	
	
	/**
	 * @author smuruga1
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value="/billResource/projectedBill", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ProjectedBillResponseList getProjectedBill(
			@RequestParam("esid") String esiId,
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.getProjectedeBill :: START");
		//Response response = null;
		logger.debug("AccountNumber" + accountNumber);

		ProjectedBillResponseList projectedResp = billingBO.getProjectedBill(esiId,
				accountNumber, companyCode, httpRequest.getSession(true).getId());
		
		
		//response = Response.status(200).entity(projectedResp).build();

		logger.debug("Start BillingResource.getProjectedeBill :: END");

		return projectedResp;

	}
	
	/**
	 * @author smuruga1
	 * @param billStartDate
	 * 		 instance to store the billStart Date
	 * @param billEndDate
	 * 		 instance to store the billEnd Date
	 * @param zoneId
	 * 		  instance to store the zoneId
	 * @param companyCode
	 * @return Response
	 * 		response object hold the average temperatureValue
	 */
	@PostMapping(value="/billResource/avgTemperatureBill", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AvgTempResponse getAvgTempBill(
			@RequestParam("billStartDate") String billStartDate,
			@RequestParam("billEndDate") String billEndDate,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.getProjectedeBill :: START");
		//Response response = null;
		logger.debug("billStartDate" + billStartDate);
		logger.debug("billEndDate" + billEndDate);
		logger.debug("zoneId" + zoneId);

		AvgTempResponse avgTempResponse = billingBO.getAvgTempBill(
				billStartDate,billEndDate,zoneId, companyCode,httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(avgTempResponse).build();

		logger.debug("Start BillingResource.getProjectedeBill :: END");

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
	@PostMapping(value="/billResource/billInfo", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public BillInfoResponse billInfo(@RequestParam("accountNumber") String accountNumber, 
			@RequestParam("bpNumber")String bpid, @RequestParam("contractId") String contractNumber, 
			@RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName){
		logger.info("Start BillingResource.BillInfo :: START");
		//Response response = null;
		
		BillInfoResponse billInfoResponse = billingBO.billInfo(accountNumber, bpid, contractNumber,companyCode,
				brandName, httpRequest.getSession(true).getId());
		
		//response = Response.status(200).entity(billInfoResponse).build();
		logger.info("END BillingResource.BillInfo :: END");
		return billInfoResponse;
	}
	
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param paymentId
	 * @param brandName
	 * @return
	 */
	@PostMapping(value="/billResource/doCancelPayment", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CancelPaymentResponse doCancelPayment(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("companyCode") String companyCode, @RequestParam("paymentId") String paymentId,
			@RequestParam("brandName") String brandName, @RequestParam("businessPartnerId") String bpid,
			@RequestParam("action") String action) {
		logger.debug("Start BillingResource.doCancelPayment :: START");
		//Response response = null;
		CancelPaymentResponse cancelPaymentResponse  = null;
		
		if (StringUtils.isNotBlank(action) && action.equalsIgnoreCase(Constants.ONLINE_ACCOUNT_TYPE_CC)) {
			EditCancelOTCCPaymentResponse editCancelOTCCPaymentResponse = billingBO.editCancelOTCCPayment(bpid, accountNumber, paymentId, action,
					companyCode, brandName, httpRequest.getSession(true).getId());
			cancelPaymentResponse  = new CancelPaymentResponse();	
			BeanUtils.copyProperties(editCancelOTCCPaymentResponse, cancelPaymentResponse);
		} else {
			 cancelPaymentResponse = billingBO.doCancelPayment(accountNumber, companyCode,
					paymentId, brandName, httpRequest.getSession(true).getId());
		}		
		
		
		//response = Response.status(200).entity(cancelPaymentResponse).build();
		
		logger.debug("END BillingResource.doCancelPayment :: END");
		return cancelPaymentResponse;
	}
	
	/**
	 * smurga1 
	 * This method is for activate & de-activate the ebill for cirro project.
	 * @param accountNumber
	 * @param ebillflag
	 * @param paperFlag
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value="/billResource/updateInvoiceDelivery", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UpdateInvoiceDeliveryResponse updateInvoiceDelivery(@RequestParam("accountNumber") String accountNumber,@RequestParam("eBillFlag") String ebillflag,
			@RequestParam("paperFlag") String paperFlag, @RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName, @RequestParam("email") String email){
		//Response response = null;
		UpdateInvoiceDeliveryResponse updateInvoiceDeliveryResponse = billingBO
				.updateInvoiceDelivery(accountNumber, ebillflag, paperFlag,
						companyCode, brandName,httpRequest.getSession(true).getId(), email);
		//response = Response.status(200).entity(updateInvoiceDeliveryResponse).build();
		
		logger.info(" START ******* Input for the updateInvoiceDelivery API**********");
		logger.info(" accountNumber - "+accountNumber);
		logger.info(" companyCode  - "+companyCode);
		logger.info(" eBillFlag  - "+ebillflag);
		logger.info(" paperFlag  - "+paperFlag);
		logger.info(" brandName  - "+brandName);
		logger.info(" OUTPUT of the updateInvoiceDelivery API*************");
		//logger.info(" Response  - "+CommonUtil.wirteObjectToJson(response));
		//System.out.println(" Response  json- "+CommonUtil.wirteObjectToJson(response));
		logger.info("END of the updateInvoiceDelivery API*************");
		
		return updateInvoiceDeliveryResponse;
		
	}
	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param companyCode
	 * @param brandName
	 * @return
	 * 
	 * Added for Cirro to fetch Bank and cc info related to a bpid or ca
	 */
	@PostMapping(value="/billResource/getBankCCInfo", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public BankCCInfoResponse getBankCCInfo(@RequestParam("businessPartnerId") String bpid, @RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName){
		//Response response = null;
		BankCCInfoResponse bankCCInfoResp = billingBO
				.getBankCCInfo(bpid, companyCode, brandName,httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(bankCCInfoResp).build();
				
		return bankCCInfoResp;
		
	}
	
	/**
	 *  Added for Cirro to update Bank info related to a bpid
	 *  
	 *  @author ahanda1
	 * @param bpid
	 * @param bankAccountNumber
	 * @param bankRoutingNumber
	 * @param updateFlag
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value="/billResource/updateBankInfo", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public BankInfoUpdateResponse updateBankInfo(
			@RequestParam("businessPartnerId") String bpid,
			@RequestParam("bankAccountNumber") String bankAccountNumber, 
			@RequestParam("bankRoutingNumber")String bankRoutingNumber, 
			@RequestParam("updateFlag")String updateFlag, 
			@RequestParam("accountNickName")String accountNickName,
			@RequestParam("defaultFlag")String defaultFlag,
			@RequestParam("bankAccountType")String bankAccountType,
			@RequestParam("bankAccountHolderType")String bankAccountHolderType,
			@RequestParam("nameOnAccount")String nameOnAccount,
			@RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName,
			@RequestParam("onlinePayAccountId")String onlinePayAccountId,
			@RequestParam("emailId") String emailId
			){
		//Response response = null;
		BankInfoUpdateResponse bankInfoUpdateResp = billingBO
				.updateBankInfo(bpid, bankAccountNumber, bankRoutingNumber, updateFlag,accountNickName, defaultFlag,bankAccountType,
						bankAccountHolderType,nameOnAccount,onlinePayAccountId, companyCode, brandName,httpRequest.getSession(true).getId(),emailId);
		//response = Response.status(200).entity(bankInfoUpdateResp).build();
				
		return bankInfoUpdateResp;
		
	}
	
	/**
	 * @author ahanda1
	 * @param bpid
	 * @param ccType
	 * @param ccNumber
	 * @param expMonth
	 * @param expYear
	 * @param billingZipCode
	 * @param updateFlag
	 * @param accountNickName
	 * @param defaultFlag
	 * @param nameOnAccount
	 * @param companyCode
	 * @param brandName
	 * @param onlinePayAccountId
	 * @return
	 */
	@PostMapping(value="/billResource/updateCCInfo", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public CcInfoUpdateResponse updateCCInfo(
			@RequestParam("businessPartnerId") String bpid,
			@RequestParam("ccType")String ccType,
			@RequestParam("ccNumber") String ccNumber, 
			@RequestParam("expMonth")String expMonth,
			@RequestParam("expYear")String expYear,
			@RequestParam("billingZipCode")String billingZipCode,
			@RequestParam("updateFlag")String updateFlag, 
			@RequestParam("accountNickName")String accountNickName,
			@RequestParam("defaultFlag")String defaultFlag,
			@RequestParam("nameOnAccount")String nameOnAccount,
			@RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName,
			@RequestParam("onlinePayAccountId")String onlinePayAccountId,
			@RequestParam("emailId")String emailId){	
		//Response response = null;
		CcInfoUpdateResponse ccInfoUpdateResponse = billingBO
				.updateCCInfo(
						bpid, 
						ccType, 
						ccNumber,
						expMonth, 
						expYear, 
						billingZipCode, 
						updateFlag, 
						accountNickName, 
						defaultFlag, 
						nameOnAccount, 
						onlinePayAccountId, 
						companyCode,
						brandName,
						httpRequest.getSession(true).getId(),emailId);
		//response = Response.status(200).entity(ccInfoUpdateResponse).build();
				
		return ccInfoUpdateResponse;
		
	}
	
	/**
	 * 
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
	 * @return
	 */
	@PostMapping(value="/billResource/scheduleOneTimeCCPayment", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ScheduleOTCCPaymentResponse scheduleOneTimeCCPayment(
			@RequestParam("businessPartnerId") String bpid,
			@RequestParam("contractAccountNumber")String contractAccountNumber,
			@RequestParam("ccNumber") String ccNumber, 
			@RequestParam("expMonth")String expMonth,
			@RequestParam("expYear")String expYear,
			@RequestParam("paymentAmount")String paymentAmount,
			@RequestParam("scheduledDate")String scheduledDate,
			@RequestParam("zipCode")String zipCode,
			@RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName,
			@RequestParam("emailId")String emailId,
			@RequestParam("isMobileRequest")boolean isMobileRequest,
			@RequestParam("accountChkDigit")String checkdigit){
		//Response response = null;
		ScheduleOTCCPaymentResponse scheduleOTCCPaymentResponse = billingBO
				.scheduleOneTimeCCPayment(
						bpid, 
						contractAccountNumber,
						ccNumber,
						expMonth, 
						expYear, 
						paymentAmount, 
						scheduledDate, 
						zipCode, 
						companyCode,
						brandName,
						httpRequest.getSession(true).getId(),emailId,isMobileRequest, checkdigit);
		
		//response = Response.status(200).entity(scheduleOTCCPaymentResponse).build();
				
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
	 * @return
	 */
	@PostMapping(value="/billResource/editCancelOTCCPayment", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public EditCancelOTCCPaymentResponse editCancelOTCCPayment(
			@RequestParam("businessPartnerId") String bpid,
			@RequestParam("contractAccountNumber")String contractAccountNumber,
			@RequestParam("trackingId") String trackingId, 
			@RequestParam("action")String action,
			@RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName){
		//Response response = null;
		EditCancelOTCCPaymentResponse editCancelOTCCPaymentResponse = billingBO.editCancelOTCCPayment(
						bpid, 
						contractAccountNumber,
						trackingId,
						action,
						companyCode,
						brandName,
						httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(editCancelOTCCPaymentResponse).build();
				
		return editCancelOTCCPaymentResponse;
		
	}
	
	@PostMapping(value="/billResource/getPayAccounts", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public PayAccountInfoResponse getPayAccounts(
			@RequestParam("contractAccountNumber")String contractAccountNumber,
			@RequestParam("companyCode") String companyCode, 
			@RequestParam("brandName") String brandName){
		//Response response = null;
		
		PayAccountInfoResponse payAccountResponse = billingBO.getPayAccounts(contractAccountNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		
		
		//response = Response.status(200).entity(payAccountResponse).build();
				
		return payAccountResponse;
		
	}
	
	@PostMapping(value="/billResource/storePayAccount", consumes = {  MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public StoreUpdatePayAccountResponse storePayAccount(StoreUpdatePayAccountRequest request){
		//Response response = null;
		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.storePayAccount(request, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
				
		return storeUpdatePayAccountResponse;
		
	}
	
	@PostMapping(value="/billResource/updatePayAccount", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public StoreUpdatePayAccountResponse updatePayAccount(StoreUpdatePayAccountRequest request){
		//Response response = null;		
		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.updatePayAccount(request, httpRequest.getSession(true).getId());
		
		
		//response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
				
		return storeUpdatePayAccountResponse;
		
	}
	
	@PostMapping(value="/billResource/getEBill", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {"application/pdf" })
	public ResponseEntity<Object> getEBill(
			
			@RequestParam("invoiceId")String invoiceId,
			@RequestParam("languageCode")String languageCode,
			@RequestParam("docType")String docType,
			@RequestParam("companyCode")String companyCode,
			@RequestParam("brandName")String brandName,
			HttpServletResponse response
			){
		
		
		try {
		ServletOutputStream out = response.getOutputStream(); 
		response.setHeader("Content-Disposition","attachment; filename=ebill.pdf");
		response.setContentType("application/pdf");
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse billResponse = null;
		String output = null;
		BufferedReader br = null;
		String url = new BaseAbstractService(){}.getEndPointUrl(Constants.EBILL_DOCUMENTUM_END_POINT_URL);
		HttpPost postRequest = new HttpPost(url);
		//HttpPost postRequest = new HttpPost(JNDILookupHelper.doJndiLookup(MobileJNDIConstants.VIEW_BILL_SERVICE_URL));
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		postRequest.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		nameValuePairs.add(new BasicNameValuePair("invoiceID", invoiceId));
		nameValuePairs.add(new BasicNameValuePair("docType", (StringUtils.isEmpty(docType))?Constants.DEFAULT_DOCTYPE:docType));
        nameValuePairs.add(new BasicNameValuePair("languageCode", (StringUtils.isEmpty(languageCode))?Constants.EN:languageCode));
        
			postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			billResponse = httpClient.execute(postRequest);
			br = new BufferedReader(new InputStreamReader((billResponse.getEntity().getContent())));
			System.out.println("Output from Server 1st time is .... \n");
		    InputStream inputStream = billResponse.getEntity().getContent();
			BufferedInputStream bis = new BufferedInputStream(inputStream); 
			byte bytes[] = new byte[4096];
			int bytesRead;
			while ((bytesRead = bis.read(bytes)) != -1) {
				out.write(bytes, 0, bytesRead);
			}
			logger.debug("After while");
			out.flush();
			out.close();
			bis.close();
			logger.debug("Throwing the values to output");
			
        } catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedException -- Printing an Error PDF");
			logger.error(e);
			//e.printStackTrace();
			//callExceptionPDFWriter(out, txtInvoiceID);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException -- Printing an Error PDF");
			logger.error(e);;
			//e.printStackTrace();
			//callExceptionPDFWriter(out, txtInvoiceID);
		} catch (IOException e) {
			logger.error("IOException -- Printing an Error PDF");
			logger.error(e);
			//e.printStackTrace();
			//callExceptionPDFWriter(out, txtInvoiceID);
		}
		
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
	
    /**
     * Operation for Average Monthly Billing Eligibility Check 
     *@param ambEligRequest
     */
	@PostMapping(value="/billResource/ambEligibilityCheck", consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse ambeligibilityCheck(AMBEligibilityCheckRequest ambEligRequest) {
		
		AMBEligibiltyCheckResponseVO ambEligibiltyCheckResponseVO = billingBO.ambeligibilityCheck(ambEligRequest, httpRequest.getSession(true).getId());
			
	    return ambEligibiltyCheckResponseVO;
	}
	
	
    /**
     * Operation for Average Monthly Billing Eligibility Check for GME
     *@param ambEligRequest
     *@param accountNumber
     *@param bpNumber
     *@param contractId
     *@param companyCode
     *@param brandName
     * @return javax.ws.rs.core.Response
     */
	@PostMapping(value="/billResource/ambEligibilityCheckForGME", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse ambeligibilityCheck(@RequestParam("accountNumber") String accountNumber, @RequestParam("bpNumber") String bpNumber,
			@RequestParam("contractId") String contractId, @RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName) {
		
		AMBEligibilityCheckRequest ambEligRequest = new AMBEligibilityCheckRequest();
		ambEligRequest.setAccountNumber(accountNumber);
		ambEligRequest.setBpNumber(bpNumber);
		ambEligRequest.setContractId(contractId);
		ambEligRequest.setCompanyCode(companyCode);
		AMBEligibiltyCheckResponseVO ambEligibiltyCheckResponseVO = billingBO.ambeligibilityCheck(ambEligRequest, httpRequest.getSession(true).getId());
	    return ambEligibiltyCheckResponseVO;
	}
	
	/**
	 * Operation for Average Monthly Billing Signup
	 * @param saveAMBSignupRequest
	 */
	@PostMapping(value="/billResource/saveAMBSignUp", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse saveAMBSignUp(SaveAMBSingupRequestVO saveAMBSignupRequest) {
		AMBSignupResponseVO ambSignupResponseVO = billingBO.saveAMBSignUp(saveAMBSignupRequest, httpRequest.getSession(true).getId());
		return ambSignupResponseVO;
	}
	
	/**
	 	* Operation for Average Monthly Billing Signup for GME
	 	* @param saveAMBSignupRequest
	 	* @param AccountNumber
		* @param ContractId
		 * @param BpNumber
		 * @param CheckDigit
		 * @param LanguageCode
		 * @param Esid
		 * @param ServStreetNum
		 * @param ServStreetName
		 * @param ServStreetAptNum
		 * @param ServCity
		 * @param ServState
		 * @param ServZipCode
		 * @param BillStreetNum
		 * @param BillStreetName
		 * @param BillStreetAptNum
		 * @param BillAddrPOBox
		 * @param BillCity
		 * @param BillState
		 * @param BillZipCode
		 * @param AmbAmount
		 * @param ToEmail
		 * @param RetroFlag
		 * @param AmtAdjust
		 * @param AmtFinal
		 * @param BbpBasis
		 * @param BillAllocDate
		 * @param EstSign
		 * @param Invoice
		 * @param ResStatus
		 * @param CompanyCode
		 * @param BrandName
	 * @return javax.ws.rs.core.Response
	 */
	@PostMapping(value="/billResource/saveAMBSignUpForGME", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse saveAMBSignUp(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId, @RequestParam("bpNumber") String bpNumber,
			@RequestParam("checkDigit") String checkDigit, @RequestParam("languageCode") String languageCode,
			@RequestParam("esid") String esid, @RequestParam("servStreetNum") String servStreetNum,
			@RequestParam("servStreetName") String servStreetName, @RequestParam("servStreetAptNum") String servStreetAptNum,
			@RequestParam("servCity") String servCity, @RequestParam("servState") String servState,
			@RequestParam("servZipCode") String servZipCode, @RequestParam("billStreetNum") String billStreetNum,
			@RequestParam("billStreetName") String billStreetName, @RequestParam("billStreetAptNum") String billStreetAptNum,
			@RequestParam("billAddrPOBox") String billAddrPOBox, @RequestParam("billCity") String billCity,
			@RequestParam("billState") String billState, @RequestParam("billZipCode") String billZipCode,
			@RequestParam("ambAmount") String ambAmount, @RequestParam("toEmail") String toEmail,
			@RequestParam("retroFlag") String retroFlag, @RequestParam("amtAdjust") String amtAdjust,
			@RequestParam("amtFinal") String amtFinal, @RequestParam("bbpBasis") String bbpBasis,
			@RequestParam("billAllocDate") String billAllocDate, @RequestParam("estSign") String estSign,
			@RequestParam("invoice") String invoice, @RequestParam("resStatus") String resStatus,
			@RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName) {
		
		//Response response = null;
		SaveAMBSingupRequestVO saveAMBSignupRequest = new SaveAMBSingupRequestVO();
		saveAMBSignupRequest.setAccountNumber(accountNumber);
		saveAMBSignupRequest.setContractId(contractId);
		saveAMBSignupRequest.setBpNumber(bpNumber);
		saveAMBSignupRequest.setCheckDigit(checkDigit);
		saveAMBSignupRequest.setLanguageCode(languageCode);
		saveAMBSignupRequest.setEsid(esid);
		saveAMBSignupRequest.setServStreetNum(servStreetNum);
		saveAMBSignupRequest.setServStreetName(servStreetName);
		saveAMBSignupRequest.setServStreetAptNum(servStreetAptNum);
		saveAMBSignupRequest.setServCity(servCity);
		saveAMBSignupRequest.setServState(servState);
		saveAMBSignupRequest.setServZipCode(servZipCode);
		saveAMBSignupRequest.setBillStreetNum(billStreetNum);
		saveAMBSignupRequest.setBillStreetName(billStreetName);
		saveAMBSignupRequest.setBillStreetAptNum(billStreetAptNum);
		saveAMBSignupRequest.setBillAddrPOBox(billAddrPOBox);
		saveAMBSignupRequest.setBillCity(billCity);
		saveAMBSignupRequest.setBillState(billState);
		saveAMBSignupRequest.setBillZipCode(billZipCode);
		saveAMBSignupRequest.setAmbAmount(ambAmount);
		saveAMBSignupRequest.setToEmail(toEmail);
		saveAMBSignupRequest.setRetroFlag(retroFlag);
		saveAMBSignupRequest.setAmtAdjust(amtAdjust);
		saveAMBSignupRequest.setAmtFinal(amtFinal);
		saveAMBSignupRequest.setBbpBasis(bbpBasis);
		saveAMBSignupRequest.setBillAllocDate(billAllocDate);
		saveAMBSignupRequest.setEstSign(estSign);
		saveAMBSignupRequest.setInvoice(invoice);
		saveAMBSignupRequest.setResStatus(resStatus);
		saveAMBSignupRequest.setCompanyCode(companyCode);
		saveAMBSignupRequest.setBrandName(brandName);
		
		AMBSignupResponseVO ambSignupResponseVO = billingBO.saveAMBSignUp(saveAMBSignupRequest, httpRequest.getSession(true).getId());
			
	    return ambSignupResponseVO;
	}
	
	/**This API is responsible for returning
	 * autopay information. 
	 * @param request
	 * @return
	 */
	@PostMapping(value="/billResource/getAutoPayInfo", consumes =  {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getAutoPayInfo(AutoPayInfoRequest request){
		AutoPayInfoResponse autoPayInfoRes = billingBO.getAutopayInfo(request);
		return autoPayInfoRes;
	}
	
	/**
	 * This API is responsible for returning
	 * payment institution name according
	 * to routing number.
	 * @param routingNumber
	 * @return
	 */
	@PostMapping(value="/billResource/getBankPaymentInstitution", consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getBankPaymentInstitution(@RequestParam("routingNumber")String routingNumber){
        GetPaymentInstitutionResponse getPayInstResp = billingBO.getPaymentInstitutionName(routingNumber);
        return getPayInstResp;
	}
	
	@PostMapping(value="/billResource/insertRetroPopup", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse insertRetroPopup(RetroPopupRequestVO request){
			
		RetroEligibilityResponse retroEligibilityResponse = billingBO.insertRetroPopup(request, httpRequest.getSession(true).getId());
					
		return retroEligibilityResponse;
		
	}
	
	/**
	 * @author adadan
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value="/billResource/checkRetroEligibility", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse checkRetroEligibility(
			@RequestParam("contractAccountNumber") String contractAccountNumber,
			@RequestParam("invoiceNo") String invoiceNo,
			@RequestParam("contractId") String contractId,
			@RequestParam("currentARAmount") String currentARAmount,
			@RequestParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.checkRetroEligibility :: START");
		
		logger.debug("AccountNumber" + contractAccountNumber);

		RetroEligibilityResponse retroEligResp = billingBO.checkRetroEligibility(contractAccountNumber,
				invoiceNo,contractId,currentARAmount, companyCode, httpRequest.getSession(true).getId());
		

		logger.debug("Start BillingResource.checkRetroEligibility :: END");

		return retroEligResp;

	}
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/billResource/billCourtesyCreditActivity", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public BillCourtesyCreditActivityResponse courtesyCreditActivity (@Valid BillCourtesyCreditActivityRequest request) throws Exception{
		
		logger.debug("Start CourtesyCreditResource.courtesyCreditActivity :: START");
		
	
		
		BillCourtesyCreditActivityResponse tempResponse = new BillCourtesyCreditActivityResponse();
		
		tempResponse = billingService.courtesyCreditActivity(request, httpRequest.getSession(true).getId());
		

			
		logger.debug("END CourtesyCreditResource.courtesyCreditActivity :: END");
		return tempResponse;


}	
	
	/**
	 * This API is responsible for returning
	 * pending payments and last paid date
	 * @author NGASPerera
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * 
	 */
	@PostMapping(value="/billResource/scheduleAndLastPaymentetails", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getPendingPayments(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName) {
		SchedulePaymentResponse schedulePayments = billingBO.getSchedulePayments(accountNumber, companyCode, brandName,
				httpRequest.getSession(true).getId());
		return schedulePayments;

	}
	
	/**
	 * This API is responsible for returning account balance for GME mobile
	 * @author NGASPerera
	 * @param accountNumber
	 * @param bpNumber
	 * @param companyCode
	 * @param brandName
	 */
	@PostMapping(value="/billResource/getBalanceForGMEMobile", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getBalanceForGMEMobile(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("bpid") String bpNumber, @RequestParam("companyCode") String companyCode,
			@RequestParam("brandName") String brandName) {
		ArMobileGMEResponse mobileArResponse = billingBO.getBalanceForGMEMobile(accountNumber, bpNumber, companyCode,
				httpRequest.getSession(true).getId(), brandName);
		return mobileArResponse;
	}
	
	/**
	 * This API is responsible for returning account balance for GME mobile
	 * @author Cuppala
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 */
	@PostMapping(value="/billResource/getPaymentMethods", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse getPaymentMethods(@RequestParam("contractAccountNumber") String contractAccountNumber , @RequestParam("companyCode") String companyCode,
			@RequestParam("brandName") String brandName, @RequestParam("bpnumber") String bpnumber) {
		PaymentMethodsResponse paymentMethodsResponse = billingBO.getPaymentMethods(contractAccountNumber, companyCode,
				httpRequest.getSession(true).getId(), brandName, bpnumber);
		return paymentMethodsResponse;
	}
	
	/**
	 * This API is responsible for saving pay accounts into DB after Duplicate pay account and nick name check.
	 * @author Cuppala
	 * @param onlinePayAccountType,lastFourDigit,nameOnAccount,payAccountNickName,payAccountToken,zipCode,
	 * activeFlag,activationDate,verifyCard,routingNumber,ccExpMonth,ccExpYear,onlinePayAccountId,ccType,
	 * autoPay,paymentInstitutionName,companyCode,brandName;
	 */
	@PostMapping(value="/billResource/savePayAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse savePayAccount(StoreUpdatePayAccountRequest request){
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.savePayAccount(request, httpRequest.getSession(true).getId());		
		return storeUpdatePayAccountResponse;
		
	}
	
	/**
	 * This API is responsible for update pay accounts into DB after Duplicate nick name check.
	 * @author Cuppala
	 * @param onlinePayAccountType,lastFourDigit,nameOnAccount,payAccountNickName,payAccountToken,zipCode,
	 * activeFlag,activationDate,verifyCard,routingNumber,ccExpMonth,ccExpYear,onlinePayAccountId,ccType,
	 * autoPay,paymentInstitutionName,companyCode,brandName;
	 */
	@PostMapping(value="/billResource/modifyPayAccount", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse modifyPayAccount(StoreUpdatePayAccountRequest request){	
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.modifyPayAccount(request, httpRequest.getSession(true).getId());
		return storeUpdatePayAccountResponse;
	}	
	/**
	 * This API is responsible to notify users if they have any pending SWAP offers.
	 * @author Cuppala
	 * @param accountNumber,companyCode,brandName;
	 */
	@PostMapping(value="/billResource/checkSwapEligibility", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GenericResponse checkSwapEligibility(@RequestParam("accountNumber") String accountNumber,@RequestParam("companyCode") String companyCode, @RequestParam("brandName") String brandName){	
		CheckSwapEligibilityResponse checkSwapEligibilityResponse = billingBO.checkSwapEligibility(accountNumber,companyCode,brandName, httpRequest.getSession(true).getId());
		return checkSwapEligibilityResponse;
		
	}

}	
