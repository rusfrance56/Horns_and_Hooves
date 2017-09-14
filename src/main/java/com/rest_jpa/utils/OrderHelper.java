package com.rest_jpa.utils;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.repository.DepartmentRepository;
import com.rest_jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class OrderHelper {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee findFreeEmployee(List<Employee> list) {
        Optional<Employee> freeEmployee = list.stream()
                .min(Comparator.comparingInt((emp -> emp.getOrderList().size())));
        return freeEmployee.get();
    }

    public void changeOrdersStatusToUnassigned(List<Order> orderList) {
        orderList.stream().forEach(order -> {
            order.setAssign(false);
            orderRepository.save(order);
        });
    }

    public void assignmentOrders(List<Order> orders, List<Employee> employees) {
        if (employees.isEmpty()) {
            changeOrdersStatusToUnassigned(orders);
        } else {
            orders.stream().forEach(order -> assignmentOrder(order, employees));
        }
    }

    public void assignmentOrder(Order order, List<Employee> employees) {
        Employee freeEmployee = findFreeEmployee(employees);
        freeEmployee.getOrderList().add(order);
        order.setEmployee(freeEmployee);
        order.setAssign(true);
    }

    public void reassignmentOrders(Employee employee) {
        List<Order> orderList = employee.getOrderList();
        if (orderList.isEmpty()) {
            return;
        }
        Department currentDepartment = departmentRepository.findOne(employee.getDepartment().getId());
        List<Employee> allByDepartment = currentDepartment.getEmployeeList();
        allByDepartment.remove(employee);
        assignmentOrders(orderList, allByDepartment);
    }
}
