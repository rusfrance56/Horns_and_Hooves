package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.enumTypes.Department;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "person")
public class Person extends BaseEntity{

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    @JsonIgnore
    private List<CustomerOrder> orderList = new ArrayList<>();*/

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", department=" + department +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
