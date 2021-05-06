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

    @PutMapping("/api/timetable/insert/{id}")
    public ResponseEntity<?> insertTimetable(@PathVariable Integer id, @RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.insertTimetable(id, timetableReq));
    }

    @PostMapping("/api/timetable/new")
    public ResponseEntity<?> addTimetable(@RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.addNew(timetableReq));
    }

    @GetMapping("/api/timetable/teacher-all/{id}")
    public ResponseEntity<?> getTeacherFromId(@PathVariable("id") Integer teacherId) {
        return ResponseEntity.ok(timetableService.getTeacherFromId(teacherId));
    }

    @PostMapping("/api/timetable/test/getList/{id}")
    public ResponseEntity<?> getListTeacherId(@PathVariable("id") Integer id, @RequestBody TimetableReq timetableReq) {
        return ResponseEntity.ok(timetableService.test(id, timetableReq));
    }

    // API For Student
    @GetMapping("/api/timetable/student/{student_id}")
    public ResponseEntity<?> getTimetableForStudent(@PathVariable("student_id") Integer studentId) {
        return ResponseEntity.ok(timetableService.getTimetableFromStudentId(studentId));
    }

}