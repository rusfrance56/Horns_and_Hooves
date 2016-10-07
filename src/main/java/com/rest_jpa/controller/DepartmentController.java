package com.rest_jpa.controller;

import com.rest_jpa.entity.Department;
import com.rest_jpa.servise.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Department>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Department> findById(@PathVariable("id") long id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
