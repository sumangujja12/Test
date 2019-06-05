package com.multibrand.vo.request;
/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */
public class ExBankValidationVO {
		
	private BankValidationVO BankValidation;

    public BankValidationVO getBankValidation ()
    {
        return BankValidation;
    }

    public void setBankValidation (BankValidationVO BankValidation)
    {
        this.BankValidation = BankValidation;
    }

    @Override
    public String toString()
    {
        return "BankValidationVO [BankValidation = "+BankValidation+"]";
    }
}
