package com.rest_jpa.entity.to;

import com.rest_jpa.entity.BaseEntity;

public class DepartmentShortTO extends BaseEntity{

    public DepartmentShortTO() {
    }

    public DepartmentShortTO(Long id, String name) {
        super(id, name);
    }
}
