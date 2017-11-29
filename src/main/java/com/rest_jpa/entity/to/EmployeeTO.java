package com.rest_jpa.entity.to;

import com.rest_jpa.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTO extends BaseEntity{
    @NotBlank
    private String surName;
    private String middleName;
    @NotNull
    private DepartmentShortTO department;
    private List<Long> orderIdList = new ArrayList<>();

    public EmployeeTO() {
    }

    public EmployeeTO(String name, String surName, String middleName, DepartmentShortTO department) {
        this(null, name, surName, middleName, department);
    }

    public EmployeeTO(Long id, String name, String surName, String middleName, DepartmentShortTO department) {
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

    public DepartmentShortTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentShortTO department) {
        this.department = department;
    }

    public List<Long> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Long> orderIdList) {
        this.orderIdList = orderIdList;
    }
}