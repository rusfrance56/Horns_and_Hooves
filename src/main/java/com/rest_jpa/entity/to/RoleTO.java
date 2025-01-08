package com.rest_jpa.entity.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_jpa.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleTO {
    private String name;

    public RoleTO(Role role) {
        this.name = role.getName().name();
    }
}
