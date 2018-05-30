package com.rest_jpa.controller;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.exceptions.ErrorKey;
import com.rest_jpa.facade.EmployeeFacade;
import com.rest_jpa.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeFacade employeeFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeTO employeeReq) {
        return Optional.ofNullable(employeeFacade.create(employeeReq))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> update(@Valid @RequestBody EmployeeTO employeeReq) {
        return Optional.ofNullable(employeeFacade.update(employeeReq))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<EmployeeTO>> findAll() throws ApplicationException {
        List<EmployeeTO> employees = JsonConverter.convertEmployee(employeeFacade.findAll());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findById(@PathVariable("id") long id) {
        return Optional.ofNullable(employeeFacade.findById(id))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
