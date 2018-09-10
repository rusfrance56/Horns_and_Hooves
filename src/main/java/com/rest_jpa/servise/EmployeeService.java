package com.rest_jpa.servise;

import com.rest_jpa.entity.Person;

import java.util.List;

public interface EmployeeService {
    Person create(Person person);
    List<Person> findAll();
    Person findById(long id);
    List<Person> findAllByDepartmentId(long id);
    Person update(Person person);
    void delete(long id);
}
