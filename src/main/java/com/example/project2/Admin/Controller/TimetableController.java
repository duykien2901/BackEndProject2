package com.example.project2.Admin.Controller;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Repository.TimeTableRepository;
import com.example.project2.Admin.Service.Impl.TimetableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/timetable/insert")
    public ResponseEntity<?> insertTimetable() {

        TimetableEntity timetableEntity = new TimetableEntity();
        timetableEntity.setClassroomId(1);
        timetableEntity.setTeacherId(1);
        timetableEntity.setCourseId(1);
        timetableEntity.setDayOfWeek(4);
        timetableEntity.setShift(1);
        timeTableRepository.save(timetableEntity);
        return ResponseEntity.ok("ok");
    }
}