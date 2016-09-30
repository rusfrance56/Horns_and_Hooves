package com.rest_jpa.servise;

import com.rest_jpa.entity.Department;
import com.rest_jpa.entity.Employee;
import com.rest_jpa.entity.Order;
import com.rest_jpa.repository.DepartmentRepository;
import com.rest_jpa.repository.EmployeeRepository;
import com.rest_jpa.repository.OrderRepository;
import com.rest_jpa.utils.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Employee on 24.09.2016.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = employeeRepository.findOne(id);
        reassignmentOrders(employee);
        employeeRepository.delete(id);
    }

    private void reassignmentOrders(Employee employee) {
        List<Order> orderList = employee.getOrderList();
        if (orderList.isEmpty()) {
            return;
        }
        Department currentDepartment = departmentRepository.findOne(employee.getDepartment().getId());
        List<Employee> allByDepartment = currentDepartment.getEmployeeList();
        allByDepartment.remove(employee);

        if (allByDepartment.isEmpty()) {
            OrderHelper.changeOrdersStatusToUnassigned(orderRepository, orderList);
        } else {
            for (Order order : orderList) {
                Employee freeEmployee = OrderHelper.findFreeEmployee(allByDepartment);
                freeEmployee.getOrderList().add(order);
                order.setEmployee(freeEmployee);
                orderRepository.save(order);
            }
        }
    }
}
