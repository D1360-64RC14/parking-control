package com.diegogarcia.parkingcontrol.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ErrorBody {
    private String message;
    private Integer statusCode;
}
