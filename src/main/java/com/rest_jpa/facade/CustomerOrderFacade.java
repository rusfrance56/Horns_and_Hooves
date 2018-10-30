package com.rest_jpa.facade;

import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerOrderFacade {
    CustomerOrderTO create(CustomerOrderRequestTO to);
    void update(CustomerOrderRequestTO to);
    void delete(long id);
    List<CustomerOrderTO> findAll();
    CustomerOrderTO findById(long id);
}
