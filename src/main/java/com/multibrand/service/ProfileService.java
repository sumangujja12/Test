package com.multibrand.service;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multibrand.domain.AcctValidationRequest;
import com.multibrand.domain.AddressDO;
import com.multibrand.domain.AllAccountDetailsRequest;
import com.multibrand.domain.AllAccountDetailsResponse;
import com.multibrand.domain.AllAlertsRequest;
import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.domain.CirroStructureCallRequest;
import com.multibrand.domain.CirroStructureCallResponse;
import com.multibrand.domain.ContractDO;
import com.multibrand.domain.CrmProfileRequest;
import com.multibrand.domain.CrmProfileResponse;
import com.multibrand.domain.EsidProfileResponse;
import com.multibrand.domain.LanguageUpdateRequest;
import com.multibrand.domain.LanguageUpdateResponse;
import com.multibrand.domain.ProfileDomain;
import com.multibrand.domain.ProfileDomainPortBindingStub;
import com.multibrand.domain.ProfileResponse;
import com.multibrand.domain.UpdateAddressRequest;
import com.multibrand.domain.UpdateAddressResponse;
import com.multibrand.domain.UpdateContactRequest;
import com.multibrand.domain.UpdateContactResponse;
import com.multibrand.domain.WseEnrollmentRequest;
import com.multibrand.domain.WseEnrollmentResponse;
import com.multibrand.domain.WseEsenseEligibilityResponse;
import com.multibrand.domain.WseServiceRequest;
import com.multibrand.domain.WseServiceResponse;
import com.multibrand.helper.ProfileHelper;
import com.multibrand.helper.UtilityLoggerHelper;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.SecondaryNameUpdateReqVO;
import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.response.CampEnvironmentDO;
import com.multibrand.vo.response.EnvironmentImpacts;
import com.multibrand.vo.response.EnvironmentImpactsResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.OfferDO;
import com.multibrand.vo.response.OfferPriceDO;
import com.multibrand.vo.response.PendingSwapDO;
import com.multibrand.vo.response.SecondaryName;
import com.multibrand.vo.response.SecondaryNameResponse;
import com.multibrand.vo.response.SegmentedFlagDO;
import com.multibrand.vo.response.profileResponse.ProductDO;
import com.multibrand.vo.response.profileResponse.ProductUpdateResponse;
import com.multibrand.vo.response.profileResponse.Products;
import com.nrg.cxfstubs.bprelationreadupd.ZCRMWSBPRELATIONREADUPD;
import com.nrg.cxfstubs.bprelationreadupd.ZCRMWSBPRELATIONREADUPD_Service;
import com.nrg.cxfstubs.bprelationreadupd.ZesPartnerNames;
import com.nrg.cxfstubs.bprelationreadupd.ZesSecondaryNames;
import com.nrg.cxfstubs.bprelationreadupd.ZetPartnerNames;
import com.nrg.cxfstubs.bprelationreadupd.ZetSecondaryNames;
import com.nrg.cxfstubs.contractinfo.IsuBapiret2T;
import com.nrg.cxfstubs.contractinfo.ZEISUGETOFFERDATAFORSWAP;
import com.nrg.cxfstubs.contractinfo.ZEISUGETOFFERDATAFORSWAP_Service;
import com.nrg.cxfstubs.contractinfo.ZeiCampEnviDetails;
import com.nrg.cxfstubs.contractinfo.ZeiOfrcdFlag;
import com.nrg.cxfstubs.contractinfo.ZeiSwapOfferInput;
import com.nrg.cxfstubs.contractinfo.ZeiSwapOutput;
import com.nrg.cxfstubs.contractinfo.ZesAvgPrice;
import com.nrg.cxfstubs.contractinfo.ZesCampEnviDetails;
import com.nrg.cxfstubs.contractinfo.ZesDocid;
import com.nrg.cxfstubs.contractinfo.ZesEligoffer;
import com.nrg.cxfstubs.contractinfo.ZesOfrcdFlag;
import com.nrg.cxfstubs.contractinfo.ZesSwapOfferInput;
import com.nrg.cxfstubs.contractinfo.ZesSwapOutput;
import com.nrg.cxfstubs.crmcontactinfoupdate.Bapiret2;
import com.nrg.cxfstubs.crmcontactinfoupdate.TableOfBapiret2;
import com.nrg.cxfstubs.crmcontactinfoupdate.TableOfZesCommPref2;
import com.nrg.cxfstubs.crmcontactinfoupdate.TableOfZesEmailsNew2;
import com.nrg.cxfstubs.crmcontactinfoupdate.TableOfZesPhoneNew2;
import com.nrg.cxfstubs.crmcontactinfoupdate.TableOfZesUnsubReason;
import com.nrg.cxfstubs.crmcontactinfoupdate.ZECRMBPCONTACTDETAILNEW;
import com.nrg.cxfstubs.crmcontactinfoupdate.ZECrmBpContactDetailNew_Service;
import com.nrg.cxfstubs.crmcontactinfoupdate.ZesCommPref2;
import com.nrg.cxfstubs.crmcontactinfoupdate.ZesEmailsNew2;
import com.nrg.cxfstubs.environmentalimpact.ZEISUENVIRONMENTALIMPACTS;
import com.nrg.cxfstubs.environmentalimpact.ZEWSENVIRONMENTALIMPACTS;
import com.nrg.cxfstubs.environmentalimpact.ZesEnviDetails;
import com.nrg.cxfstubs.environmentalimpact.ZetEnviDetails;
import com.nrg.cxfstubs.oamidentity.ZESBUT0ID2;
import com.nrg.cxfstubs.oamidentity.ZETBUT0ID2;
import com.nrg.cxfstubs.oamidentity.ZEWEBOAMIDENTYFORBP;
import com.nrg.cxfstubs.oamidentity.ZEWEBOAMIDENTYFORBP_Service;
import com.nrg.cxfstubs.oamidentity.ZetBut0Id;
import com.nrg.cxfstubs.profile.ZEISUGETCAPROFILEDATA;
import com.nrg.cxfstubs.profile.ZEIsuGetCaProfileData_Service;
import com.nrg.cxfstubs.profile.ZcaAdrc;
import com.nrg.cxfstubs.profile.ZcaOutput;
import com.nrg.cxfstubs.profile.ZcaOutputTt;
import com.nrg.cxfstubs.profile.ZcontractAdrc;
import com.nrg.cxfstubs.profile.ZcontractOutput;
import com.nrg.cxfstubs.profile.ZcontractOutputTt;
import com.nrg.cxfstubs.profile.ZesZesuerStat;
import com.nrg.cxfstubs.sundriverclub.Bapiret2T;
import com.nrg.cxfstubs.sundriverclub.ZECRMVASWEBPRODUPDATE;
import com.nrg.cxfstubs.sundriverclub.ZECRMVASWEBPRODUPDATE_Service;
import com.nrg.cxfstubs.sundriverclub.ZesEnrollProd;
import com.nrg.cxfstubs.sundriverclub.ZesPrdDetails;
import com.nrg.cxfstubs.sundriverclub.ZesWebncProducts;
import com.nrg.cxfstubs.sundriverclub.ZetEnrollProd;
import com.nrg.cxfstubs.sundriverclub.ZetPrdDetails;
import com.nrg.cxfstubs.sundriverclub.ZetWebncProducts;

/**
 * 
 * @author ahanda1
 * 
 *         This class is responsible for fetching information from Redbull
 *         Service ProfileDomain
 */

@Service
public class ProfileService extends BaseAbstractService {

	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private ProfileHelper profileHelper;
	
	private String[] excludeOfferList = {"S", "F"}; 
	private static final String SECONDAY_NAME_UPDATE_LABEL = "secondaryNameUpdate";
	
	
	
	/**
	 * This will return ProfileDomainProxy and set EndPoint URL
	 * 
	 * @return profileDomainProxy The ProfileDomainProxy Object
	 */
	protected ProfileDomain getProfileDomainProxy() {

		return (ProfileDomain) getServiceProxy(
				ProfileDomainPortBindingStub.class,
				PROFILE_SERVICE_ENDPOINT_URL);
	}
	
