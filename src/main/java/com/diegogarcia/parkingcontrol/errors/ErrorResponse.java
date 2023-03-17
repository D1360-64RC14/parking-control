package com.diegogarcia.parkingcontrol.errors;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    public static ErrorResponseBuilder description(String message) {
        return new ErrorResponseBuilder()
                .description(message);
    }

    public static ErrorResponseBuilder status(HttpStatus code) {
        return new ErrorResponseBuilder()
                .status(code);
    }
}
