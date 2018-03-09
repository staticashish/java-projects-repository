package com.skeleton.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

//@Configuration
//@EnableCaching
public class CacheConfig implements CachingConfigurer {

	private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

	@Bean
	@Override
	public CacheManager cacheManager() {
		LOG.info("Initializing simple EhCache manager.");
		return  new EhCacheCacheManager(getEhCacheFactory().getObject());	
	}

	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new MySimpleKeyGenerator();
	}
}
