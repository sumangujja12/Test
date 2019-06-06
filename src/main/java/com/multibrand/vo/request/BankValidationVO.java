package com.multibrand.vo.request;
/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */
public class BankValidationVO {
	private String message;

    private GiactBankMetaDataVO __metadata;

    private String code;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public GiactBankMetaDataVO get__metadata()
    {
        return __metadata;
    }

    public void set__metadata(GiactBankMetaDataVO __metadata)
    {
        this.__metadata = __metadata;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
