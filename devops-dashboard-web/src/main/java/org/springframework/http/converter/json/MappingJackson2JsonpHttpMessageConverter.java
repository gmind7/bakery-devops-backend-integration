package org.springframework.http.converter.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

public class MappingJackson2JsonpHttpMessageConverter extends MappingJackson2HttpMessageConverter {
	 
    private static final String DEFAULT_CALLBACK_PARAMETER = "callback";
 
    private boolean prefixJson = false;
    
    @Override
    protected void writeInternal( Object object, HttpOutputMessage outputMessage ) throws IOException, HttpMessageNotWritableException {
    	
        JsonGenerator jsonGenerator = getJsonGenerator(outputMessage);
        
        try {
        	String callbackParam = getRequestParam(DEFAULT_CALLBACK_PARAMETER);
        	
        	if (this.prefixJson) {
				jsonGenerator.writeRaw("{} && ");
			}
        	
            if (callbackParam!=null) {
                jsonGenerator.writeRaw(callbackParam);
                jsonGenerator.writeRaw(" (");
                getObjectMapper().writeValue(jsonGenerator, object);
                jsonGenerator.writeRaw(");");
                jsonGenerator.flush();
            } else {
            	getObjectMapper().writeValue(jsonGenerator, object);
            }
		}
		catch (IOException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
        
    }
 
    private JsonGenerator getJsonGenerator( HttpOutputMessage outputMessage ) throws IOException {
        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
        return getObjectMapper().getFactory().createGenerator(outputMessage.getBody(), encoding);
    }
 
    private String getRequestParam(String paramName) {
        return getServletRequest().getParameter(paramName);
    }
 
    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
