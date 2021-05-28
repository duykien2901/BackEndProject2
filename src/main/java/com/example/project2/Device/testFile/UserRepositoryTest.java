package com.example.project2.Device.testFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryTest extends JpaRepository<User, Long> {
}