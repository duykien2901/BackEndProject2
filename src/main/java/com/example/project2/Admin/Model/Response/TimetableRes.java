package com.example.project2.Admin.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimetableRes {
    private Integer id;

    private String className;

    private Integer classroomId;

    private String teacherName;

    private Integer teacherId;

    private String courseName;

    private Integer courseId;


    private Integer dayOfWeek;

    private Integer shift;
    public TimetableRes() {}
}
