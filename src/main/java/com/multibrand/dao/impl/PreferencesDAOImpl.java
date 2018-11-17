package com.multibrand.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multibrand.dao.AbstractSpringDAO;
import com.multibrand.dao.PreferencesDAO;
import com.multibrand.util.Constants;
import com.multibrand.vo.request.PrivacyPreferencesRequest;

@Repository("preferencesDAO")
public class PreferencesDAOImpl extends AbstractSpringDAO implements
PreferencesDAO, Constants  {
	
	@Autowired(required = true)
	public PreferencesDAOImpl(
			@Qualifier("solprefUserTemplate") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		init(PreferencesDAOImpl.class);
	}

	
	public boolean savePrivacyPreference(PrivacyPreferencesRequest pDto){
		int update = 0;
		boolean updateSuccess = false;
		logger.info("PreferencesDAO.savePrivacyPreference()");
		try {
			
			String smailPreferenceFlag = pDto.getSmailPreferenceFlag();
			if(smailPreferenceFlag.equalsIgnoreCase("nopostalmail"))
			{
				smailPreferenceFlag = "Y";
			}
			else
			{
				smailPreferenceFlag = "N";
			}
			String phoneCallPreferenceFlag = pDto.getPhoneCallPreferenceFlag();
			if(phoneCallPreferenceFlag.equalsIgnoreCase("phoneOptedOut"))
			{
				phoneCallPreferenceFlag = "Y";
			}
			else
			{
				phoneCallPreferenceFlag = "N";
			}
			String emailPreferenceFlag = pDto.getEmailPreferenceFlag();
			if(emailPreferenceFlag.equalsIgnoreCase("contactByEmail"))
			{
				emailPreferenceFlag = "Y";
			}
			else
			{
				emailPreferenceFlag = "N";
			}
			String customerType = pDto.getCustomerType();
			if(customerType.equalsIgnoreCase("Individual"))
			{
				customerType = "R";
			}
			if(customerType.equalsIgnoreCase("Business"))
			{
				customerType = "B";
			}
			String businessName = pDto.getBusinessName();
			String firstName = pDto.getFirstName();
			String lastName = pDto.getLastName();
			String addressLine1 = pDto.getAddressLine1();
			String addressLine2 = pDto.getAddressLine2();
			String cityValue = pDto.getCityValue();
			String stateValue = pDto.getStateValue();
			String zipCode = pDto.getZipCode();
			String phoneNumber = pDto.getPhoneNumber();
			String emailAddress = pDto.getEmailAddress();
			String companyCode = pDto.getCompanyCode();
					
			Object[] params = new Object[] {smailPreferenceFlag, phoneCallPreferenceFlag, emailPreferenceFlag, customerType, businessName,
					firstName, lastName, addressLine1, addressLine2, cityValue, stateValue, zipCode, phoneNumber, emailAddress, companyCode};  

			String sqlQuery = sqlMessage.getMessage(SQL_SAVE_PRIVACY_PREFERENCE, null, null);
			update = getJdbcTemplate().update(sqlQuery, params);

			if (update == 0 || update == 1) {
				updateSuccess = true;
				if (logger.isDebugEnabled()) {
					logger.debug("PreferencesDAO.savePrivacyPreference.update:rows affected"+update);
				}
			} else
		     
			{
				updateSuccess = false;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("PreferencesDAO.savePrivacyPreference.update:rows affected"+update);
				logger.debug("Inserted Records Successfully");
			}
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			logger.info(ex);
			logger.error(ex);
			throw ex;
			
		}
		return updateSuccess;
	}
}
