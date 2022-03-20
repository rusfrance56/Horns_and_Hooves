package com.rest_jpa.controller;

import com.rest_jpa.entity.to.TaskTO;
import com.rest_jpa.facade.TaskFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskFacade taskFacade;

    public TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping
    public ResponseEntity<TaskTO> create(@RequestBody TaskTO to) {
        return new ResponseEntity<>(taskFacade.create(to), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TaskTO to) {
        taskFacade.update(to);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        taskFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskTO>> findAll() {
        return new ResponseEntity<>(taskFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(taskFacade.findById(id), HttpStatus.OK);
    }
}
