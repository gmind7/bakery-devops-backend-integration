package io.gmind7.devops.jpa.config;

import javax.inject.Inject;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true, order = 2)
public class DataSourceConfig {

	@Inject
	private Environment environment;
	
	@Bean(destroyMethod = "close")
	@Scope("prototype")
	public DataSource parentDatasource() {
		DataSource ds = new DataSource();	
		ds.setDriverClassName(environment.getRequiredProperty("default.database.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("default.database.url"));		
		ds.setUsername(environment.getRequiredProperty("default.database.username"));
		ds.setPassword(environment.getRequiredProperty("default.database.password"));
		ds.setMaxActive(environment.getRequiredProperty("default.database.maxActive", int.class));
		ds.setMinIdle(environment.getRequiredProperty("default.database.minIdle", int.class));
		ds.setMaxIdle(environment.getRequiredProperty("default.database.maxIdle", int.class));
		ds.setMaxWait(environment.getRequiredProperty("default.database.maxWait", int.class));
		ds.setInitialSize(environment.getRequiredProperty("default.database.initialSize", int.class));
		ds.setValidationQuery(environment.getRequiredProperty("default.database.validationQuery"));
		ds.setValidationInterval(environment.getRequiredProperty("default.database.validationInterval", long.class));
		ds.setTestOnBorrow(environment.getRequiredProperty("default.database.testOnBorrow", boolean.class));
		ds.setTestWhileIdle(environment.getRequiredProperty("default.database.testWhileIdle", boolean.class));
		ds.setTimeBetweenEvictionRunsMillis(environment.getRequiredProperty("default.database.timeBetweenEvictionRunsMillis", int.class));
		ds.setRemoveAbandoned(environment.getRequiredProperty("default.database.removeAbandoned", boolean.class));
		ds.setRemoveAbandonedTimeout(environment.getRequiredProperty("default.database.removeAbandonedTimeout", int.class));
		ds.setLogAbandoned(environment.getRequiredProperty("default.database.logAbandoned", boolean.class));
		ds.setAbandonWhenPercentageFull(environment.getRequiredProperty("default.database.abandonWhenPercentageFull", int.class));
		ds.setJdbcInterceptors(environment.getRequiredProperty("default.database.jdbcInterceptors"));
		ds.setConnectionProperties(environment.getRequiredProperty("default.database.connectionProperties"));
		return ds;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource dataSource = parentDatasource();
		dataSource.setDriverClassName(environment.getRequiredProperty("default.database.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("default.database.url"));		
		dataSource.setUsername(environment.getRequiredProperty("default.database.username"));
		dataSource.setPassword(environment.getRequiredProperty("default.database.password"));
		return dataSource;
	}
	
}
