package com.multibrand.vo.response;

import java.util.List;

public class TestDataResponse extends GenericResponse {

	private List<CollectionData> collectionData;

	public List<CollectionData> getCollectionData() {
		return collectionData;
	}

	public void setCollectionData(List<CollectionData> collectionData) {
		this.collectionData = collectionData;
	}

}
