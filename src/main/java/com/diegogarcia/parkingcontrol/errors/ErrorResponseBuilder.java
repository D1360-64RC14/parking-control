package com.diegogarcia.parkingcontrol.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {
    private String description;
    private HttpStatusCode status;

    public ErrorResponseBuilder() {
        status = HttpStatus.BAD_REQUEST;
    }

    public ErrorResponseBuilder status(HttpStatusCode code) {
        status = code;
        return this;
    }

    public ErrorResponseBuilder description(String message) {
        description = message;
        return this;
    }

    public ResponseEntity<Object> build() {
        var errorBody = new ErrorBody(description, status.value());

        return new ResponseEntity<>(errorBody, status);
    }
}
