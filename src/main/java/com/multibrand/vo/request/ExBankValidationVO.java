package com.multibrand.vo.request;
/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */
public class ExBankValidationVO {
		
	private BankValidationVO bankValidation;

    public BankValidationVO getBankValidation()
    {
        return bankValidation;
    }

    public void setBankValidation(BankValidationVO bankValidation)
    {
        this.bankValidation = bankValidation;
    }
    
}
