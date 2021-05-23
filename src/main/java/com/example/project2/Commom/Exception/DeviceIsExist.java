package com.example.project2.Commom.Exception;

import lombok.Data;

@Data
public class DeviceIsExist extends RuntimeException {
    private String message;

    public DeviceIsExist(String message) {
        this.message = message;
    }
}
