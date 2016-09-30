package com.rest_jpa.controller;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.request.CreateOrderRequest;
import com.rest_jpa.servise.EmployeeService;
import com.rest_jpa.servise.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by User on 26.09.2016.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest orderReq) {
        Order newOrder = new Order();
        newOrder.setName(orderReq.getName());
        newOrder.setDate(orderReq.getDate());
        newOrder.setType(orderReq.getType());
        Employee employee = employeeService.findById(orderReq.getEmployee_id());
        newOrder.setEmployee(employee);
        newOrder = orderService.create(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByDepartment(@PathVariable("dep") String department) {
        return new ResponseEntity<>(orderService.findAllByDepartment(department), HttpStatus.OK);
    }

    @RequestMapping(value = "/byEmpId/{emp_id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByEmployeeId(@PathVariable("emp_id") long id) {
        return new ResponseEntity<>(orderService.findAllByEmployeeId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> update(@RequestBody CreateOrderRequest orderReq) {
        Order newOrder = new Order();
        newOrder.setId(orderReq.getId());
        newOrder.setName(orderReq.getName());
        newOrder.setDate(orderReq.getDate());
        newOrder.setType(orderReq.getType());
        Employee employee = employeeService.findById(orderReq.getEmployee_id());
        newOrder.setEmployee(employee);
        newOrder = orderService.create(newOrder);
        if (newOrder == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@PathVariable("id") long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
