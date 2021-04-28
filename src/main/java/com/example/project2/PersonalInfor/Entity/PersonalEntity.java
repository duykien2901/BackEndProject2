package com.example.project2.PersonalInfor.Entity;

import com.example.project2.Auth.Entity.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "personal_info")
public class PersonalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Column(name = "religion")
    private String religion;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "account_id")
    private Integer accountId;

}
