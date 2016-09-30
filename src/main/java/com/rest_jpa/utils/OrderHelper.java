package com.rest_jpa.utils;

import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.repository.OrderRepository;

import java.util.List;

/**
 * Created by User on 30.09.2016.
 */
public class OrderHelper {
    public static Employee findFreeEmployee(List<Employee> list) {
        Employee freeEmp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getOrderList().size() < freeEmp.getOrderList().size()) {
                freeEmp = list.get(i);
            }
        }
        return freeEmp;
    }

    public static void changeOrdersStatusToUnassigned(OrderRepository orderRepository, List<Order> orderList) {
        for (Order order : orderList) {
            order.setStatus(OrderStatus.unassigned);
            orderRepository.save(order);
        }
    }
}
