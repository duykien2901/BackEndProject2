package com.example.project2.PersonalInfor.Controller;

import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Model.Response.PersonalResponse;
import com.example.project2.PersonalInfor.Service.Impl.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonalController {
    @Autowired
    PersonalServiceImpl personalService;

    @GetMapping("/api/personalInfo/{accountId}")
    public ResponseEntity<?> getPersonalInfor(@PathVariable Integer accountId) {
        PersonalEntity personalEntity = personalService.findByAccountId(accountId).get();
//        return ResponseEntity.ok(new PersonalResponse(personalEntity.getFirstName(), personalEntity.getLastName(),
//                personalEntity.getGender(), personalEntity.getDateOfBirth(), personalEntity.getAddress(), personalEntity.getEthnicity(),
//                personalEntity.getReligion(), personalEntity.getPhoneNumber(), personalEntity.getEmail()
//                ));
        return ResponseEntity.ok(personalEntity);
    }
}
