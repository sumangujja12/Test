package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Context 
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
	public Response getUsage(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId,
			@FormParam("esid") String esid,
			@FormParam("zoneId") String zoneId,
			@FormParam("currentDate") String curDtInd,
			@FormParam("curDayInd") String curDayInd,
			@FormParam("dyHrInd") String dyHrInd,
			@FormParam("companyCode") String companyCode)
	{
		
		logger.debug("Inside getUsage in History Resource");
		Response response = null;
		logger.info("START-[HistoryResourse-getUsage]");
		GenericResponse usageResponse = historyBO
					.getUsage(accountNumber, contractId, esid,zoneId,curDtInd,curDayInd,dyHrInd,
							  httpRequest.getSession(true).getId(),companyCode);
		
	
		response = Response.status(200).entity(usageResponse).build();
		logger.info("END-[HistoryResourse-getUsage]");
		logger.debug("Exiting getUsage in History Resource");
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
	@PostMapping(value = "/history/getPaymentHistory", consumes = {
				MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response getPaymentHistory(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("legacyAccountNumber") String legacyAccountNumber,
			@FormParam("conversionDate") String conversionDate,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("companyCode") String companyCode)
	{
		logger.debug("Inside getPaymentHistory in History Resource");
		Response response = null;
		logger.info("START-[HistoryResourse-getPaymentHistory]");
		PaymentHistoryResponse historyResponse = historyBO.getPaymentHistory(accountNumber,legacyAccountNumber,conversionDate,startDate,endDate,companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(historyResponse).build();
		logger.info("END-[HistoryResourse-getPaymentHistory]");
		logger.debug("Exiting getPaymentHistory in History Resource");
		return response;
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
	public Response getPlanHistory(
			                       @FormParam("accountNumber")String accountNumber,
			                       @FormParam("legacyAccountNumber")String legacyAccountNumber,
			                       @FormParam("conversionDate")String conversionDate,
			                       @FormParam("startDate")String startDate,
			                       @FormParam("endDate")String endDate,
			                       @FormParam("languageCode")String languageCode,
			                       @FormParam("companyCode")String companyCode) {
		
		logger.debug("Inside getPlanHistory resource");
		Response response = null;
		logger.info("START-[UsageHistoryResourse-getUsage]");
		PlanHistoryResponse planHistoryResponse = historyBO.getPlanHistory(accountNumber, legacyAccountNumber, conversionDate, startDate, endDate, languageCode, companyCode,httpRequest.getSession(true).getId());
		response = Response.status(200).entity(planHistoryResponse).build();
		logger.info("END-[UsageHistoryResourse-getUsage]");
		logger.debug("Exiting getUsage in Usage Resource");
		return response;
	}
	
	@PostMapping(value = "/history/getBillHistory", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response getBillPaymentHistory(
			                       @FormParam("accountNumber")String accountNumber,
			                       @FormParam("legacyAccountNumber")String legacyAccountNumber,
			                       @FormParam("conversionDate")String conversionDate,
			                       @FormParam("startDate")String startDate,
			                       @FormParam("endDate")String endDate,
			                       @FormParam("companyCode")String companyCode) {
		
		logger.debug("Inside getBillPaymentHistory resource");
		Response response = null;
		logger.info("START-[getBillPaymentHistory- ]");
		BillPaymentHistoryResponse billPaymentHistoryResponse = historyBO.getBillPaymentHistory(accountNumber, legacyAccountNumber, conversionDate, 
				startDate, endDate, companyCode, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(billPaymentHistoryResponse).build();
		logger.info("END-[getBillPaymentHistory]"); 
		return response;
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
	public Response getWeeklyUsage(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId,
			@FormParam("esid") String esid,
			@FormParam("zoneId") String zoneId,
			@FormParam("currentDate") String curDate,
			@FormParam("companyCode") String companyCode)
	{
		
		Response response = null;
		logger.info("START-[HistoryResourse-getWeeklyUsage]");
		
		WeeklyUsageResponseList weeklyUsageResp = historyBO.getWeeklyUsageDetails(accountNumber, contractId, 
				esid, zoneId, curDate, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(weeklyUsageResp).build();
		
		logger.info("END-[HistoryResourse-getWeeklyUsage]"); 
		return response;
		
		
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
	public Response getMonthlyUsage(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId,
			@FormParam("esid") String esid,
			@FormParam("zoneId") String zoneId,
			@FormParam("currentDate") String curDate,
			@FormParam("companyCode") String companyCode)
	{
		Response response = null;
		logger.info("START-[getMonthlyUsage- ]");
		
		MonthlyUsageResponseList monthlyUsageResp = historyBO.getMonthlyUsageDetails(accountNumber, contractId, 
				esid, zoneId, curDate, companyCode, httpRequest.getSession(true).getId());
	
		response = Response.status(200).entity(monthlyUsageResp).build();
		
		logger.info("END-[getMonthlyUsage]"); 
		return response;
		
		
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
	public Response getDailyWeeklyUsage(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId,
			@FormParam("esid") String esid,
			@FormParam("zoneId") String zoneId,
			@FormParam("wkDate") String wkDtInV,
			@FormParam("curWkInd") String curWkInV,
			@FormParam("companyCode") String companyCode)
	{
		
		Response response = null;
		logger.info("START-[HistoryResourse-getDailyWeeklyUsage]");
		
		DailyWeeklyUsageResponseList dailyWeeklyUsageResp = historyBO.getDailyWeeklyUsageDetails(accountNumber, contractId, 
				esid, zoneId, wkDtInV, curWkInV, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(dailyWeeklyUsageResp).build();
		
		logger.info("END-[HistoryResourse-getDailyWeeklyUsage]"); 
		return response;
		
		
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
	public Response getSmartMeterUsageHistory(
			@FormParam("esid") String esid,
			@FormParam("accountNumber") String accountNumber,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("companyCode") String companyCode) {
			Response response = null;
			logger.info("START-[HistoryResourse-getSmartMeterUsageHistory]"); 
			SmartMeterUsageResponseList smartMeterUsageResp = historyBO.getSmartMeterUsageHistory(esid,accountNumber, 
					startDate, endDate, companyCode, httpRequest.getSession(true).getId());
			response = Response.status(200).entity(smartMeterUsageResp).build();
			logger.info("END-[HistoryResourse-getSmartMeterUsageHistory]"); 
			return response;
			
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
	public Response getIntervalData(
			@FormParam("esid") String esid,
			@FormParam("startDate")String startDate,
			@FormParam("endDate")String endDate,
			@FormParam("companyCode") String companyCode)
	{
		
		Response response = null;
		logger.info("START-[HistoryResourse-getIntervalData]");
		
		IntervalDataResponse intervalDataResp = historyBO.getIntervalData(esid, startDate, 
				endDate,companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(intervalDataResp).build();
		
		logger.info("END-[HistoryResourse-getIntervalData]"); 
		return response;
		
		
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
	public Response fetchPaymentHistory(
			@FormParam("accountNumber") String accountNumber,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("companyCode") String companyCode,
			@FormParam("brandName") String brandName)
	{
		logger.debug("Inside fetchPaymentHistory in History Resource");
		Response response = null;
		logger.info("START-[HistoryResourse-fetchPaymentHistory]");
		PaymentHistoryResponse historyResponse = historyBO.fetchPaymentHistory(accountNumber,startDate,endDate,companyCode,brandName,httpRequest.getSession(true).getId());
		
		
		response = Response.status(200).entity(historyResponse).build();
			
		logger.info("END-[HistoryResourse-fetchPaymentHistory]");
		logger.debug("Exiting fetchPaymentHistory in History Resource");
		return response;
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
	public Response getInvoiceUsageHistory(
			                       @FormParam("accountNumber")String accountNumber,			                       
			                       @FormParam("startDate")String startDate,
			                       @FormParam("endDate")String endDate,
			                       @FormParam("companyCode")String companyCode,
			                       @FormParam("brandName")String brandName) {
		
		logger.debug("Inside getInvoiceUsageHistory resource");
		Response response = null;
		logger.info("START-[getInvoiceUsageHistory- ]");
		InvoiceUsageHistoryResponse invoiceUsageHistoryResponse = historyBO.getInvoiceUsageHistory(accountNumber, startDate, endDate, companyCode, httpRequest.getSession(true).getId(), brandName);
		response = Response.status(200).entity(invoiceUsageHistoryResponse).build();
			
		logger.info("END-[getInvoiceUsageHistory]"); 
		return response;
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
	public Response getConsumptionUsage(
			@FormParam("contractId") String contractId,
			@FormParam("noOfMonths") String noOfMonths,
			@FormParam("brandName") String brandName,
			@FormParam("companyCode") String companyCode)
	{
		
		Response response = null;
		logger.info("START-[HistoryResourse-getConsumptionUsage]");
		
		GetConsumptionHistoryResponse consumptionResponse = historyBO.getConsumptionUsage(contractId, noOfMonths, brandName, companyCode, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(consumptionResponse).build();
		
		logger.info("END-[HistoryResourse-getConsumptionUsage]"); 
		return response;
		
		
	}
	
	@PostMapping(value = "/history/getWeeklyUsage", consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Response getWeeklyUsage(@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId, @FormParam("esid") String esid,
			@FormParam("zoneId") String zoneId, @FormParam("companyCode") String companyCode,
			@FormParam("brandName") String brandName, @FormParam("weekNumber") int weekNumber,
			@FormParam("year") int year) {
		Response response = null;
		WeeklyUsageResponse weeklyUsageSummary = historyBO.getWeeklyUsageData(accountNumber, contractId, esid, zoneId,
				companyCode, brandName, weekNumber, year, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(weeklyUsageSummary).build();
		return response;
	}

}
