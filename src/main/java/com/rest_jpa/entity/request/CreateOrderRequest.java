package com.rest_jpa.entity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest_jpa.enumTypes.OrderType;
import com.rest_jpa.utils.CustomDateDeserializer;

import java.util.Date;


/**
 * Created by User on 26.09.2016.
 */
public class CreateOrderRequest {
    private long id;
    private String name;
    private OrderType type;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;
    private long employee_id;

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

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }
}
