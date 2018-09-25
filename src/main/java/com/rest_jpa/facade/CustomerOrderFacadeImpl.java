package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.servise.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.CUSTOMER_ORDER_NOT_FOUND;

@Service
public class CustomerOrderFacadeImpl implements CustomerOrderFacade {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Override
    public CustomerOrderTO create(CustomerOrderTO to) {
        CustomerOrder order = new CustomerOrder(to);
        to.setId(customerOrderService.create(order).getId());
        return to;
    }

    @Override
    public void update(CustomerOrderTO to) {
        CustomerOrder order = customerOrderService.findById(to.getId());
        checkNotNull(order, CUSTOMER_ORDER_NOT_FOUND, to.getId());
        order.setName(to.getName());
        order.setDescription(to.getDescription());
        order.setDueDate(to.getDueDate());
        order.setStatus(OrderStatus.valueOf(to.getStatus()));
        order.setItems(to.getItems().stream().map(Item::new).collect(Collectors.toSet()));
    }

    @Override
    public void delete(long id) {
        customerOrderService.delete(id);
    }

    @Override
    public List<CustomerOrderTO> findAll() {
        List<CustomerOrder> all = customerOrderService.findAll();
        return all.stream().map(CustomerOrderTO::new).collect(Collectors.toList());
    }

    @Override
    public CustomerOrderTO findById(long id) {
        CustomerOrder order = customerOrderService.findById(id);
        return new CustomerOrderTO(order);
    }
}
