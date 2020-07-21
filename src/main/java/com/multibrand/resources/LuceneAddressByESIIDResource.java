package com.multibrand.resources;

import java.io.File;
import java.io.IOException;

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

import com.multibrand.helper.LuceneIndexSearchHelper;
import com.multibrand.util.EnvMessageReader;

/** This Resource fetches the Address based on the ESIID Input 
 * Searches the Indexed Files for the ESIID and fetches the corresponding Address
 * 
 * @author RKiran
 */

@Component
@Path("luceneSearch")
public class LuceneAddressByESIIDResource {
	
	private static Logger logger = LogManager.getLogger(LuceneAddressByESIIDResource.class);
	
	
	@Autowired
	protected EnvMessageReader envMessageReader;
	

	@Autowired
	protected LuceneIndexSearchHelper streamLuceneHelper;
		
	/** This service is to provide the Address based on ESIID
	 * 
	 * @author RKiran
	 * @param State		Customer State
	 * @param CustomerType 		 Residential Or Commercial
	 * @param esiId		Customer esiId
	 * @return response			Provide JSON/XML address as  data response
	 * @throws IOException 
	 */
		
		@POST
		@Path("getAddressByESIID")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Response getAddressByESIID(
				
				@FormParam("state") String state,
				@FormParam("customerType") String customerType,
				@FormParam("esiId")String esiId) throws IOException 
						{
			
			   String documentToReturn = null;
			   IndexReader reader = null;
			   Directory fsDir = null;
			   Analyzer analyzer = null;
			   int httpStatus = 200;
				try {

				    logger.info("state" + state);
				    logger.info("customerType" + customerType);
				    logger.info("esiId" + esiId);
				    
				    String filePath = envMessageReader.getMessage("lucene.indexed.dir");	
					logger.info("filePath" + filePath);
					File file = new File(filePath);
					fsDir = FSDirectory.open(file);
				    reader = DirectoryReader.open(fsDir);
				    IndexSearcher searcher = new IndexSearcher(reader);
				    analyzer = streamLuceneHelper.BuildLuceneAnalyzer();
				    
				    BooleanQuery query = streamLuceneHelper.formQuery(state, customerType, esiId , analyzer);
				    TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
				    searcher.search(query, collector);
				    
				    ScoreDoc[] hits = collector.topDocs().scoreDocs;
				    
				    for (int i = 0; i < hits.length; i++) {
				        documentToReturn = searcher.doc(hits[i].doc).get("Data");
				        if (i < hits.length - 1 && hits[i].score > 0.5f && hits[i].score * 0.5f > hits[i + 1].score)
				            break;
				        logger.info("document To Return formed is " + documentToReturn);
				    }
				    
				} catch (Exception e) {
					httpStatus = 500;
				    logger.error("Error getting getAddressByESIID... " + e);
				} finally {
					streamLuceneHelper.closeResources(reader);
					streamLuceneHelper.closeResources(fsDir);
					streamLuceneHelper.closeResources(analyzer);
				}
				
				if(documentToReturn == null)
				{
					logger.info("No Document found for the Input  " + "state "+ state + "\t" +  "customerType " + customerType + "\t" + "esiId " +esiId);
				}
				
				Response response = Response.status(httpStatus).entity(documentToReturn).build();
				return response;
				
		}
		
		
		
}
