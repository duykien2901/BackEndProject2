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

    private String teacherName;

    private Integer accountId;

    private String username;

    private Timestamp lastUseAt;


}
