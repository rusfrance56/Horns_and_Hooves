package com.rest_jpa.controller;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.facade.EmployeeFacade;
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
    private EmployeeFacade employeeFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeTO employeeReq) {
        Employee employee = employeeFacade.create(employeeReq);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@Valid @RequestBody EmployeeTO employeeReq) {
        Employee employee = employeeFacade.update(employeeReq);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<EmployeeTO>> findAll() {
        List<EmployeeTO> employees = JsonConverter.convertEmployee(employeeFacade.findAll());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findById(@PathVariable("id") long id) {
        Employee employee = employeeFacade.findById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Employee>> findAllByDepartment(@PathVariable("dep") long id) {
        return new ResponseEntity<>(employeeFacade.findAllByDepartmentId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> delete(@PathVariable("id") long id) {
        employeeFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
