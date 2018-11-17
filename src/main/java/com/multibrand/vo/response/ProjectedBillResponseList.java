package com.multibrand.vo.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ ProjectedBillResponse.class })
public class ProjectedBillResponseList extends GenericResponse
{

	private List<ProjectedBillResponse> projectedBillResponse;

	/**
	 * @return the projectedBillResponse
	 */
	public List<ProjectedBillResponse> getProjectedBillResponse()
	{
		return projectedBillResponse;
	}

	/**
	 * @param projectedBillResponse
	 *            the projectedBillResponse to set
	 */
	public void setProjectedBillResponse(
			List<ProjectedBillResponse> projectedBillResponse)
	{
		this.projectedBillResponse = projectedBillResponse;
	}

}
