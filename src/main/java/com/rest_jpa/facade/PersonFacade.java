package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.PersonTO;

import java.util.List;

public interface PersonFacade {
    Person create(PersonTO employee);
    Person update(PersonTO employee);
    void delete(long id);
    List<PersonTO> findAll();
    Person findById(long id);
    //    List<Person> findAllByDepartmentId(long id);
}
