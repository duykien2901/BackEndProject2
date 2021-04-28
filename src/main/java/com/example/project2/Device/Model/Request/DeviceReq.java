package com.example.project2.Device.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceReq {

    private Integer status;

    private String deviceName;

}
