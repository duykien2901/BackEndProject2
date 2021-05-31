package com.example.project2.PersonalInfor.Model.Request;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
public class PersonalRequest {

    private String firstName;

    private String lastName;

    private Integer gender;

    private Timestamp dateOfBirth;

    private String address;

    private String ethnicity;

    private String religion;

    private String phoneNumber;

    private String email;

    private Integer accountId;
}
