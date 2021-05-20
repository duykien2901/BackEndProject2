package com.example.project2.Admin.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimetableSearchReq {
    private String className;

    private String teacherName;

    private String courseName;
}
