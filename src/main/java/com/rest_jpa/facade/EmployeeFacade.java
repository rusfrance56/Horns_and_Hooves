package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.EmployeeTO;

import java.util.List;

public interface EmployeeFacade {
    Person create(EmployeeTO employee);
    List<Person> findAll();
    Person findById(long id);
    List<Person> findAllByDepartmentId(long id);
    Person update(EmployeeTO employee);
    void delete(long id);
}
