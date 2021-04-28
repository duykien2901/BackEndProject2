package com.example.project2.Device.Controller;

import com.example.project2.Device.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceRepository deviceRepository;

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(deviceRepository.findAll());
    }
    
}
