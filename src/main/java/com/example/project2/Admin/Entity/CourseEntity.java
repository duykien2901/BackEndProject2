package com.example.project2.Admin.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String courseName;
}
