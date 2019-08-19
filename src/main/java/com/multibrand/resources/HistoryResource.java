package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.bo.HistoryBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.historyResponse.BillPaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.GetConsumptionHistoryResponse;
import com.multibrand.vo.response.historyResponse.IntervalDataResponse;
import com.multibrand.vo.response.historyResponse.InvoiceUsageHistoryResponse;
import com.multibrand.vo.response.historyResponse.PaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.PlanHistoryResponse;
import com.multibrand.vo.response.historyResponse.WeeklyUsageResponse;


/***
 * 
 * @author smuruga1 This Resource handles all the history API calls with Ranges.
 * 
 */

@RestController
public class HistoryResource
{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired 
	private HttpServletRequest httpRequest;
	
	/** Object of HistoryBO class. */
	@Autowired
	private HistoryBO historyBO;
	
	@Autowired
	ErrorContentHelper errorContentHelper;

	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param curDtInd
	 * @param curDayInd
	 * @param dyHrInd
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getDailyHourlyUsage", consumes = {
				MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getUsage(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId,
			@RequestParam("esid") String esid,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("currentDate") String curDtInd,
			@RequestParam("curDayInd") String curDayInd,
			@RequestParam("dyHrInd") String dyHrInd,
			@RequestParam("companyCode") String companyCode)
	{
		
		logger.debug("Inside getUsage in History Resource");
		//Response response = null;
		logger.info("START-[HistoryResourse-getUsage]");
		GenericResponse usageResponse = historyBO
					.getUsage(accountNumber, contractId, esid,zoneId,curDtInd,curDayInd,dyHrInd,
							  httpRequest.getSession(true).getId(),companyCode);
		
	
		//response = Response.status(200).entity(usageResponse).build();
		logger.info("END-[HistoryResourse-getUsage]");
		logger.debug("Exiting getUsage in History Resource");
		return new ResponseEntity<GenericResponse>(usageResponse, HttpStatus.OK);
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
	@PostMapping(value = "/history/getPaymentHistory", consumes = {
				MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getPaymentHistory(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("legacyAccountNumber") String legacyAccountNumber,
			@RequestParam("conversionDate") String conversionDate,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("companyCode") String companyCode)
	{
		logger.debug("Inside getPaymentHistory in History Resource");
		//Response response = null;
		logger.info("START-[HistoryResourse-getPaymentHistory]");
		PaymentHistoryResponse historyResponse = historyBO.getPaymentHistory(accountNumber,legacyAccountNumber,conversionDate,startDate,endDate,companyCode,httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(historyResponse).build();
		logger.info("END-[HistoryResourse-getPaymentHistory]");
		logger.debug("Exiting getPaymentHistory in History Resource");
		return new ResponseEntity<GenericResponse>(historyResponse, HttpStatus.OK);
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
	@PostMapping(value = "/history/getPlanHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getPlanHistory(
			                       @RequestParam("accountNumber")String accountNumber,
			                       @RequestParam("legacyAccountNumber")String legacyAccountNumber,
			                       @RequestParam("conversionDate")String conversionDate,
			                       @RequestParam("startDate")String startDate,
			                       @RequestParam("endDate")String endDate,
			                       @RequestParam("languageCode")String languageCode,
			                       @RequestParam("companyCode")String companyCode) {
		
		logger.debug("Inside getPlanHistory resource");
		//Response response = null;
		logger.info("START-[UsageHistoryResourse-getUsage]");
		PlanHistoryResponse planHistoryResponse = historyBO.getPlanHistory(accountNumber, legacyAccountNumber, conversionDate, startDate, endDate, languageCode, companyCode,httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(planHistoryResponse).build();
		logger.info("END-[UsageHistoryResourse-getUsage]");
		logger.debug("Exiting getUsage in Usage Resource");
		return new ResponseEntity<GenericResponse>(planHistoryResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/history/getBillHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getBillPaymentHistory(
			                       @RequestParam("accountNumber")String accountNumber,
			                       @RequestParam("legacyAccountNumber")String legacyAccountNumber,
			                       @RequestParam("conversionDate")String conversionDate,
			                       @RequestParam("startDate")String startDate,
			                       @RequestParam("endDate")String endDate,
			                       @RequestParam("companyCode")String companyCode) {
		
		logger.debug("Inside getBillPaymentHistory resource");
		//Response response = null;
		logger.info("START-[getBillPaymentHistory- ]");
		BillPaymentHistoryResponse billPaymentHistoryResponse = historyBO.getBillPaymentHistory(accountNumber, legacyAccountNumber, conversionDate, 
				startDate, endDate, companyCode, httpRequest.getSession(true).getId());
		
		//response = Response.status(200).entity(billPaymentHistoryResponse).build();
		logger.info("END-[getBillPaymentHistory]"); 
		return new ResponseEntity<GenericResponse>(billPaymentHistoryResponse, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param curBillDt
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getWeeklyCompareUsage", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getWeeklyUsage(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId,
			@RequestParam("esid") String esid,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("currentDate") String curDate,
			@RequestParam("companyCode") String companyCode)
	{
		
		//Response response = null;
		logger.info("START-[HistoryResourse-getWeeklyUsage]");
		
		WeeklyUsageResponseList weeklyUsageResp = historyBO.getWeeklyUsageDetails(accountNumber, contractId, 
				esid, zoneId, curDate, companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(weeklyUsageResp).build();
		
		logger.info("END-[HistoryResourse-getWeeklyUsage]"); 
		return new ResponseEntity<GenericResponse>(weeklyUsageResp, HttpStatus.OK);
		
		
	}
	
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param curBillDt
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getMonthlyUsage", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getMonthlyUsage(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId,
			@RequestParam("esid") String esid,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("currentDate") String curDate,
			@RequestParam("companyCode") String companyCode)
	{
		//Response response = null;
		logger.info("START-[getMonthlyUsage- ]");
		
		MonthlyUsageResponseList monthlyUsageResp = historyBO.getMonthlyUsageDetails(accountNumber, contractId, 
				esid, zoneId, curDate, companyCode, httpRequest.getSession(true).getId());
	
		//response = Response.status(200).entity(monthlyUsageResp).build();
		
		logger.info("END-[getMonthlyUsage]"); 
		return new ResponseEntity<GenericResponse>(monthlyUsageResp, HttpStatus.OK);
		
		
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param contractId
	 * @param esid
	 * @param zoneId
	 * @param wkDtInV
	 * @param curWkInV
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getDailyWeeklyCompareUsage", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getDailyWeeklyUsage(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId,
			@RequestParam("esid") String esid,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("wkDate") String wkDtInV,
			@RequestParam("curWkInd") String curWkInV,
			@RequestParam("companyCode") String companyCode)
	{
		
		//Response response = null;
		logger.info("START-[HistoryResourse-getDailyWeeklyUsage]");
		
		DailyWeeklyUsageResponseList dailyWeeklyUsageResp = historyBO.getDailyWeeklyUsageDetails(accountNumber, contractId, 
				esid, zoneId, wkDtInV, curWkInV, companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(dailyWeeklyUsageResp).build();
		
		logger.info("END-[HistoryResourse-getDailyWeeklyUsage]"); 
		return new ResponseEntity<GenericResponse>(dailyWeeklyUsageResp, HttpStatus.OK);
		
		
	}
	
	/**
	 * 
	 * @param servicePointId
	 * @param accountNumber
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@PostMapping(value = "/history/getSmartMeterUsageHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getSmartMeterUsageHistory(
			@RequestParam("esid") String esid,
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("companyCode") String companyCode) {
			//Response response = null;
			logger.info("START-[HistoryResourse-getSmartMeterUsageHistory]"); 
			SmartMeterUsageResponseList smartMeterUsageResp = historyBO.getSmartMeterUsageHistory(esid,accountNumber, 
					startDate, endDate, companyCode, httpRequest.getSession(true).getId());
			//response = Response.status(200).entity(smartMeterUsageResp).build();
			logger.info("END-[HistoryResourse-getSmartMeterUsageHistory]"); 
			return new ResponseEntity<GenericResponse>(smartMeterUsageResp, HttpStatus.OK);
			
    }
	
	/**
	 * @author ahanda1
	 * @param esid
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getIntervalData", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getIntervalData(
			@RequestParam("esid") String esid,
			@RequestParam("startDate")String startDate,
			@RequestParam("endDate")String endDate,
			@RequestParam("companyCode") String companyCode)
	{
		
		//Response response = null;
		logger.info("START-[HistoryResourse-getIntervalData]");
		
		IntervalDataResponse intervalDataResp = historyBO.getIntervalData(esid, startDate, 
				endDate,companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(intervalDataResp).build();
		
		logger.info("END-[HistoryResourse-getIntervalData]"); 
		return new ResponseEntity<GenericResponse>(intervalDataResp, HttpStatus.OK);
		
		
	}
	/**
	 * @author kdeshmu1
	 * @param accountNumber
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "/history/fetchPaymentHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> fetchPaymentHistory(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("companyCode") String companyCode,
			@RequestParam("brandName") String brandName)
	{
		logger.debug("Inside fetchPaymentHistory in History Resource");
		//Response response = null;
		logger.info("START-[HistoryResourse-fetchPaymentHistory]");
		PaymentHistoryResponse historyResponse = historyBO.fetchPaymentHistory(accountNumber,startDate,endDate,companyCode,brandName,httpRequest.getSession(true).getId());
		
		
		//response = Response.status(200).entity(historyResponse).build();
			
		logger.info("END-[HistoryResourse-fetchPaymentHistory]");
		logger.debug("Exiting fetchPaymentHistory in History Resource");
		return new ResponseEntity<GenericResponse>(historyResponse, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param accountNumber
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param brandName
	 * @return
	 */
	@PostMapping(value = "/history/getInvoiceUsageHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getInvoiceUsageHistory(
			                       @RequestParam("accountNumber")String accountNumber,			                       
			                       @RequestParam("startDate")String startDate,
			                       @RequestParam("endDate")String endDate,
			                       @RequestParam("companyCode")String companyCode,
			                       @RequestParam("brandName")String brandName) {
		
		logger.debug("Inside getInvoiceUsageHistory resource");
		//Response response = null;
		logger.info("START-[getInvoiceUsageHistory- ]");
		InvoiceUsageHistoryResponse invoiceUsageHistoryResponse = historyBO.getInvoiceUsageHistory(accountNumber, startDate, endDate, companyCode, httpRequest.getSession(true).getId(), brandName);
		//response = Response.status(200).entity(invoiceUsageHistoryResponse).build();
			
		logger.info("END-[getInvoiceUsageHistory]"); 
		return new ResponseEntity<GenericResponse>(invoiceUsageHistoryResponse, HttpStatus.OK);
	}
	
	/**
	 * @author smuruga1
	 * @param contractId
	 * @param noOfMonths
	 * @param brandName
	 * @param companyCode
	 * @return
	 */
	@PostMapping(value = "/history/getConsumptionUsageHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getConsumptionUsage(
			@RequestParam("contractId") String contractId,
			@RequestParam("noOfMonths") String noOfMonths,
			@RequestParam("brandName") String brandName,
			@RequestParam("companyCode") String companyCode)
	{
		
		//Response response = null;
		logger.info("START-[HistoryResourse-getConsumptionUsage]");
		
		GetConsumptionHistoryResponse consumptionResponse = historyBO.getConsumptionUsage(contractId, noOfMonths, brandName, companyCode, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(consumptionResponse).build();
		
		logger.info("END-[HistoryResourse-getConsumptionUsage]"); 
		return new ResponseEntity<GenericResponse>(consumptionResponse, HttpStatus.OK);
		
		
	}
	
	@PostMapping(value = "/history/getWeeklyUsage", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GenericResponse> getWeeklyUsage(@RequestParam("accountNumber") String accountNumber,
			@RequestParam("contractId") String contractId, @RequestParam("esid") String esid,
			@RequestParam("zoneId") String zoneId, @RequestParam("companyCode") String companyCode,
			@RequestParam("brandName") String brandName, @RequestParam("weekNumber") int weekNumber,
			@RequestParam("year") int year) {
		//Response response = null;
		WeeklyUsageResponse weeklyUsageSummary = historyBO.getWeeklyUsageData(accountNumber, contractId, esid, zoneId,
				companyCode, brandName, weekNumber, year, httpRequest.getSession(true).getId());
		//response = Response.status(200).entity(weeklyUsageSummary).build();
		return new ResponseEntity<GenericResponse>(weeklyUsageSummary, HttpStatus.OK);
	}

}
