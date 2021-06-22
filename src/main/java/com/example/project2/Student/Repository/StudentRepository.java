package com.example.project2.Student.Repository;

import com.example.project2.Student.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    public StudentEntity findByAccountId(Integer accountId);

    public List<StudentEntity> findByClassroomId(Integer classroomId);
}
