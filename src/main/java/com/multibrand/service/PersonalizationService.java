package com.multibrand.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.dao.impl.TCSDAOImpl;
import com.multibrand.dto.response.TCSPersonalizedFlagsDTO;
import com.multibrand.dto.response.TCSPersonalizedFlagsSMBDTO;


@Service("personalizationService")
public class PersonalizationService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	TCSDAOImpl tcsDAO;
	
	/**
	 * This method is used to get flags related for personalization
	 */
	public TCSPersonalizedFlagsDTO  getPersonalizedFlags(String bp, String ca, String co, String customerType) {
		logger.debug("START - [PersonalizationService-getPersonalizedFlags] method");
		TCSPersonalizedFlagsDTO tcsPersonalizedFlagsDTO = null;		
		try {
			logger.debug("customerType:{0}",customerType);
			if(null != customerType && customerType.equalsIgnoreCase(BUSINESS)) {
				TCSPersonalizedFlagsSMBDTO tcsPersonalizedFlagsSMBDTO = tcsDAO.getPersonalizedFlagsSmb(bp,ca,co);
				if(null != tcsPersonalizedFlagsSMBDTO) {
					tcsPersonalizedFlagsDTO = new TCSPersonalizedFlagsDTO();
					tcsPersonalizedFlagsDTO.setPolrCustomer(tcsPersonalizedFlagsSMBDTO.getPolrCustomer());
				}
				
			} else {
				tcsPersonalizedFlagsDTO = tcsDAO.getPersonalizedFlags(bp,ca,co);
			}
			
			
		} catch (Exception e) {
			logger.error("Exception in [PersonalizationService.getPersonalizedFlags()]"+e.getMessage());
		}
		logger.debug("END - [PersonalizationService-getPersonalizedFlags] method");
		return tcsPersonalizedFlagsDTO;
		
	}
	
}
