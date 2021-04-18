package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class PermissRoleError extends RuntimeException{
    private String message;
    public PermissRoleError(String message) {
        this.message = message;
    }
}
