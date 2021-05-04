package com.example.project2.Device.Model.Response;

import com.example.project2.Device.Entity.DeviceInforEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeviceRep {
    private Integer id;

    private Integer status;

    private String deviceName;

    private List<DeviceInfoRep> PersonBorrowDevice;
}
