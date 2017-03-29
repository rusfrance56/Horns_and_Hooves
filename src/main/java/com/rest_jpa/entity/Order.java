package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "order_furniture")
public class Order extends BaseEntity{

    /*@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")*/
    //@JsonDeserialize(using = CustomDateDeserializer.class)
    @Type(type="com.rest_jpa.utils.LocalDateTimeUserType")
    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @Column(name = "isAssigned", nullable = false, columnDefinition = "bool default false")
    private boolean isAssigned = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Order() {
    }

    public Order(String name, LocalDateTime dateTime, boolean isAssigned, Employee employee, Department department) {
        this(null, name, dateTime, isAssigned, employee, department);
    }

    public Order(Long id, String name, LocalDateTime dateTime, boolean isAssigned, Employee employee, Department department) {
        super(id, name);
        this.dateTime = dateTime;
        this.isAssigned = isAssigned;
        this.employee = employee;
        this.department = department;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = date;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssign(boolean orderStatus) {
        this.isAssigned = orderStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
