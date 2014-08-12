package io.gmind7.spring.foundation.exception;

public interface ErrorMessageFactory<T extends Exception> {
 
    Class<T> getExceptionClass();
 
    ErrorMessage getErrorMessage(T ex);
 
    int getResponseCode();
}
