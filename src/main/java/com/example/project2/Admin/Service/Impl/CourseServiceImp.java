package com.example.project2.Admin.Service.Impl;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Admin.Entity.CourseEntity;
import com.example.project2.Admin.Model.Response.CourseDTORes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp {
    public CourseDTORes convertToCourseDTO(CourseEntity courseEntity, List<ClassroomEntity> classroomEntities) {
        CourseDTORes courseDTORes = new CourseDTORes(courseEntity.getId(), courseEntity.getCourseName(), classroomEntities);
        return courseDTORes;
    }
}
