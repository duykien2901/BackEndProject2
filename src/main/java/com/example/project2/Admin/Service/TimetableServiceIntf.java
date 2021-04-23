package com.example.project2.Admin.Service;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Request.TimetableReq;
import com.example.project2.Admin.Model.Response.TimetableRes;
import com.example.project2.Teacher.Entity.TeacherEntity;

import java.util.List;

public interface TimetableServiceIntf {
    public List<TimetableRes> findAll();

    public TimetableEntity insertTimetable(Integer id, TimetableReq timetableReq);
}
