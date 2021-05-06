package com.example.project2.Student.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "health_certification_id")
    private Integer heathId;

    @Column(name = "archive_id")
    private Integer archiveId;

    @Column(name = "status")
    private String status;

    @Column(name = "admission_year")
    private Integer admissionYear;

    @Column(name = "account_id")
    private Integer accountId;

}
