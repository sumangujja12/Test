package com.multibrand.vo.response;

public class Feature {

	private static final long serialVersionUID = 1013634291818508999L;

	private String feature;

	private boolean featureStatus;

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public boolean isFeatureStatus() {
		return featureStatus;
	}

	public void setFeatureStatus(boolean featureStatus) {
		this.featureStatus = featureStatus;
	}

	@Override
	public String toString() {
		return "Feature [feature=" + feature + ", featureStatus=" + featureStatus + "]";
	}
}