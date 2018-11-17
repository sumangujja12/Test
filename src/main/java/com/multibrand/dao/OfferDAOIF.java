package com.multibrand.dao;

import java.util.Map;

public interface OfferDAOIF{
	//Starts - POD POW Changes -Arumugam
	public Map<String,Object>  getPOWOffer(String strESID, String strTransactionType, String companyCode, String brandId);
	//Ends - POD POW Changes -Arumugam
}
