package io.gmind7.devops.ldap.sonar.config;

import javax.inject.Inject;

import org.sonar.wsclient.Host;
import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"classpath:environment/sonar.properties"})
public class SonarConfig {
	
	@Inject
	private Environment environment;
	
	@Bean 
	public Sonar sonarTemplate() {
		String host     = environment.getRequiredProperty("sonar.host");
		String username = environment.getRequiredProperty("sonar.username");
		String password = environment.getRequiredProperty("sonar.password");
		return new Sonar(new HttpClient4Connector(new Host(host, username, password)));
	}
	
	@Bean
	public ResourceQuery sonarResourceQuery(){
		ResourceQuery resourceQuery = new ResourceQuery();
		resourceQuery.setIncludeTrends(true);
		resourceQuery.setMetrics("coverage", "lines", "complexity", "commented_out_code_lines", "qi-complex-distrib", "test_execution_time");
		return resourceQuery;
	}

}
