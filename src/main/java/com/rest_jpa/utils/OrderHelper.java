package com.rest_jpa.utils;

import org.springframework.stereotype.Component;

@Component
public class OrderHelper {

    /*public User findFreeEmployee(List<User> list) {
        Optional<User> freeEmployee = list.stream()
                .min(Comparator.comparingInt((emp -> emp.getOrderList().size())));
        return freeEmployee.get();
    }

    public void changeOrdersStatusToUnassigned(List<CustomerOrder> customerOrderList) {
        customerOrderList.stream().forEach(order -> {
            order.setAssign(false);
            order.setPerson(null);
        });
    }

    public void assignmentOrders(List<CustomerOrder> customerOrders, List<Person> people) {
        if (people.isEmpty()) {
            changeOrdersStatusToUnassigned(customerOrders);
        } else {
            customerOrders.stream().forEach(order -> assignmentOrder(order, people));
        }
    }

    public void assignmentOrder(CustomerOrder customerOrder, List<Person> people) {
        Person freePerson = findFreeEmployee(people);
        freePerson.getOrderList().add(customerOrder);
        customerOrder.setPerson(freePerson);
        customerOrder.setAssign(true);
    }

    public void reassignmentOrders(Person person) {
        List<CustomerOrder> customerOrderList = person.getOrderList();
        if (!customerOrderList.isEmpty()) {
            List<Person> personList = person.getDepartment().getPersonList();
            personList.remove(person);
            assignmentOrders(customerOrderList, personList);
            person.setOrderList(new ArrayList<>());
        }
    }*/
}
