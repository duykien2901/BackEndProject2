package com.example.project2.Device.Service.impl;

import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.Device.Entity.DeviceEntity;
import com.example.project2.Device.Entity.DeviceInforEntity;
import com.example.project2.Device.Model.Request.DeviceReq;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<DeviceInfoRep> changeToDeviceInforRep(Integer deviceId) {
        List<DeviceInfoRep> deviceInfoRepList = deviceInfoRepository.findByDeviceId(deviceId)
                .stream().map(list -> {
                    DeviceInfoRep deviceInfoRep = new DeviceInfoRep();
                    deviceInfoRep.setId(list.getId());
                    deviceInfoRep.setAccountId(list.getAccountId());
                    deviceInfoRep.setLastuseAt(list.getLastuseAt());
                    deviceInfoRep.setName(personalRepository.findByAccountId(list.getAccountId()).get().getLastName());
                    return deviceInfoRep;
                }).collect(Collectors.toList());
        return deviceInfoRepList;
    }

    @Override
    public Map<String, Boolean> addDevice(DeviceReq deviceReq) {
        Map<String, Boolean> result = new HashMap<>();
       try {
           DeviceEntity deviceEntity = new DeviceEntity();
           deviceEntity.setStatus(deviceReq.getStatus());
           deviceEntity.setDeviceName(deviceReq.getDeviceName());
           deviceRepository.save(deviceEntity);
           result.put("message", true);
           return result;
       } catch (Exception e) {
           result.put("message", false);
           return result;
       }
    }

    @Override
    public List<DeviceRep> searchByDeviceName(Pageable pageable, String name) {
        Page<DeviceEntity> deviceEntities = deviceRepository.searchByName(name, pageable);
        List<DeviceRep> deviceRepList = deviceEntities.stream().map(item -> {
            List<DeviceInfoRep> deviceInfoRepList = changeToDeviceInforRep(item.getId());
            return new DeviceRep(item.getId(), item.getStatus(), item.getDeviceName(), deviceInfoRepList);
        }).collect(Collectors.toList());
        return deviceRepList;
    }

    @Override
    public Map<String, Boolean> deletedDevice(Integer id) throws IdNotFoundException{
        if(deviceRepository.findById(id).isPresent()) {
            Map<String, Boolean> result = new HashMap<>();
            deviceRepository.deleteById(id);
            result.put("message", true);
            return result;
        } else {
            throw new IdNotFoundException(id);
        }
    }

    @Override
    public Map<String, Boolean> updateDevice(DeviceReq deviceReq, Integer id) throws IdNotFoundException{
        Map<String, Boolean> result = new HashMap<>();
        if(deviceRepository.findById(id).isPresent()) {
            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setId(id);
            deviceEntity.setStatus(deviceReq.getStatus());
            deviceEntity.setDeviceName(deviceReq.getDeviceName());
            deviceRepository.save(deviceEntity);
            result.put("message", true);
            return result;
        } else {
            throw new IdNotFoundException(id);
        }
    }

    @Override
    public DeviceRep findById(Integer id) throws IdNotFoundException{
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(id);
        if(deviceEntityOptional.isPresent()) {
            DeviceEntity deviceEntity = deviceEntityOptional.get();
            List<DeviceInfoRep> deviceInfoRepList = changeToDeviceInforRep(deviceEntity.getId());
            return new DeviceRep(deviceEntity.getId(), deviceEntity.getStatus(), deviceEntity.getDeviceName(), deviceInfoRepList);
        } else {
            throw new IdNotFoundException(id);
        }
    }

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
