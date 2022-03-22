package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Person;
import com.rest_jpa.enumTypes.Department;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonTO {
    private Long id;
    private String name;
    private String surname;
    private String department;
    private String address;
    private String email;
    private String phone;
    private List<TaskTO> tasks = new ArrayList<>();
    private List<CustomerOrderTO> orders = new ArrayList<>();

    public PersonTO() {
    }

    public PersonTO(Long id, String name, String surname, Department department,
                    String address, String email, String phone, List<TaskTO> tasks, List<CustomerOrderTO> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department != null ? department.name() : null;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.tasks = tasks;
        this.orders = orders;
    }

    public PersonTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.department = person.getDepartment() != null ? person.getDepartment().name() : null;
        this.address = person.getAddress();
        this.email = person.getEmail();
        this.phone = person.getPhone();
        this.tasks = person.getTasks().stream().map(TaskTO::new).collect(Collectors.toList());
        this.orders = person.getOrders().stream().map(CustomerOrderTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<TaskTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskTO> tasks) {
        this.tasks = tasks;
    }

    public List<CustomerOrderTO> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrderTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "PersonTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tasks=" + tasks +
                ", orders=" + orders +
                '}';
    }
}
