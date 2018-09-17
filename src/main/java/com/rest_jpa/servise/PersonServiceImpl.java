package com.rest_jpa.servise;

import com.rest_jpa.entity.Person;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.PERSONS_NOT_FOUND;
import static com.rest_jpa.exceptions.ErrorKey.PERSON_NOT_FOUND;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(long id) {
//        Person person = personRepository.getOne(id);
//        orderHelper.reassignmentOrders(person);
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return checkNotNullAndNotEmpty(personRepository.findAll(), PERSONS_NOT_FOUND);
    }

    @Override
    public Person findById(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(() -> new ApplicationException(PERSON_NOT_FOUND, id));
    }

}
