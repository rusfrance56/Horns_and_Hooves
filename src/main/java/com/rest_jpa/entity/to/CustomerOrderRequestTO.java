package com.rest_jpa.entity.to;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerOrderRequestTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private List<Long> items = new ArrayList<>();
}
