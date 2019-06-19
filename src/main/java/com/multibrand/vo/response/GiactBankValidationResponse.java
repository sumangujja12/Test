package com.multibrand.vo.response;

import java.io.Serializable;

import com.multibrand.vo.request.ExBankValidationVO;

/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */

public class GiactBankValidationResponse implements Serializable{
	
		private static final long serialVersionUID = -6174561651074813430L;
		
		private ExBankValidationVO d;

	    public ExBankValidationVO getD()
	    {
	        return d;
	    }

	    public void setD(ExBankValidationVO d)
	    {
	        this.d = d;
	    }
	
}
