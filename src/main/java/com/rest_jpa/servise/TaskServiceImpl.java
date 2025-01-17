package com.rest_jpa.servise;

import com.rest_jpa.entity.Task;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.TASKS_NOT_FOUND;
import static com.rest_jpa.exceptions.ErrorKey.TASK_NOT_FOUND;
import static com.rest_jpa.utils.SecurityHelper.getCurrentUser;
import static com.rest_jpa.utils.SecurityHelper.hasAdminRole;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void update(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        if (hasAdminRole()) {
            return checkNotNullAndNotEmpty(taskRepository.findAll(), TASKS_NOT_FOUND);
        } else {
            return checkNotNullAndNotEmpty(taskRepository.findAllByUser_UserName(getCurrentUser().getUsername()),
                    TASKS_NOT_FOUND);
        }
    }

    @Override
    public Task findById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new ApplicationException(TASK_NOT_FOUND, id));
    }

    @Override
    public List<Task> findAllByIds(List<Long> taskIds) {
        return checkNotNullAndNotEmpty(taskRepository.findAllById(taskIds), TASKS_NOT_FOUND);
    }
}
