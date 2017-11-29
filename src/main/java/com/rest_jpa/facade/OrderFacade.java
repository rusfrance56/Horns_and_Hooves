package com.rest_jpa.facade;

import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.to.OrderTO;

import java.util.List;

public interface OrderFacade {
    Order create(OrderTO to);
    Order update(OrderTO to);
    List<Order> findAll();
    Order findById(long id);
    List<Order> findAllByDepartmentName(String department);
    List<Order> findAllByEmployeeId(long id);
    void delete(long id);
}
