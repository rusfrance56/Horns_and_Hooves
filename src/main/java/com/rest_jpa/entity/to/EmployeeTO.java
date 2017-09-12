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
    private Long departmentId;
    private List<Long> orderIdList = new ArrayList<>();

    public EmployeeTO() {
    }

    public EmployeeTO(String name, String surName, String middleName, Long departmentId) {
        this(null, name, surName, middleName, departmentId);
    }

    public EmployeeTO(Long id, String name, String surName, String middleName, Long departmentId) {
        super(id, name);
        this.surName = surName;
        this.middleName = middleName;
        this.departmentId = departmentId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Long> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
