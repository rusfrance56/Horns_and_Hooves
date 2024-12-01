package com.rest_jpa.entity.to;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String userName;
    private String password;
}
