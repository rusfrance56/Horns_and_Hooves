package com.rest_jpa.repository;

import com.rest_jpa.entity.Role;
import com.rest_jpa.enumTypes.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
