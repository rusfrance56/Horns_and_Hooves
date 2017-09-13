package com.rest_jpa.entity.to;

import com.rest_jpa.entity.BaseEntity;

public class EmployeeShortTO extends BaseEntity{

    private String surname;

    public EmployeeShortTO() {
    }

    public EmployeeShortTO(Long id, String name, String surname) {
        super(id, name);
        this.surname = surname;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
