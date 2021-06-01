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

    @Query(value = "select * from timetable where classroom_id = :classroom_id order by day_of_week, shift asc", nativeQuery = true)
    public List<TimetableEntity> findByClassroomId(@Param(value = "classroom_id") Integer classroomId);

    @Query(value = "select * from timetable order by ", nativeQuery = true)
    public Page<TimetableEntity> findAllByPage(Pageable pageable);

    @Query(value = "select t from TimetableEntity t where t.classroomEntity.classroomName like %:value%")
    public Page<TimetableEntity> findByClassroomName(@Param(value = "value") String value, Pageable pageable);

    @Query(value = "select t from TimetableEntity t where t.classroomEntity.classroomName like %:value% ")
    public List<TimetableEntity> findByClassroomNameNotPage(@Param(value = "value") String value);

    @Query(value = "select t from TimetableEntity t " +
            "where t.classroomEntity.classroomName like %:className% and t.teacherEntity.id in " +
            "(select t.id from PersonalEntity p, TeacherEntity t where p.accountId = t.accountId and p.lastName like %:teacherName%) and " +
            "t.courseEntity.courseName like %:courseName%")
    public Page<TimetableEntity> findByAllDetail(@Param(value = "className") String className,
                                                 @Param(value="teacherName") String teacherName,
                                                 @Param(value = "courseName") String courseName ,Pageable pageable);

    @Query(value = "select t from TimetableEntity t " +
            "where t.classroomEntity.classroomName like %:className% and t.teacherEntity.id in " +
            "(select t.id from PersonalEntity p, TeacherEntity t where p.accountId = t.accountId and p.lastName like %:teacherName%) and " +
            "t.courseEntity.courseName like %:courseName% order by t.classroomEntity.classroomName ASC ")
    public List<TimetableEntity> findByAllDetailNotPage(@Param(value = "className") String className,
                                                 @Param(value="teacherName") String teacherName,
                                                 @Param(value = "courseName") String courseName);

}
