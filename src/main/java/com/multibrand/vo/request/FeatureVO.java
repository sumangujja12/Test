package com.multibrand.vo.request;

import java.io.Serializable;

public class FeatureVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */	

	private String groupName;

	private String featureName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	@Override
	public String toString() {
		return "FeatureVO [groupName=" + groupName + ", featureName=" + featureName + "]";
	}

}