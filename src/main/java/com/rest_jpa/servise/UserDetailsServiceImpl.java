package com.rest_jpa.servise;

import com.rest_jpa.entity.Role;
import com.rest_jpa.entity.User;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ErrorKey.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String logonName) {
        Optional<User> userOptional = userRepository.findByLogonName(logonName);
        User user = userOptional.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, logonName));
        return new org.springframework.security.core.userdetails.User(user.getLogonName(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
