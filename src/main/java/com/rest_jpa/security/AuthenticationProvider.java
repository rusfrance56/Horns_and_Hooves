package com.rest_jpa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthenticationProvider {
    Optional<Authentication> getAuthentication();
    Optional<User> getUserDetails();
    Optional<com.rest_jpa.entity.User> getUser();
}
