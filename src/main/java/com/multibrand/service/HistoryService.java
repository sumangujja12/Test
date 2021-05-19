package com.multibrand.service;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.domain.ConsumptionHistory;
import com.multibrand.domain.GetUsageHistoryRequest;
import com.multibrand.domain.HistoryDomain;
import com.multibrand.domain.HistoryDomainPortBindingStub;
import com.multibrand.domain.PaymentHistoryRequest;
import com.multibrand.domain.PaymentHistoryResponse;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.JAXBUtil;
import com.multibrand.util.XIApacheClient;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.historyRequest.BillPaymentHistoryRequestVO;
import com.multibrand.vo.request.historyRequest.PlanHistoryRequestVO;
import com.multibrand.vo.request.historyRequest.xi.InvoiceListRequest;
import com.multibrand.vo.request.historyRequest.xi.ProductHistoryRequest;
import com.multibrand.vo.response.historyResponse.BillPaymentHistory;
import com.multibrand.vo.response.historyResponse.BillPaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.InvoiceUsageHistoryResponse;
import com.multibrand.vo.response.historyResponse.PlanHistory;
import com.multibrand.vo.response.historyResponse.PlanHistoryResponse;
import com.multibrand.vo.response.historyResponse.xi.MTGetInvoiceListResponse;
import com.multibrand.vo.response.historyResponse.xi.MTGetProductHistoryResponse;
import com.multibrand.vo.response.historyResponse.xi.RowInvoiceList;
import com.multibrand.vo.response.historyResponse.xi.RowProductHistory;
import com.nrg.cxfstubs.billpaymenthistory.ZEWSBILLPAYMENTHISTORY;
import com.nrg.cxfstubs.billpaymenthistory.ZEWSBILLPAYMENTHISTORY_Service;
import com.nrg.cxfstubs.billpaymenthistory.ZesBillHistory;
import com.nrg.cxfstubs.billpaymenthistory.ZetBillHistory;
import com.nrg.cxfstubs.planhistory.TableOfBapiret2;
import com.nrg.cxfstubs.planhistory.TableOfZesPlanHistory;
import com.nrg.cxfstubs.planhistory.ZEWSISUPLANHISTORY;
import com.nrg.cxfstubs.planhistory.ZEWSISUPLANHISTORY_Service;
import com.nrg.cxfstubs.planhistory.ZesPlanHistory;



/**
 * 
 * @author mshukla1
 * 
 */

@Service
public class HistoryService extends BaseAbstractService {
	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	
	/**
	 * This will return HistoryDomainProxy and set EndPoint URL
	 * 
	 * @return historyDomainProxy The HistoryDomainProxy Object
	 */
	protected HistoryDomain getHistoryDomainProxy() {

		return (HistoryDomain) getServiceProxy(
				HistoryDomainPortBindingStub.class,
				HISTORY_SERVICE_ENDPOINT_URL);
	}
	
	/*public PlanHistoryResponse getPlanHistory(PlanHistoryRequestVO request)throws RemoteException
	{
		PlanHistoryResponse response = null;
		logger.info("HistoryService - getPlanhistory starts");
		
		
		
		logger.info("HistoryService - getPlanhistory ends");
		return response;
	}*/
	
	/*public PaymentHistoryResponse[] getPaymentHistory(String accountNumber, String todayDate, String companyCode, String sessionId)
			throws RemoteException {
		logger.info("HistoryService - getPaymentHistory starts");
		long startTime = CommonUtil.getStartTime();
		PaymentHistoryRequest request = new PaymentHistoryRequest();
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		request.setStrHistoryDate(todayDate);
		HistoryDomain proxy = getHistoryDomainProxy();
		PaymentHistoryResponse[] response = proxy.getPaymentHistory(request);
		utilityloggerHelper.logTransaction("getPaymentHistoryCCS", false, request,response,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		logger.info(XmlUtil.pojoToXML(request));
		logger.info(XmlUtil.pojoToXML(response));
		logger.info("HistoryService - getPaymentHistory ends");
		return response;
	}*/

