package io.gmind7.devops.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class DataSourceProperties {
	
	@Configuration
	@Profile({"loc","inetdev"})
	@PropertySource({"classpath:environment/database_loc.properties"})
	static class profileLocPropertyConfig {
	}
	
	@Configuration
	@Profile("op")
	@PropertySource({"classpath:environment/database_inet.properties"})
	static class profileInetDevPropertyConfig {
	}
	
}
