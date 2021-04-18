package com.example.project2.Admin.Entity;


import com.example.project2.Teacher.Entity.TeacherEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timetable")
public class TimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "shift")
    private Integer shift;

    @OneToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private TeacherEntity teacherEntity;

    @OneToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private CourseEntity courseEntity;

    @OneToOne
    @JoinColumn(name = "classroom_id", insertable = false, updatable = false)
    private ClassroomEntity classroomEntity;
}
