package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class UsernameIsExist extends RuntimeException{
    private String message;

    public UsernameIsExist(String message) {
        this.message = message;
    }
}
