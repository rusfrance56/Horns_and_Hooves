package com.rest_jpa.servise;

import com.rest_jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User create(User user);
    List<User> findAll();
    Page<User> findAllWithPagination(int page, int size);
    User findById(long id);
    void update(User user);
    void delete(long id);
}
