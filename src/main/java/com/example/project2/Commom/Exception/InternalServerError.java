package com.example.project2.Commom.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InternalServerError extends RuntimeException{
    private String message;

    public InternalServerError(String message) {
        this.message = message;
    }
}
