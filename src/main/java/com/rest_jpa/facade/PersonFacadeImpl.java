package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonService personService;

    @Override
    public PersonTO create(PersonTO to) {
        Person person = new Person(to);
        to.setId(personService.create(person).getId());
        return to;
    }

    @Override
    public void update(PersonTO to) {
        Person person = personService.findById(to.getId());
        if (person != null) {
            person.setId(to.getId());
            person.setName(to.getName());
            person.setSurname(to.getSurname());
            person.setMiddleName(to.getMiddleName());
            person.setDepartment(to.getDepartment());
            person.setAddress(to.getAddress());
            person.setEmail(to.getEmail());
            person.setPhone(to.getPhone());
            personService.update(person);
        }
    }

    @Override
    public void delete(long id) {
        personService.delete(id);
    }

    @Override
    public List<PersonTO> findAll() {
        List<Person> all = personService.findAll();
        return all.stream().map(PersonTO::new).collect(Collectors.toList());
    }

    @Override
    public PersonTO findById(long id) {
        Person person = personService.findById(id);
        return new PersonTO(person);
    }
}
