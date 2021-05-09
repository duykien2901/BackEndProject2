package com.example.project2.Grade.Entity;

import com.example.project2.Admin.Entity.CourseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "grade")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_id")
    private Integer courseId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id",referencedColumnName = "id", updatable = false, insertable = false)
    private CourseEntity courseEntity;

    @Column(name = "school_year")
    private Integer schoolYear;

    @Column(name = "term")
    private Integer term;

    @Column(name = "mid_term_test")
    private Integer midTermTest;

    @Column(name = "final_term_test")
    private Integer finalTermTest;


}
