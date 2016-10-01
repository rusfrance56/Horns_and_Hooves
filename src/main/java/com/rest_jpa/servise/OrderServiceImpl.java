package com.rest_jpa.servise;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.repository.OrderRepository;
import com.rest_jpa.utils.OrderHelper;
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

    @Autowired
    private OrderHelper orderHelper;

    @Override
    public Order create(Order order) {
        if (order.getEmployee() == null) {
            List<Employee> employeeList = order.getDepartment().getEmployeeList();
            orderHelper.assignmentOrder(order, employeeList);
        }
        return  orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAllByDepartmentName(String department) {
        return orderRepository.findAllByDepartmentName(department);
    }

    @Override
    public List<Order> findAllByEmployeeId(long id) {
        return orderRepository.findAllByEmployeeId(id);
    }

    @Override
    public Order update(Order order) {
        if (order.getEmployee() == null) {
            List<Employee> employeeList = order.getDepartment().getEmployeeList();
            orderHelper.assignmentOrder(order, employeeList);
        }
        return orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }
}
