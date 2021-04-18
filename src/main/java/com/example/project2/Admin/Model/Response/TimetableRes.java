package com.example.project2.Admin.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimetableRes {
    private Integer id;

    private String className;

    private String teacherName;

    private String courseName;

    private Integer dayOfWeek;

    private Integer shift;
    public TimetableRes() {}
}
