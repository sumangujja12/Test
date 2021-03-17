package com.multibrand.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.DocumentException;
import com.multibrand.bo.BillingBO;
import com.multibrand.bo.ProfileBO;
import com.multibrand.dto.request.BillCourtesyCreditActivityRequest;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.resources.requestHandlers.BillingRequestHandler;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.BillingService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.request.AMBEligibilityCheckRequest;
import com.multibrand.vo.request.AutoPayInfoRequest;
import com.multibrand.vo.request.DPPEligibilityCheckRequest;
import com.multibrand.vo.request.DPPSubmitRequest;
import com.multibrand.vo.request.PaymentExtensionRequest;
import com.multibrand.vo.request.PaymentExtensionSubmitRequest;
import com.multibrand.vo.request.RetroPopupRequestVO;
import com.multibrand.vo.request.SaveAMBSingupRequestVO;
import com.multibrand.vo.request.StoreUpdatePayAccountRequest;
import com.multibrand.vo.response.AvgTempResponse;
import com.multibrand.vo.response.BillCourtesyCreditActivityResponse;
import com.multibrand.vo.response.CancelPaymentResponse;
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
import com.multibrand.vo.response.billingResponse.DPPExtensionCheckResponse;
import com.multibrand.vo.response.billingResponse.DPPSubmitResponse;
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
import com.multibrand.vo.response.profileResponse.PaymentExtensionCheckResponse;
import com.multibrand.vo.response.profileResponse.PaymentExtensionResponse;


/** This Resource is to handle all the Billing Related API calls.
 * 
 * @author Kdeshmu1
 */
@Component
@Path("billResource")
public class BillingResource {
	
