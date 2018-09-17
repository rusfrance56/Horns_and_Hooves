package com.rest_jpa.facade;

import com.rest_jpa.entity.to.PersonTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonFacade {
    PersonTO create(PersonTO to);
    void update(PersonTO to);
    void delete(long id);
    List<PersonTO> findAll();
    PersonTO findById(long id);
}
