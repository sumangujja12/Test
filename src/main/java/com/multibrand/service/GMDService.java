package com.multibrand.service;

import java.net.URL;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT;
import com.nrg.cxfstubs.gmdstatement.ZEISUGETGMDSTMT_Service;
import com.nrg.cxfstubs.gmdstatement.ZesGmdStmt;
import com.nrg.cxfstubs.gmdstatement.ZetGmdInvdate;
import com.nrg.cxfstubs.gmdstatement.ZetGmdStmt;

/**
 * 
 * @author ahanda1
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
	public void getGMDStatementDetails(String accountNumber, String companyCode, 
			String esiId, String year, String month ,String sessionId) throws NRGException {
		
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::START");
				
		ProfileResponse profileResponse = new ProfileResponse();
		
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
		}catch(Exception ex){
			utilityloggerHelper.logTransaction("getGMDStatementDetails", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);
			
		}
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::after call");
							
		utilityloggerHelper.logTransaction("getGMDStatementDetails", false, request,profileResponse, profileResponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		
		logger.info("GMDService.getGMDStatementDetails::::::::::::::::::::end");
		
	}
}
