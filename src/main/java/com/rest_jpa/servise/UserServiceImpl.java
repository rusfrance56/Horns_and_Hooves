package com.rest_jpa.servise;

import com.rest_jpa.entity.Role;
import com.rest_jpa.entity.User;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.ERole;
import com.rest_jpa.enumTypes.UserActiveStatus;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.RoleRepository;
import com.rest_jpa.repository.UserRepository;
import com.rest_jpa.security.AuthenticationProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private AuthenticationProvider authenticationProvider;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = findById(id);
        user.getOrders().forEach(order -> order.setUser(null));
        user.getTasks().forEach(task -> task.setUser(null));
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return checkNotNullAndNotEmpty(userRepository.findAll(), USERS_NOT_FOUND);
    }

    @Override
    public Page<User> findAllWithPagination(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        checkNotNullAndNotEmpty(users.getContent(), USERS_NOT_FOUND);
        return users;
    }

    @Override
    public User findById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, id));
    }

    @Override
    public User register(User user) {
        Optional<Role> roleUserOpt = roleRepository.findByName(ERole.ROLE_USER);
        Role roleUser = roleUserOpt.orElseThrow(() -> new ApplicationException(ROLE_NOT_FOUND, ERole.ROLE_USER));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserActiveStatus.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User findByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, userName));
    }

    @Override
    public List<User> findByDepartment(Department department) {
        return Optional.of(userRepository.findAllByDepartment(department))
                .orElse(Collections.emptyList());
    }

    @Override
    public User getCurrentUser() {
        Optional<User> user = authenticationProvider.getUser();
        return user.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND));
    }
}
