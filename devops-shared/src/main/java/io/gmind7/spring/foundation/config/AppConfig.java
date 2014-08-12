package io.gmind7.spring.foundation.config;

import io.gmind7.spring.foundation.constant.EnvType;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "io.gmind7", excludeFilters = {@ComponentScan.Filter(Controller.class)})
@PropertySource("classpath:environment/application.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	public AppConfig(){
		String profileActive = (String)System.getProperty(EnvType.SPRING_PROFILE_ACTIVE.getKey());
		if(profileActive==null) System.setProperty(EnvType.SPRING_PROFILE_ACTIVE.getKey(), EnvType.DEFAULT_ACTIVE_PROFILE.getKey());
	}
	
	@PostConstruct
	public void AppConfigPostConstruct(){
		String profileActive = env.getProperty(EnvType.SPRING_PROFILE_ACTIVE.getKey());
		StringBuffer sb = new StringBuffer("\n");
		sb.append("  ____           _           _ _____   ____        _                          \n");
		sb.append(" / ___|_ __ ___ (_)_ __   __| |___  | | __ )  __ _| | _____ _ __ _   _        \n");
		sb.append("| |  _| '_ ` _ \\| | '_ \\ / _` |  / /  |  _ \\ / _` | |/ / _ \\ '__| | | |   \n");
		sb.append("| |_| | | | | | | | | | | (_| | / /   | |_) | (_| |   <  __/ |  | |_| |       \n");
		sb.append(" \\____|_| |_| |_|_|_| |_|\\__,_|/_/    |____/ \\__,_|_|\\_\\___|_|   \\__, | \n");
		sb.append("                                                                 |___/        \n");
		sb.append(":: Welcome to the Gmind7 Java Recipes__________"+ profileActive +"_____       \n");
		sb.append("\n");
		System.out.print(sb.toString());
		
	}
	
	@Autowired
	private Environment environment;
	
	@Bean(name="messageSource")
	public MessageSource messageSource() {
		List<String> fileList = new ArrayList<String>();
        fileList.add("classpath:message/message");
        fileList.add("classpath:message/validation");
        
        String[] files = (String[])fileList.toArray(new String[fileList.size()]);
        
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(files);
        messageSource.setDefaultEncoding("UTF-8");
        //messageSource.setAlwaysUseMessageFormat(true);
        if (environment.acceptsProfiles("loc")) {
			messageSource.setCacheSeconds(0);
		}
        return messageSource;
	}
	
	@Bean(name="messageSourceAccessor")
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(messageSource());
	}
		
}