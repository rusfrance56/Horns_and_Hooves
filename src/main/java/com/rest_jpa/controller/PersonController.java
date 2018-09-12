package com.rest_jpa.controller;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonFacade personFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> create(@Valid @RequestBody PersonTO employeeReq) {
        return Optional.ofNullable(personFacade.create(employeeReq))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> update(@Valid @RequestBody PersonTO employeeReq) {
        return Optional.ofNullable(personFacade.update(employeeReq))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PersonTO>> findAll() throws ApplicationException {
        return new ResponseEntity<>(personFacade.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> findById(@PathVariable("id") long id) {
        return Optional.ofNullable(personFacade.findById(id))
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*@RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Person>> findAllByDepartment(@PathVariable("dep") long id) {
        return new ResponseEntity<>(personFacade.findAllByDepartmentId(id), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> delete(@PathVariable("id") long id) {
        personFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