	/**
	 * @author mshukla1
	 * @param request
	 * @return
	 * @throws RemoteException
	 */
	public PlanHistoryResponse getPlanHistory(PlanHistoryRequestVO request, String sessionId)throws Exception
	{
		logger.info("fetching data from IRW & CCS");
		
		int xiData = 0;
		int ccsData = 0;
		List<RowProductHistory> rows = null;
		
		if(request.getConversionDate()!=null && !request.getConversionDate().equals("") && request.getLegacyAccountNumber()!=null && !request.getLegacyAccountNumber().equals(""))
        {
			logger.info("fetching data from IRW");
			long startTime = CommonUtil.getStartTime();
        	ProductHistoryRequest productRequest = new ProductHistoryRequest();
        	productRequest.setAccountId(request.getLegacyAccountNumber());
        	if(request.getLanguageCode()!=null && request.getLanguageCode().equalsIgnoreCase("S"))
        		productRequest.setLanguage("ES");
        	else
        		productRequest.setLanguage("EN");
        	String resp = XIApacheClient.getResponseBody(this.envMessageReader.getMessage(XI_HISTORY_ENDPOINT_URL)+XI_PRODUCT_HISTORY_ENDPOINT_URL_QUERY_PARAM, productRequest, null);
        	logger.info("Response::"+resp);
        	MTGetProductHistoryResponse object = (MTGetProductHistoryResponse)JAXBUtil.unmarshal(resp, "com.multibrand.vo.response.historyResponse.xi.MTGetProductHistoryResponse");
        	utilityloggerHelper.logTransaction("getPlanHistoryXI", false, productRequest,object, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        	if(logger.isDebugEnabled()){
        		logger.debug(XmlUtil.pojoToXML(productRequest));
        		logger.debug(XmlUtil.pojoToXML(object));
        	}
        	rows = object.getProductHistoryResponse().getRow();
        	if(rows!=null && rows.size()>0)
        		xiData = rows.size();
        }
		
		PlanHistoryResponse response = new PlanHistoryResponse();
		
		String imCa = CommonUtil.paddedCa(request.getAccountNumber());
		String imLang= request.getLanguageCode();
		String startdate = request.getStartDate();
		String endDate = request.getEndDate();
		
		URL url = ZEWSISUPLANHISTORY_Service.class
		.getResource("Z_E_WS_ISU_PLAN_HISTORY.wsdl");
		
		ZEWSISUPLANHISTORY_Service port = new ZEWSISUPLANHISTORY_Service(url);
		
		ZEWSISUPLANHISTORY stub = port.getZEWSISUPLANHISTORY();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(PLAN_HISTORY_ENDPOINT_URL_JNDINAME));
		
		
		
		javax.xml.ws.Holder<com.nrg.cxfstubs.planhistory.TableOfZesPlanHistory> hTZPlanHistory = new javax.xml.ws.Holder<com.nrg.cxfstubs.planhistory.TableOfZesPlanHistory>();
		TableOfZesPlanHistory tbZesPlanHistory = new TableOfZesPlanHistory();
		hTZPlanHistory.value=tbZesPlanHistory;
		
		javax.xml.ws.Holder<com.nrg.cxfstubs.planhistory.TableOfBapiret2> hTBapiret2 = new  javax.xml.ws.Holder<com.nrg.cxfstubs.planhistory.TableOfBapiret2>();
		TableOfBapiret2 tBapiRet2 = new TableOfBapiret2();
		hTBapiret2.value=tBapiRet2;
		
		String ccsRequestParams="endDate="+endDate+",starteDate="+startdate+",imLang="+imLang+",imCa="+imCa;
		long startTime = CommonUtil.getStartTime();
		stub.zeIsuPlanHistory(endDate, hTZPlanHistory, hTBapiret2, imCa, imLang, startdate);
		
		
		TableOfZesPlanHistory tbzeplanHistory = hTZPlanHistory.value;
		List<ZesPlanHistory> zelistPlanHistory =  tbzeplanHistory.getItem();
		utilityloggerHelper.logTransaction("getPlanHistoryCCS", false, ccsRequestParams,zelistPlanHistory, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(zelistPlanHistory));
		}
		if(zelistPlanHistory!=null && zelistPlanHistory.size()>0)
			ccsData = zelistPlanHistory.size();
		
