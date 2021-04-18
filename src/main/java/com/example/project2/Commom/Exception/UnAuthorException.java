package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class UnAuthorException extends RuntimeException{
    private String message;
    public UnAuthorException(String message) {
        this.message = message;
    }
}
