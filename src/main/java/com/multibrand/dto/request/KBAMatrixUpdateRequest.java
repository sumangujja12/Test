package com.multibrand.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.multibrand.util.CommonUtil;

public class KBAMatrixUpdateRequest implements FormEntityRequest, Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private List<KBAMatrixUpdateDTO> kbaMatrixUpdateList = new ArrayList<>();


	/**
	 * @return the kbaMatrixUpdateList
	 */
	public List<KBAMatrixUpdateDTO> getKbaMatrixUpdateList() {
		return kbaMatrixUpdateList;
	}


	/**
	 * @param kbaMatrixUpdateList the kbaMatrixUpdateList to set
	 */
	public void setKbaMatrixUpdateList(List<KBAMatrixUpdateDTO> kbaMatrixUpdateList) {
		this.kbaMatrixUpdateList = kbaMatrixUpdateList;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
