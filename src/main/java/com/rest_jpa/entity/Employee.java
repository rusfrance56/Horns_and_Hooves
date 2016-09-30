package com.rest_jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Employee on 24.09.2016.
 */
@Entity
@Table(schema = "public", name = "employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "User_id_seq")
    @SequenceGenerator(name = "User_id_seq", sequenceName = "User_id_seq")
    private long id;

    private String name;

    private String surName;

    private String middleName;

    private String department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surname) {
        this.surName = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middlename) {
        this.middleName = middlename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
