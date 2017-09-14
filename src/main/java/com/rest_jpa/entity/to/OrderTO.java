package com.rest_jpa.entity.to;

import com.rest_jpa.entity.BaseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderTO extends BaseEntity{
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    @NotNull
    private LocalDateTime dateTime;
    private EmployeeShortTO employee;
    @NotNull
    private DepartmentShortTO department;

    public OrderTO() {
    }

    public OrderTO(long id, String name, LocalDateTime dateTime, EmployeeShortTO employeeId, DepartmentShortTO department) {
        super(id, name);
        this.dateTime = dateTime;
        this.employee = employeeId;
        this.department = department;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public EmployeeShortTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeShortTO employee) {
        this.employee = employee;
    }

    public DepartmentShortTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentShortTO department) {
        this.department = department;
    }
}
