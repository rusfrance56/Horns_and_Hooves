package com.rest_jpa.facade;

import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderResponseTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerOrderFacade {
    CustomerOrderResponseTO create(CustomerOrderRequestTO to);
    void update(CustomerOrderRequestTO to);
    void delete(long id);
    List<CustomerOrderResponseTO> findAll();
    CustomerOrderResponseTO findById(long id);
}
