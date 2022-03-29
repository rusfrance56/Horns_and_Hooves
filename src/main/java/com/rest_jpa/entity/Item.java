package com.rest_jpa.entity;

import com.rest_jpa.entity.to.ItemTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item extends BaseEntity {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "cost")
    private Double cost;

    public Item(ItemTO to) {
        super(to.getName(), to.getDescription());
        this.imageUrl = to.getImageUrl();
        this.cost = to.getCost();
    }
}
