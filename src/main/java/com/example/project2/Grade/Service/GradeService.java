package com.example.project2.Grade.Service;

import com.example.project2.Grade.Entity.GradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GradeService {
    public Page<GradeEntity> getGradeByPage(Integer studentId, Pageable pageable);
}
