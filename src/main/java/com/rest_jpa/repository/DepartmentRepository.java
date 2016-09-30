package com.rest_jpa.repository;

import com.rest_jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 30.09.2016.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
