package com.rest_jpa.controller;

import com.rest_jpa.entity.to.UserTO;
import com.rest_jpa.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserTO> create(@RequestBody UserTO request) {
        return new ResponseEntity<>(userFacade.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserTO request) {
        userFacade.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        userFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserTO>> findAll() {
        return new ResponseEntity<>(userFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userFacade.findById(id), HttpStatus.OK);
    }
}
