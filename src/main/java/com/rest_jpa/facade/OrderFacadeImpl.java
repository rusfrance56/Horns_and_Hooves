package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Person;
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
    public CustomerOrder create(OrderTO to) {

        Department department = departmentService.findById(to.getDepartment().getId());
        if (department != null) {
            CustomerOrder newCustomerOrder = new CustomerOrder();
            newCustomerOrder.setName(to.getName());

            ZonedDateTime zdt = to.getDateTime().atZone(ZoneOffset.UTC);
            LocalDateTime creationDate = zdt.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

            newCustomerOrder.setDate(creationDate);
            newCustomerOrder.setDepartment(department);

            Person person = Optional.ofNullable(to.getEmployee())
                    .map(EmployeeShortTO::getId)
                    .map(employeeService::findById).orElse(null);

            newCustomerOrder.setPerson(person);
            newCustomerOrder.setAssign(Optional.ofNullable(person).isPresent());
            return orderService.create(newCustomerOrder);
        }
        return null;
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderService.findAll();
    }

    @Override
    public CustomerOrder findById(long id) {
        return orderService.findById(id);
    }

    @Override
    public List<CustomerOrder> findAllByDepartmentName(String department) {
        return orderService.findAllByDepartmentName(department);
    }

    @Override
    public List<CustomerOrder> findAllByEmployeeId(long id) {
        return orderService.findAllByEmployeeId(id);
    }

    @Override
    public CustomerOrder update(OrderTO to) {
        CustomerOrder newCustomerOrder = orderService.findById(to.getId());
        Department department = departmentService.findById(to.getDepartment().getId());
        if (newCustomerOrder != null && department != null) {
            newCustomerOrder.setName(to.getName());

            ZonedDateTime zdt = to.getDateTime().atZone(ZoneOffset.UTC);
            LocalDateTime creationDate = zdt.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

            newCustomerOrder.setDate(creationDate);
            newCustomerOrder.setDepartment(department);

            Person person = employeeService.findById(to.getEmployee().getId());
            newCustomerOrder.setPerson(person);
            newCustomerOrder.setAssign(Optional.ofNullable(person).isPresent());
            return orderService.update(newCustomerOrder);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        orderService.delete(id);
    }
}
