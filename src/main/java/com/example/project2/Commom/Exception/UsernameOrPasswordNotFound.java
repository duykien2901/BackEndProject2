package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class UsernameOrPasswordNotFound extends RuntimeException{
    private String message;
    public UsernameOrPasswordNotFound(String message) {
        this.message = message;
    }
}
