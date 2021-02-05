package com.multibrand.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/** This class defines AddressQueryParser for forming the Query  used in Lucene Search Engine Searcher 
 * 
 * @author RKiran
 */

public class AddressQueryParser
{
	private static Logger logger = LogManager.getLogger(AddressQueryParser.class);
	
    private  String field;
    Analyzer analyzer;
    
   
    public static final String NORTH = "north";
    public static final String NORTH_ALT_TOKEN = "n";
    
    public static final String SOUTH = "south";
    public static final String SOUTH_ALT_TOKEN = "s";
    
    public static final String EAST = "east";
    public static final String EAST_ALT_TOKEN = "e";
    
    public static final String WEST = "west";
    public static final String WEST_ALT_TOKEN = "w";
    
    public static final String NORTHWEST = "northwest";
    public static final String NORTHWEST_ALT_TOKEN = "nw";
    
    public static final String SOUTHWEST = "southwest";
    public static final String SOUTHWEST_ALT_TOKEN = "sw";
    
    public static final String NORTHEAST = "northeast";
    public static final String NORTHEAST_ALT_TOKEN = "ne";
    
    public static final String SOUTHEAST = "southeast";
    public static final String SOUTHEAST_ALT_TOKEN = "se";
    

    public AddressQueryParser(String field,Analyzer analyzer)
    {
        this.field = field;
        this.analyzer = analyzer;
    }

    public Query Parse(String queryString, Analyzer analyzer) throws IOException {
    	logger.info("Lucene Address Search queryString method called ---"+queryString);
    	// manually building it because the Query Parser treats "AND" and such oddly for
    	List < String > tokens = new ArrayList < String > ();
    	TokenStream tokenStream = analyzer.tokenStream(field, new StringReader(queryString));
    	while (tokenStream.incrementToken()) {
    		tokens.add(tokenStream.getAttribute(CharTermAttribute.class).toString());
    	}

    	if (tokens.size() == 1) {
    		return new TermQuery(new Term(field, tokens.get(0)));

    	}
    	
    	else {

    		BooleanQuery searchQueryBuilder = new BooleanQuery();
    		for (int index = 0; index < tokens.size(); index++) {
    			String token = tokens.get(index);
    			if (isNumeric(token)) {
    				if (index == tokens.size() - 1) {
    					searchQueryBuilder.add(new BooleanClause(new TermQuery(new Term(field, token)), Occur.SHOULD));
    				}
    				else {
    					searchQueryBuilder.add(new BooleanClause(new TermQuery(new Term(field, token)), Occur.MUST));
    				}
    			}
    			else {
    				String altToken = token;
    				switch (token.toLowerCase()) {
    				case NORTH:
    					altToken = NORTH_ALT_TOKEN;
    					break;
    				case SOUTH:
    					altToken = SOUTH_ALT_TOKEN;
    					break;
    				case EAST:
    					altToken = EAST_ALT_TOKEN;
    					break;
    				case WEST:
    					altToken = WEST_ALT_TOKEN;
    					break;
    				case NORTHWEST:
    					altToken = NORTHWEST_ALT_TOKEN;
    					break;
    				case SOUTHWEST:
    					altToken = SOUTHWEST_ALT_TOKEN;
    					break;
    				case NORTHEAST:
    					altToken = NORTHEAST_ALT_TOKEN;
    					break;
    				case SOUTHEAST:
    					altToken = SOUTHEAST_ALT_TOKEN;
    					break;
    				default:
    					break;
    				}

    				if (altToken != token.toLowerCase()) {
    					searchQueryBuilder.add(new BooleanClause(new FuzzyQuery(new Term(field, altToken), 0.6f, 0), Occur.SHOULD));
    				}
    				searchQueryBuilder.add(new BooleanClause(new FuzzyQuery(new Term(field, token), 0.6f, 1), Occur.SHOULD));
    			}
    		}
    		return searchQueryBuilder;

    	}

    }

    public static boolean isNumeric(String token) { 
    	  try {  
    		  Integer.parseInt(token);
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
    
}