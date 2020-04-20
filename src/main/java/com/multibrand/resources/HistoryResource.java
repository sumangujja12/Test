package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.bo.HistoryBO;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.vo.response.DailyWeeklyUsageResponseList;
import com.multibrand.vo.response.GenericResponse;
import com.multibrand.vo.response.MonthlyUsageResponseList;
import com.multibrand.vo.response.SmartMeterUsageResponseList;
import com.multibrand.vo.response.WeeklyUsageResponseList;
import com.multibrand.vo.response.gmd.GMDZoneByEsiIdResponseVO;
import com.multibrand.vo.response.gmd.HourlyPriceResponse;
import com.multibrand.vo.response.historyResponse.BillPaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.GetConsumptionHistoryResponse;
import com.multibrand.vo.response.historyResponse.IntervalDataResponse;
import com.multibrand.vo.response.historyResponse.InvoiceUsageHistoryResponse;
import com.multibrand.vo.response.historyResponse.PaymentHistoryResponse;
import com.multibrand.vo.response.historyResponse.PlanHistoryResponse;
import com.multibrand.vo.response.historyResponse.WeeklyUsageResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.multibrand.util.Constants;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.response.DailyResponseVO;
import com.multibrand.vo.response.HourlyUsage;


/***
 * 
 * @author smuruga1 This Resource handles all the history API calls with Ranges.
 * 
 */

@Component
@Path("/history")
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
	@POST
	@Path("/getDailyHourlyUsage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getPaymentHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getPlanHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	
	@POST
	@Path("/getBillHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getWeeklyCompareUsage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getMonthlyUsage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getDailyWeeklyCompareUsage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getSmartMeterUsageHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("getIntervalData")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/fetchPaymentHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getInvoiceUsageHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
	@POST
	@Path("/getConsumptionUsageHistory")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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

	@POST
	@Path("/getWeeklyUsage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
	
	 @POST
	    @Path("/getWeeklyUsageByHourly")
	    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response getWeeklyUsageByHuorly(@FormParam("accountNumber") String accountNumber,
	            @FormParam("contractId") String contractId, @FormParam("esid") String esid,
	            @FormParam("zoneId") String zoneId, @FormParam("companyCode") String companyCode,
	            @FormParam("brandName") String brandName, @FormParam("weekNumber") int weekNumber,
	            @FormParam("year") int year) {
	        Response response = null;
	 
	        logger.info("fetching ActualDay from getWeeklyUsage API");
	        WeeklyUsageResponse weeklyUsageSummary = historyBO.getWeeklyUsageData(accountNumber, contractId, esid, zoneId,
	                companyCode, brandName, weekNumber, year, httpRequest.getSession(true).getId());
	 
	        // Sorting ActualDay List
	        Set<DailyResponseVO> dailyResponseSet = weeklyUsageSummary != null ? weeklyUsageSummary.getWeeklyUsageData()
	                : null;
	 
	        if (dailyResponseSet != null) {
	            Date[] arrayOfDates = new Date[dailyResponseSet.size()];
	            int i = 0;
	            for (DailyResponseVO dailyResponseVO : dailyResponseSet) {
	                if (dailyResponseVO != null && dailyResponseVO.getActualDay() != null) {
	                    arrayOfDates[i] = DateUtil.getDate(dailyResponseVO.getActualDay(), Constants.yyyyMMdd);
	                    i++;
	                }
	            }
	            Arrays.sort(arrayOfDates);// Sorting Dates
	 
	            // Data Base Call
	            logger.info("fetching ActualDay from getWeeklyUsage API");
	            if (arrayOfDates.length > 0) {
	                DailyResponseVO dailyRespVO = dailyResponseSet.iterator().next();
	                List<HourlyUsage> hourlyUsageList = historyBO.getWeeklyUsageByHuorlyDetails(dailyRespVO.getEsiId(),
	                        dailyRespVO.getContractId(), DateUtil.getFormatedDate(arrayOfDates[0], Constants.MM_dd_yyyy),
	                        DateUtil.getFormatedDate(arrayOfDates[arrayOfDates.length - 1], Constants.MM_dd_yyyy));
	    
	                // Map hourlyUsage to DailyResponse
	                Iterator<DailyResponseVO> dailyResponseItr = dailyResponseSet.iterator();
	                while (dailyResponseItr.hasNext()) {
	                    dailyRespVO = dailyResponseItr.next();
	                    for (HourlyUsage hourlyUsage : hourlyUsageList) {
	                        if (hourlyUsage.getActualDay().equals(dailyRespVO.getActualDay())) {
	                            dailyRespVO.setHourlyUsageList(hourlyUsage);	                            
	                            break;
	                        }
	                    }
	                    
	                    if(dailyRespVO.getHourlyUsageList() == null) {
	                    	dailyRespVO.setDayUsg(Constants.DEFAULT_PRICE_VALUE_ZERO_DOT_ZERO);
	                    	dailyRespVO.setDayCst(Constants.DEFAULT_PRICE_VALUE_ZERO_DOT_ZERO);
	                    	
	                    }
	                }
	            }
	        }
	 
	        response = Response.status(200).entity(weeklyUsageSummary).build();
	        return response;
	}
	
	/**
	 * 
	 * @param esid
	 * @param companyCode
	 * @return
	 */
	@POST
	@Path("/getZoneIdByESIID")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getZoneIdByEsiId(@FormParam("esid") String esid, @FormParam("companyCode") String companyCode) {
		Response response = null;
		GMDZoneByEsiIdResponseVO gmdZoneByEsiIdResponse = historyBO.getZoneIdByEsiId(esid, companyCode,
				httpRequest.getSession(true).getId());
		response = Response.status(200).entity(gmdZoneByEsiIdResponse).build();
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
	 */
	@POST
	@Path("/getGMDPrice")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getGMDPrice(@FormParam("accountNumber") String accountNumber,
			@FormParam("contractId") String contractId, @FormParam("esid") String esid,
			@FormParam("currentDate") String curDate, @FormParam("companyCode") String companyCode) {
		Response response = null;
		HourlyPriceResponse hourlyPriceResponse = historyBO.getGMDPrice(accountNumber, contractId, esid, curDate,
				httpRequest.getSession(true).getId(), companyCode);
		response = Response.status(200).entity(hourlyPriceResponse).build();
		return response;

	}
}
