package com.rest_jpa.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest_jpa.utils.CustomDateDeserializer;

import java.util.Date;

public class OrderResponse {
    private long id;
    private String name;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;
    private String employee;
    private String department;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
