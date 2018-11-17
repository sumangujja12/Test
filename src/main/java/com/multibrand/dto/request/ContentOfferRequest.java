package com.multibrand.dto.request;

import java.util.List;

import com.multibrand.dto.ContentOfferDTO;
import com.multibrand.exception.ValidateRequestException;

public class ContentOfferRequest extends NRGServicesRequest implements BaseContentRequest {
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContentOfferRequest [contentReq=");
		builder.append(contentReq);
		builder.append("]");
		return builder.toString();
	}




	private List<ContentOfferDTO> contentReq;
	
	
	public List<ContentOfferDTO> getContentReq() {
		return contentReq;
	}




	public void setContentReq(List<ContentOfferDTO> contentReq) {
		this.contentReq = contentReq;
	}




	@Override
	public void validateRequest() throws ValidateRequestException {
		
		/*if(StringUtils.isBlank(this.contentReq.companyCode)){
			throw new ValidateRequestException("COMPANY CODE SHOULD NOT BE EMPTY");
		}else{
			if(!StringUtils.equalsIgnoreCase(this.contentReq.companyCode, GME_COMPANY_CODE)){
				throw new ValidateRequestException("COMPANY CODE DOESN'T MATCH WITH GME");
			}
		}
		if(StringUtils.isBlank(this.contentReq.brandId)){
			throw new ValidateRequestException("BRAND NAME SHOULD NOT BE EMPTY");
		}else{
			if(!StringUtils.equalsIgnoreCase(this.contentReq.brandId, GME_BRAND_NAME)){
				throw new ValidateRequestException("BRAND ID IS NOT VALID");
			}
		}
		if(StringUtils.isBlank(this.contentReq.bpNumber)){throw new ValidateRequestException("BP NUMBER SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.contentReq.caNumber)){throw new ValidateRequestException("CA NUMBER SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.contentReq.coNumber)){throw new ValidateRequestException("CO NUMBER SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.contentReq.esiid)){throw new ValidateRequestException("ESIID SHOULD NOT BE EMPTY");}
		if(StringUtils.isBlank(this.contentReq.contractEndDate)){
			throw new ValidateRequestException("CONTRACT END DATE SHOULD NOT BE EMPTY");
		}else{
			try {
				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(this.contentReq.contractEndDate);
			} catch (ParseException e) {
				throw new ValidateRequestException("CONTRACT END DATE SHOULD BE IN FORMAT MM/dd/yyyy");
			}
		}*/
		
		
	}

}
