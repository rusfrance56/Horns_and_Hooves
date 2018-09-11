package com.rest_jpa.utils;

public class JsonConverter {

    /*public static OrderTO convertOrder(CustomerOrder customerOrder) {
        OrderTO orderTO = new OrderTO();
        orderTO.setId(customerOrder.getId());
        orderTO.setName(customerOrder.getName());
        orderTO.setDateTime(customerOrder.getDate());
        orderTO.setDepartment(new DepartmentShortTO(customerOrder.getDepartment().getId(), customerOrder.getDepartment().getName()));

        Person person = customerOrder.getPerson();
        if (person != null) {
            orderTO.setEmployee(new EmployeeShortTO(
                    person.getId(), person.getName(), person.getSurName()));
        } else {
            orderTO.setEmployee(null);
        }
        return orderTO;
    }

    public static List<OrderTO> convertOrder(List<CustomerOrder> customerOrders) {
        return customerOrders.stream()
                .map(JsonConverter::convertOrder)
                .collect(Collectors.toList());
    }

    public static EmployeeTO convertEmployee(Person person) {
        EmployeeTO employeeTO = new EmployeeTO();
        employeeTO.setId(person.getId());
        employeeTO.setName(person.getName());
        employeeTO.setSurName(person.getSurName());
        employeeTO.setMiddleName(person.getMiddleName());
        employeeTO.setDepartment(
                new DepartmentShortTO(person.getDepartment().getId(), person.getDepartment().getName()));
        employeeTO.setOrderIdList(person.getOrderList().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList()));
        return employeeTO;
    }

    public static List<EmployeeTO> convertEmployee(List<Person> people) {
        return people.stream()
                .map(JsonConverter::convertEmployee)
                .collect(Collectors.toList());
    }*/

}
