package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.servise.CustomerOrderService;
import com.rest_jpa.servise.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
public class CustomerOrderFacadeImpl implements CustomerOrderFacade {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private ItemService itemService;

    @Override
    public CustomerOrderTO create(CustomerOrderRequestTO to) {
        CustomerOrder order = new CustomerOrder();
        setOrderParametersFromTO(order, to);
        CustomerOrderTO customerOrderTO = new CustomerOrderTO(order);
        CustomerOrder newCustomerOrder = customerOrderService.create(order);

        customerOrderTO.setId(newCustomerOrder.getId());
        return customerOrderTO;
    }

    @Override
    public void update(CustomerOrderRequestTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        CustomerOrder order = customerOrderService.findById(to.getId());
        setOrderParametersFromTO(order, to);
        customerOrderService.update(order);
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

    private void setOrderParametersFromTO(CustomerOrder order, CustomerOrderRequestTO to) {
        order.setId(to.getId());
        order.setName(to.getName());
        order.setDescription(to.getDescription());
        order.setDueDate(to.getDueDate());
        order.setStatus(to.getStatus() != null ? OrderStatus.valueOf(to.getStatus()) : null);
        order.setItems(itemService.findAllByIds(to.getItems()));
    }
}
