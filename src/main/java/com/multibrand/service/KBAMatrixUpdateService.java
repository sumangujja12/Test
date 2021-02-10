package com.multibrand.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.multibrand.dto.request.KBAMatrixUpdateDTO;
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
			
			TableOfZesPosidKbaMtIn tableOfZesPosidKbaMtIn = new TableOfZesPosidKbaMtIn();
			
			List<ZesPosidKbaMtIn> ZesPosidKbaMtInList = new ArrayList();
			
			
			for (KBAMatrixUpdateDTO kbaMatrixUpdateDTO :  request.getKbaMatrixUpdateList()) {
				if (kbaMatrixUpdateDTO == null) {
					break;
				}
				zesPosidKbaMtIn = new ZesPosidKbaMtIn();
				zesPosidKbaMtIn.setAgentId(kbaMatrixUpdateDTO.getAgentId());
				zesPosidKbaMtIn.setDeleteFlag(kbaMatrixUpdateDTO.getDeleteFlag());
				zesPosidKbaMtIn.setDob(kbaMatrixUpdateDTO.getDateOfBirth());
				zesPosidKbaMtIn.setDrl(kbaMatrixUpdateDTO.getDriverLicenseNumber());
				zesPosidKbaMtIn.setEmail(kbaMatrixUpdateDTO.getEmailId());
				zesPosidKbaMtIn.setEsid(kbaMatrixUpdateDTO.getEsiId());
				zesPosidKbaMtIn.setFname(kbaMatrixUpdateDTO.getFirstName());
				zesPosidKbaMtIn.setLname(kbaMatrixUpdateDTO.getLastName());
				zesPosidKbaMtIn.setIpaddress(kbaMatrixUpdateDTO.getIpAddress());
				zesPosidKbaMtIn.setSsn(kbaMatrixUpdateDTO.getSocialSecurityNumber());
				zesPosidKbaMtIn.setStateDrlNo(kbaMatrixUpdateDTO.getDriverLicenseState());
				
				ZesPosidKbaMtInList.add(zesPosidKbaMtIn);
				
			}
			
			tableOfZesPosidKbaMtIn.getItem().addAll(ZesPosidKbaMtInList);
			
            ZesPosidKbaMtEx zesPosidKbaMtEx = new ZesPosidKbaMtEx();
			
			TableOfZesPosidKbaMtEx tableOfZesPosidKbaMtEx = new TableOfZesPosidKbaMtEx();
			tableOfZesPosidKbaMtEx.getItem().add(zesPosidKbaMtEx);
			
			zECrmKbaMatrixUpdateType.setTInput(tableOfZesPosidKbaMtIn);
			zECrmKbaMatrixUpdateType.setTOutput(tableOfZesPosidKbaMtEx);
			
			


			ZECrmKbaMatrixUpdateResponse response = (ZECrmKbaMatrixUpdateResponse) webServiceTemplateForKBAMatrixUpdate
					.marshalSendAndReceive(zECrmKbaMatrixUpdateType);
			
			kbaMatrixUpdateResponse.setTableOfZesPosidKbaMtEx(response.getTOutput());

		} catch (RuntimeException ex) {
			logger.error("Exception Occured in RuntimeException  kbaMatriUpdate {} ", ex.getMessage());
			kbaMatrixUpdateResponse.setErrorCode(RESULT_CODE_EXCEPTION_FAILURE);
			kbaMatrixUpdateResponse.setErrorDescription(RESULT_DESCRIPTION_EXCEPTION);
			throw new OAMException(200, ex.getMessage(), kbaMatrixUpdateResponse);

		}

		return kbaMatrixUpdateResponse;
	}

}
