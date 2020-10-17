// $codepro.audit.disable
package com.multibrand.helper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multibrand.dao.OfferDAOIF;
import com.multibrand.domain.AvergePriceTab;
import com.multibrand.domain.Docid;
import com.multibrand.domain.OfferOutPut;
import com.multibrand.domain.PromoOfferTDSPCharge;
import com.multibrand.domain.TdspCharge;
import com.multibrand.dto.OfferPlanSDLVO;
import com.multibrand.dto.SmallBusinessAvgPriceData;
import com.multibrand.dto.SmallBusinessAvgPriceVO;
import com.multibrand.dto.SmallBusinessOfferDTO;
import com.multibrand.exception.ServiceException;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.TDSPChargeDO;

@Component("offerHelper")
public class OfferHelper implements Constants{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
	

	@Autowired
	@Qualifier("environmentMessageSource")
	private ReloadableResourceBundleMessageSource environmentMessageSource;
	
	
	private ObjectMapper objectMapper;
	
	private ObjectMapper getObjectMapper() {
		return this.objectMapper == null ? new ObjectMapper() : this.objectMapper;
	}
	
	
	
	@Resource(name = "offerDAO")
	private OfferDAOIF offerDAOImpl;
	//Starts - POD POW Changes -Arumugam
	public Map<String,Object> getPOWOffer(String strESID, String strTransactionType,String companyCode, String brandId) {
		logger.info("In offerhelper offerDAOImpl.getPOWOffer ="+strESID);
		Map<String,Object> res=offerDAOImpl.getPOWOffer(strESID, strTransactionType,companyCode, brandId);
		return res;
	}
	//Ends - POD POW Changes -Arumugam
	
	
	

	public String getContentServiceEncodedURL(List<String> offerCodes, String langCode) {
		int offerCodesSize = offerCodes.size();
		int counter = 0;
		StringBuilder urlBuilder = new StringBuilder();
		
		String contentServiceEndPoint = environmentMessageSource.getMessage("content.server.endpoint.url", null, null);
		logger.info("Content Server endpoint url " + contentServiceEndPoint);
		
		String publicationID = getPublicationID(langCode); 
		logger.info("Publication ID " + publicationID);
		
		urlBuilder.append(contentServiceEndPoint);
		urlBuilder.append(CUSTOM_METADATA);
		urlBuilder.append(publicationID);//publiation id
		urlBuilder.append(KEY_NAME);
		for (String offerCode : offerCodes) {
			urlBuilder.append(STRING_VALUE);
			urlBuilder.append(offerCode);
			urlBuilder.append("%27");
			counter ++;
			if (offerCodesSize > 0 && counter != offerCodesSize) {
				urlBuilder.append(OR);
			}
		}
		urlBuilder.append(JSON_FORMAT_COMPONENT_PRESENTATION);
		return urlBuilder.toString();
	}

	private String getPublicationID(String langCode) {
		if (StringUtils.isEmpty(langCode)) {
			return environmentMessageSource.getMessage("xoom.pub.id_en", null, null);
		} else if(langCode.equalsIgnoreCase("es")) {
			return environmentMessageSource.getMessage("xoom.pub.id_es", null, null);
		} else {
			return environmentMessageSource.getMessage("xoom.pub.id_en", null, null);
		}
	}

	public Map<String, Object> getOfferPlanMapFromJsonString(String jsonString) {
		Map<String, Object> offerMap = new HashMap<>();
		
		try {
			JsonNode treeNodes = getTreeNodesFromJsonString(jsonString);
			JsonNode  resultNode = getResultFromJsonString(treeNodes);
			if (resultNode.isArray()) {
				for (JsonNode arrayItem : resultNode) {
					JsonNode componentPresentation = getComponentPresentationFromJsonNode(arrayItem);
					for(JsonNode componentPresentationItem: componentPresentation) {
						JsonNode presentaionContent = getPresentationContent(componentPresentationItem);
						Map<String, Object> responseMap = getObjectMapper().readValue(presentaionContent.textValue(), new TypeReference<HashMap<String, Object>>(){});
						OfferPlanSDLVO offerPlanSDL = getObjectMapper().convertValue(responseMap.get("Content"), OfferPlanSDLVO.class);
						offerMap.put(offerPlanSDL.getOfferCodes(), offerPlanSDL);
					}
				}
			} 
		} catch (Exception ex) {
			logger.error("Error in getOfferPlanMapFromJsonString : " + ex.getMessage());
			offerMap.put("Error", ex.getMessage());
		}
			
		return offerMap;
	}
	private JsonNode getPresentationContent(JsonNode componentPresentationItem) throws ServiceException {
		JsonNode presentationContentNoded = componentPresentationItem.get("PresentationContent");
		if (presentationContentNoded == null) {
			logger.error("Error: Getting empty data from content service api's json string for presentationContentNoded structure");
			throw new ServiceException("Getting empty data from content service api's json string in PresentationContent");
		}
		return presentationContentNoded;
	}
	
	private JsonNode getTreeNodesFromJsonString(String jsonString) throws ServiceException  {
		JsonNode treeNodes = null;
		try {
			treeNodes = getObjectMapper().readTree(jsonString);
			if (treeNodes == null) {
				logger.error("Error: converting jsonString to treeNodes");
				throw new ServiceException("Error: converting jsonString to treeNodes");
			}
		} catch (IOException | ServiceException e) {
			throw new ServiceException("Getting empty data from content service api's json string for treeNodes ");
		}
		return treeNodes;
	}

