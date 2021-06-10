package com.multibrand.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.multibrand.helper.TogglzHelper;
import com.multibrand.vo.response.Feature;


@Component
public class TogglzUtil implements Constants {

	@Autowired
	protected TogglzHelper togglzHelper;

	@Autowired
	protected EnvMessageReader envMessageReader;

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");

	
	public boolean getFeatureStatusFromTogglzByBrandId(String featureName, String CompanyCode, String brandId ){
				
		brandId = CommonUtil.getBrandIdFromCompanycodeForTogglz(CompanyCode, brandId);
		
		brandId = brandId.toLowerCase();
		String actualFeatureName = featureName+DOT+brandId;		
		
		return getFeatureStatusFromTogglz(actualFeatureName);
	}
	
	public boolean getFeatureStatusFromTogglzByChannel(String featureName, String channel){
		
		channel = CommonUtil.getChannelTypeForTogglz(channel);			
		channel = channel.toLowerCase();		
		String actualFeatureName = featureName+DOT+channel;		
		
		return getFeatureStatusFromTogglz(actualFeatureName);
	}
	
	/**
	 * Use this method to get feature status
	 * @param featureName
	 * @param cacheable
	 * @return
	 */
	public boolean getFeatureStatusFromTogglz(String featureName) {

		Map<String, List<Feature>> response = new HashMap<>();
		boolean featureStatus = false;
		List<Feature> features = new ArrayList<>();
		try {
			//TODO: DE: DE features should exit in SalesAPI instances
			response = togglzHelper.checkIfFeatureIsEnabled(getEnvProperty(Constants.DEFAULT_GROUP_NAME, null), featureName);
			
			features = response.get("nonCacheableFeatures");
			
			if(features.isEmpty())
				features = response.get("cacheableFeatures");
						  
			Feature feature = features.get(0);
			featureStatus = feature.isFeatureStatus();
			logger.info("Feature "+featureName+" status " + featureStatus);
		} catch (JsonParseException e) {
			logger.error("JsonParseException occurred while getting status of feature " + featureName+" exception message "+e.getMessage() );
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException occurred while getting status of feature " + featureName+" exception message "+e.getMessage() );
		} catch (IOException e) {
			logger.error("IOException occurred while getting status of  feature " + featureName+" exception message "+e.getMessage() );
		} catch (Exception e) {
			logger.error("Exception occurred while getting status of feature " + featureName+" exception message "+e.getMessage() );
		}
		
		return featureStatus;
	}

	public String getEnvProperty(String keyName, Locale locale) {

		String propertyValue = null;
		propertyValue = envMessageReader.getMessage(keyName);
		return propertyValue;

	}
}
