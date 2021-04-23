package com.example.project2.Admin.Controller;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Request.TimetableReq;
import com.example.project2.Admin.Repository.TimeTableRepository;
import com.example.project2.Admin.Service.Impl.TimetableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TimetableController {
    @Autowired
    TimetableServiceImpl timetableService;

    @Autowired
    TimeTableRepository timeTableRepository;

    @GetMapping("/api/timetable/all")
    public ResponseEntity<?> getAllClassroom() {
        return ResponseEntity.ok(timetableService.findAll());
    }

    @PostMapping("/api/timetable/insert/{id}")
    public ResponseEntity<?> insertTimetable(@PathVariable Integer id, @RequestBody TimetableReq timetableReq) {
//        Map<String, String> result = new HashMap<>();
//        if(timetableService.insertTimetable(id, timetableReq)){
//            result.put("message", "Success");
//        }
        return ResponseEntity.ok(timetableService.insertTimetable(id, timetableReq));
    }
}