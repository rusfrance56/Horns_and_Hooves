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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.USERS_NOT_FOUND;
import static com.rest_jpa.exceptions.ErrorKey.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

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
//        throw new ApplicationException(USERS_NOT_FOUND);
//        throw new ApplicationException(USERS_NOT_FOUND, 55);
        return checkNotNullAndNotEmpty(userRepository.findAll(), USERS_NOT_FOUND);
    }

    @Override
    public User findById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String logonName) {
        Optional<User> userOptional = userRepository.findByLogonName(logonName);
        User user = userOptional.orElseThrow(() -> new ApplicationException(USER_NOT_FOUND, logonName));
        return new org.springframework.security.core.userdetails.User(user.getLogonName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
