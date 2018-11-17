package com.multibrand.dao;

import com.multibrand.vo.request.PrivacyPreferencesRequest;

public interface PreferencesDAO {
	
	public boolean savePrivacyPreference(PrivacyPreferencesRequest pDto);

}
