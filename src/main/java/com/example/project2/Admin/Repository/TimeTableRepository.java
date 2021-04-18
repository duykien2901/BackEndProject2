package com.example.project2.Admin.Repository;

import com.example.project2.Admin.Entity.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimetableEntity, Integer> {
}
