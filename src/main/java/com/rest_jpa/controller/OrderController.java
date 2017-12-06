package com.rest_jpa.controller;

import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.to.OrderTO;
import com.rest_jpa.facade.OrderFacade;
import com.rest_jpa.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> create(@Valid @RequestBody OrderTO orderReq) {
        return Optional.ofNullable(orderFacade.create(orderReq))
                .map(order -> new ResponseEntity<>(order, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<OrderTO>> findAll() {
        List<OrderTO> responseList = JsonConverter.convertOrder(orderFacade.findAll());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderTO> findById(@PathVariable("id") long id) {
        return Optional.ofNullable(orderFacade.findById(id))
                .map(order -> new ResponseEntity<>(JsonConverter.convertOrder(order), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByDepartmentName(@PathVariable("dep") String department) {
        return new ResponseEntity<>(orderFacade.findAllByDepartmentName(department), HttpStatus.OK);
    }

    @RequestMapping(value = "/byEmpId/{emp_id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByEmployeeId(@PathVariable("emp_id") long id) {
        return new ResponseEntity<>(orderFacade.findAllByEmployeeId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> update(@Valid @RequestBody OrderTO orderReq) {
        return Optional.ofNullable(orderFacade.update(orderReq))
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@PathVariable("id") long id) {
        orderFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
