package com.multibrand.helper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.dao.BPAccountContractPayDAO;
import com.multibrand.vo.response.BussinessPartnerDO;
@Component("BPAccountContractPayHelper")
public class BPAccountContractPayHelper
{
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Resource(name = "SVTDAO")
	private BPAccountContractPayDAO bpAccountContractPayDAOImp;
	
	
	public List<BussinessPartnerDO> getSVTData(String bpNumber,
			String companyCode, String brandName, String sessionId)
	{
		logger.info("BPAccountContractPayHelper - getSVTData method starts...");

		List<BussinessPartnerDO> returnList = bpAccountContractPayDAOImp.getSVTData(bpNumber, companyCode, brandName, sessionId);
		logger.info("END-[BPAccountContractPayHelper-getSVTData]");
		return returnList;
	}
}
