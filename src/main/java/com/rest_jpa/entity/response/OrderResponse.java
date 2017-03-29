package com.rest_jpa.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class OrderResponse {
    private long id;
    private String name;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;
    private String employee;
    private String department;

    public OrderResponse() {
    }

    public OrderResponse(long id, String name, LocalDateTime date, String employee, String department) {
        this.id = id;
        this.name = name;
        this.dateTime = date;
        this.employee = employee;
        this.department = department;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
