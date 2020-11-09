package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.entity.to.PersonTO;
import com.rest_jpa.entity.to.TaskTO;
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
    private CustomerOrderService customerOrderService;

    @Override
    public PersonTO create(PersonTO to) {
        Person person = new Person();
        setPersonParametersFromTO(person, to);
        Long newPersonId = personService.create(person).getId();
        to.setId(newPersonId);
        return to;
    }

    @Override
    public void update(PersonTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        Person person = personService.findById(to.getId());
        setPersonParametersFromTO(person, to);
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

    private void setPersonParametersFromTO(Person person, PersonTO to) {
        person.setName(to.getName());
        person.setSurname(to.getSurname());
        person.setMiddleName(to.getMiddleName());
        person.setDepartment(to.getDepartment() != null ? Department.valueOf(to.getDepartment()) : null);
        person.setAddress(to.getAddress());
        person.setEmail(to.getEmail());
        person.setPhone(to.getPhone());
        if (!to.getTasks().isEmpty()) {
            person.setTasks(taskService.findAllByIds(to.getTasks().stream().map(TaskTO::getId).collect(Collectors.toList())));
        }
        if (!to.getOrders().isEmpty()) {
            person.setOrders(customerOrderService.findAllByIds(to.getOrders().stream().map(CustomerOrderTO::getId).collect(Collectors.toList())));
        }
    }
}
