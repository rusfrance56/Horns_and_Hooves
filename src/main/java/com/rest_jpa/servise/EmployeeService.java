package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;

import java.util.List;

/**
 * Created by Employee on 24.09.2016.
 */
public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> findAll();
    Employee findById(long id);
    List<Employee> findAllByDepartmentId(long id);
    Employee update(Employee employee);
    void delete(long id);
}
