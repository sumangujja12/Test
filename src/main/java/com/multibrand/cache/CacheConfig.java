package com.multibrand.cache;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import com.multibrand.util.Constants;

@Configurable
@EnableCaching
public class CacheConfig implements Constants{
	Logger logger = LogManager.getLogger("CommonUtil");
	
	
	@Bean(name="cacheManager")
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(
				Arrays.asList(new ConcurrentMapCache(ERROR_CONTENT_CACHE)));
		return cacheManager;
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	@CacheEvict(value = ERROR_CONTENT_CACHE, allEntries = true)
	public void resetCache() {
		logger.info("Cache [Key = "+ERROR_CONTENT_CACHE+" ] is being cleared...");
	}	

}