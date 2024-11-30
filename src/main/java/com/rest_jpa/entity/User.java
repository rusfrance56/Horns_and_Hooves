package com.rest_jpa.entity;

import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.UserActiveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserActiveStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CustomerOrder> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
