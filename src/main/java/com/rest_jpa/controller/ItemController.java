package com.rest_jpa.controller;

import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.facade.ItemFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private ItemFacade itemFacade;

    @PostMapping
    public ResponseEntity<ItemTO> create(@RequestBody ItemTO to) {
        return new ResponseEntity<>(itemFacade.create(to), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemTO to) {
        itemFacade.update(to);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        itemFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemTO>> findAll() {
        return new ResponseEntity<>(itemFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(itemFacade.findById(id), HttpStatus.OK);
    }
}
