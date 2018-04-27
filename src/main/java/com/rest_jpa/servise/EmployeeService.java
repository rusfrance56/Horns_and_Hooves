package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> findAll();
    Employee findById(long id);
    List<Employee> findAllByDepartmentId(long id);
    Employee update(Employee employee);
    void delete(long id);

    Page<Employee> listAllByPage(Pageable pageable);
}
