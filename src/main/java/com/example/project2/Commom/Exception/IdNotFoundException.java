package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class IdNotFoundException extends RuntimeException{
    private String message;
    public IdNotFoundException(Integer id) {
        this.message = "Not Found id: "+ id;
    }
}
