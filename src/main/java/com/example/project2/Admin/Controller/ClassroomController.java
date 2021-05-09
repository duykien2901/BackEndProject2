package com.example.project2.Admin.Controller;

import com.example.project2.Admin.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class ClassroomController {
    @Autowired
    ClassroomRepository classroomRepository;

    @GetMapping("/api/classroom/all")
    public ResponseEntity<?> getALl() {
        return ResponseEntity.ok(classroomRepository.findAll());
    }
}
