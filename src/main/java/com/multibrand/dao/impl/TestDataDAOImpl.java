package com.multibrand.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.multibrand.util.Constants;
import com.multibrand.vo.response.CollectionData;
import com.multibrand.vo.response.TestDataResponse;

@Repository("testDataDAO")
public class TestDataDAOImpl {

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	@Autowired
	private MongoClient mongoClient;

	public TestDataResponse getTestCollection(String collectionName) {
		logger.info("Collection name queried::: {}", collectionName);
		TestDataResponse response = new TestDataResponse();

		MongoDatabase db = mongoClient.getDatabase(Constants.TEST_DATA_DB);
		MongoCollection<Document> collectionData = db.getCollection(collectionName);
		MongoCursor<Document> collItr = collectionData.find().iterator();
		List<CollectionData> collections = new ArrayList<>();

		while (collItr.hasNext()) {
			Document doc = collItr.next();
			
			Set<String> keys = doc.keySet();
			CollectionData colData = new CollectionData();
			Map<String,String> map = new HashMap<>();
			for(String key:keys){
				if(!("_id".equals(key)))
					map.put(key,doc.getString(key));
			}
			colData.setData(map);
			collections.add(colData);
		}
		
		logger.info(db.getCollection(collectionName).count());

		response.setCollectionData(collections);
		return response;

	}
}
