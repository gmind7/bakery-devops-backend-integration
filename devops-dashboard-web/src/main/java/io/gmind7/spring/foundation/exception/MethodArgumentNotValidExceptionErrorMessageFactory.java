package io.gmind7.spring.foundation.exception;

import io.gmind7.spring.foundation.exception.ErrorMessage;
import io.gmind7.spring.foundation.exception.ErrorMessageFactory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class MethodArgumentNotValidExceptionErrorMessageFactory implements ErrorMessageFactory<MethodArgumentNotValidException> {
	 
    @Override
    public Class<MethodArgumentNotValidException> getExceptionClass() {
        return MethodArgumentNotValidException.class;
    }
 
    @Override
    public int getResponseCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
 
    @Override
    public ErrorMessage getErrorMessage(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        return new ErrorMessage(errors);
    }
}
