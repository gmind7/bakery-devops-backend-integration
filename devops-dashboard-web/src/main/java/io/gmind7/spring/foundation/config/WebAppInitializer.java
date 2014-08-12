/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gmind7.spring.foundation.config;

import io.gmind7.spring.foundation.config.AppConfig;
import io.gmind7.spring.foundation.constant.EnvType;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		Class<?>[] rootConfigClasses = this.getRootConfigClasses();
		if (!ObjectUtils.isEmpty(rootConfigClasses)) {
			AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
			String springProfilesActvie = rootAppContext.getEnvironment().getProperty(EnvType.SPRING_PROFILE_ACTIVE.getKey());
			if(springProfilesActvie==null) rootAppContext.getEnvironment().setActiveProfiles(EnvType.DEFAULT_ACTIVE_PROFILE.getKey());
			rootAppContext.register(rootConfigClasses);
			return rootAppContext;
		} else {
			return null;
		}
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected javax.servlet.Filter[] getServletFilters(){
		
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		
		return new javax.servlet.Filter[] { encodingFilter,
			                                new HiddenHttpMethodFilter()};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}
	
}
