package com.rest_jpa.utils;

import com.rest_jpa.entity.BaseEntity;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.to.DepartmentShortTO;
import com.rest_jpa.entity.to.EmployeeShortTO;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.entity.to.OrderTO;

import java.util.List;
import java.util.stream.Collectors;

public class JsonConverter {

    public static OrderTO convertOrder(Order order) {
        OrderTO orderTO = new OrderTO();
        orderTO.setId(order.getId());
        orderTO.setName(order.getName());
        orderTO.setDateTime(order.getDate());
        orderTO.setDepartment(new DepartmentShortTO(order.getDepartment().getId(), order.getDepartment().getName()));

        Employee employee = order.getEmployee();
        if (employee != null) {
            orderTO.setEmployee(new EmployeeShortTO(
                    employee.getId(), employee.getName(), employee.getSurName()));
        } else {
            orderTO.setEmployee(null);
        }
        return orderTO;
    }

    public static List<OrderTO> convertOrder(List<Order> orders) {
        return orders.stream()
                .map(JsonConverter::convertOrder)
                .collect(Collectors.toList());
    }

    public static EmployeeTO convertEmployee(Employee employee) {
        EmployeeTO employeeTO = new EmployeeTO();
        employeeTO.setId(employee.getId());
        employeeTO.setName(employee.getName());
        employeeTO.setSurName(employee.getSurName());
        employeeTO.setMiddleName(employee.getMiddleName());
        employeeTO.setDepartment(
                new DepartmentShortTO(employee.getDepartment().getId(), employee.getDepartment().getName()));
        employeeTO.setOrderIdList(employee.getOrderList().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList()));
        return employeeTO;
    }

    public static List<EmployeeTO> convertEmployee(List<Employee> employees) {
        return employees.stream()
                .map(JsonConverter::convertEmployee)
                .collect(Collectors.toList());
    }

}
