package org.springframework.web.client;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateErrorHandler implements ResponseErrorHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RestTemplateErrorHandler.class);
	 
	@Override
	public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
		if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
			log.debug( HttpStatus.FORBIDDEN + " response. Throwing authentication exception");
			throw new AuthenticationException();
		}
	}
	 
	@Override
	public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
	 
		if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
			log.debug("Status code: {}", clienthttpresponse.getStatusCode());
			log.debug("Response, {}", clienthttpresponse.getStatusText());
			log.debug("Body : {}", clienthttpresponse.getBody());
	 
			if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
				log.debug("Call returned a error 403 forbidden resposne ");
				return true;
			}
		}
		return false;
	}
}