package com.multibrand.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.multibrand.dto.request.GmdMdStmtRequest;
import com.multibrand.dto.request.MoveOutRequest;
import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.DateUtil;
import com.multibrand.vo.response.gmd.Breakdown;
import com.multibrand.vo.response.gmd.Costs;
import com.multibrand.vo.response.gmd.Current;
import com.multibrand.vo.response.gmd.GMDPricingResponse;
import com.multibrand.vo.response.gmd.GMDReturnCharge;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;
import com.multibrand.vo.response.gmd.GmdHourHeadsSpikeResponse;
import com.multibrand.vo.response.gmd.GmdMdDailyStmtItemResponse;
import com.multibrand.vo.response.gmd.GmdMdDailyStmtResponse;
import com.multibrand.vo.response.gmd.GmdMdMonthlyStmtItemResponse;
import com.multibrand.vo.response.gmd.GmdMdMonthlyStmtResponse;
import com.multibrand.vo.response.gmd.GmdMdStmtResponse;
import com.multibrand.vo.response.gmd.GmdZoneCa;
import com.multibrand.vo.response.gmd.GmdZoneCaResponse;
import com.multibrand.vo.response.gmd.HourlyPrice;
import com.multibrand.vo.response.gmd.HourlyPriceResponse;
import com.multibrand.vo.response.gmd.LmpAllZonesPriceResponse;
import com.multibrand.vo.response.gmd.LmpPriceItem;
import com.multibrand.vo.response.gmd.LmpPriceSpikeResponse;
import com.multibrand.vo.response.gmd.LmpSpikedPriceResponse;
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
import com.nrg.cxfstubs.gmd.hourahead.spike.ZEISUGMDHOURAHEADSPIKEResponse;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUTResponse;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUTRfcException;
import com.nrg.cxfstubs.gmdmoveout.ZEISUCREATEMOVEOUT_Type;
import com.nrg.cxfstubs.gmdprice.EPROFVALUE;
import com.nrg.cxfstubs.gmdprice.TEPROFVALUES;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE_Service;
import com.nrg.cxfstubs.gmdpricespike.ZEISUGMDPRICESPIKEALERTResponse;
import com.nrg.cxfstubs.gmdpricespike.ZTTGMDZONECA;
import com.nrg.cxfstubs.gmdpricespike.ZTTZONEPROJPRICE;
import com.nrg.cxfstubs.gmdpricespike.ZZSGMDZONECA;
import com.nrg.cxfstubs.gmdpricespike.ZZSZONEPROJPRICE;
import com.nrg.cxfstubs.gmdstatement.ZEIsuGetGmdStmtResponse;
import com.nrg.cxfstubs.gmdstatement.ZEIsuGetGmdStmt_Type;
import com.nrg.cxfstubs.gmdstatement.ZesGmdRetchr;
import com.nrg.cxfstubs.gmdstatement.ZesGmdStmt;
import com.nrg.cxfstubs.gmdstatement.ZettGmdRetchr;
import com.nrg.cxfstubs.lmp.gmdpricespike.ZEISUGMDLMPPRICESPIKEResponse;
import com.nrg.cxfstubs.lmp.gmdpricespike.ZEISUGMDLMPPRICESPIKE_Type;
import com.nrg.cxfstubs.md.gmdstatement.ZEIsuGetGmdMdStmtResponse;
import com.nrg.cxfstubs.md.gmdstatement.ZEIsuGetGmdMdStmt_Type;
import com.nrg.cxfstubs.md.gmdstatement.ZesGmdDaywiseStmt;
import com.nrg.cxfstubs.md.gmdstatement.ZesGmdMnlyStmt;


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
	
	@Autowired
	@Qualifier("webServiceTemplateForMDStmt")
	private WebServiceTemplate webServiceTemplateForMDStmt;
	
	@Autowired
	@Qualifier("webServiceTemplateForLmpPriceSpike")
	private WebServiceTemplate webServiceTemplateForLmpPriceSpike;
	
	@Autowired
	@Qualifier("webServiceTemplateForGmdHourHeadSpike")
	private WebServiceTemplate webServiceTemplateForGmdHourHeadSpike;


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
					
					
					for (int j = 0; j <= 45; j += 15) {
												
						String methodName = "getPriceHr"+StringUtils.leftPad(String.valueOf(i), 2, '0')+StringUtils.leftPad(String.valueOf(j), 2, '0');
						String price = (String) getMethodRun(hourlyPrice, methodName);
						
						PastSeries pastSeries = new PastSeries();
						
						if (org.apache.commons.lang3.StringUtils.isNotBlank(price) ) {
							pastSeries.setPrice(new BigDecimal(String.format("%.5f", Double.parseDouble(price))));
							pastSeries.setTime(formatter.format(cal.getTime())+"T"+i+":"+j+":00.000");
							
							pastSeriesList.add(pastSeries);
						}
						
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
			
			if ( response.getRETURN() != null && response.getRETURN().getTYPE().equalsIgnoreCase(TYPE_E)) {
				   moveOutResponse.setResultCode(response.getRETURN().getNUMBER());
					moveOutResponse.setResultDescription(response.getRETURN().getMESSAGE());
			} else {				
				moveOutDocId = response.getMOUTDOC();
				if (moveOutDocId != null) {
					moveOutResponse.setMoveOutDocNumber(moveOutDocId);
					moveOutResponse.setResultCode(RESULT_CODE_SUCCESS);
					moveOutResponse.setResultDescription(MSG_SUCCESS);
				}
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

		PriceSpikeAlertResponse priceSpikeAlertResponse = new PriceSpikeAlertResponse();
		try {
			com.nrg.cxfstubs.gmdpricespike.ObjectFactory factory = new com.nrg.cxfstubs.gmdpricespike.ObjectFactory();
			com.nrg.cxfstubs.gmdpricespike.ZEISUGMDPRICESPIKEALERT_Type wsRequest = factory
					.createZEISUGMDPRICESPIKEALERT_Type();
			wsRequest.setIMDATE("");
			wsRequest.setIMTHRESHOLD(null);
			wsRequest.setIMTIME(null);
			wsRequest.setIMZONE("");
			ZEISUGMDPRICESPIKEALERTResponse response = (ZEISUGMDPRICESPIKEALERTResponse) webServiceTemplateForGMDPriceSpike
					.marshalSendAndReceive(wsRequest);
			priceSpikeAlertResponse = setPriceSpikeAlertResponse(response);
			priceSpikeAlertResponse.setResultCode(RESULT_CODE_SUCCESS);
			priceSpikeAlertResponse.setResultDescription(MSG_SUCCESS);
		} catch (Exception ex) {
			logger.error("Exception Occured in  createMoveOut {} ", ex);
			priceSpikeAlertResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			priceSpikeAlertResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return priceSpikeAlertResponse;
	}
	
	public PriceSpikeAlertResponse setPriceSpikeAlertResponse(ZEISUGMDPRICESPIKEALERTResponse response) {
		PriceSpikeAlertResponse priceSpikeAlertResponse = new PriceSpikeAlertResponse();
		List<ProjectedPriceItem> projectedPriceItems = new ArrayList<>();
		List<ProjectedPriceItem> spikeProjectedPriceItems = new ArrayList<>();
		List<ZoneCaItem> zoneCaItems = new ArrayList<>();

		ZTTZONEPROJPRICE price = response.getEXTPROJECTEDPRICE();
		List<ZZSZONEPROJPRICE> projectedItemList = price.getItem();
		if (projectedItemList != null && !projectedItemList.isEmpty()) {
			for (ZZSZONEPROJPRICE priceItem : projectedItemList) {
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
		if (spikePriceItemList != null && !spikePriceItemList.isEmpty()) {
			for (ZZSZONEPROJPRICE spikePriceItem : spikePriceItemList) {
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
		if (caList != null && !caList.isEmpty()) {
			for (ZZSGMDZONECA ca : caList) {
				ZoneCaItem zoneCaItem = new ZoneCaItem();
				zoneCaItem.setVkont(ca.getVKONT());
				zoneCaItem.setZone(ca.getZZONE());
				zoneCaItems.add(zoneCaItem);
			}
		}
		// setting values
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
	
	public GmdMdStmtResponse getGmdMdStmt(GmdMdStmtRequest gmdMdStmtRequest) {
		GmdMdStmtResponse gmdMdStmtResponse = new GmdMdStmtResponse();
		try {
			SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
			
			com.nrg.cxfstubs.md.gmdstatement.ObjectFactory factory = new com.nrg.cxfstubs.md.gmdstatement.ObjectFactory();
			ZEIsuGetGmdMdStmt_Type wsRequest = factory.createZEIsuGetGmdMdStmt_Type();
			wsRequest.setCompCode(gmdMdStmtRequest.getCompanyCode());
			wsRequest.setContAcct(gmdMdStmtRequest.getContractAccountNumber());
			wsRequest.setEsid(gmdMdStmtRequest.getEsiId());
			wsRequest.setFromDay(format.format(DateUtil.getDate(gmdMdStmtRequest.getStmtFromDate(),yyyy_MM_dd)));
			wsRequest.setFromMonth(Integer.toString(DateUtil.getMonthInt(DateUtil.getDate(gmdMdStmtRequest.getStmtFromDate(),yyyy_MM_dd))));
			wsRequest.setFromYear(DateUtil.getYear(DateUtil.getDate(gmdMdStmtRequest.getStmtFromDate(),yyyy_MM_dd)));
			wsRequest.setStmtType(gmdMdStmtRequest.getStmtType());
			wsRequest.setToDay(format.format(DateUtil.getDate(gmdMdStmtRequest.getStmtToDate(),yyyy_MM_dd)));
			wsRequest.setToMonth(Integer.toString(DateUtil.getMonthInt(DateUtil.getDate(gmdMdStmtRequest.getStmtToDate(),yyyy_MM_dd))));
			wsRequest.setToYear(DateUtil.getYear(DateUtil.getDate(gmdMdStmtRequest.getStmtToDate(),yyyy_MM_dd)));

			ZEIsuGetGmdMdStmtResponse response = (ZEIsuGetGmdMdStmtResponse) webServiceTemplateForMDStmt
					.marshalSendAndReceive(wsRequest);
			if (response == null) {
				gmdMdStmtResponse.setResultCode(RESULT_CODE_NO_DATA);
				gmdMdStmtResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			} else {
				gmdMdStmtResponse = setGmdMdStmtData(response);
				gmdMdStmtResponse.setResultCode(RESULT_CODE_SUCCESS);
				gmdMdStmtResponse.setResultDescription(MSG_SUCCESS);
			}
		} catch (Exception e) {
			logger.error("Exception Occured in  getGmdMdStmt {} ", e);
			gmdMdStmtResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			gmdMdStmtResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);

		}
		return gmdMdStmtResponse;
	}
	
	public GmdMdStmtResponse setGmdMdStmtData(ZEIsuGetGmdMdStmtResponse response) {
		
		List<GmdMdMonthlyStmtItemResponse> items = new ArrayList<>();
		List<GmdMdDailyStmtItemResponse> stmtDailyDaywiseList = new ArrayList<>();
		
		
		GmdMdMonthlyStmtResponse stmtMonthlyData = new GmdMdMonthlyStmtResponse();
		GmdMdStmtResponse gmdMdStmtResponse = new GmdMdStmtResponse();
		GmdMdDailyStmtResponse stmtDailyData = new GmdMdDailyStmtResponse();

		if (response.getStmtDailyData() != null) {

			stmtDailyData.setAnciFee(response.getStmtDailyData().getAnciFee());
			stmtDailyData.setAnclServ(response.getStmtDailyData().getAnclServ());
			stmtDailyData.setBillingAmount(response.getStmtDailyData().getBillAmt());
			stmtDailyData.setCardRev(response.getStmtDailyData().getCardRev());
			stmtDailyData.setcUsage(response.getStmtDailyData().getCusage());
			stmtDailyData.setcUsageAdj(response.getStmtDailyData().getCusageAdj());
			stmtDailyData.setFromDate(response.getStmtDailyData().getFromDate());
			stmtDailyData.setIsoFee(response.getStmtDailyData().getIsoFee());
			stmtDailyData.setLoss(response.getStmtDailyData().getLoss());
			stmtDailyData.setMembershipFee(response.getStmtDailyData().getMemFee());
			stmtDailyData.setRucLrs(response.getStmtDailyData().getRucLrs());
			stmtDailyData.setServQual(response.getStmtDailyData().getServQual());
			stmtDailyData.setSolarFee(response.getStmtDailyData().getSolarFee());
			stmtDailyData.setTax(response.getStmtDailyData().getTax());
			stmtDailyData.setTdspAdj(response.getStmtDailyData().getTdspAdj());
			stmtDailyData.setTduDely(response.getStmtDailyData().getTduDely());
			stmtDailyData.setToDate(response.getStmtDailyData().getToDate());
			stmtDailyData.setUseChrg(response.getStmtDailyData().getUseChrg());
		}
		
		List<ZesGmdDaywiseStmt> dayWiseStmts = response.getStmtDailyDaywise().getItem();
		
		if (dayWiseStmts != null && !dayWiseStmts.isEmpty()) {
			
			for (ZesGmdDaywiseStmt zesGmdDaywiseStmt : dayWiseStmts) {
				
				GmdMdDailyStmtItemResponse item = new GmdMdDailyStmtItemResponse();
				
				item.setAnciFee(zesGmdDaywiseStmt.getAnciFee());
				item.setAnclServ(zesGmdDaywiseStmt.getAnclServ());
				item.setBillAmount(zesGmdDaywiseStmt.getBillAmt());
				item.setCardRev(zesGmdDaywiseStmt.getCardRev());
				item.setcUsage(zesGmdDaywiseStmt.getCusage());
				item.setcUsageAdj(zesGmdDaywiseStmt.getCusageAdj());
				item.setIsoFee(zesGmdDaywiseStmt.getIsoFee());
				item.setLoss(zesGmdDaywiseStmt.getLoss());
				item.setMembershipFee(zesGmdDaywiseStmt.getMemFee());
				item.setRucLrs(zesGmdDaywiseStmt.getRucLrs());
				item.setServQual(zesGmdDaywiseStmt.getServQual());
				item.setStmtDate(zesGmdDaywiseStmt.getDate());
				item.setTax(zesGmdDaywiseStmt.getTax());
				item.setTdspAdj(zesGmdDaywiseStmt.getTdspAdj());
				item.setTduDely(zesGmdDaywiseStmt.getTduDely());
				item.setUseChrg(zesGmdDaywiseStmt.getUseChrg());
				item.setSolarFee(zesGmdDaywiseStmt.getSolarFee());
				stmtDailyDaywiseList.add(item);
			}
			
			stmtDailyData.setStmtDailyDaywise(stmtDailyDaywiseList);

		}
		

		List<ZesGmdMnlyStmt> montlyStmts = response.getStmtMonthlyData().getItem();

		if (montlyStmts != null && montlyStmts.size() > 0) {
			for (ZesGmdMnlyStmt zesGmdMnlyStmt : montlyStmts) {
				GmdMdMonthlyStmtItemResponse item = new GmdMdMonthlyStmtItemResponse();
				item.setAnciFee(zesGmdMnlyStmt.getAnciFee());
				item.setAnclServ(zesGmdMnlyStmt.getAnclServ());
				item.setBillAmount(zesGmdMnlyStmt.getBillAmt());
				item.setCardRev(zesGmdMnlyStmt.getCardRev());
				item.setcUsage(zesGmdMnlyStmt.getCusage());
				item.setcUsageAdj(zesGmdMnlyStmt.getCusageAdj());
				item.setIsoFee(zesGmdMnlyStmt.getIsoFee());
				item.setLoss(zesGmdMnlyStmt.getLoss());
				item.setMembershipFee(zesGmdMnlyStmt.getMemFee());
				item.setRucLrs(zesGmdMnlyStmt.getRucLrs());
				item.setServQual(zesGmdMnlyStmt.getServQual());
				item.setStartMonth(zesGmdMnlyStmt.getSmonth());
				item.setStartYear(zesGmdMnlyStmt.getSyear());
				item.setTax(zesGmdMnlyStmt.getTax());
				item.setTdspAdj(zesGmdMnlyStmt.getTdspAdj());
				item.setTduDely(zesGmdMnlyStmt.getTduDely());
				item.setUseChrg(zesGmdMnlyStmt.getUseChrg());
				item.setSolarFee(zesGmdMnlyStmt.getSolarFee());
				items.add(item);
			}

		}
		stmtMonthlyData.setItems(items);
		gmdMdStmtResponse.setStmtDailyData(stmtDailyData);
		gmdMdStmtResponse.setStmtMonthlyData(stmtMonthlyData);

		return gmdMdStmtResponse;
	}
	
	public LmpPriceSpikeResponse getGmdLmpPriceSpike(String buckers) {
		LmpPriceSpikeResponse lmpPriceSpikeResponse = new LmpPriceSpikeResponse();
		try {
			com.nrg.cxfstubs.lmp.gmdpricespike.ObjectFactory factory = new com.nrg.cxfstubs.lmp.gmdpricespike.ObjectFactory();
			ZEISUGMDLMPPRICESPIKE_Type wsRequest = factory.createZEISUGMDLMPPRICESPIKE_Type();
			wsRequest.setIMBUKRS(buckers);

			ZEISUGMDLMPPRICESPIKEResponse response = (ZEISUGMDLMPPRICESPIKEResponse) webServiceTemplateForLmpPriceSpike
					.marshalSendAndReceive(wsRequest);

		

			if (response == null) {
				lmpPriceSpikeResponse.setResultCode(RESULT_CODE_NO_DATA);
				lmpPriceSpikeResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			} else {
				lmpPriceSpikeResponse = getLmpPriceData(response);
				lmpPriceSpikeResponse.setResultCode(RESULT_CODE_SUCCESS);
				lmpPriceSpikeResponse.setResultDescription(MSG_SUCCESS);
			}
		} catch (Exception e) {
			logger.error("Exception Occured in  getGmdLmpPriceSpike {} ", e);
			lmpPriceSpikeResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			lmpPriceSpikeResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return lmpPriceSpikeResponse;
	}
	
	public LmpPriceSpikeResponse getLmpPriceData(ZEISUGMDLMPPRICESPIKEResponse response) {

		LmpPriceSpikeResponse lmpPriceSpikeResponse = new LmpPriceSpikeResponse();

		lmpPriceSpikeResponse.setExMessage(response.getEXMESSAGE());
		lmpPriceSpikeResponse.setExPriceSpikeFound(response.getEXPRICESPIKEFOUND());

		List<LmpPriceItem> items = new ArrayList<>();
		List<LmpPriceItem> spikeItems = new ArrayList<>();
		List<GmdZoneCa> zoneCaList = new ArrayList<GmdZoneCa>();

		LmpAllZonesPriceResponse extAllZonesPrice = new LmpAllZonesPriceResponse();
		LmpSpikedPriceResponse extSpikedPrice = new LmpSpikedPriceResponse();
		GmdZoneCaResponse extZoneCa = new GmdZoneCaResponse();

		if (response.getEXTSPIKEDPRICE() != null) {
			List<com.nrg.cxfstubs.lmp.gmdpricespike.ZZSZONEPROJPRICE> extAllZoneItems = response.getEXTALLZONESPRICE()
					.getItem();
			if (extAllZoneItems != null && !extAllZoneItems.isEmpty()) {
				for (com.nrg.cxfstubs.lmp.gmdpricespike.ZZSZONEPROJPRICE zoneProjPrice : extAllZoneItems) {
					LmpPriceItem item = new LmpPriceItem();
					item.setProfdate(zoneProjPrice.getPROFDATE());
					item.setProftime(zoneProjPrice.getPROFTIME().toString());
					item.setProfvalue(zoneProjPrice.getPROFVALUE());
					item.setZzone(zoneProjPrice.getZZONE());
					items.add(item);

				}
			}
		}

		if (response.getEXTSPIKEDPRICE() != null) {
			List<com.nrg.cxfstubs.lmp.gmdpricespike.ZZSZONEPROJPRICE> spikedPrices = response.getEXTSPIKEDPRICE()
					.getItem();
			if (spikedPrices != null && !spikedPrices.isEmpty()) {
				for (com.nrg.cxfstubs.lmp.gmdpricespike.ZZSZONEPROJPRICE spikeProjPrice : spikedPrices) {
					LmpPriceItem item = new LmpPriceItem();
					item.setProfdate(spikeProjPrice.getPROFDATE());
					item.setProftime(spikeProjPrice.getPROFTIME().toString());
					item.setProfvalue(spikeProjPrice.getPROFVALUE());
					item.setZzone(spikeProjPrice.getZZONE());
					spikeItems.add(item);
				}
			}
		}

		if (response.getEXTZONECA() != null) {
			List<com.nrg.cxfstubs.lmp.gmdpricespike.ZZSGMDZONECA> zoneCas = response.getEXTZONECA().getItem();
			if (zoneCas != null && !zoneCas.isEmpty()) {
				for (com.nrg.cxfstubs.lmp.gmdpricespike.ZZSGMDZONECA gmdZoneCa : zoneCas) {
					GmdZoneCa item = new GmdZoneCa();
					item.setVkont(gmdZoneCa.getVKONT());
					item.setZzone(gmdZoneCa.getZZONE());
					zoneCaList.add(item);
				}
			}
		}

		extAllZonesPrice.setItems(items);
		extSpikedPrice.setItems(spikeItems);
		extZoneCa.setItem(zoneCaList);

		lmpPriceSpikeResponse.setExtAllZonesPrice(extAllZonesPrice);
		lmpPriceSpikeResponse.setExtSpikedPrice(extSpikedPrice);
		lmpPriceSpikeResponse.setExtZoneCa(extZoneCa);

		return lmpPriceSpikeResponse;
	}
	
	public GmdHourHeadsSpikeResponse getGmdHourHeadSpikeAlert(BigDecimal imThreshold) {
		GmdHourHeadsSpikeResponse gmdHourHeadsSpikeResponse = new GmdHourHeadsSpikeResponse();
		try {
			com.nrg.cxfstubs.gmd.hourahead.spike.ObjectFactory factory = new com.nrg.cxfstubs.gmd.hourahead.spike.ObjectFactory();
			com.nrg.cxfstubs.gmd.hourahead.spike.ZEISUGMDHOURAHEADSPIKE_Type wsRequest = factory
					.createZEISUGMDHOURAHEADSPIKE_Type();
			wsRequest.setIMTHRESHOLD(imThreshold);

			ZEISUGMDHOURAHEADSPIKEResponse response = (ZEISUGMDHOURAHEADSPIKEResponse) webServiceTemplateForGmdHourHeadSpike
					.marshalSendAndReceive(wsRequest);

			if (response == null) {
				gmdHourHeadsSpikeResponse.setResultCode(RESULT_CODE_NO_DATA);
				gmdHourHeadsSpikeResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
			} else {
				gmdHourHeadsSpikeResponse = getGmdHourHeadSpikeData(response);
				gmdHourHeadsSpikeResponse.setResultCode(RESULT_CODE_SUCCESS);
				gmdHourHeadsSpikeResponse.setResultDescription(MSG_SUCCESS);
			}
		} catch (Exception e) {
			logger.error("Exception Occured in  getGmdLmpPriceSpike {} ", e);
			gmdHourHeadsSpikeResponse.setResultCode(RESULT_CODE_EXCEPTION_FAILURE);
			gmdHourHeadsSpikeResponse.setResultDescription(RESULT_DESCRIPTION_EXCEPTION);
		}
		return gmdHourHeadsSpikeResponse;
	}
	
	public GmdHourHeadsSpikeResponse getGmdHourHeadSpikeData(ZEISUGMDHOURAHEADSPIKEResponse response) {

		GmdHourHeadsSpikeResponse gmdHourHeadsSpikeResponse = new GmdHourHeadsSpikeResponse();

		gmdHourHeadsSpikeResponse.setExMessage(response.getEXMESSAGE());
		gmdHourHeadsSpikeResponse.setExpriceSpikeFound(response.getEXPRICESPIKEFOUND());

		List<LmpPriceItem> items = new ArrayList<>();
		List<LmpPriceItem> spikeItems = new ArrayList<>();
		List<GmdZoneCa> zoneCaList = new ArrayList<GmdZoneCa>();

		LmpAllZonesPriceResponse extAllZonesPrice = new LmpAllZonesPriceResponse();
		LmpSpikedPriceResponse extSpikedPrice = new LmpSpikedPriceResponse();
		GmdZoneCaResponse extZoneCa = new GmdZoneCaResponse();

		if (response.getEXTSPIKEDPRICE() != null) {
			List<com.nrg.cxfstubs.gmd.hourahead.spike.ZZSZONEPROJPRICE> extAllZoneItems = response.getEXTALLZONESPRICE()
					.getItem();
			if (extAllZoneItems != null && !extAllZoneItems.isEmpty()) {
				for (com.nrg.cxfstubs.gmd.hourahead.spike.ZZSZONEPROJPRICE zoneProjPrice : extAllZoneItems) {
					LmpPriceItem item = new LmpPriceItem();
					item.setProfdate(zoneProjPrice.getPROFDATE());
					item.setProftime(zoneProjPrice.getPROFTIME().toString());
					item.setProfvalue(zoneProjPrice.getPROFVALUE());
					item.setZzone(zoneProjPrice.getZZONE());
					items.add(item);

				}
			}
		}

		if (response.getEXTSPIKEDPRICE() != null) {
			List<com.nrg.cxfstubs.gmd.hourahead.spike.ZZSZONEPROJPRICE> spikedPrices = response.getEXTSPIKEDPRICE()
					.getItem();
			if (spikedPrices != null && !spikedPrices.isEmpty()) {
				for (com.nrg.cxfstubs.gmd.hourahead.spike.ZZSZONEPROJPRICE spikeProjPrice : spikedPrices) {
					LmpPriceItem item = new LmpPriceItem();
					item.setProfdate(spikeProjPrice.getPROFDATE());
					item.setProftime(spikeProjPrice.getPROFTIME().toString());
					item.setProfvalue(spikeProjPrice.getPROFVALUE());
					item.setZzone(spikeProjPrice.getZZONE());
					spikeItems.add(item);
				}
			}
		}

		if (response.getEXTZONECA() != null) {
			List<com.nrg.cxfstubs.gmd.hourahead.spike.ZZSGMDZONECA> zoneCas = response.getEXTZONECA().getItem();
			if (zoneCas != null && !zoneCas.isEmpty()) {
				for (com.nrg.cxfstubs.gmd.hourahead.spike.ZZSGMDZONECA gmdZoneCa : zoneCas) {
					GmdZoneCa item = new GmdZoneCa();
					item.setVkont(gmdZoneCa.getVKONT());
					item.setZzone(gmdZoneCa.getZZONE());
					zoneCaList.add(item);
				}
			}
		}

		extAllZonesPrice.setItems(items);
		extSpikedPrice.setItems(spikeItems);
		extZoneCa.setItem(zoneCaList);

		gmdHourHeadsSpikeResponse.setExtAllZonesPrice(extAllZonesPrice);
		gmdHourHeadsSpikeResponse.setExtSpikedPrice(extSpikedPrice);
		gmdHourHeadsSpikeResponse.setExtZoneCa(extZoneCa);

		return gmdHourHeadsSpikeResponse;
	}

}
