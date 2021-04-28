package com.example.project2.Device.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "device")
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer status;

    @Column(name = "device_name")
    private String deviceName;
}
