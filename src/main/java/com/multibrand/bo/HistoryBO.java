package com.multibrand.bo;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.domain.ConsumptionHistory;
import com.multibrand.domain.GetUsageHistoryRequest;
import com.multibrand.domain.UsageHistoryDO;
import com.multibrand.exception.OAMException;
import com.multibrand.helper.UsageHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.service.HistoryService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.util.JAXBUtil;
import com.multibrand.util.XIApacheClient;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.DailyWeeklyUsageRequestVO;
import com.multibrand.vo.request.MonthlyUsageRequestVO;
import com.multibrand.vo.request.SmartMeterUsageRequestVO;
import com.multibrand.vo.request.UsageRequestVO;
import com.multibrand.vo.request.WeeklyUsageRequestVO;
import com.multibrand.vo.request.historyRequest.BillPaymentHistoryRequestVO;
import com.multibrand.vo.request.historyRequest.PlanHistoryRequestVO;
import com.multibrand.vo.request.historyRequest.xi.IntervalDataRequest;
import com.multibrand.vo.request.historyRequest.xi.PaymentHistoryRequest;
import com.multibrand.vo.response.DailyHourlyUsageResponseVO;
import com.multibrand.vo.response.DailyResponseVO;
import com.multibrand.vo.response.DailyUsageResponse;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.HourlyUsageResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageHistory;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.gmd.DailyHourlyPriceResponseVO;
import com.multibrand.vo.response.gmd.GMDZoneByEsiIdResponseVO;
import com.multibrand.vo.response.gmd.HourlyPriceResponse;
import com.multibrand.vo.response.historyResponse.BillPaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.ConsumptionHistoryDO;
import com.multibrand.vo.response.historyResponse.GetConsumptionHistoryResponse;
import com.multibrand.vo.response.historyResponse.IntervalDataResponse;
import com.multibrand.vo.response.historyResponse.InvoiceUsageHistoryResponse;
import com.multibrand.vo.response.historyResponse.PaymentDO;
import com.multibrand.vo.response.historyResponse.PaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.PlanHistoryResponse;
import com.multibrand.vo.response.historyResponse.WeeklyUsageResponse;
import com.multibrand.vo.response.historyResponse.xi.IntervalResponse;
import com.multibrand.vo.response.historyResponse.xi.MTGetIntervalDataResponse;
import com.multibrand.vo.response.historyResponse.xi.MTGetPaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.xi.Row;
import com.multibrand.vo.response.historyResponse.xi.RowIntervalResponse;
import com.multibrand.vo.response.HourlyUsage;

/**
 * 
 * @author smuruga1
 * This Class is used to get the history data 
 */
@Component
public class HistoryBO extends BaseAbstractService implements Constants
{
	@Autowired
	private UsageHelper usageHelper;
	
