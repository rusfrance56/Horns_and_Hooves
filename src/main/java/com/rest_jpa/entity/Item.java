package com.rest_jpa.entity;

import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.enumTypes.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item extends BaseEntity {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "cost")
    private Double cost;

    public Item(ItemTO to) {
        super(to.getName(), to.getDescription());
        this.imageUrl = to.getImageUrl();
        this.department = Department.valueOf(to.getDepartment());
        this.cost = to.getCost();
    }

    public static Item updateEntityFromTO(Item entity, ItemTO to) {
        entity.setName(to.getName());
        entity.setDescription(to.getDescription());
        entity.setImageUrl(to.getImageUrl());
        entity.setDepartment(Department.valueOf(to.getDepartment()));
        entity.setCost(to.getCost());
        return entity;
    }
}
