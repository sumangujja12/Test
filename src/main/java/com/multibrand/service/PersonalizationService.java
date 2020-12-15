package com.multibrand.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.dao.impl.TCSDAOImpl;
import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;


@Service("personalizationService")
public class PersonalizationService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	TCSDAOImpl tcsDAO;
	
	/**
	 * This method is used to get flags related for personalization
	 */
	public TCSPersonalizedFlagsDTO  getPersonalizedFlags(String bp, String ca, String co) {
		logger.debug("START - [PersonalizationService-getPersonalizedFlags] method");
		TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO = null;		
		try {
			tcsPersonalizedFlagsDTO = tcsDAO.getPersonalizedFlags(bp,ca,co);
			
		} catch (Exception e) {
			logger.error("Exception in [PersonalizationService.getPersonalizedFlags()]"+e.getMessage());
		}
		logger.debug("END - [PersonalizationService-getPersonalizedFlags] method");
		return tcsPersonalizedFlagsDTO;
		
	}
	
}
