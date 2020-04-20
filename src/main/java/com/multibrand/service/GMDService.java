package com.multibrand.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.gmd.Breakdown;
import com.multibrand.vo.response.gmd.Costs;
import com.multibrand.vo.response.gmd.Current;
import com.multibrand.vo.response.gmd.GMDPricingResponse;
import com.multibrand.vo.response.gmd.GMDReturnCharge;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;
import com.multibrand.vo.response.gmd.HourlyPrice;
import com.multibrand.vo.response.gmd.HourlyPriceResponse;
import com.multibrand.vo.response.gmd.PastSeries;
import com.multibrand.vo.response.gmd.PredictedSeries;
import com.multibrand.vo.response.gmd.Pricing;
import com.nrg.cxfstubs.gmdprice.EPROFVALUE;
import com.nrg.cxfstubs.gmdprice.TEPROFVALUES;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE_Service;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT_Service;
import com.nrg.cxfstubs.gmdstatement.ZesGmdRetchr;
import com.nrg.cxfstubs.gmdstatement.ZesGmdStmt;
import com.nrg.cxfstubs.gmdstatement.ZetGmdInvdate;
import com.nrg.cxfstubs.gmdstatement.ZetGmdStmt;
import com.nrg.cxfstubs.gmdstatement.ZettGmdRetchr;


/**
 * 
 * @author rpendur1
 * 
 *         This class is responsible for fetching information from Redbull
 *         Service ProfileDomain
 */

@Service
public class GMDService extends BaseAbstractService {

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;



