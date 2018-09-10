package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.to.OrderTO;

import java.util.List;

public interface OrderFacade {
    CustomerOrder create(OrderTO to);
    CustomerOrder update(OrderTO to);
    List<CustomerOrder> findAll();
    CustomerOrder findById(long id);
    List<CustomerOrder> findAllByDepartmentName(String department);
    List<CustomerOrder> findAllByEmployeeId(long id);
    void delete(long id);
}
