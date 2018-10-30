package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerOrderService {
    CustomerOrder create(CustomerOrder order);
    void update(CustomerOrder order);
    void delete(long id);
    List<CustomerOrder> findAll();
    CustomerOrder findById(long id);
    List<CustomerOrder> findAllByIds(List<Long> orderIds);
}