	/**
	 * @author rbansal30
	 * @param caNumber
	 * @param bpNumber
	 * @param esid
	 * @param contractId
	 * @param languageCode
	 * @param companyCode
	 * @param offerDO
	 * @return com.multibrand.vo.response.OfferDO object
	 * @throws Exception 
	 */
	public com.multibrand.vo.response.OfferDO getCurrentOfferDocs(String caNumber,
			String bpNumber, String esid, String contractId, String languageCode, String companyCode, com.multibrand.vo.response.OfferDO offerDO, String sessionId) {

		logger.info("[Profile Service ]::::::getCurrentOfferDocs");
		long startTime = CommonUtil.getStartTime();
		String request = "caNumber="+caNumber+",bpNumber="+bpNumber+",esid="+esid+",contractId="+contractId+",languageCode="+languageCode;
		
		
		logger.info("setting default values current offer data");
		offerDO.setStrEFLDocID("");
		offerDO.setStrEFLSmartCode("");
		offerDO.setStrTOSDocID("");
		offerDO.setStrTOSSmartCode("");
		offerDO.setStrYRAACDocID("");
		offerDO.setStrYRAACSmartCode("");
	 
		URL url = ZEISUGETOFFERDATAFORSWAP_Service.class
				.getResource("Z_E_ISU_GET_OFFERDATA_FOR_SWAP-RPM.wsdl");


		ZEISUGETOFFERDATAFORSWAP_Service port = new ZEISUGETOFFERDATAFORSWAP_Service(
				url);
		ZEISUGETOFFERDATAFORSWAP stub = port.getZEISUGETOFFERDATAFORSWAP();

		BindingProvider binding = (BindingProvider) stub;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
				this.envMessageReader.getMessage(CCS_USER_NAME));

		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
				this.envMessageReader.getMessage(CCS_PSD));

		binding.getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				this.envMessageReader.getMessage(GET_CONTRACT_INFO_ENDPOINT_URL_JNDINAME));

		ZeiSwapOfferInput zeiSwapOfferInputObj = new ZeiSwapOfferInput();
		List<ZesSwapOfferInput> zesSwapOfferInputList = zeiSwapOfferInputObj.getItem();

		ZesSwapOfferInput zesSwapofferInput = new ZesSwapOfferInput();
		zesSwapofferInput.setEsid(esid);
		zesSwapofferInput.setLangu(languageCode);
		zesSwapofferInput.setPartner(bpNumber);
		zesSwapofferInput.setVertrag(contractId);
		zesSwapofferInput.setVkont(CommonUtil.paddedCa(caNumber));
		zesSwapOfferInputList.add(zesSwapofferInput);

		String imCaller = WEB_SUBSCRIBER_ID;		
		javax.xml.ws.Holder<ZeiCampEnviDetails> hZeiCampEnvrDetails = new javax.xml.ws.Holder<>();
		javax.xml.ws.Holder<ZeiSwapOutput> hZeiSwapOutput = new javax.xml.ws.Holder<>();
		javax.xml.ws.Holder<ZeiOfrcdFlag> hZeiOffrCDFlag = new javax.xml.ws.Holder<>();


		try{
		stub.zeIsuGetOfferdataForSwap(imCaller, zeiSwapOfferInputObj, "", null, hZeiCampEnvrDetails, hZeiSwapOutput, hZeiOffrCDFlag);
		}catch(Exception ex){

			logger.debug(XmlUtil.pojoToXML(request));
			utilityloggerHelper.logTransaction("getCurrentOfferDocs", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
			
		}
		ZeiSwapOutput zeiSwapOutput = hZeiSwapOutput.value;
		
		ZesSwapOutput zesSwapOutput = zeiSwapOutput.getItem().get(0);
				
		if(zesSwapOutput.getCurrCodata()!=null && zesSwapOutput.getCurrCodata().getCampaignData()!=null && !zesSwapOutput.getCurrCodata().getCampaignData().getItem().isEmpty())
		{
			logger.info("[Profile Service ]::::::getCurrentOfferDocs:::inside if");
			List<ZesDocid> zesDocidList = zesSwapOutput.getCurrCodata().getCampaignData().getItem().get(0).getDocidTab().getItem();

			for (ZesDocid zesDocid : zesDocidList) {
				String docType = zesDocid.getDocType();

				if (EFL.equalsIgnoreCase(docType)) {
					offerDO.setStrEFLDocID(zesDocid.getDocId());
					offerDO.setStrEFLSmartCode(zesDocid.getSmartCode());
					offerDO.setStrEflUrl(CommonUtil.getDynamicEflUrl(zesDocid.getDocId(), zesDocid.getSmartCode()));
				} else if (TOS.equalsIgnoreCase(docType)) {
					offerDO.setStrTOSDocID(zesDocid.getDocId());
					offerDO.setStrTOSSmartCode(zesDocid.getSmartCode());
				} else if (YRAAC.equalsIgnoreCase(docType)) {
					offerDO.setStrYRAACDocID(zesDocid.getDocId());
					offerDO.setStrYRAACSmartCode(zesDocid.getSmartCode());
				}
			}

		}
		
		utilityloggerHelper.logTransaction("getCurrentOfferDocs", false, request,offerDO, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);

		logger.info("[ProfileService ]::::: getCurrentOfferDocs ");
		return offerDO;
	}



	/**
	 * This profile call will do the call to the logging framework 
	 * @param accountNumber
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> getProfile(String accountNumber, String companyCode, String sessionId) {
		
		logger.info("ProfileService.getProfile::::::::::::::::::::START");
		HashMap<String, Object> responseMap = new HashMap<>();
		
		ProfileResponse profileResponse = new ProfileResponse();
		long startTime = CommonUtil.getStartTime();
		String request = "accountNumber="+accountNumber;
		com.multibrand.domain.ContractAccountDO contractAccountDO = new com.multibrand.domain.ContractAccountDO();
		ZcaOutput zcaOutput = null;		
		ContractDO contractDO = null;
		com.multibrand.domain.OfferDO offerDO = null;
		ContractDO[] contractDOList = null;
		AddressDO billingAddressDO = null;
		AddressDO serviceAddressDO = null;
		ZcontractAdrc zcontractAdrc = null;
		ZcontractOutput[] zcontractOutput = null;
		
		//Start : Added for Redbull CXF upgrade by IJ
		URL url = ZEIsuGetCaProfileData_Service.class.getResource("Z_E_ISU_GET_CA_PROFILE_DATA.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZEIsuGetCaProfileData_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "Z_E_ISU_GET_CA_PROFILE_DATA.wsdl");
        }
        ZEIsuGetCaProfileData_Service profileService = new ZEIsuGetCaProfileData_Service(url);
		
		ZEISUGETCAPROFILEDATA stub = profileService.getZEIsuGetCaProfileData();
		 BindingProvider binding = (BindingProvider)stub;
	    
	        binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,  this.envMessageReader.getMessage(CCS_USER_NAME));
	        binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,  this.envMessageReader.getMessage(CCS_PSD));
	        binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.envMessageReader.getMessage(PROFILE_CADATA_ENDPOINT_URL_JNDINAME));
          logger.info("ProfileService.getProfile::::::::::::::::::::before call");
          
          
        String imCaOnly = null;
		String imVkont = accountNumber; //CA Number
				
		ZcaOutputTt exCaDetail = new ZcaOutputTt();
		ZcontractOutputTt exContractDetail = new ZcontractOutputTt();
		Holder<com.nrg.cxfstubs.profile.TableOfBapiret2> exMessage1 = new Holder<>();		
		
		Holder<String> exReturnCode = new Holder<>();
		exReturnCode.value = new String();		
		
		// Added for the Jar Changes
		Holder<String> exSuperPartner = new Holder<>();
		exSuperPartner.value = new String();	
		
		Holder<ZcaOutputTt> holderZcaOutputTt = new Holder<>();
		Holder<ZcontractOutputTt> holderZcontractOutputTt = new Holder<>();
		holderZcaOutputTt.value = exCaDetail;
		holderZcontractOutputTt.value = exContractDetail;
		
		ZesZesuerStat zesZesuerStat = new ZesZesuerStat();
		Holder<ZesZesuerStat> holderZesZesuerStat = new Holder<>();
		holderZesZesuerStat.value = zesZesuerStat;
		
		
		try{
			stub.zeIsuGetCaProfileData(exMessage1, imCaOnly, imVkont, holderZcaOutputTt, holderZcontractOutputTt, exReturnCode, exSuperPartner, holderZesZesuerStat);
			
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			utilityloggerHelper.logTransaction("getProfile", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
			
		}
		logger.info("ProfileService.getProfile::::::::::::::::::::after call");
		
		zesZesuerStat=holderZesZesuerStat.value;
		
		
		exCaDetail=holderZcaOutputTt.value;
		List<ZcaOutput> zcaOutputList =exCaDetail.getItem();
		
		exContractDetail=holderZcontractOutputTt.value;
		List<ZcontractOutput> zcontractOutputList=exContractDetail.getItem();
		
		
		String superBPID = exSuperPartner.value;
		logger.info("super bpid ::::: {}" , superBPID);
		profileResponse.setSuperBPID(superBPID);
		//End : Added for Redbull CXF upgrade by IJ
		
		//Populate Contract Account Details	
		
		if(!CommonUtil.isCollectionNullOrEmpty(zcaOutputList)){
			
			zcaOutput = zcaOutputList.get(0);
			contractAccountDO.setStrCANumber(zcaOutput.getExVkont());
			contractAccountDO.setStrBPNumber(zcaOutput.getExGpart());
			contractAccountDO.setStrCompany(zcaOutput.getExBukrs());
			
			//START : OAM :KD
			
			contractAccountDO.setStrLegacyAccount(zcaOutput.getExVkona());
			contractAccountDO.setStrConversionDate(zcaOutput.getExErdat());
			contractAccountDO.setStrExFirstName(zcaOutput.getExNameFirst());
			contractAccountDO.setStrExLastName(zcaOutput.getExNameLast());
			contractAccountDO.setStrExMiddleNAme(zcaOutput.getExNamemiddle());

			//END : OAM :KD

			contractAccountDO.setStrCharityId(zcaOutput.getExCharityId());
			contractAccountDO.setStrCharityName(zcaOutput.getExCharityDesc());
			contractAccountDO.setStrBrandName(zcaOutput.getExBrandId());

			
			
			contractAccountDO.setCAName(zcaOutput.getExVkbez());
			contractAccountDO.setStrFirstName(zcaOutput.getExZzFname());
			contractAccountDO.setStrLastName(zcaOutput.getExZzLname());
			contractAccountDO.setStrCheckDigit(zcaOutput.getExCheckDigit());
			
			contractAccountDO.setStrCustSegment(zcaOutput.getExCustSeg());
			
			
			
			contractAccountDO.setStrEnrollSource(zcaOutput.getExEnrlSource());
			
			
			contractAccountDO.setStrNCAStatus(CommonUtil.getFlagValue(zcaOutput.getExNcaFlag()));
			contractAccountDO.setStrNCCAStatus(CommonUtil.getFlagValue(zcaOutput.getExNccaFlag()));
			contractAccountDO.setStrBalBillFlag(CommonUtil.getFlagValue(zcaOutput.getExBbEnrl()));
			contractAccountDO.setStrAvgBillFlag(CommonUtil.getFlagValue(zcaOutput.getExAbEnrl()));
			contractAccountDO.setStrAvlBillFlag(CommonUtil.getFlagValue(zcaOutput.getExAbElig()));
			contractAccountDO.setStrPaperFlag(CommonUtil.getFlagValue(zcaOutput.getExZzPaper()));
			
			contractAccountDO.setStrAutoBankEli(CommonUtil.getFlagValue(zcaOutput.getExBankElig()));
			contractAccountDO.setStrAutoCCElig(CommonUtil.getFlagValue(zcaOutput.getExCcElig()));
			contractAccountDO.setStrAutoPayFlag(zcaOutput.getExEzawe());
						
			contractAccountDO.setStrAPBankAccNum(zcaOutput.getExBnkAccNum());
			contractAccountDO.setStrAPCCNum(zcaOutput.getExCcNum());
			contractAccountDO.setStrAPCCType(zcaOutput.getExCcType());
			contractAccountDO.setStrAPCCExpDate(zcaOutput.getExCcExpDate());
			contractAccountDO.setStrEbillFlag(CommonUtil.getFlagValue(zcaOutput.getExEbillFlag()));
			contractAccountDO.setStrPaymentType(zcaOutput.getExPaymentType());
			
			contractAccountDO.setStrDNPFlag(CommonUtil.getFlagValue(zcaOutput.getExDiscnElig()));
			
			contractAccountDO.setStrDisconnectDate(zcaOutput.getExDiscnDate());
			//START  - DEFECT- 587 
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMinimumFractionDigits(2);
			contractAccountDO.setStrDisconnectAmt(numberFormat.format(zcaOutput.getExDiscnAmt()).toString());
			//END - DEFECT - 587
			contractAccountDO.setStrCurrentInvoiceID(zcaOutput.getExInvoiceid());
			contractAccountDO.setStrLanguageCode(zcaOutput.getExLangu());
			
			//Populating biling address
			ZcaAdrc zcaAdrc = zcaOutput.getExAdrc();
			billingAddressDO = new AddressDO();
			if(null != zcaAdrc){
				billingAddressDO.setStrCity(zcaAdrc.getCity1());
				billingAddressDO.setStrPOBox(zcaAdrc.getPoBox());
				billingAddressDO.setStrStreetName(zcaAdrc.getStreet());
				billingAddressDO.setStrState(zcaAdrc.getRegion());
				billingAddressDO.setStrStreetNum(zcaAdrc.getHouseNum1());
				billingAddressDO.setStrCountry(zcaAdrc.getCountry());
				billingAddressDO.setStrZip(zcaAdrc.getPostCode1());	
				billingAddressDO.setStrApartNum(zcaAdrc.getHouseNum2());
				billingAddressDO.setStrAddressID(zcaAdrc.getAddrnumber());
				
			}
			else{
				billingAddressDO.setErrorCode(Constants.MSG_ERR_GET_PROFILE_BILLING_ADDRESS);
				billingAddressDO.setErrorMessage(Constants.MSG_ERR_GET_PROFILE_BILLING_ADDRESS);
			}
			
			contractAccountDO.setBillingAddressDO(billingAddressDO);
			
			
			if(null != zcontractOutputList  && !zcontractOutputList.isEmpty()){
				zcontractOutput = new ZcontractOutput[zcontractOutputList.size()];
				
				for(int forCnt=0;forCnt<zcontractOutput.length;forCnt++){
					zcontractOutput[forCnt] = zcontractOutputList.get(forCnt);
				}
				
				if(zcontractOutput.length > 1){
					contractAccountDO.setStrMultiContractFlag(FLAG_X);
				}
				else{
					contractAccountDO.setStrMultiContractFlag(FLAG_O);
				}
				
				List<ContractDO> contracArrList = new LinkedList<>();
				int counter = 0;
				for(ZcontractOutput contractOutput: zcontractOutput){
					if (CommonUtil.checkInactiveAccount(companyCode, contractOutput.getExAuszdat())) {
						continue;
					}
					contractDO = new ContractDO();
					offerDO = new com.multibrand.domain.OfferDO();
					serviceAddressDO = new AddressDO();
										
					contractDO.setStrContractID(contractOutput.getExVertrag());
					contractDO.setStrESIID(contractOutput.getExEsiid());
					contractDO.setStrOfferCode(contractOutput.getExOfferCode());
					contractDO.setStrContractStartDate(contractOutput.getExVbeginn());
					contractDO.setStrContractEndDate(contractOutput.getExVende());
					contractDO.setStrMoveInDate(contractOutput.getExEinzdat());
					contractDO.setStrMoveOutDate(contractOutput.getExAuszdat());
					
					
					contractDO.setStrGuardLightFlag(CommonUtil.getFlagValue(contractOutput.getExGuard()));
					
					contractDO.setStrContractLength(contractOutput.getExContractLength());
					contractDO.setStrOfferTeaser(contractOutput.getExOfferTeaser());
					contractDO.setStrAvgPrice(contractOutput.getExAvgPrice().toString());
					contractDO.setStrCancelFee(contractOutput.getExCancFee().toString());			
					contractDO.setStrMeterNumber(contractOutput.getExGeraet());
					contractDO.setStrMeterType(contractOutput.getExMeterType());
					contractDO.setStrContractLegacyAccount(contractOutput.getExCsp());
					//US US12202 Changes - DK - 09/19/2019
					String  strTouFlag = contractOutput.getExTou();
					if(strTouFlag!= null && StringUtils.isNotBlank(strTouFlag) && strTouFlag.equalsIgnoreCase("X"))
					{
						responseMap.put(contractOutput.getExVertrag(), contractOutput.getExVertrag()+"-"+strTouFlag);
					}
					else
					{
						responseMap.put(contractOutput.getExVertrag(), contractOutput.getExVertrag()+"-"+null);
					}
					//Populating Service Address
					zcontractAdrc = contractOutput.getExServiceAdrc();	
					
					if(null != zcontractAdrc){
						
						serviceAddressDO.setStrCity(zcontractAdrc.getExCity1());
						serviceAddressDO.setStrZip(zcontractAdrc.getExPostCode1());
						serviceAddressDO.setStrState(zcontractAdrc.getExRegion());
						serviceAddressDO.setStrStreetName(zcontractAdrc.getExStreet());
						serviceAddressDO.setStrStreetNum(zcontractAdrc.getExHouseNum1());
						serviceAddressDO.setStrAddressLine(zcontractAdrc.getExAddressLine());
						serviceAddressDO.setStrApartNum(zcontractAdrc.getExHausNum2());
						
					}
					else{
						
						serviceAddressDO.setErrorCode(Constants.MSG_ERR_GET_PROFILE_SERVICE_ADDRESS);
						serviceAddressDO.setErrorMessage(Constants.MSG_ERR_GET_PROFILE_SERVICE_ADDRESS);
						
					}					
					offerDO.setStrOfferCode(contractOutput.getExOfferCode());
					offerDO.setStrContractTerm(contractOutput.getExContractLength());
					offerDO.setStrOfferTeaser(contractOutput.getExOfferTeaser());					
					contractDO.setServiceAddressDO(serviceAddressDO);
					contractDO.setCurrentPlan(offerDO);	
					contracArrList.add(contractDO);	
					
				}
				contractDOList = new ContractDO[contracArrList.size()];
				for(ContractDO contractDOTemp :contracArrList) {
					contractDOList[counter] = contractDOTemp;
					counter++;
				}
				
			}
			else{
				
				contractAccountDO.setErrorCode(Constants.MSG_ERR_GET_PROFILE_CONTRACT_DETAILS);
			}
			
			contractAccountDO.setListOfContracts(contractDOList);
			
			profileResponse.setContractAccountDO(contractAccountDO);
		}
		
		if(null != exReturnCode.value && ! Constants.SUCCESS_RESPONSE.equals(exReturnCode.value)){
			
			profileResponse.setErrorCode(Constants.MSG_CCSERR_+exReturnCode.value+Constants._GET_PROFILE);
		} else if((null == zcaOutputList  || zcaOutputList.isEmpty()) && exReturnCode.value == null){
			
			profileResponse.setErrorCode(Constants.MSG_SYSTEM_UNAVAILABLE);
		} 
							
		utilityloggerHelper.logTransaction("getProfile", false, request,profileResponse, profileResponse.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);

		logger.info("ProfileService.getProfile::::::::::::::::::::end");
		responseMap.put("profileResponse", profileResponse);
		responseMap.put("profileSuerStats", zesZesuerStat);
		return responseMap;
	}
	/**
	 * This will not call the logging framework
	 * @param accountNumber
	 * @return
	 * @throws RemoteException
	 */
	/**public ProfileResponse getProfile(String accountNumber)
			throws RemoteException {

		ProfileDomain proxy = getProfileDomainProxy();
		ProfileResponse response = proxy.getProfile(accountNumber);
		
		return response;
	}
	 * @throws Exception **/

	public CrmProfileResponse getCRMProfile(CrmProfileRequest crmProfileRequest, String companyCode, String sessionId) throws RemoteException {

		ProfileDomain proxy = getProfileDomainProxy();
		long startTime = CommonUtil.getStartTime();
		CrmProfileResponse response = null;
		try{
		response= proxy.getCRMProfile(crmProfileRequest);
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(crmProfileRequest));
			logger.error(ex);
			utilityloggerHelper.logTransaction("getCRMProfile", false, crmProfileRequest,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("getCRMProfile", false, crmProfileRequest,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(crmProfileRequest));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}

	/**
	 * @author Kdeshmu1
	 * @param request
	 * @return UpdateAddressResponse
	 * @throws Exception 
	 */
	public UpdateAddressResponse updateBillingAddress(
			UpdateAddressRequest request, String companyCode, String sessionId) throws RemoteException {

		ProfileDomain proxy = getProfileDomainProxy();
		long startTime = CommonUtil.getStartTime();
		UpdateAddressResponse response=null;
		try{
			response = proxy.updateBillingAddress(request);
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateBillingAddress", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		utilityloggerHelper.logTransaction("updateBillingAddress", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}

	/**
	 * @author kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public UpdateContactResponse updateContactInfoWS(
			UpdateContactRequest request, String companyCode, String sessionId) throws Exception {
		ProfileDomain proxy = getProfileDomainProxy();
		long startTime = CommonUtil.getStartTime();
		UpdateContactResponse  response= null;
		try{
			response= proxy.updateContactInfo(request);
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateContactInfoWS", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
        utilityloggerHelper.logTransaction("updateContactInfoWS", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        if(logger.isDebugEnabled()){
	        logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
        }
		return response;
	}
	
	/**
	 * @author mshukla1
	 * @param request
	 * @throws RemoteException
	 * Method added for update CRM call
	 * */
	public UpdateContactResponse updateContactInfo(
			UserRegistrationRequest request, String businessPartner,String sessionId,String companyCode,String source, boolean isMarkettingPrefOptIn) throws RemoteException {

		logger.info("Update ContactInfo Call starts..");
		UpdateContactResponse response = new UpdateContactResponse();
		
		URL url = ZECrmBpContactDetailNew_Service.class
				.getResource("crm_bp_contact_detail_new.wsdl");

	
		ZECrmBpContactDetailNew_Service port = new ZECrmBpContactDetailNew_Service(url);

		ZECRMBPCONTACTDETAILNEW stub = port.getZECrmBpContactDetailNew();

		BindingProvider binding = (BindingProvider) stub;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));

		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));

		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(CONTACT_DETAIL_ENDPOINT_URL_JNDINAME));

		
		String imRequestType = IM_REQUEST_TYPE_UPDATE;
		String imSubscriberId = WEB_SUBSCRIBER_ID;
		String actionFlag = CRM_UPDATE_ACTION;

		javax.xml.ws.Holder<TableOfZesEmailsNew2> hTZEmailNew2 = new javax.xml.ws.Holder<>();
		TableOfZesEmailsNew2 tZEmailNew2 = new TableOfZesEmailsNew2();
		List<ZesEmailsNew2> tZEmailNew2List = tZEmailNew2.getItem();

		ZesEmailsNew2 emailsNew2s = new ZesEmailsNew2();

		emailsNew2s.setAction(actionFlag);
		emailsNew2s.setEmail(request.getEmail());

		emailsNew2s.setVkont(CommonUtil.paddedCa(request.getAccountNumber()));
		emailsNew2s.setExtId(request.getUniqueId());
		emailsNew2s.setPartner(businessPartner);
		tZEmailNew2List.add(emailsNew2s);

		hTZEmailNew2.value=tZEmailNew2;
		
		javax.xml.ws.Holder<TableOfZesPhoneNew2> hTZPhoneNew2 = new javax.xml.ws.Holder<>();
		javax.xml.ws.Holder<TableOfZesUnsubReason> hTZUnsubReason = new  javax.xml.ws.Holder<>();
		javax.xml.ws.Holder<TableOfZesCommPref2> hTZCommPref2 = new  javax.xml.ws.Holder<>();
		
		TableOfZesCommPref2 tZCommPref2 = new TableOfZesCommPref2();
		List<ZesCommPref2> tZCommPref2List = tZCommPref2.getItem();
		if (source != null && source.equalsIgnoreCase(MOBILE)) {
			
			ZesCommPref2 commPref2s = new ZesCommPref2();
			
			if (isMarkettingPrefOptIn) {
				commPref2s.setAtnam("MKT_EMAIL");
				commPref2s.setPartner(businessPartner);
				commPref2s.setAtwrt("Y");
				tZCommPref2List.add(commPref2s);
			} 
			
			
			
		}
		hTZCommPref2.value = tZCommPref2;
		
		javax.xml.ws.Holder<TableOfBapiret2> hTBapiret2 = new  javax.xml.ws.Holder<>();
		long startTime = CommonUtil.getStartTime();
		stub.zeCrmBpContactDetailNew(imRequestType, imSubscriberId, hTZCommPref2, hTZEmailNew2, hTZPhoneNew2, hTBapiret2, hTZUnsubReason);
		
		/*
		 * error code response
		 */
		
		TableOfBapiret2 tBapiret2 = new TableOfBapiret2();
		tBapiret2 = hTBapiret2.value;
        List<Bapiret2> tBapiret2List= tBapiret2.getItem();
		for (Bapiret2 bapiret2 : tBapiret2List) {
			if(bapiret2.getType().equals(TYPE_E)){
				if (StringUtils.isNotEmpty(bapiret2.getMessage())) {
					response.setErrorMessage(bapiret2.getMessage());
				}
				if (StringUtils.isNotEmpty(bapiret2.getNumber())) {
					response.setErrorCode(MSG_ERR_UPD_CONTACT);
					logger.info("Error in updation "+MSG_ERR_UPD_CONTACT);
				}
			}
		}
		logger.info("Update ContactInfo Call Ends.....");
		utilityloggerHelper.logTransaction("updateContactInfo", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}

	/**
	 * @author mshukla1
	 * @param request
	 * @return
	 * @throws Exception 
	 */

	public GetContractInfoResponse getContractInfo(String caNumber,
			String bpNumber, String esid, String contractId, String languageCode, String companyCode, String sessionId, String applicationArea)
			throws Exception {

		logger.info("[Profile Service ]::::::getContractInfo");
		GetContractInfoResponse response = new GetContractInfoResponse();

		URL url = ZEISUGETOFFERDATAFORSWAP_Service.class
				.getResource("Z_E_ISU_GET_OFFERDATA_FOR_SWAP-RPM.wsdl");

		if (url == null)
			logger.info("WSDL not initialised");

		ZEISUGETOFFERDATAFORSWAP_Service port = new ZEISUGETOFFERDATAFORSWAP_Service(
				url);
		ZEISUGETOFFERDATAFORSWAP stub = port.getZEISUGETOFFERDATAFORSWAP();

		BindingProvider binding = (BindingProvider) stub;

		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
				this.envMessageReader.getMessage(CCS_USER_NAME));

		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
				this.envMessageReader.getMessage(CCS_PSD));

		binding.getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				this.envMessageReader.getMessage(GET_CONTRACT_INFO_ENDPOINT_URL_JNDINAME));

		ZeiSwapOfferInput zeiSwapOfferInputObj = new ZeiSwapOfferInput();
		List<ZesSwapOfferInput> zesSwapOfferInputList = zeiSwapOfferInputObj.getItem();
		
		ZesSwapOfferInput zesSwapofferInput = new ZesSwapOfferInput();
		zesSwapofferInput.setEsid(esid);
		zesSwapofferInput.setLangu(languageCode);
		zesSwapofferInput.setPartner(bpNumber);
		zesSwapofferInput.setVertrag(contractId);
		zesSwapofferInput.setVkont(CommonUtil.paddedCa(caNumber));
		zesSwapOfferInputList.add(zesSwapofferInput);
		
        String imCaller = WEB_SUBSCRIBER_ID;		
        javax.xml.ws.Holder<ZeiCampEnviDetails> hZeiCampEnvrDetails = new javax.xml.ws.Holder<>();
        javax.xml.ws.Holder<ZeiSwapOutput> hZeiSwapOutput = new javax.xml.ws.Holder<>();
        javax.xml.ws.Holder<ZeiOfrcdFlag> hZeiOffrCDFlag = new javax.xml.ws.Holder<>();
        
        long startTime = CommonUtil.getStartTime();
        try{
        stub.zeIsuGetOfferdataForSwap(imCaller, zeiSwapOfferInputObj, "", null, hZeiCampEnvrDetails, hZeiSwapOutput, hZeiOffrCDFlag);
        }catch(Exception ex){
        	logger.error(ex);
        	utilityloggerHelper.logTransaction("getContractInfo", false, zeiSwapOfferInputObj,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        	if(logger.isDebugEnabled())
        		logger.debug(XmlUtil.pojoToXML(zeiSwapOfferInputObj));
			throw ex;
        }
        ZeiSwapOutput zeiSwapOutput = hZeiSwapOutput.value;
        ZeiCampEnviDetails zeiCampEnvrDetails = hZeiCampEnvrDetails.value;
        ZeiOfrcdFlag zeiOfferCdFlag = hZeiOffrCDFlag.value;
            
        ZesSwapOutput zesSwapOutput = zeiSwapOutput.getItem().get(0);
            IsuBapiret2T isuBapiRet2T = zesSwapOutput.getMessageTab();
            
            List<com.nrg.cxfstubs.contractinfo.Bapiret2> bapiRet2T = isuBapiRet2T.getItem();
            response = handleContractInfoResponse(zeiSwapOutput,zeiCampEnvrDetails,zeiOfferCdFlag, applicationArea);
            if((zesSwapOutput.getEligOffers()==null) ||(zesSwapOutput.getEligOffers().getItem().isEmpty()))
            {
            	for(com.nrg.cxfstubs.contractinfo.Bapiret2 bapiret2 : bapiRet2T)
                {
                	if(bapiret2.getType().equalsIgnoreCase(TYPE_E) && (bapiret2.getNumber())!= null || bapiret2.getMessage() != null){
                		
                		response.setResultCode(RESULT_CODE_CCS_ERROR);
                		response.setResultDescription(bapiret2.getMessage());
                		return response;
                	}
                }
            }
			response.setResultCode(RESULT_CODE_SUCCESS);
			response.setResultDescription(MSG_SUCCESS);
			utilityloggerHelper.logTransaction("getContractInfo", false, zeiSwapOfferInputObj,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled()){
				logger.debug(XmlUtil.pojoToXML(zeiSwapOfferInputObj));
				logger.debug(XmlUtil.pojoToXML(response));
			}
			logger.info("[ProfileService ]::::: getContractInfo ");
			return response;
				

	}

	private GetContractInfoResponse handleContractInfoResponse(
		  ZeiSwapOutput zeiSwapOutput,ZeiCampEnviDetails zeiCampEnvrDetails,ZeiOfrcdFlag zeiOfferCdFlag, String applicationArea) {

		GetContractInfoResponse response = new GetContractInfoResponse();
		ZesSwapOutput zesSwapOutput = zeiSwapOutput.getItem().get(0);
        List<ZesOfrcdFlag> zesOfferCDFlagList = zeiOfferCdFlag.getItem();
        List<ZesCampEnviDetails> zesCampEnvDetailsList = zeiCampEnvrDetails.getItem();
        
		// Populating pending swap data
        logger.info("ZeiSwapOutPut List Size:{} ",zeiSwapOutput.getItem().size());
		PendingSwapDO pendingSwapDO = new PendingSwapDO();

		pendingSwapDO.setStrBPNumber(zesSwapOutput.getKeydata().getPartner());
		pendingSwapDO.setStrCANumber(zesSwapOutput.getKeydata().getVkont());
		pendingSwapDO.setStrContractID(zesSwapOutput.getKeydata().getVertrag());
		pendingSwapDO.setStrESIID(zesSwapOutput.getKeydata().getEsid());
		pendingSwapDO.setStrOfferCode(zesSwapOutput.getPendingSwap().getOfferCode());
		pendingSwapDO.setStrOfferCellDesc(zesSwapOutput.getPendingSwap().getOfferCellDesc());
		pendingSwapDO.setStrCampaignCode(zesSwapOutput.getPendingSwap().getZzCampaignCode());
		pendingSwapDO.setStrOfferCellCd(zesSwapOutput.getPendingSwap().getZzOfferCellCd());
		pendingSwapDO.setStrProductCd(zesSwapOutput.getPendingSwap().getZzProductCd());
		pendingSwapDO.setStrStartDate(zesSwapOutput.getPendingSwap().getDatStartPen());
		pendingSwapDO.setStrEndDate(zesSwapOutput.getPendingSwap().getDatEndPen());
		pendingSwapDO.setStrSignedDate(zesSwapOutput.getPendingSwap().getDatSigned());
		pendingSwapDO.setStrProductDesc(zesSwapOutput.getPendingSwap().getRatecatgDescrip());
		
		response.setStrDefaultOfferFlag(zesSwapOutput.getZdefaultOfrFlag());
		response.setPendingSwapDO(pendingSwapDO);
		if(zesSwapOutput.getEligOffers() != null){
		   response.setEligibleOffersList(populateEligibleOffers(zesSwapOutput,zesOfferCDFlagList,zesCampEnvDetailsList, applicationArea));
		}  

		return response;
	}

	private OfferDO[] populateEligibleOffers(ZesSwapOutput zesSwapOutput,List<ZesOfrcdFlag> zesOfferCDFlagList,List<ZesCampEnviDetails> zesCampEnvDetailsList, String applicationArea) {

		logger.info("Setting eligible offers::::::");
		List<ZesEligoffer> zesEligibleOfferList = zesSwapOutput.getEligOffers().getItem();
		
		
		logger.info("zesEligibleOfferList:::::: list size {}", zesEligibleOfferList.size());

		List <OfferDO> eligibleOffersList = new ArrayList<>();
		
		if (!CommonUtil.isCollectionNullOrEmpty(zesEligibleOfferList)) {

			
			for (com.nrg.cxfstubs.contractinfo.ZesEligoffer zesEligoffer : zesEligibleOfferList) {
				try {
					
					
					if (isExcludeOfferList(applicationArea, zesEligoffer)) {
						continue;
					}
					
					OfferDO offerDO = new OfferDO();
					
					List<Map<String,Object>> offerCategoryLookupDetailsList = offerService.getOfferCategories(zesEligoffer.getOfferCode());
					
					String strOfferCategory = getStrOfferCategory(offerCategoryLookupDetailsList);
					
					offerDO.setStrOfferCategory(strOfferCategory);
					offerDO.setStrOfferCode(zesEligoffer
							.getOfferCode());
					offerDO.setStrCampaignCode(zesEligoffer
							.getCampaignCd());
					offerDO.setStrOfferCellTrackCode(zesEligoffer
							.getPromoCd());
					
					offerDO.setStrPromoCode(zesEligoffer
							.getPromoCd());
					offerDO.setStrTarrifType(zesEligoffer
							.getTariftyp());
					//Start : CampEnv Detail & Segment Flag data
					logger.info("FillingCamp Envr Details{}", zesCampEnvDetailsList.size());
					List<CampEnvironmentDO> campEnvrDOList = new ArrayList<>();
					Iterator<ZesCampEnviDetails> zesEnvCampDetailsItr = zesCampEnvDetailsList.iterator();
					
					//Refactoring the code due complexity of the method
					handleCampEnvironmentailDetails(zesEligoffer, campEnvrDOList, zesEnvCampDetailsItr);
					
					int campDtCounter=0;
					CampEnvironmentDO[] campDOArray = new CampEnvironmentDO[campEnvrDOList.size()];
					logger.info("CampDO ARRAY Size {}", campDOArray.length);
					
					for(CampEnvironmentDO campDO:campEnvrDOList)
					{
						campDOArray[campDtCounter] = campDO;
						campDtCounter++;
					}
					
					
					//Filling Segment Flang Data
					logger.info("Filling Segment Flag Data{}", zesOfferCDFlagList.size());
					List<SegmentedFlagDO> segmentFlagDOList = new ArrayList<>();
					Iterator<ZesOfrcdFlag> ofrCDItr = zesOfferCDFlagList.iterator();
					
					//Refactoring the code due complexity of the method
					handleSegmentFlangData(zesEligoffer, segmentFlagDOList, ofrCDItr);
					
					int segMCounter=0;
					SegmentedFlagDO[] segmentFlagDOArray = new SegmentedFlagDO[segmentFlagDOList.size()];
					logger.info("SegmentedFlagDOArray length{} ", segmentFlagDOArray.length);
					for(SegmentedFlagDO segmDO:segmentFlagDOList)
					{
						segmentFlagDOArray[segMCounter] = segmDO;
						segMCounter++;
					}
					
					offerDO.setCampEnvironmentDetails(campDOArray);
					offerDO.setSegmentedFlags(segmentFlagDOArray);
					//End : CampEnv Detail & Segment Flag data
					
					offerDO.setStrBundlingGroup(zesEligoffer.getCampaignData().getItem().get(0).getBundlingGroup());
					offerDO.setStrOfferRank(""+ zesEligoffer.getPromoRank());
					offerDO.setStrEFLIssueDate(zesEligoffer.getCampaignData().getItem().get(0).getEflIssueDate());
					offerDO.setStrCancelFee(String
							.valueOf(zesEligoffer.getCampaignData().getItem()
									.get(0).getPenaltyValue()));
					offerDO.setStrContractTerm(zesEligoffer
							.getCampaignData().getItem().get(0)
							.getContractLength());
					offerDO.setStrPlanName(zesEligoffer
							.getCampaignData().getItem().get(0).getPlanname().getDescrip());

					offerDO = this
							.populatePriceAndDocTypelists(
									offerDO, zesEligoffer);
					/** START Added for Attribute include in response changes */
					offerDO.setAttribute1(zesEligoffer.getAttribute01());
					offerDO.setAttribute2(zesEligoffer.getAttribute02());
					offerDO.setAttribute3(zesEligoffer.getAttribute03());
					offerDO.setAttribute4(zesEligoffer.getAttribute04());
					offerDO.setAttribute5(zesEligoffer.getAttribute05());
					offerDO.setAttribute6(zesEligoffer.getAttribute06());
					offerDO.setAttribute7(zesEligoffer.getAttribute07());
					offerDO.setAttribute8(zesEligoffer.getAttribute08());
					offerDO.setAttribute9(zesEligoffer.getAttribute09());
					offerDO.setAttribute10(zesEligoffer.getAttribute10());
					/** END Added for Attribute include in response changes */
					
					/** START Added for TDSP code include in response changes */
					offerDO.setStrTDSPCode(zesEligoffer.getCampaignData().getItem().get(0).getServiceid());
					/** END Added for TDSP code include in response changes */
					
					eligibleOffersList.add(offerDO);

				} catch (Exception ex) {
					logger.error("Exception Occuured while filling data ::{}", ex.getMessage());
					
				}
			}
		}
		
		return eligibleOffersList.toArray(new OfferDO[eligibleOffersList.size()]);
	}

	private void handleSegmentFlangData(com.nrg.cxfstubs.contractinfo.ZesEligoffer zesEligoffer,
			List<SegmentedFlagDO> segmentFlagDOList, Iterator<ZesOfrcdFlag> ofrCDItr) {
		while(ofrCDItr.hasNext()){
			
			ZesOfrcdFlag zesOFRCd = ofrCDItr.next();
			if(zesOFRCd.getOfferCode().equals(zesEligoffer.getOfferCode()))
			{
				logger.info("Segmented Flag Array match found{} ", zesOFRCd.getOfferCode());
				SegmentedFlagDO segmFlagDo = new SegmentedFlagDO();
				segmFlagDo.setSegmentFlag(zesOFRCd.getSegmentFlag());
				segmentFlagDOList.add(segmFlagDo);
			}
		}
	}

	private void handleCampEnvironmentailDetails(com.nrg.cxfstubs.contractinfo.ZesEligoffer zesEligoffer,
			List<CampEnvironmentDO> campEnvrDOList, Iterator<ZesCampEnviDetails> zesEnvCampDetailsItr) {
		while(zesEnvCampDetailsItr.hasNext())
		{
			ZesCampEnviDetails zesCampEnvDt = zesEnvCampDetailsItr.next();
			
			if(zesCampEnvDt.getCcsProductCd().equals(zesEligoffer.getTariftyp()))
			{
				logger.info("Camp ENvr Match Found {}", zesCampEnvDt.getCcsProductCd());
				CampEnvironmentDO campDO = new CampEnvironmentDO();
				campDO.setCalcOperand(zesCampEnvDt.getCalcOperand());
				campDO.setValue(zesCampEnvDt.getValue().toString());
				campEnvrDOList.add(campDO);
			}
		}
	}

	private String getStrOfferCategory(List<Map<String, Object>> offerCategoryLookupDetailsList) {
		String strOfferCategory = "";
		for (Map<String, Object> offerMap : offerCategoryLookupDetailsList) {
			strOfferCategory = (String) offerMap.get(OE_OFFER_CATEGORY);
			if (StringUtils.isBlank(strOfferCategory))
				strOfferCategory = EMPTY;
		}
		return strOfferCategory;
	}

	private boolean isExcludeOfferList(String applicationArea, ZesEligoffer zesEligoffer ) {

		boolean isOfferExclude= false;
		
		if( ! org.apache.commons.lang3.StringUtils.isEmpty(applicationArea) &&  applicationArea.equalsIgnoreCase(APPLICATION_SWAP_AREA) &&  zesEligoffer.getAttribute01()!=null && (StringUtils.isNotBlank(zesEligoffer.getAttribute01()))
				&& Arrays.asList(excludeOfferList).contains(zesEligoffer.getAttribute01())) {
				isOfferExclude = true;
		}
		
		return isOfferExclude;
		
	}

	/**
	 * Populate Pricing and DocType lists for eligible offers
	 * 
	 * @param offerDO
	 * @param zesEligoffer
	 * @return
	 */
	private com.multibrand.vo.response.OfferDO populatePriceAndDocTypelists(
			com.multibrand.vo.response.OfferDO offerDO,
			ZesEligoffer zesEligoffer) {
		List<ZesDocid> zesDocidList = zesEligoffer.getCampaignData().getItem().get(0).getDocidTab().getItem();

		for (ZesDocid zesDocid : zesDocidList) {
			String docType = zesDocid.getDocType();

			if (EFL.equalsIgnoreCase(docType)) {
				offerDO.setStrEFLDocID(zesDocid.getDocId());
				offerDO.setStrEFLSmartCode(zesDocid.getSmartCode());
				offerDO.setStrEflUrl(CommonUtil.getDynamicEflUrl(zesDocid.getDocId(), zesDocid.getSmartCode()));
			} else if (TOS.equalsIgnoreCase(docType)) {
				offerDO.setStrTOSDocID(zesDocid.getDocId());
				offerDO.setStrTOSSmartCode(zesDocid.getSmartCode());
			} else if (YRAAC.equalsIgnoreCase(docType)) {
				offerDO.setStrYRAACDocID(zesDocid.getDocId());
				offerDO.setStrYRAACSmartCode(zesDocid.getSmartCode());
			}
		}

		List<ZesAvgPrice> zesAvgPriceList = zesEligoffer.getCampaignData().getItem().get(0).getAvgPriceTab().getItem();
		OfferPriceDO[] offerPriceDOList = new OfferPriceDO[zesAvgPriceList.size()];
		int count = 0;
		for (ZesAvgPrice zesAvgPrice : zesAvgPriceList) {

			offerPriceDOList[count] = new OfferPriceDO();
			offerPriceDOList[count].setPriceType(zesAvgPrice.getAvgPriceType());
			offerPriceDOList[count].setPriceTypeValue(zesAvgPrice.getString1());
			offerPriceDOList[count].setStartDate(zesAvgPrice.getDateStart());
			offerPriceDOList[count].setEndDate(zesAvgPrice.getDateEnd());
			offerPriceDOList[count].setPrice(zesAvgPrice.getAvgPrice().toString());
			offerPriceDOList[count].setOfferPriceCode(zesAvgPrice.getString2());
			count++;
		}
		offerDO.setOfferPriceEntry(offerPriceDOList);

		return offerDO;

	}


	/**
	 * This method is used to get the Esid details.
	 * 
	 * @param companyCode
	 *            represent the compony code number
	 * @param esId
	 *            represent the esid number.
	 * @return EsidProfileResponse Details of the esid.
	 * @throws Exception 
	 */
	public EsidProfileResponse getESIDProfile(String companyCode, String esid, String sessionId)
			throws Exception {
		ProfileDomain proxy = getProfileDomainProxy();
		
		String request = "esid="+esid+",companyCode="+companyCode;
         long startTime = CommonUtil.getStartTime();
		EsidProfileResponse response = null;
		try{
			response= proxy.getESIDProfile(companyCode, esid);
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("getESIDProfile", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
		utilityloggerHelper.logTransaction("getESIDProfile", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}
	
	public ProductUpdateResponse productUpdate(String accountNumber, String action ,
			 String objectId, String extUi, String enrollType ,
			 String requestDate , String manuPartNo, String companyCode, String sessionId) throws Exception{
		
		String request="accountNumber="+accountNumber+",action="+action+",objectId="+objectId+",extUi="+extUi+",entrollType="+enrollType+",requestDate="+requestDate+",manuPartNo="+manuPartNo+",companyCode="+companyCode; 
		ProductUpdateResponse productResponse = new ProductUpdateResponse();
		URL url = ZECRMVASWEBPRODUPDATE_Service.class.getResource("Z_E_CRM_VAS_WEB_PROD_UPDATE.wsdl");
		
		ZECRMVASWEBPRODUPDATE_Service port = new ZECRMVASWEBPRODUPDATE_Service(url);
		ZECRMVASWEBPRODUPDATE stub = port.getZECRMVASWEBPRODUPDATE();
       BindingProvider binding = (BindingProvider)stub;
       binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.envMessageReader.getMessage(CCS_USER_NAME));       
       binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.envMessageReader.getMessage(CCS_PSD));       
       binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.envMessageReader.getMessage(CCS_CRM_PROD_UPDATE));
       BigDecimal bd = new BigDecimal(1);
       ZetEnrollProd zetEnrollProd = new ZetEnrollProd();
       List<ZesEnrollProd> enrollProdList = zetEnrollProd.getItem();
       ZesEnrollProd zesEnrollProd = new ZesEnrollProd();
       zesEnrollProd.setCampId("");
       zesEnrollProd.setManuPartNo(manuPartNo);
       zesEnrollProd.setProductId("");
       zesEnrollProd.setQuantity(bd);
       enrollProdList.add(zesEnrollProd);
              
       javax.xml.ws.Holder<com.nrg.cxfstubs.sundriverclub.ZetWebncProducts> hZetWebncProducts = new javax.xml.ws.Holder<>();
       javax.xml.ws.Holder<Bapiret2T> hTBapiret2 = new javax.xml.ws.Holder<>();
       long startTime = CommonUtil.getStartTime();
		boolean isEnrolled = false;
       try{
			if (action.equalsIgnoreCase("2")) {
				stub.zeCrmVasWebProdUpdate("1", "", accountNumber, "", enrollType, extUi, objectId, requestDate,
						zetEnrollProd, hZetWebncProducts, hTBapiret2);
				ZetWebncProducts webncProducts = hZetWebncProducts.value;
				List<ZesWebncProducts> webncProductsList = webncProducts.getItem();
				if (webncProductsList != null && !webncProductsList.isEmpty()) {
					for (ZesWebncProducts products : webncProductsList) {
						ZetPrdDetails zttypeprods = products.getProducts();
						List<ZesPrdDetails> zvvasprodsList = zttypeprods.getItem();
						for (ZesPrdDetails zvvasprods : zvvasprodsList) {
							if (zvvasprods.getManuPartNo().equalsIgnoreCase(manuPartNo)) {
								isEnrolled = true;
								break;
							}
						}
					}
				}
			}
			if (!isEnrolled) {
				stub.zeCrmVasWebProdUpdate(action, "", accountNumber, "", enrollType, extUi, objectId, requestDate,
						zetEnrollProd, hZetWebncProducts, hTBapiret2);
			}
       }catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("productUpdate", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;
		}
       if(action.equalsIgnoreCase("3"))
       {
       	Bapiret2T bapiret2T = hTBapiret2.value;

       	List<com.nrg.cxfstubs.sundriverclub.Bapiret2> listBapiret2 = bapiret2T.getItem();
       	if(listBapiret2!=null && !listBapiret2.isEmpty())
       	{
       		for(com.nrg.cxfstubs.sundriverclub.Bapiret2 bapiret2:listBapiret2)
       		{
       			if(bapiret2.getNumber()!=null && bapiret2.getNumber().equals("111"))
       				productResponse.setResultCode(RESULT_CODE_SUCCESS);
       			else
       				productResponse.setResultCode(RESULT_CODE_CCS_ERROR);
       			productResponse.setResultDescription(bapiret2.getMessage());
       		}
       	}
       	else
       	{
       		productResponse.setResultCode(RESULT_CODE_THREE);
       		productResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
       	}
       	utilityloggerHelper.logTransaction("productUpdate", false, request,productResponse, productResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
       	if(logger.isDebugEnabled()){
	       	logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(productResponse));
       	}
       	return productResponse;
       }
       else if(action.equalsIgnoreCase("1"))
       {
       	ZetWebncProducts webncProducts = hZetWebncProducts.value;
       	List<ZesWebncProducts> webncProductsList = webncProducts.getItem();
       	if(webncProductsList!=null && !webncProductsList.isEmpty())
       	{
	        	int counter=0;        
	        	ProductDO[] productDO = new ProductDO[webncProductsList.size()];
	        	for(ZesWebncProducts products:webncProductsList)
	        	{
	        		productDO[counter] = new ProductDO();
	        		productDO[counter].setAccountNumber(products.getVkont());
	        		productDO[counter].setBpNumber(products.getPartner());
	        		productDO[counter].setEsid(products.getExtUi());
					productDO[counter].setContractStartDate(products.getCtrtStartDate());
	        		ZetPrdDetails zttypeprods = products.getProducts();
	        		List<ZesPrdDetails> zvvasprodsList = zttypeprods.getItem();
	        		Products[] product = new Products[zvvasprodsList.size()];
	        		int innerCounter = 0;
	        		for(ZesPrdDetails zvvasprods:zvvasprodsList)
	        		{
	        			product[innerCounter] = new Products();
	        			product[innerCounter].setProductId(zvvasprods.getProductId());
	        			product[innerCounter].setManuPartNo(zvvasprods.getManuPartNo());
	        			product[innerCounter].setDescription(zvvasprods.getDescription());
	        			product[innerCounter].setObjectId(zvvasprods.getObjectId());
	        			innerCounter++;
	        		} 
	        		productDO[counter].setProducts(product);
	        		counter++;
	        	}
	        	productResponse.setProductDO(productDO);
	        	productResponse.setResultCode(RESULT_CODE_SUCCESS);
	    		productResponse.setResultDescription(MSG_SUCCESS);
	        }
       	else
       	{
       		Bapiret2T bapiret2T = hTBapiret2.value;
       		List<com.nrg.cxfstubs.sundriverclub.Bapiret2> listBapiret2 = bapiret2T.getItem();
       		if(listBapiret2!=null && !listBapiret2.isEmpty())
       		{
       			for(com.nrg.cxfstubs.sundriverclub.Bapiret2 bapiret2:listBapiret2)
       			{
       				productResponse.setResultCode(RESULT_CODE_CCS_ERROR);
       				productResponse.setResultDescription(bapiret2.getMessage());
       			}
       		}
       		else
           	{
       			productResponse.setProductDO(new ProductDO[0]);
       			productResponse.setResultCode(RESULT_CODE_SUCCESS);
           		productResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
           	}
       	}
    	utilityloggerHelper.logTransaction("productUpdate", false, request,productResponse, productResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
       	return productResponse;
		} else if (action.equalsIgnoreCase("2")) {
			
		if (!isEnrolled) {
	       	Bapiret2T bapiret2T = hTBapiret2.value;
	
	       	List<com.nrg.cxfstubs.sundriverclub.Bapiret2> listBapiret2 = bapiret2T.getItem();
	       	if(listBapiret2!=null && !listBapiret2.isEmpty())
	       	{
	       		for(com.nrg.cxfstubs.sundriverclub.Bapiret2 bapiret2:listBapiret2)
	       		{
	       			if(bapiret2.getNumber()!=null && bapiret2.getNumber().equals("000"))
	       			{
	       				productResponse.setResultCode(RESULT_CODE_SUCCESS);
	       				productResponse.setResultDescription(MSG_SUCCESS);
					} else {
	       			
	       				productResponse.setResultCode(RESULT_CODE_CCS_ERROR);
	       				productResponse.setResultDescription(bapiret2.getMessage());
	       			}
	       		}
	       	}else {
					productResponse.setResultCode(RESULT_CODE_THREE);
					productResponse.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
					
				}
	    	utilityloggerHelper.logTransaction("productUpdate", false, request,productResponse, productResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
	       	return productResponse;
		} else {
			productResponse.setResultCode(RESULT_CODE_FOUR);
			productResponse.setResultDescription(ACCOUNT_ALREADY_ENROLLED);
		} 
	}
     	utilityloggerHelper.logTransaction("productUpdate", false, request,productResponse, productResponse.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
       return productResponse;
	}
	/**
	 * @author kdeshmu1
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public EnvironmentImpactsResponse environmentImpacts(String accountNumber, String companyCode, String sessionId )throws Exception
	{
		EnvironmentImpactsResponse response = new EnvironmentImpactsResponse();

		String request = "accountNumber="+accountNumber;
		String imCa = CommonUtil.paddedCa(accountNumber);
		
		logger.info("imCa:{} ",imCa);
		
		
		URL url = ZEISUENVIRONMENTALIMPACTS.class
		.getResource("Z_E_ISU_ENVIRONMENTAL_IMPACTS.wsdl");
		
		
		ZEWSENVIRONMENTALIMPACTS port = new ZEWSENVIRONMENTALIMPACTS(url);
		
		ZEISUENVIRONMENTALIMPACTS stub = port.getZEWSENVIRONMENTALIMPACTS();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(ENVIRONMENT_IMPACT_ENDPOINT_URL_JNDINAME));

		javax.xml.ws.Holder<com.nrg.cxfstubs.environmentalimpact.ZetEnviDetails> hTZETEnviDetails = new javax.xml.ws.Holder<>();
		javax.xml.ws.Holder<com.nrg.cxfstubs.environmentalimpact.Bapiret2T> hTBapiret2 = new  javax.xml.ws.Holder<>();
	    
		long startTime = CommonUtil.getStartTime();
		try{
			stub.zeIsuEnvironmentalImpacts(imCa,hTZETEnviDetails, hTBapiret2);
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("environmentalImpacts", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			throw ex;			
		}
		
		
		ZetEnviDetails htZetEnviDetails = hTZETEnviDetails.value;
		List<ZesEnviDetails> listZesEnviDetails = htZetEnviDetails.getItem();
				
		logger.info("Plan History List:{} ",listZesEnviDetails.size());
		
       	if(!listZesEnviDetails.isEmpty())
       	{	
		EnvironmentImpacts[] environmentImpacts = new EnvironmentImpacts[listZesEnviDetails.size()];
		int count=0;
		for(ZesEnviDetails zesEnviDetails:listZesEnviDetails)
		{
			environmentImpacts[count] = new EnvironmentImpacts();
			environmentImpacts[count].setInstallation(zesEnviDetails.getInstallation());
			environmentImpacts[count].setContractNumber(zesEnviDetails.getContract());
			
			environmentImpacts[count].setOperand(zesEnviDetails.getOperand());
			environmentImpacts[count].setMoveOutDate(zesEnviDetails.getMoveoutdate());
			environmentImpacts[count].setMoveInDate(zesEnviDetails.getMoveindate());
			environmentImpacts[count].setEsiid(zesEnviDetails.getEsiid());
			environmentImpacts[count].setValue(zesEnviDetails.getValue().toString());
			count++;
		}
		
		response.setEnvironmentImpacts(environmentImpacts);
		List<ZesEnviDetails> newList = profileHelper.sortList(listZesEnviDetails);		
		response.setCustomerSince(newList.get(0).getMoveindate());
		response.setResultCode(RESULT_CODE_SUCCESS);
		 response.setResultDescription(MSG_SUCCESS);
		logger.info("EnvironmentService ends");
       	}
       	else
       	{
       		com.nrg.cxfstubs.environmentalimpact.Bapiret2T bapiret2T = hTBapiret2.value;
       		List<com.nrg.cxfstubs.environmentalimpact.Bapiret2> listBapiret2 = bapiret2T.getItem();
       		if(listBapiret2!=null && !listBapiret2.isEmpty())
       		{
       			for(com.nrg.cxfstubs.environmentalimpact.Bapiret2 bapiret2:listBapiret2)
       			{
       				response.setResultCode(RESULT_CODE_CCS_ERROR);
       				response.setResultDescription(bapiret2.getMessage());
       			}
       		}
       		else
           	{
       			response.setResultCode(RESULT_CODE_THREE);
       			response.setResultDescription(RESULT_CODE_DESCRIPTION_NO_DATA);
           	}
		
	}
       	utilityloggerHelper.logTransaction("environmentalImpacts", false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
       	if(logger.isDebugEnabled()){
	       	logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
       	}
       	return response;
	}
	
	public SecondaryNameResponse secondaryNameUpdate(SecondaryNameUpdateReqVO request, String companyCode, String sessionId) {
		
		SecondaryNameResponse response = new SecondaryNameResponse();
		logger.info("ProfileService - secondaryNameUpdate ccs call starts...");
		long startTime = CommonUtil.getStartTime();
		try{
		String action = request.getAction();
        String bp=request.getBpid();
        String ca = CommonUtil.paddedCa(request.getAccountNumber());
        
        logger.info("Action in secondaryNameUpdate..{}",action);
        logger.info("BP Number in secondaryNameUpdate.{}",bp);
        logger.info("CA Number in secondaryNameUpdate..{}",ca);
        
        
		URL url = ZCRMWSBPRELATIONREADUPD_Service.class.getResource("Z_CRM_WS_BP_RELATION_READ_UPD.wsdl");
		
		ZCRMWSBPRELATIONREADUPD_Service port = new ZCRMWSBPRELATIONREADUPD_Service(url);
		
		ZCRMWSBPRELATIONREADUPD stub= port.getZCRMWSBPRELATIONREADUPD();
		
		BindingProvider binding = (BindingProvider)stub;
		
		binding.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,this.envMessageReader.getMessage(CCS_USER_NAME));
		
		binding.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,this.envMessageReader.getMessage(CCS_PSD));
		
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,this.envMessageReader.getMessage(CCS_SECONDARY_NAME_UPDATE_JNDINAME));


        javax.xml.ws.Holder<ZetPartnerNames> hzetPartNames = new Holder<>();
        ZetPartnerNames zetPartNames = new ZetPartnerNames();
        hzetPartNames.value = zetPartNames;        
        
        javax.xml.ws.Holder<com.nrg.cxfstubs.bprelationreadupd.Bapiret2T> hBapiRet2=new Holder<>();
        com.nrg.cxfstubs.bprelationreadupd.Bapiret2T bapiRet2T = new com.nrg.cxfstubs.bprelationreadupd.Bapiret2T();
        hBapiRet2.value=bapiRet2T;
        
        
        ZetSecondaryNames zetSecNames = new ZetSecondaryNames();
        List<ZesSecondaryNames> zesSecondaryNamesList = zetSecNames.getItem();
        ZesSecondaryNames zesSecondaryNames = new ZesSecondaryNames();
        
        zesSecondaryNames.setFirstName(request.getFirstName());
        zesSecondaryNames.setLastName(request.getLastName());
        zesSecondaryNames.setMiddleName(request.getMiddleName());
        zesSecondaryNames.setValidFrom(request.getValidFrom());
        zesSecondaryNames.setValidUntil(request.getValidUntil());
        zesSecondaryNames.setReltyp(DEFAULT_REL_TYPE);
        
        if(!action.equals("2"))
        	zesSecondaryNames.setBusinesspartner2(request.getBpid2());
        
        zesSecondaryNamesList.add(zesSecondaryNames);
        
        stub.zeWebBpRelationReadUpd(action, bp, ca, zetSecNames, hzetPartNames, hBapiRet2);
        logger.info("Method Invoked");
       com.nrg.cxfstubs.bprelationreadupd.Bapiret2T tbapiret2 = hBapiRet2.value;
       List<com.nrg.cxfstubs.bprelationreadupd.Bapiret2> listBapiret2 = tbapiret2.getItem();
       
    	if(!CommonUtil.isCollectionNullOrEmpty(listBapiret2))
     	{
     		for(com.nrg.cxfstubs.bprelationreadupd.Bapiret2 bapiret2:listBapiret2)
     		{
     			logger.info("Setting error codes using BApitRet2");
     			logger.info("Printintg Bapiret2 values...{} {} {}",bapiret2.getType(),bapiret2.getNumber(),bapiret2.getMessage());
     			if(bapiret2.getType().equalsIgnoreCase(TYPE_E) && (bapiret2.getNumber()!= null || bapiret2.getMessage() != null)){
     				
     				if(action.equals("4"))
     				{
     				   response.setResultCode(RESULT_CODE_SUCCESS);
     				   response.setResultDescription(bapiret2.getMessage());
     				   response.setSecondaryNames(new SecondaryName[0]);
     				  utilityloggerHelper.logTransaction(SECONDAY_NAME_UPDATE_LABEL, false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
     				  return response;
     				}
     				else
     				{
     				  response.setResultCode(RESULT_CODE_CCS_ERROR);
     				  response.setResultDescription(bapiret2.getMessage());
     				response.setSecondaryNames(new SecondaryName[0]);
     				utilityloggerHelper.logTransaction(SECONDAY_NAME_UPDATE_LABEL, false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
     				return response;
     				}  
     			}
     			
     		}
     	}
        
        ZetPartnerNames zetPartDetails = hzetPartNames.value;
        logger.info("Extracting ZetPartDetails");
        SecondaryName[] names = handleSecondaryNamesUpdResponse(zetPartDetails);
        
        response.setSecondaryNames(names);
		}catch(Exception e){
			logger.error(e);
			logger.info(XmlUtil.pojoToXML(request));
			utilityloggerHelper.logTransaction(SECONDAY_NAME_UPDATE_LABEL, false, request,e, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw e;// throwing is required so that proper API response is generated in BO layer for exception scenario
		}
        logger.info("ProfileService - secondaryNameUpdate ccs call ends...");
        utilityloggerHelper.logTransaction(SECONDAY_NAME_UPDATE_LABEL, false, request,response, response.getResultDescription(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
        return response;
	}

	private SecondaryName[] handleSecondaryNamesUpdResponse(ZetPartnerNames zetPartDetails) {
		List<ZesPartnerNames> zesPartnerNameList = zetPartDetails.getItem();
        logger.info("Partner Number Lists size:{} ",zesPartnerNameList.size());
        int count = 0;
        SecondaryName[] names = new SecondaryName[zesPartnerNameList.size()];
        
        for(ZesPartnerNames partnerNames : zesPartnerNameList){
        	
        	names[count] = new SecondaryName();
        	names[count].setBpid(partnerNames.getPartner1());
        	names[count].setBpid2(partnerNames.getPartner2());
        	names[count].setFirstName(partnerNames.getFirstName());
        	names[count].setLastName(partnerNames.getLastName());
        	names[count].setMiddleName(partnerNames.getMiddleName());
        	names[count].setValidFrom(CommonUtil.changeDateFormat( partnerNames.getValidFrom()));
        	names[count].setValidUntil(CommonUtil.changeDateFormat( partnerNames.getValidUntil()));
        	names[count].setRelationship(partnerNames.getReltyp());
        	count++;
        }
		return names;
	}
	
	
	/**
	 * @author mshukla1
	 * @param request
	 * @throws RemoteException
	 */
	public void activateCRM(UserRegistrationRequest request,String businessPartner, String companyCode, String sessionId)
	  {
		  URL url = ZEWEBOAMIDENTYFORBP_Service.class.getResource("CMQ-Z_E_WEB_OAM_IDENTY_FOR_BP.wsdl");

	      if (url == null) {
	        logger.info("WSDL not initialised");
	      }
	      ZEWEBOAMIDENTYFORBP_Service service = new ZEWEBOAMIDENTYFORBP_Service(url);
	      
	      ZEWEBOAMIDENTYFORBP port = service.getZEWEBOAMIDENTYFORBP();
	      
	      BindingProvider binding = (BindingProvider)port;

	      binding.getRequestContext().put("javax.xml.ws.security.auth.username", this.envMessageReader.getMessage("CCSUSERNAME"));

	      binding.getRequestContext().put("javax.xml.ws.security.auth.password", this.envMessageReader.getMessage("CCSPASSWORD"));
	      binding.getRequestContext().put("javax.xml.ws.service.endpoint.address", this.envMessageReader.getMessage("CCS_CRM_ACTIVATION"));
	      
	      String imAction="2";
	      ZETBUT0ID2 zet = new ZETBUT0ID2();
	      List<ZESBUT0ID2> zetList = zet.getItem();
	      ZESBUT0ID2 zes = new  ZESBUT0ID2();
	      zes.setBUSINESSPARTNER(businessPartner);
	      zes.setIDNUMBER(request.getAccountNumber());
	      zes.setIDTYPE("ZOAM");
	      zes.setENTRYDATE(CommonUtil.getCurrentDateYYYYMMDD());
	      zetList.add(zes);
	      
	      
	      Holder<com.nrg.cxfstubs.oamidentity.Bapiret2T> hbapiret2T = new Holder<>();
	      Holder<ZetBut0Id> hzetbutid01 = new Holder<>();
	      Holder<ZetBut0Id> hzetbutid02 = new Holder<>();
	      long startTime = CommonUtil.getStartTime();
	      port.zeWebOamIdentyForBp(imAction, zet, hzetbutid01, hzetbutid02, hbapiret2T);
	      utilityloggerHelper.logTransaction("activateCRM", false, zes,"", "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
	      if(logger.isDebugEnabled())
	    	  logger.debug(XmlUtil.pojoToXML(request));
	  }
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public WseServiceResponse wsDeEnrollService(WseServiceRequest request, String companyCode, String sessionId) throws RemoteException
	{
		logger.debug("Start ProfileService.wsEnrollDeEnrollService :: START");
		ProfileDomain proxy = getProfileDomainProxy();

		long startTime = CommonUtil.getStartTime();
		WseServiceResponse wseResponse = proxy
				.wseEnrollDeEnrollService(request);
		utilityloggerHelper.logTransaction("wsEnrollDeEnrollService", false,
				request, wseResponse, wseResponse.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(wseResponse));
		}
		logger.debug("END ProfileService.wsEnrollDeEnrollService :: END");
		return wseResponse;
	}
	
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public WseEnrollmentResponse wsEnrollService(WseEnrollmentRequest request, String companyCode, String sessionId) throws RemoteException
	{
		logger.debug("Start ProfileService.wsEnrollDeEnrollService :: START");
		ProfileDomain proxy = getProfileDomainProxy();

		long startTime = CommonUtil.getStartTime();
		WseEnrollmentResponse wseResponse = proxy.setWSEEnrollment(request);
		utilityloggerHelper.logTransaction("wsEnrollDeEnrollService", false,
				request, wseResponse, wseResponse.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(wseResponse));
		}
		logger.debug("END ProfileService.wsEnrollDeEnrollService :: END");
		return wseResponse;
	}
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public WseEsenseEligibilityResponse wseEsenseEligibilityStatus(AllAccountDetailsRequest  request, String companyCode, String sessionId) throws RemoteException
	{
		logger.info("Start ProfileService.wseEsenseEligibilityStatus :: START");
		ProfileDomain proxy = getProfileDomainProxy();

		long startTime = CommonUtil.getStartTime();
		AllAccountDetailsResponse wseResponse = proxy.getAllAccountDetails(request);
		
		utilityloggerHelper.logTransaction("wseEsenseEligibilityStatus", false,
				request, wseResponse, wseResponse.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(wseResponse));
		}
		if (wseResponse != null
				&& (wseResponse.getWseEsenseEligibilityResponse() != null && wseResponse
						.getWseEsenseEligibilityResponse()
						.getWseEsenseEligibilityItem() != null)) {
			return wseResponse.getWseEsenseEligibilityResponse();
		}
		
		logger.info("END ProfileService.wseEsenseEligibilityStatus :: END");
		return null;
	}
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public CirroStructureCallResponse getCirroStructureCall(CirroStructureCallRequest  request, String companyCode, String sessionId) throws RemoteException
	{
		logger.info("Start ProfileService.getCirroStructureCall :: START");
		long startTime = CommonUtil.getStartTime();
		ProfileDomain proxy = getProfileDomainProxy();

		CirroStructureCallResponse callResponse  = proxy.getCirroStructureCall(request);
		
		utilityloggerHelper.logTransaction("getCirroStructureCall", false,
				request, callResponse, callResponse.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(callResponse));
		}
		
		
		logger.info("END ProfileService.getCirroStructureCall :: END");
		
		return callResponse;
		
	}
	
	/**
	 * @author smuruga1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public com.multibrand.domain.AcctValidationResponse validateAccount(
			AcctValidationRequest request, String companyCode, String sessionId)
			throws RemoteException
	{
		logger.info("Start ProfileService.AcctValidationResponse :: START");

		long startTime = CommonUtil.getStartTime();
		ProfileDomain proxy = getProfileDomainProxy();

		com.multibrand.domain.AcctValidationResponse callResponse = proxy.validateAccount(request);

		utilityloggerHelper.logTransaction("validateAccount", false, request,
				callResponse, callResponse.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId,
				companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(callResponse));
		}
		logger.info("END ProfileService.AcctValidationResponse :: END");
		return callResponse;
	}
	
	
	/**
	 * @author ahanda1
	 * @param request
	 * @param companyCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public LanguageUpdateResponse updateLanguage(LanguageUpdateRequest request, String companyCode, String sessionId)throws RemoteException
	{
		
		ProfileDomain proxy = getProfileDomainProxy();
		long startTime = CommonUtil.getStartTime();
		LanguageUpdateResponse response = null;
		try{
			response= proxy.updateLanguage(request);
		}catch(Exception ex){
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(request));
			logger.error(ex);
			utilityloggerHelper.logTransaction("updateLanguage", false, request,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			throw ex;
		}
		utilityloggerHelper.logTransaction("updateLanguage", false, request,response, response.getErrorMessage(), CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML(request));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;	
	}
	
	/**
	 * @author SMarimuthu
	 * @param allAlertsRequest
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public AllAlertsResponse getContractInfoParallel(AllAlertsRequest allAlertsRequest, String sessionId) throws RemoteException {

		ProfileDomain proxy = getProfileDomainProxy();

		long startTime = CommonUtil.getStartTime();
		AllAlertsResponse response = null;
		try {
			response = proxy.getContractInfoInParallel(allAlertsRequest);
		} catch (Exception ex) {
			logger.error(ex);
			utilityloggerHelper.logTransaction("getContractInfoInParallel", false, allAlertsRequest, ex, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId, allAlertsRequest.getCompanyCode());
			if (logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(allAlertsRequest));
			throw ex;
		}
		utilityloggerHelper.logTransaction("getContractInfoInParallel", false, allAlertsRequest, response, response.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId, allAlertsRequest.getCompanyCode());
		if (logger.isDebugEnabled()) {
			logger.debug(XmlUtil.pojoToXML(allAlertsRequest));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}	
	
	
	/**
	 * @author SMarimuthu
	 * @param allAlertsRequest
	 * @param sessionId
	 * @return
	 * @throws RemoteException
	 */
	public AllAccountDetailsResponse getAllAccountDetailsParallel(AllAccountDetailsRequest allAccountRequest, String sessionId) throws RemoteException {

		ProfileDomain proxy = getProfileDomainProxy();

		long startTime = CommonUtil.getStartTime();
		AllAccountDetailsResponse response = null;
		try {
			response = proxy.getAllAccountDetailsInParallel(allAccountRequest);
		} catch (Exception ex) {
			logger.error(ex);
			utilityloggerHelper.logTransaction("getAllAccountDetailsParallel", false, allAccountRequest, ex, "",
					CommonUtil.getElapsedTime(startTime), "", sessionId, allAccountRequest.getCompanyCode());
			if (logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML(allAccountRequest));
			throw ex;
		}
		utilityloggerHelper.logTransaction("getAllAccountDetailsParallel", false, allAccountRequest, response, response.getErrorMessage(),
				CommonUtil.getElapsedTime(startTime), "", sessionId, allAccountRequest.getCompanyCode());
		if (logger.isDebugEnabled()) {
			logger.debug(XmlUtil.pojoToXML(allAccountRequest));
			logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
	}	
	

}
