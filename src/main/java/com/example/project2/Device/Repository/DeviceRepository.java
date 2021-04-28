package com.example.project2.Device.Repository;

import com.example.project2.Device.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
}
