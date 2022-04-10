package com.rest_jpa.repository;

import com.rest_jpa.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryCustom {

    List<Task> findAll();
}
