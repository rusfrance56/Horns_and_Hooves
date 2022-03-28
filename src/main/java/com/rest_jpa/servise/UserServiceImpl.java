package com.rest_jpa.servise;

import com.rest_jpa.entity.User;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.USERS_NOT_FOUND;
import static com.rest_jpa.exceptions.ErrorKey.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

}
