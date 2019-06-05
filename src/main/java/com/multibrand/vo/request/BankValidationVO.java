package com.multibrand.vo.request;
/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */
public class BankValidationVO {
	private String Message;

    private GiactBankMetaDataVO __metadata;

    private String Code;

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public GiactBankMetaDataVO get__metadata ()
    {
        return __metadata;
    }

    public void set__metadata (GiactBankMetaDataVO __metadata)
    {
        this.__metadata = __metadata;
    }

    public String getCode ()
    {
        return Code;
    }

    public void setCode (String Code)
    {
        this.Code = Code;
    }

    @Override
    public String toString()
    {
        return "GiactBankMetaDataVO [Message = "+Message+", __metadata = "+__metadata+", Code = "+Code+"]";
    }
}
