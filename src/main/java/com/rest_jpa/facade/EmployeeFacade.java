package com.rest_jpa.facade;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.to.EmployeeTO;

import java.util.List;

public interface EmployeeFacade {
    Employee create(EmployeeTO employee);
    List<Employee> findAll();
    Employee findById(long id);
    List<Employee> findAllByDepartmentId(long id);
    Employee update(EmployeeTO employee);
    void delete(long id);
}