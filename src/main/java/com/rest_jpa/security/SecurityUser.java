package com.rest_jpa.security;

import com.rest_jpa.entity.Role;
import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.UserActiveStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getLogonName(),
                user.getPassword(),
                user.getStatus().equals(UserActiveStatus.ACTIVE),
                user.getStatus().equals(UserActiveStatus.ACTIVE),
                user.getStatus().equals(UserActiveStatus.ACTIVE),
                user.getStatus().equals(UserActiveStatus.ACTIVE),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private static Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Set<GrantedAuthority> userAuthorities = roles.stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        Set<GrantedAuthority> userRoles = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toSet());
        userAuthorities.addAll(userRoles);
        return userAuthorities;
    }
}
