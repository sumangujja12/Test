package com.multibrand.dao;

import java.util.List;

import com.multibrand.dataObjects.Contract;
import com.multibrand.exception.OAMException;

public interface ContractInfoDAO {
	public List<Contract> getContractInfo(String zipcode, String streetnumber, String streetname, String city, String state, String country, String unitnumber, String ponumber) throws OAMException;
}
