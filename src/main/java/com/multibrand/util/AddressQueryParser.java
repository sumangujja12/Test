package com.multibrand.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

public class AddressQueryParser
{
    private  String field;
    Analyzer analyzer;

    public AddressQueryParser(String field,Analyzer analyzer)
    {
        this.field = field;
        this.analyzer = analyzer;
    }

    public Query Parse(String queryString, Analyzer analyzer) throws IOException {
    	
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
    				case "north":
    					altToken = "n";
    					break;
    				case "south":
    					altToken = "s";
    					break;
    				case "east":
    					altToken = "e";
    					break;
    				case "west":
    					altToken = "w";
    					break;
    				case "northwest":
    					altToken = "nw";
    					break;
    				case "southwest":
    					altToken = "sw";
    					break;
    				case "northeast":
    					altToken = "ne";
    					break;
    				case "southeast":
    					altToken = "se";
    					break;
    				default:
    					break;
    				}

    				if (altToken != token.toLowerCase()) {
    					searchQueryBuilder.add(new BooleanClause(new FuzzyQuery(new Term(field, altToken), 0, 0), Occur.SHOULD));
    				}
    				searchQueryBuilder.add(new BooleanClause(new FuzzyQuery(new Term(field, token), 0, 0), Occur.SHOULD));
    			}
    		}
    		return searchQueryBuilder;

    	}

    }

    public static boolean isNumeric(String token) { 
    	  try {  
    	    Double.parseDouble(token);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
    
}