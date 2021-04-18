package com.example.project2.Teacher.Controller;

import com.example.project2.Teacher.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/api/teacher/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }
}