		PlanHistory[] planHistory = new PlanHistory[ccsData+xiData];
		int count=0;
		
		if(ccsData>0)
		{
			logger.info("CCS size : "+ccsData);
			for(ZesPlanHistory zePlanHistory:zelistPlanHistory)
			{
				planHistory[count] = new PlanHistory();
				planHistory[count].setCity(zePlanHistory.getCity());
				planHistory[count].setContract(zePlanHistory.getContract());
				planHistory[count].setContractAccount(zePlanHistory.getContractAccount());
				planHistory[count].setEflFilename(zePlanHistory.getEflFilename());
				planHistory[count].setEndDate(CommonUtil.changeDateFormat(zePlanHistory.getEndDate()));
				planHistory[count].setEsiid(zePlanHistory.getEsiid());
				planHistory[count].setInstallation(zePlanHistory.getInstallation());
				planHistory[count].setLanguage(zePlanHistory.getLanguage());
				planHistory[count].setProductName(zePlanHistory.getProductName());
				planHistory[count].setStartDate(CommonUtil.changeDateFormat(zePlanHistory.getStartDate()));
				planHistory[count].setState(zePlanHistory.getState());
				planHistory[count].setStreet(zePlanHistory.getStreet());
				planHistory[count].setSupplement(zePlanHistory.getSupplement());
				planHistory[count].setStreetNo(zePlanHistory.getStreetNo());
				planHistory[count].setZip(zePlanHistory.getZip());
				planHistory[count].setOfferCode(zePlanHistory.getOfferCode());
				count++;
			}
		}
		
		if(xiData>0)
		{
			logger.info("XI Data size : "+xiData);
			for(RowProductHistory row : rows)
			{
				if(row.getResult_Code()!=null && (row.getResult_Code().equals("2") || row.getResult_Code().equals("3")))
					break;
				planHistory[count] = new PlanHistory();
				planHistory[count].setCity(row.getCity());
				planHistory[count].setContract("");
				planHistory[count].setContractAccount(request.getAccountNumber());
				planHistory[count].setEflFilename(row.getDocId());
				if(row.getProductEndDate()!=null)
				{
					String[] strEndDateArray = row.getProductEndDate().split(" ");
					planHistory[count].setEndDate(CommonUtil.changeDateFormat(strEndDateArray[0]));
				}
				else
					planHistory[count].setEndDate("");
				planHistory[count].setEsiid(row.getEsid());
				planHistory[count].setInstallation("");
				planHistory[count].setLanguage(request.getLanguageCode());
				planHistory[count].setProductName(row.getProduct());
				if(row.getProductStartDate()!=null)
				{
					String[] strStartDateArray = row.getProductStartDate().split(" ");
					planHistory[count].setStartDate(CommonUtil.changeDateFormat(strStartDateArray[0]));
				}
				else
					planHistory[count].setStartDate("");
				planHistory[count].setState(row.getState());
				planHistory[count].setStreet(row.getAddressLine1());
				planHistory[count].setSupplement("");
				planHistory[count].setStreetNo(row.getAddressLine2());
				planHistory[count].setZip(row.getZipCode());
				planHistory[count].setOfferCode("");
				count++;
			}
		}
		
		response.setPlanHistory(planHistory);
		
