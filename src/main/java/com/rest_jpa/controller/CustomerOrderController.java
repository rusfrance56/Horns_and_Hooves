package com.rest_jpa.controller;

import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.facade.CustomerOrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderFacade customerOrderFacade;

    @PostMapping
    public ResponseEntity<CustomerOrderTO> create(@RequestBody CustomerOrderRequestTO to) {
        return new ResponseEntity<>(customerOrderFacade.create(to), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody CustomerOrderRequestTO to) {
        customerOrderFacade.update(to);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        customerOrderFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrderTO>> findAll() {
        return new ResponseEntity<>(customerOrderFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(customerOrderFacade.findById(id), HttpStatus.OK);
    }
}
