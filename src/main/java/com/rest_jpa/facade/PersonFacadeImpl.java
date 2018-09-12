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
    private PersonService employeeService;

    @Override
    public Person create(PersonTO to) {
       /* Department department = departmentService.findById(to.getDepartment().getId());
        if (department != null) {
            Person newPerson = new Person(to);
            newPerson.setDepartment(department);
            return employeeService.create(newPerson);
        }*/
        return null;
    }

    @Override
    public List<PersonTO> findAll() {
//        throw new ApplicationException(ErrorKey.TEST_KEY, "param1", "param2");
        List<Person> all = employeeService.findAll();
        return all.stream().map(PersonTO::new).collect(Collectors.toList());
    }

    @Override
    public Person findById(long id) {
        return employeeService.findById(id);
    }
/*

    @Override
    public List<Person> findAllByDepartmentId(long id) {
       return employeeService.findAllByDepartmentId(id);
    }
*/

    @Override
    public Person update(PersonTO to) {
        /*Department department = departmentService.findById(to.getDepartment().getId());
        Person person = employeeService.findById(to.getId());

        if (person != null && department != null) {
            person.setId(person.getId());
            person.setName(to.getName());
            person.setSurName(to.getSurName());
            person.setMiddleName(to.getMiddleName());
            person.setDepartment(department);
            return employeeService.update(person);
        }*/
        return null;
    }

    @Override
    public void delete(long id) {
        employeeService.delete(id);
    }
}
