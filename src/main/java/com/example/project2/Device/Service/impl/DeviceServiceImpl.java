package com.example.project2.Device.Service.impl;

import com.example.project2.Device.Entity.DeviceEntity;
import com.example.project2.Device.Entity.DeviceInforEntity;
import com.example.project2.Device.Model.Response.DeviceInfoRep;
import com.example.project2.Device.Model.Response.DeviceRep;
import com.example.project2.Device.Repository.DeviceInfoRepository;
import com.example.project2.Device.Repository.DeviceRepository;
import com.example.project2.Device.Service.DeviceService;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceInfoRepository deviceInfoRepository;

    @Autowired
    PersonalRepository personalRepository;

    public static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Override
    public List<DeviceRep> findAllByPage(Pageable pageable) {
        try {
            Page<DeviceEntity> deviceEntities =  deviceRepository.findAll(pageable);
            List<DeviceRep> deviceRepList = deviceEntities.stream().map(item -> {
                List<DeviceInfoRep> deviceInfoRepList = deviceInfoRepository.findByDeviceId(item.getId())
                        .stream().map(list -> {
                            DeviceInfoRep deviceInfoRep = new DeviceInfoRep();
                            deviceInfoRep.setId(list.getId());
                            deviceInfoRep.setAccountId(list.getAccountId());
                            deviceInfoRep.setLastuseAt(list.getLastuseAt());
                            deviceInfoRep.setName(personalRepository.findByAccountId(list.getAccountId()).get().getLastName());
                            return deviceInfoRep;
                        }).collect(Collectors.toList());
                return new DeviceRep(item.getId(), item.getStatus(), item.getDeviceName(), deviceInfoRepList);
            }).collect(Collectors.toList());
            return deviceRepList;
        } catch (Exception ex) {
            logger.error("Device not found error ", ex.toString());
            return null;
        }

    }
}
