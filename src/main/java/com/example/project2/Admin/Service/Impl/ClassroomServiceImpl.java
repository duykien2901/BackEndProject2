package com.example.project2.Admin.Service.Impl;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Admin.Model.Response.ClassroomRes;
import com.example.project2.Student.Service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl {
    @Autowired
    StudentServiceImpl studentService;


}
