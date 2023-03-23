package com.rest_jpa.security;

import com.rest_jpa.entity.User;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.rest_jpa.exceptions.ErrorKey.USER_NOT_FOUND;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String logonName) {
        Optional<User> userOptional = userRepository.findByUserName(logonName);
        User user = userOptional.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, logonName));
        log.info("IN loadUserByUsername - user with logonName: {} successfully loaded", logonName);
        return JwtUser.create(user);
    }
}
