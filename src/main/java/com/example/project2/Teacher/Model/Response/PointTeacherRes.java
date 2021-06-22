package com.example.project2.Teacher.Model.Response;

import com.example.project2.Admin.Entity.CourseEntity;
import com.example.project2.Grade.Entity.GradeEntity;
import lombok.Data;

import java.util.List;

@Data
public class PointTeacherRes {
    private List<CourseEntity> courseList;

    private GradeEntity grade;
}
