package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.CustomerOrderRequestTO;
import com.rest_jpa.entity.to.CustomerOrderResponseTO;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.servise.CustomerOrderService;
import com.rest_jpa.servise.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
@AllArgsConstructor
public class CustomerOrderFacadeImpl implements CustomerOrderFacade {

    private CustomerOrderService customerOrderService;
    private ItemService itemService;

    @Override
    public CustomerOrderResponseTO create(CustomerOrderRequestTO to) {
        CustomerOrder order = new CustomerOrder();
        setOrderParametersFromTO(order, to);
        CustomerOrderResponseTO customerOrderResponseTO = new CustomerOrderResponseTO(order);
        CustomerOrder newCustomerOrder = customerOrderService.create(order);

        customerOrderResponseTO.setId(newCustomerOrder.getId());
        return customerOrderResponseTO;
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
    public List<CustomerOrderResponseTO> findAll() {
        List<CustomerOrder> all = customerOrderService.findAll();
        return all.stream().map(CustomerOrderResponseTO::new).collect(Collectors.toList());
    }

    @Override
    public CustomerOrderResponseTO findById(long id) {
        CustomerOrder order = customerOrderService.findById(id);
        return new CustomerOrderResponseTO(order);
    }

    private void setOrderParametersFromTO(CustomerOrder order, CustomerOrderRequestTO to) {
        order.setId(to.getId());
        order.setName(to.getName());
        order.setDescription(to.getDescription());
        order.setDueDate(to.getDueDate());
        order.setStatus(to.getStatus() != null ? OrderStatus.valueOf(to.getStatus()) : null);
        List<Item> basicItems = itemService.findAllByIds(to.getItems());
        List<Item> newOrderItems = new ArrayList<>();
        for (Long toId : to.getItems()) {
            Optional<Item> foundedItem = basicItems.stream().filter(item -> Objects.equals(item.getId(), toId)).findFirst();
            foundedItem.ifPresent(newOrderItems::add);
        }
        order.setItems(newOrderItems);
    }
}
