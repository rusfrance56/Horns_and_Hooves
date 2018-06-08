package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.repository.EmployeeRepository;
import com.rest_jpa.utils.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Employee create(Employee employee) {
        return checkNotNull(employeeRepository.save(employee), EMPLOYEE_NOT_CREATED);
    }

    @Override
    public List<Employee> findAll() {
        return checkNotNullAndNotEmpty(employeeRepository.findAll(), EMPLOYEES_NOT_FOUND);
    }

    @Override
    public Employee findById(long id) {
        return checkNotNull(employeeRepository.findOne(id), EMPLOYEE_NOT_FOUND);
    }

    @Override
    public List<Employee> findAllByDepartmentId(long id) {
        return checkNotNullAndNotEmpty(employeeRepository.findAllByDepartmentId(id), EMPLOYEES_NOT_FOUND);
    }

    @Override
    public Employee update(Employee employee) {
        return checkNotNull(employeeRepository.save(employee), EMPLOYEE_NOT_UPDATED);
    }

    @Override
    public void delete(long id) {
        Employee employee = employeeRepository.findOne(id);
        orderHelper.reassignmentOrders(employee);
        employeeRepository.delete(id);
    }

    @Override
    public Page<Employee> listAllByPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
