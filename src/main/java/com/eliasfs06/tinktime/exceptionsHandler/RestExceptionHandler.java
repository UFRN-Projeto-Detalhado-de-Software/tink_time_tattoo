package com.eliasfs06.tinktime.exceptionsHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ErrorObject> errors = new ArrayList<ErrorObject>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()));
        }

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, status.value(), errors, ex.getMessage());

        return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
    }

}