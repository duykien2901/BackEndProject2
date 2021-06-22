package com.example.project2.Admin.Repository;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Student.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Integer> {
    public ClassroomEntity findByClassroomName(String classroom_name);
}
