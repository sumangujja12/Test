package com.multibrand.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multibrand.dao.AddressDAOIF;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.Esiddo;
import com.multibrand.domain.GetEsiidRequest;
import com.multibrand.domain.GetEsiidResponse;
import com.multibrand.domain.OEDomain;
import com.multibrand.domain.OEDomainPortBindingStub;
import com.multibrand.domain.ProfileDomain;
import com.multibrand.domain.ProfileDomainPortBindingStub;
import com.multibrand.domain.TdspDetailsRequest;
import com.multibrand.domain.TdspDetailsResponse;
import com.multibrand.vo.response.billingResponse.AddressDO;
import com.reliant.domain.AddressValidateRequest;
import com.reliant.domain.AddressValidateResponse;
import com.reliant.domain.AddressValidationDomain;
import com.reliant.domain.AddressValidationDomainPortBindingStub;

@Service("addressService")
public class AddressService extends BaseAbstractService
{
  Logger logger = LogManager.getLogger("NRGREST_LOGGER");

  @Autowired
  @Qualifier("addressDAO")
  private AddressDAOIF addressDAO;

  protected AddressValidationDomain getAddressValidationServiceProxy()
    throws ServiceException
  {
    return (AddressValidationDomain)getServiceProxy(AddressValidationDomainPortBindingStub.class, 
      "ws.endpointURL.addressvalidationDomain");
  }

  protected OEDomain getOEServiceProxy()
    throws ServiceException
  {
    return (OEDomain)getServiceProxy(OEDomainPortBindingStub.class, "ws.endpointURL.oeDomain");
  }

  protected ProfileDomain getProfileServiceProxy()
    throws ServiceException
  {
    return (ProfileDomain)getServiceProxy(ProfileDomainPortBindingStub.class, "ws.endpointURL.profileDomain");
  }

  public List<Map<String, Object>> getCityStateFromZip(String txtZipcode) {
    return this.addressDAO.getCityStateFromZip(txtZipcode);
  }

  public AddressValidateResponse performTrilliumCleanup(AddressDO addressDO) throws ServiceException
  {
    AddressValidateResponse addressResponse  = trilliumCleanup(createAddressValidateRequest(addressDO));
    return addressResponse;
  }

  private AddressValidateRequest createAddressValidateRequest(AddressDO addressDO)
  {
    AddressValidateRequest addressValidateRequest = new AddressValidateRequest();
    addressValidateRequest.setStreetNum(addressDO.getStrStreetNum());
    addressValidateRequest.setStreetName(addressDO.getStrStreetName());
    addressValidateRequest.setAptNumber(addressDO.getStrApartNum());
    addressValidateRequest.setCity(addressDO.getStrCity());
    addressValidateRequest.setPOBox("");
    addressValidateRequest.setState(addressDO.getStrState());
    addressValidateRequest.setCountry(StringUtils.defaultIfEmpty(addressDO.getStrCountry(), "US").trim());
    addressValidateRequest.setZipCode(addressDO.getStrZip());
    return addressValidateRequest;
  }

  private AddressValidateResponse trilliumCleanup(AddressValidateRequest addressRequest) throws ServiceException {
    AddressValidateResponse addressResponse = null;
    try {
      AddressValidationDomain proxy = getAddressValidationServiceProxy();
      addressResponse = proxy.validateAddress(addressRequest);
      if (StringUtils.isNotBlank(addressResponse.getErrorMessage())){
    	  logger.debug(addressResponse.getErrorMessage());
      }
        
    }
    catch (Exception e) {
      logger.error("ERROR AddressService.trilliumCleanup()..", e);
      throw new ServiceException(e);
    }

    return addressResponse;
  }

  public GetEsiidResponse getESID(AddressDO addressDO, String companyCode) throws ServiceException {
    GetEsiidRequest getEsiidRequest = new GetEsiidRequest();
    GetEsiidResponse getEsiidResponse = null;
    getEsiidRequest.setStrStreet(addressDO.getStrStreetNum() + " " + addressDO.getStrStreetName());
    getEsiidRequest.setStrAprtNum(addressDO.getStrApartNum());
    getEsiidRequest.setStrCity(addressDO.getStrCity());
    getEsiidRequest.setStrState(addressDO.getStrState());
    getEsiidRequest.setStrZipCode(addressDO.getStrZip());
    getEsiidRequest.setStrCompanyCode(companyCode);
    logger.debug("GetEsiidRequest :" + ReflectionToStringBuilder.toString(getEsiidRequest, ToStringStyle.MULTI_LINE_STYLE));
    getEsiidResponse = getESIDResponse(getEsiidRequest);

    return getEsiidResponse;
  }

