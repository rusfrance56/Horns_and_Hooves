package com.rest_jpa.controller;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import com.rest_jpa.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeTO employeeReq) {
        Department department = departmentService.findById(employeeReq.getDepartment().getId());
        if (department != null) {
            Employee newEmployee = new Employee(employeeReq.getName(),
                    employeeReq.getSurName(), employeeReq.getMiddleName(), department);
            newEmployee = employeeService.create(newEmployee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@Valid @RequestBody EmployeeTO employeeReq) {
        Department department = departmentService.findById(employeeReq.getDepartment().getId());
        Employee employee = employeeService.findById(employeeReq.getId());
        if (employee != null && department != null) {
            employee.setName(employeeReq.getName());
            employee.setSurName(employeeReq.getSurName());
            employee.setMiddleName(employeeReq.getMiddleName());
            employee.setDepartment(department);
            employee = employeeService.update(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<EmployeeTO>> findAll() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeTO> employeeTOList = JsonConverter.convertEmployee(employees);
        return new ResponseEntity<>(employeeTOList, HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Employee>> findAll(Pageable pageable) {
        return new ResponseEntity<>(employeeService.listAllByPage(pageable), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findById(@PathVariable("id") long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Employee>> findAllByDepartment(@PathVariable("dep") long id) {
        return new ResponseEntity<>(employeeService.findAllByDepartmentId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
