package com.example.project2.Device.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoRep {
    private Integer id;

    private String name;

    private Integer accountId;

    private Timestamp lastuseAt;


}
