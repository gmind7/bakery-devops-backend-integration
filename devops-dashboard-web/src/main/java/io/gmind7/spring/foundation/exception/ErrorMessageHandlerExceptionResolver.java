package io.gmind7.spring.foundation.exception;

import io.gmind7.spring.foundation.exception.ErrorMessage;
import io.gmind7.spring.foundation.exception.ErrorMessageFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class ErrorMessageHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
	 
    private static final int DEFAULT_ORDER = 0;
    
    @SuppressWarnings("rawtypes")
	private Map<Class<? extends Exception>, ErrorMessageFactory> errorMessageFactories;
    private HttpMessageConverter<?>[] messageConverters;
 
    public ErrorMessageHandlerExceptionResolver() {
        setOrder(DEFAULT_ORDER);
    }
 
    @SuppressWarnings("rawtypes")
	public void setErrorMessageFactories(ErrorMessageFactory[] errorMessageFactories) {
        this.errorMessageFactories = new HashMap<>(errorMessageFactories.length);
        for (ErrorMessageFactory<?> errorMessageFactory : errorMessageFactories) {
            this.errorMessageFactories.put(errorMessageFactory.getExceptionClass(), errorMessageFactory);
        }
    }
 
    public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
        this.messageConverters = messageConverters;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ErrorMessageFactory errorMessageFactory = errorMessageFactories.get(ex.getClass());
        if (errorMessageFactory != null) {
            response.setStatus(errorMessageFactory.getResponseCode());
            ErrorMessage errorMessage = errorMessageFactory.getErrorMessage(ex);
            ServletWebRequest webRequest = new ServletWebRequest(request, response);
            try {
                return handleResponseBody(errorMessage, webRequest);
            } catch (Exception handlerException) {
                logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
            }
        }
        return null;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
    private ModelAndView handleResponseBody(Object returnValue, ServletWebRequest webRequest)
            throws ServletException, IOException {
 
        HttpInputMessage inputMessage = new ServletServerHttpRequest(webRequest.getRequest());
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());
        Class<?> returnValueType = returnValue.getClass();
        if (this.messageConverters != null) {
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                for (HttpMessageConverter messageConverter : this.messageConverters) {
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                        messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                        return new ModelAndView();
                    }
                }
            }
        }
        if (logger.isWarnEnabled()) {
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " +
                    acceptedMediaTypes);
        }
        return null;
    }
}
