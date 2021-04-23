package com.example.project2.Teacher.Controller;

import com.example.project2.Teacher.Repository.TeacherRepository;
import com.example.project2.Teacher.Service.Impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherServiceImpl teacherService;

    @GetMapping("/api/teacher/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/api/teacher/personal-name/{id}")
    public ResponseEntity<?> getTeacherName(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherName(id));
    }
}
