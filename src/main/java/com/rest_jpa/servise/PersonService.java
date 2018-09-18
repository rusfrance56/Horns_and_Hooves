package com.rest_jpa.servise;

import com.rest_jpa.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    Person create(Person person);
    List<Person> findAll();
    Person findById(long id);
    void update(Person person);
    void delete(long id);
}
