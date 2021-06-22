package com.example.project2.Student.Service;

import com.example.project2.Student.Model.Response.StudentDTORes;

import java.util.List;

public interface StudentService {
    public List<StudentDTORes> getStudentFromClass(Integer classroomId);
}
