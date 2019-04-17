package com.multibrand.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.multibrand.domain.AllAlertsOfferSwapInput;
import com.multibrand.domain.AllAlertsRequest;
import com.multibrand.domain.AllAlertsResponse;
import com.multibrand.domain.ContractAccountDO;
import com.multibrand.domain.ContractDO;
import com.multibrand.domain.OfferDO;
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
	public String [] getContractOffer(AllAlertsResponse allRequestResponse, ContractOfferPlanContentResponse response) {
		List <String> offerCode = new LinkedList<String>();
		OfferDO[] offerStrAr = getEligibleOfferDO(allRequestResponse);
		List<ContractOffer> contractList = new LinkedList<ContractOffer>();
		response.setPlans(contractList);
		if (offerStrAr != null) {
			offerCode = new ArrayList<String>();
			for (OfferDO offerVO : offerStrAr) {
				if (StringUtils.isNotBlank(offerVO.getStrOfferCode())) {
					ContractOffer contractOffer = new ContractOffer();
					loadContractOfferResponse(contractOffer, offerVO);
					offerCode.add(offerVO.getStrOfferCode());
					contractList.add(contractOffer);
				}
			}
		}
		
		ContractOffer currentPlan = getContractCurrentPlan(allRequestResponse);
		if(currentPlan != null && StringUtils.isNotBlank(currentPlan.getOfferCode())) {
			response.setCurrentPlan(currentPlan);
			offerCode.add(currentPlan.getOfferCode());
		}
		String [] strOfferCode = new String[offerCode.size()];
		return offerCode.toArray(strOfferCode);
	}

	/**
	 * @author SMarimuthu
	 * @param offerCode
	 * @param response
	 * @param request
	 */
	public void getOfferContent(String[] offerCode, ContractOfferPlanContentResponse response,
			ContractInfoRequest request) {

		if (offerCode != null && offerCode.length > 0) {
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
								if (offerVO.getOfferCode() != null
										&& offerVO.getOfferCode().equalsIgnoreCase(tempOfferCode)) {
									copyToContractOffer(offerVO,tempJsonConten);
									break;
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
	private ContractOffer getContractCurrentPlan(AllAlertsResponse allRequestResponse) {
		ContractOffer contractOffer = null;
		com.multibrand.domain.OfferDO offerDO = getCurrentPlanOfferDO(allRequestResponse);
		if (offerDO != null) {

			contractOffer = new ContractOffer();
			loadContractOfferResponse(contractOffer,offerDO);
			ContractDO returnContractDO =  getContractDO(allRequestResponse);
			contractOffer.setNewContractBegins(returnContractDO.getStrContractStartDate());
			contractOffer.setNewContractEnds(returnContractDO.getStrContractEndDate());
			contractOffer.setAvgPrice(returnContractDO.getStrAvgPrice());
			
			
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
		offerSourceVO.setEnergyTypeIcon(offerDesVO.getEnergyTypeIcon());
		offerSourceVO.setSpecialOfferDescription(offerDesVO.getSpecialOfferDescription());
		offerSourceVO.setSpecialOfferIcon(offerDesVO.getSpecialOfferIcon());
		offerSourceVO.setProductDisclaimer(offerDesVO.getProductDisclaimer());
		offerSourceVO.setGenericDisclaimer(offerDesVO.getGenericDisclaimer());
		offerSourceVO.setErrorMessage(offerDesVO.getErrorMessage());
	}
	
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
	
	private void loadContractOfferResponse(ContractOffer contractOffer, OfferDO offerDO) {
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
		com.multibrand.domain.OfferPriceDO[] offerPriceEntry = offerDO.getOfferPriceEntry();
		if (offerPriceEntry != null) {
			for (com.multibrand.domain.OfferPriceDO priceType : offerPriceEntry) {
				if (priceType.getPriceType().equalsIgnoreCase("EFL_BR2000")) {
					contractOffer.setPrice(priceType.getPrice());
					break;
				}
			}
		}
	}

}
