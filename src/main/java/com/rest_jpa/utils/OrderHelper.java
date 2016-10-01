package com.rest_jpa.utils;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.repository.DepartmentRepository;
import com.rest_jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by User on 30.09.2016.
 */
@Component
public class OrderHelper {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee findFreeEmployee(List<Employee> list) {
        Employee freeEmp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getOrderList().size() < freeEmp.getOrderList().size()) {
                freeEmp = list.get(i);
            }
        }
        return freeEmp;
    }

    public void changeOrdersStatusToUnassigned(List<Order> orderList) {
        for (Order order : orderList) {
            order.setOrder_status(OrderStatus.unassigned);
            orderRepository.save(order);
        }
    }

    public void assignmentOrders(List<Order> orders, List<Employee> employees) {
        if (employees.isEmpty()) {
            changeOrdersStatusToUnassigned(orders);
        } else {
            for (Order order : orders) {
                assignmentOrder(order, employees);
            }
        }
    }

    public void assignmentOrder(Order order, List<Employee> employees) {
        Employee freeEmployee = findFreeEmployee(employees);
        freeEmployee.getOrderList().add(order);
        order.setEmployee(freeEmployee);
        order.setOrder_status(OrderStatus.assigned);
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
