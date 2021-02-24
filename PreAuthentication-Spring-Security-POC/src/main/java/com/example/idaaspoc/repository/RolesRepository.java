package com.example.idaaspoc.repository;

import com.example.idaaspoc.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Transactional
    @Query(value = "SELECT r FROM Roles r WHERE r.id = (SELECT b.role.id FROM UserRoles b WHERE b.user.id=?1)")
    List<Roles> getRolesByUserId(@Param("userId") Long userId);
}
