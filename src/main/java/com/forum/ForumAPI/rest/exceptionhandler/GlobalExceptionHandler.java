package com.forum.ForumAPI.rest.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.forum.ForumAPI.exception.NoPermissionException;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
import com.forum.ForumAPI.exception.UserAlreadyExistsException;
import com.forum.ForumAPI.model.ErrorResponseBody;
import com.forum.ForumAPI.model.MessageResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorResponseBody body = new ErrorResponseBody();
        
        List<String> errors = ex
        		.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.setErrors(errors);

        return ResponseEntity
        		.status(status)
        		.headers(headers)
        		.body(body);
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(RuntimeException exception) {
    	
    	String message = exception.getMessage();
    	
    	return ResponseEntity
    			.badRequest()
    			.body(new MessageResponseBody(message));
    }
    
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(RuntimeException exception) {
		
		String message = exception.getMessage();
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponseBody(message));
	}
	
	@ExceptionHandler(NoPermissionException.class)
	public ResponseEntity<?> handleNoAccess(RuntimeException exception) {
		
		String message = exception.getMessage();
		
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(new MessageResponseBody(message));
	}
}
