package com.rest_jpa.entity.to;

import com.rest_jpa.entity.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderResponseTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private List<ItemTO> items = new ArrayList<>();

    public CustomerOrderResponseTO(CustomerOrder order) {
        this.id = order.getId();
        this.name = order.getName();
        this.description = order.getDescription();
        this.dueDate = order.getDueDate();
        this.status = order.getStatus().name();
        this.items = order.getItems().stream().map(ItemTO::new).collect(Collectors.toList());
    }
}
