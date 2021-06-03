package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;

@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(Exception e){
		System.out.println(e.getClass().getName());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity constraintViolationException(ConstraintViolationException e){

		e.getConstraintViolations().forEach(error -> {
			System.out.println(error);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

}
