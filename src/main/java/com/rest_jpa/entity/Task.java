package com.rest_jpa.entity;

import com.rest_jpa.entity.to.TaskTO;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.TaskPriority;
import com.rest_jpa.enumTypes.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "task")
public class Task extends BaseEntity{

    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "priority")
    private TaskPriority priority;

    @Column(name = "department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "person_id")
//    @JsonIgnore
    private Person person;

    public Task() {
    }

    public Task(TaskTO to) {
        this.name = to.getName();
        this.description = to.getDescription();
        this.dueDate = to.getDueDate();
        this.status = TaskStatus.valueOf(to.getStatus());
        this.priority = TaskPriority.valueOf(to.getPriority());
        this.department = Department.valueOf(to.getDepartment());
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

