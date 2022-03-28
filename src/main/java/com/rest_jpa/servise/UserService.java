package com.rest_jpa.servise;

import com.rest_jpa.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User create(User user);
    List<User> findAll();
    User findById(long id);
    void update(User user);
    void delete(long id);
}
