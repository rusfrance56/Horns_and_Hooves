package com.rest_jpa.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_jpa.entity.Role;
import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.UserActiveStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private Long id;
    private final String username;
    private String email;
    @JsonIgnore
    private final String password;
    @JsonIgnore
    private LocalDateTime lastPasswordResetDate;
    private final Collection<GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtUser(Long id,
                   String username,
                   String email,
                   String password,
                   LocalDateTime lastPasswordResetDate,
                   Collection<GrantedAuthority> authorities,
                   boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
        this.enabled = enabled;
    }

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

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static UserDetails create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getUpdated(),
                mapRolesToAuthorities(user.getRoles()),
                user.getStatus().equals(UserActiveStatus.ACTIVE)
        );
    }

    private static Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Set<GrantedAuthority> userAuthorities = roles.stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        Set<GrantedAuthority> userRoles = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName().name()))
                .collect(Collectors.toSet());
        userAuthorities.addAll(userRoles);
        return userAuthorities;
    }
}
