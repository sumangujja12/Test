package com.multibrand.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import com.multibrand.util.AddressQueryParser;
import com.multibrand.util.StreamLuceneConstants;

/** This class is a Helper class for the Resource Lucene Address Search 
 * 
 * @author RKiran
 */

@Component
public class LuceneIndexSearchHelper implements StreamLuceneConstants {

	private List<String> stopWordsList = null;

	/**
	 * Builds the Stop Words , used in Analyzer
	 * @param 
	 * @return Analyzer
	 */

	LuceneIndexSearchHelper() {
		stopWordsList = new ArrayList<>();
		stopWordsList.addAll(Arrays.asList(StreetSuffixes));
		stopWordsList.addAll(Arrays.asList(SupportedStates));
		stopWordsList.addAll(Arrays.asList(ApartmentPrefixes));
	}

	public List<String> getStopWordsList() {
		return stopWordsList;
	}

	/**
	 * This will create the Standard Analyzer used to define stopWords 
	 * @param 
	 * @return Analyzer
	 */

	public Analyzer BuildLuceneAnalyzer() {
		CharArraySet stopWordSet = new CharArraySet(Version.LUCENE_35, 5, true);
		stopWordSet.addAll(getStopWordsList());
		return new StandardAnalyzer(Version.LUCENE_35, stopWordSet);
	}
	/**
	 * This will form the Query Object for Lucene Searcher 
	 * @param state , customerType , queryString , analyzer
	 * @return BooleanQuery
	 */
	public BooleanQuery formQuery(String state, String customerType, String queryString, Analyzer analyzer) throws IOException {

		BooleanQuery query = new BooleanQuery();
		query.add(new TermQuery(new Term(STATE, state)), Occur.MUST);
		query.add(new TermQuery(new Term(CUSTOMERTYPE, customerType)), Occur.MUST);
		BooleanQuery exactOrSearchQuery = new BooleanQuery();
		query.add(exactOrSearchQuery, Occur.MUST);

		// exact query
		exactOrSearchQuery.add(new TermQuery(new Term(EXACT, queryString)), Occur.SHOULD);

		// search query
		exactOrSearchQuery.add(new AddressQueryParser(CANONICAL, analyzer).Parse(queryString, analyzer), Occur.SHOULD);
		return query;

	}
	/**
	 * This will close the Resources
	 * @param Resource in Form of Object
	 * @return void
	 */
	public void closeResources(Object resource) throws IOException {
		if (resource != null) {
			if (resource instanceof IndexReader) {
				((IndexReader) resource).close();
			}
			if (resource instanceof Directory) {
				((Directory) resource).close();
			}
			if (resource instanceof Analyzer) {
				((Analyzer) resource).close();
			}
			
		}

	}
	
	/**
	 * Checks if the REST API Input is Valid Or not 
	 * @param state , customerType , queryString (All these 3 are required as per API to work )
	 * @return boolean
	 */

	public boolean isRequestValid(String state, String customerType, String queryString) {
		boolean isReqValid = true;
		if (StringUtils.isEmpty(state) || StringUtils.isEmpty(customerType) || StringUtils.isEmpty(queryString)) {
			isReqValid = false;
		}
    	return isReqValid;
	}

}