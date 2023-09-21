package com.eliasfs06.tinktime.exceptionsHandler;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {

    private HttpStatus status;
    private int code;
    private List<ErrorObject> errors;
    private String message;

    public ErrorResponse(HttpStatus status, int code, List<ErrorObject> errors, String message) {
        this.status = status;
        this.code = code;
        this.errors = errors;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public int getCode() {
        return code;
    }
}