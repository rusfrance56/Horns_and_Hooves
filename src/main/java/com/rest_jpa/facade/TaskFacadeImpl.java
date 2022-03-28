package com.rest_jpa.facade;

import com.rest_jpa.entity.User;
import com.rest_jpa.entity.Task;
import com.rest_jpa.entity.to.TaskTO;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.TaskPriority;
import com.rest_jpa.enumTypes.TaskStatus;
import com.rest_jpa.servise.UserService;
import com.rest_jpa.servise.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
public class TaskFacadeImpl implements TaskFacade {

    private TaskService taskService;
    private UserService userService;

    public TaskFacadeImpl(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public TaskTO create(TaskTO to) {
        Task task = new Task(to);
        if (to.getUserId() != null) {
            User user = userService.findById(to.getUserId());
            task.setUser(user);
        } else {
            task.setUser(null);
        }
        to.setId(taskService.create(task).getId());
        return to;
    }

    @Override
    public void update(TaskTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        Task task = taskService.findById(to.getId());
        task.setName(to.getName());
        task.setDescription(to.getDescription());
        task.setDueDate(to.getDueDate());
        task.setStatus(TaskStatus.valueOf(to.getStatus()));
        task.setPriority(TaskPriority.valueOf(to.getPriority()));
        task.setDepartment(Department.valueOf(to.getDepartment()));
        if (to.getUserId() != null) {
            User user = userService.findById(to.getUserId());
            task.setUser(user);
        } else {
            task.setUser(null);
        }
        taskService.update(task);
    }

    @Override
    public void delete(long id) {
        taskService.delete(id);
    }

    @Override
    public List<TaskTO> findAll() {
        List<Task> all = taskService.findAll();
        return all.stream().map(TaskTO::new).collect(Collectors.toList());
    }

    @Override
    public TaskTO findById(long id) {
        Task task = taskService.findById(id);
        return new TaskTO(task);
    }
}
