package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.repository.EmployeeRepository;
import com.rest_jpa.utils.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.EMPLOYEES_NOT_FOUND;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderHelper orderHelper;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return checkNotNullAndNotEmpty(employeeRepository.findAll(), EMPLOYEES_NOT_FOUND);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> findAllByDepartmentId(long id) {
        return employeeRepository.findAllByDepartmentId(id);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
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
