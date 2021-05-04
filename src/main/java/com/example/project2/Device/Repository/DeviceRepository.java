package com.example.project2.Device.Repository;

import com.example.project2.Device.Entity.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    @Query(value = "select d from DeviceEntity d")
    Page<DeviceEntity> findAll(Pageable pageable);
}
