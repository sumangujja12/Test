package com.multibrand.vo.response;


public class ProductTypeDO
{

    public ProductTypeDO()
    {
    }

    public String getStrLegalEase()
    {
        return strLegalEase;
    }

    public void setStrLegalEase(String strLegalEase)
    {
        this.strLegalEase = strLegalEase;
    }

    public String getStrPopUpText()
    {
        return strPopUpText;
    }

    public void setStrPopUpText(String strPopUpText)
    {
        this.strPopUpText = strPopUpText;
    }

    public String getStrProductType()
    {
        return strProductType;
    }

    public void setStrProductType(String strProductType)
    {
        this.strProductType = strProductType;
    }
  //Added Start by Ravi Bansal for weekly summary email phase 1
	public String getSeEligible() {
		return seEligible;
	}
	public void setSeEligible(String seEligible) {
		this.seEligible = seEligible;
	}
	public String getSeSignUpText() {
		return seSignUpText;
	}
	public void setSeSignUpText(String seSignUpText) {
		this.seSignUpText = seSignUpText;
	}
	//Added End by Ravi Bansal for weekly summary email phase 1

    private String strProductType;
    private String strPopUpText;
    private String strLegalEase;
  //Added Start by Ravi Bansal for weekly summary email phase 1
	private String seEligible;
	private String seSignUpText;
	//Added End by Ravi Bansal for weekly summary email phase 1
}
