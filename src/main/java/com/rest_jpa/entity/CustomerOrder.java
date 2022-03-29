package com.rest_jpa.entity;

import com.rest_jpa.enumTypes.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
public class CustomerOrder extends BaseEntity {

    @Column(name = "due_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.LAZY)/*, cascade = CascadeType.ALL*/
    @JoinTable(name = "orders_items",
            joinColumns = @JoinColumn(name = "customer_order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
