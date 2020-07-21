package com.multibrand.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import com.multibrand.util.AddressQueryParser;
import com.multibrand.util.StreamLuceneConstants;

/** This class is a Helper class for the Resource Lucene Address Search  By ESIID Provided
 * 
 * @author RKiran
 */

@Component
public class LuceneIndexSearchHelper implements StreamLuceneConstants{
	
	private List<String> stopWordsList = null;
	
	LuceneIndexSearchHelper()
	{
		stopWordsList = new ArrayList<>();
		stopWordsList.addAll(Arrays.asList(StreetSuffixes));
		stopWordsList.addAll(Arrays.asList(SupportedStates));
		stopWordsList.addAll(Arrays.asList(ApartmentPrefixes));
	}

	public List<String> getStopWordsList() {
		return stopWordsList;
	}

	
	 public  Analyzer BuildLuceneAnalyzer()
	    {
	       CharArraySet stopWordSet = new CharArraySet(Version.LUCENE_42, 5, true);
	        stopWordSet.addAll(getStopWordsList());
	        return new StandardAnalyzer(Version.LUCENE_42,stopWordSet);
	    }
	 
	 public BooleanQuery formQuery(String state , String customerType , String esiId , Analyzer analyzer) throws IOException
		{
			
			    BooleanQuery query = new BooleanQuery();
			    query.add(new TermQuery(new Term("State", state)), Occur.MUST); 
			    query.add(new TermQuery(new Term("CustomerType", customerType)), Occur.MUST);
			    BooleanQuery exactOrSearchQuery = new BooleanQuery();
			    query.add(exactOrSearchQuery, Occur.MUST);
			    
			    
			    // exact query
			    exactOrSearchQuery.add(new TermQuery(new Term("Exact", esiId)), Occur.SHOULD); 
			    
			    
			    // search query
			    exactOrSearchQuery.add(new AddressQueryParser("Canonical",analyzer).Parse(esiId,analyzer), Occur.SHOULD);
			    return query;
			    
		}
		
		public  void closeResources(Object resource) throws IOException
		{
			if(resource !=null)
			{
				if(resource instanceof IndexReader)
				{
					((IndexReader) resource).close();
				}
				if(resource instanceof Directory)
				{
					((Directory) resource).close();
				}
				if(resource instanceof Analyzer)
				{
					((Analyzer) resource).close();
				}
			}
			
		}

}
