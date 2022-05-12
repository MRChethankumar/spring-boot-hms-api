package com.example.hmsapi.exceptions;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ControllerAdvice
public class ValidationHandler{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
//			errors.put("message", message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
		Map<String, String> response= new HashMap<>();
		response.put("message", "Request body can not by empty.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
		Map<String, String> response= new HashMap<>();
		response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?>  handleBadRequestException(NoHandlerFoundException exception, HttpServletRequest request) {
		Map<String, String> response= new HashMap<>();
		response.put("message", "Resource you are looking for not found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?>  handleForbiddenException(AccessDeniedException exception, HttpServletRequest request) {
		Map<String, String> response= new HashMap<>();
		response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<?>  handleTokenExpiredException(TokenExpiredException exception, HttpServletRequest request) {
		Map<String, String> response= new HashMap<>();
		response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
	

//	@ExceptionHandler(IOException.class)
//	protected ResponseEntity<Object> handleEmptyRequestBody(IOException e) {
//		Map<String, String> errors = new HashMap<>();
//		errors.put("message", "Please send the request body.");
//		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//	}

}
