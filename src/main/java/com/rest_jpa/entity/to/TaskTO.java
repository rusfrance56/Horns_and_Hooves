package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Task;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.TaskPriority;
import com.rest_jpa.enumTypes.TaskStatus;

import java.time.LocalDateTime;

public class TaskTO {

    private long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private String priority;
    private String department;

    public TaskTO() {
    }

    public TaskTO(long id, String name, String description, LocalDateTime dueDate,
                  TaskStatus status, TaskPriority priority, Department department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status.name();
        this.priority = priority.name();
        this.department = department.name();
    }

    public TaskTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus().name();
        this.priority = task.getPriority().name();
        this.department = task.getDepartment().name();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "TaskTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
