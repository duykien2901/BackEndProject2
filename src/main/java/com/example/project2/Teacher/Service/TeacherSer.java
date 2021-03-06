package com.example.project2.Teacher.Service;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Teacher.Model.Response.CourseClassStudentRes;
import com.example.project2.Teacher.Model.Response.TeacherResponse;

import java.util.List;

public interface TeacherSer {
    public TeacherResponse getTeacherName(Integer id);

    public List<TeacherResponse> findAll();

//    public CourseClassStudentRes getClassCourseStudent(Integer teacherId);

}
