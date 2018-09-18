package com.rest_jpa.facade;

import com.rest_jpa.entity.to.TaskTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskFacade {
    TaskTO create(TaskTO to);
    void update(TaskTO to);
    void delete(long id);
    List<TaskTO> findAll();
    TaskTO findById(long id);
}
