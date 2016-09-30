package com.rest_jpa.controller;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.request.EmployeeRequest;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Employee on 24.09.2016.
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequest employeeReq) {
        Employee newEmployee = new Employee();
        newEmployee.setName(employeeReq.getName());
        newEmployee.setSurName(employeeReq.getSurName());
        newEmployee.setMiddleName(employeeReq.getMiddleName());

        long department_id = employeeReq.getDepartment_id();
        Department department = departmentService.findById(department_id);
        if (department != null) {
            newEmployee.setDepartment(department);
            newEmployee = employeeService.create(newEmployee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findById(@PathVariable("id") long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody EmployeeRequest employeeReq) {
        Employee newEmployee = new Employee();
        newEmployee.setId(employeeReq.getId());
        newEmployee.setName(employeeReq.getName());
        newEmployee.setSurName(employeeReq.getSurName());
        newEmployee.setMiddleName(employeeReq.getMiddleName());

        long department_id = employeeReq.getDepartment_id();
        Department department = departmentService.findById(department_id);
        if (department != null && employeeService.findById(employeeReq.getId()) != null) {
            newEmployee.setDepartment(department);
            newEmployee = employeeService.update(newEmployee);
            if (newEmployee != null) {
                return new ResponseEntity<>(newEmployee, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
