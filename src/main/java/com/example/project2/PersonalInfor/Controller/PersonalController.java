package com.example.project2.PersonalInfor.Controller;

import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Model.Request.PersonalRequest;
import com.example.project2.PersonalInfor.Model.Response.PersonalResponse;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import com.example.project2.PersonalInfor.Service.Impl.PersonalServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/personalInfo")
public class PersonalController {
    @Autowired
    PersonalServiceImpl personalService;

    @Autowired
    PersonalRepository personalRepository;

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getPersonalInfor(@PathVariable Integer accountId) {
        PersonalEntity personalEntity = personalService.findByAccountId(accountId).get();
//        return ResponseEntity.ok(new PersonalResponse(personalEntity.getFirstName(), personalEntity.getLastName(),
//                personalEntity.getGender(), personalEntity.getDateOfBirth(), personalEntity.getAddress(), personalEntity.getEthnicity(),
//                personalEntity.getReligion(), personalEntity.getPhoneNumber(), personalEntity.getEmail()
//                ));
        return ResponseEntity.ok(personalEntity);
    }

    @PutMapping("")
    public ResponseEntity<?> changeAccountInfor(@RequestBody PersonalEntity personalEntity) throws IdNotFoundException {
        if(personalRepository.findById(personalEntity.getId()).isPresent()) {
            personalRepository.save(personalEntity);
            return ResponseEntity.ok("Success");
        }
        throw new IdNotFoundException(personalEntity.getId());
    }

    @PostMapping("")
    public ResponseEntity<?> addAccountInfor(@RequestBody PersonalRequest personalRequest) {
        ModelMapper modelMapper = new ModelMapper();
        PersonalEntity personalEntity = modelMapper.map(personalRequest, PersonalEntity.class);
        System.out.println(personalEntity);
        personalRepository.save(personalEntity);
        return ResponseEntity.ok("success");
    }
}
