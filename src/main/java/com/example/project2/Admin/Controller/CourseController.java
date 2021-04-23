package com.example.project2.Admin.Controller;

import com.example.project2.Admin.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course/all")
    public ResponseEntity<?> getAllCourse() {
        return ResponseEntity.ok(courseRepository.findAll());
    }
}