	private JsonNode getComponentPresentationFromJsonNode(JsonNode arrayItem) throws ServiceException {
		JsonNode componentPresentation = arrayItem.get("Component").get("ComponentPresentations");
		if (componentPresentation == null) {
			logger.error("Error: Getting empty data from content service api's json string for componentPresentation structure");
			throw new ServiceException("Getting empty data from content service api's json string in componentPresentation structure");
		}
		return componentPresentation;
	}

	private JsonNode getResultFromJsonString(JsonNode node) throws ServiceException {
		JsonNode resultNode = node.get("d").get("results");
		if (resultNode == null) {
			logger.error("Error: Getting empty data from content service api's json string for result Structure");
			throw new ServiceException("Getting empty data from content service api's json string d and result structure");
		}
		return resultNode;
		
	}

	public void setSmallBusinessDocumentID(SmallBusinessOfferDTO smallBusinessOfferDO, OfferOutPut offerDO) {
		Docid[] docID = offerDO.getOfferData().getCampaignDatas(0).getDocidtab();
		if (docID != null) {
			for (Docid docid : docID ) {
				
				if (docid.getDoctype().equalsIgnoreCase(EFL)) {
					smallBusinessOfferDO.setStrEFLDocID(docid.getDocid());
					smallBusinessOfferDO.setStrEFLDocLink(getEflURl(docid.getStrEflUrl()));
					smallBusinessOfferDO.setStrEFLSmartCode(docid.getSmartcode());
				}
				if (docid.getDoctype().equalsIgnoreCase(TEFLF)) {
			
					smallBusinessOfferDO.setStrTEFLDocID(docid.getDocid());
					smallBusinessOfferDO.setStrTEFLDocLink(getDocURlwithID(docid.getDocid()));
					smallBusinessOfferDO.setStrTEFLDocSmartCode(docid.getSmartcode());
				}
			
				if (docid.getDoctype().equalsIgnoreCase(YRAAC)) {
					smallBusinessOfferDO.setStrYRAACDocID(docid.getDocid());
					smallBusinessOfferDO.setStrYRAACDocLink(getDocURlwithID(docid.getDocid()));
					smallBusinessOfferDO.setStrYRAACSmartCode(docid.getSmartcode());
				}
			
				if (docid.getDoctype().equalsIgnoreCase(TOS)) {
			
					smallBusinessOfferDO.setStrTOSDocID(docid.getDocid());
					smallBusinessOfferDO.setStrTOSDocLink(getDocURlwithID(docid.getDocid()));
					smallBusinessOfferDO.setStrTOSSmartCode(docid.getSmartcode());
				}
			}
		
		}


}

	public void setAvgpricesMap(SmallBusinessOfferDTO smallBusinessOfferDO, AvergePriceTab[] avgpriceTab) {
		List<SmallBusinessAvgPriceVO> avgPriceList = new ArrayList<>();
		SmallBusinessAvgPriceVO smallBusinessAvgPriceVO = null;
		for (AvergePriceTab avgPrice : avgpriceTab) {
			smallBusinessAvgPriceVO = new SmallBusinessAvgPriceVO();
			smallBusinessAvgPriceVO.setKey(avgPrice.getAvgpricetype());
			smallBusinessAvgPriceVO.setValue(new SmallBusinessAvgPriceData(avgPrice.getSeason(),avgPrice.getDatestart(),avgPrice.getDateend(),avgPrice.getAvgprice(),avgPrice.getAvgpricetype()));
			avgPriceList.add(smallBusinessAvgPriceVO);
		}
		smallBusinessOfferDO.setAvgPrices(avgPriceList);
	}

	public TDSPChargeDO getTDSPCharges(OfferOutPut offerDO) {
		TdspCharge[] tdspCharges = offerDO.getOfferData().getTdspCharges();
		String strBundlingTag=EMPTY;
		String strBundlingGroup=EMPTY;
		TDSPChargeDO tdspChargeDTO = new TDSPChargeDO();
		if(null  != tdspCharges && tdspCharges.length>0) {
			for (TdspCharge tdsp : tdspCharges) {
				strBundlingTag= tdsp.getBundlingTag();
				strBundlingGroup = tdsp.getWebReccCharggrp();
				
				if(StringUtils.equalsIgnoreCase(strBundlingTag,strBundlingGroup+"_MO")){
					tdspChargeDTO.setPerMonthValue(tdsp.getValue());
				}
				
				if(StringUtils.equalsIgnoreCase(strBundlingTag,strBundlingGroup+"_KWH")){
					tdspChargeDTO.setPerKWValue(tdsp.getValue());
				}
								
			}
		}
		return tdspChargeDTO;
	}

	public String getDocURlwithID(String strEFLDocID) {
		return environmentMessageSource.getMessage("documentum.endpoint", new Object[] {strEFLDocID}, null);
	}
	
	public String getEflURl(String strEFLDocID) {
		return environmentMessageSource.getMessage("documentum.efl.endpoint", new Object[] {strEFLDocID}, null);
	}
}
