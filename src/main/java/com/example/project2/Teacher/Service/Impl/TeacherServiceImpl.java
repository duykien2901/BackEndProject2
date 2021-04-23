package com.example.project2.Teacher.Service.Impl;

import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Service.Impl.PersonalServiceImpl;
import com.example.project2.Teacher.Entity.TeacherEntity;
import com.example.project2.Teacher.Model.Response.TeacherResponse;
import com.example.project2.Teacher.Repository.TeacherRepository;
import com.example.project2.Teacher.Service.TeacherSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherSer {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    PersonalServiceImpl personalService;

    @Override
    public TeacherResponse getTeacherName(Integer id) throws IdNotFoundException{
        Optional<TeacherEntity> teacherEntity = teacherRepository.findById(id);
        if(teacherEntity.isPresent()) {
            TeacherEntity teacherEntity1 = teacherEntity.get();
            Optional<PersonalEntity> personalEntity = personalService.findByAccountId(teacherEntity1.getAccountId());
            return new TeacherResponse(id, personalEntity.get().getLastName());
        } else {
            throw new IdNotFoundException(id);
        }
    }
}
