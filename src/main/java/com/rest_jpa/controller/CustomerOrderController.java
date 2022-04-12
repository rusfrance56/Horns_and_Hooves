package com.rest_jpa.controller;

import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderResponseTO;
import com.rest_jpa.facade.CustomerOrderFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class CustomerOrderController {

    private CustomerOrderFacade customerOrderFacade;

    @PostMapping
    public ResponseEntity<CustomerOrderResponseTO> create(@RequestBody CustomerOrderRequestTO to) {
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
    public ResponseEntity<List<CustomerOrderResponseTO>> findAll() {
        return new ResponseEntity<>(customerOrderFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderResponseTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(customerOrderFacade.findById(id), HttpStatus.OK);
    }
}
