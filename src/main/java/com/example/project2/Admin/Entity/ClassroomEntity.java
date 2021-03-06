package com.example.project2.Admin.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "classroom")
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_name", unique = true)
    private String classroomName;

    @Column(name = "home_room_teacher_id")
    private String teacherId;

    @Column(name = "location")
    private String location;
}
