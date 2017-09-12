package com.rest_jpa.entity.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rest_jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderTO extends BaseEntity{
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;
    private Long employeeId;
    @NotNull
    private Long departmentId;

    public OrderTO() {
    }

    public OrderTO(long id, String name, LocalDateTime dateTime, long employeeId, long departmentId) {
        super(id, name);
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
