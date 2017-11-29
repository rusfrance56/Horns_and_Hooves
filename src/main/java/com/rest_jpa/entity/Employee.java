package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_jpa.entity.to.EmployeeTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "employee")
public class Employee extends BaseEntity{

    private String surName;

    private String middleName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

    public Employee() {
    }

    public Employee(EmployeeTO to) {
        this.name = to.getName();
        this.surName = to.getSurName();
        this.middleName = to.getMiddleName();
    }

    public Employee(Long id, String name, String surName, String middleName, Department department) {
        super(id, name);
        this.surName = surName;
        this.middleName = middleName;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
