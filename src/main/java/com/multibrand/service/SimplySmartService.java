package com.multibrand.service;

import java.net.URL;
import javax.xml.ws.BindingProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.multibrand.exception.NRGException;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.nrg.cxfstubs.gmdprice.ZEISUGETGMDPRICE_Service;
import com.nrg.cxfstubs.nei.bpca.Bapiret2;
import com.nrg.cxfstubs.nei.bpca.Service;
import com.nrg.cxfstubs.nei.bpca.ZEISUNEICREATEBPCA;
import com.nrg.cxfstubs.nei.bpca.ZEIsuNeiCreateBpCaResponse;
import javax.xml.ws.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.client.core.WebServiceTemplate;

//@Service
public class SimplySmartService extends BaseAbstractService {

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	public void createBPCADetails(String companyCode, String esiId, String sessionId) throws NRGException {
		logger.info("****SimplySmartService.createBPCADetails::::::::::::::::::::START");

		logger.info("companyCode::" + companyCode);

		ZEIsuNeiCreateBpCaResponse bpcaCreateResp = null;

		long startTime = CommonUtil.getStartTime();
		
		String returnCode = null;

		StringBuilder request = new StringBuilder();
		request.append("accountNumber=").append("Account1").append("contractId=").append("ContractId1").append("esiId=")
				.append(esiId);

		URL url = Service.class.getResource("Z_E_ISU_NEI_CREATE_BP_CA.wsdl");
		if (url == null) {
			java.util.logging.Logger.getLogger(ZEISUGETGMDPRICE_Service.class.getName()).log(
					java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}",
					"Z_E_ISU_NEI_CREATE_BP_CA.wsdl");
		}

		Service simplySmartService = new Service(url);

		ZEISUNEICREATEBPCA stub = simplySmartService.getBinding();

		BindingProvider binding = (BindingProvider) stub;

//		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
//				this.envMessageReader.getMessage(CCS_USER_NAME));
//		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
//				this.envMessageReader.getMessage(CCS_PASSWORD));
//		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//				this.envMessageReader.getMessage(BPCA_CREATE_ENDPOINT_URL_JNDINAME));
		
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
				"CCSUSERNAME");
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
				"CCSPASSWORD");
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				"http://saprd101.retail.nrgenergy.com:8000/sap/bc/srt/scs/sap/z_e_isu_nei_create_bp_ca?sap-client=130");

		logger.info("SimplySmartService.create BPCADetails::::::::::::::::::::before call");

		Holder<Bapiret2> paramHolder = new Holder<Bapiret2>();
		Bapiret2 exBapiret2 = new Bapiret2();
		Holder<String> paramHolder1 = new Holder<String>();
		Holder<String> paramHolder2 = new Holder<String>();
		try {

			 stub.zeIsuNeiCreateBpCa("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
					"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", paramHolder, paramHolder1,
					paramHolder2);
			bpcaCreateResp = handleCreateBPCAResponse(exBapiret2, "oAcct1", "oBusin1");
		} catch (Exception ex) {
			ex.printStackTrace();
			utilityloggerHelper.logTransaction("createBPCADetails", false, request, ex, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw new NRGException(ex);

		}
		logger.info("****SimplySmartService.createBPCADetails::::::::::::::::::::END");

	}

	private ZEIsuNeiCreateBpCaResponse handleCreateBPCAResponse(Bapiret2 exBapiret2, String oAcctNumber,
			String oBusinessPartner) {

		ZEIsuNeiCreateBpCaResponse response = new ZEIsuNeiCreateBpCaResponse();
		response.setExBapiret2(exBapiret2);
		response.setOAcctNumber(oAcctNumber);
		response.setOBusinessPartner(oBusinessPartner);
		return response;

	}

}
