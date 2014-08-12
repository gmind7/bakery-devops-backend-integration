package io.gmind7.spring.foundation.config;

import io.gmind7.spring.foundation.config.AppConfig;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2JsonpHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonpView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
//@EnableHypermediaSupport
//@EnableEntityLinks
@EnableWebMvc
@ComponentScan(basePackages = "io.gmind7", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(Controller.class), @ComponentScan.Filter(ControllerAdvice.class)})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Inject
	private RequestMappingHandlerMapping requestHandlerMapping;
	
	@Inject
	private AppConfig appConfig;
	
	@Autowired
	private Environment env;
	
	private static final boolean JSTL_PRESENT = ClassUtils.isPresent("javax.servlet.jsp.jstl.core.Config", null);
	
	
	@PostConstruct
	public void initHandlerMapping() {
		requestHandlerMapping.setAlwaysUseFullPath(true);
		requestHandlerMapping.setRemoveSemicolonContent(false);
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable(); 
	}
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
	    resolver.setFallbackPageable(new PageRequest(0, 30));
	    argumentResolvers.add(resolver);
    }
		
	@Override
	public Validator getValidator() {
		return validator();
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		converters.add(new MappingJackson2JsonpHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
	}
	
	@Bean
	public DomainClassConverter<?> domainClassConverter() {
		return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService conversionService = new DefaultFormattingConversionService();
		addFormatters(conversionService);
		return conversionService;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	    validatorFactoryBean.setValidationMessageSource(appConfig.messageSource());
	    return validatorFactoryBean;
	}
	
	@Bean
	public SessionLocaleResolver sessionLocaleResolver(){
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("ko"));
		return sessionLocaleResolver;
	}
	
	@Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("ko"));
        return cookieLocaleResolver;
    }
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10240000);
		return multipartResolver;
	}
	
	@Bean
	public DefaultRequestToViewNameTranslator viewNameTranslator(){
		return new DefaultRequestToViewNameTranslator();
	}
	
	@Bean
    public ServletContextTemplateResolver layoutTemplateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/layouts/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setOrder(1);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    
    @Bean
    public ServletContextTemplateResolver templateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setOrder(2);
        templateResolver.setCacheable(false);
        return templateResolver;
    }
	
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        Set<IDialect> dialects = new HashSet<IDialect>();
//        dialects.add(new SpringSecurityDialect());
        dialects.add(new LayoutDialect());
        engine.setAdditionalDialects(dialects);
//        LinkedHashSet<ITemplateResolver> templateResolvers = new LinkedHashSet<ITemplateResolver>(2);
        LinkedHashSet<ITemplateResolver> templateResolvers = new LinkedHashSet<ITemplateResolver>(1);
        templateResolvers.add(templateResolverServlet());
        templateResolvers.add(layoutTemplateResolverServlet());
        engine.setTemplateResolvers(templateResolvers);
        return engine;
    }
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		viewResolver.setCache(false);
		return viewResolver;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		viewResolver.setCache(false);
		return viewResolver;
	}

	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
		
		ContentNegotiatingViewResolver negotiating = new ContentNegotiatingViewResolver();
		
		// mediaTypes property
		Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
		mediaTypes.put("html", MediaType.TEXT_HTML);
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("jsonp", MediaType.APPLICATION_JSON);
		mediaTypes.put("xml",  MediaType.APPLICATION_XML);
		mediaTypes.put("atom", MediaType.APPLICATION_ATOM_XML);
		
		ContentNegotiationStrategy pathExtensionContentNegotiationStrategy = new PathExtensionContentNegotiationStrategy(mediaTypes);
		ContentNegotiationStrategy  headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
		ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager(pathExtensionContentNegotiationStrategy, headerContentNegotiationStrategy);
		negotiating.setContentNegotiationManager(contentNegotiationManager);
		
		// viewResolvers property
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		
		if(JSTL_PRESENT) {
			log.debug("Trying to load jstl contentNegotiatingViewResolver");
			viewResolvers.add(internalResourceViewResolver());
		} else {
			log.debug("Trying to load thymleaf contentNegotiatingViewResolver");
			viewResolvers.add(thymeleafViewResolver());
		}
		
		negotiating.setViewResolvers(viewResolvers);
		
		List<View> JsonView = new ArrayList<View>();
		
		MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
		mappingJackson2JsonView.setExtractValueFromSingleKeyModel(true);
		JsonView.add(mappingJackson2JsonView);
		
		MappingJackson2JsonpView mappingJackson2JsonpView = new MappingJackson2JsonpView();
		mappingJackson2JsonpView.setExtractValueFromSingleKeyModel(true);
		JsonView.add(mappingJackson2JsonpView);
		
		negotiating.setDefaultViews(JsonView);
		
		negotiating.setOrder(0);
		
		return negotiating;
	}	

	
}