package com.rest_jpa.servise;

import com.rest_jpa.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);
    List<Order> findAll();
    Order findById(long id);
    List<Order> findAllByDepartmentName(String department);
    List<Order> findAllByEmployeeId(long id);
    List<Order> findAllUnfinished();
    Order update(Order order);
    void delete(long id);
}
