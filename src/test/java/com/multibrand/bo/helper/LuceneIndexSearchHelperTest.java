package com.multibrand.bo.helper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.multibrand.helper.LuceneIndexSearchHelper;
import com.multibrand.util.StreamLuceneConstants;

public class LuceneIndexSearchHelperTest implements StreamLuceneConstants{
	
	
	@InjectMocks
	LuceneIndexSearchHelper luceneHelper;
	
	
	private List<String> stopWordsList = null;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	

	}
	
	public void setUp() {
		
		stopWordsList = new ArrayList<>();
		stopWordsList.addAll(Arrays.asList(StreetSuffixes));
		stopWordsList.addAll(Arrays.asList(SupportedStates));
		stopWordsList.addAll(Arrays.asList(ApartmentPrefixes));
	}
	
	
	@Test
	public void getStopWordsList() {
		setUp();
		LuceneIndexSearchHelper luceneHelperMock = Mockito.mock(LuceneIndexSearchHelper.class);
		Mockito.when(luceneHelperMock.getStopWordsList()).thenReturn(stopWordsList);
		
		luceneHelper.getStopWordsList();
		assertThat(stopWordsList.isEmpty(), is(false));

		
		
	}
	
	

}
