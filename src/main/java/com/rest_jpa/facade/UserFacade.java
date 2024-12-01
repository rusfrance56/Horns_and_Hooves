package com.rest_jpa.facade;

import com.rest_jpa.entity.to.UserRequestTO;
import com.rest_jpa.entity.to.UserResponseTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFacade {
    UserResponseTO create(UserRequestTO to);
    void update(UserRequestTO to);
    void delete(long id);
    List<UserResponseTO> findAll();
    Page<UserResponseTO> findAllWithPagination(int page, int size);
    UserResponseTO findById(long id);
    List<UserResponseTO> findByDepartment(String department);
}
