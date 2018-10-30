package com.rest_jpa.facade;

import com.rest_jpa.entity.to.PersonRequestTO;
import com.rest_jpa.entity.to.PersonTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonFacade {
    PersonTO create(PersonRequestTO to);
    void update(PersonRequestTO to);
    void delete(long id);
    List<PersonTO> findAll();
    PersonTO findById(long id);
}
