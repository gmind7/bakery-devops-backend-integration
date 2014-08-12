package io.gmind7.devops.ehcache.config;

import javax.inject.Inject;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@PropertySource("classpath:environment/ehcache.properties")
public class EhCacheConfig {

	@Inject
	private Environment environment;
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactory() {
	    EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
	    String configLocation = environment.getRequiredProperty("ehcache.configLoaction");
	    bean.setConfigLocation(new ClassPathResource(configLocation));
	    bean.setShared(true);
	    return bean;
	}
	
    @Bean
	public EhCacheCacheManager cacheManager() {
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    	ehCacheCacheManager.setCacheManager(this.ehCacheManagerFactory().getObject());
        return ehCacheCacheManager;
	}

}