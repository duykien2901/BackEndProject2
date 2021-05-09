package com.example.project2.Admin.Entity;

import com.example.project2.Grade.Entity.GradeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name", unique = true)
    private String courseName;

}