  private GetEsiidResponse getESIDResponse(GetEsiidRequest getEsiidRequest) throws ServiceException {
    GetEsiidResponse getEsiidResponse = null;
    try {
      OEDomain proxy = getOEServiceProxy();
      getEsiidResponse = proxy.getESIIDInfo(getEsiidRequest);
      if (StringUtils.isNotBlank(getEsiidResponse.getStrErrMsg())){
    	  logger.debug(getEsiidResponse.getStrErrMsg());
        
      }
    }
    catch (Exception e) {
      logger.error("ERROR getESID()..", e);
      throw new ServiceException(e);
    }
    return getEsiidResponse;
  }
  public EsidProfileResponse getESIDProfile(String strESIID, String companyCode) {
	    EsidProfileResponse esidProfileResponse = null;
	    try {
	      ProfileDomain proxy = getProfileServiceProxy();
	      esidProfileResponse = proxy.getESIDProfile(companyCode, strESIID);

	      if (StringUtils.isNotBlank(esidProfileResponse.getErrorMessage())){
	    	  logger.debug(esidProfileResponse.getErrorMessage());
	      }
	    }
	    catch (ServiceException e)
	    {
	      logger.error("ServiceException in AddressService:getESIDProfile():", e);
	    } catch (RemoteException e) {
	      logger.error("RemoteException in AddressService:getESIDProfile():", e);
	    }
	    return esidProfileResponse;
	  }
  
  public TdspDetailsResponse getTDSP(AddressDO addressDO, String companyCode) {

    logger.debug("Fetch TDSP Details");
    TdspDetailsResponse tdspDetailsResponse = null;
    TdspDetailsRequest tdspDetailsRequest = new TdspDetailsRequest();
    try {
      tdspDetailsRequest.setStrCompanyCode(companyCode);
      tdspDetailsRequest.setStrZipCode(addressDO.getStrZip());
      tdspDetailsResponse = getTDSPDetailsFromCCS(tdspDetailsRequest);

      if ((tdspDetailsResponse != null) && (tdspDetailsResponse.getStrTdspCodes() != null) && (tdspDetailsResponse.getStrTdspCodes().length > 1)) {
        tdspDetailsResponse.setMultiTdsp(true);
      }
      if (StringUtils.isNotBlank(tdspDetailsResponse.getStrErrMessage())){
    	  logger.debug(tdspDetailsResponse.getStrErrMessage());  
      }
        
    }
    catch (Exception e) {
      logger.error("Exception in AddressService:getTDSP():", e);
    }
    return tdspDetailsResponse;
  }

  private TdspDetailsResponse getTDSPDetailsFromCCS(TdspDetailsRequest tdspDetailsRequest) {

    TdspDetailsResponse tdspDetailsResponse = null;
    try {
      OEDomain proxyclient = getOEServiceProxy();
      tdspDetailsResponse = proxyclient.getTdspDetails(tdspDetailsRequest);
      if (StringUtils.isNotBlank(tdspDetailsResponse.getStrErrMessage())){
    	  logger.debug(tdspDetailsResponse.getStrErrMessage());
      }
    }
    catch (ServiceException e) {
      logger.error("ServiceException in AddressService:getTDSPDetailsFromCCS():", e);
    } catch (RemoteException e) {
      logger.error("RemoteException in AddressService:getTDSPDetailsFromCCS():", e);
    }
    return tdspDetailsResponse;
  }
  
