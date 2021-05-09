package com.example.project2.Admin.Repository;

import com.example.project2.Admin.Entity.TimetableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimetableEntity, Integer> {
    @Query(value = "select * from timetable where teacher_id = :teacherId", nativeQuery = true)
    public List<TimetableEntity> findByTeacherId(@Param(value = "teacherId") Integer teacherId);

    @Query(value = "select * from timetable where classroom_id = :classroom_id", nativeQuery = true)
    public List<TimetableEntity> findByClassroomId(@Param(value = "classroom_id") Integer classroomId);

    @Query(value = "select * from timetable", nativeQuery = true)
    public Page<TimetableEntity> findAllByPage(Pageable pageable);
}
