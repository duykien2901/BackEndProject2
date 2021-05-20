package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class TimetableIsExist extends RuntimeException {
    private String message;

    public TimetableIsExist(String message) {
        this.message = message;
    }
}
