package com.rest_jpa.servise;

import com.rest_jpa.entity.Order;
import com.rest_jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 26.09.2016.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return  orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByDepartment(String department) {
        return orderRepository.findAllByDepartment(department);
    }

    @Override
    public List<Order> findAllByEmployeeId(long id) {
        return orderRepository.findAllByEmployeeId(id);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }
}
