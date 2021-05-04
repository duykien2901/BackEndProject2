package com.example.project2.Device.Controller;

import com.example.project2.Device.Repository.DeviceInfoRepository;
import com.example.project2.Device.Repository.DeviceRepository;
import com.example.project2.Device.Service.DeviceService;
import com.example.project2.Device.Service.impl.DeviceInforServiceImpl;
import com.example.project2.Device.Service.impl.DeviceServiceImpl;
import com.example.project2.Json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceInfoRepository deviceInfoRepository;

    @Autowired
    DeviceServiceImpl deviceService;

    @Autowired
    DeviceInforServiceImpl deviceInforService;

    public static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @GetMapping("all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(deviceRepository.findAll());
    }

    @GetMapping("")
    public ResponseEntity<?> findAllByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        try {
            // when page < 0, this is a exception
            Pageable pageable = PageRequest.of(page, size);
            int totalPage = (int)Math.ceil((double) deviceRepository.findAll().size()/size);

            return Optional.ofNullable(deviceService.findAllByPage(pageable))
                    .map(rsList -> !rsList.isEmpty() ? JsonResult.found(rsList, totalPage) : JsonResult.notFound("Device not found"))
                    .orElse(JsonResult.serverError("Internal Server Error")
                    );
        } catch (Exception ex) {
            logger.error("Device not found error ", ex.toString());
            return JsonResult.serverError("Internal Server Error");
        }
    }

//    @GetMapping("all/{id}")
//    public ResponseEntity<?> getDeviceById(@PathVariable("id") Integer id) {
//        return Optional.ofNullable(deviceInforService.getByDeviceId(id))
//                .map(rsList -> !rsList.isEmpty() ? JsonResult.found(rsList) : JsonResult.notFound("id"))
//                .orElse(JsonResult.serverError("Internal Server Error"));
//    }


}
