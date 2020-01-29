package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.PersonRequestTO;
import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.servise.CustomerOrderService;
import com.rest_jpa.servise.PersonService;
import com.rest_jpa.servise.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonService personService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CustomerOrderService orderService;

    @Override
    public PersonTO create(PersonRequestTO to) {
        Person person = new Person();
        setParameters(person, to);

        Long newPersonId = personService.create(person).getId();
        PersonTO personTO = new PersonTO(person);
        to.setId(newPersonId);
        return personTO;
    }

    @Override
    public void update(PersonRequestTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, to.getId());
        Person person = personService.findById(to.getId());
        setParameters(person, to);
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

    private void setParameters(Person person, PersonRequestTO to) {
        person.setName(to.getName());
        person.setSurname(to.getSurname());
        person.setMiddleName(to.getMiddleName());
        person.setDepartment(to.getDepartment() != null ? Department.valueOf(to.getDepartment()) : null);
        person.setAddress(to.getAddress());
        person.setEmail(to.getEmail());
        person.setPhone(to.getPhone());
        if (!to.getTasks().isEmpty()) {
            person.setTasks(taskService.findAllByIds(to.getTasks()));
        }
        if (!to.getOrders().isEmpty()) {
            person.setOrders(orderService.findAllByIds(to.getOrders()));
        }
    }
}
