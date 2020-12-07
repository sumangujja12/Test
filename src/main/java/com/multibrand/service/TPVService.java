/**
 * 
 */
package com.multibrand.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;
import org.springframework.stereotype.Service;

import com.multibrand.vo.response.tpv.TransUpdResponseVO;

import mc_style.functions.soap.sap.document.sap_com.Z_E_NEWS_TPV_API_TRANS_UPD_PortType;
import mc_style.functions.soap.sap.document.sap_com.Z_E_NEWS_TPV_API_TRANS_UPD_ServiceLocator;
import mc_style.functions.soap.sap.document.sap_com.ZesNeReturn;

/**
 * @author kbhulla1
 *
 */
@Service("tpvService")
public class TPVService extends BaseAbstractService {

	public TransUpdResponseVO tpvTransUpd(String ivAcctNum, String ivApprovedBy, String ivDateTime, String ivReason,
			String ivResult, String ivTransactionid) throws RemoteException, ServiceException {
		
		
		EngineConfiguration config = null;

		Z_E_NEWS_TPV_API_TRANS_UPD_ServiceLocator locator = new Z_E_NEWS_TPV_API_TRANS_UPD_ServiceLocator();
		locator.setEngineConfiguration(config);
	
		System.out.println("Calling TPV Service " );
		locator.setZ_E_NEWS_TPV_API_TRANS_UPD_BINDEndpointAddress(this.envMessageReader.getMessage(TPV_CCS_URL));

		Z_E_NEWS_TPV_API_TRANS_UPD_PortType port = locator.getZ_E_NEWS_TPV_API_TRANS_UPD_BIND(); 
	
		org.apache.axis.client.Stub s = (Stub)port;				
		int timeOutInMs = 120000;				 
		System.out.println("Setting Client Timeout = " + timeOutInMs);
		s.setTimeout(timeOutInMs); // Setting the client timeout in millsec explicitly.. this overrides the default 60sec axis timeout.
		s.setUsername(this.envMessageReader.getMessage(CCS_USER_NAME));
		s.setPassword(this.envMessageReader.getMessage(CCS_PSD));
		
		// Fire the service call
		ZesNeReturn output = port.ZEIsuNewsTpvApiTransUpd(ivAcctNum, 
		ivApprovedBy, ivDateTime, ivReason, ivResult, ivTransactionid);

		System.out.println("Output Message = " + output.getMessage());
		System.out.println("Output Type = " + output.getType());
		TransUpdResponseVO responseVO = new TransUpdResponseVO();
		responseVO.setMessage( output.getMessage());
		responseVO.setType(output.getType());
		
		return responseVO;
	}

}
