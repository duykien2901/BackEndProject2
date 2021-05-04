package com.example.project2.Device.Service;

import com.example.project2.Device.Entity.DeviceEntity;
import com.example.project2.Device.Model.Response.DeviceRep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {
    public List<DeviceRep> findAllByPage(Pageable pageable);
}