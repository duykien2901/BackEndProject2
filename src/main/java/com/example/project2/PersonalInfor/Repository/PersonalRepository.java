package com.example.project2.PersonalInfor.Repository;

import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalEntity, Integer> {
    @Query(value = "select * from personal_info where personal_info.account_id = :accountId", nativeQuery = true)
    Optional<PersonalEntity> findByAccountId(@Param("accountId") Integer accountId);

    PersonalEntity findByLastName(String last_name);
}