	public GetEsiidResponse getESIDInfo(AddressDO serviceAddressDO, String companyCode) throws ServiceException{
		int esidCount = 0;
		ArrayList<String> esidInfoList = new ArrayList<String>();
		String esidNumber = " ";
		String esidClass = "";
		String esidDeposit = "";
		String esidTDSP = "";
		StringBuffer esidNumberList = new StringBuffer();
		boolean esidCheckFlag = false;
		GetEsiidResponse esidResponse = null;
		try
		{
			esidResponse = getESID(serviceAddressDO, companyCode);
			if (null!=esidResponse && StringUtils.isBlank(esidResponse.getStrErrCode()) && null!=esidResponse.getEsidList()) {
				Esiddo[] esidDOArr = esidResponse.getEsidList();
				for(Esiddo esidDO: esidDOArr)
				{
					StringBuffer esidInfopBuf = new StringBuffer();
					esidInfopBuf.append(esidDO.getESIDNumber()!=null ? esidDO.getESIDNumber() : "");
					esidInfopBuf.append('#');
					esidInfopBuf.append(esidDO.getESIDClass()!=null ? esidDO.getESIDClass() : "");
					esidInfopBuf.append('#');
					esidInfopBuf.append(esidDO.getESIDDeposit()!=null ? esidDO.getESIDDeposit() : "");
					esidInfopBuf.append('#');
					esidInfopBuf.append(esidDO.getTDSPCode()!=null ? esidDO.getTDSPCode() : "");
					esidInfopBuf.append('#');
				    esidCount++;
					esidInfoList.add(esidInfopBuf.toString());					
					if(esidDO.getESIDNumber()!=null) {
						esidNumberList.append("'" + esidDO.getESIDNumber() + "'");
				    }
			    }
		    }
			
			if(esidCount == 1) {
				for(String esidInfo : esidInfoList) {
					esidNumber = esidInfo.split("#")[0];
					esidClass = esidInfo.split("#")[1];
					esidDeposit = esidInfo.split("#")[2];
					esidTDSP = esidInfo.split("#")[3];
				}
			}
		} catch (Exception e) {
			logger.error("Exception in AddressService:getESIDInfo():", e);
	   	}

		/**
		* Below iterator check weather esid list have valid one.
		* Check for below,
		* Validation true when esid have status = 'De-Energized/Active/Blank/Null' and premise type = 'Residential'
		* Validation false when esid have status = 'Inactive' and premise type = 'Small Non-Residential/Large Non-Residential'
		*/

		int eligibleEsidCount = 0;
		
		for(String esidInfo : esidInfoList) {
			esidCheckFlag = this.esidStatusValidation(esidInfo.split("#")[0]);			
		    if(esidCheckFlag) {
		    	eligibleEsidCount++;
				esidNumber = esidInfo.split("#")[0];
				esidClass = esidInfo.split("#")[1];
				esidDeposit = esidInfo.split("#")[2];
				esidTDSP = esidInfo.split("#")[3];
		    }
		}

		// Find out if the esiids are in the premise status type table
		String[] esiidPSArray = null;
		String esiidPSString = "";
		int esiidPSCount = 0;

		if(!esidNumberList.toString().equals("")) 
		{
			try
			{
				esiidPSString = esidNumberList.toString().replace("''","#");
				esiidPSString = esiidPSString.replace("'","");
				esiidPSArray = esiidPSString.split("#");
				for(int i=0;i<esiidPSArray.length;i++) {
					if(addressDAO.getESIDCount(esiidPSArray[i]) == 1) {
						esiidPSCount++;
					}
				}
			} catch(Exception e)
			{
				logger.error("Exception in AddressService:getESIDInfo():", e);
			}
		}

		if(esidInfoList.size()==0) {
			esidNumber=NESID;
		} else if(esidInfoList.size() > 0 && esidInfoList.size() == esiidPSCount){
			if(eligibleEsidCount == 0) { 
				//esidNumber=NRESID;
				esidCheckFlag = true;
				logger.info("Non Resident Esiid !!!"+esidNumber);
	 		} else if(eligibleEsidCount > 1) {
				esidNumber=MESID;
		  }
		} else if (esidInfoList.size() == 1) {
	 		esidCheckFlag = true;
		} else if(esidInfoList.size() > 1) {
	 		esidCheckFlag = true;
 			esidNumber = MESID;
		 }
		
		if(esidInfoList.size() == esiidPSCount) {
			esidCount = eligibleEsidCount;
		} else {
			esidCount = esidInfoList.size();
		}
		
		esidResponse.setStrESIID(esidNumber);

		if (esidCount > 1)
			esidResponse.setMultiESIIDs(true);
		return esidResponse;
	}
	
	public String esidStatusCheck(String esidNum){	
		String esidCheck = null;
		int esiCntLoop=0;
		try{
			List<Map<String,Object>> esidTypeList = addressDAO.getESIDTypeList(esidNum);
			for(Map esidTypeMap: esidTypeList){			
				String esiid = (String) esidTypeMap.get("ESIID");
				String status = (String) esidTypeMap.get("STATUS");
				String premiseType = (String) esidTypeMap.get("PREMISE_TYPE");
				esiCntLoop++;
				esidCheck = esiCntLoop+"#"+esiid+"#"+status+"#"+premiseType;				
			}
			
		}catch (Exception ex1){
			logger.error("checkPendingRequest: Error in esidStatusCheck",ex1);
		}
		return esidCheck;
	}
	
	private boolean esidStatusValidation(String esidNum){
		String esiCheck = esidStatusCheck(esidNum);
		if(esiCheck!=null){
			int esidCountVar = Integer.parseInt(esiCheck.split("#")[0]);
			return esidCountVar >0 ? true:false;
		}
		return false;
	}  
}