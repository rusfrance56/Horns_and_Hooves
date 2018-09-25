package com.rest_jpa.entity;

import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.enumTypes.Department;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<CustomerOrder> orders;

    public Person() {
    }

    public Person(PersonTO to) {
        this.name = to.getName();
        this.surname = to.getSurname();
        this.middleName = to.getMiddleName();
        this.department = Department.valueOf(to.getDepartment());
        this.address = to.getAddress();
        this.email = to.getEmail();
        this.phone = to.getPhone();
        this.tasks = to.getTasks().stream().map(Task::new).collect(Collectors.toList());
        this.orders = to.getOrders().stream().map(CustomerOrder::new).collect(Collectors.toList());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<CustomerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", department=" + department +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tasks=" + tasks +
                ", orders=" + orders +
                ", id=" + id +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
