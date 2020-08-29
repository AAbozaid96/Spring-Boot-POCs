package com.example.idaaspoc.repository;

import com.example.idaaspoc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByTechnicalId(String technicalId);
}
