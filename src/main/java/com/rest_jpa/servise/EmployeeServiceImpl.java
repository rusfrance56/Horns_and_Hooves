package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Employee on 24.09.2016.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(id);
    }
}
