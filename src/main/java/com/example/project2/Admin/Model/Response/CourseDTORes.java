package com.example.project2.Admin.Model.Response;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Student.Model.Response.StudentDTORes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDTORes {
    private Integer id;

    private String courseName;

    private List<ClassroomEntity> classroomEntities;

}
