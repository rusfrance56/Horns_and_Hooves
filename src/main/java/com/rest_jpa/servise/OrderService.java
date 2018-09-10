package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;

import java.util.List;

public interface OrderService {
    CustomerOrder create(CustomerOrder customerOrder);
    List<CustomerOrder> findAll();
    CustomerOrder findById(long id);
    List<CustomerOrder> findAllByDepartmentName(String department);
    List<CustomerOrder> findAllByEmployeeId(long id);
    //List<CustomerOrder> findAllUnfinished();
    CustomerOrder update(CustomerOrder customerOrder);
    void delete(long id);
}
