package com.example.project2.Grade.Repository;

import com.example.project2.Grade.Entity.GradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {
    public List<GradeEntity> findByStudentId(Integer studentId);

    @Query(value = "select g from GradeEntity g where g.studentId = :studentId")
    public Page<GradeEntity> findByStudentId(@Param("studentId") Integer studentId, Pageable pageable);

    @Query(value = "select g.studentId from GradeEntity g where g.studentId = 3")
    public Integer findByStudentIdtest();
}
