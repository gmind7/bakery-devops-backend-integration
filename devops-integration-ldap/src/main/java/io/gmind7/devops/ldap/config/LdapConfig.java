package io.gmind7.devops.ldap.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathBeanPostProcessor;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.repository.config.EnableLdapRepositories;

@Configuration
@EnableLdapRepositories
@PropertySource({"classpath:environment/ldap_ko.properties"})
public class LdapConfig {
	
	@Inject
	private Environment environment;
	
	@Bean
	public LdapContextSource ldapContextSource(){
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl(environment.getRequiredProperty("ldap.url"));
		ldapContextSource.setUserDn(environment.getRequiredProperty("ldap.userDn"));
		ldapContextSource.setPassword(environment.getRequiredProperty("ldap.password"));
		return ldapContextSource;
	}
	
	@Bean
	public LdapTemplate ldapTemplate(){
		LdapTemplate ldapTemplate = new LdapTemplate();		
		ldapTemplate.setContextSource(ldapContextSource());
		ldapTemplate.setDefaultTimeLimit(environment.getRequiredProperty("ldap.timelimit", Integer.class));		
		return ldapTemplate;
	}
	
	@Bean 
	public BaseLdapPathBeanPostProcessor baseLdapPathBeanPostProcessor(){
		return new BaseLdapPathBeanPostProcessor();
	}
	
}