	private static Logger logger = LogManager.getLogger(BillingResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;
	
	/** Object of ProfileBO class. */
	@Autowired
	private ProfileBO profileBO;

	
	/** Object of BillingBO class. */
	@Autowired
	private BillingBO billingBO;
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	ErrorContentHelper errorContentHelper;
	
	@Autowired
	private BillingRequestHandler billingRequestHandler;
	
	@Autowired
	protected EnvMessageReader envMessageReader;

	
	/** This service is to provide the balance information from CCS system.
	 * 
	 * @author Kdeshmu1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML balance data response
	 */
	@POST
	@Path("getBalance")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getBalance(@FormParam("accountNumber") String accountNumber, @FormParam("bpid") String bpNumber,
			@FormParam("companyCode") String companyCode, @FormParam("brandName")String brandName){
		
		Response response = null;
		GetArResponse getArResponse = billingBO.getBalance(accountNumber,bpNumber,companyCode, httpRequest.getSession(true).getId(),brandName);
		response = Response.status(200).entity(getArResponse).build();
		return response;
		
	}
	
	
	/** This service is to provide billing address using profile domain from Redbull Service
	 * 
	 * @author ahanda1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML response containing billing address
	 */
	@POST
	@Path("getBillingAddress")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getBillingAddress(@FormParam("accountNumber") String accountNumber, @FormParam("companyCode") String companyCode){
		
		Response response = null;
		GetBillingAddressResponse billingAddressResp = billingBO.getBillingAddress(accountNumber, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(billingAddressResp).build();
		return response;
		
	}
	
	
	/** This service is to provide account details using profile domain from Redbull Service
	 * 
	 * @author ahanda1
	 * @param accountNumber		Customer Account Number
	 * @return response			Provide JSON/XML response containing all the account details
	 */
	@POST
	@Path("getAccountDetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAccountDetails(@FormParam("accountNumber") String accountNumber,@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName){
		logger.info(" START ******* getAccountDetails API**********");
		Response response = null;
		GetAccountDetailsResponse getAccountDetailsResp = billingBO.getAccountDetails(accountNumber, companyCode,brandName, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(getAccountDetailsResp).build();
		
		logger.info("END of the getAccountDetails API*************");
		return response;
		
	}
	

	/*@GET
	@Path("billPDFImage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces("application/pdf")
	public Response getBillPDFImage() throws URISyntaxException{
		
				
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
	@POST
	@Path("updatePaperFreeBilling")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updatePaperFreeBilling(@FormParam("accountNumber") String accountNumber,@FormParam("flag") String flag,
			@FormParam("companyCode") String companyCode,@FormParam("bpNumber")String bpNumber, @FormParam("source")String source){
		
		Response response = null;
		UpdatePaperFreeBillingResponse updatePaperFreeBillingResponse = billingBO.updatePaperFreeBilling(accountNumber,flag,companyCode, httpRequest.getSession(true).getId(),bpNumber,source);
		
		
		response = Response.status(200).entity(updatePaperFreeBillingResponse).build();
		return response;
		
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
	@POST
	@Path("submitBankPayment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response submitBankPayment(@FormParam("accountNumber") String accountNumber, @FormParam("bpid")String bpid,
			@FormParam("bankAccountNumber") String bankAccountNumber, @FormParam("bankRoutingNumber") String bankRoutingNumber, @FormParam("paymentAmount")String paymentAmount,
			@FormParam("paymentDate")String paymentDate, @FormParam("companyCode") String companyCode, @FormParam("accountName") String accountName, 
			@FormParam("accountChkDigit") String accountChkDigit, @FormParam("languageCode")String locale, @FormParam("email") String email,
			@FormParam("brandName") String brandName, @FormParam("emailTypeId") String emailTypeId){
		
		logger.debug("Start BillingResource.submitBankPayment :: START");
		Response response = null;
		
		PayByBankResponse payByBankResp = billingBO.submitBankPayment(accountNumber, bpid, bankAccountNumber,bankRoutingNumber, paymentAmount, paymentDate, companyCode, 
				accountName, accountChkDigit, locale, email, httpRequest.getSession(true).getId(), brandName, emailTypeId);
		
		
		response = Response.status(200).entity(payByBankResp).build();
		logger.debug("END BillingResource.submitBankPayment :: END");
		return response;
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
	@POST
	@Path("submitCCPayment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response submitCCPayment(@FormParam("authType")String authType, @FormParam("accountNumber") String accountNumber, @FormParam("bpid")String bpid,
			@FormParam("ccNumber") String ccNumber, @FormParam("cvvNumber") String cvvNumber, @FormParam("expirationDate")String expirationDate,
			@FormParam("billingZip")String billingZip, @FormParam("paymentAmount") String paymentAmount, @FormParam("accountName") String accountName,
			@FormParam("accountChkDigit") String accountChkDigit, @FormParam("languageCode")String locale, @FormParam("email") String email, 
			@FormParam("companyCode")String companyCode, @FormParam("paymentDate") String paymentDate,@FormParam("brandName") String brandName,
			@FormParam("emailTypeId") String emailTypeId){
		logger.debug("Start BillingResource.submitCCPayment :: START");
		Response response = null;

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
		
		
		response = Response.status(200).entity(payByCCResp).build();
				
		logger.debug("END BillingResource.submitCCPayment :: END");
		return response;
	}
	
	
	
	/**
	 * @author smuruga1
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/projectedBill")
	public Response getProjectedBill(
			@FormParam("esid") String esiId,
			@FormParam("accountNumber") String accountNumber,
			@FormParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.getProjectedeBill :: START");
		Response response = null;
		logger.debug("AccountNumber" + accountNumber);

		ProjectedBillResponseList projectedResp = billingBO.getProjectedBill(esiId,
				accountNumber, companyCode, httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(projectedResp).build();

		logger.debug("Start BillingResource.getProjectedeBill :: END");

		return response;

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
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/avgTemperatureBill")
	public Response getAvgTempBill(
			@FormParam("billStartDate") String billStartDate,
			@FormParam("billEndDate") String billEndDate,
			@FormParam("zoneId") String zoneId,
			@FormParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.getProjectedeBill :: START");
		Response response = null;
		logger.debug("billStartDate" + billStartDate);
		logger.debug("billEndDate" + billEndDate);
		logger.debug("zoneId" + zoneId);

		AvgTempResponse avgTempResponse = billingBO.getAvgTempBill(
				billStartDate,billEndDate,zoneId, companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(avgTempResponse).build();

		logger.debug("Start BillingResource.getProjectedeBill :: END");

		return response;

	}
	
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param bpid
	 * @param contractNumber
	 * @param companyCode
	 * @return
	 */
	
	@POST
	@Path("billInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response billInfo(@FormParam("accountNumber") String accountNumber, 
			@FormParam("bpNumber")String bpid, @FormParam("contractId") String contractNumber, 
			@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName){
		logger.info("Start BillingResource.BillInfo :: START");
		Response response = null;
		
		BillInfoResponse billInfoResponse = billingBO.billInfo(accountNumber, bpid, contractNumber,companyCode,
				brandName, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(billInfoResponse).build();
			
		logger.info("END BillingResource.BillInfo :: END");
		return response;
	}
	
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param paymentId
	 * @param brandName
	 * @return
	 */
	@POST
	@Path("doCancelPayment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response doCancelPayment(@FormParam("accountNumber") String accountNumber,
			@FormParam("companyCode") String companyCode, @FormParam("paymentId") String paymentId,
			@FormParam("brandName") String brandName, @FormParam("businessPartnerId") String bpid,
			@FormParam("action") String action, @FormParam("source") String source, @FormParam("email") String email,
			@FormParam("paymentAmount") String paymentAmount,
			@FormParam("scheduledPaymentDate") String scheduledPaymentDate, @FormParam("checkDigit") String checkDigit,
			@FormParam("langCode") String langCode) {
		logger.debug("Start BillingResource.doCancelPayment :: START");
		Response response = null;
		CancelPaymentResponse cancelPaymentResponse = null;

		if (StringUtils.isNotBlank(action) && action.equalsIgnoreCase(Constants.ONLINE_ACCOUNT_TYPE_CC)) {
			EditCancelOTCCPaymentResponse editCancelOTCCPaymentResponse = billingBO.editCancelOTCCPayment(bpid,
					accountNumber, paymentId, action, companyCode, brandName, httpRequest.getSession(true).getId(),
					source, email, paymentAmount, scheduledPaymentDate, checkDigit, langCode);
			cancelPaymentResponse = new CancelPaymentResponse();
			BeanUtils.copyProperties(editCancelOTCCPaymentResponse, cancelPaymentResponse);
		} else {
			cancelPaymentResponse = billingBO.doCancelPayment(accountNumber, companyCode, paymentId, brandName,
					httpRequest.getSession(true).getId(), source, email, paymentAmount, scheduledPaymentDate,
					checkDigit, langCode);
		}

		response = Response.status(200).entity(cancelPaymentResponse).build();

		logger.debug("END BillingResource.doCancelPayment :: END");
		return response;
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
	@POST
	@Path("updateInvoiceDelivery")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateInvoiceDelivery(@FormParam("accountNumber") String accountNumber,@FormParam("eBillFlag") String ebillflag,
			@FormParam("paperFlag") String paperFlag, @FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName, @FormParam("email") String email){
		Response response = null;
		UpdateInvoiceDeliveryResponse updateInvoiceDeliveryResponse = billingBO
				.updateInvoiceDelivery(accountNumber, ebillflag, paperFlag,
						companyCode, brandName,httpRequest.getSession(true).getId(), email);
		response = Response.status(200).entity(updateInvoiceDeliveryResponse).build();
		
		logger.info(" START ******* Input for the updateInvoiceDelivery API**********");
		logger.info(" accountNumber - "+accountNumber);
		logger.info(" companyCode  - "+companyCode);
		logger.info(" eBillFlag  - "+ebillflag);
		logger.info(" paperFlag  - "+paperFlag);
		logger.info(" brandName  - "+brandName);
		logger.info(" OUTPUT of the updateInvoiceDelivery API*************");
		logger.info(" Response  - "+CommonUtil.wirteObjectToJson(response));
		System.out.println(" Response  json- "+CommonUtil.wirteObjectToJson(response));
		logger.info("END of the updateInvoiceDelivery API*************");
		
		return response;
		
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
	@POST
	@Path("getBankCCInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getBankCCInfo(@FormParam("businessPartnerId") String bpid, @FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName){
		Response response = null;
		BankCCInfoResponse bankCCInfoResp = billingBO
				.getBankCCInfo(bpid, companyCode, brandName,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(bankCCInfoResp).build();
				
		return response;
		
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
	@POST
	@Path("updateBankInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateBankInfo(
			@FormParam("businessPartnerId") String bpid,
			@FormParam("bankAccountNumber") String bankAccountNumber, 
			@FormParam("bankRoutingNumber")String bankRoutingNumber, 
			@FormParam("updateFlag")String updateFlag, 
			@FormParam("accountNickName")String accountNickName,
			@FormParam("defaultFlag")String defaultFlag,
			@FormParam("bankAccountType")String bankAccountType,
			@FormParam("bankAccountHolderType")String bankAccountHolderType,
			@FormParam("nameOnAccount")String nameOnAccount,
			@FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName,
			@FormParam("onlinePayAccountId")String onlinePayAccountId,
			@FormParam("emailId") String emailId
			){
		Response response = null;
		BankInfoUpdateResponse bankInfoUpdateResp = billingBO
				.updateBankInfo(bpid, bankAccountNumber, bankRoutingNumber, updateFlag,accountNickName, defaultFlag,bankAccountType,
						bankAccountHolderType,nameOnAccount,onlinePayAccountId, companyCode, brandName,httpRequest.getSession(true).getId(),emailId);
		response = Response.status(200).entity(bankInfoUpdateResp).build();
				
		return response;
		
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
	
	@POST
	@Path("updateCCInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateCCInfo(
			@FormParam("businessPartnerId") String bpid,
			@FormParam("ccType")String ccType,
			@FormParam("ccNumber") String ccNumber, 
			@FormParam("expMonth")String expMonth,
			@FormParam("expYear")String expYear,
			@FormParam("billingZipCode")String billingZipCode,
			@FormParam("updateFlag")String updateFlag, 
			@FormParam("accountNickName")String accountNickName,
			@FormParam("defaultFlag")String defaultFlag,
			@FormParam("nameOnAccount")String nameOnAccount,
			@FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName,
			@FormParam("onlinePayAccountId")String onlinePayAccountId,
			@FormParam("emailId")String emailId){	
		Response response = null;
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
		response = Response.status(200).entity(ccInfoUpdateResponse).build();
				
		return response;
		
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
	@POST
	@Path("scheduleOneTimeCCPayment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response scheduleOneTimeCCPayment(
			@FormParam("businessPartnerId") String bpid,
			@FormParam("contractAccountNumber")String contractAccountNumber,
			@FormParam("ccNumber") String ccNumber, 
			@FormParam("expMonth")String expMonth,
			@FormParam("expYear")String expYear,
			@FormParam("paymentAmount")String paymentAmount,
			@FormParam("scheduledDate")String scheduledDate,
			@FormParam("zipCode")String zipCode,
			@FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName,
			@FormParam("emailId")String emailId,
			@FormParam("isMobileRequest")boolean isMobileRequest,
			@FormParam("accountChkDigit")String checkdigit){
		Response response = null;
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
		
		response = Response.status(200).entity(scheduleOTCCPaymentResponse).build();
				
		return response;
		
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
	
	@POST
	@Path("editCancelOTCCPayment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response editCancelOTCCPayment(@FormParam("businessPartnerId") String bpid,
			@FormParam("contractAccountNumber") String contractAccountNumber,
			@FormParam("trackingId") String trackingId, @FormParam("action") String action,
			@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName,
			@FormParam("source") String source, @FormParam("email") String email,
			@FormParam("paymentAmount") String paymentAmount,
			@FormParam("scheduledPaymentDate") String scheduledPaymentDate, @FormParam("checkDigit") String checkDigit,
			@FormParam("langCode") String langCode) {
		Response response = null;
		EditCancelOTCCPaymentResponse editCancelOTCCPaymentResponse = billingBO.editCancelOTCCPayment(bpid,
				contractAccountNumber, trackingId, action, companyCode, brandName, httpRequest.getSession(true).getId(),
				source, email, paymentAmount, scheduledPaymentDate, checkDigit, langCode);
		response = Response.status(200).entity(editCancelOTCCPaymentResponse).build();
		return response;
	}

	@POST
	@Path("getPayAccounts")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getPayAccounts(
			@FormParam("contractAccountNumber")String contractAccountNumber,
			@FormParam("companyCode") String companyCode, 
			@FormParam("brandName") String brandName){
		Response response = null;
		
		PayAccountInfoResponse payAccountResponse = billingBO.getPayAccounts(contractAccountNumber, companyCode, brandName, httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(payAccountResponse).build();
				
		return response;
		
	}
	
	@POST
	@Path("storePayAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response storePayAccount(StoreUpdatePayAccountRequest request){
		Response response = null;
		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.storePayAccount(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
				
		return response;
		
	}
	

	@POST
	@Path("updatePayAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updatePayAccount(StoreUpdatePayAccountRequest request){
		Response response = null;		
		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.updatePayAccount(request, httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
				
		return response;
		
	}
	
	@POST
	@Path("getEBill")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({"application/pdf"})
	public Response getEBill(
			
			@FormParam("invoiceId")String invoiceId,
			@FormParam("languageCode")String languageCode,
			@FormParam("docType")String docType,
			@FormParam("companyCode")String companyCode,
			@FormParam("brandName")String brandName,
			@Context HttpServletResponse response
			) throws IOException, DocumentException {
		
		
		String customErrorMessage = envMessageReader.getMessage("custom.inv.error.message");
		ServletOutputStream out = response.getOutputStream(); 
		
		CloseableHttpClient client =  null;
		InputStream inputstream = null;
		
		try {
		
			int timeout = Integer.parseInt(envMessageReader.getMessage("doc.inv.url.timeout")); // seconds
			
			
			response.setHeader("Content-Disposition","attachment; filename=ebill.pdf");
			response.setContentType("application/pdf");
			
			
			RequestConfig config = RequestConfig.custom()
					  .setConnectTimeout(timeout * 1000)
					  .setConnectionRequestTimeout(timeout * 1000)
					  .setSocketTimeout(timeout * 1000).build();
			
			client = 
					  HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			
			
			String url = new BaseAbstractService().getEndPointUrl(Constants.EBILL_DOCUMENTUM_END_POINT_URL);
			
			HttpPost post = new HttpPost(url);
	
			
			List<NameValuePair> arguments = new ArrayList<>(3);
			arguments.add(new BasicNameValuePair("invoiceID", invoiceId));
			arguments.add(new BasicNameValuePair("docType",(StringUtils.isEmpty(docType))?Constants.DEFAULT_DOCTYPE:docType));
			arguments.add(new BasicNameValuePair("languageCode",(StringUtils.isEmpty(languageCode))?Constants.EN:languageCode));
	        
	        
			post.setEntity(new UrlEncodedFormEntity(arguments));
			
			HttpResponse httpResponse = client.execute(post);
			inputstream = httpResponse.getEntity().getContent();
			
			IOUtils.copy(inputstream, out);
			
			out.flush();
			out.close(); 
       
			logger.debug("Throwing the values to output");
			
        } catch(SocketTimeoutException e){
        	logger.error("SocketTimeoutException -- :{}", e.getMessage());
        	ByteArrayOutputStream outputStream = CommonUtil.getInvoiceTimeOutException(customErrorMessage);
			out.write(outputStream.toByteArray());
			out.flush();
			out.close();
			outputStream.close();

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
		} finally {
			if (client != null) {
				client.close();
			}
			if (inputstream != null) {
				inputstream.close();
			}
		}
		
		
		
		return Response.ok().build();
		
	}
	
    /**
     * Operation for Average Monthly Billing Eligibility Check 
     *@param ambEligRequest
     */
	
	@POST
	@Path("ambEligibilityCheck")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response ambeligibilityCheck(AMBEligibilityCheckRequest ambEligRequest) {
		
		Response response = null;
		AMBEligibiltyCheckResponseVO ambEligibiltyCheckResponseVO = billingBO.ambeligibilityCheck(ambEligRequest, httpRequest.getSession(true).getId());
		
				
		response = Response.status(200).entity(ambEligibiltyCheckResponseVO).build();
			
	    return response;
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
	@POST
	@Path("ambEligibilityCheckForGME")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response ambeligibilityCheck(@FormParam("accountNumber") String accountNumber, @FormParam("bpNumber") String bpNumber,
			@FormParam("contractId") String contractId, @FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName) {
		
		Response response = null;
		AMBEligibilityCheckRequest ambEligRequest = new AMBEligibilityCheckRequest();
		ambEligRequest.setAccountNumber(accountNumber);
		ambEligRequest.setBpNumber(bpNumber);
		ambEligRequest.setContractId(contractId);
		ambEligRequest.setCompanyCode(companyCode);
		AMBEligibiltyCheckResponseVO ambEligibiltyCheckResponseVO = billingBO.ambeligibilityCheck(ambEligRequest, httpRequest.getSession(true).getId());
		
				
		response = Response.status(200).entity(ambEligibiltyCheckResponseVO).build();
			
	    return response;
	}
	
	/**
	 * Operation for Average Monthly Billing Signup
	 * @param saveAMBSignupRequest
	 */
	@POST
	@Path("saveAMBSignUp")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response saveAMBSignUp(SaveAMBSingupRequestVO saveAMBSignupRequest) {
		
		Response response = null;
			
		AMBSignupResponseVO ambSignupResponseVO = billingBO.saveAMBSignUp(saveAMBSignupRequest, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(ambSignupResponseVO).build();
			
	    return response;
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
	@POST
	@Path("saveAMBSignUpForGME")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response saveAMBSignUp(@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId, @FormParam("bpNumber") String bpNumber,
			@FormParam("checkDigit") String checkDigit, @FormParam("languageCode") String languageCode,
			@FormParam("esid") String esid, @FormParam("servStreetNum") String servStreetNum,
			@FormParam("servStreetName") String servStreetName, @FormParam("servStreetAptNum") String servStreetAptNum,
			@FormParam("servCity") String servCity, @FormParam("servState") String servState,
			@FormParam("servZipCode") String servZipCode, @FormParam("billStreetNum") String billStreetNum,
			@FormParam("billStreetName") String billStreetName, @FormParam("billStreetAptNum") String billStreetAptNum,
			@FormParam("billAddrPOBox") String billAddrPOBox, @FormParam("billCity") String billCity,
			@FormParam("billState") String billState, @FormParam("billZipCode") String billZipCode,
			@FormParam("ambAmount") String ambAmount, @FormParam("toEmail") String toEmail,
			@FormParam("retroFlag") String retroFlag, @FormParam("amtAdjust") String amtAdjust,
			@FormParam("amtFinal") String amtFinal, @FormParam("bbpBasis") String bbpBasis,
			@FormParam("billAllocDate") String billAllocDate, @FormParam("estSign") String estSign,
			@FormParam("invoice") String invoice, @FormParam("resStatus") String resStatus,
			@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName) {
		
		Response response = null;
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
		response = Response.status(200).entity(ambSignupResponseVO).build();
			
	    return response;
	}
	
	/**This API is responsible for returning
	 * autopay information. 
	 * @param request
	 * @return
	 */
	@POST
	@Path("getAutoPayInfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public Response getAutoPayInfo(AutoPayInfoRequest request){
		Response response=null;
		AutoPayInfoResponse autoPayInfoRes = billingBO.getAutopayInfo(request);
		
		
		response= Response.status(200).entity(autoPayInfoRes).build();
		return response;
	}
	
	/**
	 * This API is responsible for returning
	 * payment institution name according
	 * to routing number.
	 * @param routingNumber
	 * @return
	 */
	@POST
	@Path("getBankPaymentInstitution")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getBankPaymentInstitution(@FormParam("routingNumber")String routingNumber){
		Response response = null;
        GetPaymentInstitutionResponse getPayInstResp = billingBO.getPaymentInstitutionName(routingNumber);
        response=Response.status(200).entity(getPayInstResp).build();
        return response;
	}
	
	@POST
	@Path("insertRetroPopup")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response insertRetroPopup(RetroPopupRequestVO request){
		Response response = null;		
		
		RetroEligibilityResponse retroEligibilityResponse = billingBO.insertRetroPopup(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(retroEligibilityResponse).build();
				
		return response;
		
	}
	
	/**
	 * @author adadan
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("checkRetroEligibility")
	public Response checkRetroEligibility(
			@FormParam("contractAccountNumber") String contractAccountNumber,
			@FormParam("invoiceNo") String invoiceNo,
			@FormParam("contractId") String contractId,
			@FormParam("currentARAmount") String currentARAmount,
			@FormParam("companyCode") String companyCode)
	{
		logger.debug("Start BillingResource.checkRetroEligibility :: START");
		Response response = null;
		logger.debug("AccountNumber" + contractAccountNumber);

		RetroEligibilityResponse retroEligResp = billingBO.checkRetroEligibility(contractAccountNumber,
				invoiceNo,contractId,currentARAmount, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(retroEligResp).build();

		logger.debug("Start BillingResource.checkRetroEligibility :: END");

		return response;

	}
	/**
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("billCourtesyCreditActivity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response courtesyCreditActivity (@Valid BillCourtesyCreditActivityRequest request) throws Exception{
		
		logger.debug("Start CourtesyCreditResource.courtesyCreditActivity :: START");
		
		Response response = null;
		
		BillCourtesyCreditActivityResponse tempResponse = new BillCourtesyCreditActivityResponse();
		
		tempResponse = billingService.courtesyCreditActivity(request, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(tempResponse).build();
			
		logger.debug("END CourtesyCreditResource.courtesyCreditActivity :: END");
		return response;


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
	@POST
	@Path("scheduleAndLastPaymentetails")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPendingPayments(@FormParam("accountNumber") String accountNumber,
			@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName) {
		Response response = null;
		SchedulePaymentResponse schedulePayments = billingBO.getSchedulePayments(accountNumber, companyCode, brandName,
				httpRequest.getSession(true).getId());
		response = Response.status(200).entity(schedulePayments).build();
		return response;

	}
	
	/**
	 * This API is responsible for returning account balance for GME mobile
	 * @author NGASPerera
	 * @param accountNumber
	 * @param bpNumber
	 * @param companyCode
	 * @param brandName
	 */
	@POST
	@Path("getBalanceForGMEMobile")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getBalanceForGMEMobile(@FormParam("accountNumber") String accountNumber,
			@FormParam("bpid") String bpNumber, @FormParam("companyCode") String companyCode,
			@FormParam("brandName") String brandName) {
		Response response = null;
		ArMobileGMEResponse mobileArResponse = billingBO.getBalanceForGMEMobile(accountNumber, bpNumber, companyCode,
				httpRequest.getSession(true).getId(), brandName);
		
		response = Response.status(200).entity(mobileArResponse).build();
		return response;
	}
	
	/**
	 * This API is responsible for returning account balance for GME mobile
	 * @author Cuppala
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 */
	@POST
	@Path("getPaymentMethods")
	@Consumes({  MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPaymentMethods(@FormParam("contractAccountNumber") String contractAccountNumber , @FormParam("companyCode") String companyCode,
			@FormParam("brandName") String brandName, @FormParam("bpnumber") String bpnumber) {
		Response response = null;
		PaymentMethodsResponse paymentMethodsResponse = billingBO.getPaymentMethods(contractAccountNumber, companyCode,
				httpRequest.getSession(true).getId(), brandName, bpnumber);
		response = Response.status(200).entity(paymentMethodsResponse).build();
		return response;
	}
	
	/**
	 * This API is responsible for saving pay accounts into DB after Duplicate pay account and nick name check.
	 * @author Cuppala
	 * @param onlinePayAccountType,lastFourDigit,nameOnAccount,payAccountNickName,payAccountToken,zipCode,
	 * activeFlag,activationDate,verifyCard,routingNumber,ccExpMonth,ccExpYear,onlinePayAccountId,ccType,
	 * autoPay,paymentInstitutionName,companyCode,brandName;
	 */
	
	@POST
	@Path("savePayAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response savePayAccount(StoreUpdatePayAccountRequest request){
		Response response = null;
		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.savePayAccount(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
				
		return response;
		
	}
	
	/**
	 * This API is responsible for update pay accounts into DB after Duplicate nick name check.
	 * @author Cuppala
	 * @param onlinePayAccountType,lastFourDigit,nameOnAccount,payAccountNickName,payAccountToken,zipCode,
	 * activeFlag,activationDate,verifyCard,routingNumber,ccExpMonth,ccExpYear,onlinePayAccountId,ccType,
	 * autoPay,paymentInstitutionName,companyCode,brandName;
	 */

	@POST
	@Path("modifyPayAccount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response modifyPayAccount(StoreUpdatePayAccountRequest request){
		Response response = null;		
		StoreUpdatePayAccountResponse storeUpdatePayAccountResponse = billingBO.modifyPayAccount(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(storeUpdatePayAccountResponse).build();
		return response;
}	
	/**
	 * This API is responsible to notify users if they have any pending SWAP offers.
	 * @author Cuppala
	 * @param accountNumber,companyCode,brandName;
	 */

	@POST
	@Path("checkSwapEligibility")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response checkSwapEligibility(@FormParam("accountNumber") String accountNumber,@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName){
		Response response = null;		
		
		CheckSwapEligibilityResponse checkSwapEligibilityResponse = billingBO.checkSwapEligibility(accountNumber,companyCode,brandName, httpRequest.getSession(true).getId(),Constants.APPLICATION_SWAP_AREA );
		response = Response.status(200).entity(checkSwapEligibilityResponse).build();
				
		return response;
		
	}

	@POST
	@Path("submitPaymentExtension")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response submitPaymentExtension(@Valid PaymentExtensionSubmitRequest request) {
		Response response = null;
		logger.info("Start-[BillingResource-submitPaymentExtension]");
		PaymentExtensionResponse paymentExtensionResponse = billingBO.submitPaymentExtension(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(paymentExtensionResponse).build();
		logger.info("End-[BillingResource-submitPaymentExtension]");
		return response;
	}
	
	@POST
	@Path("paymentExtEligibilityCheck")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPaymentExtensionCheck(@Valid PaymentExtensionRequest request) {
		Response response = null;
		logger.info("Start-[BillingResource-paymentExtEligibilityCheck]");
		PaymentExtensionCheckResponse paymentExtensionResponse = billingBO.getPaymentExtensionCheck(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(paymentExtensionResponse).build();
		logger.info("End-[BillingResource-paymentExtEligibilityCheck]");
		return response;
		
	}
	
	
	@POST
	@Path("dppEligibilityCheck")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getDppExtensionCheck(@Valid DPPEligibilityCheckRequest request) {
		Response response = null;
		logger.info("Start-[BillingResource-getDppExtensionCheck]");
		DPPExtensionCheckResponse dppExtensionCheckResponse = billingBO.getDPPPaymentExtensionCheck(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(dppExtensionCheckResponse).build();
		logger.info("End-[BillingResource-getDppExtensionCheck]");
		return response;
		
	}
	@POST
	@Path("submitdpp")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response submitdpp(@Valid DPPSubmitRequest request) {
		Response response = null;
		logger.info("Start-[BillingResource-submitdpp]");
		DPPSubmitResponse dppSubmitResponse = billingBO.dppSubmit(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(dppSubmitResponse).build();
		logger.info("End-[BillingResource-submitdpp]");
		return response;
		
	}	
}	
