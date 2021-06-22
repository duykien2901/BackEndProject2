package com.example.project2.Teacher.Model.Response;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Admin.Entity.CourseEntity;
import lombok.Data;

import java.util.List;

@Data
public class CourseClassStudentRes {
    private List<CourseEntity> courseEntities;

    private List<ClassroomEntity> classroomEntities;

}
