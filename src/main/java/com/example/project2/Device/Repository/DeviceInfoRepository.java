package com.example.project2.Device.Repository;

import com.example.project2.Device.Entity.DeviceInforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInforEntity, Integer> {
    @Query(value = "select d from DeviceInforEntity d where d.deviceId = :id")
    public List<DeviceInforEntity> findByDeviceId(@Param("id") Integer deviceId);
}
