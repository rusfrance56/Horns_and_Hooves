package com.rest_jpa.controller;

import com.rest_jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private DaoAuthenticationProvider provider;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogonName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication authenticate = provider.authenticate(createSuccessfulAuthentication(authentication, userDetails));
        return user.getLogonName().equals("user1") && user.getPassword().equals("1");
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
