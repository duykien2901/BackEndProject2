package com.example.project2.Device.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "device_manage")
public class DeviceInforEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "lastuse_at")
    private Timestamp lastuseAt;
}