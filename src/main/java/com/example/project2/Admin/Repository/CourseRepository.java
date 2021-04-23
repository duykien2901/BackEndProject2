package com.example.project2.Admin.Repository;

import com.example.project2.Admin.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    public CourseEntity findByCourseName(String course_name);
}
