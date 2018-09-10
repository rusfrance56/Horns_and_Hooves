package com.rest_jpa.servise;

import com.rest_jpa.entity.Person;
import com.rest_jpa.repository.EmployeeRepository;
import com.rest_jpa.utils.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.*;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderHelper orderHelper;

    @Override
    public Person create(Person person) {
        return checkNotNull(employeeRepository.save(person), EMPLOYEE_NOT_CREATED);
    }

    @Override
    public List<Person> findAll() {
        return checkNotNullAndNotEmpty(employeeRepository.findAll(), EMPLOYEES_NOT_FOUND);
    }

    @Override
    public Person findById(long id) {
        return checkNotNull(employeeRepository.getOne(id), EMPLOYEE_NOT_FOUND);
    }

    @Override
    public List<Person> findAllByDepartmentId(long id) {
        return checkNotNullAndNotEmpty(employeeRepository.findAllByDepartmentId(id), EMPLOYEES_NOT_FOUND);
    }

    @Override
    public Person update(Person person) {
        return checkNotNull(employeeRepository.save(person), EMPLOYEE_NOT_UPDATED);
    }

    @Override
    public void delete(long id) {
        Person person = employeeRepository.getOne(id);
        orderHelper.reassignmentOrders(person);
        employeeRepository.deleteById(id);
    }
}
