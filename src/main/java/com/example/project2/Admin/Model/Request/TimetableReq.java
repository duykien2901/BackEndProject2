package com.example.project2.Admin.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableReq {
    private Integer classroomId;

    private Integer teacherId;

    private Integer courseId;

    private Integer dayOfWeek;

    private Integer shift;
}
