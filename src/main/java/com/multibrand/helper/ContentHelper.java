package com.multibrand.helper;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.multibrand.dto.MultiBrandOfferDTO;
import com.multibrand.service.impl.ContentServiceImpl;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.MessageKey;
import com.multibrand.util.MessageKeyTypeEnum;
import com.multibrand.util.SearchTypeEnum;
import com.multibrand.vo.request.ContractInfoRequest;
import com.multibrand.vo.response.CampEnvironmentDO;
import com.multibrand.vo.response.ContractOffer;
import com.multibrand.vo.response.ContractOfferPlanContentResponse;
import com.multibrand.vo.response.OfferDO;
import com.multibrand.vo.response.OfferPriceDO;

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
	public String getPublicationId(String brandId,  String languageCode) {

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
			return this.appConstMessageSource.getMessage( Constants.RE_TAXONOMY_ID, null, null);
		} else if (brandId.equalsIgnoreCase(Constants.GME_BRAND_NAME)) {
			return StringUtils.EMPTY ;
		} else if (brandId.equalsIgnoreCase(Constants.BRAND_NAME_PENNYWISE)) {
			return  StringUtils.EMPTY;
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
	public void createMsgKey(String [] keyName, MessageKeyTypeEnum keyType, SearchTypeEnum searchType,
			List<MessageKey> messageKeyList) {
		if (keyName != null && keyName.length > 0) {
			for (String strKeyVal : keyName) {
				createMsgKey(strKeyVal,keyType,searchType,messageKeyList);
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
	public void createMsgKey(String keyName, MessageKeyTypeEnum keyType, SearchTypeEnum searchType, List<MessageKey> messageKeyList) {
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
		String OfferList =  envMessageReader.getMessage(brandId.toLowerCase() + Constants.CONTENT_OFFER_MESSAGE_KEY);
		return OfferList;
	}
	
	
	/**
	 * @author SMarimuthu
	 * @param offerStrAr
	 * @param contractList
	 * @return
	 */
	public String[] getContractOffer(OfferDO[] offerStrAr, List<ContractOffer> contractList) {
		String[] offerCode = null;
		int count = 0;
		if (offerStrAr != null) {
			
			offerCode = new String[offerStrAr.length];
			for (OfferDO offerVO : offerStrAr) {
				ContractOffer contractOffer = new ContractOffer();
				contractOffer.setYrracDocId(offerVO.getStrYRAACDocID());
				contractOffer.setEflDocId(offerVO.getStrEFLDocID());
				contractOffer.setCancellationFee(offerVO.getStrCancelFee());
				contractOffer.setOfferName(offerVO.getStrPlanName());
				contractOffer.setOfferTeaser(offerVO.getStrOfferTeaser());
				contractOffer.setOfferCode(offerVO.getStrOfferCode());
				contractOffer.setTermLength(offerVO.getStrContractTerm());
				contractOffer.setTosDocId(offerVO.getStrTOSDocID());
				OfferPriceDO[] offerPriceEntry = offerVO.getOfferPriceEntry();
				if (offerPriceEntry != null) {
					for (OfferPriceDO priceType : offerPriceEntry) {
						if (priceType.getPriceType().equalsIgnoreCase("EFL_BR2000")) {
							contractOffer.setPrice(priceType.getPrice());
							break;
						}
					}
				}
				CampEnvironmentDO[] campEnvironmentDetails = offerVO.getCampEnvironmentDetails();
				
				if (campEnvironmentDetails != null) {
					
					for (CampEnvironmentDO campEnvironment : campEnvironmentDetails) {
						if (campEnvironment.getCalcOperand().equalsIgnoreCase("YRLYTREES_2000")) {
							contractOffer.setNumberOfTreesSaved(campEnvironment.getValue());
							break;
						}
						
					}
					
				}
				

				if (StringUtils.isNotBlank(offerVO.getStrOfferCode())) {
					offerCode[count] = offerVO.getStrOfferCode();
					count++;
				}

				contractList.add(contractOffer);
			}
		}
		
		return offerCode;
	}
	
	/**
	 * @author SMarimuthu
	 * @param offerCode
	 * @param response
	 * @param request
	 */
	public void getOfferContent(String[] offerCode, ContractOfferPlanContentResponse response, ContractInfoRequest request) {
		
		if (offerCode != null && offerCode.length > 0) {
			logger.info(":::::::::::GET CONTENT FOR OFFER CODE FROM REST SERVER ContractOfferPlanContentResponse Method :::::::::::"+offerCode);

			Map<String, Object> jsonMap = getContentRequestJSON(request.getBrandId(),
					request.getLanguageCode(), true);
			List<MessageKey> messageKeyList = new ArrayList<MessageKey>();
			Gson gson = new Gson();
			for (String strOfferCode : offerCode) {
				createMsgKey(strOfferCode, MessageKeyTypeEnum.OFFER_CODE,
						SearchTypeEnum.STR_VALUE_FILTER, messageKeyList);
			}
			jsonMap.put(CONTENT_TITLE_MESSAGEKEYS, getMessageMap(messageKeyList));
			String restContentJson = contentService.getJSONContentResponse(gson.toJson(jsonMap));
			if (StringUtils.isNotBlank(restContentJson) && CommonUtil.isValidJson(restContentJson)) {
				if (isValidContent(response, restContentJson)) {
					MultiBrandOfferDTO offerConetent = gson.fromJson(restContentJson, MultiBrandOfferDTO.class);
					for (int i = 0; i < offerConetent.getMultiBrandOfferList().size(); i++) {
						for (ContractOffer offerVO : response.getContractOffer()) {
							String tempOfferCode = offerConetent.getMultiBrandOfferList().get(i).getOfferCode();
							if (offerVO.getOfferCode() != null
									&& offerVO.getOfferCode().equalsIgnoreCase(tempOfferCode)) {
								offerVO.setOfferContent(offerConetent.getMultiBrandOfferList().get(i));
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
	
		
	

}
