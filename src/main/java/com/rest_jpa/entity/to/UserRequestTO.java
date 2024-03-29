package com.rest_jpa.entity.to;

import com.rest_jpa.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestTO {
    private Long id;
    private String logonName;
    private String password;
    private String name;
    private String surname;
    private String department;
    private String address;
    private String email;
    private String phone;
    private List<TaskTO> tasks = new ArrayList<>();
    private List<CustomerOrderResponseTO> orders = new ArrayList<>();

    public UserRequestTO(User user) {
        this.id = user.getId();
        this.logonName = user.getLogonName();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.department = user.getDepartment() != null ? user.getDepartment().name() : null;
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.tasks = user.getTasks().stream().map(TaskTO::new).collect(Collectors.toList());
        this.orders = user.getOrders().stream().map(CustomerOrderResponseTO::new).collect(Collectors.toList());
    }
}
