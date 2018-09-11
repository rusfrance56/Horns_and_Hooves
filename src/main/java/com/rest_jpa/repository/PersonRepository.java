package com.rest_jpa.repository;

import com.rest_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
//    List<Person> findAllByDepartmentId(long id);
}
