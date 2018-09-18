package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.CUSTOMER_ORDERS_NOT_FOUND;
import static com.rest_jpa.exceptions.ErrorKey.CUSTOMER_ORDER_NOT_FOUND;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder create(CustomerOrder order) {
        return customerOrderRepository.save(order);
    }

    @Override
    public void update(CustomerOrder order) {
        customerOrderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        customerOrderRepository.deleteById(id);
    }

    @Override
    public List<CustomerOrder> findAll() {
        return checkNotNullAndNotEmpty(customerOrderRepository.findAll(), CUSTOMER_ORDERS_NOT_FOUND);
    }

    @Override
    public CustomerOrder findById(long id) {
        Optional<CustomerOrder> order = customerOrderRepository.findById(id);
        return order.orElseThrow(() -> new ApplicationException(CUSTOMER_ORDER_NOT_FOUND, id));
    }
}