	/**
	 * This profile call will do the call to the logging framework 
	 * @param accountNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	public GMDStatementBreakDownResponse getGMDStatementDetails(String accountNumber, String companyCode, 
			String esiId, String year, String month ,String sessionId) throws NRGException {
		
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::START"); 
				
		GMDStatementBreakDownResponse gmdStatementBreakDownResp = new GMDStatementBreakDownResponse();
		
		long startTime = CommonUtil.getStartTime();
		StringBuilder request = new StringBuilder();
		request
			.append("accountNumber=")
			.append(accountNumber)
			.append("esiId=")
			.append(esiId)
			.append("year=")
			.append(year)
			.append("month=")
			.append(month);

		
		//Start : Added for Redbull CXF upgrade by IJ
		URL url = ZEISUGETGMDSTMT_Service.class.getResource("Z_E_ISU_GET_GMD_STMT.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZEISUGETGMDSTMT_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Z_E_ISU_GET_GMD_STMT.wsdl");
        }
        ZEISUGETGMDSTMT_Service gmdStatementService = new ZEISUGETGMDSTMT_Service(url);
		
		ZEISUGETGMDSTMT stub = gmdStatementService.getZEISUGETGMDSTMT();
		 BindingProvider binding = (BindingProvider)stub;
	    
	        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,  this.envMessageReader.getMessage(CCS_USER_NAME));
	        binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,  this.envMessageReader.getMessage(CCS_PASSWORD));
	        binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.envMessageReader.getMessage(GMD_STATEMENT_ENDPOINT_URL_JNDINAME));
	        
          logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::before call");
          
          ZetGmdInvdate zetGmdInvdate = new ZetGmdInvdate();
          ZesGmdStmt zesGmdStmt = new ZesGmdStmt();
          ZetGmdStmt zetGmdStmt = new ZetGmdStmt();
          ZettGmdRetchr zettGmdRetchr = new ZettGmdRetchr();

         Holder<ZetGmdInvdate>  holderZetGmdInvdate = new Holder<>();
         holderZetGmdInvdate.value = zetGmdInvdate;
         
         Holder<ZettGmdRetchr>  holderZettGmdRetchr = new Holder<>();
         holderZettGmdRetchr.value = zettGmdRetchr;
         
         Holder<ZesGmdStmt>  holderZesGmdStmt = new Holder<>();
         holderZesGmdStmt.value = zesGmdStmt;
         
         Holder<ZetGmdStmt>  holderZetGmdStmt = new Holder<>();
         holderZetGmdStmt.value = zetGmdStmt;
		
		try{
			
			stub.zeIsuGetGmdStmt(companyCode, accountNumber, esiId, month, year, holderZetGmdInvdate, holderZettGmdRetchr,  holderZesGmdStmt, holderZetGmdStmt);
						
			gmdStatementBreakDownResp = handleGMDStatementResponse(holderZesGmdStmt, holderZettGmdRetchr);
			
			double rate = handleRateResponse(holderZetGmdStmt);
			
			gmdStatementBreakDownResp.setRate(rate);
			
		}catch(Exception ex){
			utilityloggerHelper.logTransaction("getGMDStatementDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);
			
		}
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::after call");
							
		utilityloggerHelper.logTransaction("getGMDStatementDetails", false, request,gmdStatementBreakDownResp, 
				gmdStatementBreakDownResp.getMessageText(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::end");
		
		return  gmdStatementBreakDownResp;
	}
	
	
	/**
	 * This profile call will do the call to the logging framework 
	 * @param accountNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	public GMDPricingResponse getGMDPriceDetails(String accountNumber, String contractId, String companyCode,
			String esiId,String sessionId, HourlyPriceResponse response) throws NRGException {
		
		logger.info("GMDService.getGMDPriceDetails::::::::::::::::::::START");
		
		GMDPricingResponse gmdPricingResp = new GMDPricingResponse();
		
		long startTime = CommonUtil.getStartTime();
		StringBuilder request = new StringBuilder();
		request
			.append("accountNumber=")
			.append(accountNumber)
			.append("contractId=")
			.append(contractId)			
			.append("esiId=")
			.append(esiId);

		
		//Start : Added for Redbull CXF upgrade by IJ
		URL url = ZEISUGETGMDPRICE_Service.class.getResource("Z_E_ISU_GET_GMD_PRICE.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZEISUGETGMDPRICE_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Z_E_ISU_GET_GMD_PRICE.wsdl");
        }
        ZEISUGETGMDPRICE_Service gmdPriceService = new ZEISUGETGMDPRICE_Service(url);
		
        ZEISUGETGMDPRICE stub = gmdPriceService.getZEISUGETGMDPRICE();
		BindingProvider binding = (BindingProvider)stub;
	    
	    binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,  this.envMessageReader.getMessage(CCS_USER_NAME));
	    binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,  this.envMessageReader.getMessage(CCS_PASSWORD));
	    binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.envMessageReader.getMessage(GMD_PRICET_ENDPOINT_URL_JNDINAME));
	        
        logger.info("GMDService.getGMDPriceDetails::::::::::::::::::::before call");
        
        Holder<String> exCurrentDate = new Holder<>();
  		
        Holder<BigDecimal> exCurrentPrice  = new  Holder<>();
        Holder<XMLGregorianCalendar> exCurrentTime = new Holder<>();
        
        Holder<String> exErrorMessage = new Holder<>();
        Holder<String> exZone = new Holder<>();
        
        Holder<TEPROFVALUES> exTepProfValues = new Holder<>(); 
        
		try{
			
			stub.zEISUGETGMDPRICE(companyCode, accountNumber, esiId, exCurrentDate, exCurrentPrice, exCurrentTime, exErrorMessage, exTepProfValues,exZone);
			gmdPricingResp = handleGMDCurrentPriceResponse(exTepProfValues, exCurrentDate, exCurrentPrice, exCurrentTime, exErrorMessage, response );
			
		}catch(Exception ex){
			utilityloggerHelper.logTransaction("getGMDPriceDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);
			
		}
		logger.info("GMDService.getGMDPriceDetails::::::::::::::::::::after call");
       
		return gmdPricingResp;
	}
	private double handleRateResponse(Holder<ZetGmdStmt>  holderZetGmdStmt) {
	
		
		ZetGmdStmt zetGmdStmt = holderZetGmdStmt.value;
		
		double rate = 0;
		
		int count = 0;
		
		for ( ZesGmdStmt ZesGmdStmt : zetGmdStmt.getItem()) {
			rate = rate+ZesGmdStmt.getUseChrg().doubleValue();
			count ++;
		}
					

		if ( count  > 0) {
			return Double.parseDouble(String.format("%.2f", rate/count));
		} else {
			return rate;
		}
		

	}	
	
	private GMDPricingResponse handleGMDCurrentPriceResponse( Holder<TEPROFVALUES> exTepProfValues, Holder<String> exCurrentDate,
			Holder<BigDecimal> exCurrentPrice,  Holder<XMLGregorianCalendar> exCurrentTime, Holder<String> exErrorMessage, HourlyPriceResponse hourlyPriceResponse) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		GMDPricingResponse response = new GMDPricingResponse();
	
		Pricing pricing = new Pricing();
		Current currentPrice = getGMDCurrentPrice(exCurrentPrice, exCurrentDate, exCurrentTime );
		
		List<PredictedSeries> predictedSeriesList = getProjectedPrice(exTepProfValues);
		List<PastSeries> pastSeriesList = getPastPrice(hourlyPriceResponse);

		pricing.setCurrent(currentPrice);
		pricing.setPredictedSeries(predictedSeriesList);
		
		pricing.setPastSeries(pastSeriesList);
		
		response.setPricing(pricing);
		return response;

	}
	
	private List<GMDReturnCharge> getReturnCharge(Holder<ZettGmdRetchr> holderZettGmdRetchr) {
		
		List<GMDReturnCharge> gmdReturnChargeList = new ArrayList<>();
				
		for (ZesGmdRetchr zesGmdRetchr : holderZettGmdRetchr.value.getItem()) {
			
			GMDReturnCharge gmdReturnCharge = new GMDReturnCharge();
			
			gmdReturnCharge.setBillingDate(zesGmdRetchr.getBillDate());
			gmdReturnCharge.setInvoiceNumber(zesGmdRetchr.getInvoice());
			gmdReturnCharge.setReturnChrg(zesGmdRetchr.getRetChrg());
			
			gmdReturnChargeList.add(gmdReturnCharge);
		}
		
		
		return gmdReturnChargeList;
	} 
	

	private List<PredictedSeries> getProjectedPrice(Holder<TEPROFVALUES> exTepProfValues) {
		
		List<PredictedSeries> predictedSeriesList = new ArrayList<>();
				
		for (EPROFVALUE epROFVALUE : exTepProfValues.value.getItem()) {
			
			PredictedSeries predictedSeries = new PredictedSeries();
			
			predictedSeries.setPrice(epROFVALUE.getPROFVALUE());
			predictedSeries.setTime(epROFVALUE.getPROFDATE()+"T" +epROFVALUE.getPROFTIME()+".000");
			
			predictedSeriesList.add(predictedSeries);
		}
		
		
		return predictedSeriesList;
	} 
	
	private List<PastSeries> getPastPrice(HourlyPriceResponse response) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		
		
		List<PastSeries> pastSeriesList = new ArrayList<>();
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		for (HourlyPrice hourlyPrice : response.getHourlyPriceList()) {
			
			
			for (int i = cal.get(Calendar.HOUR_OF_DAY) ; i > 0 ;i--) {
				
				String methodName = "getPriceHr"+StringUtils.leftPad(String.valueOf(i), 2, '0');
				
				String price = (String) getMethodRun(hourlyPrice, methodName);

				PastSeries pastSeries = new PastSeries();
				
				if (org.apache.commons.lang3.StringUtils.isNotBlank(price) ) {
					pastSeries.setPrice(new BigDecimal(String.format("%.5f", Double.parseDouble(price))));
				} else {
					pastSeries.setPrice(new BigDecimal(0.00));
				}
				pastSeries.setTime(formatter.format(cal.getTime())+"T"+i+":00:00.000");
				
				pastSeriesList.add(pastSeries);
			
			}
		}
		
		
		return pastSeriesList;
	} 
	
	private Current getGMDCurrentPrice(Holder<BigDecimal> exCurrentPrice, Holder<String> exCurrentDate,
			Holder<XMLGregorianCalendar> exCurrentTime) {
		
		Current current = new Current();
		current.setPrice(exCurrentPrice.value);
		
		current.setLastUpdated(exCurrentDate.value+"T" +exCurrentTime.value+".000");
		
		return current;
	}
	
	private GMDStatementBreakDownResponse handleGMDStatementResponse(Holder<ZesGmdStmt>  holderZesGmdStmt, Holder<ZettGmdRetchr> holderZettGmdRetchr) {

		GMDStatementBreakDownResponse response = new GMDStatementBreakDownResponse();

		ZesGmdStmt zesGmdStmt = holderZesGmdStmt.value ;
	
		
		response.setTotalCost(zesGmdStmt.getUseChrg());
		response.setTotalUsage(zesGmdStmt.getCusage());
		
		List<Breakdown> breakdown = new ArrayList<>();
		
		List<GMDReturnCharge> gmdReturnChargeList = getReturnCharge(holderZettGmdRetchr);
		
		breakdown.add(wholeSaleitemBreakDown(zesGmdStmt, WHOLESALE_ELECTRICITY));
		
		breakdown.add(tduDeliveryitemBreakDown(zesGmdStmt, TDU_DELIVERY_CHARGES));
		
		breakdown.add(gmdMemberBreakDown(zesGmdStmt, GMD_MEMBERSHIP));
		breakdown.add(taxesBreakDown(zesGmdStmt, TAXES_FEES));
		
		
		response.setBreakdown(breakdown);
		response.setReturnCharge(gmdReturnChargeList);

		return response;

	}

	
	private Breakdown wholeSaleitemBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		Breakdown wholesaleElecBreakDown = new Breakdown();
		
		List<Costs> wholeSaleCost = new ArrayList<>();
		
		Costs costs = new Costs();
		costs.setItem(SOLAR_FEE);
		costs.setCost(zesGmdStmt.getSolarFee());
		
		wholeSaleCost.add(costs);
		
		costs = new Costs();
		costs.setItem(ANCILLARY_SERVICES);
		costs.setCost(zesGmdStmt.getAnclServ());
		
		wholeSaleCost.add(costs);
		
		costs = new Costs();
		costs.setItem(ELECTRICITY_USAGE);
		costs.setCost(zesGmdStmt.getUseChrg());
		
		wholeSaleCost.add(costs);
		
		wholesaleElecBreakDown.setGroup(group);

		
		wholesaleElecBreakDown.setTotalCost(zesGmdStmt.getSolarFee() .add(zesGmdStmt.getAnclServ()).add(zesGmdStmt.getUseChrg()));
		wholesaleElecBreakDown.setCosts(wholeSaleCost);
		return wholesaleElecBreakDown;
	}
	
	private Breakdown tduDeliveryitemBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown tduDeliveryitemBreakDown = new Breakdown();
		
		List<Costs> wholeSaleCost = new ArrayList<>();
		
		Costs costs = new Costs();
		costs.setItem(TDU_DELIVERY_CHARGES);
		costs.setCost(zesGmdStmt.getTduDely());
		
		wholeSaleCost.add(costs);
		
		costs = new Costs();
		costs.setItem(QUALITY_OTHER_CREDIT);
		costs.setCost(zesGmdStmt.getServQual());
		
		wholeSaleCost.add(costs);
		
		tduDeliveryitemBreakDown.setGroup(group);
		tduDeliveryitemBreakDown.setTotalCost(zesGmdStmt.getTduDely() .add(zesGmdStmt.getServQual()));
		tduDeliveryitemBreakDown.setCosts(wholeSaleCost);
		
		return tduDeliveryitemBreakDown;
	}	
	
	private Breakdown gmdMemberBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdMemberBreakDown = new Breakdown();
		
	
		
		gmdMemberBreakDown.setGroup(group);
		gmdMemberBreakDown.setTotalCost(zesGmdStmt.getMemFee());
		gmdMemberBreakDown.setTotalCost(zesGmdStmt.getMemFee());
		return gmdMemberBreakDown;
	}
	
	private Breakdown taxesBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdTaxesBreakDown = new Breakdown();
		
		gmdTaxesBreakDown.setGroup(group);
		gmdTaxesBreakDown.setTotalCost(zesGmdStmt.getTax());
		gmdTaxesBreakDown.setTotalCost(zesGmdStmt.getTax());

		
		
		return gmdTaxesBreakDown;
	}	
	
	private Object getMethodRun(Object obj, String methodName) {
		
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
		
			method.setAccessible(true);
			return method.invoke(obj, null);
		} catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
			logger.error("Exception getMethodRun {} ",ex);
		}
		return 0.0;

	}
}
