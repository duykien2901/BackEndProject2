package com.example.project2.Grade.Service.Impl;

import com.example.project2.Grade.Entity.GradeEntity;
import com.example.project2.Grade.Repository.GradeRepository;
import com.example.project2.Grade.Service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GradeSeviceImpl implements GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public Page<GradeEntity> getGradeByPage(Integer studentId, Pageable pageable) {
        log.info(String.valueOf("Size: " + gradeRepository.findByStudentId(studentId, pageable).getSize()));
        return gradeRepository.findByStudentId(studentId, pageable);
    }
}
