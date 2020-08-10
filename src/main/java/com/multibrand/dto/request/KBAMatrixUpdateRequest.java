package com.multibrand.dto.request;

import java.io.Serializable;
import java.util.List;
import com.multibrand.util.CommonUtil;

public class KBAMatrixUpdateRequest implements FormEntityRequest, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<KBAMatrixUpdateDTO> KBAMatrixUpdateLst;
	
	
	
	/**
	 * @return the kBAMatrixUpdateLst
	 */
	public List<KBAMatrixUpdateDTO> getKBAMatrixUpdateLst() {
		return KBAMatrixUpdateLst;
	}



	/**
	 * @param kBAMatrixUpdateLst the kBAMatrixUpdateLst to set
	 */
	public void setKBAMatrixUpdateLst(List<KBAMatrixUpdateDTO> kBAMatrixUpdateLst) {
		KBAMatrixUpdateLst = kBAMatrixUpdateLst;
	}

	@Override
	public String toString() {
		return CommonUtil.doRender(this);
	}
}
