package io.gmind7.spring.foundation.exception;

import io.gmind7.spring.foundation.exception.ErrorMessage;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler
    public ResponseEntity<Object> handleException(NoSuchRequestHandlingMethodException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler
    public ResponseEntity<Object> handleException(DataAccessException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler
    public ResponseEntity<Object> handleException(EmptyResultDataAccessException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_MODIFIED);
    }
	
	 
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.GONE)
//    @ResponseBody
//    public ErrorMessage handleException(Exception ex) {
//        ErrorMessage errorMessage = createErrorMessage(ex);
//        return errorMessage;
//    }
//    
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ResponseBody
//    public ErrorMessage handleException(Exception ex) {
//        ErrorMessage errorMessage = createErrorMessage(ex);
//        return errorMessage;
//    }

}
