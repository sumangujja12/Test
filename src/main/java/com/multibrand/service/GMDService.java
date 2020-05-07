package com.multibrand.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
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
				
		GMDStatementBreakDownResponse gmdStatementBreakDownResp = null;
		
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
		URL url =  ZEISUGETGMDSTMT_Service.class.getResource("Z_E_ISU_GET_GMD_STMT.wsdl");
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

         Holder<ZetGmdInvdate>  holderZetGmdInvdate = new Holder<>();
         holderZetGmdInvdate.value = zetGmdInvdate;
         
         Holder<ZettGmdRetchr> holderZettGmdRetchr =  new Holder<>();
         
         
         Holder<BigDecimal> holderAvgPrice = new Holder<>(); 
         
         Holder<ZesGmdStmt>  holderZesGmdStmt = new Holder<>();
         holderZesGmdStmt.value = zesGmdStmt;
         
         Holder<ZetGmdStmt>  holderZetGmdStmt = new Holder<>();
         holderZetGmdStmt.value = zetGmdStmt;
		
		try{
			
			stub.zeIsuGetGmdStmt(companyCode, accountNumber, esiId, month, year, holderAvgPrice, holderZetGmdInvdate, holderZettGmdRetchr,  holderZesGmdStmt, holderZetGmdStmt);
						
			gmdStatementBreakDownResp = handleGMDStatementResponse(holderZesGmdStmt, holderAvgPrice, holderZettGmdRetchr);
			
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
		
		GMDPricingResponse gmdPricingResp = null;
		
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
			gmdPricingResp = handleGMDCurrentPriceResponse(exTepProfValues, exCurrentDate, exCurrentPrice, exCurrentTime, response );
			
		}catch(Exception ex){
			utilityloggerHelper.logTransaction("getGMDPriceDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);
			
		}
		logger.info("GMDService.getGMDPriceDetails::::::::::::::::::::after call");
       
		return gmdPricingResp;
	}	
	
	private GMDPricingResponse handleGMDCurrentPriceResponse( Holder<TEPROFVALUES> exTepProfValues, Holder<String> exCurrentDate,
			Holder<BigDecimal> exCurrentPrice,  Holder<XMLGregorianCalendar> exCurrentTime, HourlyPriceResponse hourlyPriceResponse)  {

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
	
	private List<PastSeries> getPastPrice(HourlyPriceResponse response)  {
		
		
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
					pastSeries.setPrice(new BigDecimal("0.00"));
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
	
	private GMDStatementBreakDownResponse handleGMDStatementResponse(Holder<ZesGmdStmt>  holderZesGmdStmt,  Holder<BigDecimal> holderAvgPrice ,Holder<ZettGmdRetchr> holderZettGmdRetchr) {

		GMDStatementBreakDownResponse response = new GMDStatementBreakDownResponse();

		ZesGmdStmt zesGmdStmt = holderZesGmdStmt.value ;
	
		
		response.setTotalCost(zesGmdStmt.getUseChrg());
		response.setTotalUsage(zesGmdStmt.getCusage());
		
		response.setAvgPrice(holderAvgPrice !=null ? holderAvgPrice.value : null);
		
		List<Breakdown> breakdown = new ArrayList<>();
		
		List<GMDReturnCharge> gmdReturnChargeList = getReturnCharge(holderZettGmdRetchr);
		
		breakdown.add(energyChargeitemBreakDown(zesGmdStmt, GMD_ENERGY_CHARGE));
		
		breakdown.add(tduDeliveryitemBreakDown(zesGmdStmt, TDSP_DELIVERY_CHARGES));
		
		
		breakdown.add(gmdFixedRateBreakDown(zesGmdStmt, FIXED_RATE_THIRD_PARTY_CHRG));
		
		breakdown.add(gmdSolarRecsBreakDown(zesGmdStmt, SOLAR_RECS));
		
		breakdown.add(gmdMemberBreakDown(zesGmdStmt, GMD_MEMBERSHIP));
		breakdown.add(taxesBreakDown(zesGmdStmt, TAXES_FEES));
		
		
		response.setBreakdown(breakdown);
		response.setReturnCharge(gmdReturnChargeList);		

		return response;

	}

	
	private Breakdown energyChargeitemBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		Breakdown energyChargeBreakDown = new Breakdown();
		
		List<Costs> energyChargeSaleCost = new ArrayList<>();
		
		Costs costs = new Costs();
		costs.setItem(GMD_ENERGY_CHARGE);
		costs.setCost(zesGmdStmt.getUseChrg());
		
		energyChargeSaleCost.add(costs);

		
		costs = new Costs();
		costs.setItem(GMD_ENERGY_TRUE_UP);
		costs.setCost(zesGmdStmt.getCusageAdj());
		
		energyChargeSaleCost.add(costs);
		
		costs = new Costs();
		costs.setItem(GMD_USAGE_TRUE_UP);
		costs.setCost(new BigDecimal("0.00"));
		
		energyChargeSaleCost.add(costs);
		
		energyChargeBreakDown.setGroup(group);

		
		energyChargeBreakDown.setTotalCost(zesGmdStmt.getCusage() .add(zesGmdStmt.getCusageAdj()).add(zesGmdStmt.getCusageAdj()));
		energyChargeBreakDown.setCosts(energyChargeSaleCost);
		return energyChargeBreakDown;
	}
	
	private Breakdown tduDeliveryitemBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown tduDeliveryitemBreakDown = new Breakdown();
		
		List<Costs> wholeSaleCost = new ArrayList<>();
		
		Costs costs = new Costs();
		costs.setItem(TDSP_DELIVERY_CHARGES);
		costs.setCost(zesGmdStmt.getTduDely());
		
		wholeSaleCost.add(costs);
		
		costs = new Costs();
		costs.setItem(QUALITY_OTHER_CREDIT);
		costs.setCost(new BigDecimal("0.00"));
		
		wholeSaleCost.add(costs);
		
		tduDeliveryitemBreakDown.setGroup(group);
		tduDeliveryitemBreakDown.setTotalCost(zesGmdStmt.getTduDely());
		tduDeliveryitemBreakDown.setCosts(wholeSaleCost);
		
		return tduDeliveryitemBreakDown;
	}	
	
	private Breakdown gmdMemberBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdMemberBreakDown = new Breakdown();
		
	
		
		gmdMemberBreakDown.setGroup(group);
		gmdMemberBreakDown.setTotalCost(zesGmdStmt.getMemFee());
	
		return gmdMemberBreakDown;
	}
	
	
	
	private Breakdown gmdFixedRateBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdFixedBreakDown = new Breakdown();
		
	
		
		gmdFixedBreakDown.setGroup(group);
		gmdFixedBreakDown.setTotalCost(zesGmdStmt.getAnclServ());
	
		return gmdFixedBreakDown;
	}
	
	
	private Breakdown gmdSolarRecsBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdSolarBreakDown = new Breakdown();
		
	
		
		gmdSolarBreakDown.setGroup(group);
		gmdSolarBreakDown.setTotalCost(zesGmdStmt.getSolarFee());
	
		return gmdSolarBreakDown;
	}
	
	private Breakdown taxesBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdTaxesBreakDown = new Breakdown();
		
		List<Costs> taxCost = new ArrayList<>();
		
		Costs costs = new Costs();
		costs.setItem(SALES_TAX);
		costs.setCost(zesGmdStmt.getTax());
		
		taxCost.add(costs);
		
		costs = new Costs();
		costs.setItem(GROSS_RECP_TAX);
		costs.setCost(new BigDecimal("0.00"));
		
		taxCost.add(costs);
		
		costs = new Costs();
		costs.setItem(PUC_FEE);
		costs.setCost(new BigDecimal("0.00"));
		
		taxCost.add(costs);		
		
		
		gmdTaxesBreakDown.setGroup(group);
		gmdTaxesBreakDown.setTotalCost(zesGmdStmt.getTax());
		gmdTaxesBreakDown.setCosts(taxCost);
		
		return gmdTaxesBreakDown;
	}	
	
	private Object getMethodRun(Object obj, String methodName) {
		
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
		
			return method.invoke(obj, null);
		} catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
			logger.error("Exception getMethodRun {} ",ex);
		}
		return 0.0;

	}
}
