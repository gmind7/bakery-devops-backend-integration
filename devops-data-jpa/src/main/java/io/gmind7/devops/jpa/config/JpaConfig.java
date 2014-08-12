package io.gmind7.devops.jpa.config;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("io.gmind7")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef="springAuditorAware")
@PropertySource("classpath:environment/hibernate.properties")
public class JpaConfig {
	
	@Inject
	private Environment environment;
	
	@Inject
	private DataSourceConfig dataSourceConfig;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		
		entityManager.setJpaVendorAdapter(vendorAdapter);  
		entityManager.setPackagesToScan(environment.getRequiredProperty("hibernate.packagesToScan"));
		entityManager.setMappingResources(environment.getRequiredProperty("hibernate.mappingResources"));
		entityManager.setDataSource(dataSourceConfig.dataSource());
		entityManager.setJpaProperties(additionalProperties());
		
		return entityManager;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	
	public Properties additionalProperties(){
		Properties properties = new Properties();
		// Default SQL
		properties.setProperty("hibernate.default_catalog", environment.getRequiredProperty("hibernate.default_catalog"));
		properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
		properties.setProperty("hibernate.auto_close_session", environment.getRequiredProperty("hibernate.auto_close_session"));
		properties.setProperty("hibernate.cache.use_second_level_cache", environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
		properties.setProperty("hibernate.cache.use_query_cache", environment.getRequiredProperty("hibernate.cache.use_query_cache"));
		properties.setProperty("hibernate.generate_statistics", environment.getRequiredProperty("hibernate.generate_statistics"));
		properties.setProperty("net.sf.ehcache.configurationResourceName", environment.getRequiredProperty("ehcache.configLoaction"));
		properties.setProperty("hibernate.cache.region.factory_class", environment.getRequiredProperty("hibernate.cache.region.factory_class"));
		properties.setProperty("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		// Logging
		properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
		// charset
		properties.setProperty("hibernate.connection.CharSet", environment.getRequiredProperty("hibernate.connection.CharSet"));
		properties.setProperty("hibernate.connection.characterEncoding", environment.getRequiredProperty("hibernate.connection.characterEncoding"));
		properties.setProperty("hibernate.connection.useUnicode", environment.getRequiredProperty("hibernate.connection.useUnicode"));
		return properties;
	}
	
}
