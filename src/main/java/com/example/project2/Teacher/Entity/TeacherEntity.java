package com.example.project2.Teacher.Entity;

import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "archive_id")
    private Integer archiveId;

    @Column(name = "account_id")
    private Integer accountId;

}
