package com.rest_jpa.entity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rest_jpa.enumTypes.OrderType;
import com.rest_jpa.utils.CustomDateDeserializer;

import java.util.Date;


/**
 * Created by User on 26.09.2016.
 */
public class OrderRequest {
    private long id;
    private String name;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;
    private long employee_id;
    private long department_id;

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

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }
}
