package com.multibrand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.multibrand.dto.request.KBAMatrixUpdateRequest;
import com.multibrand.exception.OAMException;
import com.multibrand.vo.response.KBAMatrixUpdateResponse;
import com.nrg.cxfstubs.kbamatrix.TableOfZesPosidKbaMtEx;
import com.nrg.cxfstubs.kbamatrix.TableOfZesPosidKbaMtIn;
import com.nrg.cxfstubs.kbamatrix.ZECrmKbaMatrixUpdateResponse;
import com.nrg.cxfstubs.kbamatrix.ZECrmKbaMatrixUpdate_Type;
import com.nrg.cxfstubs.kbamatrix.ZesPosidKbaMtEx;
import com.nrg.cxfstubs.kbamatrix.ZesPosidKbaMtIn;

/**
 * 
 * @author rpendur1
 * 
 *         This class is responsible for fetching information from Redbull
 *         Service ProfileDomain
 */

@Service
public class KBAMatrixUpdateService extends BaseAbstractService {

	@Autowired
	@Qualifier("webServiceTemplateForKBAMatrixUpdate")
	private WebServiceTemplate webServiceTemplateForKBAMatrixUpdate;

	public KBAMatrixUpdateResponse kbaMatriUpdate(KBAMatrixUpdateRequest request) {

		KBAMatrixUpdateResponse kbaMatrixUpdateResponse = new KBAMatrixUpdateResponse();
		
		try {
			
			com.nrg.cxfstubs.kbamatrix.ObjectFactory factory = new com.nrg.cxfstubs.kbamatrix.ObjectFactory();

			ZECrmKbaMatrixUpdate_Type zECrmKbaMatrixUpdateType = factory.createZECrmKbaMatrixUpdate_Type();
			
			ZesPosidKbaMtIn zesPosidKbaMtIn = new ZesPosidKbaMtIn();
			zesPosidKbaMtIn.setAgentId(request.getAgentId());
			zesPosidKbaMtIn.setDeleteFlag(request.getDeleteFlag());
			zesPosidKbaMtIn.setDob(request.getDateOfBirth());
			zesPosidKbaMtIn.setDrl(request.getDriverLicenseNumber());
			zesPosidKbaMtIn.setEmail(request.getEmailId());
			zesPosidKbaMtIn.setEsid(request.getEsiId());
			zesPosidKbaMtIn.setFname(request.getFirstName());
			zesPosidKbaMtIn.setLname(request.getLastName());
			zesPosidKbaMtIn.setIpaddress(request.getIpAddress());
			zesPosidKbaMtIn.setSsn(request.getSocialSecurityNumber());
			zesPosidKbaMtIn.setStateDrlNo(request.getDriverLicenseState());
			
			
			TableOfZesPosidKbaMtIn tableOfZesPosidKbaMtIn = new TableOfZesPosidKbaMtIn();
			tableOfZesPosidKbaMtIn.getItem().add(zesPosidKbaMtIn);
			
			ZesPosidKbaMtEx zesPosidKbaMtEx = new ZesPosidKbaMtEx();
			
			TableOfZesPosidKbaMtEx tableOfZesPosidKbaMtEx = new TableOfZesPosidKbaMtEx();
			tableOfZesPosidKbaMtEx.getItem().add(zesPosidKbaMtEx);
			
			
			zECrmKbaMatrixUpdateType.setTInput(tableOfZesPosidKbaMtIn);
			zECrmKbaMatrixUpdateType.setTOutput(tableOfZesPosidKbaMtEx);
			
			


			ZECrmKbaMatrixUpdateResponse response = (ZECrmKbaMatrixUpdateResponse) webServiceTemplateForKBAMatrixUpdate
					.marshalSendAndReceive(zECrmKbaMatrixUpdateType);
			
			kbaMatrixUpdateResponse.setTableOfZesPosidKbaMtEx(response.getTOutput());

		} catch (RuntimeException ex) {
			logger.error("Exception Occured in RuntimeException  kbaMatriUpdate {} ", ex);
			kbaMatrixUpdateResponse.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			kbaMatrixUpdateResponse.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), kbaMatrixUpdateResponse);

		}

		return kbaMatrixUpdateResponse;
	}

}
