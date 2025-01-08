package com.rest_jpa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthenticationProvider {
    Optional<Authentication> getAuthentication();
    Optional<UserDetails> getUserDetails();
    Optional<com.rest_jpa.entity.User> getUser();
}
