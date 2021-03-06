package com.example.project2.Admin.Model.Response;

import com.example.project2.Student.Model.Response.StudentDTORes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassroomRes {
    private Integer id;

    private String classroomName;

    private List<StudentDTORes> studentDTORes;
}
