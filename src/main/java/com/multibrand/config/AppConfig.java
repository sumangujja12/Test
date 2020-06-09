package com.multibrand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.multibrand.util.EnvMessageReader;

@Configuration
public class AppConfig {

	public @Bean MongoClient mongoClient(EnvMessageReader envMessageReader){
		return new MongoClient(new MongoClientURI(envMessageReader.getMessage("mongodb.uri")));
	}
}
