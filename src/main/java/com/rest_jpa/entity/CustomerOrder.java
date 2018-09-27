package com.rest_jpa.entity;

import com.rest_jpa.entity.to.CustomerOrderTO;
import com.rest_jpa.enumTypes.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(schema = "public", name = "customer_order")
public class CustomerOrder extends BaseEntity{

    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_items",
            joinColumns = @JoinColumn(name = "customer_order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public CustomerOrder() {
    }

    public CustomerOrder(CustomerOrderTO to) {
        this.id = to.getId();
        this.name = to.getName();
        this.description = to.getDescription();
        this.dueDate = to.getDueDate();
        this.status = to.getStatus() != null ? OrderStatus.valueOf(to.getStatus()) : null;
        this.items = to.getItems().stream().map(Item::new).collect(Collectors.toSet());
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "dueDate=" + dueDate +
                ", status=" + status +
                ", items=" + items +
                ", person=" + person +
                ", id=" + id +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
