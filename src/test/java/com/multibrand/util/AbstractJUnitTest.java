package com.multibrand.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public abstract class AbstractJUnitTest {


	 protected String getContent(String fileName) throws IOException {
		InputStream fileStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		return IOUtils.toString(fileStream);

	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}