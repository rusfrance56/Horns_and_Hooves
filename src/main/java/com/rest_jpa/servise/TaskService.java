package com.rest_jpa.servise;

import com.rest_jpa.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task create(Task task);
    void update(Task task);
    void delete(long id);
    List<Task> findAll();
    Task findById(long id);
    List<Task> findAllByIds(List<Long> taskIds);
}
