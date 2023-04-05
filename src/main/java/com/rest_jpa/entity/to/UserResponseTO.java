package com.rest_jpa.entity.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseTO {
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private Department department;
    private String address;
    private String email;
    private String phone;
    private LocalDateTime updated;
    private List<TaskTO> tasks = new ArrayList<>();
    private List<CustomerOrderResponseTO> orders = new ArrayList<>();

    public UserResponseTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.department = user.getDepartment() != null ? user.getDepartment() : null;
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.updated = user.getUpdated();
        this.tasks = user.getTasks().stream().map(TaskTO::new).collect(Collectors.toList());
        this.orders = user.getOrders().stream().map(CustomerOrderResponseTO::new).collect(Collectors.toList());
    }
}
