package com.rest_jpa.controller;

import com.rest_jpa.entity.to.UserRequestTO;
import com.rest_jpa.entity.to.UserResponseTO;
import com.rest_jpa.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<UserResponseTO> create(@RequestBody UserRequestTO request) {
        return new ResponseEntity<>(userFacade.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRequestTO request) {
        userFacade.update(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        userFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseTO>> findAll() {
        return new ResponseEntity<>(userFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<UserResponseTO>> findAllWithPagination(@RequestParam int page, @RequestParam int size) {
        Page<UserResponseTO> usersTOPage = userFacade.findAllWithPagination(page, size);
        return new ResponseEntity<>(usersTOPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userFacade.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byDepartment")
    public ResponseEntity<List<UserResponseTO>> findByDepartment(@RequestParam(value = "department") String department) {
        return new ResponseEntity<>(userFacade.findByDepartment(department), HttpStatus.OK);
    }
}
