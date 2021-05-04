package com.example.project2.Device.Service.impl;

import com.example.project2.Device.Entity.DeviceInforEntity;
import com.example.project2.Device.Repository.DeviceInfoRepository;
import com.example.project2.Device.Service.DeviceInfoService;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceInforServiceImpl implements DeviceInfoService {
    @Autowired
    DeviceInfoRepository deviceInfoRepository;

    @Autowired
    PersonalRepository personalRepository;

//    @Override
//    public List<DeviceInforEntity> getByDeviceId(Integer id) {
//        List<DeviceInforEntity> deviceInforEntityList = deviceInfoRepository.findByDeviceId(id);
//        List<PersonalEntity> personalEntityList = deviceInforEntityList.stream().map(item -> {
//            Optional<PersonalEntity> personalEntityList1 = personalRepository.findByAccountId(item.getAccountId());
//
//        })
//    }
}
