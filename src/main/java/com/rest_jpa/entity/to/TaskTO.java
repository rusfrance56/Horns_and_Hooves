package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private String priority;
    private String department;
    private Long userId;
    private ItemTO item;
    private CustomerOrderResponseTO orderRequestTO;

    public TaskTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus().name();
        this.priority = task.getPriority().name();
        this.department = task.getDepartment().name();
        this.userId = task.getUser() != null ? task.getUser().getId() : null;
        this.item = new ItemTO(task.getItem());
        this.orderRequestTO = new CustomerOrderResponseTO(task.getOrder());
    }
}
