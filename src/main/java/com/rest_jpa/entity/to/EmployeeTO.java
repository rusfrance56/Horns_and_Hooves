package com.rest_jpa.entity.to;

import com.rest_jpa.entity.BaseEntity;
import com.rest_jpa.enumTypes.Department;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTO extends BaseEntity{
    @NotBlank
    private String surName;
    private String middleName;
    @NotNull
    private Department department;
    private List<Long> orderIdList = new ArrayList<>();

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

    public List<Long> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Long> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
