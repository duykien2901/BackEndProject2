package com.example.project2.PersonalInfor.Service.Impl;

import com.example.project2.Commom.Error.ErrorModelResponse;
import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Model.Response.ErrorMessage;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import com.example.project2.PersonalInfor.Service.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PersonalServiceImpl implements Personal {
    @Autowired
    PersonalRepository personalRepository;

    @Override
    public Optional<PersonalEntity> findByAccountId(Integer accountId) throws IdNotFoundException{
        if(personalRepository.findByAccountId(accountId).isPresent()) {
            return personalRepository.findByAccountId(accountId);
        }
        throw new IdNotFoundException(accountId);
    }
}
