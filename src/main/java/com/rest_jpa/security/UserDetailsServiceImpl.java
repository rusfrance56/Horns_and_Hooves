package com.rest_jpa.security;

import com.rest_jpa.entity.User;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        return SecurityUser.fromUser(user);
    }
}
