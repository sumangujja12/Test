package com.multibrand.service;

import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

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

	/**
	 * 
	 * @param bpcaRequest
	 * @param sessionId
	 * @return
	 * @throws NRGException
	 */
	public ZEIsuNeiCreateBpCaResponse createNEIBPCA(NeiBPCARequest bpcaRequest, String sessionId) throws NRGException {
		
		long startTime = CommonUtil.getStartTime();
		logger.info("{}:****NEISimplySmartService.createNEIBPCA::::::::::::::::::::Start:", sessionId );

		String companyCode ="NEI";

		ZEIsuNeiCreateBpCaResponse bpcaCreateResp = new ZEIsuNeiCreateBpCaResponse();
		String endPoint = this.envMessageReader.getMessage(NEI_CREATE_BPCA_CCS_ENDPOINT_URL);

		URL url = ZEISUNEICREATEBPCA_Service.class.getResource("Z_E_ISU_NEI_CREATE_BP_CA.wsdl");
				
		ZEISUNEICREATEBPCA_Service port = new ZEISUNEICREATEBPCA_Service(url);
		ZEISUNEICREATEBPCA stub = port.getZEISUNEICREATEBPCABIND();
        BindingProvider binding = (BindingProvider) stub;
        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,endPoint);
		
		Holder<Bapiret2> exBapiret2Resp =new Holder<Bapiret2>();
		Holder<String> acctNumberResp =new Holder<String>();
		Holder<String> bpResp =new Holder<String>();
		
		logger.debug("{}::Invoking a CCS call: {} " , sessionId, endPoint);
		logger.debug("{}::Request: {}" ,sessionId,bpcaRequest);
		try {
			stub.zeIsuNeiCreateBpCa(bpcaRequest.getAcctNumber(),
									bpcaRequest.getAutopay(), 
					 				bpcaRequest.getBillCity(),
					 				bpcaRequest.getBillCountry(), 
					 				bpcaRequest.getBillDistrict(), 
					 				bpcaRequest.getBillHouseNum1(),
					 				bpcaRequest.getBillState(),
					 				bpcaRequest.getBillStreet(),
					 				bpcaRequest.getBillZip(), 
					 				bpcaRequest.getEbill(), 
					 				bpcaRequest.getEmailAddr(), 
					 				bpcaRequest.getFirstName(),
					 				bpcaRequest.getFullName(),
					 				bpcaRequest.getLastName(),
					 				bpcaRequest.getPhoneNumber(), 
					 				bpcaRequest.getPlanId(), 
					 				bpcaRequest.getPlanName(),
					 				bpcaRequest.getSrvcCity(), 
					 				bpcaRequest.getSrvcCountry(), 
					 				bpcaRequest.getSrvcDistrict(), 
					 				bpcaRequest.getSrvcHouseNum1(),
					 				bpcaRequest.getSrvcState(),
					 				bpcaRequest.getSrvcStreet(),
					 				bpcaRequest.getSrvcZip(),
					 				bpcaRequest.getUtility(),
					 				exBapiret2Resp, acctNumberResp, bpResp);
			
						
			bpcaCreateResp.setExBapiret2(exBapiret2Resp.value);
			bpcaCreateResp.setOAcctNumber(acctNumberResp.value);
			bpcaCreateResp.setOBusinessPartner(bpResp.value);
			
			if(logger.isDebugEnabled()) {
			 logger.debug("{}:Response: {}", sessionId, new ObjectMapper().writeValueAsString(bpcaCreateResp));
			}
			
		} catch (Exception ex) { 
			
			logger.error(String.format("%s :createNEIBPCA : Exception while fetching Offers:", sessionId), ex);
			utilityloggerHelper.logTransaction("NEISimplySmartService:createNEIBPCA", false, bpcaRequest, ex, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);

		}
		logger.info("{}:****NEISimplySmartService.createNEIBPCA:End:: Execution Time:{}", sessionId,CommonUtil.getElapsedTime(startTime));
		
		return bpcaCreateResp;

	}
    
}
