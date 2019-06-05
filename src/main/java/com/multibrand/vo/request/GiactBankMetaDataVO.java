package com.multibrand.vo.request;

/*
 * Start | US19653 | MBAR: Sprint 23 -GIACT REST IMPL : validate bank details  | Jyothi | 5/31/2019
 */

public class GiactBankMetaDataVO {
	private String type;

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "GiactBankMetaDataVO [type = "+type+"]";
    }
}
