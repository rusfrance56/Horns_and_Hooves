package com.rest_jpa.facade;

import com.rest_jpa.entity.Person;
import com.rest_jpa.entity.to.EmployeeTO;
import com.rest_jpa.servise.DepartmentService;
import com.rest_jpa.servise.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Person create(EmployeeTO to) {
        Department department = departmentService.findById(to.getDepartment().getId());
        if (department != null) {
            Person newPerson = new Person(to);
            newPerson.setDepartment(department);
            return employeeService.create(newPerson);
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
//        throw new ApplicationException(ErrorKey.TEST_KEY, "param1", "param2");
        return employeeService.findAll();
    }

    @Override
    public Person findById(long id) {
        return employeeService.findById(id);
    }

    @Override
    public List<Person> findAllByDepartmentId(long id) {
       return employeeService.findAllByDepartmentId(id);
    }

    @Override
    public Person update(EmployeeTO to) {
        Department department = departmentService.findById(to.getDepartment().getId());
        Person person = employeeService.findById(to.getId());

        if (person != null && department != null) {
            person.setId(person.getId());
            person.setName(to.getName());
            person.setSurName(to.getSurName());
            person.setMiddleName(to.getMiddleName());
            person.setDepartment(department);
            return employeeService.update(person);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        employeeService.delete(id);
    }
}
