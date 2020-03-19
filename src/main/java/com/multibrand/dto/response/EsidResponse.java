package com.multibrand.dto.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.multibrand.vo.request.ESIDData;

public class EsidResponse extends SalesBaseResponse implements Serializable {

	private static final long serialVersionUID = -6374796615856422775L;
	
	private List<ESIDData> esidList;

	public List<ESIDData> getEsidList() {
		return esidList;
	}

	public void setEsidList(List<ESIDData> esidList) {
		this.esidList = esidList;
	}

	public String toString(){
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
