package com.example.project2.Admin.Repository;

import com.example.project2.Admin.Entity.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimetableEntity, Integer> {
    @Query(value = "select * from timetable where teacher_id = :teacherId", nativeQuery = true)
    public List<TimetableEntity> findByTeacherId(@Param(value = "teacherId") Integer teacherId);
}
