package com.rest_jpa.facade;

import com.rest_jpa.entity.to.UserTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFacade {
    UserTO create(UserTO to);
    void update(UserTO to);
    void delete(long id);
    List<UserTO> findAll();
    UserTO findById(long id);
}
