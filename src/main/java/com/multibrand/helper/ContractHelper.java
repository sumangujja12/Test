package com.multibrand.helper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.multibrand.dao.ContractInfoDAO;
import com.multibrand.dataObjects.Contract;
import com.multibrand.util.Constants;
@Component("contractHelper")
public class ContractHelper implements Constants{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	@Resource(name = "contractinfodao")
	private ContractInfoDAO contractInfoDAOImpl;
	public List<Contract> getContractInfo(String zipcode, String streetnumber, String streetname, String city, String state, String country, String unitnumber, String ponumber) {
		logger.debug("in the contract helper");
		return contractInfoDAOImpl.getContractInfo(zipcode, streetnumber, streetname, city, state, country, unitnumber, ponumber);
	}

}
