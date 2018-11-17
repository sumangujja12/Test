package com.multibrand.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multibrand.dao.impl.TCSDAOImpl;
import com.multibrand.dto.response.TCSBPDetailsDTO;


@Service("tcsCslrService")
public class TCSCslrService extends BaseAbstractService {

	private Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	TCSDAOImpl tcsDAO;
	
	/**
	 * This method is used 
	 */
	public List<TCSBPDetailsDTO>  getBPDetails( String agreementId )  {
		logger.debug("START - [TCSCslrService-getBPDetails] method");
		List<TCSBPDetailsDTO> tcsBPDetailsDTOList = null;		
		try {
			tcsBPDetailsDTOList = tcsDAO.getBPDetails(agreementId);
			
		} catch (Exception e) {
			logger.error("Exception in [TCSCslrService.getBPDetails()]"+e.getMessage());
			//throw new Exception("getUserLoginId Failed :", e);
		}
		logger.debug("END - [TCSCslrService-getBPDetails] method");
		return tcsBPDetailsDTOList;
		
	}
}
