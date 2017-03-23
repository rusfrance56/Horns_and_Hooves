package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest_jpa.utils.CustomDateDeserializer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "public", name = "order_furniture")
public class Order {
    @Id
    @GeneratedValue(generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq")
    private long id;

    private String name;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String orderName) {
        this.name = orderName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
