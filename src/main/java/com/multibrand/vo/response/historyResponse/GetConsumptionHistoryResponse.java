package com.multibrand.vo.response.historyResponse;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.multibrand.vo.response.GenericResponse;

@XmlRootElement(name="consumptionHistory")
public class GetConsumptionHistoryResponse extends GenericResponse
{
	
	private List<ConsumptionHistoryDO> usageConsumptionList;

	/**
	 * @return the usageConsumptionList
	 */
	@XmlElement(name="usageList")
	public List<ConsumptionHistoryDO> getUsageConsumptionList()
	{
		return usageConsumptionList;
	}

	/**
	 * @param usageConsumptionList the usageConsumptionList to set
	 */
	public void setUsageConsumptionList(
			List<ConsumptionHistoryDO> usageConsumptionList)
	{
		this.usageConsumptionList = usageConsumptionList;
	}
	
	

}
