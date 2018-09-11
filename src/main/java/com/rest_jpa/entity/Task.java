package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "priority")
    private TaskPriority priority;

    @Column(name = "department")
    private Department department;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getAssigned() {
        return isAssigned;
    }

    public void setAssigned(Boolean assigned) {
        isAssigned = assigned;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

