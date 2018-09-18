package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Person;
import com.rest_jpa.enumTypes.Department;

import java.time.LocalDateTime;

public class PersonTO {
    private long id;
    private String name;
    private String surname;
    private String middleName;
    private String department;
    private String address;
    private String email;
    private String phone;

    public PersonTO() {
    }

    public PersonTO(long id, String name, String surname, String middleName,
                    Department department, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.department = department.name();
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public PersonTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.middleName = person.getMiddleName();
        this.department = person.getDepartment().name();
        this.address = person.getAddress();
        this.email = person.getEmail();
        this.phone = person.getPhone();
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "EmployeeTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", department=" + department +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
