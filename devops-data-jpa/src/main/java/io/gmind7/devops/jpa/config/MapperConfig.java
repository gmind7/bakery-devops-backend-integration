package io.gmind7.devops.jpa.config;

import java.util.Properties;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages="io.gmind7.**.mybatis", sqlSessionFactoryRef="mybatisSessionFactory")
@PropertySource({"classpath:environment/mybatis.properties"})
public class MapperConfig {
	
	@Inject
	private Environment environment;
	
	@Inject
	private DataSourceConfig dataSourceConfig;
	
	@Bean(name="mybatisSessionFactory")
	public SqlSessionFactoryBean mybatisSessionFactory() {
		Properties mybatisProperties = new Properties();
		mybatisProperties.setProperty("mapperLocations", environment.getRequiredProperty("mybatis.mapperLocations"));
		mybatisProperties.setProperty("configLoaction", environment.getRequiredProperty("mybatis.configLoaction"));
		mybatisProperties.setProperty("typeAliasesPackage", environment.getRequiredProperty("mybatis.typeAliasesPackage"));
		
		SqlSessionFactoryBean mybatisSessionFactory = new SqlSessionFactoryBean();
		mybatisSessionFactory.setDataSource(dataSourceConfig.dataSource());
		mybatisSessionFactory.setConfigurationProperties(mybatisProperties);
		return mybatisSessionFactory;
	}
	
	@Bean(name = "mybatisTX")
	public PlatformTransactionManager mybatisTransactionManager(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSourceConfig.dataSource());
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}
	
}
