package com.multibrand.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.response.gmd.Breakdown;
import com.multibrand.vo.response.gmd.Costs;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT_Service;
import com.nrg.cxfstubs.gmdstatement.ZesGmdStmt;
import com.nrg.cxfstubs.gmdstatement.ZetGmdInvdate;
import com.nrg.cxfstubs.gmdstatement.ZetGmdStmt;

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

         Holder<ZetGmdInvdate>  holderZetGmdInvdate = new Holder<>();
         holderZetGmdInvdate.value = zetGmdInvdate;
         
         Holder<ZesGmdStmt>  holderZesGmdStmt = new Holder<>();
         holderZesGmdStmt.value = zesGmdStmt;
         
         Holder<ZetGmdStmt>  holderZetGmdStmt = new Holder<>();
         holderZetGmdStmt.value = zetGmdStmt;
		
		try{
			
			stub.zeIsuGetGmdStmt(companyCode, accountNumber, esiId, month, year, holderZetGmdInvdate, holderZesGmdStmt, holderZetGmdStmt);
						
			gmdStatementBreakDownResp = handleGMDStatementResponse(holderZesGmdStmt);
			
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
	
	private GMDStatementBreakDownResponse handleGMDStatementResponse(Holder<ZesGmdStmt>  holderZesGmdStmt) {

		GMDStatementBreakDownResponse response = new GMDStatementBreakDownResponse();

		ZesGmdStmt zesGmdStmt = holderZesGmdStmt.value ;
	
		
		response.setTotalCost(zesGmdStmt.getUseChrg());
		response.setTotalUsage(zesGmdStmt.getCusage());
		
		List<Breakdown> breakdown = new ArrayList<>();
		
		breakdown.add(wholeSaleitemBreakDown(zesGmdStmt, WHOLESALE_ELECTRICITY));
		
		breakdown.add(tduDeliveryitemBreakDown(zesGmdStmt, TDU_DELIVERY_CHARGES));
		
		breakdown.add(gmdMemberBreakDown(zesGmdStmt, GMD_MEMBERSHIP));
		breakdown.add(taxesBreakDown(zesGmdStmt, TAXES_FEES));
		
		
		
		response.setBreakdown(breakdown);
		

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
		tduDeliveryitemBreakDown.setCosts(wholeSaleCost);
		return tduDeliveryitemBreakDown;
	}	
	
	private Breakdown gmdMemberBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdMemberBreakDown = new Breakdown();
		
	
		
		gmdMemberBreakDown.setGroup(group);
		gmdMemberBreakDown.setTotalCost(zesGmdStmt.getMemFee());
		return gmdMemberBreakDown;
	}
	
	private Breakdown taxesBreakDown(ZesGmdStmt zesGmdStmt, String group) {
		
		Breakdown gmdMemberBreakDown = new Breakdown();
		
	
		
		gmdMemberBreakDown.setGroup(group);
		gmdMemberBreakDown.setTotalCost(zesGmdStmt.getTax());
		return gmdMemberBreakDown;
	}	
	
}
