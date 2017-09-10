package com.rest_jpa.entity.to;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class OrderRequest {
    private long id;
    @NotBlank
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private long employee_id;
    private long department_id;

    public OrderRequest() {
    }

    public OrderRequest(long id, String name, LocalDateTime dateTime, long employee_id, long department_id) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.employee_id = employee_id;
        this.department_id = department_id;
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

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }
}
