package com.rest_jpa.entity.to;

import com.rest_jpa.entity.CustomerOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderTO {

    private long id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private String status;
    private List<ItemTO> items = new ArrayList<>();

    public CustomerOrderTO() {
    }

    public CustomerOrderTO(long id, String name, String description, LocalDateTime dueDate,
                           String status, List<ItemTO> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.items = items;
    }

    public CustomerOrderTO(CustomerOrder order) {
        this.id = order.getId();
        this.name = order.getName();
        this.description = order.getDescription();
        this.dueDate = order.getDueDate();
        this.status = order.getStatus().name();
        this.items = order.getItems().stream().map(ItemTO::new).collect(Collectors.toList());
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

    public List<ItemTO> getItems() {
        return items;
    }

    public void setItems(List<ItemTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CustomerOrderTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
