package com.example.project2.Auth.Repository;


import com.example.project2.Auth.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Page<UserEntity> findByPermissionNotAndAndStatusNotOrderByPermissionAsc(Integer permission, Integer status, Pageable pageable);

    List<UserEntity> findByPermissionNotAndAndStatusNot(Integer permission, Integer status);
}