		logger.info("HistoryService - getPlanhistory ends");
		return response;
	}
	/**
	 * @author kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public BillPaymentHistoryResponse getBillPaymentHistory(BillPaymentHistoryRequestVO request, String sessionId)throws Exception
	{
		logger.info("fetching data from IRW & CCS");
		int xiData = 0;
		int ccsData = 0;
		List<RowInvoiceList> rows = null;
		String dateFrom = CommonUtil.changeDateFormat(request.getStartDate(), MM_dd_yyyy, yyyy_MM_dd);
        String endDate = CommonUtil.changeDateFormat(request.getEndDate(), MM_dd_yyyy, yyyy_MM_dd);
        if(request.getConversionDate()!=null && !request.getConversionDate().equals("") && request.getLegacyAccountNumber()!=null && !request.getLegacyAccountNumber().equals(""))
        {
        	logger.info("fetching data from IRW");
        	InvoiceListRequest invoiceRequest = new InvoiceListRequest();
        	invoiceRequest.setAccountId(request.getLegacyAccountNumber());
        	invoiceRequest.setStartDate(dateFrom);
        	invoiceRequest.setStopDate(endDate);
        	long startTime = CommonUtil.getStartTime();
        	String resp = XIApacheClient.getResponseBody(this.envMessageReader.getMessage(XI_HISTORY_ENDPOINT_URL)+XI_INVOICE_LIST_ENDPOINT_URL_QUERY_PARAM, invoiceRequest, null);
        	logger.info("Response::"+resp);
        	MTGetInvoiceListResponse object = (MTGetInvoiceListResponse)JAXBUtil.unmarshal(resp, "com.multibrand.vo.response.historyResponse.xi.MTGetInvoiceListResponse");
        	utilityloggerHelper.logTransaction("getBillPaymentHistoryXI", false, invoiceRequest,object, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
        	if(logger.isDebugEnabled()){
	        	logger.debug(XmlUtil.pojoToXML(invoiceRequest));
				logger.debug(XmlUtil.pojoToXML(object));
        	}
        	rows = object.getInvoiceListResponse().getRow();
        	if(rows!=null && rows.size()>0)
        		xiData = rows.size();
        }
		
		BillPaymentHistoryResponse response = new BillPaymentHistoryResponse();
		String imCa = CommonUtil.paddedCa(request.getAccountNumber());
		logger.info("Start Dtae"+request.getStartDate());
		logger.info("Date from....."+CommonUtil.changeDateFormat(request.getStartDate(), MM_dd_yyyy, yyyy_MM_dd));
		logger.info("date form request......"+dateFrom);
		//sending 2 months old date to ccs
		DateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd);
		Date startDate=null;
		Calendar cal = Calendar.getInstance();
		try {
			startDate = dateFormat.parse(dateFrom);
			cal.setTime(startDate);
			cal.add(Calendar.DATE, -60);
			logger.info("Date before 60 days"+cal.getTime());
			
		} catch (ParseException e) {
			logger.error("Exception occurred in parsing date "+e);
		}
		
        String startdate = dateFormat.format(cal.getTime());
		logger.info("start date to be sent to ccs .."+startdate);

		URL url = ZEWSBILLPAYMENTHISTORY_Service.class
		.getResource("Z_E_WS_BILL_PAYMENT_HISTORY.wsdl");
		
		ZEWSBILLPAYMENTHISTORY_Service port = new ZEWSBILLPAYMENTHISTORY_Service(url);
		
		ZEWSBILLPAYMENTHISTORY stub = port.getZEWSBILLPAYMENTHISTORY();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(BILL_PAYMENT_HISTORY_ENDPOINT_URL_JNDINAME));
		
		
		javax.xml.ws.Holder<com.nrg.cxfstubs.billpaymenthistory.ZetBillHistory> hTZBillHistory = new javax.xml.ws.Holder<com.nrg.cxfstubs.billpaymenthistory.ZetBillHistory>();
		ZetBillHistory tbZesBillHistory = new ZetBillHistory();
		List<ZesBillHistory> listZesBillHistory = tbZesBillHistory.getItem();
		
		listZesBillHistory.add(new ZesBillHistory());
		hTZBillHistory.value=tbZesBillHistory;
		
		
		javax.xml.ws.Holder<Integer> eReturnCode = new  javax.xml.ws.Holder<Integer>();
		String ccsRequest="imCa="+imCa+",startDate="+startdate+",endDate="+endDate;
		long startTime = CommonUtil.getStartTime();
		stub.zeIsuBillPaymentHistory(imCa,startdate,endDate, eReturnCode,hTZBillHistory);
		
		
		ZetBillHistory tbzebilHistory = hTZBillHistory.value;
		List<ZesBillHistory> zelistBillHistory =  tbzebilHistory.getItem();
		
		utilityloggerHelper.logTransaction("getBillPaymentHistoryCCS", false, ccsRequest,zelistBillHistory, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(ccsRequest));
			logger.debug(XmlUtil.pojoToXML(zelistBillHistory));
		}
		if(zelistBillHistory!=null && zelistBillHistory.size()>0)
			ccsData = zelistBillHistory.size();
		
		logger.info("Bill History List "+zelistBillHistory.size());
		BillPaymentHistory[] billPaymentHistory = new BillPaymentHistory[ccsData+xiData];
		int count=0;
		Date actualStartDate = null;
		Date invoiceDate = null;
		BigDecimal amountDue=null;


		actualStartDate = dateFormat.parse(dateFrom);
		logger.info("Actual start Date "+actualStartDate);

		if(ccsData>0)
		{
			logger.info("CCS Data size : "+ccsData);
			
			// setting the ccsDataFlag = X
			logger.info("Setting CCS Data flag = X");
			response.setCcsDataFlag("X");
			
			for(ZesBillHistory zeBillHistory:zelistBillHistory)
			{
				invoiceDate=dateFormat.parse(zeBillHistory.getInvoiceDate());
				logger.info("Invoice date "+invoiceDate);
				if(invoiceDate.after(actualStartDate))
				{
					billPaymentHistory[count] = new BillPaymentHistory();
					billPaymentHistory[count].setAmountDue(zeBillHistory.getAmountDue().toString());
					billPaymentHistory[count].setDueDate(CommonUtil.changeDateFormat(zeBillHistory.getDueDate()));
					billPaymentHistory[count].setInvoiceDate(CommonUtil.changeDateFormat(zeBillHistory.getInvoiceDate()));
					billPaymentHistory[count].setInvoiceNo(zeBillHistory.getInvoiceNo());
					billPaymentHistory[count].setNewCharges(zeBillHistory.getNewCharges().toString());
					billPaymentHistory[count].setPaymentReceived(zeBillHistory.getPaymentreceived().toString());
					billPaymentHistory[count].setKwhTotal(zeBillHistory.getKwhTotal().toString());
					billPaymentHistory[count].setBillPeriodBegin(CommonUtil.changeDateFormat(zeBillHistory.getBillperiodBegin()));
					billPaymentHistory[count].setBillPeriodEnd(CommonUtil.changeDateFormat(zeBillHistory.getBillperiodEnd()));

					if(count==0)
					{
						billPaymentHistory[count].setPreviousBalance("0.0");
					}
					else
					{
						billPaymentHistory[count].setPreviousBalance(amountDue.toString());
					}

				}
				amountDue=zeBillHistory.getAmountDue();
				count++;
			}
		}
		
		if(xiData>0)
		{
			logger.info("XI Data size : "+xiData);
			for(RowInvoiceList row : rows)
			{
				if(row.getResult_Code()!=null && (row.getResult_Code().equals("2") || row.getResult_Code().equals("3")))
					break;
				billPaymentHistory[count] = new BillPaymentHistory();
				billPaymentHistory[count].setAmountDue(row.getTotalAmtDue());
				billPaymentHistory[count].setDueDate(CommonUtil.changeDateFormat(row.getDueDate(),MM_dd_yyyy,yyyyMMdd));
				billPaymentHistory[count].setInvoiceDate(CommonUtil.changeDateFormat(row.getInvoiceDate(),MM_dd_yyyy,yyyyMMdd));
				billPaymentHistory[count].setInvoiceNo(row.getInvoiceNo());
				billPaymentHistory[count].setNewCharges(row.getInvoiceAmt());
				billPaymentHistory[count].setPaymentReceived(row.getCredits());
				billPaymentHistory[count].setKwhTotal(row.getTotalUsage());
				billPaymentHistory[count].setBillPeriodBegin(CommonUtil.changeDateFormat(row.getBillStartDate(),MM_dd_yyyy,yyyyMMdd));
				billPaymentHistory[count].setBillPeriodEnd(CommonUtil.changeDateFormat(row.getBillingToDate(),MM_dd_yyyy,yyyyMMdd));
				billPaymentHistory[count].setPreviousBalance(row.getPreviousBalance());
				count++;
			}
		}
		
		response.setBillPaymentHistory(billPaymentHistory);

		logger.info("HistoryService - getBillPaymenthistory ends");
		return response;
	}
	
	
	/**
	 * @author ahanda1
	 * @param request
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public InvoiceUsageHistoryResponse getInvoiceUsageHistory(BillPaymentHistoryRequestVO request, String sessionId)throws Exception
	{
		logger.info(":::::::::::::::Start HistoryService.getInvoiceUsageHistory::::::::::::::::::::");
		int ccsData = 0;
		String startDate = CommonUtil.changeDateFormat(request.getStartDate(), MM_dd_yyyy, yyyy_MM_dd);
        String endDate = CommonUtil.changeDateFormat(request.getEndDate(), MM_dd_yyyy, yyyy_MM_dd);
        
		
        InvoiceUsageHistoryResponse response = new InvoiceUsageHistoryResponse();
		String imCa = CommonUtil.paddedCa(request.getAccountNumber());
		logger.info("Start Date ::: "+request.getStartDate());
		logger.info("Date from :::: "+CommonUtil.changeDateFormat(request.getStartDate(), MM_dd_yyyy, yyyy_MM_dd));
		logger.info("date form request......"+startDate);
		//sending 2 months old date to ccs
		DateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd);
		/*Date startDate=null;
		Calendar cal = Calendar.getInstance();
		try {
			startDate = dateFormat.parse(dateFrom);
			cal.setTime(startDate);
			cal.add(Calendar.DATE, -60);
			logger.info("Date before 60 days"+cal.getTime());
			
		} catch (ParseException e) {
			logger.error("Exception occurred in parsing date "+e);
		}
		
        String startdate = dateFormat.format(cal.getTime());
		logger.info("start date to be sent to ccs .."+startdate);*/

		URL url = ZEWSBILLPAYMENTHISTORY_Service.class
		.getResource("Z_E_WS_BILL_PAYMENT_HISTORY.wsdl");
		
		ZEWSBILLPAYMENTHISTORY_Service port = new ZEWSBILLPAYMENTHISTORY_Service(url);
		
		ZEWSBILLPAYMENTHISTORY stub = port.getZEWSBILLPAYMENTHISTORY();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(BILL_PAYMENT_HISTORY_ENDPOINT_URL_JNDINAME));
		
		
		javax.xml.ws.Holder<com.nrg.cxfstubs.billpaymenthistory.ZetBillHistory> hTZBillHistory = new javax.xml.ws.Holder<com.nrg.cxfstubs.billpaymenthistory.ZetBillHistory>();
		ZetBillHistory tbZesBillHistory = new ZetBillHistory();
		List<ZesBillHistory> listZesBillHistory = tbZesBillHistory.getItem();
		
		listZesBillHistory.add(new ZesBillHistory());
		hTZBillHistory.value=tbZesBillHistory;
		
		
		javax.xml.ws.Holder<Integer> eReturnCode = new  javax.xml.ws.Holder<Integer>();
		String ccsRequest="imCa="+imCa+",startDate="+startDate+",endDate="+endDate;
		long startTime = CommonUtil.getStartTime();
		stub.zeIsuBillPaymentHistory(imCa,startDate,endDate, eReturnCode,hTZBillHistory);
		
		
		ZetBillHistory tbzebilHistory = hTZBillHistory.value;
		List<ZesBillHistory> zelistBillHistory =  tbzebilHistory.getItem();
		
		utilityloggerHelper.logTransaction("getInvoiceUsageHistory", false, ccsRequest,zelistBillHistory, "", CommonUtil.getElapsedTime(startTime), "", sessionId, request.getCompanyCode());
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(ccsRequest));
			logger.debug(XmlUtil.pojoToXML(zelistBillHistory));
		}
		if(zelistBillHistory!=null && zelistBillHistory.size()>0)
			ccsData = zelistBillHistory.size();
		
		logger.info("Bill History List {}",zelistBillHistory);
		BillPaymentHistory[] billPaymentHistory = new BillPaymentHistory[ccsData];
		int count=0;
		Date actualStartDate = null;
		Date invoiceDate = null;
		BigDecimal amountDue=null;


		actualStartDate = dateFormat.parse(startDate);
		logger.info("Actual start Date "+actualStartDate);

		if(ccsData>0)
		{
			logger.info("CCS Data size : "+ccsData);
			
			/*// setting the ccsDataFlag = X
			logger.info("Setting CCS Data flag = X");
			response.setCcsDataFlag("X");*/
			
			for(ZesBillHistory zeBillHistory:zelistBillHistory)
			{
				invoiceDate=dateFormat.parse(zeBillHistory.getInvoiceDate());
				logger.info("Invoice date "+invoiceDate);
				if(invoiceDate.after(actualStartDate))
				{
					billPaymentHistory[count] = new BillPaymentHistory();
					billPaymentHistory[count].setAmountDue(zeBillHistory.getAmountDue().toString());
					billPaymentHistory[count].setDueDate(CommonUtil.changeDateFormat(zeBillHistory.getDueDate()));
					billPaymentHistory[count].setInvoiceDate(CommonUtil.changeDateFormat(zeBillHistory.getInvoiceDate()));
					billPaymentHistory[count].setInvoiceNo(zeBillHistory.getInvoiceNo());
					// Added for collInvoiceNo field
					billPaymentHistory[count].setCollInvoiceNo(zeBillHistory.getCollInvNo());
					billPaymentHistory[count].setNewCharges(zeBillHistory.getNewCharges().toString());
					billPaymentHistory[count].setPaymentReceived(zeBillHistory.getPaymentreceived().toString());
					billPaymentHistory[count].setKwhTotal(zeBillHistory.getKwhTotal().toString());
					billPaymentHistory[count].setBillPeriodBegin(CommonUtil.changeDateFormat(zeBillHistory.getBillperiodBegin()));
					billPaymentHistory[count].setBillPeriodEnd(CommonUtil.changeDateFormat(zeBillHistory.getBillperiodEnd()));

					if(count==0)
					{
						billPaymentHistory[count].setPreviousBalance("0.0");
					}
					else
					{
						billPaymentHistory[count].setPreviousBalance(amountDue.toString());
					}

				}
				amountDue=zeBillHistory.getAmountDue();
				count++;
			}
		}
		
		
		
		response.setBillPaymentHistory(billPaymentHistory);

		logger.info(":::::::::::::::::::::::::End HistoryService.getInvoiceUsageHistory::::::::::::::::::::::::::::");
		return response;
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param todayDate
	 * @param companyCode
	 * @param brandName
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public PaymentHistoryResponse[] getPaymentHistory(String accountNumber, String endDate, String companyCode,String brandName, String sessionId)
			throws RemoteException {
		logger.info("HistoryService - getPaymentHistory starts");
		long startTime = CommonUtil.getStartTime();
		PaymentHistoryRequest request = new PaymentHistoryRequest();
		request.setStrCANumber(accountNumber);
		request.setStrCompanyCode(companyCode);
		request.setStrHistoryDate(endDate);
		if(brandName != null && brandName.length() > 0)
			request.setStrBrandName(brandName);
		HistoryDomain proxy = getHistoryDomainProxy();
		PaymentHistoryResponse[] response = proxy.getPaymentHistory(request);
		utilityloggerHelper.logTransaction("getPaymentHistoryCCS", false, request,response,"", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		logger.info("HistoryService - getPaymentHistory ends");
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws RemoteException 
	 */
	public ConsumptionHistory getUsageHistoryList(GetUsageHistoryRequest request,String companyCode, String sessionId) throws RemoteException {
		
		logger.info("Start HistoryService.getUsageHistoryList :: START");
		long startTime = CommonUtil.getStartTime();
		HistoryDomain proxy = getHistoryDomainProxy();
		ConsumptionHistory consumptionHistory = proxy.getConsumptionUsageList(request);
		utilityloggerHelper.logTransaction("getUsageHistoryList", false,
				request, consumptionHistory, consumptionHistory.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(consumptionHistory));
		}
		logger.info("HistoryService - getUsageHistoryList ends");
		return consumptionHistory;
		
	}
			
			
		
}
