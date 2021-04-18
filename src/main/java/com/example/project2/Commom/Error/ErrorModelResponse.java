package com.example.project2.Commom.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorModelResponse {
    private HttpStatus httpStatus;
    private String message;
}
