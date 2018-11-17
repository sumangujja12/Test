// $codepro.audit.disable
package com.multibrand.helper;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.dao.OfferDAOIF;
import com.multibrand.util.Constants;


@Component("offerHelper")
public class OfferHelper implements Constants{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Resource(name = "offerDAO")
	private OfferDAOIF offerDAOImpl;
	//Starts - POD POW Changes -Arumugam
	public Map<String,Object> getPOWOffer(String strESID, String strTransactionType,String companyCode, String brandId) {
		logger.info("In offerhelper offerDAOImpl.getPOWOffer ="+strESID);
		Map<String,Object> res=offerDAOImpl.getPOWOffer(strESID, strTransactionType,companyCode, brandId);
		return res;
	}
	//Ends - POD POW Changes -Arumugam
}