	@Autowired
	private HistoryService historyService;
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private DateUtil dateUtil;
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param curDtInd
	 * @param curDayInd
	 * @param dyHrInd
	 * @param sessionId
	 * @param CompanyCode
	 * @return
	 * @throws OAMException
	 */
	public GenericResponse getUsage(String accountNumber, String contractId,
			String esid, String zoneId, String curDtInd, String curDayInd,
			String dyHrInd, String sessionId, String companyCode)
			throws OAMException
	{
		
			
		logger.info(" START of the getUsage() Helpermethod");
		UsageRequestVO usageRequestVO = new UsageRequestVO();
		usageRequestVO.setContractAcctId(CommonUtil.paddedCa(accountNumber));
		usageRequestVO.setContractId(contractId);
		usageRequestVO.setEsiId(esid);
		usageRequestVO.setCurDtInd(curDtInd);
		usageRequestVO.setCurDayInd(curDayInd);
		usageRequestVO.setDyHrInd(dyHrInd);
		usageRequestVO.setZoneId(zoneId);
		DailyHourlyUsageResponseVO usageResp = null;
		DailyUsageResponse dailyResponse = null;
		HourlyUsageResponse hourlyResponse = null;
		long startTime = CommonUtil.getStartTime();
		try {

			usageResp = usageHelper.getHourlyUsageFromDB(usageRequestVO,sessionId, companyCode);

			if (usageResp != null && (usageResp.getDailyUsageList() != null
					&& usageResp.getDailyUsageList().size() > 0 )
					) {
				dailyResponse = new DailyUsageResponse();
				dailyResponse.setResultCode(RESULT_CODE_SUCCESS);
				dailyResponse.setResultDescription(MSG_SUCCESS);
				dailyResponse.setDailyUsageList(usageResp.getDailyUsageList());
				utilityloggerHelper.logTransaction("getUsage", false,
						usageRequestVO, dailyResponse,
						dailyResponse.getResultDescription(),
						CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				if(logger.isDebugEnabled()){
					logger.debug(XmlUtil.pojoToXML(usageRequestVO));
					logger.debug(XmlUtil.pojoToXML(dailyResponse));
				}
				logger.info(" END of the getUsage() Helpermethod");
				return dailyResponse;
			} else if(usageResp != null && 
					 usageResp.getHourlyUsageList() != null
					&& usageResp.getHourlyUsageList().size() > 0) {
				hourlyResponse = new HourlyUsageResponse();
				hourlyResponse.setResultCode(RESULT_CODE_SUCCESS);
				hourlyResponse.setResultDescription(MSG_SUCCESS);
				hourlyResponse.setHourlyUsageList(usageResp.getHourlyUsageList());
				utilityloggerHelper.logTransaction("getUsage", false,
						usageRequestVO, hourlyResponse,
						hourlyResponse.getResultDescription(),
						CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				if(logger.isDebugEnabled()){
					logger.debug(XmlUtil.pojoToXML(usageRequestVO));
					logger.debug(XmlUtil.pojoToXML(hourlyResponse));
				}
				logger.info(" END of the getUsage() Helpermethod");
				return hourlyResponse;
			} else {
				if (Constants.DAILY_INDICATOR.equalsIgnoreCase(usageRequestVO.getDyHrInd())) {
					dailyResponse = new DailyUsageResponse();
					dailyResponse.setResultCode(RESULT_CODE_THREE);
					dailyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
					//dailyResponse.setDailyUsageList(usageResp.getDailyUsageList());
					utilityloggerHelper.logTransaction("getUsage", false,
							usageRequestVO, dailyResponse,
							dailyResponse.getResultDescription(),
							CommonUtil.getElapsedTime(startTime), "", sessionId,
							companyCode);
					if(logger.isDebugEnabled()){
						logger.debug(XmlUtil.pojoToXML(usageRequestVO));
						logger.debug(XmlUtil.pojoToXML(dailyResponse));
					}
					logger.info(" END of the getUsage() Helpermethod");
					return dailyResponse;
				} else {
					hourlyResponse = new HourlyUsageResponse();
					hourlyResponse.setResultCode(RESULT_CODE_THREE);
					hourlyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
					//hourlyResponse.setHourlyUsageList(usageResp.getHourlyUsageList());
					utilityloggerHelper.logTransaction("getUsage", false,
							usageRequestVO, hourlyResponse,
							hourlyResponse.getResultDescription(),
							CommonUtil.getElapsedTime(startTime), "", sessionId,
							companyCode);
					if(logger.isDebugEnabled()){
						logger.debug(XmlUtil.pojoToXML(usageRequestVO));
						logger.debug(XmlUtil.pojoToXML(hourlyResponse));
					}
					logger.info(" END of the getUsage() Helpermethod");
					return hourlyResponse;
				}
				
			}
			
			
		} catch (Exception e) {
			logger.error(" Error "+e.getMessage());
			if (Constants.DAILY_INDICATOR.equalsIgnoreCase(usageRequestVO.getDyHrInd())) {
				dailyResponse = new DailyUsageResponse();
				dailyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				dailyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				//dailyResponse.setDailyUsageList(usageResp.getDailyUsageList());
				utilityloggerHelper.logTransaction("getUsage", false,
						usageRequestVO, dailyResponse,
						dailyResponse.getResultDescription(),
						CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				throw new OAMException(200, e.getMessage(), dailyResponse);
			} else {
				hourlyResponse = new HourlyUsageResponse();
				hourlyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				hourlyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
				//hourlyResponse.setHourlyUsageList(usageResp.getHourlyUsageList());
				utilityloggerHelper.logTransaction("getUsage", false,
						usageRequestVO, hourlyResponse,
						hourlyResponse.getResultDescription(),
						CommonUtil.getElapsedTime(startTime), "", sessionId,
						companyCode);
				throw new OAMException(200, e.getMessage(), hourlyResponse);
				
			}
			
			
		}
		
		
	}
	
	
	/**
	 * @author mshukla1
	 * @param accountNumber
	 * @param legacyAccountNumber
	 * @param conversionDate
	 * @param startDate
	 * @param endDate
	 * @param languageCode
	 * @param companyCode
	 * @return
	 */
	public PlanHistoryResponse getPlanHistory(String accountNumber,String legacyAccountNumber,
			String conversionDate,String startDate,String endDate,
			String languageCode,String companyCode, String sessionId){
		
		PlanHistoryResponse response  = new PlanHistoryResponse();
		PlanHistoryRequestVO planHistoryReqVO = new PlanHistoryRequestVO();
		planHistoryReqVO.setAccountNumber(accountNumber);
		planHistoryReqVO.setCompanyCode(companyCode);
		planHistoryReqVO.setConversionDate(conversionDate);
		planHistoryReqVO.setEndDate(CommonUtil.changeDateFormat(endDate, MM_dd_yyyy, yyyy_MM_dd));
		planHistoryReqVO.setLanguageCode(languageCode);
		planHistoryReqVO.setLegacyAccountNumber(legacyAccountNumber);
		planHistoryReqVO.setStartDate(CommonUtil.changeDateFormat(startDate, MM_dd_yyyy, yyyy_MM_dd));
		
		long startTime = CommonUtil.getStartTime();
		try {
			response = historyService.getPlanHistory(planHistoryReqVO, sessionId);
			if(response.getPlanHistory().length>0){
			 response.setResultCode(RESULT_CODE_SUCCESS);
			 response.setResultDescription(MSG_SUCCESS);
			}
			else
			{
				response.setResultCode(RESULT_CODE_NO_DATA);
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			utilityloggerHelper.logTransaction("getPlanHistory", false, planHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(planHistoryReqVO));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		} catch (RemoteException e) {
			logger.info("Remote Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getPlanHistory", false, planHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		catch (Exception ex) {
			logger.info("Exception... "+ex);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getPlanHistory", false, planHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, ex.getMessage(), response);
		}
		
		return response;
   }
	
	/**
	 * 
	 * @param accountNumber
	 * @param legacyAccountNumber
	 * @param conversionDate
	 * @param startDate 
	 * @param endDate
	 * @param companyCode
	 * @return This service is used to fetch the Payment Data in the specified Range.
	 * 
	 */
	public PaymentHistoryResponse getPaymentHistory(String accountNumber, String legacyAccountNumber, String conversionDate, String startDate, String endDate, String companyCode, String sessionId) throws OAMException
	{
		logger.info(" START of the getPaymentHistory() method");
		PaymentHistoryResponse historyResponse = new PaymentHistoryResponse();
		long startTime = CommonUtil.getStartTime();
		DateFormat dateFormat = new SimpleDateFormat(MM_dd_yyyy);
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		int counter = 0;
		List<Row> rows = null;
		int xiData = 0;
		int ccsData = 0;
		String request = "accountNumber="+accountNumber+",legacyAccountNumber="+legacyAccountNumber+",conversionDate="+conversionDate+",startDate="+startDate+",endDate="+endDate+",companyCode="+companyCode;
		try {
			if(conversionDate!=null && !conversionDate.equals("") && legacyAccountNumber!=null && !legacyAccountNumber.equals(""))
			{
				logger.info("fetching data from IRW");
				PaymentHistoryRequest paymentHistoryRequest = new PaymentHistoryRequest();
				paymentHistoryRequest.setAccountId(legacyAccountNumber);
				paymentHistoryRequest.setStartDate(CommonUtil.changeDateFormat(startDate, MM_dd_yyyy, yyyy_MM_dd));
				paymentHistoryRequest.setStopDate(CommonUtil.changeDateFormat(endDate, MM_dd_yyyy, yyyy_MM_dd));
				String resp = XIApacheClient.getResponseBody(this.envMessageReader.getMessage(XI_HISTORY_ENDPOINT_URL)+XI_PAYMENT_HISTORY_ENDPOINT_URL_QUERY_PARAM, paymentHistoryRequest, null);		
				logger.info("Response::"+resp);
				MTGetPaymentHistoryResponse object = (MTGetPaymentHistoryResponse)JAXBUtil.unmarshal(resp, "com.multibrand.vo.response.historyResponse.xi.MTGetPaymentHistoryResponse");
				utilityloggerHelper.logTransaction("getPaymentHistoryXI", false, paymentHistoryRequest,object, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
				if(logger.isDebugEnabled()){
					logger.debug(XmlUtil.pojoToXML(paymentHistoryRequest));
					logger.debug(XmlUtil.pojoToXML(object));
				}
				rows = object.getPaymentHistoryResponse().getRow();
				if(rows!=null && rows.size()>0)
					xiData = rows.size();
			}	
			logger.info("fetching data from CCS");
			String paymentDate = "";
			boolean ccsError = false;
			boolean irwError = false;
			com.multibrand.domain.PaymentHistoryResponse[] domainHistoryResponse = null;
			logger.info("CCS inputs : accountNumber : "+accountNumber+" todayDate : "+todayDate+" companyCode : "+companyCode);
			domainHistoryResponse = historyService.getPaymentHistory(accountNumber, todayDate, companyCode, null, sessionId);
			if(domainHistoryResponse!=null && domainHistoryResponse.length>0)
				ccsData = domainHistoryResponse.length;
			if(ccsData>0 || xiData>0)
			{
				PaymentDO[] paymentDO = new PaymentDO[ccsData+xiData];
				DateFormat df = new SimpleDateFormat(yyyyMMdd);
				if(ccsData>0)
				{
					logger.info("CCS size : "+ccsData);
					for(com.multibrand.domain.PaymentHistoryResponse paymentHistoryResponse:domainHistoryResponse)
					{
						if(paymentHistoryResponse.getErrCode()!=null && paymentHistoryResponse.getErrCode().trim().contains("MSG_CCSERR"))
						{
							logger.info("error true");
							ccsError= true;
							paymentDO = new PaymentDO[0];
							break;
						}
						paymentDO[counter] = new PaymentDO();
						if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(CANCELLED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(REVERSE)))
						{
							paymentDate = df.format(paymentHistoryResponse.getStrPaymentReversalDate().getTime());
							paymentDO[counter].setPaymentDate(paymentDate);
						}
						else
						{
							paymentDate = df.format(paymentHistoryResponse.getStrScheduledPaymentDate().getTime());
							paymentDO[counter].setPaymentDate(paymentDate);
						}
						paymentDO[counter].setPaymentAmount(paymentHistoryResponse.getStrPaymentAmt());
						if(paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("I"))
						{
							paymentDO[counter].setChannel("Website");
						}
						else if((paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("T")))
						{
							paymentDO[counter].setChannel("Phone");
						}
						else if(paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("X") || (paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("O")))
						{
							paymentDO[counter].setChannel("Other");
						}
						if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(SENT)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase("")))
						{
							paymentDO[counter].setStatus("Paid");
						}
						else if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(CANCELLED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(REVERSE)))
						{
							paymentDO[counter].setStatus("Cancelled");
						}
						else if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(POSTED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(RETRY)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(SCHEDULED)))
						{
							paymentDO[counter].setStatus("Pending");
						}
						else if(paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(DECLINED))
						{
							paymentDO[counter].setStatus("Declined");
						}
												
						counter++;
					}
				}
				if(xiData>0)
				{
					logger.info("IRW data size : "+xiData);
					if(ccsError)
						paymentDO = new PaymentDO[xiData];
					for(Row row : rows)
					{
						if(row.getResult_Code()!=null && (row.getResult_Code().equals("2") || row.getResult_Code().equals("3")))
						{
							irwError= true;
							break;
						}
						paymentDO[counter] = new PaymentDO();
						if(row.getLast_Payment_Date()!=null)
						{
							String[] strPaymentDateArray = row.getLast_Payment_Date().split(" ");
							paymentDO[counter].setPaymentDate(CommonUtil.changeDateFormat(strPaymentDateArray[0]));
						}
						else
							paymentDO[counter].setPaymentDate("");
						paymentDO[counter].setPaymentAmount(row.getLast_Payment_Amount());
						paymentDO[counter].setChannel("");
						paymentDO[counter].setStatus("");
						counter++;
					}
				}
				if(ccsError && irwError)
				{
					historyResponse.setPaymentDO(paymentDO);
					historyResponse.setResultCode(RESULT_CODE_NO_DATA);
					historyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				}
				else
				{
					historyResponse.setPaymentDO(paymentDO);
					historyResponse.setResultCode(RESULT_CODE_SUCCESS);
					historyResponse.setResultDescription(MSG_SUCCESS);
				}
			}
			else
			{
				historyResponse.setResultCode(RESULT_CODE_NO_DATA);
				historyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

			utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
            if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(request));
				logger.debug(XmlUtil.pojoToXML(historyResponse));
            }
		} catch (RemoteException e) {
			historyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			historyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), historyResponse);
		}
		catch (Exception e) {
			historyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			historyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), historyResponse);
		}
		return historyResponse;
	}

	/**
	 * @author Kdeshmu1
	 * @param accountNumber
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	public BillPaymentHistoryResponse getBillPaymentHistory(String accountNumber,String legacyAccountNumber, String conversionDate,
			String startDate,String endDate,String companyCode, String sessionId){
		
		BillPaymentHistoryResponse response  = new BillPaymentHistoryResponse();
		BillPaymentHistoryRequestVO billPaymentHistoryReqVO = new BillPaymentHistoryRequestVO();
		billPaymentHistoryReqVO.setAccountNumber(accountNumber);
		billPaymentHistoryReqVO.setLegacyAccountNumber(legacyAccountNumber);
		billPaymentHistoryReqVO.setConversionDate(conversionDate);
		billPaymentHistoryReqVO.setEndDate(endDate);
		billPaymentHistoryReqVO.setStartDate(startDate);
		
		long startTime = CommonUtil.getStartTime();
		try {
			response = historyService.getBillPaymentHistory(billPaymentHistoryReqVO, sessionId);
			if(response.getBillPaymentHistory().length>0){
			 response.setResultCode(RESULT_CODE_SUCCESS);
			 response.setResultDescription(MSG_SUCCESS);
			}
			else
			{
				response.setResultCode(RESULT_CODE_NO_DATA);
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			utilityloggerHelper.logTransaction("getBillPaymentHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(billPaymentHistoryReqVO));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		} catch (RemoteException e) {
			logger.info("Remote Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getBillPaymentHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		catch (Exception e) {
			logger.info("Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getBillPaymentHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
   }
	
	/**
	 * 
	 * @param accountNumber
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param sessionId
	 * @param brandName
	 * @return
	 */
	public InvoiceUsageHistoryResponse getInvoiceUsageHistory(String accountNumber,String startDate,String endDate,String companyCode, String sessionId, String brandName){
		
		InvoiceUsageHistoryResponse response  = new InvoiceUsageHistoryResponse();
		BillPaymentHistoryRequestVO billPaymentHistoryReqVO = new BillPaymentHistoryRequestVO();
		billPaymentHistoryReqVO.setAccountNumber(accountNumber);
		//billPaymentHistoryReqVO.setLegacyAccountNumber(legacyAccountNumber);
		//billPaymentHistoryReqVO.setConversionDate(conversionDate);
		billPaymentHistoryReqVO.setEndDate(endDate);
		billPaymentHistoryReqVO.setStartDate(startDate);
		
		long startTime = CommonUtil.getStartTime();
		try {
			response = historyService.getInvoiceUsageHistory(billPaymentHistoryReqVO, sessionId);
			if(response.getBillPaymentHistory().length>0){
			 response.setResultCode(RESULT_CODE_SUCCESS);
			 response.setResultDescription(MSG_SUCCESS);
			}
			else
			{
				response.setResultCode(RESULT_CODE_NO_DATA);
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			utilityloggerHelper.logTransaction("getInvoiceUsageHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(billPaymentHistoryReqVO));
				logger.debug(XmlUtil.pojoToXML(response));
			}
		} catch (RemoteException e) {
			logger.info("Remote Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getInvoiceUsageHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		catch (Exception e) {
			logger.info("Exception "+e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getInvoiceUsageHistory", false, billPaymentHistoryReqVO,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		
		return response;
   }
	
	/**
	 * @author dpethap1
	 * @param accountNumber
	 * @param companyCode
	 * @return
	 */
	public WeeklyUsageResponseList getWeeklyUsageDetails(String accountNumber, 
			String contractId, String esiId,String zoneId,String curDate,
			String companyCode,String sessionId)
	{
		logger.info("START-[HistoryBO-getWeeklyUsageDetails]");
		long startTime = CommonUtil.getStartTime();
		WeeklyUsageRequestVO weeklyUsageReq = new WeeklyUsageRequestVO();
		WeeklyUsageResponseList weeklyUsageRespList = null;
		weeklyUsageReq.setContractAccountNumber(CommonUtil.paddedCa(accountNumber));
		weeklyUsageReq.setContractId(contractId);
		weeklyUsageReq.setEsiId(esiId);
		weeklyUsageReq.setZoneId(zoneId);
		weeklyUsageReq.setCurDate(curDate);
		weeklyUsageReq.setCompanyCode(companyCode);
		
		
		try {
			weeklyUsageRespList = usageHelper.getWeeklyUsageDetails(weeklyUsageReq,companyCode,sessionId);

			if (weeklyUsageRespList != null
					&& (weeklyUsageRespList.getWeeklyUsageResponse() != null && weeklyUsageRespList
							.getWeeklyUsageResponse().size() > 0)) {
				weeklyUsageRespList.setResultCode(RESULT_CODE_SUCCESS);
				weeklyUsageRespList.setResultDescription(MSG_SUCCESS);

			} else {
				weeklyUsageRespList = new WeeklyUsageResponseList();
				weeklyUsageRespList.setResultCode(RESULT_CODE_NO_DATA);
				weeklyUsageRespList
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getWeeklyUsageDetails"
					+ e.getMessage());
			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error "+e.getMessage());
			utilityloggerHelper.logTransaction("getWeeklyUsageDetails", false,
					weeklyUsageReq, e,
					"",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
			    logger.info(XmlUtil.pojoToXML(weeklyUsageReq));	
			
			weeklyUsageRespList = new WeeklyUsageResponseList();
			weeklyUsageRespList.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			weeklyUsageRespList.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), weeklyUsageRespList);
		}
		//commenting this logging as DAO layer has already done the utility logging for this DB call
		
		/*utilityloggerHelper.logTransaction("getWeeklyUsageDetails", false,
				weeklyUsageReq, weeklyUsageRespList,
				weeklyUsageRespList.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		logger.info(XmlUtil.pojoToXML(weeklyUsageReq));
		logger.info(XmlUtil.pojoToXML(weeklyUsageRespList));
		*/logger.info("END-[HistoryBO-getWeeklyUsageDetails]");
		return weeklyUsageRespList;
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esiId
	 * @param zoneId
	 * @param curBillDt
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public MonthlyUsageResponseList getMonthlyUsageDetails(String accountNumber, 
			String contractId, String esiId,String zoneId,String curDate,
			String companyCode,String sessionId)
	{
		logger.info("START-[HistoryBO-getMonthlyUsageDetails]");
		long startTime = CommonUtil.getStartTime();
		MonthlyUsageRequestVO monthlyUsageReq = new MonthlyUsageRequestVO();
		MonthlyUsageResponseList monthlyUsageResp = null;
		monthlyUsageReq.setContractAccountId(CommonUtil.paddedCa(accountNumber));
		monthlyUsageReq.setContractId(contractId);
		monthlyUsageReq.setEsiid(esiId);
		monthlyUsageReq.setZoneId(zoneId);
		monthlyUsageReq.setCurDate(curDate);
		monthlyUsageReq.setCompanyCode(companyCode);
		
		try {
			monthlyUsageResp = usageHelper.getMonthlyUsageDetails(monthlyUsageReq, companyCode, sessionId);

			if (monthlyUsageResp != null
					&& (monthlyUsageResp.getMonthlyUsageResponse() != null && monthlyUsageResp
							.getMonthlyUsageResponse().size() > 0)) {
				monthlyUsageResp.setResultCode(RESULT_CODE_SUCCESS);
				monthlyUsageResp.setResultDescription(MSG_SUCCESS);

			} else {
				monthlyUsageResp = new MonthlyUsageResponseList();
				monthlyUsageResp.setResultCode(RESULT_CODE_NO_DATA);
				monthlyUsageResp
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

			//JavaBeanUtil.copy(projBillResp, projBillResp);
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getMonthlyUsageDetails"
					+ e.getMessage());
			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error "+e.getMessage());
			utilityloggerHelper.logTransaction("getMonthlyUsageDetails", false,
					monthlyUsageReq, e,
					"",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(monthlyUsageReq));
			monthlyUsageResp = new MonthlyUsageResponseList();
			monthlyUsageResp.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			monthlyUsageResp.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), monthlyUsageResp);
		}
		
		// commenting this logging as DAO layer has already done the utility logging for this DB call
		/*utilityloggerHelper.logTransaction("getMonthlyUsageDetails", false,
				monthlyUsageReq, monthlyUsageResp,
				monthlyUsageResp.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		logger.info(XmlUtil.pojoToXML(monthlyUsageReq));
		logger.info(XmlUtil.pojoToXML(monthlyUsageResp));*/
		
		logger.info("END-[HistoryBO-getMonthlyUsageDetails]");
		return monthlyUsageResp;
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esiId
	 * @param zoneId
	 * @param wkDtInV
	 * @param curWkInV
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public DailyWeeklyUsageResponseList getDailyWeeklyUsageDetails(String accountNumber, 
			String contractId, String esiId, String zoneId, String wkDtInV, String curWkInV,
			String companyCode,String sessionId)
	{
		logger.info("START-[HistoryBO-getDailyWeeklyUsageDetails]");
		long startTime = CommonUtil.getStartTime();
		DailyWeeklyUsageRequestVO dailyWeeklyUsageReq = new DailyWeeklyUsageRequestVO();
		DailyWeeklyUsageResponseList dailyWeeklyUsageRespList = null;
		dailyWeeklyUsageReq.setContractAccountNumber(CommonUtil.paddedCa(accountNumber));
		dailyWeeklyUsageReq.setContractId(contractId);
		dailyWeeklyUsageReq.setEsiId(esiId);
		dailyWeeklyUsageReq.setZoneId(zoneId);
		dailyWeeklyUsageReq.setWkDtInV(wkDtInV);
		dailyWeeklyUsageReq.setCurWkInV(curWkInV);
		dailyWeeklyUsageReq.setCompanyCode(companyCode);
		
		
		try {
			dailyWeeklyUsageRespList = usageHelper.getDailyWeeklyUsageDetails(dailyWeeklyUsageReq,companyCode,sessionId);

			if (dailyWeeklyUsageRespList != null
					&& (dailyWeeklyUsageRespList.getDailyWeeklyUsageResponse() != null && dailyWeeklyUsageRespList
							.getDailyWeeklyUsageResponse().size() > 0)) {
				dailyWeeklyUsageRespList.setResultCode(RESULT_CODE_SUCCESS);
				dailyWeeklyUsageRespList.setResultDescription(MSG_SUCCESS);

			} else {
				dailyWeeklyUsageRespList = new DailyWeeklyUsageResponseList();
				dailyWeeklyUsageRespList.setResultCode(RESULT_CODE_NO_DATA);
				dailyWeeklyUsageRespList
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getDailyWeeklyUsageDetails"
					+ e.getMessage());
			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error "+e.getMessage());
			utilityloggerHelper.logTransaction("getDailyWeeklyUsageDetails", false,
					dailyWeeklyUsageReq, e,
					"",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(dailyWeeklyUsageReq));
			
			dailyWeeklyUsageRespList = new DailyWeeklyUsageResponseList();
			dailyWeeklyUsageRespList.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			dailyWeeklyUsageRespList.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), dailyWeeklyUsageRespList);
		}
		//commenting this logging as DAO layer has already done the utility logging for this DB call
		/*utilityloggerHelper.logTransaction("getDailyWeeklyUsageDetails", false,
				dailyWeeklyUsageReq, dailyWeeklyUsageRespList,
				dailyWeeklyUsageRespList.getResultDescription(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		logger.info(XmlUtil.pojoToXML(dailyWeeklyUsageReq));
		logger.info(XmlUtil.pojoToXML(dailyWeeklyUsageRespList));
		*/logger.info("END-[HistoryBO-getDailyWeeklyUsageDetails]");
		return dailyWeeklyUsageRespList;
	}
	
	/**
	 * 
	 * @param servicePointId
	 * @param accountNumber
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public SmartMeterUsageResponseList getSmartMeterUsageHistory(
			 String esid,
			 String accountNumber,
			 String startDate,
			 String endDate,
			 String companyCode,
			 String sessionId) {
		logger.info("START-[HistoryBO-getSmartMeterUsageHistory]");
		long startTime = CommonUtil.getStartTime();
		SmartMeterUsageResponseList responseList = null;
		SmartMeterUsageRequestVO requestVO = new SmartMeterUsageRequestVO();
		requestVO.setAccountNumber(accountNumber);
		requestVO.setServicePointId(esid);
		requestVO.setStartDate(startDate);
		requestVO.setEndDate(endDate);
		
		try {
			
			responseList = usageHelper.getSmartMeterUsageHistory(requestVO,
					companyCode, sessionId);
			logger.info(" Exiting from the DAO Layer-[HistoryBO-getSmartMeterUsageHistory]");
			
			if (responseList != null
					&& (responseList.getSmartMeterUsageHistoryList() != null && responseList
							.getSmartMeterUsageHistoryList().size() > 0)) {
				responseList.setResultCode(RESULT_CODE_SUCCESS);
				responseList.setResultDescription(MSG_SUCCESS);

			} else {
				responseList = new SmartMeterUsageResponseList();
				responseList.setSmartMeterUsageHistoryList(new LinkedList<SmartMeterUsageHistory>());
				responseList.setResultCode(RESULT_CODE_NO_DATA);
				responseList
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			
		} catch (Exception e) {
			logger.info(" Exeception Occured in the getSmartMeterUsageHistory"
					+ e.getMessage());
			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error "+e.getMessage());
			utilityloggerHelper.logTransaction("getSmartMeterUsageHistory", false,
					requestVO, e,
					"",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(requestVO));
			
			responseList = new SmartMeterUsageResponseList();
			responseList.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			responseList.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), responseList);
		}
		
		return responseList;
	}
	
	
	/**
	 * @author ahanda1
	 * @param esid
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
public IntervalDataResponse getIntervalData(String esid, String startDate, String endDate, String companyCode, String sessionId){
		
		IntervalDataResponse response = new IntervalDataResponse();
		
		IntervalDataRequest intervalDataRequest = new IntervalDataRequest();
		intervalDataRequest.setEsid(esid);
		intervalDataRequest.setStartDate(CommonUtil.changeDateFormat(startDate, MM_dd_yyyy, MMddyyyy));
		intervalDataRequest.setEndDate(CommonUtil.changeDateFormat(endDate, MM_dd_yyyy, MMddyyyy));
		long startTime = CommonUtil.getStartTime();
		try{
		String resp = XIApacheClient.getResponseBody(this.envMessageReader.getMessage(XI_HISTORY_ENDPOINT_URL)+XI_INTERVAL_DATA_ENDPOINT_URL_QUERY_PARAM, intervalDataRequest, null);		
		logger.info("Response::"+resp);
		MTGetIntervalDataResponse object = (MTGetIntervalDataResponse)JAXBUtil.unmarshal(resp, "com.multibrand.vo.response.historyResponse.xi.MTGetIntervalDataResponse");
		utilityloggerHelper.logTransaction("getIntervalDataXI", false, intervalDataRequest,object, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(intervalDataRequest));
			logger.debug(XmlUtil.pojoToXML(object));			
		}
		
		// first formatting the consumption dates in all rows
		IntervalResponse intervalResp = object.getIntervalResponse();
		    List<RowIntervalResponse> rowList = null;
		if(intervalResp!= null) {
			 rowList =intervalResp.getRow();
		}
		if(rowList!=null){
			for(RowIntervalResponse row : rowList){
				String consumptionDate = row.getConsumptionDate();
				row.setConsumptionDate(CommonUtil.changeDateFormat(consumptionDate.substring(0, 10)) );
			}
		}
		
		// setting the internal data from XI into main API response
		response.setIntervalResponse(intervalResp);
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception :::: " + e);
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			utilityloggerHelper.logTransaction("getIntervalDataXI", false, intervalDataRequest,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new OAMException(200, e.getMessage(), response);
		}
		return response;
		
	}
/**
 * @author kdeshmu1
 * @param accountNumber
 * @param companyCode
 * @param brandName
 * @param sessionId
 * @return
 * @throws OAMException
 */
public PaymentHistoryResponse fetchPaymentHistory(String accountNumber,String startDate,String endDate,String companyCode, String brandName,String sessionId) throws OAMException
{
	logger.info(" START of the getPaymentHistory() method");
	PaymentHistoryResponse historyResponse = new PaymentHistoryResponse();
	long startTime = CommonUtil.getStartTime();
	DateFormat dateFormat = new SimpleDateFormat(MM_dd_yyyy);
	Date date = new Date();
	String todayDate = dateFormat.format(date);
	int counter = 0;
	
	int ccsData = 0;
	String request = "accountNumber="+accountNumber+",companyCode="+companyCode+",brandName="+brandName;
	try {
		
		logger.info("fetching data from CCS");
		String paymentDate = "";
		boolean ccsError = false;
		
		com.multibrand.domain.PaymentHistoryResponse[] domainHistoryResponse = null;
		logger.info("CCS inputs : accountNumber : "+accountNumber+" endDate : "+endDate+" companyCode : "+companyCode+" brandName : "+brandName);
		domainHistoryResponse = historyService.getPaymentHistory(accountNumber, endDate, companyCode,brandName, sessionId);
		if(domainHistoryResponse!=null && domainHistoryResponse.length>0)
			ccsData = domainHistoryResponse.length;
		if(ccsData>0 )
		{
			PaymentDO[] paymentDO = new PaymentDO[ccsData];
			DateFormat df = new SimpleDateFormat(yyyyMMdd);
			if(ccsData>0)
			{
				logger.info("CCS size : "+ccsData);
				for(com.multibrand.domain.PaymentHistoryResponse paymentHistoryResponse:domainHistoryResponse)
				{
					if(paymentHistoryResponse.getErrCode()!=null && paymentHistoryResponse.getErrCode().trim().contains("MSG_CCSERR"))
					{
						logger.info("error true");
						ccsError= true;
						paymentDO = new PaymentDO[0];
						break;
					}
					paymentDO[counter] = new PaymentDO();
					if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(CANCELLED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(REVERSE)))
					{
						paymentDate = df.format(paymentHistoryResponse.getStrPaymentReversalDate().getTime());
						paymentDO[counter].setPaymentDate(paymentDate);
					}
					else
					{
						paymentDate = df.format(paymentHistoryResponse.getStrScheduledPaymentDate().getTime());
						paymentDO[counter].setPaymentDate(paymentDate);
					}
					String subDate = df.format(paymentHistoryResponse.getStrPaymentSubmissionDate().getTime());
					paymentDO[counter].setPaymentSubmissionDate(subDate);
					paymentDO[counter].setPaymentAmount(paymentHistoryResponse.getStrPaymentAmt());
					if(paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("I"))
					{
						paymentDO[counter].setChannel("Website");
					}
					else if((paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("T")))
					{
						paymentDO[counter].setChannel("Phone");
					}
					else if(paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("X") || (paymentHistoryResponse.getStrChannel()!=null && paymentHistoryResponse.getStrChannel().equalsIgnoreCase("O")))
					{
						paymentDO[counter].setChannel("Other");
					}
					if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(SENT)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase("")))
					{
						paymentDO[counter].setStatus("Paid");
					}
					else if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(CANCELLED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(REVERSE)))
					{
						paymentDO[counter].setStatus("Cancelled");
					}
					else if((paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(POSTED)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(RETRY)) || (paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(SCHEDULED)))
					{
						paymentDO[counter].setStatus("Pending");
					}
					else if(paymentHistoryResponse.getStrPaymentStatus()!=null && paymentHistoryResponse.getStrPaymentStatus().equalsIgnoreCase(DECLINED))
					{
						paymentDO[counter].setStatus("Declined");
					}
					
					// Cirro payment history changes
					if(paymentHistoryResponse.getStrPaymentId()!=null)
					{							
						paymentDO[counter].setPaymentId(paymentHistoryResponse.getStrPaymentId());
					}
					if(paymentHistoryResponse.getStrBankAccntNum()!=null)
					{							
						paymentDO[counter].setBankNumber(paymentHistoryResponse.getStrBankAccntNum());
						paymentDO[counter].setRoutingNumber(paymentHistoryResponse.getStrBankRoutingNum());
					}
					if(paymentHistoryResponse.getStrCCType()!=null)
					{							
						paymentDO[counter].setCcType(paymentHistoryResponse.getStrCCType());
					}
					if(paymentHistoryResponse.getStrCCNumber()!=null)
					{							
						paymentDO[counter].setCcNumber(paymentHistoryResponse.getStrCCNumber());
					}
					// Cirro payment history changes
					
					counter++;
				}
			}
			
			if(ccsError)
			{
				historyResponse.setPaymentDO(paymentDO);
				historyResponse.setResultCode(RESULT_CODE_NO_DATA);
				historyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}
			else
			{
				historyResponse.setPaymentDO(paymentDO);
				historyResponse.setResultCode(RESULT_CODE_SUCCESS);
				historyResponse.setResultDescription(MSG_SUCCESS);
			}
		}
		else
		{
			historyResponse.setResultCode(RESULT_CODE_NO_DATA);
			historyResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
		}

		utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);

		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(historyResponse));
		}
	} catch (RemoteException e) {
		historyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		historyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		throw new OAMException(200, e.getMessage(), historyResponse);
	}
	catch (Exception e) {
		historyResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
		historyResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		utilityloggerHelper.logTransaction("getPaymentHistory", false, request,historyResponse, historyResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		throw new OAMException(200, e.getMessage(), historyResponse);
	}
	return historyResponse;
}

	/**
	 * 
	 * @param contractId
	 * @param noOfMonths
	 * @param brandName
	 * @param companyCode
	 * @return
	 */
	public GetConsumptionHistoryResponse getConsumptionUsage(String contractId, String noOfMonths,
			String brandName, String companyCode, String sessionId)
	{
		logger.info("History - getConsumptionUsage method starts...");
		
		GetConsumptionHistoryResponse response = new GetConsumptionHistoryResponse();
		GetUsageHistoryRequest request = new GetUsageHistoryRequest();
		
		request.setBrandName(brandName);
		request.setCompanyCode(companyCode);
		request.setContractId(contractId);
		request.setNoOfMonths(noOfMonths);
		
		
		try {
			ConsumptionHistory serviceResponse = historyService
					.getUsageHistoryList(request, companyCode, sessionId);
			
			System.out.println("After call from the "+serviceResponse.getErrorCode());
			
			if (serviceResponse != null
					&& (serviceResponse.getErrorCode() == null || serviceResponse
							.getErrorCode().equals(""))) {
				logger.info("HistoryBO -getConsumptionUsage  After Service method .IF LOOP..");
				List<ConsumptionHistoryDO> usageConsumptionList = new LinkedList<ConsumptionHistoryDO>();
				UsageHistoryDO[] tempUsageHistoryList = serviceResponse.getUsageHistoryList();
				response.setUsageConsumptionList(usageConsumptionList);
				
				if(tempUsageHistoryList != null) {
					for(int i=0; i<tempUsageHistoryList.length; i++) {
						UsageHistoryDO tempUsageHistory = tempUsageHistoryList[i];
						ConsumptionHistoryDO consumptionHistoryDO = new ConsumptionHistoryDO();
						consumptionHistoryDO.setConsumption(tempUsageHistory.getConsumption());
						consumptionHistoryDO.setContractId(tempUsageHistory.getContractId());
						if(tempUsageHistory.getPeriodEndDate()!=null && !tempUsageHistory.getPeriodEndDate().equals("") && !tempUsageHistory.getPeriodEndDate().equals("0000-00-00"))
							consumptionHistoryDO.setPeriodEndDate(CommonUtil.changeDateFormat(tempUsageHistory.getPeriodEndDate(),DT_AVGTEMP_FMT, DT_FMT));
						if(tempUsageHistory.getPeriodStartDate()!=null && !tempUsageHistory.getPeriodStartDate().equals("") && !tempUsageHistory.getPeriodStartDate().equals("0000-00-00"))
							consumptionHistoryDO.setPeriodStartDate(CommonUtil.changeDateFormat(tempUsageHistory.getPeriodStartDate(),DT_AVGTEMP_FMT, DT_FMT));
						usageConsumptionList.add(consumptionHistoryDO);
					}
				}
				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);
				
			} else {
				logger.info("HistoryBO -getConsumptionUsage - After Service method .ELSE LOOP..");
				response.setResultCode(RESULT_CODE_NO_DATA);
				response
						.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
				List<ConsumptionHistoryDO> usageConsumptionList = new LinkedList<ConsumptionHistoryDO>();
				response.setUsageConsumptionList(usageConsumptionList);
			}
			
		} catch (Exception e) {
			long startTime = CommonUtil.getStartTime();
			logger.error("Exception getConsumptionUsage" + e);
			System.out.println("getConsumptionUsage"+e.getStackTrace().toString());
			utilityloggerHelper.logTransaction("getCirroStructureCall", false, request,
					e, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId,
					companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			List<ConsumptionHistoryDO> usageConsumptionList = new LinkedList<ConsumptionHistoryDO>();
			response.setUsageConsumptionList(usageConsumptionList);
		}

		return response;
	}
	
	/**
	 * This method returns weekly usage data for the given week number & year
	 * @author NGASPerera
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param companyCode
	 * @param brandName
	 * @param weekNumber
	 * @param year
	 * @param sessionId
	 * @return WeeklyUsageResponse
	 */
	public WeeklyUsageResponse getWeeklyUsageData(String accountNumber, String contractId, String esid, String zoneId,
			String companyCode, String brandName, int weekNumber, int year, String sessionId) {

		WeeklyUsageResponse weeklyUsageResponse = new WeeklyUsageResponse();
		List<DailyResponseVO> dailyUsageListForSameMonth = new ArrayList<>();
		List<DailyResponseVO> dailyUsageListForPreviousMonth = new ArrayList<>();
		List<DailyResponseVO> dailyUsageListForNextMonth = new ArrayList<>();
		Set<DailyResponseVO> weeklyUsageData = new HashSet<>();

		try {
			UsageRequestVO usageReq = new UsageRequestVO();
			usageReq.setContractAcctId(CommonUtil.paddedCa(accountNumber));
			usageReq.setContractId(contractId);
			usageReq.setEsiId(esid);
			usageReq.setCurDayInd(Constants.CURRENT_DAY_INDICATOR_N);
			usageReq.setDyHrInd(Constants.DAILY_HOUR_INDICATOR_D);
			usageReq.setZoneId(zoneId);

			// get start date and end date of the week
			// GME starts week by Sunday, because of that parameter passed as 1
			Date startDate = dateUtil.getStartingDateOftheWeek(year, weekNumber, 1);
			// Need to get weekly data, because of that parameter passed as 6 to
			// get 7 days
			Date endDate = dateUtil.addNumberOfDays(startDate, 6);

			/**
			 * to check weather dates of week belongs to same month , previous
			 * month or next month
			 */
			int monthComparison = dateUtil.compareMonths(startDate, endDate);
			// Dates belongs to same month
			if (monthComparison == 0) {
				dailyUsageListForSameMonth = getUsageDataForMonth(usageReq, sessionId, companyCode, startDate);
				weeklyUsageData = getDailyUsagesByDate(dailyUsageListForSameMonth, startDate, endDate);
				weeklyUsageResponse.setWeeklyUsageData(weeklyUsageData);
			}
			// Dates belongs to previous month also
			if (monthComparison > 0) {
				dailyUsageListForPreviousMonth = getUsageDataForMonth(usageReq, sessionId, companyCode, startDate);
				dailyUsageListForSameMonth = getUsageDataForMonth(usageReq, sessionId, companyCode, endDate);
				dailyUsageListForPreviousMonth.addAll(dailyUsageListForSameMonth);
				weeklyUsageData = getDailyUsagesByDate(dailyUsageListForPreviousMonth, startDate, endDate);
				weeklyUsageResponse.setWeeklyUsageData(weeklyUsageData);
			}
			// Dates belongs to next month also
			if (monthComparison < 0) {
				dailyUsageListForSameMonth = getUsageDataForMonth(usageReq, sessionId, companyCode, startDate);
				dailyUsageListForNextMonth = getUsageDataForMonth(usageReq, sessionId, companyCode, endDate);
				dailyUsageListForSameMonth.addAll(dailyUsageListForNextMonth);
				weeklyUsageData = getDailyUsagesByDate(dailyUsageListForSameMonth, startDate, endDate);
				weeklyUsageResponse.setWeeklyUsageData(weeklyUsageData);
			}

			if (weeklyUsageResponse.getWeeklyUsageData() != null
					&& weeklyUsageResponse.getWeeklyUsageData().size() > 0) {
				if (weeklyUsageResponse.getWeeklyUsageData().size() > 1
						&& weeklyUsageResponse.getWeeklyUsageData().size() < 7) {
					weeklyUsageData = createDummyData(usageReq, weeklyUsageResponse.getWeeklyUsageData(), startDate,
							endDate);
					weeklyUsageResponse.setWeeklyUsageData(weeklyUsageData);
				}
				weeklyUsageResponse.setResultCode(Constants.RESULT_CODE_SUCCESS);
				weeklyUsageResponse.setResultDescription(Constants.MSG_SUCCESS);
			} else {
				weeklyUsageData = createDummyDataForWeek(usageReq, startDate, endDate);
				weeklyUsageResponse.setWeeklyUsageData(weeklyUsageData);
			}
		} catch (Exception e) {
			logger.info("Exception Occured : " + e);
			weeklyUsageResponse.setErrorCode(Constants.RESULT_CODE_EXCEPTION_FAILURE);
			weeklyUsageResponse.setErrorDescription(Constants.RESULT_DESCRIPTION_EXCEPTION);
		}
		return weeklyUsageResponse;
	}	
	
	/**
	 * This method returns usage data for a time period
	 * @author NGASPerera
	 * @param monthlyUsageData
	 * @param startDate
	 * @param endDate
	 * @return Set<DailyResponseVO>
	 */
	public Set<DailyResponseVO> getDailyUsagesByDate(List<DailyResponseVO> monthlyUsageData, Date startDate,
			Date endDate) {
		Set<DailyResponseVO> dailyUsageData = new LinkedHashSet<>();
		for (DailyResponseVO dailyResponse : monthlyUsageData) {
			if (dateUtil.getDate(dailyResponse.getActualDay()).compareTo(startDate) >= 0
					&& dateUtil.getDate(dailyResponse.getActualDay()).compareTo(endDate) <= 0) {
				dailyUsageData.add(dailyResponse);
			}
		}
		return dailyUsageData;
	}
	
	/**
	 * This method return usage data for month
	 * @author NGASPerera
	 * @param usageReq DailyResponseVO
	 * @param sessionId
	 * @param companyCode
	 * @param monthDate
	 * @return List<DailyResponseVO>
	 */
	public List<DailyResponseVO> getUsageDataForMonth(UsageRequestVO usageReq, String sessionId, String companyCode,
			Date monthDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.MM_dd_yyyy);
		String curDate = formatter.format(monthDate);
		usageReq.setCurDtInd(curDate);
		DailyHourlyUsageResponseVO dailyHourlyUsageResponse = usageHelper.getHourlyUsageFromDB(usageReq, sessionId,
				companyCode);
		List<DailyResponseVO> dailyUsageList = dailyHourlyUsageResponse.getDailyUsageList();
		return dailyUsageList;
	}
	
	/**
	 * This method creates dummy data for a consecutive week
	 * @author NGASPerera
	 * @param req DailyResponseVO
	 * @param startDate
	 * @param endDate
	 * @return Set<DailyResponseVO>
	 */
	public Set<DailyResponseVO> createDummyDataForWeek(UsageRequestVO req, Date startDate, Date endDate) {
		Set<DailyResponseVO> dummyWeeklyUsageData = new LinkedHashSet<>();
		List<Date> datesInRange = dateUtil.getDatesInRange(startDate, endDate);
		for (Date date : datesInRange) {
			DailyResponseVO dummyResponseData = new DailyResponseVO();
			dummyResponseData.setActualDay(dateUtil.getDate(date));
			dummyResponseData.setContractAcctId(req.getContractAcctId());
			dummyResponseData.setContractId(req.getContractId());
			dummyResponseData.setDayCst(Constants.DAILY_COST_DUMMY_DATA);
			dummyResponseData.setDayTempHigh(Constants.DAILY_HIGH_TEMP_DUMMY_DATA);
			dummyResponseData.setDayTempLow(Constants.DAILY_LOW_TEMP_DUMMY_DATA);
			dummyResponseData.setDayUsg(Constants.DAIY_USAGE_DUMMY_DATA);
			dummyResponseData.setEsiId(req.getEsiId());
			dummyWeeklyUsageData.add(dummyResponseData);
		}
		return dummyWeeklyUsageData;
	}
	
	/**
	 * This method creates dummy data for random days for a week
	 * @param req UsageRequestVO
	 * @param weeklyUsageData
	 * @param startDate
	 * @param endDate
	 * @return Set<DailyResponseVO>
	 */
	public Set<DailyResponseVO> createDummyData(UsageRequestVO req, Set<DailyResponseVO> weeklyUsageData,
			Date startDate, Date endDate) {
		Map<Date, DailyResponseVO> dummyData = new LinkedHashMap<>();
		List<Date> datesInRange = dateUtil.getDatesInRange(startDate, endDate);
		for (Date currentDate : datesInRange) {
			DailyResponseVO dummyResponseData = new DailyResponseVO();
			dummyResponseData.setContractAcctId(req.getContractAcctId());
			dummyResponseData.setContractId(req.getContractId());
			dummyResponseData.setDayCst(Constants.DAILY_COST_DUMMY_DATA);
			dummyResponseData.setDayTempHigh(Constants.DAILY_HIGH_TEMP_DUMMY_DATA);
			dummyResponseData.setDayTempLow(Constants.DAILY_LOW_TEMP_DUMMY_DATA);
			dummyResponseData.setDayUsg(Constants.DAIY_USAGE_DUMMY_DATA);
			dummyResponseData.setEsiId(req.getEsiId());
			dummyResponseData.setActualDay(dateUtil.getDate(currentDate));
			dummyData.put(currentDate, dummyResponseData);
			dummyResponseData = null;
		}
		for (DailyResponseVO res : weeklyUsageData) {
			if (dummyData.containsKey(dateUtil.getDate(res.getActualDay()))) {
				dummyData.put(dateUtil.getDate(res.getActualDay()), res);
			}
		}
		Set<DailyResponseVO> weeklyUsageDummy = new LinkedHashSet<DailyResponseVO>(dummyData.values());
		return weeklyUsageDummy;
	}
	
	 public List<HourlyUsage> getWeeklyUsageByHuorlyDetails(String esiId, String contractId, String fromDate , String toDate)
	    {
	        return usageHelper.getWeeklyUsageByHuorlyDetails(esiId, contractId, fromDate , toDate);
	    }
	
	/**
	 * 
	 * @param esid
	 * @param companyCode
	 * @param sessionId
	 * @return
	 */
	public GMDZoneByEsiIdResponseVO getZoneIdByEsiId(String esid, String companyCode, String sessionId) {
		logger.info("START-[HistoryBO-getZoneIdByEsiId]");

		UsageRequestVO usageRequestVO = new UsageRequestVO();
		usageRequestVO.setEsiId(esid);
		GMDZoneByEsiIdResponseVO response = null;

		try {

			response = usageHelper.getZoneIdByEsiId(usageRequestVO, companyCode, sessionId);

			logger.info(" Exiting from the DAO Layer-[HistoryBO-getZoneIdByEsiId]");

			if (response != null) {

				response.setResultCode(RESULT_CODE_SUCCESS);
				response.setResultDescription(MSG_SUCCESS);

			} else {
				response = new GMDZoneByEsiIdResponseVO();
				response.setResultCode(RESULT_CODE_NO_DATA);
				response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

		} catch (Exception e) {

			logger.info(" Exeception Occured in the getZoneIdByEsiId" + e.getMessage());

			logger.error(e.getMessage());
			logger.error(e.getCause());
			logger.error(" Error {}", e.getMessage());

			response = new GMDZoneByEsiIdResponseVO();
			response.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			response.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), response);
		}

		return response;
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param currentDate
	 * @param companyCode
	 * @return
	 * @throws OAMException
	 */
	public HourlyPriceResponse getGMDPrice(String accountNumber, String contractId, String esid, String curDate,
			String sessionId, String companyCode) throws OAMException {

		logger.info(" START of the getGMDPrice() Helpermethod");
		UsageRequestVO usageRequestVO = new UsageRequestVO();
		usageRequestVO.setContractAcctId(accountNumber);
		usageRequestVO.setContractId(contractId);
		usageRequestVO.setEsiId(esid);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.MM_dd_yyyy);		
		usageRequestVO.setCurDtInd(formatter.format(date));

		DailyHourlyPriceResponseVO usageResp = null;
		HourlyPriceResponse hourlyPriceResponse = new HourlyPriceResponse();

		try {

			usageResp = usageHelper.getGMDPriceFromDB(usageRequestVO, sessionId, companyCode);

			if (usageResp != null && usageResp.getHourlyPriceList() != null
					&& !usageResp.getHourlyPriceList().isEmpty()) {

				hourlyPriceResponse = new HourlyPriceResponse();

				hourlyPriceResponse.setResultCode(RESULT_CODE_SUCCESS);
				hourlyPriceResponse.setResultDescription(MSG_SUCCESS);
				hourlyPriceResponse.setHourlyPriceList(usageResp.getHourlyPriceList());
				
				logger.info(" END of the getUsage() Helpermethod");
				return hourlyPriceResponse;
			} else {
				hourlyPriceResponse.setResultCode(RESULT_CODE_THREE);
				hourlyPriceResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			}

		} catch (Exception e) {
			logger.error(" Error {}", e.getMessage());
			hourlyPriceResponse = new HourlyPriceResponse();
			hourlyPriceResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			hourlyPriceResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, e.getMessage(), hourlyPriceResponse);

		}
		return hourlyPriceResponse;
	}
}
	