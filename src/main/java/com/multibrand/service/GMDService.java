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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.multibrand.dto.request.MoveOutRequest;
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
import com.multibrand.vo.response.gmd.MoveOutResponse;
import com.multibrand.vo.response.gmd.PastSeries;
import com.multibrand.vo.response.gmd.PredictedSeries;
import com.multibrand.vo.response.gmd.PriceSpikeAlertResponse;
import com.multibrand.vo.response.gmd.Pricing;
import com.multibrand.vo.response.gmd.ProjectedPrice;
import com.multibrand.vo.response.gmd.ProjectedPriceItem;
import com.multibrand.vo.response.gmd.SpikeProjectedPrice;
import com.multibrand.vo.response.gmd.ZoneCa;
import com.multibrand.vo.response.gmd.ZoneCaItem;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUTResponse;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUTRfcException;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUT_Type;
import com.nrg.cxfstubs.gmdprice.EPROFVALUE;
import com.nrg.cxfstubs.gmdprice.TEPROFVALUES;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE_Service;
import com.nrg.cxfstubs.gmdpricespike.ZEISUGMDPRICESPIKEALERT;
import com.nrg.cxfstubs.gmdpricespike.ZEISUGMDPRICESPIKEALERTResponse;
import com.nrg.cxfstubs.gmdpricespike.ZEISUGMDPRICESPIKEALERT_Type;
import com.nrg.cxfstubs.gmdpricespike.ZTTGMDZONECA;
import com.nrg.cxfstubs.gmdpricespike.ZTTZONEPROJPRICE;
import com.nrg.cxfstubs.gmdpricespike.ZZSGMDZONECA;
import com.nrg.cxfstubs.gmdpricespike.ZZSZONEPROJPRICE;
import com.nrg.cxfstubs.gmdstatement.ZEIsuGetGmdStmtResponse;
import com.nrg.cxfstubs.gmdstatement.ZEIsuGetGmdStmt_Type;
import com.nrg.cxfstubs.gmdstatement.ZesGmdRetchr;
import com.nrg.cxfstubs.gmdstatement.ZesGmdStmt;
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
	
	@Autowired
	@Qualifier("webServiceTemplateForGMDStatement")
	private WebServiceTemplate webServiceTemplateForGMDStatement;
	
	@Autowired
	@Qualifier("webServiceTemplateForGMDCreateMoveOut")
	private WebServiceTemplate webServiceTemplateForGMDCreateMoveOut; 
	
	@Autowired
	@Qualifier("webServiceTemplateForGMDPriceSpike")
	private WebServiceTemplate webServiceTemplateForGMDPriceSpike;


	/**
	 * This profile call will do the call to the logging framework 
	 * @param accountNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	public GMDStatementBreakDownResponse getGMDStatementDetails(String accountNumber, String companyCode, 
			String esiId, String year, String month ,boolean isAllInPriceCall, String sessionId) throws NRGException {
		
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::START"); 
				
		GMDStatementBreakDownResponse gmdStatementBreakDownResp = null;
		
		long startTime = CommonUtil.getStartTime();
		Long endTime = null;
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
          		
 		
		try{

			
			com.nrg.cxfstubs.gmdstatement.ObjectFactory factory = new com.nrg.cxfstubs.gmdstatement.ObjectFactory();
			
			ZEIsuGetGmdStmt_Type wsRequest = factory.createZEIsuGetGmdStmt_Type();

			wsRequest.setCompCode(companyCode);
			wsRequest.setContAcct(accountNumber);
			wsRequest.setEsid(esiId);
			wsRequest.setStmtMonth(month);
			wsRequest.setStmtYear(year);
			wsRequest.setAllinPrice(isAllInPriceCall ? "X" :"");
			
			startTime = Calendar.getInstance().getTimeInMillis();
			
			ZEIsuGetGmdStmtResponse  zEIsuGetGmdStmtResponse = (ZEIsuGetGmdStmtResponse) webServiceTemplateForGMDStatement.marshalSendAndReceive(wsRequest);

			endTime = Calendar.getInstance().getTimeInMillis();
			logger.info("Time taken by service is ={}" , (endTime - startTime));
									
			gmdStatementBreakDownResp = handleGMDStatementResponse(zEIsuGetGmdStmtResponse);
			
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
	
	private List<GMDReturnCharge> getReturnCharge(ZettGmdRetchr holderZettGmdRetchr) {
		
		List<GMDReturnCharge> gmdReturnChargeList = new ArrayList<>();
				
		for (ZesGmdRetchr zesGmdRetchr : holderZettGmdRetchr.getItem()) {
			
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
		
		if (response.getHourlyPriceList() != null)  {
			for (HourlyPrice hourlyPrice : response.getHourlyPriceList()) {
				
				
				for (int i = cal.get(Calendar.HOUR_OF_DAY) ; i > 0 ;i--) {
					
					String methodName = "getPriceHr"+StringUtils.leftPad(String.valueOf(i), 2, '0');
					
					String price = (String) getMethodRun(hourlyPrice, methodName);
	
					PastSeries pastSeries = new PastSeries();
					
					if (org.apache.commons.lang3.StringUtils.isNotBlank(price) ) {
						pastSeries.setPrice(new BigDecimal(String.format("%.5f", Double.parseDouble(price))));
						pastSeries.setTime(formatter.format(cal.getTime())+"T"+i+":00:00.000");
						
						pastSeriesList.add(pastSeries);
					}
					
				
				}
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
	
	private GMDStatementBreakDownResponse handleGMDStatementResponse(ZEIsuGetGmdStmtResponse zEIsuGetGmdStmtResponse) {
			
			//Holder<ZesGmdStmt>  holderZesGmdStmt,  Holder<BigDecimal> holderAvgPrice ,Holder<ZettGmdRetchr> holderZettGmdRetchr, Holder<String>holderLastBillDate) {

		GMDStatementBreakDownResponse response = new GMDStatementBreakDownResponse();

		ZesGmdStmt zesGmdStmt = zEIsuGetGmdStmtResponse.getStmt();
	
		BigDecimal totalCost = new BigDecimal("0.00");
		
		
		response.setAllInPrice(zEIsuGetGmdStmtResponse.getAllinCharge());
		response.setAvgPrice(zEIsuGetGmdStmtResponse.getAvgPrice() !=null ? zEIsuGetGmdStmtResponse.getAvgPrice() : null);
		
		List<Breakdown> breakdown = new ArrayList<>();
		
		List<GMDReturnCharge> gmdReturnChargeList = getReturnCharge(zEIsuGetGmdStmtResponse.getRetChrg());
		
		breakdown.add(energyChargeitemBreakDown(zesGmdStmt, GMD_ENERGY_CHARGE));
		

		
		breakdown.add(tduDeliveryitemBreakDown(zesGmdStmt, TDSP_DELIVERY_CHARGES));
		
		
		breakdown.add(gmdFixedRateBreakDown(zesGmdStmt, FIXED_RATE_THIRD_PARTY_CHRG));
		
		breakdown.add(gmdSolarRecsBreakDown(zesGmdStmt, SOLAR_RECS));
		
		breakdown.add(gmdMemberBreakDown(zesGmdStmt, GMD_MEMBERSHIP));
		breakdown.add(taxesBreakDown(zesGmdStmt, TAXES_FEES));

		totalCost = totalCost.add(zesGmdStmt.getUseChrg() .add(zesGmdStmt.getCusageAdj())
				.add(zesGmdStmt.getTduDely())
				.add(zesGmdStmt.getAnclServ()).
				add(zesGmdStmt.getSolarFee())
				.add(zesGmdStmt.getMemFee())
				.add(zesGmdStmt.getTax()));
		
		
		response.setTotalCost(totalCost);
		response.setBreakdown(breakdown);
		response.setReturnCharge(gmdReturnChargeList);		
		response.setTotalUsage(zesGmdStmt.getCusage());
		response.setLastBillDate(zEIsuGetGmdStmtResponse.getLastBildate() !=null ? zEIsuGetGmdStmtResponse.getLastBildate() : null);
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

		
		energyChargeBreakDown.setTotalCost(zesGmdStmt.getUseChrg() .add(zesGmdStmt.getCusageAdj()));
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
	
	public MoveOutResponse createMoveOut(MoveOutRequest moveOutRequest) {
		String moveOutDocId = "";
		MoveOutResponse moveOutResponse = new MoveOutResponse();
		try {
			com.nrg.cxfstubs.gmdmoveout.ObjectFactory factory = new com.nrg.cxfstubs.gmdmoveout.ObjectFactory();
			ZEISUCREATEMOVEOUT_Type wsRequest = factory.createZEISUCREATEMOVEOUT_Type();
			wsRequest.setCONTACC(moveOutRequest.getContractAccountNumber());
			wsRequest.setESID(moveOutRequest.getEsiId());
			wsRequest.setMOUTDATE(moveOutRequest.getMoveOutDate());
			wsRequest.setMOUTREASON(moveOutRequest.getMoveOutReason());

			ZEISUCREATEMOVEOUTResponse response = (ZEISUCREATEMOVEOUTResponse) webServiceTemplateForGMDCreateMoveOut
					.marshalSendAndReceive(wsRequest);
			moveOutDocId = response.getMOUTDOC();
			if (moveOutDocId != null) {
				moveOutResponse.setMoveOutDocNumber(moveOutDocId);
				moveOutResponse.setResultCode(RESULT_CODE_SUCCESS);
				moveOutResponse.setResultDescription(MSG_SUCCESS);
			}
			
		}catch (RuntimeException ex) {
			logger.error("Exception Occured in RuntimeException  createMoveOut {} ", ex.getMessage());
			try {
				
				ZEISUCREATEMOVEOUTRfcException   zEISUCREATEMOVEOUTException = 
						(ZEISUCREATEMOVEOUTRfcException) CommonUtil.unmarshallSoapFault(CommonUtil.getTagValue(ex.getMessage(), "detail"), ZEISUCREATEMOVEOUTRfcException.class);		

				moveOutResponse.setResultCode(zEISUCREATEMOVEOUTException.getName().value());
				moveOutResponse.setResultDescription(zEISUCREATEMOVEOUTException.getText());
			} catch (Exception e) {
				logger.error("Exception Occured in  createMoveOut {} ", e);
				moveOutResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
				moveOutResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
			} 
		} catch (Exception ex) {
			logger.error("Exception Occured in  createMoveOut {} ", ex);
			moveOutResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			moveOutResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return moveOutResponse;
	}
	
	public PriceSpikeAlertResponse getGMDPriceSpikeAlert() {
		com.nrg.cxfstubs.gmdpricespike.ObjectFactory factory = new com.nrg.cxfstubs.gmdpricespike.ObjectFactory();
		ZEISUGMDPRICESPIKEALERT_Type  wsRequest = factory.createZEISUGMDPRICESPIKEALERT_Type();
	
		wsRequest.setIMDATE("");
		wsRequest.setIMTHRESHOLD(null);
		wsRequest.setIMTIME(null);
		wsRequest.setIMZONE("");
		ZEISUGMDPRICESPIKEALERTResponse response =(ZEISUGMDPRICESPIKEALERTResponse) webServiceTemplateForGMDPriceSpike.marshalSendAndReceive(wsRequest);
		PriceSpikeAlertResponse priceSpikeAlertResponse = setPriceSpikeAlertResponse(response);
		return priceSpikeAlertResponse;
	}
	
	public PriceSpikeAlertResponse setPriceSpikeAlertResponse(ZEISUGMDPRICESPIKEALERTResponse response) {
		PriceSpikeAlertResponse priceSpikeAlertResponse = new PriceSpikeAlertResponse();
		List<ProjectedPriceItem> projectedPriceItems = new ArrayList<>();
		List<ProjectedPriceItem> spikeProjectedPriceItems = new ArrayList<>();
		List<ZoneCaItem> zoneCaItems = new ArrayList<>();
		
		
		
		ZTTZONEPROJPRICE price = response.getEXTPROJECTEDPRICE();
		List<ZZSZONEPROJPRICE> projectedItemList = price.getItem();
		if(projectedItemList!=null && !projectedItemList.isEmpty()) {
			for(ZZSZONEPROJPRICE priceItem : projectedItemList) {
				ProjectedPriceItem item = new ProjectedPriceItem();
				item.setProfDate(priceItem.getPROFDATE());
				item.setProfTime(priceItem.getPROFTIME());
				item.setProfValue(priceItem.getPROFVALUE());
				item.setZone(priceItem.getZZONE());
				projectedPriceItems.add(item);
			}
		}
		
		ZTTZONEPROJPRICE spikePrice = response.getEXTSPIKEDPROJPRICE();
		List<ZZSZONEPROJPRICE> spikePriceItemList = spikePrice.getItem();
		if(spikePriceItemList!=null && !spikePriceItemList.isEmpty()) {
			for(ZZSZONEPROJPRICE spikePriceItem : spikePriceItemList) {
				ProjectedPriceItem spikeItem = new ProjectedPriceItem();
				spikeItem.setProfDate(spikePriceItem.getPROFDATE());
				spikeItem.setProfTime(spikePriceItem.getPROFTIME());
				spikeItem.setProfValue(spikePriceItem.getPROFVALUE());
				spikeItem.setZone(spikePriceItem.getZZONE());
				spikeProjectedPriceItems.add(spikeItem);
			}
		}
		
		ZTTGMDZONECA zoneCaList = response.getEXTZONECA();
		List<ZZSGMDZONECA> caList = zoneCaList.getItem();
		if(caList!=null && !caList.isEmpty()) {
			for(ZZSGMDZONECA ca : caList) {
				ZoneCaItem zoneCaItem = new ZoneCaItem();
				zoneCaItem.setVkont(ca.getVKONT());
				zoneCaItem.setZone(ca.getZZONE());
				zoneCaItems.add(zoneCaItem);
			}
		}
		//setting values
		priceSpikeAlertResponse.setPriceSpikeFound(response.getEXPRICESPIKEFOUND());
		priceSpikeAlertResponse.setMessage(response.getEXMESSAGE());
		
		ProjectedPrice projectedPrice = new ProjectedPrice();
		projectedPrice.setProjectedPriceItems(spikeProjectedPriceItems);
		
		SpikeProjectedPrice spikeProjectedPrice = new SpikeProjectedPrice();
		spikeProjectedPrice.setSpikeProjectedPriceItems(spikeProjectedPriceItems);
		
		ZoneCa zoneCa = new ZoneCa();
		zoneCa.setZoneItems(zoneCaItems);
		
		
		priceSpikeAlertResponse.setProjectedPrice(projectedPrice);
		priceSpikeAlertResponse.setSpikeProjectedPrice(spikeProjectedPrice);
		priceSpikeAlertResponse.setZoneCa(zoneCa);
		return priceSpikeAlertResponse;
	}
}
