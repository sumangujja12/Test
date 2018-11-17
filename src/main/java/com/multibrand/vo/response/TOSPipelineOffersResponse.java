package com.multibrand.vo.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TOSPipeLineOffersResponse")
public class TOSPipelineOffersResponse extends GenericResponse {
	private String switchHoldStatus="";
	  private CampEnvironmentOutData[] campEnvironmentDetailsOuts=new CampEnvironmentOutData[0];
	  private PromoCharityOutData[] charityOuts = new PromoCharityOutData[0];
	  private String charityStrErrCode="";
	  private String charityStrErrMessage="";
	  private PromoOfferOutData[] offerOuts = new PromoOfferOutData[0] ;
	  private SegmentedFlagsOutData[] segmentedFlagsOuts= new SegmentedFlagsOutData[0];
	  private List<POWOfferDO> powOffers=new ArrayList<POWOfferDO>();
	  private String strErrCode="";
	  private String strErrMessage="";
	  
	public String getSwitchHoldStatus() {
		return switchHoldStatus;
	}

	public void setSwitchHoldStatus(String switchHoldStatus) {
		this.switchHoldStatus = switchHoldStatus;
	}

	public final CampEnvironmentOutData[] getCampEnvironmentDetailsOuts() {
		return campEnvironmentDetailsOuts;
	}

	public final void setCampEnvironmentDetailsOuts(
			CampEnvironmentOutData[] campEnvironmentDetailsOuts) {
		this.campEnvironmentDetailsOuts = campEnvironmentDetailsOuts;
	}

	public final PromoCharityOutData[] getCharityOuts() {
		return charityOuts;
	}

	public final void setCharityOuts(PromoCharityOutData[] charityOuts) {
		this.charityOuts = charityOuts;
	}

	public final String getCharityStrErrCode() {
		return charityStrErrCode;
	}

	public final void setCharityStrErrCode(String charityStrErrCode) {
		this.charityStrErrCode = charityStrErrCode;
	}

	public final String getCharityStrErrMessage() {
		return charityStrErrMessage;
	}

	public final void setCharityStrErrMessage(String charityStrErrMessage) {
		this.charityStrErrMessage = charityStrErrMessage;
	}

	public final PromoOfferOutData[] getOfferOuts() {
		return offerOuts;
	}

	public final void setOfferOuts(PromoOfferOutData[] offerOuts) {
		this.offerOuts = offerOuts;
	}

	public final SegmentedFlagsOutData[] getSegmentedFlagsOuts() {
		return segmentedFlagsOuts;
	}

	public final void setSegmentedFlagsOuts(
			SegmentedFlagsOutData[] segmentedFlagsOuts) {
		this.segmentedFlagsOuts = segmentedFlagsOuts;
	}

	public final String getStrErrCode() {
		return strErrCode;
	}

	public final void setStrErrCode(String strErrCode) {
		this.strErrCode = strErrCode;
	}

	public final String getStrErrMessage() {
		return strErrMessage;
	}

	public final void setStrErrMessage(String strErrMessage) {
		this.strErrMessage = strErrMessage;
	}

	public final List<POWOfferDO> getPowOffers() {
		return powOffers;
	}

	public final void setPowOffers(List<POWOfferDO> powOffers) {
		this.powOffers = powOffers;
	}

}
