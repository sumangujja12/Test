package com.multibrand.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.multibrand.domain.AddressDO;
import com.multibrand.domain.AllAlertsOfferSwapInput;
import com.multibrand.domain.AllAlertsRequest;
import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.ContractDO;
import com.multibrand.dto.MultiBrandOfferDTO;
import com.multibrand.service.impl.ContentServiceImpl;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.MessageKey;
import com.multibrand.util.MessageKeyTypeEnum;
import com.multibrand.util.SearchTypeEnum;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.ContractOffer;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.GetContractInfoResponse;
import com.multibrand.vo.response.OfferDO;
import com.multibrand.vo.response.OfferPriceDO;
import com.multibrand.vo.response.PendingSwapDO;
import com.multibrand.vo.response.ServiceAddressDO;


@Component
public class ContentHelper implements Constants {

	Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	protected EnvMessageReader envMessageReader;

	@Autowired
	@Qualifier("appConstMessageSource")
	protected ReloadableResourceBundleMessageSource appConstMessageSource;

	@Autowired
	private ContentServiceImpl contentService;
		

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @param languageCode
	 * @return
	 */
	public String getPublicationId(String brandId, String languageCode) {

		brandId = brandId != null ? brandId : StringUtils.EMPTY;

		if (languageCode != null && Constants.LANG_ES.equalsIgnoreCase(languageCode)) {
			return envMessageReader.getMessage(brandId.toLowerCase() + Constants.PUB + Constants.ES.toLowerCase());
		} else {
			return envMessageReader.getMessage(brandId.toLowerCase() + Constants.PUB + Constants.EN.toLowerCase());
		}
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @param isDesktop
	 * @return
	 */
	public String getTemplateId(String brandId, boolean isDesktop) {

		if (isDesktop) {
			return envMessageReader.getMessage(brandId.toLowerCase() + Constants.TEMPLATE_DESKTOP);
		} else {
			return envMessageReader.getMessage(brandId.toLowerCase() + Constants.TEMPLATE_MOBILE);
		}
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @return
	 */
	public String getTaxonomyId(String brandId) {
		if (brandId.equalsIgnoreCase(Constants.RELIANT_BRAND_NAME)) {
			return this.appConstMessageSource.getMessage(Constants.RE_TAXONOMY_ID, null, null);
		} else if (brandId.equalsIgnoreCase(Constants.GME_BRAND_NAME)) {
			return StringUtils.EMPTY;
		} else if (brandId.equalsIgnoreCase(Constants.BRAND_NAME_PENNYWISE)) {
			return StringUtils.EMPTY;
		} else if (brandId.equalsIgnoreCase(Constants.BRAND_NAME_CIRRO)) {
			return StringUtils.EMPTY;
		}

		return StringUtils.EMPTY;
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @return
	 */
	public String getProductOfferSchemaId(String brandId) {
		brandId = brandId != null ? brandId : StringUtils.EMPTY;
		return envMessageReader.getMessage(brandId.toLowerCase() + Constants.PROD_OFFER_SCHEMA_ID);
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @return
	 */
	public String getProductBonusSchemaId(String brandId) {
		brandId = brandId != null ? brandId : StringUtils.EMPTY;
		return envMessageReader.getMessage(brandId.toLowerCase() + Constants.PROD_BONUS_SCHEMA_ID);
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @return
	 */
	public String getEndPointURL(String brandId) {
		brandId = brandId != null ? brandId : StringUtils.EMPTY;
		return envMessageReader.getMessage(brandId.toLowerCase() + Constants.CONTENT_SERVER_ENDPOINT_URL);
	}

	/**
	 * @author SMarimuthu
	 * @param keyName
	 * @param keyType
	 * @param searchType
	 * @param messageKeyList
	 */
	public void createMsgKey(String[] keyName, MessageKeyTypeEnum keyType, SearchTypeEnum searchType,
			List<MessageKey> messageKeyList) {
		if (keyName != null && keyName.length > 0) {
			for (String strKeyVal : keyName) {
				createMsgKey(strKeyVal, keyType, searchType, messageKeyList);
			}
		}

	}

	/**
	 * @author SMarimuthu
	 * @param keyName
	 * @param keyType
	 * @param searchType
	 * @param messageKeyList
	 */
	public void createMsgKey(String keyName, MessageKeyTypeEnum keyType, SearchTypeEnum searchType,
			List<MessageKey> messageKeyList) {
		MessageKey msgKey = new MessageKey();
		msgKey.setKeyName(keyName);
		msgKey.setKeyType(keyType);
		msgKey.setSearchType(searchType);
		messageKeyList.add(msgKey);
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @param languageCode
	 * @param IsDesktop
	 * @return
	 */
	public Map<String, Object> getContentRequestJSON(String brandId, String languageCode, boolean IsDesktop) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(CONTENT_TITLE_JSON_END_POINT_URL, getEndPointURL(brandId));
		jsonMap.put(CONTENT_TITLE_JSON_PUBLICATION_ID, getPublicationId(brandId, languageCode));
		jsonMap.put(CONTENT_TITLE_JSON_BRAND_ID, brandId);
		jsonMap.put(CONTENT_TITLE_TEMPLATE_ID, getTemplateId(brandId, IsDesktop));
		jsonMap.put(CONTENT_TITLE_PROD_OFFER_SCHEMA_ID, getProductOfferSchemaId(brandId));
		jsonMap.put(CONTENT_TITLE_PROD_BONUS_SCHEMA_ID, getProductBonusSchemaId(brandId));
		jsonMap.put(CONTENT_TITLE_TAXONOMY_ID, getTaxonomyId(brandId));
		jsonMap.put(CONTENT_TITLE_JSON_TRANSACTION_ID, String.valueOf(CommonUtil.getStartTime()));
		return jsonMap;
	}

	/**
	 * @author SMarimuthu
	 * @param messageKeyList
	 * @return
	 */
	public Map<String, List<MessageKey>> getMessageMap(List<MessageKey> messageKeyList) {
		Map<String, List<MessageKey>> jsonMap = new HashMap<String, List<MessageKey>>();
		jsonMap.put(CONTENT_TITLE_MESSAGEKEY_LIST, messageKeyList);
		return jsonMap;
	}

	/**
	 * @author SMarimuthu
	 * @param brandId
	 * @return
	 */
	public String getOfferMessageKey(String brandId) {
		brandId = brandId != null ? brandId : StringUtils.EMPTY;
		String OfferList = envMessageReader.getMessage(brandId.toLowerCase() + Constants.CONTENT_OFFER_MESSAGE_KEY);
		return OfferList;
	}

	/**
	 * @author SMarimuthu
	 * @param offerStrAr
	 * @param contractList
	 * @return
	 */
	public Set<String> getContractOffer(GetContractInfoResponse contractInfoResponse,
			AllAlertsResponse allRequestResponse, ContractOfferPlanContentResponse response) {
		Set <String> offerCode = null;
		OfferDO[] offerStrAr = contractInfoResponse.getEligibleOffersList();
		List<ContractOffer> contractList = new LinkedList<ContractOffer>();
		response.setPlans(contractList);
		if (offerStrAr != null) {
			offerCode =  new TreeSet<String>();
			for (OfferDO offerVO : offerStrAr) {
				if (StringUtils.isNotBlank(offerVO.getStrOfferCode())) {
					ContractOffer contractOffer = new ContractOffer();
					offerCode.add(loadContractOfferResponse(contractOffer, offerVO));
					contractList.add(contractOffer);
				}
			}
		}
		
		
		ContractOffer currentPlan = getContractCurrentPlan(allRequestResponse, contractInfoResponse);
		if(currentPlan != null && StringUtils.isNotBlank(currentPlan.getOfferCode())) {
			response.setCurrentPlan(currentPlan);
			//offerCode.add(currentPlan.getOfferCode());
		}

		return offerCode;
	}

	/**
	 * @author SMarimuthu
	 * @param offerCode
	 * @param response
	 * @param request
	 */
	public void getOfferContent(Set<String> offerCode, ContractOfferPlanContentResponse response,
			ContractInfoRequest request) {
		
		if (offerCode != null && offerCode.size() > 0) {
			logger.info(
					":::::::::::GET CONTENT FOR OFFER CODE FROM REST SERVER ContractOfferPlanContentResponse Method :::::::::::"
							+ offerCode);

			Map<String, Object> jsonMap = getContentRequestJSON(request.getBrandId(), request.getLanguageCode(), true);
			List<MessageKey> messageKeyList = new ArrayList<MessageKey>();
			Gson gson = new Gson();
			for (String strOfferCode : offerCode) {
				createMsgKey(strOfferCode, MessageKeyTypeEnum.OFFER_CODE, SearchTypeEnum.STR_VALUE_FILTER,
						messageKeyList);
			}
			jsonMap.put(CONTENT_TITLE_MESSAGEKEYS, getMessageMap(messageKeyList));
			String restContentJson = contentService.getJSONContentResponse(gson.toJson(jsonMap));
			if (StringUtils.isNotBlank(restContentJson) && CommonUtil.isValidJson(restContentJson)) {
				if (isValidContent(response, restContentJson)) {
					MultiBrandOfferDTO offerConetent = gson.fromJson(restContentJson, MultiBrandOfferDTO.class);
					int listSize = offerConetent.getMultiBrandOfferList().size();
					for (int i = 0; i < listSize; i++) {
						String tempOfferCode = offerConetent.getMultiBrandOfferList().get(i).getOfferCode();
						ContractOffer tempJsonConten = offerConetent.getMultiBrandOfferList().get(i);
						if (i < response.getPlans().size()) {
							for (ContractOffer offerVO : response.getPlans() ) {
								if ((offerVO.getOfferCode() != null
										&& offerVO.getOfferCode().equalsIgnoreCase(tempOfferCode))
										|| (offerVO.getOfferFamily() != null
												&& offerVO.getOfferFamily().equalsIgnoreCase(tempOfferCode))) {
									copyToContractOffer(offerVO,tempJsonConten);
								}
							}
						} else {
							ContractOffer currentOfferVO = response.getCurrentPlan();
							if (response.getCurrentPlan() != null
									&& (currentOfferVO.getOfferCode() != null && currentOfferVO.getOfferCode()
											.equalsIgnoreCase(response.getCurrentPlan().getOfferCode()))) {
								copyToContractOffer(currentOfferVO,tempJsonConten);
								break;
							}
						}
					}

				}

			} else {
				response.setResultCode("01");
				response.setResultDescription(ERROR_CONTENT_DEFAULT);
			}

		} else {
			response.setResultCode(response.getErrorCode());
			response.setResultDescription(response.getMessageCode());
		}

	}

	/**
	 * @author SMarimuthu
	 * @param response
	 * @param restContentJson
	 * @return
	 */
	private boolean isValidContent(ContractOfferPlanContentResponse response, String restContentJson) {

		JsonObject json = CommonUtil.getJsonObject(restContentJson);
		String errorCode = CommonUtil.getJsonValue(json, "errorCode");
		String errorMsg = CommonUtil.getJsonValue(json, "errorMsg");
		if (errorCode != null && StringUtils.isNotEmpty(errorCode)) {
			response.setResultCode("01");
			response.setResultDescription(json.get("errorMessage").toString());
			return false;
		} else if (errorMsg != null && !errorMsg.equalsIgnoreCase("null")
				&& StringUtils.isNotEmpty(errorMsg.toString())) {
			response.setResultCode("01");
			response.setResultDescription(errorMsg);
			return false;
		}
		return true;
	}

	/**
	 * @author SMarimuthu
	 * @param request
	 * @return
	 */
	public AllAlertsRequest getContractInfoParallelRequest(ContractInfoRequest request) {
		AllAlertsRequest allAlertsRequest = new AllAlertsRequest();
		AllAlertsOfferSwapInput[] inputOfferSwapList = new AllAlertsOfferSwapInput[1];
		AllAlertsOfferSwapInput inputOffer = new AllAlertsOfferSwapInput();
		inputOffer.setStrBusinessPartnerId(request.getBpNumber());
		inputOffer.setStrCaNumber(request.getAccountNumber());
		inputOffer.setStrCo(request.getContractId());
		inputOffer.setStrEsiId(request.getEsid());
		inputOffer.setStrLangCode(request.getLanguageCode());
		inputOfferSwapList[0] = inputOffer;
		allAlertsRequest.setInputOfferSwapList(inputOfferSwapList);
		return allAlertsRequest;
	}

	/**
	 * @author SMarimuthu
	 * @param allRequestResponse
	 * @return
	 */
	private ContractOffer getContractCurrentPlan(AllAlertsResponse allRequestResponse,
			GetContractInfoResponse contractInfoResponse) {
		ContractOffer contractOffer = null;
		com.multibrand.domain.OfferDO offerDO = getCurrentPlanOfferDO(allRequestResponse);
		if (offerDO != null) {

			contractOffer = new ContractOffer();
			loadContractOfferCurrenPlanResponse(contractOffer,offerDO);
			ContractDO returnContractDO =  getContractDO(allRequestResponse);
			contractOffer.setNewContractBegins(returnContractDO.getStrContractStartDate());
			contractOffer.setNewContractEnds(returnContractDO.getStrContractEndDate());
			contractOffer.setAvgPrice(returnContractDO.getStrAvgPrice());
			ContractDO contractDo = getContractDO(allRequestResponse);
			AddressDO address = contractDo.getServiceAddressDO();
			ServiceAddressDO serviceAddressDO = new ServiceAddressDO();
			contractOffer.setServiceAddress(serviceAddressDO);
			BeanUtils.copyProperties(address, serviceAddressDO);
			PendingSwapDO pendingSwapDO = contractInfoResponse.getPendingSwapDO();
			
			if (pendingSwapDO != null ) {
				
				if (pendingSwapDO.getStrStartDate() != null
						&& (!pendingSwapDO.getStrStartDate().equalsIgnoreCase(invalidDate)
								&& !pendingSwapDO.getStrStartDate().equalsIgnoreCase(invalidDate1))) {
					contractOffer.setSwapPendingDate(pendingSwapDO.getStrStartDate());
				}
				
				if(pendingSwapDO.getStrOfferCode() != null)  {
					Integer offerCode = 0;
					try {
						offerCode = Integer.parseInt(pendingSwapDO.getStrOfferCode());
					} catch (NumberFormatException e) {
						offerCode = 0;
					}
					
					if(offerCode > 0) {
						contractOffer.setPendingSwap(true);
					}
				}
				
			}
			
		}
		return contractOffer;
	}
	
	/**
	 * @author SMarimuthu
	 * @param allRequestResponse
	 * @return
	 */
	private com.multibrand.domain.OfferDO getCurrentPlanOfferDO(AllAlertsResponse allRequestResponse) {
		com.multibrand.domain.OfferDO returnOfferDO = null;
		ContractAccountDO[] contractAccountList = allRequestResponse.getContractAccDoList();
		if (contractAccountList != null) {
			for (ContractAccountDO contractAccount : contractAccountList) {
				
				ContractDO[] contractDOList = contractAccount.getListOfContracts();
				if (contractDOList != null) {
					ContractDO contractDO = contractDOList[0];
					returnOfferDO = contractDO.getCurrentPlan();
					return returnOfferDO;
				}
			}
		}
		return returnOfferDO;
	}
	
	/**
	 * @author SMarimuthu
	 * @param allRequestResponse
	 * @return
	 */
	private com.multibrand.domain.OfferDO [] getEligibleOfferDO(AllAlertsResponse allRequestResponse) {
		com.multibrand.domain.OfferDO returnOfferDO [] = null;
		ContractAccountDO[] contractAccountList = allRequestResponse.getContractAccDoList();
		if (contractAccountList != null) {
			for (ContractAccountDO contractAccount : contractAccountList) {
				ContractDO[] contractDOList = contractAccount.getListOfContracts();
				if (contractDOList != null) {
					ContractDO contractDO = contractDOList[0];
					returnOfferDO = contractDO.getEligibleOffersList();
					return returnOfferDO;
				}
			}
		}
		return returnOfferDO;
	}
	
	/**
	 * @author SMarimuthu
	 * @param offerSourceVO
	 * @param offerDesVO
	 */
	private void copyToContractOffer(ContractOffer offerSourceVO, ContractOffer offerDesVO) {
		offerSourceVO.setOfferHeadline(offerDesVO.getOfferHeadline());
		offerSourceVO.setOfferDescription(offerDesVO.getOfferDescription());
		offerSourceVO.setEnergyTypeDescription(offerDesVO.getEnergyTypeDescription());
		offerSourceVO.setEnergyTypeIcon(getReplaceImageString(offerDesVO.getEnergyTypeIcon()));
		offerSourceVO.setSpecialOfferDescription(offerDesVO.getSpecialOfferDescription());
		offerSourceVO.setSpecialOfferIcon(getReplaceImageString(offerDesVO.getSpecialOfferIcon()));
		offerSourceVO.setProductDisclaimer(offerDesVO.getProductDisclaimer());
		offerSourceVO.setGenericDisclaimer(offerDesVO.getGenericDisclaimer());
		offerSourceVO.setErrorMessage(offerDesVO.getErrorMessage());
	}
	
	/**
	 * @author SMarimuthu
	 * @param allRequestResponse
	 * @return
	 */
	private ContractDO getContractDO(AllAlertsResponse allRequestResponse) {
		ContractDO returnContractDO = null;
		ContractAccountDO[] contractAccountList = allRequestResponse.getContractAccDoList();
		if (contractAccountList != null) {
			for (ContractAccountDO contractAccount : contractAccountList) {
				
				ContractDO[] contractDOList = contractAccount.getListOfContracts();
				if (contractDOList != null) {
					returnContractDO = contractDOList[0];
					return returnContractDO;
				}
			}
		}
		return returnContractDO;
	}
	
	/**
	 * @author SMarimuthu
	 * @param contractOffer
	 * @param offerDO
	 */
	private String loadContractOfferResponse(ContractOffer contractOffer, com.multibrand.vo.response.OfferDO offerDO) {
		String OfferEFamily = "";
		contractOffer.setYrracDocId(offerDO.getStrYRAACDocID());
		contractOffer.setEflDocId(offerDO.getStrEFLDocID());
		contractOffer.setCancellationFee(offerDO.getStrCancelFee());
		contractOffer.setOfferName(offerDO.getStrPlanName());
		contractOffer.setOfferTeaser(offerDO.getStrOfferTeaser());
		contractOffer.setOfferCode(offerDO.getStrOfferCode());
		contractOffer.setTermLength(offerDO.getStrContractTerm());
		contractOffer.setTosDocId(offerDO.getStrTOSDocID());
		contractOffer.setCampaignCode(offerDO.getStrCampaignCode());
		contractOffer.setOfferRank(offerDO.getStrOfferRank());
		contractOffer.setEFLSmartCode(offerDO.getStrEFLSmartCode());
		contractOffer.setYRAACSmartCode(offerDO.getStrYRAACSmartCode());
		contractOffer.setTOSSmartCode(offerDO.getStrTOSSmartCode());
		contractOffer.setEflURL(getURL(contractOffer.getEflDocId()));
		contractOffer.setTosURL(getURL(contractOffer.getTosDocId()));
		contractOffer.setYraacURL(getURL(contractOffer.getYrracDocId()));
		
		if (offerDO.getAttribute1() != null && offerDO.getAttribute1().equalsIgnoreCase("R")) {
			contractOffer.setRenewalOffers(true);
		}
		
		if (offerDO.getAttribute1() != null && offerDO.getAttribute1().equalsIgnoreCase("P")) {
			contractOffer.setSwapOffers(true);
		}
		
		OfferPriceDO[] offerPriceEntry = offerDO.getOfferPriceEntry();
		if (offerPriceEntry != null) {
			for (OfferPriceDO priceType : offerPriceEntry) {
				if (priceType.getPriceType().equalsIgnoreCase("EFL_BR2000")) {
					contractOffer.setPrice(priceType.getPrice());
				}
				
				if (priceType.getPriceType().equalsIgnoreCase("E_FAMILY")) {
					OfferEFamily = priceType.getOfferPriceCode();
					contractOffer.setOfferFamily(OfferEFamily);
				}
			}
		}
		
		com.multibrand.vo.response.CampEnvironmentDO[] campEnvironmentDetails = offerDO
				.getCampEnvironmentDetails();

		if (campEnvironmentDetails != null) {
			for (com.multibrand.vo.response.CampEnvironmentDO campEnvironment : campEnvironmentDetails) {
				if (campEnvironment.getCalcOperand().equalsIgnoreCase("YRLYTREES_2000")) {
					contractOffer.setNumberOfTreesSaved(campEnvironment.getValue());
				}
				if (campEnvironment.getCalcOperand().equalsIgnoreCase("RENEW_PERCENT_CD")) {
					if(StringUtils.isNotBlank(campEnvironment.getValue())) {
						int key = 0;
						try {
						 key =  (int) Double.parseDouble(campEnvironment.getValue());
						} catch (Exception e) {
							logger.info("RENEW_PERCENT_CD FROM SAP IS"+campEnvironment.getValue());
						}
						switch (key) {
							case 1 :
									contractOffer.setProductContent(Constants.ENUM_PRODUCT_CONTENT.ONE.getProductContent());
									break;
								
							case 2 :
									contractOffer.setProductContent(Constants.ENUM_PRODUCT_CONTENT.TWO.getProductContent());
									break;
								
							case 3 :
									contractOffer.setProductContent(Constants.ENUM_PRODUCT_CONTENT.THREE.getProductContent());
									break;
								
							case 4:
									contractOffer.setProductContent(Constants.ENUM_PRODUCT_CONTENT.FOUR.getProductContent());
									break;
									
							default :
								contractOffer.setProductContent(Constants.BLANK);
	
						}
						 
					}
					
				}
				
			}

		}
		
		 if(!StringUtils.isNotBlank(OfferEFamily)) {
			 OfferEFamily = contractOffer.getOfferCode();
		 }
		 
		 return OfferEFamily;
	}
	
	
	private void loadContractOfferCurrenPlanResponse(ContractOffer contractOffer, com.multibrand.domain.OfferDO offerDO) {
		String OfferEFamily = "";
		contractOffer.setYrracDocId(offerDO.getStrYRAACDocId());
		contractOffer.setEflDocId(offerDO.getStrEFLDocId());
		contractOffer.setCancellationFee(offerDO.getStrCancellationFee());
		contractOffer.setOfferName(offerDO.getStrPlanName());
		contractOffer.setOfferTeaser(offerDO.getStrOfferTeaser());
		contractOffer.setOfferCode(offerDO.getStrOfferCode());
		contractOffer.setTermLength(offerDO.getStrContractTerm());
		contractOffer.setTosDocId(offerDO.getStrTOSDocId());
		contractOffer.setCampaignCode(offerDO.getStrCampaignCode());
		contractOffer.setOfferRank(offerDO.getStrOfferRank());
		contractOffer.setEFLSmartCode(offerDO.getStrEFLCode());
		contractOffer.setYRAACSmartCode(offerDO.getStrYRAACCode());
		contractOffer.setTOSSmartCode(offerDO.getStrTOSCode());
		contractOffer.setEflURL(getURL(contractOffer.getEflDocId()));
		contractOffer.setTosURL(getURL(contractOffer.getTosDocId()));
		contractOffer.setYraacURL(getURL(contractOffer.getYrracDocId()));
		
		com.multibrand.domain.OfferPriceDO[] offerPriceEntry = offerDO.getOfferPriceEntry();
		if (offerPriceEntry != null) {
			for (com.multibrand.domain.OfferPriceDO priceType : offerPriceEntry) {
				if (priceType.getPriceType().equalsIgnoreCase("EFL_BR2000")) {
					contractOffer.setPrice(priceType.getPrice());
				}
			}
			
		}
		
		 if(StringUtils.isNotBlank(OfferEFamily) && StringUtils.isNumeric(OfferEFamily)) {
			 contractOffer.setOfferCode(OfferEFamily);
		 } 
	}
	
	
	/**
	 * @author SMarimuthu
	 * @param offerStrAr
	 * @param contractList
	 * @return
	 */
	public Map<String, String> getContractNoOfTrees(com.multibrand.vo.response.OfferDO[] offerStrAr) {
		Map<String, String> offerCodMap = new LinkedHashMap<String, String>();
		if (offerStrAr != null) {
			for (com.multibrand.vo.response.OfferDO offerVO : offerStrAr) {
				com.multibrand.vo.response.CampEnvironmentDO[] campEnvironmentDetails = offerVO
						.getCampEnvironmentDetails();

				if (campEnvironmentDetails != null) {

					for (com.multibrand.vo.response.CampEnvironmentDO campEnvironment : campEnvironmentDetails) {
						if (campEnvironment.getCalcOperand().equalsIgnoreCase("YRLYTREES_2000")) {
							offerCodMap.put(offerVO.getStrOfferCode(), campEnvironment.getValue());
							break;
						}

					}

				}

			}
		}

		return offerCodMap;
	}
	
	/**
	 * @author SMarimuthu
	 * @param documentName
	 * @return
	 */
	private String getURL(String documentName) {
		if(StringUtils.isBlank(documentName)) {
			return BLANK;
		}
		String baseURL= envMessageReader.getMessage(Constants.GME_BASE_URL);
		baseURL = baseURL.trim() +CONST_FILES+documentName+CONST_DOT_PDF;
		return baseURL;
	}
	
	/**
	 * @author SMarimuthu
	 * @param strReplace
	 * @return
	 */
	private String getReplaceImageString(String strReplace) {
		String imageURL = "";
		
		if(StringUtils.isEmpty(strReplace)) {
			return imageURL = "";
		}
		
		Pattern p = Pattern.compile("<img[^>]*src=[\"']([^\"^']*)",  Pattern.CASE_INSENSITIVE);
		String codeGroup ="";
		
		 Matcher m = p.matcher(strReplace);
		 if (m.find()) {
		      codeGroup = m.group(1);
		 } else {
			 return strReplace;
		 }
		 String baseURL= envMessageReader.getMessage(Constants.GME_BASE_URL);
		 if(StringUtils.isNotBlank(codeGroup)) {
			 codeGroup =  baseURL.trim() +codeGroup.trim();
			 imageURL = codeGroup;
		 }
		 
		 return imageURL;
	}
	
	/**
	 * @author SMarimuthu
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean handleValidationContentRequest(ContractInfoRequest request,
			ContractOfferPlanContentResponse response) {
		StringBuffer variableField = new StringBuffer("");

		if (StringUtils.isBlank(request.getAccountNumber())) {
			getErrorString(ACCOUNT_NUMBER,variableField);
		}

		if (StringUtils.isBlank(request.getBpNumber())) {
			getErrorString(BP_NUMBER,variableField);
		}

		if (StringUtils.isBlank(request.getContractId())) {
			getErrorString(CONTRACT_ID,variableField);
		}

		if (StringUtils.isBlank(request.getBrandId())) {
			getErrorString(CONTENT_TITLE_JSON_BRAND_ID,variableField);
		} else if (!request.getBrandId().equalsIgnoreCase(BRAND_ID_GME)) {
			getErrorString(CONTENT_TITLE_JSON_BRAND_ID,variableField);
		}

		if (StringUtils.isBlank(request.getCompanyCode())) {
			getErrorString(COMPANY_CODE,variableField);
		} else if (!request.getCompanyCode().equalsIgnoreCase(COMPANY_CODE_GME)) {
			getErrorString(COMPANY_CODE,variableField);
		}

		if (StringUtils.isBlank(request.getLanguageCode())) {
			getErrorString(LANGUAGE_CODE,variableField);
		} else if (!request.getLanguageCode().equalsIgnoreCase(LANG_EN)
				  && !request.getLanguageCode().equalsIgnoreCase(LANG_ES)) {
			getErrorString(LANGUAGE_CODE,variableField);
		}

		//if (StringUtils.isBlank(request.getZoneId())) {
		///	getErrorString(ZONE_ID_IN,variableField);
		//}
		
		if(variableField.length() > 0) {
			variableField.deleteCharAt(variableField.length()-1);
			response.setResultCode("05");
			response.setResultDescription(INVALID_REQUEST.replace("{0}", variableField.toString()));
			return true;
		}

		return false;

	}
	
	private void getErrorString(String errorVariable, StringBuffer variableField) {
		
		if(variableField.length() > 0) {
			variableField.append(" ");
		}
		variableField.append(errorVariable);
		variableField.append(SYMBOL_COMMA);
	}

}
