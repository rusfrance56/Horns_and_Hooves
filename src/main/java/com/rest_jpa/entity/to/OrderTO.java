package com.rest_jpa.entity.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rest_jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderTO extends BaseEntity{
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date dateTime;
    private Long employeeId;
    @NotNull
    private DepartmentShortTO department;

    public OrderTO() {
    }

    public OrderTO(long id, String name, Date dateTime, long employeeId, DepartmentShortTO department) {
        super(id, name);
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.department = department;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public DepartmentShortTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentShortTO department) {
        this.department = department;
    }
}
