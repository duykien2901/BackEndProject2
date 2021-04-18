package com.example.project2.PersonalInfor.Service;

import com.example.project2.PersonalInfor.Entity.PersonalEntity;

import java.util.Optional;

public interface Personal {
    Optional<PersonalEntity> findByAccountId(Integer id);
}
