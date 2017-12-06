package com.rest_jpa.facade;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.to.EmployeeShortTO;
import com.rest_jpa.entity.to.OrderTO;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import com.rest_jpa.servise.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderFacadeImpl implements OrderFacade{

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Order create(OrderTO to) {

        Department department = departmentService.findById(to.getDepartment().getId());
        if (department != null) {
            Order newOrder = new Order();
            newOrder.setName(to.getName());

            ZonedDateTime zdt = to.getDateTime().atZone(ZoneOffset.UTC);
            LocalDateTime creationDate = zdt.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

            newOrder.setDate(creationDate);
            newOrder.setDepartment(department);

            Employee employee = Optional.ofNullable(to.getEmployee())
                    .map(EmployeeShortTO::getId)
                    .map(employeeService::findById).orElse(null);

            newOrder.setEmployee(employee);
            newOrder.setAssign(Optional.ofNullable(employee).isPresent());
            return orderService.create(newOrder);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @Override
    public Order findById(long id) {
        return orderService.findById(id);
    }

    @Override
    public List<Order> findAllByDepartmentName(String department) {
        return orderService.findAllByDepartmentName(department);
    }

    @Override
    public List<Order> findAllByEmployeeId(long id) {
        return orderService.findAllByEmployeeId(id);
    }

    @Override
    public Order update(OrderTO to) {
        Order newOrder = orderService.findById(to.getId());
        Department department = departmentService.findById(to.getDepartment().getId());
        if (newOrder != null && department != null) {
            newOrder.setName(to.getName());

            ZonedDateTime zdt = to.getDateTime().atZone(ZoneOffset.UTC);
            LocalDateTime creationDate = zdt.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

            newOrder.setDate(creationDate);
            newOrder.setDepartment(department);

            Employee employee = employeeService.findById(to.getEmployee().getId());
            newOrder.setEmployee(employee);
            newOrder.setAssign(Optional.ofNullable(employee).isPresent());
            return orderService.update(newOrder);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        orderService.delete(id);
    }
}
