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

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequest employeeReq) {
        Department department = departmentService.findById(employeeReq.getDepartment_id());
        if (department != null) {
            Employee newEmployee = new Employee();
            newEmployee.setName(employeeReq.getName());
            newEmployee.setSurName(employeeReq.getSurName());
            newEmployee.setMiddleName(employeeReq.getMiddleName());
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
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@RequestBody EmployeeRequest employeeReq) {
        Department department = departmentService.findById(employeeReq.getDepartment_id());
        Employee byId = employeeService.findById(employeeReq.getId());
        if (byId != null && department != null) {
            Employee employee = byId;
            employee.setName(employeeReq.getName());
            employee.setSurName(employeeReq.getSurName());
            employee.setMiddleName(employeeReq.getMiddleName());
            employee.setDepartment(department);
            employee = employeeService.update(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
