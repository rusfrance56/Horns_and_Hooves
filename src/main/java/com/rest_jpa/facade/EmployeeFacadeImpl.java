package com.rest_jpa.facade;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Employee create(EmployeeTO to) {
        Department department = departmentService.findById(to.getDepartment().getId());
        if (department != null) {
            Employee newEmployee = new Employee(to);
            newEmployee.setDepartment(department);
            return employeeService.create(newEmployee);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
//        throw new ApplicationException(ErrorKey.TEST_KEY, "param1", "param2");
        return employeeService.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeService.findById(id);
    }

    @Override
    public List<Employee> findAllByDepartmentId(long id) {
       return employeeService.findAllByDepartmentId(id);
    }

    @Override
    public Employee update(EmployeeTO to) {
        Department department = departmentService.findById(to.getDepartment().getId());
        Employee employee = employeeService.findById(to.getId());

        if (employee != null && department != null) {
            employee.setId(employee.getId());
            employee.setName(to.getName());
            employee.setSurName(to.getSurName());
            employee.setMiddleName(to.getMiddleName());
            employee.setDepartment(department);
            return employeeService.update(employee);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        employeeService.delete(id);
    }
}
