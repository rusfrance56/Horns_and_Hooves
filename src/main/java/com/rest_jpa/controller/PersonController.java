package com.rest_jpa.controller;

import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonFacade personFacade;

    @PostMapping
    public ResponseEntity<PersonTO> create(@Valid @RequestBody PersonTO request) {
        return new ResponseEntity<>(personFacade.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PersonTO request) {
        personFacade.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        personFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonTO>> findAll() {
        return new ResponseEntity<>(personFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(personFacade.findById(id), HttpStatus.OK);
    }
}
