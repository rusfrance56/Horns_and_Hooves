package com.rest_jpa.repository;

import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    List<User> findAllByDepartment(Department department);
    Boolean existsByUserName(String userName);
}
