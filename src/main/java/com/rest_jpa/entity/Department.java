package com.rest_jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "department")
public class Department {
    @Id
    @GeneratedValue(generator = "department_id_seq")
    @SequenceGenerator(name = "department_id_seq", sequenceName = "department_id_seq")
    private long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
