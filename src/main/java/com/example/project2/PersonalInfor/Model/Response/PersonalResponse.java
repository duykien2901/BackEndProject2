package com.example.project2.PersonalInfor.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class PersonalResponse {
    private String firstName;

    private String lastName;

    private Integer gender;

    private Timestamp dateOfBirth;

    private String address;

    private String ethnicity;

    private String religion;

    private String phoneNumber;

    private String email;
}
