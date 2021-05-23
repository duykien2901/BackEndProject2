package com.example.project2.Device.Controller;

import com.example.project2.Device.Model.Request.DeviceReq;
import com.example.project2.Device.Repository.DeviceInfoRepository;
import com.example.project2.Device.Repository.DeviceRepository;
import com.example.project2.Device.Service.DeviceService;
import com.example.project2.Device.Service.impl.DeviceInforServiceImpl;
import com.example.project2.Device.Service.impl.DeviceServiceImpl;
import com.example.project2.Json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/page")
    public ResponseEntity<?> findAllByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            // when page < 0, this is a exception
            Pageable pageable = PageRequest.of(page, pageSize);

            return Optional.ofNullable(deviceService.findAllByPage(pageable))
                    .map(rsList -> !rsList.isEmpty() ? JsonResult.found(rsList, deviceRepository.findAll().size()) :
                            JsonResult.notFound("Device "))
                    .orElse(JsonResult.serverError("Internal Server Error")
                    );
        } catch (Exception ex) {
            logger.error("Device not found error ", ex.toString());
            return JsonResult.serverError("Internal Server Error");
        }
    }

    @GetMapping("/pageSort")
    public ResponseEntity<?> findAllByPageSort(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("sort") Boolean sort) {
        try {
            // when page < 0, this is a exception
            Pageable pageable = PageRequest.of(page, pageSize);

            return Optional.ofNullable(deviceService.findAllByPageSort(pageable, sort))
                    .map(rsList -> !rsList.isEmpty() ? JsonResult.found(rsList, deviceRepository.findAll().size()) :
                            JsonResult.notFound("Device "))
                    .orElse(JsonResult.serverError("Internal Server Error")
                    );
        } catch (Exception ex) {
            logger.error("Device not found error ", ex.toString());
            return JsonResult.serverError("Internal Server Error");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name, @RequestParam("page") Integer page,
                                          @RequestParam("size") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            int totalPage = (int)Math.ceil((double) deviceRepository.searchByName(name, pageable).getSize()/size);
            return Optional.ofNullable(deviceService.searchByDeviceName(pageable, name))
                    .map(rsList -> !rsList.isEmpty() ? JsonResult.found(rsList, totalPage) :
                            JsonResult.notFound("Device"))
                    .orElse(JsonResult.serverError("Internal Server Error"));

        } catch (Exception ex) {
            logger.error("Device is empty", ex.toString());
            return JsonResult.serverError("Internal Server Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDevice(@RequestBody DeviceReq deviceReq) {
        return ResponseEntity.ok(deviceService.addDevice(deviceReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@RequestBody DeviceReq deviceReq, @PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.updateDevice(deviceReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedDevice(@PathVariable Integer id) {
        return ResponseEntity.ok(deviceService.deletedDevice(id));
    }
}
