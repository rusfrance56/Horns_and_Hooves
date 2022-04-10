package com.rest_jpa.facade;

import com.rest_jpa.entity.to.UserRequestTO;
import com.rest_jpa.entity.to.UserResponseTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFacade {
    UserResponseTO create(UserRequestTO to);
    void update(UserRequestTO to);
    void delete(long id);
    List<UserResponseTO> findAll();
    UserResponseTO findById(long id);
}
