package com.multibrand.resources;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.multibrand.helper.LuceneIndexSearchHelper;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.response.lucenesearch.Location;

/** This Resource fetches the Address based on the ESIID Input 
 * Searches the Indexed Files for the ESIID and fetches the corresponding Address
 * 
 * @author RKiran
 */

@Component
@Path("luceneSearch")
public class LuceneAddressSearchResource {

	private static Logger logger = LogManager.getLogger(LuceneAddressSearchResource.class);

	@Autowired
	protected EnvMessageReader envMessageReader;

	@Autowired
	protected LuceneIndexSearchHelper addressLuceneHelper;

	/** This service is to provide the Address based on Lucene Search Engine 
	 * 
	 * @author RKiran
	 * @param State		Customer State
	 * @param CustomerType 		 Residential Or Commercial
	 * @param queryString		 queryString (esiid/typeahead/zipcode)
	 * @return response			Provide JSON/XML address as  data response
	 * @throws IOException 
	 */

	@POST
	@Path("getAddress")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({
		MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
	})
	public Response getAddress(

		@FormParam("state") String state,
		@FormParam("customerType") String customerType,
		@FormParam("queryString") String queryString) throws IOException {
		
		long startTime = System.currentTimeMillis();

		String documentToReturn = null;
		IndexReader reader = null;
		Directory fsDir = null;
		Analyzer analyzer = null;
		int httpStatus = 200;
		Set<Location> responseSet = new LinkedHashSet<>();
		Gson gson = new Gson();
		try {
			
			logger.info("state" + state);
			logger.info("customerType" + customerType);
			logger.info("queryString" + queryString);

			if(addressLuceneHelper.isRequestValid(state, customerType, queryString))
			{
				
			String filePath = envMessageReader.getMessage("lucene.indexed.dir");
			logger.info("lucene indexed dir location" + filePath);
			File file = new File(filePath);
			fsDir = FSDirectory.open(file);
			reader = DirectoryReader.open(fsDir);
			IndexSearcher searcher = new IndexSearcher(reader);
			analyzer = addressLuceneHelper.BuildLuceneAnalyzer();

			BooleanQuery query = addressLuceneHelper.formQuery(state, customerType, queryString, analyzer);
			TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
			searcher.search(query, collector);

			ScoreDoc[] hits = collector.topDocs().scoreDocs;

		

			for (int i = 0; i<hits.length; i++) {
				documentToReturn = searcher.doc(hits[i].doc).get("Data");
				Location location = gson.fromJson(documentToReturn, Location.class);
				responseSet.add(location);
				
				// Simple heuristic to reduce match count when the top choices are a good match and the remaining ones aren't
				if (i<hits.length - 1 && hits[i].score > 0.5f && hits[i].score * 0.5f > hits[i + 1].score)
					break;
				
			}
			}
		}
		
		catch (Exception e) {
			httpStatus = 500;
			logger.error("Error getting getAddressFromLuceneSearch... " + e);
		} 
		
		finally {
			
			addressLuceneHelper.closeResources(reader);
			addressLuceneHelper.closeResources(fsDir);
			addressLuceneHelper.closeResources(analyzer);
		}

		if (responseSet.isEmpty()) {
			logger.info("No Documents found for the Input  " + "state " + state + "\t" + "customerType " + customerType + "\t" + "queryString " + queryString);
		}
		
		long endTime = System.currentTimeMillis();
    	Response response = Response.status(httpStatus).entity(gson.toJson(responseSet)).build();
		logger.info("getAddress API Call took " + (endTime - startTime) + " milliseconds");
		return response;

	}

}