package com.rest_jpa.entity.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUserTO {
    private Long id;
    private String userName;
    private Department department;
    private Set<RoleTO> userRoles;

    public AuthUserTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.department = user.getDepartment() != null ? user.getDepartment() : null;
        this.userRoles = user.getRoles().stream().map(RoleTO::new).collect(Collectors.toSet());
    }
}
