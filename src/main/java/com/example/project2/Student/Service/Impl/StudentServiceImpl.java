package com.example.project2.Student.Service.Impl;

import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import com.example.project2.Student.Entity.StudentEntity;
import com.example.project2.Student.Model.Response.StudentDTORes;
import com.example.project2.Student.Repository.StudentRepository;
import com.example.project2.Student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonalRepository personalRepository;

    public List<StudentDTORes> convertToListDTO(List<StudentEntity> studentEntities) {
        List<StudentDTORes> listStudentDTORes = studentEntities.stream().map(item -> {
            return new StudentDTORes(item.getId(), personalRepository.findById(item.getAccountId()).get().getLastName());
        }).collect(Collectors.toList());
        return listStudentDTORes;
    }

    @Override
    public List<StudentDTORes> getStudentFromClass(Integer classroomId) throws IdNotFoundException {
        List<StudentEntity> studentEntities = studentRepository.findByClassroomId(classroomId);
        if (!studentEntities.isEmpty()) {
            return convertToListDTO(studentEntities);
        }

        throw new IdNotFoundException(classroomId);
    }
}
