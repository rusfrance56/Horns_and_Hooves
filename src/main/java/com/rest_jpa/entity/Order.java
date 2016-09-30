package com.rest_jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest_jpa.enumTypes.OrderType;
import com.rest_jpa.utils.CustomDateDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by User on 26.09.2016.
 */
@Entity
@Table(schema = "public", name = "order")
public class Order {
    @Id
    @GeneratedValue(generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq")
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String orderName) {
        this.name = orderName;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType orderType) {
        this.type = orderType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
