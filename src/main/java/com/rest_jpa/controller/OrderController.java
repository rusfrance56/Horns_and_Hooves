package com.rest_jpa.controller;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.request.OrderRequest;
import com.rest_jpa.entity.response.OrderResponse;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import com.rest_jpa.servise.OrderService;
import com.rest_jpa.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> create(@RequestBody OrderRequest orderReq) {
        Department department = departmentService.findById(orderReq.getDepartment_id());
        if (department != null) {
            Order newOrder = new Order();
            newOrder.setName(orderReq.getName());
            newOrder.setDate(orderReq.getDate());
            newOrder.setDepartment(department);

            Employee employee = employeeService.findById(orderReq.getEmployee_id());
            newOrder.setEmployee(employee);
            if (employee != null) {
                newOrder.setAssign(true);
            } else {
                newOrder.setAssign(false);
            }
            newOrder = orderService.create(newOrder);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<OrderResponse>> findAll() {
        List<Order> all = orderService.findAll();
        List<OrderResponse> responseList = JsonConverter.convert(all);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> findById(@PathVariable("id") long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/byDep/{dep}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByDepartmentName(@PathVariable("dep") String department) {
        return new ResponseEntity<>(orderService.findAllByDepartmentName(department), HttpStatus.OK);
    }

    @RequestMapping(value = "/byEmpId/{emp_id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllByEmployeeId(@PathVariable("emp_id") long id) {
        return new ResponseEntity<>(orderService.findAllByEmployeeId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/unfinished", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> findAllUnfinished() {
        return new ResponseEntity<>(orderService.findAllUnfinished(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> update(@RequestBody OrderRequest orderReq) {
        Order newOrder = orderService.findById(orderReq.getId());
        Department department = departmentService.findById(orderReq.getDepartment_id());
        if (newOrder != null && department != null) {
            newOrder.setName(orderReq.getName());
            newOrder.setDate(orderReq.getDate());
            newOrder.setDepartment(department);

            Employee employee = employeeService.findById(orderReq.getEmployee_id());
            newOrder.setEmployee(employee);
            if (employee != null) {
                newOrder.setAssign(true);
            } else {
                newOrder.setAssign(false);
            }
            newOrder = orderService.update(newOrder);
            if (newOrder != null) {
                return new ResponseEntity<>(newOrder, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@PathVariable("id") long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
