package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Person;
import com.rest_jpa.repository.OrderRepository;
import com.rest_jpa.utils.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHelper orderHelper;

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        if (customerOrder.getPerson() == null) {
            List<Person> personList = customerOrder.getDepartment().getPersonList();
            orderHelper.assignmentOrder(customerOrder, personList);
        }
        return orderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public CustomerOrder findById(long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<CustomerOrder> findAllByDepartmentName(String department) {
        return orderRepository.findAllByDepartmentName(department);
    }

    @Override
    public List<CustomerOrder> findAllByEmployeeId(long id) {
        return orderRepository.findAllByEmployeeId(id);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        if (customerOrder.getPerson() == null) {
            List<Person> personList = customerOrder.getDepartment().getPersonList();
            orderHelper.assignmentOrder(customerOrder, personList);
        }
        return orderRepository.save(customerOrder);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
