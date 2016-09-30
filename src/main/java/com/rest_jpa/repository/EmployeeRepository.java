package com.rest_jpa.repository;

import com.rest_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Employee on 24.09.2016.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
