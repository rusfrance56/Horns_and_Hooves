package com.rest_jpa.repository;

import com.rest_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Employee on 24.09.2016.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
