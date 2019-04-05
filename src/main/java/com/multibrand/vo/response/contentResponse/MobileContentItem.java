package com.multibrand.vo.response.contentResponse;

import java.util.ArrayList;
import java.util.List;

public class MobileContentItem {

	private List<MobileContent> english;
	private List<MobileContent> spanish;

	public List<MobileContent> getSpanish() {
		if(spanish==null) {
			spanish = new ArrayList<MobileContent>();
		}
		return spanish;
	}

	public void setSpanish(List<MobileContent> spanish) {
		this.spanish = spanish;
	}

	public List<MobileContent> getEnglish() {
		if (english == null) {
			english = new ArrayList<MobileContent>();
		}
		return english;
	}

	public void setEnglish(List<MobileContent> english) {
		this.english = english;
	}

}
