package com.rest_jpa.facade;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.Task;
import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.servise.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.PERSON_NOT_FOUND;

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
        checkNotNull(person, PERSON_NOT_FOUND, to.getId());
        person.setName(to.getName());
        person.setSurname(to.getSurname());
        person.setMiddleName(to.getMiddleName());
        person.setDepartment(Department.valueOf(to.getDepartment()));
        person.setAddress(to.getAddress());
        person.setEmail(to.getEmail());
        person.setPhone(to.getPhone());
        person.setTasks(to.getTasks().stream().map(Task::new).collect(Collectors.toList()));
        person.setOrders(to.getOrders().stream().map(CustomerOrder::new).collect(Collectors.toList()));
        personService.update(person);
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
