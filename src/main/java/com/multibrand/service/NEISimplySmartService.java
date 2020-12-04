package com.multibrand.service;

import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multibrand.dto.request.NeiBPCARequest;
import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.nei.bpca.Bapiret2;
import com.nrg.cxfstubs.nei.bpca.ZEISUNEICREATEBPCA;
import com.nrg.cxfstubs.nei.bpca.ZEISUNEICREATEBPCA_Service;
import com.nrg.cxfstubs.nei.bpca.ZEIsuNeiCreateBpCaResponse;

/**
 * 
 * @author NULTHI1
 *
 */
@Service
public class NEISimplySmartService extends BaseAbstractService {

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
    
	/**
	 * 
	 * @param bpcaRequest
	 * @param sessionId
	 * @return
	 * @throws NRGException
	 */
	public ZEIsuNeiCreateBpCaResponse createNEIBPCA(NeiBPCARequest bpcaRequest, String sessionId) throws NRGException {
		
		long startTime = CommonUtil.getStartTime();
		logger.info(sessionId+":****NEISimplySmartService.createNEIBPCA::::::::::::::::::::Start:" );

		String companyCode ="NEI";

		ZEIsuNeiCreateBpCaResponse bpcaCreateResp = new ZEIsuNeiCreateBpCaResponse();
		String endPoint = this.envMessageReader.getMessage(NEI_CREATE_BPCA_CCS_ENDPOINT_URL);

		URL url = ZEISUNEICREATEBPCA_Service.class.getResource("Z_E_ISU_NEI_CREATE_BP_CA.wsdl");
				
		ZEISUNEICREATEBPCA_Service port = new ZEISUNEICREATEBPCA_Service(url);
		ZEISUNEICREATEBPCA stub = port.getZEISUNEICREATEBPCABIND();
        BindingProvider binding = (BindingProvider) stub;
        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PASSWORD));
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,endPoint);
		
		Holder<Bapiret2> exBapiret2Resp =new Holder<Bapiret2>();
		Holder<String> acctNumberResp =new Holder<String>();
		Holder<String> BPResp =new Holder<String>();
		
		logger.debug(sessionId+":Invoking a CCS call: " +endPoint);
		logger.debug(sessionId+":Request: " +bpcaRequest);
		try {
			stub.zeIsuNeiCreateBpCa(bpcaRequest.getIAcctNumber(),
									bpcaRequest.getIAutopay(), 
					 				bpcaRequest.getIBillCity(),
					 				bpcaRequest.getIBillCountry(), 
					 				bpcaRequest.getIBillDistrict(), 
					 				bpcaRequest.getIBillHouseNum1(),
					 				bpcaRequest.getIBillState(),
					 				bpcaRequest.getIBillStreet(),
					 				bpcaRequest.getIBillZip(), 
					 				bpcaRequest.getIEbill(), 
					 				bpcaRequest.getIEmailAddr(), 
					 				bpcaRequest.getIFirstName(),
					 				bpcaRequest.getIFullName(),
					 				bpcaRequest.getILastName(),
					 				bpcaRequest.getIPhoneNumber(), 
					 				bpcaRequest.getIPlanId(), 
					 				bpcaRequest.getIPlanName(),
					 				bpcaRequest.getISrvcCity(), 
					 				bpcaRequest.getISrvcCountry(), 
					 				bpcaRequest.getISrvcDistrict(), 
					 				bpcaRequest.getISrvcHouseNum1(),
					 				bpcaRequest.getISrvcState(),
					 				bpcaRequest.getISrvcStreet(),
					 				bpcaRequest.getISrvcZip(),
					 				bpcaRequest.getIUtility(),
					 				exBapiret2Resp, acctNumberResp, BPResp);
			
						
			bpcaCreateResp.setExBapiret2(exBapiret2Resp.value);
			bpcaCreateResp.setOAcctNumber(acctNumberResp.value);
			bpcaCreateResp.setOBusinessPartner(BPResp.value);
			
			logger.debug(sessionId+":Response: " + new ObjectMapper().writeValueAsString(bpcaCreateResp));
			
		} catch (Exception ex) { 
			
			logger.error(sessionId+":createNEIBPCA : Exception while fetching Offers:", ex);
			utilityloggerHelper.logTransaction("NEISimplySmartService:createNEIBPCA", false, bpcaRequest, ex, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);

		}
		logger.info(sessionId+":****NEISimplySmartService.createNEIBPCA:End:: Execution Time:"+CommonUtil.getElapsedTime(startTime));
		
		return bpcaCreateResp;

	}
    
}
