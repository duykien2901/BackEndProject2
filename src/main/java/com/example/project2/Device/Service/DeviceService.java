package com.example.project2.Device.Service;

import com.example.project2.Device.Entity.DeviceEntity;
import com.example.project2.Device.Model.Request.DeviceReq;
import com.example.project2.Device.Model.Response.DeviceRep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    public List<DeviceRep> findAllByPage(Pageable pageable);

    public DeviceRep findById(Integer id);

    public List<DeviceRep> searchByDeviceName(Pageable pageable, String name);

    public Map<String, Boolean> addDevice(DeviceReq deviceReq);

    public Map<String, Boolean> updateDevice(DeviceReq deviceReq, Integer id);

    public Map<String , Boolean> deletedDevice(Integer id);
}