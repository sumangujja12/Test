package com.multibrand.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.multibrand.service.TogglzService;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.LoggerUtil;
import com.multibrand.vo.request.FeatureVO;
import com.multibrand.vo.response.Feature;

@Component
public class TogglzHelper {

	@Autowired
	private TogglzService togglzService;

	LoggerUtil logger = LoggerUtil.getInstance("NRGREST_LOGGER");
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	
	public Map<String, List<Feature>> checkIfFeatureIsEnabled(String groupName, String featureName) throws IOException {

		FeatureVO featureVO = new FeatureVO();

		featureVO.setFeatureName(featureName);

		String response = null;

		if (StringUtils.isNotBlank(groupName)) {

			featureVO.setGroupName(groupName);

			response = togglzService.isFeatureEnabled(featureVO);

		} else {


			featureVO.setGroupName(envMessageReader.getMessage(Constants.DEFAULT_GROUP_NAME));

			response = togglzService.isFeatureEnabled(featureVO);

		}
        logger.info("Feature Response "+response);
		final ObjectMapper mapper = new ObjectMapper();

		final MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);

		Map<String, List<Feature>> data = new HashMap<>();

		
			Map<String, Object> tempData = mapper.readValue(response, type);
			for (String key : tempData.keySet()) {
				List<Feature> features = mapper.convertValue(tempData.get(key), new TypeReference<List<Feature>>() {
				});
				data.put(key, features);
			}
		return data;

	}
}
